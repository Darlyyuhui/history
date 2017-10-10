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
      农产品查询
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            数据查询
        </small>
    </h1>
</div>
<div style="margin:10px 0">
    <form class="form-search" action="${root}/statistics/apb/list/${menuid}/" method="post">
        <div class="col-xs-12 alert alert-info" style="padding:2px;margin-bottom:2px;">
            <div style="width:800px; margin-top:3px;">
                	产品类型：
                   	<input type="text" class="input-large" id="typeName" name="search_typeName" value="${typeName }" />
					种植基地：
                   	<input type="text" class="input-large" id="apbName" name="search_apbName" value="${apbName }" />
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
			    <th>产品编号</th>
			    <th>产品名称</th>
			    <th>产品类型</th>
			    <th>种植基地</th>
			    <th>描述</th>
			  </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${pageList.result}" var="item">
                <tr>
				  	<td>${item.code }</td>
				  	<td>${item.name }</td>
				  	<td>${item.typeName }</td>
				  	<td>${item.apbNames }</td>
				  	<td align="center">
				  		<div style="width:280px;height:22px;margin-top:2px;white-space:nowrap;overflow:hidden;-o-text-overflow:ellipsis;text-overflow:ellipsis;"
                            title="${item.explain }">${item.explain }</div>
				  	</td>
				</tr>
            </c:forEach>
            <c:if test="${pageList.result!=null}">
                <c:forEach begin="1" end="${15-fn:length(pageList.result)}">
                    <tr>
                        <td colspan="68">&nbsp;</td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <div class="row">
            <c:if test="${pageList!=null}">
                <tags:pagination page="${pageList}"></tags:pagination>
            </c:if>
        </div>
    </div>
</div>
<script type="text/javascript">
function resetSearValue() {
	$("#typeName").val("");
	$("#apbName").val("");
}
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>