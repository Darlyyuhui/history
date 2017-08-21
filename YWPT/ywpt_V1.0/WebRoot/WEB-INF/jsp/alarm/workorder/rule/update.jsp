<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box">
	<form id="inputForm" class="form-inline" action="${root}/alarm/workrule/doUpdate" method="post" style="margin:0;">
		<h4 class="xtcs_h4" style="margin:0;">评估规则-修改</h4>
		<input type="hidden" name="menuid" value="${menuid}"/>
		<input type="hidden" name="id" value="${rule.id}"/>
		<input type="hidden" name="page" value="${page}"/>
		<div class="mar_5">
		  <table class="table tingche-table table-border-rl table-border-bot" width="100%">
			<tr>
				<td class="device_td_bg3">规则名称：</td>
				<td>
					<input style="width:40%;" type="text" id="name" name="name" value="${rule.name}" class="required" chinese="true" maxlength="20">
					<font color="red">*</font>
				</td> 
			</tr>
			<tr>
				<td class="device_td_bg3">规则编号：</td>
				<td>
				    <input style="width:40%;" type="text" id="code" name="code" englishDigits="true" value="${rule.code}" class="required" maxlength="10" readonly="readonly">
					<font color="red">*</font>
		        </td>
			</tr>
			<tr>
				<td class="device_td_bg3">规则描述：</td>
				<td>
				    <input style="width:40%;" type="text" id="describe" name="describe" value="${rule.describe}" class="required" maxlength="100">
					<font color="red">*</font>
		        </td>
			</tr>
			<tr>
				<td class="device_td_bg3">规则分值：</td>
				<td>
				    <input style="width:40%;" type="text" id="score" name="score" value="${rule.score}" class="required" maxlength="2" digits="true">
					<font color="red">*</font>
		        </td>
			</tr>
			<tr>
				<td class="device_td_bg3">备注：</td>
				<td>
					<textarea rows="3" style="width:60%;" id="note" name="note" maxlength="100" >${rule.note}</textarea>
				</td>	
			</tr>
		  </table>
		</div>
		<div class="btn_line">
			<button class="btn btn-info mar_r10" type="submit">保存</button>
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
	</form>
</div>
<script type="text/javascript">
<!--
	function showList(){
		window.location.href = "${root}/alarm/workrule/list/${menuid}/?isgetsession=1&page=${page}";
	}
	$(document).ready(function() {
			//聚焦第一个输入框
			$("#name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					"name":{
						remote:{
							url:"${root}/alarm/workrule/nameExist",
							type:"post",
							data:{
								name:function(){
									return $("#name").val();
								},
								oper:function(){
									return "${rule.name}";
								}
							}
						}
					},
					"code":{
						remote:{
							url:"${root}/alarm/workrule/codeExist",
							type:"post",
							data:{
								code:function(){
									return $("#code").val();
								},
								oper:function(){
									return "${rule.code}";
								}
							}
						}
					}
				}
			});
		});
//-->
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
