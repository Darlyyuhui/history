<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${root}/js/custom.validate.js" type="text/javascript"></script>
<div class="page-header">
    <h1>
        事故预案登记
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增预案
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/pollute/reserve/doAdd/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;" enctype="multipart/form-data">
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name" style="min-width: 80px; width: 100px;">预案编号</div>
                <div class="profile-info-value">
                	<input type="text" id="code" name="code" maxlength="20"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
					<span id="checkCodeSpan" style="color: red"></span>
				</div>
				
				<div class="profile-info-name" style="min-width: 80px; width: 100px;">预案名称</div>
                <div class="profile-info-value">
					<input type="text" id="name" name="name" maxlength="50"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">制定时间</div>
                <div class="profile-info-value">
					<input id="madeTime" name="madeTime" type="text" 
							class="input-large required" readonly="readonly" style="min-width:120px; width: 350px;"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />
				</div>
				
				<div class="profile-info-name">事故类型</div>
                <div class="profile-info-value">
					<select id="accidentType" name="accidentType" style="min-width:120px; width: 350px;">
						<tags:diccache typeCode="POLLUTE_RESERVE_TYPE"/>
					</select>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">制定单位</div>
                <div class="profile-info-value">
					<input type="text" id="dept" name="dept" maxlength="30"
						style="min-width:120px; width: 350px;" class="input-large"/>
				</div>
				
				<div class="profile-info-name"></div>
                <div class="profile-info-value">
				</div>
            </div>
        </div>
        <div class="profile-user-info profile-user-info-striped" style="border-top-style: none;">
            <div class="profile-info-row">
                <div class="profile-info-name" style="min-width: 80px; width: 110px;">附件</div>
				<div class="profile-info-value">
					<tags:fileinputs uploadFileTypes=".pdf" maxFileSize="50" />
				</div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="button" onclick="checkForm()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/pollute/reserve/list/${menuid }/?page=${page }&isgetsession=1'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
    </form>
</div>

<script>
	var isCheck = false;
	var v;
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        v = $("#inputForm").validate();
        $("#code").focus();
    });
    function checkForm() {
    	checkCode();
    	if (v.checkForm() && isCheck) {
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
    function checkCode() {
    	var codeObj = $("#code");
    	if (codeObj.val() != "") {
    		$.ajax({
    			async:false,
    			type:"post",
    			url:"${root}/pollute/reserve/checkCode/"+codeObj.val()+"/",
    			data:"tName=T_POLLUTE_RESERVE_PLAN&cName=CODE",
    			success:function(data) {
    				if (data.result == "ok") {
    					isCheck = true;
    					$("#checkCodeSpan").empty();
    				}else {
    					$("#checkCodeSpan").empty().html(data.message);
    				}
    			}
    		});
    	}
    }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>