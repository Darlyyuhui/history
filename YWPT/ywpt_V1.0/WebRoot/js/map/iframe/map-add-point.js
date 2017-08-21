
dojo.require("esri.map");
dojo.require("esri.layers.ArcGISTiledMapServiceLayer");
dojo.require("esri.layers.FeatureLayer");
dojo.require("esri.tasks.identify");
dojo.require("esri.layers.graphics");
dojo.require("esri.geometry.Point");
dojo.require("esri.symbols.PictureMarkerSymbol");

var addMapPoint = (function(){
	
	var //地图窗口
		bMap,
	
		//用于显示添加到底图上的graphic等信息
		graphicsLayer,
		
		//是否可选取点，布尔值，也可以是能返回布尔值的回调函数，
		//默认是不可以选取点
		selectable = false,
		
		//缓存父页面的文档对象
		parentDoc = parent.document,
		
		//获取点成功或失败后的回调函数
		successCallback,
		failCallback,
		queryCallback,
		
		//查询的url和字段
		queryUrl,
		fieldName,
		layerIds,
		
		//执行的操作类型
		type = "",
		
		//是否启用捕捉
		isSnapping = false,
		
		//获取点成功后是否执行查询
		isQuery = false,
		
		//是否注册点击事件
		clickable = false,

		//地图初始化的默认参数	
		options = {
			//地图容器的Id
			mapContainer : "mapDiv_signal",
			
			//底图的服务路径
			baseMapUrl: baseServiceURL.basemapnew.url,
			
			snappingUrl: baseServiceURL.snappingRoad.url,
								
			//路口图层点符号路径
			picUrl: path+"/images/lukoutupian.png",
			
			//图片大小
			picHeight: 20,
			picWidth: 20

		};
	
	//对外开放的接口
	var api = {
		initMap: initMap,
		startSnapping: startSnapping,
		stopSnapping: stopSnapping,
		startClick: startClick,
		stopClick: stopClick,
		view: view,
		executeQuery: executeQuery
	};
	
	//初始化地图
	function initMap() {
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
		
		return this;
	}
		
	//启用捕捉
	function startSnapping(success, fail) {
		
		var snappingParams = {
			map: bMap,
			alwaysSnap: true,
			tolerance: 10,
			layerInfos: [{layer:new esri.layers.FeatureLayer(options.snappingUrl)}]
		}

		bMap.enableSnapping(snappingParams);
		if (!isSnapping) {
			dojo.connect(bMap, "onClick", afterClick);
			isSnapping = true;
		}
		
		successCallback = success;
		failCallback = fail;
		
		return this;
	}
	
	//关闭捕捉
	function stopSnapping() {
		bMap.disableSnapping();
		
		if (isSnapping) {
			dojo.disConnect(bMap, "onClick", afterClick);
			isSnapping = false;
		}
	}
	
	//注册地图点击事件
	function startClick(success, fail) {
		var point;
		
		if (!clickable) {
			
			//注册地图单击事件
			dojo.connect(bMap, "onClick", afterClick);			
			clickable = true;
		}
		
		successCallback = success;
		failCallback = fail;
		
		return this;
	}
	
	//取消地图点击事件
	function stopClick() {
		if (clickable) {
			dojo.disConnect(bMap, "onClick", afterClick);
		}
		
		return this;
	}
	
	//传入参数格式为{x:"",y:""}或者[x,y] 或者[[x,y],[x,y],[x,y]] 或者[{x:"",y:""},{x:"",y:""}....]
	function view(json) {
		var point,
			toString = Object.prototype.toString;
		if(json == null) return;
		
		//同时显示多个点
		if (toString.call(json) === "[object Array]" && typeof json[0] === "object" ) {
			for(var i = 0; i < json.length; i = i + 1) {
				point = new esri.geometry.Point(json[i]);
				//地图上添加点
				graphicsLayer.add(addPoint(point));
			} 
		} else {
			point = new esri.geometry.Point(json);
			//地图上添加点
			graphicsLayer.add(addPoint(point));
			bMap.centerAt(point);
		}
		
		return this;
	}
	
	//地图点后的处理事件
	function afterClick(e) {
		isSnapping ? getSnappingPoint(e) : getClickPoint(e);
	}
		
	function getSnappingPoint(e) {
		
		var //遍历变量
			i = 0,
			//返回的延迟对象
			deferred = bMap.snappingManager.getSnappingPoint(e.screenPoint);

		deferred.then(function(value){
			if(typeof value !== "undefined") {
				successCallback(value);
				graphicsLayer.clear();
				isQuery ? queryLine(value) : "";
				//处理生成的点
				graphicsLayer.add(addPoint(value));
				bMap.centerAt(value);	
				
			} else {
				//查询失败处理函数
				failCallback();
			}
		});
		
		return this;
			
	}
	
	function getClickPoint(e) {
		var i = 0;

		successCallback(e.mapPoint);
		isQuery ? queryLine(e.mapPoint) : "";
		graphicsLayer.clear();
		//处理生成的点
		graphicsLayer.add(addPoint(e.mapPoint));
		bMap.centerAt(e.mapPoint);
	}
	
	function executeQuery(url, name, layers, func) {
		isQuery = true;
		queryCallback = func;
		fieldName = name;
		queryUrl = url;
		layerIds = layers;
		
	}
	//以点查线
	function queryLine(geoPoint) {
		
		var //存储遍历过程中数据集的道路名称
			tempRoadName,		
			//存储不重复的道路集名称,道路名称间以；隔开
			roadNames = "",		
			//正则表达式
			reg,		
			params = new esri.tasks.IdentifyParameters(),
			identifyTask = new esri.tasks.IdentifyTask(queryUrl);
		
		params.geometry = geoPoint;
		params.mapExtent = bMap.extent;
		params.layerOption = esri.tasks.IdentifyParameters.LAYER_OPTION_ALL;
		params.returnGeometry = true;
		params.tolerance = 1;
		params.layerIds = layerIds;
		
		identifyTask.execute(params,function(results){
			if(!results || results.length === 0) return;
			for(var i = 0;i < results.length; i = i + 1){								
				tempRoadName = results[i].feature.attributes[fieldName];
				if(!tempRoadName || tempRoadName.length == 0) continue;
				
				reg = new RegExp(tempRoadName);
				if(!reg.test(roadNames)){
					roadNames = roadNames + tempRoadName + ";";
				}
			}
			
			//去掉最后一个";"
			roadNames = roadNames.substring(0, roadNames.length - 1);
			queryCallback(roadNames);
		});
        
        return this;
	}
	
	/**
	 * 工具
	 * */
	
	//对当前选取的位置标注，生成一个没有属性的graphic。注意：如果graphic作为路径运算的stops，需要使它的属性在对应的路径图层中存在
	//否则应将其属性置空，防止出错
	function addPoint(point){
		
		var labelPoint,
			graphic,
			picSymbol;
		
		picSymbol = new esri.symbol.PictureMarkerSymbol(options.picUrl, options.picWidth, options.picHeight);
		graphic = new esri.Graphic(point, picSymbol, null, null);
		
		return graphic;
	}
	
	//验证是否是空对象
	function isEmptyObject(obj){
		for(var i in obj){
			return false;
		}		
		return true;
	}
	
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
	
	
	return api;
})()