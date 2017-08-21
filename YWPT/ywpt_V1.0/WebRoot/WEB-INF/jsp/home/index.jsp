<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.xiangxun.atms.common.system.service.ParamsService" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String sessionid = request.getSession().getId();
	request.setAttribute("sessionId", sessionid);
	//String skincssType = ParamsService.SYSTEM_PARAMS.get("skincssType");
%>
<!-- index2 -->
<link href="${root}/compnents/bootstrap/css/bootstrap-cerulean.css" rel="stylesheet" type="text/css" />
<link href="${root}/compnents/bootstrap/css/charisma-app.css" rel="stylesheet">
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" type="text/css" />
<link id="TipsCss" href="${root}/css/tipswindown/tipswindown.css" type="text/css" rel="stylesheet" media="all" />
<link id="MainCss" href="${root}/css/transport.css" type="text/css" rel="stylesheet">
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/js/tree.js'/>" type="text/javascript"></script>
<script type="text/javascript">
	var root='${root}';
	var webroot='<%=basePath%>'
</script>
<script src="${root}/js/leftmenu.js" type="text/javascript"></script>
<script src="${root}/js/jquery.float-div.js" type="text/javascript"></script>
<script type="text/javascript" src="${root}/js/tipswindown/tipswindownFd.js"></script>
<script type="text/javascript" src="${root}/js/cross/jms/amq_jquery_adapter.js"></script>
<script type="text/javascript" src="${root}/js/cross/jms/amq.js"></script>
<script type="text/javascript" src="${root}/swfs/videoplayer/swfobject.js"></script>
<script type="text/javascript">
      var swfVersionStr = "10.0.0";
      var xiSwfUrlStr = "";
      var flashvars = {};
      var params = {};
      params.quality = "high";
      params.bgcolor = "#ffffff";
      params.allowscriptaccess = "sameDomain";
      params.allowfullscreen = "true";
      var attributes = {};
      attributes.id = "videoPlayer";
      attributes.name = "videoPlayer";
      attributes.align = "middle";
      swfobject.embedSWF("videoPlayer.swf?width=300&height=300", "flashContent", "100%", "100%", swfVersionStr, xiSwfUrlStr, flashvars, params, attributes);
	  swfobject.createCSS("#flashContent", "display:block;text-align:left;");
</script>

<script type="text/javascript">
	//切换皮肤方法
	function skinCss(){
		var skin=$("#skin-sel option:selected").val();
		if(skin=="1"){
			$("#MainCss").attr("href","${root}/css/transport.css");
			$("#TipsCss").attr("href","${root}/css/tipswindown/tipswindown.css");
			//$("#skin-sel").removeClass("skin-sel").addClass("skin-sel-blue");//皮肤切换时同步改变选择框背景色。add by kouyunhao 2014-03-24
			$("#skin-sel").attr("class","shin-sel-blue");
		}else if(skin=="2"){
			$("#MainCss").attr("href","${root}/cssGreen/transport.css");
			$("#TipsCss").attr("href","${root}/cssGreen/tipswindown.css");
			//$("#skin-sel").removeClass("skin-sel-blue").addClass("skin-sel");//皮肤切换时同步改变选择框背景色。add by kouyunhao 2014-03-24
			$("#skin-sel").attr("class","skin-sel");
		}else if(skin=="3"){
			$("#MainCss").attr("href","${root}/cssDarkBlue/transport.css");
			$("#TipsCss").attr("href","${root}/cssDarkBlue/tipswindown.css");
			//$("#skin-sel").removeClass("skin-sel-blue").addClass("skin-sel");//皮肤切换时同步改变选择框背景色。add by kouyunhao 2014-03-24
			$("#skin-sel").attr("class","skin-sel-blue");
		}
		jQuery.ajax({
			type : "GET",  
			async: false,
			url: "${root}/system/skin/change_skin/" + skin + "/?time="+new Date(),
			success : function(data) {
				//alert(data);
			}
		});
	}
	
	$(document).ready(function(){
		//页面加载时获取页面风格参数
		var skincssType = '${skincssType}';
		if(skincssType=="1"){
			$("#MainCss").attr("href","${root}/css/transport.css");
			$("#TipsCss").attr("href","${root}/css/tipswindown/tipswindown.css");
			//$("#skin-sel").removeClass("skin-sel").addClass("skin-sel-blue");//皮肤切换时同步改变选择框背景色。add by kouyunhao 2014-03-24
			$("#skin-sel").attr("class","shin-sel-blue");
		}else if(skincssType=="2"){
			$("#MainCss").attr("href","${root}/cssGreen/transport.css");
			$("#TipsCss").attr("href","${root}/cssGreen/tipswindown.css");
			//$("#skin-sel").removeClass("skin-sel-blue").addClass("skin-sel");//皮肤切换时同步改变选择框背景色。add by kouyunhao 2014-03-24
			$("#skin-sel").attr("class","skin-sel");
		}else if(skincssType=="3"){
			$("#MainCss").attr("href","${root}/cssDarkBlue/transport.css");
			$("#TipsCss").attr("href","${root}/cssDarkBlue/tipswindown.css");
			//$("#skin-sel").removeClass("skin-sel-blue").addClass("skin-sel");//皮肤切换时同步改变选择框背景色。add by kouyunhao 2014-03-24
			$("#skin-sel").attr("class","skin-sel-blue");
		}
		$('#skin-sel').val(skincssType);
	});
</script>
<script type="text/javascript">
	function changeBlackgroud(a){
		$("#"+a).attr("style","background:url(${root}/images/picone/sys_menu_bg.png) repeat;");
	}
	function resetBlackgroud(a){
		$("#"+a).removeAttr("style");
	}
	
	//设置主界面自适应高度。
	// 是否可以改变
	var isCanChange = true;
	
	// 每100毫秒允许改变一下
	//setInterval("change()",100);


	function change(){
		isCanChange = true;
	}
	
	$(function(){
		var indexH = "";
		if($.browser.safari){
			indexH = window.screen.availHeight-150;
			$("#mainInfo").height(indexH);
		}else if($.browser.msie){
			indexH = window.screen.availHeight-window.screenTop-95;
			$("#mainInfo").height(indexH);
		}else if($.browser.mozilla){
			if($.browser.version=='11.0'){
				indexH = window.screen.availHeight-window.screenTop-95;
				$("#mainInfo").height(indexH);
			}else{
				indexH = window.screen.availHeight-window.mozInnerScreenY-90;
				$("#mainInfo").height(indexH);
				$("#pageMultiple").height(indexH);
			}
		}
		$("#pageMultiple").height(indexH-25);
	
		window.onresize=function(){
			if(isCanChange){
				var indexH = "";
				if($.browser.safari){
					indexH = window.screen.availHeight-150;
					$("#mainInfo").height(indexH);
				}else if($.browser.msie){
					indexH = window.screen.availHeight-window.screenTop-95;
					$("#mainInfo").height(indexH);
				}else if($.browser.mozilla){
					if($.browser.version=='11.0'){
						indexH = window.screen.availHeight-window.screenTop-95;
						$("#mainInfo").height(indexH);
					}else{
						indexH = window.screen.availHeight-window.mozInnerScreenY-90;
						$("#mainInfo").height(indexH);
					}
				}
				$("#pageMultiple").height(indexH-25);
				
				isCanChange = false;
			}
		}
	});
</script>
<!-- 头部 starts -->
<div class="container-fluid header" style="padding:0 5px;">
  <div class="logo"></div>
  <div class="ladtnews" id="notice">
    <ul id="notice_ul" style="float:left; margin-left:0;" class="scroll-container"></ul>
  </div>
  <div style="float:right;">  
    <!-- 皮肤切换开始 -->
    <div class="pull-right" style="margin-top:11px; margin-right:10px;">
      <select id="skin-sel" class="skin-sel" style="width:90px;padding:4px;height:28px;margin:0;" onchange="skinCss()" >
        <option value="1">天空蓝</option>
        <option value="2">清新绿</option>
        <option value="3">深邃蓝</option>
      </select>
    </div>
    <!-- 皮肤切换结束 -->
    <!-- 用户上下收缩开始 -->
    <div class="btn-group pull-right" style="margin-top:11px; margin-right:6px;background:none;">
      <a class="btn dropdown-toggle border-btn" data-toggle="dropdown" href="#" id="dropdown-toggle">
        <i class="icon-user pull-left"></i><span class="pull-left mar_l5">${userName}</span> <span class="caret"></span>
      </a>
      <ul class="dropdown-menu" id="dropdown-menu">
        <li><a href="javascript:showChangePasswdDialog()">修改密码</a></li>
        <li><a href="${root}/j_spring_security_logout">安全退出</a></li>
        <!-- <li><a href="${root}/downloadfile/ITMS_UserManual.doc/?filepath=sysfile/ITMS_UserManual.doc">手册下载</a></li>
        <li><a href="${root}/downloadfile/ITMS_UpdateLog.doc/?filepath=sysfile/ITMS_UpdateLog.doc">修改日志</a></li> -->
      </ul>
    </div>
    <!-- 用户上下收缩结束 -->
  </div>
</div>
<!-- 顶部栏结束 -->
<!-- 主栏目打开后显示的标签栏开始 -->
<div id="mainnavbar">
  <div class="menu_main_h menu_main_op" id="main-interface"><a href="#" onclick="showMain('${root}/home/welcome/','主界面')" style="text-align:center;">主界面</a></div>
</div>
<!-- 主栏目打开后显示的标签栏结束 -->
<div id="mainInfo" class="row-fluid main-box">
  <div class="container-fluid" style="margin:0; padding:0;">
  	<c:if test="${isShowComplex==1}">
    <div id="mainPage" class="row-fluid" style="display:none;">
  	</c:if>
 	<c:if test="${isShowComplex!=1}">
    <div id="mainPage" class="row-fluid" >
 	</c:if>
      <!-- 系统搜索部分开始 -->
      <div style="padding:50px 0 30px;"></div>
      <!-- 系统搜索部分结束 -->
      <div class="rollBox">
        <!-- 修正原来toolbar条覆盖系统图标部分，点击不起作用的bug --> 
        <cite class="pull-left LeftBotton"><img src="${root}/images/picone/left_arr.png"></cite> 
       	<cite class="pull-right RightBotton"><img src="${root}/images/picone/right_arr.png"></cite>
        <!-- 图片列表 begin -->
        <c:if test="${modulenum==6}">
          <div class="Cont" id="scr_list" style="margin-bottom:-30px;">
            <div id="List1" style="margin-top:30px;">
              <!-- 图片列表 begin -->
              <div id="menupage_1" class="menupage" style="width:980px; height:340px; float:left; margin:0;">
	              <c:forEach items="${menus}" var="menu" varStatus="m">
	                <div class="pic" id="menu_${menu.id}" onmouseover="changeBlackgroud('menu_${menu.id}')" onmouseout="resetBlackgroud('menu_${menu.id}')"><a href="#" style="display:block;"><img src="${root}/images/picone/mainmenu/${menu.picName}.png"/></a>
	                  <p class="list1_nav_p"><a class="nav_name_list1" href="#">${menu.name}</a></p>
	                </div>
	              </c:forEach>
             	 <div class="clear"></div>		              
              </div>
              <!-- 图片列表 end -->
              <div class="clear"></div>
            </div>
            <div id="List2" style="width:980px; height:430px; margin:20px 0 0 0;"> </div>
          </div>
        </c:if>
        <c:if test="${modulenum==7}">
          <div class="Cont2" id="scr_list">
            <div id="List1">
              <div id="menupage_1" class="menupage" style="width:980px;height:430px; float:left; margin:0;">
	              <c:forEach items="${menus}" var="menu" varStatus="m">
	                <div class="pic-2 pull-left" id="menu_${menu.id}" onmouseover="changeBlackgroud('menu_${menu.id}')" onmouseout="resetBlackgroud('menu_${menu.id}')">
	                  <a href="#" style="display:block;"><img style="width:80px; height:80px; margin:10px 0 6px;" src="${root}/images/picone/mainmenu/${menu.picName}.png"/></a>
	                  <p class="list1_nav_p"><a class="nav_name_list1" href="#">${menu.name}</a></p>
	                </div>
	              </c:forEach>
             	 <div class="clear"></div>		              
              </div>
              <!-- 图片列表 end -->
              <div class="clear"></div>
            </div>
            <div id="List2" style="width:980px; height:430px; margin:0;"> </div>
          </div>
        </c:if>
        <c:if test="${modulenum==8}">
          <div class="Cont3" id="scr_list">
            <div id="List1">
              <!-- 图片列表 begin -->
              <div id="menupage_1" class="menupage" style="width:980px;height:430px; float:left; margin:0;">
	              <c:forEach items="${menus}" var="menu" varStatus="m">
	                <div class="pic-3 pull-left" id="menu_${menu.id}" onmouseover="changeBlackgroud('menu_${menu.id}')" 
	                onmouseout="resetBlackgroud('menu_${menu.id}')">
	                <a href="#" style="display:block;"><img style="width:70px; height:70px; margin:10px 0 6px;" 
	                src="${root}/images/picone/mainmenu/${menu.picName}.png"/></a>
	                  <p class="list1_nav_p"><a class="nav_name_list1" href="#">${menu.name}</a></p>
	                </div>
	              </c:forEach>
             	 <div class="clear"></div>		              
              </div>
              <!-- 图片列表 end -->
              <div class="clear"></div>
            </div>
            <div id="List2" style="width:980px; height:430px; margin:0;"> </div>
          </div>
        </c:if>
      </div>
      <div class="clear"></div>
    </div>
    <!-- 主界面图标区域结束 -->
    <!-- 主界面综合统计页面----------开始 -->
    <c:if test="${isShowComplex==1}">
	<div id="pageMultiple" class="row-fluid char-area">
	  <table width="100%" style="margin:0;">
		  <tr>
		    <td id="col0" width="20%" valign="top">
		      <div class="mov_div" id="move0">
		        <h6 class="title_move"><span><img src="${root}/images/policecar.png"></span>122接警信息</h6>
		        <div class="mov_con">
                  <table class="table table-border-bot table-border-rl table-condensed" style="margin:0;">
                      <tr>
                        <td class="td_bg4" width="65">事故总数：</td>
                        <td><b style="color:#ff0000;font-size:12px;">2324</b></td>
                        <td class="td_bg4" width="65">直行事故：</td>
                        <td><b style="color:#6600ff;font-size:12px;">343</b></td>
                      </tr>
                      <tr>
                        <td class="td_bg4">追尾事故：</td>
                        <td><b style="color:#66aa44;font-size:12px;">765</b></td>
                        <td class="td_bg4">超车事故：</td>
                        <td><b style="color:#660000;font-size:12px;">434</b></td>
                      </tr>
                      <tr>
                        <td class="td_bg4">转弯事故：</td>
                        <td><b style="color:#87a920;font-size:12px;">845</b></td>
                        <td class="td_bg4"></td>
                        <td></td>
                      </tr>
                  </table>                  
                </div>
		      </div>
		      <div class="mov_div" id="move1">
		        <h6 class="title_move"><span><img src="${root}/images/police.png"></span>当前警力</h6>
		        <div class="mov_con">
                  <table class="table table-border-bot table-border-rl table-condensed" style="margin:0;">
                      <tr>
                        <td class="td_bg4" width="65">警员</td>
                        <td>总数：<b style="color:#6600ff;font-size:12px;">845</b></td>
                        <td>在线：<b style="color:#87a920;font-size:12px;">344</b></td>
                      </tr>
                      <tr>
                        <td class="td_bg4">摩托车</td>
                        <td>总数：<b style="color:#6600ff;font-size:12px;">845</b></td>
                        <td>在线：<b style="color:#87a920;font-size:12px;">553</b></td>
                      </tr>
                      <tr>
                        <td class="td_bg4">警车</td>
                        <td>总数：<b style="color:#6600ff;font-size:12px;">845</b></td>
                        <td>在线：<b style="color:#87a920;font-size:12px;">235</b></td>
                      </tr>
                  </table>                  
                </div>
		      </div>
		      <div class="mov_div" id="move2">
		        <h6 class="title_move unvisible" style="margin-bottom:-36px;position:relative;z-index:100;"></h6>
		        <table width="100%" style="margin:0;">
		          <tr>
		            <td width="50%" class="border-right">
		              <h4 class="title_move"><span><img src="${root}/images/cross.png"></span>卡口</h4>
		              <ul class="ul_cross_index span_65" style="margin:0;">
			            <li><span>本日总计：</span><b style="color:#87a920;font-size:12px;">246</b></li>
			            <li><span>本周总计：</span><b style="color:#6600ff;font-size:12px;">945</b></li>
			            <li><span style="border:0;">本月总计：</span><b style="color:#000;font-size:12px;">4544</b></li>
			          </ul>
		            </td>
		            <td>
		              <h4 class="title_move"><span><img src="${root}/images/newIndex/kakou.png"></span>违法</h4>
		              <ul class="ul_cross_index span_65" style="margin:0;">
			            <li><span>本日总计：</span><b style="color:#87a920;font-size:12px;">444</b></li>
			            <li><span>本周总计：</span><b style="color:#6600ff;font-size:12px;">1233</b></li>
			            <li><span style="border:0;">本月总计：</span><b style="color:#000000;font-size:12px;">3423</b></li>
			          </ul>
		            </td>
		          </tr>
		        </table>
		        <div class="clear"></div>
		      </div>
		      <div class="mov_div" id="move3">
		        <h6 class="title_move"><span><img src="${root}/images/newIndex/screen.png"></span>设备状态</h6>
		        <div class="mov_con">
                  <table class="table table-border-bot table-border-rl" id="table" style="margin:0;">
                    <tbody>
                      <tr>
                        <td><b>录入设备数：</b><b style="color:#87a920;font-size:12px;">4566</b></td>
                      </tr>
                      <tr>
                        <td><b>正常设备数：</b><b style="color:#8709ff;font-size:12px;">2566</b></td>
                      </tr>
                      <tr>
                        <td><b>故障设备数：</b><b style="color:#ff0000;font-size:12px;">564</b></td>
                      </tr>
                      <tr>
                        <td><b>未接入设备：</b><b style="color:#666;font-size:12px;">564</b></td>
                      </tr>
                    </tbody>
                  </table>  
		        </div>
		      </div>
		    </td>
		    <td id="col1" valign="top">
		      <div class="mov_div" id="move4">
		        <h6 class="title_move"><span><img src="${root}/images/map.png"></span>地图管理</h6>
		        <div style="height:350px;">
		          <iframe id="map-Index" width="100%" height="350" frameborder="0" style="margin:0;padding:0;" src="${root}/forward/map/mapTools/iframe/iframe_map/"></iframe>
		        </div>
		      </div>
		      <div class="mov_div" id="move5">
		        <h6 class="title_move"><span><img src="${root}/images/device.png"></span>道路挖占</h6>
		        <div class="mar_5">
			        <table class="table table-border-bot table-border-rl table-condensed" style="margin:0;">
			          <tr>
			            <td width="25%" class="td_bg4 bor-last"><b>挖占项目总计：</b><b style="color:#6600ff;font-size:12px;">3444</b></td>
			            <td width="25%"><b>施工中项目：</b><b style="color:#87a920;font-size:12px;">1233</b></td>
			            <td width="25%" class="td_bg4 bor-last"><b>快到期项目：</b><b style="color:#000000;font-size:12px;">1123</b></td>
			            <td><b>已到期项目：</b><b style="color:#000000;font-size:12px;">1123</b></td>
			          </tr>
			        </table>
		        </div>	         
		      </div>
		      <div class="mov_div" id="move6">
		        <h6 class="title_move"><span><img src="${root}/images/chart_bar.png"></span>实时路况</h6>
		        <div class="mar_5 div-bor-top table-border-rl">
		            <ul class="ul_cross" style="margin:0;">
			            <li><span><b>统计路段总数：</b><b style="color:#6600ff;font-size:12px;">4243</b></span></li>
			            <li><b>畅通：</b><b style="color:#87a920;font-size:12px;">1233</b></li>
			            <li><span><b>拥堵：</b><b style="color:#7d7d7d;font-size:12px;">1523</b></span></li>
			            <li><b>阻塞：</b><b style="color:#000000;font-size:12px;">1023</b></li>
			            <div class="clear"></div>
			        </ul>
		            <ul class="ul_cross2" style="margin:0;">
			            <li><span><b>最拥堵TOP5：</b><code class="mar_r5">太白北路边家村十字</code><code class="mar_r5">光华路由东向西路口</code><code class="mar_r5">光华路由东向西路口</code><code class="mar_r5">光华路由东向西路口</code><code class="mar_r5">光华路由东向西路口</code></span></li>
			            <div class="clear"></div>
			        </ul>
		        </div>
		      </div>
		    </td>
		    <td id="col2" width="20%" valign="top">
		      <div class="mov_div" id="move7">
		        <h6 class="title_move"><span><img src="${root}/images/clock.png"></span>系统时间</h6>
		        <div class="mov_con" id="Time" style="padding:5px;height:30px;line-height:30px;">
		        <script>setInterval("Time.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);</script>
		        </div>
		      </div>
		      <div class="mov_div" id="move8">
		        <h6 class="title_move"><span><img src="${root}/images/page.png"></span>值班表</h6>
		        <div class="mov_con">
                  <table class="table table-bordered table-condensed table-style" id="table" style="margin:0;">
                    <thead>
                      <tr>
                        <th>姓名</th>
                        <th>时间</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>张三</td>
                        <td>2014-10-12</td>
                      </tr>
                      <tr>
                        <td>张三</td>
                        <td>2014-10-12</td>
                      </tr>
                      <tr>
                        <td>张三</td>
                        <td>2014-10-12</td>
                      </tr>
                      <tr>
                        <td>张三</td>
                        <td>2014-10-12</td>
                      </tr>
                    </tbody>
                  </table>                  
		        </div>
		      </div>
		      <div class="mov_div" id="move9">
		        <h6 class="title_move"><span><img src="${root}/images/star.png"></span>通行证信息</h6>
		        <div class="mov_con">
                  <table class="table table-border-bot table-border-rl table-condensed" style="margin:0;">
                      <tr>
                        <td class="td_bg4" width="110">通行证信息总数：</td>
                        <td><b style="color:#8700ff;font-size:12px;">1235</b></td>
                      </tr>
                      <tr>
                        <td class="td_bg4">待审核总数：</td>
                        <td><b style="color:#000;font-size:12px;">735</b></td>
                      </tr>
                      <tr>
                        <td class="td_bg4">已审核总数：</td>
                        <td><b style="color:#87a920;font-size:12px;">335</b></td>
                      </tr>
                  </table>                  
		        </div>
		      </div>
		    </td>
		  </tr>
	  </table>
	</div>
	</c:if>
    <!-- 主界面综合统计页面----------结束 -->
    
    <!-- 分页按钮开始 -->
    <div id="operation_bar" class="operation_bar">
      <div class="options">
		  <table class="op_table" height="30" style="margin:0 auto;">
		    <tr>
		      <td class="control_l"></td>
		      <td class="control_c" id="smalPic">
		      <c:if test="${isShowComplex==1}">
		        <a id="multiple" href="#"><img src="${root}/images/picone/cur_dot.png"></a>
		        <a id="pic1" href="#"><img src="${root}/images/picone/other_dot.png"></a>
		      </c:if>
		      <c:if test="${isShowComplex!=1}">
		        <a id="pic1" href="#"><img src="${root}/images/picone/cur_dot.png"></a>
		      </c:if>
		        <a id="pic2" href="#"><img src="${root}/images/picone/other_dot.png"></a>
		        <a id="pic3" style="display:none" href="#"><img src="${root}/images/picone/other_dot.png"></a>
		        <a id="pic4" style="display:none" href="#"><img src="${root}/images/picone/other_dot.png"></a>
		        <a id="pic5" style="display:none" href="#"><img src="${root}/images/picone/other_dot.png"></a>
		        <a id="pic6" style="display:none" href="#"><img src="${root}/images/picone/other_dot.png"></a>
		        <a id="pic7" style="display:none" href="#"><img src="${root}/images/picone/other_dot.png"></a>
		        <a id="pic8" style="display:none" href="#"><img src="${root}/images/picone/other_dot.png"></a>
		        <a id="pic9" style="display:none" href="#"><img src="${root}/images/picone/other_dot.png"></a>
		        <h><span style="color: white;">|</span></h></td>
		      <td class="control_op"><img id="dropmenu_btn" src="${root}/images/picone/gongneng.png"></td>
		      <td class="control_r"></td>
		    </tr>
		  </table>
		  <div class="dropmenu">
		    <h3>功能菜单<a class="close-btn" href="#">关闭</a></h3>
		    <div id="tab_nav"> <a href="#t_1"><span><img src="${root}/images/picone/yingyong.png"></span>应用设置</a> <a href="#t_2"><span><img src="${root}/images/picone/fenping.png"></span>分屏设置</a>
		      <div id="datainfo"></div>
		    </div>
		    <div id="tab_content"  style="height:340px;">
		      <div id="t_1"  style="height: 340px;">
		        <!-- 图片列表 begin -->
		        <div class="sidebar" style="overflow-y:scroll;">
		          <ul class="sub-menu" style="margin-left:0; padding-left:0; text-align: center;">
		            <c:forEach items="${menus}" var="menu" varStatus="m">
		              <li id="2_level_menu"><a id="img_${menu.id}" class="current"> ${menu.name}</a></li>
		              <div class="secMenu" id="submenu_frame_${menu.id}"></div>
		            </c:forEach>
		          </ul>
		        </div>
		        <!-- 左边菜单栏结束 -->
		        <div class="right_con"> </div>
		        <!-- 右边内容栏结束 -->
		      </div>
		      <div id="t_2" style="height:340px;">
		        <div id="scr1" class="div_box del"> <span></span> </div>
		        <div id="scr2" class="div_box del"> <span></span></div>
		        <div id="scr3" style="display: none" class="div_box del"> <span></span></div>
		        <div id="scr4" style="display: none" class="div_box del"> <span></span></div>
		        <div id="scr5" style="display: none" class="div_box del"> <span></span></div>
		        <div id="scr6" style="display: none" class="div_box del"> <span></span></div>
		        <div id="scr7" style="display: none" class="div_box del"> <span></span></div>
		        <div id="scr8" style="display: none" class="div_box del"> <span></span></div>
		        <div id="scr9" style="display: none" class="div_box del"> <span></span></div>
		        <div class="div_box add" title="点击添加桌面" style="cursor: pointer;"></div>
		      </div>
		    </div>
		  </div>
	  </div>   
    </div>
  </div>
</div>
<div id="contentInfo" class="row-fluid" style="margin:0px;padding:0; padding-top:5px; display:none;">
  <!-- left menu starts -->
  <div id="leftMenuDiv" class="span2" style="float:left;padding:0; margin:0;margin-bottom:40px;">
    <!-- 左边菜单开始 -->
    <div id="leftMenu"></div>
    <!-- 左侧菜单列结束-->
  </div>
  <div id="content" class="span10">
    <!-- content starts -->
    <div id="lr_btn" class="lr_btn lr_btn_bg_l"></div>
    <!-- 界面左右展开闭合按钮 -->
    <div class="span12" style="margin:0; margin-top:-480px;">
      <iframe id="content-frame" frameborder="0" scrolling="no" width="100%" style="margin:0; padding:0; margin-bottom:40px;background:none;" src=""></iframe>
    </div>
  </div>
</div>
<div class="clear"></div>
<div class="modal hide fade" id="passwd-change">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal">×</button>
    <h2 style="text-shadow:none;color:#333;">修改密码</h2>
  </div>
  <div class="modal-body">
    <form id="changePasswdForm" class="form-horizontal">
      <div class="control-group">
        <label class="control-label"> 原密码： </label>
        <div class="controls">
          <input type="password" id="old-passwd" placeholder="原密码" name="oldPwd" value="" class="input-large required" maxlength="20" />
        </div>
      </div>
      <div class="control-group">
        <label class="control-label"> 新密码： </label>
        <div class="controls">
          <input type="password" id="user-passwd" placeholder="新密码" name="pwd" value="" class="input-large required" minlength=8 maxlength="20" />
        </div>
      </div>
      <div class="control-group">
        <label class="control-label"> 重复一次： </label>
        <div class="controls">
          <input type="password" id="repeat-passwd" name="repeatPasswd" placeholder="重复一次" value="" equalTo="#user-passwd" class="input-large required" minlength=8 maxlength="20" />
        </div>
      </div>
    </form>
  </div>
  <div class="modal-footer">
    <a href="javascript:changePasswd()" class="btn btn-primary" style="color:#fff;">修改</a>
  	<a href="#" class="btn" data-dismiss="modal">关闭</a>
  </div>
</div>
<!-- 应急指挥 文档管理添加、修改页面遮罩开始 -->
<div class="modal hide fade" id="uploading_div">
  <div class="modal-header">
    <h4>文件上传</h4>
  </div>
  <div class="modal-body">
  	<img src="${root}/images/loading32.gif" /><font style="color: red; font-size: small;">&nbsp;文档上传中，请稍候...上传完成后系统将自动关闭该窗口，期间请勿操作！</font>
  </div>
</div>
<div class="clear"></div>
<!-- 应急指挥 文档管理添加、修改页面遮罩结束 -->
<div id="progress-bar" style="background-color: #666633; width:50px; height:50px; padding: 2px; color: white;display: none;"></div>
<div class="company-info"><span class="mar_l10">Copyright &copy; 西安翔迅科技有限责任公司</span>
<span class="shouce_download"  id="flashContent"><font color="red">系统提示：</font>系统未安装Flash插件，或现安装版本低于10.0.0
  <a href='${root}/downloadfile/AdobeFlashPlayer.zip/?filepath=sysfile/AdobeFlashPlayer.zip'>下载安装</a>
</span>
</div>

<!-- 打开主界面页面栏目 ，所点击菜单名称添加到主栏目标签中开始 --> 

<script type="text/javascript">

    function resetIframeHeight(height) {
		$("#content-frame").height(height+40);
	}
	 
	function add_zero(temp)
	{
		if(temp<10) return "0"+temp;
		else return temp;
	}
	setInterval("get_time()",1000);


  $(function(){
  	//ul_index隔行换色
  	$(".ul_index li:even").css("background","#f8f8f8");
  	$(".ul_index li:odd").css("background","#f5f5f5");
  	$(".ul_sys li:even").css("background","#f8f8f8");
  	$(".ul_sys li:odd").css("background","#f5f5f5");
  	//$(".ul_cross_index li:even").css("background","#f8f8f8");
  	//$(".ul_cross_index li:odd").css("background","#f5f5f5");
  	//$(".ul_wazhan li:even").css("background","#f8f8f8");
  	//$(".ul_wazhan li:odd").css("background","#f5f5f5");
  
    //-----------解决dropdown-toggle插件弹出下来菜单时，在子窗体中点击鼠标下拉菜单不收回问题
    $("#dropdown-toggle").toggle(function(){
    	//$("#dropdown-menu").show();
    	$(this).next().show();
    },function(){
    	//$("#dropdown-menu").show();
    	$(this).next().hide();
    });
    $("#dropdown-menu").mouseleave(function(){
		$(this).hide();
	});
	//-----------结束
	
	//-----------iframe自适应高度
	$("#content-frame").load(function() {
		var mainheight = $(this).contents().height();
		$(this).height(mainheight < 680 ? 680 : mainheight);
	});
	
	//左右展开闭合方法
  	$("#lr_btn").click(function(){
  		if($("#leftMenuDiv").is(":visible")){
  			$("#leftMenuDiv").hide();
  			$(this).addClass("lr_btn_bg_r").removeClass("lr_btn_bg_l");
  			$(this).css("left","-15px");
  			$("#content").removeClass("span10").addClass("mar_l20");
  		}else{
  			$("#leftMenuDiv").show();
  			$(this).addClass("lr_btn_bg_l").removeClass("lr_btn_bg_r");
  			$(this).css("left","-15px");
  			$("#content").addClass("span10").css("margin-left","0px;");
  		}
  	});
  	
  	//点击页面下方的圆形按钮切换主界面
  	$("#multiple").click(function(){
  		$("#pageMultiple").css("display","block");
  		$("#mainPage").css("display","none");
  		$("#multiple img").attr("src","/itms/images/picone/cur_dot.png");
  	});
  	$("#pic1").click(function(){
  		$("#pageMultiple").css("display","none");
  		$("#mainPage").css("display","block");
  		$("#multiple img").attr("src","/itms/images/picone/other_dot.png");
  	});
  	$("#pic2").click(function(){
  		$("#pageMultiple").css("display","none");
  		$("#mainPage").css("display","block");
  		$("#multiple img").attr("src","/itms/images/picone/other_dot.png");
  	});
  	$("#pic3").click(function(){
  		$("#pageMultiple").css("display","none");
  		$("#mainPage").css("display","block");
  		$("#multiple img").attr("src","/itms/images/picone/other_dot.png");
  	});
  });
</script>
<script type="text/javascript">
<!--
	var navbar = new Array();
	navbar.push('main-interface');
	
//-->
</script>
<c:forEach items="${menus}" var="menu" varStatus="m">
<script type="text/javascript">
var scrno = 1;// 当前页面标记（初始为第一个页面）
var initNum = 2;// 初始两个两个小图标与桌面对应
var idArr = new Array();
idArr.push(1);
idArr.push(2);// 初始化两个桌面保存到数组中
	//动态添加标签栏
	$("#menu_${menu.id}").click(function(){
		var tabName = $("#mainnavbar").text();
		if(tabName.indexOf("${menu.name}")>=0){
			navbar.push('${menu.picName}');
		 	if(navbar.length > 2){
		 		navbar.splice(0,1);
		 	}
		 	$("#"+navbar[1]).removeClass("menu_sys").addClass("menu_active").siblings().removeClass("menu_active").addClass("menu_sys");
			$(".menu_main_op").removeClass("menu_main_h").addClass("menu_main");
			if('地图管理'=='${menu.name}'){
			//岩涛 ADD 屏蔽左侧菜单区域， 并修改GIS的最外围框架为全屏，并重定向GIS请求地址
			  $("#leftMenuDiv").css("display","none");
			  $("#content").removeClass("span10").addClass("row-fluid");
			  $("#content-frame").attr("src", "${root}/map/home/${menu.id}/");
			}
			else{
			  $("#leftMenuDiv").css("display","block");
			  $("#content").removeClass("row-fluid").addClass("span10");
			  showLeafMenu('${menu.content}','${menu.name}');
			}
		  	$("#mainInfo").hide(500);
		  	$("#contentInfo").show(500);
		 }else{
		 	navbar.push('${menu.picName}');
		 	if(navbar.length > 2){
		 		navbar.splice(0,1);
		 	}
		   	$("#mainnavbar").append('<div id="${menu.picName}" class="menu_active menu_op"><a href="#" onclick="showLeafMenu(\'${menu.content}\',\'${menu.name}\')">${menu.name}</a><span class="close_span"></span></div>');
		   	$("#"+navbar[1]).removeClass("menu_sys").addClass("menu_active").siblings().removeClass("menu_active").addClass("menu_sys");
			$(".menu_main_op").removeClass("menu_main_h").addClass("menu_main");
		   	if('地图管理'=='${menu.name}'){
		   	//岩涛 ADD 屏蔽左侧菜单区域 ， 并修改GIS的最外围框架为全屏，并重定向GIS请求地址
		   	  $("#leftMenuDiv").css("display","none");
		   	  $("#content").removeClass("span10").addClass("row-fluid");
			  $("#content-frame").attr("src", "${root}/map/home/${menu.id}/");
		   	}
			else{
			  $("#leftMenuDiv").css("display","block");
			  $("#content").removeClass("row-fluid").addClass("span10");
			  showLeafMenu('${menu.content}','${menu.name}');
			}
			$(".menu_main_op").removeClass("menu_main_h").addClass("menu_main");
			$("#mainInfo").hide(500);
			$("#contentInfo").show(500);
		 }
	  
		//主菜单和标签栏切换
		$(".menu_main_op").click(function(){
			navbar.push(this.id);
		 	if(navbar.length > 2){
		 		navbar.splice(0,1);
		 	}
		    $("#"+navbar[1]).removeClass("menu_sys").addClass("menu_active").siblings().removeClass("menu_active").addClass("menu_sys");
			    $(".menu_main_op").removeClass("menu_main").addClass("menu_main_h");
		});
		
		//主菜单标签栏关闭
		$(".close_span").click(function(){
		  $("#mainInfo").show(1000);
		  $("#contentInfo").hide(1000);
		  $(".menu_main_op").removeClass("menu_sys").removeClass("menu_main").addClass("menu_main_h");
		  $("#"+navbar[1]).remove();
 		  navbar.splice(1,1);
		  return false;
		});
		
		//标签栏之间的切换
		$("#${menu.picName}").click(function(){
			navbar.push(this.id);
		 	if(navbar.length > 2){
		 		navbar.splice(0,1);
		 	}
		 	$("#"+navbar[1]).removeClass("menu_sys").addClass("menu_active").siblings().removeClass("menu_active").addClass("menu_sys");
			    $(".menu_main_op").removeClass("menu_main_h menu_sys").addClass("menu_main");
				
			if('${menu.picName}'=='dituguanli'){
			  $("#leftMenuDiv").css("display","none");
			  $("#content").removeClass("span10").addClass("row-fluid");
			  $("#content-frame").attr("src", "${root}/map/home/${menu.id}/");
			}
			else if('${menu.picName}'=='kaiyuanditu'){
			  $("#leftMenuDiv").css("display","none");
			  $("#content").removeClass("span10").addClass("row-fluid");
			  $("#content-frame").attr("src", "${root}/openmap/home/${menu.id}/");
			}
			else if('${menu.picName}'=='pgisxitong'){
			  $("#leftMenuDiv").css("display","none");
			  $("#content").removeClass("span10").addClass("row-fluid");
			  $("#content-frame").attr("src", "${root}/pgis/home/${menu.id}/");
			}
			else{
			  $("#leftMenuDiv").css("display","block");
			  $("#content").removeClass("row-fluid").addClass("span10");
			  $("#content-frame").attr("src", "");
			}
		  
		});
	});
	
	var temp = new Array();
	// 点击功能菜单左侧应用（add by kouyunhao 2014-01-02 添加点击当前菜单，其他打开菜单关闭效果）
	$("#img_${menu.id}").click(function(){
		if($("#submenu_frame_${menu.id}").html().length != 0){
			$("#submenu_frame_${menu.id}").html("");
		}else{
			var id = '${menu.id}';
			var name = '${menu.name}';
			if(temp[0] != undefined){
				$("#submenu_frame_"+temp[0]).html("");
				temp.splice(0,1);
			}
			temp.push(id);
			$.getJSON("${root}/desk/deskinfo/get_left_2menu/" + id + "/" + name,
				function(data) {
					$("#submenu_frame_${menu.id}").html(data.content);
				});
		}
	});
	
	var bg = new Array();
	//鼠标滑过时改变菜单树背景颜色
	function change_bgcolor(id){
		if(bgcol[0] != bg[0]){
			if(bg[0] != undefined){
				$("#"+bg[0]).parent().attr("style","border-bottom: 1px solid #B2B2B2;line-height: 28px;");
				$("#"+bg[0]).attr("style","color:blue;");
				bg.splice(0,1);
			}
			bg.push(id);
			$("#"+bg[0]).parent().attr("style","background:#7BD4FA;border-bottom: 1px solid #B2B2B2;line-height: 28px;");
			$("#"+bg[0]).attr("style","color:white;");
		}else{
			bg.splice(0,1);
			bg.push('');
		}
	}
	
	var subid;
	var subname;
	var content;
	//var picname;
	var bgcol = new Array();
	function changebg_onclick(id){
		if(bgcol[0] != undefined){
			$("#"+bgcol[0]).parent().attr("style","border-bottom: 1px solid #B2B2B2;line-height: 28px;");
			$("#"+bgcol[0]).attr("style","color:blue;");
			bgcol.splice(0,1);
		}
		bgcol.push(id);
		$("#"+bgcol[0]).parent().attr("style","background:#7BD4FA;border-bottom: 1px solid #B2B2B2;line-height: 28px;");
		$("#"+bgcol[0]).attr("style","color:white;");
	}
	
	// 将图标添加到桌面上（点击功能菜单左侧二级菜单）
	function add_to_desk(menuid, name){
		changebg_onclick(menuid);
		if(scrno == '1'){
			scrno++;
			getResList(scrno);	
		}
		var subid;
		var subname;
		var content;
		var resid;
		var picname;
		// 从后台获取三级菜单字符串并解析，点击左侧二级菜单将图标显示在右侧区域
		jQuery.ajax({
				type : "GET",  
				cache: false,
				url: "${root}/desk/deskinfo/get_right_shortcut/" + menuid,
				success:function(data) {
					if(data != "null"){
						subid = new Array();
						subname = new Array();
						content = new Array();
						picname = new Array();
						var contentArr = data.split(",");
						for(var i = 0; i < contentArr.length; i++){
							if(i % 4 == 0){
								subid[i/4] = contentArr[i];
							}
							if(i % 4 == 1){
								subname[(i-1)/4] = contentArr[i];
							}
							if(i % 4 == 2){
								content[(i-2)/4] = contentArr[i];
							}
							if(i % 4 == 3){
								picname[(i-3)/4] = contentArr[i];
							}
						}
						jQuery.ajax({
							type : "GET",  
							cache: false,
							url: "${root}/desk/deskinfo/res_list/",
							success:function(data) {
								var div = '<div id="shortcut_div">';
								if(data != "null"){
									resid = data.split(",");
									for(var j = 0; j < subid.length; j++){
										var isEq = false;
										for(var m = 0; m < resid.length; m++){
											if(resid[m] == subid[j]){
												isEq = true;
											}
										}
										if(isEq){
											div += '<div id="picc_'+subid[j]+'" style="display:none;float:left;width:76px; margin:0 5px;text-align:center;" href="#" ><div>'+
											'<img style="width:50px;height:50px;"  src="../../images/picone/mainmenu/threelevel/'+picname[j]+'.png"/></div><a class="nav_name_small" title="'+subname[j]+'">'+subname[j]+'</a></div>';
										}else{
											div += '<div id="picc_'+subid[j]+'" style="display:block;float:left;width:76px; margin:0 5px;text-align:center;" href="#" ><div>'+
											'<img style="width:50px;height:50px;"  src="../../images/picone/mainmenu/threelevel/'+picname[j]+'.png"/></div><a class="nav_name_small" title="'+subname[j]+'">'+subname[j]+'</a></div>';
										}
									}
								}else{
									for(var j = 0; j < subid.length; j++){
										div += '<div id="picc_'+subid[j]+'" style="display:block;float:left;width:76px; margin:0 5px;text-align:center;" href="#" ><div>'+
										'<img style="width:50px;height:50px;"  src="../../images/picone/mainmenu/threelevel/'+picname[j]+'.png"/></div><a class="nav_name_small" title="'+subname[j]+'">'+subname[j]+'</a></div>';
									}
								}
								div += '</div>';
								$(".right_con").html("").html(div);
								for(var k = 0; k < subname.length; k++){
									toDesk(subid[k], subname[k], name, content[k], menuid, picname[k]);	
								}
							}
						});
					}
				}
			
		});
		fn1();
	}
	
	function toDesk(subid, subname, name, content, menuid, picname){
		var url = content+"/"+subid+"/";
		$("#picc_"+subid).click(function(){
		 	var mesg = true;
			jQuery.ajax({
				type : "GET",  
				async: false,
				url: "${root}/desk/deskinfo/get_resnum/" + scrno,
				success : function(data) {
					if(data > 20){
						if(!confirm("当前桌面应用21个，继续添加将会引起新增应用无法显示或使用，确定要继续吗？")){
							mesg = false;
						}
					}
				}
			});
			if(mesg){
				$("#List"+scrno).append('<div id="picc_'+subid+'" onmouseover="showDel(\''+scrno+'\',\''+subid+'\')" onmouseout="hideDel(\''+subid+'\')" style="float:left;width:100px; margin:0 20px;position:relative; text-align:center;" href="#"'+ 
					'onclick="onDeskClick(\''+subid+'\',\''+subname+'\',\''+name+'\',\''+url+'\',\''+menuid+'\')">'+ 
						'<span style="position:absolute;top:0; right:0;width:14px; height:14px;"><img id="del_'+subid+'" style="display:none;" src="${root}/images/picone/close.png"></span>'+
						'<div><img src="../../images/picone/mainmenu/threelevel/'+picname+'.png"/></div><p class="nav_name_p"><a class="nav_name" title='+subname+'>'+subname+'</a></p></div>');
				add_menu(scrno, subid);
				$(this).attr("style","display:none");
			}
			
			//add_menu(scrno, subid);
			//$(this).attr("style","display:none");
		});
	}
	
	function showSysIndex(url){
		$("#content-frame").attr("src", "${root}/"+url);
	}
	
	function onDeskClick(id, name,pname,url, pid) {
		jQuery.ajax({
			type : "GET",  
			cache: false,
			url: "${root}/desk/deskinfo/get_grandfather_node/" + pid + "/" + name + "/1",
			success:function(data) {
				var info = data.split(",");
				var p_content = info[0];
				var p_name = info[1];
				var p_picname = info[2];
				var tabName = $("#mainnavbar").text();
				if(tabName.indexOf(name)>=0){
					navbar.push('extra_'+id);
				 	if(navbar.length > 2){
				 		navbar.splice(0,1);
				 	}
				 	$("#"+navbar[1]).removeClass("menu_sys").addClass("menu_active").siblings().removeClass("menu_active").addClass("menu_sys");
					$(".menu_main_op").removeClass("menu_main_h").addClass("menu_main");
					showLeafMenu(p_content,p_name);
				  	$("#mainInfo").hide(1000);
				  	$("#contentInfo").show(1000);
					
				 }else{
				   	navbar.push('extra_'+id);
				   	if(navbar.length > 2){
				 		navbar.splice(0,1);
				 	}
				   	$("#mainnavbar").append('<div id="extra_'+id+'" class="menu_active menu_op"><a href="#" onclick="showMenu(\''+id+'\',\''+name+'\',\''+pname+'\',\''+url+'\',\''+pid+'\',\''+p_content+'\',\''+p_name+'\')">'+name+'</a><span onclick="close_bt()" class="close_span"></span></div>');
			 		$("#"+navbar[1]).removeClass("menu_sys").addClass("menu_active").siblings().removeClass("menu_active").addClass("menu_sys");
	  				$(".menu_main_op").removeClass("menu_main_h menu_sys").addClass("menu_main");
					showLeafMenu(p_content,p_name);
					$("#mainInfo").hide(1000);
					$("#contentInfo").show(1000);
				 }
			}
		});
		setTimeout('menuOnclick(\''+id+'\',\''+name+'\',\''+pname+'\',\''+url+'\',\''+pid+'\')', 200);
		
		//主菜单和标签栏切换
		$("#main-interface").click(function(){
			if(navbar[1] != this.id){
				navbar.push(this.id);
			 	if(navbar.length > 2){
			 		navbar.splice(0,1);
			 	}
			    $("#"+navbar[1]).removeClass("menu_sys").addClass("menu_active").siblings().removeClass("menu_active").addClass("menu_sys");
				$(".menu_main_op").removeClass("menu_main").addClass("menu_main_h");
			}
		});
	}
	
	function close_bt(){
		  $("#mainInfo").show(1000);
		  $("#contentInfo").hide(1000);
		  $(".menu_main_op").removeClass("menu_sys").removeClass("menu_main").addClass("menu_main_h");
		  $("#"+navbar[1]).remove();
 		  navbar.splice(1,1);
	}
	
	function showMenu(id,name,pname,url,pid,p_content,p_name){
		navbar.push('extra_'+id);
	   	if(navbar.length > 2){
	 		navbar.splice(0,1);
	 	}
		$("#"+navbar[1]).removeClass("menu_sys").addClass("menu_active").siblings().removeClass("menu_active").addClass("menu_sys");
		$(".menu_main_op").removeClass("menu_main_h").addClass("menu_main");
	  	showLeafMenu(p_content,p_name);
	  	setTimeout('menuOnclick(\''+id+'\',\''+name+'\',\''+pname+'\',\''+url+'\',\''+pid+'\')', 200);
	}	
	
	function fn1(){
		var resourceid = $("menu.id");
		if(scrno == 1){
			$("#List1").hide(); 
			$("#List2").show();
			$("#pic2").children("img").attr({src:"../../images/picone/cur_dot.png"});
	    	scrno++;
			for(var j=0; j<idArr.length; j++){
		    	if(idArr[j] != 2){
		    		$("#pic"+idArr[j]+"").children("img").attr({src:"../../images/picone/other_dot.png"});
		    	}
		    	if(scrno == idArr[j]){
		    		if(j == idArr.length-1){
		    			$(".RightBotton").children("img").attr({src:"../../images/picone/right_arr_gray.png"});
		    		}else{
		    			$(".RightBotton").children("img").attr({src:"../../images/picone/right_arr.png"});
		    		}
		    		if(j == 0){
		    			$(".LeftBotton").children("img").attr({src:"../../images/picone/left_arr_gray.png"});
		    		}else{
		    			$(".LeftBotton").children("img").attr({src:"../../images/picone/left_arr.png"});
		    		}
	    		}
	    	}
		}else{
			$("#pic" + scrno).children("img").attr({src:"../../images/picone/cur_dot.png"});
			for(var j=0; j<idArr.length; j++){
		    	if(idArr[j] != scrno){
		    		$("#pic"+idArr[j]+"").children("img").attr({src:"../../images/picone/other_dot.png"});
		    	}
		    	if(scrno == idArr[j]){
		    		if(j == idArr.length-1){
		    			$(".RightBotton").children("img").attr({src:"../../images/picone/right_arr_gray.png"});
		    		}else{
		    			$(".RightBotton").children("img").attr({src:"../../images/picone/right_arr.png"});
		    		}
		    		if(j == 0){
		    			$(".LeftBotton").children("img").attr({src:"../../images/picone/left_arr_gray.png"});
		    		}else{
		    			$(".LeftBotton").children("img").attr({src:"../../images/picone/left_arr.png"});
		    		}
	    		}
	    	}
		}
	}
</script>
</c:forEach>
<!-- 打开主界面页面栏目 ，所点击菜单名称添加到主栏目标签中结束 --> 

<!-- 翻页功能开始 -->
<script type="text/javascript">
	function add_menu(initNum, resourceid){
		$.getJSON("${root}/desk/deskinfo/add_menu/" + initNum +"/"+ resourceid,
			function(data) {
				$("#datainfo").show();
				if(data.result=="ok"){
	   				 $("#datainfo").html("").html('<font style="color: green;font-size: large;">应用已添加到当前桌面！</font>');
	   			}else{
	   				 $("#datainfo").html("").html('<font style="color: green;font-size: large;">应用添加失败！</font>');
	   			}
	   			setTimeout('$("#datainfo").hide()', 1200);
			});
	}
	
	function del_menu(initNum, resourceid){
		$.getJSON("${root}/desk/deskinfo/del_menu/" + initNum +"/"+ resourceid,
			function(data) {
				alert(data);
			});
	}
	
	// 获取某个桌面的快捷方式
	function getResList(scrno){
		//add by kouyunhao 2014-06-26 自定义桌面标号为99的是“第二套主题”的标号，此处必须加以区分。
		if(scrno != "1" && scrno != "99"){
		jQuery.ajax({
			type : "GET",  
			cache: false,
			url: "${root}/desk/deskinfo/get_list/" + scrno,
			success:function(data) {
	    		$("#List"+scrno).show();
				$("#List"+scrno).html("");
				$("#pic"+scrno).children("img").attr({src:"../../images/picone/cur_dot.png"});	
				for(var j=0; j<idArr.length; j++){
			    	if(scrno != idArr[j]){
			    		$("#List"+idArr[j]).hide();
			    		$("#pic"+idArr[j]).children("img").attr({src:"../../images/picone/other_dot.png"});
			    	}
		    	}    
				$("#List"+scrno).html('<div id="List'+scrno+'" style="display: block;">第'+scrno+'个桌面</div>');
				if(data != "null"){
					var resArr = data.split(",");
					$("#List"+scrno).html("");
					for(var i = 0; i < resArr.length; i++){
							var res = resArr[i].split("_")
							var resid = res[0];
							var resname = res[1];
							var name = res[2];
							var url = res[3];
							var menuid = res[4];
							var picname = res[5];
							$("#List"+scrno).append('<div id="picc_'+resid+'" onmouseover="showDel(\''+scrno+'\',\''+resid+'\')" onmouseout="hideDel(\''+resid+'\')" style="float:left;width:100px; margin:5px 20px;padding:5px 0;position:relative; text-align:center;" href="#"'+ 
								'onclick="showDel(\''+resid+'\');onDeskClick(\''+resid+'\',\''+resname+'\',\''+name+'\',\''+url+'\',\''+menuid+'\');">'+ 
									'<span style="position:absolute;top:0; right:0;width:14px; height:14px;"><img id="del_'+resid+'" style="display:none;" src="${root}/images/picone/close.png"></span>'+
									'<div><img src="../../images/picone/mainmenu/threelevel/'+picname+'.png" style="width:80px;height:80px;"/></div><p class="nav_name_p"><a class="nav_name" title='+resname+'>'+resname+'</a></p></div>');
					}
				}
			}
		});
		}else{
			$("#List"+scrno).show();
			$("#pic"+scrno).children("img").attr({src:"../../images/picone/cur_dot.png"});	
			for(var j=0; j<idArr.length; j++){
		    	if(scrno != idArr[j]){
		    		$("#List"+idArr[j]).hide();
		    		$("#pic"+idArr[j]).children("img").attr({src:"../../images/picone/other_dot.png"});
		    	}
	    	}    
		}
	}
	function showDel(initNum, resid){
		$("#picc_"+resid).addClass("bgcolor_add");
		$("#del_"+resid).css("display","block");
		$("#del_"+resid).click(function(){
			del_menu(initNum, resid);
			$("#picc_"+resid).css("display","none");
			return false;
		});
		return true;
	}
	
	function hideDel(resid){
		$("#picc_"+resid).removeClass("bgcolor_add");
		$("#del_"+resid).css("display","none");
	}
	
	// 初始化桌面
	function initDeskList(){
		jQuery.ajax({
			type : "GET",  
			cache: false,
			url: "${root}/desk/deskinfo/desk_list/",
			success:function(data) {
				if(data != "null"){
					var scrArr = data.split(",");
					idArr.splice(2,idArr.length-2);
					for(var i = 0; i < scrArr.length; i++){
						//alert(scrArr[i]);
						//add by kouyunhao 2014-06-26 自定义桌面标号为99的是“第二套主题”的标号，此处必须加以区分。
						if(scrArr[i] != '2' && scrArr[i] != '99'){
							$("#scr_list").append('<div id="List'+scrArr[i]+'" style="display: none">第'+scrArr[i]+'个桌面</div>');
							// 添加桌面
							var scrId = 'scr'+(scrArr[i]);
							$("#" + scrId).css("display", "");
							// 添加底部小图标
							var picId = 'pic'+(scrArr[i]);
							$("#" + picId).css("display", "");
							// 更改底部显示宽度
							var changeWidth = 130+(scrArr.length)*40+'px';
							$(".options").css("width",changeWidth);
							idArr.push(scrArr[i]);
						}
					}
				}
			}
		});
	}
</script>


<!-- 翻页功能结束 --> 
 
<!-- 分屏功能开始 --> 
<script type="text/javascript">
$(document).ready(function(){
    initDeskList();
	$(".LeftBotton").children("img").attr({src:"../../images/picone/left_arr_gray.png"});
	// 点击添加
   	$(".add").click(function(){
   		initNum = idArr[idArr.length-1];
   		//alert("beforeadd: " + initNum);
   		initNum ++;
   		if(initNum < 10){
   			add(initNum);
	   		idArr.push(initNum);
	   		//alert("add: " + initNum);
	   		// 添加桌面
	   		var scrId = 'scr'+(initNum);
			if($("#"+scrId).css("display") != null){
				$("#"+scrId).css("display","");
			}else{
				$("#"+scrId).attr("display","");
			}
			
			// 添加底部小图标
			var picId = 'pic'+(initNum);
			$("#"+picId).css("display","block");
			
			// 更改底部显示宽度
			var changeWidth = 130+(initNum-1)*40+'px';
			$(".options").css("width",changeWidth);
		  	if($("#List"+initNum).html() == null){
	    		$("#scr_list").append('<div id="List'+initNum+'" style="display: none">第'+initNum+'个桌面</div>');
	    	}
			changePic(initNum, idArr);
			init();
   		}else{
   			alert("用户自定义桌面个数已达上限");
   		}
		
	});
	
	// 桌面后台保存
	function add(initNum){
		$.getJSON("${root}/desk/deskinfo/add_desk/" + initNum,
			function(data) {
				$("#datainfo").show();
				if(data.result=="ok"){
	   				 $("#datainfo").html("").html('<font style="color: green;font-size: large;">桌面添加成功！</font>');
	   			}else{
	   				 $("#datainfo").html("").html('<font style="color: green;font-size: large;">桌面添加失败！</font>');
	   			}
	   			setTimeout('$("#datainfo").hide()', 1200);
			});
	}
	
	//桌面后台删除
	function del(initNum){
  		var url="${root}/desk/deskinfo/del_desk/" + initNum;
		$.ajax({
			type : 'DELETE',
			url : url,
			dataType : "json",
			success:function(msg){
				$("#datainfo").show();
				if(msg.result=="ok"){
	   				 $("#datainfo").html("").html('<font style="color: green;font-size: large;">桌面删除成功！</font>');
	   			}else{
	   				 $("#datainfo").html("").html('<font style="color: green;font-size: large;">桌面删除失败！</font>');
	   			}
	   			setTimeout('$("#datainfo").hide()', 1200);
			}
		});
	}
	
	// 点击删除
   	$(".del").click(function(){
   		var id = $(this).attr("id");
   		var delId = id.substring(id.indexOf('r')+1,id.length);
  		if(delId == 1 || delId == 2){
  			window.alert("系统默认桌面不允许删除！");
  		}else{
  			if(window.confirm("删除桌面将删除桌面上所有模块，您确定要删除吗？")){
				del(delId);
				initNum --;
		   		$("#scr"+idArr[idArr.length-1]).css("display","none");
		   		$("#pic"+idArr[idArr.length-1]).css("display","none");
				idArr.splice(idArr.length-1,1);
				$("#List"+delId+"").hide();
		   		$("#List1").show();
		   		$("#pic1").children("img").attr({src:"../../images/picone/cur_dot.png"});
				for(var j=1; j<idArr.length; j++){
		   			$("#List"+idArr[j]).hide();
    				$("#pic"+idArr[j]).children("img").attr({src:"../../images/picone/other_dot.png"});
		    	}
		   		// 更改底部显示宽度
		   		var width = parseInt($(".options").css("width"));
				var changeWidth = (width-1*40)+'px';
				$(".options").css("width",changeWidth);
				scrno = 1;
		  		init();
		    }
  		}
   	});	
   	
   	// 点击底部小图片
	$("#smalPic").children("a").click(function(){
		scrno++;
		var curr_pic_id = $(this).attr("id");
		var x = curr_pic_id.substring(curr_pic_id.indexOf('c')+1,curr_pic_id.length);
		changePic(x, idArr);
		getResList(scrno);
		init();
	});
	
	// 共用方法，改变底部小图片的显示
	function changePic(num, idArr){
    	$("#List"+num).show();
    	$("#pic"+num).children("img").attr({src:"../../images/picone/cur_dot.png"});
    	scrno = num;
    	for(var j=0; j<idArr.length; j++){
	    	if(num != idArr[j]){
	    		$("#List"+idArr[j]+"").hide();
	    		$("#pic"+idArr[j]+"").children("img").attr({src:"../../images/picone/other_dot.png"});
	    	}
    	}
	}
	
	//*******************左右切屏功能开始*************************
	$(".RightBotton").click(function(){
		if($(".RightBotton").children("img").attr("src")=="../../images/picone/right_arr_gray.png"){
			return;	
    	}
    	scrno++;
    	getResList(scrno);
    	init();
	});
	
	$(".LeftBotton").click(function(){
    	if($(".LeftBotton").children("img").attr("src")=="../../images/picone/left_arr_gray.png"){
			return;	
    	}
    	scrno--;
    	getResList(scrno);
    	init();
	});
	
	// 图片切换
	function init(){
		for(var j=0; j<idArr.length; j++){
	    	if(scrno == idArr[j]){
	    		if(j == idArr.length-1){
	    			$(".RightBotton").children("img").attr({src:"../../images/picone/right_arr_gray.png"});
	    		}else{
	    			$(".RightBotton").children("img").attr({src:"../../images/picone/right_arr.png"});
	    		}
	    		if(j == 0){
	    			$(".LeftBotton").children("img").attr({src:"../../images/picone/left_arr_gray.png"});
	    		}else{
	    			$(".LeftBotton").children("img").attr({src:"../../images/picone/left_arr.png"});
	    		}
	    	}
    	}
	}
	
	//*******************左右切屏功能结束*************************
   });
   
</script> 
<!-- 分屏功能结束 --> 
 
<!-- 主界面菜单页功能操作按钮弹出页面 --> 
<script type="text/javascript">
$(document).ready(function(){
//alert("111");
  $(".close-btn").click(function(){
    $(".dropmenu").css("display","none");
    return false;
  });
  $("#dropmenu_btn").click(function(){
    var h = $(document).height();
	$('#screen').css({ 'height': h });	
	$('#screen').show();
	$('.dropmenu').center();
	$('.dropmenu').show();
	return false;
  });
});

jQuery.fn.center = function(loaded) {
	var obj = this;
	body_width = parseInt($(window).width());
	body_height = parseInt($(window).height());
	block_width = parseInt(obj.width());
	block_height = parseInt(obj.height());
	
	left_position = parseInt((body_width/2) - (block_width/2)  + $(window).scrollLeft());
	if (body_width<block_width) { left_position = 0 + $(window).scrollLeft(); };
	
	top_position = parseInt((body_height/2) - (block_height/2) + $(window).scrollTop());
	if (body_height<block_height) { top_position = 0 + $(window).scrollTop(); };
	
	if(!loaded) {
		
		obj.css({'position': 'absolute'});
		obj.css({ 'top': top_position, 'left': left_position });
		$(window).bind('resize', function() { 
			obj.center(!loaded);
		});
		$(window).bind('scroll', function() { 
			obj.center(!loaded);
		});
		
	} else {
		obj.stop();
		obj.css({'position': 'absolute'});
		obj.animate({ 'top': top_position }, 200, 'linear');
	}
}
</script> 
<!-- 主界面菜单页功能操作按钮弹出页面结束 --> 
 
<script type="text/javascript">
	var secname;
	var thirdName;
	
	function showMain(url, name){
	    $("#mainInfo").show(1000);
        $("#contentInfo").hide(1000);
	}
	
	function showLeafMenu(url, name) {
		$.ajax({
		   type: "get",
		   url:"${root}/" + url,
		   dataType:'json',
		   success: function(data){
		   		if(data.code=='502'){
		   			alert("登陆超时，请重新登陆 ");
		   		}else{
			    	$("#leftMenu").html("").html(data.content);
					$("#content-frame").attr("src","");
					if(data.url){
						$("#content-frame").attr("src","${root}/"+data.url);
					}
					$("#sec-nav").html(name);
					secname = name;
					$(".accordion-toggle").bind("click",function() {
						thirdName = $(this).text();
						$("#nav-position").html(secname+ '<span class="divider"> >> </span><li><span>'+ $(this).text()+ '</span>');
					});
		   		}
		   },
           error: function (result, b){
              top.location.href = "${root}/login";
           }
		}); 
	    $("#mainInfo").hide(1000);
        $("#contentInfo").show(1000);
	}
	
	var point = new Array();
	point.push("");
	function menuOnclick(id, name,pname,url, pid) {
		//alert(id + "," + name + "," + pname + "," + url + "," + pid);
		point.push(id); 
		if(point.length > 2)
		point.splice(0,1);
		var cur = secname;
		$(".bs-docs-sidenav li").each(function() {
			$(this).removeClass("active");
		});
		//鼠标单击改变当前选中子节点背景颜色
		$(".colums_two li").each(function() {
			if(point[0] != ''){
				$("#"+point[0]).removeAttr("style");
			}
		});
		
		$("#li_" + id).addClass("active");
		var nav = '<span class="divider">>></span><li id="'+id+'" onmouseover="changeBC('+id+')"><a href="javascript:void(0);menuOnclick('	+ id + ',' + name + ',' + url + ')">' + name + '</a></li>';
		$("#nav-position").html(cur + '<span class="divider">>></span>' + pname + nav);
		//岩涛 ADD 2014.11.11
		var urltemp = url.substring(0,4);
		if(urltemp == "http"){
		  $("#content-frame").attr("src", url);
		}else{
		  $("#content-frame").attr("src", "${root}/" + url);
		}
	}
	
	var point1 = new Array();
	//鼠标滑过时改变菜单树背景颜色
	function changeBC(id){
		point1.push(id);
		if(point1.length > 2)
		point1.splice(0,1);
		$(".colums_two li").each(function() {
			$("#"+point1[point1.length-1]).attr("style", "background:url('../../images/picone/two_li_bg.png') repeat-x 0 0 transparent;");
			if(point1.length == 2){
				if(point[1] != point1[0]){
					$("#"+point1[0]).removeAttr("style");
				}
			}
		});
	}

	function showChangePasswdDialog() {
		$("#changePasswdForm").validate();
		$('#passwd-change').modal('show');
	}

	function changePasswd() {
		var old = $("#old-passwd").val();
		var newPasswd = $("#user-passwd").val();
		$.getJSON("${root}/system/user/changepasswd/" + old + "/" + newPasswd,
				function(data){
					if (data.result == 'ok') {
						alert("修改成功");
						$("#old-passwd").val("");
						$("#user-passwd").val("");
						$("#repeat-passwd").val("");
						$('#passwd-change').modal('hide');
					}

					if (data.result == '502') {
						alert(data.message);
						top.location.href = "${root}/login";
					}

					if (data.result == 'error') {
						alert(data.message);
					}
				});
	}
	$("#progress-bar").floatdiv({top:"40px",right:"200px"});
	
</script> 
<script type="text/javascript">

</script>

<script type="text/javascript">
	/*
	$("#blue").click(function(){
		$("#mainInfo").removeAttr("style").attr("style","background:url(${root}/images/picone/menu_body_bg_blue.jpg) no-repeat center bottom;");
	});
	$("#green").click(function(){
		$("#mainInfo").removeAttr("style").attr("style","background:url(${root}/images/picone/menu_body_bg_green.jpg) no-repeat center top;");
	});
	*/
	
	/*
	$("#gold").click(function(){
		$("#mainInfo").removeAttr("style").attr("style","background:url(${root}/images/picone/menu_body_bg_gold.jpg) no-repeat center bottom;");
	});
	*/
	
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
	
	var crossMsgHandler = {
		 crossfinishComplete:function(message){
			var str;
			if (message.text == undefined) {
				str = message.textContent;
			} else {
				str = message.text;
			}
			var tds = eval("(" + str + ")");
			var msg = tds.msg;
		    var progressCount = parseFloat(msg.split(",")[1]);
		    var totals = parseInt(msg.split(",")[0]);
		    var result = parseFloat(progressCount / totals) * 100;
			var baf = result.toFixed(2);
			$("#progress-bar").html(baf + "%");
		},
		
		completeDownload:function(){
			$("#progress-bar").html("100%");
			org.activemq.Amq.removeListener("crossProcess","topic://download_finished_${sessionId}");
			org.activemq.Amq.removeListener("crossComplete","topic://complete_${sessionId}");
			setTimeout('hideProgressBar()',1000);
		}
	
	};
	
	function exportMessageInit(){
		var amq = org.activemq.Amq;
		amq.init({
			uri : '${root}/amq',
			logging : true,
			timeout : 35,
			clientId : (new Date()).getTime().toString()
		});
		var finishedSubject = "topic://download_finished_${sessionId}";
		var completeSubject = "topic://complete_${sessionId}";
		amq.addListener("crossProcess", finishedSubject,crossMsgHandler.crossfinishComplete);
		amq.addListener("crossComplete", completeSubject,crossMsgHandler.completeDownload);
	}
	
	
	
	function hideProgressBar(){
		$("#progress-bar").html("").hide();
	}
	
	
	//光标事件
	window.onfocus=function (){
	
		if(typeof(window.childWindow)!='undefined')
		{
			window.childWindow.focus();
		}
		
	}
	
</script>
<!-- <script type="text/javascript" src="${root}/js/move-div.js"></script> -->