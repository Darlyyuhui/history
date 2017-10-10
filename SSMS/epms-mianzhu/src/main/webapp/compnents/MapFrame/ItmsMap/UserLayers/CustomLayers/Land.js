MapFactory.Define("ItmsMap/UserLayers/CustomLayers/Land*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"MapFactory/LayerManager","MapFactory/GraphicManager",
	"MapFactory/InfoWindowManager","MapFactory/Geometry*",
	"ItmsMap/SymbolConfig*","MapFactory/MapManager"
],function(api,LayerManager,GraphicManager,InfoWindowManager,GeometryUtil,SymbolConfig,_map){
	var currentClickGraphicObj;
	var landLyrPologn;
	var landLyrPoint;
	var isInit=false;
	var map;
	var graphicTemp;
	var landInfoList;
	return function(){
		function initInfo(){
			if(isInit){
				return;
			}
			map= _map();
			landLyrPologn=LayerManager("landLyrPologn");
			landLyrPoint=LayerManager("landLyrPoint");
//			landLyrPologn.addOnClickEvent(function(obj){
//				
//				currentClickGraphicObj=obj.graphic;
//				getClickGraphic(currentClickGraphicObj);
//			});
			
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
		
		/*
		 * 清除所有点
		 */
		function clearAllGraphic(){
			currentClickGraphicObj=null;
			landLyrPologn.clear();
			landLyrPoint.clear();
			InfoWindowManager().hide();
		}
		
		/**
		 * 
		 * 绘制多个点
		 * @param data
		 * @returns
		 */
		function drawRegions(id){
			MapFactory.XHR.Post(path
					+ "/map/all/landblock/",
					function(target) {
				if(id){
					for(var j=0;j<target.length;j++){
						if(target[j].id==id){
							target.splice(j,1);
						}
					}
				}
				drawLands(target,"qk");
			});
			MapFactory.XHR.Post(path
					+ "/map/all/apblandblock/",
					function(target) {
				if(id){
					if(id=="1"){return;}
					for(var i=0;i<target.length;i++){
						if(target[i].id==id){
							target.splice(i,1);
						}
					}
				}
				drawLands(target,"apb");
			});
		}
		function drawLands(data,type){
			var list=data;
			for(var i=0;i<list.length;i++){
				var itemData=list[i];
				drawOnlyRegions(itemData,false,type);
			}
		}
		
		function drawRegionByTimeChange(){
			
		}
		/**
		 * 
		 * 绘制单个点
		 * @param data
		 * @returns
		 */
		
		function drawOnlyRegions(data,isLocal,type){
			var graphicObj,graphic;
			var pologn = data.geoJson;
			var polognSymbol=SymbolConfig["landSymbol"];
			if(type=="apb"){
				polognSymbol=SymbolConfig["apblandSymbol"];
			}
			//绘制区块
			if(pologn){
			graphicObj={geo: pologn, symbol: polognSymbol, attributes: data};
			graphic=GraphicManager(graphicObj);
			graphic.addToLayer("landLyrPologn");
			}
			//绘制弹出框背景图
			if(data.longitude){
				var point={type: "point", points: data.longitude + "," + data.latitude};
				graphicObj={geo: point, symbol: SymbolConfig["landInfoSymbol"], attributes: data};
				graphic=GraphicManager(graphicObj);
				graphic.addToLayer("landLyrPoint");
				
				var textSymbol=SymbolConfig["landInfoTextSymbol"];
				textSymbol.text="编号:"+data.code +" 面积(亩):"+data.area;
				graphicObj={geo: point, symbol: textSymbol, attributes: data};
				graphic=GraphicManager(graphicObj);
				graphic.addToLayer("landLyrPoint");
			}
		}
		return eval(MapFactory.GenerateAPI(api));
	}
});