<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">事件类型-详情</h4>
	<input type="hidden" name="menuid" value="${menuid}"/>
	<input type="hidden" name="page" value="${page}"/>
	<div class="mar_5">
	  <table class="table tingche-table table-border-rl table-border-bot">
		<tr>
			<td class="device_td_bg2">类型名称：</td>
			<td>
				${eventtype.name}
			</td> 
		</tr>
		<tr>
			<td class="device_td_bg2">类型编号：</td>
			<td>
				${eventtype.code}
			</td> 
		</tr>
		<tr> 
			<td class="device_td_bg2">事件级别：</td>
			<td>
				${eventtype.relationLevel}
			</td> 
		</tr>
		<tr>
			<td class="device_td_bg2">类别：</td>
			<td>
				<c:if test='${eventtype.type=="all"}'>公共</c:if>
				<c:if test='${eventtype.type=="device"}'>卡口</c:if>
				<c:if test='${eventtype.type=="server"}'>服务器</c:if>
				<c:if test='${eventtype.type=="database"}'>数据库</c:if>
				<c:if test='${eventtype.type=="ftp"}'>FTP</c:if>
				<c:if test='${eventtype.type=="project"}'>平台</c:if>
				<c:if test='${eventtype.type=="cabinet"}'>机柜</c:if>	
			</td> 
		</tr>
		<tr>
			<td class="device_td_bg2">备注：</td>
			<td>
				${eventtype.note}
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
		window.location.href = "${root}/alarm/eventtype/list/${menuid}/?page=${page}&isgetsession=1";
	}
//-->
</script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>