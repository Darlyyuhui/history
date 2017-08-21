<%@ page language="java" pageEncoding="UTF-8"%>
<script src="<c:url value='/js/jquery.form.js'/>" type="text/javascript"></script>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<div class="alert alert-block pull-top alert-success" id="alert-div" style="display:none;">
	<p id="alert-content" align="center"></p>
</div>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">添加数据</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/system/dic/doAdd" method="post" style="margin:0;padding:0">
		<input type="hidden" name="menuid" value="${param.menuid}"/>
		<input type="hidden" name="groupid" value="${param.groupid}"/>
		<input type="hidden" name="search_type" value="${param.type}"/>
		<div class="mar_5">
		  <table class="table bukong-table table-border-bot table-border-rl">
		    <tr>
				<td class="device_td_bg3">名　　称：</td>
				<td><input style="width:370px;" minlength="2" maxlength="20" type="text" id="dic-name" placeholder="名称" name="name" class="required" specialcharfilter="true"> <font color="red">*</font></td>
			</tr>
		    <tr>
				<td class="device_td_bg3">类　　型：</td>
				<td><select style="width:380px;padding:4px;" id="dic_type" name="type" onchange="changeType()"  class="required">
				  	　<option value="">请选择</option>
				  	 <c:forEach items="${dic_sec}" var="keyValue">
				  		<option value="${keyValue.key}" gencode="${keyValue.gencode}" ${keyValue.key==param.type?'selected':''}>${keyValue.name}</option>
					　</c:forEach>
				  　</select> <font color="red">*</font>
				</td>
			</tr>
		    <tr> 
				<td class="device_td_bg3">编　　码：</td>
				<td><input style="width:370px;" type="text" id="dic-code" placeholder="编码" name="code" maxlength="8" class="required"  ${(fn:length(param.type) gt 0)?"":"readonly='readonly'"}> <font color="red">*</font></td>
			</tr>
		    <tr>
				<td class="device_td_bg3">备　　注：</td>
				<td><textarea rows="5" maxlength="100" id="dic-remark" class="span8" name="remark"></textarea> <span></span></td>
			</tr>
		  </table>
		</div>
		<div class="btn_line">
			<input id="next_btn" class="btn btn-primary mar_r10" type="button" onclick="saveAndNext()" value="保存并继续" />
			<input id="submit_btn" class="btn btn-info mar_r10" type="submit" value="保存" />
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="goBack()" />
		</div>
    </form>
</div>
<script>
    var isauto = false;
	function changeType(){
		var opt =$("#dic_type option:selected");
		var gen =($(opt).attr("gencode"));
		//表示系统的编码是自动生成的
		if(gen=='auto'){
			isauto=true;
			$.ajax({
				   type: "POST",
				   url: "${root}/system/dic/nextCode",
				   data:{type:$(opt).val()},
				   cache:false,
				   success: function(data){
					   $("#dic-code").val(data);
				   }
			}); 
		}
		$("#dic-code").removeAttr("readonly")
	}
	
	function goBack(){
		window.location.href ="${root}/system/dic/list/${param.groupid}/${menuid}/";
	}
	
	function saveAndNext(){
		var v = $("#inputForm").validate();
		var haserror = v.checkForm();
		var options = {
				success : function(data) {
					showMessage("添加成功。");
					$("#dic-name").val("");
					$("#dic-code").val("");
					$("#dic-remark").val("");
					changeType();
				}
		};
		if(haserror){
			$("#inputForm").ajaxSubmit(options);
		}
	}
	

	$(document).ready(function() {
		//聚焦第一个输入框
		$("#user-name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			 rules: {
					"code":{
						remote:{
							url:"${root}/system/dic/codeExist",
							type:"post",
							data:{
								code:function(){
									return $("#dic-code").val();
								},
								type:function(){
									return $("#dic_type").val();
								}
							}
						
						}
					}
			}
			
		});
	});
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
