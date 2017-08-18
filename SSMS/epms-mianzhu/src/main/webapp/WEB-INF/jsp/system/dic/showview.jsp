<%@ page language="java" pageEncoding="UTF-8"%>

<div class="page-content-area">
    <div class="page-header">
	    <h1>
	        数据字典
	        <small> <i class="ace-icon fa fa-angle-double-right"></i>
	            查看字典详情 </small>
	    </h1>
    </div>

    <div class="profile-user-info profile-user-info-striped">
        <div class="profile-info-row">
            <div class="profile-info-name">
                名称
            </div>
            <div class="profile-info-value">
                <span class="editable editable-click">${dic.name}</span>
            </div>
        </div>
        <div class="profile-info-row">
            <div class="profile-info-name">
                类型
            </div>
            <div class="profile-info-value">
                <span class="editable editable-click"><c:forEach items="${dic_sec}" var="keyValue">${keyValue.key eq dic.type.value?keyValue.name:''}</c:forEach></span>
            </div>
        </div>
        <div class="profile-info-row">
            <div class="profile-info-name">
                编码
            </div>
            <div class="profile-info-value">
                <span class="editable editable-click">${dic.code}</span>
            </div>
        </div>
                <div class="profile-info-row">
            <div class="profile-info-name">
                备注
            </div>
            <div class="profile-info-value">
                <span class="editable editable-click">${dic.remark}</span>
            </div>
        </div>
    </div>
    <div class="clearfix form-actions">
        <div class="col-md-offset-3 col-md-9">
            <button class="btn" type="reset" onclick="history.back()">
                <i class="ace-icon fa fa-undo bigger-110"></i> 返回
            </button>
        </div>
    </div>
</div>

