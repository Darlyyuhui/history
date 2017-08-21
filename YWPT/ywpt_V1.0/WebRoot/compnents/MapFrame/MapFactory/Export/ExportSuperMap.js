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
		}

		function exportMap(successFunc,failureFunc){
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});