MapFactory.Define("ItmsMap/UserLayers/CustomLayers/Video*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"MapFactory/LayerManager"
],function(api,LayerManager){
	
	function showLayers(data) {
		if(data.videoLayer.isOpen)LayerManager("videoLayer").show();
		else LayerManager("videoLayer").hide();
	}

	return eval(MapFactory.GenerateAPI(api));
});