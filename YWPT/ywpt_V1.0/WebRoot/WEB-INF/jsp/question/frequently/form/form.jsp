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
			<th>厂商名称</th>
			<c:forEach items="${types}" var="type">
				<th>${type}</th>
			</c:forEach>
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
							<td colspan="100">&nbsp;</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
						<tr>
							<td colspan="100">&nbsp;</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</c:if>
		<c:if test="${pageList.result=='[]'}">
			<tr>
				<td colspan="100">
					<div style='text-align: center;margin-right: 40px;'>
						<font color='red'>没有查到结果，请重新查询或者改变查询条件。</font>
					</div>
				</td>
			</tr>
			<c:forEach begin="1" end="14">
				<tr>
					<td colspan="100">&nbsp;</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
</div>
<div class="row-fluid">
	<c:if test="${pageList.result!='[]'}">
		<tags:paginationnew page="${pageList}" pageaction="${root}/question/frequently/form/search/${menuid}/" ></tags:paginationnew>
	</c:if>
</div>

<form method="post" name="form1" id="form1">
	<input type="hidden" id="beginDate" name="post_beginDate"/>
	<input type="hidden" id="endDate" name="post_endDate"/>
	<input type="hidden" id="sortType" name="post_sortType" value="breakdown_time desc"/>
	<input type="hidden" id="stateType" name="post_stateType"/>
</form>
<script type="text/javascript">
function whichButton(event){
    eval('window.parent.datacontrol()');
}
</script>
<script>
	//导出
	function doExport(){
		//统计方式
		var stateType = "";
		//统计方式 获取单选按钮的value值
		var search_stateType = window.parent.document.getElementsByName("search_stateType");
		for(var i=0;i<search_stateType.length;i++){
			if(search_stateType[i].checked){
				stateType = search_stateType[i].value;
			}
		}
		document.getElementById("beginDate").value = window.parent.document.getElementById("beginDate").value;
		//统计开始日期
		var beginDate = window.parent.document.getElementById("beginDate").value;
		if (beginDate.length == 0) {
				window.parent.showMessage("请选择统计日期，查询数据后重新导出！");
				return false;
			}
		if (stateType == "space") {
			document.getElementById("endDate").value = window.parent.document.getElementById("endDate").value;
			//按时间段查询 结束日期
			var endDate = window.parent.document.getElementById("endDate").value;
			if (beginDate.length == 0 || endDate.length == 0) {
				showMessage("请选择统计日期，查询数据后重新导出！");
				return false;
			}
			
		}
		document.getElementById("stateType").value = stateType;
		
	    document.form1.action="${root}/question/frequently/export/doExport/${menuid}/?tag="+stateType;
	    document.form1.submit();
	    
	}
	
</script>
