<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
    <p id="alert-content" align="center"></p>
</div>
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
        大气沉降采样登记
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增信息
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/reg/air/doAdd/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;" enctype="multipart/form-data">
        <div class="profile-user-info profile-user-info-striped">
        
            <div class="profile-info-row">
                <div class="profile-info-name">样品编号</div>
                <div class="profile-info-value">
                	<input type="text" id="code" name="code" maxlength="20"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
					<span id="checkCodeSpan" style="color: red"></span>
				</div>
				
				<div class="profile-info-name">采样任务</div>
                <div class="profile-info-value">
                	<select id="missionId" name="missionId" style="min-width:120px; width: 350px;" class="required">
                		<option value="">请选择</option>
                		<c:forEach items="${missions }" var="m">
                			<option value="${m.id }">${m.name }</option>
                		</c:forEach>
                	</select>
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">采样点位</div>
                <div class="profile-info-value">
					<select id="pointId" name="pointId" style="min-width:120px; width: 350px;" class="required">
						<option value="">请选择</option>
						<c:forEach items="${airpoints }" var="item">
							<option value="${item.id }">${item.name }【${item.code }】</option>
						</c:forEach>
					</select>
					<span style="color: red">*</span>
				</div>
				
				<div class="profile-info-name">采样时间</div>
                <div class="profile-info-value">
					<input id="samplingTime" name="samplingTime" type="text" 
						class="input-large required" readonly="readonly" style="min-width:120px; width: 350px;"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">容器体积</div>
                <div class="profile-info-value">
					<input type="text" id="containerVolume" name="containerVolume" maxlength="10"
						style="min-width:120px; width: 350px;" class="input-large required number"/>
					<span style="color: red">*</span>
				</div>
				
				<div class="profile-info-name">收集量</div>
                <div class="profile-info-value">
					<input type="text" id="collectVolume" name="collectVolume" maxlength="10"
						style="min-width:120px; width: 350px;" class="input-large required number" />
					<span style="color: red">*</span>
				</div>
				
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">采样人</div>
                <div class="profile-info-value">
					<input type="text" id="samplingUser" name="samplingUser" maxlength="20"
						style="min-width:120px; width: 350px;" class="input-large"/>
				</div>
				
				<div class="profile-info-name"></div>
                <div class="profile-info-value">
				</div>
            </div>
        </div>
        <div class="profile-user-info profile-user-info-striped" style="border-top-style: none;">
        	<div class="profile-info-row" >
                <div class="profile-info-name">现场素材</div>
				<div class="profile-info-value">
					<tags:fileinputs uploadFileTypes=".jpg.jpeg.bmp.png" maxFileSize="20" />
				</div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="button" onclick="checkForm()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/reg/air/list/${menuid }/?page=${page }&isgetsession=1'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
    </form>
</div>

<script>
	var isCheck = false;
	var v;
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        v = $("#inputForm").validate();
        $("#name").focus();
    });
    function checkForm() {
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
    			url:"${root}/reg/air/checkCode/"+codeObj.val()+"/",
    			data:"tName=T_SAMPLING_AIR_REG&cName=CODE",
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