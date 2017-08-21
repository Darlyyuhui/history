<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box" style="margin-left: 190px;">
	<h4 class="xtcs_h4" style="margin:0;">设备功能-修改</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/property/devicetypeinfo/doUpdate/" method="post" style="margin:0;">
		<input type="hidden" name="id" value="${deviceTypeInfo.id}">
		<input type="hidden" name="menuid" value="${menuid}">
		<div class="mar_5">
			<table class="table table-border-rl table-border-bot bukong-table">
				<tr>
					<td class="device_td_bg2">上级设备功能名称：</td>
					<td>
						<c:if test="${deviceTypeInfo.pid == '00'}">设备功能</c:if>
						<c:if test="${deviceTypeInfo.pid != '00'}"><tags:xiangxuncache keyName="DeviceTypeInfo" id="${deviceTypeInfo.pid}"></tags:xiangxuncache></c:if></td>
				</tr>
				<tr>
					<td class="device_td_bg2">设备功能名称：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="deviceTypeInfo-name" placeholder="设备功能名称" name="name" value="${deviceTypeInfo.name}" maxlength="10" class="input-large required" chinese="true"><span style="color:red">&nbsp;*</span></td>
				</tr>
				<tr>
					<td class="device_td_bg2">详细说明：</td>
					<td><textarea rows="5" class="span8" maxlength="100" style="min-width:200px;" name="note">${deviceTypeInfo.note}</textarea><span></span></td>
				</tr>
			</table>
	    </div>
		<div class="btn_line">
			<input class="btn btn-info mar_r10" type="submit" value="保存" />
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
	</form>
</div>
<script>
		function showList(){
			window.location.href="${root}/property/devicetypeinfo/sublist/${menuid}/";
		}
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#deviceTypeInfo-name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>