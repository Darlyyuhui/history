<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box" style="margin-left: 180px;">
	<h4 class="xtcs_h4" style="margin:0;">平台信息-详情</h4>
	<input type="hidden" name="menuid" value="${menuid}"/>
	<input type="hidden" name="page" value="${page}"/>
	<div class="mar_5">
	  	<table class="table tingche-table table-border-rl table-border-bot">
			<tr>
				<td class="device_td_bg3">平台名称：</td>
				<td colspan="3"><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:22px;white-space:nowrap;" title="${project.name}">${project.name}</div></td>
			</tr>
			<tr> 
				<td class="device_td_bg3">IP地址：</td>
				<td>
					${project.ip}
				</td>
				<td class="device_td_bg3">端口号：</td>
				<td>
					${project.port}
				</td>
			</tr>
			<tr>
				<td class="device_td_bg3">项目路径：</td>
				<td>
					${project.url}
				</td>
		  	    <td class="device_td_bg3">运维服务商：</td>
		  	    <td>
		          ${project.factoryName}
		        </td>
		  	</tr>
		</table>
	</div>
	<div class="btn_line">
		<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
	</div>
</div>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>