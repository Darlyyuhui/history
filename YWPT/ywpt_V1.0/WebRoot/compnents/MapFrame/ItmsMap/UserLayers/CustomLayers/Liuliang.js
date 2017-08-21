MapFactory.Define("ItmsMap/UserLayers/CustomLayers/Liuliang*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"ItmsMap/Flow/Flow*"
],function(api,Flow){
		function showLayers(data) {
		var flow = Flow();
		flow.showLinks(data.flow.isOpen);
		flow.showDetectors(data.flowDevice.isOpen);
		flow.showGuides(data.flowPanel.isOpen);
	}
	return eval(MapFactory.GenerateAPI(api));
});