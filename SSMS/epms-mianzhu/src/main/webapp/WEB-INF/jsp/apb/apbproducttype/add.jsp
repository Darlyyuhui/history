<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<c:if test="${not empty message}">
    <div id="message" class="alert alert-success">
        <button data-dismiss="alert" class="close">×</button>
        <p align="center">${message}</p>
    </div>
    <script>
        setTimeout('$("#message").hide("slow")', 1200);
    </script>
</c:if>
<div class="page-header">
    <h1>
        农产品种类
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增农产品种类
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/apb/apbproducttype/doAdd/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;">
		<input type="hidden" id="isContinue" name="isContinue" value="0"/>
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name" style="width: 180px;">上级类型名称</div>
                <div class="profile-info-value">
                ${pname }
                <input type="hidden" name="pid" value="${pid }" />
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">类型编号</div>
                <div class="profile-info-value">
                	<input type="text" id="code" name="code" maxlength="20"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
					<span id="checkCodeSpan" style="color: red"></span>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">类型名称</div>
                <div class="profile-info-value">
                	<input type="text" id="name" name="name" maxlength="50"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">种植说明</div>
                <div class="profile-info-value">
                	<input type="text" id="explain" name="explain" maxlength="50"
						style="min-width:120px; width: 350px;" class="input-large "/>
					
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">排序</div>
                <div class="profile-info-value">
                <input type="text" id="sort" name="sort" maxlength="5"
                     style="min-width:120px; width: 350px;" class="input-large digits">
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">备注</div>
                <div class="profile-info-value">
                	<input type="text" id="remark" name="remark" maxlength="200"
						style="min-width:120px; width: 350px;" class="input-large"/>
				</div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="button" onclick="checkForm()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                </button>
                <button class="btn btn-primary" type="button" onclick="continueSubmit()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存并继续
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/apb/apbproducttype/list/${menuid }/'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
    </form>
</div>
<script>
	var isSub = false;
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        $("#inputForm").validate();
        $("#name").focus();
    });
    function continueSubmit() {
    	$("#isContinue").val("1");
    	checkForm();
    }
    function checkForm() {
    	checkCode();
    	var v = $("#inputForm").validate();
    	if (v.checkForm() && isSub) {
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
    function checkCode() {
    	var codeObj = $("#code");
    	if (codeObj.val() != "") {
    		$.ajax({
    			async:false,
    			type:"post",
    			url:"${root}/apb/apbproducttype/checkCode/"+codeObj.val()+"/",
    			data:"tName=T_APB_PRODUCT_TYPE&cName=CODE",
    			success:function(data) {
    				if (data.result == "ok") {
    					isSub = true;
    					
    					$("#checkCodeSpan").empty();
    				}else {
    					$("#checkCodeSpan").empty().html(data.message);
    				}
    			}
    		});
    	}else{
    	}
    }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>