/**
 * 此模块为双地图的模块，因无法加载到地图框架中，故单独写成js文件共使用调用
 * 
 * 使用方法
 * var addGeo='{"type":"multipolygon","points":"104.11493583976,31.379404165805,104.11261846705,31.369718736275,104.13216758555,31.364727471977,104.13442553845,31.380176623375,104.13365308088,31.380414302627,104.11493583976,31.379404165805"}';
	var addJson = eval("(" + addGeo + ")");
    var mapConfig=Object();
    mapConfig.url="http://10.10.15.201:8090/iserver/services/map-MianZhuShi/rest/maps/绵竹市";
    mapConfig.dom1="map2";
    mapConfig.dom2="map3";
	mapConfig.mapName="baseMap";
	mapConfig.geometry=addJson.points;
	mapConfig.over1Ratio=1;
	mapConfig.over2Ratio=2;
    init(mapConfig);
 * 
 * 
 */
var isLoad = true;
var maps = [];
var map;
var vectorLayer;
var map1 = new Object();
var map2 = new Object();
function init(mapConfig) {
	map1.dom = mapConfig.dom1;
	map1.url = mapConfig.url;
	map1.mapName = mapConfig.mapName;
	map1.geometry = mapConfig.geometry;
	map1.overRatio = mapConfig.over1Ratio;
	map2.dom = mapConfig.dom2;
	map2.url = mapConfig.url;
	map2.mapName = mapConfig.mapName;
	map2.geometry = mapConfig.geometry;
	map2.overRatio = mapConfig.over2Ratio;
	initMap(map1);
};
function initMap(mapConfig) {
	var url = mapConfig.url;
	map = new SuperMap.Map(mapConfig.dom, {
		controls : [ new SuperMap.Control.Navigation(), ]
	});
	//初始化图层
	layer = new SuperMap.Layer.TiledDynamicRESTLayer(mapConfig.mapName, url, {
		transparent : true,
	}, {
		maxResolution : "auto",
	});
	var style = resultColorStyle(mapConfig.overRatio);
	var geometrys=mapConfig.geometry.split("#");
	var pointFeature;
	vectorLayer = new SuperMap.Layer.Vector("Vector Layer");
	for(var i=0;i<geometrys.length;i++){
		(function(geometry,style){
			var multiGraphic = _convertToMultiPolygon(geometry);
			var pointFeature = new SuperMap.Feature.Vector(multiGraphic, null, style);
			vectorLayer.addFeatures(pointFeature);
		})(geometrys[i],style)
	}	
	//监听图层信息加载完成事件
	layer.events.on({
		"layerInitialized" : addLayer
	});
}

function _convertToMultiPolygon(points) {
	var pointArr = points.split("|"), geoArr = [];
	for (var i = 0, len = pointArr.length; i < len; i++) {
		geoArr.push(_convertToPolygon(pointArr[i]));
	}
	return new SuperMap.Geometry.MultiPolygon(geoArr);
}
function _convertToPolygon(points) {
	return new SuperMap.Geometry.Polygon([ new SuperMap.Geometry.LinearRing(
			_convertPointStrToArray(points)) ]);
}
function _convertPointStrToArray(points) {
	var pointArr = points.split(","), arrLen = pointArr.length, tmpArr = [];
	for (var i = 0; i < arrLen; i += 2) {
		tmpArr.push(new SuperMap.Geometry.Point(parseFloat(pointArr[i]),
				parseFloat(pointArr[i + 1])));
	}
	return tmpArr;
};

//异步加载图层
function addLayer() {
	map.addLayer(layer);
	map.addLayer(vectorLayer);
	//显示地图范围
	map.setCenter(new SuperMap.LonLat(104.12445, 31.42520), 1);
	map.maxExtent = {
		minX : 103.902907838671,
		minY : 31.1530368831954,
		maxX : 104.344413677999,
		maxY : 31.7032817292694
	};
	map.minExtent = {
		minX : 103.902907838671,
		minY : 31.1530368831954,
		maxX : 104.344413677999,
		maxY : 31.7032817292694
	};
	maps.push(map);
	if (isLoad) {
		maps[0].events.register("moveend", null, map_mousemove2);
		isLoad = false;
		initMap(map2);
	} else {
		maps[1].events.register("moveend", null, map_mousemove);
		return;
	}

};

function map_mousemove(evt) {
	maps[0].panTo(evt.object.center);
	maps[0].zoomTo(evt.object.zoom);
}
function map_mousemove2(evt) {
	maps[1].panTo(evt.object.center);
	maps[1].zoomTo(evt.object.zoom);
}

//	 计算渲染样式
function resultColorStyle(overRatio) {
	var style = {
		strokeColor : "#339933",
		strokeOpacity : 1,
		strokeWidth : 3,
		pointRadius : 6
	};
	switch (overRatio) {
	case 1:
		style.strokeColor = "#FFFFFF";
		return style;
		break;
	case 2:
		style.strokeColor = "#FF0000";
		return style;
		break;
	default:
		return null;
	}
}
