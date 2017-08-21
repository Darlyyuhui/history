<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body onmousedown="whichButton(event)">
<table class="table table-striped table-bordered table-condensed table-style" id="table">
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
		<c:forEach begin="1" end="19">
			<tr>
				<td colspan="31">&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>
</body>
<script type="text/javascript">
	function whichButton(event){
	    eval('window.parent.datacontrol()');
	}
</script>