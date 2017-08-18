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
		基地产品信息 <small> <i class="ace-icon fa fa-angle-double-right"></i>
			修改基地产品信息
		</small>
	</h1>
</div>
<div class="row">
	<form id="inputForm" class="form-horizontal"
		action="${root}/apb/apbproduct/doUpdate/${menuid }/${info.id}"
		method="post" enctype="multipart/form-data">
		<input type="hidden" name="menuid" value="${menuid}" /> <input
			type="hidden" name="page" value="${page}" /> <input type="hidden"
			name="id" value="${info.id}" />
		<div class="profile-user-info profile-user-info-striped">
			<div class="profile-info-row">
				<div class="profile-info-name">编号</div>
				<div class="profile-info-value">${info.code }</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">名称</div>
				<div class="profile-info-value">
					<input type="text" id="name" name="name" maxlength="50"
						value="${info.name }" style="min-width: 120px; width: 500px;"
						class="input-large required">
					<span style="color: red">*</span>
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">种类</div>
				<div class="profile-info-value">
					<select id="typeCode" name="typeCode" style="min-width: 120px; width: 500px;"
						class="input-large required">
						<tags:dicothercache typeCode="${CODE_NAME }"
							defaultValue="${info.typeCode}"/>
					</select>
					<span style="color: red">*</span>
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">产品描述</div>
				<div class="profile-info-value">
					<input type="text" id="explain" name="explain" maxlength="200"
						value="${info.explain }" style="min-width: 120px; width: 500px;"
						class="input-large" />
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">所属基地</div>
				<div class="profile-info-value">
					   <table>
	                  <tr>
	                  <c:forEach items="${infoListAll}" var="infoAll"  varStatus="status">
						<td style="padding-right: 5px;padding-left:2px" ><input type="checkbox" name="info_name" value="${infoAll.id }" 
							<c:forEach items="${infoName}" var="info"><c:if test="${info.id== infoAll.id}">
                      checked</c:if></c:forEach>>
                     ${infoAll.name }</td>
                 	
                 	   <c:if test="${(status.index+1)%5==0&&status.index!=0 }">
		                 </tr>
		                 <tr>
		                </c:if>
	                </c:forEach>
	                </table>
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
			onclick="window.location.href='${root}/apb/apbproduct/list/${menuid }/?page=${page}'">
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
	    $("#name").focus();
	});
	function checkForm() {
		var apbIds = checkApbInfo();
		if (apbIds == "") {
			showMessage("请至少选择一个基地信息。");
			return false;
		}
		var isOk=checkFiles();
		if (v.checkForm()&&isOk) {
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