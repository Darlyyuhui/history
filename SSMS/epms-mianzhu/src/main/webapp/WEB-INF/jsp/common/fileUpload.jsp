<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <link rel="stylesheet" href="${root}/compnents/ace/css/bootstrap.min.css" />
	<style type="text/css">
		*{
			padding:0;
			margin:0;
		}
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
			width:114px;
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
			left:0px;
			height:24px;
			filter:alpha(opacity:0);
			-moz-opacity:0;
			opacity: 0;
			width:180px;
			margin:0;
			z-index:1110;
			border:1px solid #000;
			cursor:default;
		}
		.file-btn3 {
			position:absolute;
			top:0px;
			right:0px;
			left : 140px;
			z-index:0;
		}
		/*file浏览控件样式设定 3 结束*/
	</style>
    <script type="text/javascript">
        function onBodyLoad() {
            document.getElementById("modulName").value='${param.modulName}';
        }
    </script>
</head>
<body style="background:none;" onload="onBodyLoad()">
	<form id="fileUploadForm" action="${root}/file/upload" method="post" enctype="multipart/form-data">
        <input type="hidden" name="modulName" id="modulName">
		<div class="file-box3">
            <input type="hidden" name="uploadname" value="abcdefg">
		    <input type="file" style="float:left;" name="abcdefg" id="filename" />
            <font color="red">*目前支持的附件类型：.txt.doc.docx.xls.xlsx.ppt.pptx.pdf</font>
		</div>
	</form>
</body>

</html>