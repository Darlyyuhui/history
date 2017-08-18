<%@ page language="java" pageEncoding="UTF-8"%>
<div class="row">
	<form id="inputForm" class="form-horizontal" action="${root}/system/res/doUpdate" method="post" style="margin-bottom:0;padding:0;">
		<input type="hidden" value="${menuid}" name="menuid">
		<input type="hidden" value="${res.id}" name="id">
		<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name">名称：</div>
					<div class="profile-info-value"><input style="width:50%; min-width:200px;" type="text" id="res-name" placeholder="名称" maxlength="50" name="name" specialcharfilter="true" value="${res.name}" class="input-large required" ><span style="color: red">*</span></div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">图标：</div>
					<div class="profile-info-value"><input style="width:50%; min-width:200px;" type="text" maxlength="30" placeholder="图标" name="icon" value="${res.icon}" class="input-large"></div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">排序：</div>
					<div class="profile-info-value"><input style="width:50%; min-width:200px;" type="text" maxlength="10" placeholder="排序" name="sortOrder" value="${res.sortOrder}" digits="true" class="input-large required"><span style="color: red">*</span></div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">访问地址：</div>
					<div class="profile-info-value"><textarea rows="6" class="span8" style="min-width:520px;" maxlength="100" name="content">${res.content}</textarea><span></span></div>
				</div>
		</div>
		<div class="clear"></div>
		<div class="btn_line" style="padding-left:120px; margin-bottom:0;">
			<input id="submit_btn" class="btn btn-info mar_r10" type="submit" value="保存" />
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
  </form>
</div>
<script>
		function showList(){
			window.location.href="${root}/system/res/list/${menuid}/";
		}
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#res-name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
