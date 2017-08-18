<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>

	<div class="page-header">
		<h1>
			行业标准<small> <i
				class="ace-icon fa fa-angle-double-right"></i> 查看行业标准详情
			</small>
		</h1>
	</div>
  <div class="row">
        <input type="hidden" name="page" value="${page }" />
        <input type="hidden" name="id" value="${standardIndustry.id }" />
	<div class="profile-user-info profile-user-info-striped">
	   <div class="profile-info-row">
                <div class="profile-info-name">编号</div>
                <div class="profile-info-value">
                	${standardIndustry.code }
				</div>
        </div>
        <div class="profile-info-row">
                <div class="profile-info-name">名称</div>
                <div class="profile-info-value">
                	${standardIndustry.name }
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">样品</div>
                <div class="profile-info-value">
                   ${standardIndustry.sample }
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">参数</div>
                <div class="profile-info-value">
                	${standardIndustry.params }
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">影响因子</div>
                <div class="profile-info-value">
                	${standardIndustry.factor }
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">标准值</div>
                <div class="profile-info-value">
                	${standardIndustry.standardVal }
				</div>
            </div>
            
            <div class="profile-info-row">
               <div class="profile-info-name">单位</div>
               <div class="profile-info-value">
                	${standardIndustry.unit }
			</div>
           </div>
           </div>
          <div class="profile-user-info profile-user-info-striped" style="border-top-style: none;">
        	<div class="profile-info-row" >
                <div class="profile-info-name">图片</div>
				<div class="profile-info-value">
					<tags:files businessId="${standardIndustry.id }" />
				</div>
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
		window.location.href = "${root}/bs/standardIndustry/list/${menuid}/?page=${page}";
	}
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>