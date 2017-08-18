<%@ page language="java" pageEncoding="UTF-8" %>
<div class="page-header">
    <h1>
        修改用户
        <small><i class="ace-icon fa fa-angle-double-right"></i>修改用户信息</small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/system/user/doUpdate" method="post"
          style="margin-bottom:0;padding:0;">
        <input type="hidden" name="menuid" value="${menuid}"/> <input type="hidden" name="id" value="${user.id}"/>
        <input type="hidden" name="page" value="${page}"/>

        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">所属机构：</div>
                <div class="profile-info-value"><tags:cache keyName="Department" id="${user.deptid}"></tags:cache></div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">姓 名</div>
                <div class="profile-info-value"><input style="width:50%; min-width:200px;" type="text" id="user-name"
                                                       value="${user.name}" maxlength="20" chinese="true"
                                                       specialcharfilter="true" name="name" class="input-large required"
                                                       disabled="disabled"/></div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">账 号：</div>
                <div class="profile-info-value"><input style="width:50%; min-width:200px;" type="text" id="user-account"
                                                       placeholder="账号" value="${user.account}" minlength="6"
                                                       maxlength="20" readonly="readonly"  name="account" class="input-large required"/><font
                        color="red">*</font></div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">手 机 号：</div>
                <div class="profile-info-value"><input style="width:50%; min-width:200px;" name="mobile"
                                                       id="user-mobile" type="text" value="${user.mobile}"
                                                       maxlength="11" placeholder="手机号" mobilephone="true"/></div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">备 注：</div>
                <div class="profile-info-value"><textarea rows="5" class="span8" style="min-width:60%;"
                                                          maxlength="100"
                                                          name="memo">${user.memo}</textarea><span></span></div>
            </div>
        </div>
        <div class="clear"></div>

        <div class="clearfix form-actions">
            <div class="col-md-10" style="margin-left: 100px">
                <button class="btn btn-primary" type="submit">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 修改
                </button>
                <button class="btn" type="reset" onclick="showList();">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
    </form>
</div>
<script>
    $(document).ready(function () {
        //聚焦第一个输入框
        $("#user-mobile").focus();
        //为inputForm注册validate函数
        $("#inputForm").validate(/**{
            rules: {
                "account": {
                    remote: {
                        url: "${root}/system/user/accountExist",
                        type: "post",
                        data: {
                            account: function () {
                                return $("#user-account").val();
                            },
                            oper: function () {
                                return "${user.account}";
                            }
                        }

                    }
                }
            }

        }**/);
    });

    function showList() {
        window.location.href = "${root}/system/user/list/${menuid}/";
    }
</script>