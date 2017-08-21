<%@ page language="java" pageEncoding="UTF-8"%>
<link href="${root}/cssDarkBlue/login.css" rel="stylesheet" />
<body>
<style>
.browser-chose {
	position: absolute;
	bottom:-25px;
	width:100%;
	text-align:center;
}
.browser-chose a {
	color:#e85353;
	text-decoration:underline;
}
</style>
<div id="wrap">
  <div class="login_wrap">
    <div class="decoration"><img src="${root}/images/login_2014811/airplane.png"></div>
    <div class="sysname">${itmsname}</div>
    <div class="login_main_box">
      <div class="version">版本：V1.0</div>
      <div class="login_logo"><img src="${root}/images/xx-logo.png"></div>
      <div class="login_info">
        <form action="j_spring_security_check" method="post" id="loginForm" class="log_form">
          <div class="username"><input type="text" autofocus name="j_username" id="username" maxlength="30" autocomplete="off" placeholder="请输入用户名"></div>
          <div class="password"><input name="j_password" id="password" type="password"  maxlength="20" autocomplete="off" placeholder="请输入密码"></div>
          <div class="login_error" style="${param.error?'':'display:none'}">
            <div style="font-weight: bold;color: #EA5200;">${param.error?'用户名密码不正确':''}</div>
          </div>
          <div class="login_btn_line">
            <input type="submit" value="登 录" class="btn btn-info">
            <input type="reset" value="重 置" class="btn" onclick="reset()" style="margin-left:20px;border:1px solid #ccc;">
          </div>
        </form>
      </div>
      <div class="copyright">CopyRight 版权所有 西安翔迅科技有限责任公司</div>
      <div class="browser-chose"><a id="browser" href="javascript:void(0);" onclick="downloadBrowser();return false;">谷歌(chrome)浏览器安装包下载</a></div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript">
    function login(){
    }
    function reset(){
       	$("#username").val("");
       	$("#password").val("");
    }
    $(function(){
		var height = $(window).height();
		$("#wrap").height(height);
		var obj = $.browser;
		if(obj.msie || obj.version == '11.0'){
			alert("本系统不支持IE内核的浏览器，请更换其它浏览器访问！\n\n* 推荐使用谷歌(chrome)浏览器");
			if (confirm("即将开始下载系统推荐的浏览器安装包，确定并开始下载？")) {
				$("#browser").click();
			}
		}
	});
    function downloadBrowser() {
    	window.location.href = "${root }/downloadfile/Chrome39.rar/?filepath=/browser/Chrome39.rar";
    }
</script>