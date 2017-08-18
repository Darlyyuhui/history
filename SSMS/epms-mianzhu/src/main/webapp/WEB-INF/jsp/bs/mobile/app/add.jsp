<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${root}/js/custom.validate.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
    <p id="alert-content" align="center"></p>
</div>
<div class="page-header">
    <h1>
      执法APP
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            上传APP
        </small>
    </h1>
</div>
<form id="inputForm" class="form-horizontal" action="${root}/bs/mobile/app/doAdd/${menuid}/" method="post"
          style="margin-bottom:0;padding:0;" enctype="multipart/form-data">
    <input type="hidden" name="page" value="${page }" />
	<div class="row">
        <div class="profile-user-info profile-user-info-striped">
        	<div class="profile-info-row">
                <div class="profile-info-name" style="width: 150px;">版本号</div>
                <div class="profile-info-value">
                	<input type="text" id="version" name="version" maxlength="50"
                       style="min-width:120px; width: 477px;" class="input-large required digits">
                	<span style="color: red">*</span>
                	<span id="checkCodeSpan" style="color: red"></span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name" style="width: 150px;">名称</div>
                <div class="profile-info-value">
                	<input type="text" id="name" name="name" maxlength="50"
                       style="min-width:120px; width: 477px;" class="input-large required">
                    <span style="color: red">*</span>
                </div>
            </div>
        	<div class="profile-info-row">
                <div class="profile-info-name" style="width: 150px;">备注</div>
                <div class="profile-info-value">
                	<input type="text" id="remark" name="remark" maxlength="100"
                       style="min-width:120px; width: 477px;" class="input-large">
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name" style="width: 150px;">APP上传</div>
				<div class="profile-info-value">
					<tags:fileinputs uploadFileTypes=".apk" maxFileSize="50" maxSize="1" />
				</div>
            </div>
        </div>
	</div>
	<div class="clearfix form-actions">
	    <div style="margin-left:90px;" class="col-md-10">
	        <button class="btn btn-primary" type="submit" onclick="return checkForm();">
	            <i class="ace-icon fa fa-submit bigger-110"></i> 保存
	        </button>
	        <button class="btn" type="button" onclick="goList()">
	            <i class="ace-icon fa fa-undo bigger-110"></i> 返回
	        </button>
	    </div>
	</div>
</form>
<script>
var isCheck = false;
var v;
$(document).ready(function () {
    //聚焦第一个输入框
    //为inputForm注册validate函数
	v = $("#inputForm").validate();
	$("#file1").addClass("required");
});
function goList() {
	window.location.href = "${root}/bs/mobile/app/list/${menuid}/?page=${page}&isgetsession=1";
}
function checkForm() {
	checkCode();
	if (v.checkForm() && checkFiles() && isCheck) {
		return true;
	}else{
		v.showErrors();
		return false;
	}
}
function checkCode() {
	var codeObj = $("#version");
	if (codeObj.val() != "") {
		$.ajax({
			async:false,
			type:"post",
			url:"${root}/bs/mobile/app/checkCode/"+codeObj.val()+"/",
			data:"tName=T_MOBILE_APP&cName=VERSION",
			success:function(data) {
				if (data.result == "ok") {
					isCheck = true;
					$("#checkCodeSpan").empty();
				}else {
					$("#checkCodeSpan").empty().html("版本号重复");
				}
			}
		});
	}
}
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>