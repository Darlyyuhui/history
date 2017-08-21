<%@ page language="java" pageEncoding="UTF-8"%>
	<form id="inputForm" class="form-horizontal" action="${root}/device/ftp/doUpdate" method="post">
		<input type="hidden" name="menuid" value="${menuid}"/>
		<input type="hidden" name="page" value="${page}"/>
		<input type="hidden" name="id" value="${ftpInfo.id}"/>
		<div class="span12">
		<fieldset>
			<legend>
				<small>修改FTP服务器</small>
			</legend>
				
				<div class="control-group">
					<label class="control-label">IP地址：</label>
					<div class="controls">
						<input type="text" id="ftp-ip" placeholder="IP地址" value="${ftpInfo.ftpip}" name="ftpip"  ip="true" class="input-large required" >
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">端口号：</label>
					<div class="controls">
						<input type="text" id="ftp-port" placeholder="端口号" value="${ftpInfo.ftpport}"  name="ftpport" minlength="2" maxlength="6" digits="true" class="input-large required" >
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">FTP用户名：</label>
					<div class="controls">
						<input type="text" id="ftp-user" placeholder="FTP用户名" value="${ftpInfo.ftpuser}" name="ftpuser" maxlength="10" class="input-large required" >
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">FTP密码：</label>
					<div class="controls">
						<input type="text" id="ftp-password" placeholder="FTP密码" value="${ftpInfo.ftppassword}" name="ftppassword" maxlength="10" class="input-large required" >
					</div>
				</div>
				
				
				<div class="control-group">
					<label class="control-label">FTP名称：</label>
					<div class="controls">
						<input type="text" id="ftp-name" placeholder="FTP名称" value="${ftpInfo.name}" name="name" maxlength="12" class="input-large required" >
					</div>
				</div>

		</fieldset>

		<div class="form-actions">
			<input id="submit_btn" class="btn btn-primary" type="submit" value="修改" />
			&nbsp;
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
		</div>
	 </div>
  </form>
<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#ftp-ip").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
</script>
