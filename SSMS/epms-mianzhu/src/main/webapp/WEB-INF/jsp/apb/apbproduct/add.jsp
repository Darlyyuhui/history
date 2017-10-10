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
<!-- 控制iframe中的页面返回的结果显示开始 -->
<div id="subinfo" class="alert alert-success" style="display: none;">
    <button data-dismiss="alert" class="close">×</button>
    <p id="mesg" align="center"></p>
</div>
 <div id="fileuplodmessage" class="alert alert-success"  style="display:none">
        <button data-dismiss="alert" class="close">×</button>
        <p align="center"></p>
    </div>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
    <p id="alert-content" align="center"></p>
</div>
<div class="page-header">
    <h1>
        基地产品
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增基地产品
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/apb/apbproduct/doAdd/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;" enctype="multipart/form-data">
		<input type="hidden" id="isContinue" name="isContinue" value="0"/>
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">名称</div>
                <div class="profile-info-value">
                	<input type="text" id="name" name="name" maxlength="50"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">种类</div>
                <div class="profile-info-value">
                	<select id="typeCode" name="typeCode" style="min-width:120px; width: 350px;" class="input-large required">
						<tags:dicothercache typeCode="${CODE_NAME }" />
					</select>
                	<span style="color: red">*</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">产品描述</div>
                <div class="profile-info-value">
                	<input type="text" id="explain" name="explain" maxlength="200"
						style="min-width:120px; width: 350px;" class="input-large"/>
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">所属基地</div>
                <div class="profile-info-value">
	                <table>
	                  <tr>
	                  <c:forEach items="${infoListAll}" var="infoAll"  varStatus="status">
	                  
	                   <td style="padding-right: 5px;padding-left:2px" > <input type="checkbox" name="info_name"  value="${infoAll.id }"  > ${infoAll.name }</td>
	                     
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
                	<tags:fileinputs uploadFileTypes=".jpg.jpeg.bmp.png" maxFileSize="60" />
				</div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="button" onclick="checkForm()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                </button>
                <button class="btn btn-primary" type="button" onclick="continueSubmit()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存并继续
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/apb/apbproduct/list/${menuid }/'">
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
    function continueSubmit() {
    	$("#isContinue").val("1");
    	checkForm();
    }
    function checkForm() {
    	var apbIds = checkApbInfo();
		if (apbIds == "") {
			showMessage("请至少选择一个基地信息。");
			return false;
		}
    	var isOk=checkFiles();
    	var v = $("#inputForm").validate();
    	if (v.checkForm() && isOk) {
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
    function  showMessage(str){
      	$("#fileuplodmessage").show();
      	$("#fileuplodmessage p").html(str).css("color","red");
      	 setTimeout('$("#fileuplodmessage").hide("slow")', 2400);
      	
      }
    
    function checkApbInfo() {
    	var apbInfos = [];
    	$("input[name='info_name']:checked").each(function() {
    		apbInfos.push($(this).val());
    	});
    	return apbInfos.toString();
    }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>