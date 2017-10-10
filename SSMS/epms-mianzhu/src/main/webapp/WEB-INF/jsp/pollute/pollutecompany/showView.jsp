<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>

	<div class="page-header">
		<h1>
			污染企业信息<small> <i
				class="ace-icon fa fa-angle-double-right"></i> 查看污染企业信息
			</small>
		</h1>
	</div>
<div class="row">
        <input type="hidden" name="page" value="${page }" />
        <input type="hidden" name="id" value="${info.id }" />
	<div class="profile-user-info profile-user-info-striped">
	   	 <div class="profile-info-row">
                <div class="profile-info-name">名称</div>
                <div class="profile-info-value">
                	${info.name }
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">法人代表</div>
                <div class="profile-info-value">
                	${info.corporation }
						
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">N（北纬）</div>
                <div class="profile-info-value">
                	${info.latitude }
					
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">E（东经）</div>
                <div class="profile-info-value">
                	${info.longitude }
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">地址</div>
                <div class="profile-info-value">
                	${info.address }
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">联系人</div>
                <div class="profile-info-value">
                	${info.linkMan }
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">联系电话</div>
                <div class="profile-info-value">
	                ${info.linkTel }
				</div>
            </div> 
            <div class="profile-info-row">
                <div class="profile-info-name">特征污染物</div>
                <div class="profile-info-value">
	                ${info.features }
				</div>
            </div> 
            <div class="profile-info-row">
                <div class="profile-info-name">状态</div>
                <div class="profile-info-value">
                	<c:if test="${info.status == 0}">停产 </c:if> 
                     <c:if test="${info.status == 1}">停业整顿  </c:if>
                     <c:if test="${info.status == 9}"> 在产  </c:if> 
                    
				</div>
            </div>
           </div>
           <div class="profile-user-info profile-user-info-striped" style="border-top-style: none;">
            <div class="profile-info-row" >
                <div class="profile-info-name">附件</div>
				<div class="profile-info-value">
					<tags:files businessId="${info.id }" />
				</div>
            </div>
        </div>
		
	<div class="clearfix form-actions">
		<div class="col-md-offset-3 col-md-9">
			<button class="btn" type="reset" onclick="showList()">
				<i class="ace-icon fa fa-undo bigger-110"></i> 返回
			</button>
		</div>
	</div>
</div>
<script>
	function showList() {
		window.location.href = "${root}/pollute/pollutecompany/list/${menuid}/?page=${page}";
	}
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>