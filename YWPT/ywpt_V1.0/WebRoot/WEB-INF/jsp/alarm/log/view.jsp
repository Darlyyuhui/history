<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box" style="height:660px;overflow:hidden;margin-left:180px;">
	<h4 class="xtcs_h4" style="margin:0;">告警日志-详情</h4>
	<div class="mar_5">
	  <table class="table tingche-table table-border-rl table-border-bot">
		<tr>
			<td class="device_td_bg2">资产名称：</td>
			<td>
				${log.deviceName}
			</td> 
			<td class="device_td_bg2">资产编号：</td>
			<td>
				${log.deviceCode}
			</td>			
		</tr>
		<tr>
			<td class="device_td_bg2">资产 IP：</td>
			<td>
				${log.deviceIp}
			</td> 
			<td class="device_td_bg2">事件类别：</td>
			<td>
				<font style="background-color: ${log.alarmColor};">${log.eventTypeName}</font>
			</td>			
		</tr>
		<tr>
			<td class="device_td_bg2">告警级别：</td>
			<td>
				${log.eventLevel}
			</td> 
			<td class="device_td_bg2">告警方式：</td>
			<td>
				${log.alarmType}
			</td>			
		</tr>
		<tr>
			<td class="device_td_bg2">告警时间：</td>
			<td>
				<fmt:formatDate value="${log.alarmTime}" pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
			<td class="device_td_bg2"></td>
			<td>
				
			</td>
		</tr>
	  </table>
	</div>
	<div class="btn_line">
		<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
	</div>
</div>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>