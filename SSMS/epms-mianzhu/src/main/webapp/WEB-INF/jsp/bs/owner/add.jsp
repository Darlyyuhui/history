<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<tags:selTree idElement="regionId" nameElement="regionName" treeType="region" />
<c:if test="${not empty message}">
    <div id="message" class="alert alert-success">
        <button data-dismiss="alert" class="close">×</button>
        <p align="center">${message}</p>
    </div>
    <script>
        setTimeout('$("#message").hide("slow")', 1200);
    </script>
</c:if>
<div class="page-header">
    <h1>
        村民责任人
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增村民责任人
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/bs/owner/doAdd/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;">
		<input type="hidden" id="isContinue" name="isContinue" value="0"/>
		<input type="hidden" id="page" name="page" value="${page }"/>
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">姓名</div>
                <div class="profile-info-value">
                	<input type="text" id="name" name="name" maxlength="20"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">所属乡镇</div>
                <div class="profile-info-value">
                	<input type="text" id="regionName" readonly="readonly"
						style="min-width:120px; width: 350px;" class="input-large required" />
					<input type="hidden" id="regionId" name="regionId" />
					<span style="color: red">*</span>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">备注</div>
                <div class="profile-info-value">
                	<input type="text" id="remark" name="remark" maxlength="150"
						style="min-width:120px; width: 350px;" class="input-large "/>
				</div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="button" onclick="checkForm()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                </button>
                <button class="btn btn-primary" type="button" onclick="continueSubmit()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存并继续
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/bs/owner/list/${menuid }/'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
    </form>
</div>
<script>
	var v;
	var isSub = false;
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        v = $("#inputForm").validate();
        $("#name").focus();
    });
    function continueSubmit() {
    	$("#isContinue").val("1");
    	checkForm();
    }

    function checkForm() {
    	if (v.checkForm()) {
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
    
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>