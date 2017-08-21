<%@ page language="java" pageEncoding="UTF-8"%>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin-top:0;">修改角色</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/system/role/doUpdate" method="post" style="margin:0;padding:0">
		<input type="hidden" name="menuid" value="${menuid}"/>
		<input type="hidden" name="page" value="${page}"/>
		<input type="hidden" name="id" value="${role.id}"/>
		<div class="mar_5" >
			<table class="table table-border-rl table-border-bot bukong-table">
				<tr>
					<td class="device_td_bg3">角色名称：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="user-name" maxlength="10" value="${role.name}" name="name" class="input-large required" chinese="true" specialcharfilter="true"><font color="red">&nbsp;*</font></td>
				</tr>
				<tr>
					<td class="device_td_bg3">备注：</td>
					<td><textarea rows="5" class="span8" maxlength="100" style="min-width:200px;" name="memo" >${role.memo}</textarea><span></span></td>
				</tr>
			</table>
		</div>
		<div class="btn_line">
		  <input id="submit_btn" class="btn btn-info mar_r10" type="submit" value="修改" />
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
						"name":{
							remote:{
								url:"${root}/system/role/nameExist",
								type:"post",
								data:{
									name:function(){
										return $("#user-name").val();
									},
									oper:function(){
										return "${role.name}";
									}
								}
							
							}
						}
				}
				
			});
		});
</script>
