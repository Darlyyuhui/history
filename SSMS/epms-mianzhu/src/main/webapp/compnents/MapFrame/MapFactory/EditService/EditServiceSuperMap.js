MapFactory.Define("MapFactory/EditService",[
	"MapFactory/EditServicesAPI*",
	"MapFactory/GeometryUtil"
],function(api,GeometryUtil){
	return function(){

		var _type = "",
			_url = "",
			_graphics = [],
			_geoUtil = GeometryUtil();

		function setEditType(type){
			if("ADD" == type){
				_type = SuperMap.REST.EditType.ADD;
			}else if("UPDATE" == type){
				_type = SuperMap.REST.EditType.UPDATE;
			}else if("DELETE" == type){
				_type = SuperMap.REST.EditType.DELETE;
			}
		}

		function setUrl(url){
			_url = url;
		}

		function setGraphics(graphics){
			var attrNames = [],attrValues = [];
			for(var i=0,len=graphics.length;i<len;i++){
				var attributes = graphics[i].attributes;
				var geometry = _geoUtil.convertFromMapFactory(graphics[i].geo);
				//geometry.id = graphics[i]
				if(!attributes.OBJECTID){
					continue;
				}
				geometry.id = attributes.OBJECTID;
				for(var attr in attributes){
					attrNames.push(attr);
					attrValues.push(attributes[attr]);
				}
				_graphics.push({
					fieldNames : attrNames,
					fieldValues : attrValues,
					geometry : geometry
				});
				attrNames = [];
				attrValues = [];
			}
		}

		function submit(successFunc,failureFunc){
			var _editFeatureParameter,_editFeatureService ;
			var _serviceOption = {};
			_serviceOption["processCompleted"] = function(data){
				successFunc();
			}
			_serviceOption["processFailed"] = function(data){
				failureFunc();
			}
			var _params = {};
			_params["editType"] = _type;
			if(_type == SuperMap.REST.EditType.DELETE){
				_params["IDs"] = [];
				for(var i=0,len=_graphics.length;i<len;i++){
					var _attr = _graphics[i].attributes;
					if(_attr && _attr.OBJECTID){
						_params["IDs"].push(_attr.OBJECTID);
					}
				}
			}else{
				_params["features"] = _graphics;
			}
			_editFeatureParameter = new SuperMap.REST.EditFeaturesParameters(_params);
			_editFeatureService = new SuperMap.REST.EditFeaturesService(_url,{
				eventListeners : _serviceOption
			});
			_editFeatureService.processAsync(_editFeatureParameter);
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});