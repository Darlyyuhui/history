<%@ page language="java" pageEncoding="UTF-8"%>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

<script type="text/javascript">
	function checkForm(){
		var checkFlag = true;

		if($("#title").val() == ''){
			alert("问题标题不允许为空");
			checkFlag = false;
		}else if($("#content").val() == ''){
			alert("问题详情不允许为空");
			checkFlag = false;
		}
		return checkFlag;
	}
</script>

<div class="conten_box" style="overflow:hidden;">
	<form id="inputForm" class="form-inline" action="${root}/question/info/doAdd" method="post" style="margin:0;">
		<h4 class="xtcs_h4" style="margin: 0;">问题录入--添加</h4>
		<input type="hidden" name="menuid" value="${menuid}" />
		<input type="hidden" name="page" value="${page}" />
		<div class="mar_5">
		  <table class="table table-border-rl table-border-bot bukong-table">
			<tr>
				<td class="device_td_bg3">录入人员：</td>
				<td>
					<input type="hidden" id="operator" name="operator" value="${operator}">
					<input style="width:160px;" type="text" name="operatorName" value="${operatorName}" maxlength="16" readonly="readonly">
					<font color="red">*</font>
				</td>
				
				<td class="device_td_bg3">录入时间：</td>
				<td>
					<input style="width:160px;" type="text" id="insertTimeStr" name="insertTimeStr" value="${nowTime}" readonly="readonly">
					<font color="red">*</font>
				</td>		
			</tr>
			
			<tr>
				<td class="device_td_bg3">问题标题：</td>
				<td colspan="3">
					<input style="width:160px;" type="text" id="title" name="title" maxlength="50">
					<font color="red">*</font>
				</td>
				
			</tr>
			
			<tr>
			 	<td class="device_td_bg3">问题详情：</td>
				<td colspan="3">
					<textarea rows="8" maxlength="500" style="width:80%;" class="span8" id="content" name="content"></textarea>
					<font color="red">*</font>
				</td>
			</tr>
		  </table>
		</div>
		<div class="btn_line">
			<button class="btn btn-info mar_r10" onclick="javascript:return checkForm();" type="submit">保存</button>
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
	</form>
</div>
<script type="text/javascript">
	function showList(){
		window.location.href = "${root}/question/info/list/${menuid}/";
	}
	
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#operator").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate();
	});
</script>

<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
