<%@ page language="java" pageEncoding="UTF-8"%>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">添加角色</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/system/role/doAdd" method="post" style="margin:0;padding:0;">
		<input type="hidden" name="menuid" value="${param.menuid}"/>
		<input type="hidden" name="deptid" value="${param.deptid}"/>
		<div class="mar_5" >
			<table class="table table-border-rl table-border-bot bukong-table">
				<tr>
					<td class="device_td_bg3">角色名称：</td>
					<td><input type="text" id="role-name" placeholder="名称" name="name" maxlength="10" style="width:50%; min-width:200px;" class="input-large required" chinese="true" specialcharfilter="true"><font color="red">&nbsp;*</font></td>
				</tr>
				<tr>
					<td class="device_td_bg3">备注：</td>
					<td><textarea rows="5" class="span8" maxlength="100" style="min-width:200px;" name="memo" ></textarea><span></span></td>
				</tr>
			</table>
		</div>
		<div class="btn_line">
			  <input id="submit_btn" class="btn btn-info mar_r10" type="submit" value="保存" />
			  <input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
		</div> 
	</form>
</div>
  <script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#role-name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				 rules: {
						"name":{
							remote:{
								url:"${root}/system/role/nameExist",
								type:"post",
								data:{
									name:function(){
										return $("#role-name").val();
									}
								}
							
							}
						}
				}
				
			});
		});
 </script>
