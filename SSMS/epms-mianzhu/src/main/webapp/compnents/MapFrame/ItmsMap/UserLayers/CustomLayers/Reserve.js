MapFactory.Define("ItmsMap/UserLayers/CustomLayers/Reserve*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"MapFactory/LayerManager"
],function(api,LayerManager){
	
	function showLayers(data) {
		if(data.naturePolygon.isOpen)LayerManager("naturePolygon").show();
		else LayerManager("naturePolygon").hide();

        if(data.waterPolygon.isOpen)LayerManager("waterPolygon").show();
        else LayerManager("waterPolygon").hide();
	}

	return eval(MapFactory.GenerateAPI(api));
});