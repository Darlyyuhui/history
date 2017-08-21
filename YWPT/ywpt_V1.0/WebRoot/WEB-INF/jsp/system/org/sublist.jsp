<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<c:if test="${not empty message}">
	<script type="text/javascript">
		$(document).ready(function(){
			if($("#submsg").val() != ""){
				parent.setMesg($("#submsg").val());
			}
			$("#submsg").val("");
		});
	</script>
</c:if>
<input id="submsg" type="hidden" name="submsg" value="${message}"/>
<div style="margin-right:0; margin-left:193px; min-height:550px; overflow-x:scroll;">
	<table class="table table-striped table-bordered table-condensed table-style" id="table">
		<thead>
			<tr>
				<th>序号</th>
				<th>部门名称</th>
				<th>部门编码</th>
				<th>上级部门名称</th>
				<th>级别</th>
				<th>状态</th>
			</tr>
		</thead>
		<tbody id="tbody">
			<c:forEach items="${pageList.result}" var="department">
				<tr>
					<td width="10%">${department.index}</td>
					<td width="30%">${department.name}</td>
					<td width="10%">${department.code}</td>
					<td width="30%"><tags:xiangxuncache keyName="Department" id="${department.parentid}"></tags:xiangxuncache></td>
					<td width="10%">${department.level}</td>
					<td>
						<c:if test="${department.status == '0'}">
							<font color="green">启用</font>
						</c:if>
						<c:if test="${department.status == '1'}">
							<font color="red">未启用</font>
						</c:if>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${pageList.result!=null}">
				<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
					<tr>
						<td colspan="6">&nbsp;</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<tags:pagination page="${pageList}"></tags:pagination>
</div>
