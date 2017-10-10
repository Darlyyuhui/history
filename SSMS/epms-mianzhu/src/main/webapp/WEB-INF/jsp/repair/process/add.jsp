<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${root}/js/custom.validate.js" type="text/javascript"></script>
<tags:selTree idElement="stageId" nameElement="stageName" treeType="repairstage" />
<style type="text/css">
.input-width {
	min-width: 120px;
	width: 350px;
}
</style>
<div class="page-header">
    <h1>
        土壤修复过程
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增过程
        </small>
    </h1>
</div>
<form id="inputForm" class="form-horizontal" action="${root}/repair/process/doAdd/${menuid}/" method="post"
          style="margin-bottom:0;padding:0;" enctype="multipart/form-data">
    <input type="hidden" name="page" value="${page }" />
    <input type="hidden" name="proId" value="${projectInfo.id }" />
	<div class="row">
        <div class="profile-user-info profile-user-info-striped">
        	<div class="profile-info-row">
                <div class="profile-info-name">修复阶段</div>
                <div class="profile-info-value">
                	<input type="text" id="stageName" name="stageName" readonly="readonly"
                       class="input-large required input-width" />
                   	<input type="hidden" id="stageId" name="stageId" />
                	<span style="color: red">*</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">施工月份</div>
                <div class="profile-info-value">
                	<input id="workDate" name="workDate" type="text"
                       class="input-large required input-width" readonly="readonly"
                       onfocus="WdatePicker({dateFmt:'yyyy-MM',alwaysUseStartDate:true})" />
                    <span style="color: red">*</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">施工单位</div>
                <div class="profile-info-value">
                	<input type="text" id="workDept" name="workDept" maxlength="30"
                       class="input-large required input-width" />
                    <span style="color: red">*</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">施工内容</div>
                <div class="profile-info-value">
                	<textarea rows="5" class="input-width" id="workContent" name="workContent"
                	  onkeyup="countChars(this, 'textworkContent')"></textarea> 
                	<br/>
					<span style="margin-right: 10px;">最大字符数：800</span>
					<span id="textworkContent" style="color: blue;">当前字符数：0</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">施工附件</div>
                <div class="profile-info-value">
                	<tags:fileinputs uploadFileTypes=".jpg.jpeg.bmp.png" maxFileSize="20" />
                </div>
            </div>
        </div>
	</div>
	<div class="clearfix form-actions">
	    <div style="margin-left:90px;" class="col-md-10">
	        <button class="btn btn-primary" type="button" onclick="return checkForm();">
	            <i class="ace-icon fa fa-submit bigger-110"></i> 保存
	        </button>
	        <button class="btn" type="button" onclick="window.location.href='${root}/repair/process/list/${menuid }/${projectInfo.id }/?page=${page }&isgetsession=1'">
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
        v = $("#inputForm").validate({
        	rules: {
        		workContent: {
    				textLength: 800
    			}
    		}
        });
        $("#workDept").focus();
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