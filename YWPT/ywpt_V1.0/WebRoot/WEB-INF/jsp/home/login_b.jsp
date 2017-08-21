<%@ page language="java" pageEncoding="UTF-8"%>
<link href="${root}/css/login_b.css" rel="stylesheet" />

<body>
<div id="wrapBg">
  <div class="login_wrap">
    <div class="login_box">
      <div class="login">
        <div class="title_line"><cite><img src="${root}/images/itms.png"></cite><span>版本：V1.0</span></div>
        <h3 class="name">道路交通信息综合管理平台</h3>
        <div class="login_info" >
          <form action="j_spring_security_check" method="post" id="loginForm" style="margin-top:60px;">
            <h4 class="login_tips">请输入用户名和密码</h4>
            <div class="u_name"><input autofocus name="j_username" id="username" type="text" value="" class="required" maxlength="30" autocomplete="off"></div>
            <div class="u_pass"><input name="j_password" id="password" type="password" value="" class="required"  maxlength="20" autocomplete="off"></div>
            <div class="clear"></div>
            <div style="height:25px;${param.error?'':'display:none'}">
       		  <div style="font-weight: bold;color: #EA5200;">${param.error?'用户名密码不正确':''}</div>
		    </div>
            <div class="btn_lines" style="margin-top:20px ">
        	  <input class="btn btn-primary" type="submit" value="登 录" style="height:34px;width:80px;font-size:18px; margin-right:30px;font-family:Microsoft YaHei;padding:7px;"/>
       		  <input class="btn btn-info" type="reset" value="重 置" onclick="reset()" style="height:34px;width:80px;font-size:18px;font-family:Microsoft YaHei;padding:7px;"/>
            </div>
           </form>
        </div>
      </div>
    </div>
  </div> 
  <div class="footer">
    <div class="foot_con" style="text-align:center;">西安翔迅科技有限公司版权所有<span>©</span>2013-2015  <div style="float:right;margin-right:10px; display:none;">技术支持：029-88151288</div></div>
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
			height=window.screen.availHeight-70;
		}else if($.browser.msie){
			height=window.screen.availHeight-window.screenTop-10;
		}else if($.browser.mozilla){
			height=window.screen.availHeight-window.mozInnerScreenY-10;
		}
	   	
	   	$("#wrapBg").height(height);
	   	$(".main").height(height);
	   	$(".bulding_bg").height(height);
	});	
</script>