<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<SCRIPT type="text/javascript">
<!--
	function roadTypeClick(code){
		$("#coderoadtype").attr("value", code);
	}
	
	// add by kouyunhao 添加验证，阻止非法提交。
	function checkForm(){
		var checkFlag = true;
		var coderoadtype = $("#coderoadtype").val();
		var codeRoadDh = $("#codeRoadDh").val();
		var codeRoadZh = $("#codeRoadZh").val();
		var codeRoadMi = $("#codeRoadMi").val();
		if(coderoadtype != '' && codeRoadDh != '' && codeRoadZh != '' && codeRoadMi != ''){
			var uploadcode = coderoadtype + codeRoadDh + codeRoadZh + codeRoadMi;
			check_selectedval(uploadcode);
			if($("#info_div").html() != ''){
				checkFlag = false;
			}
		}
		return checkFlag;
	}
	
	function check_selectedval(value){
		//add by kouyunhao 2014-04-25 添加判断，解决直接修改无法提交bug。
		var oldcode = $("#old_uploadcode").val();
		if(value != oldcode){
			var url="${root}/system/road/uploadcodeExist/"+value+"/";
			$.ajax( {
				type : 'GET',
				url : url,
				async : false,
				dataType : "json",
				success:function(data){
					if(data.message != ''){
						$("#info_div").css("display", "");
						$("#info_div").html("").html('<font color="red">'+data.message+'</font>');
					}else{
						$("#info_div").html("");
						$("#info_div").css("display", "none");
					}
				}
			});
		}
	}
//-->
</SCRIPT>
<style type="text/css">
.bh1, .bh2, .bh3, .bh4 {
	/*
	float:left;
	position:relative;
	*/
	margin:2px 0;
}
.bh1 .error, .bh2 .error, .bh3 .error, .bh4 .error {
	/*
	position:absolute;
	left:5px;
	top:30px;
	*/
	
}

</style>
<div class="conten_box" style="margin-left:190px;">
	<h4 class="xtcs_h4" style="margin:0;">道路--修改</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/system/road/doUpdate" method="post"  style="margin-bottom:0;padding:0;">
		<input type="hidden" name="id" value="${roadInfo.id}">
		<input type="hidden" name="menuid" value="${menuid}">
	    <div class="mar_5">
	       <table class="table table-border-bot table-border-rl bukong-table">
				<tr>
					<td class="device_td_bg3">上级道路名称：</td>
					<td><tags:xiangxuncache keyName="RoadInfo" id="${roadInfo.pid}"></tags:xiangxuncache></td>
				</tr>
				<tr>
					<td class="device_td_bg3">道路名称：</td>
					<td>
					<input type="text" id="roadInfo-name" placeholder="道路名称" maxlength="30" name="name"  specialcharfilter="true" value="${roadInfo.name}" class="input-large required">
					</td>
				</tr>
				<c:if test="${roadInfo.pid!='00'}">
				<tr>
					<td class="device_td_bg3">上传编号：</td>
					<td>
					<div class="input-prepend">
               		  <div class="bh1">
               		  <div class="btn-group">
					    <button class="btn dropdown-toggle" data-toggle="dropdown" style="width:130px;">
					      道路类型<span class="caret"></span>
					    </button>
					    <ul class="dropdown-menu">
						    <c:forEach items="${roadTypeList}" var="roadType">
						    <li><a onclick="roadTypeClick('${roadType.code}')">${roadType.name}</a></li>
							</c:forEach>
						</ul>
					 	</div>
					 	<input type="text" id="coderoadtype" style="width:35px" readonly name="coderoadtype" value="${roadInfo.coderoadtype}" class="input-large required" digits="true" ><font color="red">&nbsp;*</font>
               		  </div>
               		  
               		  <div class="bh2">
               		     <div class="btn-group">
						    <button class="btn dropdown-toggle" data-toggle="dropdown" style="width:130px;">道路代号：</button>
						 </div>
					 	<input type="text" id="codeRoadDh" style="width:35px"  name="codeRoadDh"  value="${roadInfo.codeRoadDh}" minlength="4" maxlength="4" class="input-large required" digits="true" ><font color="red">&nbsp;*</font>
               		  </div>
               		  
               		  <div class="bh3">
               		    <div class="btn-group">
						    <button class="btn dropdown-toggle" data-toggle="dropdown" style="width:130px;">公路里程桩号：</button>
					 	</div>
					 	<input type="text" id="codeRoadZh" style="width:35px"  name="codeRoadZh"  value="${roadInfo.codeRoadZh}" minlength="4" maxlength="4" class="input-large required" digits="true" ><font color="red">&nbsp;*</font>
               		  </div>
               		  
               		  <div class="bh4">
               		    <div class="btn-group">
						  <button class="btn dropdown-toggle" data-toggle="dropdown" style="width:130px;">超过：</button>
					 	</div>
					 	<input type="text" id="codeRoadMi" style="width:35px"  name="codeRoadMi"  value="${roadInfo.codeRoadMi}" minlength="3" maxlength="3" class="input-large required" digits="true" ><font color="red">&nbsp;*</font>
					 	<div class="btn-group">
						  <button class="btn dropdown-toggle" data-toggle="dropdown">米</button>
					 	</div>
               		  </div>
					</div>
					<input type="hidden" id="old_uploadcode" value="${roadInfo.uploadcode}"> 
					<div id="info_div" style="display: none;"></div>
		           </td>
				</tr>
				</c:if>
				<tr>
					<td class="device_td_bg3">备　　注：</td>
					<td><textarea rows="6" class="span8" style="min-width:200px;" maxlength="100" name="note">${roadInfo.note}</textarea><span></span>					
					</td>
				</tr>
			</table>
	    </div>
		<div class="btn_line">
			<button class="btn btn-info mar_r10" onclick="javascript: return checkForm();" type="submit">保存</button>
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
	</form>
</div>
<script type="text/javascript">
	function showList(){
	    window.location.href = "${root}/system/road/view/${roadInfo.id}/${menuid}/";
    }
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#roadInfo-name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate();
	});
</script>
