<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<table width="100%">
  <tr>
    <td height="550px" width="180px"></td>
    <td valign="top">
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin-top:0;">${department.name} 详情</h4>
	<input type="hidden" name="menuid" value="${menuid}" />
	<input type="hidden" name="page" value="${page}" />
	<input type="hidden" name="id" value="${department.id}" />
	<div class="row-fluid">
		<table class="table table-bordered" width="100%" style="margin-top:-10px; margin-bottom:0;">
			<tr align="center" style="text-align: center;">
				<td align="right" style="background:#f8f8f8;font-weight:bold;width: 20%;"><span>部门名称：</span></td>
				<td width="30%">${department.name}</td>
				<td align="right" style="background:#f8f8f8;font-weight:bold;width: 20%;"><span>部门编码：</span></td>
				<td width="30%">${department.code}</td>
			</tr>
			<tr align="center" style="text-align: center;">
				<td align="right" style="background:#f8f8f8;font-weight:bold;">上级部门名称：</td>
				<td><tags:xiangxuncache keyName="Department" id="${department.parentid}"></tags:xiangxuncache></td>
				<td align="right" style="background:#f8f8f8;font-weight:bold;"><span>级&nbsp;&nbsp;别：</span></td>
				<td>${department.level}</td>
			</tr>
			<tr align="center" style="text-align: center;">
				<td align="right" style="background:#f8f8f8;font-weight:bold;"><span>状&nbsp;&nbsp;态：</span></td>
				<td>
					<c:if test="${department.status == '0'}">
						<font color="green">启用</font>
					</c:if>
					<c:if test="${department.status == '1'}">
						<font color="red">未启用</font>
					</c:if>
				</td>
				<td></td>
				<td></td>
			</tr>
		</table>
    </div>
	<div class="btn_line">
		<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
	</div>
</div>
</td>
  </tr>
</table>
<script type="text/javascript">
<!--
	function showList(){
		window.location.href = "${root}/system/org/sublist/${menuid}/";
	}
//-->
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
