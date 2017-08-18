<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%
	String parentName = URLDecoder.decode(request.getParameter("parentName"),"UTF-8");
	request.setAttribute("parentName",parentName);
%>
<div class="row">
	<form id="inputForm" class="form-horizontal" action="${root}/system/res/doAddBtn/${param.menuid}/" method="post" style="margin:0;padding:0">
		<input type="hidden" name="parentid" value="${param.parentid}"/>
		<div class="profile-user-info profile-user-info-striped">
			<div class="profile-info-row">
				<div class="profile-info-name">
					菜单名称：
				</div>
				<div class="profile-info-value">
					${parentName}
				</div>
			</div>	
			<div class="profile-info-row">	
				<div class="profile-info-name">
					按钮名称：
				</div>
				<div class="profile-info-value">
					<input style="width: 50%; min-width: 200px;" type="text" id="res-name" maxlength="10" placeholder="名称" chinese="true" specialcharfilter="true" name="name" class="input-large required">
					<span style="color: red">*</span>
				</div>
			</div>
		</div>	
			<div class="clear"></div>
			<div class="clearfix form-actions">
				<div class="col-md-offset-1 col-md-9">
					<input type="hidden" name="icon" maxlength="30" class="input-large">
					<input type="hidden" name="sortOrder" maxlength="20" digits="true" class="input-large required">
					<input id="submit_btn" class="btn btn-info mar_r10" type="submit" value="保存" />
					<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
				</div>
			</div>
	</form>
</div>
<script>
		function showList(){
			window.location.href="${root}/system/res/list/${menuid}/";
		}
		
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#res-name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
</script>
