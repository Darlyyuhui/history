<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body onmousedown="whichButton(event)">
	<div style="overflow-x:scroll;">
		<table class="table table-striped table-bordered table-condensed table-style" id="table">
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
				<c:forEach begin="1" end="15">
					<tr>
						<td colspan="100">&nbsp;</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
