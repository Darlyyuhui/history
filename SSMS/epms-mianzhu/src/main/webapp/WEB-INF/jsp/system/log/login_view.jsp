<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="page-header">
	<h1>
		登陆日志
		<small> <i class="ace-icon fa fa-angle-double-right"></i> 查看登陆详情 </small>
	</h1>
</div>
<div class="row" style="margin-right: 0;">
	<div class="profile-user-info profile-user-info-striped">
		<div class="profile-info-row">
			<div class="profile-info-name">
				账号：
			</div>
			<div class="profile-info-value">
				${syslog.operatorId}
			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name">
				操作员姓名：
			</div>
			<div class="profile-info-value">
				${syslog.operatorName}
			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name">
				IP地址：
			</div>
			<div class="profile-info-value">
				${syslog.ipAddress}
			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name">
				类型：
			</div>
			<div class="profile-info-value">
				${syslog.type==1?"登录":'退出'}
			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name">
				发生时间：
			</div>
			<div class="profile-info-value">
				<fmt:formatDate value="${syslog.operationTime}" pattern="yyyy-MM-dd HH:mm:SS" />
			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name">
				内容：
			</div>
			<div class="profile-info-value">
				${syslog.content}
			</div>
		</div>
	</div>
	<div class="clearfix form-actions">
		<div class="col-md-offset-1 col-md-9">
			<button class="btn" type="reset" onclick="history.back()">
				<i class="ace-icon fa fa-undo bigger-110"></i> 返回
			</button>
		</div>
	</div>
</div>
