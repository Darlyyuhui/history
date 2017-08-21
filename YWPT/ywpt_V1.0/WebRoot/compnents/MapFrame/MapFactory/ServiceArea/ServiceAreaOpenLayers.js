/**
 * 服务区分析ArcGIS实现
 * @author ZLT
 */
MapFactory.Define("MapFactory/ServiceArea",[
	"MapFactory/ServiceAreaAPI*",
	"MapFactory/MapManager",
	"esri/tasks/ServiceAreaParameters*",
	"esri/tasks/ServiceAreaTask*",
	"MapFactory/GeometryUtil",
	"esri/tasks/FeatureSet*",
	"esri/graphic*",
	"MapFactory/GeometryType*",
	"MapFactory/Message*"
],function(api,mapManager,serviceParams,serviceTask,geoUtil,fSetClass,graphicClass,geoType,messageClass){
	return function(){
		var _url = "",
			_facilitiesF = new fSetClass(),
			_pointBarriersF = new fSetClass(),
			_polylineBarriersF = new fSetClass(),
			_polygonBarriersF = new fSetClass(),
			_times = [],
			_geoUtil = geoUtil(),
			_mapManager = mapManager();

		function setUrl(url){
			_url = url;
		}

		function setFacilities(geoArr){

		}

		function setBarriers(geoArr){

		}

		function setBreaks(times){
			_times = times;
		}

		function execute(successFunc,failureFunc){

		}

		return eval(MapFactory.GenerateAPI(api));
	}
});