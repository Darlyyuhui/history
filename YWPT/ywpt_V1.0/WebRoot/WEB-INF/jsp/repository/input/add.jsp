<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<%
	String pages = request.getParameter("page");
	String pid = request.getParameter("pid");
%>
<div class="conten_box" style="margin-right:0; margin-left:180px;">
	<form id="inputForm" class="form-inline" action="${root}/repository/input/doAdd" method="post" style="margin:0; padding:0;">
		<input type="hidden" name="menuid" value="${menuid}" />
		<input type="hidden" name="page" value="<%=pages %>" />
		<input type="hidden" name="pid" value="<%=pid %>" />
		<div class="mar_5">
		  <table class="table table-border-bot table-border-rl tingche-table" width="100%">
			<tr>
				<td class="device_td_bg3">文件名称：</td>
				<td>
					<input style="width:50%;" type="text" id="name" name="name" class="required" minlength="3" maxlength="30">
					<font color="red">*</font>
				</td> 
			</tr>
			<tr>
				<td class="device_td_bg3">备注：</td>
				<td>
					<textarea rows="1" maxlength="100" style="width:50%" name="note" ></textarea>
				</td>
			</tr>
			<tr>
				<td class="device_td_bg3">文件内容：</td>
				<td>
					<textarea rows="3" maxlength="2000" style="width:660px" id="txt_content" name="context" ></textarea>
				</td>
			</tr>
		  </table>
		</div>
		<div class="btn_line" style="margin-right:0; margin-left:5px;">
			<input class="btn btn-info mar_r10" onclick="javascript:return checkForm();" type="submit" value="保存" />
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
		</div>
	</form>
</div>
<script type="text/javascript">

	function checkForm(){
		var flag = true;
		var name = $("#name").val();
		var s = $("#edui1_wordcount").html();
		if(name == ''){
			alert("文件名称不允许为空");
			flag = false;
		}else if(s == ''){
			alert("文件内容不允许为空");
			flag = false;
		}else{
			if(s == '<span style="color:red;">字数超出最大允许值，服务器可能拒绝保存！</span>'){
				alert("字数超出最大允许值");
				flag = false;
			}
		}
		return flag;
	}

	function showList(){
		window.location.href = "${root}/repository/input/list/${menuid}/";
	}
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: {
				"name":{
					remote:{
						url:"${root}/repository/input/nameExist",
						type:"post",
						data:{
							filename:function(){
								return $("#name").val();
							}
						}
					}
				}
			}
		});
		
		//实例化编辑器
	    var options = {
	        imageUrl:"${root}/forward/ueditor/imageUp/",
	        imagePath:"${root}/forward/ueditor/imageUp/",

	        scrawlUrl:"${root}/forward/ueditor/scrawlUp/",
	        scrawlPath:"http://",

	        fileUrl:"${root}/forward/ueditor/fileUp/",
	        filePath:"http://",

	        catcherUrl:"${root}/forward/ueditor/getRemoteImage/",
	        catcherPath:UEDITOR_HOME_URL + "php/",

	        imageManagerUrl:"${root}/forward/ueditor/imageManager/",
	        imageManagerPath:"http://",

	        wordImageUrl:"${root}/forward/ueditor/imageUp/",
	        wordImagePath:"http://", 
			toolbars:[
			            [   'undo', 'redo', '|',
			                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
			                'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
			                'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
			                'directionalityltr', 'directionalityrtl', 'indent', '|',
			                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
			                'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
			                'insertimage', 'scrawl','attachment','insertframe','webapp', 'pagebreak', 'template', 'background', '|',
			                'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
			                'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', '|',
			                'print', 'preview', 'searchreplace']
			],
	        lang:/^zh/.test(navigator.language || navigator.browserLanguage || navigator.userLanguage) ? 'zh-cn' : 'en',
	        langPath:UEDITOR_HOME_URL + "lang/",

	        webAppKey:"9HrmGf2ul4mlyK8ktO2Ziayd",
	        initialFrameWidth:725,
	        initialFrameHeight:299,
	        autoHeightEnabled:false,
	        focus:false
	    };

	    var editor = new UE.ui.Editor(options); 
	    editor.render("txt_content"); 

	    //1.2.4以后可以使用一下代码实例化编辑器

	   //UE.getEditor('myEditor')
	});
	
</script>
<script src="${root}/compnents/ueditor1.2.6.1/ueditor.config.js" type="text/javascript"></script>
<script src="${root}/compnents/ueditor1.2.6.1/ueditor.all.min.js" type="text/javascript"></script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
