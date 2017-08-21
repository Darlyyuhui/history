<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%
	String parentName = URLDecoder.decode(request.getParameter("parentName"),"UTF-8");
	request.setAttribute("parentName",parentName);
%>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">添加操作</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/system/res/doAddBtn/${param.menuid}/" method="post" style="margin:0;">
		<input type="hidden" name="parentid" value="${param.parentid}"/>
		<div class="mar_5" >
			<table class="table table-border-rl table-border-bot bukong-table">
				<tr>
					<td class="device_td_bg3">菜单名称：</td>
					<td>${parentName}</td>
				</tr>
				<tr> 
					<td class="device_td_bg3">名称：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="res-name" maxlength="10" placeholder="名称" chinese="true" specialcharfilter="true" name="name" class="input-large required" ><font color="red">*</font></td>
				</tr>
			</table>
		</div>
		<div class="btn_line">
			<input type="hidden" name="icon" maxlength="30" class="input-large">
			<input type="hidden" name="sortOrder" maxlength="20" digits="true" class="input-large required">
			<input id="submit_btn" class="btn btn-info mar_r10" type="submit" value="保存" />
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
  </form>
</div>
<script>
		function showList(){
			window.location.href="${root}/system/res/sublist/${menuid}/";
		}
		
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#res-name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
</script>
