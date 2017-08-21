<%@ page language="java" pageEncoding="UTF-8"%>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">
	function checkForm(){
		var checkFlag = true;
	
		if($("#name").val() == ''){
			alert("公司名称不允许为空");
			checkFlag = false;
		}else if($("#linkman").val() == ''){
			alert("联系人不允许为空");
			checkFlag = false;
		}else if($("#telphone").val() == ''){
			alert("联系电话不允许为空");
			checkFlag = false;
		}
		return checkFlag;
	}
</script>

<div class="conten_box" style="overflow:hidden;">
	<form id="inputForm" class="form-inline" action="${root}/sergrade/factory/info/doUpdate" method="post" style="margin:0;">
		<h4 class="xtcs_h4" style="margin:0;">运维服务商--修改</h4>
		<input type="hidden" name="menuid" value="${menuid}" />
		<input type="hidden" name="page" value="${page}" />
		<input type="hidden" name="id" value="${factoryInfo.id}" />
		<div class="mar_5">
		  <table class="table table-border-rl table-border-bot bukong-table">
			<tr>
				<td class="device_td_bg3">公司名称：</td>
				<td>
					<input style="width:160px;" type="text" id="name" name="name" class="required" value="${factoryInfo.name}" maxlength="16">
					<font color="red">*</font>
				</td>
				
				<td class="device_td_bg3">联系人：</td>
				<td>
					<input style="width:160px;" type="text" id="linkman" name="linkman" class="required" value="${factoryInfo.linkman}" maxlength="16">
					<font color="red">*</font>
				</td>		
			</tr>
			<tr>
				<td class="device_td_bg3">联系电话：</td>
				<td colspan="3">
					<input style="width:160px;" type="text" id="telphone" name="telphone" class="required" value="${factoryInfo.telphone}" maxlength="16">
					<font color="red">*</font>
				</td>
			</tr>
			
			<tr>
			 	<td class="device_td_bg3">备　　注：</td>
				<td colspan="3"><textarea rows="4" maxlength="100" style="width:50%;" class="span8" name="remark">${factoryInfo.remark}</textarea></td>
			</tr>
		  </table>
		</div>
		<div class="btn_line">
			<button class="btn btn-info mar_r10" onclick="javascript:return checkForm();" type="submit">保存</button>
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="javascript:history.go(-1);" />
		</div>
	</form>
</div>
<script type="text/javascript">
	function showList(){
		window.location.href = "${root}/sergrade/factory/info/list/${menuid}/";
	}
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: {
				"name":{
					remote:{
						url:"${root}/sergrade/factory/info/nameExist",
						type:"post",
						data:{
							code:function(){
								return $("#name").val();
							},
							oper:function(){
								return "${factoryInfo.name}";
							}
						}
					}
				}
			}
		});
		
</script>

<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
