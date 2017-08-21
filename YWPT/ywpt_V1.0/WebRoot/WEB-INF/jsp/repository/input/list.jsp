<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/js/tree.js'/>" type="text/javascript"></script>

<div class="alert alert-block pull-top  alert-error" id="alert-div" style="display:none;">
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
<!-- 控制iframe中的页面返回的结果显示结束 -->
<script type="text/javascript">
	function setMesg(s){
		$("#subinfo").css("display","");
		document.getElementById("mesg").innerHTML = s;
		$("#subinfo").removeClass("alert-error").addClass("alert-success");
		setTimeout('$("#subinfo").hide("slow")', 1200);
		setTimeout("showList()",1800);
	}
	function showList(){
		window.location.href = "${root}/repository/input/list/${menuid}/";
	}
</script>
<div class="conten_box" style="height:720px;overflow:hidden;">
	<h4 class="title_intro">知识库管理-人工录入</h4>
	<div class="table_box">
    	<div class="btn-group-wrap mar_b5">
            <font style="font-weight: bolder;color:red;float:left;">目录选项:</font>
			<div class="btn-group pull-left mar_l5 mar_r5">
				<btn:authorBtn menuid="${menuid}" text="新建"><button class="btn btn-small" id="addFolder"><i class="icon-plus-sign"></i>新建</button>
				</btn:authorBtn>
				<btn:authorBtn menuid="${menuid}" text="修改"><button class="btn btn-small" id="updateFolder"><i class="icon-edit"></i>修改</button>
				</btn:authorBtn>
				<btn:authorBtn menuid="${menuid}" text="删除"><button class="btn btn-small" id="delFolder"><i class="icon-remove"></i>删除</button>
				</btn:authorBtn>
			</div>
			<div class="clear"></div>
		</div>
		<div class="mar_t5">
			<div class="pull-left ztree_box" style="height:640px; width:175px;overflow:auto; position:fixed;left:6px;">
				<ul id="tree-folder" class="ztree" style="min-height:500px;margin-top:5px; width:260px;"></ul> 
			</div>
			<iframe name="myIframe" style="min-height:650px;" frameborder="0" scrolling="no" width="100%" align="right" id="myIframe" src=""></iframe>
			<div class="clear"></div>
		</div>
		<input type="hidden" id="menuid" value="${menuid}" />
	</div>
</div>
<script type="text/javascript">
	var tree;
	var firstAsyncSuccessFlag = 0;
	var chooseId;
	$(document).ready(function() {
		ztree = new Tree("${root}/repository/catalog/showTree/", "tree-folder", false, treeClick, zTreeOnAsyncSuccess);
		ztree.showTree();
		tree = ztree.getTree();
		tree.setting.callback.onExpand=zTreeOnExpand;
		//关闭遮罩
		var modal = parent.$("#uploading_div").attr("class");
		if(modal == 'modal hide fade in'){
			parent.$("#uploading_div").modal('hide');
		}
				
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
			} catch (err) {
	
			}
		}
	}
	
	function treeClick(event, treeId, treeNode, clickFlag) {
		chooseId = treeNode.id;
		if(chooseId == '00'){
			myIframe.window.location.href = "${root}/repository/input/sublist/${menuid}/";
		}else{
			myIframe.window.location.href = "${root}/repository/input/sublist/${menuid}/?search_pid=" + chooseId;
		}
	}
	
	function zTreeOnExpand() {
		var mainheight = $("body").height() + 50;
		parent.$("#content-frame").height(mainheight);
	}
	
	$(document).ready(function(){
		$("#myIframe").attr("src","${root}/repository/input/sublist/${menuid}/");
		$("#message").css("display","none");
	});
	
	$("#addFolder").click(function(){
		var nodes = tree.getSelectedNodes();
		if (nodes.length == 0 || nodes.length > 1) {
			showMessage("请先选择一个父节点");
			return;
		}
		myIframe.window.location.href="${root}/repository/catalog/showAdd/${menuid}/" + nodes[0].id + "/?type=input";
		
	});
	
	function add(){
		return chooseId;
	}
	
	$("#delFolder").click(function(){
		var nodes = tree.getSelectedNodes();
		if (nodes.length == 0 || nodes.length > 1) {
			showMessage("请选择要删除的目录");
			return;
		} else {
			myIframe.delFolderById(nodes);
		}
	});
	
	$("#updateFolder").click(function(){
		var nodes = tree.getSelectedNodes();
		if (nodes.length == 0 || nodes.length > 1) {
			showMessage("请选择要修改的目录");
			return;
		}
		parent.$("#titlebar").css("display","none");
		myIframe.window.location.href="${root}/repository/catalog/update/"+nodes[0].id+"/${menuid}/?type=input";
	});
</script>