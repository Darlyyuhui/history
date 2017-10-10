<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="idElement" type="java.lang.String" required="true"%>
<%@ attribute name="nameElement" type="java.lang.String" required="true"%>
<%@ attribute name="treeType" type="java.lang.String" required="true"%>
<%@ attribute name="width" type="java.lang.String" required="false"%>
<%@ attribute name="height" type="java.lang.String" required="false"%>
<%@ attribute name="isCallback" type="java.lang.String" required="false"%>
<%-- 
idElement : 树选择后ID存放的html对象ID值
nameElement : 树选择后NAME存放的html对象ID值
treeType : 树类型：region=行政区域树，landtype=土壤类型树，repairstage=土壤修复阶段
width : 模态框宽度（非必须，默认350）
height : 模态框高度（非必须，默认500）
isCallback : 是否有回调函数，此回调函数方法名固定regionTreeCB、landtypeTreeCB、repairstageTreeCB，当isCallback=1时，调用该标签的页面必须有对应的回调函数
 --%>

<%
	if (width == null || "".equals(width)) {
		width = "350";
	}
	if (height == null || "".equals(height)) {
		height = "500";
	}
	String title = null;
	String url = null;
	if ("region".equals(treeType)) {
		title = "行政区域选择";
		url = "bs/region/showRegion/";
	} 
	else if ("landtype".equals(treeType)) {
		title = "土壤类型选择";
		url = "bs/landtype/showType/";
	} 
	else if ("repairstage".equals(treeType)) {
		title = "土壤修复阶段选择";
		url = "bs/repairstage/showType/";
	}
	else {
		title = "行政区域选择";
		url = "bs/region/showRegion/";
		treeType = "region";
	}
	request.setAttribute("width", width);
	request.setAttribute("height", height);
	request.setAttribute("title", title);
	request.setAttribute("url", url);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<link href="${root}/compnents/jbox/jBox.css" rel="stylesheet" />
<script src="${root}/compnents/jbox/jBox.min.js" type="text/javascript"></script>
<script type="text/javascript">
var jbox_${treeType}_modal;
$(function() {
	jbox_${treeType}_modal = new jBox("Modal", {
		attach: "#${nameElement}",
		trigger : "click",
		width: ${width},
		height: ${height},
		closeButton: "title",
		animation: false,
		title: "${title}",
		ajax: {
			url: "${root}/${url}",
			data : {
				"idElement" : "${idElement}",
				"nameElement" : "${nameElement}",
				"isCallback" : "${isCallback}"
			},
			reload: "strict"
		}
	});
});
</script>