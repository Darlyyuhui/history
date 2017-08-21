<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String container = request.getParameter("mapCon");
	String url = request.getParameter("url");
%>

<!-- 导入map相关配置-->
<link rel="stylesheet" href="${atms['map_restUrl']}/arcgis_js_api/library/3.5/jsapi/js/dojo/dijit/themes/claro/claro.css">
<link rel="stylesheet" type="text/css" href="${atms['map_restUrl']}/arcgis_js_api/library/3.5/jsapi/js/esri/css/esri.css" />
<link id="CommonCss" rel="stylesheet" href="<%=basePath%>css/GisStyle/Common.css" type="text/css">
<script type="text/javascript" src="${atms['map_restUrl']}/arcgis_js_api/library/3.5/jsapi/init.js"></script>
<script type="text/javascript" src="<%=basePath%>compnents/bootstrap/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>compnents/itmap/arcgis/map-navigation.js"></script>
<script type="text/javascript">
	var baseServiceURL = ${mapLayersInfo},
		basePath = "<%=basePath%>",
		container = "<%=container%>",
		url = "<%=url%>";
	var path = "<%=path%>";
</script>

<script type="text/javascript" src="<%=basePath%>js/map/iframe/map-embedded.js"></script>
<script type="text/javascript" src="<%=basePath%>js/map/iframe/parallel.js"></script>

<body>
<div id="mapDiv_signal" style="width:100%; height:100%;" class="claro">
	<div id="mapNavigationBox"></div>
</div>
<script type="text/javascript">
	//设置代理
	esriConfig.defaults.io.proxyUrl = basePath + "proxy.jsp";
	
	var isLoaded = false,
		isAutoLoaded = '${param.isAutoloaded}',
		container = '${param.mapCon}',
		url = '${param.url}';
		
	dojo.addOnLoad(function() {
		if (isAutoLoaded !== "false") {
			EmbeddedMap.initMap(container, url);
		}
		
		isLoaded = true;

	});
</script>
</body>
