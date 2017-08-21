<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" 	type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/js/tree.js'/>" type="text/javascript"></script>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none;">
  <p id="alert-content" align="center"></p>
</div>
<SCRIPT type="text/javascript">
		<!--
		var orgTree;	
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
			
	   //组织机构树相关函数
		function onRadioClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("orgTreeSpace");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}
		
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
		
		
		function showMenu(selectId) {
			var cityObj = $("#"+selectId);
			var cityOffset = $("#"+selectId).offset();
			if(selectId == 'orgNames'){
			   $("#orgTreeContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
			}
			if(selectId == 'deviceTypeNames'){
			   $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
			}
			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#orgTreeContent").fadeOut("fast");
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "deviceTypeNames"|| event.target.id == "orgNames" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
		
		/**$(document).ready(function(){
			var dept ='${departmentjsonArray}';
			alert(dept);
		    var departmentNodes = eval("(" + dept + ")");
			orgTree = $.fn.zTree.init($("#orgTreeSpace"),orgSetting,departmentNodes);
			orgTree.expandAll(true);
		});*/
		
		
		function userModels(values) {
		  if(values==0){
		   $("#videoAddressInfo").show(1000);
		   $("#videoAddressId").hide(1000);
		  }
		  if(values==1){
		  $("#videoAddressId").show(1000);
		  $("#videoAddressInfo").hide(1000);
		  }
		   
		}
		
		//-->
</SCRIPT>
<div class="conten_box"> 
	<form id="inputForm" class="form-horizontal" style="margin:0; padding:0;">
		<h4 class="xtcs_h4" style="margin:0;">监控设备信息--详情</h4>
		<input type="hidden" name="menuid" value="${menuid}"/>
		<input type="hidden" name="page" value="${page}"/>
	    <!-- 选项卡 开始 -->	 
		<ul class="nav nav-tabs" style="padding:0 10px;margin:5px 0 0 0;">
		  <li class="active"><a href="#tab1" data-toggle="tab">基本信息<strong style="color:red">*</strong></a></li>
		</ul>
		<div class="tab-content" style="margin:0;padding:0;">
		    <!-- 1111 -->	
			<div class="tab-pane mar_5 active" id="tab1" >
		  	   <table class="table table-border-rl table-border-bot tingche-table">
		  	      <tr>
				    <td class="device_td_bg3">所在道路：</td>
				    <td clospan="3"><tags:xiangxuncache keyName="RoadInfo" id="${videoInfo.roadinfoId}"></tags:xiangxuncache></td>
				    <td class="device_td_bg3"></td>
				    <td></td>
				  </tr>
		  	      <tr>
				    <td class="device_td_bg3">设备编号：</td>
				    <td>${videoInfo.code}</td>
				    <td class="device_td_bg3">设备 IP：</td>
				    <td>${videoInfo.deviceIp}</td>
				  </tr>
				  <tr>
				    <td class="device_td_bg3">监控名称：</td>
				    <td>${videoInfo.name}</td>
				    <td class="device_td_bg3">监控类型：</td>
				    <td><tags:xiangxuncache keyName="Dic" typeCode="monittype" id="${videoInfo.videotypeCode}" ></tags:xiangxuncache></td>
				  </tr>
				  <tr>
				    <td class="device_td_bg3">监控方向：</td>
				    <td><tags:xiangxuncache keyName="Dic" typeCode="direction" id="${videoInfo.directionCode}" ></tags:xiangxuncache></td>
				    <td class="device_td_bg3">播放配置：</td>
				    <td>${1==videoInfo.usetemplate?'使用模板':'单独配置'}</td>
				  </tr>
				  <tr>
				    <td class="device_td_bg3">所属部门：</td>
				    <td><tags:xiangxuncache keyName="Department" id="${videoInfo.orgId}"></tags:xiangxuncache></td>
				    <td class="device_td_bg3">建设厂家：</td>
				    <td><tags:xiangxuncache keyName="DeviceCompanyInfo" id="${videoInfo.companyid}"></tags:xiangxuncache></td>
				  </tr>
				  <tr>
				    <td class="device_td_bg3">设备账号：</td>
				    <td>${videoInfo.deviceUsername}</td>
				    <td class="device_td_bg3">访问密码：</td>
				    <td>${videoInfo.devicePassword}</td>
				  </tr>
				  <tr>
				    <td class="device_td_bg3">端 口 号：</td>
				    <td>${videoInfo.devicePort}</td>
				    <td class="device_td_bg3">添加时间</td>
				    <td><fmt:formatDate value="${videoInfo.inserttime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				  </tr>
				</table>
				
				<div class="mar_t5">
					<table class="table table-border-rl table-border-bot tingche-table">
					  <tr>
					    <td class="device_td_bg3">流媒体服务器：</td>
					    <td>
					      <ul class="ul_checkbox">
					        <li><span>IP地址：</span><input type="text" id="ip" name="ip" readonly style="width:120px;" minlength="7" maxlength="15" value="${videoInfo.ip}" ip="true"></li>
					        <li><span>端口号：</span><input type="text" id="port" name="port" readonly style="width:120px;" minlength="2" maxlength="6" value="${videoInfo.port}" digits="true" ></li>
					        <li><span>用户名：</span><input type="text" id="username" name="username" readonly style="width:120px;" maxlength="10" value="${videoInfo.username}"></li>
					        <li><span>密　码：</span><input type="text" id="password" name="password" readonly style="width:120px;" maxlength="10" value="${videoInfo.password}"></li>
					      </ul>
					    </td>
					  </tr>
					</table>
				
				<span style="float:left; margin-left:10px; margin-top:4px;"></span>
				  <div style="margin-left:100px; line-height:30px;">
				    
				    
				    
				  </div>
				</div>
            </div>
        </div>
		<div class="btn_line">
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
		</div>
	</form>
</div>
<div id="orgTreeContent" class="menuContent" style="display:none; position: absolute;" >
	<ul id="orgTreeSpace" class="ztree" style="margin-top: 0px;border: 1px solid #617775;background: #f0f6e4;width:210px;height:260px;overflow-y:scroll;overflow-x:auto;"></ul>
</div>
<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
 </script>
<script src="${root}/compnents/bootstrap/js/jquery-ui-1.8.21.custom.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.cleditor.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.history.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>	
  <%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>