MapFactory.Define("ItmsMap/UserLayers/CustomLayers/QinWu*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"ItmsMap/Om/OmOrder*"
],function(api,OmOrder){
	function showLayers(data){
		console.log(data);		
	}
	return eval(MapFactory.GenerateAPI(api));
});