<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="maxSize" type="java.lang.Integer" required="false" %>
<%@ attribute name="uploadFileTypes" type="java.lang.String" required="true" %>
<%@ attribute name="maxFileSize" type="java.lang.Long" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
maxSize  最大上传数，0或者不传值为不限制数量，其他整数为可上传附件的最大数
uploadFileTypes  允许上传的类型
maxFileSize   单个文件大小，单位：M
该标签同时提供上传附件验证功能，验证的提示消息通过showMessage()方法进行显示
 --%>
<%
	if (maxSize == null || maxSize < 0) {
		maxSize = 0;
	}
	request.setAttribute("maxSize", maxSize);
%>
<script type="text/javascript">
var maxFileInputSize = ${maxSize};
var uiindex = 1;
function autoAddFile(obj) {
	if (obj.value != "") {
		if (maxFileInputSize != 0) {
			if (getFileInputCount() < maxFileInputSize) {
				isAddFileInput();
			}
		} else {
			isAddFileInput();
		}
	}
}
function getFileInputCount() {
	var count = 0;
	$(".auto_upload_file").each(function() {
		count++;
	});
	return count;
}
function isAddFileInput() {
	var emptyCount = 0;
	$(".auto_upload_file").each(function() {
		if (this.value == "") {
			emptyCount++;
		}
	});
	if (emptyCount == 0) {
		uiindex++;
		$("#upload_input_div").append("<p style=\"overflow: hidden\"><input type='file' id='file"+uiindex+"' name='file"+uiindex+"' class='auto_upload_file' onchange='autoAddFile(this)' style='float: left;'/><a href=\"javascript:cleanFile('file"+uiindex+"')\" style=\"float: left;\">清除</a></p>");
	}
}
function cleanFile(id) {
	var fileObj = $("#"+id);
	if (fileObj.clone().val() != "") {
		fileObj.after(fileObj.clone().val(""));
		var pObj = fileObj.parent();
		fileObj.remove();
		if (getFileInputCount() > 1) {
			pObj.remove();
		}
	}
}

function checkFiles() {
	var fileTypes = "${uploadFileTypes}";
	var fileSizeMb = ${maxFileSize};
	var fileSizeB = fileSizeMb  * 1024 * 1024;
	var isOK = true;
	var fileIndex = 1;
	//验证附件类型
	$(".auto_upload_file").each(function() {
		var val = this.value;
		if (val == "") {
			return true;
		}
		var typeName = val.substr(val.lastIndexOf("."), val.length).toLowerCase();
		if (fileTypes.indexOf(typeName) == -1) {
			showMessage("附件"+fileIndex+"：类型必须为所列举的一种：" + fileTypes);
			isOK = false;
			return false;
		}
		
		var fileInput = $(this)[0];
		fileInput = fileInput.files[0];
	    var fizeSize = fileInput.size;
	    if (fizeSize > fileSizeB) {
	    	showMessage("附件"+fileIndex+"：超过大小限制，当前单个附件限制为：" + fileSizeMb + "Mb");
			isOK = false;
			return false;
	    }
	    fileIndex++;
	});
	return isOK;
}
</script>
<div id="upload_input_div">
	<p style="overflow: hidden"><input  type="file" id="file1" name="file1" class="auto_upload_file" onchange="autoAddFile(this)" style="float: left;width: 185px;margin-right:10px"/><a href="javascript:cleanFile('file1')" style="float: left;">清除</a></p>
</div>
<div style="float: left; color: red;">
	<c:if test="${maxSize != 0 }">
		一次最多上传${maxSize }个附件。<br />
	</c:if>
	单个附件大小限制为：${maxFileSize } Mb<br />
	目前支持的附件类型有：${uploadFileTypes }
</div>
