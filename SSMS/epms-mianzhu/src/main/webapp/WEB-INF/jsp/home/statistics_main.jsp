<%@ page pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="${root}/compnents/ace/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${root}/compnents/ace/css/font-awesome.min.css"/>
<!-- page specific plugin styles -->
<!-- text fonts -->
<link rel="stylesheet" href="${root}/compnents/ace/css/ace-fonts.css"/>

<!-- ace styles -->
<link rel="stylesheet" href="${root}/compnents/ace/css/ace.min.css" id="main-ace-style"/>

<!--[if lte IE 9]>
<link rel="stylesheet" href="${root}/compnents/ace/css/ace-part2.min.css"/>
<![endif]-->
<link rel="stylesheet" href="${root}/compnents/ace/css/ace-skins.min.css"/>
<link rel="stylesheet" href="${root}/compnents/ace/css/ace-rtl.min.css"/>
<!--[if lte IE 9]>
<link rel="stylesheet" href="${root}/compnents/ace/css/ace-ie.min.css"/>
<![endif]-->
<!-- inline styles related to this page -->
<!-- ace settings handler -->
<script src="${root}/compnents/ace/js/jquery-1.7.2.min.js"></script>
<script src="${root}/compnents/ace/js/ace-extra.min.js"></script>
<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
<!--[if lte IE 8]>
<script src="${root}/compnents/ace/js/html5shiv.min.js"></script>
<script src="${root}/compnents/ace/js/respond.min.js"></script>
<![endif]-->
<style type="text/css">
.chart_title {
	text-align: center;
	font-size: 18px;
}
</style>

<div style="background-color:#fff;" id="wrap">
    <div class="row" style="margin-top:1px;">
	    <div class="col-xs-6" >
	    	<div class="chart_title">全市土壤污染面积</div>
	        <div id="pieChart" style="width:100%; height:450px; padding:10px;"></div>
	        
	        <div class="chart_title">各乡镇土壤污染因子分析</div>
	        <div id="lineChart" style="width:100%; height:450px; padding:10px;"></div>
	    </div>
	    <div class="col-xs-6" >
	    	<div class="chart_title">全市土壤酸碱度分级统计</div>
	        <div id="phChart" style="width:100%; height:300px; padding:10px;"></div>
	        
	        <div class="chart_title">全市土壤总镉污染指数统计</div>
	        <div id="cdChart" style="width:100%; height:300px; padding:10px;"></div>
	        
	        <div class="chart_title">全市土壤有效态镉分布统计</div>
	        <div id="acdChart" style="width:100%; height:300px; padding:10px;"></div>
	    </div>
	</div>
</div>

<script type="text/javascript">
var chart1Opt = ${pieOpt};
var chart2Opt = ${lineOpt};
var chart3Opt = ${phOpt};
var chart4Opt = ${cdOpt};
var chart5Opt = ${acdOpt};
$(function() {
	var chart1 = echarts.init(document.getElementById("pieChart"), "shine");
	chart1.setOption(chart1Opt);
	
	var chart2 = echarts.init(document.getElementById("lineChart"), "shine");
	chart2.setOption(chart2Opt);
	
	var chart3 = echarts.init(document.getElementById("phChart"), "shine");
	chart3.setOption(chart3Opt);
	
	var chart4 = echarts.init(document.getElementById("cdChart"), "shine");
	chart4.setOption(chart4Opt);
	
	var chart5 = echarts.init(document.getElementById("acdChart"), "shine");
	chart5.setOption(chart5Opt);
	
	$("#wrap").css("height",$("#wrap").parent().height()+"px");
});
</script>

<!--[if IE]>
<script type="text/javascript">
window.jQuery || document.write("<script src='${root}/compnents/ace/js/jquery1x.min.js'>" + "<" + "/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='${root}/compnents/ace/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script src="${root}/compnents/ace/js/bootstrap.min.js"></script>
<!-- page specific plugin scripts -->
<!--[if lte IE 8]>
<script src="${root}/compnents/ace/js/excanvas.min.js"></script>
<![endif]-->
<!-- ace scripts -->
<script src="${root}/compnents/ace/js/ace-elements.min.js"></script>
<script src="${root}/compnents/ace/js/ace.min.js"></script>
<script src="${root}/compnents/ECharts/echarts.min.js" type="text/javascript"></script>
<script src="${root}/compnents/ECharts/theme/shine.js" type="text/javascript"></script>
