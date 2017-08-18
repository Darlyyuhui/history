MapFactory.Define("ItmsMap/UserLayers/CustomLayers/Kakou*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"ItmsMap/Cross/Cross*"
],function(api,Cross){
		function showLayers(data) {
		var cross = Cross();
		cross.showAll(data.cross.isOpen);
		cross.getAlarmCodes(data.crossAlarm.isOpen);
	}
	return eval(MapFactory.GenerateAPI(api));
});