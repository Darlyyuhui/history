<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">用户信息</h4>
	<div class="mar_5">
		<table class="table table-border-rl table-border-bot bukong-table">
			<tr>
				<td class="device_td_bg3">姓 名：</td>
				<td>${user.name}</td>
			</tr>
			<tr>
				<td class="device_td_bg3">账 号：</td>
				<td>${user.account}</td>
			</tr>
			<tr>
				<td class="device_td_bg3">所属部门：</td>
				<td><tags:xiangxuncache keyName="Department" id="${user.deptid}"></tags:xiangxuncache></td>
			</tr>
			<tr>
				<td class="device_td_bg3">拥有角色：</td>
				<td>${roles}</td>
			</tr>
			<tr>
				<td class="device_td_bg3">IP控制：</td>
				<td>${user.iprule}</td>
			</tr>
			<tr>
				<td class="device_td_bg3">手 机 号：</td>
				<td>${user.mobile}</td>
			</tr>
			<tr>
				<td class="device_td_bg3">备 注：</td>
				<td><p>${user.memo}</p></td>
			</tr>
		</table>
	</div>
	<div class="btn_line">
		<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
	</div>
</div>
<script>
function showList() {
	window.location.href = "${root}/system/user/list/${menuid}/";
}
</script>