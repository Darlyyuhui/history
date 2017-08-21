<%@ page language="java" pageEncoding="UTF-8"%>
<link href="${root}/compnents/jquery-jbox/2.3/Skins/Bootstrap/jbox.css" rel="stylesheet" />
<script src="${root}/compnents/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${root}/compnents/jquery-jbox/2.3/i18n/jquery.jBox-zh-CN.min.js" type="text/javascript"></script>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">添加运维人员</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/sergrade/contact/doAdd" method="post" style="margin:0;padding:0;">
		<c:if test="${fn:length(param.menuid)>0}"><input type="hidden" name="menuid" value="${param.menuid}" /> </c:if>
		<c:if test="${fn:length(param.factoryid)>0}"><input type="hidden" name="factoryid" value="${param.factoryid}" /></c:if>
		<c:if test="${fn:length(param.menuid)==0}">
				<input type="hidden" name="menuid" value="${menuid}" /> 
		</c:if>
		<c:if test="${fn:length(param.factoryid)==0}">
				<input type="hidden" name="factoryid" value="${factoryid}" /> 
		</c:if>

		<div class="mar_5">
			<table class="table table-border-rl table-border-bot bukong-table">
				<tr>
					<td class="device_td_bg3">所属公司：</td>
					<td>
						<tags:xiangxuncache keyName="FactoryInfo" id="${param.factoryid}"></tags:xiangxuncache>
					</td>
				<tr>
					<td class="device_td_bg3">姓 名：</td>
					<td>
						<input style="width:50%; min-width:200px;" type="text" id="user-name" placeholder="姓名" name="userName" maxlength="10" class="input-large required" specialcharfilter="true" >
						<font color="red">*</font>
					</td>
				</tr>
				</tr>
				
				<tr>
					<td class="device_td_bg3">手 机 号：</td>
					<td>
						<input style="width:50%; min-width:200px;" name="mobile" id="user-mobile" type="text"  placeholder="手机号" mobilephone="true" maxlength="11" />
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td class="device_td_bg3">备 注：</td>
					<td><textarea rows="5" maxlength="100" name="memo" style="width:50%;min-width:200px;"></textarea> <span></span></td>
				</tr>
			</table>
		</div>
		<div class="btn_line">
			<input id="submit_btn" class="btn btn-info mar_r10" type="submit" value="保存" />
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList();" />
		</div>
	</form>
</div>
<script>
	$(document).ready(function() {
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules : {
				"mobile" : {
					remote : {
						url : "${root}/system/user/accountExist",
						type : "post",
						data : {
							account : function() {
								return $("#user-mobile").val();
							}
						}
					}
				}
			}
		});
	});
	
	function showList(){
		window.location.href="${root}/sergrade/contact/list/${menuid}/";
	}

</script>
