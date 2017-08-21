/**
 * 基础地图类
 * @author ZLT
 * @since 2014-3-25
 */
MapFactory.Define("MapFactory/MapManager",[
	"MapFactory/MapManagerAPI*",
	"MapFactory/Geometry*",
	"MapFactory/GeometryType*"
],function(api,Geometry,GeometryType){
	var _conf = {
			src : "mapDiv",
			mapConfig : {},
			loaded : ""
		},
		_eventHandlers = {
			"addLayer" : {},
			"removeLayer" : {},
			"dragStart" : {},
			"dragMove" : {},
			"dragEnd" : {},
			"zoomStart" : {},
			"zooming" : {},
			"zoomEnd" : {},
			"mouseMove" : {},
			"mouseDown" : {},
			"mouseUp" : {},
			"mouseClick" : {},
			"mouseWheel" : {},
			"mouseDbClick" : {},
			"extentChange" : {}
		},
		_controls = {};
	return function(conf){
		var _mapHandler,
			_panFactor = 50,
			_defferTime = 500,
			onLayerAddEvent,onLayerRemoveEvent,
			_variableTypes = MapFactory.VariableTypes,
			_events = MapFactory.Events;

		MapFactory.Extend(_conf,conf);

		var _mapConfig = _conf.mapConfig,
			initExtent = _mapConfig.initExtent;

		_mapHandler = MapFactory._MapManagerResource[MapFactory.Engine];
		if(!_mapHandler){
			var spatialReference = "EPSG:" + _mapConfig.spatialReference;
			var tileOption = {};
			tileOption[spatialReference] = true;

			_controls["Navigation"] = new OpenLayers.Control.Navigation();

			MapFactory._MapManagerResource[MapFactory.Engine] = _mapHandler = new OpenLayers.Map(_conf.src,{
				maxExtent : new OpenLayers.Bounds(initExtent.minX,initExtent.minY,initExtent.maxX,initExtent.maxY),
				resolutions : _mapConfig.resolutions,
				projection : spatialReference,
				units : "degrees",
				tileManager : null,
				controls : [_controls["Navigation"]]
			});
			_controls["Navigation"].activate();

			_mapHandler.events.register("mousemove",{},function(){
				if(_mapHandler.dragging){
					var _layers = _mapHandler.layers;
					for(var i=0,len=_layers.length;i<len;i++){
						if(_layers[i].visibility && i!=0){
							_layers[i].display(false);
						}
					}
				}
			});

			var baseLayer = new OpenLayers.Layer.WMS(
				"basemap",_mapConfig.serviceUrl,
				{
					LAYERS:_mapConfig.layers.baseMap.url,
					tiled:true,
					format:"image/png",
					tilesOrigin:initExtent.minX + "," + initExtent.minY
				},
				{
					tileSize: new OpenLayers.Size(256,256)
				}
			);
			baseLayer.id = _mapConfig.layers.baseMap.id;
			_mapHandler.addLayer(baseLayer);
			_mapHandler.zoomToMaxExtent();

			for(var elem in _mapConfig.referenceCSS){
				MapFactory.CreateCssNode(MapFactory.FramePath + _mapConfig.referenceCSS[elem]);
			}
			document.getElementById(_conf.src).oncontextmenu = function(){return false;};

			// 延迟设置地图事件
			setTimeout(function(){
				_mouseDownEvt();
				_mouseUpEvt();
				_layerAddEvt();
				_layerRemoveEvt();
				_dragEvt();
				_zoomEvt();
				_mouseMoveEvt();
				_mouseClickEvt();
				_dbClickEvt();
				_extentChangeEvt();
				_mouseWheelEvt();
				if(_conf.loaded){
					_conf.loaded();
				}
			},200);
		}

		function centerAndZoom(x,y,level,callback){
			_mapHandler.setCenter(new OpenLayers.LonLat(x,y),level);
			if(_variableTypes.isFunc(callback)){
				setTimeout(callback,_defferTime);
			}
		}

		function centerAt(x,y,callback){
			_mapHandler.setCenter(new OpenLayers.LonLat(x,y));
			if(_variableTypes.isFunc(callback)){
				setTimeout(callback,_defferTime);
			}
		}

		function centerAtWithOffset(x,y,offX,offY,callback){
			var _screenP = toScreen(x,y);
			_screenP.x = _screenP.x + offX;
			_screenP.y = _screenP.y + offY;
			var _mapP = toMap(_screenP.x,_screenP.y);
			centerAt(_mapP.x,_mapP.y,callback);
		}

		function panLeft(callback){
			_mapHandler.pan(-_panFactor,0);
			if(_variableTypes.isFunc(callback)){
				setTimeout(callback,_defferTime);
			}
		}

		function panRight(callback){
			_mapHandler.pan(_panFactor,0);
			if(_variableTypes.isFunc(callback)){
				setTimeout(callback,_defferTime);
			}
		}

		function panUp(callback){
			_mapHandler.pan(0,-_panFactor);
			if(_variableTypes.isFunc(callback)){
				setTimeout(callback,_defferTime);
			}
		}

		function panDown(callback){
			_mapHandler.pan(0,_panFactor);
			if(_variableTypes.isFunc(callback)){
				setTimeout(callback,_defferTime);
			}
		}

		function setLevel(level,callback){
			_mapHandler.zoomTo(level);
			if(_variableTypes.isFunc(callback)){
				setTimeout(callback,_defferTime);
			}
		}

		function getLevel(){
			return _mapHandler.getZoom();
		}

		function getNumsOfLevel(){
			return _mapHandler.getNumZoomLevels();
		}

		function setExtent(extent){
			_mapHandler.zoomToExtent(new OpenLayers.Bounds(extent.minX,extent.minY,extent.maxX,extent.maxY));
		}
		
		function getCurrentExtent(){
			var extent = _mapHandler.getExtent();
			return {
				minX : extent.left,
				minY : extent.bottom,
				maxX : extent.right,
				maxY : extent.top
			}
		}

		function isInCurrentExtent(x,y){
			var _p = new OpenLayers.LonLat(parseFloat(x),parseFloat(y));
			return _mapHandler.getExtent().containsLonLat(_p);
		}

		function toMap(x,y){
			var p = _mapHandler.getLonLatFromViewPortPx(new OpenLayers.Pixel(x,y));
			return {
				x : p.lon,
				y : p.lat
			}
		}

		function toScreen(x,y){
			var p = _mapHandler.getPixelFromLonLat(new OpenLayers.LonLat(x,y));
			return {
				x : p.x,
				y : p.y
			}
		}

		function getAllLayersID(){
			var layeridArr = [],
				layers = _mapHandler.layers;
			for(var i=0,len=layers.length;i<len;i++){
				if(!layers[i]){
					continue;
				}
				layeridArr.push(layers[i].id);
			}
			return layeridArr;
		}

		function setLayerAddEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _evtId = MapFactory.GenerateID();
			_eventHandlers["addLayer"][_evtId] = func;
			return _evtId;
		}

		function _layerAddEvt(){
			_mapHandler.events.register("addlayer",_mapHandler,function(event){
				for(var elem in _eventHandlers["addLayer"]){
					_eventHandlers["addLayer"][elem](event.layer.id);
				}
			});
		}

		function setLayerRemoveEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _evtId = MapFactory.GenerateID();
			_eventHandlers["removeLayer"][_evtId] = func;
			return _evtId;
		}

		function _layerRemoveEvt(){
			_mapHandler.events.register("removelayer",_mapHandler,function(event){
				for(var elem in _eventHandlers["removeLayer"]){
					if(_eventHandlers["removeLayer"][elem])_eventHandlers["removeLayer"][elem](event.layer.id);
				}
			});
		}

		function reorderLayer(layerId,layerIndex){
			var layer = _mapHandler.getLayer(layerId);
			if(!layer){
				return;
			}
			_mapHandler.setLayerIndex(layer,layerIndex);
		}

		function setMouseDragEvent(obj){
			var _dragStartId,_dragMoveId,_dragEndId;

			if(_variableTypes.isFunc(obj.dragstart)){
				_dragStartId = MapFactory.GenerateID();
				_eventHandlers["dragStart"][_dragStartId] = obj.dragstart;
			}

			if(_variableTypes.isFunc(obj.dragmove)){
				_dragMoveId = MapFactory.GenerateID();
				_eventHandlers["dragMove"][_dragMoveId] = obj.dragmove;
			}

			if(_variableTypes.isFunc(obj.dragend)){
				_dragEndId = MapFactory.GenerateID();
				_eventHandlers["dragEnd"][_dragEndId] = obj.dragend;
			}

			return {
				_dragstart : _dragStartId,
				_dragmove : _dragMoveId,
				_dragend : _dragEndId
			};
		}

		function _dragEvt(){
			var _point = null;
			var _isDrag = false;
			var _isDragStart = false;
			_mapHandler.events.register("mousemove",null,function(e){
				var _p = e.object.events.getMousePosition(e);
				_point = toMap(_p.x,_p.y);
			},true);
			_mapHandler.events.register("mousedown",null,function(e){
				_isDrag = true;
				_isDragStart = true;
			},true);
			_mapHandler.events.register("mouseup",null,function(e){
				if(!_isDrag){
					return;
				}
				for(var elem in _eventHandlers["dragEnd"]){
					_eventHandlers["dragEnd"][elem]({
						mapPoint : new Geometry({
							type : GeometryType.POINT,
							points : _point.x + "," + _point.y
						})
					});
				}
				_isDrag = false;
			});
			_mapHandler.events.register("mousemove",null,function(e){
				if(!_isDrag){
					return;
				}
				if(_isDragStart){
					for(var elem in _eventHandlers["dragStart"]){
						_eventHandlers["dragStart"][elem]({
							mapPoint : new Geometry({
								type : GeometryType.POINT,
								points : _point.x + "," + _point.y
							})
						});
					}
					_isDragStart = false;
				}else{
					for(var elem in _eventHandlers["dragMove"]){
						_eventHandlers["dragMove"][elem]({
							mapPoint : new Geometry({
								type : GeometryType.POINT,
								points : _point.x + "," + _point.y
							})
						});
					}
				}
			});
		}

		function removeMouseDragEvent(){
			if(!_variableTypes.isEmptyObject(_eventHandlers["dragStart"])){
				_eventHandlers["dragStart"] = {};
			}
			if(!_variableTypes.isEmptyObject(_eventHandlers["dragMove"])){
				_eventHandlers["dragMove"] = {};
			}
			if(!_variableTypes.isEmptyObject(_eventHandlers["dragEnd"])){
				_eventHandlers["dragEnd"] = {};
			}
		}

		function setZoomEvent(obj){
			var _zoomStartId,_zoomingId,_zoomEndId;
			if(_variableTypes.isFunc(obj.zoomstart)){
				_zoomStartId = MapFactory.GenerateID();
				_eventHandlers["zoomStart"][_zoomStartId] = obj.zoomstart;
			}
			if(_variableTypes.isFunc(obj.zooming)){
				_zoomId = MapFactory.GenerateID();
				_eventHandlers["zooming"][_zoomingId] = obj.zooming;
			}
			if(_variableTypes.isFunc(obj.zoomend)){
				_zoomEndId = MapFactory.GenerateID();
				_eventHandlers["zoomEnd"][_zoomEndId] = obj.zoomend;
			}
			return {
				zoomStart : _zoomStartId,
				zooming : _zoomingId,
				zoomEnd : _zoomEndId
			};
		}

		function _zoomEvt(){
			_mapHandler.events.register("movestart",{},function(e){
				if(!e.zoomChanged){
					return;
				}
				for(var elem in _eventHandlers["zoomStart"]){
					_eventHandlers["zoomStart"][elem](getCurrentExtent());
				}
			});
			_mapHandler.events.register("move",{},function(e){
				if(!e.zoomChanged){
					return;
				}
				for(var elem in _eventHandlers["zooming"]){
					_eventHandlers["zooming"][elem](getCurrentExtent());
				}
			});
			_mapHandler.events.register("zoomend",{},function(e){
				for(var elem in _eventHandlers["zoomEnd"]){
					_eventHandlers["zoomEnd"][elem](getCurrentExtent());
				}
			});
		}

		function setMouseMoveEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _mouseMoveId = MapFactory.GenerateID();
			_eventHandlers["mouseMove"][_mouseMoveId] = func;
			return _mouseMoveId;
		}

		function _mouseMoveEvt(){
			_mapHandler.events.register("mousemove",{},function(event){
				var _screenX = event.layerX,
					_screenY = event.layerY,
					_mapPoint = toMap(_screenX,_screenY);
				var _screenP = new Geometry({
					type : GeometryType.POINT,
					points : _screenX + "," + _screenY
				});
				var _mapP =  new Geometry({
					type : GeometryType.POINT,
					points : _mapPoint.x + "," + _mapPoint.y
				});
				for(var elem in _eventHandlers["mouseMove"]){
					_eventHandlers["mouseMove"][elem]({
						screenPoint : _screenP,
						mapPoint : _mapP
					});
				}
			});
		}

		function removeMouseMoveEvent(){
			if(_variableTypes.isEmptyObject(_eventHandlers["mouseMove"])){
				return;
			}
			_eventHandlers["mouseMove"] = {};
		}

		function setMouseDownEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _mouseDownId = MapFactory.GenerateID();
			_eventHandlers["mouseDown"][_mouseDownId] = func;
			return _mouseDownId;
		}

		function _mouseDownEvt(){
			_mapHandler.events.register("mousedown",{},function(event){
				var _screenX = event.layerX,
					_screenY = event.layerY,
					_mapPoint = toMap(_screenX,_screenY);
				var _screenP = new Geometry({
					type : GeometryType.POINT,
					points : _screenX + "," + _screenY
				});
				var _mapP =  new Geometry({
					type : GeometryType.POINT,
					points : _mapPoint.x + "," + _mapPoint.y
				});
				for(var elem in _eventHandlers["mouseDown"]){
					_eventHandlers["mouseDown"][elem]({
						which : event.which,
						button : event.button,
						screenPoint : _screenP,
						mapPoint : _mapP
					});
				}
			},true);
		}

		function removeMouseDownEvent(){
			if(_variableTypes.isEmptyObject(_eventHandlers["mouseDown"])){
				return;
			}
			_eventHandlers["mouseDown"] = {};
		}

		function setMouseUpEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _mouseUpId = MapFactory.GenerateID();
			_eventHandlers["mouseUp"][_mouseUpId] = func;
			return _mouseUpId;
		}

		function _mouseUpEvt(){
			_mapHandler.events.register("mouseup",{},function(event){
				var _screenX = event.layerX,
					_screenY = event.layerY,
					_mapPoint = toMap(_screenX,_screenY);
				var _screenP = new Geometry({
					type : GeometryType.POINT,
					points : _screenX + "," + _screenY
				});
				var _mapP =  new Geometry({
					type : GeometryType.POINT,
					points : _mapPoint.x + "," + _mapPoint.y
				});
				for(var elem in _eventHandlers["mouseUp"]){
					_eventHandlers["mouseUp"][elem]({
						which : event.which,
						button : event.button,
						screenPoint : _screenP,
						mapPoint : _mapP
					});
				}
			});
		}

		function removeMouseUpEvent(){
			if(_variableTypes.isEmptyObject(_eventHandlers["mouseUp"])){
				return;
			}
			_eventHandlers["mouseUp"] = {};
		}

		function setMouseClickEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _mouseClickId = MapFactory.GenerateID();
			_eventHandlers["mouseClick"][_mouseClickId] = func;
			return _mouseClickId;
		}

		function _mouseClickEvt(){
			_mapHandler.events.register("click",{},function(event){
				var _screenX = event.layerX,
					_screenY = event.layerY,
					_mapPoint = toMap(_screenX,_screenY);
				for(var elem in _eventHandlers["mouseClick"]){
					_eventHandlers["mouseClick"][elem]({
						screenPoint : new Geometry({
							type : GeometryType.POINT,
							points : _screenX + "," + _screenY
						}),
						mapPoint : new Geometry({
							type : GeometryType.POINT,
							points : _mapPoint.x + "," + _mapPoint.y
						})
					});
				}
			});
		}

		function removeMouseClickEvent(){
			if(!_variableTypes.isEmptyObject(_eventHandlers["mouseClick"])){
				_eventHandlers["mouseClick"] = {};
			}
		}

		function setMouseDoubleClickEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _mouseDbClickId = MapFactory.GenerateID();
			_eventHandlers["mouseDbClick"][_mouseDbClickId] = func;
			return _mouseDbClickId;
		}

		function _dbClickEvt(){
			OpenLayers.Control.Click = OpenLayers.Class(OpenLayers.Control, {
                defaultHandlerOptions: {
                    'single': false,
                    'double': true,
                    'pixelTolerance': 0,
                    'stopSingle': false,
                    'stopDouble': false
                },

                initialize: function(options) {
                    this.handlerOptions = OpenLayers.Util.extend(
                        {}, this.defaultHandlerOptions
                    );
                    OpenLayers.Control.prototype.initialize.apply(
                        this, arguments
                    ); 
                    this.handler = new OpenLayers.Handler.Click(
                        this, {
                            'dblclick': this.onDblclick
                        }, this.handlerOptions
                    );
                },

                onDblclick: function(evt) {
                	var _screenX = evt.layerX,
						_screenY = evt.layerY,
						_mapPoint = toMap(_screenX,_screenY);
                	for(var elem in _eventHandlers["mouseDbClick"]){
                		_eventHandlers["mouseDbClick"][elem]({
    						screenPoint : new Geometry({
    							type : GeometryType.POINT,
    							points : _screenX + "," + _screenY
    						}),
    						mapPoint : new Geometry({
    							type : GeometryType.POINT,
    							points : _mapPoint.x + "," + _mapPoint.y
    						})
    					});
                	}
                }
            });
			var clickControl = new OpenLayers.Control.Click();
			_mapHandler.addControl(clickControl);
			clickControl.activate();
		}

		function removeMouseDoubleClickEvent(){
			if(_variableTypes.isEmptyObject(_eventHandlers["mouseDbClick"])){
				return;
			}
			_eventHandlers["mouseDbClick"] = {};
		}

		function setExtentChangeEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _extentChangeId = MapFactory.GenerateID();
			_eventHandlers["extentChange"][_extentChangeId] = func;
			return _extentChangeId;
		}

		function _extentChangeEvt(){
			_mapHandler.events.register("moveend",{},function(event){
				for(var elem in _eventHandlers["extentChange"]){
					_eventHandlers["extentChange"][elem](getCurrentExtent());
				}
			});
		}

		function removeExtentChangeEvent(){
			if(_variableTypes.isEmptyObject(_eventHandlers["extentChange"])){
				return;
			}
			_eventHandlers["extentChange"] = {};
		}

		function setMouseWheelEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _mouseWheelId = MapFactory.GenerateID();
			_eventHandlers["mouseWheel"][_mouseWheelId] = func;
			return _mouseWheelId;
		}

		function _mouseWheelEvt(){
			_events.onMouseWheel(document.getElementById(_conf.src),function(e){
				var _directionFlag = e.wheelDelta > 0 ? "in" : "out";
				for(var elem in _eventHandlers["mouseWheel"]){
					_eventHandlers["mouseWheel"][elem]({
						wheelDirection : _directionFlag
					});
				}
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
			_mapHandler.updateSize();
		}

		function getMapConfig(){
			return _conf.mapConfig;
		}

		function disablePan(){
			if(_controls["Navigation"]){
				_controls["Navigation"].deactivate();
			}
			var _cs = _mapHandler.controls;
			for(var i=0,len=_cs.length;i<len;i++){
				var _c = _cs[i];
				if(_c && _c.active && _c.displayClass == "olControlDragPan"){
					_c.deactivate();
				}
			}
		}

		function enablePan(){
			var _cs = _mapHandler.controls;
			for(var i=0,len=_cs.length;i<len;i++){
				var _c = _cs[i];
				if(_c && !_c.active && _c.displayClass == "olControlDragPan"){
					_c.activate();
				}
			}
			if(_controls["Navigation"]){
				_controls["Navigation"].activate();
			}
		}

		function isLayerExist(layerid){
			return !!_mapHandler.getLayer(layerid);
		}

		function zoomIn(){
			_mapHandler.zoomIn();
		}

		function zoomOut(){
			_mapHandler.zoomOut();
		}

		function getMapSize(){
			return {
				width : _mapHandler.size.w,
				height : _mapHandler.size.h
			};
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});