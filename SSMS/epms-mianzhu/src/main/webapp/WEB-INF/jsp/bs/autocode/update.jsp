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
            修改规则
        </small>
    </h1>
</div>
<form id="inputForm" class="form-horizontal" action="${root}/bs/autocode/doUpdate/${menuid}/" method="post"
          style="margin-bottom:0;padding:0;" >
    <input type="hidden" name="page" value="${page }" />
    <input type="hidden" name="typeCode" value="${info.typeCode }" />
	<div class="row">
        <div class="profile-user-info profile-user-info-striped">
        	<div class="profile-info-row">
                <div class="profile-info-name">编号类型</div>
                <div class="profile-info-value">
                	<tags:xiangxuncache keyName="Dic" typeCode="AUTO_CODE_TYPE" id="${info.typeCode }"/>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">前区编号</div>
                <div class="profile-info-value">
                	<input type="text" id="headCode" name="headCode" maxlength="6" value="${info.headCode }"
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
                    <input type="hidden" id="dateType" name="dateType" value="${info.dateType }" />
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">中区编号</div>
                <div class="profile-info-value">
                	<input type="text" id="centerCode" name="centerCode" maxlength="6" value="${info.centerCode }"
                       class="input-large input-width">
                    <span style="color: blue">（只能输入大写字母）</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">尾部序列号长度</div>
                <div class="profile-info-value">
                	<select id="orderLength" name="orderLength" class="input-width">
                		<option value="4" ${info.orderLength eq '4' ? 'selected' : '' }>4</option>
                		<option value="5" ${info.orderLength eq '5' ? 'selected' : '' }>5</option>
                		<option value="6" ${info.orderLength eq '6' ? 'selected' : '' }>6</option>
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
	var v;
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        v = $("#inputForm").validate();
        $("#headCode").focus();
        initChkBox();
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
    	if (v.checkForm()) {
    		getCkbVal();
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
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
    
    function initChkBox() {
    	var dt = "${info.dateType}";
    	$("input[name='dateTypes']").each(function() {
    		var val = $(this).val();
    		if (dt.indexOf(val) > -1) {
    			$(this).attr("checked", "checked");
    		}
    	}); 
    }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>