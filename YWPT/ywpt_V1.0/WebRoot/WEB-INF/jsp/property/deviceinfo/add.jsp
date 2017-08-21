<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<link id="MainCss" href='${root}/css/transport.css' rel='stylesheet'>
<link id="PicCss" href='${root}/css/picmix.css' rel='stylesheet'>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" 	type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<%
Date date = new Date();
Calendar calendar = Calendar.getInstance();
calendar.setTime(date);
calendar.add(Calendar.YEAR, 1);
Date ydate = calendar.getTime();
String code = new SimpleDateFormat("yyMMddHHmmss").format(date)+new Random().nextInt(10000);
%>
<script type="text/javascript">
	var isMapTabClick = false;
	var isTabMap = true;
	function mapTabShow() {
		if(isMapTabClick)return;
		isMapTabClick = true;
		var obj = window.frames["result_form"];
		if(obj && typeof obj.showmap === "function") {
			obj.showmap();
		}
	}
	var map;
	function iframeMapLoadCallback(mapAPI) {
		map = mapAPI;
		map.setMapXY("mapx", "mapy");// 默认为mapx  mapy
		//map.setPointImg("images/lukoutupian.png");
		map.drawPoint();
	}
</script>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none;">
  <p id="alert-content" align="center"></p>
</div>
<script  type="text/javascript">
//切换皮肤方法
var d=window.parent.parent.document.getElementById("skin-sel");
if(d!=null){
	var value= d.options[d.selectedIndex].value;
	if(value=="1"){
		$("#MainCss").attr("href","${root}/css/transport.css");
		$("#PicCss").attr("href","${root}/css/picmix.css");
	}else if(value=="2"){
		$("#MainCss").attr("href","${root}/cssGreen/transport.css");
		$("#PicCss").attr("href","${root}/cssGreen/picmix.css");
	}else if(value=="3"){
		$("#MainCss").attr("href","${root}/cssDarkBlue/transport.css");
		$("#PicCss").attr("href","${root}/cssDarkBlue/picmix.css");
	}else{
	
	}
	//d.addEventListener("change",ssss,false);
	//监听主页皮肤切换
	$(d).bind("change", function(){
		var  d1=window.parent.parent.document.getElementById("skin-sel");
		var value1= d1.options[d1.selectedIndex].value;
		if(value1=="1"){
			$("#MainCss").attr("href","${root}/css/transport.css");
			$("#PicCss").attr("href","${root}/css/picmix.css");
		}else if(value1=="2"){
			$("#MainCss").attr("href","${root}/cssGreen/transport.css");
			$("#PicCss").attr("href","${root}/cssGreen/picmix.css");
		}else if(value1=="2"){
			$("#MainCss").attr("href","${root}/cssDarkBlue/transport.css");
			$("#PicCss").attr("href","${root}/cssDarkBlue/picmix.css");
		}else{
		}
	});
}
</script>

<SCRIPT type="text/javascript">
	var orgTree, deviceTypeTree;
	// 设备功能 类型树
	var deviceTypeSetting = {
		check: {
			enable: true,
			chkboxType: {"Y":"", "N":""}
		},
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClick,
			onCheck: onCheck
		}
	};


       // 设备功能 树的相关操作函数
	function beforeClick(treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("dtTreeSpace");
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	
	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("dtTreeSpace"),
		nodes = zTree.getCheckedNodes(true),
		v = "";
		var h = "";
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
			h += nodes[i].id + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		var deviceTypeNames = $("#deviceTypeNames");
		deviceTypeNames.attr("value", v);
		
		if (h.length > 0 ) h = h.substring(0, h.length-1);
		var deviceTypeIds = $("#deviceTypeIds");
		deviceTypeIds.attr("value", h);
	}
		
		
	// 所属部门 组织机构树
	var orgSetting = {
		check: {
			  enable: true,
			  chkStyle: "radio",
			  radioType: "all"
		},
		view: {
			  dblClickExpand: false
		},
		data: {
			  simpleData: {
				 enable: true
			  }
		},
		callback: {
			  onClick: onRadioClick,
			  onCheck: onRadioCheck
		}
	};
		
	
		
   //--------组织机构树相关函数------------
   
   //当点击节点文字时候，同步选中单选框
	function onRadioClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("orgTreeSpace");
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	
	//当选中单选框时 触发该函数
	function onRadioCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("orgTreeSpace"),
		nodes = zTree.getCheckedNodes(true),
		v = "";
		var h = "";
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
			h += nodes[i].id + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		var cityObj = $("#orgNames");
		cityObj.attr("value", v);
		
		if (h.length > 0 ) h = h.substring(0, h.length-1);
		var hiddenValue = $("#orgId");
		hiddenValue.attr("value", h);
	}
	

	
	//设备所属部门 设备功能 弹出选择菜单
	function showMenu(selectId) {
		var cityObj = $("#"+selectId);
		var cityOffset = $("#"+selectId).offset();
		if(selectId == 'orgNames'){
		   $("#orgTreeContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		   //add by kouyunhao 2013-08-06 解决设备功能和所属部门同时显示问题。
		   $("#menuContent").fadeOut();
		}
		if(selectId == 'deviceTypeNames'){
		   $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		   //add by kouyunhao 2013-08-06 解决设备功能和所属部门同时显示问题。
		   $("#orgTreeContent").fadeOut();
		}
		$("body").bind("mousedown",onBodyDown);
	}
	function hideMenu() {
		$("#orgTreeContent").fadeOut();
		$("#menuContent").fadeOut();
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "deviceTypeNames"|| event.target.id == "orgNames" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0 || event.target.id == "orgTreeContent" || $(event.target).parents("#orgTreeContent").length>0)) {
			hideMenu();
		}
	}
		
		
	function checkDeviceCode(){
	    $("#loadingdiv").css("display","");
	    var code = $("#code").val();
		$.getJSON("${root}/property/deviceinfo/codeExist/" + code + "/",
				function(data) {
					$("#messagediv").html(data.content);
					$("#loadingdiv").css("display","none");
		});
	}
		
	// 设备功能 
	$(document).ready(function(){
	    //聚焦第一个输入框
		$("#ip").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: {
				"ip":{
					remote:{
						url:"${root}/property/deviceinfo/ipExist",
						type:"post",
						data:{
							ip:function(){
								return $("#ip").val();
							}
						}
					}
				},
				"name":{
					remote:{
						url:"${root}/property/deviceinfo/nameExist",
						type:"post",
						data:{
							name:function(){
								return $("#name").val();
							}
						}
					}
				}
			}
		});
	    
	    var dt ='${deviceTypejsonArray}';
	    var deviceTypeNodes = eval("(" + dt + ")");
		deviceTypeTree = $.fn.zTree.init($("#dtTreeSpace"),deviceTypeSetting,deviceTypeNodes);
		deviceTypeTree.setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };
		deviceTypeTree.expandAll(true);
		
		var dept ='${departmentjsonArray}';
	    var departmentNodes = eval("(" + dept + ")");
		orgTree = $.fn.zTree.init($("#orgTreeSpace"),orgSetting,departmentNodes);
		orgTree.expandAll(true);
		
	});
	
		
	
	function showFile(){
		if($("#fileInput1").css("display")=="block" && $("#fileInput2").css("display")=="block"){
			alert("现场照片最大可以传三张");
			return;
		}
		if($("#fileInput1").css("display") == "none"){
			$("#fileInput1").css("display","block");
		}else{
			$("#fileInput2").css("display","block");
		}
	}
	
	function checkForm(){
		var submit = true;
		var codeStart = $("#code").val();
		var name = $("#name").val();
		var timeout = $("#timeout").val();
		var factoryId = $("#factoryId").val();
		var ip = $("#ip").val();
		var orgId = $("#orgId").val();
		var deviceTypeNames = $("#deviceTypeNames").val();
		if(ip == ''){
			alert("[基本信息]-IP 地址不能为空");
			document.getElementById("ip").focus();
			submit = false;
		}else if(codeStart == ''){
			alert("[基本信息]-设备编号不能为空");
			document.getElementById("code").focus();
			submit = false;
		}else if(name == ''){
			alert("[基本信息]-设备名称不能为空");
			document.getElementById("name").focus();
			submit = false;
		}else if(factoryId == ''){
			alert("[基本信息]-服务厂商不能为空");
			document.getElementById("factoryId").focus();
			submit = false;
		}else if(timeout == ''){
			alert("[基本信息]-超时设置不能为空");
			document.getElementById("timeout").focus();
			submit = false;
		}else if(orgId == ''){
			alert("[基本信息]-所属部门不能为空");
			document.getElementById("orgId").focus();
			submit = false;
		}else if(deviceTypeNames == ''){
			alert("[基本信息]-设备功能不能为空");
			document.getElementById("deviceTypeNames").focus();
			submit = false;
		}
		if($("#toasset").val() == '1'){
	    	//资产状态
	    	var assetstatus =$("#assetstatus").val();
	    	//保修时间
	    	var guaranteetimestr =$("#guaranteetimestr").val();
	    	//采购时间
	    	var purchasetimestr =$("#purchasetimestr").val();
	    	//安装时间
	    	var installtimestr = $("#installtimestr").val();
	    	if(assetstatus==''){
    			alert("[资产配置]标签页-资产状态不能为空");
    			submit = false;
    		}else if(guaranteetimestr==''){
    			alert("[资产配置]标签页-保修时间不能为空");
    			submit = false;
    		}else if(purchasetimestr==''){
    			alert("[资产配置]标签页-采购时间不能为空");
    			submit = false;
    		}else if(installtimestr==''){
    			alert("[资产配置]标签页-安装时间不能为空");
    			submit = false;
    		}
	    }
		var namestyle = $("#name").attr("class");
    	var ipstyle = $("#ip").attr("class");
    	if(namestyle == 'required error'){
    		alert("[基本信息]标签页-设备名称不正确");
			submit = false;
    	}else if(ipstyle == 'required error'){
    		alert("[基本信息]标签页-IP 地址不正确");
			submit = false;
    	}
		return submit;
	}
	
	function ipTrue(){
		var ips = $("#ips").val();
		if(ips != ''){
			$("#ips").attr("ip","true");
		}else{
			$("#ips").attr("ip","");
		}
	}
		
</SCRIPT>
<style type="text/css">
#dropdown-ul li {
	line-height:26px;
	padding-left:10px;
	border-bottom:1px solid #f3f3f3;
	cursor:default;
	
}
#dropdown-ul li:last-child {
	border-bottom:0;
} 
</style>
<script type="text/javascript">
	$(function(){
		$("#dropdown-ul li").mouseover(function(){
			$(this).attr("style","background:#0080c0;color:#fff;");
		});
		$("#dropdown-ul li").mouseout(function(){
			$(this).attr("style","background:none;");
		});
	})
</script>
<div style="min-height:560px;">
  <div class="conten_box">
	<form id="inputForm" class="form-horizontal" action="${root}/property/deviceinfo/doAddImg" 
	enctype="multipart/form-data" method="post" style="margin:0;padding:0;">
		<h4 class="xtcs_h4">设备信息-添加
		</h4>
		<input type="hidden" name="menuid" value="${menuid}"/>
		<input type="hidden" name="page" value="${page}"/>
	    <!-- 选项卡 开始 -->	 
		<ul class="nav nav-tabs" style="padding-left:10px; margin:0;">
		    <li id="tab_li1" class="active"><a href="#tab1" data-toggle="tab">基本信息<strong style="color:red">*</strong></a></li>
		    <li><a href="#tab4" data-toggle="tab">建设信息</a></li>
		    <li><a href="#tab5" data-toggle="tab" onclick="mapTabShow()">地图位置</a></li>
		    <li style="display: none;"><a href="#tab7" data-toggle="tab">资产配置</a></li>
		</ul>
		<div class="tab-content" style="overflow:hidden;">
			<!-- 1111 -->
			<div class="tab-pane mar_5 active" style="min-height:430px;" id="tab1" >
			  <div class="pull-left" style="width:74%;">
			    <table class="table table-border-rl table-border-bot tingche-table">
			      <tr>
			        <td class="device_td_bg3">设备 IP：</td>
			        <td><input type="text" id="ip" placeholder="IP地址" name="ip" maxlength="15" style="width:200px;" ip="true" class="required">
			          <strong style="color:red">&nbsp;*</strong>
			        </td>
			      </tr>
			      
			      <tr>
			  	    <td class="device_td_bg3">设备编号：</td>
			  	    <td>
			            <input type="text" id="code" style="width:200px" name="code" placeholder="设备编号" maxlength="18" class="input-large required" onfocusout="checkDeviceCode()"/> <strong style="color:red">&nbsp;*</strong>
			            <span id="loadingdiv" style="display:none"><img style="width:25px; height:25px;" src="${root}/images/loading.gif" /></span>
			            <span id="messagediv"></span>
			  	    </td>
			  	  </tr>
			  	  
			  	  <tr>
			  	    <td class="device_td_bg3">设备名称：</td>
			  	    <td><input type="text" id="name"  maxlength="20" placeholder="设备名称" name="name" style="width:200px;" class="required" specialcharfilter="true"> <strong style="color:red">&nbsp;*</strong></td>
			  	  </tr>
			  	  
			  	  <tr>
	  			    <td class="device_td_bg3">服务厂商：</td>
	  			    <td>
	  			      <select style="width:210px;" id="factoryId" name="factoryId" onchange="showService()" placeholder="服务厂商" class="required" data-rel="chosen">
			            <option value="">请选择</option>
			            <c:forEach items="${factoryList}" var="factory">
			                <option value="${factory.id}" ${factory.id==deviceInfo.factoryId?'selected':''}>${factory.name}</option>
			            </c:forEach>
			          </select>
			          <strong style="color:red">&nbsp;*</strong>
	  			    </td>
	  			  </tr>
			  	  
			  	  <tr>
			        <td class="device_td_bg3">超时设置：</td>
			        <td>
			          <input type="text" id="timeout" placeholder="超时设置" name="timeout" class="required" maxlength="4" style="width:200px;" digits="true" >
			          <strong style="color:red">&nbsp;*</strong>
			          <button class="btn dropdown-toggle" data-toggle="dropdown">分钟</button>
			        </td>
			      </tr>
			      
			  	  <tr>
			  	    <td class="device_td_bg3">所属道路：</td>
			  	    <td><input readonly type="text" value='<tags:xiangxuncache keyName="RoadInfo" id="${roadId}"></tags:xiangxuncache>' style="width:200px;"></td>
			  	  </tr>
			  	  <tr>
			  	    <td class="device_td_bg3">所属部门：</td>
			  	    <td>
			  	      <input type="text" id="orgNames" name="orgNames"  readonly onclick="showMenu('orgNames');" 
						  placeholder="所属部门" class="required" style="width:200px;"/><strong style="color:red">&nbsp;*</strong>
				      <input type="hidden" id="orgId" name="orgId" /> 
			  	    </td>
			  	  </tr>
			  	  
			  	  
			  	  <tr>
			  	    <td class="device_td_bg3">设备功能：</td>
			  	    <td>
			  	      <input type="text" id="deviceTypeNames" name="deviceTypeNames" readonly onclick="showMenu('deviceTypeNames');" placeholder="设备功能" style="width:200px;" class="required"/> <strong style="color:red">&nbsp;*</strong>
				      <input type="hidden" id="deviceTypeIds" name="deviceTypeIds" />
			  	    </td>
			  	  </tr>
			  	  <tr>
			  	    <td class="device_td_bg3">设备型号：</td>
			  	    <td>
			  	    <input type="text" id="pattern" maxlength="30" placeholder="设备型号" name="pattern" style="width:200px;">
			  	    <input type="hidden" id="license" name="license" value="${deviceInfo.license}" >
			  	    <input type="hidden" id="isblackanalyse" name="isblackanalyse" value="${deviceInfo.isblackanalyse}" >
			  	    </td>
			  	  </tr>
			  	  <tr>
			  	    <td class="device_td_bg3">是否加入监控：</td>
			  	    <td>
			  	    <input type="radio" name="toasset"  value="1" checked/><font style="font-family: inherit;font-weight: bolder;color: blue;">加入监控</font>
			  	    <input type="radio" name="toasset"  value="0" /><font style="font-family: inherit;font-weight: bolder;color: red;">暂不加入</font>
			  	    </td>
			  	  </tr>
			  	</table>
			   </div>
			   <div class="pull-right" style="margin:0; margin-right:2px;width:24%;">
				  <div class="box-header well"> <small><i class="icon-info-sign"></i>&nbsp;&nbsp;设备基本信息&nbsp;&nbsp;添加说明：<br/><br/>
				    <p>1.&nbsp;&nbsp;带 <strong style="color:red">*</strong> 号的选项卡下的内容是<strong style="color:blue">必填信息</strong></p>
				    <p>2.&nbsp;&nbsp;<strong style="color:blue">该录入设备信息默认进入资产监控范围，若不需要请在是否加入监控项选择：暂不加入 </strong></p>
				    <p>3.&nbsp;&nbsp;设备信息属于整个系统的基础信息请您认真填写。</p>
				    </small> </div>
				</div>
				<div class="clear"></div>
			</div>
			<div class="tab-pane mar_5" style="min-height:430px;" id="tab4" >
			  <div class="pull-left" style="margin-right:0;width:76%">
		  		<table class="table table-border-rl table-border-bot tingche-table">
		  		  <tr>
		  		    <td class="device_td_bg3">建设厂家：</td>
		  		    <td>
		  		      <select style="width:200px;" id="companyId" name="companyId" placeholder="建设厂家" data-rel="chosen">
			            <option value="">请选择</option>
			              <c:forEach items="${companyList}" var="company">
			                <option value="${company.id}" ${company.id==deviceInfo.companyId?'selected':''}>${company.name}</option>
			              </c:forEach>
			          </select>
		  		    </td>
		  		  </tr>
		  		  <tr>
		  		    <td class="device_td_bg3">建设时间：</td>
		  		    <td><input style="width:200px;" name="buildtimeStr" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:document.getElementById('buildtimeStr').value})" id="buildtimeStr" value="${nowdate}" type="text" placeholder="建设时间" /></td>
		  		  </tr>
		  		  
		  		  <tr>
		  		    <td class="device_td_bg3">网络运营商：</td>
		  		    <td>
		  		      <select style="width:200px;" id="buildispId" name="buildispId" placeholder="网络运营商" data-rel="chosen">
			            <option value="">请选择</option>
			            <c:forEach items="${netcompanyList}" var="netcompany">
			              <option value="${netcompany.code}" ${netcompany.code==deviceInfo.buildispId?'selected':''}>${netcompany.name}</option>
			            </c:forEach>
			          </select>
		  		    </td>
		  		  </tr>
		  		  <tr>
		  		    <td class="device_td_bg3">接入方式：</td>
		  		    <td>
		  		      <select style="width:200px;" id="buildnetworkId" name="buildnetworkId" placeholder="接入方式" data-rel="chosen">
			            <option value="">请选择</option>
			            <c:forEach items="${nettypeList}" var="nettype">
			              <option value="${nettype.code}" ${nettype.code==deviceInfo.buildnetworkId?'selected':''}>${nettype.name}</option>
			            </c:forEach>
			          </select>
		  		    </td>
		  		  </tr>
		  		  <tr>
		  		    <td class="device_td_bg3">接电位置：</td>
		  		    <td><input style="width:200px;" type="text" id="powersource" maxlength="50" placeholder="接电位置" name="powersource"></td>
		  		  </tr>
		  		  <tr>
		  		    <td class="device_td_bg3">现场照片：</td>
		  		    <td>
		  		      <p style="margin-bottom:5px;"><input class="input-file uniform_on" name="file1" id="fileInput" type="file" accept="image/gif"><input type="button"  onclick="return showFile()" value="增加"></p>
					  <p style="margin-bottom:5px;"><input class="input-file uniform_on" name="file2" style="display:none;" id="fileInput1" type="file"></p>
					  <input class="input-file uniform_on" name="file3" style="display:none;" id="fileInput2" type="file">
		  		    </td>
		  		  </tr>
		  		</table>
			   </div>
			   <div class="pull-right" style="margin:0; margin-right:2px;width:22%;">
				  <div class="box-header well"> <small><i class="icon-info-sign"></i>&nbsp;&nbsp;建设信息&nbsp;&nbsp;添加说明：<br/><br/>
				    <p>1.&nbsp;&nbsp;带 <strong style="color:red">*</strong> 号的选项卡下的内容是<strong style="color:blue">必填信息</strong></p>
				    <p>2.&nbsp;&nbsp;现场照片最大可以传三张。</p>
				    </small>
				  </div>
			   </div>
			   <div class="clear"></div>
			</div>
			<!-- 55555 -->
			<div class="tab-pane mar_5" style="min-height:430px;" id="tab5" >
			  <table width="100%" style="border:1px solid #ddd;">
			    <tr>
			      <td valign="top" style="border-right:1px solid #ccc;">
			        <input type="hidden" id="gid" name="gid" value="${gid }"/>
				  	<input type="hidden" id="geometryText" name="geometryText" value="${geometryText }"/>
				  	<iframe id="result_form" name="result_form" src="${root}/forward/map/mapTools/iframe/iframe_map/" width="100%" height="420" scrolling="no" style="padding:0; margin:0;" frameborder="0"></iframe>
			      </td>
			      <td valign="top" width="260">
			        <table class="table tingche-table table-border-bot" style="margin-top:-2px;">
			          <tr>
			            <td class="device_td_bg3">设备经度(X)：</td>
			            <td><input autocomplete="off" style="width:120px;" name="mapx" id="mapx" value="" type="text"  readonly /></td>
			          </tr>
			          <tr>
			            <td class="device_td_bg3">设备纬度(Y)：</td>
			            <td><input autocomplete="off" style="width:120px" name="mapy" id="mapy" value="" type="text"  readonly /></td>
			          </tr>
			        </table>
			        <div id="errorDiv" style="display:none;" class="alert alert-block pull-top alert-error"></div>
			      </td>
			    </tr>
			  </table>
			</div>
			<!-- 77777 -->
			<div class="tab-pane mar_5" style="min-height:430px;height:430px;overflow: auto;" id="tab7" >
				<!-- 444444 -->
				<div class="pull-left" style="width:76%;">
			  		<table class="table table-border-rl table-border-bot tingche-table">
			  		  <tr>
			  		    <td class="device_td_bg3" style="width: 8%">资产编号：</td>
			  		    <td>
			  		      <input style="width:200px;" id="assetcode" name="assetcode" class="required" value="<%=code %>" readonly="readonly" type="text" placeholder="资产编号" />
			  		      <input type="hidden" id="serviceid" name="serviceid" value='${factory.id}' /> 
				          <font color="red">*</font>
			  		    </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">资产型号：</td>
			  		    <td>
			  		    	<input style="width:200px;" name="assetmodel" maxlength="30" type="text" placeholder="资产型号" />
				        </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">资产状态：</td>
			  		    <td>
		  			      <select style="width:210px;" id="assetstatus" name="assetstatus" placeholder="资产状态" class="required">
				            <option value="">请选择</option>
				            <c:forEach items="${assetstatusList}" var="status">
				                <option value="${status.code}" ${status.code=='001'?'selected':''}>${status.name}</option>
				            </c:forEach>
				          </select>
				          	<font color="red">*</font>
				        </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">保修时间：</td>
			  		    <td>
			  		    	<input style="width:200px;" id="guaranteetimestr" name="guaranteetimestr" class="required" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'new Date()'})" value="<fmt:formatDate value="<%=ydate %>" pattern="yyyy-MM-dd" />" type="text" placeholder="保修时间" />
				          	<font color="red">*</font>
				        </td>
			  		  </tr>
			  		 
			  		  <tr>
			  		    <td class="device_td_bg3">采购时间：</td>
			  		    <td>
			  		    	<input style="width:200px;" id="purchasetimestr" name="purchasetimestr" class="required" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'new Date()'})" value="<fmt:formatDate value="<%=date %>" pattern="yyyy-MM-dd" />" type="text" placeholder="采购时间" />
				          	<font color="red">*</font>
				        </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">安装时间：</td>
			  		    <td>
			  		    	<input style="width:200px;" id="installtimestr" name="installtimestr" class="required" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,maxDate:'new Date()'})" value="<fmt:formatDate value="<%=date %>" pattern="yyyy-MM-dd HH:mm:ss" />" type="text" placeholder="安装时间" />
				          	<font color="red">*</font>
				        </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">安装地点：</td>
			  		    <td>
			  		    	<input style="width:200px;" id="installplace" name="installplace" class="required" maxlength="50" type="text" value='<tags:xiangxuncache keyName="RoadInfo" id="${roadId}"></tags:xiangxuncache>' />
				          	<font color="red">*</font>
				        </td>
			  		  </tr>
					  <tr>
			  		    <td class="device_td_bg3">MAC地址：</td>
			  		    <td>
			  		    	<input style="width:200px;" name="macaddress" maxlength="25" type="text" placeholder="MAC地址" />
				        </td>
			  		  </tr>
			  		</table>
			  	</div>
		  	</div>
		</div>			
		<div class="btn_line" style="text-align:center;margin-top:0;">
			<input type="hidden" name="roadId" value="${roadId}" />
			<input id="submit_btn" onclick="javascript: return checkForm();" class="btn btn-info mar_r10" type="submit" value="保存" />
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
		</div>
	</form>
  </div>
</div>
	<div id="menuContent" class="menuContent" style="display:none; position: absolute;border: 1px solid #617775;background:#f0f6e4;width:240px;height:160px;overflow:auto;" >
		<ul id="dtTreeSpace" class="ztree" style="margin-top:0px;min-width:340px;"></ul>
	</div>
	
	<div id="orgTreeContent" class="menuContent" style="display:none;position:absolute;background: #f0f6e4;width:240px;height:160px;overflow:auto;border: 1px solid #617775;" >
		<ul id="orgTreeSpace" class="ztree" style="margin-top: 0px;width:340px;"></ul>
	</div>
<script type="text/javascript">
	
	
	function showService(){
		var text = $("#factoryId").find("option:selected").text();
		$("#serviceid").val($("#factoryId").val());
	}

	function userModels(values) {
	  if(values==0){
	   $("#videoAddressInfo").show(1000);
	   $("#videoAddressId").hide();
	  }
	  if(values==1){
	  $("#videoAddressId").show(1000);
	  $("#videoAddressInfo").hide();
	  }
	}
	
</script>
<script src="${root}/compnents/bootstrap/js/jquery-ui-1.8.21.custom.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.cleditor.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.history.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>