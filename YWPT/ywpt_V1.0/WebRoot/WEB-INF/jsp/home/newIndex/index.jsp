<%@ page language="java"  pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<link id="MainCss" href="${root}/css/newIndex/newIndex.css" type="text/css" rel="stylesheet">
<link id="TipsCss" href="${root}/css/tipswindown/tipswindown.css" type="text/css" rel="stylesheet" media="all" />
<script type="text/javascript" src="${root}/js/tipswindown/tipswindownFd.js"></script>
<script type="text/javascript" src="${root}/js/newIndex/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="${root}/skin/default/css/base.css?v=606" />  
<script type="text/javascript" src="${root}/skin/default/js/common.js?v=725"></script>
<script src="${root}/skin/default/js/jquery.vticker.min.js"></script>
<script>
</script>
<style>
.wrap { margin-top: 0px; padding: 15px 0; border: 1px solid #ddd; background: #f5f5f5;}
.dowebok { width: 100%; height: 100%; margin: 0 auto; line-height: 30px;}
.dowebok ul{width: 95%;}
.dowebok li { overflow: hidden; zoom: 1;}
.dowebok a { color: #333; text-decoration: none;}
.dowebok a:hover { color: #000;}
.dowebok span{width:120px}
</style>
<script type="text/javascript">
//base
var _baseUrl = '';
var baseJSUrl = '${root}/skin/default/js/';
var baseCSSUrl = '${root}/skin/default/css/';
</script>
<script type="text/javascript">
	var themeType = '${themeType}';
	var skincssType = '${skincssType}';
	
	//切换皮肤方法
	function skinCss(){
		var $li=$("#skin-sel li");
		$li.click(function(){
			$(this).addClass("skin_seled").siblings().removeClass("skin_seled");
			var value=$(this).text();
			if(value=="1"){
				$("#MainCss").attr("href","${root}/css/newIndex/newIndex.css");
				
			}else if(value=="2"){
				$("#MainCss").attr("href","${root}/css/newIndex/newIndex.css");
				
			}else {
				
			}
			
			jQuery.ajax({
				type : "GET",  
				async: false,
				url: "${root}/system/skin/change_skin/" + value + "/",
				success : function(data) {
					//alert(data);
				}
			});
		});
	}
	
	$(document).ready(function(){
		//页面加载时获取页面风格参数
		var skincssType = '${skincssType}';
		if(skincssType=="1"){
			$("#MainCss").attr("href","${root}/css/newIndex/newIndex.css");
			$("#skin1").addClass("skin_seled").siblings(".skin-ul li").removeClass("skin_seled");
		}else if(skincssType=="2"){
			$("#MainCss").attr("href","${root}/css/newIndex/newIndex.css");
			$("#skin2").addClass("skin_seled").siblings(".skin-ul li").removeClass("skin_seled");
		}else{
		
		}
		$('#skin-sel').val(skincssType);
		
	});
	
	function logout(){
		if(confirm("确定要退出当前登录吗？")){
			window.location.href = "${root}/j_spring_security_logout";				
		}
		
	}
</script>

<body class="body_new">
<div class="head_new">
  <div class="logo_new"><img src="${root}/images/newIndex/logo_itms.png"></div>
  <div class="ladtnews" id="notice">
    <ul id="notice_ul" style="float:left; margin-left:0;" class="scroll-container">
    </ul>
  </div>
  
  <div class="tips_head">
    <ul class="tips_ul">
      <li><span><img src="${root}/images/newIndex/out12.png"></span><a href="javascript:logout();">安全退出</a></li>
      <li><span><img src="${root}/images/newIndex/down12.png"></span><a href="${root}/downloadfile/ITMS_UserManual.doc/?filepath=sysfile/ITMS_UserManual.doc">下载手册</a></li>
      <li><span><img src="${root}/images/newIndex/edit12.png"></span><a href="javascript:showChangePasswdDialog()">修改密码</a></li>
      <li><span><img src="${root}/images/newIndex/user12.png"></span><a href="javascript.html#" rel="popover" data-placement="bottom" data-html="true" title="当前用户信息" data-content="<table class=\'table bg_white\' cellpadding=\'0\' cellspacing=\'0\'><tr><td class=\'device_td_bg2\'>姓&nbsp;&nbsp;&nbsp;&nbsp;名：</td><td>${user.username}</td></tr><tr><td class=\'device_td_bg2\'>部&nbsp;&nbsp;&nbsp;&nbsp;门：</td><td>${departmentname}</td></tr><tr><td class=\'device_td_bg2\'>角&nbsp;&nbsp;&nbsp;&nbsp;色：</td><td>${userRole}</td></tr><tr><td class=\'device_td_bg2\'>上次&nbsp;IP：</td><td>${opratorLog.ipAddress}</td></tr><tr><td class=\'device_td_bg2\'>上次时间：</td><td>${logintimeStr}</td></tr></table>">${userName}</a></li>
    </ul>
  </div>
</div>
<div class="modal hide fade" id="passwd-change">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal">×</button>
    <h2 style="text-shadow:none;font-size:16px;margin:0;">修改密码</h2>
  </div>
  <div class="modal-body">
    <form id="changePasswdForm" class="form-horizontal">
      <div class="control-group">
        <label class="control-label"> 原密码： </label>
        <div class="controls" id="old-passwd-div">
          <input type="password" id="old-passwd" placeholder="原密码" name="oldPwd" value="" class="required" maxlength="20" /><strong style="color:red">&nbsp;*</strong>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label"> 新密码： </label>
        <div class="controls" id="user-passwd-div">
          <input type="password" id="user-passwd" placeholder="新密码" name="pwd" value="" class="input required" minlength=8 maxlength="20" /><strong style="color:red">&nbsp;*</strong>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label"> 重复一次： </label>
        <div class="controls" id="repeat-passwd-div">
          <input type="password" id="repeat-passwd" name="repeatPasswd" placeholder="重复一次" value="" equalTo="#user-passwd" class="input required" minlength=8 maxlength="20" /><strong style="color:red">&nbsp;*</strong>
        </div>
      </div>
    </form>
  </div>
  <div class="modal-footer"> <a href="javascript:changePasswd()" class="btn btn-primary" style="color:#fff;">修改</a>
    <a href="#" class="btn" data-dismiss="modal">关闭</a> </div>
</div>
<div class="mainbody_new" id="mainbodyH">
  <table width="100%">
    <tr>
      <td id="leftMenu" width="212">
        <div class="leftside_bar" id="LeftBar">
		    <p class="left_bar_title">操作导航</p>
		    <div id="LeftNav" class="LeftNav" style="overflow-x:hidden;overflow-y:hidden;position:relative;">
		      <ul class="mainnav_new" id="Mainnav_ul" style="overflow-x:hidden;overflow-y:auto;">
		      	<li class="active_nav"><a id="menu_${menu.id}" onclick="showMainIndex()">主页</a></li>
		      	<c:forEach items="${menus}" var="menu" varStatus="m">
		      		<li class="sys_nav"><a id="menu_${menu.id}">${menu.name}</a></li>
		      	</c:forEach>
		      </ul>
		      <div class="navBox_new" id="submenu_frame" style="position:fixed;left:47px;top:76px;overflow-x:hidden;overflow-y:scroll;width:169px;z-index:1000;">
		      </div>
		      <div class="clear"></div>
		    </div>
		  </div>
		  <!-- 左侧菜单栏结束 -->
      </td>
      
      <td>
        <div class="rightside" id="RightCon" style="margin-left:5px;overflow-x:hidden;overflow-y:auto;">
		    <iframe id="content-frame" name="content-frame" frameborder="0" width="99%" scrolling="no" style="margin:0;padding:5px;" src="" ></iframe>
		    <!-- 此处的id必须与另一套的皮肤id一致，否则部分功能无法使用 -->
		  </div>
		   <div>
     
      </div>
      </td>
    </tr>
  </table>
  <!-- 告警日志详情弹出框 -->
<div class="modal hide fade" id="alarmlog-modal">
  	<h4 class="xtcs_h4" style="margin: 0;"><font style="margin-left: 5px;">告警日志详情</font><img src="${root}/images/delete.png" href="#" style="cursor: pointer;float: right;height:20px;width:20px;margin-left:0;font-size: small;vertical-align: middle;margin-top: 5px;color: red;" data-dismiss="modal"/></h4>
  	<div class="modal-body" id="alarmlog_div"></div>
</div>
<!-- 派发工单信息弹出框 -->
<div class="modal hide fade" id="showassign-modal" style="width:760px;">
  	<h4 class="xtcs_h4" style="margin: 0;"><font style="margin-left: 5px;">派发工单信息</font><img src="${root}/images/delete.png" href="#" style="cursor: pointer;float: right;height:20px;width:20px;margin-left:0;font-size: small;vertical-align: middle;margin-top: 5px;color: red;" data-dismiss="modal"/></h4>
  	<div class="modal-body" id="showassign_div"></div>
</div>


<!-- 问题信息详情弹出框 -->
<div class="modal hide fade" id="questionlog-modal">
  	<h4 class="xtcs_h4" style="margin: 0;"><font style="margin-left: 5px;">问题信息详情</font><img src="${root}/images/delete.png" href="#" style="cursor: pointer;float: right;height:20px;width:20px;margin-left:0;font-size: small;vertical-align: middle;margin-top: 5px;color: red;" data-dismiss="modal"/></h4>
  	<div class="modal-body" id="questionlog_div"></div>
</div>

<!-- 工单信息详情弹出框 -->
<div class="modal hide fade" id="workorderlog-modal" style="width:850px;">
  	<h4 class="xtcs_h4" style="margin: 0;"><font style="margin-left: 5px;">工单信息详情</font><img src="${root}/images/delete.png" href="#" style="cursor: pointer;float: right;height:20px;width:20px;margin-left:0;font-size: small;vertical-align: middle;margin-top: 5px;color: red;" data-dismiss="modal"/></h4>
  	<div class="modal-body" id="workorderlog_div"></div>
</div>
  
  <!-- 右侧主体区结束 -->
</div>
</body>
<div class="company-info"><span>Copyright &copy; 西安翔迅科技有限责任公司</span></div>
<script type="text/javascript" src="${root}/js/newIndex/newIndex.js"></script>
<script type="text/javascript">
var account_comment_url = "http://sc.chinaz.com";
var account_consult_url = "http://sc.chinaz.com";
var account_message_url = "http://sc.chinaz.com";
var account_arrival_url = "http://sc.chinaz.com";
var account_reduce_url = "http://sc.chinaz.com";
//Dialog icon base path
	$(document).ready(function(){
		showMainIndex();
		getsubmenu('${first.id}',false);
		ds.loadScript(baseJSUrl+'quick_links.js'); 
	});
	
	function showMainIndex(){
		$("#leftMenu").attr("width","40");
		$("#LeftBar .left_bar_title").html("");
		$("#LeftBar").attr("style", "width:40px");
		$("#submenu_frame").css("display", "none");
		$("#content-frame").attr("src","${root}/home/newindex/main/");
		//getsubmenu('${first.id}',false);
	}
	
	function showChangePasswdDialog() {
		$("#old-passwd").val("");
		$("#user-passwd").val("");
		$("#repeat-passwd").val("");

		var userarr = document.getElementById("user-passwd-div").getElementsByTagName("span");
		var repeatarr = document.getElementById("repeat-passwd-div").getElementsByTagName("span");
		if(userarr.length != 0){
			userarr[0].style.display="none";
		}
		if(repeatarr.length != 0){
			repeatarr[0].style.display="none";
		}
		
		$("#changePasswdForm").validate();
		$('#passwd-change').modal('show');
	}

	function changePasswd() {
		
		var old = $("#old-passwd").val();
		var newPasswd = $("#user-passwd").val();
		$.getJSON("${root}/system/user/changepasswd/" + old + "/" + newPasswd,
			function(data) {
				if (data.result == 'ok') {
					//alert("修改成功");
					$("#old-passwd").val("");
					$("#user-passwd").val("");
					$("#repeat-passwd").val("");
					$('#passwd-change').modal('hide');
				}

				if (data.result == '502') {
					//alert(data.message);
					window.top.location.href = "${root}/login";
				}

				if (data.result == 'error') {
					alert(data.message);
				
				}
			});
	}
	$("#progress-bar").floatdiv({top:"40px",right:"200px"});
	
	/**
	*图片特写方法
	*/
	function tipsdownImgFd(titleContext,imgpath,left,top){
		if(imgpath=='undefined' || imgpath=="" || imgpath =="null"){
			alert("图片路径不正确");
		}else{
		var leftvalue="";
		var topvalue="";
		if(left==undefined||left==''||left==null){
			leftvalue='';
		}else{
			leftvalue=left;
		}
		if(top==undefined||top==''||top==null){
			topvalue='';
		}else
		{
			topvalue=top;
		}
		tipsWindown(titleContext,'iframe:${root}/fd.jsp?imgpath='+imgpath,'1010','407','true','','true','content',leftvalue,topvalue);
		}
		
	}
	
</script> 
<c:forEach items="${menus}" var="menu" varStatus="m">
<script type="text/javascript">
	var temp = new Array();
	// 点击功能菜单左侧应用（add by kouyunhao 2014-01-02 添加点击当前菜单，其他打开菜单关闭效果）
	$("#menu_${menu.id}").click(function(){
		var name = $("#menu_${menu.id}").html();
		if(name == '地图'){
			//岩涛 ADD 屏蔽左侧菜单区域 ， 并修改GIS的最外围框架为全屏，并重定向GIS请求地址
			$("#leftMenu").attr("width","40");
			$("#LeftBar .left_bar_title").html("");
			$("#LeftBar").attr("style", "width:40px");
			$("#submenu_frame").css("display", "none");
			$("#content-frame").attr("src", "${root}/map/home/${menu.id}/");
		}else{
			getsubmenu('${menu.id}',true);
			$("#leftMenu").attr("width","212");
			$("#LeftBar .left_bar_title").html("操作导航");
			$("#LeftBar").attr("style", "width:210px");
			$("#submenu_frame").css("display", "block");
		}
	});
	
	function getsubmenu(id,flag){
		$.getJSON("${root}/home/newindex/getsubmenu/" + id,
			function(data) {
				$("#submenu_frame").html(data.content);
				if(flag){
					showSysIndex(data.url);
				}
			});
	}
	
	function showSysIndex(url){
		$("#content-frame").attr("src", "${root}/"+url);
	}
	
	var point1 = new Array();
	//鼠标滑过时改变菜单树背景颜色
	function changeBC(id){
		try{
			point1.push(id);
			if(point1.length > 2)
			point1.splice(0,1);
			$(".nav_second li").each(function() {
				$("#"+point1[point1.length-1]).attr("style", "background:url('../../images/picone/two_li_bg.png') repeat-x 0 0 transparent;");
				if(point1.length == 2){
					if(point[1] != point1[0]){
						$("#"+point1[0]).removeAttr("style");
					}
				}
			});
		}catch(e){
		}
		
	}
	
	var point = new Array();
	point.push("");
	function menuOnclick(id, name,pname,url) {
		point.push(id);
		if(point.length > 2)
		point.splice(0,1);
		//岩涛 ADD 2014.11.11
		var urltemp = url.substring(0,4);
		if(urltemp == "http"){
		  //智能锁具定制
		  if(url.indexOf("IntelligentLock") > 0 ){
			    var user_name = "hebo";
				var user_pwd = "123";
				var code = "xx";
			    $.ajax({
					type:"POST",
					url:"/IntelligentLock/user/Login",
					data:{
						"user_id":user_name,
						"user_pwd":user_pwd,
						"comp_machine_code":code
					},
					success:function(returnedData){
						var jo0 = eval("("+returnedData+")");
						if(jo0.check=="1"){
							if(cookieEnabled()){
		   						setCookie("user_name", user_name, 365);
		   						setCookie("code", code, 365);
		   					}
							 $("#content-frame").attr("src", "/IntelligentLock/jsp/system/index.jsp");
						}else{
							alert("智能锁具系统系统账号有误!");
							$("#content-frame").attr("src", "/IntelligentLock/jsp/login/login.jsp");
						}
					}
				}); 
		  }else{
			  $("#content-frame").attr("src", url);
		  }
		}else{
		  $("#content-frame").attr("src", "${root}/" + url);
		}
	}
</script>
</c:forEach>
<script type="text/javascript">
//顶部报警
function show_newalerm(){
     $.ajax({
                type : "get",
                url : "${root}/alarm/log/newAlarm?temp="+Math.random(),
                dataType : "json",
                success : function(msg) {
                if(msg.success){
                   if(msg != undefined){
                      $("#notice_ul").html("<li><a href=\"javascript:showAlarmLogDialog('"+msg.data.id+"')\">"+msg.data.deviceName+"&nbsp;&nbsp;"+msg.data.eventTypeName+"&nbsp;&nbsp;"
                      +"&nbsp;&nbsp;"+msg.data.alarmTimeStr+"&nbsp;&nbsp;</a></li>");
                      var alarmTypeStr = msg.data.alarmType;
                      if(alarmTypeStr.indexOf("声音告警")!=-1){
                        if ($.browser.safari) {
			  		      play1();
				        }else{
				 	      play2();
				        }
                      }
                      if(alarmTypeStr.indexOf("短信提醒")!=-1){
                         
                      }
                      
                   }else{
                	   $("#notice_ul").html("");
                   }
                }
                },error:function() {
                  
                }
      });
}
setInterval('show_newalerm()',5000);


//告警日志详情弹出框
function showAlarmLogDialog(id) {
	var url = "${root}/home/newindex/alarm/log/view/" + id + "/";
	$.ajax( {
		type : 'GET',
		url : url,
		dataType : "text",
		success:function(data) {
			if(data != null){
				debugger;
				$("#alarmlog_div").html(data);
			}
		}
	});
	$('#alarmlog-modal').modal('show');
}

function alarmLog(id) {
	showAlarmLogDialog(id);
}

//派发工单弹出框
function showassignDialog(deviceid,devicetype) {
	debugger;
	var url = "${root}/home/newindex/showassign/" + deviceid + "/" + devicetype + "/";
	$.ajax( {
		type : 'GET',
		url : url,
		dataType : "text",
		success:function(data) {
			if(data != null){
				debugger;
				$("#showassign_div").html(data);
			}
		}
	});
	$('#alarmlog-modal').modal('hide');
	$('#showassign-modal').modal('show');
}

function showassign(deviceid,devicetype) {
	showassignDialog(deviceid,devicetype);
}

//问题信息详情弹出框
function showQuestionLogDialog(id) {
	var url = "${root}/home/newindex/question/log/view/" + id + "/";
	$.ajax( {
		type : 'GET',
		url : url,
		dataType : "text",
		success:function(data) {
			if(data != null){
				$("#questionlog_div").html(data);
			}
		}
	});
	$('#questionlog-modal').modal('show');
}

function questionLog(id) {
	showQuestionLogDialog(id);
}

//工单信息详情弹出框
function showWorkorderLogDialog(id) {
	var url = "${root}/home/newindex/workorder/log/view/" + id + "/";
	$.ajax({
		type : 'GET',
		url : url,
		dataType : "text",
		success:function(data) {
			if(data != null){
				$("#workorderlog_div").html(data);
			}
		}
	});
	$('#workorderlog-modal').modal('show');
}

function workorderLog(id) {
	showWorkorderLogDialog(id);
}

function showTelephone(a){
	if(a.value == ''){
		$("#telephone").val("");
		$("#messages").html("");
		return;
	}
	var data = $("#contact option:selected");
	if (data) {
		var json = $(data).attr("userdata");
		var obj = $.parseJSON(json);
		$("#telephone").val(obj.mobile);
		var message = '${factory.name}' + "的" + obj.name + "，你好："+
		"位于[${asset.installplace}]上的IP为[${asset.ip}]的设备[${asset.assetname}]发生故障，请及时维修。";
		$("#messages").html(message);
	}
}

function doAssign(devicetype){
	debugger;
	input_form.action = "${root}/home/newindex/doAssign/"+devicetype+"/";
	input_form.method = "post";
	input_form.submit();
}	
</script>

<script type="text/javascript" >
//报警声音
function play1(){
var s=document.getElementById("snd");
s.src="${root}/sound/alert.mp3";
}
function play2(){
var s=document.getElementById("sndie");
s.src="${root}/sound/alert.mp3";
}
</script>

<script type="text/javascript" >
function cookieEnabled(){
	if(!(document.cookie || navigator.cookieEnabled)){
		alert("未记住用户编号、密码,浏览器已被禁用Cookie！");
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
<bgsound src="" id="sndie" >
<audio src="" id="snd" autoplay="true" playcount="-1"></audio>