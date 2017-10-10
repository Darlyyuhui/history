MapFactory.Define("ItmsMap/UserLayers/CustomLayers/SampleMonitor*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"MapFactory/LayerManager","MapFactory/GraphicManager",
	"MapFactory/InfoWindowManager","MapFactory/Geometry*",
	"ItmsMap/SymbolConfig*","MapFactory/MapManager"
],function(api,LayerManager,GraphicManager,InfoWindowManager,GeometryUtil,SymbolConfig,_map){
	
	var currentClickGraphicObj;
	var ydjkLayerPoint;
	var isInit=false;
	var map;
	var graphicTemp;
	return function(){
		function initInfo(){
			if(isInit){
				return;
			}
			map= _map();
			ydjkLayerPoint=LayerManager("ydjkLayerPoint");
			ydjkLayerPoint.addOnClickEvent(function(obj){
				currentClickGraphicObj=obj.graphic;
				getClickGraphic(currentClickGraphicObj);
			});
			isInit=true;
		}
		
		initInfo();
		
		function showLayers(data) {
			if(data.ydjkLayerPoint.isOpen)ydjkLayerPoint.show();
			else ydjkLayerPoint.hide();
		}
		/*
		 * 移除单个点
		 */
		function removeGraphic(itemData){
			ydjkLayerPoint.removeFeatureByAttribute(itemData);
			if(currentClickGraphicObj && MapFactory.JSON.Stringify(itemData)===MapFactory.JSON.Stringify(currentClickGraphicObj.attributes)){
				InfoWindowManager().hide();
				currentClickGraphicObj=null;
			}
		}
        function drawRegionByTimeChange(){
			
		}
		/*
		 * 清除所有点
		 */
		function clearAllGraphic(){
			currentClickGraphicObj=null;
			ydjkLayerPoint.clear();
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
			
			var graphicPoint={geo: point, symbol: SymbolConfig["ydjkSymbol"], attributes: data};
			var graphic=GraphicManager(graphicPoint);
			graphic.addToLayer("ydjkLayerPoint");
			
		}
		function getClickGraphic(g) {
			//showInfowindow(new GeometryUtil(g.geo), "", "");
			var attributes=g.attributes;
			var cydId=attributes.id;
			
			showInfowindow(new GeometryUtil(g.geo),cydId,"");
		}
		
		function showInfowindow(point, code, plateNum) {
			var info=InfoWindowManager();
			info.setAnchor(point);
			info.setWidth(600);
			info.setHeight(500);
			info.setTitle("样地监控");
			info.setLoadPage(path+"/map/video/info/"+code+"/",{}, function(){
				
			});
			info.show();
		}
		
		return eval(MapFactory.GenerateAPI(api));
	}
});