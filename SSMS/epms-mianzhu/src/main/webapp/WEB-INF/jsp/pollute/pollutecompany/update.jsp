<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf"%>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
    <p id="alert-content" align="center"></p>
</div>
<!-- 控制iframe中的页面返回的结果显示开始 -->
<div id="subinfo" class="alert alert-success" style="display: none;">
    <button data-dismiss="alert" class="close">×</button>
    <p id="mesg" align="center"></p>
</div>
 <div id="fileuplodmessage" class="alert alert-success"  style="display:none">
        <button data-dismiss="alert" class="close">×</button>
        <p align="center"></p>
    </div>
<div class="page-header">
	<h1>
		污染企业信息 <small> <i class="ace-icon fa fa-angle-double-right"></i>
			修改污染企业信息
		</small>
	</h1>
</div>
<div class="row">
	<form id="inputForm" class="form-horizontal"
		action="${root}/pollute/pollutecompany/doUpdate/${menuid}/"     
		method="post" enctype="multipart/form-data">
		<input type="hidden" name="menuid" value="${menuid}" /> <input
			type="hidden" name="page" value="${page}" /> <input type="hidden"
			name="id" value="${info.id}" />
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
                	<input type="text" id="corporation" name="corporation" maxlength="50"  value="${info.corporation }"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">N（北纬）</div>
                <div class="profile-info-value">
                	<input type="text" id="latitude"  name="latitude" maxlength="12"  min="0"  max="90"  value="${info.latitude }"
						style="min-width:120px; width: 350px;" class="input-large decimal4 required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">E（东经）</div>
                <div class="profile-info-value">
                	<input type="text" id="longitude" name="longitude" maxlength="12"  min="0" max="180"  value="${info.longitude }"
						style="min-width:120px; width: 350px;" class="input-large decimal4 required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">地址</div>
                <div class="profile-info-value">
                	<input type="text" id="address" name="address" maxlength="100"  value="${info.address }"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">联系人</div>
                <div class="profile-info-value">
                	<input type="text" id="linkMan" name="linkMan" maxlength="25"  value="${info.linkMan }"
						style="min-width:120px; width: 350px;" class="input-large"/>
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">联系电话</div>
                <div class="profile-info-value">
	                <input type="text" id="linkTel" name="linkTel" maxlength="13"  value="${info.linkTel }"
						style="min-width:120px; width: 350px;" class="input-large "/>
				</div>
            </div> 
             <div class="profile-info-row">
                <div class="profile-info-name">特征污染物</div>
                <div class="profile-info-value">
	                <input type="text" id="features" name="features" maxlength="200"    value="${info.features }"
						style="min-width:120px; width: 350px;" class="input-large "/>
				</div>
            </div> 
            <div class="profile-info-row">
                <div class="profile-info-name">状态</div>
                <div class="profile-info-value">
                	<input type="radio" name="status" value="1" <c:if test="${info.status == 0}">checked  </c:if> />停产
                    <input type="radio" name="status" value="0"  <c:if test="${info.status == 1}">checked  </c:if>/>停业整顿
                    <input type="radio" name="status" value="9" <c:if test="${info.status == 9}"> checked  </c:if> />在产
                    
				</div>
            </div>
            <div class="profile-info-row">
				<div class="profile-info-name">附件</div>
				<div class="profile-info-value">
					<tags:files businessId="${info.id }" isDel="1" />
					<tags:fileinputs uploadFileTypes=".jpg.jpeg.bmp.png"
						maxFileSize="60" />
				</div>
			</div>
		</div>
</div>
<div class="clearfix form-actions">
	<div class="col-md-offset-2 col-md-10">
		<button class="btn btn-primary" type="button" onclick="checkForm()">
			<i class="ace-icon fa fa-submit bigger-110"></i> 修改
		</button>
		<button class="btn" type="reset"
			onclick="window.location.href='${root}/pollute/pollutecompany/list/${menuid }/?page=${page}'">
			<i class="ace-icon fa fa-undo bigger-110"></i> 返回
		</button>
	</div>
</div>
</form>
</div>
<script>
	var v;
	$(document).ready(function () {
	    //聚焦第一个输入框
	    //为inputForm注册validate函数
	    v = $("#inputForm").validate();
	    /* $("#name").focus(); */
	});
	function checkForm() {
		/* var apbIds = checkApbInfo();
		if (apbIds == "") {
			showMessage("请至少选择一个基地信息。");
			return false;
		} */
		var isOk=checkFiles();
		if (isOk) {
			$("#inputForm").submit();
		}else{
			v.showErrors();
		}
	}
    
    function checkApbInfo() {
    	var apbInfos = [];
    	$("input[name='info_name']:checked").each(function() {
    		apbInfos.push($(this).val());
    	});
    	return apbInfos.toString();
    }
    function  showMessage(str){
      	$("#fileuplodmessage").show();
      	$("#fileuplodmessage p").html(str).css("color","red");
      	 setTimeout('$("#fileuplodmessage").hide("slow")', 2400);
      	
      }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf"%>