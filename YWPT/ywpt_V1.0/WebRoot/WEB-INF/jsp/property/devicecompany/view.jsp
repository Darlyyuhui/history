<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">设备厂商信息-详情</h4>
	<input type="hidden" name="menuid" value="${menuid}"/>
	<input type="hidden" name="page" value="${page}"/>
	<input type="hidden" name="id" value="${deviceCompanyInfo.id}"/>
	<div class="mar_5">
	  <table class="table table-border-rl table-border-bot bukong-table">
	    <tr>
			<td class="device_td_bg3">设备厂商：</td>
			<td>
			${deviceCompanyInfo.name}
			</td>
		</tr>
	    <tr>
			<td class="device_td_bg3">联 系 人：</td>
			<td>
			${deviceCompanyInfo.contactperson}
			</td>
		</tr>
	    <tr>
			<td class="device_td_bg3">联系电话：</td>
			<td>
			${deviceCompanyInfo.contactphone}
			</td>
		</tr>
	    <tr>
			<td class="device_td_bg3">备　　注：</td>
			<td>
			${deviceCompanyInfo.note}
			</td>
		</tr>
	  </table>
	</div>
	<div class="btn_line">
		<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
　　</div>
</div>
<script>
		function showList(){
			window.location.href="${root}/property/devicecompany/list/${menuid}/?page=${page}&isgetsession=1";
		}
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#ftp-ip").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>