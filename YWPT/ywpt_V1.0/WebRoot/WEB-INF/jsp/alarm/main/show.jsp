<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link id="MainCss" href="${root}/css/transport.css" type="text/css" rel="stylesheet">
<link id="DevCss" href="${root}/css/deviceindex.css" type="text/css" rel="stylesheet">
<script src="${root}/compnents/fusioncharts/js/FusionCharts.js" type="text/javascript"></script>
<script  type="text/javascript">
//切换皮肤方法
var d=window.parent.document.getElementById("skin-sel");
if(d!=null){
	var value= d.options[d.selectedIndex].value;
	if(value=="1"){
		$("#DevCss").attr("href","${root}/css/deviceindex.css");
		$("#MainCss").attr("href","${root}/css/transport.css");
	}else if(value=="2"){
		$("#DevCss").attr("href","${root}/cssGreen/deviceindex.css");
		$("#MainCss").attr("href","${root}/cssGreen/transport.css");
	}else if(value=="3"){
		$("#DevCss").attr("href","${root}/cssDarkBlue/deviceindex.css");
		$("#MainCss").attr("href","${root}/cssDarkBlue/transport.css");
	}else{
	
	}
	//d.addEventListener("change",ssss,false);
	//监听主页皮肤切换
	$(d).bind("change", function(){
		var  d1=window.parent.document.getElementById("skin-sel");
		var value1= d1.options[d1.selectedIndex].value;
		if(value1=="1"){
			$("#DevCss").attr("href","${root}/css/deviceindex.css");
			$("#MainCss").attr("href","${root}/css/transport.css");
		}else if(value1=="2"){
			$("#DevCss").attr("href","${root}/cssGreen/deviceindex.css");
			$("#MainCss").attr("href","${root}/cssGreen/transport.css");
		}else if(value1=="3"){
			$("#DevCss").attr("href","${root}/cssDarkBlue/deviceindex.css");
			$("#MainCss").attr("href","${root}/cssDarkBlue/transport.css");
		}else{
		
		}
	});
}
</script>
<style type="text/css">
.chart-line object {
	width:100%;
}
</style>
<div class="conten_box" style="height:695px;overflow:hidden;">
	<h4 class="title_intro" style="margin-top: 0;">
		<span><img src="${root}/images/picone/notice.gif"></span>最新告警日志
	</h4>
	<div class="mar_l5 mar_r5" style="margin-bottom: 5px; margin-top: 0;">
		<div class="shebei_info mar_b5">
          <table class="table table-striped table-bordered table-condensed table-style" id="table" style="margin:0;">
			<c:if test="${pageList==null}"><td><div style="text-align: center;margin-right: 40px;"><font color="red">没有告警日志。</font></div></td></c:if>
  		    <c:if test="${pageList!=null}">
			<thead>
				<tr>
					<th width="20">序号</th>
					<th>资产名称</th>
					<th>资产编号</th>
					<th>资产 IP</th>
					<th>事件类别</th>
					<th>告警级别</th>
					<th>重复次数</th>
					<th>告警方式</th>
					<th>告警时间</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:forEach items="${pageList}" var="log" varStatus="x">
					<tr onclick="rowOnclick(this)">
						<td style="text-align:center;">${x.index+1}</td>
						<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${log.deviceName}">${log.deviceName}</div></td>
						<td>${log.deviceCode}</td>
						<td>${log.deviceIp}</td>
						<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${log.eventTypeName}"><font style="background-color: ${log.alarmColor};">${log.eventTypeName}</font></div></td>
						<td>${log.eventLevel}</td>
						<td>${log.count}</td>
						<td>${log.alarmType}</td>
						<td><fmt:formatDate value="${log.alarmTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
				<c:if test="${pageList!=null}">
					<c:forEach begin="1" end="${5-fn:length(pageList)}">
						<tr>
							<td colspan="12">&nbsp;</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
			</c:if>
		  </table>
		</div>
	</div>
	<div style="width: 100%;margin-top: -4px;">
       <div class="diagram pull-left" style="width: 49.8%;">
         <h4 class="title_intro"><span><img src="${root}/images/newIndex/user.png"></span>监控项统计</h4>
         <div id="diagram" style="min-height:310px;margin-bottom:-10px;margin-top: 40px;">
         <div id="chart-column"></div>
         </div>
       </div>
       <div class="diagram pull-left" style="width: 49.8%;">
         <h4 class="title_intro"><span><img src="${root}/images/newIndex/screen_sys.png"></span>十日设备故障数</h4>
         <div id="diagram" style="min-height:310px;margin-bottom:-10px;margin-top: 40px;">
         <div id="chart-column2"></div>
         </div>
       </div>
       <div class="clear"></div>
    </div>
</div>
<!-- 
<textarea id="chart-xml" style="display: none">
<chart caption='告警统计' aboutMenuItemLabel='西安翔迅科技有限公司'  aboutMenuItemLink=''  bgColor='#FBFBFB'  formatNumber='1'  numberSuffix='次'
  rotateYAxisName='0'  baseFont='微软雅黑'  baseFontSize='12'  outCnvBaseFont='微软雅黑'  outCnvBaseFontSize='12'  decimalPrecision='0'  
  chartLeftMargin='5'  shownames='1'  showValues='1'  animation='1'  formatNumberScale='0'  showPercentageValues='0'  startingAngle='45'  
  slicingDistance='1'  enableSmartLabels='1'  enableRotation='1'>
	    <c:forEach items="${alarmList}" var="data">
			<set name='${data['TYPES']}' value='${data['ALARMTIMES']}'/>
		</c:forEach>
</chart>
</textarea>

<textarea id="chart-xml1" style="display: none !important">
<chart caption='告警级别统计' aboutMenuItemLabel='西安翔迅科技有限公司'  aboutMenuItemLink=''  bgColor='#FBFBFB'  formatNumber='1'  numberSuffix='次'
  rotateYAxisName='0'  baseFont='微软雅黑'  baseFontSize='12'  outCnvBaseFont='微软雅黑'  outCnvBaseFontSize='12'  decimalPrecision='0'  
  chartLeftMargin='5'  shownames='1'  showValues='1'  animation='1'  formatNumberScale='0'  showPercentageValues='0'  startingAngle='45'  
  slicingDistance='1'  enableSmartLabels='1'  enableRotation='1' showBorder='0'>
	    <c:forEach items="${listGrade}" var="data">
			<set name='${data['NAME']}' value='${data['ALARMTIMES']}'/>
		</c:forEach>
</chart>
</textarea>
 -->

<textarea id="chart-xml" style="display: none">
<chart aboutMenuItemLabel='西安翔迅科技有限公司'  aboutMenuItemLink=''  bgColor='#FBFBFB'  formatNumber='1'  numberSuffix='个'
  rotateYAxisName='0'  baseFont='微软雅黑'  baseFontSize='12'  outCnvBaseFont='微软雅黑'  outCnvBaseFontSize='12'  decimalPrecision='0'  
  chartLeftMargin='5'  shownames='1'  showValues='1'  animation='1'  formatNumberScale='0'  showPercentageValues='0'  startingAngle='45'  
  slicingDistance='1'  enableSmartLabels='1'  enableRotation='1'  >
	    <c:forEach items="${countByTypeList}" var="data">
			<set name='${data.assettype}' value='${data.assetstatus}'/>
		</c:forEach>
</chart>
</textarea>
<textarea id="chart-xml2" style="display: none">
<chart  aboutMenuItemLabel='西安翔迅科技有限公司'  aboutMenuItemLink='' numberSuffix='个'  bgColor='#FBFBFB'  rotateYAxisName='0'  
baseFont='微软雅黑'  baseFontSize='12'  outCnvBaseFont='微软雅黑'  outCnvBaseFontSize='12'  decimalPrecision='0'  chartLeftMargin='5'  shownames='1'  formatNumberScale='0'  
  numdivlines='4'  areaBorderColor='DAE5F4'  numVDivLines='29'  vDivLineAlpha='30'  showValues='0' >
	    ${categoriexml}
	    <c:forEach items="${datasetss}" var="datasetss" varStatus="k">
				${datasetss.dataXml}
		</c:forEach>
</chart>
</textarea>

<script>


  // 页面初始化加载
	$(document).ready(function(){
		//隔行换色
		$("#tbody tr:odd").addClass("color1");
		$("#tbody tr:even").addClass("color2");
		
		
		//魏克明add鼠标划进划出tr行产生效果的js	
		$("#tbody tr:odd").mouseover(function(e){
			$(this).removeClass("color1").addClass("tr_over");
	 	});
	 	$("#tbody tr:even").mouseover(function(){
	 		$(this).removeClass("color2").addClass("tr_over");
	 	});
		
	 	$("#tbody tr:odd").mouseout(function(){
	 		if($(this).find(":checkbox").is(":checked")){
	 			$(this).removeClass("color1").addClass("tr_over");
	 		}else{
	 			$(this).removeClass("tr_over").addClass("color1");
	 		}
	 	});
	 	$("#tbody tr:even").mouseout(function(){
	 		if($(this).find(":checkbox").is(":checked")){
	 			$(this).removeClass("color2").addClass("tr_over");
	 		}else{
	 			$(this).removeClass("tr_over").addClass("color2");
	 		}
	 	});
		
		//点击偶数行复选框状态变化
		$("#tbody tr:odd").click(function(e){
			if($(e.target).attr("type") != "checkbox"){
				if($(this).find(":checkbox").is(":checked")){
					$(this).find(":checkbox").attr("checked",!$(this).find(":checkbox").attr("checked"));
					$(this).removeClass("tr_over").addClass("color1");
				}else{
					$(this).find(":checkbox").attr("checked","checked");
					$(this).removeClass("color1").addClass("tr_over");
					
				}
			}
		})
		//点击奇数行复选框状态变化
		$("#tbody tr:even").click(function(e){
			if($(e.target).attr("type") != "checkbox"){
				if($(this).find(":checkbox").is(":checked")){
					$(this).find(":checkbox").attr("checked",!$(this).find(":checkbox").attr("checked"));
					$(this).removeClass("tr_over").addClass("color2");
				}else{
					$(this).find(":checkbox").attr("checked","checked");
					$(this).removeClass("color2").addClass("tr_over");
				}
			}
		})
		
		//隔行换色
		$("#tbody1 tr:odd").addClass("color1");
		$("#tbody1 tr:even").addClass("color2");
		
		
		//魏克明add鼠标划进划出tr行产生效果的js	
		$("#tbody1 tr:odd").mouseover(function(e){
			$(this).removeClass("color1").addClass("tr_over");
	 	});
	 	$("#tbody1 tr:even").mouseover(function(){
	 		$(this).removeClass("color2").addClass("tr_over");
	 	});
		
	 	$("#tbody1 tr:odd").mouseout(function(){
	 		if($(this).find(":checkbox").is(":checked")){
	 			$(this).removeClass("color1").addClass("tr_over");
	 		}else{
	 			$(this).removeClass("tr_over").addClass("color1");
	 		}
	 	});
	 	$("#tbody1 tr:even").mouseout(function(){
	 		if($(this).find(":checkbox").is(":checked")){
	 			$(this).removeClass("color2").addClass("tr_over");
	 		}else{
	 			$(this).removeClass("tr_over").addClass("color2");
	 		}
	 	});
		
		//点击偶数行复选框状态变化
		$("#tbody1 tr:odd").click(function(e){
			if($(e.target).attr("type") != "checkbox"){
				if($(this).find(":checkbox").is(":checked")){
					$(this).find(":checkbox").attr("checked",!$(this).find(":checkbox").attr("checked"));
					$(this).removeClass("tr_over").addClass("color1");
				}else{
					$(this).find(":checkbox").attr("checked","checked");
					$(this).removeClass("color1").addClass("tr_over");
					
				}
			}
		})
		//点击奇数行复选框状态变化
		$("#tbody1 tr:even").click(function(e){
			if($(e.target).attr("type") != "checkbox"){
				if($(this).find(":checkbox").is(":checked")){
					$(this).find(":checkbox").attr("checked",!$(this).find(":checkbox").attr("checked"));
					$(this).removeClass("tr_over").addClass("color2");
				}else{
					$(this).find(":checkbox").attr("checked","checked");
					$(this).removeClass("color2").addClass("tr_over");
				}
			}
		})
		
		getChart1XmlData();
		getChart2XmlData();
	});
		
	/*function getChart1XmlData(){
	    var chart = new FusionCharts("${root}/compnents/fusioncharts/chart/Column3D.swf", "Chart1Id","100%","350");
		var total = '${unfirmSize}';
		if (total == '0') {
			$("#chart-column").html("<center style='margin-top:150px;color:blue'>无数据</center>");
		} else {
			chart.setDataXML($("#chart-xml").val());
			chart.render("chart-column");
		}
	}
  	
  	 function getChart2XmlData(){
	    var chart = new FusionCharts("${root}/compnents/fusioncharts/chart/Pie2D.swf", "Chart2Id","100%","350");
		var total = '${unfirmSize}';
		if (total == '0') {
			$("#chart-column1").html("<center style='margin-top:150px;color:blue'>无数据</center>");
		} else {
			chart.setDataXML($("#chart-xml1").val());
			chart.render("chart-column1");
		}
	}*/
  	
  	function getChart1XmlData(){
	    var chart = new FusionCharts("${root}/compnents/fusioncharts/chart/Column3D.swf", "Chart1Id","100%","350");
		var total = '${unfirmSize}';
		if (total == '0') {
			$("#chart-column").html("<center style='margin-top:150px;color:blue'>无数据</center>");
		} else {
			chart.setDataXML($("#chart-xml").val());
			chart.render("chart-column");
		}
	} 	
	
	function getChart2XmlData(){
	    var chart = new FusionCharts("${root}/compnents/fusioncharts/chart/MSLine.swf", "chart2Id", "100%", "350");		
		var total = '${unfirmSize}';
		if (total == '0') {
			$("#chart-column2").html("<center style='margin-top:150px;color:blue'>无数据</center>");
		} else {
			chart.setDataXML($("#chart-xml2").val());
			chart.render("chart-column2");
		}
	}
</script>
