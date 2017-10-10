<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<link href="${root }/compnents/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script src='${root}/compnents/ace/js/jquery-1.7.2.min.js'></script>
<script src="${root }/compnents/ztree/js/jquery.ztree.core-3.5.min.js" type="text/javascript"></script>
<script type="text/javascript">
   var zNodes = ${treeNode};
    var tree;
    $(function () {
    	var setting = {
   			callback: {
   				onClick : treeClick
   			}
   		};
        tree = $.fn.zTree.init($("#tree-rec"), setting, zNodes);
    });
    function treeClick(event, treeId, treeNode, clickFlag) {
        $("#showId").html(treeNode.id);
        $("#showName").html(treeNode.name);
    }
</script>
<div>
	<div>ID：<span  id="showId"></span></div>
	<div>Name：<span  id="showName"></span></div>
    <div class="ztree_box col-xs-12">
        <ul id="tree-rec" class="ztree" style="margin-top: 5px;"></ul>
    </div>
</div>