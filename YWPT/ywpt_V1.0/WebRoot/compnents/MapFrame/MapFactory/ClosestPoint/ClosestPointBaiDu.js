MapFactory.Define("MapFactory/ClosestPoint",[
    "MapFactory/ClosestPointAPI*"
],function(api){
	return function(conf){

		function setLayerNames(urls){
		}

		function setLayerIds(_ids){
		}

		function setGeometry(_geos){
		}

		function destroy(){
		}

		function getClosestPoint(point,func){
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});