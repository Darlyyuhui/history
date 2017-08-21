<%@ page language="java" pageEncoding="UTF-8"%>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">修改用户</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/system/user/doUpdate" method="post" style="margin:0;padding:0;">
		<input type="hidden" name="menuid" value="${menuid}" /> <input type="hidden" name="id" value="${user.id}" /> <input type="hidden" name="page" value="${page}" />
		<div class="mar_5">
			<table class="table table-border-rl table-border-bot bukong-table">
				<tr>
					<td class="device_td_bg3">姓 名：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="user-name" value="${user.name}" maxlength="20"  specialcharfilter="true" name="name" class="input-large required" disabled="disabled" /></td>
				</tr>
				<tr>
					<td class="device_td_bg3">所属部门：</td>
					<td><tags:xiangxuncache keyName="Department" id="${user.deptid}"></tags:xiangxuncache></td>
				</tr>
				<tr>
					<td class="device_td_bg3">账 号：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="user-account" placeholder="账号" value="${user.account}" maxlength="20" name="account" class="input-large required" /><font color="red">*</font></td>
				</tr>
				<tr>
					<td class="device_td_bg3">IP 控制：</td>
					<td><input style="width:4%; min-width:20px;" name="iprule1" value="${user.iprule1}" type="text" maxlength="3" xinghaoDigits="true" />
					.
					<input style="width:4%; min-width:20px;" name="iprule2" value="${user.iprule2}" type="text" maxlength="3" xinghaoDigits="true" />
					.
					<input style="width:4%; min-width:20px;" name="iprule3" value="${user.iprule3}" type="text" maxlength="3" xinghaoDigits="true" />
					.
					<input style="width:4%; min-width:20px;" name="iprule4" value="${user.iprule4}" type="text" maxlength="3" xinghaoDigits="true" />
					(模糊位请输入<font color="red">*</font>号,列如：<font color="red">192.168.***.001</font>)
					</td>
				</tr>
				<tr>
					<td class="device_td_bg3">手 机 号：</td>
					<td><input style="width:50%; min-width:200px;" name="mobile" id="user-mobile" type="text" value="${user.mobile}" maxlength="11" placeholder="手机号" mobilephone="true" /></td>
				</tr>
				<tr>
					<td class="device_td_bg3">备 注：</td>
					<td><textarea rows="5" class="span8" style="min-width:200px;" maxlength="100" name="memo">${user.memo}</textarea><span></span></td>
				</tr>
			</table>
		</div>
		<div class="btn_line">
			<input id="submit_btn" class="btn btn-info mar_r10" type="submit" value="修改" />
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
	</form>
</div>
<script>
	$(document).ready(function() {
		//聚焦第一个输入框
		//$("#user-name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules : {
				"account" : {
					remote : {
						url : "${root}/system/user/accountExist",
						type : "post",
						data : {
							account : function() {
								return $("#user-account").val();
							},
							oper : function() {
								return "${user.account}";
							}
						}

					}
				}
			}

		});
	});

	function showList() {
		window.location.href = "${root}/system/user/list/${menuid}/";
	}
</script>