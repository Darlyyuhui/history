MapFactory.Define("ItmsMap/UserLayers/CustomLayers/Regions*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"MapFactory/LayerManager","MapFactory/GraphicManager",
	"MapFactory/InfoWindowManager","MapFactory/Geometry*",
	"ItmsMap/SymbolConfig*","MapFactory/MapManager",
	"ItmsMap/LayerLevelConfig*","ItmsMap/UserLayers/DataController*",
	"ItmsMap/SymbolColorConfig*"
	],function(api,LayerManager,GraphicManager,InfoWindowManager,
			GeometryUtil,SymbolConfig,_map,layerLevelCon,DataController,SymbolColorConfig){

	var currentClickGraphicObj;
	var xzqyPolognLyr;
	var xzqyLyr;
	var map;
	var graphicTemp;
	var isInit=false;
	var countList;
	return function(){

		function initInfo(){

			if(isInit){
				return;
			}
			countList=DataController().getCountData();
			map= _map();
			xzqyPolognLyr=LayerManager("xzqyLyrPologn");
			//xzqyLyr=LayerManager("xzqyLyrPoint");
			var attr;

			map.setZoomEvent({zoomend : function(){
				mapZoomHandle();
			}});
//			xzqyLyr.addMouseOverEvent(function(obj){
//			attr=obj.graphic.attributes;
//			if(!(currentClickGraphicObj && attr.id===currentClickGraphicObj.attributes.id)){
//			getClickGraphic(attr);
//			}
//			currentClickGraphicObj=obj.graphic;
//			});
			xzqyPolognLyr.addMouseOverEvent(function(obj){

				attr=obj.graphic.attributes;
				if(!(currentClickGraphicObj && attr.id==currentClickGraphicObj.attributes.id)){
				    getClickGraphic(attr,obj.clickedPointLonLat);
				    addHightLight(attr);
				}
				currentClickGraphicObj=obj.graphic;
			});

			isInit=true;
		}

		initInfo();

		function mapZoomHandle() {
			var currentLevel=map.getLevel();
			var isShow=parseInt(currentLevel)<layerLevelCon["xzqushowlevel"];
			if(isShow){
				xzqyPolognLyr.show();
				//xzqyLyr.show();
			}else{
				xzqyPolognLyr.hide();
				//	xzqyLyr.hide();
				removeHeightLight();
				InfoWindowManager().hide();
			}
		}

		function showLayers(data) {
//			if(data.xlzLyr.isOpen)xzqyLyr.show();
//			else xzqyLyr.hide();
		}
		/*
		 * 移除单个点
		 */
		function removeGraphic(itemData){
//			xzqyLyr.removeFeatureByAttribute(itemData);
			xzqyPolognLyr.removeFeatureByAttribute(itemData);
			if(currentClickGraphicObj && MapFactory.JSON.Stringify(itemData)===MapFactory.JSON.Stringify(currentClickGraphicObj.attributes)){
				info.hide();
				currentClickGraphicObj=null;
			}
		}

		/*
		 * 清除所有点
		 */
		function clearAllGraphic(){
			currentClickGraphicObj=null;
			xzqyPolognLyr.clear();
//			xzqyLyr.clear();
			removeHeightLight();
			InfoWindowManager().hide();
		}
		/**
		 * 
		 * 绘制多个点
		 * @param data
		 * @returns
		 */
		function drawRegions(data,extendArr){
			//是否隐藏图层
			mapZoomHandle();
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

			// 获取行政区域污染级别
			var regionid=data.id;

			if(!regionid)
				return;

			var countObj=countList[regionid];
			if(!countObj)
				return;

			var polygongeo=countObj.geo;
			if(!polygongeo){
				return;
			}
			var pointCenter=countObj.center;
			pointCenter=MapFactory.JSON.Parse(pointCenter);
			var polluteLevel=SymbolColorConfig[countObj["polluteLevel"]];
			var symbol=SymbolConfig["regionPolluteLevel4"];
			if(polluteLevel){
				symbol=SymbolConfig[polluteLevel];
			}
			var graphicPologn={geo: polygongeo, symbol:symbol , attributes: data};
			GraphicManager(graphicPologn).addToLayer("xzqyLyrPologn");
			if(isLocal){
				//是否隐藏图层
				mapZoomHandle();
				
				var pointArr=pointCenter.points.split(",");
				var x=parseFloat(pointArr[0]);
				var y=parseFloat(pointArr[1]);
				map.centerAt(x,y);
			}
		}

		/**
		 * 
		 * 移除高亮区域
		 */
		function removeHeightLight(){
			if(graphicTemp){
				graphicTemp.remove();
				graphicTemp=null;
			}
		}
		/**
		 * 
		 * 添加高亮图层
		 */
		function addHightLight(attr){
			removeHeightLight();
			var multipolygon = attr.geom; 
			var graphicPolognJson={geo: multipolygon, symbol: SymbolConfig["regionPolognSymbol"], attributes: attr};
			var graphicPologn=GraphicManager(graphicPolognJson);
			graphicPologn.addToLayer("hightLightLyr");
			graphicTemp=graphicPologn;
		}
		function getClickGraphic(attr,pointLonLat) {
			var countObj=countList[attr.id];
			var center=countObj.center;
			if(center){
				//var point={type: "point", points: pointLonLat.x + "," + pointLonLat.y};
				var point=MapFactory.JSON.Parse(center);
				var pointG=new GeometryUtil(point);
				DataController().setReginId(attr.id);
				showInfowindow(pointG, attr.id, attr.name);
			}
		}

		function showInfowindow(point, code, title) {
			var info=InfoWindowManager();
			info.setCloseFunc(function(){
				currentClickGraphicObj=null;
				removeHeightLight();
			});
			info.setAnchor(point);
			info.setWidth(340);
			info.setHeight(220);
			info.setTitle(title);
			//path+"/forward/iframe/regions/"
			var spaceTime = DataController().gettimeSpace();
			info.setLoadPage(path+"/map/pollute/region/info/"+code+"/",spaceTime, function(){

			});

//			positionManager.addHighLightPosition(point);
			info.show();
		}
		function drawRegionByTimeChange(){
		
		}
		return eval(MapFactory.GenerateAPI(api));
	}
});