<%@ page language="java" pageEncoding="UTF-8"%>
<head>
<link href="${root}/cssYwpt/login.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
</head>

<body style="margin:0; padding:0;">

  <div class="background">
    <div class="login_box">
       <img src="${root}/images/login_ywpt/loginbackground.png" />
       <div class="login_img02"><img  src="${root}/images/login_ywpt/i01.png"></div>
          
    <div class="text">
       <h1><a href="">智能交通运维管理平台</a></h1>
       <p><a href="">ENERGY CONSUMPTION MONITORING MANAGEMENT SYSTEM</a></p> 
     </div>
     
     <div class="username_box">  
       <div class="username">
        <a>用户名</a>
        <input/>
        <div class="icon01"><img src="${root}/images/login_ywpt/icon01.png" /></div>
          </div>
         <div class="username">
        <a>密&nbsp;&nbsp;&nbsp;码</a>
        <input/>
        <div class="icon01"><img style=" margin-top:37px;" src="${root}/images/login_ywpt/icon02.png"></div>
          </div>
       <div class="username">
        <a>验证码</a>
        <input style=" width:74px;"/>
        <div class="icon01"><img src="${root}/images/login_ywpt/icon01.png" /></div>
          </div>
          <div class="btn01"><img src="${root}/images/login_ywpt/btn01.png"></div>
          <div class="btn02"><img src="${root}/images/login_ywpt/btn02.png"></div>
        </div>  
 
   </div>
     <div class="clearfix"></div>
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
	});
</script>
