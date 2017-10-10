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
            查看过程
        </small>
    </h1>
</div>
    <input type="hidden" name="page" value="${page }" />
    <input type="hidden" name="proId" value="${info.proId }" />
    <input type="hidden" name="id" value="${info.id }" />
	<div class="row">
        <div class="profile-user-info profile-user-info-striped">
        	<div class="profile-info-row">
                <div class="profile-info-name">修复阶段</div>
                <div class="profile-info-value">
                	<tags:xiangxuncache keyName="REPAIRSTAGE_IDNAME" id="${info.stageId }"/>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">施工月份</div>
                <div class="profile-info-value">
                	${info.workDate }
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">施工单位</div>
                <div class="profile-info-value">
                	${info.workDept }
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">施工内容</div>
                <div class="profile-info-value">
                	${info.workContent }
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">施工附件</div>
                <div class="profile-info-value">
                	<tags:files businessId="${info.id }" width="800" height="600" />
                </div>
            </div>
        </div>
	</div>
	<div class="clearfix form-actions">
	    <div style="margin-left:90px;" class="col-md-10">
	        <button class="btn" type="button" onclick="window.location.href='${root}/repair/process/list/${menuid }/${projectInfo.id }/?page=${page }&isgetsession=1'">
	            <i class="ace-icon fa fa-undo bigger-110"></i> 返回
	        </button>
	    </div>
	</div>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>