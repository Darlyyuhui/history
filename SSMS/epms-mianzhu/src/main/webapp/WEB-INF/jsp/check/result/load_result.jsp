<%@ page language="java" pageEncoding="UTF-8" %>
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
        setTimeout('$("#message").hide("slow")', 1200);    </script>
</c:if>
<div class="page-header">
    <h1>
      数据校验结果
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            数据校验
        </small>
    </h1>
</div>
	<div class="row">
        <div class="profile-user-info profile-user-info-striped">
        	<div class="profile-info-row">
                <div class="profile-info-name">样品来源</div>
                <div class="profile-info-value">
                	${dataCheckInfo.schemeName }
                </div>
                
                <div class="profile-info-name">样品种类</div>
                <div class="profile-info-value">
                	<tags:xiangxuncache keyName="Dic" typeCode="SAMPLING_TYPES" id="${dataCheckInfo.sampleType }"/>
                </div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">校验样品数</div>
                <div class="profile-info-value">
                	${dataCheckInfo.analysisCount }
                </div>
                
                <div class="profile-info-name">分析单位</div>
                <div class="profile-info-value">
                	${dataCheckInfo.analysisDept }
                </div>
            </div>
        </div>
	</div>
	<div class="clearfix form-actions">
	    <div style="margin-left:90px; font-size: 30px;" class="col-md-10">
	        正在进行数据校验【<span id="djs"></span>】......
	    </div>
	</div>
<script type="text/javascript">
var num = 5;
function doDjs() {
	if (num == 1) {
		window.location.href = "${root}/check/result/list/${menuid}/${dataCheckInfo.id }/";
	} else {
		$("#djs").html(num-=1);
	}
}
$(function() {
	$("#djs").html(num);
	$.post("${root}/check/result/doCheck/${dataCheckInfo.id }/");
	window.setInterval("doDjs()", 1000);
});	
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>