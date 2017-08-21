<%@ page language="java" pageEncoding="UTF-8"%>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>
<div class="pull-left ztree_box" style="margin-right:0; margin-left:0; height:509px; width:340px; overflow:scroll; position:fixed; left:11px;">
	<ul id="tree-rec" class="ztree tree_border2 ztree_ul"></ul>
</div>
<script>
	function treeOnclick(event, treeId, treeNode, clickFlag) {
		//choosePid = treeNode.pId;
		//if ("00" != choosePid) {
			chooseId = treeNode.id;
			lastchooseName = treeNode.name;
			window.returnValue = treeNode.id + "," + treeNode.name;
			window.close();
		//}
	}

	var setting = {
		async : {
			enable : true,
			autoParam : [ "id" ]
		},
		check : {
			enable : false,
			chkStyle : "radio",
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : treeOnclick
		}
	};
	var menuid = window.dialogArguments;
	var url = "${root}/system/org/showTreeNode/" + menuid + "/";
	$.getJSON(url, function(zNodes) {
		tree = $.fn.zTree.init($("#tree-rec"), setting, zNodes);
		var node = tree.getNodeByParam("pId", 0, null);
		tree.expandNode(node, true, true, true);
	});
</script>


