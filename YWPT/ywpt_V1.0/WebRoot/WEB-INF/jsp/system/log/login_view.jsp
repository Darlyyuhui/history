<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">登录日志信息</h4>
	<div class="mar_5" >
		<table class="table table-border-rl table-border-bot bukong-table">
		  <tr>
			<td class="device_td_bg3">操作账号：</td>
			<td>${syslog.operatorId}</td>
		  </tr>
		  <tr>
			<td class="device_td_bg3">操作员姓名：</td>
			<td>${syslog.operatorName}</td>
		  </tr>
		  <tr>
			<td class="device_td_bg3">IP地址：</td>
			<td>${syslog.ipAddress}</td>
		  </tr>
		  <tr>
			<td class="device_td_bg3">类型：</td>
			<td>${syslog.type==1?"登录":'退出'}</td>
		  </tr>
		  <tr>
			<td class="device_td_bg3">发生时间：</td>
			<td><fmt:formatDate value="${syslog.operationTime}" pattern="yyyy-MM-dd HH:mm:SS" /></td>
		  </tr>
		  <tr>
			<td class="device_td_bg3">内容：</td>
			<td class="textdidden">${syslog.content}</td>
		  </tr>
	    </table>
	</div>
	<div class="btn_line">
		<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
	</div>
</div>
