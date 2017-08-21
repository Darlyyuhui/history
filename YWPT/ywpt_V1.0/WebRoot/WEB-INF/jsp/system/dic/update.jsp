<%@ page language="java" pageEncoding="UTF-8"%>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">修改数据</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/system/dic/doUpdate" method="post" style="margin:0;padding:0;">
		<input type="hidden" name="menuid" value="${menuid}"/>
		<input type="hidden" name="groupid" value="${groupid}"/>
		<input type="hidden" name="id" value="${dic.id}"/>
		<input type="hidden" name="page" value="${page}"/>
		<div class="mar_5" >
			<table class="table bukong-table table-border-bot table-border-rl">
			    <tr>
					<td class="device_td_bg3">名　　称：</td>
					<td><input style="width:50%; min-width:200px;" value="${dic.name}" minlength="2" maxlength="20" type="text" id="dic-name" placeholder="名称" name="name" class="input-large required" specialcharfilter="true"> <font color="red">*</font></td>
				</tr>
			    <tr>
					<td class="device_td_bg3">类　　型：</td>
					<td><c:forEach items="${dic_sec}" var="keyValue">${keyValue.key eq dic.type.value?keyValue.name:''}</c:forEach>
					</td>
				</tr>
			    <tr>
					<td class="device_td_bg3">编　　码：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="dic-code" placeholder="编码" name="code" value="${dic.code}" maxlength="8" disabled="disabled" class="input-large required" > <font color="red">*</font></td>
				</tr>
			    <tr>
					<td class="device_td_bg3">备　　注：</td>
					<td><textarea rows="5" class="span8" maxlength="100" name="remark"> ${dic.remark}</textarea> <span></span></td>
				</tr>
			</table>
		</div>
		<div class="btn_line">
		    <button class="btn btn-info mar_r10" onclick="save();">保存</button>
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
		</div>
	</form>
</div>
<script>
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#user-name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			 rules: {
					"code":{
						remote:{
							url:"${root}/system/dic/codeExist",
							type:"post",
							data:{
								code:function(){
									return $("#dic-code").val();
								},
								type:function(){
									return "${type}";
								},
								oper:function(){
									return "${dic.code}";
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