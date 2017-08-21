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
var proj4326 = new OpenLayers.Projection("EPSG:4326");
var proj900913 = new OpenLayers.Projection("EPSG:900913");
function transformTo900913(x, y) {
	x -= offsetX;
	y -= offsetY;
	var t = new OpenLayers.LonLat(x, y);
	if(x < 180 && y < 90) {
		t = t.transform(proj4326, proj900913);
	}
	return t;
}
function transformTo4326(x, y) {
	var t = new OpenLayers.LonLat(x, y);
	if(x > 180 && y > 90) {
		t = t.transform(proj900913, proj4326);
		t = new OpenLayers.LonLat(t.lon + offsetX, t.lat + offsetY);
	}
	return t;
}
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
	var zoomOffset = 0;
	var resolutions = [156543.03390625, 78271.516953125, 39135.7584765625, 19567.87923828125, 9783.939619140625, 4891.9698095703125, 2445.9849047851562, 1222.9924523925781, 611.4962261962891, 305.74811309814453, 152.87405654907226, 76.43702827453613, 38.218514137268066, 19.109257068634033, 9.554628534317017, 4.777314267158508, 2.388657133579254, 1.194328566789627, 0.5971642833948135, 0.29858214169740677];

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

		// 计算zoom偏移量
		for(var i=0; i<20; i++) {
			if(_mapConfig.resolutions[0] == resolutions[i]) {
				zoomOffset = i;
				break;
			}
		}
		_mapHandler = MapFactory._MapManagerResource[MapFactory.Engine];
		if(!_mapHandler){
			var spatialReference = "EPSG:" + _mapConfig.spatialReference;
			var tileOption = {};
			tileOption[spatialReference] = true;

			_controls["Navigation"] = new OpenLayers.Control.Navigation();
			
			var point = transformTo900913(initExtent.minX, initExtent.minY);
			initExtent.minX = point.lon;
			initExtent.minY = point.lat;
			var point2 = transformTo900913(initExtent.maxX, initExtent.maxY);
			initExtent.maxX = point2.lon;
			initExtent.maxY = point2.lat;
			var maxExtent = new OpenLayers.Bounds(initExtent.minX,initExtent.minY,initExtent.maxX,initExtent.maxY);

			MapFactory._MapManagerResource[MapFactory.Engine] = _mapHandler = new OpenLayers.Map(_conf.src,{
				//maxExtent : new OpenLayers.Bounds(initExtent.minX,initExtent.minY,initExtent.maxX,initExtent.maxY),
				resolutions : _mapConfig.resolutions,
				projection : "EPSG:900913",// 该坐标系为地图坐标系，比如new一个点的时候会采用该坐标系
				displayProjection: new OpenLayers.Projection("EPSG:4326"),//该坐标系为显示的坐标系，右下角默认显示的经纬度
				//units : "degrees",// 需要屏蔽，要不然测量结果不准确，应该会按照经纬度测量距离
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

			// 如果单独使用本地缓存图片，可以使用XYZ图层加载方式展示，优点是速度快
			/*var baseLayer = new OpenLayers.Layer.XYZ(
				"basemap",[_mapConfig.layers.baseMap.url],
				{
					getXYZ: function(bounds) {
						var z = this.getServerZoom();
						z += zoomOffset;// 给zoom加10
						var res = this.getServerResolution();
						var x = Math.round((bounds.left + 20037508.34) / (res * this.tileSize.w));
						var y = Math.round((20037508.34 - bounds.top) / (res * this.tileSize.h));

						//////////////腾讯地图添加部分/////////////////
						//var scope =new Array(0, 0, 0, 0, 0, 3, 0, 3, 0, 3, 0, 3, 0, 7, 0, 7, 0, 15, 0, 15, 0, 31, 0, 31, 0, 63, 4, 59, 0, 127, 12, 115, 0, 225, 28, 227, 356, 455, 150, 259, 720, 899, 320, 469, 1440, 1799, 650, 929, 2880, 3589, 1200, 2069, 5760, 7179, 2550, 3709, 11520, 14349, 5100, 7999, 23060, 28689, 10710, 15429, 46120, 57369, 20290, 29849, 89990, 124729, 41430, 60689, 184228, 229827, 84169, 128886);
						//var f= z*4;
						//var i = scope[f++];
						//var j = scope[f++];
						//var l = scope[f++];
						//var scope = scope[f];
						//if (x >= i && x <= j && y >= l && y <= scope) {
						//	y = Math.pow(2, z) - 1 - y;
						//}

						return {'x': x, 'y': y, 'z': z};
					}
				}
			);*/
			// 使用WMS图层加载底图，可以动态浏览下载瓦片进行缓存
			var baseLayer = new OpenLayers.Layer.WMS(
				"basemap", "forward/map/title/",{},
				{
					getURL: function (bounds) {
				        bounds = this.adjustBounds(bounds);
				        
				        var imageSize = this.getImageSize();
				        var newParams = {};
				        // WMS 1.3 introduced axis order
				        var reverseAxisOrder = this.reverseAxisOrder();
				        newParams.BBOX = this.encodeBBOX ?
				            bounds.toBBOX(null, reverseAxisOrder) :
				            bounds.toArray(reverseAxisOrder);
				        newParams.WIDTH = imageSize.w;
				        newParams.HEIGHT = imageSize.h;
				        newParams.ZOOM = this.getServerZoom()+zoomOffset;
				        var requestString = this.getFullRequestString(newParams);
				        return requestString;
				    }
				}
			);
			
			baseLayer.id = _mapConfig.layers.baseMap.id;
			_mapHandler.addLayer(baseLayer);
			var cp = maxExtent.getCenterLonLat();
			//_mapHandler.setCenter(cp);
			_mapHandler.zoomToExtent(maxExtent);

			for(var elem in _mapConfig.referenceCSS){
				MapFactory.CreateCssNode(MapFactory.FramePath + _mapConfig.referenceCSS[elem]);
			}
			document.getElementById(_conf.src).oncontextmenu = function(){return false;};

			//_mapHandler.addControl(new OpenLayers.Control.Scale());//添加地图比例尺

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
			_mapHandler.setCenter(transformTo900913(x, y), level);
			if(_variableTypes.isFunc(callback)){
				setTimeout(callback,_defferTime);
			}
		}

		function centerAt(x,y,callback){
			_mapHandler.setCenter(transformTo900913(x, y));
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
			var point = transformTo900913(extent.minX, extent.minY);
			extent.minX = point.lon;
			extent.minY = point.lat;
			var point2 = transformTo900913(extent.maxX, extent.maxY);
			extent.maxX = point2.lon;
			extent.maxY = point2.lat;
			_mapHandler.zoomToExtent(new OpenLayers.Bounds(extent.minX,extent.minY,extent.maxX,extent.maxY));
		}
		
		function getCurrentExtent(){
			var extent = _mapHandler.getExtent();
			if(!extent)return null;
			var lb = transformTo4326(extent.left, extent.bottom);
			var rt = transformTo4326(extent.right, extent.top);
			return {
				minX : lb.lon,
				minY : lb.lat,
				maxX : rt.lon,
				maxY : rt.lat
			}
		}

		function isInCurrentExtent(x,y){
			var _p = transformTo900913(x, y);
			return _mapHandler.getExtent().containsLonLat(_p);
		}

		function toMap(x,y){
			var p = _mapHandler.getLonLatFromViewPortPx(new OpenLayers.Pixel(x,y));
			var p = transformTo4326(p.lon, p.lat);
			return {
				x : p.lon,
				y : p.lat
			}
		}

		function toScreen(x,y){
			var p = _mapHandler.getPixelFromLonLat(transformTo900913(x, y));
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