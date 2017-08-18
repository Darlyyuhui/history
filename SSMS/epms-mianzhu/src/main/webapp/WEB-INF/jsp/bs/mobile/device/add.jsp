<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${root}/js/custom.validate.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
    <p id="alert-content" align="center"></p>
</div>
<div class="page-header">
    <h1>
      移动设备
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            新增设备
        </small>
    </h1>
</div>
<form id="inputForm" class="form-horizontal" action="${root}/bs/mobile/device/doAdd/${menuid}/" method="post"
          style="margin-bottom:0;padding:0;">
    <input type="hidden" name="page" value="${page }" />
	<div class="row">
        <div class="profile-user-info profile-user-info-striped">
        	<div class="profile-info-row">
                <div class="profile-info-name" style="width: 150px;">IMEI</div>
                <div class="profile-info-value">
                	<input type="text" id="imeiNo" name="imeiNo" maxlength="25"
                        style="min-width:120px; width: 277px;" class="input-large required">
                	<span style="color: red">*</span>
                	<span id="checkCodeSpan" style="color: red"></span>
                </div>
                
                <div class="profile-info-name" style="width: 150px;">设备型号</div>
                <div class="profile-info-value">
                	<input type="text" id="mobelNo" name="mobelNo" maxlength="50"
                        style="min-width:120px; width: 277px;" class="input-large">
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name" style="width: 150px;">使用者</div>
                <div class="profile-info-value">
                	<input type="text" id="userId" name="userId" maxlength="15"
                        style="min-width:120px; width: 277px;" class="input-large">
                </div>
                <div class="profile-info-name" style="width: 150px;">使用者联系电话</div>
                <div class="profile-info-value">
                	<input type="text" id="userPhone" name="userPhone" maxlength="20"
                        style="min-width:120px; width: 277px;" class="input-large">
                </div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name" style="width: 150px;">设备状态</div>
                <div class="profile-info-value">
                	<input type="radio" name="radio_status" value="1" checked="checked">启用
                	<input type="radio" name="radio_status" value="0" style="margin-left: 20px;">停用
                	<input type="hidden" id="status" name="status" />
                </div>
                <div class="profile-info-name" style="width: 150px;"></div>
                <div class="profile-info-value">
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
	v = $("#inputForm").validate({
    	rules: {
    		userPhone: {
    			isPhone : true
    		}
    	}
    });
});
function goList() {
	window.location.href = "${root}/bs/mobile/device/list/${menuid}/?page=${page}&isgetsession=1";
}
function radioValue() {
	$("#status").val($("input[name='radio_status']:checked").val());
}
function checkForm() {
	checkCode();
	var v = $("#inputForm").validate();
	if (v.checkForm() && isCheck) {
		radioValue();
		return true;
	}else{
		v.showErrors();
		return false;
	}
}
function checkCode() {
	var codeObj = $("#imeiNo");
	if (codeObj.val() != "") {
		$.ajax({
			async:false,
			type:"post",
			url:"${root}/bs/mobile/app/checkCode/"+codeObj.val()+"/",
			data:"tName=T_MOBILE_DEVICE&cName=IMEI_NO",
			success:function(data) {
				if (data.result == "ok") {
					isCheck = true;
					$("#checkCodeSpan").empty();
				}else {
					$("#checkCodeSpan").empty().html("IMEI码重复");
				}
			}
		});
	}
}
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>