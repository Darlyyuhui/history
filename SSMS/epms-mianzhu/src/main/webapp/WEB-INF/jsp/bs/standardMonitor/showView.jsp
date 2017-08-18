<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>

	<div class="page-header">
		<h1>
			监测指标<small> <i
				class="ace-icon fa fa-angle-double-right"></i> 查看监测指标项详情
			</small>
		</h1>
	</div>
	<div class="row">
        <input type="hidden" name="page" value="${page }" />
        <input type="hidden" name="id" value="${info.id }" />
	<div class="profile-user-info profile-user-info-striped">
	   <div class="profile-info-row">
                <div class="profile-info-name">类型编号</div>
                <div class="profile-info-value">
                	${standardMonitor.code }
				</div>
        </div>
        <div class="profile-info-row">
                <div class="profile-info-name">指标类型</div>
                <div class="profile-info-value">
                	${standardMonitor.typeCode }
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">指标名称</div>
                <div class="profile-info-value">
                   ${standardMonitor.name }
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">标准最小值</div>
                <div class="profile-info-value">
                	${standardMonitor.minVal }
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">标准最大值</div>
                <div class="profile-info-value">
                	${standardMonitor.maxVal }
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">报警值</div>
                <div class="profile-info-value">
                	${standardMonitor.alarmVal }
				</div>
            </div>
            
            <div class="profile-info-row">
               <div class="profile-info-name">单位</div>
               <div class="profile-info-value">
                	${standardMonitor.unit }
			</div>
           </div>
           <div class="profile-info-row">
               <div class="profile-info-name">评价等级</div>
               <div class="profile-info-value">
                	${standardMonitor.level }
			</div>
           </div>
           <div class="profile-info-row">
               <div class="profile-info-name">描述</div>
               <div class="profile-info-value">
                	${standardMonitor.describe }
			</div>
           </div>
		
	<div class="clearfix form-actions">
		<div class="col-md-offset-2 col-md-10">
			<button class="btn" type="reset" onclick="showList()">
				<i class="ace-icon fa fa-undo bigger-110"></i> 返回
			</button>
		</div>
	</div>
</div>
<script>
	function showList() {
		window.location.href = "${root}/bs/standardMonitor/list/${menuid}/?page=${page}";
	}
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>