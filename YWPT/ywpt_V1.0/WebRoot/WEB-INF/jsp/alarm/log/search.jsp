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
		$("#deviceCode").val("");
	    $("#deviceName").val("");
		$("#startTime").val("");
		$("#endTime").val("");
		$("#eventType").val("");
    }
</script>

<!-- 重置查询输入框结束 -->
<div class="conten_box" style="height:655px;margin-left:180px;">
	<div class="search-area" style="padding:6px 0 4px;">
	  <form class="form-inline" action="${root}/alarm/log/search/${type}/${menuid}/" method="post" style="margin:0;padding:0 6px;">
		<table class="table-pad-td" width="100%">
		  <tr>
		    <td class="search_item_td">资产名称</td>
		    <td style="width: 30%"><input title="支持 资产名称 模糊查询" id="deviceName" name="search_deviceName" value="${log.deviceName}" maxlength="30" type="text" placeholder="资产名称" /></td>
		    <td class="search_item_td">开始时间</td>
		    <td><input name="search_startTime" type="text" value="${log.startTime}" class="Wdate" id="startTime"
	                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:document.getElementById('endTime').value == ''?'new Date()':'#F{$dp.$D(\'endTime\',{d:0})}'})"
	                           readonly="readonly" style="width: 50%;min-width: 165px;" placeholder="开始时间"/>
	        </td>
	        <td class="search_item_td">结束时间</td>
	        <td>
	            <input name="search_endTime" type="text" value="${log.endTime}" class="Wdate" id="endTime"
	                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\',{d:0})}',maxDate:new Date()})"
	                           readonly="readonly" style="width: 50%;min-width: 165px;" placeholder="结束时间"/>
	        </td>
		  </tr>
		  <tr>
		    <td class="search_item_td">资产编号</td>
		    <td><input title="支持 资产编号 模糊查询" id="deviceCode" name="search_deviceCode" value="${log.deviceCode}" maxlength="20" type="text" placeholder="资产编号" /></td>
		    <td class="search_item_td">事件类别</td>
		    <td style="width: 30%">
		      <select id="eventType" name="search_eventType" style="width: 53%;min-width: 175px;">
				<option value="">请选择</option>
				<c:forEach items="${eventtypeList}" var="eventtype">
				   <option value="${eventtype.code}" ${eventtype.code==log.eventType?'selected':''}>${eventtype.name}</option>
				</c:forEach>
			  </select>
		    </td>
		    <td colspan="2">
		      <input type="submit" class="btn btn-info" value="查询" style="height:28px;"/>
			  <input onclick="reValues()" type="button" class="btn mar_l10" value="重置" style="height:28px;"/>
		    </td>				  
		  </tr>
		</table>
	  </form>
	</div>
	<div class="table_box" style="overflow:auto;">
	   <table class="table table-striped table-bordered table-condensed table-style" id="table">
	   		<c:if test="${pageList.result[0]==null}"><td><div style="text-align: center;margin-right: 40px;"><font color="red">没有查到结果，请重新查询或者改变查询条件。</font></div></td></c:if>
  		    <c:if test="${pageList.result[0]!=null}">
			<thead>
				<tr>
					<th width="20"></th>
					<th>资产名称</th>
					<th>资产编号</th>
					<th>资产 IP</th>
					<th>事件类别</th>
					<th>告警级别</th>
					<th>告警方式</th>
					<th>告警时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:forEach items="${pageList.result}" var="log">
					<tr onclick="rowOnclick(this)" data="${log.id}">
						<td style="text-align:center;"><input type="checkbox" value="${log.id}" name="select-chk" /></td>
						<td>${log.deviceName}</td>
						<td>${log.deviceCode}</td>
						<td>${log.deviceIp}</td>
						<td><font style="background-color: ${log.alarmColor};">${log.eventTypeName}</font></td>
						<td>${log.eventLevel}</td>
						<td>${log.alarmType}</td>
						<td><fmt:formatDate value="${log.alarmTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td class="center">
							<btn:authorBtn menuid="${menuid}" text="查看">
								<a href="javascript:viewById('${log.id}')"> <i class="icon-th-list"></i>查看</a>
							</btn:authorBtn>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${pageList.result!=null}">
					<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
						<tr>
							<td colspan="12">&nbsp;</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
			</c:if>
		</table>
		<tags:pagination page="${pageList}"></tags:pagination>
		<div class="clear"></div>
	</div>
</div>
<script type="text/javascript">
<!--
	$(document).ready(function() {
		$("#role-content").width($('body').width())
		$("#alert-div").hide();
	});
	
	function viewById(id){
		var page='${current}';
		window.location.href="${root}/alarm/log/view/"+id+"/${menuid}/?page="+page;
	}
//-->
</script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>