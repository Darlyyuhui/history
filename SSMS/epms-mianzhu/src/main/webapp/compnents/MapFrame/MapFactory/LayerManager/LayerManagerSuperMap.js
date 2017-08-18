MapFactory.Define("MapFactory/LayerManager",[
	"MapFactory/LayerManagerAPI*",
	"MapFactory/GeometryUtil",
	"MapFactory/MapManager",
	"MapFactory/Geometry*",
	"MapFactory/GeometryType*",
	"MapFactory/SymbolUtil",
	"MapFactory/Graphic*"
],function(api,GeometryUtil,MapManager,Geometry,GeometryType,SymbolUtil,Graphic){
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
				minScale : "",
				maxScale : "",
				isVisible : "",
				clusterStyles: [],
				layerType : "Vector",
				maxLevel:6,  //超过该级后，不再进行聚合，而是直接绘制
				isDiffused:false, //当点击聚合点时，是否允许聚合点可以散开
				tolerance:40 ,//聚合的范围，该像素范围内的小点会被聚合成一个大点
				maxDiffuseAmount:10//聚合点最多能散开小点的数目，当点击聚合点的时候，聚合数目小于等于该值的聚合点可以散开,大于该值的聚合点不会散开。
			},
			_geoUtil = GeometryUtil(),
			_symbolUtil = SymbolUtil(),
			_variableTypes = MapFactory.VariableTypes,
			_isMouseOvered = false, // 用来判断鼠标是否悬停
			_hasMouseOverFeature = false,
			_isMouseOverCurrentLayer = false,
			_layerType="Vector";

		MapFactory.Extend(_conf,conf);
		_layerid = id;
		_layerType=_conf.layerType;
	   //_layerType="Vector";
		if(_layerid){
			_layerHandler = _mapHandler.getLayer(_layerid);
		}

		if(!_layerHandler){
			
			if(_layerType=="Vector"){
				_layerHandler = new SuperMap.Layer.Vector();
				//_layerHandler=new SuperMap.Feature.Vector();
			}else if(_layerType=="ClusterLayer"){
				_layerHandler = new SuperMap.Layer.ClusterLayer();
				//_layerHandler=new SuperMap.Feature.Vector();
			}
			
			if(_layerid){
				_layerHandler.id = _layerid;
			}
			if(_conf.minScale){
				_layerHandler.minScale = _conf.minScale;
			}
			if(_conf.maxScale){
				_layerHandler.maxScale = _conf.maxScale;
			}
			if(_variableTypes.isBoolean(_conf.isVisible)){
				_layerHandler.visibility = _conf.isVisible;
			}
			
			if(_layerType=="ClusterLayer"){
				_layerHandler.maxLevel=_conf.maxLevel;
				_layerHandler.isDiffused=_conf.isDiffused;
				_layerHandler.tolerance=_conf.tolerance;
				_layerHandler.maxDiffuseAmount=_conf.maxDiffuseAmount;
				_layerHandler.clusterStyles=_conf.clusterStyles;
			}
			_mapHandler.addLayer(_layerHandler);

			_clickEvt();
			_mouseDownEvt();
			_visibleChangeEvt();
			_mouseOverEvt();
			_mouseOutEvt();
		}

		function getId(){
			return _layerHandler.id;
		}

		function getDomId(){
			return _layerHandler.div.id;
		}

		function show(){
			_layerHandler.setVisibility(true);
		}

		function hide(){
			_layerHandler.setVisibility(false);
		}

		function clear(){
			if(_layerType=="Vector"){
				var gArr = _layerHandler.features;
				for(var elem in gArr){
					delete MapFactory._MapGraphicResource[gArr[elem].id];
				}
				delete MapFactory._MapLayerGraphicResource[_layerHandler.id];
				_layerHandler.removeAllFeatures();
			}else if(_layerType=="ClusterLayer"){
				_layerHandler.destroyCluster();
				recalculateDraw();
			}
		}
		//聚合图层由于清除后  要重新进行聚合计算并绘制
		function recalculateDraw(){
			_layerHandler.refresh();
		}

		function isVisible(){
			return _layerHandler.visibility;
		}

		function removeFromMap(){
			_mapHandler.removeLayer(_layerHandler);
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

		function _getTargetFeature(x,y){
			var i,layer,target = null,layers = [],feature = null;
			for(i=_mapHandler.layers.length-1;i>=0;i--){
				layer = _mapHandler.layers[i];
				if(layer.div.style.display == "none"){
					continue;
				}
				target = document.elementFromPoint(x,y);
				if(target && target._featureId){
					feature = layer.getFeatureById(target._featureId);
					break;
				}else{
					layers.push(layer);
					layer.display(false);
				}
			}
			if(layers.length){
				for(i=layers.length-1;i>=0;i--){
					layers[i].display(true);
				}
			}
			return feature;
		}

		function _clickEvt(){
			_mapHandler.events.register("mouseup",{},function(e){
				if(_variableTypes.isEmptyObject(_eventHandlers["mouseClick"])){
					return;
				}

				var x = e.clientX, y = e.clientY,
					i,len,layer,target,feature,targets = [],layers = [];
				target = document.elementFromPoint(x,y);
				if(target && target._featureId){
					for(i=_mapHandler.layers.length-1;i>=0;i--){
						layer = _mapHandler.layers[i];
						
						if(!layer.getFeatureById){
							continue;
						}
						feature = layer.getFeatureById(target._featureId);
						if(feature){
							break;
						}
					}
				}else{
					feature = _getTargetFeature(x,y);
				}

				if(feature){
					var _geometry = _geoUtil.convertFromObject(feature.geometry),
						_centerP = _geoUtil.convertFromObject(feature.geometry.getCentroid()),
						clickedPointLonLat=MapManager().toMap(e.offsetX,e.offsetY);
					if(feature.layer.id == _layerid){
						for(var elem in _eventHandlers["mouseClick"]){
							if(_eventHandlers["mouseClick"][elem]["layerid"] != _layerid ||
								feature.layer.id != _layerid
							){
								continue;
							}
							_eventHandlers["mouseClick"][elem]["func"]({
								layerid : getId(),
								graphic : {
									id : feature.id,
									geo : _geometry,
									attributes : feature.attributes
								},
								mapPoint : _centerP,
								//获取点击点屏幕坐标 并转换为地图经纬度坐标
								clickedPointLonLat:clickedPointLonLat
							});
						}
					}
				}
			});
		}

		function _visibleChangeEvt(){
			_layerHandler.events.register("visibilitychanged",{},function(){
				if(_variableTypes.isEmptyObject(_eventHandlers["visibleChanged"])){
					return;
				}
				for(var elem in _eventHandlers["visibleChanged"]){
					if(_eventHandlers["visibleChanged"][elem]["layerid"] != _layerid){
						continue;
					}
					_eventHandlers["visibleChanged"][elem]["func"](_layerHandler.visibility);
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

		function _mouseDownEvt(){
			_mapHandler.events.register("mousedown",{},function(evt){
				if(_variableTypes.isEmptyObject(_eventHandlers["mouseDown"])){
					return;
				}
				var _featureId = null;
				if(!evt.target || !evt.target._featureId){
					if(!evt.srcElement || !evt.srcElement._featureId){
						return;
					}else{
						_featureId = evt.srcElement._featureId;
					}
				}else{
					_featureId = evt.target._featureId;
				}
				var feature = _layerHandler.getFeatureById(_featureId),
					graphic = new Graphic();
				if(!feature){
					return;
				}
				graphic.id = feature.id;
				graphic.geo = _geoUtil.convertFromObject(feature.geometry);
				graphic.symbol = _symbolUtil.convertFromObject(graphic.geo.type,feature.style);
				graphic.attributes = feature.attributes;
				for(var elem in _eventHandlers["mouseDown"]){
					if(_eventHandlers["mouseDown"][elem]["layerid"]!=_layerid ||
						feature.layer.id != _layerid
					){
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
			},true);
		}

		var preFeatureId;
		function _mouseOverEvt(){
			_mapHandler.events.register("mousemove",{},function(evt){
				var _target = evt.target || evt.srcElement;
				var feature = null;
				_hasMouseOverFeature = false;
				if(!_target || !_target._featureId){
					feature = _getTargetFeature(evt.clientX,evt.clientY);
				}else{
					feature = _layerHandler.getFeatureById(_target._featureId);
				}
				if(!feature){
					return;
				}
				if(feature.layer.id != _layerid){
					_isMouseOverCurrentLayer = false;
					return;
				}
				_isMouseOverCurrentLayer = true;
				_hasMouseOverFeature = true;
				_isMouseOvered = true;
				if(_variableTypes.isEmptyObject(_eventHandlers["mouseOver"])){
					return;
				}
				var graphic = new Graphic();
				graphic.id = feature.id;
				graphic.geo = _geoUtil.convertFromObject(feature.geometry);
				graphic.symbol = _symbolUtil.convertFromObject(graphic.geo.type,feature.style);
				graphic.attributes = feature.attributes;

				for(var elem in _eventHandlers["mouseOver"]){
					if(_eventHandlers["mouseOver"][elem]["layerid"]!=_layerid){
						continue;
					}
					_eventHandlers["mouseOver"][elem]["func"]({
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
			_mapHandler.events.register("mousemove",{},function(evt){
				if(!_isMouseOvered || _hasMouseOverFeature || !_isMouseOverCurrentLayer){
					return;
				}
				for(var elem in _eventHandlers["mouseOut"]){
					if(_eventHandlers["mouseOut"][elem]["layerid"]!=_layerid){
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
				_isMouseOvered = false;
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
			var _features = _layerHandler.features,
				_newFeatures = [];
			if(!_features){
				return _newFeatures;
			}
			for(var i=0,len=_features.length;i<len;i++){
				var geo = _geoUtil.convertFromObject(_features[i].geometry);
				_newFeatures.push({
					id : _features[i].id,
					geo : geo,
					attributes : _features[i].attributes,
					symbol : _symbolUtil.convertFromObject(geo.type,_features[i].style)
				});
			}
			return _newFeatures;
		}
		
      
        
		
		function filterByAttr(condition,isremove){
			var _features = _layerHandler.features,
				i,len,elem,
				_removeAbleFeatures = [],
				_featuresId = [];
			for(i=0,len=_features.length;i<len;i++){
				var _feature = _features[i],
					_attributes = null;
				if(!_feature){
					continue;
				}
				_attributes = _feature.attributes;
				if(!_attributes && isremove){
					if(!isremove){
						_removeAbleFeatures.push(_feature);
						_featuresId.push(_feature.id);
					}
					continue;
				}
				for(elem in condition){
					 if(typeof _attributes[elem] == "undefined"){
						if(!isremove){
							_removeAbleFeatures.push(_feature);
							_featuresId.push(_feature.id);
						}
						continue;
					 }else{
						if(isremove){
							if(_attributes[elem] == condition[elem]){
								_removeAbleFeatures.push(_feature);
								_featuresId.push(_feature.id);
							}
						}else{
							if(_attributes[elem] != condition[elem]){
								_removeAbleFeatures.push(_feature);
								_featuresId.push(_feature.id);
							}
						}
					 }
				}
			}
			_layerHandler.removeFeatures(_removeAbleFeatures);
			for(i=0,len=_featuresId.length;i<len;i++){
				delete MapFactory._MapGraphicResource[_featuresId[i]];
			}
		}
		
	   function removeFeatureByAttribute(item) {
			    if(!item){
			    	return;
			    }
				var _features = _layerHandler.features, 
				i, len, elem,
				_removeAbleFeatures = [],
				_featuresId = [];
				for (i = 0, len = _features.length; i < len; i++) {
					var _feature = _features[i];
					var _attribute = _feature.attributes;
					if (!_feature || !_attribute) {
						continue;
					}
					if(MapFactory.JSON.Stringify(_attribute)===MapFactory.JSON.Stringify(item)){
						_removeAbleFeatures.push(_feature);
						_featuresId.push(_feature.id);
					}
				}
				_layerHandler.removeFeatures(_removeAbleFeatures);
				for(i=0,len=_featuresId.length;i<len;i++){
					delete MapFactory._MapGraphicResource[_featuresId[i]];
				}
	  }
		
		return eval(MapFactory.GenerateAPI(api));
	}
});