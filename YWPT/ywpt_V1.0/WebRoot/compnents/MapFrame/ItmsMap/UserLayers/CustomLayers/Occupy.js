MapFactory.Define("ItmsMap/UserLayers/CustomLayers/Occupy*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"ItmsMap/Occupy/OccupyRoadInfo*",
	"MapFactory/LayerManager"
],function(api,OccupyInfo,LayerManager){
	  var occupys=OccupyInfo();
	   var occupyArr= occupys.displayAllOccupyInfo(false);	
	function showLayers(data){	  
		
		for(var layerid in data){			
			if(data[layerid]["isOpen"]==true){
				var status=getStatusFromLayerName(layerid);
				var  occupyinfos=getOccupyInfoByConditon(layerid);
				occupys.displayAllOccupyInfoToMap(occupyinfos,{isclear:false,status:status}); 				
		     } 
			if(data[layerid]["isOpen"]==false){	
				var status=getStatusFromLayerName(layerid);
				var occupyinfos=getOccupyInfoByConditon(layerid);
				occupys.displayAllOccupyInfoToMap(occupyinfos,{isclear:true,status:status}); 				
		     } 
			
		}
	}
	 function getOccupyInfoByConditon(layerid){		 	  
	        var occupyInfos=[];
	        var status=getStatusFromLayerName(layerid); 		
				for(var i=0,len=occupyArr.length; i<len; i++) {
					   var occupyinfo=occupyArr[i];
					   if(occupyinfo.status==status){
					      occupyInfos.push(occupyinfo);
					   }								
					}								      
		  return occupyInfos;
	 }
	function getStatusFromLayerName(layerid){		
			var status=null;
			switch(layerid){
			case occupys.Occupy_Layer_ID.unStart: {
				 status=occupys.OCCUPY_STATUS.UNSTARTED;
				 break;
			}
			case occupys.Occupy_Layer_ID.constructing : {
				 status=occupys.OCCUPY_STATUS.CONSTRUCTION;
				break;
			}
			case occupys.Occupy_Layer_ID.maturring : {
				 status=occupys.OCCUPY_STATUS.MATURING;
				break;
			}
			case occupys.Occupy_Layer_ID.defferring : {
				 status=occupys.OCCUPY_STATUS.DEFERRING;
				break;
			}
			case occupys.Occupy_Layer_ID.done : {
				 status=occupys.OCCUPY_STATUS.DONE;
				break;
			}
			case occupys.Occupy_Layer_ID.matured: {
				 status=occupys.OCCUPY_STATUS.MATURED;
				break;
			}		
		}				
			return status;
	}		
	return eval(MapFactory.GenerateAPI(api));
}
);