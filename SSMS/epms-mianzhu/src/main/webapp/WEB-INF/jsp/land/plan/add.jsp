<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${root}/js/custom.validate.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
    <p id="alert-content" align="center"></p>
</div>
<div class="page-header">
    <h1>
      采样计划
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            新增计划
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/land/sampling/plan/doAdd/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;">
        <div class="profile-user-info profile-user-info-striped">
            
            <div class="profile-info-row">
                <div class="profile-info-name">计划名称</div>
                <div class="profile-info-value">
                	<input type="text" id="name" name="name" maxlength="50"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">采样种类</div>
                <div class="profile-info-value">
					<c:forEach items="${samplingTypes }" var="item">
						<input type="checkbox" name="ckb_sampleCodes" value="${item.code }" ${disableMap[item.code] ? 'disabled=\"disabled\"' : '' } />${item.name }&nbsp;&nbsp;
					</c:forEach>
					<input type="hidden" id="sampleCodes" name="sampleCodes" />
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">实施机构</div>
                <div class="profile-info-value">
					<input type="text" id="dept" name="dept" maxlength="50"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
				<div class="profile-info-name">开始日期</div>
                <div class="profile-info-value">
					<input id="startTime" name="startTime" type="text" 
							class="input-large required" readonly="readonly" style="min-width:120px; width: 350px;"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
				<div class="profile-info-name">计划说明</div>
                <div class="profile-info-value">
					<textarea rows="5" style="width: 500px" id="explain" name="explain"
                	  onkeyup="countChars(this, 'textExplain')"></textarea> 
                	<br/>
					<span style="margin-right: 10px;">最大字符数：1000</span>
					<span id="textExplain" style="color: blue;">当前字符数：0</span>
				</div>
            </div>
            
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="button" onclick="checkForm()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/land/sampling/plan/list/${menuid }/?page=${page }&isgetsession=1'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
    </form>
</div>

<script>
	var v;
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        v = $("#inputForm").validate({
        	rules: {
        		explain: {
    				textLength: 1000
    			}
    		}
        });
        $("#name").focus();
    });
    function checkForm() {
    	var codes = [];
    	$("input[name='ckb_sampleCodes']:checked").each(function(){
    		codes.push($(this).val());
    	});
    	$("#sampleCodes").val(codes.toString());
    	if ($("#sampleCodes").val() == "") {
    		showMessage("请选择采样种类。");
    		return false;
    	}
    	if (v.checkForm() && isCheck) {
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>