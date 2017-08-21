<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>智能交通综合管理平台地图部分</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href='compnents/bootstrap/css/bootstrap.css' rel='stylesheet'/>
		<link rel="stylesheet" href="css/GisStyle/Dialog.css" type="text/css">
		<link rel="stylesheet" href="css/GisStyle/baseMapTools.css" type="text/css">
		<link id="CommonCss" rel="stylesheet" href="css/Common.css" type="text/css">
		
		<script src="compnents/bootstrap/js/jquery-1.7.2.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			var currentModuleId = "";// 当前活动的模块对应的模块id
			function strListToNumberList(list) {
				var result = [];
				for(var i=0,len=list.length; i<len; i++) {
					result[i] = Number(list[i]);
				}
				return result;
			}
			function getSimilarIp(urls, ip) {
				var i=0,len=urls.length;
				for(i=0; i<len; i++) {
					if(urls[i].indexOf(ip) != -1) {
						return urls[i];
					}
				}
				if(ip.indexOf(".") == -1)return urls[0];
				ip = ip.substr(0, ip.lastIndexOf("."));
				return getSimilarIp(urls, ip);
			}
			var path = "<%=path%>",
				basePath = "<%=basePath%>",
				menuid = "${menuid}",
				arcgisbaseurl = "${atms.arcgis_rest_url}",
				supermapbaseurl = "${atms.supermap_base_url}",
				geoserverbaseurl = "${atms.geoserver_base_url}",
				map_center_zoom = "${atms.map_center_zoom}",
				map_max_extent = "${atms.map_max_extent}",
				geoserver_resolutions = "${atms.geoserver_resolutions}",
				supermap_resolutions = "${atms.supermap_resolutions}",
				arcgis_resolutions = "${atms.arcgis_resolutions}",
				MapFactoryBaseUrl = "<%=basePath%>";
			var centerAndZoom,
				maxExtent,
				geoserverResolutions,
				supermapResolutions,
				arcgisResolutions;
			// 获取当前浏览器地址location.href，并进行截断处理，只保留ip
			var locationUrl = location.href;
			locationUrl = locationUrl.substr("http://".length, locationUrl.length);
			locationUrl = locationUrl.substr(0, locationUrl.indexOf(":"));
			
			var arcgisUrls = arcgisbaseurl.split(";");
			if(arcgisUrls.length > 1) {
				arcgisbaseurl = getSimilarIp(arcgisUrls, locationUrl);
			}
			var geoserverUrls = geoserverbaseurl.split(";");
			if(geoserverUrls.length > 1) {
				geoserverbaseurl = getSimilarIp(geoserverUrls, locationUrl);
			}
			var supermapUrls = supermapbaseurl.split(";");
			if(supermapUrls.length > 1) {
				supermapbaseurl = getSimilarIp(supermapUrls, locationUrl);
			}
			if(map_center_zoom) {
				centerAndZoom = strListToNumberList(map_center_zoom.split(","));
			}
			if(map_max_extent) {
				var temp = strListToNumberList(map_max_extent.split(","));
				maxExtent = {minX: temp[0], minY: temp[1], maxX: temp[2], maxY: temp[3]};
			}
			if(geoserver_resolutions) {
				geoserverResolutions = strListToNumberList(geoserver_resolutions.split(","));
			}
			if(supermap_resolutions) {
				//supermapResolutions = strListToNumberList(supermap_resolutions.split(","));
			}
			if(arcgis_resolutions) {
				arcgisResolutions = strListToNumberList(arcgis_resolutions.split(","));
			}
			
			
		</script>
		<script type="text/javascript" src="compnents/MapFrame/MapFactory.js"></script>
	</head>
	<body>
	<div id="map" class="claro" style="height:100%;">
		<div id="mapWaiting"></div>
		<div id="mapNavigationBox"></div>
		<div id="mapViewSwitcher"></div>
		<div id="mapZoomSlider"></div>
	</div>
		<script type="text/javascript">
			var isCenter = true;
			var search = location.search;
			if(search) {
				var params = search.substr(1).split("=");
				if(params && params.length>1 && params[0]=="isCenter" && params[1]=="false") {
					isCenter = false;
				}
			}
			var _engine = "";
			var levelNums = 0;
			var isMapInit = false;
			var isBodyLoad = false;
			var mapEngineType = "${mapEngineType}";
			if(mapEngineType == "0") {
				_engine = "ArcGIS";
				levelNums = arcgisResolutions.length;
			}
			else if(mapEngineType == "2") {
				_engine = "OpenLayers";
				levelNums = geoserverResolutions.length;
			}
			else if(mapEngineType == "3") {
				_engine = "SuperMap";
				levelNums = geoserverResolutions.length;
			}
			else if(mapEngineType == "1") {
				alert("PGIS未实现！请选择其他地图引擎！");
			}
			else {
				alert("地图引擎不存在，请重新设置！");
			}
			if(0==parent.window.tempLoadTimes) {
				parent.window.tempLoadTimes++;
				window.location.reload();
			}
			// offset=真实坐标-腾讯地图坐标
			var offsetX = 108.91869 - 108.92319;
			var offsetY = 34.25254 - 34.25098;
			// offset=真实坐标-百度地图坐标
			offsetX = 108.94245 - 108.953567;
			offsetY = 34.26102 - 34.265685;
			offsetX = 0;
			offsetY = 0;
			function showmap() {
				if(_engine == "ArcGIS")_initMap();
				else if(_engine == "OpenLayers")setTimeout(_initMap, 1000);
				else if(_engine == "SuperMap")setTimeout(_initMap, 1000);
			}
			function _initMap() {
				if(isMapInit)return;
				isMapInit = true;
				MapFactory.Init({
					engine : _engine,
					callback : function(){
						MapFactory.Require([
							"ItmsMap/MapConfig",
							"MapFactory/MapManager",
							"ItmsMap/Util/Navigation*",
							"ItmsMap/Util/ViewSwitch*",
							"MapFactory/Util/ZoomSlider*"
						],function(mapConfig,mapManager,navigation,viewSwitch,zoomSlider){
							$("#mapWaiting").hide();
							var _mapConfig = mapConfig;
							/**
							 * 初始化地图
							 */
							var mapMGM = mapManager({
								src : "map",
								mapConfig : _mapConfig,
								loaded : function(){
									new navigation({
										initExtent : maxExtent,
										container : "mapNavigationBox"
									});

									new viewSwitch({
										src : "mapViewSwitcher"
									});

									var _zoomBarHeight = new zoomSlider({
										src : "mapZoomSlider",
										levels : mapMGM.getNumsOfLevel(),
										levelStart : 0,
										levelEnd : 7
									});

									$("#mapClearBtn").css("margin-top", _zoomBarHeight + 100);

									if(centerAndZoom && isCenter)mapMGM.centerAndZoom(centerAndZoom[0], centerAndZoom[1], centerAndZoom[2]);
									
									isMapLoad = true;
									// 加载后台资产数据成功后，添加数据到地图上
									if(isDataLoad)showInMap();
								}
							});
							
						});
					}
				});
			}
			(function(){
				document.body.onload = function() {
					isBodyLoad = true;
					showmap();
					loadData();
				};
			})();
			
			var isMapLoad, list, isDataLoad;
			// 加载后台资产数据
			function loadData() {
				$.ajax({
					url: "alarm/status/maplist/${menuid}/",
					type: "POST",
					success: function(result) {
						list = result;
						isDataLoad = true;
						if(isMapLoad)showInMap();
					},
					fail: function(error) {
						isDataLoad = true;
						if(isMapLoad)showInMap();
					}
				});
			}
			// 添加数据到地图上
			function showInMap() {
				MapFactory.Require([
					"MapFactory/MapManager",
					"MapFactory/LayerManager",
					"MapFactory/GraphicManager",
					"MapFactory/InfoWindowManager",
					"ItmsMap/SymbolConfig*",
					"ItmsMap/Util/Legend*"
				],function(mapManager,LayerManager,GraphicManager,InfoWindow,SymbolConfig,Legend){
					var map = mapManager();
					var tlayer = LayerManager("crossStatus");
					tlayer.addOnClickEvent(layerClick);
					
					// 初始化图例
					var legendConfig = {
						items: ["zcPowerErrorStyle","zcNetErrorStyle","zcDataErrorStyle","zcNormalStyle"],
						name: "资产状态"
					};
					Legend.showModuleWithConfig(legendConfig);
					
					for(var i=0; i<list.length; i++) {
						var item = list[i];
						var tempStyle = SymbolConfig.zcPowerErrorStyle;
						if(item.powerStatus == "3") {
							// 供电异常
							tempStyle = SymbolConfig.zcPowerErrorStyle;
						}
						else if(item.powerStatus == "0") {
							// 供电未知
							tempStyle = SymbolConfig.zcPowerErrorStyle;
						}
						else if(item.netStatus == "3") {
							// 网络异常
							tempStyle = SymbolConfig.zcNetErrorStyle;
						}
						else if(item.netStatus == "0") {
							// 网络未知
							tempStyle = SymbolConfig.zcNetErrorStyle;
						}
						// 网络正常
						else if(item.dataStatus == "3") {
							// 数据异常
							tempStyle = SymbolConfig.zcDataErrorStyle;
						}
						else if(item.dataStatus == "0") {
							// 数据未知
							tempStyle = SymbolConfig.zcDataErrorStyle;
						}
						else if(item.dataStatus == "1") {
							// 数据正常
							tempStyle = SymbolConfig.zcNormalStyle;
						}
						var graphic = GraphicManager({geo:{type:"point",points:item.cpuStatus+","+item.memoryStatus}, symbol:tempStyle, attributes:item});
						graphic.addToLayer("crossStatus");
						
					}
					
					// 图层单击事件
					function layerClick(e) {
						var geo = e.graphic.geo;
						var att = e.graphic.attributes;
						
						var info = InfoWindow();
						info.setWidth(300);
						info.setHeight(200);
						info.setAnchor(geo);
						info.setTitle("卡口设备状态检测信息");
						info.setLoadPage("alarm/status/infowindow/"+att.id+"/");
						info.show();
					}
				});
			}
		</script>
	</body>
</html>
