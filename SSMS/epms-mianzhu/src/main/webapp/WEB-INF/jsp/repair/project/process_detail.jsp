<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<div class="row">
	<div class="profile-user-info profile-user-info-striped">
		<div class="profile-info-row">
			<div class="profile-info-name">施工月份</div>
			<div class="profile-info-value">${info.workDate }</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name">施工单位</div>
			<div class="profile-info-value">${info.workDept }</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name">施工内容</div>
			<div class="profile-info-value">${info.workContent }</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name">施工附件</div>
			<div class="profile-info-value">
				<c:if test="${not empty info.id }">
				<tags:files businessId="${info.id }" width="800" height="520" />
				</c:if>
			</div>
		</div>
	</div>
</div>