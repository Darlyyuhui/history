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
      土壤修复数据统计
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            统计查询
        </small>
    </h1>
</div>
<div style="margin:10px 0">
    <form class="form-search" action="${root}/statistics/repair/count/${menuid}/" method="post">
        <div class="col-xs-12 alert alert-info" style="padding:2px;margin-bottom:2px;">
            <div style="width:800px; margin-top:3px;">
            	<input type="hidden" name="regionId" value="${regionId }" />
                	修复项目开始时间：
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
			    <th rowspan="2">区域地块总面积(亩)</th>
			    <th colspan="2">修复完成</th>
			    <th colspan="${fn:length(dicMsgs) }">修复中</th>
			  </tr>
			  <tr>
			    <th>面积(亩)</th>
			    <th>占比(%)</th>
			    <c:forEach items="${dicMsgs }" var="d">
			    	<th>${d[1] }(亩)</th>
			    </c:forEach>
			  </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${countList}" var="item">
                <tr>
				  	<td><tags:xiangxuncache keyName="TREGION_NAME" id="${item.regionId }"/></td>
				  	<td>${item.total }</td>
				    <td>${item.end }</td>
				    <td><fmt:formatNumber pattern="#0.00" value="${item.endProp }" /></td>
				    <c:forEach items="${dicMsgs }" varStatus="vs">
				    	<c:set var="perp" value="stage${vs.index+1 }" scope="page" />
				    	<td>${item[perp] }</td>
				    </c:forEach>
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
</div>
<script type="text/javascript">
function resetSearValue() {
	$("#beginTime").val("");
	$("#endTime").val("");
}
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>