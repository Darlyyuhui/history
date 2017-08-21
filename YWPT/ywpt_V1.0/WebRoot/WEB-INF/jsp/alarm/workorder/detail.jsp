<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box" style="overflow:auto;">
  	<table class="table table-striped table-bordered table-condensed table-style" id="table">
		<thead>
			<tr>
				<th>序号</th>
				<th>操作账号</th>
				<th>操作姓名</th>
				<th>操作时间</th>
				<th>操作内容</th>
				<th>工单状态</th>
				<th>备注信息</th>
			</tr>
		</thead>
		<tbody id="tbody">
			<c:forEach items="${pageList.result}" var="worklog" varStatus="x">
				<tr onclick="rowOnclick(this)" data="${worklog.id}">
					<td>${x.index+1+(page-1)*10}</td>
					<td>${worklog.account}</td>
					<td><tags:xiangxuncache keyName="username_cache" id="${worklog.operator}"></tags:xiangxuncache></td>
					<td><fmt:formatDate value="${worklog.opertime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${worklog.opercontent}</td>
					<td>${worklog.statusHtml}</td>
					<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${worklog.note}">${worklog.note}</div></td>
				</tr>
			</c:forEach>
			<c:if test="${pageList.result!=null}">
				<c:forEach begin="1" end="${10-fn:length(pageList.result)}">
					<tr>
						<td colspan="15">&nbsp;</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<tags:pagination page="${pageList}"></tags:pagination>
</div>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>