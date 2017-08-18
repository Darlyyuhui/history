<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<tags:selTree idElement="regionId" nameElement="regionName" treeType="region" />
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
    <p id="alert-content" align="center"></p>
</div>
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
      采样外业任务
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            查看任务
        </small>
    </h1>
</div>
<div class="row">
        <input type="hidden" name="id" value="${info.id }" />
        <input type="hidden" name="page" value="${page }" />
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">任务编号</div>
                <div class="profile-info-value" sty>
                	${info.code }
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">任务名称</div>
                <div class="profile-info-value" sty>
                	${info.name }
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">采样方案</div>
                <div class="profile-info-value" sty>
                	${scheme.name }
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">执行单位</div>
                <div class="profile-info-value">
					${info.dept }
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">任务状态</div>
                <div class="profile-info-value">
					${info.missionStatus eq '1' ? '已执行' : '未执行' }
				</div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn" type="reset" onclick="window.location.href='${root}/land/mission/list/${menuid }/?page=${page }&isgetsession=1'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
</div>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>