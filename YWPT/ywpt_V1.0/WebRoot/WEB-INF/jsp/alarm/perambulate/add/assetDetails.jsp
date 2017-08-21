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
		$("#assetname").val("");
		$("#assettype").val("");

    }
	
</script>

<script type="text/javascript">
	
	if(${status} == true){

		window.location.href = "${root}/alarm/perambulateList/referAsset/${menuid}/";
		
	}
	
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box" style="height: 690px;">
	<div class="search-area" style="padding:6px 0 4px;">
		<form class="form-inline" action="${root}/alarm/perambulateList/referAsset/${menuid}/" method="post" style="margin:0;padding:0 6px;">
			<table width="100%">
			  <tr>
			    <td class="td40">设备名称</td>
			    <td><input title="支持 设备名称 模糊查询" style="width:90%;max-width:144px;" id="assetname" name="search_assetname" value="${assetInfo.assetname}" maxlength="30" type="text" placeholder="设备名称" /></td>
			    <td class="td40">设备类型</td>
			    <td>
			      <select  title="支持 设备名称 模糊查询" style="width:90%;max-width:144px;" id="assettype" name="search_assettype" value="${assetInfo.assettype}" maxlength="30" type="text" placeholder="设备名称" >
			      	  <option value="" ${assetInfo.assettype == null ? 'selected' : '' }>全部</option>
				      <option value="device" ${assetInfo.assettype == 'device'? 'selected':''}>卡口设备</option>
				      <option value="cabinet" ${assetInfo.assettype == 'cabinet'? 'selected':''}>智能机柜</option>
				      <option value="server" ${assetInfo.assettype == 'server'? 'selected':''}>服务器</option>
				      <option value="database" ${assetInfo.assettype == 'database'? 'selected':''}>数据库</option>
				      <option value="project" ${assetInfo.assettype == 'project'? 'selected':''}>平台信息</option>
				      <option value="ftp" ${assetInfo.assettype == 'ftp'? 'selected':''}>FTP信息</option>
				      
				  </select>
			    
			    </td>
			    
			    
			    <%-- <td><input title="支持 设备编号 模糊查询" style="width:90%;max-width:144px;" id="assettype" name="search_assettype" value="${workorder.devicecode}" maxlength="20" type="text" placeholder="设备类型" /></td> --%>
			    <!-- <td class="td96">设备IP</td> -->
			    <td><input title="支持 设备IP 模糊查询" style="width:90%;max-width:144px;" id="" name="search_deviceip" value="" maxlength="15" type="hidden" placeholder="设备IP" /></td>
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
			<%-- <btn:authorBtn menuid="${menuid}" text="新增巡检工单"> --%>
			<button class="btn btn-small" onclick="receive();">
				<i class="icon-edit"></i>新增巡检工单
			</button>
		    <%-- </btn:authorBtn> --%>
		  
		  </div>
		  <div class="clear"></div>
	  	</div>
	  	
	  	<c:if test="${assetInfo != null}">
	  	
	  	
	  		<div style="overflow: auto;">
		  <table class="table table-striped table-bordered table-condensed table-style" id="table">
				<thead>
					<tr>
						<th width="20"><input type="hidden" id="selectAll" onclick="selectAll(this)" value="-1" /></th>
						<th>设备名称</th>
						<th>设备编号</th>
						<th>设备类型</th>
						<th>位置信息</th>

					</tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${pageList.result}" var="assetInfo">
						<tr onclick="rowOnclick(this)" data="${assetInfo.id}">
							<td style="text-align:center;"><input type="checkbox" value="${assetInfo.id}" name="select-chk" /></td>
							<td>${assetInfo.assetname}</td>
							<td>${assetInfo.assetcode}</td>
							<%-- <td>${assetInfo.deviceip}</td> --%>
							<td>
								<c:if test="${assetInfo.assettype == 'server'}">服务器</c:if>
								<c:if test="${assetInfo.assettype == 'device'}">卡口</c:if>
								<c:if test="${assetInfo.assettype == 'cabinet'}">智能机柜</c:if>
								<c:if test="${assetInfo.assettype == 'database'}">数据库</c:if>
								<c:if test="${assetInfo.assettype == 'ftp'}">FTP</c:if>
								<c:if test="${assetInfo.assettype == 'project'}">平台</c:if>
							</td>
							<td>${assetInfo.installplace}</td>
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
	  	
	  	</c:if>

	  	
	    
	</div>
</div>
<!-- 工单接收窗口开始 -->
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


<!-- 工单信息详情弹出框-->
<%-- <div class="modal hide fade" id="workorderlog-modal" style="width:950px;margin-left:-500px;">
  	<h4 class="xtcs_h4" style="margin: 0;"><font style="margin-left: 5px;">工单信息情详</font><img src="${root}/images/delete.png" href="#" style="cursor: pointer;float: right;height:20px;width:20px;margin-left:0;font-size: small;vertical-align: middle;margin-top: 5px;color: red;" data-dismiss="modal"/></h4>
  	<div class="modal-body" id="workorderlog_div"></div>
</div> --%>


<!-- 工单接收窗口结束 -->
<script type="text/javascript">
	/* var submit = false;
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
		$("#input_form").attr("action", "${root}/alarm/perambulateList/save/{menuid}/");

		return true;
	} */

	function showReasonDialog(id, type) {
		$("#input_form").validate();
		$('#reason_div').modal('show');
		$("#id").val(id);
		if(type == 'receive'){
			$("#title").html("设备巡检说明窗口");
			$("#input_form").attr("action", "${root}/alarm/perambulateList/save/${menuid}/");
		}
		
	}
		
	function showList(){
		window.location.href = "${root}/alarm/perambulateList/referAsset/{menuid}/";
	}

	function receive() {
		var ids = getSelectedValue();
		if (ids.length == 0) {
			showMessage("请选择您巡检的设备。");
		} 
		if (ids.length >= 2) {
			showMessage("只能选取一个设备。");
		}if(ids.length == 1){
			showReasonDialog(ids,"receive");
		}
	}

	$(document).ready(function() {
		$("#role-content").width($('body').width())
		$("#alert-div").hide();
		
	});
	

</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>