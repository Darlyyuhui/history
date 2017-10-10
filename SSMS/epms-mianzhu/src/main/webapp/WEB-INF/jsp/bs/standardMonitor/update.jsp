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
            修改监测指标
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/bs/standardMonitor/doUpdate/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;">
		<input type="hidden" name="page" value="${page }"/>
		<input type="hidden" name="id" value="${info.id }"/>
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">编号</div>
                <div class="profile-info-value">
                	${info.code }
				</div>
				
				<div class="profile-info-name">指标类型</div>
                <div class="profile-info-value">
					<select id="typeCode" name="typeCode" style="min-width:120px; width: 350px;" class="required">
						<tags:diccache typeCode="STANDARD_MONITOR_TYPE" defaultValue="${info.typeCode }" />
					</select>
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">标准最小值</div>
                <div class="profile-info-value">
                	<input type="text" id="minVal" name="minVal" maxlength="9"  min="0" value="${info.minVal }"
						style="min-width:120px; width: 350px;" class="input-large number"/>
				</div>
				
				<div class="profile-info-name">标准最大值</div>
                <div class="profile-info-value">
                	<input type="text" id="maxVal" name="maxVal" maxlength="9"  min="0" value="${info.maxVal }"
						style="min-width:120px; width: 350px;" class="input-large number"/>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">报警值</div>
                <div class="profile-info-value">
                	<input type="text" id="alarmVal" name="alarmVal" maxlength="9" min="0"  value="${info.alarmVal }"
						style="min-width:120px; width: 350px;" class="input-large number"/>
				</div>
				
				<div class="profile-info-name">单位</div>
				<div class="profile-info-value">
					<input type="text" id="unit" name="unit" maxlength="20" value="${info.unit }"
						style="min-width:120px; width: 350px;" class="input-large"/>
            	</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">评价等级</div>
				<div class="profile-info-value">
                	<input type="text" id="remark" name="level" maxlength="20" value="${info.level }"
						style="min-width:120px; width: 350px;" class="input-large"/>
            	</div>
				
				<div class="profile-info-name">描述</div>
				<div class="profile-info-value">
                	<input type="text" id="describe" name="describe" maxlength="100" value="${info.describe }"
						style="min-width:120px; width: 350px;" class="input-large"/>
				</div>
			</div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="button" onclick="checkForm()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/bs/standardMonitor/list/${menuid }/?isgetsession=1&page=${page }'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
    </form>
</div>
<script>
  var capital =false
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        $("#inputForm").validate();
    });
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
    	var flag=checkCapital();
    	var v = $("#inputForm").validate();
    	if (v.checkForm()&&flag) {
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>