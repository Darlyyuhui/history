<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<tags:selTree idElement="regionId" nameElement="regionName" treeType="region" />
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
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
      采样方案
        <small><i class="ace-icon fa fa-angle-double-right"></i>
           查看方案
        </small>
    </h1>
</div>
<div class="row">
        <input type="hidden" name="id" value="${info.id }"/>
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">采样计划</div>
                <div class="profile-info-value" style="width: 500px;">
               		${planInfo.name }【${planInfo.code }】
				</div>
				
				<div class="profile-info-name">采样选址</div>
                <div class="profile-info-value">
					<tags:xiangxuncache keyName="TREGION_FULL_NAME" id="${info.regionId }"/>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">方案编号</div>
                <div class="profile-info-value">
                	${info.code }
				</div>
                
                <div class="profile-info-name">方案名称</div>
                <div class="profile-info-value">
                	${info.name }
				</div>
            </div>
            
            <div class="profile-info-row">
            	<div class="profile-info-name">采样品种</div>
                <div class="profile-info-value">
                	<tags:xiangxuncache keyName="Dic" typeCode="SAMPLING_TYPES" id="${info.sampleCode }"/>
                </div>
            	
                <div class="profile-info-name">制定单位</div>
                <div class="profile-info-value">
					${info.dept }
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">检测项目</div>
                <div class="profile-info-value">
					${info.testItems }
				</div>
				
				<div class="profile-info-name"></div>
                <div class="profile-info-value"></div>
            </div>
            
        </div>
        <div id="blockDiv" class="profile-user-info profile-user-info-striped" style="border-top-style: none; display: none;">
        	<div class="profile-info-row">
				<div class="profile-info-name">采样地块</div>
                <div class="profile-info-value" style="width: 500px;">
                	<c:forEach items="${blocks }" var="item">
               			<c:if test="${info.blockId eq item.id}">${item.name }【${item.code }】</c:if>
               		</c:forEach>
                </div>
                
                <div class="profile-info-name">修复进度</div>
                <div class="profile-info-value">
                    <tags:xiangxuncache keyName="Dic" typeCode="LAND_REPAIR_SCHEDULE" id="${info.repairSchedule }"></tags:xiangxuncache>
                </div>
            </div>
        </div>
        <div class="profile-user-info profile-user-info-striped" style="border-top-style: none;">
        	<div class="profile-info-row" >
                <div class="profile-info-name">方案附件</div>
				<div class="profile-info-value">
					<tags:files businessId="${info.id }" />
				</div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn" type="reset" onclick="window.location.href='${root}/land/sampling/scheme/list/${menuid }/?page=${page }&isgetsession=1'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
</div>
<script type="text/javascript">
function ckbSample(val) {
	if (val == "NTTR") {
		$("#blockDiv").css("display", "block");
	} else {
		$("#blockDiv").css("display", "none");
		$("#blockId").val("");
		$("#repairSchedule").val("");
	}
}
$(function() {
	ckbSample("${info.sampleCode}");
});
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>