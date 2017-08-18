MapFactory.Define("ItmsMap/UserLayers/CustomLayers/Diggings*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"MapFactory/LayerManager"
],function(api,LayerManager){
	
	function showLayers(data) {
		if(data.ndPolygon.isOpen)LayerManager("ndPolygon").show();
		else LayerManager("ndPolygon").hide();
	}

	return eval(MapFactory.GenerateAPI(api));
});