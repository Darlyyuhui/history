<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body onmousedown="whichButton(event)">
	<div style="overflow-x:scroll;">
		<table class="table table-striped table-bordered table-condensed table-style" id="table">
			<thead>
				<tr>
					<th>服务商名称</th>
					<th>卡口数量</th>
					<!-- <th>监控数量</th> -->
					<th>服务器数量</th>
					<th>数据库数量</th>
					<th>FTP数量</th>
					<th>平台数量</th>
					<th>机柜数量</th>
					<th>总数</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach begin="1" end="15">
					<tr>
						<td colspan="8">&nbsp;</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
