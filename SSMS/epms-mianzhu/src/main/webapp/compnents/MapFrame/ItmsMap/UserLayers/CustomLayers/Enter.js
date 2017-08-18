MapFactory.Define("ItmsMap/UserLayers/CustomLayers/Enter*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"MapFactory/LayerManager"
],function(api,LayerManager){
	
	function showLayers(data) {
		if(data.eaLayer.isOpen)LayerManager("eaLayer").show();
		else LayerManager("eaLayer").hide();
	}

	return eval(MapFactory.GenerateAPI(api));
});