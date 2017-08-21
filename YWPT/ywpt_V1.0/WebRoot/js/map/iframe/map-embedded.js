dojo.require("esri.map");
dojo.require("esri.layers.ArcGISTiledMapServiceLayer");
dojo.require("esri.layers.FeatureLayer");
dojo.require("esri.tasks.identify");
dojo.require("esri.layers.graphics");
dojo.require("esri.geometry.Point");
dojo.require("esri.symbols.PictureMarkerSymbol");
dojo.require("esri.toolbars.edit");
dojo.require("esri.tasks.geometry");

var EmbeddedMap = (function(){
	
	var //地图窗口
		bMap,
		
		// 地图窗口id
		mapContainer='',
				
		//当前活动图层
		currentLayer,
		
		// 当前操作图层
		currentActiveLayer,
		
		//地图点击模式,表示执行一次getPoint地图上是否允许多次触发点击事件，默认不允许
		isMultiClick = false,
		
		//是否允许触发地图点击事件
		isClickable = true,
		
		//记录生成的Graphic与对应的layerId之间的关系的对象
		obj = {},
		
		isFromGraphic = false,
		
		//获取非起点 终点成功后的回调函数
		currentCallback,
				
		//当前graphic的Id
		currentGraphicId,
						
		//是否启用捕捉
		isSnapping = false,
		
		//事件句柄
		eventHandler = {},
		
		//路径分析结果
		route,
		routeGraphicsLayer,
		routeCallback,
		//编辑工具条
		editTools,
		
		// 初始化范围
		initExtent,
		
		//地图初始化的默认参数	
		options = {
			//地图容器的Id
			mapContainer: "mapDiv_signal",
			
			//底图的服务路径
			baseMapUrl: baseServiceURL.basemapnew.url,
			
			//路径服务地址
			routeServiceUrl: baseServiceURL.route.url,
			
			snappingUrl: baseServiceURL.snappingRoad.url,
			
			// 几何服务地址
			geometryServiceUrl : baseServiceURL.geometry.url,
			
			picUrl: basePath + "images/lukoutupian.png",
			
			//图片大小
			picHeight: 20,
			picWidth: 20
	
		}; 
	
	//对外开放的接口函数
	var api = {
		initMap: initMap,
		startEdit: startEdit,
		stopEdit: stopEdit,
		setPolygon: setPolygon,
		startGraphicClick: startGraphicClick,
		startClick: startClick,
		stopClick: stopClick,
		getPoint: getPoint,
		getRoute: getRoute,
		getIdentifyRoads: getIdentifyRoads,
		getGraphic: getGraphic,
		getGraphicById: getGraphicById,
		getFromLayer: getFromLayer,
		showPoints: showPoints,
		showRoute: showRoute,
		clearGraphic: clearGraphic,
		clearLayer: clearLayer,
		clearTargetGraphic: clearTargetGraphic,
		clearGraphicsLayer: clearGraphicsLayer,
		showNavigation: showNavigation,
		getIdentifyResults: getIdentifyResults,
		query: query
	};
	
	/**
	 * 初始地图
	 * 如果没有传入参数,则采用默认的options设置
	 * @param container 装载地图的html标签
	 * @param url 地图的服务路径
	 * @param config 地图的配置参数
	 * */
	function initMap(container, url, config) {
		//底图
		var baseMap;
		//初始化地图
		if (Object.prototype.toString.call(config) !== "[object Object]") {
			config = null;
		}
		
		mapContainer = container || options.mapContainer;
		
		bMap = new esri.Map(mapContainer, config || {slider:true,sliderStyle:"large", minScale:75000, logo:false});
		baseMap = new esri.layers.ArcGISTiledMapServiceLayer(url || options.baseMapUrl);
		bMap.addLayer(baseMap);
		
		routeGraphicsLayer = new esri.layers.GraphicsLayer({id: "routeLayer"});
		bMap.addLayer(routeGraphicsLayer);
		
		initExtent = new esri.geometry.Extent(108.79421457225156,34.185600937445486,109.06190393540747,34.361443105776345,new esri.SpatialReference(4326));
				
		return this;
	}
	
	/**
	 * 是否显示导航按钮
	 * @return
	 */
	function showNavigation(){
		/**
		 * 设置导航按钮
		 */
		new itmap.arcgis.navigation({
			mapC : bMap,
			initExtent : initExtent,
			container : "mapNavigationBox"
		});
		document.getElementById(mapContainer+"_zoom_slider").style.marginTop="45px";
	}

	/**
	 * 返回graphic
	 * */
	function setPolygon(layerId,rings,displayParams) {
		var layer,
			poly,
			polyGraphic;
		
		layer = bMap.getLayer(layerId) || bMap.addLayer(new esri.layers.GraphicsLayer({id: layerId}));
		poly = new esri.geometry.Polygon(new esri.SpatialReference({wkid:4326}));
		var newrings = [];
		for(var i=0,len=rings.length;i<len;i++){
			newrings.push([rings[i][0],rings[i][1]]);
		}
		poly.addRing(newrings);
		polyGraphic = new esri.Graphic(poly, new esri.symbol.SimpleFillSymbol(
				   esri.symbol.SimpleFillSymbol.STYLE_SOLID,
				   new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_DASHDOT,
				   new dojo.Color([255,0,0]), 2),
				   new dojo.Color([255,255,0,0.25])), null, null);
		
		layer.add(polyGraphic);
		currentActiveLayer = layer;

		if(displayParams){
			var geoParams = caculatePolygonGeometry(poly);
			var area = geoParams.areas[0].toFixed(2);
			var lineLength = geoParams.lineLength[0].toFixed(2);
			var text = "面积："+area+"平方米";
			displayGeometryParams(poly,text);
		}
		
		return methodAccess(polyGraphic);
	}

	/**
	 * 计算多边形参数
	 * @param polygon
	 * @return
	 */
	function caculatePolygonGeometry(polygon){
		var oldRings = polygon.rings;
		var newPolygon = new esri.geometry.Polygon(new esri.SpatialReference({wkid:4326}));
		var newPolyline = new esri.geometry.Polyline(new esri.SpatialReference({wkid:4326}));
		var i,len;
		var result = {};
		for(var i=0,len=oldRings.length;i<len;i++){
			var ring = oldRings[i];
			var newPath = [];
			if(!esri.geometry.isClockwise(ring)){
				var newRing = [];
				for(var j=ring.length-1;j>=0;j--){
					newRing.push([ring[j][0],ring[j][1]]);
					if(j!=0){
						newPath.push([ring[j][0],ring[j][1]]);
					}
				}
				newPolygon.addRing(newRing);
				newPolyline.addPath(newPath);
			}else{
				newPolygon.addRing(ring);
				for(var s=0,pathLen=ring.length;s<pathLen;s++){
					if(s!=pathLen-1){
						newPath.push([ring[s][0],ring[s][1]]);
					}
				}
			}
			newPolyline.addPath(newPath);
		}
		result.areas = esri.geometry.geodesicAreas([newPolygon],esri.Units.SQUARE_METERS);
		result.lineLength = esri.geometry.geodesicLengths([newPolyline],esri.Units.SQUARE_METERS);
		return result;
	}
	
	/**
	 * 显示几何参数
	 * @param polygon
	 * @return
	 */
	function displayGeometryParams(polygon,text){
		var centerPoint = polygon.getExtent().getCenter();
		var textSymbol = new esri.symbol.TextSymbol(text);
		var font  = new esri.symbol.Font();
		font.setSize("14pt");
		font.setWeight(esri.symbol.Font.WEIGHT_BOLD);
		textSymbol.setFont(font);
		textSymbol.setColor(new dojo.Color([255,0,0,1]));
		var newGraphic = new esri.Graphic(centerPoint,textSymbol);
		bMap.graphics.add(newGraphic);
	}

	/**
	 * 编辑
	 * */
	function startEdit(func) {
		eventHandler.edit = dojo.connect(currentActiveLayer,"onClick",function(event){
			dojo.stopEvent(event);
			editClick(event.graphic);
			if(func){
				func(event.graphic);
			}
		});
		
	}
	
	function editClick(graphic) {
		
		if (graphic) {
			var _options = {
				allowAddVertices : true,
				allowDeletevertices : true,
				uniformScaling : false
			};
			if(!editTools){
				editTools = new esri.toolbars.Edit(bMap);
			}
			var _toolsOptions = esri.toolbars.Edit.MOVE | esri.toolbars.Edit.EDIT_VERTICES | esri.toolbars.Edit.SCALE | esri.toolbars.Edit.ROTATE;
			editTools.activate(_toolsOptions,graphic,_options);
			
		}
	}

	/**
	 * 停止编辑
	 * */
	function stopEdit(func) {
		if(editTools){
			editTools.deactivate();
			editTools = null;
		}
		dojo.disconnect(eventHandler.graphicMoveStop);
		dojo.disconnect(eventHandler.edit);
	}
	
	/**
	 * 从graphic图层上获取点位置
	 * */
	function startGraphicClick(layerId) {
		//如果设置从grahpic获取点击对象，需要将所有的graphic放到id为"graphicsAccess"的图层上
		var graphicsLayer;
		isFromGraphic = true;
		graphicsLayer = bMap.getLayer(layerId) || bMap.addLayer(new esri.layers.GraphicsLayer({id: layerId}));
		eventHandler.graphicClick = dojo.connect(graphicsLayer, "onClick", graphicClick);
		return this;
	}
	/**
	 * 是否启用捕捉
	 * @param flag true or false true启用捕捉，false则是点击
	 * @param snappingUrl flag为true时 如果需要设置捕捉图层，则设置此参数,
	 * 如果flag为true时不设置此参数则采取默认；flag为false时设置此参数则不起作用
	 * */	
	function startClick(flag, snappingUrl) {	
		//捕捉
		if (flag) {
			isSnapping = true;
			snappingUrl = snappingUrl || options.snappingUrl;
			enableSnapping(snappingUrl);
			//注册地图单击事件
			eventHandler.click = dojo.connect(bMap, "onClick", afterClick);	
			return this;
		}

		//不捕捉
		isClickable = true;
		isSnapping = false;
		
		//注册地图单击事件
		eventHandler.click = dojo.connect(bMap, "onClick", afterClick);	
		
		return this;
	}
	
	//取消地图点击事件
	function stopClick() {
		isFromGraphic = false;
		bMap.snappingManager.destroy();
		if (eventHandler.click) {
			dojo.disconnect(eventHandler.click);
		}
		if (eventHandler.graphicClick) {
			dojo.disconnect(eventHandler.graphicClick);
		}
		if (eventHandler.zoom) {
			dojo.disconnect(eventHandler.zoom);
		}
		isClickable = false;
		return this;
	}
	
	function enableSnapping(snappingUrl) {
		var snappingLayer = new esri.layers.FeatureLayer(snappingUrl);
		var	snappingParams = {
				map: bMap,
				alwaysSnap: true,
				tolerance: 10,
				layerInfos: [{layer: snappingLayer}]
			};
//		eventHandler.zoom = dojo.connect(bMap, "onZoom", function(extent, factor, achor) {
//			console.log(factor);
//			snappingParams.tolerance = snappingParams.tolerance * factor;
//			bMap.snappingManager.destroy();
//			bMap.enableSnapping(snappingParams);			
//		});
		
		bMap.enableSnapping(snappingParams);
	}
	
	/**
	 * 依据空间关系查询
	 * @url 查询的服务路径
	 * @params 参数对象 空间关系参数键值为type,值包括overlaps,
	 * contains,crosses,envelopeintersects,indexintersects,intersects,relation,touches,within
	 * 其它键值与esri的参数一致
	 * */
	function query(url, params, func) {
		var queryParams = new esri.tasks.Query(),
			//空间关系类型
			spatialType;
		if (typeof func != "function") return;
		queryParams.geometry = params.geometry || bMap.extent;
		type = "SPATIAL_REL_" + (params.type || "overlaps").toUpperCase();
		queryParams.spatialRelationship = esri.tasks.Query[type];
		
		queryParams.returnGeometry = params.returnGeometry && true;
		queryParams.outFields = params.outFields || ["DLMC"];
		queryParams.orderByFields = params.orderByFields && ["DLMC"];
		queryParams.where = params.where || "";
		queryParams.searchField = params.searchField || ["DLMC"];
		queryTask = new esri.tasks.QueryTask(url);
		queryTask.execute(queryParams, func);
	}
	
	/**
	 * 获取点位置
	 * @param layerId 生成的Graphic添加到的图层
	 * @param graphicId 生成的Graphic的id,设置此参数后每次点击地图会清除对应graphic，并将此id赋给新的graphic，保证其唯一存在；如果不指定
	 * graphicId，则生成的此graphic将无法清除，除非调用clearLayer方法
	 * @param multiClick 是否允许多次点击，如果省略此参数则认为是不允许
	 * @param func 回调函数,点击地图后会调用此回调函数
	 * */
	function getPoint(layerId, graphicId, multiClick, func) {
		currentLayer = bMap.getLayer(layerId) || bMap.addLayer(new esri.layers.GraphicsLayer({id: layerId}));
		if (typeof multiClick == "function") {
			func = multiClick;
			multiClick = false;
		}
		
		
		if (typeof graphicId == "function") {
			func = graphicId;
			multiClick = false;
			graphicId = null;
		}

		isMultiClick = !!multiClick;
		//如果graphicId存在，则设置Obj[layerId]
		if (graphicId) {
			obj[layerId] = obj[layerId] || {};
		}
		
		isClickable = true;
		
		currentGraphicId = graphicId;
		
		if (typeof func == "function") {
			currentCallback = func;
		}

		return this;
	}
	
	function afterClick(e) {	
		isSnapping ? getSnappingPoint(e) : getClickPoint(e);
	}
	
	function getSnappingPoint(e) {

		var //遍历变量
			i = 0,
			
			//返回的graphic
			graphic,
			//返回的延迟对象
			deferred = bMap.snappingManager.getSnappingPoint(e.screenPoint);
		
		deferred.then(function(value){
			isClickable ? dealWithPoint(value) : "";
		});
	}
	
	function getClickPoint(e) {
		isClickable ? dealWithPoint(e.mapPoint) : "";
	}
	
	function dealWithPoint(point) {
		var tempObj,
			tempGraphic,
			graphic;
		if (!currentLayer) return;
		tempObj = obj[currentLayer.id];
		graphic = addPoint(point, options.picUrl);
		if (currentGraphicId) {
			tempGraphic = tempObj[currentGraphicId];
			currentLayer.remove(tempGraphic);
			tempObj[currentGraphicId] = graphic;
		}
		graphic.hide();
		currentLayer.add(graphic);
		//bMap.centerAt(point);
		isClickable = isMultiClick ? true : false; 
		
//		currentCallback(methodAccess(graphic));
		methodAccess(graphic);
		
		currentCallback(graphic);
//		currentLayer.redraw();
		graphic.show();
	}
	
	/**
	 * 获取点击的graphic
	 * */
	function getGraphic(layerId, graphicId, func) {
		currentLayer = bMap.getLayer(layerId) || bMap.addLayer(new esri.layers.GraphicsLayer({id: layerId}));
		
		//如果graphicId存在，则设置Obj[layerId]
		if (graphicId) {
			obj[layerId] = obj[layerId] || {};
		}
		
		currentGraphicId = graphicId;
		
		if (typeof func == "function") {
			currentCallback = func;
		}
		isFromGraphic = true;
		return this;
	}
	function graphicClick(e) {
		var tempObj,
			tempGraphic,
			newGraphic,
			graphic;

		if (!currentLayer || !isFromGraphic) return; 
		tempObj = obj[currentLayer.id];
		graphic = e.graphic;
		newGraphic = new esri.Graphic(graphic.geometry, new esri.symbol.PictureMarkerSymbol(options.picUrl, options.picWidth, options.picHeight),
					graphic.attributes, null);
		if (currentGraphicId) {
			tempGraphic = tempObj[currentGraphicId];
			currentLayer.remove(tempGraphic);
			tempObj[currentGraphicId] = newGraphic;
		}	
		newGraphic.hide();
		currentLayer.add(newGraphic);
		isFromGraphic = false;
//		currentCallback(methodAccess(newGraphic));
		methodAccess(newGraphic);
		currentCallback(newGraphic);
//		currentLayer.redraw();
		newGraphic.show();
	}
	/**
	 * 路径分析
	 * @param start 表示起点位置的graphic
	 * @param end 表示终点位置的graphic
	 * @param stop 表示停靠点位置的graphic,可以是graphic数组,也可以省略
	 * @param func 路径分析成功后的回调
	 * */
	function getRoute(start, end, stop, func) {
		var rooteTask,
			routeParams,
			stops;

		stop = stop || [];
		
		//没有传入stop参数
		if (typeof stop == "function") {
			func = stop;
			stop = [];
		}
		if (Object.prototype.toString.call(stop) != "[object Array]") {
			stop = [stop];
		}

		if (start && end) {
			routeTask = new esri.tasks.RouteTask(options.routeServiceUrl);
			routeParams = new esri.tasks.RouteParameters();
			routeParams.outputGeometryPrecisionUnits = "esriMeters";
			routeParams.stops = new esri.tasks.FeatureSet();
			stops = routeParams.stops.features;
			stops.push(start);
			Array.prototype.push.apply(stops, stop);	
			stops.push(end);
			routeParams.outSpatialReference = {"wkid":4326};
			routeTask.solve(routeParams, routeSuccess);			
		}
		
		routeCallback = func;
		
		return this;
	}
	
	function routeSuccess(solveResults) {
		dojo.forEach(solveResults.routeResults, function(routeResult, i) {
			routeGraphicsLayer.remove(route);
			route = routeResult.route;
			route.setSymbol(new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID,
					  		new dojo.Color([255, 0, 0, 0.5]), 5));
			//计算长度
			route.length = esri.geometry.geodesicLengths([route.geometry], esri.Units.METERS)[0]; 
			route.hide();
			routeGraphicsLayer.add(route);
			bMap.centerAt(route.geometry.getExtent().getCenter());
			methodAccess(route);
			routeCallback ? routeCallback(route) :　"";
//			routeGraphicsLayer.redraw();
			route.show();
		});
	}
	
	/**
	 * identify分析,求出经过某一点的所有道路名称
	 * @param url 查询的服务图层路径
	 * @param name 返回的字段名称
	 * @param layerIds 查询的图层id,数组类型
	 * @param point 点
	 * @param func 查询成功后的回调函数
	 * */
	function getIdentifyRoads(queryUrl, fieldName, layerIds, geometry, func) {
		var //存储遍历过程中数据集的道路名称
			tempRoadName,		
			//存储不重复的道路集名称,道路名称间以；隔开
			roadNames = "",		
			//正则表达式
			reg,
			params = new esri.tasks.IdentifyParameters(),
			identifyTask = new esri.tasks.IdentifyTask(queryUrl);
		if (!geometry) return;
		params.geometry = geometry;
		params.mapExtent = bMap.extent;
		params.layerOption = esri.tasks.IdentifyParameters.LAYER_OPTION_ALL;
		params.returnGeometry = true;

		params.tolerance = 1;
		params.layerIds = layerIds;
				
		identifyTask.execute(params, function(results){
			if(!results || results.length === 0) return this;
			for(var i = 0;i < results.length; i = i + 1){								
				tempRoadName = results[i].feature.attributes[fieldName];
				if(!tempRoadName || tempRoadName.length == 0) continue;
				
				reg = new RegExp(tempRoadName);
				if(!reg.test(roadNames)){
					roadNames = roadNames + tempRoadName + ";";
				}
			}
			
			//去掉最后一个";"
			roadNames = roadNames.substring(0, roadNames.length - 1);
			func ? func(roadNames) : "";
		});
		
		return this;
	}
	
	function getIdentifyResults(queryUrl, fieldName, layerIds, geometry, func) {
		var params = new esri.tasks.IdentifyParameters(),
			identifyTask = new esri.tasks.IdentifyTask(queryUrl);
		
		if (!geometry) return;
		params.geometry = geometry;
		params.mapExtent = bMap.extent;
		params.layerOption = esri.tasks.IdentifyParameters.LAYER_OPTION_ALL;
		params.returnGeometry = true;
	
		params.tolerance = 1;
		params.layerIds = layerIds;
	
		identifyTask.execute(params, function(results){
			var graphics = [],
				attribute,
				geometry,
				isExisted = false;

			for (var i = 0; i < results.length; i = i + 1) {
				attribute = results[i].feature.attributes[fieldName],
				geometry = results[i].feature.geometry;
				
				for (var j = 0; j < graphics.length; j = j + 1) {
					if (attribute == graphics[j].attributes[fieldName]) {
						graphics[j].geometry.addPath.apply(graphics[j].geometry, geometry.paths);
						isExisted = true;
						break;
					}
				}
				
				if (!isExisted) {
					graphics.push(results[i].feature);
				}
			}

			if (typeof func == "function") {
				func(graphics);
			}
		});
	}
	/**
	 * 选出多条道路时弹出对话框供选择
	 * @param roads; 包含道路graphic或者graphic数组。
	 * @param point; 弹出对话框的位置
	 * @param name; 存储道路名称的字段,可以省略
	 * */
	function showRoads(roads, point, name) {
		var infoWindow = bMap.infoWindow,
			html = "<div id='showroads'>",
			name = name || "DLMC",
			roadName;
			//名称-道路关联数组
			orm = {};
		
		if (!roads instanceof Array) roads = [roads];
		
		for (var i = 0, len = roads.length - 1; i <= len; i = i + 1) {
			roads[i].symbol.setColor(new dojo.Color([255, 0, 0]));
			roads[i].hide();
			roadName = roads[i].attributes[name];
			html = html + "<span>" + roadName + "</span><br>";
			orm[roadName] = roads[i];
		}
		
		html = html + "</div>";
		
		infoWindow.setTitle("选择道路");
		infoWindow.setContent(html);
		
		infoWindow.show(point);		
	}
	/**
	 * 在地图上显示点
	 * @param points 点数组，必须 。传入参数格式为{x:"",y:"",picUrl} 或者{x:"",y:"",nodename:"",nodeid:"",picUrl: ""}或者由它们构成的数组,
	 * picUrl若省略，则以默认图片代替
	 * @param layerId 图层id，必须。点显示到哪个图层中
	 * @graphicId graphic的唯一标识符。layerId和graphicId同时传入可保证此graphic唯一存在
	 * */
	function showPoints(points, layerId, graphicId) {

		var point,
			tempObj,
			tempGraphic,
			toString = Object.prototype.toString,
			layer = bMap.getLayer(layerId) || bMap.addLayer(new esri.layers.GraphicsLayer({id: layerId || "viewLayer"}));
		
		if(points == null || (toString.call(points) === "[object Array]" && points.length == 0)) return this;		
		//同时显示多个点
		if (toString.call(points) === "[object Array]" && typeof points[0] === "object") {
			
			while (points.length) {
				node = points.pop();
				point = new esri.geometry.Point(node.x, node.y);
				tempGraphic = addPoint(point, node.picUrl || options.picUrl);
				
				if (node.nodename) {
					tempGraphic.setAttributes({"nodename": node.nodename, "nodeid": node.nodeid});
				}
				layer.add(tempGraphic);
			}
			
			return this;			
		} 
		
		//显示单点
		point = new esri.geometry.Point(points.x, points.y);			

		//如果graphicId存在，则设置Obj[layerId]
		if (graphicId) {
			obj[layerId] = obj[layerId] || {};
			tempObj = obj[layerId];
			layer.remove(tempObj[graphicId]);
			tempGraphic = addPoint(point, points.picUrl);
			tempObj[graphicId] = tempGraphic;
		}
		
		tempGraphic = tempGraphic || addPoint(point, points.picUrl);
		//地图上添加点
		layer.add(tempGraphic);
		bMap.centerAt(point);
		
		return this;
	}
	
	/**
	 * 路径相关的点数据
	 * @param points。必须。参数格式为{start:[],end:[],stop:[],startPicUrl:""...},{other1},{other2}...
	 * @param layerId。要添加到的图层名，默认为“viewLayer”
	 * other1的格式为{name:"",coords:[[x,y],...],url:""}
	 * */
	function showRoute(points, layerId) {
		var arguments,
			layer,
			pointStart,
			pointEnd,
			tempCoord,
			tempUrl,
			tempLayer,
			stopGraphic = [],
			tempObj,
			stops,
			slice = Array.prototype.slice,
			//自定义名称的点数据
			otherPoint,
			otherPoints;
	
		//如果未传入参数则终止程序
		if (arguments.length === 0) return;	
		
		//layerId不是字符串,则points以后的参数都被认为是otherPoints;layerId不存在则otherPoint是为空数组
		otherPoints = layerId ?
				typeof layerId === "string" ? slice.call(arguments, 2) : slice.call(arguments, 1) : 
				[];
		
		layerId = layerId || "viewLayer";
		obj[layerId] = obj[layerId] || {};
		tempObj = obj[layerId];
		
		layer = bMap.getLayer(layerId) || bMap.addLayer(new esri.layers.GraphicsLayer({id: layerId}));
		pointStart = new esri.geometry.Point(points.start[0], points.start[1]);
		pointEnd = new esri.geometry.Point(points.end[0], points.end[1]);

		startGraphic = addPoint(pointStart, points.startUrl || options.picUrl);
		endGraphic = addPoint(pointEnd, points.endUrl || options.picUrl);
		
		//停靠点，可以有多个,格式[[x,y],...]
		stops = points.top;
		
		for(var j = 0; j < stops.length; j = j + 1) {			
			stopsGraphic.push(layer.add(addPoint(new esri.geometry.Point(stops[0], stops[1]), points.stopPicUrl || options.picUrl)));
		}
		
		
		layer.add(startGraphic);
		layer.add(endGraphic);
		
		tempObj["start"] = startGraphic;
		tempObj["end"] = endGraphic;
		tempObj["stop"] = stopGraphic;
				
		getRoute(startGraphic, endGraphic, stopGraphic);	
		
		for (var i = 1; i < otherPoints.length; i = i + 1) {
			otherPoint = otherPoints[i];
			tempLayer = bMap.getLayer(otherPoint["name"]) || bMap.addLayer(new esri.layers.GraphicsLayer({id: otherPoint["name"]}));
			tempCoord = otherPoint["coords"];
			tempUrl = otherPoint["url"];
	
			while (tempPoint.length) {
				tempLayer.add(addPoint(new esri.geometry.Point(tempPoint.pop()), tempUrl));
			}
		}
				
		return this;
	}
	
	/**
	 * 显示错误提示信息
	 * @param container dom元素节点
	 * */
	function showErrorMsg(container, msg) {
				
	}
	
	/**
	 * 获取某个图层的所有的graphic
	 * @param layerId 图层id
	 * */
	function getFromLayer(layerId) {
		var layer = bMap.getLayer(layerId),
			graphics;
		
		if (layer) {
			graphics = layer.graphics;
		}
		
		return graphics;
	}
	/**
	 * 获取指定id的graphic
	 * @param layerId
	 * @param graphicId
	 * */
	function getGraphicById(layerId, graphicId) {
		var tempObj;
		if (tempObj = obj[layerId]) {
			return methodAccess(tempObj[graphicId]);
		}
	}
	/**
	 * 清除指定的graphic
	 * @param layerId 图层id
	 * @param graphicId graphic的id
	 * */
	function clearGraphic(layerId, graphicId) {
		var layer,
			tempLayer,
			graphic;
		
		tempLayer = obj[layerId];
		if (tempLayer && tempLayer[graphicId]) {
			layer = bMap.getLayer(layerId);
			layer.remove(tempLayer[graphicId]);
		}		
	}
	
	/**
	 * 清除指定图层的指定graphic
	 * @param layerId 图层ID
	 * @param graphic esri.Graphic
	 */
	function clearTargetGraphic(graphic){
		if(!graphic){
			return;
		}
		var layer;
		layer = graphic.getLayer();
		if(layer){
			layer.remove(graphic);
		}
	}
	
	/**
	 * 清除指定图层的graphic
	 * @param layerId 图层id
	 * */
	function clearLayer(layerId) {
		var layer = bMap.getLayer(layerId);
		if (layer) {
			layer.clear();
		}
	}
	
	/**
	 * 清除map自带graphicsLayer里的graphic
	 */
	function clearGraphicsLayer(){
		bMap.graphics.clear();
	}
	
	/**
	 * 将graphic的方法暴露给外部
	 * @param graphic 需要暴露方法的graphic
	 * */
	function methodAccess(graphic) {
		var layer = graphic.getLayer();
		
		//设置样式
		graphic.setStyle = function(style) {
			style = esri.symbol.SimpleLineSymbol["STYLE_" + style.toUpperCase()];
			graphic.symbol.setStyle(style);			
		}
		
		//释放
		graphic.destroy = function() {
			layer ? layer.remove(graphic) : "";
		}
		graphic.setUrl = function(url) {
			graphic.symbol.setUrl(url);
		}
		
		//刷新
		graphic.refresh = function() {
			layer ? layer.redraw() : "";
		}
		
		//设置颜色
		//color格式为[R, G, B] ,[R, G, B, Alpha], 十六进制, 字符串(如 blue) 
		graphic.setColor = function(color) {
			if(graphic.symbol) {
				graphic.symbol.setColor(new dojo.Color(color));
			}
		}
		
		//设置symbol
		graphic.setSymbol = function() {
			
		}
		return graphic;
	}
		
	function addPoint(point, pictureUrl){
		
		var labelPoint,
			graphic,
			picSymbol;
		
		pictureUrl = pictureUrl || options.picUrl;
		picSymbol = new esri.symbol.PictureMarkerSymbol(pictureUrl, options.picWidth, options.picHeight);
		graphic = new esri.Graphic(point, picSymbol, null, null);
		
		return graphic;
	}
	
	
	return api;
})()