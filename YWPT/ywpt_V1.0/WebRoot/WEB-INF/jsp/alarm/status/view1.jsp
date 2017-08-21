<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box" style="margin-left: 180px;">
	<h4 class="xtcs_h4" style="margin:0;">机柜信息-详情</h4>
	<input type="hidden" name="menuid" value="${menuid}"/>
	<input type="hidden" name="page" value="${page}"/>
	<div class="mar_5">
	  	<table class="table tingche-table table-border-rl table-border-bot">
			<tr>
				<td class="device_td_bg3" style="width: 12%">设备IP：</td>
				<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:22px;white-space:nowrap;" title="${ftp.name}">${ftp.name}</div></td>
				<td class="device_td_bg3" style="width: 12%">设备编号：</td>
				<td style="width: 38%">
					${ftp.ftptype}
				</td>
			</tr>
			<tr> 
				<td class="device_td_bg3">设备名称：</td>
				<td>
					${ftp.ip}
				</td>
				<td class="device_td_bg3">振动：</td>
				<td>
					${ftp.port}
				</td>
			</tr>
			
			<tr> 
				<td class="device_td_bg3">温度：</td>
				<td>
					${ftp.ip}
				</td>
				<td class="device_td_bg3">湿度：</td>
				<td>
					${ftp.port}
				</td>
				<tr> 
				<td class="device_td_bg3">AC插座电流：</td>
				<td>
					${ftp.ip}
				</td>
				<td class="device_td_bg3">AC插座电压：</td>
				<td>
					${ftp.port}
				</td>
			
			</tr>
			
			
		</table>
	</div>
	<div class="btn_line">
		<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
	</div>
</div>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>