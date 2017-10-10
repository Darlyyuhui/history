<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${root}/compnents/ECharts/echarts.min.js" type="text/javascript"></script>
<script src="${root}/compnents/ECharts/theme/shine.js" type="text/javascript"></script>
<tags:selTree idElement="regionId" nameElement="regionName" treeType="region" />
<style type="text/css">
.chart_title {
	text-align: center;
	font-size: 18px;
}
</style>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
    <p id="alert-content" align="center"></p>
</div>
<c:if test="${not empty message}">
    <div id="message" class="alert alert-success">
        <button data-dismiss="alert" class="close">×</button>
        <p align="center">${message}</p>
    </div>
    <script>
        setTimeout('$("#message").hide("slow")', 1200);    </script>
</c:if>
<div class="page-header">
    <h1>
      土壤采样统计
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            统计查询
        </small>
    </h1>
</div>
<div style="margin:10px 0">
    <form class="form-search" action="${root}/statistics/land/queryYear/${menuid}/" method="post">
        <div class="col-xs-12 alert alert-info" style="padding:2px;margin-bottom:2px;">
            <div style="width:800px; margin-top:3px;">
            		<input type="hidden" name="year" value="${year }" />
                	选择乡镇：
                   	<input type="text" id="regionName" name="regionName" readonly="readonly" value='<tags:xiangxuncache keyName="TREGION_NAME" id="${regionId }"/>'
						class="input-large gj-query-input" onclick="showRegion('regionId','regionName')"/>
					<input type="hidden" id="regionId" name="regionId" value="${regionId }" />
                <span>
                        <button type="submit" class="btn btn-purple btn-sm" style="margin-left:1px;">
                            查询
                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                        </button>

                        <button type="button" onclick="resetSearValue()" class="btn btn-info btn-sm" style="margin-left:1px;">
                            重置
                        </button>

                    </span>
            </div>
        </div>
    </form>
</div>
<div class="row" style="margin-top:1px;">
    <div class="col-xs-6" >
    	<div class="chart_title">${title1 }土壤污染面积</div>
        <div id="pieChart" style="width:100%; height:450px; padding:10px;"></div>
        
        <div class="chart_title">${title2 }土壤污染因子分析</div>
        <div id="lineChart" style="width:100%; height:450px; padding:10px;"></div>
    </div>
    <div class="col-xs-6" >
    	<div class="chart_title">${title1 }土壤酸碱度分级统计</div>
        <div id="phChart" style="width:100%; height:300px; padding:10px;"></div>
        
        <div class="chart_title">${title1 }土壤总镉污染指数统计</div>
        <div id="cdChart" style="width:100%; height:300px; padding:10px;"></div>
        
        <div class="chart_title">${title1 }土壤有效态镉分布统计</div>
        <div id="acdChart" style="width:100%; height:300px; padding:10px;"></div>
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
});
function resetSearValue() {
	$("#regionId").val("");
	$("#regionName").val("");
}
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>