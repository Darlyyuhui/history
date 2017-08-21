<%@ page language="java" pageEncoding="UTF-8"%>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin-top:0;">修改部门</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/system/org/doUpdate" method="post" style="margin:0;padding:0">
		<input type="hidden" name="id" value="${org.id}">
		<input type="hidden" name="menuid" value="${menuid}">
		<div class="row-fluid" >
			<table class="table bukong-table table-border-bot" style="margin-top:-10px">
				<tr>
					<td class="device_td_bg3">上级部门名称：</td>
					<td><tags:xiangxuncache keyName="Department" id="${org.parentid}"></tags:xiangxuncache></td>
				</tr>
				<tr>
					<td class="device_td_bg3">部 门 编 码：</td>
					<td>${org.code}</td>
				</tr>
				<tr>
					<td class="device_td_bg3">部 门 名 称：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="org-name" placeholder="部门名称" specialcharfilter="true" name="name" value="${org.name}" maxlength="20"  class="input-large required" chinese="true"/><font color="red">&nbsp;*</font></td>
				</tr>
				<tr>
					<td class="device_td_bg3">责任人：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="org-principal" placeholder="责任人" maxlength="10" name="principalName" value="${org.principalName}" class="input-large"/>
						<input type="hidden" name="principal" value="${org.principal}" id="principal-id">
						<div id="messageDiv" style="display: none;">
							<font id="info" color="red"></font>
						</div>
					</td>
				</tr>
				<tr>
					<td class="device_td_bg3">联系方式：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="org-mobile" value="${org.mobile}" placeholder="联系方式" maxlength="13" teletest="true" name="mobile" class="input-large"/>
						<div id="messageDiv" style="display: none;">
							<font id="info" color="red"></font>
						</div>
					</td>
				</tr>
				<tr>
					<td class="device_td_bg3">备　　注：</td>
					<td><textarea rows="6" class="span8" maxlength="100" style="min-width:200px;" name="memo">${org.memo}</textarea><span></span></td>
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

		var chooseVal;
		function showList(){
			window.location.href="${root}/system/org/list/${menuid}/";
		}

		function showPoliceDialog(a,b,c){
			var v = window.showModalDialog("${root}/system/policeuser/dialog/show/${menuid}/",c,"dialogWidth=680px;dialogHeight=577px;center=yes;middle=yes;help=no;status=no;scroll=no;resizable=no");
			var ids = chooseVal.split("#")[0];
			var names = chooseVal.split("#")[1];
			$(a).val(ids);
			$(b).val(names);
		}
		
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#org-name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>