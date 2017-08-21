<%@ page language="java" pageEncoding="UTF-8"%>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">修改运维人员</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/sergrade/contact/doUpdate" method="post" style="margin:0;padding:0;">
		<input type="hidden" name="menuid" value="${menuid}" /> 
		<input type="hidden" name="id" value="${contactInfo.id}" />
		<input type="hidden" name="userid" value="${contactInfo.userid}" />
		<input type="hidden" name="page" value="${page}" />
		<div class="mar_5">
			<table class="table table-border-rl table-border-bot bukong-table">
				<tr>
					<td class="device_td_bg3">所属公司：</td>
					<td><tags:xiangxuncache keyName="FactoryInfo" id="${contactInfo.factoryid}"></tags:xiangxuncache></td>
				</tr>
				<tr>
					<td class="device_td_bg3">姓 名：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="user-name" value="${contactInfo.userName}" maxlength="20" specialcharfilter="true" name="userName" class="input-large required" /></td>
				</tr>
				<tr>
					<td class="device_td_bg3">手 机 号：</td>
					<td><input style="width:50%; min-width:200px;" name="mobile" id="user-mobile" type="text" value="${contactInfo.mobile}" maxlength="11" placeholder="手机号" mobilephone="true" /></td>
				</tr>
				<tr>
					<td class="device_td_bg3">备 注：</td>
					<td><textarea rows="5" class="span8" style="min-width:200px;" maxlength="100" name="memo">${contactInfo.memo}</textarea><span></span></td>
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
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules : {
				"mobile" : {
					remote : {
						url : "${root}/system/user/accountExist",
						type : "post",
						data : {
							account : function() {
								return $("#user-mobile").val();
							}
						}
					}
				}
			}
		});
	});

	function showList() {
		window.location.href = "${root}/sergrade/contact/list/${menuid}/";
	}
</script>