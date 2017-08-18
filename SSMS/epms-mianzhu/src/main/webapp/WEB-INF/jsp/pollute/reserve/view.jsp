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
            查看预案
        </small>
    </h1>
</div>
<div class="row">
        <input type="hidden" name="id" value="${info.id }"/>
        <input type="hidden" name="page" value="${page }"/>
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">预案编号</div>
                <div class="profile-info-value">
                	${info.code }
				</div>
				
				<div class="profile-info-name">预案名称</div>
                <div class="profile-info-value">
					${info.name }
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">制定时间</div>
                <div class="profile-info-value">
					<fmt:formatDate value='${info.madeTime }' pattern='yyyy-MM-dd hh:mm:ss'/>
				</div>
				
				<div class="profile-info-name">事故类型</div>
                <div class="profile-info-value">
					<tags:xiangxuncache keyName="Dic" typeCode="POLLUTE_RESERVE_TYPE" id="${info.accidentType }"/>
				</div>
            </div>
        </div>
        <div class="profile-user-info profile-user-info-striped" style="border-top-style: none;">
        	<div class="profile-info-row">
                <div class="profile-info-name">制定单位</div>
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
                <button class="btn" type="reset" onclick="window.location.href='${root}/pollute/reserve/list/${menuid }/?page=${page }&isgetsession=1'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
</div>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>