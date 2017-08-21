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
<div class="alert alert-block pull-top alert-success" id="alertsuc-div" style="display:none;">
  <p id="alertsuc-content" align="center"></p>
</div>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>
		<p align="center">${message}</p>
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
		if(s.indexOf("一级道路下不允许添加视频监控") >= 0){
			$("#subinfo").removeClass("alert-success").addClass("alert-error");
		}
		setTimeout('$("#subinfo").hide("slow")', 1200);
	}
</script>
<!-- 控制iframe中的页面返回的结果显示结束 -->
<!-- 重置查询输入框开始 -->
<script type="text/javascript">

    function reValues(){
        $("#companyid_chzn>a span").html("请选择");
        $("#companyid").val("");
        $("#videotypeCode_chzn>a span").html("请选择");
        $("#videotypeCode").val("");
        $("#directionCode_chzn>a span").html("请选择");
        $("#directionCode").val("");
	    $("#name").val("");
		$("#orgNames").val("");
		$("#search_orgId").val("");
		delParam();
		init();
	}
	
	function delParam(){
		$.getJSON("${root}/property/videoinfo/del_earchparams",
			function(data) {
				//alert(data.result);
			});
	}
	
	function exportXls(){
	    var companyid = $("#companyid").val();
	    var name = $("#name").val();
	    var directionCode = $("#directionCode").val();
	    var videotypeCode = $("#videotypeCode").val()
	    var orgId = $("#search_orgId").val();
        window.location.href="${root}/property/videoinfo/exportXls/?search_name="+name+"&search_companyid="+companyid+"&search_videotypeCode="+videotypeCode+"&search_directionCode="+directionCode+"&search_orgId="+orgId+"&menuid=${menuid}";
    }
    
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box" style="height:690px;overflow:hidden;margin-left:180px;">
	<div class="search-area" style="padding:6px 0 4px;">
		<form class="form-inline" action="${root}/property/videoinfo/list/${menuid}/?searchFlag=1" method="post" style="margin:0; padding:0 6px;" target="myIframe">
			<table class="table-pad-td" width="100%">
			  <tr> 
			    <td>监控名称</td>
			    <td>建设厂家</td>
			    <td>监控类型</td>
			    <td>监控方向</td>
			    <td>所属部门</td>
			    <td></td>
			  </tr>
			  <tr> 
			    <td><input title="支持 监控名称 模糊查询" style="width:90%;" id="name" name="search_name" value="${videoInfo.name}" type="text" placeholder="监控名称" /></td>
			    <td>
			      <select id="companyid" name="search_companyid" style="width:96%;">
					<option value="">请选择</option>
					<c:forEach items="${companyList}" var="company">
					   <option value="${company.id}" ${company.id==videoInfo.companyid?'selected':''}>${company.name}</option>
					</c:forEach>
				  </select>
				</td>
			    <td><select id="videotypeCode" name="search_videotypeCode" style="width:96%;">
					<option value="">请选择</option>
					<c:forEach items="${videoTypeList}" var="videoType">
					   <option value="${videoType.code}" ${videoType.code==videoInfo.videotypeCode?'selected':''}>${videoType.name}</option>
					</c:forEach>
				  </select>
				</td>
			    <td><select id="directionCode" name="search_directionCode" style="width:96%;">
					<option value="">请选择</option>
					<c:forEach items="${directList}" var="direct">
					   <option value="${direct.code}" ${direct.code==videoInfo.directionCode?'selected':''}>${direct.name}</option>
					</c:forEach>
				    </select></td>
			    <td><input style="width:90%;max-width:200px;" type="text" id="orgNames" value="${videoInfo.orgNames}" name="search_orgNames"  readonly onclick="showMenu('orgNames');"   />
				    <input type="hidden" id="search_orgId" name="search_orgId" value="${videoInfo.orgId}"/></td>
			    <td style="min-widht:140px;">
			      <input type="submit" class="btn btn-info mar_l10" value="查询" style="height:28px;"/>
			      <input onclick="reValues()" type="button" class="btn mar_l10" value="重置" style="height:28px;"/>
			    </td>
			  </tr>
			</table>
		</form>
	</div>	
	<div class="mar_5">
       <div id="titlebar" class="btn-group-wrap mar_b5" style="display: block;">
		   <div class="btn-group pull-right">
				<btn:authorBtn menuid="${menuid}" text="添加">
					<button class="btn btn-small" id="addVideo"><i class="icon-plus"></i>添加</button>
				</btn:authorBtn>
				<btn:authorBtn menuid="${menuid}" text="删除">
					<button class="btn btn-small" id="delVideo"><i class="icon-remove"></i>删除</button>
				</btn:authorBtn>
				<btn:authorBtn menuid="${menuid}" text="导出">
					<button id="exportXls" class="btn btn-small" onclick="exportXls();"><i class="icon-download"></i>导出</button>
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
       <div class="row-fluid">
       		<div class="ztree_box pull-left" style="margin:0;height:382px; width:180px; overflow:hidden; position:fixed; left:0;margin-top: 198px;z-index: 10001;">
			    <h4 class="title_intro">道路信息树</h4>
			    <ul id="tree-rec" class="ztree tree_border2 ztree_ul" style="min-width:180px;"></ul>
			  </div>
        	<iframe name="myIframe" frameborder="0" width="100%" id="myIframe" scrolling="no" src=""></iframe>
      </div>
	</div>
</div>
<!-- 变更窗口开始 -->
<div class="modal hide fade" id="modifyDiv" style="width: 60%;text-align: center;">
    <button type="button" class="close" data-dismiss="modal">×</button>
    <h4 id="modifyTitle" class="xtcs_h4" style="margin: 0;"></h4>
  	<div class="modal-body">
	    <div class="conten_box">
			<form id="modifyInputForm" class="form-inline" action="${root}/property/videoinfo/doModify" method="post" style="margin:0;">
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
		window.location.href = "${root}/property/videoinfo/list/${menuid}/?${searchParams}";
    }
</script>
<script type="text/javascript">
	$("#myIframe").load(function(){
		var IframeH = $(this).contents().height();
		$(this).height(IframeH);
	});
	
	var firstAsyncSuccessFlag = 0;
	var chooseId;
	var chooseNode;
	
	$("#addVideo").click(function(){
		myIframe.add(chooseId);
	});
	
	$("#delVideo").click(function(){
		myIframe.remove();
	});
	
	function modifyById(type){
		myIframe.modifyById(type);
	}
	
	function treeOnlick(event, treeId, treeNode, clickFlag){
       	chooseId = treeNode.id;
       	myIframe.window.location.href="${root}/property/videoinfo/sublist/${menuid}/?search_roadinfoId="+chooseId+"&searchFlag=2";
    }
    
    $(document).ready(function(){
		$("#myIframe").attr("src","${root}/property/videoinfo/sublist/${menuid}/");
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
	                  chooseId ='${videoInfo.roadinfoId}';
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
				chkStyle: "radio",
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
		
	var orgTree;
	// 所属部门 组织机构树
	var orgSetting = {
		
		view: {
			  dblClickExpand: false
		},
		data: {
			  simpleData: {
				 enable: true
			  }
		},
		callback: {
			  //onClick: onRadioClick,
			  //onCheck: onRadioCheck
			  onClick: onClick
		}
	};
		
   
	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("orgTreeSpace"),
		nodes = zTree.getSelectedNodes(),
		v = "";
		h = "";
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
			h += nodes[i].id + ",";
		}
		
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		var orgObj = $("#orgNames");
		orgObj.attr("value", v);
		
		if (h.length > 0 ) h = h.substring(0, h.length-1);
		var hiddenValue = $("#search_orgId");
	    hiddenValue.attr("value", h);
	    
	}
	
	//组织机构树相关函数
	//function onRadioClick(e, treeId, treeNode) {
	//	var zTree = $.fn.zTree.getZTreeObj("orgTreeSpace");
	//	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	//	return false;
	//}
	
	
	//function onRadioCheck(e, treeId, treeNode) {
	//	var zTree = $.fn.zTree.getZTreeObj("orgTreeSpace"),
	//	nodes = zTree.getCheckedNodes(true),
	//	v = "";
	//	var h = "";
	//	for (var i=0, l=nodes.length; i<l; i++) {
	//		v += nodes[i].name + ",";
	//		h += nodes[i].id + ",";
	//	}
	//	if (v.length > 0 ) v = v.substring(0, v.length-1);
	//	var cityObj = $("#orgNames");
	//	cityObj.attr("value", v);
	//	
	//	if (h.length > 0 ) h = h.substring(0, h.length-1);
	//	var hiddenValue = $("#search_orgId");
	//	hiddenValue.attr("value", h);
	//}
	
	
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
		if (!(event.target.id == "menuBtn" ||event.target.id == "orgTreeContent" || event.target.id == "deviceTypeNames"|| event.target.id == "orgNames" || event.target.id == "menuContent" || $(event.target).parents("#orgTreeContent").length>0)) {
			hideMenu();
		}
	}
	
	// 设备类型 
	$(document).ready(function(){
		init();
	});
	
	function init(){
		var dept ='${departmentjsonArray}';
	    var departmentNodes = eval("(" + dept + ")");
		orgTree = $.fn.zTree.init($("#orgTreeSpace"),orgSetting,departmentNodes);
		orgTree.expandAll(true);
	}
		
</SCRIPT>

<div id="orgTreeContent" class="menuContent" style="display:none; position: absolute; margin-top: 0px;border: 1px solid #617775;background: #f0f6e4; width:180px;height:260px;overflow-y:scroll;overflow-x:auto;" >
	<ul id="orgTreeSpace" class="ztree" style="width:220px;"></ul>
</div>		
<script src="${root}/compnents/bootstrap/js/jquery-ui-1.8.21.custom.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.cleditor.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.history.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>	
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>