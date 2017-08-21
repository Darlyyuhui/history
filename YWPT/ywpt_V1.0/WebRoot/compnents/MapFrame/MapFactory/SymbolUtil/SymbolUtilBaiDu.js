MapFactory.Define("MapFactory/SymbolUtil",[
    "MapFactory/SymbolUtilAPI*"
],function(api){
	return function(){

		function convertFromMapFactory(gType,symbolObj){
			return null;
		}

		function convertFromObject(gType,symbolObject){
			return null;
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});