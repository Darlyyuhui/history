MapFactory.Define("ItmsMap/HeatMap/HeatMap*",
		[
          "MapFactory/Layer/HeatLayer*"	,
          "MapFactory/Geometry*",
          "MapFactory/GeometryType*",
          "ItmsMap/Util/ModuleMessage*"	
		], function(Heatlayer,Geometry,GeometryType,ModuleMessage) {
		
	     	      return function(config){	     	    	  
	     	    	         var heatlayer= Heatlayer();
	     	    	             heatlayer.create(config);	     	    	       	     	    	  
	     	    	   function addHeatLayer(paramObj){	        	 
	     		        	 var heattype=paramObj.heattype;
	     		        	 var url="";
	     		        	 if(heattype==""){
	     		        		 return;	        		 
	     		        	   }
	     		        	 if(heattype=="1"){
	     		        		 url="map/vio/getVioheatData/"+currentModuleId+"/";   
	     		        		        		 
	     		        	   }
	     		        	 if(heattype=="2"){
	     		        		 url="cross/map/getCrossHeatStaticData/"+currentModuleId+"/";
	     		        	   }
	     		     		$.ajax({
	     						url: url,
	     						data: {starttime:paramObj.starttime,endtime:paramObj.endtime},
	     						type: "POST",
	     						success: function(result) {
	     							ModuleMessage.showMessage("分析成功！！！！！",ModuleMessage.LOG,99999);	
	     							ModuleMessage.hideMessage();
	     							 var graphics=[];
	     							for(var i=0,len=result.length; i<len; i++) {
	     								var tempData = result[i];
	     								x = parseFloat(tempData.longitude);
	     								y = parseFloat(tempData.lattitude);
	     								var geo= new Geometry({
	     									type : GeometryType.POINT,
	     									points : x + "," + y
	     								});	     			
	     								var graphic={geo:geo,attributes:{count:tempData.counts}};
	     								graphics.push(graphic);
	     							}
	     							heatlayer.addDataSet({graphics:graphics,max:graphics.length});	     							
	     						},
	     						fail: function() {
	     							ModuleMessage.showMessage("分析数据查询错误！！！！",ModuleMessage.ERROR);
	     							
	     						}
	     					});	     		        		     		        	 
	     		          }  	     	    	   
	     	    	   function clearLayer(){	     	    		   
	     	    		   heatlayer.clear();	     	    		   
	     	    	   }	     	    	   	     	    	   
	     	    	   var api={addHeatLayer:addHeatLayer,clearLayer:clearLayer};
	     	    	   return api;
	     	    	  	     	    	  
	     	      }
	     	      			  	
});