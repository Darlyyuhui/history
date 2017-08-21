/*
 *初始化参数的传入形式如下，如果没有传入参数，则启用默认配置参数
 *{		
 *	mapContainer : "",	
 *	//底图的服务路径
 *	baseMapUrl:"",	
 *	//路径服务地址
 *	snappingUrl : "",	
 *	//起点图片的路径
 *	startPicUrl : "",	
 *	//终点图片的路径
 *	endPicUrl : "",	
 *	//路口图层点符号路径
 *	picUrl : "",	
 *	//图片大小
 *	picHeight : 20,
 *	picWidth : 20,	
 *	//节点信息json数组,如[{"xPos":108.02,"yPos":34.1,"nodename":dfd,"nodeid":""}]
 *	nodes:""
 *}
 *需要jquery 1.6以上版本支持
 * @author by LHF
 * @date 2013-10-23
 */


/*
 * 导入所需要的类库
 * */
dojo.require("esri.map");
dojo.require("esri.layers.ArcGISTiledMapServiceLayer");
dojo.require("esri.layers.FeatureLayer");
dojo.require("esri.tasks.identify");
dojo.require("esri.layers.graphics");
dojo.require("esri.geometry.Point");
dojo.require("esri.symbols.PictureMarkerSymbol");

esriConfig.defaults.io.proxyUrl="proxy.jsp";

if(!window['esriMap']) {
	esriMap = {};
}
function createPkg(pkgs) {
	var levels = pkgs.split(".");
	var obj = esriMap;
	for(var i=1;i <levels.length; i++) {
		if(typeof obj[levels[i]] == "undefined") {
			obj[levels[i]] = new Object();
		}
		obj = obj[levels[i]];
	}
}

/*
 * 加载地图，设置起点和终点即可生成路段。
 * */
createPkg("esriMap.common");
esriMap.common.addMapPoint = (function(){
		
	/**
	 * 私有变量
	 * */
	
	//地图窗口
	var bMap,
	
	//用于显示添加到底图上的graphic等信息
	graphicsLayer,
	
	//是否可选取点
	selectable = false,
	
	//表达路段选取时，当前是选取起点还是终点
	startSelectable = false,
	endSelectable = false,
	
	//路段选取的起点和终点
	startGraphic,
	endGraphic,
	
	//路段
	route,
	
	//父页面的文档对象
	parentDoc = window.parent.document,
	
	//执行的操作类型
	type = "",
	
	//identify的配置参数
	identifyConfig,
		
	//获取点位置后的回调函数
	callback;
	
	
	
	/**
	 * 初始化地图默认参数
	 * */
		
	var options = {
		//地图容器的Id
		mapContainer : "mapDiv_signal",
		
		//底图的服务路径
		baseMapUrl:baseServiceURL.basemapnew.url,
		
		//是否捕捉，如果不捕捉，则下面的图层服务路径被忽略
		isSnapping:true,
		
		//是否添加路段
		isLinkAdded:false,
		
		//要进行捕捉的图层服务路径
		snappingUrl:baseServiceURL.snappingRoad.url,
		
		//路径服务路径
		routeServiceUrl:baseServiceURL.route.url,
				
		//路口图层点符号路径
		picUrl:path+"/images/lukoutupian.png",
		
		//图片大小
		picHeight:20,
		picWidth:20

	};
	
	/**
	 * 默认绑定的控件 
	 * */
	var bindElements = {
		viewLon:"#longitude",
		viewLat:"#latitude",
		addBtn:"#addPoint_btn",
		addLon:"#longitude",
		addLat:"#latitude",
		addStart:"#addStartPoint_btn",
		addEnd:"#addEndPoint_btn",
		coordStart:"#coordStart",
		coordEnd:"#coordEnd",
		addStop:"#addStop_btn",
		clearStop:"#clearStop_btn",
		prompt:"#prompt_add"
	}
	
	/**
	 * 开放接口
	 * */
	
	var api = {
		init:init,
		initOperation:initOperation,
		initIdentify:initIdentify,
		execOperation:execOperation
	}
	
	
	/**
	 * 功能定义
	 * */
	
	//初始化
	function init(){
		
		//底图
		var baseMap,
		
		//传入的配置参数
		config = arguments[0];
		
		//初始化参数
		copyParams(options,config);
		
		//初始化地图
		bMap = new esri.Map(options.mapContainer,{slider:false,minScale:75000,logo:false});
		baseMap = new esri.layers.ArcGISTiledMapServiceLayer(options.baseMapUrl);
		bMap.addLayer(baseMap);
		
		graphicsLayer = new esri.layers.GraphicsLayer();
		bMap.addLayer(graphicsLayer);
		
		//激活捕捉，用于获取鼠标点击处最近的点
		options.isSnapping ? activeSnapping():"";
		
		return api;		
	}
	
	//启用捕捉，snappingParams在函数内部定义，是为了延迟其初始化，否则snappingParams可能会因为
	//bMap等未定义而报错
	function activeSnapping(){
		var snappingParams = {
			map: bMap,
			alwaysSnap: true,
			tolerance: 10,
			layerInfos: [{layer:new esri.layers.FeatureLayer(options.snappingUrl)}]
		}
		
		bMap.enableSnapping(snappingParams);
	}
	
	/**
	 * 初始化操作类型
	 * 
	 * 目前的操作类型如下
	 *	view
	 *	add 
	 *	update
	 *
	 * 绑定的控件如下,控件名称以id来表示
	 * {
	 *   viewLon:"#longitude",
	 *   viewLat:"#latitude",
	 *   addBtn:"#addPoint_btn",
	 *   addLon:"#longitude",
	 *   addLat:"#latitude",
	 *   addStart:"#addStartPoint_btn",
	 *   addEnd:"#addEndPoint_btn",
	 *   coordStart:"#coordStart",
	 *   coordEnd:"#coordEnd",
	 *   prompt:""
	 * }
	 *
	 *传入参数的格式如下
	 * {
	 *  type:"",
	 *  bindElements:{
	 *   	viewLon:"#longitude",
	 *   	viewLat:"#latitude",
	 *   	addBtn:"#addPoint_btn",
	 *   	addLon:"#longitude",
	 *   	addLat:"#latitude",
	 *      addStart:"",
	 *      addEnd:"",
	 *      coordStart:"",
	 *      coordEnd:"",
	 *      prompt:"#prompt_add"
	 * 	}
	 *  
	 * } 
	 * 其中id换成自己的控件id，type换成需要进行的操作类型
	 * 默认情况下type为空
	 * */
		
	function initOperation(){

		//由传入的参数确定执行何种操作		
		var config = arguments[0];		
		
		//如果已传入绑定元素属性，则将其与默认属性比较，防止传入的属性不全
		if(config && typeof config === "object"){			
			copyParams(bindElements, config.bindElements);			
			type = config.type || type;
		}
		
		return api;
	}
	
	/**
	 * 初始化identify配置,参数格式如下
	 * {
	 *	identifyUrl:baseServiceURL.xianmap.url,
	 *	identifyParams:{
	 *				layerOption:esri.tasks.IdentifyParameters.LAYER_OPTION_ALL,
	 *				returnGeometry:true,
	 *				tolerance:1,
	 *				layerIds:[104]}
	 *  fieldName:"DLMC",
	 *  bindElement:"#connectionroads"
	 *  
	 * }
	 * */
	function initIdentify(){
		//由传入的参数确定如何初始化identify		
		var config = arguments[0];
		
		//默认的配置参数，如果将默认参数置顶会由于bMap等并未初始化而报错
		identifyConfig = {
				identifyUrl:baseServiceURL.xianmap.url,
				identifyParams:{layerOption:esri.tasks.IdentifyParameters.LAYER_OPTION_ALL,
								returnGeometry:true,
								tolerance:1,
								layerIds:[104]},
				fieldName:"DLMC",
				bindElement:"#connectionroads"
		};
				
		//如果已传入绑定元素属性，则将其与默认属性比较，防止传入的属性不全
		copyParams(identifyConfig, config);
		if(config && typeof config === "object"){		
			copyParams(identifyConfig.identifyParams, config.identifyParams);
		}
		
//		identifyUrl = identifyConfig.identifyUrl;
//		copyParams(identifyParams,identifyConfig.identifyParams);
		
		return api;
	}
	
	/**
	 * 执行操作
	 * */
	function execOperation(){
		//对获取的点再进行处理，否则在click事件中就需要添加if-else判断，每点击一次就需要判断一次
		if(identifyConfig){
			callback = chooseRoads;
		}else{
			callback = function(){};
		}
		
		//执行操作
		switch(type){
			case "view":
				view();
				break;
			case "add":
				add();
				break;
			case "update":
				view();
				add();
				break;
			default:
				break;
		}
	}
	
	/**
	 * "查看功能"需要执行的一系列操作
	 * 
	 * */
	function view(){
		
		var pointX,pointY,point,pointStart,pointEnd,startCoords,endCoords;
		
		if(!options.isLinkAdded){
			pointX = parseFloat(access(bindElements.viewLon));
			pointY = parseFloat(access(bindElements.viewLat));
			if(!isNaN(pointX) && !isNaN(pointY)){
				point = new esri.geometry.Point(pointX,pointY);
				graphicsLayer.add(roadCrossLabel(point));
				bMap.centerAt(point);
			}
		}else{
			//从input text中获取的坐标形式为“x;y”
			startCoords = access(bindElements.coordStart).split(";");
			endCoords = access(bindElements.coordEnd).split(";");
			pointStart = new esri.geometry.Point(parseFloat(startCoords[0]), parseFloat(startCoords[1]));
			pointEnd = new esri.geometry.Point(parseFloat(endCoords[0]), parseFloat(endCoords[1]));
			startGraphic = roadCrossLabel(pointStart);
			endGraphic = roadCrossLabel(pointEnd);			
			graphicsLayer.add(startGraphic);
			graphicsLayer.add(endGraphic);
			getRoute(startGraphic,endGraphic);
		}
	}
			
	/**
	 * 添加功能需要进行的一系列操作
	 * 根据传入的参数确定是进行点添加还是路段添加操作
	 * */
	function add(){
		
		if(!options.isLinkAdded){
			//给父页面中的按钮注册click事件，用于选取路口
			$(bindElements.addBtn,parentDoc).click(function(){			
				//选取路口，在地图窗口中目标点处单击即可选取路口
				selectable = true;
				//绑定地图的单击事件
				dojo.connect(bMap,"onClick",addPoint);
			});
		}else{
			
			//给父页面中的按钮注册click事件，用于选取起点
			$(bindElements.addStart,parentDoc).click(function(){			
				startSelectable = true;
				endSelectable = false;
				stopsSelectable = false;
				stopsClearable = false;
			});
			
			//给父页面中的按钮注册click事件，用于选取终点
			$(bindElements.addEnd,parentDoc).click(function(){			
				endSelectable = true;
				startSelectable = false;
				stopsSelectable = false;
				stopsClearable = false;
			});
			
			dojo.connect(bMap,"onClick",addLinks);
		}
	}
		
	//鼠标事件，用来添加点
	function addPoint(e){		
		var deferred,tempObj;		
		if(parentDoc && selectable){			
			graphicsLayer.clear();
			if(options.isSnapping){
				deferred = bMap.snappingManager.getSnappingPoint(e.screenPoint);
				deferred.then(function(value){

				  if(typeof value !== "undefined"){
					  graphicsLayer.add(roadCrossLabel(value));
			          access(bindElements.addLon, value.x);
			          access(bindElements.addLat,value.y);
			          access(bindElements.prompt,"");
			          callback(value,bMap.extent,identifyConfig.identifyUrl,identifyConfig.identifyParams);
				  }else{
					  access(bindElements.addLon, "");
			          access(bindElements.addLat, "");
					  access(bindElements.prompt,"此处离路口还很远哦，请重新选取路口");
					  access(identifyConfig.bindElement,"");
				  }
				});
			}else{
				graphicsLayer.add(roadCrossLabel(e.mapPoint));
				$(bindElements.addLon, parentDoc).attr("value",e.mapPoint.x);
		        $(bindElements.addLat, parentDoc).attr("value",e.mapPoint.y);
			}			
			selectable = false;
		}
	}
	
	//鼠标事件，用来添加路段
	function addLinks(e){		
		var deferred,pointString;		
		if(parentDoc){			
			//选取起点并显示，同时将其坐标传入父页面
			if(startSelectable && options.isSnapping){				
				graphicsLayer.remove(startGraphic);
				deferred = bMap.snappingManager.getSnappingPoint(e.screenPoint);				
				deferred.then(function(value){
					 if(typeof value !== "undefined"){
						 
						// 将坐标对以“x;y”的形式显示
						 pointString = value.x + ";" + value.y;
						 access(bindElements.coordStart,pointString);
						 
						 startGraphic = roadCrossLabel(value);;
						 graphicsLayer.add(startGraphic);
						 if(endGraphic){
							 getRoute(startGraphic,endGraphic);
						 }
					 }					
				});								
				startSelectable = false;				
			//选取终点并显示，同时将其坐标传入父页面
			}else if(endSelectable){
				graphicsLayer.remove(endGraphic);
				deferred = bMap.snappingManager.getSnappingPoint(e.screenPoint);
				deferred.then(function(value){
					 if(typeof value !== "undefined"){
						 
						 //将坐标对以“x;y”的形式显示
						 pointString = value.x + ";" + value.y;						 
						 access(bindElements.coordEnd, pointString);
						 
						 endGraphic = graphicsLayer.add(roadCrossLabel(value));
						 graphicsLayer.add(endGraphic);
						 if(startGraphic){
							 getRoute(startGraphic,endGraphic);
						 }
					 }					
				});
				endSelectable = false;
			}
		}
	}
	
	
	/**
	 * 公共操作 
	 * */
	//对当前选取的位置标注，生成一个没有属性的graphic。注意：如果graphic作为路径运算的stops，则需要使它的属性在对应的路径图层中存在
	//否则应将其属性置空，否则可能会出错
	function roadCrossLabel(point){		
		var labelPoint,graphic,picSymbol;
		picSymbol = new esri.symbol.PictureMarkerSymbol(options.picUrl,options.picWidth,options.picHeight);
		graphic = new esri.Graphic(point,picSymbol,null,null);
		return graphic;
	}
	
	//选取经过当前路口的道路,由于在identify参数的初始化过程中bMap未初始化完成，所以在此传入extet参数
	function chooseRoads(point,mapExent,url,identifyParams){
		
		//存储遍历过程中数据集的道路名称
		var tempRoadName,
		
		//存储不重复的道路集名称,道路名称间以；隔开
		roadNames = "",
		
		//正则表达式
		reg,
		
		params = new esri.tasks.IdentifyParameters(),
		identifyTask = new esri.tasks.IdentifyTask(url);
		
		copyParams(params,identifyParams);
		params.geometry = point;
		params.mapExtent = mapExent;
		
		identifyTask.execute(params,function(results){
			if(!results || results.length === 0) return;
			for(var i = 0;i < results.length;i++){								
				tempRoadName = results[i].feature.attributes[identifyConfig.fieldName];
				if(!tempRoadName || tempRoadName.length == 0)continue;
				
				reg = new RegExp(tempRoadName);
				if(!reg.test(roadNames)){
					roadNames = roadNames + tempRoadName + ";";
				}
			}
			
			//去掉最后一个";"
			roadNames = roadNames.substring(0, roadNames.length-1);
			
			//将处理后的数组以;作为间隔符输出
			access(identifyConfig.bindElement,roadNames);
			
		},function(error){
			$(bindElements.prompt,parentDoc).attr("value",error.details);
		});		
	}
	
	//路径分析
	function getRoute(graphicStart,graphicEnd,stops){
		var routeTask,routeParams;
		
		routeTask = new esri.tasks.RouteTask(options.routeServiceUrl);
		routeParams = new esri.tasks.RouteParameters();
		routeParams.stops = new esri.tasks.FeatureSet();

		routeParams.stops.features.push(graphicStart);
		if(stops){
			routeParams.stops.features.push(stops);
		}		
		routeParams.stops.features.push(graphicEnd);		
		routeParams.outSpatialReference = {"wkid":4326};
		routeTask.solve(routeParams, showRoute, showError);		
	}
	
	function showRoute(solveResults){
		dojo.forEach(solveResults.routeResults, function(routeResult, i) {
			graphicsLayer.remove(route);
			routeResult.route.setSymbol(new esri.symbol.SimpleLineSymbol().setColor(new dojo.Color([0,0,255,0.5])).setWidth(5));
			route = routeResult.route;
			graphicsLayer.add(route);
			bMap.centerAt(route.geometry.getExtent().getCenter());
//			$("#length",parentDoc).attr("value",Math.floor(route.attributes["Total_长度"]));

		});
	}
	
	function showError(e){
		access(bindElements.prompt, e.details);
	}
	
	/**
	 * 辅助工具
	 * */
	
	//处理传入的参数，如果传入的参数某一项存在，则以它的值作为参数进行初始化，否则以默认参数初始化
	function copyParams(defaultParams,inputParams,exception){
		
		if(typeof inputParams !== "object" || inputParams === null || isEmptyObject(inputParams))return;
		//判断是否相等用"!==", false == ""会得到true值
		for(key in defaultParams){
			if(typeof inputParams[key] !== "undefined" && inputParams[key] !== "" && key !== exception){
				defaultParams[key] = inputParams[key];
			}
		}
	}
	
	//验证是否是空对象
	function isEmptyObject(obj){
		for(var i in obj){
			return false;
		}		
		return true;
	}
	
	//定义一个数据存储函数，根据传入的参数情况来设置或获取元素的值
	function access(id,value){
//		var obj = arguments[0],value = arguments[1];
//		
//		//如果arguments[0]不存在或者不是Object对象，则终止		
//		if(!obj) return;
//		
//		//如果arguments[0]是对象，则根据对象的属性名和属性值给元素赋值
//		//属性名对应与元素的id，属性值对应某个元素的属性节点值或文本节点值
//		if(typeof obj === "object"){
//			for(var index in obj){
//				if(obj[index]){
//					$(index,parentDoc).attr("value",obj[index]) || $(index,parentDoc).text(obj[index]);
//				}
//			}			
//			return;
//		}
//		//如果arguments[0]是字符串，则作为元素的id，以arguments[1]为元素赋值
//		//如果arguments[1]不存在，则取出id为arguments[0]的元素的属性值或文本节点值
//		if(typeof obj === "string"){
//			if(value){
//				$(obj,parentDoc).attr("value",value) || $(obj,parentDoc).text(value);
//			}else{
//				return $(index,parentDoc).attr("value") || $(index,parentDoc).text();
//			}
//		}
		var $inputNode = $(id, parentDoc);
		if(id && $inputNode && $inputNode.length > 0){
			if(value || value === ""){				
				if ($inputNode[0].nodeName.toLowerCase() === "input") {
					$inputNode.attr("value", value);
				} else if ($inputNode[0].nodeName.toLowerCase() === "td") {
					$inputNode.text(value);
				}
//				$(id,parentDoc).attr("value",value) || $(id,parentDoc).text(value);
			}else{
				return $(id,parentDoc).attr("value") || $(id,parentDoc).text();
			}
		}		
	}
	
	return api;
})();