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
       土壤修复资金管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            查看信息
        </small>
    </h1>
</div>
<div class="row">
        <input type="hidden" name="id" value="${info.id }"/>
        <input type="hidden" name="page" value="${page }"/>
        <div class="profile-user-info profile-user-info-striped">
        
            <div class="profile-info-row">
                <div class="profile-info-name">修复项目</div>
                <div class="profile-info-value">
                	<span id="pro_span"></span>
				</div>
				
				<div class="profile-info-name" style="width: 140px;">资金来源</div>
                <div class="profile-info-value">
                	<tags:xiangxuncache keyName="Dic" typeCode="LAND_CAPITAL_SOURCE" id="${info.source }"/>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">拨付时间</div>
                <div class="profile-info-value">
					<fmt:formatDate value='${info.moneyTime }' pattern='yyyy-MM-dd hh:mm:ss'/>
				</div>
				
				<div class="profile-info-name">拨付金额（万元）</div>
                <div class="profile-info-value">
					${info.total }
				</div>
            </div>
            
            <div class="profile-info-row">
				<div class="profile-info-name">主管单位</div>
                <div class="profile-info-value">
					${info.competentUnit }
				</div>
				
				<div class="profile-info-name">使用状态</div>
                <div class="profile-info-value">
					${info.useStatus eq '1' ? '已使用' : '未使用' }
				</div>
            </div>
        </div>
        <div class="profile-user-info profile-user-info-striped" style="border-top-style: none;">
        	<div class="profile-info-row" >
                <div class="profile-info-name">附件</div>
				<div class="profile-info-value">
					<tags:files businessId="${info.id }"/>
				</div>
            </div>
        </div>
        
        
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn" type="reset" onclick="window.location.href='${root}/repair/capital/list/${menuid }/?page=${page }&isgetsession=1'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
</div>
<div style="display: none;">
	<select id="proId" >
		<option value=""></option>
		<c:forEach items="${projects }" var="item">
			<option value="${item.id }" ${info.proId eq item.id ? 'selected' : '' }>${item.name }【${item.code }】</option>
		</c:forEach>
    </select>
</div>
<script type="text/javascript">
$(function() {
	$("#pro_span").html($("#proId option:selected").text());
});
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>