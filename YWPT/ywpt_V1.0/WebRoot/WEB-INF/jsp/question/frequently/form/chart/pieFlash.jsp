<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script language="javascript" src="${root}/js/LodopFuncs.js"></script>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
 		<title>频发故障类型统计饼状图</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
 		
 		<script src="${root}/compnents/fusioncharts/js/FusionCharts.js" type="text/javascript" ></script>
  		<script src="${root}/compnents/fusioncharts/js/FusionChartsExportComponent.js" type="text/javascript" ></script>
	    <style type="text/css">
		<!--
		body {
			
			font-size: 12px;
		}
		-->
		</style>

 	</head>
 	<body >
 		<div class="conten_box" style="width:100%;height:600px;overflow-x:scroll; border:0;">
			
			<table align="center" height="400" width="100%" >
				<tr>
					<td align="center" valign="middle">
						${pieChartStr}
					</td>
				</tr>
			    
			</table>
		</div>
	</body>

</html>
 