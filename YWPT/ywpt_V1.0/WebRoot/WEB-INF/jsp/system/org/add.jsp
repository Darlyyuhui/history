<%@ page language="java" pageEncoding="UTF-8"%>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin-top:0;">添加新部门</h4>
    <form id="inputForm" class="form-horizontal" action="${root}/system/org/doAdd/${menuid}/" method="post" style="margin:0;padding:0">
		<input type="hidden" name="parentid" value="${org.id}">
		<div class="mar_5" style="height:480px;">
			<table class="table bukong-table table-border-bot table-border-rl">
				<tr>
					<td class="device_td_bg3">上级部门名称：</td>
					<td><tags:xiangxuncache keyName="Department" id="${org.id}"></tags:xiangxuncache></td>
				</tr>
				<tr>
					<td class="device_td_bg3">部门编码：</td>
					<td>
						<div class="btn-group">
						 	<button class="btn dropdown-toggle" data-toggle="dropdown">行政区域编码<span class="caret"></span></button>
		              		<ul class="dropdown-menu">
				                <c:forEach items="${plateArea}" var="areacode">
				                  <li><a href="javascript:void(0)" onclick="areaClick('${areacode.code}')";>${areacode.name}</a></li>
				                </c:forEach>
			              	</ul>
			               	<input type="text" id="codeStart" style="width:50px" readonly name="areaCode"  class="input-large required"/>
						</div>
						<input style="width:50px;margin-left:5px;" value="${code}" maxlength="6" minlength="6" type="text" id="org-code" placeholder="部门编码" name="code" class="input-small required" digits="true" />
						<font color="red">&nbsp;*</font>
					</td>
				</tr>
				<tr>
					<td class="device_td_bg3">部门名称：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="org-name" placeholder="部门名称" maxlength="20" name="name" specialcharfilter="true" class="input-large required" chinese="true"/><font color="red">&nbsp;*</font>
						<div id="messageDiv" style="display: none;">
							<font id="info" color="red"></font>
						</div>
					</td>
				</tr>
				<tr>
					<td class="device_td_bg3">责任人：</td>
					<td>
						<input style="width:50%; min-width:200px;" type="text" id="org-principal" placeholder="责任人" maxlength="10" name="principalName" class="input-large" />
						<input type="hidden" name="principal" id="principal-id">
					</td>
				</tr>
				
					<tr>
					<td class="device_td_bg3">联系方式：</td>
					<td>
						<input style="width:50%; min-width:200px;" type="text" id="org-mobile" placeholder="联系方式" teletest="true"  maxlength="13" name="mobile" class="input-large"/>
					</td>
				</tr>
				<tr>
					<td class="device_td_bg3">备注：</td>
					<td><textarea rows="6" class="span8" style="min-width:200px;" maxlength="100" name="memo"></textarea><span></span></td>
				</tr>
			</table>
		</div>
		<div class="btn_line">
	        <button class="btn btn-info mar_r10" type="submit">保存</button>
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
	</form>
</div>
<script>
		function showList(){
			window.location.href="${root}/system/org/list/${menuid}/";
		}
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#org-name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				 rules: {
					"code":{
						remote:{
							url:"${root}/system/org/codeExist",
							type:"post",
							data:{
								code:function(){
									return $("#org-code").val();
								}
							}
						
						}
					}
				}
			});
			$("#code-div span.error").height(50).width(200);
		});
		function areaClick(code){
			var codestart = $("#codeStart");
			codestart.attr("value", code);
		}
	</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>