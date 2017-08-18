<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<tags:selTree idElement="regionId" nameElement="regionName" treeType="region" />
<div class="page-header">
    <h1>
        村民责任人
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            修改村民责任人
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/bs/owner/doUpdate/${menuid }/" method="post">
    <input type="hidden" name="menuid" value="${menuid}"/>
        <input type="hidden" name="page" value="${page}"/>
        <input type="hidden" name="id" value="${info.id}"/>
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">姓名</div>
                <div class="profile-info-value">
                	<input type="text" id="name" name="name" maxlength="20" value="${info.name }"
						style="min-width:120px; width: 350px;" class="input-large required" />
					<span style="color: red">*</span>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">所属乡镇</div>
                <div class="profile-info-value">
                	<input type="text" id="regionName" readonly="readonly" value='<tags:xiangxuncache keyName="TREGION_NAME" id="${info.regionId }"/>'
						style="min-width:120px; width: 350px;" class="input-large required" onclick="showRegion('regionId','regionName')"/>
					<input type="hidden" id="regionId" name="regionId" value="${info.regionId }" />
					<span style="color: red">*</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">备注</div>
                <div class="profile-info-value">
                <input type="text" id="remark" name="remark" maxlength="150" value="${info.remark }"
                     style="min-width:120px;width: 500px;" class="input-large ">
                </div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="submit">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 修改
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/bs/owner/list/${menuid }/?isgetsession=1&page=${page}'">
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