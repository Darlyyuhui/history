<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${root}/video-js/video-js.min.css" rel="stylesheet"/>
<script src="${root}/video-js/video.min.js" type="text/javascript"></script>
</head>
<body>
<video id="my-video" class="video-js" controls preload="auto" width="800" height="600" src="${root }/${fileUrl}">
</video>
</body>
</html>