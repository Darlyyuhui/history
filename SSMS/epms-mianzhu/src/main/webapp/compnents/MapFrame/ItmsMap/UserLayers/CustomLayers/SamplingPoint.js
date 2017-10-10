MapFactory.Define("ItmsMap/UserLayers/CustomLayers/SamplingPoint*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"MapFactory/LayerManager","MapFactory/GraphicManager",
	"MapFactory/InfoWindowManager","MapFactory/Geometry*",
	"ItmsMap/SymbolConfig*","MapFactory/MapManager",
	"ItmsMap/UserLayers/DataController*",
	"ItmsMap/LayerLevelConfig*","ItmsMap/Util/DateFormat*",
	"ItmsMap/Util/ExtentFilter*"
	],function(api,LayerManager,GraphicManager,InfoWindowManager,
			GeometryUtil,SymbolConfig,_map,DataController,
			layerLevelCon,DateFormat,ExtentFilter){

	var currentClickGraphicObj,selectedPoint,pccydLayerPoint,
	 xzqyLyrPoint,isInit=false,map,graphicTemp,datacontroller,
	 _menus,_regionmenu,lastExtent,_extentFilter;
	return function(){
		function initInfo(){
			if(isInit){
				return;
			}
			isInit=true;
			map= _map();
			_extentFilter=ExtentFilter("pccydLayerPoint");
			map.setExtentChangeEvent(function(){
				mapZoomHandle();
			});
			datacontroller=DataController();

			//var clusterStyles=[SymbolConfig["jh1"],SymbolConfig["jh2"],SymbolConfig["jh3"]]
			//普查采样点
//			pccydLayerPoint=LayerManager("pccydLayerPoint",{layerType : "ClusterLayer",clusterStyles:clusterStyles});
			pccydLayerPoint=LayerManager("pccydLayerPoint");
			pccydLayerPoint.addMouseOverEvent(function(pointObj){
				var attr=pointObj.graphic;
				
				if(!(selectedPoint && attr.id==selectedPoint.id)){
					cydPointClickHandle(attr.attributes);
				}
				selectedPoint=attr;
			});
			//行政区域采样点统计图层
			xzqyLyrPoint=LayerManager("xzqyLyrPoint");
//			xzqyLyrPoint.addMouseOverEvent(function(obj){
//				var attr=obj.graphic.attributes;
//				if(!(currentClickGraphicObj && attr.id===currentClickGraphicObj.attributes.id)){
//					getClickGraphic(obj.graphic);
//				}
//				currentClickGraphicObj=obj.graphic;
//			});
		}

		initInfo();

		function mapZoomHandle() {
			var currentLevel=map.getLevel();
			var isShow=(parseInt(currentLevel)<layerLevelCon["cydshowlevel"]);
			if(isShow){
				xzqyLyrPoint.show();
				pccydLayerPoint.hide();
				pccydLayerPoint.clear();
				lastExtent=null;
			}else{
				xzqyLyrPoint.hide();
				pccydLayerPoint.show();
				isExitPointData();
//				InfoWindowManager().hide();
//				selectedPoint=null;
			}
		}

		function isExitPointData(){
			var pointList=datacontroller.getPointList();
			var isQuery=true;
			for(var property in pointList){
				isQuery=false;
			}
			if(!isQuery){
				drawcydPoint();
			}else{
				querycyd();
			}
		}


		function querycyd(){
			var spaceTime = datacontroller.gettimeSpace();
			MapFactory.XHR.Post(path
					+ "/map/getall/points/",spaceTime,
					function(target) {
				datacontroller.setPointList(target);
				drawcydPoint();
			});
		}

		function drawcydPoint(){
			var pointList=datacontroller.getPointListByMenu(_menus,_regionmenu);
			//绘制出现新范围的部分
			_extentFilter.queryPolluteLevel(pointList,lastExtent);
			//移除范围外的部分
			_extentFilter.removeOutGraphic();
			lastExtent=map.getCurrentExtent();
		}

		function showLayers(data) {
			if(data.pccydLayerPoint.isOpen)pccydLayerPoint.show();
			else pccydLayerPoint.hide();
		}
		/*
		 * 移除单个点
		 */
		function removeGraphic(itemData){
			pccydLayerPoint.removeFeatureByAttribute(itemData);
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
			selectedPoint=null;
			pccydLayerPoint.clear();
			xzqyLyrPoint.clear();
			InfoWindowManager().hide();
			lastExtent=null;
		}
		function drawRegionByTimeChange(){
			datacontroller.setPointList([]);
			drawRegions(_menus,_regionmenu);
		}
		/**
		 * 
		 * 绘制行政区采样点统计
		 * @param data
		 * @returns
		 */
		function drawRegions(menus,extendArr){

			clearAllGraphic();
			_menus=menus;
			_regionmenu=extendArr;
			if(!menus || menus.length==0){
				return;
			}
			var regionCountList=datacontroller.getRegionCountByMenus(menus);
			for(var i=0;i<regionCountList.length;i++){
				var data=regionCountList[i];
				var centerPoint=data.countObj.center;
				if(!centerPoint){
					continue;
				}
				var graphicPoint={geo:centerPoint , symbol: SymbolConfig["regionPointSymbol"], attributes: data};
				var graphic=GraphicManager(graphicPoint);
				graphic.addToLayer("xzqyLyrPoint");

				var textSymbol=SymbolConfig["regionPointCountSymbol"];
				textSymbol.text=data.cydCountNum+"";
				var textGraphicPoint={geo:centerPoint , symbol: textSymbol, attributes: data};
				var textGraphic=GraphicManager(textGraphicPoint);
				textGraphic.addToLayer("xzqyLyrPoint");
			}
			mapZoomHandle();
		}

		function drawOnlyRegions(data,isLocal){

		}
		
		function cydPointClickHandle(cydPoint){
			var point={type: "point", points: cydPoint.longitude + "," + cydPoint.latitude};
			showcydInfowindow(new GeometryUtil(point),cydPoint);
		}
		
		function showcydInfowindow(point, countObj){
			var info=InfoWindowManager();
			info.setCloseFunc(function(){
				selectedPoint=null;
			});
			info.setAnchor(point);  		    		
			info.setTitle(countObj.pointTypeName);
//			info.setContent(getPointInfo(countObj));
			datacontroller.setcydPoint(countObj);
            info.setLoadPage(path+"/map/point/baseinfo/",{type:countObj.pointTypeTag,
            	objctBean:MapFactory.JSON.Stringify(getNewObject(countObj))}, function(){
			});
			info.setWidth(240);
			info.setHeight(180);
//			info.setHeight(lineNum*28+10);

			info.show();
		}
		var removeAttributes={pointTypeTag:"类型",pointTypeName:"类型名称"};
		/**
		 * 去除某些属性属性
		 */
		function getNewObject(obj){
			var newObj = {};
			for(var elem in obj){
				if(!removeAttributes[elem]){
					newObj[elem] = obj[elem];
				}
			}
			return newObj;
		}
		var dataModel={
				// 地下水监测点   地表灌溉水监测点  底泥监测点
			//	typeCode:"类型",
			//	samplingType:"样品类型",
			//	riversName:"河流名称",
			//	mudLongitude:"底泥经度",
			//	mudLatitude:"底泥纬度",
			//	polluteSs:"污染源状态",
				
			//	regionId:"采样地点",
            //	testItem:"待测项目",
		    //	receiveUser:"收样人",
		    //	sendUser:"送样人",
			//	checkStatus:"审查状态",
			//	checkUser:"审查人",
			//	checkTime:"审查时间",
			//	createId:"创建人",
			//	updateId:"修改人",
			//	updateTime:"修改时间",
			//	status:"状态",
			//	createTime: "创建时间",
		    //  longitude:"经度",
	    	//	latitude: "纬度",
				samplingTime:"采样时间",
				samplingSource:"采样来源",
				samplingUser:"采样人",
	    		name:"样品名称",
	    		depth:"采样深度",
	    		
	    		pointId:"采样地点",
	    		containerVolume:"容器体积",
	    		collectVolume:"收集量",
	    	//	soilType:"土壤类型",
	    		
	    		typeCode:"类型",
	    		
	    		position:"采样部位",
	    		
	    		shopName:"店名",
	    		shopkeeper:"店主",
	    		tel:"联系方式",
	    		dealManure:"经营肥料"
	  		};
		var lineNum=0;
		function getPointInfo(countObj){
			var innerHtml="<div style='margin-left:7px;'><table style='background:white;color: #000;' class='width-100 text-center' id='cydPointInfoDiv'>";
			var line=0;
			for(var key in dataModel){
				if (countObj.hasOwnProperty(key)){
					++line;
					var value = countObj[key] ? countObj[key]:"";
					if(key=='createTime' || key=='updateTime' || key=='samplingTime' || key=='checkTime'){
						value=getDataTimeFormat(value);
						innerHtml = innerHtml + "<tr style='height: 28px;'><td style='font-weight: bold;'>" + dataModel[key] + "</td><td>" + value + "</td></tr>";
					}else if(key=='checkStatus'){
						value=getCheckStatus(value);
						innerHtml = innerHtml + "<tr style='height: 28px;'><td style='font-weight: bold;'>" + dataModel[key] + "</td><td>" + value + "</td></tr>";
					}else if(key=='pointId'){
						value=getCheckStatus(value);
						innerHtml = innerHtml + "<tr style='height: 28px;'><td style='font-weight: bold;'>" + dataModel[key] + "</td><td><tags:xiangxuncache keyName='AIRPOINT_ID_NAME' id='"+value+"'/></td></tr>";
					}else{
						value=valueStringHandle(value);
						innerHtml = innerHtml + "<tr style='height: 28px;'><td style='font-weight: bold;'>" + dataModel[key] + "</td><td>" + value + "</td></tr>";
					}
				}
			}
			
			var files=countObj.files;
			var _dom = MapFactory.Dom;
			if(files){
				innerHtml=innerHtml+"<tr><td style='font-weight: bold;'>现场素材</td><td>";
				for(var i=0;i<files.length;i++){
					var file=files[i];
					innerHtml=innerHtml+"<img  style='margin:2px;width:33px;height:56px;' src='"+path+"/"+file.filePath+"' onclick=showImgInfo('"+file.filePath+"')  onerror=loadImgError(this)></img>";
				}
				innerHtml=innerHtml+"</tr></td>";
				line=line+2;
			}
			lineNum=line;
			//innerHtml=innerHtml+"<tr><td><img src='${root}/"+countObj.files[0].filePath+"'/></td></tr>";
			innerHtml=innerHtml+"</table></div>";
			return innerHtml;
		}
		function valueStringHandle(value){
			return value ? value : "";
		}
		function getDataTimeFormat(time){
			//审查状态 0=未审查  1=通过 2=不通过
			if(!time){
				return "";
			}
			var unixTimestamp = new Date( time );
			var formatTime=DateFormat.format(unixTimestamp,"yyyy-MM-dd hh:mm:ss");
			return formatTime;
		}
		function getCheckStatus(status){
			//审查状态 0=未审查  1=通过 2=不通过
			if(status==0){
				return "未审查";
			}else if(status==1){
				return "通过";
			}else{
				return "不通过";
			}
		}
		
		/**
		 * 
		 *获取多边形中心点 
		 */
		function getPolognCenter(multipolygon){
			var graphicPologn={geo: multipolygon, symbol: SymbolConfig["regionPolluteLevel1"]};
			var centerPoint=GraphicManager(graphicPologn).getCenter();
			return  centerPoint;
		}

		function getClickGraphic(g) {
			//showInfowindow(new GeometryUtil(g.geo), "", "");
			var attributes=g.attributes;
			var countObj=attributes.countObj;

			showInfowindow(new GeometryUtil(MapFactory.JSON.Parse(countObj.center)),countObj,attributes.townName);
		}
		
		
		return eval(MapFactory.GenerateAPI(api));
	}
});