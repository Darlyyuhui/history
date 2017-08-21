dojo.require("esri.map");
dojo.require("esri.layers.ArcGISTiledMapServiceLayer");
dojo.require("esri.layers.FeatureLayer");
dojo.require("esri.tasks.identify");
dojo.require("esri.layers.graphics");
dojo.require("esri.geometry.Point");
dojo.require("esri.symbols.PictureMarkerSymbol");

var addMapLinks = (function(){
	
	var //地图窗口
		bMap,
	
		//用于显示添加到底图上的graphic等信息
		graphicsLayer,
		
		//是否可选取点，布尔值，也可以是能返回布尔值的回调函数，
		//默认是不可以选取点
		selectable = false,
		
		startGraphic,
		endGraphic,
		stopGraphic,
		
		routeCallback,
		route,
		
		//缓存父页面的文档对象
		parentDoc = parent.document,
		
		//是否可选取起点，终点
		startSelectable = false,
		endSelectable = false,
		stopSelectable = false,
		
		//当前活动图层
		currentLayer,
		//选取开始结束节点的回调函数
		startCallback,
		endCallback,
		stopCallback,
		
		//获取非起点 终点成功后的回调函数
		callback,
				
		//是否启用捕捉
		isSnapping = false,
		
		//地图初始化的默认参数	
		options = {
			//地图容器的Id
			mapContainer: "mapDiv_signal",
			
			//底图的服务路径
			baseMapUrl: baseServiceURL.basemapnew.url,
			
			//路径服务地址
			routeServiceUrl: baseServiceURL.route.url,
			
			snappingUrl: baseServiceURL.snappingRoad.url,
								
			//起点图片的路径
			startPicUrl: path+"/images/start.png",
			
			//终点图片的路径
			endPicUrl: path+"/images/end.png",
			
			picUrl: path+"/images/lukoutupian.png",
			
			stopPicUrl: path+"/images/lukoutupian.png",
			
			//图片大小
			picHeight: 20,
			picWidth: 20

		};
	
	//对外开放的接口
	var api = {
		initMap: initMap,
		startSnapping: startSnapping,
		stopSnapping: stopSnapping,
		selectStartPoint: selectStartPoint,
		selectEndPoint: selectEndPoint,
		selectStop: selectStop,
		done: done,
		view: view,
		addPoints: addPoints,
		getPoint: getPoint,
		clearGraphic: clearGraphic,
		clearStop: clearStop
	};
	
	//初始化地图
	function initMap() {
		//底图
		var baseMap,
		
			//传入的配置参数
			config = arguments[0];
		
		//初始化参数
		copyParams(options,config);
		
		//初始化地图
		bMap = new esri.Map(options.mapContainer,{slider:false,minScale:75000,logo:false});
		baseMap = new esri.layers.ArcGISTiledMapServiceLayer(options.baseMapUrl);
		bMap.addLayer(baseMap);
		
		graphicsLayer = new esri.layers.GraphicsLayer();
		bMap.addLayer(graphicsLayer);
		
		//默认当前活动图层为GraphicsLayer;
		currentLayer = graphicsLayer;
		
		//注册地图单击事件
		dojo.connect(bMap, "onClick", afterClick);	
		
		return this;
	}
	
	//查看功能，func返回的数据格式为{start:[],end:[],stop:[],others:{name1:[],name2:[]}}
	//此函数回调应该去掉
	//参数形式nodes1,nodes2,nodes3.....
	//nodes1的格式为{start:[],end:[],stop:[]}
	//其它参数的格式为{name:"",coords:[],url:""}
	function view() {
		var arguments,
			pointStart,
			pointEnd,
			tempPoint,
			tempUrl,
			tempLayer,
			//自定义名称的点数据
			otherPoints;
		
		//如果未传入参数则终止程序
		if (arguments.length === 0) return;
		pointStart = new esri.geometry.Point(arguments[0].start);
		pointEnd = new esri.geometry.Point(arguments[0].end);
		startGraphic = addPoint(pointStart, options.startPicUrl);
		endGraphic = addPoint(pointEnd, options.endPicUrl);
		arguments[0].stop && arguments[0].stop.length > 0 ? graphicsLayer.add(addPoint(new esri.geometry.Point(arguments[0].stop), options.stopPicUrl)) : stopGraphic;
		
		graphicsLayer.add(startGraphic);
		graphicsLayer.add(endGraphic);
		
		getRoute();	
		
		for (var i = 1; i < arguments.length; i = i + 1) {
			otherPoints = arguments[i];
			tempLayer = new esri.layers.GraphicsLayer({id: otherPoints["name"]});
			tempPoint = otherPoints["coords"];
			tempUrl = otherPoints["url"];

			while (tempPoint.length) {
				tempLayer.add(addPoint(new esri.geometry.Point(tempPoint.pop()), tempUrl));
			}
			bMap.addLayer(tempLayer);
		}
				
		return this;
	}
	
//	function view(func) {
//		var pointStart,
//			pointEnd,
//			normal = "start end stop",
//			tempPoint,
//			tempLayer,
//			//自定义名称的点数据
//			otherPoints,
//			nodes;
//		
//		if (typeof func === "function" && Object.prototype.toString.call(nodes = func()) === "[object Object]") {
//			pointStart = new esri.geometry.Point(nodes.start);
//			pointEnd = new esri.geometry.Point(nodes.end);
//			startGraphic = addPoint(pointStart, options.startPicUrl);
//			endGraphic = addPoint(pointEnd, options.endPicUrl);			
//			graphicsLayer.add(startGraphic);
//			graphicsLayer.add(endGraphic);
//			
//			getRoute();	
//		}
//		
//		return this;
//	}
	
	function selectStartPoint() {
		currentLayer = graphicsLayer;
		startSelectable = true;
		endSelectable = false;
		stopSelectable = false;
		
		return this;
	}
	
	function selectEndPoint() {
		currentLayer = graphicsLayer;
		endSelectable = true;
		startSelectable = false;
		stopSelectable = false;
		return this;
	}
	
	function selectStop() {
		currentLayer = graphicsLayer;
		startSelectable = false;
		endSelectable = false;
		stopSelectable = true;
		
		return this;
	}
	
	function done(startCall, endCall, routeCall, stopCall) {
		startCallback = startCall;
		endCallback = endCall;
		routeCallback = routeCall;
		stopCallback = stopCall;
		
		return this;
	}
	
	function getPoint(layerId, func) {
		currentLayer = bMap.getLayer(layerId) || bMap.addLayer(new esri.layers.GraphicsLayer({id: layerId}));
		callback = func;
		
	}
	
	function clearStop() {
		graphicsLayer.remove(stopGraphic);
	}
	
	//启用捕捉
	function startSnapping() {		
		var snappingParams = {
			map: bMap,
			alwaysSnap: true,
			tolerance: 10,
			layerInfos: [{layer:new esri.layers.FeatureLayer(options.snappingUrl)}]
		}
		
		bMap.enableSnapping(snappingParams);
		isSnapping = true;
		
		return this;
	}
	
	//关闭捕捉
	function stopSnapping() {
		bMap.disableSnapping();
		isSnapping = false;
		
		return this;
	}
	
	//地图点后的处理事件
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
			var point;

			if(typeof value !== "undefined") {
				if (currentLayer === graphicsLayer) {
					if (startSelectable) {
						if (typeof startCallback === "function") {
							point = startCallback(value) || value;
						}

						point = point || value;
						
						startSelectable = false;										
						graphicsLayer.remove(startGraphic);
						//处理生成的点
						startGraphic = addPoint(point, options.startPicUrl);
						graphicsLayer.add(startGraphic);
						getRoute();
						
					} else if (endSelectable) {
						if (typeof endCallback === "function") {
							point = endCallback(value) || value;
						}
						
						point = point || value;
						
						endSelectable = false;										
						graphicsLayer.remove(endGraphic);
						//处理生成的点
						endGraphic = addPoint(point, options.endPicUrl);
						graphicsLayer.add(endGraphic);
						getRoute();
					} else if (stopSelectable) {
						if (typeof stopCallback === "function") {
							point = stopCallback(value) || value;
						}
						
						point = point || value;
						
						stopSelectable = false;										
						graphicsLayer.remove(stopGraphic);
						
						//处理生成的点
						stopGraphic = addPoint(point, options.stopPicUrl);
						graphicsLayer.add(stopGraphic);
						getRoute();
					}
				} else {
					graphic = addPoint(point);
					callback(currentLayer, graphic);
				}				
			} 
		});
		
		return this;
			
	}
	
	function getClickPoint(e) {
		var i = 0,
			point;

		if (currentLayer == graphicsLayer) {
			if (startSelectable) {
				if (typeof startCallback === "function") {
					point = startCallback(e) || e.mapPoint;
				}
				//如果startCallback不是function
				point = point || e.mapPoint;
				
				startSelectable = false;
				
				graphicsLayer.remove(startGraphic);
				//处理生成的点
				startGraphic = addPoint(point, options.startPicUrl);
				graphicsLayer.add(startGraphic);
				getRoute();
				
			} else if (endSelectable) {
				if (typeof endCallback === "function") {
					point = endCallback(e) || e.mapPoint;
				}
				
				point = point || e.mapPoint;
				
				endSelectable = false;
				graphicsLayer.remove(endGraphic);
				//处理生成的点
				endGraphic = addPoint(point, options.endPicUrl);
				graphicsLayer.add(endGraphic);
				getRoute();
			} else if (stopSelectable) {
				if (typeof stopCallback === "function") {
					point = stopCallback(e) || e.mapPoint;
				}
				
				point = point || e.mapPoint;
				
				stopSelectable = false;										
				graphicsLayer.remove(stopGraphic);
				
				//处理生成的点
				stopGraphic = addPoint(point, options.stopPicUrl);
				graphicsLayer.add(stopGraphic);
				getRoute();
			}

		} else {
			graphic = addPoint(e.mapPoint);
			callback(currentLayer, graphic);
		}
	}
	
	//清除添加到新图层中的graphic
	function clearGraphic(layerId) {
		var layer = bMap.getLayer(layerId);
		layer ? layer.clear() : "";
	}
	
	
	function getRoute() {
		var rooteTask,
			routeParams;

		if (startGraphic && endGraphic) {
			routeTask = new esri.tasks.RouteTask(options.routeServiceUrl);
			routeParams = new esri.tasks.RouteParameters();
			routeParams.stops = new esri.tasks.FeatureSet();
			routeParams.stops.features.push(startGraphic);
			
			if(stopGraphic) {
				routeParams.stops.features.push(stopGraphic);
			}
			
			routeParams.stops.features.push(endGraphic);
			routeParams.outSpatialReference = {"wkid":4326};
			routeTask.solve(routeParams, showRoute);
		}
	}
	
	function showRoute(solveResults) {
		dojo.forEach(solveResults.routeResults, function(routeResult, i) {
			graphicsLayer.remove(route);
			route = routeResult.route;
			route.setSymbol(new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_DASH,
					  new dojo.Color([255, 0, 0, 0]), 3));
			route.hide();
			graphicsLayer.add(route);
			routeCallback(addMethod(route), graphicsLayer);
			bMap.centerAt(route.geometry.getExtent().getCenter());
		});
	}
	
	//设置线类型
	function setLineStyle(style) {
		
	}
	
	/**
	 * 工具
	 * */
	
	//对当前选取的位置标注，生成一个没有属性的graphic。注意：如果graphic作为路径运算的stops，需要使它的属性在对应的路径图层中存在
	//否则应将其属性置空，防止出错
	//对当前选取的节点进行标注
	function addPoint(point, pictureUrl){
		
		var labelPoint,
			graphic,
			picSymbol;
		
		pictureUrl = pictureUrl || options.picUrl;
		picSymbol = new esri.symbol.PictureMarkerSymbol(pictureUrl, options.picWidth, options.picHeight);
		graphic = new esri.Graphic(point, picSymbol, null, null);
		
		return graphic;
	}
	
	function addPoints(nodes) {
		var node,
			point,
			graphic;
		
		if (nodes instanceof Array && nodes.length > 0) {
			while (nodes.length) {
				node = nodes.pop();
				point = new esri.geometry.Point(node.xPos, node.yPos);
				graphic = addPoint(point);
				graphic.setAttributes({"nodename": node.nodename, "nodeid": node.nodeid});
				graphicsLayer.add(graphic);
			}
		}
		
		return this;
	}
	
	//验证是否是空对象
	function isEmptyObject(obj){
		for(var i in obj){
			return false;
		}		
		return true;
	}
	
	//处理传入的参数，如果传入的参数某一项存在，则以它的值作为参数进行初始化，否则以默认参数初始化
	function copyParams(defaultParams, inputParams, exception){
		
		if(typeof inputParams !== "object" || inputParams === null || isEmptyObject(inputParams))return;
		//判断是否相等用"!==", false == ""会得到true值
		for(key in defaultParams){
			if(typeof inputParams[key] !== "undefined" && inputParams[key] !== "" && key !== exception){
				defaultParams[key] = inputParams[key];
			}
		}
	}
	
	//可以创建类似方法挂到itmap下,graphic的方法需要加上?,应该再加参数设置哪些可以暴露给用户
	function addMethod(graphic) {
			
			//返回的封装后的graphic
			var graphics = {},
				//缓存symbol对象
				symbol = graphic.symbol,
				
				//symbol的类型
				symbolType = symbol.type,
				
				//当前类型symbol的方法
				currentMethod,
				
				//各种类型symbol的方法
				symbolMethods = {
					simplelinesymbol: ["setWidth"],
					simplefillsymbol: [],
					picturefillsymbol: ["setHeight", "setOffset", "setUrl", "setWidth", "setXScale", "setYScale"],
					simplemarkersymbol: ["setAngle", "setColor", "setOffset", "setOutLine", "setPath", "setSize"],
					textsymbol: ["setAlign", "setAngle", "setColor", "setDecoration", "setFont", "setKerning", "setOffset", "setRotaed", "setText"],
					picturemakersymbol: ["setAngle", "setColor", "setHeight", "setOffset", "setSize", "setUrl", "setWidth"]
				};	
			
			currentMethod = symbolMethods[symbolType];
			
			//添加方法
			for(var i = currentMethod.length - 1; i >= 0; i = i - 1) {
				graphics[currentMethod[i]] = function(args) {
					symbol[currentMethod[i]](args);
				}
			}
			//setColor为所有的symbol都有的方法，参数为[r,g,b] | [r,g,b,a] | 或者以#开头的6位16进制颜色值 或者表示颜色的文本如“blue”
			graphics.setColor = function(color) {
				symbol.setColor(new dojo.Color(color));
			}
			
			
			if(symbol.setOutLine) {
				graphics.setOutLine = function(color, width, style) {
					var line = symbol.outline;
					line.setColor(color).setWidth(width).setStyle(style);
				}
			}
			
			if(symbol.setStyle) {
				graphics.setStyle = function(style) {
					style = style || "dashdot";
					style = "STYLE_" + style.toUpperCase();
					symbol.setStyle(esri.symbol.SimpleLineSymbol[style]);
				}
			}
			
			graphics.setSymbol = function(args) {
				if (symbolType != args) {
					symbol = graphic.symbol = new esri.symbol[args];
					graphics.setSymbol(symbol);
				} 
			}
			
			graphics.destroy = function() {
				var layer = graphic.getLayer();			
				if (layer) {
					layer.remove(graphic);
				}
			}
			
			graphics.show = function() {
				graphic.show();
			}
			
			graphics.hide = function() {
				graphic.hide();
			}
			
			graphics.refresh = function() {
				var l = graphic.getLayer();
				l ? l.redraw() : "";
			}
			graphics.geometry = graphic.geometry;
			
			return graphics;		
		}
	
	return api;
})()