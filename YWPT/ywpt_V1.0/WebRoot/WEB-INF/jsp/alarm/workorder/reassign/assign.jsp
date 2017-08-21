<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/header.jspf" %>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${root}/js/json2.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display: none;">
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
<div class="conten_box">
	<form class="form-inline" action="${root}/alarm/workreassign/doAssign/" id="input_form" method="post" style="margin:0">
		<input type="hidden" name="menuid" value="${menuid}"/>
		<input type="hidden" name="id" value="${workorder.id}"/>
		<h4 class="xtcs_h4" style="margin:0;">资产信息详情</h4>
		<div class="mar_5">
			<table class="table table-border-rl table-border-bot bukong-table" width="100%">
				<tr>
					<td class="device_td_bg3" width="10%">资产名称：</td>
					<td width="40%">
						${asset.assetname} 
					</td>
					<td class="device_td_bg3" width="10%">资产编号：</td>
					<td width="40%">
						${asset.assetcode}
					</td>
				</tr>
				<tr>
					<td class="device_td_bg3">资产型号：</td>
					<td>
						${asset.assetmodel}
					</td>
					<td class="device_td_bg3">资产状态：</td>
					<td>
						<tags:xiangxuncache keyName="Dic" typeCode="assetstatus" id="${asset.assetstatus}"></tags:xiangxuncache>
					</td>
				</tr>
			</table>
		</div>
        <h4 class="xtcs_h4" style="margin:0;">故障设备信息</h4>
		<div class="mar_5">
			<table class="table table-border-rl table-border-bot bukong-table" width="100%">
				<tr>
					<td class="device_td_bg3" width="10%">所在位置：</td>
					<td width="40%">
						${workorder.position}
					</td>
					<td class="device_td_bg3" width="10%">设备名称：</td>
					<td width="40%">
						${workorder.devicename}
					</td>
				</tr>
				<tr>
					<td class="device_td_bg3">设备编号：</td>
					<td>
						${workorder.devicecode}
					</td>
					<td class="device_td_bg3">设备IP：</td>
					<td>
						${workorder.deviceip}
					</td>
				</tr>
			</table>
		</div>
		<c:if test="${devicetype != 'server'}">
			<h4 class="xtcs_h4" style="margin:0;">建设厂家信息</h4>
			<div class="mar_5">
				<table class="table table-border-rl table-border-bot bukong-table">
					<tr>
						<td class="device_td_bg3">厂商名称：</td>
						<td width="40%">
							<tags:xiangxuncache keyName="DeviceCompanyInfo" id="${company.id}"></tags:xiangxuncache> 
						</td>
						<td class="device_td_bg3">责 任 人：</td>
						<td width="40%">
							${company.contactperson} 
						</td>
					</tr>
					<tr>
						<td class="device_td_bg3">联系电话：</td>
						<td>
							${company.contactphone}
						</td>
						<td></td>
						<td>
						</td>
					</tr>
				</table>
		    </div>
		</c:if>
	    <h4 class="xtcs_h4" style="margin:0;">服务厂商信息</h4>
		<div class="mar_5">
			<table class="table table-border-rl table-border-bot bukong-table">
				<tr>
					<td class="device_td_bg3">厂商名称：</td>
					<td width="40%">
						<tags:xiangxuncache keyName="FactoryInfo" id="${workorder.companyid}"></tags:xiangxuncache> 
						<input type="hidden" id="factoryname" value="${factory.name}"/>
					</td>
					<td class="device_td_bg3">联  系  人：</td>
					<td width="40%">
						<select id="contact" name="contact" style="width:62%;" onchange="showTelephone(this)" class="required">
			                <option value="">请选择</option>
			                <c:forEach items="${contactList}" var="contact">
			                  <option value="${contact.id}" userdata='${contact.userdata}'>${contact.name}</option>
			                </c:forEach>
			            </select> 
			            <span id="count_div"></span> 
			            <font color="red">*</font>
					</td>
				</tr>
				
				<tr>
					<td class="device_td_bg3">联系电话：</td>
					<td>
						<input class="required" readonly autocomplete="off" style="width:60%;" id="telephone" name="telephone" value="" type="text" />
						<font color="red">*</font> 
					</td>
					<td></td>
					<td>
					</td>
				</tr>
				
				<tr>
					<td class="device_td_bg3">短信通知：</td>
					<td class="inputImportent" colspan="3">
						<textarea class="required" name="messages" id="messages" maxlength="200" style="WIDTH:70%;height:50px;"></textarea>
						<font color="red">*</font> 
					</td>
				</tr>
				
			</table>
	    </div>
		<div class="btn_line">
			<input id="submit_btn" class="btn btn-info mar_r10" onclick="javascript: return checkSubmit();" type="submit" value="保存" />
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
		</div>
	</form>
</div>
<script>
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#contact").focus();
		//为inputForm注册validate函数
		$("#input_form").validate();
	});
	
	function checkSubmit() {
		var flag = $("#input_form").valid();
		if(flag){
			$("#submit_btn").attr("disabled",true);
			$("#input_form").submit();
			return true;
		}
	}
	
	function showTelephone(a){
		if(a.value == ''){
			$("#telephone").val("");
			$("#messages").html("");
			return;
		}
		var data = $("#contact option:selected");
		if (data) {
			var json = $(data).attr("userdata");
			var obj = $.parseJSON(json);
			$("#telephone").val(obj.mobile);
			var message = $("#factoryname").val() + "的" + obj.name + "，你好："+
			"位于[${workorder.position}]上的设备编号为[${workorder.devicecode}]的设备[${workorder.devicename}]发生故障，请及时维修。";
			$("#messages").html(message);
		}
		$.ajax( {
			type : 'GET',
			url : "${root}/alarm/workassign/countworkorder/" + obj.id + "/",
			dataType : "text",
			success:function(data) {
				if(data != null){
					$("#count_div").html("<font color='red'>已有工单数量："+data+"</font>");
				}
			}
		});
	}
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>