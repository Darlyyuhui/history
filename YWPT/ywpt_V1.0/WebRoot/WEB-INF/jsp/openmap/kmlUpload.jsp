<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<link rel="stylesheet" href="<%=basePath%>compnents/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=basePath%>css/transport.css">
<style type="text/css">
/*file浏览控件样式设定3*/
.file-box3 {
	position:relative;
	width:100%;
	text-align:left;
	height:28px;
	line-height:28px;
	margin-bottom:10px;
}
.txt3 {
	width:124px;
	height:18px;
	padding:2px;
	poition:absolute;
	left:0;
	top:0;
	z-index:10;
	margin:0;
}
.file3 {
	position:absolute;
	top:0;
	right:0px;
	height:24px;
	filter:alpha(opacity:0);
	-moz-opacity:0;
	opacity: 0;
	width:180px;
	margin:0;
	z-index:1110;
	border:1px solid #000;
}
.file-btn3 {
	position:absolute;
	top:0px;
	right:0px;
	z-index:0;
}
/*file浏览控件样式设定 3 结束*/
.input {
	border-left:1px solid #1f6377;
	border-top:1px solid #1f6377;
	border-right:1px solid #b3bfca;
	border-bottom:1px solid #b3bfca;
	height:18px;
	padding:3px;
}
</style>
</head>
<body style="padding:0;margin:0;">
<form id="occpuyUpload" class="mar_t10" action="<%=basePath%>occupy/gis/uploadsKml" method="post"
 enctype="multipart/form-data" style="margin-bottom:5px;">
  <div class="file-box3">
	<input type="text" class="txt3 input" id="aaaa"/>
	<input type="file" class="file3" name="file" id="mapEmergencySignsIco" onchange="document.getElementById('aaaa').value=this.value" />
	<input type="button" class="btn btn-info file-btn3" value="浏览..." style="height:28px; width:70px;"/>
  </div>
  <input class="btn btn-info" style="padding:3px 10px; width:218px;" value="上载数据" type="submit"/> 
</form>
</body>
</html>