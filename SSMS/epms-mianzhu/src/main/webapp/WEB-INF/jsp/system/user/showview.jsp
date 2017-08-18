<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="page-header">
    <h1>
        查看用户
        <small><i class="ace-icon fa fa-angle-double-right"></i>查看用户详细信息</small>
    </h1>
</div>
<div class="row">
    <div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name">姓 名：</div>
					<div class="profile-info-value">${user.name}</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">拥有角色：</div>
					<div class="profile-info-value">${roles}</div>
				</div>
                    <div class="profile-info-row">
					<div class="profile-info-name">所属部门：</div>
					<div class="profile-info-value"><tags:cache keyName="Department" id="${user.deptid}"></tags:cache></div>
				</div>
                        <div class="profile-info-row">
					<div class="profile-info-name">账 号：</div>
					<div class="profile-info-value">${user.account}</div>
				</div>
                            <div class="profile-info-row">
					<div class="profile-info-name">手 机 号：</div>
					<div class="profile-info-value">${user.mobile}</div>
				</div>
                                <div class="profile-info-row">
					<div class="profile-info-name">备 注：</div>
					<div class="profile-info-value"><p>${user.memo}</p></div>
				</div>
		</div>
    <div class="clearfix form-actions">
        <div class="col-md-offset-2 col-md-10">
            <button class="btn" type="reset" onclick="showList();">
                <i class="ace-icon fa fa-undo bigger-110"></i> 返回
            </button>
        </div>
    </div>
</div>
<script>
function showList() {
	window.location.href = "${root}/system/user/list/${menuid}/";
}
</script>