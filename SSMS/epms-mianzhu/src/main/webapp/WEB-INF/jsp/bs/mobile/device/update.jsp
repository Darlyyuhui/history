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
            修改设备
        </small>
    </h1>
</div>
<form id="inputForm" class="form-horizontal" action="${root}/bs/mobile/device/doUpdate/${menuid}/" method="post"
          style="margin-bottom:0;padding:0;">
    <input type="hidden" name="page" value="${page }" />
    <input type="hidden" name="id" value="${info.id }" />
	<div class="row">
        <div class="profile-user-info profile-user-info-striped">
        	<div class="profile-info-row">
                <div class="profile-info-name" style="width: 150px;">IMEI</div>
                <div class="profile-info-value">
                	${info.imeiNo }
                </div>
                
                <div class="profile-info-name" style="width: 150px;">设备型号</div>
                <div class="profile-info-value">
                	<input type="text" id="mobelNo" name="mobelNo" maxlength="50" value="${info.mobelNo }"
                        style="min-width:120px; width: 277px;" class="input-large">
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name" style="width: 150px;">使用者</div>
                <div class="profile-info-value">
                	<input type="text" id="userId" name="userId" maxlength="15" value="${info.userId }"
                        style="min-width:120px; width: 277px;" class="input-large">
                </div>
                <div class="profile-info-name" style="width: 150px;">使用者联系电话</div>
                <div class="profile-info-value">
                	<input type="text" id="userPhone" name="userPhone" maxlength="20" value="${info.userPhone }"
                        style="min-width:120px; width: 277px;" class="input-large">
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name" style="width: 150px;">设备状态</div>
                <div class="profile-info-value">
                	<input type="radio" name="radio_status" value="1" ${info.status eq '1' ? 'checked' : ''}>启用
                	<input type="radio" name="radio_status" value="0" ${info.status eq '0' ? 'checked' : ''} style="margin-left: 20px;">停用
                	<input type="hidden" id="status" name="status" value="${info.status }" />
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
$(document).ready(function () {
    //聚焦第一个输入框
    //为inputForm注册validate函数
	$("#inputForm").validate({
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
	var v = $("#inputForm").validate();
	if (v.checkForm()) {
		radioValue();
		return true;
	}else{
		v.showErrors();
		return false;
	}
}
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>