<%@ page language="java" pageEncoding="UTF-8"%>
<div class="page-content-area">
	<div class="page-header">
		<h1>
			角色管理
			<small> <i class="ace-icon fa fa-angle-double-right"></i>
				查看角色详情 </small>
		</h1>
	</div>

	<div class="profile-user-info profile-user-info-striped">
		<div class="profile-info-row">
			<div class="profile-info-name">
				名称
			</div>
			<div class="profile-info-value">
				<span class="editable editable-click">${role.name}</span>
			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name">
				备注
			</div>
			<div class="profile-info-value">
				<span class="editable editable-click">${role.memo}</span>
			</div>
		</div>

	</div>
	<div class="clearfix form-actions">
		<div class="col-md-offset-3 col-md-9">
			<button class="btn" type="reset" onclick="history.back()">
				<i class="ace-icon fa fa-undo bigger-110"></i> 返回
			</button>
		</div>
	</div>
</div>