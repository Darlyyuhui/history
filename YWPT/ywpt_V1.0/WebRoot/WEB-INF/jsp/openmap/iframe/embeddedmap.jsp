<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String container = request.getParameter("mapCon");
	String url = request.getParameter("url");

%>
<html>
  <head>
  	<base href="<%=basePath %>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv='Pragma' content='No-cache'> 
	<meta http-equiv='Cache-Control' content='No-cache'>
	<meta http-equiv='Expires' content='0'>

	<%-- 地图统一样式引入 --%>
	<link rel="stylesheet" href="css/GisStyle/Dialog.css" type="text/css">
	<link rel="stylesheet" href="css/GisStyle/Common.css" type="text/css">
	<link rel="stylesheet" href="css/GisStyle/baseMapTools.css" type="text/css">
	<link rel='stylesheet' href='css/opencss/openmapstyle.css'>
	<link rel='stylesheet' href='css/transport.css'>
	<%-- 引入openLayer相关 --%>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>compnents/openmap/theme/default/style.css"/>
	<script type="text/javascript" src="<%=basePath%>compnents/openmap/lib/OpenLayers.js"></script>
	<script type="text/javascript" src="<%=basePath%>compnents/bootstrap/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="compnents/itmap/itmap.js"></script>
	<script type="text/javascript" src="compnents/itmap/openlayer/util.js"></script>
	<script type="text/javascript" src="compnents/itmap/openlayer/navigation.js"></script>
	<script type="text/javascript">
		var baseServiceURL = ${mapLayersInfo},
			basePath = "<%=basePath%>",
			container = "<%=container%>",
			url = "<%=url%>";
	</script>
  </head>
  <body>
  	<div id="mapDiv_signal" style="height:100%; width:100%">
  		<div id="mapNavigationBox"></div>
  	</div>
  	<script type="text/javascript" src="<%=basePath%>js/openmap/iframe/map-embedded.js"></script>
	<script type="text/javascript">
		var isLoaded = false,
			isAutoLoaded = '${param.isAutoloaded}',
			container = '${param.mapCon}',
			url = '${param.url}';
			EmbeddedMap.initMap(container, url);
			isLoaded = true;;
	</script>
  </body>
</html>
