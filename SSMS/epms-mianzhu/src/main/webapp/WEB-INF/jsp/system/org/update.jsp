<%@ page language="java" pageEncoding="UTF-8"%>
<div class="page-header">
	<h1>
		组织机构
		<small> <i class="ace-icon fa fa-angle-double-right"></i>
			修改组织机构 </small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="">
		<!-- PAGE CONTENT BEGINS -->
		<form id="inputForm" class="form-horizontal" role="form"
			action="${root}/system/org/doUpdate" method="post">
			<input type="hidden" name="id" value="${org.id}">
			<input type="hidden" name="menuid" value="${menuid}">
			<!-- #section:elements.form -->
			<div class="profile-user-info profile-user-info-striped">
			<div class="profile-info-row">
				<label class="profile-info-name">
					上级机构名称
				</label>
				<div class="profile-info-value">
					<tags:cache keyName="Department" id="${org.parentid}"></tags:cache>
				</div>
			</div>
			<div class="profile-info-row">
				<label class="profile-info-name">
					机构编码
				</label>
				<div class="profile-info-value">
					${org.code}
				</div>
			</div>
			<div class="profile-info-row">
				<label class="profile-info-name"
					for="form-field-1">
					机构名称
				</label>
				<div class="profile-info-value">
					<input type="text" id="org-name" placeholder="机构名称"
							specialcharfilter="true" name="name" value="${org.name}"
							maxlength="20" class="input-large required" chinese="true" />
					<span style="color: red">*</span>
				</div>
			</div>
			<div class="profile-info-row">
				<label class="profile-info-name"
					for="form-field-1-1">
					责任人
				</label>
				<div class="profile-info-value">
					<input type="text" id="org-principal" placeholder="责任人" maxlength="10"
							name="principalname" value="${org.principalname}"
							class="input-large required" chinese="true" />
					<span style="color: red">*</span>
				</div>
			</div>
			<div class="profile-info-row">
				<label class="profile-info-name"
					for="form-field-1-1">
					联系电话
				</label>
				<div class="profile-info-value">
					<input type="text" id="org-mobile" value="${org.mobile}"
							placeholder="联系电话" maxlength="13" teletest="true" name="mobile"
							class="input-large required" />
					<span style="color: red">*</span>
				</div>
			</div>
			<div class="profile-info-row">
				<label class="profile-info-name"
					for="form-field-1-1">
					备注
				</label>
				<div class="profile-info-value">
					<textarea name="memo" class="form-control" maxlength="100" id="form-field-8">${org.memo}</textarea>
				</div>
			</div>
			</div>
			<div class="clearfix form-actions">
				<div class="col-md-9" style="margin-left: 100px">
					<button class="btn btn-info" type="submit">
						<i class="ace-icon fa fa-check bigger-110"></i> 保存
					</button>
					&nbsp; &nbsp; &nbsp;
					<button class="btn" type="reset" onclick="showList()">
						<i class="ace-icon fa fa-undo bigger-110"></i> 返回
					</button>
				</div>
			</div>
		</form>
	</div>
</div>

<!-- /section:elements.form -->

<script>

		var chooseVal;
		function showList(){
			window.location.href="${root}/system/org/list/${menuid}/";
		}

		function showPoliceDialog(a,b,c){
			var v = window.showModalDialog("${root}/system/policeuser/dialog/show/${menuid}/",c,"dialogWidth=680px;dialogHeight=577px;center=yes;middle=yes;help=no;status=no;scroll=no;resizable=no");
			var ids = chooseVal.split("#")[0];
			var names = chooseVal.split("#")[1];
			$(a).val(ids);
			$(b).val(names);
		}
		
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#org-name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>