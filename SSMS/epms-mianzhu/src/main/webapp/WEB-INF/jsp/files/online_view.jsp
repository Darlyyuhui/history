<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${root }/compnents/ace/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${root }/compnents/media/jquery.media.js"></script>
<script type="text/javascript" src="${root }/compnents/media/jquery.metadata.js"></script>
<script type="text/javascript">
$(function() {
	<c:if test="${not empty fileUrl }">
	var h = $(document.body).height()-5;
	var w = $(document.body).width()-5;
	if (h < 0) {
		h = 820;
	}
	if (w < 0) {
		w = 1100;
	}
    $('a.media').media({width:w, height:h});
    </c:if> 
    if ("${fileUrl}" == "") {
    	alert("未找到该附件的可浏览文件。\n\n点击确定后，该页面将关闭！");
    	window.close();
    }
});
</script>
</head>
<body>
<c:if test="${not empty fileUrl }">
<a class="media" href="${root }/${fileUrl }"></a>
</c:if> 
</body>
</html>