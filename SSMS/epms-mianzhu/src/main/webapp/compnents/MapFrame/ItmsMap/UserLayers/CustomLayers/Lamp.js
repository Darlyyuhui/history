MapFactory.Define("ItmsMap/UserLayers/CustomLayers/Lamp*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"MapFactory/LayerManager"
],function(api,LayerManager){
	
	function showLayers(data) {
		if(data.lampLayer.isOpen)LayerManager("lampLayer").show();
		else LayerManager("lampLayer").hide();
	}

	return eval(MapFactory.GenerateAPI(api));
});