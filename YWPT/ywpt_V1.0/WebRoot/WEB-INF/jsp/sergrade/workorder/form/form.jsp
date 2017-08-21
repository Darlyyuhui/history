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
			<th rowspan="2" style="vertical-align:middle;">服务商名称</th>
			
			<c:forEach items="${days}" var="day">
				<th colspan="3">${day}</th>
			</c:forEach>

			<th colspan="3">总数</th>

		</tr>
		<tr>
			<c:forEach items="${days}" var="day">
				<th>总工单数</th>
				<th>已解决</th>
				<th>未解决</th>
			</c:forEach>
			<th>总工单数</th>
			<th>已解决</th>
			<th>未解决</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageList.result}" var="report" varStatus="k">
			<tr onclick="rowOnclick(this)">
				<td>
					<tags:xiangxuncache keyName="FactoryInfo" id="${report.factoryid}"></tags:xiangxuncache>
				</td>
				
				<c:forEach items="${report.dayCounts}" var="dayCount">
					<td>${dayCount[0]}</td>
					<td>${dayCount[1]}</td>
					<td>${dayCount[2]}</td>
				</c:forEach>
				<td>${report.totals}</td>
				<td>${report.ctotals}</td>
				<td>${report.ototals}</td>
			</tr>
		</c:forEach>
		<c:if test="${pageList.result!='[]'}">
			<c:if test="${showTotal=='1'}">
				<tr>
					<td>合计</td>
					<c:forEach items="${mTotals.dayCounts}" var="dayCount">
						<td>${dayCount[0]}</td>
						<td>${dayCount[1]}</td>
						<td>${dayCount[2]}</td>
					</c:forEach>
					<td>${mTotals.totals}</td>
					<td>${mTotals.ctotals}</td>
					<td>${mTotals.ototals}</td>
				</tr>
			</c:if>
		</c:if>
		<c:if test="${pageList.result!='[]'}">
			<c:choose>
				<c:when test="${totalPage==curPageNo || totalPage==1}">
					<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
						<tr>
							<td colspan="200">&nbsp;</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
						<tr>
							<td colspan="200">&nbsp;</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>

		</c:if>
		<c:if test="${pageList.result=='[]'}">
			<tr>
				<td colspan="200">
					<div style='text-align: center;margin-right: 40px;'>
						<font color='red'>没有查到结果，请重新查询或者改变查询条件。</font>
					</div>
				</td>
			</tr>
			<c:forEach begin="1" end="14">
				<tr>
					<td colspan="200">&nbsp;</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
</div>
<div class="row-fluid">
	<c:if test="${pageList.result!='[]'}">
		<tags:paginationnew page="${pageList}" pageaction="${root}/sergrade/workorder/search/${menuid}/" ></tags:paginationnew>
	</c:if>
</div>

<form method="post" name="form1" id="form1">
	<input type="hidden" id="beginDate" name="post_beginDate"/>
	<input type="hidden" id="endDate" name="post_endDate"/>
	<input type="hidden" id="factoryName" name="post_factoryName"/>
	<input type="hidden" id="sortType" name="post_sortType" value="factoryid"/>
</form>
</body>
<script type="text/javascript">
	function whichButton(event){
	    eval('window.parent.datacontrol()');
	}
</script>
<script>
	//导出
	function doExport(){
		document.getElementById("beginDate").value=window.parent.document.getElementById("beginDate").value;
		document.getElementById("endDate").value=window.parent.document.getElementById("endDate").value;
		document.getElementById("factoryName").value=window.parent.document.getElementById("factoryName").value;
	    document.form1.action="${root}/sergrade/workorder/export/doExport/${menuid}/"
	    document.form1.submit();
	    
	}
	
</script>
