<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<link href="${root }/compnents/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script src="${root}/compnents/ace/js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="${root }/compnents/ztree/js/jquery.ztree.core.min.js" type="text/javascript"></script>

<script type="text/javascript">
	var landtype_zNodes = ${treeData};
	var landtype_tree;
	$(function () {
		var landtype_setting = {
			data: {
				simpleData: {
					enable: true
				}
			}
			,callback: {
				onClick : treeClick
			}
		};
		landtype_tree = $.fn.zTree.init($("#landtype-tree-rec"), landtype_setting, landtype_zNodes);
	    var node = landtype_tree.getNodeByParam("name", "地块土壤类型", null);
	    landtype_tree.expandNode(node, true, false, false);
	});
	
	function treeClick(event, treeId, treeNode, clickFlag) {
		$("#${idElement}").val(treeNode.id);
		$("#${nameElement}").val(treeNode.name);
		jbox_landtype_modal.close();
		<c:if test="${isCallback eq '1'}">
		landtypeTreeCB();
		</c:if>
	}
</script>
<div id="landtype-tree-rec" class="ztree" style="margin-top: 5px; width: 100%;">
</div>