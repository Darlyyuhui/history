<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<link href="${root }/compnents/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script src="${root}/compnents/ace/js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="${root }/compnents/ztree/js/jquery.ztree.core.min.js" type="text/javascript"></script>

<script type="text/javascript">
	var repairstage_zNodes = ${treeData};
	var repairstage_tree;
	$(function () {
		var repairstage_setting = {
			data: {
				simpleData: {
					enable: true
				}
			}
			,callback: {
				onClick : treeClick
			}
		};
		repairstage_tree = $.fn.zTree.init($("#repairstage-tree-rec"), repairstage_setting, repairstage_zNodes);
	    var node = repairstage_tree.getNodeByParam("name", "土壤修复阶段", null);
	    repairstage_tree.expandNode(node, true, false, false);
	});
	
	function treeClick(event, treeId, treeNode, clickFlag) {
		$("#${idElement}").val(treeNode.id);
		$("#${nameElement}").val(treeNode.name);
		jbox_repairstage_modal.close();
		<c:if test="${isCallback eq '1'}">
		repairstageTreeCB();
		</c:if>
	}
</script>
<div id="repairstage-tree-rec" class="ztree" style="margin-top: 5px; width: 100%;">
</div>