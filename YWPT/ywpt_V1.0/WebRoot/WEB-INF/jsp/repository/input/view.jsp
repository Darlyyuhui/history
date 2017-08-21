<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box" style="margin-right:0; margin-left:180px;">
	<h4 class="xtcs_h4" style="margin:0;">知识库-详情</h4>
	<div class="mar_5">
		<table class="table table-border-bot table-border-rl bukong-table" width="100%">
			<tr>
				<td class="device_td_bg3" width="10%">文件名称：</td>
				<td width="40%"><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:22px;" title="${knowledge.name}">${knowledge.name}</div></td>
				<td class="device_td_bg3" width="10%">知识类别：</td>
				<td width="40%"><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:22px;" title="${knowledge.knowledgetype}">${knowledge.knowledgetype}</div></td>
			</tr>
			<tr>
				<td class="device_td_bg3">创建人：</td>
				<td><tags:xiangxuncache keyName="username_cache" id="${knowledge.operator}"></tags:xiangxuncache></td>
				<td class="device_td_bg3">创建时间：</td>
				<td><fmt:formatDate value="${knowledge.createtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
			<tr>
				<td class="device_td_bg3">文件内容：</td>
				<td><div style="height: 300px;overflow: auto;"><jsp:include page="context.jsp"></jsp:include></div></td>
				<c:if test="${workorder != null}">
					<td></td>
					<td></td>
				</c:if>
				<c:if test="${workorder == null}">
					<td class="device_td_bg3">备&nbsp;&nbsp;注：</td>
					<td>${knowledge.note}</td>
				</c:if>
			</tr>
		</table>
    </div>
	<div class="btn_line">
		<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
	</div>
</div>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
