MapFactory.Define("MapFactory/LayerManager",[
	"MapFactory/LayerManagerAPI*",
	"MapFactory/MapManager",
	"esri/layers/GraphicsLayer*",
	"MapFactory/GeometryUtil",
	"MapFactory/SymbolUtil",
	"MapFactory/Graphic*"
],function(api,mapMGM,graphicLayer,geoUtil,SymbolUtil,Graphic){
	var _eventHandler = {};
	var _eventHandlers = {
		"mouseClick" : {},
		"visibleChanged" : {},
		"mouseDown" : {},
		"mouseOver" : {},
		"mouseOut" : {}
	};
	return function(id,conf){
		var _layerid = "",
			_layerHandler = null,
			_mapHandler = MapFactory._MapManagerResource[MapFactory.Engine],
			_conf = {
				url : "",
				minScale : "",
				maxScale : "",
				isVisible : ""
			},
			_geoUtil = geoUtil(),
			_symbolUtil = SymbolUtil(),
			_variableTypes = MapFactory.VariableTypes;

		MapFactory.Extend(_conf,conf);

		_layerid = id;

		if(_layerid){
			_layerHandler = _mapHandler.getLayer(_layerid);
		}

		if(!_layerHandler){
			_layerHandler = new graphicLayer();
			if(_layerid){
				_layerHandler.id = _layerid;
			}
			if(_conf.minScale){
				_layerHandler.minScale = _conf.minScale;
			}
			if(_conf.maxScale){
				_layerHandler.maxScale = _conf.maxScale;
			}
			if(MapFactory.VariableTypes.isBoolean(_conf.isVisible)){
				_layerHandler.visible = _conf.isVisible;
			}
			_mapHandler.addLayer(_layerHandler);

			_clickEvt();
			_visibleChanageEvt();
			_mouseDownEvt();
			_mouseOverEvt();
			_mouseOutEvt();
		}

		function getId(){
			return _layerHandler.id;
		}

		function getDomId(){
			return _layerid + "_layer";
		}

		function show(){
			_layerHandler.show();
		}

		function hide(){
			_layerHandler.hide();
		}

		function clear(){
			var gArr = _layerHandler.graphics;
			for(var elem in gArr){
				delete MapFactory._MapGraphicResource[gArr[elem].id];
			}
			delete MapFactory._MapLayerGraphicResource[_layerHandler.id];
			_layerHandler.clear();
		}

		function isVisible(){
			return _layerHandler.visible;
		}

		function removeFromMap(){
			_mapHandler.removeLayer(_layerHandler);
		}

		function _clickEvt(){
			dojo.connect(_layerHandler,"onClick",function(event){
				if(_variableTypes.isEmptyObject(_eventHandlers["mouseClick"])){
					return;
				}
				var _graphic = event.graphic;
				var _geometry = _geoUtil.convertFromObject(_graphic.geometry);
				var _mapPoint = _geoUtil.convertFromObject(event.mapPoint);
				for(var elem in _eventHandlers["mouseClick"]){
					if(_eventHandlers["mouseClick"][elem]["layerid"] != _layerid){
						continue;
					}
					_eventHandlers["mouseClick"][elem]["func"]({
						layerid : _layerid,
						graphic : {
							id : _graphic.id,
							geo : _geometry,
							attributes : _graphic.attributes
						},
						mapPoint : _mapPoint
					});
				}
			});
		}

		function addOnClickEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _evtId = MapFactory.GenerateID();
			if(!_eventHandlers["mouseClick"][_evtId]){
				_eventHandlers["mouseClick"][_evtId] = {};
			}
			_eventHandlers["mouseClick"][_evtId]["func"] = func;
			_eventHandlers["mouseClick"][_evtId]["layerid"] = _layerid;
			return _evtId;
		}

		function _visibleChanageEvt(){
			dojo.connect(_layerHandler,"onVisibilityChange",function(visibleFlag){
				if(_variableTypes.isEmptyObject(_eventHandlers["visibleChanged"])){
					return;
				}
				for(var elem in _eventHandlers["visibleChanged"]){
					if(_eventHandlers["visibleChanged"][elem]["layerid"] != _layerid){
						continue;
					}
					_eventHandlers["visibleChanged"][elem]["func"](visibleFlag);
				}
			});
		}

		function addVisibleChangeEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _evtId = MapFactory.GenerateID();
			if(!_eventHandlers["visibleChanged"][_evtId]){
				_eventHandlers["visibleChanged"][_evtId] = {};
			}
			_eventHandlers["visibleChanged"][_evtId]["func"] = func;
			_eventHandlers["visibleChanged"][_evtId]["layerid"] = _layerid;
			return _evtId;
		}

		function _mouseDownEvt(){
			dojo.connect(_layerHandler,"onMouseDown",function(evt){
				if(_variableTypes.isEmptyObject(_eventHandlers["mouseDown"])){
					return;
				}
				var oldGraphic = evt.graphic,
					graphic = new Graphic();
				graphic.id = oldGraphic.id;
				graphic.geo = _geoUtil.convertFromObject(oldGraphic.geometry);
				graphic.symbol = _symbolUtil.convertFromObject(graphic.geo.type,oldGraphic.symbol);
				graphic.attributes = oldGraphic.attributes;
				for(var elem in _eventHandlers["mouseDown"]){
					if(_eventHandlers["mouseDown"][elem]["layerid"] != _layerid){
						continue;
					}
					_eventHandlers["mouseDown"][elem]["func"]({
						button : evt.button,
						which : evt.which,
						graphic : graphic,
						screenX : evt.screenX,
						screenY : evt.screenY,
						offsetX : evt.offsetX,
						offsetY : evt.offsetY,
						clientX : evt.clientX,
						clientY : evt.clientY
					});
				}
			});
		}

		function addMouseDownEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _evtId = MapFactory.GenerateID();
			if(!_eventHandlers["mouseDown"][_evtId]){
				_eventHandlers["mouseDown"][_evtId] = {};
			}
			_eventHandlers["mouseDown"][_evtId]["func"] = func;
			_eventHandlers["mouseDown"][_evtId]["layerid"] = _layerid;
			return _evtId;
		}

		function _mouseOverEvt(){
			dojo.connect(_layerHandler,"onMouseOver",function(evt){
				if(_variableTypes.isEmptyObject(_eventHandlers["mouseOver"])){
					return;
				}
				if(evt.graphic.getLayer().id != _layerid){
					return;
				}
				var oldGraphic = evt.graphic,
					graphic = new Graphic();
				
				graphic.id = oldGraphic.id;
				graphic.geo = _geoUtil.convertFromObject(oldGraphic.geometry);
				graphic.symbol = _symbolUtil.convertFromObject(graphic.geo.type,oldGraphic.symbol);
				graphic.attributes = oldGraphic.attributes;
				for(var elem in _eventHandlers["mouseOver"]){
					if(_eventHandlers["mouseOver"][elem]["layerid"] != _layerid){
						continue;
					}
					_eventHandlers["mouseOver"][elem]["func"]({
						which : evt.which,
						graphic : graphic,
						screenX : evt.screenX,
						screenY : evt.screenY,
						offsetX : evt.offsetX,
						offsetY : evt.offsetY,
						clientX : evt.clientX,
						clientY : evt.clientY
					});
				}
			});
		}

		function addMouseOverEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _evtId = MapFactory.GenerateID();
			if(!_eventHandlers["mouseOver"][_evtId]){
				_eventHandlers["mouseOver"][_evtId] = {};
			}
			_eventHandlers["mouseOver"][_evtId]["func"] = func;
			_eventHandlers["mouseOver"][_evtId]["layerid"] = _layerid;
			return _evtId;
		}
		
		function _mouseOutEvt(){
			dojo.connect(_layerHandler,"onMouseOut",function(evt){
				if(_variableTypes.isEmptyObject(_eventHandlers["mouseOut"])){
					return;
				}
				for(var elem in _eventHandlers["mouseOut"]){
					if(_eventHandlers["mouseOut"][elem]["layerid"] != _layerid){
						continue;
					}
					_eventHandlers["mouseOut"][elem]["func"]({
						screenX : evt.screenX,
						screenY : evt.screenY,
						offsetX : evt.offsetX,
						offsetY : evt.offsetY,
						clientX : evt.clientX,
						clientY : evt.clientY
					});
				}
			});
		}

		function addMouseOutEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _evtId = MapFactory.GenerateID();
			if(!_eventHandlers["mouseOut"][_evtId]){
				_eventHandlers["mouseOut"][_evtId] = {};
			}
			_eventHandlers["mouseOut"][_evtId]["func"] = func;
			_eventHandlers["mouseOut"][_evtId]["layerid"] = _layerid;
			return _evtId;
		}
		
		function removeEvent(evtId){
			if(!_variableTypes.isString(evtId)){
				return;
			}
			for(var elem in _eventHandlers){
				if(_eventHandlers[elem][evtId]){
					delete _eventHandlers[elem][evtId];
					break;
				}
			}
		}

		function getFeatures(){
			var _features = _layerHandler.graphics,
				_newFeatures = [],
				_symbolUtil = SymbolUtil();
			if(!_features){
				return _newFeatures;
			}
			for(var i=0,len=_features.length;i<len;i++){
				var geo = _geoUtil.convertFromObject(_features[i].geometry);
				_newFeatures.push({
					id : _features[i].id,
					geo : geo,
					attributes : _features[i].attributes,
					symbol : _symbolUtil.convertFromObject(geo.type, _features[i].symbol)
				});
			}
			return _newFeatures;
		}

		function filterByAttr(condition,isremove){
			var _features = _layerHandler.graphics,
				i,len,elem,featureIndex,
				_featuresId = [];
			for(i=0,len=_features.length;i<len;i++){
				_featuresId.push(_features[i].id);
			}
			for(i=0,len=_featuresId.length;i<len;i++){
				var _feature = MapFactory._MapGraphicResource[_featuresId[i]],
					_attributes = null;
				if(!_feature){
					continue;
				}
				_attributes = _feature.attributes;
				if(!_attributes && isremove){
					if(!isremove){
						_layerHandler.remove(_feature);
						delete MapFactory._MapGraphicResource[_featuresId[i]];
					}
					continue;
				}
				for(elem in condition){
					if(typeof _attributes[elem] == "undefined"){
						if(!isremove){
							_layerHandler.remove(_feature);
							delete MapFactory._MapGraphicResource[_featuresId[i]];
						}
						continue;
					}else{
						if(isremove){
							if(_attributes[elem] == condition[elem]){
								_layerHandler.remove(_feature);
								delete MapFactory._MapGraphicResource[_featuresId[i]];
							}
						}else{
							if(_attributes[elem] != condition[elem]){
								_layerHandler.remove(_feature);
								delete MapFactory._MapGraphicResource[_featuresId[i]];
							}
						}
					}
				}
			}
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});