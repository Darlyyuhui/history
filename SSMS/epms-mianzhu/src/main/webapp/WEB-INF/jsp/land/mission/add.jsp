<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
    <p id="alert-content" align="center"></p>
</div>
<div class="page-header">
    <h1>
      采样外业任务
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            新增任务
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/land/mission/doAdd/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;">
        <div class="profile-user-info profile-user-info-striped">
        
            <div class="profile-info-row">
                <div class="profile-info-name">任务编号</div>
                <div class="profile-info-value" sty>
                	<input type="text" id="code" name="code" maxlength="20"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
					<span id="checkCodeSpan" style="color: red"></span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">任务名称</div>
                <div class="profile-info-value" sty>
                	<input type="text" id="name" name="name" maxlength="50"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">方案选择</div>
                <div class="profile-info-value">
                	<c:if test="${fn:length(schemeList) == 0 }">
                		<span style="color: blue;">没有未分配任务的方案。</span>
                	</c:if>
                	<c:if test="${fn:length(schemeList) gt 0 }">
                		<select id="schemeId" name="schemeId" style="min-width:120px; width: 350px;" class="required">
	                		<option value="">请选择</option>
	                		<c:forEach items="${schemeList }" var="item">
	                			<option value="${item.id }">${item.name }</option>
	                		</c:forEach>
	                	</select>
                	</c:if>
                	<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">执行单位</div>
                <div class="profile-info-value">
					<input type="text" id="dept" name="dept" maxlength="50"
						style="min-width:120px; width: 350px;" class="input-large"/>
				</div>
            </div>
            
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="button" onclick="checkForm()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/land/mission/list/${menuid }/?page=${page }&isgetsession=1'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
    </form>
</div>

<script>
	var v;
	var isCheck = false;
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        v = $("#inputForm").validate();
        $("#code").focus();
    });
    function checkForm() {
    	if (${fn:length(schemeList)} == 0) {
    		showMessage("没有可选的采样方案");
    		return false;
    	}
    	checkCode();
    	if (v.checkForm() && isCheck) {
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
    			url:"${root}/land/mission/checkCode/"+codeObj.val()+"/",
    			data:"tName=T_LAND_MISSION&cName=CODE",
    			success:function(data) {
    				if (data.result == "ok") {
    					isCheck = true;
    					$("#checkCodeSpan").empty();
    				}else {
    					$("#checkCodeSpan").empty().html(data.message);
    				}
    			}
    		});
    	}
    }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>