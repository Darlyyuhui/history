
MapFactory.Define("ItmsMap/SymbolChange*",["ItmsMap/Util/Legend*","ItmsMap/SymbolConfig*","ItmsMap/ModuleConfig*"],function(legend,symbolConfig,ModuleConfig){

	var moduleSymbol = {
		"wazhan" : _occupy
	};	
	var api = {
		setSymbol : setSymbol,
		changeOccupyIcon:changeOccupyIcon
	};
	var _callback = null;
	function setSymbol(moduleid,callback){
		_callback = callback;
		var shortId = ModuleConfig[moduleid];
		if(shortId && moduleSymbol[shortId]){
			moduleSymbol[shortId]();
		}else{
			_callback();
		}
	}
	function _occupy(){		
		MapFactory.XHR.Get("manage/icon/curruntIconInfo",function(list){
			if(list==null || list.length==0){				
				return;
			  }
		      for(var key in list){				  
		         var occpyinfo=list[key];
				     symbolConfig[occpyinfo.id]={						
						url: occpyinfo.path,
						width: 32,
						height: 32,
						name:occpyinfo.type+"-"+occpyinfo.status					
				}				
			}			
	    	 legend.setIncoInfo(list);
	    	_callback();
		});								
	}
	function changeOccupyIcon(){				
		MapFactory.XHR.Get("manage/icon/curruntIconInfo",function(list){
			if(list==null || list.length==0){				
				return;
			  }
		      for(var key in list){				  
		         var occpyinfo=list[key];
				     symbolConfig[occpyinfo.id]={						
						url: occpyinfo.path,
						width: 32,
						height: 32,
						name:occpyinfo.type+"-"+occpyinfo.status					
				}				
			}			
		});							
	}
	
	
	return api; 

});