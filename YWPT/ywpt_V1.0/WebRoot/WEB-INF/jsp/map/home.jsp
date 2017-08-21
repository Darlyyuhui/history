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
<link rel="stylesheet" href="compnents/bootstrap/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="css/GisStyle/Dialog.css" type="text/css">
<link rel="stylesheet" href="css/GisStyle/baseMapTools.css" type="text/css">
<link id="CommonCss" rel="stylesheet" href="css/Common.css" type="text/css">
<link rel="stylesheet" href="css/Mapstyle.css" type="text/css">
<link id="headCss" rel="stylesheet" href="css/transport.css" type="text/css"/>
<style type="text/css">
.mapAMQModuleNormal{
	background-image:url("images/map/moduleTools.png");
	background-position:-1px 0;
}
.mapAMQModuleClicked{
	background-image:url("images/map/moduleTools.png");
	background-position:-14px 0;
}

</style>
<%-- amq相关js引入 --%>
<script type="text/javascript" src="js/cross/jms/amq_jquery_adapter.js"></script>
<script type="text/javascript" src="js/cross/jms/amq.js"></script>
<script src="<%=basePath%>/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="<%=basePath%>/compnents/fusioncharts/js/FusionCharts.js" type="text/javascript"></script>
<script type="text/javascript">
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
		mapToolMenuList = ${menulist},
		arcgisbaseurl = "${atms.arcgis_rest_url}",
		supermapbaseurl = "${atms.supermap_base_url}",
		geoserverbaseurl = "${atms.geoserver_base_url}",
		map_center_zoom = "${atms.map_center_zoom}",
		map_max_extent = "${atms.map_max_extent}",
		geoserver_resolutions = "152.87405654907226, 76.43702827453613, 38.218514137268066, 19.109257068634033",
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
<script src="compnents/MapFrame/MapFactory.js" type="text/javascript"></script>
</head>
<body>
<table width="100%">
  <tr>
    <td valign="top" width="240">
      <div style="height:830px;">
	    <div id="mapLeft">
		  <ul id="menuList" activeStyle="selectelement" deactiveStyle="">
		    <li><a href="javascript:void(0)">导航</a></li>
		    <li><a href="javascript:void(0)">结果</a></li>
		  </ul>
		  <div id="mapTabContent">
		    <div id="mapMenuList"></div>
		    <div id="mapResultContainer">
		      <div id="mapOther"></div>
		      <div id="mapResultC"></div>
		    </div>
		  </div>
		</div>
	  </div>
    </td>
    <td width="10">
    	<div id="mapCenter" style="width:10px;"><img id="optionImg" style="width:10px;" src="images/picone/arr_left.png"> </div>
    </td>
    <td valign="top">
	    <div id="mapContainer">
		  <div id="mapToolBar"></div>
		  <div id="map" class="claro">
		    <div id="mapWaiting"></div>
		    <div id="mapNavigationBox"></div>
		    <div id="mapViewSwitcher"></div>
		    <div id="mapZoomSlider"></div>
		    <div id="mapClearBtn" class="mapClearBtn mapClearBtnNormal" title="清屏" alt="清屏"></div>
		    <div id="mapScaleBarBox" style="width:300px;"></div>
		  </div>
		</div>
    </td>
  </tr>
</table>




<script type="text/javascript">

	var passroaddialog,flowvideodialog;//添加全局的弹出框，通行证详情弹出框和流量视频播放弹出框
	var _videoDahuaDialog, _videoDialog, _videoDahuaPlayer;//大华视频信息框弹出框--由于是单独的，所以需要在全局添加变量控制
	var currentModuleId = "";// 当前活动的模块对应的模块id
	var _engine = "";
	var _tmpStart = 0, _tmpEnd = 7;
	var mapEngineType = "${mapEngineType}";
	if(mapEngineType == "0")_engine = "ArcGIS";
	else if(mapEngineType == "2")_engine = "OpenLayers";
	else if(mapEngineType == "3") {
		_engine = "SuperMap";
		_tmpStart = 8;
		_tmpEnd = 15;
		centerAndZoom[2] += 8;
	}
	else if(mapEngineType == "1") {
		alert("PGIS未实现！请选择其他地图引擎！");
	}
	else {
		alert("地图引擎不存在，请重新设置！");
	}
	if(0==parent.window.tempLoadTimes){
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
	//_tmpStart = 12;
	//_tmpEnd = 16;
	//centerAndZoom[2] = 12;
	
	(function(){
		document.body.onload = function(){
			setTimeout(function(){
				MapFactory.Init({
					engine : "BaiDu",
					callback : function(){
						MapFactory.Require([
							"ItmsMap/Util/PageInit*", // 调入时页面自动初始化
							"ItmsMap/MapConfig",
							"MapFactory/MapManager",
							"MapFactory/LayerManager",
							"ItmsMap/Util/DropMenu*",
							"MapFactory/Util/Toc*",
							"ItmsMap/Util/Navigation*",
							"ItmsMap/Util/ViewSwitch*",
							"MapFactory/Util/ZoomSlider*",
							"ItmsMap/Util/RightClick*",
							"MapFactory/Util/Dialog*",
							"MapFactory/InfoWindowManager",
							"ItmsMap/Util/DrawWidget*",
							"ItmsMap/Util/ModuleManager*",
							"ItmsMap/Util/ActiveMQWarpper*",
							"ItmsMap/Util/Legend*",
							"ItmsMap/SymbolConfig*",
							"ItmsMap/SymbolChange*",
							"ItmsMap/Util/ResultBox*",
							"ItmsMap/Util/Tab*",
							"ItmsMap/UserLayers/UserLayers*",
							"ItmsMap/Util/ExportMap*"
						],function(leftMenu,mapConfig,mapManager,LayerManager,dropMenu,tocClass,navigation,viewSwitch,zoomSlider,RightClick,dialog,InfoWindow,DrawWidget,ModuleManager,amq,Legend,SymbolConfig,SymbolChange,resultBox,Tab,UserLayers,ExportMap){

							var _mapConfig = mapConfig,
								_userLayer,
								_layers = _mapConfig.layers;

							/**
							 * 初始化地图
							 */
							var mapMGM = mapManager({
								src : "map",
								mapConfig : _mapConfig,
								loaded : function(){

									/**
									 * 初始化地图导航按钮
									 
									new navigation({
										initExtent : _mapConfig.initExtent,
										container : "mapNavigationBox"
									});*/

									/**
									 * 初始化视图切换按钮
									 
									new viewSwitch({
										src : "mapViewSwitcher"
									});*/

									/**
									 * 初始化地图缩放条
									 
									var _zoomBarHeight = zoomSlider({
										src : "mapZoomSlider",
										levels : mapMGM.getNumsOfLevel(),
										levelStart : _tmpStart,
										levelEnd : _tmpEnd
									});

									$("#mapClearBtn").css("margin-top", _zoomBarHeight + 100);
*/
									if(centerAndZoom)mapMGM.centerAndZoom(centerAndZoom[0], centerAndZoom[1], centerAndZoom[2]);

									/**
									 * 加载用户所拥有权限的图层
									 */
									_userLayer = UserLayers();
									_userLayer.init();
								}
							});

							/**
							 * 将置顶图层放置在最上
							 */
							var _topLayers = [];
							for(var elem in _layers){
								if(_layers[elem]["isTop"]){
									_topLayers.push(_layers[elem]["id"]);
								}
							}

							mapMGM.setLayerAddEvent(function(layerid){
								for(var i=0,len=_topLayers.length;i<len;i++){
									mapMGM.reorderLayer(_topLayers[i],1000-i);
								}
							});

							/**
							 * 初始化顶部工具栏
							 */
							var _toc;// 使用一个toc实例
							var _measureMent, _queryContainer, _bookMarks;
							dropMenu().init({srcNode:"mapToolBar",data:[
								{label:"<a title='用户图层'><img src='images/map/layers.png' alt='用户图层'/></a>",style:"menuIcon",func:function(){
									if(_userLayer)_userLayer.show();
								}},
								/*{label:"<a title='图层工具'><img src='images/map/layers.png' alt='图层工具'/></a>",style:"menuIcon",func:function(){
									if(_toc) {
										_toc.show();
										return;
									}
									var _layersConf = {},
										_tocOptions;
						          	for(var elem in _layers){
						          		_layersConf[_layers[elem].id] = _layers[elem];
						          	}

						          	_tocOptions = {
										src : "mapTOC",
										layers : _layersConf,
										group : [
											{label:"业务图层",layers:[],isAllowReorder:true,defaultGroup:true},

											{label:"停车诱导",layers:[
												"dirParking","genParking","leadDevice"
											],isAllowReorder:true},
											{label:"流量管理",layers:[
												"flow","flowDevice","flowPanel"
											],isAllowReorder:true},
											{label:"GPS管理",layers:[
												"gps","gpsHistoryLayer","gpsextent"
											],isAllowReorder:true},
											{label:"卡口管理",layers:[
												"cross","crossAlarm","crossAnalyBarriersLayer","crossAnalyLayer"
											],isAllowReorder:true},
											{label:"道路挖占",layers:[
											"occupyUnstart","occupyConstruct","occupyMaturring","occupyDeffering","occupyDone","occupyMatured"
											],isAllowReorder:true},
											{label:"勤务模块",layers:[
												"omUnorderedLayer","mapOMStationLayer","mapOMPoliceLayer","mapOMStationExtentsLayer"
											],isAllowReorder:true},
											{label:"基础图层",layers:["baseMap","brigade","locus"]}
										],
										excludeLayer : ["tempLayer","tempLineLayer","tempDrawLayer","dyLayer","gpsMove","crossRelation","graphicHighlightLayer","positionHighLightLayer","flownodetemp"]
									};
									_toc = new tocClass();
									_toc.init(_tocOptions);
								}},*/
								{label:"<a title='测量'><img src='images/map/measurement.png' alt='测量'/></a>",style:"menuIcon",func:function(){
									MapFactory.Require(["MapFactory/MeasureMent"],function(measureMent){
										if(_measureMent) {
											_measureMent.show();
											return;
										}
										_measureMent = measureMent();
										_measureMent.setGeometryServiceUrl(mapConfig.layers.geometryService.url);
										_measureMent.show();
									});
								}},
								{label:"<a title='快速导航'><img src='images/map/navigation.png'/></a>",style:"menuIcon",func:function(){
									MapFactory.Require(["MapFactory/BookMark*"],function(bookMarks){
										if(_bookMarks) {
											_bookMarks.show();
											return;
										}
										_bookMarks = bookMarks({
											items : [
												{name:"西安市",extent:mapMGM.getCurrentExtent()}
											]
										});
										_bookMarks.show();
									});
								}},
								{label:"<a title='地图导出'><img src='images/map/export.png'/></a>",style:"menuIcon",func:function(){
									ExportMap().init();
								}},
								{label:"<a title='兴趣点查询'><img src='images/map/query.png'/></a>",style:"menuIcon",func:function(){
									if(_queryContainer) {
										_queryContainer.show();
										return;
									}
									_queryContainer = dialog({
										mutiDialog : true,
										mutiDialogSeed : "mapQuery",
										mask : false,
										title : "兴趣点查询",
										content : "<div id='queryContainer' style='width:250px;height:120px;'></div>",
										buttonDisplay : {
											'confirmButton' : false,
											'cancelButton' : false
										},
										right:10,
										top:35
									});
									_queryContainer.show();
									MapFactory.XHR.Load("queryContainer","map/home/mapquerypage/");
								}},
								{label:"<a title='标绘工具'><img src='images/map/signs.png'/></a>",style:"menuIcon",func:function(){
									DrawWidget.showWidget();
									DrawWidget.setRightClick([]);
								}}
							]});

							var _moduleManager = ModuleManager;

							_moduleManager.init({
								src:"mapMenuList",
								target:"mapToolContent",
								modules:mapToolMenuList,
								parentModuleOpenEvt:function(module){
									if(_userLayer)_userLayer.showMoudle(module.id, true);
									if(amq){
										amq.destroy();//销毁amq的所有监听
									}
									currentModuleId = module.id;
									SymbolChange.setSymbol(module.id,function(){
										Legend.addModule(module);
									});
									// 清除视频部分弹出的dialog
									if(_videoDahuaDialog)_videoDahuaDialog.hide();
									if(_videoDialog)_videoDialog.hide();
									if(_videoDahuaPlayer)_videoDahuaPlayer.hide();
									//clearScreen();
								},
								subModuleOpenEvt : function(module){
									currentModuleId = module.id;
								}
							});

							/**
							 * 清屏功能
							 
							var _dom = MapFactory.Dom;
							var _mapclear = _dom.getById("mapClearBtn");
							_mapclear.onclick = clearScreen;

							function clearScreen(){
								InfoWindow().hide();
								if(_measureMent)_measureMent.hide();
								if(_queryContainer)_queryContainer.hide();
								if(_bookMarks)_bookMarks.hide();
								DrawWidget.hide();
								if(amq){
									amq.destroy();//销毁amq的所有监听
								}
								var layerids = mapMGM.getAllLayersID(),
									excludeLayer = [
										_layers.baseMap.id
									];
								for(var i=0,len=layerids.length;i<len;i++){
									if(MapFactory.Array.inArray(layerids[i],excludeLayer)){
										var _layerMGM = LayerManager(layerids[i]);
										_layerMGM.clear();
										_layerMGM.removeFromMap();
										if(_toc)_toc.removeLayer(layerids[i]);
									}
								}
								if(currentModuleId){
									_moduleManager.cleanModuleExcludeId(currentModuleId);
								}
								resultBox().init("mapResultC").clearBox();
							    Tab().switchTab(0,null,"menuListTab");
							    if(_videoDahuaDialog)_videoDahuaDialog.hide();
							    if(_videoDialog)_videoDialog.hide();
							}
							_mapclear.onmouseover = function(){
								_dom.removeClass(_mapclear,"mapClearBtnNormal");
								_dom.addClass(_mapclear,"mapClearBtnHover");
							}

							_mapclear.onmouseout = function(){
								_dom.removeClass(_mapclear,"mapClearBtnHover");
								_dom.addClass(_mapclear,"mapClearBtnNormal");
							}*/
							
						});
						
						//切换皮肤方法
						var d=window.parent.document.getElementById("skin-sel");
						if(d==null){
							d = parent.parent.document.getElementById("skin-sel");
						}
						if(d!=null){
							var value= d.options[d.selectedIndex].value;
							if(value=="1"){
								$("#CommonCss").attr("href","css/Common.css");
								$("#headCss").attr("href","css/transport.css");
							}else if(value=="2"){
								$("#CommonCss").attr("href","cssGreen/Common.css");
								$("#headCss").attr("href","cssGreen/transport.css");
							}else if(value=="3"){
								$("#CommonCss").attr("href","cssDarkBlue/Common.css");
								$("#headCss").attr("href","cssDarkBlue/transport.css");
							}else{
							
							}
							//d.addEventListener("change",ssss,false);
							//监听主页皮肤切换
							$(d).bind("change", function(){
								var  d1=window.parent.document.getElementById("skin-sel");
								if(d1==null){
									d1 = parent.parent.document.getElementById("skin-sel");
								}
								var value1= d1.options[d1.selectedIndex].value;
								if(value1=="1"){
									$("#CommonCss").attr("href","css/Common.css");
									$("#headCss").attr("href","css/transport.css");
								}else if(value1=="2"){
									$("#CommonCss").attr("href","cssGreen/Common.css");
									$("#headCss").attr("href","cssGreen/transport.css");
								}else if(value1=="3"){
									$("#CommonCss").attr("href","cssDarkBlue/Common.css");
									$("#headCss").attr("href","cssDarkBlue/transport.css");
								}else{
								}
							});
						}
						// 切换皮肤方法结束
					}
				});
			},1000);
			// 5分钟发送一次请求，保持session连接
			/*setInterval(function() {
				$.post("map/getTime/", function(result){
					if (typeof console != 'undefined' && console.log) console.log("time:"+result);
				});
			}, 300000);*/
		}
	})();
</script>
</body>
</html>
