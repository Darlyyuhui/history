<%@ page language="java" pageEncoding="UTF-8"%>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none;">
	<p id="alert-content" align="center"></p>
</div>
<div class="page-header">
    <h1>
        用户管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            对用户进行角色授权
        </small>
    </h1>
</div>

<div class="row">
	<form id="inputForm" class="form-horizontal" action="${root}/system/role/doGrantRoles" method="post" style="padding:0;margin:0;">
		<input type="hidden" name="menuid" value="${menuid}" />
		<input type="hidden" name="page" value="${page}" />
		<input type="hidden" name="userids" value="${userids}"/>
		<div class="mar_l10 mar_r10">
			<div style="padding:15px 10px;">待授权的账号(姓名)：${accounts}</div>
            <table id="table" class="table table-striped table-bordered table-hover table-style">
				<thead>
					<tr>
						<th width="30"></th>
						<th>角色名称</th>
						<th class="hidden-480">备注</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${roleList}" var="role">
						<tr onclick="rowOnclick(this)">
							<td>
                                <label class="position-relative">
                                    <input type="checkbox" class="ace" value="${role.id}"/>
                                    <span class="lbl"></span>
                                </label>
							</td>
							<td>${role.name}</td>
							<td class="hidden-480"><div style="text-overflow:ellipsis;overflow:hidden;width:350px">${role.memo}</div></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-3 col-md-9">
                <button class="btn btn-info" type="button" onclick="submitForm()">
                    <i class="ace-icon fa fa-check bigger-110"></i>
                    确 定
                </button>

                <button class="btn btn-info" type="button" onclick="showList()">
                    <i class="ace-icon fa fa-reply bigger-110"></i>
                    返回
                </button>
            </div>
        </div>
	</form>
</div>
<script>
	function submitForm() {
		var roles = getSelectedValue();
		if (roles.length == 0) {
			showMessage("请选择要授权的角色");
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

