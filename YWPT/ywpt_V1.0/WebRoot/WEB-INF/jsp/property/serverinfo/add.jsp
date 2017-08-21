<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" 	type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<%
	String pages = request.getParameter("page");
	String menuid = request.getParameter("menuid");
	Date date = new Date();
	String code = new SimpleDateFormat("yyMMddHHmmss").format(date)+new Random().nextInt(10000);
 %>
<script type="text/javascript">
	function checkForm(){
		var submit = true;
		var name = $("#name").val();
		var serverip = $("#serverip").val()
		var model = $("#model").val();
		var type = $("#type").val();
		var orgNames = $("#orgNames").val();
		var factoryId = $("#factoryId").val();
		var cpuModel = $("#cpuModel").val();
		var cpuNum = $("#cpuNum").val();
		var cpuCoreNum = $("#cpuCoreNum").val();
		var ramType = $("#ramType").val();
		var ramVolume = $("#ramVolume").val();
		var diskVolume = $("#diskVolume").val();
		
		if(name == ''){
			alert("[基本信息]标签页-服务器名称不能为空");
			submit = false;
		}else if(serverip == ''){
			alert("[基本信息]标签页-服务器IP不能为空");
			submit = false;
		}else if(model == ''){
			alert("[基本信息]标签页-服务器型号不能为空");
			submit = false;
		}else if(type == ''){
			alert("[基本信息]标签页-服务器类别不能为空");
			submit = false;
		}else if(orgNames == ''){
			alert("[基本信息]标签页-所属部门不能为空");
			submit = false;
		}else if(factoryId == ''){
			alert("[基本信息]标签页-服务厂商不能为空");
			submit = false;
		}else if(cpuModel == ''){
			alert("[处理器信息]标签页-CPU型号不能为空");
			submit = false;
		}else if(cpuNum == ''){
			alert("[处理器信息]标签页-CPU数量不能为空");
			submit = false;
		}else if(cpuCoreNum == ''){
			alert("[处理器信息]标签页-CPU核心数不能为空");
			submit = false;
		}else if(ramType == ''){
			alert("[内存及其他]标签页-内存类型不能为空");
			submit = false;
		}else if(ramVolume == ''){
			alert("[内存及其他]标签页-内存容量不能为空");
			submit = false;
		}else if(diskVolume == ''){
			alert("[内存及其他]标签页-硬盘容量不能为空");
			submit = false;
		}else{
			if($("#toasset").val() == '1'){
		    	//资产状态
		    	var assetstatus =$("#assetstatus").val();
		    	//保修时间
		    	var guaranteetimestr =$("#guaranteetimestr").val();
		    	//采购时间
		    	var purchasetimestr =$("#purchasetimestr").val();
		    	//安装时间
		    	var installtimestr = $("#installtimestr").val();
		    	//安装地点
		    	var installplace = $("#installplace").val();
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
	    		}else if(installplace==''){
	    			alert("[资产配置]标签页-安装地点不能为空");
	    			submit = false;
	    		}
		    }else{
		    	var namestyle = $("#name").attr("class");
		    	var ipstyle = $("#serverip").attr("class");
		    	if(namestyle == 'required error'){
		    		alert("[基本信息]标签页-服务器名称不正确");
					submit = false;
		    	}else if(ipstyle == 'required error'){
		    		alert("[基本信息]标签页-服务器IP不正确");
					submit = false;
		    	}
		    }
		}
		return submit;
	}
</script>
<div class="conten_box" style="margin-left: 180px;">
	<form id="inputForm" class="form-inline" action="${root}/property/serverinfo/doAdd" method="post">
		<h4 class="xtcs_h4" style="margin:0;">服务器信息-添加</h4>
		<input type="hidden" name="menuid" value="<%=menuid%>"/>
		<div id="common_div">
			<!-- 选项卡 开始 -->	 
			<ul class="nav nav-tabs" style="padding-left:10px;margin:0;">
			    <li class="active"><a href="#tab1" data-toggle="tab">基础信息<font color="red">*</font></a></li>
			    <li><a href="#tab2" data-toggle="tab">处理器信息<font color="red">*</font></a></li>
			    <li><a href="#tab3" data-toggle="tab">内存及其他<font color="red">*</font></a></li>
			    <li style="display: none;"><a href="#tab4" data-toggle="tab">资产配置</a></li>
			</ul>
			<div class="tab-content">
				<!-- 1111 -->
				<div class="tab-pane mar_5 active" id="tab1" style="min-height:500px;">
					<table class="table tingche-table table-border-rl table-border-bot">
						<tr>
							<td class="device_td_bg3">服务器名称：</td>
							<td>
								<input style="width:160px;" type="text" id="name" name="name" maxlength="30" class="required">
								<font color="red">*</font>
							</td> 
							<td class="device_td_bg3">服务器IP：</td>
							<td>
								<input style="width:160px;" type="text" id="serverip" name="serverip" ip="true" maxlength="15" class="required">
			                    <font color="red">*</font>
							</td>	
						</tr>
						<tr>
							<td class="device_td_bg3">服务器型号：</td>
							<td>
								<input style="width:160px;" type="text" id="model" name="model" maxlength="20" class="required">
								<font color="red">*</font>
							</td>		
							<td class="device_td_bg3">服务器类别：</td>
							<td>
								<select style="width:37%;min-width: 170px;" id="type" name="type" placeholder="服务器类别" class="required">
									<option value="">请选择</option>
								    <option value="机架式">机架式</option>
								    <option value="塔式">塔式</option>
						        </select>
								<font color="red">*</font>
							</td> 
						</tr>
						<tr>
					  	    <td class="device_td_bg3">所属部门：</td>
					  	    <td>
					  	      <input type="text" id="orgNames" name="orgNames"  readonly onclick="showMenu('orgNames');" 
								  placeholder="所属部门" class="required" style="width:160px;"/><font color="red">*</font>
						      <input type="hidden" id="orgId" name="orgId" /> 
					  	    </td>
					  	    <td class="device_td_bg3">服务厂商：</td>
					  	    <td>
					          <select style="width:170px;padding:4px;overflow:hidden;" id="factoryId" name="factoryId" class="required" onchange="showService()">
					            <option value="">请选择</option>
					            <c:forEach items="${factoryList}" var="factory">
					              <option value="${factory.id}">${factory.name}</option>
					            </c:forEach>
						      </select>
						      <font color="red">*</font>
					        </td>
					  	</tr>
				  <tr>
			  	    <td class="device_td_bg3">是否加入监控：</td>
			  	    <td>
			  	    <input type="radio" name="toasset"  value="1" checked/><font style="font-family: inherit;font-weight: bolder;color: blue;">加入监控</font>
			  	    <input type="radio" name="toasset"  value="0" /><font style="font-family: inherit;font-weight: bolder;color: red;">暂不加入</font>
			  	    </td>
			  	    
			  	    <td class="device_td_bg3"></td>
					        <td></td>
			  	  </tr>
					</table>
				</div>
				<!-- 2222 -->
				<div class="tab-pane mar_5" id="tab2" style="min-height:500px;">
					<table class="table tingche-table table-border-rl table-border-bot" style="border:1px solid #ccc;">
						<tr>
							<td class="device_td_bg3">CPU型号：</td>
							<td>
								<input style="width:160px;" type="text" id="cpuModel" name="cpuModel" maxlength="20" class="required">
								<font color="red">*</font>
							</td> 
							<td class="device_td_bg3">CPU类型：</td>
							<td>
								<input style="width:160px;" type="text" id="cpuType" name="cpuType" maxlength="20">
							</td> 
						</tr>
						<tr>
							<td class="device_td_bg3">CPU数量：</td>
							<td>
								<input style="width:160px;" type="text" id="cpuNum" name="cpuNum" digits="true" maxlength="2" class="required">
			                    <font color="red">*</font>（颗）
							</td>
							<td class="device_td_bg3">CPU核心数：</td>
							<td>
								<input style="width:160px;" type="text" id="cpuCoreNum" name="cpuCoreNum" digits="true" maxlength="2" class="required">
								<font color="red">*</font>（核）
							</td> 
						 </tr>
						 <tr>
							<td class="device_td_bg3">CPU线程数：</td>
							<td>
								<input style="width:160px;" type="text" name="threadNum" digits="true" maxlength="3">
							</td>
							<td class="device_td_bg3">三级缓存：</td>
							<td>
								<input style="width:160px;" type="text" name="threeCache" digits="true" maxlength="3">（MB）
							</td>			
						</tr>
					</table>
				</div>
				<!-- 3333 -->
				<div class="tab-pane mar_5" style="min-height:500px;" id="tab3" >
					<table class="table tingche-table table-border-rl table-border-bot" id="TbData">
						<tr>
							<td class="device_td_bg3">内存类型：</td>
							<td>
								<input style="width:160px;" type="text" id="ramType" name="ramType" class="required" maxlength="10">
								<font color="red">*</font>
							</td> 
							<td class="device_td_bg3">内存容量：</td>
							<td>
								<input style="width:160px;" type="text" id="ramVolume" name="ramVolume" digits="true" class="required" maxlength="5">
								<select style="width:5%;min-width: 60px;" name="ramUnit" placeholder="内存单位">
									<option value="GB">GB</option>
								    <option value="TB">TB</option>
						        </select>
								<font color="red">*</font>
							</td> 
						</tr>
						<tr>
							<td class="device_td_bg3">硬盘接口类型：</td>
							<td>
								<input style="width:160px;" type="text" name="diskPortType" maxlength="10">
							</td> 
							<td class="device_td_bg3">硬盘容量：</td>
							<td>
								<input style="width:160px;" type="text" id="diskVolume" name="diskVolume" digits="true" class="required" maxlength="5">
								<select style="width:5%;min-width: 60px;" name="diskUnit" placeholder="内存单位">
									<option value="GB">GB</option>
								    <option value="TB">TB</option>
						        </select>
								<font color="red">*</font>
							</td> 
						</tr>
						<tr>
							<td class="device_td_bg3">备注：</td>
							<td colspan="3">
								<textarea rows="3" style="width:80%;" name="note" maxlength="100" ></textarea>
							</td>	
						</tr>
					</table>
				</div>
				<!-- 444444 -->
				<div class="tab-pane mar_5" style="min-height:430px;height:500px;overflow: auto;" id="tab4" >
					<div class="pull-left" style="width:76%;">
				  		<table class="table table-border-rl table-border-bot tingche-table">
				  		  <tr>
				  		    <td class="device_td_bg3" style="width: 8%">资产编号：</td>
				  		    <td>
				  		      <input style="width:200px;" name="assetcode" class="required" value="<%=code %>" readonly="readonly" type="text" placeholder="资产编号" />
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
				  		    	<input style="width:200px;" id="guaranteetimestr" name="guaranteetimestr" class="required" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'new Date()'})" value="<fmt:formatDate value="<%=date %>" pattern="yyyy-MM-dd" />" type="text" placeholder="保修时间" />
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
				  		    	<input style="width:200px;" id="installplace" name="installplace" class="required" maxlength="50" type="text" placeholder="安装地点" value="内场" />
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
		</div>
		<div class="btn_line">
			<button class="btn btn-info mar_r10" onclick="javascript:return checkForm();" type="submit">保存</button>
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
	</form>
</div>
<script type="text/javascript">

	function showService(){
		var text = $("#factoryId").find("option:selected").text();
		$("#servicename").val($("#factoryId").val()==''?"":text);
		$("#serviceid").val($("#factoryId").val());
	}
	

	//所属部门 组织机构树
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
		
	var orgTree;
		
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
		$("body").bind("mousedown",onBodyDown);
	}
	function hideMenu() {
		$("#orgTreeContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "deviceTypeNames"|| event.target.id == "orgNames" || event.target.id == "menuContent" ||event.target.id == "orgTreeContent" || $(event.target).parents("#menuContent").length>0|| $(event.target).parents("#orgTreeContent").length>0 )) {
			hideMenu();
		}
	}
	function showList(){
		window.location.href = "${root}/property/serverinfo/list/${menuid}/?isgetsession=1&page=" + <%=pages%>;
	}
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: {
				"name":{
					remote:{
						url:"${root}/property/serverinfo/nameExist",
						type:"post",
						data:{
							name:function(){
								return $("#name").val();
							}
						}
					}
				},
				"serverip":{
					remote:{
						url:"${root}/property/serverinfo/ipExist",
						type:"post",
						data:{
							name:function(){
								return $("#serverip").val();
							}
						}
					}
				}
			}
		});
		var dept ='${departmentjsonArray}';
	    var departmentNodes = eval("(" + dept + ")");
		orgTree = $.fn.zTree.init($("#orgTreeSpace"),orgSetting,departmentNodes);
		orgTree.expandAll(true);
	});
</script>
<div id="orgTreeContent" class="menuContent" style="display:none; position: absolute; background: #f0f6e4; border: 1px solid #617775; width:175px;overflow-x:scroll; height:260px; overflow-y:scroll;" >
	<ul id="orgTreeSpace" class="ztree" style="margin-top: 0px; width:260px;"></ul>
</div>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
