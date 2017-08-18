<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="page-header">
    <h1>
        村民责任人
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            查看村民责任人
        </small>
    </h1>
</div>
<div class="row">
        <input type="hidden" name="id" value="${info.id}"/>
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">姓名</div>
                <div class="profile-info-value">
                	${info.name }
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">所属乡镇</div>
                <div class="profile-info-value">
                	<tags:xiangxuncache keyName="TREGION_FULL_NAME" id="${info.regionId }"/>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">备注</div>
                <div class="profile-info-value">
                	${info.remark }
                </div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn" type="reset" onclick="window.location.href='${root}/bs/owner/list/${menuid }/?isgetsession=1&page=${page}'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
</div>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>