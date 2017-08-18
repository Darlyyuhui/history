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
    <form class="form-search" action="${root}/statistics/land/query/${menuid}/" method="post">
        <div class="col-xs-12 alert alert-info" style="padding:2px;margin-bottom:2px;">
            <div style="width:800px; margin-top:3px;">
            	<input type="hidden" name="regionId" value="${regionId }" />
                	开始时间：
                   	<input id="beginTime" name="beginTime" type="text" value="${beginTime }"
						class="input-large" readonly="readonly" style="width: 180px;"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,maxDate:document.getElementById('endTime').value==''?'':document.getElementById('endTime').value})" />
					结束时间：
                   	<input id="endTime" name="endTime" type="text" value="${endTime }"
						class="input-large" readonly="readonly" style="width: 180px;"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,minDate:document.getElementById('beginTime').value})" />
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
        <div id="pieChart" style="width:100%; height:500px; padding:10px;"></div>
    </div>
    <div class="col-xs-6" >
    	<div class="chart_title">${title2 }土壤污染因子分析</div>
        <div id="lineChart" style="width:100%; height:500px; padding:10px;"></div>
    </div>
</div>
<script type="text/javascript">
var chart1Opt = ${pieOpt};
var chart2Opt = ${lineOpt};
$(function() {
	var chart1 = echarts.init(document.getElementById("pieChart"), "shine");
	chart1.setOption(chart1Opt);
	
	var chart2 = echarts.init(document.getElementById("lineChart"), "shine");
	chart2.setOption(chart2Opt);
});

function resetSearValue() {
	$("#beginTime").val("");
	$("#endTime").val("");
}
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>