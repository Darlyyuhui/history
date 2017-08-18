MapFactory.Define("ItmsMap/UserLayers/CustomLayers/Nd*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"MapFactory/LayerManager"
],function(api,LayerManager){
	
	function showLayers(data) {
		if(data.ndLayer.isOpen)LayerManager("ndLayer").show();
		else LayerManager("ndLayer").hide();
	}

	return eval(MapFactory.GenerateAPI(api));
});