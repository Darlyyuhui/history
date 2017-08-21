MapFactory.Define("MapFactory/Query",[
	"MapFactory/QueryAPI*",
	"MapFactory/Message*"
],function(api,messageClass){
	return function(url){
		var _url = "",
			_geometry,
			_geometryPrecision = 1,
			_relationship = "intersect",
			_where;

		function setUrl(url){
			_url = url;
		}

		function setGeometry(geometry){
			_geometry = geometry;
		}

		function setGeometryPrecision(precision){
			_geometryPrecision = precision ? precision : _geometryPrecision;
		}

		function setSpatialRelationShip(relationship){
			_relationship = relationship ? relationship : _relationship;
		}

		function setCondition(obj){
		
			
		}

		function execute(successFunc,failureFunc){
			
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});