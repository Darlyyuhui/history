<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="page-header">
    <h1>
        污染防治计划
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            查看计划
        </small>
    </h1>
</div>
<div class="row">
        <input type="hidden" name="id" value="${info.id }"/>
        <input type="hidden" name="page" value="${page }"/>
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">计划编号</div>
                <div class="profile-info-value">
                	${info.code }
				</div>
				
				<div class="profile-info-name">计划名称</div>
                <div class="profile-info-value">
					${info.name }
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">执行时间</div>
                <div class="profile-info-value">
					<fmt:formatDate value='${info.executeTime }' pattern='yyyy-MM-dd hh:mm:ss'/>
				</div>
				
				<div class="profile-info-name">是否被执行</div>
                <div class="profile-info-value">
					${info.isExecute eq '1' ? '是' : '否' }
				</div>
            </div>
        </div>
        <div class="profile-user-info profile-user-info-striped" style="border-top-style: none;">
        	<div class="profile-info-row">
                <div class="profile-info-name">计划内容</div>
                <div class="profile-info-value">
                	${info.content }
				</div>
            </div>
        	<div class="profile-info-row">
                <div class="profile-info-name">计划执行单位</div>
                <div class="profile-info-value">
					${info.dept }
				</div>
            </div>
            <div class="profile-info-row" >
                <div class="profile-info-name">附件</div>
				<div class="profile-info-value">
					<tags:files businessId="${info.id }" />
				</div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn" type="reset" onclick="window.location.href='${root}/pollute/controll/list/${menuid }/?page=${page }&isgetsession=1'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
</div>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>