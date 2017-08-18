<%@ page language="java" pageEncoding="UTF-8"%>
<div class="page-content-area">
    <div class="page-header">
        <h1>区域管理
            <small><i class="ace-icon fa fa-angle-double-right"></i>查看区域详情 </small>
        </h1>
    </div>
    <div class="profile-user-info profile-user-info-striped">
        <div class="profile-info-row">
            <div class="profile-info-name">编码</div>
            <div class="profile-info-value">${region.code}</div>
        </div>
        <div class="profile-info-row">
            <div class="profile-info-name">名称</div>
            <div class="profile-info-value">${region.name}</div>
        </div>
        <div class="profile-info-row">
            <div class="profile-info-name">父节点</div>
            <div class="profile-info-value">${region.parentCode}</div>
        </div>
        <div class="profile-info-row">
            <div class="profile-info-name">描述</div>
            <div class="profile-info-value">${region.remark}</div>
        </div>
    </div>
    <div class="clearfix form-actions">
        <div class="col-md-offset-3 col-md-9">
            <button class="btn" type="reset" onclick="showList()">
                <i class="ace-icon fa fa-undo bigger-110"></i>
                返回
            </button>
        </div>
    </div>
</div>
<script>
	function showList() {
		window.location.href = "${root}/region/list/${menuid}/?page=${page}";
	}
</script>
