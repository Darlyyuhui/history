<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
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
	    $("#status").val("");
    }
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box" style="height:675px;overflow:hidden;">
	<div class="search-area" style="padding:6px 0 4px;">
		<form class="form-inline" action="${root}/alarm/workschedule/list/${menuid}/" method="post" style="margin:0;padding:0 6px;">
			<table width="100%">
			  <tr>
			    <td class="td40">设备名称</td>
			    <td><input title="支持 设备名称 模糊查询" style="width:90%;max-width:144px;" id="devicename" name="search_devicename" value="${workorder.devicename}" maxlength="30" type="text" placeholder="设备名称" /></td>
			    <td class="td40">设备编号</td>
			    <td><input title="支持 设备编号 模糊查询" style="width:90%;max-width:144px;" id="devicecode" name="search_devicecode" value="${workorder.devicecode}" maxlength="20" type="text" placeholder="设备编号" /></td>
			    <td class="td96">设备IP</td>
			    <td><input title="支持 设备IP 模糊查询" style="width:90%;max-width:144px;" id="deviceip" name="search_deviceip" value="${workorder.deviceip}" maxlength="15" type="text" placeholder="设备IP" /></td>
			    <td class="search_item_td">工单状态</td>
	            <td>
	            	<select id="status" name="search_status" style="width:96%;">
		                <option value="">请选择</option>
		                <option value="0" ${workorder.status=='0'?'selected':''}>已派发</option>
		                <option value="1" ${workorder.status=='1'?'selected':''}>已接收</option>
		                <option value="2" ${workorder.status=='2'?'selected':''}>已退回</option>
		                <option value="3" ${workorder.status=='3'?'selected':''}>已转派</option>
		                <option value="4" ${workorder.status=='4'?'selected':''}>已上报</option>
		                <option value="5" ${workorder.status=='5'?'selected':''}>遗留中</option>
		                <option value="6" ${workorder.status=='6'?'selected':''}>已关闭</option>
		                <option value="7" ${workorder.status=='7'?'selected':''}>已评估</option>
		                <option value="8" ${workorder.status=='8'?'selected':''}>已完成</option>
		                
		            </select>
	            </td>
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
				<button class="btn btn-small" onclick="doExport();"><i class="icon-download"></i>导出</button>
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
							<td><c:choose>
							<c:when test="${workorder.orgid!=null}"><tags:xiangxuncache keyName="Department" id="${workorder.orgid}"></tags:xiangxuncache></c:when>
													<c:otherwise>无</c:otherwise>
							</c:choose>
							</td>
							<td>
								<c:if test="${workorder.isouter==0}">是</c:if>
								<c:if test="${workorder.isouter==1}">否</c:if>
							</td>
							<td><tags:xiangxuncache keyName="username_cache" id="${workorder.assignaccount}"></tags:xiangxuncache></td>
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
								<c:if test="${workorder.isappraised != '1'}">
									<btn:authorBtn menuid="${menuid}" text="生成经验">
										<a href="javascript:experience('${workorder.id}')"> <i class="icon-retweet"></i>生成经验</a>
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

<!-- 工单信息详情弹出框 -->
<div class="modal hide fade" id="workorderlog-modal" style="width:950px;margin-left:-500px;">
  	<h4 class="xtcs_h4" style="margin: 0;"><font style="margin-left: 5px;">工单信息详情</font><img src="${root}/images/delete.png" href="#" style="cursor: pointer;float: right;height:20px;width:20px;margin-left:0;font-size: small;vertical-align: middle;margin-top: 5px;color: red;" data-dismiss="modal"/></h4>
  	<div class="modal-body" id="workorderlog_div"></div>
</div>

<script type="text/javascript">

	function experience(id){
		window.location.href="${root}/alarm/workreport/showexperience/"+id+"/${menuid}/";
	}

	/* function viewById(id){
		var page='${current}';
		window.location.href="${root}/alarm/workassign/view/"+id+"/${menuid}/?page="+page;
	} */
	
	function doExport(){
        window.location.href="${root}/alarm/workschedule/doExport/${menuid}/?${searchParams}";
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