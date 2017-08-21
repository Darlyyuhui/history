<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<%
	String pages = request.getParameter("page");
	String pid = request.getParameter("pid");
%>
<div class="conten_box" style="height:660px;overflow:hidden;margin-left:180px;">
	<form id="inputForm" enctype="multipart/form-data" class="form-inline" action="${root}/repository/upload/doUpload" method="post" style="margin:0;">
		<h4 class="xtcs_h4" style="margin: 0;">文件上传</h4>
		<input type="hidden" name="menuid" value="${menuid}" />
		<input type="hidden" name="page" value="<%=pages %>" />
		<input type="hidden" name="pid" value="<%=pid %>" />
		<div class="mar_5">
		  <table class="table table-border-bot table-border-rl tingche-table" id="table" width="100%">
			<tr>
				<td class="device_td_bg2">请选择文件：</td>
				<td>
					<input style="height: 24px;width:250px;margin-right: 0;" class="input-file uniform_on" name="file" id="fileInput" type="file">
				</td>
			</tr>
			<font id="message" color="red" style="font-weight: bolder;text-align: center;"></font>
			<tr>
				<td class="device_td_bg2">备注：</td>
				<td>
					<textarea rows="1" maxlength="100" style="width:50%" name="note" ></textarea>
				</td>
			</tr>
			<c:if test="${not empty message}">
				<tr>
					<td colspan="2" style="text-align: center;width: 20px;">
						<font id="backMesg" color="red" style="font-weight: bolder;">${message}</font>
					</td>
				</tr>
			</c:if>
			<tr id="showLoadingPic" style="display:none">
				<td colspan="2" style="text-align: center;width: 20px;">
					<img src="${root}/images/loading32.gif" />文档上传中,请稍候...
				</td>
			</tr>
		  </table>
		  <div class="mar_l10 mar_r10 mar_t5">
			<font color="red" style="font-weight: bolder;text-align: right;">注：允许的上传文件类型为（.doc .docx .xls .xlsx .ppt .pptx .pdf .txt）</font>
		  </div>
		</div>
		<div class="btn_line">
			<input class="btn btn-info mar_r10" onclick="javascript:return checkForm();" type="submit" value="上传"/>
			<input id="cancel_btn" class="btn" type="button" value="取消" onclick="history.back()" />
		</div>
	</form>
</div>
<script type="text/javascript">
	var allowType = ".doc.docx.xls.xlsx.ppt.pptx.pdf.txt";
	function checkForm(){
		var checkFlag = true;
		var filename = $("#fileInput").val();
		var backMesg = $("#backMesg").html();
		if(filename == ''){
			if(backMesg != ''){
				$("#backMesg").html("");
			}
			$("#message").html("").html("*请选择上传文件！");
			checkFlag = false;
		}else{
			var suffix = filename.substr(filename.lastIndexOf("."), filename.length);
			if(allowType.indexOf(suffix.toLowerCase()) == -1){
				if(backMesg != ''){
					$("#backMesg").html("");
				}
				$("#message").html("").html("*上传文件格式不正确！");
				checkFlag = false;
			}else{
				if(backMesg != ''){
					$("#backMesg").html("");
				}
				$("#message").html("")
				//$("#showLoadingPic").css("display", "");
			}
		}
		if(checkFlag){
			//打开遮罩
			this.parent.$("#uploading_div").modal('show');
		}
		return checkFlag;
	}


	function showList(){
		window.location.href = "${root}/repository/upload/list/${menuid}/";
	}
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate();
	});
	
//-->
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
