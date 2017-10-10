<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="page-header">
    <h1>
        土壤修复阶段
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            修改阶段
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/bs/repairstage/doUpdate/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;">
		<input type="hidden" name="id" value="${id }" />
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">上级阶段</div>
                <div class="profile-info-value">
                ${pname }
                <input type="hidden" name="parentId" value="${info.pid }" />
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">阶段名称</div>
                <div class="profile-info-value">
                	<input type="text" id="name" name="name" maxlength="50" value="${info.name }"
                       style="min-width:120px;width: 500px;" class="input-large required">
					<span style="color: red">*</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">排序</div>
                <div class="profile-info-value">
	                <input type="text" id="sort" name="sort" maxlength="5" value="${info.sort }"
	                     style="min-width:120px;width: 500px;" class="input-large digits required">
	                <span style="color: red">*</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">备注</div>
                <div class="profile-info-value">
	                <input type="text" id="remark" name="remark" maxlength="100" value="${info.remark }"
	                     style="min-width:120px; width: 500px;" class="input-large">
                </div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="submit">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 修改
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/bs/repairstage/list/${menuid }/'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
    </form>
</div>
<script>
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        $("#inputForm").validate();
    });
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>