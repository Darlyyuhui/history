<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box" style="height:660px;overflow:hidden;margin-left:180px;">
	<form id="inputForm" class="form-inline" action="${root}/property/deviceftp/doUpdate" method="post" style="margin:0;">
		<h4 class="xtcs_h4" style="margin: 0;">文件服务器 信息修改</h4>
		<input type="hidden" name="menuid" value="${menuid}"/>
		<input type="hidden" name="page" value="${page}"/>
		<input type="hidden" name="id" value="${ftpInfo.id}"/>
		<div class="mar_5">
		  <table class="table tingche-table table-border-rl table-border-bot">
			<tr>
				<td class="device_td_bg3">服务器名称：</td>
				<td>
					<input style="width:160px;" type="text" id="name" name="name" value="${ftpInfo.name}" class="required" maxlength="30" class="required">
					<font color="red">*</font>
				</td> 
				<td class="device_td_bg3">服务器IP：</td>
				<td>
					<input style="width:160px;" type="text" id="ftpip" name="ftpip" value="${ftpInfo.ftpip}" maxlength="15" class="required" ip="true">
                    <font style="color: red;"> *</font>
				</td>			
			</tr>
			<tr>
				<td class="device_td_bg3">上行端口：</td>
				<td>
					<input style="width:160px;" type="text" id="ftpport" name="ftpport" value="${ftpInfo.ftpport}" class="required" minlength="2" maxlength="6" digits="true">
					<font color="red">*</font>（FTP上传使用）
				</td> 
				<td class="device_td_bg3">下行端口：</td>
				<td>
					<input style="width:160px;" type="text" id="httpport" name="httpport" value="${ftpInfo.httpport}" minlength="2" maxlength="6" digits="true">
                    （HTTP下载使用）
				</td>			
			</tr>
			<tr>
				<td class="device_td_bg3">上行账号：</td>
				<td>
					<input style="width:160px;" type="text" id="ftpuser" name="ftpuser" value="${ftpInfo.ftpuser}" class="required" maxlength="10" specialcharfilter="true">
					<font color="red">*</font>
				</td> 
				<td class="device_td_bg3">下行虚拟目录：</td>
				<td>
					<input style="width:160px;" type="text" id="dirname" name="dirname" value="${ftpInfo.dirname}" maxlength="11" class="required">
                    <font style="color: red;"> *</font>
				</td>			
			</tr>
			<tr>
				<td class="device_td_bg3">上行密码：</td>
				<td>
					<input style="width:160px;" type="text" id="ftppassword" name="ftppassword" value="${ftpInfo.ftppassword}" password="true" maxlength="12" class="required">
					<font color="red">*</font>
				</td>
				<td></td>
				<td>
				</td>
			</tr>
		  </table>
		</div>
		<div class="btn_line">
			<button class="btn btn-info mar_r10" type="submit">保存</button>
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
	</form>
</div>
<script type="text/javascript">
<!--
	function showList(){
		window.location.href = "${root}/property/deviceftp/list/${menuid}/?page=${page}&isgetsession=1";
	}
	$(document).ready(function() {
			//聚焦第一个输入框
			$("#ftpip").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					ftppassword:{
						rangelength:[6,12]
					}
				}
			});
		});
//-->
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>