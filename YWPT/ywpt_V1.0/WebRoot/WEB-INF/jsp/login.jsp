<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<link href="${root}/ui/css/login/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${root}/ui/js/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${root}/ui/js/layer/layer.js"></script>

</head>

<title>${itmsname}</title>
<body>
	<div class="top">
		
	</div>
	<div class="ma">
		<div class="bt"><i class="hui2"><img src="${root}/images/login/wxlogo.png" width="48px" height="48px" /></i> ${itmsname} <span class="beat">V2.0</span></div>
		<div class="bg">
			<div class="tit"><i class='hui'><img src="${root}/images/login/yonghu.png" width="26px" height="22px" /></i> 用户登录</div>
			<form action="j_spring_security_check" method="post" id="loginForm" >
			<div class="con">
				<span class="sp">
					<i class='hui1'><img src="${root}/images/login/user.png" width="25px" height="25px" /></i></i>
					<input name="j_username" type="text" class="name" id="user_name" placeholder="用户账号" value=""/>
				</span>
				<span class="sp">
					<i class='hui1'><img src="${root}/images/login/pass.png" width="25px" height="25px" /></i>
					<input name="j_password" type="password" class="pass" id="user_pwd" placeholder="密码" onpaste="return false" onfocus="this.type='password'" autocomplete="off"/>
				</span>
				<span class="login" onclick="Login()">登 录</span>
			</div>
			</form>
		</div>
	</div>
<script>
$(function(){
	var u_name = getCookie("user_name");
	$("#user_name").val(u_name);
	document.getElementById("user_name").focus();
	$(".top").css("height",$(window).height()*0.4);
	$(window).resize(function() {
		$(".top").css("height",$(window).height()*0.4);
	});
	if('true'=='${param.error}'){
		layer.closeAll('loading');
		layer.tips("账号或密码错误",'#user_name',{
			tips:3,time:2000
		});
		document.getElementById("user_name").focus();
	}
});  
document.onkeydown = function(e){
	e = e ? e : window.event;
    var keycode = e.which ? e.which : e.keyCode;
	if(keycode == 13){
		Login();
	}
};
function Login(){
	layer.load(2,{shade:0.3});
	var user_name = $("#user_name").val();
	var user_pwd = $("#user_pwd").val();
	if(""==user_name.replace(/\s/g,"")){
		layer.closeAll('loading');
		layer.tips("请输入用户账号",'#user_name',{
			tips:3,time:2000
		});
		document.getElementById("user_name").focus();
	}else if(""==user_pwd){
		layer.closeAll('loading');
		layer.tips("请输入密码",'#user_pwd',{
			tips:3,time:2000
		});
		document.getElementById("user_pwd").focus();
	}else{
		document.getElementById("loginForm").submit();
	}
}

function cookieEnabled(){
	if(!(document.cookie || navigator.cookieEnabled)){
		layer.msg("未记住用户编号、密码,浏览器已被禁用Cookie！",{time: 3000});
		return false;
	}else{
		return true;
	}
}
function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays*24*60*60*1000));
	var expires = "expires="+d.toUTCString();
	document.cookie = cname + "=" + cvalue + "; " + expires;
}
function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for(var i=0; i<ca.length; i++) {
 		var c = ca[i];
 		while (c.charAt(0)==' ') c = c.substring(1);
 		if (c.indexOf(name) != -1) 
 			return c.substring(name.length, c.length);
	}
	return "";
}
</script>
</body>

</html>