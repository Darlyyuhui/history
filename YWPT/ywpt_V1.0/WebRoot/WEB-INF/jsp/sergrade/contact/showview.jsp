<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">运维人员信息</h4>
	<div class="mar_5">
		<table class="table table-border-rl table-border-bot bukong-table">
			<tr>
				<td class="device_td_bg3">姓 名：</td>
				<td>${contactInfo.userName}</td>
			</tr>
			<tr>
				<td class="device_td_bg3">账 号：</td>
				<td>${contactInfo.account}&nbsp;&nbsp;<font color="red">*注：初始密码为"123456"，请登录后尽快修改密码！</font></td>
			</tr>
			<tr>
				<td class="device_td_bg3">所属公司：</td>
				<td>${contactInfo.factoryName}</td>
			</tr>
			<tr>
				<td class="device_td_bg3">联系方式：</td>
				<td>${contactInfo.mobile}</td>
			</tr>
			<tr>
				<td class="device_td_bg3">备 注：</td>
				<td><p>${contactInfo.memo}</p></td>
			</tr>
		</table>
	</div>
	<div class="btn_line">
		<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
	</div>
</div>
<script>
function showList() {
	window.location.href = "${root}/sergrade/contact/list/${menuid}/";
}
</script>