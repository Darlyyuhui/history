<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="businessId" type="java.lang.String" required="true" %>
<%@ attribute name="isDel" type="java.lang.String" required="false" %>
<%@ attribute name="width" type="java.lang.Integer" required="false" %>
<%@ attribute name="height" type="java.lang.Integer" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//=========================================================================
	//	附件标签
	//	businessId：业务ID			isDel：是否可删除（0=不可，1=可）
	//=========================================================================
    if (isDel == null || isDel.length() == 0) {
    	isDel = "0";
    }
	if (width == null || width == 0) {
		width = 250;
	}
	if (height == null || height == 0) {
		height = 200;
    }
    request.setAttribute("isDel", isDel);
    request.setAttribute("width", width);
    request.setAttribute("height", height);
    request.setAttribute("businessId", businessId);
    request.setAttribute("root", request.getContextPath());
%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${root}/compnents/ace/css/jquery-ui.min.css"/>
<script src="${root}/compnents/ace/js/jquery-ui.min.js"></script>
<script src="${root}/js/jquery-dialog-extend.js"></script>
<script type="text/javascript">
var myimage;
function getFiles() {
	var url = "${root}/files/viewFiles/${businessId}/";
	if ("${isDel}" == 1) {
		url = "${root}/files/getFiles/${businessId}/";
	}
	$.ajax({
		type : "get",
		url : url,
		data : "isDel=${isDel}&width=${width}&height=${height}&n="+Math.random(),
		success : function(data) {
			$("#fileDvi").html(data);
		}
	});
}
function deleteFile(id) {
	bootbox.confirm("删除后数据将无法恢复，确定要删除吗？",function(a) {
        if (!a) return;
        $.ajax({
    		type : "delete",
    		url : "${root}/files/deleteFile/"+id+"/",
    		success : function(data) {
    			if (data.result == "ok") {
    				alert("删除成功。");
    				getFiles();
    			}else if (data.result == "err") {
    				alert("删除失败。");
    			}
    		}
    	});
	});
}

function showDialogImg(imgName, imgPath) {
	$("#showImg").attr("src", imgPath);
	$("#showImg").css("width", "100%");
	$("#ea-dialog-message").removeClass('hide').dialog({
		modal : true,
		width : 900,
		height : 700,
		title : "<div class='widget-header' style='margin:0;padding:0'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i>" + imgName + "</h4></div>",
		title_html : true,
		buttons : [ {
			text : "关闭",
			"class" : "btn btn-xs",
			click : function() {
				$(this).dialog("close");
			}
		} ]
	});
	$(".ui-dialog .ui-dialog-titlebar").css("padding", "0");
}
function showWindowImg(imgName, imgPath) {
	window.open(imgPath, imgName, "location:no,menubar:no,status:no,toolbar:no,titlebar:no");
}
function onlineView(fileId) {
	window.open("${root}/files/onlineView/" + fileId + "/", "", "location:no,menubar:no,status:no,toolbar:no,titlebar:no");
}
function onlineVideo(fileId) {
	window.open("${root}/files/onlineVideo/" + fileId + "/", "", "location:no,menubar:no,status:no,toolbar:no,titlebar:no");
}
$(function() {
	getFiles();
	imgScaling();
});
function imgScaling() {
	myimage = document.getElementById("showImg");   
	if (myimage.addEventListener) {   
	    // IE9, Chrome, Safari, Opera   
	    myimage.addEventListener("mousewheel", MouseWheelHandler, false);   
	    // Firefox   
	    myimage.addEventListener("DOMMouseScroll", MouseWheelHandler, false);   
	} else {
		// IE 6/7/8   
		myimage.attachEvent("onmousewheel", MouseWheelHandler);
	}  
}

function MouseWheelHandler(e) {   
    // cross-browser wheel delta   
    var e = window.event || e; // old IE support   
    var delta = Math.max(-1, Math.min(1, (e.wheelDelta || -e.detail)));  
    myimage.style.width = Math.max(50, Math.min(2000, myimage.width + (30 * delta))) + "px"; 
//    myimage.style.height = Math.max(50, Math.min(2000, myimage.width + (30 * delta))) + "px";
    return false;   
}  
</script>
<div id="ea-dialog-message" class="hide" style="width:500px;height:300px"><img id="showImg"/></div>
<div id="fileDvi">
	<!-- 已有附件 -->
</div>