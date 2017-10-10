MapFactory.Define("ItmsMap/MapConfig",[
	"MapFactory/MapConfigAPI*"
],function(api){
	var serviceUrl = supermapbaseurl+"/services/",
		maxExtent = _maxExtent,
        initExtent = _maxExtent,
		resolutions = [0.0011897305029151398,5.948652514575699E-4,2.3794610058302794E-4,1.1897305029151397E-4,5.9486525145756985E-5,3.569191508745419E-5,1.6656227040811957E-5],
		spatialReference = 4326,
		projectSpatialReference = 32649,
		referenceCSS = [
			"/ThirdParty/SuperMapLib/theme/default/style.css",
			"/ThirdParty/SuperMapLib/theme/default/google.css"
		];
	var layers = {
		baseMap : {name:"底图",url:serviceUrl+"map-MianZhuShi/rest/maps/绵竹市",id:"baseMap",allowOperation:false,isBaseMap:true,isInit:true},
        imageMap : {name:"影像图",url:serviceUrl+"map-MianZhuShi2/rest/maps/绵竹市",id:"imageMap",allowOperation:false,isBaseMap:true,isInit:true},
		route : {name:"路径分析地址",url: serviceUrl+"transportationAnalyst-dmms/rest/networkanalyst/BuildNetwork@road",id:"route",isBaseMap:false,isInit:false},
		land: {name:"", url:path+"/openmap/query/q/land/",id:"land",isBaseMap:false,isInit:false},
		geometryService : {url:"",name:"几何服务",id:"geometryService"},
		serviceAreaRest : {url:"",name:"服务区服务",id:"serviceAreaRest"},
		positionLayer : {name:"定位图层",id:"positionLayer",isTop:true},
		positionHighLightLayer : {name:"定位高亮图层",id:"positionHighLightLayer",isTop:true},
		route : {name:"路径分析地址",url: "",id:"route"},
		closedpoint : {name:"投影点查询",url:serviceUrl+"transportationAnalyst-dmms/rest/networkanalyst/BuildNetwork@road",id:"closedpoint",isBaseMap:false,isInit:false},
		buffer : {name:"缓冲区分析地址",url:serviceUrl+"spatialAnalysis-dmms/restjsr/spatialanalyst",id:"buffer",isBaseMap:false,isInit:false},
		union : {name:"几何体合并服务",url:"",id:"union"},
		queryResult : {name:"查询结果",id:"queryResult"},
		position : {name:"位置图层",id:"position"},
		unStart : {name:"未开工图层",id:"occupyUnstart"},
		constructing : {name:"施工中图层",id:"occupyConstruct"},
		maturring : {name:"快到期图层",id:"occupyMaturring"},
		defferring : {name:"已延期图层",id:"occupyDeffering"},
		done : {name:"已完工图层",id:"occupyDone"},
		matured: {name:"已超期图层",id:"occup" +
				"yMatured"},
		baseRoad : {name:"道路图层",url:serviceUrl+"map-dmms-roadline/rest/maps/roadline@dmms-roadline/roadline@dmms-roadline",id:"baseRoad",isBaseMap:false,isInit:false},
		baseRoadEdit : {name:"道路编辑",url:serviceUrl+"data-dmms-roadline/rest/data/datasources/dmms-roadline/datasets/roadline",id:"baseRoadEdit",isBaseMap:false,isInit:false},
		cross : {name:"卡口图层",url:"",id:"cross"},
		crossAnalyLayer : {name:"卡口分析",id:"crossAnalyLayer"},
		crossAnalyBarriersLayer : {name:"卡口障碍物",id:"crossAnalyBarriersLayer"},
		crossAlarm : {name:"卡口报警",url:"",id:"crossAlarm"},
		flow : {name:"流量图层",url:"",id:"flow"},
		flowDevice : {name:"流量检测器",url:"",id:"flowDevice"},
		flowPanel : {name:"流量诱导屏",url:"",id:"flowPanel"},
		gpsextent : {name:"GPS电子围栏",url:"",id:"gpsextent"},
		occupyPolygon : {name:"挖占面",url:"",id:"occupyPolygon"},
		signal : {name:"信号机",url:"",id:"signal"},
		educationPOI : {name:"教育机构",url:"",id:"educationPOI"},
		orgPOI : {name:"行政区面",url:"",id:"orgPOI"},
		townsPOI : {name:"乡镇点",url:"",id:"townsPOI"},
		governmentPOI : {name:"行政单位",url:"",id:"governmentPOI"},
		enterprisePOI : {name:"企事业单位",url:"",id:"enterprisePOI"},
		publicPOI : {name:"公共场所",url:"",id:"publicPOI"},
		hospitalPOI : {name:"医疗卫生",url:"",id:"hospitalPOI"},
		tourismPOI : {name:"旅游景点",url:"",id:"tourismPOI"},
		marketPOI : {name:"超市广场",url:"",id:"marketPOI"},
		petrolFillPOI : {name:"加油站",url:"",id:"petrolFillPOI"},
		mapSigns : {name:"标绘图层",id:"mapSigns"},
		omPoint : {name:"勤务点服务",url:"",id:"omPoint"},
		omPolyline : {name:"勤务线服务",url:"",id:"omPolyline"},
		omPolygon : {name:"勤务面服务",url:"",id:"omPolygon"},
		omStationLayer : {name:"岗位层",id:"mapOMStationLayer"},
		omStationExtentsLayer : {name:"巡查面",id:"mapOMStationExtentsLayer"},
		omUnorderedLayer : {name:"未排班",id:"omUnorderedLayer"},
		omPoliceLayer : {name:"警员",id:"mapOMPoliceLayer"},
		tempPlayerLayer : {name:"轨迹回放车辆",id:"tempPlayerLayer"},
		crossHistoryLayer : {name:"轨迹回放路径",id:"crossHistoryLayer"},
		gps : {name:"GPS图层",id:"gps"},
		gpsHistoryLayer : {name:"GPS历史轨迹",id:"gpsHistoryLayer"},
		videoLayer : {name:"视频设备",id:"videoLayer"},
		vioLayer : {name:"违法设备",id:"vioLayer"},
		dirParking : {name:"直属停车场",id:"dirParking"},
		genParking : {name:"停车场",id:"genParking"},
		leadDevice : {name:"停车场诱导屏",id:"leadDevice"},
		preplanPoint : {name:"预案点",url:"",id:"preplanPoint"},
		preplanPolyline : {name:"预案线",url:"",id:"preplanPolyline"},
		preplanPolygon : {name:"预案面",url:"",id:"preplanPolygon"},
		traffic_point : {name:"交通设施点",url:"",id:"traffic_pointlayer"},
		traffic_polygon : {name:"交通设施面",url:"",id:"traffic_polygonlayer"},
		bindedPreplanLayer : {name:"已绑定",id:"bindedPreplanLayer"},
		passLayer: {name: "通行证图层", id: "passportresult"},
		threeDimension: {name: "三维标注图层", id: "Label3D"},
		roadMaintenance : {name:"道路维护",id:"roadMaintenance"},
		naturePolygon : {name:"乡镇面",url:serviceUrl+"map-MianZhuShi/rest/maps/乡镇@绵竹市/乡镇@绵竹市",id:"naturePolygon",isBaseMap:false,isInit:false},
		//小心地址中不能存在空格
		xzqyLyrPologn: {name: "行政区域块", id: "xzqyLyrPologn"},
		
		lcpjdLyrPoint: {name: "农产品基地", id: "lcpjdLyrPoint"},
		
		pccydLayerPoint: {name: "普查采样点位", id: "pccydLayerPoint"},
		xzqyLyrPoint: {name: "行政区域采样点统计", id: "xzqyLyrPoint"},
		
		ydjkLayerPoint: {name: "样地监控", id: "ydjkLayerPoint"},
		
		landLyrPologn: {name: "地块图层", id: "landLyrPologn"},
		landLyrPoint: {name: "地块图层详细信息", id: "landLyrPoint"},
		tempLandLyrPologn: {name: "临时地块绘制", id: "tempLandLyrPologn"},
		
		wrcpLyr: {name: "污染源企业", id: "wrcpLyr"},
		wrcpLyr: {name: "要素分析", id: "ysfxLyr"},
		hightLightLyr: {name: "高亮图层", id: "hightLightLyr"},
		riverLyr:{name:"水系示意图",url:serviceUrl+"map-MianZhuShi/rest/maps/水系@绵竹市",id:"riverLyr",isBaseMap:false,isInit:true,visibility:false}
	};

	MapFactory.CreateScriptNode(MapFactory.FramePath + "/ThirdParty/SuperMapLib/Lang/zh-CN.js",{},function(){});

	return eval(MapFactory.GenerateAPI(api));
});