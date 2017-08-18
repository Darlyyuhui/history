<%@ page language="java" pageEncoding="UTF-8"%>
<div class="page-header">
    <h1>
        角色管理
        <small> <i class="ace-icon fa fa-angle-double-right"></i>
            增加角色信息 </small>
    </h1>
</div>
<div class="row">
	<form id="inputForm" class="form-horizontal" action="${root}/system/role/doAdd" method="post" style="margin-bottom:0;padding:0;">
		<input type="hidden" name="menuid" value="${param.menuid}"/>
		<input type="hidden" name="deptid" value="${param.deptid}"/>
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">
                    名称
                </div>
                <div class="profile-info-value">
                    <input type="text" id="user-name" maxlength="10"
                           value="" name="name" class="input-large required"
                           chinese="true" specialcharfilter="true">
                    <span style="color: red;">*</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">
                    备注
                </div>
                <div class="profile-info-value">
                    <textarea rows="5" class="autosize-transition form-control"
                              maxlength="100" name="memo"></textarea>
                    <span></span>
                </div>
            </div>
        </div>

        <div class="clearfix form-actions">
            <div class="col-md-10" style="margin-left: 100px;">
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
			$("#role-name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				 rules: {
						"name":{
							remote:{
								url:"${root}/system/role/nameExist",
								type:"post",
								data:{
									name:function(){
										return $("#role-name").val();
									}
								}
							
							}
						}
				}
				
			});
		});
 </script>
