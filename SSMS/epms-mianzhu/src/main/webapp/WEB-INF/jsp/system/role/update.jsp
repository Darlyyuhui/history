<%@ page language="java" pageEncoding="UTF-8"%>
<div class="page-content-area">
	<div class="page-header">
		<h1>
			角色管理
			<small> <i class="ace-icon fa fa-angle-double-right"></i>
				修改角色信息 </small>
		</h1>
	</div>
		<form id="inputForm" class="form-horizontal"
			action="${root}/system/role/doUpdate" method="post">
			<input type="hidden" name="menuid" value="${menuid}" />
			<input type="hidden" name="page" value="${page}" />
			<input type="hidden" name="id" value="${role.id}" />
	<div class="profile-user-info profile-user-info-striped">
			<div class="profile-info-row">
				<div class="profile-info-name">
					名称
				</div>
				<div class="profile-info-value">
					<input type="text" id="user-name" maxlength="10"
						value="${role.name}" name="name" class="input-large required"
						chinese="true" specialcharfilter="true">
					<span style="color: red;">*</span>
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">
					备注
				</div>
				<div class="profile-info-value">
					<textarea rows="5" class="autosize-transition form-control"
						maxlength="100" name="memo">${role.memo}</textarea>
					<span></span>
				</div>
			</div>
	</div>
	<div class="clearfix form-actions">
		<div class="col-md-9" style="margin-left: 100px">
			<button class="btn btn-primary" type="submit">
				<i class="ace-icon fa fa-submit bigger-110"></i> 保存
			</button>
			<button class="btn" type="reset" onclick="history.back()">
				<i class="ace-icon fa fa-undo bigger-110"></i> 返回
			</button>
		</div>
	</div>
	</form>
</div>



<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#user-name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				 rules: {
						"name":{
							remote:{
								url:"${root}/system/role/nameExist",
								type:"post",
								data:{
									name:function(){
										return $("#user-name").val();
									},
									oper:function(){
										return "${role.name}";
									}
								}
							
							}
						}
				}
				
			});
		});
</script>
