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
<table class="table table-striped table-bordered  table-condensed table-style mar_t35" id="table">
	<thead>
		<tr>

		<th  rowspan="2">故障类型</th>
		<c:forEach items="${days}" var="day">
				<th><tags:xiangxundate date="${day}"></tags:xiangxundate></th>
		</c:forEach>
		<th  rowspan="2">总数</th>
		</tr>
		<tr>
			<c:forEach items="${days}" var="day">
				<th>${day}</th>
			</c:forEach>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageList.result}" var="weekReport" varStatus="k">
			<tr onclick="rowOnclick(this)">
				<td>${weekReport.typeName}</td>
				
				<td>${weekReport.d01}</td>
				<td>${weekReport.d02}</td>
				<td>${weekReport.d03}</td>
				<td>${weekReport.d04}</td>
				<td>${weekReport.d05}</td>
				<td>${weekReport.d06}</td>
				<td>${weekReport.d07}</td>
				<td>${weekReport.totals}</td>
			</tr>
		</c:forEach>
		<c:if test="${pageList.result!='[]'}">
			<c:if test="${showTotal=='1'}">
				<tr>
					<td>合计</td>

					<td>${D01}</td>
					<td>${D02}</td>
					<td>${D03}</td>
					<td>${D04}</td>
					<td>${D05}</td>
					<td>${D06}</td>
					<td>${D07}</td>
					<td>${D01+D02+D03+D04+D05+D06+D07}</td>
				</tr>
			</c:if>
		</c:if>
		<c:if test="${pageList.result!='[]'}">
			<c:choose>
				<c:when test="${totalPage==curPageNo || totalPage==1}">
					<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
						<tr>
							<td colspan="13">&nbsp;</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
						<tr>
							<td colspan="13">&nbsp;</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>

		</c:if>
		<c:if test="${pageList.result=='[]'}">
			<tr>
				<td colspan="13">
					<div style='text-align: center;margin-right: 40px;'>
						<font color='red'>没有查到结果，请重新查询或者改变查询条件。</font>
					</div>
				</td>
			</tr>
			<c:forEach begin="1" end="14">
				<tr>
					<td colspan="13">&nbsp;</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
</div>
<div class="row-fluid">
	<c:if test="${pageList.result!='[]'}">
		<tags:paginationnew page="${pageList}" pageaction="${root}/question/trend/form/search/${menuid}/" ></tags:paginationnew>
	</c:if>
</div>

<form method="post" name="form1" id="form1">
	<input type="hidden" id="beginDate" name="post_beginDate"/>
	
	<input type="hidden" id="sortType" name="post_sortType" value=""/>
	<input type="hidden" id="stateType" name="post_stateType" value="2" />
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
	
	    document.form1.action="${root}/question/trend/export/doExport/${menuid}/?tag=week"
	    document.form1.submit();
	    
	}
</script>
