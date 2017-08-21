<%@ page language="java" pageEncoding="UTF-8"%>
<link href="${root}/compnents/jquery-jbox/2.3/Skins/Bootstrap/jbox.css" rel="stylesheet" />
<script src="${root}/compnents/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${root}/compnents/jquery-jbox/2.3/i18n/jquery.jBox-zh-CN.min.js" type="text/javascript"></script>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">添加用户</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/system/user/doAdd" method="post" style="margin:0;padding:0;">
		<c:if test="${fn:length(param.menuid)>0}"><input type="hidden" name="menuid" value="${param.menuid}" /> </c:if>
		<c:if test="${fn:length(param.deptid)>0}"><input type="hidden" name="deptid" value="${param.deptid}" /></c:if>
		<c:if test="${fn:length(param.menuid)==0}">
				<input type="hidden" name="menuid" value="${menuid}" /> 
		</c:if>
		<c:if test="${fn:length(param.deptid)==0}">
				<input type="hidden" name="deptid" value="${deptid}" /> 
		</c:if>
		<input type="hidden" id="usname" name="usname"  />
		<input type="hidden" name="puserId" id="puserId" value="${puserId}" />
		<input type="hidden" id="org-name" />
		<div class="mar_5">
			<table class="table table-border-rl table-border-bot bukong-table">
				<tr>
					<td class="device_td_bg3">所属部门：</td>
					<td>
						<tags:xiangxuncache keyName="Department" id="${param.deptid}"></tags:xiangxuncache>
						<!-- 勿删，选择警员使用 -->
				        <%-- <a class="btn" href="javascript:choicePolice('${param.deptid}')">选择警员</a> --%>
			        </td>
				<tr>
					<td class="device_td_bg3">姓 名：</td>
					<td><input style="width:50%; min-width:200px;" type="text" value="${policeName}" id="user-name" placeholder="姓名" name="name" maxlength="10" class="input-large required" specialcharfilter="true" ><font color="red">*</font></td>
				</tr>
				</tr>
				<tr>
					<td class="device_td_bg3">账 号：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="user-account" value="${account}" englishDigits="true" placeholder="账号" name="account" maxlength="20" class="input-large required"><font color="red">*</font></td>
				</tr>
				<tr>
					<td class="device_td_bg3">登录密码：</td>
					<td><input style="width:50%; min-width:200px;" type="text" value="123456" id="user-passwd" password="true" placeholder="登陆密码" name="pwd" value="" maxlength="12" class="input-large required" /><font color="red">*</font></td>
				</tr>
				<tr>
					<td class="device_td_bg3">重复一次：</td>
					<td><input style="width:50%; min-width:200px;" type="text" value="123456"  name="repeatPasswd" password="true" placeholder="重复一次" value="" equalTo="#user-passwd" maxlength="12" class="input-large required" /><font color="red">*</font></td>
				</tr>
				<tr>
					<td class="device_td_bg3">IP 控制：</td>
					<td><input style="width:4%; min-width:20px;" onfocus="ipfocus('iprule1');" id="iprule1" name="iprule1" value="" type="text" maxlength="3" xinghaoDigits="true" />
					.
					<input style="width:4%; min-width:20px;" onfocus="ipfocus('iprule2');" id="iprule2" name="iprule2" value="" type="text" maxlength="3" xinghaoDigits="true" />
					. 
					<input style="width:4%; min-width:20px;" onfocus="ipfocus('iprule3');" id="iprule3" name="iprule3" value="" type="text"  maxlength="3" xinghaoDigits="true" />
					.
					<input style="width:4%; min-width:20px;" id="iprule4" name="iprule4" value="" type="text" maxlength="3" xinghaoDigits="true" />
					(模糊位请输入<font color="red">*</font>号,列如：<font color="red">192.168.***.100</font>)
					</td>
				</tr>
				<tr>
					<td class="device_td_bg3">手 机 号：</td>
					<td><input style="width:50%; min-width:200px;" name="mobile" value="${policeMobile}" id="user-mobile" type="text" value="" placeholder="手机号" mobilephone="true" maxlength="11" /></td>
				</tr>
				<tr>
					<td class="device_td_bg3">备 注：</td>
					<td><textarea rows="5" maxlength="100" style="width:50%;min-width:200px;"></textarea> <span></span></td>
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
var flag = 0;
var flagip = "";
	
	//设置值
	function ipfocus(id){
		flagip = id;
		flag = id.substr(id.length - 1 , id.length);
	}

	$(document).keydown(function(event){  
		if((event.which == "110" || event.which == "190") && flag == 1 && flagip == "iprule1") { 
			$("#iprule2").focus();
			return false;
		}
		if((event.which == "110" || event.which == "190") && flag == 2 && flagip == "iprule2") { 
			$("#iprule3").focus();
			return false;
		}
		if((event.which == "110" || event.which == "190") && flag == 3 && flagip == "iprule3") { 
			$("#iprule4").focus();
			return false;
		}
	}); 


	$(document).ready(function() {
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules : {
				"account" : {
					remote : {
						url : "${root}/system/user/accountExist",
						type : "post",
						data : {
							account : function() {
								return $("#user-account").val();
							}
						}
					}
				},
				pwd:{
					rangelength:[6,12]
				},
				repeatPasswd:{
					rangelength:[6,12]
				}
			}
		});
	});
	
	function showList(){
		window.location.href="${root}/system/user/list/${menuid}/";
	}
	function choicePolice(orgId){
	    $.jBox("iframe:${root}/system/policeuser/choiceusers/"+orgId+"/${menuid}/", {
				    title: "警员信息",
				    width: 800,
				    height:550,
				    showScrolling: false,
				    buttons: {'<font>确定</font>':1},
				    submit: function (v, h, f) {
						var ids=$("#usname").val().split(",");
						if (ids.length >1) {
							alert("请选择一条记录。");
						} else {
							var arr=ids[0].split("_");
							$("#puserId").val(arr[0]);
							$("#user-name").val(arr[1]);
							$("#user-account").val(arr[2]);
							$("#user-mobile").val(arr[3]);
						}
			        }
				});
		   $(".jbox-button-panel").height(30);   	
	}
//	function choicePolice(orgId){
//		window.location.href="${root}/system/policeuser/choiceuser/"+orgId+"/${menuid}/";		
//	}
</script>
