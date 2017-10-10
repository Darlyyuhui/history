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
       土壤分析数据登记
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增信息
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/analysis/land/doAdd/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;">
        <div class="profile-user-info profile-user-info-striped">
        
            <div class="profile-info-row">
				<div class="profile-info-name">采样编号</div>
                <div class="profile-info-value">
                	<select id="regId" name="regId" style="min-width:120px; width: 350px;" class="required" onchange="getRegDetail(this.value)">
                		<option value="">请选择</option>
                		<c:forEach items="${regs }" var="item">
                			<option value="${item.id }">${item.code }</option>
                		</c:forEach>
                	</select>
					<span style="color: red">*</span>
				</div>
				
				<div class="profile-info-name">分析单位</div>
                <div class="profile-info-value">
					<input type="text" id="dept" name="dept" maxlength="20" 
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">样品名称</div>
                <div class="profile-info-value" id="regName">
				</div>
				
				<div class="profile-info-name">经纬度</div>
                <div class="profile-info-value" id="regLocation">
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">采样时间</div>
                <div class="profile-info-value" id="regTime">
				</div>
				
				<div class="profile-info-name">采样地点</div>
                <div class="profile-info-value" id="regRegion">
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">PH值</div>
                <div class="profile-info-value">
					<input type="text" id="ph" name="ph" maxlength="4" min="0" max="14"
						style="min-width:120px; width: 350px;" class="input-large number"/>
				</div>
				
				<div class="profile-info-name">镉</div>
                <div class="profile-info-value">
					<input type="text" id="cadmium" name="cadmium" maxlength="4"
						style="min-width:120px; width: 350px;" class="input-large number required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
            	<div class="profile-info-name">有效态镉</div>
                <div class="profile-info-value">
					<input type="text" id="availableCadmium" name="availableCadmium" maxlength="4"
						style="min-width:120px; width: 350px;" class="input-large number"/>
				</div>
            
                <div class="profile-info-name">样品状态</div>
                <div class="profile-info-value">
					<input type="text" id="sampleStatus" name="sampleStatus" maxlength="150"
						style="min-width:120px; width: 350px;" class="input-large"/>
				</div>
				
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">是否超标</div>
                <div class="profile-info-value">
					<input type="radio" name="rdo_isOver" value="1" onclick="isOverLock()" />是
					<input type="radio" name="rdo_isOver" value="0" onclick="isOverLock()" checked style="margin-left: 10px;"/>否
					<input type="hidden" id="isOver" name="isOver" />
				</div>
				
				<div class="profile-info-name">超标项</div>
                <div class="profile-info-value">
					<input type="text" id="overItem" name="overItem" maxlength="20" readonly="readonly"
						style="min-width:120px; width: 350px;" class="input-large"/>
				</div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="button" onclick="checkForm()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/analysis/land/list/${menuid }/?page=${page }&isgetsession=1'">
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
        v = $("#inputForm").validate({
        	rules: {
        		ph: {
        			decimal2: true
        		},
        		cadmium: {
        			decimal2: true
    			},
    			availableCadmium: {
    				decimal2: true
    			}
    		}
        });
        $("#code").focus();
    });
    function checkForm() {
    	checkCode();
    	$("#isOver").val($("input[name='rdo_isOver']:checked").val());
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
    			url:"${root}/analysis/land/checkCode/"+codeObj.val()+"/",
    			data:"tName=T_ANALYSIS_LAND&cName=CODE",
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
    
    function isOverLock() {
    	var isOver = $("input[name='rdo_isOver']:checked").val();
    	var obj = $("#overItem");
    	if (isOver == "1") {
    		obj.removeAttr("readonly");
    	} else {
    		obj.attr("readonly", true);
    		obj.val("");
    	}
    }
    
    function getRegDetail(id) {
    	if (id == "") {
    		$("#regName").html("");
			$("#regTime").html("");
			$("#regLocation").html("");
			$("#regRegion").html("");
    	} else {
    		$.get(
    			"${root}/analysis/land/getRegById/"+id+"/",
    			function(data) {
    				$("#regName").html(data.name);
    				$("#regTime").html(data.samplingTimeStr);
    				$("#regLocation").html(data.longitude+","+data.latitude);
    				$("#regRegion").html(data.regionId);
    			}
    		);
    	}
    }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>