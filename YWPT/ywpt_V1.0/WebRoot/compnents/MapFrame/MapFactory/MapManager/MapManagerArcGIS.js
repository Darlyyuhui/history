/**
 * 基础地图类
 * @author ZLT
 * @since 2014-3-25
 */
MapFactory.Define("MapFactory/MapManager",[
	"MapFactory/MapManagerAPI*",
	"esri/map*",
	"esri/layers/ArcGISTiledMapServiceLayer*",
	"esri/geometry/Point*",
	"esri/SpatialReference*",
	"esri/geometry/Extent*",
	"MapFactory/Geometry*",
	"MapFactory/GeometryType*"
],function(api,esriMap,tileLayer,point,spatialReference,extentClass,geoClass,geoType){
	var _mapHandler,_conf = {
			src : "mapDiv",
			mapConfig : {},
			loaded : ""
		},
		srr = null,
		_eventHandler = {},
		_baseLayer = null;
	return function(conf){

		MapFactory.Extend(_conf,conf);

		var _mapConfig = _conf.mapConfig,
			initExtent = _mapConfig.initExtent,
			_events = MapFactory.Events;

		_mapHandler = MapFactory._MapManagerResource[MapFactory.Engine];
		if(!_mapHandler){
			srr = new spatialReference(_mapConfig.spatialReference);
			MapFactory._MapManagerResource[MapFactory.Engine] = _mapHandler = new esriMap(_conf.src,{
				logo : false,
				slider: false,
				autoResize : true,
				displayGraphicsOnPan : false
			});
			_mapHandler.setExtent(new extentClass(initExtent.minX,initExtent.minY,initExtent.maxX,initExtent.maxY));
			_baseLayer = new tileLayer(_mapConfig.layers.baseMap.url,{
				id : _mapConfig.layers.baseMap.id
			});
			_mapHandler.addLayer(_baseLayer);
			esriConfig.defaults.io.proxyUrl="proxy.jsp";
			esriConfig.defaults.io.alwaysUseProxy=false;
			for(var elem in _mapConfig.referenceCSS){
				MapFactory.CreateCssNode(MapFactory.FramePath + _mapConfig.referenceCSS[elem]);
			}
			if(_conf.loaded){
				dojo.connect(_mapHandler,"onLoad",function(){
					setTimeout(function(){_conf.loaded()},100);
				});
			}
			document.getElementById(_conf.src).oncontextmenu = function(){return false;};
		}

		function centerAndZoom(x,y,level,callback){
			_mapHandler.centerAndZoom(new point(parseFloat(x),parseFloat(y),new spatialReference(srr)),level).then(callback);
		}

		function centerAt(x,y,callback){
			_mapHandler.centerAt(new point(x,y,new spatialReference(srr))).then(callback);
		}

		function centerAtWithOffset(x,y,offX,offY,callback){
			var _screenP = toScreen(x,y);
			_screenP.x = _screenP.x + offX;
			_screenP.y = _screenP.y + offY;
			var _mapP = toMap(_screenP.x,_screenP.y);
			centerAt(_mapP.x,_mapP.y,callback);
		}

		function panLeft(callback){
			_mapHandler.panLeft().then(callback);
		}

		function panRight(callback){
			_mapHandler.panRight().then(callback);
		}

		function panUp(callback){
			_mapHandler.panUp().then(callback);
		}

		function panDown(callback){
			_mapHandler.panDown().then(callback);
		}

		function setLevel(level,callback){
			_mapHandler.setLevel(level).then(callback);
		}

		function getLevel(){
			return _mapHandler.getLevel();
		}

		function getNumsOfLevel(){
			if(_baseLayer instanceof tileLayer){
				return _baseLayer.tileInfo.lods.length;
			}
			return 0;
		}

		function setExtent(extent){
			_mapHandler.setExtent(new extentClass(extent.minX,extent.minY,extent.maxX,extent.maxY));
		}

		function getCurrentExtent(){
			var extent = _mapHandler.extent;
			return {
				minX : extent.xmin,
				minY : extent.ymin,
				maxX : extent.xmax,
				maxY : extent.ymax
			}
		}

		function isInCurrentExtent(x,y){
			var _p = new point(parseFloat(x),parseFloat(y),new spatialReference(srr));
			return _mapHandler.extent.contains(_p);
		}

		function toMap(x,y){
			var p = _mapHandler.toMap(new point(x,y));
			return {
				x : p.x,
				y : p.y
			}
		}

		function toScreen(x,y){
			var p = _mapHandler.toScreen(new point(x,y,new spatialReference(srr)));
			return {
				x : p.x,
				y : p.y
			}
		}

		function getAllLayersID(){
			return _mapHandler.layerIds.concat(_mapHandler.graphicsLayerIds);
		}

		function reorderLayer(layerId,layerIndex){
			var _layer = _mapHandler.getLayer(layerId);
			if(!_layer){
				return;
			}
			_mapHandler.reorderLayer(_layer,layerIndex);
		}

		function setLayerAddEvent(func){
			if(MapFactory.VariableTypes.isFunc(func)){
				dojo.connect(_mapHandler,"onLayerAdd",function(layer){
					func(layer.id);
				});
			}
		}

		function setLayerRemoveEvent(func){
			if(MapFactory.VariableTypes.isFunc(func)){
				dojo.connect(_mapHandler,"onLayerRemove",function(layer){
					func(layer.id);
				});
			}
		}

		function setMouseDragEvent(obj){
			if(obj.dragstart){
				_eventHandler["onMouseDragStart"] = dojo.connect(_mapHandler,"onMouseDragStart",function(e){
					obj.dragstart({
						mapPoint: new geoClass({
							type : geoType.POINT,
							points : e.mapPoint.x + "," + e.mapPoint.y
						})
					});
				});
			}
			if(obj.dragmove){
				_eventHandler["onMouseDrag"] = dojo.connect(_mapHandler,"onMouseDrag",function(e){
					obj.dragmove({
						mapPoint: new geoClass({
							type : geoType.POINT,
							points : e.mapPoint.x + "," + e.mapPoint.y
						})
					});
				});
			}
			if(obj.dragend){
				_eventHandler["onMouseDragEnd"] = dojo.connect(_mapHandler,"onMouseDragEnd",function(e){
					obj.dragend({
						mapPoint: new geoClass({
							type : geoType.POINT,
							points : e.mapPoint.x + "," + e.mapPoint.y
						})
					});
				});
			}
		}

		function removeMouseDragEvent(){
			if(_eventHandler["onMouseDragStart"]){
				dojo.disconnect(_eventHandler["onMouseDragStart"]);
			}
			if(_eventHandler["onMouseDrag"]){
				dojo.disconnect(_eventHandler["onMouseDrag"]);
			}
			if(_eventHandler["onMouseDragEnd"]){
				dojo.disconnect(_eventHandler["onMouseDragEnd"]);
			}
		}

		function setZoomEvent(obj){
			if(obj.zoomstart){
				dojo.connect(_mapHandler,"onZoomStart",function(extent, zoomFactor, anchor){
					obj.zoomstart({
						minX : extent.xmin,
						minY : extent.ymin,
						maxX : extent.xmax,
						maxY : extent.ymax
					});
				});
			}
			if(obj.zooming){
				dojo.connect(_mapHandler,"onZoom",function(extent,zoomFactor,anchor,level){
					obj.zooming({
						minX : extent.xmin,
						minY : extent.ymin,
						maxX : extent.xmax,
						maxY : extent.ymax
					});
				});
			}
			if(obj.zoomend){
				dojo.connect(_mapHandler,"onZoomEnd",function(extent,zoomFactor,anchor,level){
					obj.zoomend({
						minX : extent.xmin,
						minY : extent.ymin,
						maxX : extent.xmax,
						maxY : extent.ymax
					});
				});
			}
		}

		function setMouseMoveEvent(func){
			_eventHandler["onMouseMove"] = dojo.connect(_mapHandler,"onMouseMove",function(event){
				if(func){
					func({
						screenPoint : new geoClass({
							type : geoType.POINT,
							points : event.screenPoint.x + "," + event.screenPoint.y
						}),
						mapPoint : new geoClass({
							type : geoType.POINT,
							points : event.mapPoint.x + "," + event.mapPoint.y
						})
					});
				}
			});
		}

		function removeMouseMoveEvent(){
			if(_eventHandler["onMouseMove"]){
				dojo.disconnect(_eventHandler["onMouseMove"]);
			}
		}

		function setMouseDownEvent(func){
			if(!func){
				return;
			}
			_eventHandler["onMouseDown"] = dojo.connect(_mapHandler,"onMouseDown",function(event){
				func({
					which : event.which,
					button : event.button,
					screenPoint : new geoClass({
						type : geoType.POINT,
						points : event.screenPoint.x + "," + event.screenPoint.y
					}),
					mapPoint : new geoClass({
						type : geoType.POINT,
						points : event.mapPoint.x + "," + event.mapPoint.y
					})
				});
			});
		}

		function removeMouseDownEvent(){
			if(_eventHandler["onMouseDown"]){
				dojo.disconnect(_eventHandler["onMouseDown"]);
			}
		}

		function setMouseUpEvent(func){
			_eventHandler["onMouseUp"] = dojo.connect(_mapHandler,"onMouseUp",function(event){
				func({
					which : event.which,
					button : event.button,
					screenPoint : new geoClass({
						type : geoType.POINT,
						points : event.screenPoint.x + "," + event.screenPoint.y
					}),
					mapPoint : new geoClass({
						type : geoType.POINT,
						points : event.mapPoint.x + "," + event.mapPoint.y
					})
				});
			});
		}

		function removeMouseUpEvent(){
			if(_eventHandler["onMouseUp"]){
				dojo.disconnect(_eventHandler["onMouseUp"]);
			}
		}

		function setMouseClickEvent(func){
			if(!func){
				return;
			}
			_eventHandler["onMouseClick"] = dojo.connect(_mapHandler,"onClick",function(event){
				func({
					screenPoint : new geoClass({
						type : geoType.POINT,
						points : event.screenPoint.x + "," + event.screenPoint.y
					}),
					mapPoint : new geoClass({
						type : geoType.POINT,
						points : event.mapPoint.x + "," + event.mapPoint.y
					})
				});
			});
		}

		function removeMouseClickEvent(){
			if(_eventHandler["onMouseClick"]){
				dojo.disconnect(_eventHandler["onMouseClick"]);
			}
		}

		function setMouseDoubleClickEvent(func){
			_mapHandler.disableDoubleClickZoom();
			_eventHandler["onMouseDoubleClick"] = dojo.connect(_mapHandler,"onDblClick",function(e){
				if(func){
					var _x = event.clientX,
						_y = event.clientY,
						_mapP = toMap(_x,_y);
					func({
						screenPoint : new geoClass({
							type : geoType.POINT,
							points : _x + "," + _y
						}),
						mapPoint : new geoClass({
							type : geoType.POINT,
							points : _mapP.x + "," + _mapP.y
						})
					});
				}
			});
		}

		function removeMouseDoubleClickEvent(){
			if(_eventHandler["onMouseDoubleClick"]){
				dojo.disconnect(_eventHandler["onMouseDoubleClick"]);
			}
		}

		function setExtentChangeEvent(func){
			_eventHandler["onExtentChange"] = dojo.connect(_mapHandler,"onExtentChange",function(extent){
				if(func){
					func({
						minX : extent.xmin,
						minY : extent.ymin,
						maxX : extent.xmax,
						maxY : extent.ymax
					});
				}
			});
		}

		function removeExtentChangeEvent(){
			if(_eventHandler["onExtentChange"]){
				dojo.disconnect(_eventHandler["onExtentChange"]);
			}
		}

		function setMouseWheelEvent(func){
			_events.onMouseWheel(document.getElementById(_conf.src),function(e){
				var _directionFlag = e.wheelDelta > 0 ? "in" : "out";
				func({
					wheelDirection : _directionFlag
				});
				return false;
			});
		}

		function getSpatialReferenceCode(){
			return _mapConfig.spatialReference;
		}

		function getProjectSpatialReferenceCode(){
			return _mapConfig.projectSpatialReference;
		}

		function getInitExtent(){
			return {
				minX : initExtent.minX,
				minY : initExtent.minY,
				maxX : initExtent.maxX,
				maxY : initExtent.maxY
			}
		}

		function resize(){
			_mapHandler.resize();
		}

		function getMapConfig(){
			return _conf.mapConfig;
		}

		function disablePan(){
			_mapHandler.disablePan();
		}

		function enablePan(){
			_mapHandler.enablePan();
		}

		function isLayerExist(layerid){
			return !!_mapHandler.getLayer(layerid);
		}

		function zoomIn(){
			var currentZoom = _mapHandler.getZoom(),
				maxZoom = _mapHandler.getMaxZoom(),
				minZoom = _mapHandler.getMinZoom();
			if(currentZoom == maxZoom || currentZoom == minZoom){
				return;
			}
			_mapHandler.setZoom(currentZoom + 1);
		}

		function zoomOut(){
			var currentZoom = _mapHandler.getZoom(),
				maxZoom = _mapHandler.getMaxZoom(),
				minZoom = _mapHandler.getMinZoom();
			if(currentZoom == maxZoom || currentZoom == minZoom){
				return;
			}
			_mapHandler.setZoom(currentZoom - 1);
		}

		function getMapSize(){
			return {
				width : _mapHandler.width,
				height : _mapHandler.height
			};
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});