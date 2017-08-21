<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none">
	<p id="alert-content" align="center"></p>
</div>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>
		<p align="center">${message}</p>
	</div>
	<script>
		setTimeout('$("#message").hide("slow")', 1200);
	</script>
</c:if>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function reValues(){
		$("#devicename").val("");
	    $("#devicecode").val("");
	    $("#deviceip").val("");
    }
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box" style="height: 690px;">
	<div class="search-area" style="padding:6px 0 4px;">
		<form class="form-inline" action="${root}/alarm/perambulateList/refer/${menuid}/" method="post" style="margin:0;padding:0 6px;">
			<table width="100%">
			  <tr>
			    <td class="td40">设备名称</td>
			    <td><input title="支持 设备名称 模糊查询" style="width:90%;max-width:144px;" id="assetname" name="search_assetname" value="${workorder.devicename}" maxlength="30" type="text" placeholder="设备名称" /></td>
			    <td class="td40">设备类型</td>
			    <td>
			      <select  title="支持 设备名称 模糊查询" style="width:90%;max-width:144px;" id="assettype" name="search_assettype" value="${workorder.devicename}" maxlength="30" type="text" placeholder="设备名称" >
				      <option value="device">卡口设备</option>
				      <option value="icabinef">智能机柜</option>
				      <option value="server">服务器</option>
				      <option value="database">数据库</option>
				      <option value="project">平台信息</option>
				      <option value="ftp">FTP信息</option>
				      
				  </select>
			    
			    </td>
			    
			    
			    <%-- <td><input title="支持 设备编号 模糊查询" style="width:90%;max-width:144px;" id="assettype" name="search_assettype" value="${workorder.devicecode}" maxlength="20" type="text" placeholder="设备类型" /></td> --%>
			    <!-- <td class="td96">设备IP</td> -->
			    <td><input title="支持 设备IP 模糊查询" style="width:90%;max-width:144px;" id="deviceip" name="search_deviceip" value="${workorder.deviceip}" maxlength="15" type="hidden" placeholder="设备IP" /></td>
			    <td style="min-width:140px;">
			      <input type="submit" class="btn btn-info pull-left mar_l10" value="查询" style="height:28px;"/>
			      <input onclick="reValues()" type="button" class="btn mar_l10" value="重置" style="height:28px;"/>
			    </td>
			  </tr>
			</table>
		</form>
	</div>
	<div class="table_box">
    	<div class="btn-group-wrap mar_b5">
		  <div class="btn-group pull-right">
			<btn:authorBtn menuid="${menuid}" text="新增巡检工单">
			<button class="btn btn-small" onclick="receive();">
				<i class="icon-edit"></i>新增巡检工单
			</button>
		    </btn:authorBtn>
		  
		  </div>
		  <div class="clear"></div>
	  	</div>

	</div>
</div>

<!-- 工单接收窗口结束 -->
<script type="text/javascript">
	$(document).ready(function() {
		$("#role-content").width($('body').width())
		$("#alert-div").hide();
	});

</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>