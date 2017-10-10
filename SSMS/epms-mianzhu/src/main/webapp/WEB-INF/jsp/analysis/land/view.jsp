<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="page-header">
    <h1>
       土壤分析数据登记
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            查看信息
        </small>
    </h1>
</div>
<div class="row">
        <input type="hidden" name="id" value="${info.id }" />
        <div class="profile-user-info profile-user-info-striped">
        
            <div class="profile-info-row">
                <div class="profile-info-name">分析编号</div>
                <div class="profile-info-value">
                	${info.code }
				</div>
				
				<div class="profile-info-name">采样编号</div>
                <div class="profile-info-value">
                	${regInfo.code }
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">样品名称</div>
                <div class="profile-info-value" id="regName">
                	${regInfo.name }
				</div>
				
				<div class="profile-info-name">经纬度</div>
                <div class="profile-info-value" id="regLocation">
                	<c:if test="${not empty regInfo.longitude && not empty regInfo.latitude }">
                		${regInfo.longitude },${regInfo.latitude }
                	</c:if>
                	<c:if test="${not empty regInfo.longitude && empty regInfo.latitude }">
                		${regInfo.longitude }, 
                	</c:if>
                	<c:if test="${empty regInfo.longitude && not empty regInfo.latitude }">
                		 ,${regInfo.latitude }
                	</c:if>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">采样时间</div>
                <div class="profile-info-value" id="regTime">
                 	${regInfo.samplingTimeStr }
				</div>
				
				<div class="profile-info-name">采样地点</div>
                <div class="profile-info-value" id="regRegion">
                 	${regInfo.regionId }
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">PH值</div>
                <div class="profile-info-value">
					${info.ph }
				</div>
				
				<div class="profile-info-name">镉</div>
                <div class="profile-info-value">
					${info.cadmium }
				</div>
            </div>
            
            <div class="profile-info-row">
            	<div class="profile-info-name">有效态镉</div>
                <div class="profile-info-value">
					${info.availableCadmium }
				</div>
            
                <div class="profile-info-name">样品状态</div>
                <div class="profile-info-value">
					${info.sampleStatus }
				</div>
				
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">是否超标</div>
                <div class="profile-info-value">
					${info.isOver eq '1' ? '是' : '否' }
				</div>
				
				<div class="profile-info-name">超标项</div>
                <div class="profile-info-value">
					${info.overItem }
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">分析单位</div>
                <div class="profile-info-value">
					${info.dept }
				</div>
				
				<div class="profile-info-name"></div>
                <div class="profile-info-value">
				</div>
            </div>
        </div>
        
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn" type="reset" onclick="window.location.href='${root}/analysis/land/list/${menuid }/?page=${page }&isgetsession=1'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
</div>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>