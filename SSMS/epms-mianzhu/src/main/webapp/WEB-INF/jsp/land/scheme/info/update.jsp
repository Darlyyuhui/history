<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<tags:selTree idElement="regionId" nameElement="regionName" treeType="region" />
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
    <p id="alert-content" align="center"></p>
</div>
<div class="page-header">
    <h1>
      采样方案
        <small><i class="ace-icon fa fa-angle-double-right"></i>
           修改方案
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/land/sampling/scheme/doUpdate/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${info.id }"/>
        <input type="hidden" name="page" value="${page }"/>
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">采样计划</div>
                <div class="profile-info-value" style="width: 500px;">
                	<select id="planId" name="planId" class="required" style="min-width:120px; width: 350px;" onchange="getSamplingTypes(this.value)">
                		<option value="">请选择</option>
                		<c:forEach items="${plans }" var="item">
                			<option value="${item.id }" ${info.planId eq item.id ? 'selected' : '' }>${item.name }【${item.code }】</option>
                		</c:forEach>
                	</select>
                	<span style="color: red">*</span>
				</div>
				
				<div class="profile-info-name">采样选址</div>
                <div class="profile-info-value">
					<input type="text" id="regionName" readonly="readonly" value='<tags:xiangxuncache keyName="TREGION_NAME" id="${info.regionId }"/>'
						style="min-width:120px; width: 350px;" class="input-large required" />
					<input type="hidden" id="regionId" name="regionId" value="${info.regionId }" />
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">方案编号</div>
                <div class="profile-info-value">
                	${info.code }
				</div>
                
                <div class="profile-info-name">方案名称</div>
                <div class="profile-info-value">
                	<input type="text" id="name" name="name" maxlength="50" value="${info.name }"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
            	<div class="profile-info-name">采样品种</div>
                <div class="profile-info-value">
                	<div id="samplingTypeDiv" style="float: left;">
                		<span style="color: blue">请选择采样计划</span>
                	</div>
                	<div style="color: red; float: left;">*</div>
                </div>
            	
                <div class="profile-info-name">制定单位</div>
                <div class="profile-info-value">
					<input type="text" id="dept" name="dept" maxlength="50" value="${info.dept }"
						style="min-width:120px; width: 350px;" class="input-large"/>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">检测项目</div>
                <div class="profile-info-value">
					<input type="text" id="testItems" name="testItems" maxlength="100" value="${info.testItems }"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
				</div>
				
				<div class="profile-info-name"></div>
                <div class="profile-info-value"></div>
            </div>
        </div>
        
        <div id="blockDiv" class="profile-user-info profile-user-info-striped" style="border-top-style: none; display: none;">
        	<div class="profile-info-row">
				<div class="profile-info-name">采样地块</div>
                <div class="profile-info-value" style="width: 500px;">
                	<select id="blockId" name="blockId" style="min-width:120px; width: 350px;">
                		<option value="">请选择</option>
                		<c:forEach items="${blocks }" var="item">
                			<option value="${item.id }" ${info.blockId eq item.id ? 'selected' : '' }>${item.name }【${item.code }】</option>
                		</c:forEach>
                	</select>
                </div>
                
                <div class="profile-info-name">修复进度</div>
                <div class="profile-info-value">
                    <select id="repairSchedule" name="repairSchedule" style="min-width:120px; width: 350px;">
                        <tags:diccache typeCode="LAND_REPAIR_SCHEDULE" defaultValue="${info.repairSchedule }" />
                    </select>
                </div>
            </div>
        </div>
        <div class="profile-user-info profile-user-info-striped" style="border-top-style: none;">
        	<div class="profile-info-row" >
                <div class="profile-info-name">方案附件</div>
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
                <button class="btn" type="reset" onclick="window.location.href='${root}/land/sampling/scheme/list/${menuid }/?page=${page }&isgetsession=1'">
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
        v = $("#inputForm").validate();
        $("#code").focus();
        getSamplingTypes("${info.planId}");
        ckbSample("${info.sampleCode}");
    });
    function checkForm() {
    	if (v.checkForm()) {
    		if (checkedRadio()) {
    			var radVal = $("input[name='sampleCode']:checked").val();
    			if(radVal == "NTTR" && $("#blockId option:selected").val() == "") {
    				showMessage("采样农田土壤时，请选择采样地块");
    			} else {
    				$("#inputForm").submit();
    			}
    		} else {
    			showMessage("请选择采样品种");
    		}
    	}else{
    		v.showErrors();
    	}
    }
    function checkedRadio() {
    	var isCked = false;
    	$("input[name='sampleCode']").each(function() {
    		if (this.checked) {
    			isCked = true;
    			return false;
    		}
    	});
    	return isCked;
    }
    function getSamplingTypes(planId) {
    	if (planId == "") {
    		$("#samplingTypeDiv").html("<span style=\"color: blue\">请选择采样计划</span>");
    	} else {
    		$.post(
    			"${root}/land/sampling/scheme/getSampleCodes/"+planId+"/",
    			{
    				"code" : "${info.sampleCode}"
    			},
    			function(data) {
    				$("#samplingTypeDiv").html(data);
    			}
    		);
    	}
    }
    function ckbSample(val) {
    	if (val == "NTTR") {
    		$("#blockDiv").css("display", "block");
    	} else {
    		$("#blockDiv").css("display", "none");
    		$("#blockId").val("");
    		$("#repairSchedule").val("");
    	}
    }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>