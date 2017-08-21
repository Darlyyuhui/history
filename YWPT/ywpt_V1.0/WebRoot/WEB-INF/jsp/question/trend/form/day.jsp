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
			<th>故障类型</th>
			<th>0时</th>
			<th>1时</th>
			<th>2时</th>
			<th>3时</th>
			<th>4时</th>
			<th>5时</th>
			<th>6时</th>
			<th>7时</th>
			<th>8时</th>
			<th>9时</th>
			<th>10时</th>
			<th>11时</th>
			<th>12时</th>
			<th>13时</th>
			<th>14时</th>
			<th>15时</th>
			<th>16时</th>
			<th>17时</th>
			<th>18时</th>
			<th>19时</th>
			<th>20时</th>
			<th>21时</th>
			<th>22时</th>
			<th>23时</th>
			<th>总数</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageList.result}" var="dayReport" varStatus="k">
			<tr onclick="rowOnclick(this)">
				<td>${dayReport.typeName}</td>
				
				<td>${dayReport.h01}</td>
				<td>${dayReport.h02}</td>
				<td>${dayReport.h03}</td>
				<td>${dayReport.h04}</td>
				<td>${dayReport.h05}</td>
				<td>${dayReport.h06}</td>
				<td>${dayReport.h07}</td>
				<td>${dayReport.h08}</td>
				<td>${dayReport.h09}</td>
				<td>${dayReport.h10}</td>
				<td>${dayReport.h11}</td>
				<td>${dayReport.h12}</td>
				<td>${dayReport.h13}</td>
				<td>${dayReport.h14}</td>
				<td>${dayReport.h15}</td>
				<td>${dayReport.h16}</td>
				<td>${dayReport.h17}</td>
				<td>${dayReport.h18}</td>
				<td>${dayReport.h19}</td>
				<td>${dayReport.h20}</td>
				<td>${dayReport.h21}</td>
				<td>${dayReport.h22}</td>
				<td>${dayReport.h23}</td>
				<td>${dayReport.h24}</td>
				<td>${dayReport.totals}</td>
			</tr>
		</c:forEach>
		<c:if test="${pageList.result!='[]'}">
			<c:if test="${showTotal=='1'}">
				<tr>
					<td>合计</td>

					<td>${H01}</td>
					<td>${H02}</td>
					<td>${H03}</td>
					<td>${H04}</td>
					<td>${H05}</td>
					<td>${H06}</td>
					<td>${H07}</td>
					<td>${H08}</td>
					<td>${H09}</td>
					<td>${H10}</td>
					<td>${H11}</td>
					<td>${H12}</td>
					<td>${H13}</td>
					<td>${H14}</td>
					<td>${H15}</td>
					<td>${H16}</td>
					<td>${H17}</td>
					<td>${H18}</td>
					<td>${H19}</td>
					<td>${H20}</td>
					<td>${H21}</td>
					<td>${H22}</td>
					<td>${H23}</td>
					<td>${H24}</td>
					<td>${H01+H02+H03+H04+H05+H06+H07+H08+H09+H10+H11+H12+H13+H14+H15+H16+H17+H18+H19+H20+H21+H22+H23+H24}</td>
				</tr>
			</c:if>
		</c:if>
		<c:if test="${pageList.result != '[]'}">
			<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
				<tr>
					<td colspan="31">&nbsp;</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${pageList.result == '[]'}">
			<tr>
				<td colspan="31">
					<div style='text-align: center;margin-right: 40px;'>
						<font color='red'>没有查到结果，请重新查询或者改变查询条件。</font>
					</div>
				</td>
			</tr>
			<c:forEach begin="1" end="14">
				<tr>
					<td colspan="31">&nbsp;</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
  </table>
</div>
<div class="row-fluid">
	<c:if test="${pageList.result != '[]'}">
		<tags:paginationnew page="${pageList}" pageaction="${root}/question/trend/form/search/${menuid}/" ></tags:paginationnew>
	</c:if>
</div>
<form method="post" name="form1" id="form1">
	<input type="hidden" id="beginDate" name="post_beginDate"/>
	
	<input type="hidden" id="sortType" name="post_sortType" value=""/>
	<input type="hidden" id="stateType" name="post_stateType" value="1" />
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
		
	    document.form1.action="${root}/question/trend/export/doExport/${menuid}/?tag=day"
	    document.form1.submit();
	}
</script>
