/**
 * openmap嵌入地图
 * */

var EmbeddedMap = (function() {
	
	var //地图窗口
		bMap,
		
		//是否启用捕捉
		isSnapping = false,
		
		//捕捉对象
		snap,
		
		//缓存父页面的文档对象
		parentDoc = parent.document,
		
		//地图初始化的默认参数	
		options = {
			//地图容器的Id
			mapContainer: "mapDiv_signal",
			
			//底图的服务路径
			baseMapUrl: baseServiceURL.geoserverWMS.url,
			
			//请求geoserver使用na服务的请求路径地址
			routeServiceUrl: baseServiceURL.geoserverNSService.url
	
		};
	var wkt = new OpenLayers.Format.WKT();
	// 网络分析名称
	var roadLineNAName = baseServiceURL.geoserverRoadLineNAName.url;
	var roadPolygonNAName = baseServiceURL.geoserverRoadPolygonNAName.url;
	var vectorLayer;//不清除的layer，需要清除时需要指定具体的feature
	var drawLayer, roadLayer, isSnap, isSnapPoint;
	var mapx="mapx", mapy="mapy";
	var drawControl;// 绘制实例
	var xchazhi = (108.942608-108.942123)/2;// x轴上道路宽度的1/2长度
	var ychazhi = (34.261199-34.260863)/2;// y轴上道路宽度的1/2长度
	/**
	 * 开放接口
	 * */
	var api = {
			initMap: initMap,// 初始化开源地图，并返回此对象的实例
			getChazhi: getChazhi,// 获取x轴和y轴的差值
			getFeatureFromWKT: function(str){return wkt.read(str);},// 根据wkt字符串获取要素
			getWKTString: function(feature){return wkt.write(feature);},// 根据feature获取wkt格式的字符串
			getPolygonStyle: function(){return polygonStyle;},// 获取面样式
			getPolylineStyle: function(){return polylineStyle;},// 获取线样式
			getPointStyle: function(){return pointStyle;},// 获取点样式
			createPoint: createPoint,// 创建点要素
			createlineString: createlineString,// 创建线要素--String
			createLinearRing: createLinearRing,// 创建线要素--创建面的封闭线
			createPolygon: createPolygon,// 创建面要素
			createFeature: createFeature,// 创建feature对象
			setPointImg: setPointImg,// 设置默认标注点的样式
			setMapXY: setMapXY,// 设置父页面的xy对应的input的id
			activeSnapping: activeSnapping,// 开启捕捉
			deactivaSnapping: deactivaSnapping,// 取消捕捉
			drawPoints: drawPoints,// 绘制点到地图上，添加到vectorLayer图层上
			routeSearch: routeSearch,// 网络分析，添加到vectorLayer图层上
			getOrderRoutePoints: function(feature){
				return itmap.openlayer.util.orderRouteResult(feature.geometry.components);
			},// 获取以排序的网络分析后的点的集合
			addPoint: addPoint,// 添加点，并定位到地图中心
			createPointStyle: createPointStyle,// 创建点样式
			addLayer: addLayer,// 添加图层到地图上
			removeLayer: removeLayer,// 添加图层到地图上
			addFeature: addFeature,// 指定图层上的添加要素，默认为vectorLayer图层
			removeFeature: removeFeature,// 指定图层上的移除要素，默认为vectorLayer图层
			deactivateDraw: deactivateDraw,// 停止绘制
			drawPoint: drawPoint// 开启绘制点，使用默认样式，并且每次绘制完成后清除图层
	};
	
	var pointStyle = new OpenLayers.Symbolizer.Point({
		graphicWidth: 34,	
		graphicHeight: 40,	
		graphicXOffset: -10,
		graphicYOffset: -40,
		externalGraphic: "images/map/position.png"
	});
	var polylineStyle = new OpenLayers.Symbolizer.Line({
		strokeWidth: 3,
        strokeOpacity: 1,
        strokeColor: "#6666aa"
	});
	var polygonStyle = new OpenLayers.Symbolizer.Polygon({
		strokeWidth: 1,
        strokeOpacity: 1,
        fillOpacity: 1,
        fillColor: "#9999aa",
        strokeColor: "#6666aa"
	});
	
	
	/**
	 * 初始地图
	 * 如果没有传入参数,则采用默认的options设置
	 * 传入参数的格式如options
	 * */
	function initMap(container, url, config) {
		
		var //底图
			baseMap,
			//显示范围
			bounds;

		//初始化地图
		if (Object.prototype.toString.call(config) !== "[object Object]") {
			config = null;
		}
		
		//bounds = new OpenLayers.Bounds(107.658335527263, 33.694007339867,109.819740124407, 34.7425082587493);
		bounds = new OpenLayers.Bounds(108.6121489285334,34.18003479100233,109.38484380332164,34.37320850969939);
		
	    bMap = new OpenLayers.Map(container || options.mapContainer, {controls:[], maxExtent: bounds, 
	    	//resolutions: [0.0025152827955346596, 0.0012576413977673298, 6.288206988836649E-4, 3.1441034944183245E-4, 1.886462096650995E-4, 6.288206988836648E-5, 3.144103494418324E-5, 1.8864620966509947E-5, 8.803489784371308E-6, 2.5152827955346597E-6],
	    	resolutions: [1.886462096650995E-4, 8.803489784371309E-5, 3.772924193301989E-5, 1.8864620966509947E-5, 8.803489784371308E-6, 2.5152827955346597E-6, 1.2576413977673299E-6],
			projection: "EPSG:4326",
	        units: 'degrees',
	    }); 
	    //添加鼠标控制
        bMap.addControl(new OpenLayers.Control.Navigation());
	    baseMap = new OpenLayers.Layer.WMS("重庆市底图", url || options.baseMapUrl,
					        {
					            LAYERS: baseServiceURL.geoserverBaseLayer.url,
					            STYLES: '',
					            format: 'image/png',
					            tiled: true,
					            tilesOrigin : bMap.maxExtent.left + ',' + bMap.maxExtent.bottom
					        },
					        {
					            buffer: 0,
					            displayOutsideMaxExtent: true,
					            isBaseLayer: true,
					            yx: {'EPSG:4326': true}
					        });
	    
        
		bMap.addLayer(baseMap);
        
		new itmap.openlayer.navigation({
		    mapC : bMap,
		    container : "mapNavigationBox",
		    initExtent : bounds
		});
		bMap.addControl(new OpenLayers.Control.PanZoomBar({
          position: new OpenLayers.Pixel(15, 33),
          zoomWorldIcon : false,
          panIcons : false
        }));
        
        //添加图层
        drawLayer = new OpenLayers.Layer.Vector("drawLayer");
    	drawLayer.id="drawLayer";
    	bMap.addLayer(drawLayer);
    	
        vectorLayer = new OpenLayers.Layer.Vector("vectorLayer");
    	vectorLayer.id="vectorLayer";
    	bMap.addLayer(vectorLayer);
    	
		bMap.setCenter(new OpenLayers.LonLat(108.9420,34.27158),3);
		return this;
	}
	
	function getRoad() {
		$.ajax({
		  type: "POST",
		  dataType: "json",
		  url: "openmap/snappoint/getroad",
		  cache: false,
		  success: function(list){
			if(list) {
				//var wkt = new OpenLayers.Format.WKT();
				// 循环添加道路到图层上
				for(var i=0,len=list.length; i<len; i++) {
					if(list[i]) {
						//var feature = wkt.read(list[i]);
						var feature = new OpenLayers.Feature.Vector(OpenLayers.Geometry.fromWKT(list[i]));
						roadLayer.addFeatures(feature);
					}
				}
	        }
		  },
		  error: function(e) {
		  }
		});
	}
	// 开启捕捉
	function activeSnapping() {
		isSnap = true;
		//开启捕捉功能后查询路网数据
        if(!roadLayer) {
	    	roadLayer = new OpenLayers.Layer.Vector("roadLayer");
			getRoad();
        }
        
		if(!snap) {
			snap = new OpenLayers.Control.Snapping({
	                layer: drawLayer,
	                targets: [roadLayer],
	                greedy: false
	            });
	        snap.events.register("snap", snap, snaplistener);
	        snap.events.register("unsnap", snap, unsnaplistener);
		}
        snap.activate();
	}
	function snaplistener(e) {
		isSnapPoint = true;
	}
	function unsnaplistener(e) {
		isSnapPoint = false;
	}
	// 停止捕捉
	function deactivaSnapping() {
		if(snap)snap.deactivate();
		isSnap = false;
	}
	
	// 添加图层到地图上
	function addLayer(layerId, style, layerClick) {
		var tlayer = bMap.getLayer(layerId);
		if(tlayer) return tlayer;
		tlayer = new OpenLayers.Layer.Vector(layerId, {styleMap: style, eventListeners: {featureclick: layerClick}});
		tlayer.id = layerId;
		bMap.addLayer(tlayer);
		return tlayer;
	}
	
	//移除地图上的图层
	function removeLayer(layerId) {
		var tlayer = bMap.getLayer(layerId);
		if(tlayer) {
			tlayer.destroy();
			bMap.removeLayer(tlayer);
		}
	}
	
	// 设置点图片信息
	function setPointImg(imgUrl, imgWidth, imgHeight, xoffset, yoffset) {
		if(!imgUrl)return;// 如果没有传递图片地址，则不进行设置
		if(!imgWidth)imgWidth = 24;
		if(!imgHeight)imgHeight = 24;
		if(!xoffset)xoffset = -imgWidth/2;
		if(!yoffset)yoffset = -imgHeight/2;
		pointStyle = new OpenLayers.Symbolizer.Point({
			graphicWidth: imgWidth,
			graphicHeight: imgHeight,
			graphicXOffset: xoffset,
			graphicYOffset: yoffset,
			externalGraphic: imgUrl
		});
	}
	
	// 设置点图片信息
	function createPointStyle(imgUrl, imgWidth, imgHeight, xoffset, yoffset) {
		if(!imgWidth)imgWidth = 24;
		if(!imgHeight)imgHeight = 24;
		if(!xoffset)xoffset = -imgWidth/2;
		if(!yoffset)yoffset = -imgHeight/2;
		return new OpenLayers.Symbolizer.Point({
			graphicWidth: imgWidth,
			graphicHeight: imgHeight,
			graphicXOffset: xoffset,
			graphicYOffset: yoffset,
			externalGraphic: imgUrl
		});
	}
	
	// 添加坐标到地图上--默认添加到绘制图层上，并且不定位到地图中心
	function addPoint(x, y, isCenterToMap, layer, att, style) {
		if(!att)att = null;
		pointStyle = style || pointStyle;
		var point = new OpenLayers.Geometry.Point(x, y);
		var graphic = new OpenLayers.Feature.Vector(point, att, pointStyle);
		if(layer)layer.addFeatures(graphic);
		else drawLayer.addFeatures(graphic);
		if(isCenterToMap)bMap.setCenter(new OpenLayers.LonLat(parseFloat(x), parseFloat(y)),7);
		return graphic;
	}
	
	// 设置父页面的x y的input的id
	function setMapXY(xid, yid) {
		mapx = xid;
		mapy = yid;
	}
	// 网络分析features为feature的数组
	function routeSearch(features, callback, routeType, url) {
		var routeName = roadLineNAName;
		if(routeType) {
			if(routeType == "polygon")routeName = roadPolygonNAName;
			if(routeType == "polyline")routeName = roadLineNAName;
		}
		else routeName = roadLineNAName;
		if(!url)url = options.routeServiceUrl;
		var wkt = new OpenLayers.Format.WKT();	
		for(var i=0,len=features.length; i<len; i++) {
			features[i] = wkt.write(features[i]);
		}
		var stops=features.join("|");
		$.ajax({
		  type: "POST",
		  url: "openmap/naServer",
		  cache: false,
		  data: {reqUrl:url,stopPoint:stops,methodname:"getClosedRoute",barriers:"",roadnet:routeName},		
		  success: function(strs){
		  	var routeLine = new OpenLayers.Feature.Vector(OpenLayers.Geometry.fromWKT(strs));
		  	routeLine.style = polylineStyle;
		  	vectorLayer.addFeatures(routeLine);
		  	callback(routeLine);
		  },
		  error: function(e) {
		  	callback(null);
		  }
		});
	}
	
	function save(obj) {
		$.ajax({
			type: "GET",
			url: "",
			data: obj,
			success: function(status) {
			
			},
			fail: function() {
				
			}
		});
	}
	
	// 获取x轴和y轴上的差值
	function getChazhi() {
		return {x: xchazhi, y:ychazhi};
	}
	// 创建point
	function createPoint(x, y) {
		return new OpenLayers.Geometry.Point(x, y);
	}
	// 创建LineString参数为point的array数组
	function createlineString(points) {
		return new OpenLayers.Geometry.LineString(points);
	}
	// 创建LinearRing参数为point的array数组
	function createLinearRing(points) {
		return new OpenLayers.Geometry.LinearRing(points);
	}
	// 创建polygon
	function createPolygon(lines) {
		return new OpenLayers.Geometry.MultiPolygon(new OpenLayers.Geometry.Polygon(lines));
	}
	// 创建feature
	function createFeature(geometry, attributes, style) {
		return new OpenLayers.Feature.Vector(geometry, attributes, style);
	}
	// 添加feature默认为vectorLayer图层上的添加
	function addFeature(feature, layer) {
		if(feature) {
			if(layer) layer.addFeatures(feature);
			else vectorLayer.addFeatures(feature);
		}
	}
	// 移除feature默认为vectorLayer图层上的移除
	function removeFeature(feature, layer) {
		if(feature) {
			if(layer) layer.removeFeatures(feature);
			else vectorLayer.removeFeatures(feature);
		}
	}
	// 绘制多点，并添加到地图上
	function drawPoints(callback, style) {
		if(drawControl)drawControl.deactivate();
    	drawControl=new OpenLayers.Control.DrawFeature(drawLayer,OpenLayers.Handler.Point);
    	drawControl.featureAdded=function(feature) {
    		// 清除图层上的所有点
    		drawLayer.removeAllFeatures();
    		if(isSnap && !isSnapPoint) {
    			callback(null);// 启用捕捉后，没有选中捕捉到的点，返回null
		    	return;
	    	}
	    	callback(feature);
	    	if(style)feature.style = style;
	    	else feature.style = pointStyle;
	    	// 添加自定义点到地图上
			vectorLayer.addFeatures(feature);
    	};
       	bMap.addControl(drawControl);
       	drawControl.activate();
    }
    // 停止绘制
	function deactivateDraw() {
		if(drawControl)drawControl.deactivate();
	}
	// 绘制点，并添加到地图上
	function drawPoint() {
		if(drawControl)drawControl.deactivate();
    	drawControl=new OpenLayers.Control.DrawFeature(drawLayer,OpenLayers.Handler.Point);
    	drawControl.featureAdded=drawEnd;
       	bMap.addControl(drawControl);
       	drawControl.activate();
    }
    // 绘制完成后的回调方法
    function drawEnd(feature) {
    	// 清除图层上的所有点
    	drawLayer.removeAllFeatures();
    	if(isSnap && !isSnapPoint) {
			parentDoc.getElementById(mapx).value = "";
			parentDoc.getElementById(mapy).value = "";
	    	return;
    	}
    	
    	feature.style = pointStyle;
    	// 添加自定义点到地图上
		drawLayer.addFeatures(feature);
		parentDoc.getElementById(mapx).value = feature.geometry.x;
		parentDoc.getElementById(mapy).value = feature.geometry.y;
    }
	
	return api;
})();