<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">评估规则-详情</h4>
	<input type="hidden" name="menuid" value="${menuid}"/>
	<input type="hidden" name="page" value="${page}"/>
	<div class="mar_5">
	  <table class="table tingche-table table-border-rl table-border-bot">
		<tr>
			<td class="device_td_bg2">规则名称：</td>
			<td>
				${rule.name}
			</td> 
		</tr>
		<tr>
			<td class="device_td_bg2">规则编号：</td>
			<td>
				${rule.code}
			</td> 
		</tr>
		<tr>
			<td class="device_td_bg2">规则描述：</td>
			<td>
				${rule.describe}
			</td> 
		</tr>
		<tr>
			<td class="device_td_bg2">规则分值：</td>
			<td>
				${rule.score}
			</td> 
		</tr>
		<tr>
			<td class="device_td_bg2">备注：</td>
			<td>
				${rule.note}
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
		window.location.href = "${root}/alarm/workrule/list/${menuid}/?page=${page}&isgetsession=1";
	}
//-->
</script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>