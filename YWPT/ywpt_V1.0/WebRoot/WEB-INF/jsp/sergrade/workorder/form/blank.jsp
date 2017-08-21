<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body onmousedown="whichButton(event)">
<table class="table table-striped table-bordered table-condensed table-style" id="table">
	<thead>
		<tr>
			<th rowspan="2" >服务商名称</th>
			<th colspan="3" >${date}</th>

			<th colspan="3" >总数</th>

		</tr>
		<tr>
			<c:forEach begin="1" end="2">
				<th>总工单数</th>
				<th>已解决</th>
				<th>未解决</th>
			</c:forEach>
		</tr>
	</thead>
	<tbody>
		<c:forEach begin="1" end="13">
			<tr>
				<td colspan="100">&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
<script type="text/javascript">
	function whichButton(event){
	    eval('window.parent.datacontrol()');
	}
</script>