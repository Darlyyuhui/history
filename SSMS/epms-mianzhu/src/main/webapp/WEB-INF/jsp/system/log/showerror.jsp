<%@ page language="java" pageEncoding="UTF-8" %>
<div class="page-header">
    <h1>
        异常日志
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            查看异常日志详情
        </small>
    </h1>
</div>
<div class="row">
    <div class="profile-user-info profile-user-info-striped">
        <div class="profile-info-row">
            <div class="profile-info-name">IP地址：</div>
            <div class="profile-info-value">${log.ip}</div>
        </div>
        <div class="profile-info-row">
            <div class="profile-info-name">操作时间：</div>
            <div class="profile-info-value"><fmt:formatDate value="${log.operateTime}"
                                                            pattern="yyyy-MM-dd HH:mm:SS"/></div>
        </div>
        <div class="profile-info-row">
            <div class="profile-info-name">基本信息：</div>
            <div class="profile-info-value">${log.className}-${log.methodName}</div>
        </div>
        <div class="profile-info-row">
            <div class="profile-info-name">操作员：</div>
            <div class="profile-info-value">${log.operator}</div>
        </div>

        <div class="profile-info-row">
            <div class="profile-info-name">参数：</div>
            <div class="profile-info-value"><p style="width:400px">${log.args}</p></div>
        </div>

        <div class="profile-info-row">
            <div class="profile-info-name">异常信息：</div>
            <div class="profile-info-value"><p>${log.content}</p></div>
        </div>
    </div>
    <div class="clearfix form-actions">
        <div class="col-md-offset-1 col-md-9">
            <button class="btn" type="reset" onclick="history.back()">
                <i class="ace-icon fa fa-undo bigger-110"></i> 返回
            </button>
        </div>
    </div>
</div>