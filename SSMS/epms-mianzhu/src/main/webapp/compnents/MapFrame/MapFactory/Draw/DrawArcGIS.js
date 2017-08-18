MapFactory.Define("MapFactory/Draw",[
    "MapFactory/DrawAPI*",
    "MapFactory/GeometryType*",
    "MapFactory/GraphicManager",
    "MapFactory/GeometryUtil",
    "esri/layers/FeatureLayer*",
    "esri/SnappingManager*",
    "MapFactory/MapManager",
    "MapFactory/LayerManager",
    "MapFactory/Geometry*"
],function(api,geoType,graphicManager,geoUtil,featureLayer,snappingManager,mapManager,layerManager,geoClass){
	return function(){

		var _draw = null,_fillSym,_lineSym,_markerSym,_callback,
			_geoUtil = geoUtil(),
			_type,_mapHandler = MapFactory._MapManagerResource[MapFactory.Engine],
			_isSnap = false,
			_snapLayers = [],
			_snappingManager = null,
			_mapManager = mapManager(),
			_tempLayerID = "tempLayer",
			_tempDrawLayerID = "tempDrawLayer",
			_tempLayer = layerManager(_tempLayerID),
			_tempDrawLayer = layerManager(_tempDrawLayerID),
			_snapConf = {},
			_snapSymbol = null,
			_snapPoint = null,
			_dbClickHandler = null,
			_mouseDownHandler = null;

		function setGeoType(geometryType){
			_type = geometryType;
		}

		function setFillSymbol(fillSymbol){
			_fillSym = fillSymbol;
		}

		function setLineSymbol(lineSymbol){
			_lineSym = lineSymbol;
		}

		function setMarkerSymbol(markerSymbol){
			_snapSymbol = markerSymbol;
			_markerSym = markerSymbol;
		}

		function setDrawEndEvent(func){
			_callback = func;
		}

		function openSnapping(snapConf){
			_isSnap = true;
			_snapConf = snapConf;
		}

		function closeSnapping(){
			_isSnap = false;
			if(_snappingManager){
				_snappingManager.destroy();
				_snappingManager = null;
			}
		}

		function activate(){
			if(!_type){
				return;
			}

			_mapHandler.disableDoubleClickZoom();

			switch(_type){
				case geoType.POINT : {
					_drawPoint();
					break;
				}
				case geoType.POLYLINE : {
					_drawPolyline();
					break;
				}
				case geoType.POLYGON : {
					_drawPolygon();
					break;
				}
			}
		}

		function deactivate(){
			_callback = null;
			_mapHandler.enableDoubleClickZoom();
			_mapManager.removeMouseMoveEvent();
			_mapManager.removeMouseClickEvent();
			_mapManager.removeMouseDownEvent();
			_mapManager.removeMouseUpEvent();
			_mapManager.removeMouseDoubleClickEvent();
			if(_mouseDownHandler){
				dojo.disconnect(_mouseDownHandler);
			}
			if(_dbClickHandler){
				dojo.disconnect(_dbClickHandler);
			}
			if(_snappingManager){
				_snappingManager.destroy();
				_snappingManager = null;
			}
		}

		function _drawPoint(){
			var _mapP = null;
			_mapManager.setMouseMoveEvent(function(e){
				var _graphic = {},
					_point = null;
				if(_isSnap){
					_snappingHandler(e.screenPoint,function(mapP){
						_mapP = mapP;
					});
				}
				if(_markerSym){
					_graphic["symbol"] = _markerSym;
				}
				if(_mapP){
					_point = _mapP;
				}else{
					_point = e.mapPoint;
				}
				_graphic["geo"] = _point;
				_tempDrawLayer.clear();
				//graphicManager(_graphic).addToLayer(_tempDrawLayerID);
			});
			_mapManager.setMouseDownEvent(function(e){
				_tempDrawLayer.clear();
				if(_callback){
					var _point = null;
					if(_mapP){
						_point = _mapP;
					}else{
						_point = e.mapPoint;
					}
					_callback(_point);
				}
			});
		}

		function _drawPolyline(){
			var _lineGeo = "",
				_graphic = {},
				_isFirstClick = true;
			
			var _lastPoint = "";

			if(_lineSym){
				_graphic["symbol"] = _lineSym;
			}

			_mapManager.setMouseMoveEvent(function(e){
				if(_isSnap){
					_snappingHandler(e.screenPoint,function(mapP){
						_snapPoint = mapP;
					});
				}
				_tempDrawLayer.clear();
				var _points = "";
				if(_snapPoint){
					_points = _snapPoint.points;
				}else{
					_points = e.mapPoint.points;
				}
				_graphic["geo"] = new geoClass({
					type : geoType.POLYLINE,
					points : _lineGeo + "," + _points
				});
				graphicManager(_graphic).addToLayer(_tempDrawLayerID);
			});

			_mapManager.setMouseUpEvent(function(clickEvt){
				drawing(clickEvt);
			});

			function drawing(e){
				var _commaFlag = "",
					_points = "";
				if(_snapPoint){
					_points = _snapPoint.points;
				}else{
					_points = e.mapPoint.points;
				}
				if(!_isFirstClick){
					_commaFlag = ",";
				}else{
					_isFirstClick = false;
				}
				if(_points == _lastPoint){
					drawDone(e);
					return;
				}else{
					_lastPoint = _points;
				}
				_lineGeo = _lineGeo + _commaFlag + _points;
				_tempDrawLayer.clear();
				_graphic["geo"] = new geoClass({
					type : geoType.POLYLINE,
					points : _lineGeo
				});
				graphicManager(_graphic).addToLayer(_tempDrawLayerID);
			}

			function drawDone(e){
				_tempDrawLayer.clear();
				if(_callback){
					var points = "";
					if(_snapPoint){
						points = "," + _snapPoint.points;
					}else{
						points = "," + e.mapPoint.points;
					}
					_callback(new geoClass({
						type : geoType.POLYLINE,
						points : _lineGeo + points
					}));
				}
				_lineGeo = "";
				_isFirstClick = true;
				_graphic = {};
			}
		}

		function _drawPolygon(){
			var _polygonGeo = "",
				_graphic = {},
				_startPoint = "",
				_mapP = null,
				_lastPoint = "",
				_isFirstClick = true;
			if(_fillSym){
				_graphic["symbol"] = _fillSym;
			}

			_mapManager.setMouseMoveEvent(function(e){
				if(_isSnap){
					_snappingHandler(e.screenPoint,function(mapP){
						_mapP = mapP;
					});
				}
				_tempDrawLayer.clear();
				var _points = "";
				if(_mapP){
					_points = _mapP.points;
				}else{
					_points = e.mapPoint.points;
				}
				_graphic["geo"] = new geoClass({
					type : geoType.POLYGON,
					points : _polygonGeo + "," + _points
				});
				graphicManager(_graphic).addToLayer(_tempDrawLayerID);
			});

			_mapManager.setMouseUpEvent(drawing);

			function drawing(clickEvt){
				var _commaFlag = "",
					_points = "";
				_points = clickEvt.mapPoint.points;
				if(_points == _lastPoint){
					drawDone(clickEvt);
					return;
				}else{
					_lastPoint = _points;
				}
				if(!_isFirstClick){
					_commaFlag = ",";
				}else{
					_startPoint = _points;
					_isFirstClick = false;
				}
				_polygonGeo = _polygonGeo + _commaFlag + _points;
				_graphic["geo"] = new geoClass({
					type : geoType.POLYGON,
					points : _polygonGeo
				});
				_tempDrawLayer.clear();
				graphicManager(_graphic).addToLayer(_tempDrawLayerID);
			}

			function drawDone(e){
				_tempDrawLayer.clear();
				if(_callback){
					var points = "";
					if(_mapP){
						points = _mapP.points;
					}else{
						points = e.mapPoint.points;
					}
					_callback(new geoClass({
						type : geoType.POLYGON,
						points : _polygonGeo + "," + points + "," + _startPoint
					}));
				}
				_polygonGeo = "";
				_isFirstClick = true;
				_graphic = {};
				_mapP = null;
				_startPoint = "";
			}
		}

		function _snappingInit(){
			if(_snappingManager){
				return;
			}

			var _ids = _snapConf.ids,
				_urls = _snapConf.urls;

			if(_ids && _ids.length){
				for(var i=0,len=_ids.length;i<len;i++){
					if(_ids[i]){
						var _layer = _mapHandler.getLayer(_ids[i]);
						if(_layer){
							_snapLayers.push({layer:_layer});
						}
					}
				}
			}

			if(_urls && _urls.length){
				for(var i=0,len=_urls.length;i<len;i++){
					if(_urls[i]){
						_snapLayers.push({layer:new featureLayer(_urls[i])});
					}
				}
			}

			if(!_snapLayers.length){
				return;
			}

			var _options = {
				map : _mapHandler,
				alwaysSnap : true,
				tolerance : 10,
				layerInfos : _snapLayers
			};

			_snappingManager = new snappingManager(_options);
		}

		function _snappingHandler(screenPoint,func){
			var _screenP = _geoUtil.convertFromMapFactory(screenPoint);
			_screenP.spatialReference = null;
			_tempLayer.clear();
			_snappingInit();
			_snappingManager.getSnappingPoint(_screenP).then(function(value){
				if(!value){
					if(func){
						func();
					}
					return;
				}
				var _mapP = _geoUtil.convertFromObject(value);
				var graphic = {
					geo : _mapP
				}
				if(_snapSymbol){
					graphic["symbol"] = _snapSymbol;
				}
				//graphicManager(graphic).addToLayer(_tempLayerID);
				if(func){
					func(_mapP);
				}
			});
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});