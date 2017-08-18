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
<div id="message" class="alert alert-success"  style="display:none">
        <button data-dismiss="alert" class="close">×</button>
        <p align="center"></p>
    </div>
<div class="page-header">
    <h1>
        监测指标
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增监测指标
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/bs/standardMonitor/doAdd/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;">
		<input type="hidden" id="isContinue" name="isContinue" value="0"/>
		<input type="hidden" id="page" name="page" value="${page }"/>
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">编号</div>
                <div class="profile-info-value">
                	<input type="text" id="code" name="code" maxlength="20"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
					<span id="checkCodeSpan" style="color: red"></span>
				</div>
				
				<div class="profile-info-name">指标类型</div>
                <div class="profile-info-value">
					<select id="typeCode" name="typeCode" style="min-width:120px; width: 350px;" class="required">
						<tags:diccache typeCode="STANDARD_MONITOR_TYPE" defaultValue="${typeCode }" />
					</select>
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">标准最小值</div>
                <div class="profile-info-value">
                	<input type="text" id="minVal" name="minVal" maxlength="9"  min="0"
						style="min-width:120px; width: 350px;" class="input-large number"/>
				</div>
				
				<div class="profile-info-name">标准最大值</div>
                <div class="profile-info-value">
                	<input type="text" id="maxVal" name="maxVal" maxlength="9"  min="0"
						style="min-width:120px; width: 350px;" class="input-large number"/>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">报警值</div>
                <div class="profile-info-value">
                	<input type="text" id="alarmVal" name="alarmVal" maxlength="9" min="0" 
						style="min-width:120px; width: 350px;" class="input-large number"/>
				</div>
				
				<div class="profile-info-name">单位</div>
				<div class="profile-info-value">
					<input type="text" id="unit" name="unit" maxlength="20"
						style="min-width:120px; width: 350px;" class="input-large"/>
            	</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">评价等级</div>
				<div class="profile-info-value">
                	<input type="text" id="remark" name="level" maxlength="50"
						style="min-width:120px; width: 350px;" class="input-large"/>
            	</div>
				
				<div class="profile-info-name">描述</div>
				<div class="profile-info-value">
                	<input type="text" id="describe" name="describe" maxlength="200"
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
                <button class="btn" type="reset" onclick="window.location.href='${root}/bs/standardMonitor/list/${menuid }/'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
    </form>
</div>
<script>
    
	var isSub = false;
	var capital =false
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        $("#inputForm").validate();
        $("#code").focus();
    });
    function continueSubmit() {
    	$("#isContinue").val("1");
    	checkForm();
    }
    function checkCapital(){
    	var min=$("#minVal").val();
    	var max = $("#maxVal").val();
    	if(parseFloat(max)>parseFloat(min)){
    		capital = true;
    	}else{
    		$("#message").show();
    	  	$("#message p").html("标准最小值必须小于报警最大值").css("color","red");
    		setTimeout('$("#message").hide("slow")', 1200);
    	}
    	return capital;
    }
    function checkForm() {
    	checkCode();
    	var flag=checkCapital();
    	var v = $("#inputForm").validate();
    	if (v.checkForm() && isSub&&flag) {
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
    			url:"${root}/bs/standardMonitor/checkCode/"+codeObj.val()+"/",
    			data:"tName=T_STANDARD_MONITOR&cName=CODE",
    			success:function(data) {
    				if (data.result == "ok") {
    					isSub = true;
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