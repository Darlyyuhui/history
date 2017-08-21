<%@ page language="java" pageEncoding="UTF-8"%>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/js/tree.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.exedit-3.5.min.js'/>" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-success" id="alert-div" style="display:none;">
	<p id="alert-content" align="center"></p>
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
		setTimeout('$("#subinfo").hide("slow")', 1200);
		setTimeout("showList()",1800);
	}
</script>
<!-- 控制iframe中的页面返回的结果显示结束 -->
<div class="conten_box">
	<h4 class="title_intro">资源管理</h4>
	<div class="table_box">
		<div class="btn-group-wrap mar_b5">
			<div class="btn-group pull-left">
				<button class="btn btn-small" onclick="addModule()">
					<i class="icon-home"></i>添加模块
				</button>
				<button class="btn btn-small" onclick="addMenu()">
					<i class="icon-plus"></i>添加功能
				</button>
				<button class="btn btn-small" onclick="addBtns()">
					<i class="icon-plus-sign"></i>快速添加增删改按钮
				</button>
				<button class="btn btn-small" onclick="addBtn()">
					<i class="icon-screenshot"></i>添加操作按钮
				</button>
				<button class="btn btn-small" onclick="update()">
					<i class="icon-edit"></i>修改
				</button>
				<button class="btn btn-small" onclick="deleteRes()">
					<i class="icon-remove"></i>删除
				</button>
			</div>
			<div class="clear"></div>
		</div>
		<div class="row-fluid">
			<div class="pull-left ztree_box" style="margin:0; height:565px; width:225px; overflow:scroll; position:fixed; left:6px;">
			  <ul id="tree-rec" class="ztree" style="margin-top: 5px;width:260px;"></ul> 
			</div>
			<div style="margin-left:230px;">
			  <iframe name="myIframe" style="min-height:580px;" frameborder="0" width="100%" id="myIframe" src=""></iframe>
			  <div class="clear"></div>
			</div>
		</div>
	</div>
</div>
<div id="tree-data" style="display:none;">${data}</div>
<script type="text/javascript">
	var zNodes = eval("(" + $('#tree-data').html() + ")");
	var tree;
	var ids;
	$(function() {
		var setting = {
			edit : {
				enable : true,
				showRemoveBtn : false,
				showRenameBtn : false,
				drag : {
					autoExpandTrigger: false,
					prev : Node.prevTree,
					next : Node.nextTree,
					inner : Node.innerTree
				}
			},
			check : {
				enable : false
			},
			data : {
				simpleData : {
					enable : true
				}
			},
			callback : {
				onClick : treeClick,
				beforeDrop : confirmDrop,
				onDrop : moveNode,
				onExpand : zTreeOnExpand
			}
		};
		tree = $.fn.zTree.init($("#tree-rec"), setting, zNodes);
		var node = tree.getNodeByParam("pId", 0, null);

		tree.expandNode(node, true, false, false);
	});

	function zTreeOnExpand() {
		var mainheight = $("body").height() + 50;
		parent.$("#content-frame").height(mainheight);
	}

	function treeClick(event, treeId, treeNode, clickFlag) {
		ids = treeNode.id;
	}

	var Node = {
		prevTree : function(treeId, treeNodes, targetNode) {
			return targetNode.parentTId == treeNodes[0].parentTId;
		},
		nextTree : function(treeId, treeNodes, targetNode) {
			return targetNode.parentTId == treeNodes[0].parentTId;
		},
		innerTree : function(treeId, treeNodes, targetNode) {
			return targetNode != null && targetNode.pId != '00';
		}
	}

	/**
	 * 拖拽节点
	 *
	 * 如果 moveType = null，表明拖拽无效
	 * moveType  指定移动到目标节点的相对位置 "inner"：成为子节点，"prev"：成为同级前一个节点，"next"：成为同级后一个节点
	 *
	 *	isCopy  拖拽节点操作是 复制 或 移动	true：复制；false：移动
	 */
	function moveNode(event, treeId, treeNodes, targetNode, moveType, isCopy) {
		if (moveType == null)
			return;
		var url = '${root}/system/res/moveNode/' + treeNodes[0].id + '/' + targetNode.id + '/?moveType=' + moveType;
		$.ajax({
			type : 'GET',
			url : url,
			dataType : "json",
			success : function(msg) {
				if (msg.result == "ok") {
					showMessage("移动成功。");

				} else {
					showMessage("移动失败。");
				}
			}
		});
	}

	function confirmDrop() {
		if (confirm("确定要移动菜单吗？")) {
			return true;
		} else {
			return false;
		}

	}

	//添加模块
	function addModule() {
		$("#myIframe").attr("src","${root}/forward/system/res/add/?menuid=${menuid}");
		//window.location.href = "${root}/forward/system/res/add/?menuid=${menuid}";
	}

	//添加操作 
	function addBtn() {
		if (tree.getSelectedNodes().length == 0) {
			showMessage("请选择父节点");
			return;
		} else {
			var node = tree.getSelectedNodes()[0];
			$("#myIframe").attr("src","${root}/forward/system/res/add_btn/?menuid=${menuid}&parentid=" + node.id + "&parentName=" + encodeURI(encodeURI(node.name)));
			//window.location.href = "${root}/forward/system/res/add_btn/?menuid=${menuid}&parentid=" + node.id + "&parentName=" + encodeURI(encodeURI(node.name));
		}
	}

	function update() {
		if (tree.getSelectedNodes().length == 0) {
			showMessage("请选择要修改的节点");
			return;
		} else {
			$("#myIframe").attr("src","${root}/system/res/update/" + ids + "/${menuid}/");
			//window.location.href = "${root}/system/res/update/" + ids + "/${menuid}/";
		}

	}

	function deleteRes() {
		if (confirm("删除后数据将无法恢复，确定要删除吗？")) {
			var url = "${root}/system/res/delete/" + ids + "/";
			$.ajax({
				type : 'delete',
				url : url,
				dataType : "json",
				success : function(msg) {
					if (msg.result == "ok") {
						$("#alert-div").removeClass("alert-error").addClass("alert-success");
						showMessage("删除成功");
						setTimeout("showList()", 1600);
					}
					if (msg.result == "hasChild") {
						$("#alert-div").removeClass("alert-success").addClass("alert-error");
						showMessage("要删除的节点存在子节点，不能直接删除！请先删除子节点后再试！");
						setTimeout("showList()", 1600);
					}
					if (msg.result == "error") {
						showMessage(msg.message);
					}
				}
			});
		}

	}

	//添加功能菜单
	function addMenu() {
		if (tree.getSelectedNodes().length == 0) {
			showMessage("请选择父节点");
			return;
		} else {
			var node = tree.getSelectedNodes()[0];
			$("#myIframe").attr("src","${root}/forward/system/res/add_menu/?menuid=${menuid}&parentid=" + node.id + "&parentName=" + encodeURI(encodeURI(node.name)));
			//window.location.href = "${root}/forward/system/res/add_menu/?menuid=${menuid}&parentid=" + node.id + "&parentName=" + encodeURI(encodeURI(node.name));
		}
	}

	//快速添加按钮
	function addBtns() {
		if (tree.getSelectedNodes().length == 0) {
			showMessage("请选择父节点");
			return;
		} else {
			var node = tree.getSelectedNodes()[0];
			var url = "${root}/system/res/doAddCommonButtons/${menuid}/" + node.id + "/";
			$.getJSON(url, function(msg) {
				if (msg.result == "ok") {
					showMessage("添加成功");
					setTimeout("showList()", 1200);
				} else {
					showMessage("添加失败");
				}
			}, "json");
		}
	}
	
	function showList(){
		window.location.href = "${root}/system/res/list/${menuid}/";
    }
</script>