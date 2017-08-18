MapFactory.Define("ItmsMap/UserLayers/CustomLayers/GPS*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"ItmsMap/GPS/GPSManager*",
	"MapFactory/LayerManager"
],function(api,GPS,LayerManager){	   
	function showLayers(data){			   			
			if(data["gps"]["isOpen"]==true){
				 GPS.statistic(null);				
			}else{				
				 LayerManager("gps").clear();
			}	 		  					
	}		
	return eval(MapFactory.GenerateAPI(api));
});