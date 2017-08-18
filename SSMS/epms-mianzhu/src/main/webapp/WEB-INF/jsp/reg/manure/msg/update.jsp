<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<tags:selTree idElement="regionId" nameElement="regionName" treeType="region" />
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
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
        肥料信息管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            修改信息
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/reg/manure/msg/doUpdate/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${info.id }" />
        <input type="hidden" name="page" value="${page }" />
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name" style="width: 180px;">采样信息</div>
                <div class="profile-info-value">
                	${manureReg.code }
				</div>
            </div>
            
            <div class="profile-info-row">
            	<div class="profile-info-name" style="width: 130px;">肥料种类</div>
                <div class="profile-info-value">
                	<select id="typeCode" name="typeCode" style="min-width:120px; width: 350px;" class="required">
                		<tags:diccache typeCode="SAMPLING_MANURE_TYPE" defaultValue="${info.typeCode }"/>
                	</select>
                	<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">销量（吨/年）</div>
                <div class="profile-info-value">
					<input type="text" id="sale" name="sale" maxlength="15" value="${info.sale }"
						style="min-width:120px; width: 350px;" class="input-large required number"/>
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">推荐施用量（Kg/亩）</div>
                <div class="profile-info-value">
					<input type="text" id="suggest" name="suggest" maxlength="15" value="${info.suggest }"
						style="min-width:120px; width: 350px;" class="input-large required number"/>
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">主要销售年度</div>
                <div class="profile-info-value">
					<input type="text" id="salesYear" name="salesYear" maxlength="4" value="${info.salesYear }"
						style="min-width:120px; width: 350px;" class="input-large required digits"/>
					<span style="color: red">*</span>
				</div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="button" onclick="checkForm()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/reg/manure/msg/list/${menuid }/${manureReg.id }/'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
    </form>
</div>

<script>
	var v;
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        v = $("#inputForm").validate();
        $("#sale").focus();
    });
    function checkForm() {
    	if (v.checkForm()) {
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>