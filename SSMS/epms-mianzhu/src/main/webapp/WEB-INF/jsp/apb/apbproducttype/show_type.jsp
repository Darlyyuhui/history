<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<link href="${root }/compnents/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script src="${root}/compnents/ace/js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="${root }/compnents/ztree/js/jquery.ztree.core.min.js" type="text/javascript"></script>

<script type="text/javascript">
	var zNodes = ${treeData};
	var tree;
	$(function () {
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			}
			,callback: {
				onClick : treeClick
			}
		};
	    tree = $.fn.zTree.init($("#tree-rec"), setting, zNodes);
	    var node = tree.getNodeByParam("name", "农产品类型", null);
	    tree.expandNode(node, true, false, false);
	});
	
	function treeClick(event, treeId, treeNode, clickFlag) {
		$("#${idElement}").val(treeNode.id);
		$("#${nameElement}").val(treeNode.name);
		jbox_tree_modal.close();
	}
</script>
<div id="tree-rec" class="ztree" style="margin-top: 5px; width: 100%;">
</div>