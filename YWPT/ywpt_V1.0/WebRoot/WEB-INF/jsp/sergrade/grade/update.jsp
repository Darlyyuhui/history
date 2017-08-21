<%@ page language="java" pageEncoding="UTF-8"%>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">
	function checkForm(){
		var checkFlag = true;
	
		if($("#code").val() == ''){
			alert("编号不允许为空");
			checkFlag = false;
		}else if($("#name").val() == ''){
			alert("名称不允许为空");
			checkFlag = false;
		}else if($("#minpoint").val() != '' && $("#maxpoint").val() != ''){
			if(parseInt($("#minpoint").val()) >= parseInt($("#maxpoint").val())){
				alert("最小分值只能小于最大分值");
				checkFlag = false;
			}
		}
		return checkFlag;
	}
</script>

<div class="conten_box" style="overflow:hidden;">
	<form id="inputForm" class="form-inline" action="${root}/sergrade/grade/info/doUpdate" method="post" style="margin:0;">
		<h4 class="xtcs_h4" style="margin:0;">服务级别--修改</h4>
		<input type="hidden" name="menuid" value="${menuid}" />
		<input type="hidden" name="page" value="${page}" />
		<input type="hidden" name="id" value="${gradeInfo.id}" />
		<div class="mar_5">
		  <table class="table table-border-rl table-border-bot bukong-table">
			<tr>
				<td class="device_td_bg3">服务级别：</td>
				<td colspan="3">
					<tags:xiangxuncache keyName="Dic" typeCode="gradetype" id="${gradeInfo.code}" ></tags:xiangxuncache>
				</td>		
			</tr>

			<tr>
				<td class="device_td_bg3">分值范围：</td>
				<td colspan="3">
					<input style="width:50px;" type="text" id="minpoint" name="minpoint" class="digits" value="${gradeInfo.minpoint}" required>
					-
					<input style="width:50px;" type="text" id="maxpoint" name="maxpoint" class="digits" value="${gradeInfo.maxpoint}" required>
					
					<font color="red">*（注：服务级别总共分五档，第一档：0-20，第二档：21-40，第三档：41-60，第四档：61-80，第五档：81-100）</font>
				</td>
			</tr>
			<tr>
				<td class="device_td_bg3">星级：</td>
				<td colspan="3">
					<c:if test="${gradeInfo.code == '1'}">
						<img src="${root}/images/star.png" style="width:20px;">
					</c:if>
					<c:if test="${gradeInfo.code == '2'}">
						<img src="${root}/images/star.png" style="width:20px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
					</c:if>
					<c:if test="${gradeInfo.code == '3'}">
						<img src="${root}/images/star.png" style="width:20px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
					</c:if>
					<c:if test="${gradeInfo.code == '4'}">
						<img src="${root}/images/star.png" style="width:20px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
					</c:if>
					<c:if test="${gradeInfo.code == '5'}">
						<img src="${root}/images/star.png" style="width:20px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
					</c:if>
				</td>
			</tr>
			<tr>
			 	<td class="device_td_bg3">备　　注：</td>
				<td colspan="3"><textarea rows="4" maxlength="128" style="width:50%;" class="span8" name="remark">${gradeInfo.remark}</textarea></td>
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
		window.location.href = "${root}/sergrade/grade/info/list/${menuid}/";
	}
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#code").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: {
				"code":{
					remote:{
						url:"${root}/sergrade/grade/info/codeExist",
						type:"post",
						data:{
							code:function(){
								return $("#code").val();
							},
							oper:function(){
								return "${gradeInfo.code}";
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
