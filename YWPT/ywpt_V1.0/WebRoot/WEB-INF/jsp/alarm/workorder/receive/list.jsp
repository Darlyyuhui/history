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
		<form class="form-inline" action="${root}/alarm/workreceive/list/${menuid}/" method="post" style="margin:0;padding:0 6px;">
			<table width="100%">
			  <tr>
			    <td class="td40">设备名称</td>
			    <td><input title="支持 设备名称 模糊查询" style="width:90%;max-width:144px;" id="devicename" name="search_devicename" value="${workorder.devicename}" maxlength="30" type="text" placeholder="设备名称" /></td>
			    <td class="td40">设备编号</td>
			    <td><input title="支持 设备编号 模糊查询" style="width:90%;max-width:144px;" id="devicecode" name="search_devicecode" value="${workorder.devicecode}" maxlength="20" type="text" placeholder="设备编号" /></td>
			    <td class="td96">设备IP</td>
			    <td><input title="支持 设备IP 模糊查询" style="width:90%;max-width:144px;" id="deviceip" name="search_deviceip" value="${workorder.deviceip}" maxlength="15" type="text" placeholder="设备IP" /></td>
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
			<btn:authorBtn menuid="${menuid}" text="工单接收">
			<button class="btn btn-small" onclick="receive();">
				<i class="icon-edit"></i>工单接收
			</button>
		    </btn:authorBtn>
		    <btn:authorBtn menuid="${menuid}" text="工单退回">
			<button class="btn btn-small" onclick="refuse();">
				<i class="icon-share"></i>工单退回
			</button>
		    </btn:authorBtn>
		  </div>
		  <div class="clear"></div>
	  </div>
	  <div style="overflow: auto;">
		  <table class="table table-striped table-bordered table-condensed table-style" id="table">
				<thead>
					<tr>
						<th width="20"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1" /></th>
						<th>设备名称</th>
						<th>设备编号</th>
						<th>设备IP</th>
						<th>设备类型</th>
						<th>位置信息</th>
						<th>所属部门</th>
						<th>是否场外</th>
						<th>派发人</th>
						<th>派发时间</th>
						<th>工单状态</th>
						<th>巡更状态</th>
						<th>巡更时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${pageList.result}" var="workorder">
						<tr onclick="rowOnclick(this)" data="${workorder.id}">
						
							<td style="text-align:center;"><input type="checkbox" value="${workorder.id}" name="select-chk" /></td>
							<td>${workorder.devicename}</td>
							<td>${workorder.devicecode}</td>
							<td>${workorder.deviceip}</td>
							<td>
								<c:if test="${workorder.devicetype == 'server'}">服务器</c:if>
								<c:if test="${workorder.devicetype == 'device'}">卡口</c:if>
								<c:if test="${workorder.devicetype == 'cabinet'}">智能机柜</c:if>
								<c:if test="${workorder.devicetype == 'database'}">数据库</c:if>
								<c:if test="${workorder.devicetype == 'ftp'}">FTP</c:if>
								<c:if test="${workorder.devicetype == 'project'}">平台</c:if>
							</td>
							<td>${workorder.position}</td>
							<td>
							<c:choose>
							<c:when test="${workorder.orgid!=null}"><tags:xiangxuncache keyName="Department" id="${workorder.orgid}"></tags:xiangxuncache></c:when>
													<c:otherwise>无</c:otherwise>
							</c:choose>
			<!-- 			<c:if test="${workorder.orgid!=null}">
								<tags:xiangxuncache keyName="Department" id="${workorder.orgid}"></tags:xiangxuncache>
							</c:if>
							<c:if test="${workorder.orgid==''}">
								无
							</c:if>
							 -->	
							</td>
							<td>
								<c:if test="${workorder.isouter==0}">是</c:if>
								<c:if test="${workorder.isouter==1}">否</c:if>
							</td>
							<td>
								<tags:xiangxuncache keyName="username_cache" id="${workorder.assignaccount}"></tags:xiangxuncache>
							</td>
							<td><fmt:formatDate value="${workorder.assigntime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>
								${workorder.statusHtml}
							</td>
							<td>
								<c:if test="${workorder.xungeng==0}"><font color="#0473AE">未到场</font></c:if>
								<c:if test="${workorder.xungeng==1}"><font color="green">已到场</font></c:if>
							</td>
							<td>
								<fmt:formatDate value="${workorder.xungengtime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td class="center">
								<btn:authorBtn menuid="${menuid}" text="查看">
									<a href="javascript:viewById('${workorder.id}')"> <i class="icon-th-list"></i>查看</a>
								</btn:authorBtn>
								<c:if test="${workorder.status==0 || workorder.status==3}">
									<btn:authorBtn menuid="${menuid}" text="工单接收">
										<a href="javascript:showReasonDialog('${workorder.id}','receive')"> <i class="icon-edit"></i>工单接收</a>
									</btn:authorBtn> 
								</c:if>
								<c:if test="${workorder.status==0 || workorder.status==3}">
									<btn:authorBtn menuid="${menuid}" text="工单退回">
										<a href="javascript:showReasonDialog('${workorder.id}','refuse')"> <i class="icon-share"></i>工单退回</a>
									</btn:authorBtn>
								</c:if>
							</td>
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
<div class="modal hide fade" id="workorderlog-modal" style="width:950px;margin-left:-500px;">
  	<h4 class="xtcs_h4" style="margin: 0;"><font style="margin-left: 5px;">工单信息详情</font><img src="${root}/images/delete.png" href="#" style="cursor: pointer;float: right;height:20px;width:20px;margin-left:0;font-size: small;vertical-align: middle;margin-top: 5px;color: red;" data-dismiss="modal"/></h4>
  	<div class="modal-body" id="workorderlog_div"></div>
</div>


<!-- 工单接收窗口结束 -->
<script type="text/javascript">
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

	function showReasonDialog(id, type) {
		$("#input_form").validate();
		$('#reason_div').modal('show');
		$("#id").val(id);
		if(type == 'receive'){
			$("#title").html("工单接收说明窗口");
			$("#input_form").attr("action", "${root}/alarm/workreceive/receive");
		}else{
			$("#title").html("工单退回说明窗口");
			$("#input_form").attr("action", "${root}/alarm/workreceive/refuse");
		}
	}
		
	function showList(){
		window.location.href = "${root}/alarm/workreceive/list/${menuid}/?page=${current}&isgetsession=1";
	}

	/* function viewById(id){
		var page='${current}';
		window.location.href="${root}/alarm/workassign/view/"+id+"/${menuid}/?page="+page;
	} */

	function receive() {
		var ids = getSelectedValue();
		if (ids.length == 0) {
			showMessage("请选择要接收的工单记录。");
		} else {
			showReasonDialog(ids,"receive");
		}
	}
	
	function refuse() {
		var ids = getSelectedValue();
		if (ids.length == 0) {
			showMessage("请选择要退回的工单记录。");
		} else {
			showReasonDialog(ids,"refuse");
		}
	}

	$(document).ready(function() {
		$("#role-content").width($('body').width())
		$("#alert-div").hide();
	});
	
		//工单信息详情弹出框 
	function showViewByIdDialog(id) {
		var url = "${root}/home/newindex/workorder/log/view/" + id + "/";
		$.ajax({
			type : 'GET',
			url : url,
			dataType : "text",
			success:function(data) {
				if(data != null){
					$("#workorderlog_div").html(data);
				}
			}
		});
		$('#workorderlog-modal').modal('show');
	}
	//工单信息详情弹出框 
	function viewById(id) {
		showViewByIdDialog(id);
	}
	
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>