<%@ page language="java" pageEncoding="UTF-8"%>
<div class="page-header">
	<h1>
		组织机构
		<small> <i class="ace-icon fa fa-angle-double-right"></i> 修改组织机构 </small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="">
		<!-- PAGE CONTENT BEGINS -->
		<form id="inputForm" class="form-horizontal" role="form" action="${root}/system/org/doAdd/${menuid}/" method="post">
			<input type="hidden" name="parentid" value="${org.id}">

			<!-- #section:elements.form -->
			<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name">
						上级机构名称
					</div>
					<div class="profile-info-value">
						<tags:cache keyName="Department" id="${org.id}"></tags:cache>
					</div>
				</div>
				<div class="profile-info-row">
					<label class="profile-info-name" for="form-field-1-1">
						机构编码
					</label>
					<div class="profile-info-value">
						${atms['areacode']}<input style="width: 80px;" value="${code}" maxlength="6" minlength="6" type="text" id="org-code" placeholder="机构编码" name="code" class="input-small required" digits="true" />
					</div>
				</div>
				<div class="profile-info-row">
					<label class="profile-info-name" for="form-field-1">
						机构名称
					</label>
					<div class="profile-info-value">
						<span class="input-icon input-icon-right"> <input type="text" id="org-name" placeholder="机构名称" specialcharfilter="true" name="name" maxlength="20" class="input-large required" chinese="true" /> <i class="ace-icon fa fa-asterisk red"></i> </span>
					</div>
				</div>
				<div class="profile-info-row">
					<label class="profile-info-name" for="form-field-1-1">
						责任人
					</label>
					<div class="profile-info-value">
						<span class="input-icon input-icon-right"> <input type="text" id="org-principal" placeholder="责任人" maxlength="10" name="principalname" class="input-large required" chinese="true" /> <i class="ace-icon fa fa-asterisk red"></i> </span>
					</div>
				</div>
				<div class="profile-info-row">
					<label class="profile-info-name" for="form-field-1-1">
						联系电话
					</label>
					<div class="profile-info-value">
						<span class="input-icon input-icon-right"> <input type="text" id="org-mobile" maxlength="13" teletest="true"  name="mobile"
							class="input-large required" /> <i class="ace-icon fa fa-asterisk red"></i> </span>
					</div>
				</div>
				<div class="profile-info-row">
					<label class="profile-info-name" for="form-field-1-1">
						备注
					</label>
					<div class="profile-info-value">
						<textarea name="memo" class="form-control" maxlength="100" id="form-field-8"></textarea>
					</div>
				</div>
			</div>
			<div class="clearfix form-actions">
				<div class="col-md-offset-1 col-md-9">
					<button class="btn btn-info" type="submit">
						<i class="ace-icon fa fa-check bigger-110"></i> 保存
					</button>
					<button class="btn" type="reset" onclick="showList()">
						<i class="ace-icon fa fa-undo bigger-110"></i> 返回
					</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script>
		function showList(){
			window.location.href="${root}/system/org/list/${menuid}/";
		}
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#org-name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				 rules: {
					"code":{
						remote:{
							url:"${root}/system/org/codeExist",
							type:"post",
							data:{
								code:function(){
									return $("#org-code").val();
								}
							}
						
						}
					}
				}
			});
			$("#code-div span.error").height(50).width(200);
		});
		
	</script>