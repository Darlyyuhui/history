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
		};
	return function(conf){
		MapFactory.Extend(_conf,conf);
		var _mapHandler = null,
			_baseLayer = null,
            _imageLayer = null,
            _appLayer=null,
            _appLayerArr=null,
			_baseLayerLabels = null,
			_mapConfig = _conf.mapConfig,
			initExtent = _mapConfig.initExtent,
            maxExtent = _mapConfig.maxExtent,
			_spatialReference = "EPSG:" + _mapConfig.spatialReference,
			_defferTime = 500,
			_panFactor = 50,
			_variableTypes = MapFactory.VariableTypes,
			_events = MapFactory.Events;
		   _mapHandler = MapFactory._MapManagerResource[MapFactory.Engine];
		if(!_mapHandler){
			var _nav = new SuperMap.Control.Navigation({
				zoomWheelEnabled : false
			});
			MapFactory._MapManagerResource[MapFactory.Engine] = _mapHandler =  new SuperMap.Map(_conf.src,{
				projection: _spatialReference,
                resolutions : _mapConfig.resolutions,
				theme : null,
				controls : [_nav]
			});
			
			_baseLayer = new SuperMap.Layer.TiledDynamicRESTLayer(_mapConfig.layers.baseMap.id,_mapConfig.layers.baseMap.url, {transparent: true});
			_baseLayer.id = _mapConfig.layers.baseMap.id;
			_baseLayer.events.on({"layerInitialized": _addLayer});

			for(var elem in _mapConfig.referenceCSS){
				MapFactory.CreateCssNode(MapFactory.FramePath + _mapConfig.referenceCSS[elem]);
			}
			document.getElementById(_conf.src).oncontextmenu = function(){return false;};
			if(_conf.src){document.getElementById(_conf.src).oncontextmenu = function(){return false;};}
		}
		
		//获取需要初始化的图层
		function loadNeedInitLayerConfig(){
			_appLayerArr=[];
			for(var elem in _mapConfig.layers){
				var lyr=_mapConfig.layers[elem];
				var isBaseMap=lyr.hasOwnProperty("isBaseMap") ? lyr.isBaseMap : false;
				var isInit=lyr.hasOwnProperty("isInit") ? lyr.isInit : false;
				if(lyr.hasOwnProperty("url") && !isBaseMap && isInit){
					_appLayerArr.push(lyr);
				}
			}
			loadAppServiceLayer();
		}
		//添加服务应用图层
		function loadAppServiceLayer(){
			if(!_appLayerArr || _appLayerArr.length==0){
				return;
			}
			var layer=_appLayerArr.shift();
			var visibility=layer.hasOwnProperty("visibility") ? layer.visibility : false;
			_appLayer = new SuperMap.Layer.TiledDynamicRESTLayer(layer.id,layer.url, {transparent: true},{visibility: visibility});
			_appLayer.id = layer.id;
			_appLayer.events.on({"layerInitialized": initAddServiceLayer});
		}
		//初始化完成加入地图
		function initAddServiceLayer(){
			_mapHandler.addLayer(_appLayer);
			loadAppServiceLayer();
		}
       
		//暂时没用
		function setCurrentMap(mapDiv) {
			if(mapDiv ==_conf.src) {
				MapFactory._MapManagerResource[MapFactory.Engine] = _mapHandler = MapFactory._MapManagerResource["map1"];
			}
			else {
				MapFactory._MapManagerResource[MapFactory.Engine] = _mapHandler = MapFactory._MapManagerResource["map2"];
			}
		}

		function _addLayer(){
			_mapHandler.addLayer(_baseLayer);
            _mapHandler.setBaseLayer(_baseLayer);
			_mapHandler.allOverlays = true;
            _imageLayer = new SuperMap.Layer.TiledDynamicRESTLayer(_mapConfig.layers.imageMap.id,_mapConfig.layers.imageMap.url, {transparent: true},{visibility: false});
            _imageLayer.id = _mapConfig.layers.imageMap.id;
            _imageLayer.events.on({"layerInitialized": _addLabelsLayer});
			
			if(_conf.loaded){
				return;
			}else{
				_conf.loaded();	
			}
		}
		
		function _addLabelsLayer(){
            _mapHandler.addLayer(_imageLayer);
			
			if(_mapConfig.initExtent){
				setExtent(_mapConfig.initExtent);
			}
			
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
					if(document.getElementById(_conf.src)){document.getElementById(_conf.src).oncontextmenu = function(){return false;};}
					_conf.loaded();
			}
			
			loadNeedInitLayerConfig();
		}

        function setBaseMapLayer(layer) {
            if(layer.id == _mapConfig.layers.baseMap.id){
                _mapHandler.setBaseLayer(_baseLayer);
                _imageLayer.setVisibility(false);
                _baseLayer.setVisibility(true);
            }
            else{
                _mapHandler.setBaseLayer(_imageLayer);
                _imageLayer.setVisibility(true);
                _baseLayer.setVisibility(false);
            }
        }

		function centerAndZoom(x,y,level,callback){
			_mapHandler.setCenter(new SuperMap.LonLat(x,y),level);
			if(_variableTypes.isFunc(callback)){
				setTimeout(callback,_defferTime);
			}
		}

		function centerAt(x,y,callback){
			_mapHandler.setCenter(new SuperMap.LonLat(x,y));
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
			_mapHandler.zoomToExtent(new SuperMap.Bounds(extent.minX,extent.minY,extent.maxX,extent.maxY));
		}

		function getCurrentExtent(){
			var extent = _mapHandler.getExtent();
			if(!extent){
				return null;
			}
			return {
				minX : extent.left,
				minY : extent.bottom,
				maxX : extent.right,
				maxY : extent.top
			}
		}
		
		
		function isInCurrentExtent(x,y){
			var _p = new SuperMap.LonLat(parseFloat(x),parseFloat(y));
			return _mapHandler.getExtent().containsLonLat(_p);
		}

		function toMap(x,y){
			var p = _mapHandler.getLonLatFromViewPortPx(new SuperMap.Pixel(x,y));
			if(!p){
				return null;
			}
			return {
				x : p.lon,
				y : p.lat
			}
		}

		function toScreen(x,y){
			var p = _mapHandler.getPixelFromLonLat(new SuperMap.LonLat(x,y));
			if(!p){
				return null;
			}
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
			var _layer = _mapHandler.getLayer(layerId);
			if(!_layer){
				return;
			}
			_mapHandler.setLayerIndex(_layer,layerIndex);
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
            activeDBclick();
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _mouseDbClickId = MapFactory.GenerateID();
			_eventHandlers["mouseDbClick"][_mouseDbClickId] = func;
			return _mouseDbClickId;
		}

		function _dbClickEvt(){
			SuperMap.Control.Click = SuperMap.Class(SuperMap.Control, {
                defaultHandlerOptions: {
                    'single': false,
                    'double': true,
                    'pixelTolerance': 0,
                    'stopSingle': false,
                    'stopDouble': false
                },

                initialize: function(options) {
                    this.handlerOptions = SuperMap.Util.extend(
                        {}, this.defaultHandlerOptions
                    );
                    SuperMap.Control.prototype.initialize.apply(
                        this, arguments
                    ); 
                    this.handler = new SuperMap.Handler.Click(
                        this, {
                            'dblclick': this.onDblclick
                        }, this.handlerOptions
                    );
                },

                onDblclick: function(evt) {
                    evt.preventDefault();
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
			clickControl = new SuperMap.Control.Click();
			_mapHandler.addControl(clickControl);
			//clickControl.activate();
		}
        var clickControl;

        function activeDBclick() {
            //clickControl.activate();
        }

        function deactivateDBclick() {
            clickControl.deactivate();
        }

		function removeMouseDoubleClickEvent(){
			if(_variableTypes.isEmptyObject(_eventHandlers["mouseDbClick"])){
				return;
			}
			_eventHandlers["mouseDbClick"] = {};
            deactivateDBclick();
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

        function getMaxExtent(){
            return {
                minX : maxExtent.minX,
                minY : maxExtent.minY,
                maxX : maxExtent.maxX,
                maxY : maxExtent.maxY
            }
        }

		function resize(){
			_mapHandler.updateSize();
		}

		function getMapConfig(){
			return _conf.mapConfig;
		}

		function disablePan(){
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