<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" 	type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery-ui-1.8.21.custom.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.cleditor.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.history.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<link href="${root}/compnents/bootstrap/css/uniform.default.min.css" rel="stylesheet">
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none;">
  <p id="alert-content" align="center"></p>
</div>
<div class="alert alert-block pull-top alert-success" id="alertsuc-div" style="display:none;">
  <p id="alertsuc-content" align="center"></p>
</div>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>
		<p id="alert-suc" align="center">${message}</p>
	</div>
	<script>
		setTimeout('$("#message").hide("slow")', 1200);
	</script>
</c:if>
<!-- 控制iframe中的页面返回的结果显示开始 -->
<div id="subinfo" class="alert alert-success" style="display: none;">
	<button data-dismiss="alert" class="close">×</button>
	<p id="mesg" align="center"></p>
</div>
<script type="text/javascript">
	function setMesg(s){
		$("#subinfo").css("display","");
		document.getElementById("mesg").innerHTML = s;
		$("#subinfo").removeClass("alert-error").addClass("alert-success");
		if(s.indexOf("一级道路下不允许添加设备信息") >= 0){
			$("#subinfo").removeClass("alert-success").addClass("alert-error");
		}
		setTimeout('$("#subinfo").hide("slow")', 1200);
	}
</script>
<!-- 控制iframe中的页面返回的结果显示结束 -->
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function reValues(){
        $("#dev-select_chzn>a span").html("请选择");
        $("#dev-select").val("");
        $("#selXSF_chzn>a span").html("请选择");
	    $("#selXSF").val("");
		$("#s_name").val("");
	    $("#s_code").val("");
	    $("#deviceTypeNames").val("");
	    $("#deviceTypeIds").val("");
		$("#orgNames").val("");
		$("#search_orgId").val("");
		$("#s_ip").val("");
		delParam();
		init();
    }
    
    function delParam(){
		$.getJSON("${root}/property/deviceinfo/del_earchparams",
			function(data) {
				//alert(data.result);
			});
	}
    //miaoxu 修复无法导出xls问题
    function exportXls(){
        var name = $("#s_name").val();
        var code = $("#s_code").val();
        //var companyid = $("#dev-select").val();//无
        //var devicetypecode = $("#selXSF").val();//无
        //var deviceTypeIds = $("#deviceTypeIds").val();//无
        var orgId = $("#search_orgId").val();
        var ip = $("#s_ip").val();
        //window.location.href="${root}/property/deviceinfo/exportXls/${menuid}/?search_name="+name+"&search_companyId="+companyid+"&search_code="+code+"&search_devicetypecode="+devicetypecode+"&search_deviceTypeIds="+deviceTypeIds+"&search_orgId="+orgId+"&search_ip="+ip;
        window.location.href="${root}/property/deviceinfo/exportXls/${menuid}/?search_name="+name+"&search_code="+code+"&search_orgId="+orgId+"&search_ip="+ip;
        
    }
    
    function importXls(){
    	//$("#iframoo").hide();
    	//myIframe.window.location.href="${root}/forward/property/deviceinfo/import/?menuid=${menuid}";
    	window.location.href="${root}/forward/property/deviceinfo/import/?menuid=${menuid}";
    }
    
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box" style="height:690px;overflow:hidden;margin-left:180px;">
    <div class="search-area" style="padding:6px 0 4px;">
      <form class="form-inline" action="${root}/property/deviceinfo/list/${menuid}/?searchFlag=1" method="post" 
      style="margin:0; padding:0 6px;" target="myIframe">
        <table class="table-pad-td" width="100%">
          <tr>
            <td class="search_item_td">设备名称</td>
            <td><input title="支持 设备名称 模糊查询" id="s_name" style="width:90%;" name="search_name" value="${device.name}" type="text" maxlength="20" placeholder="设备名称" /></td>
            <td class="search_item_td">设备编号</td>
            <td><input title="支持 设备编号 模糊查询" id="s_code" style="width:90%;" name="search_code" value="${device.code}" type="text" maxlength="18" placeholder="设备编号" /></td>
            
          </tr>
          <tr>
            
            <td class="search_item_td">所属部门</td>
            <td><input style="width:90%;" type="text" id="orgNames" name="search_orgNames" value="${device.orgNames}" readonly onclick="showMenu('orgNames');"/>
              <input type="hidden" id="search_orgId" name="search_orgId" value="${device.orgId}" /></td>
            <td class="search_item_td">IP 地址</td>
            <td><input title="支持 IP地址 模糊查询" id="s_ip" style="width:90%;" name="search_ip" value="${device.ip}" type="text" maxlength="15" placeholder="设备IP" /></td>
            <td></td>
            <td style="min-width:140px;"><input type="submit" class="btn btn-info" value="查询" style="height:28px;"/>
              <input onclick="reValues()" type="button" class="btn mar_l10" value="重置" style="height:28px;"/></td>
          </tr>
        </table>
      </form>
    </div>
    <div class="mar_5">
      <div id="titlebar" class="btn-group-wrap mar_b5" style="display:block;">
      	<div class="btn-group pull-left">
      		<button class="btn btn-small" id="showInMap" style="position:absolute;"><i class="icon-cog"></i>地图显示</button>
      		<button class="btn btn-small" id="showInTable" style="visibility:hidden;position:absolute;"><i class="icon-cog"></i>列表显示</button>
      	</div>
        <div id="opBtns" class="btn-group pull-right">
        <btn:authorBtn menuid="${menuid}" text="导入">
             <button id="exportXls" class="btn btn-small" onclick="importXls();"><i class="icon-download"></i>导入</button>
          </btn:authorBtn>
          <btn:authorBtn menuid="${menuid}" text="导出">
            <button id="exportXls" class="btn btn-small" onclick="exportXls();"><i class="icon-download"></i>导出</button>
          </btn:authorBtn>
          <btn:authorBtn menuid="${menuid}" text="添加">
            <button class="btn btn-small" id="addImg"><i class="icon-plus"></i>添加</button>
          </btn:authorBtn>
          <btn:authorBtn menuid="${menuid}" text="删除">
            <button class="btn btn-small" id="delImg"><i class="icon-remove"></i>删除</button>
          </btn:authorBtn>
          <div class="btn-group">
              <button class="btn btn-small" data-toggle="dropdown">更多操作<span class="caret"></span> </button>
              <ul class="dropdown-menu" tyle="width: 40px;right:20px;margin-right:20px;">
                  <li><a href="javascript:modifyById('1');"><i class="icon-cog"></i>维修</a></li>
                  <li><a href="javascript:modifyById('2');"><i class="icon-off"></i>移除</a></li>
                  <li><a href="javascript:modifyById('3');"><i class="icon-remove-circle"></i>报废</a></li>
              </ul>
          </div>
        </div>
        <div class="clear"></div>
      </div>
      <div class="row-fluid" id="iframo">
      	<div id="iframoo" class="pull-left ztree_box" style="margin:0;height:382px; width:180px; overflow:auto; position:fixed; left:0;margin-top: 190px;z-index: 10001;">
      	  <h4 class="title_intro" style="width:100%">道路信息树</h4>
		  <ul id="tree-rec" class="ztree tree_border2 ztree_ul" style="margin-top:5px;min-width:180px"></ul> 
		</div>
        <iframe name="myIframe" frameborder="0" width="100%" scrolling="no" id="myIframe" style="margin:0;padding:0;" src=""></iframe>
      </div>
      <!--/row-->
    </div>
</div>
<!-- 变更窗口开始 -->
<div class="modal hide fade" id="modifyDiv" style="width: 60%;text-align: center;z-index: 10000;">
    <button type="button" class="close" data-dismiss="modal">×</button>
    <h4 id="modifyTitle" class="xtcs_h4" style="margin: 0;"></h4>
  	<div class="modal-body">
	    <div class="conten_box">
			<form id="modifyInputForm" class="form-inline" action="${root}/property/deviceinfo/doModify" method="post" style="margin:0;">
				<input type="hidden" name="menuid" value="${menuid}" />
				<input type="hidden" id="modifyId" name="modifyId"/>
				<input type="hidden" id="modifyType" name="modifyType"/>
				<div class="mar_5">
				  <table class="table table-border-rl table-border-bot bukong-table">
					<tr>
						<td id="modifyDatetime" class="device_td_bg3"></td>
						<td>
							<input name="modifyDatetimeStr" type="text"
									class="Wdate required" readonly="readonly" style="width:160px;"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:new Date()})"/>
							<font color="red">*</font>
						</td>	
					</tr>
					<tr>
						<td class="device_td_bg3">操作人员：</td>
						<td>
							<input style="width:160px;" type="text" name="modifyOperator" class="required" maxlength="15">
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
					 	<td id="modifyReason" class="device_td_bg3"></td>
						<td colspan="3">
							<textarea rows="3" maxlength="100" style="width:75%;" class="span8 required" name="modifyReason"></textarea>
							<font color="red">*</font>
						</td>
					</tr>
				  </table>
				</div>
				<div class="btn_line">
					<button class="btn btn-info mar_r10" type="submit">保存</button>
					<input id="cancel_btn" class="btn" type="button" value="取消" onclick="showList()" />
				</div>
			</form>
		</div>
  	</div>
</div>
<!-- 地图显示/列表显示 -->
<script type="text/javascript">
	var map, list, isDataLoad;
	function iframeMapLoadCallback(mapAPI) {
		map = mapAPI;
		map.setPointImg("${root}/images/map/cross/cross_normal.png");
		if(isDataLoad)showInMap();
	}
	function showInMap() {
		if(!list) {
			// 数据查询出错了！
			map.showTipMsg("查询数据出错了！");
		}
		else if(list.length == 0) {
			// 条件查询数据为空
			map.showTipMsg("没有对应的数据！");
		}
		else {
			// 加载设备到地图上
			var addMapNum = 0;
			var mapx,mapy;
			for(var i=0; i<list.length; i++) {
				var g = map.addPoint(list[i].mapx, list[i].mapy);
				if(g) {
					addMapNum++;
					mapx = list[i].mapx;
					mapy = list[i].mapy;
				}
			}
			if(addMapNum == 0)map.showTipMsg("对应的数据坐标信息！");
			else if(addMapNum == 1)map.centerAtByPointObj(mapx, mapy);
		}
	}
	$("#showInMap").click(function(){
		// 隐藏添加、删除这类按钮
		$("#opBtns").css("visibility", "hidden");
		$("#showInMap").css("visibility", "hidden");
		$("#showInTable").css("visibility", "visible");
		$("#myIframe").attr("src","${root}/forward/map/mapTools/iframe/iframe_map/?isCenter=false");
		var params = {
			name : $("#s_name").val(),
			code : $("#s_code").val(),
			companyId : $("#dev-select").val(),
			devicetypecode : $("#selXSF").val(),
			deviceTypeNames : $("#deviceTypeNames").val(),
			orgNames : $("#orgNames").val(),
			ip : $("#s_ip").val()
		};
		$.ajax({
			url: "${root}/property/deviceinfo/jsonlist/${menuid}/",
			type: "POST",
			data: params,
			success: function(result) {
				list = result;
				isDataLoad = true;
				if(map)showInMap();
			},
			fail: function(error) {
				isDataLoad = true;
				if(map)showInMap();
			}
		});
	});
	$("#showInTable").click(function() {
		// 隐藏添加、删除这类按钮
		$("#opBtns").css("visibility", "visible");
		$("#showInMap").css("visibility", "visible");
		$("#showInTable").css("visibility", "hidden");
		$("#myIframe").attr("src","${root}/property/deviceinfo/sublist/${menuid}/");
	});
	
	
</script>
<!-- 变更窗口结束 -->
<script type="text/javascript">
	function showModifyDialog(infoId,type) {
		var typename = "维修";
		if(type == '2'){
			typename = "移除";
		}else if(type == '3'){
			typename = "报废";
		}
		$("#modifyTitle").html(typename+"操作窗口");
		$("#modifyDatetime").html(typename+"日期：");
		$("#modifyReason").html(typename+"说明：");
		
		$("#modifyInputForm").validate();
		$('#modifyDiv').modal('show');
		$("#modifyId").val(infoId);
		$("#modifyType").val(type);
	}
	function showList(){
		window.location.href = "${root}/property/deviceinfo/list/${menuid}/?${searchParams}";
    }
	
	$("#myIframe").load(function() {
		var mainheight = $(this).contents().height();
		$(this).height(mainheight);
	});
</script>
<script type="text/javascript">
	var firstAsyncSuccessFlag = 0;
	var chooseId;
	var chooseNode;
	var choosePid;
	
	$("#addImg").click(function(){
		myIframe.add(chooseId);
	});
	
	$("#delImg").click(function(){
		myIframe.remove();
	});
	
	function modifyById(type){
		myIframe.modifyById(type);
	}
	
	function treeOnlick(event, treeId, treeNode, clickFlag){
		$("#opBtns").css("visibility", "visible");
		$("#showInMap").css("visibility", "visible");
		$("#showInTable").css("visibility", "hidden");
		chooseId = treeNode.id;
       	myIframe.window.location.href="${root}/property/deviceinfo/sublist/${menuid}/?search_roadId="+chooseId+"&searchFlag=2";
    }
    
    $(document).ready(function(){
		$("#myIframe").attr("src","${root}/property/deviceinfo/sublist/${menuid}/");
	});
	
    function zTreeOnAsyncSuccess(event, treeId, msg) {  
		if (firstAsyncSuccessFlag == 0) {  
           try {  
                  //调用默认展开第一个结点  
                  var nodes = tree.getNodes();  
                  tree.expandNode(nodes[0], true);  
               
                  var childNodes = tree.transformToArray(nodes[0]);  
                  tree.expandNode(childNodes[1], true);  
                  var childNodes1 = tree.transformToArray(childNodes[1]);  
                  tree.checkNode(childNodes1[1], true, true);  
                  firstAsyncSuccessFlag = 1;  
                  
                  //让单击过的节点选中
                  chooseId ='${device.roadId}';
                  chooseNode = tree.getNodeByParam("id", chooseId, null);
  					  tree.selectNode(chooseNode);
            } catch (err) {  
               
            }  
		}
	} 
   
	$(document).ready(function(){
	   	var setting = {
		    async: {
					enable: true,
					url:"${root}/system/road/showTree/",
					autoParam:["id"]
					},
			check: {
					enable: false,
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback : {
				onClick : treeOnlick,
				onAsyncSuccess: zTreeOnAsyncSuccess
			}
		};
		tree= $.fn.zTree.init($("#tree-rec"), setting);
		$("#user-content").width($('body').width())
		$("#alert-div").hide();
	});
	
</script>
<SCRIPT type="text/javascript">
		
	var orgTree, deviceTypeTree;
	
	// 设备类型 类型树
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


       // 设备类型 树的相关操作函数
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
		var hiddenValue = $("#search_orgId");
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
		$("body").bind("mousedown",onBodyDown);
	}
	function hideMenu() {
		$("#orgTreeContent").fadeOut("fast");
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "deviceTypeNames"|| event.target.id == "orgNames" || event.target.id == "menuContent" ||event.target.id == "orgTreeContent" || $(event.target).parents("#menuContent").length>0|| $(event.target).parents("#orgTreeContent").length>0 )) {
			hideMenu();
		}
	}
	
	
	// 设备类型 
	$(document).ready(function(){
	    init();
	});
	
	function init(){
		var dt ='${deviceTypejsonArray}';
	    var deviceTypeNodes = eval("(" + dt + ")");
		deviceTypeTree = $.fn.zTree.init($("#dtTreeSpace"),deviceTypeSetting,deviceTypeNodes);
		deviceTypeTree.setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };
		deviceTypeTree.expandAll(true);
		
		var dept ='${departmentjsonArray}';
	    var departmentNodes = eval("(" + dept + ")");
		orgTree = $.fn.zTree.init($("#orgTreeSpace"),orgSetting,departmentNodes);
		orgTree.expandAll(true);
	}
		
</SCRIPT>

<div id="menuContent" class="menuContent" style="display:none; position: absolute; background: #f0f6e4; border: 1px solid #617775; width:175px;overflow-x:scroll; height:260px; overflow-y:scroll;" >
	<ul id="dtTreeSpace" class="ztree" style="margin-top:0px; width:260px;"></ul>
</div>

<div id="orgTreeContent" class="menuContent" style="display:none; position: absolute; background: #f0f6e4; border: 1px solid #617775; width:175px;overflow-x:scroll; height:260px; overflow-y:scroll;" >
	<ul id="orgTreeSpace" class="ztree" style="margin-top: 0px; width:260px;"></ul>
</div>	
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
	
