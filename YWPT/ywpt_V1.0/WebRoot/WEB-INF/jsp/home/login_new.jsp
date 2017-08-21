<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录页面</title>
<link href="${root}/css/login_new.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="wrap">
  <div class="login_box">
    <div class="title">
      <div class="sysname">智能交通综合管控平台</div>
      <div class="version">版本号 V1.0</div>
    </div>
    <div class="syssign">${itmsname}</div>
    <form action="j_spring_security_check" method="post" id="loginForm">
      <div class="info">
        <div class="info_line">
          <span>用户名</span>
          <div class="name">
            <input style="height:17px;width:178px;border:0;margin-top:-2px;" name="j_username" id="username" type="text" value="请输入用户名" onFocus="clearvalue();" class="required" maxlength="30" autocomplete="off"/>
          </div>
        </div>
        <div class="info_line">
          <span>密　码</span>
          <div class="password">
            <input style="height:17px;width:178px;border:0;margin-top:-2px;"  name="j_password" id="password" type="password" value="" class="required"  maxlength="20" autocomplete="off"/>
          </div>
        </div>
        <div class="btn_group">
          <input type="submit" value="登 录" class="login_btn_h" onmouseover="this.className='login_btn'" onmouseout="this.className='login_btn_h'" style="width:62px;height:28px;"/>
          <input type="button" value="重 置" class="login_btn" onmouseover="this.className='login_btn_h'" onmouseout="this.className='login_btn'" onclick="clearvalue();" style="width:62px;height:28px;"/>
        </div>
      </div>
      <div align="center" style="height:10px;${param.error?'':'display:none'}">
       	<div style="font-weight: bold;color: #EA5200;">${param.error?'用户名密码不正确':''}</div>
	  </div>   
    </form>
  </div>
</div>
</body>
</html>

<script type="text/javascript">
    function clearvalue(){
       $("#username").val("");
       $("#password").val("");
    }
</script>