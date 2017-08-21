<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box" style="margin-left:190px;">
	<c:if test="${roadInfo == null}">
	<h4 class="xtcs_h4" style="margin: 0;">道路信息--详情</h4>
	<div class="mar_5">
       <table class="table table-border-bot table-border-rl bukong-table">
			<tr>
				<td class="device_td_bg3">节点名称：</td>
				<td>道路信息</td>
			</tr>
			<tr>
				<td class="device_td_bg3">备注：</td>
				<td>道路信息根节点</td>
			</tr>
		</table>
	</div>
	</c:if>
	<c:if test="${roadInfo != null}">
		<c:if test="${roadInfo.pid == '00'}">
		<h4 class="xtcs_h4" style="margin:0;">道路分组--详情</h4>
		<div class="mar_5">
	       <table class="table table-border-bot table-border-rl bukong-table">
				<tr>
					<td class="device_td_bg3">分组名称：</td>
					<td>${roadInfo.name}</td>
				</tr>
				<tr>
					<td class="device_td_bg3">备注：</td>
					<td>${roadInfo.note}</td>
				</tr>
			</table>
		</div>
		</c:if>
		<c:if test="${roadInfo.pid != '00'}">
		<h4 class="xtcs_h4" style="margin:0;">道路信息详情</h4>
		<div class="mar_5">
	       <table class="table table-border-bot table-border-rl bukong-table">
				<tr>
					<td class="device_td_bg3">道路名称：</td>
					<td>${roadInfo.name}</td>
				</tr>
				<tr>
					<td class="device_td_bg3">上级道路名称：</td>
					<td><tags:xiangxuncache keyName="RoadInfo" id="${roadInfo.pid}"></tags:xiangxuncache></td>
				</tr>
				<tr>
					<td class="device_td_bg3">上传编号：</td>
					<td>${roadInfo.uploadcode}</td>
				</tr>
				<tr>
					<td class="device_td_bg3">道路类型：</td>
					<td><tags:xiangxuncache keyName="Dic" id="${roadInfo.coderoadtype}" typeCode="roadtype"></tags:xiangxuncache></td>
				</tr>
				<tr>
					<td class="device_td_bg3">备注：</td>
					<td>${roadInfo.note}</td>
				</tr>
			</table>
	    </div>
		</c:if>
	</c:if>
</div>
<script type="text/javascript">
<!--
	
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: {
				"name":{
					remote:{
						url:"${root}/system/road/groupNameExist",
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
	});
	
//-->
</script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
