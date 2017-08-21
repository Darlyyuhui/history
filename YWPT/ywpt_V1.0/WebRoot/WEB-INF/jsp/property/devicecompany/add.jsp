<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">设备厂商信息-添加</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/property/devicecompany/doAdd" method="post" style="margin:0;padding:0">
	  <input type="hidden" name="menuid" value="${param.menuid}"/>
		<div class="mar_5">
		  <table class="table table-border-rl table-border-bot bukong-table">
		    <tr>
				<td class="device_td_bg3">设备厂商：</td>
				<td><input style="width:50%; min-width:200px;" minlength="1" maxlength="30" type="text" id="name" placeholder="设备厂商" name="name" class="input-large required"  specialcharfilter="true"><span style="color:red">&nbsp;*</span></td>
			</tr>
		    <tr>
				<td class="device_td_bg3">联 系 人：</td>
				<td><input style="width:50%; min-width:200px;" maxlength="20" type="text" id="contactperson" placeholder="联系人" name="contactperson" class="input-large required" chinese="true" specialcharfilter="true"><span style="color:red">&nbsp;*</span></td>
			</tr>
		    <tr>
				<td class="device_td_bg3">联系电话：</td>
				<td><input style="width:50%; min-width:200px;" maxlength="13" type="text" id="contactphone" placeholder="联系电话" name="contactphone" class="input-large required" teletest="true"><span style="color:red">&nbsp;*</span></td>
			</tr>
		    <tr>
				<td class="device_td_bg3">备　　注：</td>
				<td><textarea rows="5" style="min-width:200px;" maxlength="100" class="span8" name="note"></textarea></td>
			</tr>
		 </table>
	  </div>
	  <div class="btn_line">
		<input id="submit_btn" class="btn btn-info mar_r10" type="submit" value="保存" />
		<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
　　  </div>
  </form>
</div>
  <script>
  		function showList(){
			window.location.href="${root}/property/devicecompany/list/${menuid}/";
		}
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
 </script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>