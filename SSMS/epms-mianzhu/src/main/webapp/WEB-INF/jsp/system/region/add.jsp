<%@ page language="java" pageEncoding="UTF-8"%>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="page-header">
    <h1>
        区域管理
        <small> <i class="ace-icon fa fa-angle-double-right"></i>
            增加区域信息 </small>
    </h1>
</div>
<div class="row">
		<form id="inputForm" class="form-horizontal" action="${root}/region/doAdd" method="post" style="margin-bottom:0;padding:0;">
		<input type="hidden" name="menuid" value="${menuid}"/>
		<input type="hidden" name="parentCode" value="${region.code}"/>
		<input type="hidden" name="levels" value="${region.levels}"/>

            <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                    <div class="profile-info-name" style="width:180px">父节点</div>
                    <div class="profile-info-value">${region.name}</div>
                </div>
                <div class="profile-info-row">
                    <div class="profile-info-name">编码</div>
                    <div class="profile-info-value"><input type="text" id="code" name="code" maxlength="10"
                                                           style="min-width:120px;" class="input-large "></div>
                </div>
                <div class="profile-info-row">
                    <div class="profile-info-name">名称</div>
                    <div class="profile-info-value"><input type="text" id="name" name="name" maxlength="40"
                                                           style="min-width:120px;" class="input-large "></div>
                </div>

                <div class="profile-info-row">
                    <div class="profile-info-name">描述</div>
                    <div class="profile-info-value"><textarea rows="3" name="remark" maxlength="490" style="min-width:70%;" ></textarea>
                </div>
                </div>
            </div>

		<div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="submit">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                </button>
                <button class="btn" type="reset" onclick="history.back()">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
	</form>
</div>
<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
		</script>
