<%@ page language="java" pageEncoding="UTF-8"%>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none;">
	<p id="alert-content" align="center"></p>
</div>
<div class="conten_box">
	<h4 class="title_intro">授权角色</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/system/role/doGrantRoles" 
	method="post" style="padding:0;margin:0;">
		<input type="hidden" name="menuid" value="${menuid}" />
		<input type="hidden" name="page" value="${page}" />
		<input type="hidden" name="userids" value="${userids}"/>
		<div class="mar_l10 mar_r10">
			<div style="padding:15px 10px;">待授权的账号(姓名)：${accounts}</div>
			<table class="table table-striped table-bordered table-style" id="table">
				<thead>
					<tr>
						<th width="30"></th>
						<th>角色名称</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${roleList}" var="role">
						<tr>
							<td style="text-align:center;"><input type="checkbox" value="${role.id}" name="select-chk" /></td>
							<td>${role.name}</td>
							<td class="texthidden"><div style="text-overflow:ellipsis;overflow:hidden;width:350px">${role.memo}</div></td>
						</tr>
					</c:forEach>
					<c:if test="${roleList!=null}">
					<c:if test="${fn:length(roleList)<15}">
						<c:forEach begin="1" end="${15-fn:length(roleList)}">
							<tr height="28">
								<td colspan="3">&nbsp;</td>
							</tr>
						</c:forEach>
					</c:if>
					</c:if>
				</tbody>
			</table>
		</div>
		<div class="btn_line">
			<input id="submit_btn" class="btn btn-info" type="button" value="确定" onclick="submitForm()" />
			<input id="cancel_btn" class="btn mar_l10" type="button" value="返回" onclick="showList()" />
		</div>
	</form>
</div>
<script>
	function submitForm() {
		var roles = getSelectedValue();
		if (roles.length == 0) {
			parent.showMessage("请选择要授权的角色");
		} else {
			var url = "${root}/system/user/doGrantRoles/${userids}/" + roles + "/";
			$("#inputForm").attr("action", url);
			$("#inputForm").submit();
		}
	}

	function showList() {
		window.location.href = "${root}/system/user/list/${menuid}/";
	}
</script>

