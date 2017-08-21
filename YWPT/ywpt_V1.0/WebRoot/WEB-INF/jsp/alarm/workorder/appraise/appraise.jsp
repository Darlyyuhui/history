<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>

<div class="conten_box" style="height: 670px;overflow: auto;">
	<h4 class="xtcs_h4" style="margin:0;">工单信息</h4>
	<form id="inputForm" class="form-inline" action="${root}/alarm/workappraise/doAppraise" method="post" style="margin:0;">
		<input type="hidden" name="menuid" value="${menuid}"/>
		<input type="hidden" name="page" value="${page}"/>
		<input type="hidden" name="workorderid" value="${workorder.id}"/>
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
					<c:if test="${workorder.status==4}">
						 <a href="#" onclick="javascript:showReasonDialog('${workorder.id}');"><font style="font-weight: bold;color: red;text-decoration: underline;">点击遗留关闭</font></a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="device_td_bg2" style="width: 10%">关闭人：</td>
				<td style="width: 22%"><tags:xiangxuncache keyName="username_cache" id="${workorder.offaccount}"></tags:xiangxuncache></td> 
				<td class="device_td_bg2" style="width: 10%">关闭时间：</td>
				<td style="width: 22%"><fmt:formatDate value="${workorder.offtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td></td>
				<td></td>
			</tr>
		  </table>
		  <h4 class="xtcs_h4" style="margin:0;">工单评估</h4>
		  <div style="height: 300px;overflow: auto;">
			  <table class="table table-striped table-bordered table-condensed table-style" id="table">
			  	<thead>
					<tr>
						<th width="20"><input type="checkbox" onclick="selectall(this)" value="-1" /></th>
						<th>规则编号</th>
						<th>规则名称</th>
						<th>规则描述</th>
						<th>规则分值</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody id="tabody">
					<c:forEach items="${pageList}" var="rule">
						<tr data="${rule.id}">
							<td style="text-align:center;"><input type="checkbox" onclick="getScore1()" value="${rule.id}" name="select-chk" /></td>
							<td>${rule.code}</td>
							<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px; white-space:nowrap;" title="${rule.name}">${rule.name}</div></td>
							<td><div style="width:260px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px; white-space:nowrap;" title="${rule.describe}">${rule.describe}</div></td>
							<td>${rule.score}</td>
							<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px; white-space:nowrap;" title="${rule.note}">${rule.note}</div></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		  </div>
		<table class="table tingche-table table-border-rl table-border-bot" width="100%">
			<tr>
				<td class="device_td_bg2" style="width: 10%">当前分数：</td>
				<td colspan="5">
					<input style="width:10%;" type="text" id="finalscore" name="finalscore" class="required" value="100" readonly="readonly" maxlength="3">
					<font id="message" style="font-weight: bolder;color: red;"></font>
					<input type="hidden" id="ruleids" name="ruleids">
				</td> 
			</tr>
		  </table>
		</div>
		<div class="btn_line">
			<button class="btn btn-info mar_r10" onclick="javascript: return checkSubmit();" id="submitbut" type="submit">保存</button>
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
		</div>
	</form>
</div>
<script type="text/javascript">
	function checkSubmit() {
		var flag = true;
		var message = $("#message").html();
		if(message != ""){
			flag = false;
		}
		if(flg) $("#submitbut").attr("disabled",true);
		$("#inputForm").submit();
		return flag;
	}
	
	function selectall(a){
	   var checked = $(a).attr("checked");
	   $("#table :checkbox").each(function(){
	   	    if((checked=="checked") || checked){
	  			$(this).attr("checked",checked);
	   	    }else{
	   	    	$(this).removeAttr("checked");
	   	    }
	   });
	   var checkeds = getSelectValues();
	   getScore(checkeds);
	}
	
	// 岩涛 ADD  给表格的行增加单击选择事件  
	$("#tabody tr").click(function (e) {
		if ($(e.target).attr("type") != "checkbox") {
			$(this).find(":checkbox").attr("checked", !$(this).find(":checkbox").attr("checked"));
			var checkeds = getSelectValues();
			getScore(checkeds);
		}
	});
	
	function getSelectValues(){
		var checkeds = [];
		var checks = $("#table :checkbox:checked");
		for (var i = 0; i < checks.length; i++) {
			var sigle = checks[i];
			var v = $(sigle).val();
			if (v != "-1") {
				checkeds.push(v);
			}
		}
		checkeds.join(",");
		return checkeds;
	}

	function getScore(ids){
		if(ids != ''){
			var url="${root}/alarm/workappraise/getScoreByAjax/"+ids+"/";
			$.ajax( {
				type : 'GET',
				url : url,
				async : true,
				dataType : "text",
				success:function(data){
					if(data != null){
						if(data < 0){
							$("#message").css("display", "");
							$("#message").html("*评估分数不能小于0");
							$("#finalscore").val("");
							$("#ruleids").val("");
						}else{
							$("#message").css("display", "none");
							$("#message").html("");
							$("#finalscore").val(data);
							$("#ruleids").val(ids);
						}
					}
				}
			});
		}else{
			$("#finalscore").val("100");
			$("#ruleids").val("");
			$("#message").css("display", "none");
			$("#message").html("");
		}
	}
	
	function getScore1(){
		var ids = getSelectedValue();
		getScore(ids);
	}
	
	$(document).ready(function() {
		//为inputForm注册validate函数
		$("#inputForm").validate();
	});
	
</script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>