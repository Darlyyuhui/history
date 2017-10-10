MapFactory.Define("ItmsMap/UserLayers/CustomLayers/Company*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"MapFactory/LayerManager","MapFactory/GraphicManager",
	"MapFactory/InfoWindowManager","MapFactory/Geometry*",
	"ItmsMap/SymbolConfig*","MapFactory/MapManager","ItmsMap/UserLayers/DataController*"
	],function(api,LayerManager,GraphicManager,InfoWindowManager,GeometryUtil,SymbolConfig,_map,DataController){
	var isInit=false;
	var wrcpLyr;
	var currentClickGraphicObj;
	return function(){
		function initInfo(){
			if(isInit){
				return;
			}
			map= _map();
			wrcpLyr=LayerManager("wrcpLyr");
			wrcpLyr.addOnClickEvent(function(obj){
				currentClickGraphicObj=obj.graphic;
				getClickGraphic(currentClickGraphicObj);
			});
			isInit=true;
		}
		
		initInfo();

		function isShowLyr(id,isShow){
			isShow ? LayerManager(id).show() : LayerManager(id).hide();
		}

		function showLayers(data) {

		}
		/*
		 * 移除单个点
		 */
		function removeGraphic(itemData){
			isShowLyr(itemData.id,false);
		}

		/*
		 * 清除所有点
		 */
		function clearAllGraphic(data){
			wrcpLyr.clear();
		}
		/**
		 * 
		 * 绘制多个点
		 * @param data
		 * @returns
		 */
		function drawRegions(data,extendArr){
			 MapFactory.XHR.Post(path + "/map/getPolluteCompany/",
						 function(list) {
				 drawComPoint(list);
		     });
		}
		
		function drawComPoint(list){
			for(var i=0;i<list.length;i++){
				var obj=list[i];
				var point={type:"point",points:obj.longitude+","+obj.latitude};
				var graphicPoint={geo: point, symbol: SymbolConfig["wrySymbol"], attributes: obj};
				var graphic=GraphicManager(graphicPoint);
				graphic.addToLayer("wrcpLyr");
			}
		}

		/**
		 * 
		 * 绘制单个点
		 * @param data
		 * @returns
		 */

		function drawOnlyRegions(data,isLocal){
			

		}
		function getClickGraphic(g) {
			//showInfowindow(new GeometryUtil(g.geo), "", "");
			var attributes=g.attributes;
			
			showInfowindow(new GeometryUtil(g.geo),attributes);
		}
		
		function showInfowindow(point, countObj) {
			var info=InfoWindowManager();
			info.setAnchor(point);
			info.setTitle("污染企业信息");
			DataController().setcydPoint(countObj);
            info.setLoadPage(path+"/map/comp/info/",{}, function(){
				
			});
			info.setWidth(400);
			info.setHeight(250);
			info.show();
		}
		
        function drawRegionByTimeChange(){
			
		}
		return eval(MapFactory.GenerateAPI(api));
	}
});