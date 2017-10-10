<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${root}/js/custom.validate.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
    <p id="alert-content" align="center"></p>
</div>
<div class="page-header">
    <h1>
      数据校验规则
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            修改规则
        </small>
    </h1>
</div>
<form id="inputForm" class="form-horizontal" action="${root}/check/rule/doUpdate/${menuid}/" method="post"
          style="margin-bottom:0;padding:0;">
    <input type="hidden" name="page" value="${page }" />
    <input type="hidden" name="id" value="${info.id }" />
	<div class="row">
        <div class="profile-user-info profile-user-info-striped">
        	<div class="profile-info-row">
                <div class="profile-info-name" style="width: 130px;">规则名称</div>
                <div class="profile-info-value">
                	<input type="text" id="name" name="name" maxlength="25" value="${info.name }"
                        style="min-width:120px; width: 300px;" class="input-large required">
                	<span style="color: red">*</span>
                </div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">校验类型</div>
                <div class="profile-info-value">
                	<select id="checkType" name="checkType" style="min-width:120px; width: 300px;" disabled="disabled">
                		<tags:diccache typeCode="DATA_CHECK_TYPE" defaultValue="${info.checkType }" />
                	</select>
                	<span style="color: red">*</span>
                </div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">校验对象</div>
                <div class="profile-info-value">
                	<select id="checkObj" name="checkObj" style="min-width:120px; width: 300px;" disabled="disabled">
                		<tags:diccache typeCode="DATA_CHECK_OBJ" defaultValue="${info.checkObj }" />
                	</select>
                	<span style="color: red">*</span>
                </div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">校验维度</div>
                <div class="profile-info-value">
                	<select id="checkDimension" name="checkDimension" style="min-width:120px; width: 300px;" disabled="disabled">
                		<tags:diccache typeCode="DATA_CHECK_DIMENSION" defaultValue="${info.checkDimension }" />
                	</select>
                	<span style="color: red">*</span>
                </div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">异常数据规则参数</div>
                <div class="profile-info-value">
                	<input type="text" id="outlierParameter" name="outlierParameter" maxlength="2" value="${info.outlierParameter }"
                        style="min-width:120px; width: 300px;" class="input-large required number">
                	<span style="color: red">*</span>
                </div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">异常数据描述</div>
                <div class="profile-info-value">
                	<textarea rows="3" style="min-width:120px; width: 300px;" id="outlierRemark" name="outlierRemark"
                		onkeyup="countChars(this, 'textoutlierRemark')">${info.outlierRemark }</textarea>
                	<br/>
					<span style="margin-right: 10px;">最大字符数：300</span>
					<span id="textoutlierRemark" style="color: blue;">当前字符数：0</span>
                </div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">失效数据规则参数</div>
                <div class="profile-info-value">
                	<input type="text" id="invalidParameter" name="invalidParameter" maxlength="2" value="${info.invalidParameter }"
                        style="min-width:120px; width: 300px;" class="input-large required number">
                	<span style="color: red">*</span>
                </div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">失效数据描述</div>
                <div class="profile-info-value">
                	<textarea rows="3" style="min-width:120px; width: 300px;" id="invalidRemark" name="invalidRemark"
                		onkeyup="countChars(this, 'textinvalidRemark')">${info.invalidRemark }</textarea>
                	<br/>
					<span style="margin-right: 10px;">最大字符数：300</span>
					<span id="textinvalidRemark" style="color: blue;">当前字符数：0</span>
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
var v;
$(document).ready(function () {
    //聚焦第一个输入框
    //为inputForm注册validate函数
	v = $("#inputForm").validate({
    	rules: {
    		outlierRemark: {
				textLength: 300
			},
			invalidRemark: {
				textLength: 300
			}
		}
    });
	countChars(document.getElementById("outlierRemark"), "textoutlierRemark");
	countChars(document.getElementById("invalidRemark"), "textinvalidRemark");
});
function goList() {
	window.location.href = "${root}/check/rule/list/${menuid}/?page=${page}&isgetsession=1";
}
function checkForm() {
	var v = $("#inputForm").validate();
	if (v.checkForm()) {
		return true;
	}else{
		v.showErrors();
		return false;
	}
}
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>