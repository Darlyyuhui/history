MapFactory.Define("ItmsMap/UserLayers/CustomLayers/AgrBase*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"MapFactory/LayerManager","MapFactory/GraphicManager",
	"MapFactory/InfoWindowManager","MapFactory/Geometry*",
	"ItmsMap/SymbolConfig*","MapFactory/MapManager"
],function(api,LayerManager,GraphicManager,InfoWindowManager,GeometryUtil,SymbolConfig,_map){
	
	var currentClickGraphicObj;
	var lcpjdLyrPoint;
	var isInit=false;
	var map;
	var graphicTemp;
	return function(){
		function initInfo(){
			if(isInit){
				return;
			}
			map= _map();
			lcpjdLyrPoint=LayerManager("lcpjdLyrPoint");
			
			lcpjdLyrPoint.addOnClickEvent(function(obj){
				currentClickGraphicObj=obj.graphic;
				getClickGraphic(currentClickGraphicObj);
			});
			
			isInit=true;
		}
		
		initInfo();
		
		function showLayers(data) {
			if(data.lcpjdLyrPoint.isOpen)lcpjdLyrPoint.show();
			else lcpjdLyrPoint.hide();
		}
		/*
		 * 移除单个点
		 */
		function removeGraphic(itemData){
			lcpjdLyrPoint.removeFeatureByAttribute(itemData);
			if(currentClickGraphicObj && MapFactory.JSON.Stringify(itemData)===MapFactory.JSON.Stringify(currentClickGraphicObj.attributes)){
				InfoWindowManager().hide();
				currentClickGraphicObj=null;
			}
		}
		
		/*
		 * 清除所有点
		 */
		function clearAllGraphic(){
			currentClickGraphicObj=null;
			lcpjdLyrPoint.clear();
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
			var point = data.geom;
			if(isLocal){
				var pointArr=MapFactory.JSON.Parse(point).points.split(",");
				var x=parseFloat(pointArr[0]);
				var y=parseFloat(pointArr[1]);
				map.centerAt(x,y);
			}
			var symobl=MapFactory.Clone(SymbolConfig["nchpPointSymbolByG"]);
			
			var graphicPoint={geo: point, symbol:symobl, attributes: data};
			var graphic=GraphicManager(graphicPoint);
			graphic.addToLayer("lcpjdLyrPoint");
			
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
		function getClickGraphic(g) {
			//showInfowindow(new GeometryUtil(g.geo), "", "");
			var attributes=g.attributes;
			var ncpId=attributes.id;
			
			showInfowindow(new GeometryUtil(g.geo),ncpId,"");
		}
		
		function showInfowindow(point, code, plateNum) {
			var info=InfoWindowManager();
			info.setAnchor(point);
			info.setWidth(240);
			info.setHeight(160);
			info.setTitle("农产品基地");
			info.setLoadPage(path+"/map/apb/info/"+code+"/",{}, function(){
				
			});
//			positionManager.addHighLightPosition(point);
			info.show();
		}
		
		return eval(MapFactory.GenerateAPI(api));
	}
});