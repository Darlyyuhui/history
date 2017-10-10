<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<tags:selTree idElement="regionId" nameElement="regionName" treeType="region" />
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
       大气沉降分析数据登记
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            修改信息
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/analysis/air/doUpdate/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;">
          
        <input type="hidden" name="page" value="${page }" />
        <input type="hidden" name="id" value="${info.id }" />
        <div class="profile-user-info profile-user-info-striped">
        
            <div class="profile-info-row">
                <div class="profile-info-name">分析编号</div>
                <div class="profile-info-value" style="width: 500px;">
                	${info.code }
				</div>
				
				<div class="profile-info-name">采样编号</div>
                <div class="profile-info-value">
                	${regInfo.code }
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">采样点</div>
                <div class="profile-info-value" id="regPoint">
                	${regInfo.pointId }
				</div>
				<div class="profile-info-name">采样时间</div>
                <div class="profile-info-value" id="regTime">
                	${regInfo.samplingTimeStr }
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">容器体积</div>
                <div class="profile-info-value" id="regContainerVolume">
                	${regInfo.containerVolume }
				</div>
				<div class="profile-info-name">收集量</div>
                <div class="profile-info-value" id="regCollectVolume">
                	${regInfo.collectVolume }
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">镉含量</div>
                <div class="profile-info-value">
					<input type="text" id="cadmium" name="cadmium" maxlength="4" value="${info.cadmium }"
						style="min-width:120px; width: 350px;" class="input-large number"/>
				</div>
				
				<div class="profile-info-name">是否超标</div>
                <div class="profile-info-value">
					<input type="radio" name="rdo_isOver" value="1" ${info.isOver eq '1' ? 'checked' : '' } />是
					<input type="radio" name="rdo_isOver" value="0" ${info.isOver eq '0' ? 'checked' : '' } style="margin-left: 10px;"/>否
					<input type="hidden" id="isOver" name="isOver" />
				</div>
            </div>
            
            <div class="profile-info-row">
            	<div class="profile-info-name">分析单位</div>
                <div class="profile-info-value">
					<input type="text" id="dept" name="dept" maxlength="20" value="${info.dept }"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
				</div>
				
				<div class="profile-info-name">备注</div>
                <div class="profile-info-value">
					<input type="text" id="remark" name="remark" maxlength="200" value="${info.remark }"
						style="min-width:120px; width: 350px;" class="input-large"/>
				</div>
            </div>
            
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="button" onclick="checkForm()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/analysis/air/list/${menuid }/?page=${page }&isgetsession=1'">
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
        		cadmium: {
        			decimal2: true
    			}
    		}
        });
        $("#code").focus();
    });
    function checkForm() {
    	$("#isOver").val($("input[name='rdo_isOver']:checked").val());
    	if (v.checkForm()) {
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
    
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>