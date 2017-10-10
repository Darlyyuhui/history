<%@ page language="java" pageEncoding="UTF-8" %>
<div class="page-header">
    <h1>
        添加用户
        <small><i class="ace-icon fa fa-angle-double-right"></i>添加用户信息</small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/system/user/doAdd" method="post"
          style="margin:0;padding:0;">
        <c:if test="${fn:length(param.menuid)>0}"><input type="hidden" name="menuid" value="${param.menuid}"/> </c:if>
        <c:if test="${fn:length(param.deptid)>0}"><input type="hidden" name="deptid" value="${param.deptid}"/></c:if>
        <c:if test="${fn:length(param.menuid)==0}">
            <input type="hidden" name="menuid" value="${menuid}"/>
        </c:if>
        <c:if test="${fn:length(param.deptid)==0}">
            <input type="hidden" name="deptid" value="${deptid}"/>
        </c:if>
        <input type="hidden" id="org-name"/>
        <input type="hidden" name="skin" value="no-skin"/>

        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">
                    姓 名：
                </div>
                <div class="profile-info-value">
                    <input style="width:50%; min-width:200px;" type="text" value="${policeName}"
                           placeholder="姓名" name="name" maxlength="10" class="input-large required" chinese="true"
                           specialcharfilter="true" chinese="true"><span style="color: red">*</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">
                    账 号：
                </div>
                <div class="profile-info-value">
                    <input style="width:50%; min-width:200px;" type="text" id="user-account" value="${account}" englishDigits="true" placeholder="账号" name="account" minlength="6" maxlength="20" class="input-large required"><span style="color: red">*</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name">
                    登陆密码：
                </div>
                <div class="profile-info-value">
                    <input style="width:50%; min-width:200px;" type="text" value="123456" id="user-passwd" password="true" placeholder="登陆密码" name="pwd" value="" maxlength="12" class="input-large required" /><span style="color: red">*</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name">重复一次：</div>
                <div class="profile-info-value">
                    <input style="width:50%; min-width:200px;" type="text" value="123456" name="repeatPasswd"
                           password="true" placeholder="重复一次" value="" equalTo="#user-passwd" maxlength="12"
                           class="input-large required"/><span style="color: red">*</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name">手 机 号：</div>
                <div class="profile-info-value">
                    <input style="width:50%; min-width:200px;" name="mobile" value="${policeMobile}" id="user-mobile"
                           type="text" value="" placeholder="手机号" mobilephone="true" maxlength="11"/>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">备 注：</div>
                <div class="profile-info-value">
                    <textarea rows="5" maxlength="100" name="memo" style="width:50%;min-width:200px;"></textarea> <span></span>
                </div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="submit">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存
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
        //为inputForm注册validate函数
        $("#inputForm").validate({
            rules: {
                "account": {
                    remote: {
                        url: "${root}/system/user/accountExist",
                        type: "post",
                        data: {
                            account: function () {
                                return $("#user-account").val();
                            }
                        }
                    }
                },
                pwd: {
                    rangelength: [6, 12]
                },
                repeatPasswd: {
                    rangelength: [6, 12]
                }
            }
        });
    });

    function showList() {
        window.location.href = "${root}/system/user/list/${menuid}/";
    }
</script>
