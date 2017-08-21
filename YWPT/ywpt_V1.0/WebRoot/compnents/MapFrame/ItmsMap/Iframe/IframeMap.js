MapFactory.Define("ItmsMap/Iframe/IframeMap*",[
	"ItmsMap/MapConfig",
	"MapFactory/MapManager",
	"MapFactory/LayerManager",
	"MapFactory/Query",
	"MapFactory/Route",
	"MapFactory/Util/Renderer*",
	"MapFactory/Util/Player*",
	"MapFactory/Geometry*",
	"MapFactory/Buffer",
	"MapFactory/GraphicManager",
	"MapFactory/SymbolUtil",
	"MapFactory/Draw",
	"MapFactory/Edit",
	"MapFactory/GeometryType*",
	"MapFactory/GeometryUtil",
	"MapFactory/Util/Util*",
	"ItmsMap/Util/DateFormat*",
	"ItmsMap/Util/Tip*",
	"MapFactory/Parallel",
    "MapFactory/MeasureMent",
	"MapFactory/InfoWindowManager"
], function(mapConfig, mapFun, layerFun, queryFun, routeFun, renderFun, Player, geoFun, bufferFun, graphicFun, symbolFun, drawFun, editFun, geoType,
			geoUtilFun, util, df, Tip, Parallel, MeasureMent, infoWindow){
	var gid = 1;
	return function(){
		var route = routeFun();
		var tip = Tip();
		route.setUrl(mapConfig.layers.route.url);
		var map = mapFun();
		var geoUtil = geoUtilFun();
		var roadQuery = queryFun();
		roadQuery.setUrl(mapConfig.layers.baseRoad.url);
		var crossQuery = queryFun();
		crossQuery.setUrl(mapConfig.layers.cross.url);
		var signalQuery = queryFun();
		signalQuery.setUrl(mapConfig.layers.signal.url);
		var edit = null;
		var render = renderFun();
		
		//缓存父页面的文档对象
		var parentDoc = parent.document;
		
		var vectorLayer;//不清除的layer，需要清除时需要指定具体的feature
		var drawLayer, roadLayer, isSnap, isSnapPoint;
		var mapx="mapx", mapy="mapy";
		var drawControl = drawFun();// 绘制实例
		var xchazhi = (108.942608-108.942123)/2;// x轴上道路宽度的1/2长度
		var ychazhi = (34.261199-34.260863)/2;// y轴上道路宽度的1/2长度
		
		var api = {
			showTipMsg: showTipMsg,// 地图中心提示信息框
			getChazhi: getChazhi,// 获取x轴和y轴的差值
			getRoutePolygon: getRoutePolygon,// 根据路网分析结果线、差值，获取分析结果面
			getDirectionByXY: function(x, y){return util.getDirectionByXY(x, y);},// 根据x，y的差值计算方向，返回值为8个方向的英文首字母
			getPolygonStyle: function(){return polygonStyle;},// 获取面样式
			getPolylineStyle: function(){return polylineStyle;},// 获取线样式
			getPointStyle: function(){return pointStyle;},// 获取点样式
			centerAtByGraphicObject: centerAtByGraphicObject,// 定位地图中心，参数为GraphicManager对象或该对象集合
			extentAt: extentAt,// 设置地图范围，参数为geo对象，如果为点，则定位到地图中心，并设置级别
			centerAtByPointObj: centerAtByPointObj,// 定位地图中心
			createPoint: createPoint,// 创建点要素
			createPolyline: createPolyline,// 创建线要素
			createPolygon: createPolygon,// 创建面要素
			createMultiPoint: createMultiPoint,// 创建多点要素
			createMultiPolyline: createMultiPolyline,// 创建多线要素
			createMultiPolygon: createMultiPolygon,// 创建多面要素
			createGraphic: createGraphic,// 创建feature对象
			setPointImg: setPointImg,// 设置默认标注点的样式
			setMapXY: setMapXY,// 设置父页面的xy对应的input的id
			activeSnapping: activeSnapping,// 开启捕捉
			deactivaSnapping: deactivaSnapping,// 取消捕捉
			drawPoints: drawPoints,// 绘制点到地图上，添加到vectorLayer图层上
			addRoadByName: addRoadByName,// 添加道路到地图上，精确查询后添加，回调函数，确定是否添加成功了
			queryRoad: queryRoad,// 模糊查询道路
			queryCross: queryCross,// 查询卡口
			queryCrossByCodes: queryCrossByCodes,// 通过卡口codes查询卡口
			queryGPSExtent: queryGPSExtent,// 查询GPS电子围栏
			queryOccupyPolygon: queryOccupyPolygon,// 查询挖占面
			querySignal: querySignal,// 信号机几何信息查询
			queryOmStation: queryOmStation,// 勤务岗位信息查询
			queryGpsOmExtent: queryGpsOmExtent,// GPS电子围栏，勤务执勤范围查询
			routeSearch: routeSearch,// 网络分析，添加到vectorLayer图层上
			addRouteResult: addRouteResult,// 网络分析，添加网络分析结果到地图上，根据经过次数不同进行不同颜色的渲染
			addRouteResultArrow: addRouteResultArrow,// 网络分析，添加网络分析结果线上的方向箭头，并根据一定的距离添加
			createPlayer: createPlayer,// 创建一个播放器控制对象   对应的方法：play();pause();stop();setspeed('D');setspeed('U');destroy();
			bufferGeometry: bufferGeometry,// 缓冲几何体
			getBufferStyle: getBufferStyle,// 获取buffer面的默认样式
			addPoint: addPoint,// 添加点，并定位到地图中心
			createPointStyle: createPointStyle,// 创建点样式
			addLayer: addLayer,// 添加图层到地图上
			popUpInfowindow: popUpInfowindow,// 弹出信息框
			setDivTxt: setDivTxt,// 设置div的值
			dateFormartFull: dateFormartFull,// 日期转换
			removeLayer: removeLayer,// 添加图层到地图上
			clearLayer: clearLayer,// 清空图层上的要素
			addGraphic: addGraphic,// 指定图层上的添加要素，默认为vectorLayer图层
			removeGraphic: removeGraphic,// 指定图层上的移除要素，默认为vectorLayer图层
			getFromLayer: getFromLayer,// 获取图层上的所有要素
			startEdit: startEdit,// 开始编辑
			startEditWithMode: startEditWithMode,// 设置编辑模式进行编辑
			stopEdit: stopEdit,// 停止编辑
			getClosedPoint: getClosedPoint,// 根据传递的pointGeo获取在路网上投影点，并返回geo
			deactivateDraw: deactivateDraw,// 停止绘制
			drawPolygon: drawPolygon,// 绘制面
			draw: draw,// 绘制
			drawPoint: drawPoint// 开启绘制点，使用默认样式，并且每次绘制完成后清除图层
		};
		
		var pointStyle = {url: "images/map/position.png", width: 34, height:40, xOffset: 8, yOffset: 20};
		var polylineStyle = {outLineColor: "#6666aa", outLineWidth: 3, outlineOpacity: 1};
		var polygonStyle = {outLineColor: "#6666aa", outLineWidth: 3, outlineOpacity: 1, backgroundColor: "#9999aa", backgroundOpacity: 0.4};
		var bufferStyle = {outLineWidth : 1, outLineColor : "#FF0000", outLineStyle : "dashed", backgroundColor : "#ffff00", backgroundOpacity : 0.2};
		
		layerFun("defaultLayer");
		layerFun("vectorLayer");
		
		// 定位地图中心，参数为GraphicManager对象或该对象集合
		function centerAtByGraphicObject(graphicObj) {
			var _graphicObjCenterPoint;
			if(Object.prototype.toString.call(graphicObj) == '[object Array]') {
				var ps = [];
				for(var i=0,len=graphicObj.length; i<len; i++) {
					ps = ps.concat(graphicObj[i].getCenter().points.split(","));
				}
				_graphicObjCenterPoint = createGraphic(createPolygon(ps.join(","))).getCenter();
			}
			else {
				_graphicObjCenterPoint = graphicObj.getCenter();
			}
			centerAtByPointObj(_graphicObjCenterPoint);
		}
		function extentAt(graphicObj) {
			if(Object.prototype.toString.call(graphicObj) == '[object Array]') {
				var ps = [];
				for(var i=0,len=graphicObj.length; i<len; i++) {
					ps.push(graphicObj[i].geo.points);
				}
				map.setExtent(createGraphic(createPolygon(ps.join(","))).getExtent());
			}
			else {
				map.setExtent(graphicFun(graphicObj).getExtent());
			}
		}
		// 定位地图中心，参数可以为Point或者x，y坐标
		function centerAtByPointObj(x, y) {
			if(isNaN(x) || isNaN(y) || !x || !y)return null;
			if(typeof x === "string") {
				x = parseFloat(x);
			}
			if(typeof y === "string") {
				y = parseFloat(y);
			}
			if(typeof x === "object") {
				var tp = x.points.split(",");
				y = parseFloat(tp[1]);
				x = parseFloat(tp[0]);
			}
			map.centerAt(x, y);
		}
		
		// 添加图层到地图上
		function addLayer(layerId, layerClick) {
			var tlayer = null;
			if(typeof layerId === "string" && layerId) {
				tlayer = layerFun(layerId);
				if(typeof layerClick === "function")tlayer.addOnClickEvent(layerClick);
			}
			return tlayer;
		}
		
		//移除地图上的图层
		function removeLayer(layerId) {
			if(typeof layerId === "string" && layerId)layerFun(layerId).removeFromMap();
		}
		
		//获取图层上的所有要素
		function getFromLayer(layerId) {
			if(typeof layerId === "string" && layerId)return layerFun(layerId).getFeatures();
			else return [];
		}
		
		//清空图层上的要素
		function clearLayer(layerId) {
			if(typeof layerId === "string" && layerId)layerFun(layerId).clear();
		}
		
		// 设置点图片信息
		function setPointImg(imgUrl, imgWidth, imgHeight, xoffset, yoffset) {
			if(!imgUrl) {
				// 如果没有传递imgUrl则重置为默认图标点样式
				pointStyle = {url: "images/map/position.png", width: 34, height:40, xOffset: 8, yOffset: 20};
			}
			else {
				if(!imgWidth)imgWidth = 24;
				if(!imgHeight)imgHeight = 24;
				if(!xoffset)xoffset = 0;
				if(!yoffset)yoffset = 0;
				pointStyle = {url: imgUrl, width: imgWidth, height:imgHeight, xOffset: xoffset, yOffset: yoffset};
			}
		}
		
		// 设置点图片信息
		function createPointStyle(imgUrl, imgWidth, imgHeight, xoffset, yoffset) {
			if(!imgWidth)imgWidth = 24;
			if(!imgHeight)imgHeight = 24;
			if(!xoffset)xoffset = 0;
			if(!yoffset)yoffset = 0;
			return {url: imgUrl, width: imgWidth, height:imgHeight, xOffset: xoffset, yOffset: yoffset};
		}
		
		// 添加坐标到地图上--默认添加到绘制图层上，并且不定位到地图中心
		function addPoint(x, y, isCenterToMap, layer, att, style) {
			if(isNaN(x) || isNaN(y) || !x || !y)return null;
			x = parseFloat(x);
			y = parseFloat(y);
			if(!x || !y)return null;
			var point = new geoFun({type : "point", points : x+","+y});
			var tempStyle = style || pointStyle;
			
			var graphic = graphicFun({geo:point, symbol:tempStyle, attributes:att});
			if(layer)graphic.addToLayer(layer);
			else graphic.addToLayer("defaultLayer");
			if(isCenterToMap)map.centerAt(x, y);
			
			return graphic;
		}
		
		// 设置父页面的x y的input的id
		function setMapXY(xid, yid) {
			mapx = xid;
			mapy = yid;
		}
		// 网络分析features为feature的数组
		function routeSearch(stops, barriers, callback, errorfun) {
			var _start = stops[0];
			var _end = stops[stops.length-1];
			route.setStart(new geoFun({type : "point", points : _start.points}));
			route.setEnd(new geoFun({type : "point", points : _end.points}));
			route.setStops(stops);
			route.setBarriers(barriers);
			route.solve(function(result){
				if(callback)callback(result);
			}, function(error){
				if (typeof console != 'undefined' && console.log) console.log("iframeMap--最短路径分析的时候，出错了！"+error);
				if(errorfun)errorfun(error);
			});
		}
		
		// 添加网络分析结果
		function addRouteResult(pointList, layerId) {
			if(!layerId)layerId = "defaultLayer";
			render.addRouteResult(pointList, layerId);
		}
		
		// 添加网络分析结果的方向箭头
		function addRouteResultArrow(pointList, layerId) {
			if(!layerId)layerId = "vectorLayer";
			render.addRouteResultArrow(pointList, layerId);
		}
		
		// 创建一个播放器控制对象
		function createPlayer(pointList) {
			return Player(pointList);
		}
		
		// 缓冲几何体
		function bufferGeometry(geometries, meterArr, callback) {
			if(!callback)return;
			var buffer = bufferFun();
			buffer.setUrl(mapConfig.layers.buffer.url);
			buffer.setGeometry(geometries);
			buffer.setDistance(meterArr);
			buffer.execute(function(_resArr){
				callback(_resArr);
			}, function(_fault){
				callback(null);
			});
		}
		
		function getBufferStyle() {
			return bufferStyle;
		}
		
		function getRoutePolygon(line, distance) {
			if(!line || !line.points)return null;
			var length = MeasureMent().getLength(line);
			var newpoints = "";
			var paths = line.points.split("|");
			
			// 剔除原始线的重复点并转换多线为单线------这里认为该多线为排序好的多线
			var prex = null, prey = null, pointslist=[];
			for(var i=0,len=paths.length; i<len; i++) {
				var temps = paths[i].split(",");
				for(var j=0,jl=temps.length; j<jl; j+=2) {
					if(!prex && j == 0) {
						// 初始化prePoint
						prex = temps[j];
						prey = temps[j+1];
						pointslist.push(temps[j]);
						pointslist.push(temps[j+1]);
					}
					if(prex == temps[j] && prey == temps[j+1]) {
						continue;
					}
					pointslist.push(temps[j]);
					pointslist.push(temps[j+1]);
					prex = temps[j];
					prey = temps[j+1];
				}
			}
			
			var fistPoints = paths[0].split(",");
			var geo = Parallel.createParallel(line, distance, "right");
			newpoints = pointslist.join(",")+","+geo.points+","+fistPoints[0]+","+fistPoints[1];
			
			/*var lastPoints = paths[paths.length-1].split(",");
			var ll = lastPoints.length;
			var laststr = lastPoints[ll-2]+","+lastPoints[ll-1];
			
			var newPoints = line.points;
			var length = MeasureMent().getLength(line);
			var geo = Parallel.createParallel(line, distance, "right");
			var paths2 = geo.points.split("|");
			var fistPoints2 = paths2[0].split(",");
			var lastPoints2 = paths2[paths2.length-1].split(",");
			var ll2 = lastPoints2.length;
			var laststr2 = lastPoints2[ll2-2]+","+lastPoints2[ll2-1];
			
			newpoints += "|"+geo.points;
			newpoints += "|"+fistPoints[0]+","+fistPoints[1]+","+fistPoints2[0]+","+fistPoints2[1];
			newpoints += "|"+laststr+","+laststr2;*/
			
			return {geo: new geoFun({type: geoType.MULTIPOLYGON, points: newpoints}), len: length};
		}
		
		// 获取x轴和y轴上的差值
		function getChazhi() {
			return {x: xchazhi, y:ychazhi};
		}
		// 创建point
		function createPoint(x, y) {
			return new geoFun({type : geoType.POINT, points : x+","+y});
		}
		// 创建LineString参数为point的array数组
		function createPolyline(points) {
			return new geoFun({type : geoType.POLYLINE, points : points});
		}
		// 创建polygon
		function createPolygon(points) {
			return new geoFun({type : geoType.POLYGON, points : points});
		}
		// 创建多点
		function createMultiPoint(points) {
			return new geoFun({type : geoType.MULTIPOINT, points : points});
		}
		// 创建多线
		function createMultiPolyline(points) {
			return new geoFun({type : geoType.MULTIPOLYLINE, points : points});
		}
		// 创建多面
		function createMultiPolygon(points) {
			return new geoFun({type : geoType.MULTIPOLYGON, points : points});
		}
		// 创建graphic
		function createGraphic(geometry, attributes, style) {
			if(MapFactory.VariableTypes.isString(geometry)){
				geometry = MapFactory.JSON.Parse(geometry);
			}
			if(!style) {
				style = _getDefaultSymbol(geometry);
			}
			return graphicFun({geo:geometry, symbol:style, attributes:attributes});
		}
		function _getDefaultSymbol(geometry) {
			if(geometry.type == geoType.POINT)return pointStyle;
			else if(geometry.type == geoType.MULTIPOINT)return pointStyle;
			else if(geometry.type == geoType.POLYLINE)return polylineStyle;
			else if(geometry.type == geoType.MULTIPOLYLINE)return polylineStyle;
			else if(geometry.type == geoType.POLYGON)return polygonStyle;
			else if(geometry.type == geoType.MULTIPOLYGON)return polygonStyle;
		}
		// 添加graphic默认为vectorLayer图层上的添加
		function addGraphic(graphic, layer) {
			if(layer)graphic.addToLayer(layer);
			else graphic.addToLayer("vectorLayer");
		}
		// 移除graphic默认为vectorLayer图层上的移除
		function removeGraphic(graphic) {
			if(graphic) {
				graphic.remove();
			}
		}
		
		// 绘制多点，并添加到地图上
		function drawPoints(callback, style) {
			drawControl.deactivate();
			drawControl = drawFun();
			if(isSnap && !isRoadSnap)drawControl.openSnapping(snapConfig);
	    	drawControl.setGeoType(geoType.POINT);
	    	drawControl.setDrawEndEvent(function(geo) {
	    		if(isSnap && isRoadSnap) {
	    			getClosedPoint(geo, function(newGeo){
	    				if(newGeo) {
	    					_drawPointsEnd(newGeo, callback, style);
	    				}
	    				else {
					    	callback("");
	    				}
	    			});
	    		}
	    		else {
	    			_drawPointsEnd(geo, callback, style);
	    		}
	    	});
	       	drawControl.activate();
	    }
	    // drawPoints的辅助方法
	    function _drawPointsEnd(geo, callback, style) {
	    	if(!style)style = pointStyle;
	    	// 添加自定义点到地图上
			var graphic = graphicFun({geo:geo, symbol:style, attributes: {gid: gid++}});
			graphic.addToLayer("vectorLayer");
			var ps = geo.points.split(",");
			if(parentDoc.getElementById(mapx))parentDoc.getElementById(mapx).value = ps[0];
			if(parentDoc.getElementById(mapy))parentDoc.getElementById(mapy).value = ps[1];
	    	callback(graphic);
	    }
	    
	    // 停止绘制
		function deactivateDraw() {
			deactivaSnapping();
			drawControl.deactivate();
		}
		
		// 绘制点，并添加到地图上
		function draw(type, callback, isAutoClear, layerId) {
			if(!layerId)layerId = "defaultLayer";
			drawControl.deactivate();
			drawControl = drawFun();
	    	drawControl.setGeoType(type);
	    	drawControl.setDrawEndEvent(function(geo) {
	    		if(isAutoClear)layerFun(layerId).clear();
		    	// 添加自定义面到地图上
		    	var _symbol=null;
		    	if(type == "point")_symbol = pointStyle;
		    	if(type == "polyline")_symbol = polylineStyle;
		    	if(type == "polygon")_symbol = polygonStyle;
				var graphic = graphicFun({geo:geo, symbol:_symbol, attributes: {gid: gid++}});
				graphic.addToLayer(layerId);
				callback(graphic);
	    	});
	       	drawControl.activate();
	    }
		
		// 绘制面，并添加到地图上
		function drawPolygon(callback, isAutoClear, layerId) {
			draw(geoType.POLYGON, callback, isAutoClear, layerId);
	    }
		
		// 绘制点，并添加到地图上
		function drawPoint(callback, layerId) {
			if(!layerId)layerId = "defaultLayer";
			drawControl.deactivate();
			drawControl = drawFun();
			if(isSnap && !isRoadSnap)drawControl.openSnapping(snapConfig);
	    	drawControl.setGeoType(geoType.POINT);
	    	drawControl.setDrawEndEvent(function(geo) {
	    		if(isSnap && isRoadSnap) {
	    			getClosedPoint(geo, function(newGeo){
    					_drawPointEnd(newGeo, callback, layerId);
	    			});
	    		}
	    		else {
		    		_drawPointEnd(geo, callback, layerId);
	    		}
	    	});
	       	drawControl.activate();
	    }
	    // drawPoint的辅助方法
	    function _drawPointEnd(geo, callback, layerId) {
	    	layerFun(layerId).clear();
	    	if(!geo) {
	    		if(callback)callback("");
	    		if(parentDoc.getElementById("geomText"))parentDoc.getElementById("geomText").value = '';
		    	if(parentDoc.getElementById(mapx))parentDoc.getElementById(mapx).value = '';
				if(parentDoc.getElementById(mapy))parentDoc.getElementById(mapy).value = '';
	    		return;
	    	}
	    	// 添加自定义点到地图上
			var graphic = graphicFun({geo:geo, symbol:pointStyle, attributes: {gid: gid++}});
			graphic.addToLayer(layerId);
			
	    	if(parentDoc.getElementById("geomText"))parentDoc.getElementById("geomText").value = '{"type":"point","points":"'+geo.points+'"}';
	    	var ps = geo.points.split(",");
	    	if(parentDoc.getElementById(mapx))parentDoc.getElementById(mapx).value = ps[0];
			if(parentDoc.getElementById(mapy))parentDoc.getElementById(mapy).value = ps[1];
			
    		if(isSnap && callback) {
				// 查询点所在的道路信息
				roadQuery.setGeometry(geo);
				roadQuery.setCondition({});
				roadQuery.execute(function(result) {
					if(result && result.length) {
						callback(result[0]);// 返回查询到的几何信息
					}
					else {
						callback("");
					}
				}, function(error) {
					if (typeof console != 'undefined' && console.log) console.log("全查流量道路面数据的时候，出错了！"+error);
					callback("");
				});
			}
			else if(callback) {
				callback("");
			}
	    }
	    
	    // 开启捕捉
	    var isSnap, snapConfig, isRoadSnap;
		function activeSnapping(layerIds) {
			if(layerIds) {
				snapConfig = {ids: layerIds};
				isRoadSnap = false;
			}
			else {
				isRoadSnap = true;
				// 默认捕捉道路图层
				snapConfig = {urls: [mapConfig.layers.baseRoad.url]};
			}
			isSnap = true;
		}
		// 停止捕捉
		function deactivaSnapping() {
			drawControl.closeSnapping();
			isSnap = false;
		}
		
		function getClosedPoint(pointGeo, callback) {
			var query = queryFun();
			query.setUrl(mapConfig.layers.closedpoint.url);
			query.setSpatialRelationShip("closedpoint");
			query.setGeometry(pointGeo);
			query.execute(function(result) {
				if(result && result.length) {
					callback(result[0].geo);
				}
				else {
					//closedpoint查询结果为空！
					callback("");
				}
			}, function(error) {
				callback("");//closedpoint查询结果出错了！
				console.log("closedpoint查询结果出错了！"+error);
			});
		}
		
		function addRoadByName(name, callback, layerId) {
			// 查询点所在的道路信息
			roadQuery.setGeometry(null);
			roadQuery.setCondition({"NAME": name});
			roadQuery.execute(function(result) {
				if(result && result.length) {
					var roadGraphics = [];
					if(!layerId)layerId = "drawLayer";
					for(var i=0,len=result.length; i<len; i++) {
						var graphic = result[i];
						var roadGraphic = graphicFun({geo: graphic.geo, symbol: polylineStyle, attributes: graphic.attributes});
						roadGraphic.addToLayer(layerId);
						roadGraphics.push(roadGraphic);
					}
					if(callback)callback(roadGraphics);
				}
				else {
					if(callback)callback(null);
				}
			}, function(error) {
				if (typeof console != 'undefined' && console.log) console.log("全查流量道路面数据的时候，出错了！"+error);
				if(callback)callback(null);
			});
		}
		
		function queryRoad(name, callback) {
			if(!callback)return;
			// 查询点所在的道路信息
			roadQuery.setGeometry(null);
			roadQuery.setCondition({"NAME like": "%"+name+"%"});
			roadQuery.execute(function(result) {
				callback(result);
			}, function(error) {
				if (typeof console != 'undefined' && console.log) console.log("全查流量道路面数据的时候，出错了！"+error);
				callback(null);
			});
		}
		
		function queryCross(geo, callback) {
			if(!callback)return;
			// 查询几何体所包含的卡口
			crossQuery.setGeometry(geo);
			crossQuery.setCondition({});
			crossQuery.execute(function(result) {
				if(result && result.length) {
					callback(result);
				}
				else {
					callback("");
				}
			}, function(error) {
				if (typeof console != 'undefined' && console.log) console.log("查询卡口数据的时候，出错了！"+error);
				callback("");
			});
		}
		
		function queryCrossByCodes(codes, callback) {
			if(!callback)return;
			// 查询几何体所包含的卡口
			crossQuery.setGeometry(null);
			crossQuery.setCondition({"code IN": codes.join(',')});
			crossQuery.execute(function(result) {
				if(result && result.length) {
					callback(result);
				}
				else {
					callback("");
				}
			}, function(error) {
				if (typeof console != 'undefined' && console.log) console.log("查询卡口数据的时候，出错了！"+error);
				callback("");
			});
		}
		
		function queryGPSExtent(code, geo, callback) {
			if(!callback)return;
			var _query = queryFun();
			_query.setUrl(mapConfig.layers.gpsextent.url);
			if(geo)_query.setGeometry(geo);
			if(code)_query.setCondition({CODE: code});
			_query.execute(function(result) {
				if(result && result.length) {
					callback(result);
				}
				else {
					callback("");
				}
			}, function(error) {
				if (typeof console != 'undefined' && console.log) console.log("查询GPS电子围栏的时候，出错了！"+error);
				callback("");
			});
		}
		
		function querySignal(geo, callback) {
			if(!callback)return;
			signalQuery.setGeometry(geo);
			signalQuery.setCondition({});
			signalQuery.execute(function(result) {
				if(result && result.length) {
					callback(result);
				}
				else {
					callback("");
				}
			}, function(error) {
				if (typeof console != 'undefined' && console.log) console.log("查询信号机数据的时候，出错了！"+error);
				callback("");
			});
		}
		
		function queryOmStation(code, type, callback) {
			if(!callback)return;
			var _query = queryFun(), _omurl="";
			if(type == "point")_omurl = mapConfig.layers.omPoint.url;
			if(type == "polyline")_omurl = mapConfig.layers.omPolyline.url;
			if(type == "polygon")_omurl = mapConfig.layers.omPolygon.url;
			if(!_omurl) {
				callback("");
				return;
			}
			_query.setUrl(_omurl);
			_query.setCondition({CODE: code});
			_query.execute(function(result) {
				if(result && result.length) {
					callback(result);
				}
				else {
					callback("");
				}
			}, function(error) {
				if (typeof console != 'undefined' && console.log) console.log("查询勤务数据的时候，出错了！"+error);
				callback("");
			});
		}
		
		function queryGpsOmExtent(code, callback) {
			if(!callback)return;
			var _query = queryFun();
			_query.setUrl(mapConfig.layers.gpsextent.url);
			_query.setCondition({CODE: code});
			_query.execute(function(result) {
				if(result && result.length) {
					callback(result);
				}
				else {
					callback("");
				}
			}, function(error) {
				if (typeof console != 'undefined' && console.log) console.log("查询勤务数据的时候，出错了！"+error);
				callback("");
			});
		}
		
		function popUpInfowindow(geo, title, content, width, height) {
			var info = infoWindow();
			info.setWidth(width);
			info.setHeight(height);
			info.setAnchor(new geoFun(geo));
			info.setTitle(title);
			info.setContent(content);
			info.show();
		}
		
		function setDivTxt(div, label) {
			document.getElementById(div).innerText = label;
		}
		
		function dateFormartFull(date, dateSplit, timeSplit) {
			return df.dateFormartFull(date, dateSplit, timeSplit);
		}
		
		function queryOccupyPolygon(code, geo, callback) {
			if(!callback)return;
			var _query = queryFun();
			_query.setUrl(mapConfig.layers.occupyPolygon.url);
			if(geo)_query.setGeometry(geo);
			if(code)_query.setCondition({CODE: code});
			_query.execute(function(result) {
				if(result && result.length) {
					callback(result);
				}
				else {
					callback("");
				}
			}, function(error) {
				if (typeof console != 'undefined' && console.log) console.log("查询挖占面数据的时候，出错了！"+error);
				callback("");
			});
		}
		
		function startEdit(layerId) {
			if(edit)edit.deactivate();
			edit = editFun();
			edit.setEditMode("editvertices|rotate|scale|move");
			edit.setLayerID(layerId);
			edit.activate();
		}
		
		function startEditWithMode(layerId, modde) {
			if(edit)edit.deactivate();
			edit = editFun();
			edit.setEditMode(modde);
			edit.setLayerID(layerId);
			edit.activate();
		}
		
		function stopEdit() {
			if(edit) {
				edit.deactivate();
				edit = null;
			}
		}
		
		// 地图中心提示消息
		function showTipMsg(msg) {
			tip.setContent(msg);
			tip.show();
			setTimeout(function(){
				tip.setContent("");
				tip.hide();
			}, 1500);
		}
		
		return api;
	}
});