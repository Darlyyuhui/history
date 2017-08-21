MapFactory.Define("ItmsMap/UserLayers/CustomLayers/Pass*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"ItmsMap/Pass/Passquery*",
	"MapFactory/LayerManager"
],function(api,Passquery,LayerManager){
		function showLayers(data) {
			 var pass=Passquery();
			if(data["passportresult"]["isOpen"]==true){
				 pass.statistics(null);			
			}else{				
				LayerManager("passportresult").clear();
			}	          	              	           
	  }
	return eval(MapFactory.GenerateAPI(api));
});