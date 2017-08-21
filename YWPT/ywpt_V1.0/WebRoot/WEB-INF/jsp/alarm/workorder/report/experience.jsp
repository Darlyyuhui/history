<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/ueditor1.2.6.1/ueditor.config.js" type="text/javascript"></script>
<script src="${root}/compnents/ueditor1.2.6.1/ueditor.all.min.js" type="text/javascript"></script>
<div class="conten_box" style="height: 670px;overflow: auto;">
	<h4 class="xtcs_h4" style="margin:0;">工单详情</h4>
	<div class="mar_5">
	  <table class="table tingche-table table-border-rl table-border-bot" width="100%">
		<tr>
			<td class="device_td_bg2" style="width: 10%">设备名称：</td>
			<td style="width: 22%"><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:22px; white-space:nowrap;" title="${workorder.devicename}">${workorder.devicename}</div></td> 
			<td class="device_td_bg2" style="width: 10%">设备编号：</td>
			<td style="width: 22%">${workorder.devicecode}</td>			
			<td class="device_td_bg2" style="width: 10%">设备IP：</td>
			<td style="width: 26%">${workorder.deviceip}</td> 
		</tr>
		<tr>
			<td class="device_td_bg2">设备类型：</td>
			<td>${workorder.devicetype}</td>			
			<td class="device_td_bg2">位置信息：</td>
			<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:22px; white-space:nowrap;" title="${workorder.position}">${workorder.position}</div></td>
			<td class="device_td_bg2">所属部门：</td>
			<td><tags:xiangxuncache keyName="Department" id="${workorder.orgid}"></tags:xiangxuncache></td>			
		</tr>
		<tr>
			<td class="device_td_bg2">派发人：</td>
			<td><tags:xiangxuncache keyName="username_cache" id="${workorder.assignaccount}"></tags:xiangxuncache></td>
			<td class="device_td_bg2">派发时间：</td>
			<td><fmt:formatDate value="${workorder.assigntime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>	
			<td class="device_td_bg2">工单状态：</td>
			<td>${workorder.statusHtml}
			</td>
		</tr>
	  </table>
	</div>
	<form id="inputForm" class="form-inline" action="${root}/alarm/workreport/doExperience" method="post" style="margin:0; padding:0;">
		<h4 class="xtcs_h4" style="margin: 0;">生成经验</h4>
		<input type="hidden" name="menuid" value="${menuid}" />
		<input type="hidden" name="page" value="${page}" />
		<input type="hidden" name="id" value="${workorder.id}" />
		<div class="mar_5" style="overflow: auto;">
		  <table class="table table-border-bot table-border-rl tingche-table" width="100%">
			<tr>
				<td class="device_td_bg2" style="width: 10%;">经验简述：</td>
				<td>
					<input style="width:30%;" type="text" id="name" name="name" class="required" maxlength="30">
					<font color="red">*（30字以内）</font>
				</td> 
			</tr>
			<tr>
				<td class="device_td_bg2" style="width: 10%;">经验类别：</td>
				<td>
					<select style="width:31%;" id="pid" name="pid" class="required" placeholder="经验类别">
						<option value="">请选择</option>
						<c:forEach items="${catalogList}" var="catalog">
							<option value="${catalog.id}">${catalog.name}</option>
						</c:forEach>
					</select>
					<font color="red">*</font>
				</td> 
			</tr>
			<tr>
				<td class="device_td_bg2">经验内容：</td>
				<td>
					<textarea rows="3" maxlength="2000" style="width:660px" id="txt_content" name="context"></textarea>
				</td>
			</tr>
		  </table>
		</div>
		<div class="btn_line">
			<input class="btn btn-info mar_r10" onclick="javascript:return checkForm();" id="submitbut" type="submit" value="保存" />
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
		</div>
	</form>
</div>
<script type="text/javascript">
	function checkForm(){
		var flag = true;
		var name = $("#name").val();
		var pid = $("#pid").val();
		var s = $("#edui1_wordcount").html();
		if(name == ''){
			alert("经验简述不允许为空");
			flag = false;
		}else if(pid == ''){
			alert("经验类别不允许为空");
			flag = false;
		}else if(s == ''){
			alert("经验内容不允许为空");
			flag = false;
		}else{
			if(s == '<span style="color:red;">字数超出最大允许值，服务器可能拒绝保存！</span>'){
				alert("字数超出最大允许值");
				flag = false;
			}
		}
		if(flag){
			$("#submitbut").attr("disabled",true);
			$("#inputForm").submit();
		}
		return flag;
	}

	$(document).ready(function() {
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate();
		
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
	        initialFrameWidth:860,
	        initialFrameHeight:200,
	        autoHeightEnabled:false,
	        focus:false
	    };

	    var editor = new UE.ui.Editor(options); 
	    editor.render("txt_content"); 

	    //1.2.4以后可以使用一下代码实例化编辑器

	   //UE.getEditor('myEditor')	
	});

</script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>