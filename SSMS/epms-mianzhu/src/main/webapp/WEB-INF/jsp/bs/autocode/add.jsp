<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
    <p id="alert-content" align="center"></p>
</div>
<style type="text/css">
.input-width {
	min-width: 120px;
	width: 280px;
}
</style>
<div class="page-header">
    <h1>
        自动编号规则
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增规则
        </small>
    </h1>
</div>
<form id="inputForm" class="form-horizontal" action="${root}/bs/autocode/doAdd/${menuid}/" method="post"
          style="margin-bottom:0;padding:0;" >
    <input type="hidden" name="page" value="${page }" />
	<div class="row">
        <div class="profile-user-info profile-user-info-striped">
        	<div class="profile-info-row">
                <div class="profile-info-name">编号类型</div>
                <div class="profile-info-value">
                	<select id="typeCode" name="typeCode" class="required input-width">
                   		<tags:diccache typeCode="AUTO_CODE_TYPE" />
                   	</select>
                	<span style="color: red">*</span>
                	<span id="checkCodeSpan" style="color: red"></span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">前区编号</div>
                <div class="profile-info-value">
                	<input type="text" id="headCode" name="headCode" maxlength="6"
                       class="input-large required input-width">
                    <span style="color: red">*</span>
                    <span style="color: blue">（只能输入大写字母）</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">日期类型</div>
                <div class="profile-info-value">
                	<input type="checkbox" name="dateTypes" value="yyyy" />年份 
                	<input type="checkbox" name="dateTypes" value="mm" style="margin-left: 10px;" />月份 
                	<input type="checkbox" name="dateTypes" value="dd" style="margin-left: 10px;"/>日期 
                    <input type="hidden" id="dateType" name="dateType" />
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">中区编号</div>
                <div class="profile-info-value">
                	<input type="text" id="centerCode" name="centerCode" maxlength="6"
                       class="input-large input-width">
                    <span style="color: blue">（只能输入大写字母）</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">尾部序列号长度</div>
                <div class="profile-info-value">
                	<select id="orderLength" name="orderLength" class="input-width">
                		<option value="4">4</option>
                		<option value="5">5</option>
                		<option value="6">6</option>
                	</select>
                </div>
            </div>
        </div>
	</div>
	<div class="clearfix form-actions">
	    <div style="margin-left:90px;" class="col-md-10">
	        <button class="btn btn-primary" type="button" onclick="return checkForm();">
	            <i class="ace-icon fa fa-submit bigger-110"></i> 保存
	        </button>
	        <button class="btn" type="button" onclick="window.location.href='${root}/bs/autocode/list/${menuid }/?page=${page }&isgetsession=1'">
	            <i class="ace-icon fa fa-undo bigger-110"></i> 返回
	        </button>
	    </div>
	</div>
</form>

<script type="text/javascript">
	var isCheck = false;
	var v;
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        v = $("#inputForm").validate();
        $("#headCode").focus();
    });
    function checkForm() {
    	if (!checkInput("headCode")) {
    		showMessage("前区编号只能输入大写字母");
    		return false;
    	}
		if (!checkInput("centerCode")) {
			showMessage("中区编号只能输入大写字母");
			return false;
    	}
    	checkCode();
    	if (v.checkForm() && isCheck) {
    		getCkbVal();
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
    function checkCode() {
    	var codeObj = $("#typeCode");
    	if (codeObj.val() != "") {
    		$.ajax({
    			async:false,
    			type:"post",
    			url:"${root}/bs/autocode/checkOnly/"+codeObj.val()+"/",
    			success:function(data) {
    				if (data.result == "ok") {
    					isCheck = true;
    					$("#checkCodeSpan").empty();
    				}else {
    					$("#checkCodeSpan").empty().html("该编号类型已添加过规则。");
    				}
    			}
    		});
    	}
    }
    function getCkbVal() {
    	var val = "";
    	$("input[name='dateTypes']:checked").each(function() {
    		val += $(this).val();
    	});
    	$("#dateType").val(val);
    }
    
    function checkInput(id) {
    	var pattern = /^[A-Z]*$/;
    	return pattern.test($("#"+id).val());
    }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>