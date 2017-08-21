<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">变更信息详情-基本信息</h4>
	<table class="table table-border-bot tingche-table">
		<tr>
			<td class="device_td_bg">模块名称：</td>
			<td>${record.moduleName}</td>
			<td class="device_td_bg">变更类别：</td>
			<td>${record.modifyType}</td>
		</tr>
		<tr>
			<td class="device_td_bg">操作人员：</td>
			<td>${record.modifyOperator}</td>
			<td class="device_td_bg">变更日期：</td>
			<td><fmt:formatDate value="${record.modifyDatetime}" pattern="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<td class="device_td_bg">登录名称：</td>
			<td><tags:xiangxuncache keyName="username_cache" id="${record.operator}"></tags:xiangxuncache></td>
			<td class="device_td_bg">操作时间：</td>
			<td><fmt:formatDate value="${record.operatorTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<tr>
			<td class="device_td_bg">变更原因：</td>
			<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;white-space:nowrap;height: 22px;" title="${record.modifyReason}">${record.modifyReason}</div></td>
			<td class="device_td_bg">版本号：</td>
			<td>${record.version}</td>
		</tr>
	</table>
    <h4 class="xtcs_h4" style="margin:0;">变更信息详情-变更前</h4>
   	<div class="table_box">
		<c:if test="${type == 'device'}">
	   	  	<table class="table table-striped table-bordered table-condensed table-style" id="table">
				<thead>
					<tr>
					  <th>设备名称</th>
					  <th>设备编号</th>
					  <th>设备类型</th>
					  <th>IP地址</th>
					  <th>设备功能</th>
					  <th>所属部门</th>
					  <th>建设厂家</th>
					  <th>变更前ID</th>
					</tr>
				 </thead>   
				 <tbody id="tbody">
					<c:forEach items="${before.result}" var="before"  varStatus="s">
				  	<tr id="rowcount${s.index }" onClick="rowOnclick(this)" data="${before.id}">
						<td>${before.name}</td>
						<td>${before.code}</td>
		                <td><tags:xiangxuncache keyName="Dic" id="${before.devicetypecode}" typeCode="devicetypes"></tags:xiangxuncache></td>
						<td>${before.ip}</td>
						<td><div style="width:150px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px; white-space:nowrap;" title="${before.deviceTypeNames}">${before.deviceTypeNames}</div></td>
						<td><tags:xiangxuncache keyName="Department" id="${before.orgId}"></tags:xiangxuncache></td>
						<td><tags:xiangxuncache keyName="DeviceCompanyInfo" id="${before.companyId}"></tags:xiangxuncache></td>
						<td><font color="red">${before.id}</font></td>
					</tr>	
					</c:forEach>
				</tbody>
			</table>            
		</c:if>
		<c:if test="${type == 'video'}">
	   	  	<table class="table table-striped table-bordered table-condensed table-style" id="table">
				<thead>
					<tr>
					  <th>监控名称</th>
					  <th>设备 IP</th>
					  <th>设备端口</th>
					  <th>监控方向</th>
					  <th>监控类型</th>
					  <th>所属部门</th>
					  <th>建设厂家</th>
					  <th>变更前ID</th>
					</tr>
				 </thead>   
				 <tbody id="tbody">
					<c:forEach items="${before.result}" var="before"  varStatus="s">
				  	<tr id="rowcount${s.index }" onClick="rowOnclick(this)" data="${before.id}">
						<td>${before.name}</td>
						<td>${before.deviceIp}</td>
						<td>${before.devicePort}</td>
						<td><tags:xiangxuncache keyName="Dic" typeCode="direction" id="${before.directionCode}" ></tags:xiangxuncache></td>
						<td><tags:xiangxuncache keyName="Dic" typeCode="monittype" id="${before.videotypeCode}"></tags:xiangxuncache></td>
						<td><tags:xiangxuncache keyName="Department" id="${before.orgId}"></tags:xiangxuncache></td>
						<td><tags:xiangxuncache keyName="DeviceCompanyInfo" id="${before.companyid}"></tags:xiangxuncache></td>
						<td><font color="red">${before.id}</font></td>
					</tr>	
					</c:forEach>
				</tbody>
			</table>            
		</c:if>
		<c:if test="${type == 'server'}">
	   	  	<table class="table table-striped table-bordered table-condensed table-style" id="table">
				<thead>
					<tr>
					  <th>设备名称</th>
					  <th>设备编号</th>
					  <th>设备类别</th>
					  <th>设备型号</th>
					  <th>IP地址</th>
					  <th>所属部门</th>
					  <th>服务厂商</th>
					  <th>变更前ID</th>
					</tr>
				 </thead>   
				 <tbody id="tbody">
					<c:forEach items="${before.result}" var="before"  varStatus="s">
				  	<tr id="rowcount${s.index }" onClick="rowOnclick(this)" data="${before.id}">
						<td>${before.name}</td>
						<td>${before.code}</td>
		                <td>${before.type}</td>
		                <td>${before.model}</td>
						<td>${before.serverip}</td>
						<td><tags:xiangxuncache keyName="Department" id="${before.orgId}"></tags:xiangxuncache></td>
						<td><tags:xiangxuncache keyName="FactoryInfo" id="${before.factoryId}"></tags:xiangxuncache></td>
						<td><font color="red">${before.id}</font></td>
					</tr>	
					</c:forEach>
				</tbody>
			</table>            
		</c:if>
	</div>
	<h4 class="xtcs_h4" style="margin:0;">变更信息详情-变更后</h4>
   	<div class="table_box">
   		<c:if test="${type == 'device'}">
   			<table class="table table-striped table-bordered table-condensed table-style" id="table">
				<thead>
					<tr>
					  <th>设备名称</th>
					  <th>设备编号</th>
					  <th>设备类型</th>
					  <th>IP地址</th>
					  <th>设备功能</th>
					  <th>所属部门</th>
					  <th>建设厂家</th>
					  <th>变更后ID</th>
					</tr>
				 </thead>   
				 <tbody id="tbody">
					<c:forEach items="${after.result}" var="after"  varStatus="s">
				  	<tr id="rowcount${s.index }" onClick="rowOnclick(this)" data="${after.id}">
						<td>${after.name}</td>
						<td>${after.code}</td>
		                <td><tags:xiangxuncache keyName="Dic" id="${after.devicetypecode}" typeCode="devicetypes"></tags:xiangxuncache></td>
						<td>${after.ip}</td>
						<td><div style="width:150px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px; white-space:nowrap;" title="${after.deviceTypeNames}">${after.deviceTypeNames}</div></td>
						<td><tags:xiangxuncache keyName="Department" id="${after.orgId}"></tags:xiangxuncache></td>
						<td><tags:xiangxuncache keyName="DeviceCompanyInfo" id="${after.companyId}"></tags:xiangxuncache></td>
						<td>
							<c:if test="${isLatest == true}">
								<font color="green"><div style="overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px; white-space:nowrap;" title="当前卡口设备ID">${after.id}</div></font>
							</c:if>
							<c:if test="${isLatest == false}">
								<font color="red">${after.id}</font>
							</c:if>
						</td>
					</tr>	
					</c:forEach>
				</tbody>
			</table>            
   		</c:if>
   		<c:if test="${type == 'video'}">
	   	  	<table class="table table-striped table-bordered table-condensed table-style" id="table">
				<thead>
					<tr>
					  <th>监控名称</th>
					  <th>设备 IP</th>
					  <th>设备端口</th>
					  <th>监控方向</th>
					  <th>监控类型</th>
					  <th>所属部门</th>
					  <th>建设厂家</th>
					  <th>变更后ID</th>
					</tr>
				 </thead>   
				 <tbody id="tbody">
					<c:forEach items="${after.result}" var="after"  varStatus="s">
				  	<tr id="rowcount${s.index }" onClick="rowOnclick(this)" data="${after.id}">
						<td>${after.name}</td>
						<td>${after.deviceIp}</td>
						<td>${after.devicePort}</td>
						<td><tags:xiangxuncache keyName="Dic" typeCode="direction" id="${after.directionCode}" ></tags:xiangxuncache></td>
						<td><tags:xiangxuncache keyName="Dic" typeCode="monittype" id="${after.videotypeCode}"></tags:xiangxuncache></td>
						<td><tags:xiangxuncache keyName="Department" id="${after.orgId}"></tags:xiangxuncache></td>
						<td><tags:xiangxuncache keyName="DeviceCompanyInfo" id="${after.companyid}"></tags:xiangxuncache></td>
						<td>
							<c:if test="${isLatest == true}">
								<font color="green"><div style="overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px; white-space:nowrap;" title="当前监控设备ID">${after.id}</div></font>
							</c:if>
							<c:if test="${isLatest == false}">
								<font color="red">${after.id}</font>
							</c:if>
						</td>
					</tr>	
					</c:forEach>
				</tbody>
			</table>            
		</c:if>
		<c:if test="${type == 'server'}">
	   	  	<table class="table table-striped table-bordered table-condensed table-style" id="table">
				<thead>
					<tr>
					  <th>设备名称</th>
					  <th>设备编号</th>
					  <th>设备类别</th>
					  <th>设备型号</th>
					  <th>IP地址</th>
					  <th>所属部门</th>
					  <th>服务厂商</th>
					  <th>变更后ID</th>
					</tr>
				 </thead>   
				 <tbody id="tbody">
					<c:forEach items="${after.result}" var="after"  varStatus="s">
				  	<tr id="rowcount${s.index }" onClick="rowOnclick(this)" data="${after.id}">
						<td>${after.name}</td>
						<td>${after.code}</td>
		                <td>${after.type}</td>
		                <td>${after.model}</td>
						<td>${after.serverip}</td>
						<td><tags:xiangxuncache keyName="Department" id="${after.orgId}"></tags:xiangxuncache></td>
						<td><tags:xiangxuncache keyName="FactoryInfo" id="${after.factoryId}"></tags:xiangxuncache></td>
						<td>
							<c:if test="${isLatest == true}">
								<font color="green"><div style="overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px; white-space:nowrap;" title="当前监控设备ID">${after.id}</div></font>
							</c:if>
							<c:if test="${isLatest == false}">
								<font color="red">${after.id}</font>
							</c:if>
						</td>
					</tr>	
					</c:forEach>
				</tbody>
			</table>            
		</c:if>
	</div>
	<h4 class="xtcs_h4" style="margin:0;">变更信息详情-变更字段</h4>
	<div class="table_box">
		ID
	</div>
	<div class="table_box">
		<font color="red">*注：绿色标示的是当前设备ID</font>
		
	</div>
	<div class="btn_line">
		<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
	</div>
</div>
<script>
	function showList(){
		window.location.href = "${root}/modify/record/search/${menuid}/?page=1&isgetsession=1";
	}
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>