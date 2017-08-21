<%@ page language="java" pageEncoding="UTF-8"%>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">日志--详情</h4>
	<div class="mar_5" >
		<table class="table table-border-rl table-border-bot bukong-table">
			<tbody>
				<tr>
					<td class="device_td_bg3">IP地址：</td>
					<td>${log.ip}</td>
				</tr>
				<tr>
					<td class="device_td_bg3">操作时间：</td>
					<td><fmt:formatDate value="${log.operateTime}" pattern="yyyy-MM-dd HH:mm:SS" /></td>
				</tr>
				<tr>
					<td class="device_td_bg3">基本信息：</td>
					<td>${log.className}-${log.methodName}</td>
				</tr>
				<tr>
					<td class="device_td_bg3">操作员：</td>
					<td>${log.operator}</td>
				</tr>
				<tr>
					<td class="device_td_bg3">参数：</td>
					<td><p style="width:400px">${log.args}</p></td>
				</tr>
				<tr>
					<td class="device_td_bg3">异常信息：</td>
					<td style="padding-right: 5px;"><p>${log.content}</p></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btn_line">
		<input id="cancel_btn" class="btn btn-info" type="button" value="返回" onclick="history.back()" />
	</div>
</div>
