MapFactory.Define("ItmsMap/UserLayers/CustomLayers/BaseLyr*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"MapFactory/LayerManager","MapFactory/GraphicManager",
	"MapFactory/InfoWindowManager","MapFactory/Geometry*",
	"ItmsMap/SymbolConfig*","MapFactory/MapManager"
	],function(api,LayerManager,GraphicManager,InfoWindowManager,GeometryUtil,SymbolConfig,_map){

	return function(){
		function initInfo(){

		}

		function isShowLyr(id,isShow){
			isShow ? LayerManager(id).show() : LayerManager(id).hide();
		}

		function showLayers(data) {

		}
		function drawRegionByTimeChange(){
			
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
			for(var em in data){
				isShowLyr(em,false);
			}
		}
		/**
		 * 
		 * 绘制多个点
		 * @param data
		 * @returns
		 */
		function drawRegions(data,extendArr){
			for(var em in data){
				isShowLyr(em,true);
			}
		}

		/**
		 * 
		 * 绘制单个点
		 * @param data
		 * @returns
		 */

		function drawOnlyRegions(data,isLocal){
			isShowLyr(data.id,true);

		}
		function getClickGraphic(g) {
			//showInfowindow(new GeometryUtil(g.geo), "", "");

		}

		function showInfowindow(point, code, plateNum) {

		}

		return eval(MapFactory.GenerateAPI(api));
	}
});