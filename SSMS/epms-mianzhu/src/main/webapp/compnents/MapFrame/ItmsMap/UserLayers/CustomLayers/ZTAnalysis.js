MapFactory.Define("ItmsMap/UserLayers/CustomLayers/ZTAnalysis*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"MapFactory/LayerManager","MapFactory/GraphicManager",
	"MapFactory/InfoWindowManager","MapFactory/Geometry*",
	"ItmsMap/SymbolConfig*","MapFactory/MapManager"
],function(api,LayerManager,GraphicManager,InfoWindowManager,GeometryUtil,SymbolConfig,_map){
	
	var currentClickGraphicObj;
	var isInit=false;
	var map;
	var graphicTemp;
	return function(){
		function initInfo(){
			if(isInit){
				return;
			}
			map= _map();
			
			isInit=true;
		}
		
		initInfo();
		
		function showLayers(data) {
			
		}
		function drawRegionByTimeChange(){
			
		}
		/*
		 * 移除单个点
		 */
		function removeGraphic(itemData){
			LayerManager("ysfxLyr").clear();
		}
		
		/*
		 * 清除所有点
		 */
		function clearAllGraphic(){
			currentClickGraphicObj=null;
			
			InfoWindowManager().hide();
		}
		/**
		 * 
		 * 绘制多个点
		 * @param data
		 * @returns
		 */
		function drawRegions(data,extendArr){
			var list=data;
			for(var i=0;i<list.length;i++){
				var itemData=list[i];
				drawOnlyRegions(itemData,false);
			}
		}
		
		/**
		 * 
		 * 绘制单个点
		 * @param data
		 * @returns
		 */
		function drawOnlyRegions(data,isLocal){
			LayerManager("ysfxLyr").clear();
			var code=data.url;
			MapFactory.XHR.Post(
					path+ "/map/getStandardMonitor/"+code+"/",
					function(list) {
					var dataInterval=producePolluteLavel(list);
					MapFactory.XHR.Post(path + "/map/mapgeo/equreface/"+code+"/"+dataInterval+"/",
							function(dataList) {
						var geojsonobj=MapFactory.JSON.Parse(dataList);
						renderFXLyr(geojsonobj);
					});
			});
			
		}
		function renderFXLyr(geojsonobj){
			 var geojson=geojsonobj.features;
			 for(var i=0;i<geojson.length;i++){
				 var geo=geojson[i];
				 var value=(geo.properties.hvalue+geo.properties.hvalue)/2;
				 var symobl=SymbolConfig["phEqureFace"];
				 symobl.backgroundColor=getColorRange(getPolluteLavel(value));
				 var multipolygon = geo.geometry;
				 var graphicPolognJson={geo: multipolygon, symbol: symobl,attributes:{}};
				 var graphicPologn=GraphicManager(graphicPolognJson);
				 graphicPologn.addToLayer("ysfxLyr");
			 }
		}
		function getColorRange(level){
			if(level==1){
				return "#E1E1E1"
			}else if(level==2){
				return "#FEBEBE"
			}else if(level==3){
				return "#FF7F7E"
			}else if(level==4){
				return "#FE0000"
			}else if(level==5){
				return "#A80000"
			}else{
				return "#FFFFFF"
			}
		}
		function producePolluteLavel(list){
			PolluteLevel=new Object();
			var dataInterval="";
			for(var i=0;i<list.length;i++){
				var obj=list[i];
				var min=obj.minVal;
				var max=obj.maxVal;
                if(i==0){
					if(min){
						dataInterval=dataInterval+min+",";
					}
					if(max){
						dataInterval=dataInterval+max+",";
					}
				}else{
                    if(max){
                    	dataInterval=dataInterval+max+",";
					}
				}
				if(!min){
					min="∞";
				}
				if(!max){
					max="∞";
				}
				var key=min+"-"+max;
				PolluteLevel[key]=i+1;
			}
			return dataInterval.substring(0,dataInterval.length-1)
		}
		function getPolluteLavel(cadmium){
			if(!cadmium){
				return "1";
			}
			for(var key in PolluteLevel){
				var valueArr=key.split("-");
				if(valueArr[0]=="∞"){
					if(cadmium<=parseFloat(valueArr[1])){
						return PolluteLevel[key];
					}
				}else if(valueArr[1]=="∞"){
					if(cadmium>parseFloat(valueArr[0])){
						return PolluteLevel[key];
					}
				}else{
					if(cadmium>parseFloat(valueArr[0]) && cadmium<=parseFloat(valueArr[1])){
						return PolluteLevel[key];
					}
				}
			}
		}
		
		return eval(MapFactory.GenerateAPI(api));
	}
});