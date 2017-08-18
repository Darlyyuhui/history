<%@ page language="java" pageEncoding="UTF-8"%>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck.min.js'/>" type="text/javascript"></script>
<div style="height: 420px; overflow-x: hidden; overflow-y: hidden; position: static; left: -10000px;">
<div class="pull-left ztree_box" style="margin-right:0; margin-left:0; height:410px; width:330px; overflow:auto; position:fixed; left:11px;">
	<ul id="tree-rec" class="ztree tree_border2 ztree_ul"></ul>
</div>
</div>
<script>
	function treeOnclick(event, treeId, treeNode, clickFlag) {
		chooseId = treeNode.id;
		lastchooseName = treeNode.name;
		
		parent.$("#${param.orgId}").val(chooseId);
		parent.$("#${param.orgName}").val(lastchooseName);
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
	var url = "${root}/system/org/showTreeNode/${param.menuid}/";
	$.getJSON(url, function(zNodes) {
		tree = $.fn.zTree.init($("#tree-rec"), setting, zNodes);
		var node = tree.getNodeByParam("pId", 0, null);
		tree.expandNode(node, true, true, true);
	});
	parent.$(".jbox-button-panel").height(30);
</script>


