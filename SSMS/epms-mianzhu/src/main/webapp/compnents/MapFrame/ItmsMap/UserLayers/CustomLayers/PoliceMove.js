MapFactory.Define("ItmsMap/UserLayers/CustomLayers/PoliceMove*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"ItmsMap/PoliceMove/PoliceMoveManager*",
	"MapFactory/LayerManager"
],function(api,PoliceMove,LayerManager){
	function showLayers(data){		
			if(data["policemoveLayer"]["isOpen"]==true){
				//PoliceMove.statistic(null);		
				PoliceMove.showAll(data.policemoveLayer.isOpen);
			}else{				
				 LayerManager("policemoveLayer").clear();
			}	 		  					
	}		
	return eval(MapFactory.GenerateAPI(api));
});