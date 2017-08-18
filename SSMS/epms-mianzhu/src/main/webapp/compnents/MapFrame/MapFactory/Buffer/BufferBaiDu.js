MapFactory.Define("MapFactory/Buffer",[
	"MapFactory/BufferAPI*",
	"MapFactory/GeometryUtil",
	"MapFactory/Message*"
],function(api,geoUtil,messageClass){
	return function(){

		var _url = "",
			_geometries = [],
			_distance = 10,
			_geoUtil = geoUtil();

		function setUrl(url){
			_url = url;
		}

		function setGeometry(geometryArr){
			_geometries = geometryArr;
		}

		function setDistance(meterArr){
			_distance = meterArr[0];
		}

		function execute(successFunc,failureFunc){
			
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});