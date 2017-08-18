MapFactory.Define("ItmsMap/UserLayers/CustomLayers/Weifa*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"ItmsMap/Violate/Violate*"
],function(api,Violate){
		function showLayers(data) {
		var vio = Violate();
		vio.showAll(data.vioLayer.isOpen);
	}
	return eval(MapFactory.GenerateAPI(api));
});