<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box" style="height: 650px;overflow: auto;">
	<h4 class="xtcs_h4" style="margin:0;">工单信息-详情</h4>
	<input type="hidden" name="menuid" value="${menuid}"/>
	<input type="hidden" name="page" value="${page}"/>
	<div class="mar_5">
	  <table class="table tingche-table table-border-rl table-border-bot" width="100%">
		<tr>
			<td class="device_td_bg2" style="width: 10%">设备名称：</td>
			<td style="width: 22%"><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:22px; white-space:nowrap;" title="${workorder.devicename}">${workorder.devicename}</div></td> 
			<td class="device_td_bg2" style="width: 10%">设备编号：</td>
			<td style="width: 22%">${workorder.devicecode}</td>			
			<td class="device_td_bg2" style="width: 10%">设备IP：</td>
			<td style="width: 26%">${workorder.deviceip}</td> 
		</tr>
		<tr>
			<td class="device_td_bg2">设备类型：</td>
			<td>${workorder.devicetype}</td>			
			<td class="device_td_bg2">位置信息：</td>
			<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:22px; white-space:nowrap;" title="${workorder.position}">${workorder.position}</div></td>  
			<td class="device_td_bg2">所属部门：</td>
			<td><tags:xiangxuncache keyName="Department" id="${workorder.orgid}"></tags:xiangxuncache></td>			
		</tr>
		<tr>
			<td class="device_td_bg2">派发人：</td>
			<td><tags:xiangxuncache keyName="username_cache" id="${workorder.assignaccount}"></tags:xiangxuncache></td>
			<td class="device_td_bg2">派发时间：</td>
			<td><fmt:formatDate value="${workorder.assigntime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>	
			<td class="device_td_bg2">工单状态：</td>
			<td>${workorder.statusHtml}
				<c:if test="${workorder.status==5}">
					 <a href="#" onclick="javascript:showReasonDialog('${workorder.id}');"><font style="font-weight: bold;color: red;text-decoration: underline;">点击遗留关闭</font></a>
				</c:if>
				<c:if test="${workorder.status==7}">
					  <a href="#" onclick="javascript:appraiseView('${workorder.id}');"><font style="font-weight: bold;color: red;text-decoration: underline;">查看评估详情</font></a>
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="device_td_bg2">是否场外：</td>
			<td>
				<c:if test="${workorder.isouter==0}">是</c:if>
				<c:if test="${workorder.isouter==1}">否</c:if>
			</td> 
			<td class="device_td_bg2">是否转派：</td>
			<td>
				<c:if test="${workorder.isreassign==0}">否</c:if>
				<c:if test="${workorder.isreassign==1}">是</c:if>
			</td>
			<td class="device_td_bg2">是否遗留：</td>
			<td>
				<c:if test="${workorder.isleave==0}">否</c:if>
				<c:if test="${workorder.isleave==1}">是</c:if>
			</td>			
		</tr>
		<tr>
			<td class="device_td_bg2">短信内容：</td>
			<td colspan="5">
				${workorder.messages}
			</td>
		</tr>
	  </table>
	  <h4 class="xtcs_h4" style="margin:0;">异常上报-详情</h4>
	  <table class="table tingche-table table-border-rl table-border-bot" width="100%">
		<tr>
			<td class="device_td_bg2" style="width: 10%">上报人：</td>
			<td style="width: 22%">${exception.operator}</td> 
			<td class="device_td_bg2" style="width: 10%">上报时间：</td>
			<td style="width: 22%"><fmt:formatDate value="${exception.reporttime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td class="device_td_bg2" style="width: 10%">上报内容：</td>
			<td style="width: 26%">${exception.content}</td> 
		</tr>
	  </table>
	  <h4 class="xtcs_h4" style="margin:0;">工单关闭-详情</h4>
	  <table class="table tingche-table table-border-rl table-border-bot">
		<tr>
			<td class="device_td_bg2" style="width: 10%">关闭人：</td>
			<td style="width: 22%"><tags:xiangxuncache keyName="username_cache" id="${workorder.offaccount}"></tags:xiangxuncache></td> 
			<td class="device_td_bg2" style="width: 10%">关闭时间：</td>
			<td style="width: 22%"><fmt:formatDate value="${workorder.offtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>	
			<td colspan="2" style="width: 36%"><a href="#" onclick="showlog()"></a>
				<input class="btn btn-info mar_r10" type="button" value="查看工单日志" onclick="openNotice('${workorder.id}')"/>
				<!-- <input id="cancel_btn" class="btn" type="button" value="关闭工单日志" onclick="closelog()" /> -->
			</td>		
		</tr>
	  </table>
	  <div id="log_div" style="display: none;height: 300px;">
		  <h4 class="xtcs_h4" style="margin:0;">工单日志-详情</h4>
		  <div style="height: 200px;overflow: auto;"><jsp:include page="detail.jsp"></jsp:include></div>
	  </div>
	</div>
	<div class="btn_line">
		<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
	</div>
</div>
<!-- 工单关闭窗口开始 -->
<div class="modal hide fade" id="reason_div" style="width: 60%;text-align: center;">
    <button type="button" class="close" data-dismiss="modal">×</button>
    <h4 id="title" class="xtcs_h4" style="margin: 0;">工单关闭说明窗口</h4>
  	<div class="modal-body">
	    <div class="conten_box">
	    	<form id="input_form" class="form-inline" action="${root}/alarm/workclose/off?leaveoff=1" method="post" style="margin:0;">
				<input type="hidden" name="menuid" value="${menuid}" />
				<input type="hidden" id="id" name="id" value=""/>
				<div class="mar_5">
				  <table class="table table-border-rl table-border-bot bukong-table">
					<tr>
					 	<td class="device_td_bg3">原因说明</td>
						<td colspan="3">
							<textarea rows="3" maxlength="100" style="width:75%;" class="span8 required" name="note"></textarea>
							<font color="red">*</font>
						</td>
					</tr>
				  </table>
				</div>
				<div class="btn_line">
					<button class="btn btn-info mar_r10" type="submit">保存</button>
					<input id="cancel_btn" class="btn" type="button" value="取消" onclick="$('#reason_div').modal('hide')" />
				</div>
			</form>
		</div>
  	</div>
</div>
<!-- 工单关闭窗口结束 -->
<script type="text/javascript">

function showTelephone(a){
	if(a.value == ''){
		$("#telephone").val("");
		$("#messages").html("");
		$("#count_div").html("");
		return;
	}
	var data = $("#contact option:selected");
	if (data) {
		var json = $(data).attr("userdata");
		var obj = $.parseJSON(json);
		$("#telephone").val(obj.mobile);
		var message = '${factory.name}' + "的" + obj.name + "，你好："+
		"位于[${position}]上的设备编号为[${device.code}]的设备[${device.name}]发生故障，请及时维修。";
		$("#messages").html(message);
	}
	$.ajax({
		url: "${root}/alarm/workassign/countworkordernew/" + obj.id + "/",
		type: "POST",
		success: function(data) {
			var obj = eval("("+data+")");
			if(data != null){
				$("#count_div").html("<font color='red'>已有工单数量："+obj.count+"</font>");
			}
		}
	});
}
	function appraiseView(id) {
		var url = "${root}/alarm/workappraise/view/" + id + "/${menuid}/";
		var myLeft = (screen.availWidth-900)/2+100;
		window.open(url,"工单评估详情","width=900,height=400,center=yes,middle=yes,help=no,status=no,scroll=no,top=150,left="+myLeft+",resizable=yes");
	}

	function openNotice(id) {
		var url = "${root}/alarm/workassign/logView/" + id + "/${menuid}/";
		var myLeft = (screen.availWidth-900)/2+100;
		window.open(url,"工单进度详情","width=900,height=400,center=yes,middle=yes,help=no,status=no,scroll=no,top=150,left="+myLeft+",resizable=yes");
		//this.parent.$("#uploading_div").modal('show');
	}
	
	function showlog(){
		$("#log_div").css("display", "");
	}
	function closelog(){
		$("#log_div").css("display", "none");
	}
	function showReasonDialog(id) {
		$("#input_form").validate();
		$('#reason_div').modal('show');
		$("#id").val(id);
	}
	
</script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>