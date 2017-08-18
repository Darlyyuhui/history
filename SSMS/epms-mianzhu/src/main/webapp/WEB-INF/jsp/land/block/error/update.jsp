<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${root}/js/custom.validate.js" type="text/javascript"></script>
<script src="${root}/js/business.validate.js" type="text/javascript"></script>
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
        土壤地块异常
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            修改异常信息
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/land/block/error/doUpdate/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;" enctype="multipart/form-data">
          <input type="hidden" name="id" value="${info.id }" />
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name" style="min-width: 110px;width: 110px;">地块信息</div>
                <div class="profile-info-value" style="width: 600px;">
                	${blockName }【${blockCode }】
				</div>
				
				<div class="profile-info-name" style="min-width: 110px;width: 110px;">异常发生时间</div>
                <div class="profile-info-value">
                	<input id="errorTime" name="errorTime" type="text" value="<fmt:formatDate value='${info.errorTime }' pattern='yyyy-MM-dd hh:mm:ss'/>"
							class="input-large required" readonly="readonly" style="min-width:120px; width: 200px;"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name" style="min-width: 110px;width: 110px;">经度</div>
                <div class="profile-info-value">
					<input type="text" id="longitude" name="longitude" maxlength="12" value="${info.longitude }"
						style="min-width:120px; width: 200px;" class="input-large number"/>
				</div>
				
				<div class="profile-info-name" style="min-width: 110px;width: 110px;">纬度</div>
                <div class="profile-info-value">
					<input type="text" id="latitude" name="latitude" maxlength="12" value="${info.latitude }"
						style="min-width:120px; width: 200px;" class="input-large number"/>
				</div>
            </div>
        </div>
        <div class="profile-user-info profile-user-info-striped" style="border-top-style: none;">
            <div class="profile-info-name" style="min-width: 110px;width: 110px;">异常描述</div>
				<div class="profile-info-value">
                	<textarea rows="5" style="min-width: 110px;width: 910px;" id="describe" name="describe"
                	  onkeyup="countChars(this, 'textDescribe')">${info.describe }</textarea> 
                	<br/>
					<span style="margin-right: 10px;">最大字符数：400</span>
					<span id="textDescribe" style="color: blue;">当前字符数：0</span>
				</div>
        </div>
        <div class="profile-user-info profile-user-info-striped" style="border-top-style: none;">
            <div class="profile-info-row" >
                <div class="profile-info-name" style="min-width: 110px;width: 110px;">现场照片</div>
				<div class="profile-info-value">
					<tags:files businessId="${info.id }" isDel="1" />
					<tags:fileinputs uploadFileTypes=".jpg.jpeg.png.bmp" maxFileSize="10" />
				</div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="button" onclick="checkForm()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/land/block/error/list/${menuid }/${blockId }/'">
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
        v = $("#inputForm").validate({
        	rules: {
        		describe: {
    				textLength: 400
    			},
    			longitude: {
        			isCoordinate: true
    			},
    			latitude: {
        			isCoordinate: true
    			}
    		}
        });
        $("#name").focus();
        countChars(document.getElementById("describe"), "textDescribe");
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