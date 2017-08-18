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
 <div id="fileuplodmessage" class="alert alert-success"  style="display:none">
        <button data-dismiss="alert" class="close">×</button>
        <p align="center"></p>
    </div>
<!-- 控制iframe中的页面返回的结果显示开始 -->
<div id="subinfo" class="alert alert-success" style="display: none;">
    <button data-dismiss="alert" class="close">×</button>
    <p id="mesg" align="center"></p>
</div>
<div class="page-header">
    <h1>
        行业标准
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增行业标准
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/bs/standardIndustry/doAdd/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;" enctype="multipart/form-data">
		<input type="hidden" id="isContinue" name="isContinue" value="0"/>
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">编号</div>
                <div class="profile-info-value">
                	<input type="text" id="code" name="code" maxlength="20"
						style="min-width:120px; width: 500px;" class="input-large required"/>
					<span style="color: red">*</span>
					<span id="checkCodeSpan" style="color: red"></span>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">标准名称</div>
                <div class="profile-info-value">
                	<input type="text" id="name" name="name" maxlength="50"
						style="min-width:120px; width: 500px;" class="input-large required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">样品</div>
                <div class="profile-info-value">
                <input type="text" id="sample" name="sample" maxlength="20"
                     style="min-width:120px; width: 500px;" class="input-large ">
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">参数</div>
                <div class="profile-info-value">
                	<input type="text" id="params" name="params" maxlength="50"
						style="min-width:120px; width: 500px;" class="input-large"/>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">影响因子</div>
                <div class="profile-info-value">
                	<input type="text" id="factor" name="factor" maxlength="50"
						style="min-width:120px; width: 500px;" class="input-large"/>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">标准值</div>
                <div class="profile-info-value">
                	<input type="text" id="standardVal" name="standardVal" maxlength="9"  min="0"  
						style="min-width:120px; width: 500px;" class="input-large number"/>
				</div>
            </div>
            
            <div class="profile-info-row">
               <div class="profile-info-name">单位</div>
               <div class="profile-info-value">
                	<input type="text" id="unit" name="unit" maxlength="20"
						style="min-width:120px; width: 500px;" class="input-large "/>
			</div>
           </div>
        
           
              <div class="profile-info-row">
                <div class="profile-info-name">附件</div>
				<div class="profile-info-value">
                	<tags:fileinputs uploadFileTypes=".pdf" maxFileSize="60" />
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
                <button class="btn" type="reset" onclick="window.location.href='${root}/bs/standardIndustry/list/${menuid }/?page=${page}'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
    </form>
</div>
<script>
	var isSub = false;
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        $("#inputForm").validate();
        $("#name").focus();
    });
    function continueSubmit() {
    	$("#isContinue").val("1");
    	checkForm();
    }
    function checkForm() {
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
    	var codeObj = $("#code");
    	if (codeObj.val() != "") {
    		$.ajax({
    			async:false,
    			type:"post",
    			url:"${root}/bs/standardIndustry/checkCode/"+codeObj.val()+"/",
    			data:"tName=T_STANDARD_INDUSTRY&cName=CODE",
    			success:function(data) {
    				if (data.result == "ok") {
    					isSub = true;
    					$("#checkCodeSpan").empty();
    			
    				}else {
    					$("#checkCodeSpan").empty().html(data.message);
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
 function  checktosubmit(){
	   var isOk=checkFiles();
  	if(isOk){
  		$("#inputForm").submit();
  	}else{
  		return;
  	}
  }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>