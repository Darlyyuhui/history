<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${root}/js/custom.validate.js" type="text/javascript"></script>
<div class="page-header">
    <h1>
        污染防治计划
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            修改计划
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/pollute/controll/doUpdate/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${info.id }"/>
        <input type="hidden" name="page" value="${page }"/>
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name" style="min-width:80px;width: 100px;">计划编号</div>
                <div class="profile-info-value">
                	${info.code }
				</div>
				
				<div class="profile-info-name" style="min-width:80px;width: 100px;">计划名称</div>
                <div class="profile-info-value">
					<input type="text" id="name" name="name" maxlength="50" value="${info.name }"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">执行时间</div>
                <div class="profile-info-value">
					<input id="executeTime" name="executeTime" type="text" value="<fmt:formatDate value='${info.executeTime }' pattern='yyyy-MM-dd hh:mm:ss'/>"
							class="input-large required" readonly="readonly" style="min-width:120px; width: 350px;"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />
				</div>
				
				<div class="profile-info-name">是否被执行</div>
                <div class="profile-info-value">
					<input type="radio" name="rdo_isExecute" value="1" ${info.isExecute eq '1' ? 'checked' : '' } />是
					<input type="radio" name="rdo_isExecute" value="0" ${info.isExecute eq '0' ? 'checked' : '' } style="margin-left: 10px;" />否
					<input type="hidden" id="isExecute" name="isExecute" />
				</div>
            </div>
        </div>
        <div class="profile-user-info profile-user-info-striped" style="border-top-style: none;">
        	<div class="profile-info-row">
                <div class="profile-info-name" style="min-width:80px;width: 100px;">计划内容</div>
                <div class="profile-info-value">
                	<textarea rows="5" style="min-width:120px; width: 840px;" id="content" name="content"
                	  onkeyup="countChars(this, 'textContent')">${info.content }</textarea> 
                	<br/>
					<span style="margin-right: 10px;">最大字符数：800</span>
					<span id="textContent" style="color: blue;">当前字符数：0</span>
				</div>
            </div>
        	<div class="profile-info-row">
                <div class="profile-info-name">计划执行单位</div>
                <div class="profile-info-value">
					<input type="text" id="dept" name="dept" maxlength="50" value="${info.dept }"
						style="min-width:120px; width: 840px;" class="input-large"/>
				</div>
            </div>
            <div class="profile-info-row" >
                <div class="profile-info-name">附件</div>
				<div class="profile-info-value">
					<tags:files businessId="${info.id }" isDel="1" />
					<tags:fileinputs uploadFileTypes=".pdf" maxFileSize="50" />
				</div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="button" onclick="checkForm()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/pollute/controll/list/${menuid }/?page=${page }&isgetsession=1'">
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
        		content: {
    				textLength: 800
    			}
    		}
        });
        $("#name").focus();
        countChars(document.getElementById("content"), "textContent");
    });
    function checkForm() {
    	$("#isExecute").val($("input[name='rdo_isExecute']:checked").val());
    	if (v.checkForm()) {
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>