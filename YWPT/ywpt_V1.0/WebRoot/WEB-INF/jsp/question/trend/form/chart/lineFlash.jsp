<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script language="javascript" src="${root}/js/LodopFuncs.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
 		<title>故障类型趋势曲线图统计</title>
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
<script LANGUAGE="JavaScript">
		function updateChart(domId){			
			updateChartXML(domId,generateXML());
		}
		function generateXML(){
			var strXML="";
			strXML = "${chartHeadStr} showValues='0' >";
			strXML = strXML + "${categoriexml}";
			<c:forEach items="${datasetss}" var="datasetss" varStatus="k">
				strXML = (document.getElementById("PA${datasetss.viewId}").checked==true)?(strXML + "${datasetss.dataXml}"):(strXML);
			</c:forEach>
			strXML = strXML + "</chart>";
			return strXML;
		}
		
</script>
 	</head>
 	<body >
 		<div class="conten_box" style="width:100%;height:650px;overflow-x:scroll; border:0;">
		 	<table>
		 	    <FORM name='productSelector' id='productSelector' action='Index.html' method='POST' >
					<div style="display:none">
					<B>请选择:</B>
					<c:forEach items="${datasetss}" var="datasetss" varStatus="k">
					<INPUT TYPE='Checkbox' name='PA${datasetss.viewId}' id='PA${datasetss.viewId}'  onClick="JavaScript:updateChart('chartAreaId');" checked />&nbsp;&nbsp;&nbsp;${datasetss.type}
					</c:forEach>
					</div>
				</FORM>
		 	</table>
			
			<table height="400" width="100%">
				<tr>
					<td align="center" valign="middle">
					<div id="chartdiv">
					<img  src="${root}/images/loading32.gif"/>
					<font style="font-size: 12">正在加载数据,请稍等...</font>
					</div>
			
					</td>
				</tr>
				<tr>
					<td align="center">
					<div id="fcexpDiv" >导出</div>
					<script type="text/javascript">
					    if("nodata" == "${nodata}"){
							document.getElementById("chartdiv").innerHTML ="<table align='center' height='400' width='100%' style='background-color: white;border:solid 1px #CCCCCC;'><tr><td align='center' valign='middle'>" +
								"<div style='line-height:30px;width:130px;margin:0 auto;margin-top:60px;text-align:center;background:#ced5e6;'>没有数据...</div></td></tr></table>";
							document.getElementById("fcexpDiv").style.display="none";
						}else{ 
							var chart1 = new FusionCharts("${root}/compnents/fusioncharts/chart/MSLine.swf", "chartAreaId", "${chart.wide}", "${chart.high}");		   
							var strXML=generateXML();
							chart1.setDataXML(strXML);
							chart1.render("chartdiv");
							var myExportComponent = new FusionChartsExportObject("fcExporter1", "${root}/compnents/fusioncharts/chart/FCExporter.swf");
							myExportComponent.Render("fcexpDiv");
					            }
					</script>
					</td>
			    </tr>
			</table>
		</div>
	</body>

</html>
 