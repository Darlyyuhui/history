
var mapMarkObj;
var mapMark = (function () {
	return function(map) {
		var api = {
			addPoint : addPoint,
			addPointByXY : addPointByXY,
			addPointOnLayer : addPointOnLayer,
			addPointOnRoad : addPointOnRoad,
			disableMapSnapping : disableRoadLineSnapping,
			disableMapClick : disableMapClick,
			addRoad : addRoad,
			addPolygon : addPolygon
		};
		
		var pointGraphicLayer;
		var lineGraphicLayer;
		var polygonGraphicLayer;
		
		function _init() {
			polygonGraphicLayer = new esri.layers.GraphicsLayer();
			polygonGraphicLayer.id = "mapMark_pgLayer";
			map.addLayer(polygonGraphicLayer);
			lineGraphicLayer = new esri.layers.GraphicsLayer();
			lineGraphicLayer.id = "mapMark_lLayer";
			map.addLayer(lineGraphicLayer);
			pointGraphicLayer = new esri.layers.GraphicsLayer();
			pointGraphicLayer.id = "mapMark_pLayer";
			map.addLayer(pointGraphicLayer);
		}
		_init();
		
		//启动捕捉功能
		function activeRoadLineSnapping(layer){
			var snappingParams = {
				map:map,
				alwaysSnap:true,
				tolerance:30,
				//layerInfos:[{layer:new esri.layers.FeatureLayer(baseServiceURL.roadline.url)}]
				layerInfos:[{layer:layer, snapToPoint:false,snapToVertex:false,snapToEdge:true}]
			}
			map.enableSnapping(snappingParams);
		}
		// 停止捕捉功能
		function disableRoadLineSnapping(){
			map.disableSnapping();
		}
		function disableMapClick() {
			dojo.disconnect(mapClickHandler);
		}
		
		// 高亮渲染一条道路--callback返回所选道路的graphic对象或者""
		function addRoad(road, isSetExtent, callback) {
			queryByWhere(baseServiceURL.roadline.url, "DLMC='"+road+"'", function(queryResult) {
				lineGraphicLayer.clear();
				if(queryResult.features.length == 0) {
					if(callback)callback("");
					return;
				}
				var polyline = new esri.geometry.Polyline(map.spatialReference);
				for(var i=0; i<queryResult.features.length; i++) {
					var graphic = queryResult.features[i];
					if(graphic.geometry.type == "polyline") {
						for(var j in graphic.geometry.paths) {
							polyline.addPath(graphic.geometry.paths[j]);
						}
					}
				}
				var newGraphic = new esri.Graphic(polyline, polylineSymbol());
				lineGraphicLayer.add(newGraphic);
				if(callback)callback(newGraphic);
				if(isSetExtent)map.setExtent(polyline.getExtent());
			});
		}
		
		var mapClickHandler;
		function addPointOnLayer(layer, isClear, callback, imgsrc) {
			activeRoadLineSnapping(layer);
			mapClickHandler = dojo.connect(map, "onClick", function(evt) {
				if(isClear)pointGraphicLayer.clear();
				var latitude = "";
				var longitude = "";
				
				var deferred = map.snappingManager.getSnappingPoint(evt.screenPoint);
				deferred.then(function(value){
				  if(typeof value != "undefined"){
				    var snapPoint = value;
				    longitude = value.x;
				    latitude = value.y;
					var tempPoint = new esri.Graphic(value, createPicSymbol(imgsrc));
					pointGraphicLayer.add(tempPoint);
				    if(callback)callback({point:tempPoint, msg:"ok"});
				  }
				  else {
				    curPoint = null;
				    if(callback)callback({point:null, msg:"距离道路太远!"});
				  }
				},
				function(error){
				  curPoint = null;
				  if(callback)callback({point:null, msg:"在道路上选点时出现错误！"});
				});
			});
		}
		function addPointOnRoad(isClear, callback, imgsrc) {
			addPointOnLayer(new esri.layers.FeatureLayer(baseServiceURL.roadline.url), isClear, callback, imgsrc);
		}
		function createPicSymbol(imgsrc) {
			if(imgsrc)return new esri.symbol.PictureMarkerSymbol(imgsrc, 24, 24);
			var ps = new esri.symbol.PictureMarkerSymbol("images/map/position.png", 34, 40);
			ps.setOffset(7, 20);
			return ps;
		}
		
		// 通过x,y坐标值添加点
		function addPointByXY(x,y,isClear, imgsrc) {
			if(isClear)pointGraphicLayer.clear();
			var point = new esri.Graphic(new esri.geometry.Point(x,y,map.spatialReference), createPicSymbol(imgsrc));
			pointGraphicLayer.add(point);
		}
		// 标注点
		function addPoint(isClear, imgsrc) {
			if(isClear)pointGraphicLayer.clear();
			mapClickHandler = dojo.connect(map, "onClick", function(evt) {
				pointGraphicLayer.add(new esri.Graphic(evt.mapPoint, createPicSymbol(imgsrc)));
			});
		}
		
		function queryByWhere(wherestr, showQueryResult, layerUrl) {
			var queryTask = new esri.tasks.QueryTask(layerUrl);
			var query = new esri.tasks.Query();
			query.where = wherestr;
			query.outFields = ["*"];
			query.returnGeometry = true;
			
			queryTask.execute(query, showQueryResult, function(e){
			});
		}
		function polylineSymbol(){
			return new esri.symbol.SimpleLineSymbol(
						esri.symbol.SimpleLineSymbol.STYLE_SOLID,
						new dojo.Color([255,0,0]),3
			       );
		}
		function addPolygon() {
			
		}
		
		mapMarkObj = api;
		return api;
	}
})();
