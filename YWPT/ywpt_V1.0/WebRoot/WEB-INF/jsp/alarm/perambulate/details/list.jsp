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
		<form class="form-inline" action="${root}/alarm/perambulateList/list/${menuid}/" method="post" style="margin:0;padding:0 6px;">
			<table width="100%">
			  <tr>
			    <td class="td40">巡检设备名称</td>
			    <td><input title="支持 设备名称 模糊查询" style="width:90%;max-width:144px;" id="devicename" name="search_devicename" value="${perambulate.devicename}" maxlength="30" type="text" placeholder="设备名称" /></td>
			    <td class="td40">巡检设备编号</td>
			    <td><input title="支持 设备编号 模糊查询" style="width:90%;max-width:144px;" id="devicecode" name="search_devicecode" value="${perambulate.devicecode}" maxlength="20" type="text" placeholder="设备编号" /></td>
			    <td class="td96">巡检设备IP</td>
			    <td><input title="支持 设备IP 模糊查询" style="width:90%;max-width:144px;" id="deviceip" name="search_deviceip" value="${perambulate.deviceip}" maxlength="15" type="text" placeholder="设备IP" /></td>
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
			<btn:authorBtn menuid="${menuid}" text="导出">
	            <button id="exportXls" class="btn btn-small" onclick="exportXls();">
	            <i class="icon-edit"></i>导出
	            </button>
	        </btn:authorBtn>
		  
		  </div>
		  <div class="clear"></div>
	  	</div>
	  <div style="overflow: auto;">
		  <table class="table table-striped table-bordered table-condensed table-style" id="table">
				<thead>
					<tr>
						<th width="20"><input type="hidden" id="selectAll" onclick="selectAll(this)" value="-1" /></th>
						<th>设备名称</th>
						<th>设备编号</th>
						<th>设备IP</th>
						<th>设备类型</th>
						<th>位置信息</th>
						<th>原因</th>
						<!-- <th>备注</th> -->

					</tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${pageList.result}" var="perambulate">
						<tr >
							<td style="text-align:center;"><input type=hidden value="${perambulate.id}" name="select-chk" /></td>
							<td>${perambulate.devicename}</td>
							<td>${perambulate.devicecode}</td>
							<td>${perambulate.deviceip}</td>
							<td>
								<c:if test="${perambulate.devicetype == 'server'}">服务器</c:if>
								<c:if test="${perambulate.devicetype == 'device'}">卡口</c:if>
								<c:if test="${perambulate.devicetype == 'cabinet'}">智能机柜</c:if>
								<c:if test="${perambulate.devicetype == 'database'}">数据库</c:if>
								<c:if test="${perambulate.devicetype == 'ftp'}">FTP</c:if>
								<c:if test="${perambulate.devicetype == 'project'}">平台</c:if>
							</td>
							<td>${perambulate.position}</td>
							<td>${perambulate.reason}</td>
							<%-- <td>${perambulate.note}</td> --%>
						</tr>
					</c:forEach>
					<c:if test="${pageList.result!=null}">
						<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
							<tr>
								<td colspan="15">&nbsp;</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<tags:pagination page="${pageList}"></tags:pagination>
	  </div>
	</div>
</div>
<!-- 原因窗口开始 -->
<div class="modal hide fade" id="reason_div" style="width: 60%;text-align: center;">
    <button type="button" class="close" data-dismiss="modal">×</button>
    <h4 id="title" class="xtcs_h4" style="margin: 0;"></h4>
  	<div class="modal-body">
	    <div class="conten_box">
	    	<form id="input_form" class="form-inline" action="" onsubmit="return dosubmit();" method="post" style="margin:0;">
				<input type="hidden" name="menuid" value="${menuid}" />
				<input type="hidden" id="id" name="id" value=""/>
				<div class="mar_5">
				  <table class="table table-border-rl table-border-bot bukong-table">
					<tr>
					 	<td class="device_td_bg3">原因说明</td>
						<td colspan="3">
							<textarea rows="3" maxlength="100" style="width:75%;" class="span8 required" id="reason" name="reason"></textarea>
							<font color="red">*</font>
						</td>
					</tr>
				  </table>
				</div>
				<div class="btn_line">
					<button class="btn btn-info mar_r10" id="submitbut" type="submit">保存</button>
					<input id="cancel_btn" class="btn" type="button" value="取消" onclick="showList()" />
				</div>
			</form>
		</div>
  	</div>
</div>


<script type="text/javascript">

	//miaoxu 巡检导出xls
	function exportXls(){
	    var devicename = $("#devicename").val();
	    var devicecode = $("#devicecode").val();
	    var deviceip = $("#deviceip").val();
	    window.location.href="${root}/alarm/perambulateList/exportXls/${menuid}/?search_devicename="+devicename+"&search_devicecode="+devicecode+"&search_deviceip="+deviceip;
	    
	}

	var submit = false;
	function dosubmit(){
		var reason = $("#reason").val();
		if(reason == ''){
			return false;
		}
		if(submit){
			return false;
		}
		submit = true;
		$("#submitbut").attr("disabled",true);
		$("#input_form").submit();
		return true;
	}



	$(document).ready(function() {
		$("#role-content").width($('body').width())
		$("#alert-div").hide();
		
	});


</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>