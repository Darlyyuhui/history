<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
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
<div class="page-header">
    <h1>
      土壤分析数据统计
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            统计查询
        </small>
    </h1>
</div>
<div style="margin:10px 0">
    <form class="form-search" action="${root}/statistics/analysis/landCount/${menuid}/" method="post">
        <div class="col-xs-12 alert alert-info" style="padding:2px;margin-bottom:2px;">
            <div style="width:800px; margin-top:3px;">
            	<input type="hidden" name="regionId" value="${regionId }" />
                	采样时间：
                   	<input id="beginTime" name="beginTime" type="text" value="${beginTime }"
						class="input-large" readonly="readonly" style="width: 180px;"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,maxDate:document.getElementById('endTime').value==''?'':document.getElementById('endTime').value})" />
					至
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
                            <i class="ace-icon fa fa-refresh icon-on-right bigger-110"></i>
                        </button>
                        
						<button type="button" onclick="doExport()" class="btn btn-success btn-sm" style="margin-left:1px;">
                            导出
                            <i class="ace-icon fa fa-download icon-on-right bigger-110"></i>
                        </button>
                    </span>
            </div>
        </div>
    </form>
</div>
<div class="row" style="margin-top:1px;">
    <div class="col-xs-12">
        <table id="table" class="table table-striped table-bordered table-hover table-style" style="text-align: center">
            <thead>
              <tr>
			    <th rowspan="2">行政区域</th>
			    <th colspan="5">PH</th>
			    <th colspan="6">总镉</th>
			    <th colspan="5">有效镉</th>
			  </tr>
			  <tr>
			    <th>最小值</th>
			    <th>最大值</th>
			    <th>平均值</th>
			    <th>标准差</th>
			    <th>变异系数(%)</th>
			    <th>最小值</th>
			    <th>最大值</th>
			    <th>平均值</th>
			    <th>标准差</th>
			    <th>变异系数(%)</th>
			    <th>超标率(%)</th>
			    <th>最小值</th>
			    <th>最大值</th>
			    <th>平均值</th>
			    <th>标准差</th>
			    <th>变异系数(%)</th>
			  </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${countList}" var="item">
                <tr>
				  	<td><tags:xiangxuncache keyName="TREGION_NAME" id="${item.regionId }"/></td>
				  	<td><fmt:formatNumber pattern="#0.00" value="${item.minPh }" /></td>
				    <td><fmt:formatNumber pattern="#0.00" value="${item.maxPh }" /></td>
				    <td><fmt:formatNumber pattern="#0.00" value="${item.avgPh }" /></td>
				    <td><fmt:formatNumber pattern="#0.00" value="${item.stdevPh }" /></td>
				    <td><fmt:formatNumber pattern="#0.00" value="${item.byPh }" /></td>
				    <td><fmt:formatNumber pattern="#0.00" value="${item.minCd }" /></td>
				    <td><fmt:formatNumber pattern="#0.00" value="${item.maxCd }" /></td>
				    <td><fmt:formatNumber pattern="#0.00" value="${item.avgCd }" /></td>
				    <td><fmt:formatNumber pattern="#0.00" value="${item.stdevCd }" /></td>
				    <td><fmt:formatNumber pattern="#0.00" value="${item.byCd }" /></td>
				    <td><fmt:formatNumber pattern="#0.00" value="${item.cbCd }" /></td>
				    <td><fmt:formatNumber pattern="#0.00" value="${item.minACd }" /></td>
				    <td><fmt:formatNumber pattern="#0.00" value="${item.maxACd }" /></td>
				    <td><fmt:formatNumber pattern="#0.00" value="${item.avgACd }" /></td>
				    <td><fmt:formatNumber pattern="#0.00" value="${item.stdevACd }" /></td>
				    <td><fmt:formatNumber pattern="#0.00" value="${item.byACd }" /></td>
				</tr>
            </c:forEach>
            <c:if test="${countList!=null}">
                <c:forEach begin="1" end="${18-fn:length(countList)}">
                    <tr>
                        <td colspan="68">&nbsp;</td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
    <form id="exportForm" action="${root}/statistics/analysis/doLandExport/" method="post">
    	<input type="hidden" name="regionId" value="${regionId }" />
    	<input type="hidden" name="beginTime" value="${beginTime }" />
    	<input type="hidden" name="endTime" value="${endTime }" />
    </form>
</div>
<script type="text/javascript">
function resetSearValue() {
	$("#beginTime").val("");
	$("#endTime").val("");
}
function doExport() {
	$("#exportForm").submit();
}
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>