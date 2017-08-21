<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script language="javascript" src="${root}/js/LodopFuncs.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
 		<title>服务商下责任资产数量统计</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
        <script src="${root}/compnents/fusioncharts/js/FusionCharts.js" type="text/javascript" ></script>
        <script src="${root}/compnents/fusioncharts/js/FusionChartsExportComponent.js" type="text/javascript" ></script>
    <style type="text/css">
		body {
			
			font-size: 12px;
		}
	</style>
	<script LANGUAGE="JavaScript">
			function updateChart(domId){			
				updateChartXML(domId,generateXML());
			}
			function generateXML(){			
				var strXML="";
				strXML = "${chartHeadStr}" + ((document.getElementById("ShowValues").checked==true)?("showValues='1' rotateValues='0'"):(" showValues='0' ")) + " >";
				strXML = strXML + "${factoryNameXml}";
				strXML = (document.getElementById("PA").checked==true)?(strXML + getProductXML('deviceStackedXml')):(strXML);
				/* strXML = (document.getElementById("PB").checked==true)?(strXML + getProductXML('videoStackedXml')):(strXML); */
				strXML = (document.getElementById("PC").checked==true)?(strXML + getProductXML('serverStackedXml')):(strXML);
				strXML = (document.getElementById("PD").checked==true)?(strXML + getProductXML('databaseStackedXml')):(strXML);
				strXML = (document.getElementById("PE").checked==true)?(strXML + getProductXML('ftpStackedXml')):(strXML);
				strXML = (document.getElementById("PF").checked==true)?(strXML + getProductXML('projectStackedXml')):(strXML);
				strXML = (document.getElementById("PG").checked==true)?(strXML + getProductXML('cabinetStackedXml')):(strXML);
				strXML = strXML + "</chart>";
				
				return strXML;
			}
			function getProductXML(productIndex){		
				var productXML;
				if(productIndex=="deviceStackedXml"){
				    productXML = "${deviceStackedXml}";
				}
				/* if(productIndex=="videoStackedXml"){
				    productXML = "${videoStackedXml}";
				} */
				if(productIndex=="serverStackedXml"){
				    productXML = "${serverStackedXml}";
				}
				if(productIndex=="databaseStackedXml"){
				    productXML = "${databaseStackedXml}";
				}
				if(productIndex=="ftpStackedXml"){
				    productXML = "${ftpStackedXml}";
				}
				if(productIndex=="projectStackedXml"){
				    productXML = "${projectStackedXml}";
				}if(productIndex=="cabinetStackedXml"){
					productXML = "${cabinetStackedXml}";
				}
				return productXML;			
			}
			function showCharT(chartSWF){
			 var chart1 = new FusionCharts("${root}/compnents/fusioncharts/chart/StackedColumn3D.swf", "chart1Id", "${chart.wide}", "${chart.high}", "0", "0");     
			 if(document.getElementById("showCharTypes").checked==true){
			    chart1 = new FusionCharts(chartSWF, "chart1Id", "${chart.wide}", "${chart.high}", "0", "0");
			 }
	            var strXML=generateXML();     
	            chart1.setDataXML(strXML);        
		        chart1.render("chartdiv");        
	        }
	</script>
</head>
<body >
	<div class="conten_box" style="width:100%;height:710px;overflow-x:scroll; border:0;">
		<table align="center" height="400" width="100%" style="background-color: white;margin-bottom:5px;border:solid 1px #CCCCCC;" id="chart_id">
			<tr>
				<td align="center" valign="middle">
					<div id="chartdiv">
						<img  src="${root}/images/loading32.gif"/>
						<font style="font-size: 12">正在加载数据,请稍等...</font>
					</div>
				</td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td align="center">
			 	    <FORM name='productSelector' id='productSelector' action='Index.html' method='POST' >
			 	    	<table>
			 	    		<tr>
			 	    			<td style="border:1px solid #CCCCCC;" id="2d_id">
				 	    			<div id="2dcheckbox" style="float:left;">
										<div style="display:none">
											<B>请选择:</B>
											<INPUT id = "PA"  TYPE='Checkbox' name='PA' onClick="JavaScript:updateChart('chart1Id');" checked />&nbsp;卡口&nbsp;&nbsp;
											<!-- <INPUT id = "PB"  TYPE='Checkbox' name='PB' onClick="JavaScript:updateChart('chart1Id');" checked />&nbsp;监控&nbsp;&nbsp; -->
											<INPUT id = "PC"  TYPE='Checkbox' name='PC' onClick="JavaScript:updateChart('chart1Id');" checked />&nbsp;服务器&nbsp;&nbsp;
											<INPUT id = "PD"  TYPE='Checkbox' name='PD' onClick="JavaScript:updateChart('chart1Id');" checked />&nbsp;数据库&nbsp;&nbsp;
											<INPUT id = "PE"  TYPE='Checkbox' name='PE' onClick="JavaScript:updateChart('chart1Id');" checked />&nbsp;FTP&nbsp;&nbsp;
											<INPUT id = "PF"  TYPE='Checkbox' name='PF' onClick="JavaScript:updateChart('chart1Id');" checked />&nbsp;平台&nbsp;&nbsp;
											<INPUT id = "PG"  TYPE='Checkbox' name='PG' onClick="JavaScript:updateChart('chart1Id');" checked />&nbsp;机柜&nbsp;&nbsp;
											<INPUT id = "ShowValues"  TYPE='Checkbox' name='ShowValues' onClick="JavaScript:updateChart('chart1Id');" checked />显示数值&nbsp;&nbsp;
											
										</div>
										<INPUT id = "showCharTypes" style="margin-bottom: 6px;" TYPE='Checkbox' name='showCharTypes' onClick="JavaScript:showCharT('${root}/compnents/fusioncharts/chart/StackedColumn2D.swf');"  /><font style="font-weight: bold;">显示2D&nbsp;&nbsp;</font>
									</div>
								</td>
								<td style="border:1px solid #CCCCCC;" id="ex_id">
									<div id="fcexpDiv" style="margin-bottom: 6px;" >Export</div>
								</td>
			 	    		</tr>
			 	    	
			 	    	</table>
			 	    	
			 	    	<script language="JavaScript">
				            if("nodata" == "${nodata}"){ 
								document.getElementById("chartdiv").innerHTML ="<div style='line-height:30px;width:130px;margin:0 auto;margin-top:60px;text-align:center;background:#ced5e6;'>没有数据...</div>"
								document.getElementById("fcexpDiv").style.display="none";
								document.getElementById("2dcheckbox").style.display="none";
								document.getElementById("2d_id").style.border="0";
								document.getElementById("ex_id").style.border="0";
								document.getElementById("chart_id").style.border="solid 1px #CCCCCC";
						    }else{
						    	var myExportComponent = new FusionChartsExportObject("fcExporter1", "${root}/compnents/fusioncharts/chart/FCExporter.swf");
					            myExportComponent.Render("fcexpDiv");
					            var chart1 = new FusionCharts("${root}/compnents/fusioncharts/chart/StackedColumn3D.swf", "chart1Id", "${chart.wide}", "${chart.high}");	
								var strXML=generateXML();
								chart1.setDataXML(strXML);
								chart1.render("chartdiv");
							}
						</script>
					</FORM>
				</td>
			</tr>		
		</table>	
	</div>
</body>

</html>
 