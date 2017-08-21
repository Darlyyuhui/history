<%@ page language="java" pageEncoding="UTF-8"%>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">角色信息</h4>
		<div class="mar_5" >
			<table class="table table-border-rl table-border-bot bukong-table">
				<tr>
					<td class="device_td_bg3">角色名称：</td>
					<td>${role.name}</td>
				</tr>
				<tr>
					<td class="device_td_bg3">备注：</td>
					<td><p>${role.memo}</p> <span></span></td>
				</tr>
			</table>
		</div>
		<div class="btn_line">
			  <input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
		</div>
</div>
