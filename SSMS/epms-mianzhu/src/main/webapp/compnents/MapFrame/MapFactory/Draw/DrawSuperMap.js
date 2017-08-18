MapFactory.Define("MapFactory/Draw",[
    "MapFactory/DrawAPI*",
    "MapFactory/LayerManager",
    "MapFactory/GeometryType*",
    "MapFactory/SymbolUtil",
    "MapFactory/GeometryUtil",
    "MapFactory/Query"
],function(api,layerManager,geoType,symbolUtil,geoUtil,query){
	var _tempDrawLayer = null,
		_isDrawing = false;
	return function(){
		var _draw,_handler,_fillSym,_lineSym,_markerSym,_callback,
			_layerid = "tempDrawLayer",
			_snapResourceLayerid = "snapResourceLayer",
			_symbolUtil = symbolUtil(),
			_snapResourceLayer = null,
			_mapHandler = MapFactory._MapManagerResource[MapFactory.Engine],
			_geoUtil = geoUtil(),
			_type,_option = {handlerOptions:{}},
			_isSnap = false,		
			_urls = [],_ids = [],
			_snappingManager = null,
			_resGraphicList = [],
			_openLayerDefaultLayer = null,
			_targets = [];

		function setGeoType(geometryType){
			_type = geometryType;
		}

		function setFillSymbol(fillSymbol){
			_fillSym = _symbolUtil.convertFromMapFactory(geoType.POLYGON,fillSymbol);
		}

		function setLineSymbol(lineSymbol){
			_lineSym = _symbolUtil.convertFromMapFactory(geoType.POLYLINE,lineSymbol);
		}

		function setMarkerSymbol(markerSymbol){
			_markerSym = _symbolUtil.convertFromMapFactory(geoType.POINT,markerSymbol);
		}

		function setDrawEndEvent(func){
			_callback = func;
		}

		function openSnapping(snapConf){
			_isSnap = true;
			_urls = snapConf.urls;
			_ids = snapConf.ids;
		}

		function closeSnapping(){
			_isSnap = false;
			if(_snappingManager){
				_snappingManager.deactivate();
			}
		}

		function activate(){
			if(_isDrawing){
				return;
			}
			if(!_tempDrawLayer){
				_tempDrawLayer = new SuperMap.Layer.Vector();
				_tempDrawLayer.id = _layerid;
				_mapHandler.addLayer(_tempDrawLayer);
			}

			if(_callback){
				_option["featureAdded"] = function(e){
					_tempDrawLayer.removeAllFeatures();
					//约束经纬度为小数点后七位
					var x=e.geometry.x;
					var y=e.geometry.y;
					e.geometry.x=x.toFixed(7);
					e.geometry.y=y.toFixed(7);
					_callback(_geoUtil.convertFromObject(e.geometry));
				};
			}
			if(_type == geoType.POINT){
				_handler = SuperMap.Handler.Point;
				_option.handlerOptions["createFeature"] = _drawPoint();
			}
			if(_type == geoType.POLYLINE){
				_handler = SuperMap.Handler.Path;
				_option.handlerOptions["createFeature"] = _drawPolyline();
			}
			if(_type == geoType.POLYGON){
				_handler = SuperMap.Handler.Polygon;
				_option.handlerOptions["createFeature"] = _drawPolygon();
			}
			_draw = new SuperMap.Control.DrawFeature(_tempDrawLayer,_handler,_option);
			_mapHandler.addControl(_draw);
			_draw.activate();
			if(_isSnap){
				_snappingInit();
			}
			_isDrawing = true;
		}

		function _drawPoint(){
			return function(pixel){
				_openLayerDefaultLayer = this.layer;
				if(_openLayerDefaultLayer && _openLayerDefaultLayer.id != _layerid){
					_mapHandler.removeLayer(_openLayerDefaultLayer);
				}
				this.layer = _tempDrawLayer;
				var lonlat = this.layer.getLonLatFromViewPortPx(pixel); 
		        var geometry = new SuperMap.Geometry.Point(
		            lonlat.lon, lonlat.lat
		        );
		        this.point = new SuperMap.Feature.Vector(geometry,{},_markerSym);
		        this.callback("create", [this.point.geometry, this.point]);
		        this.point.geometry.clearBounds();
		        this.layer.addFeatures([this.point], {silent: true});
		    }
		}

		function _drawPolyline(){
			return function(pixel){
				_openLayerDefaultLayer = this.layer;
				if(_openLayerDefaultLayer && _openLayerDefaultLayer.id != _layerid){
					_mapHandler.removeLayer(_openLayerDefaultLayer);
				}
				this.layer = _tempDrawLayer;
				var lonlat = this.layer.getLonLatFromViewPortPx(pixel); 
		        var geometry = new SuperMap.Geometry.Point(
		            lonlat.lon, lonlat.lat
		        );
		        this.point = new SuperMap.Feature.Vector(geometry);
		        this.line = new SuperMap.Feature.Vector(
		            new SuperMap.Geometry.LineString([this.point.geometry]),
		            {},
		            _lineSym
		        );
		        this.callback("create", [this.point.geometry, this.getSketch()]);
		        this.point.geometry.clearBounds();
		        this.layer.addFeatures([this.line, this.point], {silent: true});
			}
		}

		function _drawPolygon(){
			return function(pixel){
				_openLayerDefaultLayer = this.layer;
				if(_openLayerDefaultLayer && _openLayerDefaultLayer.id != _layerid){
					_mapHandler.removeLayer(_openLayerDefaultLayer);
				}
				this.layer = _tempDrawLayer;
		        var lonlat = this.layer.getLonLatFromViewPortPx(pixel);
		        var geometry = new SuperMap.Geometry.Point(
		            lonlat.lon, lonlat.lat
		        );
		        this.point = new SuperMap.Feature.Vector(geometry);
		        this.line = new SuperMap.Feature.Vector(
		            new SuperMap.Geometry.LinearRing([this.point.geometry])
		        );
		        this.polygon = new SuperMap.Feature.Vector(
		            new SuperMap.Geometry.Polygon([this.line.geometry]),
		            {},
		            _fillSym
		        );
		        this.callback("create", [this.point.geometry, this.getSketch()]);
		        this.point.geometry.clearBounds();
		        this.layer.addFeatures([this.polygon, this.point], {silent: true});
			}
		}

		function _snappingInit(){
			if(_snappingManager){
				return;
			}
			_snapResourceLayer = new SuperMap.Layer.Vector(_snapResourceLayerid);
			if(_ids){
				for(var i=0,len=_ids.length;i<len;i++){
					_targets.push(_mapHandler.getLayer(_ids[i]));
				}
				_snappingHandler();
			}
			if(_urls){
				_iteratorLayers(0);
			}
		}

		function _snappingHandler(){
			_targets.push(_snapResourceLayer);
			_snappingManager = new SuperMap.Control.Snapping({
                layer: _tempDrawLayer,
                targets: _targets,
                greedy: false
            });
			_snappingManager.activate();
		}

		function _iteratorLayers(index){
			if(index < _urls.length){
				_query.setUrl(_urls[index]);
				_query.execute(function(graphicList){
					_resGraphicList = _resGraphicList.concat(graphicList);
				       index=index+1;
					_iteratorLayers(index);
				},function(){
					_iteratorLayers(index);
				});
			}else{
				for(var i=0,len=_resGraphicList.length;i<len;i=i+1){
					_resGraphicList[i] = new SuperMap.Feature.Vector(_geoUtil.convertFromMapFactory(_resGraphicList[i].geo));
				}
				_snapResourceLayer.addFeatures(_resGraphicList);
				_isVectorReady = true;
				_snappingHandler();
			}
		}

		function deactivate(){
			if(_draw){
				if(_mapHandler.getLayer(_layerid)){
					if(_snappingManager)_snappingManager.deactivate();
					_snappingManager = null;
					_mapHandler.removeLayer(_tempDrawLayer);
					_tempDrawLayer = null;
				}
				_draw.deactivate();
				_mapHandler.removeControl(_draw);
				_draw = null;
				_isDrawing = false;
			}
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});