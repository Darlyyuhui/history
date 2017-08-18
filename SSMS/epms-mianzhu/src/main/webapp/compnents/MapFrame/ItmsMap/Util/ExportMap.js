MapFactory.Define("ItmsMap/Util/ExportMap*",[
	"MapFactory/MapManager",
	"MapFactory/LayerManager",
	 "ItmsMap/SymbolConfig*",
	 "ItmsMap/SymbolChange*"
],function(MapManager,LayerManager,SymbolConfig,SymbolChange){
	SymbolChange.changeOccupyIcon();
	return function(){
		var api = {
			init : init
		};
		var _mapManager = MapManager();
		var _dom = MapFactory.Dom;
		var newWindow = null;	
		function _initWindow(){
			var _url = "map/getExportMapPage/";
			if(MapFactory.Browser.isIE()){
				_url = "../../../"+_url;
			}
			var _mapContainer = _dom.getById("map");
			var _width = 860,_height = 600;

			if(_mapContainer){
				_width = mapContainer.clientWidth;
				_height = mapContainer.clientHeight;
			}
			// 打开新窗口
			newWindow = window.open(_url,"地图导出","status=no,left=140,top=100,resizable=0,location=no,width="+_width+",height=600");
			//获取地图当前范围---
			var _currentExtent = _mapManager.getCurrentExtent();
			//获取当前地图所有图层id
			var _layerIds = _mapManager.getAllLayersID();
			var _features ={};
			var _grahics=[];			
			for(var i=0,len=_layerIds.length;i<len;i=i+1){
				_grahics = _grahics.concat(LayerManager(_layerIds[i]).getFeatures());
				  var layer=LayerManager(_layerIds[i]);
				  var vectors=layer.getFeatures();
				  if(vectors==null ||vectors.length==0){
					  continue;
				  }
				  _features[_layerIds[i]]=vectors;
			}			  
			//获取当前地图所有图层要素---------结束-----			
			newWindow.currentExtent = _currentExtent;
			newWindow.currentFeatures = _features;
			newWindow.mapWidth = _width;
			newWindow.mapHeight = _height;
			newWindow.legendimages=getLegend(_grahics);
			//为新窗口添加新属性----			
//			var _infoWindow = _dom.getById("DialogOuterBox_InfoWindow");
//			if(_infoWindow){
//				newWindow.infoWindowDialog = _dom.outerHTML(_infoWindow);
//			}else{
//				newWindow.infoWindowDialog = "";
//			}
			//为新窗口添加标题-----
			newWindow.document.title = "地图打印";				
//			var _legend = _dom.getById("DialogOuterBox_mapLegend");
//			if(_legend){
//				newWindow.mapLegend = _dom.outerHTML(_legend);
//			}else{
//				newWindow.mapLegend = "";
//			}
		}		
		 function getLegend(features){
		       var lastfeatures=[];
			   for(var index=0;index<features.length;index=index+1){
				   var i=-1;
				   var firstobj=getSymbolName(features[index].symbol);
				   if(MapFactory.VariableTypes.isNull(firstobj)){
					   continue;
				   }
				   for(var j=0;j<lastfeatures.length;j=j+1 ){
					   var name=lastfeatures[j].name;
					    if(firstobj["name"]==name){					    	
					    	i=j;					    	
					    }					   					   					   
				   }				
				   if(i==-1){
					   lastfeatures.push(firstobj);					   
				   }				   				   				   				   
			   }
			  
		   	 return lastfeatures;			 			 			 			 
		 }
		 
		 function getSymbolName(symbol){
			 if(MapFactory.VariableTypes.isUndefined(symbol) || MapFactory.VariableTypes.isNull(symbol)){				 
				 return null;				 
			 }			
			  var url=symbol["url"];
              var symObj=null;
			      if(url){
			    	  for(var attr in SymbolConfig){
			    		  var symbolvalue=SymbolConfig[attr];
			    		  var imageurl=symbolvalue["url"];
			    		  if(imageurl){
			    			  if(url==imageurl){
			    				  symObj=symbolvalue;		    				  
			    			  }			    			  			    			  
			    		  }			    		  			    		  			    					    		  
			    	  }			    	  			    	  			    	  
			       } else{
			    	    var outLineColor=symbol.outLineColor;
					//	var backgroundColor=symbol.backgroundColor;
						 for(var attr in SymbolConfig){
				    		  var symbolvalue=SymbolConfig[attr];
				    		  var imageurl=symbolvalue["url"];
				    		  if(MapFactory.VariableTypes.isUndefined(imageurl) || MapFactory.VariableTypes.isNull(imageurl)){
				    		    if(outLineColor==symbolvalue.outLineColor){
				    		    	symObj=	symbolvalue;	    	    	  			    	    	  			    	    	  
				    	         }   
				    		  }
				    		  
				    	  }																    	    			    	   			    	   
			       }			 			 
			 return symObj;
			 
		 }				
		function init(){
			_initWindow();
		}
		return api;
	}
});