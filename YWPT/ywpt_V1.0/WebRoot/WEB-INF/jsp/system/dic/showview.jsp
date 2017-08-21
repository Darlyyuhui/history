<%@ page language="java" pageEncoding="UTF-8"%>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin: 0;">字典信息</h4>
	<div class="mar_5">
		<table class="table bukong-table table-border-bot table-border-rl">
			<tr>
				<td class="device_td_bg3">名 称：</td>
				<td>${dic.name}</td>
			</tr>
			<tr>
				<td class="device_td_bg3">类 型：</td>
				<td><c:forEach items="${dic_sec}" var="keyValue">${keyValue.key eq dic.type.value?keyValue.name:''}</c:forEach></td>
			</tr>
			<tr>
				<td class="device_td_bg3">编 码：</td>
				<td>${dic.code}</td>
			</tr>
			<tr>
				<td class="device_td_bg3">备 注：</td>
				<td>${dic.remark}</td>
			</tr>
		</table>
	</div>
	<div class="btn_line">
		<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
	</div>
</div>
