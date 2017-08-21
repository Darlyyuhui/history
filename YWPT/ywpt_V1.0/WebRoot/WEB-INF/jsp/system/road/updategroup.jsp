<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box" style="margin-left:190px;">
	<form id="inputForm" class="form-inline" action="${root}/system/road/doUpdate" method="post" style="margin:0;">
		<h4 class="xtcs_h4" style="margin: 0;">修改道路分组</h4>
		<input type="hidden" name="id" value="${roadInfo.id}">
		<input type="hidden" name="menuid" value="${menuid}">
	    <div class="mar_5">
	       <table class="table table-border-bot table-border-rl bukong-table">
			<tr>
				<td class="device_td_bg3">分组名称：</td>
				<td>
					<input style="width:50%;" type="text" id="name" name="name" value="${roadInfo.name}" class="required"  specialcharfilter="true" maxlength="30">
					<font color="red">*</font>
				</td> 
			</tr>
			<tr>
				<td class="device_td_bg3">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
				<td><textarea rows="3" class="span8" style="min-width:200px;" maxlength="100" name="note">${roadInfo.note}</textarea></td>
			</tr>
		</table>
		<div class="btn_line">
			<input class="btn btn-info mar_r10" type="submit" value="保存" />
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
	</form>
</div>
<script type="text/javascript">
<!--
	function showList(){
		window.location.href = "${root}/system/road/view/${roadInfo.id}/${menuid}/";
	}
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: {
				"name":{
					remote:{
						url:"${root}/system/road/groupNameExist",
						type:"post",
						data:{
							name:function(){
								return $("#name").val();
							},
							oper:function(){
								return "${roadInfo.name}";
							}
						}
					}
				}
			}
		});
	});
	
//-->
</script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
