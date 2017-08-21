<%@ page language="java" pageEncoding="UTF-8"%>
<link href="${root}/css/login_blue.css" rel="stylesheet" />
<body class="body_bg">
<div class="wrap">
  <div class="login_box">
    <div class="sys_name">道路交通信息综合管理平台</div>
    <div class="login_main">
      <p><img src="${root}/images/picone/login_bt.gif"></p>
      <div class="main_box">
        <div class="itms_logo"><img src="${root}/images/picone/itms_logo.png"></div>
        <form action="j_spring_security_check" method="post" id="loginForm" class="log_form">
          <div style="height:20px;line-height:20px;margin-bottom:-15px;margin-top:5px;${param.error?'':'display:none'}">
       		  <div style="font-weight: bold;color: #EA5200;">${param.error?'用户名密码不正确':''}</div>
		  </div>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="25%">用户名：</td>
              <td width="75%"><input autofocus name="j_username" id="username" type="text" value="" class="in_text required" maxlength="30" autocomplete="off" style="padding:2px; height:22px;border:1px solid #8e8e82;"></td>
            </tr>
            <tr>
              <td>密　码：</td>
              <td><input name="j_password" id="password" type="password" value="" class="in_text required"  maxlength="20" autocomplete="off" style="padding:2px;height:22px;border:1px solid #8e8e82;"></td>
            </tr>
            <tr>
              <td colspan="2">
               <input class="btn btn-primary" type="submit" value="登 录" style="height:30px;width:80px;font-size:14px; margin-right:10px;"/>
       		   <input class="btn btn-info" type="reset" value="重 置" onclick="reset()" style="height:30px;width:80px;font-size:14px;"/>
       		  </td>
            </tr>
          </table>
        </form>
      </div>
      <p class="bot_border"></p>
    </div>
    <div style="text-align:center; line-height:30px; width:100%;">Copyright2014 版权所有：西安翔迅科技有限责任公司 </div>
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
	   	
	   	//网页正文部分上，y轴坐标点
	   	window.screenTop;
	   	//网页正文部分左，x轴坐标点
	   	window.screenLeft;
	   	//屏幕高度
	   	window.screen.height;
	   	//屏幕可用工作区高度
	   	
	   	window.screen.availHeight;
	   	
	   	if ($.browser.safari) {
			height=window.screen.availHeight-60;
		}else if($.browser.msie){
			height=window.screen.availHeight-window.screenTop;
		}else if($.browser.mozilla){
			height=window.screen.availHeight-window.mozInnerScreenY;
		}
	   	
	   	$("#wrapBg").height(height);
	   	$(".main").height(height);
	   	$(".bulding_bg").height(height);
	});	
</script>
