<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="conten_box" style="margin-right:0; margin-left:180px;">
	<form id="inputForm" class="form-inline" action="${root}/repository/catalog/doUpdate/${type}/${menuid}/" method="post" style="margin-bottom:0; padding-bottom:0;">
		<h4 class="xtcs_h4" style="margin: 0;">修改目录</h4>
		<input type="hidden" name="menuid" value="${menuid}" />
		<input type="hidden" name="page" value="${page}" />
		<input type="hidden" name="pid" value="${catalog.pid}" />
		<input type="hidden" name="id" value="${catalog.id}" />
		<div class="mar_5">
		  <table class="table table-border-bot table-border-rl tingche-table">
			<tr>
				<td class="device_td_bg3">目录名称：</td>
				<td>
					<input style="width:50%;" type="text" id="name" name="name" value="${catalog.name}" class="required" minlength="3" maxlength="20">
					<font color="red">*</font>
				</td> 
			</tr>
			<tr>
				<td class="device_td_bg3">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
				<td><textarea rows="1" maxlength="100" style="width:50%;" class="span8" name="note">${catalog.note}</textarea><span></span></td>
			</tr>
		  </table>
		</div>
		<div class="btn_line">
			<input class="btn btn-info mar_r10" type="submit" value="保存" />
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
		</div>
	</form>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: {
				"name":{
					remote:{
						url:"${root}/repository/catalog/nameExist",
						type:"post",
						data:{
							name:function(){
								return $("#name").val();
							},
							oper:function(){
								return "${catalog.name}";
							}
						}
					}
				}
			}
		});
	});
	
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
