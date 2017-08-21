<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf"%>
<link id="DevCss" href="${root}/css/deviceindex.css" type="text/css" rel="stylesheet">
<script src="${root}/compnents/fusioncharts/js/FusionCharts.js" type="text/javascript" ></script>
<script  type="text/javascript">
//切换皮肤方法
var d=window.parent.document.getElementById("skin-sel");
if(d!=null){
var value= d.options[d.selectedIndex].value;
if(value=="1"){
		$("#DevCss").attr("href","${root}/css/deviceindex.css");
	}else if(value=="2"){
		$("#DevCss").attr("href","${root}/cssGreen/deviceindex.css");
	}else if(value=="3"){
		$("#DevCss").attr("href","${root}/cssDarkBlue/deviceindex.css");
	}else{
	}
	//监听主页皮肤切换
	$(d).bind("change", function(){
		var  d1=window.parent.document.getElementById("skin-sel");
		var value1= d1.options[d1.selectedIndex].value;
		if(value1=="1"){
			$("#DevCss").attr("href","${root}/css/deviceindex.css");
		}else if(value1=="2"){
			$("#DevCss").attr("href","${root}/cssGreen/deviceindex.css");
		}else if(value1=="3"){
			$("#DevCss").attr("href","${root}/cssDarkBlue/deviceindex.css");
		}else{
		
		}
	});
}
</script>
<div class="conten_box" style="height:700px;overflow:hidden;">
	<div class="introduction">
		<div class="row-fluid" style="margin: 0px; display: block; background-position: 50% 100%; background-repeat: no-repeat no-repeat;">
			<h4 class="title_intro">巡检管理--本周巡检信息列表
				<font>&nbsp;&nbsp;<a href="${root}/alarm/perambulateList/list/1707050933368212af8e88fd80ceedb4/" style="color: blue; font-weight: lighter;">全部</a></font>
			</h4>
			<div class="mar_5" style="overflow-x:auto;">
		      	<table class="table table-striped table-bordered table-condensed table-style" id="table">
					<thead>
						<tr>
						<th>序号</th>
						<th>巡检人员</th>
						<th>设备名称</th>
						<th>设备编号</th>
						<th>设备IP</th>
						<th>设备类型</th>
						<th>位置信息</th>
						<th>原因</th>
						<!-- <th>备注</th> -->
						</tr>
					</thead>
					<c:if test="${pageList.result[0]==null}">
			  			<tr>
							<td colspan="9">
								<div style='text-align: center;margin-right: 40px;'>
									<font color='red'>没有查到结果。</font>
								</div>
							</td>
						</tr>
						<c:forEach begin="1" end="7">
							<tr>
								<td colspan="9">&nbsp;</td>
							</tr>
						</c:forEach>
			  		</c:if>
			  		<c:if test="${pageList.result[0]!=null}">
						<tbody id="tbody">
						  	<c:forEach items="${pageList.result}" var="perambulate" varStatus="x">
						<tr >
							<td style="text-align:center;">${x.index+1}</td>
							<td>${perambulate.user.name}</td>
							<td>${perambulate.devicename}</td>
							<td>${perambulate.devicecode}</td>
							<td>${perambulate.deviceip}</td>
							<td>
								<c:if test="${perambulate.devicetype == 'server'}">服务器</c:if>
								<c:if test="${perambulate.devicetype == 'device'}">卡口</c:if>
								<c:if test="${perambulate.devicetype == 'cabinet'}">智能机柜</c:if>
								<c:if test="${perambulate.devicetype == 'database'}">数据库</c:if>
								<c:if test="${perambulate.devicetype == 'ftp'}">FTP</c:if>
								<c:if test="${perambulate.devicetype == 'project'}">平台</c:if>
							</td>
							<td>${perambulate.position}</td>
							<td>${perambulate.reason}</td>
							<%-- <td>${perambulate.note}</td> --%>
						</tr>	
							  </c:forEach>
							  <c:if test="${pageList.result != null}">
								<c:forEach begin="1" end="${8-fn:length(pageList.result)}">
									<tr>
										<td colspan="9">&nbsp;</td>
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
         <h4 class="title_intro"><span><img src="${root}/images/picone/zhuxing.png"></span>本周-统计频发故障设备类型柱状图</h4>
         <div id="diagram" style="min-height:310px;margin-bottom:-10px;">
         <div id="chart-column"></div>
         </div>
       </div>
       <div class="diagram pull-left" style="width: 49.8%;">
         <h4 class="title_intro"><span><img src="${root}/images/picone/bingtu.png"></span>本周-统计巡检人员工作量饼状图</h4>
         <div id="diagram" style="min-height:310px;margin-bottom:-10px;">
         <div id="chart-column1"></div>
         </div>
       </div>
       <div class="clear"></div>
    </div>
		
	</div>
</div>
<textarea id="chart-xml" style="display: none">
<chart caption='设备类型故障统计' aboutMenuItemLabel='西安翔迅科技有限公司'  aboutMenuItemLink=''  bgColor='#FBFBFB'  formatNumber='1'  numberSuffix='次'
  rotateYAxisName='0'  baseFont='微软雅黑'  baseFontSize='12'  outCnvBaseFont='微软雅黑'  outCnvBaseFontSize='12'  decimalPrecision='0'  
  chartLeftMargin='5'  shownames='1'  showValues='1'  animation='1'  formatNumberScale='0'  showPercentageValues='0'  startingAngle='45'  
  slicingDistance='1'  enableSmartLabels='1'  enableRotation='1'  >
	    <c:forEach items="${getcountbydevicetype}" var="data">
			<set name='${data['TYPES']}' value='${data['COUNTNUM']}'/>
		</c:forEach>
</chart>
</textarea>
<textarea id="chart-xml1" style="display: none">
<chart caption='巡检人员工作量统计' aboutMenuItemLabel='西安翔迅科技有限公司'  aboutMenuItemLink=''  bgColor='#FBFBFB'  formatNumber='1'  numberSuffix='次'
  rotateYAxisName='0'  baseFont='微软雅黑'  baseFontSize='12'  outCnvBaseFont='微软雅黑'  outCnvBaseFontSize='12'  decimalPrecision='0'  
  chartLeftMargin='5'  shownames='1'  showValues='1'  animation='1'  formatNumberScale='0'  showPercentageValues='0'  startingAngle='45'  
  slicingDistance='1'  enableSmartLabels='1'  enableRotation='1'  >
	    <c:forEach items="${getcountbyuser}" var="data">
			<set name='${data['NAME']}' value='${data['COUNTNUM']}'/>
		</c:forEach>
</chart>
</textarea>

<script>
	

    // 页面初始化加载
	$(document).ready(function(){
		//initflashchar();
		getChart1XmlData();
		getChart2XmlData();//初始化图表
		
	});

	
	
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
	    var chart = new FusionCharts("${root}/compnents/fusioncharts/chart/Pie2D.swf", "Chart2Id","100%","350");
		var total = '${unfirmSize}';
		if (total == '0') {
			$("#chart-column1").html("<center style='margin-top:150px;color:blue'>无数据</center>");
		} else {
			chart.setDataXML($("#chart-xml1").val());
			chart.render("chart-column1");
		}
	}
	
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf"%>