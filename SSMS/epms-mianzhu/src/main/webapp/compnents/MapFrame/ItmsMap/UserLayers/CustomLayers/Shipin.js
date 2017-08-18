MapFactory.Define("ItmsMap/UserLayers/CustomLayers/Shipin*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"ItmsMap/Video/Video*"
],function(api,Video){	
	function showLayers(data) {
		var video = Video();
		video.showAll(data.videoLayer.isOpen);
	}
	return eval(MapFactory.GenerateAPI(api));
});