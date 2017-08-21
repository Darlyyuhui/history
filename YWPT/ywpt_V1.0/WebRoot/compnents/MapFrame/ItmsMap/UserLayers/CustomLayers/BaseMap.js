MapFactory.Define("ItmsMap/UserLayers/CustomLayers/BaseMap*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"MapFactory/LayerManager"
],function(api,LayerManager){
	
	function showLayers(data) {
		if(data.baseMap.isOpen)LayerManager("baseMap").show();
		else LayerManager("baseMap").hide();
	}

	return eval(MapFactory.GenerateAPI(api));
});