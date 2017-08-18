<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
 <div id="fileuplodmessage" class="alert alert-success"  style="display:none">
        <button data-dismiss="alert" class="close">×</button>
        <p align="center"></p>
    </div>
<div class="page-header">
    <h1>
        行业标准
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            行业标准
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/bs/standardIndustry/doUpdate/${menuid }/${info.id}" method="post"
    enctype="multipart/form-data"   >
    <input type="hidden" name="menuid" value="${menuid}"/>
        <input type="hidden" name="page" value="${page}"/>
        <input type="hidden" name="id" value="${info.id}"/>
        <div class="profile-user-info profile-user-info-striped">
          <%--   <div class="profile-info-row">
                <div class="profile-info-name" style="width: 180px;">上级类型名称</div>
                <div class="profile-info-value">
                ${pname }
                <input type="hidden" name="parentId" value="${info.pid }" />
                </div>
            </div> --%>
            <div class="profile-info-row">
                <div class="profile-info-name">编号</div>
                <div class="profile-info-value">
                	${info.code }
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">标准名称</div>
                <div class="profile-info-value">
                	<input type="text" id="name" name="name" maxlength="50" value="${info.name }"
                       style="min-width:120px;width: 500px;" class="input-large required">
					<span style="color: red">*</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">样品</div>
                <div class="profile-info-value">
                <input type="text" id="sample" name="sample" maxlength="20" value="${info.sample }"
                     style="min-width:120px;width: 500px;" class="input-large ">
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">参数</div>
                <div class="profile-info-value">
                	<input type="text" id="params" name="params" maxlength="50" value="${info.params }"
						style="min-width:120px; width: 500px;" class="input-large"/>
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">影响因子</div>
                <div class="profile-info-value">
                	<input type="text" id="factor" name="factor" maxlength="50"  value="${info.factor }"
						style="min-width:120px; width: 500px;" class="input-large"/>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">标准值</div>
                <div class="profile-info-value">
                	<input type="text" id="standardVal" name="standardVal" maxlength="9"  min="0"   value="${info.standardVal }"
						style="min-width:120px; width: 500px;" class="input-large  number"/>
				</div>
            </div>
            
            <div class="profile-info-row">
               <div class="profile-info-name">单位</div>
               <div class="profile-info-value">
                	<input type="text" id="unit" name="unit" maxlength="20" value="${info.unit }"
						style="min-width:120px; width: 500px;" class="input-large"/>
			</div>
           </div>
            <div class="profile-info-row">
                <div class="profile-info-name">附件</div>
				<div class="profile-info-value">
					<tags:files businessId="${info.id }" isDel="1" />
					<tags:fileinputs uploadFileTypes=".pdf" maxFileSize="60" />
				</div>
            </div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="button"  onclick="checktosubmit()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 修改
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/bs/standardIndustry/list/${menuid }/?page=${page}'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
    </form>
</div>
<script>
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        $("#inputForm").validate();
    });
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