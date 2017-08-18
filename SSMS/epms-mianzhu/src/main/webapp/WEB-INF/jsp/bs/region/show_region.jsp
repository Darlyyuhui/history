<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<link href="${root }/compnents/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script src="${root}/compnents/ace/js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="${root }/compnents/ztree/js/jquery.ztree.core.min.js" type="text/javascript"></script>

<script type="text/javascript">
	var region_zNodes = ${treeData};
	var region_tree;
	$(function () {
		var region_setting = {
			data: {
				simpleData: {
					enable: true
				}
			}
			,callback: {
				onClick : treeClick
			}
		};
		region_tree = $.fn.zTree.init($("#region-tree-rec"), region_setting, region_zNodes);
	    var node = region_tree.getNodeByParam("name", "绵竹市", null);
	    region_tree.expandNode(node, true, false, false);
	});
	
	function treeClick(event, treeId, treeNode, clickFlag) {
		$("#${idElement}").val(treeNode.id);
		$("#${nameElement}").val(treeNode.name);
		jbox_region_modal.close();
		<c:if test="${isCallback eq '1'}">
		regionTreeCB();
		</c:if>
	}
</script>
<div id="region-tree-rec" class="ztree" style="margin-top: 5px; width: 100%;">
</div>