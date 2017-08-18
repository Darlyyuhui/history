<%@ page language="java" pageEncoding="UTF-8" %>
<script src="<c:url value='/js/jquery.form.js'/>" type="text/javascript"></script>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<div class="alert alert-block pull-top alert-success" id="alert-div" style="display:none;">
    <p id="alert-content" align="center"></p>
</div>


<div class="page-content-area">
    <div class="page-header">
        <h1>
            数据字典
            <small><i class="ace-icon fa fa-angle-double-right"></i>
                增加一个字典元素
            </small>
        </h1>
    </div>

    <form id="inputForm" class="form-horizontal" action="${root}/system/dic/doAdd" method="post" style="margin: 0; padding: 0">
        <input type="hidden" name="menuid" value="${param.menuid}"/>
        <input type="hidden" name="groupid" value="${param.groupid}"/>
        <input type="hidden" name="search_type" value="${param.type}"/>

        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">
                    名称
                </div>
                <div class="profile-info-value">
                    <input minlength="2" maxlength="20" type="text" id="dic-name"
                           placeholder="名称" name="name" class="input-large required"
                           >
					<span style="color: red;">*</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">
                    类型
                </div>
                <div class="profile-info-value">
                    <select id="dic_type" name="type" onchange="changeType()"
                            class="input-large required">
                        <option value="">
                            请选择
                        </option>
                        <c:forEach items="${dic_sec}" var="keyValue">
                            <option value="${keyValue.key}" gencode="${keyValue.gencode}"
                                ${keyValue.key==param.type?'selected':''}>
                                    ${keyValue.name}
                            </option>
                        </c:forEach>
                    </select>
                    <span style="color: red;">*</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">
                    编码
                </div>
                <div class="profile-info-value">
                    <input type="text" id="dic-code" placeholder="编码" name="code"
                           maxlength="6" class="input-large required"
                    ${fn:length(param.type) gt 0?'':'readonly=\"readonly\"'} >
                    <span style="color: red;">*</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">
                    备注
                </div>
                <div class="profile-info-value">
					<textarea rows="5" maxlength="100" id="dic-remark"
                              class="autosize-transition form-control" name="remark"></textarea>
                    <span></span>
                </div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-10" style="margin-left: 100px;">
                <input id="next_btn" class="btn btn-primary mar_r10" type="button" onclick="saveAndNext()"
                       value="保存并继续"/>
                <input id="submit_btn" class="btn btn-info mar_r10" type="submit" value="保存"/>
                <input id="cancel_btn" class="btn" type="button" value="返回" onclick="goBack()"/>
            </div>
        </div>
    </form>
</div>

<script>
    var isauto = false;
    function changeType() {
        var opt = $("#dic_type option:selected");
        var gen = ($(opt).attr("gencode"));
        //表示系统的编码是自动生成的
        if (gen == 'auto') {
            isauto = true;
            $.ajax({
                type: "POST",
                url: "${root}/system/dic/nextCode",
                data: {type: $(opt).val()},
                cache: false,
                success: function (data) {
                    $("#dic-code").val(data);
                }
            });
        }
        $("#dic-code").removeAttr("readonly")
    }

    function goBack() {
        window.location.href = "${root}/system/dic/list/${param.groupid}/${menuid}/";
    }

    function saveAndNext() {
        var haserror = v.checkForm();
        var options = {
            success: function (data) {
                showMessage("添加成功。");
                $("#dic-name").val("");
                $("#dic-code").val("");
                $("#dic-remark").val("");
                changeType();
            }
        };
        if (haserror) {
            $("#inputForm").ajaxSubmit(options);
        }else {
        	v.showErrors();
        }
    }
    var v;
    $(document).ready(function () {
        //聚焦第一个输入框
        $("#user-name").focus();
        //为inputForm注册validate函数
        v = $("#inputForm").validate({
            rules: {
                "code": {
                    remote: {
                        url: "${root}/system/dic/codeExist",
                        type: "post",
                        data: {
                            code: function () {
                                return $("#dic-code").val();
                            },
                            type: function () {
                                return $("#dic_type").val();
                            }
                        }

                    }
                }
            }

        });
    });
</script>