<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script language="javascript" src="${root}/js/LodopFuncs.js"></script>
<body onmousedown="whichButton(event)">
<div class="btn-group-wrap mar_b5 print_line">
	<div class="btn-group pull-right">
		<btn:authorBtn menuid="${menuid}" text="导出">
			<button class="btn btn-small" onclick="doExport();">
				<i class="icon-download"></i>导出
			</button>
		</btn:authorBtn>
	</div>
	<div class="clear"></div>
</div>
<div class="mar_b5" style="overflow:auto;">
<table class="table table-striped table-bordered table-condensed table-style mar_t35" id="table">
	<thead>
		<tr>
			<th>服务商名称</th>
			<!-- <th>卡口数量</th> -->
			<th>监控数量</th>
			
			<th>服务器数量</th>
			<th>数据库数量</th>
			<th>FTP数量</th>
			<th>平台数量</th>
			<th>机柜数量</th>
			<th>总数</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageList.result}" var="report" varStatus="k">
			<tr onclick="rowOnclick(this)">
				<td>${report.factoryName}</td>
				<c:forEach items="${report.values}"  var="value">
					<td>
						${value}
					</td>
				</c:forEach>
				<td>${report.totals}</td>
			</tr>
		</c:forEach>
		<c:if test="${pageList.result!='[]'}">
			<c:if test="${showTotal=='1'}">
				<tr>
					<td>合计</td>
					<c:forEach items="${mTotals.values}"  var="value">
						<td>
							${value}
						</td>
					</c:forEach>
					<td>${mTotals.totals}</td>
				</tr>
			</c:if>
		</c:if>
		<c:if test="${pageList.result!='[]'}">
			<c:choose>
				<c:when test="${totalPage==curPageNo || totalPage==1}">
					<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
						<tr>
							<td colspan="8">&nbsp;</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
						<tr>
							<td colspan="8">&nbsp;</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</c:if>
		<c:if test="${pageList.result=='[]'}">
			<tr>
				<td colspan="8">
					<div style='text-align: center;margin-right: 40px;'>
						<font color='red'>没有查到结果，请重新查询或者改变查询条件。</font>
					</div>
				</td>
			</tr>
			<c:forEach begin="1" end="14">
				<tr>
					<td colspan="8">&nbsp;</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
</div>
<div class="row-fluid">
	<c:if test="${pageList.result!='[]'}">
		<tags:paginationnew page="${pageList}" pageaction="${root}/sergrade/count/form/search/${menuid}/" ></tags:paginationnew>
	</c:if>
</div>

<form method="post" name="form1" id="form1">
	<input type="hidden" id="factoryName" name="post_factoryName"/>
</form>
<script type="text/javascript">
function whichButton(event){
    eval('window.parent.datacontrol()');
}
</script>
<script>
	//导出
	function doExport(){
	    document.form1.action="${root}/sergrade/count/export/doExport/${menuid}/";
	    document.form1.submit();
	    
	}
	
</script>
