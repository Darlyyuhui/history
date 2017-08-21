<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" 	type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/js/tree.js'/>" type="text/javascript"></script>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<%
	Date date = new Date();
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
		map.drawPoint();
	}
</script>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none;">
  <p id="alert-content" align="center"></p>
</div>
<SCRIPT type="text/javascript">
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
		
		//将选中的部门编号 复制到设备编号的前12位中
		selectOrgCodeByOrgId(h);
	}
	
	//根据部门ID 查对应的部门CODE 并自动赋值到设备编号前12位 
	//YANTAO 20140331 ADD
	function selectOrgCodeByOrgId(orgid){
        $.getJSON("${root}/property/deviceinfo/getOrgCodeByOrgId/" + orgid + "/",
		function(data) {
			var codestart = $("#codeStart");
	        codestart.attr("value", data.content);
	        areaClick(data.content);
        });
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
		if (!(event.target.id == "menuBtn" || event.target.id == "deviceTypeNames"|| event.target.id == "orgNames" || event.target.id == "orgTreeContent" || $(event.target).parents("#orgTreeContent").length>0)) {
			hideMenu();
		}
	}
	
	$(document).ready(function(){
		var dept ='${departmentjsonArray}';
	    var departmentNodes = eval("(" + dept + ")");
		orgTree = $.fn.zTree.init($("#orgTreeSpace"),orgSetting,departmentNodes);
		orgTree.expandAll(true);
	});
	
	
	function userModels(values) {
		if (values == 0) {
			$("#videoAddressInfo").show(1000);
			$("#videoAddressId").hide(1000);
		}
		if (values == 1) {
			$("#videoAddressId").show(1000);
			$("#videoAddressInfo").hide(1000);
		}

	}
	function areaClick(code) {
		if ($("#codeStart").val() != '' && $("#codeMiddle").val() != '') {
			checkDeviceCode();
		}
	}
	function checkDeviceCode() {
		$("#loadingdiv").css("display", "");
		var codeStart = $("#codeStart").val();
		var codeMiddle = $("#codeMiddle").val();
		var codeEnd = $("#codeEnd").val();
		var videoCode = codeStart + codeMiddle + codeEnd;
		$.getJSON("${root}/property/videoinfo/codeExist/" + videoCode + "/",
				function(data) {
					$("#messagediv").html(data.content);
					$("#loadingdiv").css("display", "none");
				});
	}
	
	function checkSubmit(){
		var submit = true;
		var name = $("#name").val();
		var deviceIp = $("#deviceIp").val()
		var orgNames = $("#orgNames").val();
		var codeStart = $("#codeStart").val();
		var companyid = $("#companyid").val();
		var factoryId = $("#factoryId").val();
		var videotypeCode = $("#videotypeCode").val();
		var directionCode = $("#directionCode").val();
		
		if(name == ''){
			alert("[基本信息]标签页-监控名称不能为空");
			submit = false;
		}else if(deviceIp == ''){
			alert("[基本信息]标签页-设备 IP不能为空");
			submit = false;
		}else if(orgNames == ''){
			alert("[基本信息]标签页-所属部门不能为空");
			submit = false;
		}else if(codeStart == ''){
			alert("[基本信息]标签页-部门编号不能为空");
			submit = false;
		}else if(companyid == ''){
			alert("[基本信息]标签页-建设厂家不能为空");
			submit = false;
		}else if(factoryId == ''){
			alert("[基本信息]标签页-服务厂商不能为空");
			submit = false;
		}else if(videotypeCode == ''){
			alert("[基本信息]标签页-监控类型不能为空");
			submit = false;
		}else if(directionCode == ''){
			alert("[基本信息]标签页-监控方向不能为空");
			submit = false;
		}else{
			if($("#modelFile").attr('checked')=='checked'){
		    	if($("#realvideoaddressid").val() == ''){
		    		alert("请选择流媒体服务器");
		    		submit = false;
		    	}
		    }else{
		    	if($("#ip").val() == ''){
		    		alert("流媒体服务器IP地址不能为空");
		    		submit = false;
		    	}
		    }
		}
		if(submit){
			if($("#toasset").val() == '1'){
		    	//资产状态
		    	var assetstatus =$("#assetstatus").val();
		    	//保修时间
		    	var guaranteetimestr =$("#guaranteetimestr").val();
		    	//生产厂商
		    	var manufacturer =$("#manufacturer").val();
		    	//采购时间
		    	var purchasetimestr =$("#purchasetimestr").val();
		    	//工程商
		    	var engineering = $("#engineering").val();
		    	//安装时间
		    	var installtimestr = $("#installtimestr").val();
		    	//安装地点
		    	var installplace = $("#installplace").val();
		    	if(assetstatus==''&&manufacturer==''&&engineering==''&&installplace==''){
		    		alert("请输入资产配置信息或者取消勾选");
		    		submit = false;
		    	}else{
		    		if(assetstatus==''){
		    			alert("[资产配置]标签页-资产状态不能为空");
		    			submit = false;
		    		}else if(guaranteetimestr==''){
		    			alert("[资产配置]标签页-保修时间不能为空");
		    			submit = false;
		    		}else if(manufacturer==''){
		    			alert("[资产配置]标签页-生产厂商不能为空");
		    			submit = false;
		    		}else if(purchasetimestr==''){
		    			alert("[资产配置]标签页-采购时间不能为空");
		    			submit = false;
		    		}else if(engineering==''){
		    			alert("[资产配置]标签页-工程商不能为空");
		    			submit = false;
		    		}else if(installtimestr==''){
		    			alert("[资产配置]标签页-安装时间不能为空");
		    			submit = false;
		    		}else if(installplace==''){
		    			alert("[资产配置]标签页-安装地点不能为空");
		    			submit = false;
		    		}
		    	}
		    }else{
		    	var namestyle = $("#name").attr("class");
		    	var ipstyle = $("#deviceIp").attr("class");
		    	if(namestyle == 'required error'){
		    		alert("[基本信息]标签页-监控名称不正确");
					submit = false;
		    	}else if(ipstyle == 'required error'){
		    		alert("[基本信息]标签页-设备IP不正确");
					submit = false;
		    	}
		    }
			if(submit){
				var codeStart = $("#codeStart").val();
				var codeMiddle = $("#codeMiddle").val();
				var codeEnd = $("#codeEnd").val();
				var videoCode = codeStart + codeMiddle + codeEnd;
				$("#videoCode").val(videoCode);
			}
		}
		return submit;
    }

	// 地图上点击添加设备点
	function addVideoPosition() {
		window.frames["result_form"].activeMapClick();
	}
	function showMap() {
		//window.frames["result_form"].initmap();
	}
</script>
<div class="conten_box">   
	<form id="inputForm" class="form-horizontal" action="${root}/property/videoinfo/doAdd" method="post" style="margin-bottom:0;padding:0">
       <h4 class="xtcs_h4" style="margin:0;"><tags:xiangxuncache keyName="RoadInfo" id="${roadId}"></tags:xiangxuncache>&nbsp;&nbsp;设备信息-添加
		<div style="float: right;">
			<input type="checkbox" onclick="show(this)"/><font style="font-family: inherit;font-weight: bolder;color: red;">转为资产</font>
		</div>
       </h4>
		<input type="hidden" name="menuid" value="${menuid}"/>
		<input type="hidden" name="page" value="${page}"/>
		<input type="hidden" id="toasset" name="toasset" value=""/>
		<input type="hidden" id="videoCode" name="code" value=""/>
		<!-- 选项卡 开始 -->	 
		<ul class="nav nav-tabs"  style="padding:0 10px;margin:5px 0 0 0;">
		  <li class="active"><a href="#tab1" data-toggle="tab">基本信息<strong style="color:red">*</strong></a></li>
		  <li><a href="#tab2" data-toggle="tab" onclick="mapTabShow()">地图信息</a></li>
		  <li style="display: none;"><a href="#tab3" data-toggle="tab">资产配置</a></li>
		</ul>
		<div class="tab-content" style="height:460px;overflow-y:auto;">
			<div class="tab-pane mar_5" id="tab2">
			    <table width="100%" style="border:1px solid #ccc;">
			  	  <tr>
			  	    <td valign="top" style="border-right:1px solid #ccc;">
			 		    <iframe id="result_form" name="result_form" src="${root}/forward/map/mapTools/iframe/iframe_map/?name=${roadInfo.name }" 
			 		    width="100%" height="440" scrolling="no" style="padding:0; margin:0;" frameborder="0"></iframe>
			  	    </td>
			  	    <td width="260" valign="top">
			  	      <table class="table table-border-bot tingche-table" style="margin-top:-2px;">
						<tr>
						  <td class="device_td_bg3">设备经度(X)：</td>
						  <td><input class="input" autocomplete="off" style="width:90%;" name="mapx" id="mapx" value="" type="text" readonly /></td>
						</tr>
						<tr>
						  <td class="device_td_bg3">设备纬度(Y)：</td>
						  <td><input class="input" autocomplete="off" style="width:90%;" name="mapy" id="mapy" value="" type="text" readonly /></td>
						</tr>
					  </table>
				      <div id="errorDiv" style="display:none;" class="alert alert-block pull-top alert-error"></div>
			  	    </td>
			  	  </tr>
			  	</table>
			</div>
			<!-- 1111 -->	
			<div class="tab-pane mar_5 active" id="tab1">
				<table class="table table-border-rl table-border-bot tingche-table">
					<tr>
						<td class="device_td_bg3">监控名称：</td>
						<td><input style="width:160px;" type="text" id="name" maxlength="20" specialcharfilter="true" placeholder="监控名称" name="name" class="required" ><strong style="color:red">&nbsp;*</strong></td>
						<td class="device_td_bg3">设备 IP：</td>
						<td width="35%"><input type="text" id="deviceIp" name="deviceIp" maxlength="15" class="required" ip="true" style="width:160px;"><strong style="color:red">&nbsp;*</strong></td>
					</tr>
					<tr>
						<td class="device_td_bg3">所属部门：</td>
						<td>
						  <input style="width:160px;" type="text" id="orgNames" name="orgNames" readonly onclick="showMenu('orgNames');" placeholder="所属部门" class="required" /><strong style="color:red">&nbsp;*</strong>
						  <input type="hidden" id="orgId" name="orgId" />
						</td>
					</tr>
					<tr>
						<td class="device_td_bg3">设备编号：</td>
						<td colspan="3">
			            	部门编号
				            <input type="text" id="codeStart" style="width:100px" name="toporgcode" readonly="readonly" placeholder="点击部门输入框添加" class="required"/><strong style="color:red">&nbsp;*</strong>
				            <input type="button" class="btn" value="设备类型" readonly="readonly">
				            <input type="text" id="codeMiddle" value="05" style="width:20px" readonly  name="devicetypecode"/>
				            <input type="text" id="codeEnd" style="width:30px" readonly name="codeEnd"  value="${automaticNum}"/>
				            <span id="loadingdiv" style="display:none"><img style="width:25px; height:25px;" src="${root}/images/loading.gif" /></span>
				            <span id="messagediv"></span>
						</td>
					</tr>
					<tr>
						<td class="device_td_bg3">建设厂家：</td>
						<td>
						  <select style="width:170px;" id="companyid" name="companyid" class="required" placeholder="建设厂家">
							<option value="">请选择</option>
							<c:forEach items="${companyList}" var="company">
							    <option value="${company.id}" ${company.id==videoInfo.companyid?'selected':''}>${company.name}</option>
							</c:forEach>
				          </select> <strong style="color:red">*</strong>
				        </td>
		  			    <td class="device_td_bg3">服务厂商：</td>
		  			    <td>
		  			      <select style="width:170px;" id="factoryId" name="factoryId" onchange="showService()" placeholder="服务厂商" class="required">
				            <option value="">请选择</option>
				            <c:forEach items="${factoryList}" var="factory">
				                <option value="${factory.id}" ${factory.id==videoInfo.factoryId?'selected':''}>${factory.name}</option>
				            </c:forEach>
				          </select>
				          <strong style="color:red">*</strong>
		  			    </td>
		  			</tr>
					<tr>
						<td class="device_td_bg3">监控类型：</td>
						<td>
						  <select id="videotypeCode" name="videotypeCode" style="width:170px;" class="required" placeholder="监控类型">
							<option value="">请选择</option>
							<c:forEach items="${videoTypeList}" var="videoType">
								 <option value="${videoType.code}" ${videoType.code==videoInfo.videotypeCode?'selected':''}>${videoType.name}</option>
							</c:forEach>
				          </select> <strong style="color:red">*</strong>
						</td>
						<td class="device_td_bg3">监控方向：</td>
						<td>
						    <select style="width:170px;" id="directionCode" name="directionCode" class="required" placeholder="监控方向">
								<option value="">请选择</option>
								<c:forEach items="${directList}" var="direct">
									<option value="${direct.code}" ${direct.code==videoInfo.directionCode?'selected':''}>${direct.name}</option>
								</c:forEach>
					        </select> <strong style="color:red">*</strong>
						</td>
					</tr>
					<tr>
						<td class="device_td_bg3">设备账号：</td>
						<td><input style="width:160px;" type="text" id="deviceUsername" maxlength="20" placeholder="访问账号" name="deviceUsername"></td>
						<td class="device_td_bg3">访问密码：</td>
						<td width="35%"><input type="text" id="devicePassword" name="devicePassword" maxlength="15" style="width:160px;"></td>
					</tr>
					
					<tr>
						<td class="device_td_bg3">端 口 号：</td>
						<td><input style="width:160px;" type="text" id="devicePort" maxlength="10" digits="true" placeholder="设备端口号" name="devicePort"></td>
						<td class="device_td_bg3">监控机型：</td>
						<td width="35%">
						  <ul class="ul_checkbox">
						    <li><input type="radio" name="deviceShape" value="0" checked>枪机</li>
						    <li><input type="radio" name="deviceShape" value="1">球机</li>
					      </ul>
				        </td>
					</tr>
					<!-- 智能交通项目使用 -->
					<tr>
						<td class="device_td_bg3">播放配置：</td>
						<td colspan="3">
						  <ul class="ul_checkbox">
						    <li><input type="radio" id="modelFile" name="usetemplate" onclick="userModels(this.value);" value="1" checked>使用模板</li>
						    <li><input type="radio" name="usetemplate" onclick="userModels(this.value);" value="0" >单独配置</li>
						  </ul>
						</td>
					</tr>
				</table>
				<div id="videoAddressInfo" style="display:none">
					<table class="table table-border-rl table-border-bot bukong-table">
					  <tr>
						<td class="device_td_bg3">流媒体服务器：</td>
						<td>
						  <span>IP地址：</span><input type="text" id="ip" name="ip" maxlength="15" class="required" style="width:110px;" ip="true"><strong style="color:red"> *</strong>
						  <span>端口号：</span><input type="text" id="port" name="port" style="width:40px;" maxlength="6" digits="true" >
						  <span>用户名：</span><input type="text" id="username" name="username" specialcharfilter="true" maxlength="10" style="width:80px;">
						  <span>密　码：</span><input type="text" id="password" name="password" password="true" maxlength="20" style="width:80px;">
						</td>
					  </tr>
					</table>
				</div>
				<div id="videoAddressId">
					<table class="table table-border-rl table-border-bot bukong-table">
					  <tr>
					    <td class="device_td_bg3">流媒体服务器：</td>
					    <td>
					    	<select id="realvideoaddressid" class="required" name="realvideoaddressid" style="width:170px;padding:4px;margin:0;" data-rel="chosen" placeholder="使用模板">
								<option value="">请选择</option>
								<c:forEach items="${realiVideoAddressList}" var="realiVideoAddress">
									<option value="${realiVideoAddress.id}" ${realiVideoAddress.id==videoInfo.videotypeCode?'selected':''}>${realiVideoAddress.name} [ ${realiVideoAddress.ip} ]</option>
								</c:forEach>
					        </select>
					        <strong style="color:red">&nbsp;*</strong>
					    </td>
					  </tr>
					</table>
                </div>
			  </div>
			  <!-- 111结束 -->
			  <!-- 2222222 -->
			<div class="tab-pane mar_5" style="min-height:430px;height:500px;overflow: auto;" id="tab3" >
				<div class="pull-left" style="width:76%;">
			  		<table class="table table-border-rl table-border-bot tingche-table">
			  		  <tr>
			  		    <td class="device_td_bg3" style="width: 8%">资产编号：</td>
			  		    <td>
			  		      <input style="width:160px;" name="assetcode" class="required" value="<%=code %>" readonly="readonly" type="text" placeholder="资产编号" />
				          <font color="red">*</font>
			  		    </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">资产型号：</td>
			  		    <td>
			  		    	<input style="width:160px;" name="assetmodel" maxlength="30" type="text" placeholder="资产型号" />
				        </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">资产状态：</td>
			  		    <td>
		  			      <select style="width:170px;" id="assetstatus" name="assetstatus" placeholder="资产状态" class="required">
				            <option value="">请选择</option>
				            <c:forEach items="${assetstatusList}" var="status">
				                <option value="${status.code}" ${status.code==asset.assetstatus?'selected':''}>${status.name}</option>
				            </c:forEach>
				          </select>
				          	<font color="red">*</font>
				        </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">保修时间：</td>
			  		    <td>
			  		    	<input style="width:160px;" id="guaranteetimestr" name="guaranteetimestr" class="required" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'new Date()'})" value="<fmt:formatDate value="<%=date %>" pattern="yyyy-MM-dd" />" type="text" placeholder="保修时间" />
				          	<font color="red">*</font>
				        </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">生产厂商：</td>
			  		    <td>
			  		    	<input style="width:160px;" id="manufacturer" name="manufacturer" class="required" maxlength="30" type="text" placeholder="生产厂商" />
				          	<font color="red">*</font>
				        </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">采购时间：</td>
			  		    <td>
			  		    	<input style="width:160px;" id="purchasetimestr" name="purchasetimestr" class="required" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'new Date()'})" value="<fmt:formatDate value="<%=date %>" pattern="yyyy-MM-dd" />" type="text" placeholder="采购时间" />
				          	<font color="red">*</font>
				        </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">工&nbsp;&nbsp;程&nbsp;&nbsp;商：</td>
			  		    <td>
			  		    	<input style="width:160px;" id="engineering" name="engineering" class="required" maxlength="30" type="text" placeholder="工&nbsp;&nbsp;程&nbsp;&nbsp;商" />
				          	<font color="red">*</font>
				        </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">安装时间：</td>
			  		    <td>
			  		    	<input style="width:160px;" id="installtimestr" name="installtimestr" class="required" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,maxDate:'new Date()'})" value="<fmt:formatDate value="<%=date %>" pattern="yyyy-MM-dd HH:mm:ss" />" type="text" placeholder="安装时间" />
				          	<font color="red">*</font>
				        </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">安装地点：</td>
			  		    <td>
			  		    	<input style="width:160px;" id="installplace" name="installplace" class="required" maxlength="50" type="text" placeholder="安装地点" />
				          	<font color="red">*</font>
				        </td>
			  		  </tr>
			  		  <tr>
						<td class="device_td_bg3">服务厂商：</td>
						<td width="40%">
							<input style="width:160px;" id="servicename" name="servicename" type="text" readonly="readonly" placeholder="服务厂商" />
							<input type="hidden" id="serviceid" name="serviceid" value='${factory.id}' /> 
						</td>
					  </tr>
					  <tr>
			  		    <td class="device_td_bg3">MAC地址：</td>
			  		    <td>
			  		    	<input style="width:160px;" name="macaddress" maxlength="25" type="text" placeholder="MAC地址" />
				        </td>
			  		  </tr>
			  		</table>
			  	</div>
		  	</div>
	    </div>
		<div class="btn_line">
		    <input type="hidden" name="roadinfoId" value="${roadId}" />
			<input id="submit_btn" onclick="javascript: return checkSubmit();" class="btn btn-info mar_r10" type="submit" value="保存" />
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
		</div>
	</form>
</div>

<div id="orgTreeContent" class="menuContent" style="display:none; position: absolute;border: 1px solid #617775;background: #f0f6e4;width:160px;height:260px;overflow:scroll;" >
	<ul id="orgTreeSpace" class="ztree" style="margin-top: 0px; width:360px;"></ul>
</div>

<script>
	function show(a){
		var checked = $(a).attr("checked");
		if((checked=="checked") || checked){
				$(this).attr("checked",checked);
				$("#toasset").val("1");
				$(".nav-tabs li:last").css("display","");
		    }else{
		    	$(this).removeAttr("checked");
		    	$("#toasset").val("");
		    	$(".nav-tabs li:last").css("display","none");
		    	$(".nav-tabs li:first").addClass("active").siblings().removeClass("active");
				$("#tab1").addClass("active").siblings().removeClass("active");
		    }
	}
	
	function showService(){
		var text = $("#factoryId").find("option:selected").text();
		$("#servicename").val($("#factoryId").val()==''?"":text);
		$("#serviceid").val($("#factoryId").val());
	}
	
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: {
				"deviceIp":{
					remote:{
						url:"${root}/property/videoinfo/ipExist",
						type:"post",
						data:{
							name:function(){
								return $("#deviceIp").val();
							}
						}
					}
				},
				"name":{
					remote:{
						url:"${root}/property/videoinfo/nameExist",
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
