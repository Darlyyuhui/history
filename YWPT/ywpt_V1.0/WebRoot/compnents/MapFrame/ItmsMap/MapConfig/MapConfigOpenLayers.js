MapFactory.Define("ItmsMap/MapConfig",
	["MapFactory/MapConfigAPI*"],
	function(api){
		var	serviceUrl = geoserverbaseurl+"/gwc/service/wms",
			baseMapUrl = "xiangxun:baseMap",
			initExtent = maxExtent,
			resolutions = [],
			spatialReference = 4326,
			projectSpatialReference = 32649,
			referenceCSS = [];
		if(geoserverResolutions)resolutions = geoserverResolutions;
		var layers = {
			baseMap : {url:baseMapUrl,name:"底图",id:"baseMap",allowOperation:false},
			geometryService : {url:"",name:"几何服务",id:"geometryService"},
			serviceAreaRest : {url:"",name:"服务区服务",id:"serviceAreaRest"},
			positionLayer : {name:"定位图层",id:"positionLayer",isTop:true},
			positionHighLightLayer : {name:"定位高亮图层",id:"positionHighLightLayer",isTop:true},
			route : {name:"路径分析地址",url: geoserverbaseurl+"/ows",id:"route"},
			closedpoint : {name:"投影点查询",url:"openmap/query/closedpoint/",id:"closedpoint"},
			buffer : {name:"缓冲区分析地址",url: geoserverbaseurl+"/ows",id:"buffer"},
			union : {name:"几何体合并服务",url:"openmap/query/union/",id:"union"},
			queryResult : {name:"查询结果",id:"queryResult"},
			position : {name:"位置图层",id:"position"},
			unStart : {name:"未开工图层",id:"occupyUnstart"},
			constructing : {name:"施工中图层",id:"occupyConstruct"},
			maturring : {name:"快到期图层",id:"occupyMaturring"},
			defferring : {name:"已延期图层",id:"occupyDeffering"},
			done : {name:"已完工图层",id:"occupyDone"},
			matured: {name:"已超期图层",id:"occupyMatured"},
			baseRoad : {name:"道路图层",url:"openmap/query/q/ROAD_LINE/",id:"baseRoad"},
			cross : {name:"卡口图层",url:"openmap/query/q/cross/",id:"cross"},
			crossAnalyLayer : {name:"卡口分析",id:"crossAnalyLayer"},
			crossAnalyBarriersLayer : {name:"卡口障碍物",id:"crossAnalyBarriersLayer"},
			crossAlarm : {name:"卡口报警",url:"openmap/query/q/cross/",id:"crossAlarm"},
			flow : {name:"流量图层",url:"openmap/query/q/flow_road_polygon/",id:"flow"},
			flowDevice : {name:"流量检测器",url:"openmap/query/q/flow_device/",id:"flowDevice"},
			flowPanel : {name:"流量诱导屏",url:"openmap/query/q/flow_panel/",id:"flowPanel"},
			gpsextent : {name:"GPS电子围栏",url:"openmap/query/q/gps_extent/",id:"gpsextent"},
			occupyPolygon : {name:"挖占面",url:"openmap/query/q/occupy_polygon/",id:"occupyPolygon"},
			signal : {name:"信号机",url:"openmap/query/q/signal/",id:"signal"},
			educationPOI : {name:"教育机构",url:"openmap/query/q/EDUCATION/",id:"educationPOI"},
			orgPOI : {name:"行政区面",url:"openmap/query/q/ORG/",id:"orgPOI"},
			townsPOI : {name:"乡镇点",url:"openmap/query/q/TOWNSHIP_GOV/",id:"townsPOI"},
			governmentPOI : {name:"行政单位",url:"openmap/query/q/GOVERNMENT/",id:"governmentPOI"},
			enterprisePOI : {name:"企事业单位",url:"openmap/query/q/ENTERPRISE/",id:"enterprisePOI"},
			publicPOI : {name:"公共场所",url:"openmap/query/q/PUBLIC_PLACES/",id:"publicPOI"},
			hospitalPOI : {name:"医疗卫生",url:"openmap/query/q/MEDICAL_PLACE/",id:"hospitalPOI"},
			tourismPOI : {name:"旅游景点",url:"openmap/query/q/SCENIC_SPOT/",id:"tourismPOI"},
			marketPOI : {name:"超市广场",url:"openmap/query/q/SUPERMARKET/",id:"marketPOI"},
			petrolFillPOI : {name:"加油站",url:"openmap/query/q/OIL_STATION/",id:"petrolFillPOI"},
			mapSigns : {name:"标绘图层",id:"mapSigns"},
			omPoint : {name:"勤务点服务",url:"openmap/query/q/OM_POINT/",id:"omPoint"},
			omPolyline : {name:"勤务线服务",url:"openmap/query/q/OM_POLYLINE/",id:"omPolyline"},
			omPolygon : {name:"勤务面服务",url:"openmap/query/q/OM_POLYGON/",id:"omPolygon"},
			omStationLayer : {name:"岗位层",id:"mapOMStationLayer"},
			omStationExtentsLayer : {name:"巡查面",id:"mapOMStationExtentsLayer"},
			omUnorderedLayer : {name:"未排班",id:"omUnorderedLayer"},
			omPoliceLayer : {name:"警员",id:"mapOMPoliceLayer"},
			tempPlayerLayer : {name:"轨迹回放车辆",id:"tempPlayerLayer"},
			crossHistoryLayer : {name:"轨迹回放路径",id:"crossHistoryLayer"},
			gps : {name:"GPS图层",id:"gps"},
			gpsHistoryLayer : {name:"GPS历史轨迹",id:"gpsHistoryLayer"},
			videoLayer : {name:"视频设备",url:"openmap/query/q/video/",id:"videoLayer"},
			vioLayer : {name:"违法设备",id:"vioLayer"},
			dirParking : {name:"直属停车场",id:"dirParking"},
			genParking : {name:"停车场",id:"genParking"},
			leadDevice : {name:"停车场诱导屏",id:"leadDevice"},
			preplanPoint : {name:"预案点",url:"openmap/query/q/PREPLAN_POINT/",id:"preplanPoint"},
			preplanPolyline : {name:"预案线",url:"openmap/query/q/PREPLAN_POINT/",id:"preplanPolyline"},
			preplanPolygon : {name:"预案面",url:"openmap/query/q/PREPLAN_POINT/",id:"preplanPolygon"},
			traffic_point : {name:"交通设施点",url:"openmap/query/q/trafficfacilities_point/",id:"traffic_pointlayer"},
			traffic_polygon : {name:"交通设施面",url:"openmap/query/q/trafficfacilities_polyline/",id:"traffic_polygonlayer"},
			bindedPreplanLayer : {name:"已绑定",id:"bindedPreplanLayer"},
			passLayer: {name: "通行证图层", id: "passportresult"},
			threeDimension: {name: "三维标注图层", id: "Label3D"},
			roadMaintenance : {name:"道路维护",id:"roadMaintenance"}
		}
		return eval(MapFactory.GenerateAPI(api));
	}
);