<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box" style="margin-left: 180px;">
	<h4 class="xtcs_h4" style="margin:0;">服务器信息-详情</h4>
	<input type="hidden" name="menuid" value="${menuid}"/>
	<input type="hidden" name="page" value="${page}"/>
	<div class="mar_5">
	  <table class="table tingche-table table-border-rl table-border-bot" width="100%">
		<tr>
			<td class="device_td_bg3" style="width: 12%;">服务器名称：</td>
			<td style="width: 38%;">
				${server.name}
			</td> 
			<td class="device_td_bg3" style="width: 12%;">服务器IP：</td>
			<td style="width: 38%;">
				${server.serverip}
			</td>	
		</tr>
		<tr>
			<td class="device_td_bg3">服务器型号：</td>
			<td>
				${server.model}
			</td>		
			<td class="device_td_bg3">服务器类别：</td>
			<td>
				${server.type}
			</td> 
		</tr>
		<tr>
			<td class="device_td_bg3">所属部门：</td>
			<td>
				${server.orgNames}
			</td>		
			<td class="device_td_bg3">服务厂商：</td>
			<td>
				${server.factoryName}
			</td> 
		</tr>
		<tr>
			<td class="device_td_bg3">操作账号：</td>
			<td>
				<tags:xiangxuncache keyName="username_cache" id="${server.operator}"></tags:xiangxuncache>
			</td>		
			<td class="device_td_bg3">添加时间：</td>
			<td>
				<fmt:formatDate value="${server.addTime}" pattern="yyyy-MM-dd HH:mm:ss" />
			</td> 
		</tr>
	  </table>
	  <h4 class="xtcs_h4" style="margin:0;">处理器信息</h4>
	  <table class="table tingche-table table-border-rl table-border-bot" width="100%">
		<tr>
			<td class="device_td_bg3" style="width: 12%;">CPU型号：</td>
			<td style="width: 38%;">
				${server.cpuModel}
			</td> 
			<td class="device_td_bg3" style="width: 12%;">CPU类型：</td>
			<td style="width: 38%;">
				${server.cpuType}
			</td> 
		</tr>
		<tr>
			<td class="device_td_bg3">CPU数量：</td>
			<td>
				${server.cpuNum}（颗）
			</td>
			<td class="device_td_bg3">CPU核心数：</td>
			<td>
				${server.cpuCoreNum}（核）
			</td> 
		 </tr>
		 <tr>
			<td class="device_td_bg3">CPU线程数：</td>
			<td>
				${server.threadNum}
			</td>
			<td class="device_td_bg3">三级缓存：</td>
			<td>
				${server.threeCache}（MB）
			</td>			
		</tr>
	</table>
	  <h4 class="xtcs_h4" style="margin:0;">内存及其他</h4>
	  <table class="table tingche-table table-border-rl table-border-bot" width="100%">
		<tr>
			<td class="device_td_bg3" style="width: 12%;">内存类型：</td>
			<td style="width: 38%;">
				${server.ramType}
			</td> 
			<td class="device_td_bg3" style="width: 12%;">内存容量：</td>
			<td style="width: 38%;">
				${server.ramVolume}${server.ramUnit}
			</td> 
		</tr>
		<tr>
			<td class="device_td_bg3">硬盘接口类型：</td>
			<td>
				${server.diskPortType}
			</td> 
			<td class="device_td_bg3">硬盘容量：</td>
			<td>
				${server.diskVolume}${server.diskUnit}
			</td> 
		</tr>
		<tr>
			<td class="device_td_bg3">备注：</td>
			<td colspan="3">
				${server.note}
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
		window.location.href = "${root}/property/serverinfo/list/${menuid}/?page=${page}&isgetsession=1";
	}
//-->
</script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>