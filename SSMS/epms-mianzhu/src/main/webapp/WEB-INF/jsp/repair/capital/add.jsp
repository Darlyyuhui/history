<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${root}/js/business.validate.js" type="text/javascript"></script>
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
       土壤修复资金管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增信息
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/repair/capital/doAdd/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;" enctype="multipart/form-data">
        <div class="profile-user-info profile-user-info-striped">
        
            <div class="profile-info-row">
                <div class="profile-info-name">修复项目</div>
                <div class="profile-info-value">
                	<select id="proId" name="proId" style="min-width:120px; width: 350px;" class="required">
                		<option value="">请选择</option>
                		<c:forEach items="${projects }" var="item">
                			<option value="${item.id }">${item.name }【${item.code }】</option>
                		</c:forEach>
                	</select>
					<span style="color: red">*</span>
				</div>
				
				<div class="profile-info-name" style="width: 140px;">资金来源</div>
                <div class="profile-info-value">
                	<select id="source" name="source" style="min-width:120px; width: 350px;" class="required">
                		<tags:diccache typeCode="LAND_CAPITAL_SOURCE" />
                	</select>
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">拨付时间</div>
                <div class="profile-info-value">
					<input id="moneyTime" name="moneyTime" type="text" 
						class="input-large required" readonly="readonly" style="min-width:120px; width: 350px;"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />
					<span style="color: red">*</span>
				</div>
				
				<div class="profile-info-name">拨付金额（万元）</div>
                <div class="profile-info-value">
					<input type="text" id="total" name="total" maxlength="8"
						style="min-width:120px; width: 350px;" class="input-large number required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
				<div class="profile-info-name">主管单位</div>
                <div class="profile-info-value">
					<input type="text" id="competentUnit" name="competentUnit" maxlength="30"
						style="min-width:120px; width: 350px;" class="input-large" />
				</div>
				
				<div class="profile-info-name">使用状态</div>
                <div class="profile-info-value">
					<input type="radio" name="rdo_useStatus" value="1" checked />已使用
					<input type="radio" name="rdo_useStatus" value="0" style="margin-left: 10px;" />未使用
					<input type="hidden" id="useStatus" name="useStatus" />
				</div>
            </div>
        </div>
        <div class="profile-user-info profile-user-info-striped" style="border-top-style: none;">
        	<div class="profile-info-row" >
                <div class="profile-info-name">附件</div>
				<div class="profile-info-value">
					<tags:fileinputs uploadFileTypes=".jpg.jpeg.bmp.png.pdf" maxFileSize="50" />
				</div>
            </div>
        </div>
        
        
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="button" onclick="checkForm()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/repair/capital/list/${menuid }/?page=${page }&isgetsession=1'">
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
        		total: {
        			decimal4: true
    			}
    		}
        });
        $("#code").focus();
    });
    function checkForm() {
    	$("#useStatus").val($("input[name='rdo_useStatus']:checked").val());
    	if (v.checkForm()) {
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>