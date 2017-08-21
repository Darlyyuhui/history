<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">首页组件--信息</h4>
	<input type="hidden" name="menuid" value="${menuid}"/>
	<input type="hidden" name="page" value="${page}"/>
	<div class="mar_5">
	  <table class="table tingche-table table-border-rl table-border-bot">
		<tr>
			<td class="device_td_bg2">首页组件编号：</td>
			<td>
				${sysIndexModel.code}
			</td> 
			<td class="device_td_bg2">首页组件名称：</td>
			<td>
				${sysIndexModel.name}
			</td>			
		</tr>
		<tr>
			<td class="device_td_bg2">组件行数：</td>
			<td>
				${sysIndexModel.rowcount}
			</td> 
			<td class="device_td_bg2">组件布局：</td>
			<td>
				<c:if test="${'left'==sysIndexModel.layout}">左侧列显示</c:if>
				<c:if test="${'center'==sysIndexModel.layout}">中间列显示</c:if>
				<c:if test="${'right'==sysIndexModel.layout}">右侧列显示</c:if>
			</td>			
		</tr>
		<tr>
				<td class="device_td_bg3">HTML源码：</td>
				<td colspan="3">
					<textarea rows="10" cols="60" readonly>${sysIndexModel.htmlsrc}</textarea>
				</td> 
			</tr>
		<tr>
			<td class="device_td_bg2">是否启用：</td>
			<td>
				<c:if test="${sysIndexModel.isshow=='0'}">不启用</c:if>
				<c:if test="${sysIndexModel.isshow=='1'}">启用</c:if>
			</td> 
			<td class="device_td_bg2"></td>
			<td>
			</td>			
		</tr>
	  </table>
	</div>
	<div class="btn_line">
		<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
	</div>
</div>
<script type="text/javascript">
<!--
	function showList(){
		window.location.href = "${root}/system/sysindexmodel/list/${menuid}/?page=${page}&isgetsession=1";
	}
	
//-->
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>