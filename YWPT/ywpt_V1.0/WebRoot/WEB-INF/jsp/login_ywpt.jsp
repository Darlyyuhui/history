<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="btn" uri="/WEB-INF/author-btn.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<script src="/ywpt/compnents/bootstrap/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<head>
<link href="${root}/cssYwpt/login.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${itmsname}</title>
</head>

<body>

<div id="wrap" class="background">
  <div class="login_box">
       <img src="${root}/images/login_ywpt/loginbackground2.png" />
       <div class="login_img02"><img  src="${root}/images/login_ywpt/i01.png"></div>
          
    <div class="text">
       <div class="syssign">${itmsname}</div>
    </div>
<form action="j_spring_security_check" method="post" id="loginForm">
    <div class="username_box">  
       <div class="username">
        <a>账&nbsp;&nbsp;&nbsp;号</a>
        <input name="j_username" id="username" type="text" value="请输入用户名" onFocus="clearvalue();" maxlength="30" autocomplete="off"/>
       </div>
       <div class="username">
        <a>密&nbsp;&nbsp;&nbsp;码</a>
        <input name="j_password" id="password" type="password" value="" maxlength="20" autocomplete="off"/>
       </div>
       <!-- 
       <div class="username">
        <a>验证码</a>
        <input name="j_validate_code" type="text" style="width:79px;margin-top: 2px;"/>
        <img style="width:72px;height:22px;vertical-align: middle;margin-top: -4px;" src="${root}/captcha.svl" onclick="this.src='${root}/captcha.svl?d='+new Date()*1"/>
       </div>
        -->
       <div style="text-align: center;margin-top: 15px;">
          <div class="btn01">
          <input type="submit" value="登 录" class="login_btn" onmouseover="this.className='login_btn_h'" onmouseout="this.className='login_btn'" style="width:65px;height:25px;color:#fff;"/>
          </div>
          <div class="btn02">
          <input type="button" value="重 置" class="login_btn" onmouseover="this.className='login_btn_h'" onmouseout="this.className='login_btn'" onclick="clearvalue();" style="width:65px;height:25px;color:#fff;"/>
          </div>
       </div>
     </div>
     <div align="center" style="height:10px;${param.error?'':'display:none'}">
       	<div style="font-weight: bold;color: #EA5200;">${param.error?'用户名密码不正确':''}</div>
	 </div>
</form>
  </div>
     <div class="clearfix"></div>
</div>
 
</body>
<script type="text/javascript">
    function reset(){
       	$("#username").val("");
       	$("#password").val("");
    }
    function clearvalue(){
       $("#username").val("");
       $("#password").val("");
    }
    $(document).ready(function() {
		var height = $(window).height();
		$("#wrap").height(height);
	});
</script>
