<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>

	<div class="page-header">
		<h1>
			基地产品信息<small> <i
				class="ace-icon fa fa-angle-double-right"></i> 查看基地产品信息
			</small>
		</h1>
	</div>
<div class="row">
        <input type="hidden" name="page" value="${page }" />
        <input type="hidden" name="id" value="${info.id }" />
	<div class="profile-user-info profile-user-info-striped">
	   <div class="profile-info-row">
                <div class="profile-info-name">编号</div>
                <div class="profile-info-value">
                	${info.code }
				</div>
        </div>
        <div class="profile-info-row">
                <div class="profile-info-name">名称</div>
                <div class="profile-info-value">
                	${info.name }
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">产品说明</div>
                <div class="profile-info-value">
                   ${info.explain }
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">种类</div>
                <div class="profile-info-value">
                	${typeName }
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">基地</div>
                <div class="profile-info-value">
                	 <td><tags:listshow keyName="${infoName }"  id="${info.id }"></tags:listshow></td>
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
		window.location.href = "${root}/apb/apbproduct/list/${menuid}/?page=${page}";
	}
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>