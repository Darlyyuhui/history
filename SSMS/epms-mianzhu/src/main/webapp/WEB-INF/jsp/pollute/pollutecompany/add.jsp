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
        污染企业
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增污染企业
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/pollute/pollutecompany/doAdd/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;" enctype="multipart/form-data">
		<input type="hidden" id="isContinue" name="isContinue" value="0"/>
        <div class="profile-user-info profile-user-info-striped">
           
            <div class="profile-info-row">
                <div class="profile-info-name">名称</div>
                <div class="profile-info-value">
                	<input type="text" id="name" name="name" maxlength="50"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
					<span id="checkNameSpan" style="color: red"></span>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">法人代表</div>
                <div class="profile-info-value">
                	<input type="text" id="name" name="corporation" maxlength="50"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">N（北纬）</div>
                <div class="profile-info-value">
                	<input type="text" id="name"  name="latitude" maxlength="12"  min="0"  max="90"
						style="min-width:120px; width: 350px;" class="input-large decimal4 required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">E（东经）</div>
                <div class="profile-info-value">
                	<input type="text" id="name" name="longitude" maxlength="12"  min="0" max="180"
						style="min-width:120px; width: 350px;" class="input-large decimal4 required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">地址</div>
                <div class="profile-info-value">
                	<input type="text" id="name" name="address" maxlength="100"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					  <span style="color: red">*</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">联系人</div>
                <div class="profile-info-value">
                	<input type="text" id="linkMan" name="linkMan" maxlength="25"
						style="min-width:120px; width: 350px;" class="input-large"/>
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">联系电话</div>
                <div class="profile-info-value">
	                <input type="text" id="linkTel" name="linkTel" maxlength="13"
						style="min-width:120px; width: 350px;" class="input-large "/>
				</div>
            </div> 
              <div class="profile-info-row">
                <div class="profile-info-name">特征污染物</div>
                <div class="profile-info-value">
	                <input type="text" id="features" name="features" maxlength="200" 
						style="min-width:120px; width: 350px;" class="input-large "/>
				</div>
            </div> 
           
            <div class="profile-info-row">
                <div class="profile-info-name">状态</div>
                <div class="profile-info-value">
                	<input type="radio" name="status" value="1" />停产
                    <input type="radio" name="status" value="0"  />停业整顿
                    <input type="radio" name="status" value="9" checked />在产
                    
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
                <button class="btn" type="reset" onclick="window.location.href='${root}/pollute/pollutecompany/list/${menuid }/'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
    </form>
</div>
<script>
	var v;
	var isSub = false;
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        v = $("#inputForm").validate();
        $("#code").focus();
    });
    function continueSubmit() {
    	$("#isContinue").val("1");
    	checkForm();
    }
    function checkForm() {
    	/*var checkStatus = checkStatus();
		if (apbIds == "") {
			showMessage("请选择污染企业状态。");
			return false; 
		}*/
    	 checkCode(); 
    	var isOk=checkFiles();
    	var v = $("#inputForm").validate();
    	if (v.checkForm() && isSub&&isOk) {
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
    function checkCode() {
    	var codeObj = $("#name");
    	if (codeObj.val() != "") {
    		$.ajax({
    			async:false,
    			type:"post",
    			url:"${root}/pollute/pollutecompany/checkCode/"+codeObj.val()+"/",
    			data:"tName=T_POLLUTE_COMPANY&cName=Name",
    			success:function(data) {
    				if (data.result == "ok") {
    					isSub = true;
    					$("#checkNameSpan").empty();
    			
    				}else {
    					$("#checkNameSpan").empty().html("该企业已存在！！！！");
    				}
    			}
    		});
    	}
    }
    function  showMessage(str){
      	$("#fileuplodmessage").show();
      	$("#fileuplodmessage p").html(str).css("color","red");
      	 setTimeout('$("#fileuplodmessage").hide("slow")', 2400);
      	
      }
    
    function checkStatus() {
    	var apbInfos = [];
    	$("input[name='status']:checked").each(function() {
    		apbInfos.push($(this).val());
    	});
    	return apbInfos.toString();
    }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>