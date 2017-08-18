MapFactory.Define("MapFactory/Export",[
    "MapFactory/ExportAPI*"
],function(api){
	return function(){
		var _url = "";

		var _params = {};

		var _xhr = MapFactory.XHR;

		var _json = MapFactory.JSON;

		function setUrl(url){
			_url = url;
		}

		function setParams(params){
			var _layerids = params.layerids;//params.export_graphics;
			var _graphics = [];
			for(var i=0,len=_layerids.length;i<len;i++){
				var graphicids = MapFactory._MapLayerGraphicResource[_layerids[i]];
				if(!graphicids){
					continue;
				}
				for(var j=0,sublen=graphicids.length;j<sublen;j++){
					var _graphic = MapFactory._MapGraphicResource[graphicids[j]];
					if(_graphic){
						_graphic = _graphic.toJson();
						_graphic.attributes = {};
						_graphics.push(_graphic);
					}
				}
			}
			delete params.layerids;
			if(_graphics.length){
				params["export_graphics"] = _json.Stringify(_graphics);
			}else{
				params["export_graphics"] = "";
			}
			_params = params;
		}

		function exportMap(successFunc,failureFunc){
			_xhr.Post(_url,_params,function(data){
				if(!data && failureFunc){
					failureFunc();
				}
				successFunc(data);
			});
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});