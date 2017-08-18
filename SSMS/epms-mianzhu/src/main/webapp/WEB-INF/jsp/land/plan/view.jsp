<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${root}/js/custom.validate.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
    <p id="alert-content" align="center"></p>
</div>
<div class="page-header">
    <h1>
      采样计划
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            查看计划
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/land/sampling/plan/doUpdate/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;">
        <input type="hidden" name="id" value="${info.id }" />
        <input type="hidden" name="page" value="${page }"/>
        <div class="profile-user-info profile-user-info-striped">
        
            <div class="profile-info-row">
                <div class="profile-info-name">计划编号</div>
                <div class="profile-info-value">
                	${info.code }
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">计划名称</div>
                <div class="profile-info-value">
                	${info.name }
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">采样种类</div>
                <div class="profile-info-value">
					${info.sampleCodes }
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">实施机构</div>
                <div class="profile-info-value">
					${info.dept }
				</div>
            </div>
            
            <div class="profile-info-row">
				<div class="profile-info-name">开始日期</div>
                <div class="profile-info-value">
					<fmt:formatDate value='${info.startTime }' pattern='yyyy-MM-dd'/>
				</div>
            </div>
            
            <div class="profile-info-row">
				<div class="profile-info-name">计划说明</div>
                <div class="profile-info-value">
					${info.explain }
				</div>
            </div>
            
            <div class="profile-info-row">
				<div class="profile-info-name">是否完成</div>
                <div class="profile-info-value">
					${info.isFinish eq '1' ? '已完成' : '未完成' }
				</div>
            </div>
            
            <div class="profile-info-row">
				<div class="profile-info-name">完成日期</div>
                <div class="profile-info-value">
					<fmt:formatDate value='${info.finishTime }' pattern='yyyy-MM-dd'/>
				</div>
            </div>
            
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn" type="reset" onclick="window.location.href='${root}/land/sampling/plan/list/${menuid }/?page=${page }&isgetsession=1'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
    </form>
</div>


<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>