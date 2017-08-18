<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${root}/js/custom.validate.js" type="text/javascript"></script>
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
            查看异常信息
        </small>
    </h1>
</div>
<div class="row">
        <input type="hidden" name="id" value="${info.id }" />
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">地块信息</div>
                <div class="profile-info-value">
                	${blockName }【${blockCode }】
				</div>
				
				<div class="profile-info-name">异常发生时间</div>
                <div class="profile-info-value">
                	<fmt:formatDate value='${info.errorTime }' pattern='yyyy-MM-dd hh:mm:ss'/>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">经度</div>
                <div class="profile-info-value">
					${info.longitude }
				</div>
				
				<div class="profile-info-name">纬度</div>
                <div class="profile-info-value">
					${info.latitude }
				</div>
            </div>
        </div>
        <div class="profile-user-info profile-user-info-striped" style="border-top-style: none;">
            <div class="profile-info-name">异常描述</div>
				<div class="profile-info-value">
                	${info.describe }
				</div>
        </div>
        <div class="profile-user-info profile-user-info-striped" style="border-top-style: none;">
            <div class="profile-info-row" >
                <div class="profile-info-name">现场照片</div>
				<div class="profile-info-value">
					<tags:files businessId="${info.id }" />
				</div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn" type="reset" onclick="window.location.href='${root}/land/block/error/list/${menuid }/${blockId }/'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
    </form>
</div>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>