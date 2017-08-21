<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<table class='table table-striped table-bordered table-condensed table-style' id='table' style="overflow: scroll;">
	<thead>
		<tr>
			<th width="20">序号</th>
			<th>错误信息</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${errors}" var="error" varStatus="c">
			<tr height="28">
				<td>&nbsp;${c.index+1}</td>
				<td>&nbsp;${error.value}</td>
			</tr>
		</c:forEach>
		<c:if test="${errors==null}">
			<c:forEach begin="1" end="13">
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>