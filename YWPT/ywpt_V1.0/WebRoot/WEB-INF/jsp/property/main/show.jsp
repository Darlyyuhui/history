<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<link id="MainCss" href='${root}/css/transport.css' rel='stylesheet'>
<link id="PicCss" href='${root}/css/picmix.css' rel='stylesheet'>
<div class="conten_box" style="height:680px;overflow:auto;">
	<h4 class="title_intro" style="margin-top: 0;"><span><img src="${root}/images/newIndex/diqiu.png"></span>场外设备信息<a href="#" style="color: red;float: right;" onclick="showMore('outer')">更多+</a></h4>
    <div class="conten_box">
     	<!-- 选项卡 开始 -->	  
		<ul class="nav nav-tabs" style="padding-left:5px; margin:0;">
		    <c:forEach items="${openInfoList1}" var="openInfo" varStatus="status">
		    	<c:if test="${status.index == 0}">
			    	<li class="active">
			    	<input type="hidden" id = "openInfoStatus1" value="${openInfo.name}" />
			    </c:if>
			    <c:if test="${status.index != 0}">
			    	<li>
			    </c:if>
		    	<c:if test="${openInfo.name == 'opendevice'}">
		    		<a href="#tab1" data-toggle="tab">卡口</a></li>
		    	</c:if>
		    	<c:if test="${openInfo.name == 'opencabinet'}">
		    		<a href="#tab7" data-toggle="tab">机柜</a></li>
		    	</c:if>
		    	</c:forEach>
		</ul>
		
		<div class="tab-content" style="overflow:hidden;">
			<!-- 卡口Tab页 -->
			<div class="tab-pane mar_5" id="tab1">
				<div class="tab-pane" style="overflow: auto;">
					<table class="table table-striped table-bordered table-condensed table-style" id="table">
						<thead>
							<tr>
							  <th width="20">序号</th>
							  <th>设备名称</th>
							  <th>设备编号</th>
							  <th>设备类型</th>
							  <th>IP地址</th>
							  <th>设备功能</th>
							  <th>所属部门</th>
							  <th>建设厂家</th>
							</tr>
						 </thead>   
						 <tbody id="tbody">
							<c:forEach items="${devicepage.result}" var="deviceInfo" varStatus="s">
						  	<tr id="rowcount${s.index }" onClick="rowOnclick(this)" data="${deviceInfo.id}">
						  		<td>${(s.index+1)+(page-1)*7}</td>
								<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${deviceInfo.name}">${deviceInfo.name}</div></td>
								<td>${deviceInfo.code}</td>
				                <td><tags:xiangxuncache keyName="Dic" id="${deviceInfo.devicetypecode}" typeCode="devicetypes"></tags:xiangxuncache></td>
								<td>${deviceInfo.ip}</td>
								<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${deviceInfo.deviceTypeNames}">${deviceInfo.deviceTypeNames}</div></td>
								<td><tags:xiangxuncache keyName="Department" id="${deviceInfo.orgId}"></tags:xiangxuncache></td>
								<td><tags:xiangxuncache keyName="DeviceCompanyInfo" id="${deviceInfo.companyId}"></tags:xiangxuncache></td>
							</tr>	
							</c:forEach>
							<c:if test="${devicepage.result!=null}">
							  <c:forEach begin="1" end="${7-fn:length(devicepage.result)}">
								 <tr>
								    <td colspan="10">&nbsp;</td>
								 </tr>
							  </c:forEach>
						    </c:if>
						</tbody>
					</table>            
	            </div>
			</div>
			
			<!-- 机柜Tab页 -->
			<div class="tab-pane mar_5" id="tab7">
				<div class="tab-pane" style="overflow: auto;">
					<table class="table table-striped table-bordered table-condensed table-style" id="table">
						<thead>
							<tr>
							  <th width="20">序号</th>
							  <th>设备名称</th>
							  <th>设备编号</th>
							  <th>IP地址</th>
							  <th>运维服务商</th>
							</tr>
						 </thead>   
						 <tbody id="tbody">
							<c:forEach items="${cabinetpage.result}" var="cabinetInfo" varStatus="s">
						  	<tr id="rowcount${s.index }" onClick="rowOnclick(this)" data="${cabinetInfo.id}">
						  		<td>${(s.index+1)+(page-1)*7}</td>
								<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${cabinetInfo.assetname}">${cabinetInfo.assetname}</div></td>
								<td>${cabinetInfo.assetcode}</td>
								<td>${cabinetInfo.ip}</td>
								<td><tags:xiangxuncache keyName="FactoryInfo" id="${cabinetInfo.serviceid}"></tags:xiangxuncache></td>
							</tr>	
							</c:forEach>
							<c:if test="${cabinetpage.result!=null}">
							  <c:forEach begin="1" end="${7-fn:length(cabinetpage.result)}">
								 <tr>
								    <td colspan="10">&nbsp;</td>
								 </tr>
							  </c:forEach>
						    </c:if>
						</tbody>
					</table>            
	            </div>
			</div>
			<!-- 监控Tab页 -->
			<!-- <div class="tab-pane mar_5" style="overflow-x:hidden;overflow-y: auto;" id="tab2" >
				<div class="tab-pane" style="overflow: auto;">
	           		<table class="table table-striped table-bordered table-condensed table-style" id="table">
					  <thead>
						<tr>
						  <th width="20">序号</th>
						  <th>监控名称</th>
						  <th>设备 IP</th>
						  <th>监控方向</th>
						  <th>监控类型</th>
						  <th>所属部门</th>
						  <th>建设厂家</th>
						  <th>服务厂商</th>
					    </tr>
					  </thead>   
					  <tbody id="tbody">
					    <c:forEach items="${videopage.result}" var="videoInfo" varStatus="s">
					 	<tr onClick="rowOnclick(this)" data="${videoInfo.id}">
					 	<td>${(s.index+1)+(page-1)*7}</td>
						<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${videoInfo.name}">${videoInfo.name}</div></td>
						<td>${videoInfo.deviceIp}</td>
						<td><tags:xiangxuncache keyName="Dic" typeCode="direction" id="${videoInfo.directionCode}" ></tags:xiangxuncache></td>
						<td><tags:xiangxuncache keyName="Dic" typeCode="monittype" id="${videoInfo.videotypeCode}"></tags:xiangxuncache></td>
						<td><tags:xiangxuncache keyName="Department" id="${videoInfo.orgId}"></tags:xiangxuncache></td>
						<td><tags:xiangxuncache keyName="DeviceCompanyInfo" id="${videoInfo.companyid}"></tags:xiangxuncache></td>
						<td><tags:xiangxuncache keyName="FactoryInfo" id="${videoInfo.factoryId}"></tags:xiangxuncache></td>
					   </tr>	
					   </c:forEach>
					   <c:if test="${videopage.result!=null}">
							<c:forEach begin="1" end="${7-fn:length(videopage.result)}">
								<tr>	
									<td colspan="12">&nbsp;</td>
								</tr>
							</c:forEach>
					   </c:if>
					 </tbody>
					</table>            
	            </div>
	        </div> -->
		</div>
    </div>
    <h4 class="title_intro" style="margin-top: 0;"><span><img src="${root}/images/newIndex/ico1.png"></span>场内设备信息<a href="#" style="color: red;float: right;" onclick="showMore('inner')">更多+</a></h4>
    <div class="conten_box">
     	<!-- 选项卡 开始 -->	  
		<ul class="nav nav-tabs" style="padding-left:5px; margin:0;">
		    <c:forEach items="${openInfoList}" var="openInfo" varStatus="status">
		    	<c:if test="${status.index == 0}">
			    	<li class="active">
			    	<input type="hidden" id = "openInfoStatus" value="${openInfo.name}" />
			    </c:if>
			    <c:if test="${status.index != 0}">
			    	<li>
			    </c:if>
		    	<c:if test="${openInfo.name == 'openserver'}">
		    		<a href="#tab3" data-toggle="tab">服务器</a></li>
		    	</c:if>
		    	<c:if test="${openInfo.name == 'opendatabase'}">
		    		<a href="#tab4" data-toggle="tab">数据库</a></li>
		    	</c:if>
		    	<c:if test="${openInfo.name == 'openftp'}">
		    		<a href="#tab5" data-toggle="tab">FTP</a></li>
		    	</c:if>
		    	<c:if test="${openInfo.name == 'openproject'}">
		    		<a href="#tab6" data-toggle="tab">平台</a></li>
		    	</c:if>
		    </c:forEach>
		    <!-- 
		    <li class="active"><a href="#tab3" data-toggle="tab">服务器</a></li>
		    <li><a href="#tab4" data-toggle="tab">数据库</a></li>
		    <li><a href="#tab5" data-toggle="tab">FTP</a></li>
		    <li><a href="#tab6" data-toggle="tab">平台</a></li>
		     -->
		</ul>
		<div class="tab-content" style="overflow:hidden;">
			<!-- 服务器Tab页 -->
			<div class="tab-pane mar_5" id="tab3">
				<div class="tab-pane" style="overflow: auto;">
					<table class="table table-striped table-bordered table-condensed table-style" id="table">
						<thead>
							<tr>
								<th width="20">序号</th>
								<th>服务器名称</th>
								<th>服务器编号</th>
								<th>服务器IP</th>
								<th>服务器型号</th>
								<th>服务器类别</th>
								<th>CPU型号</th>
								<th>内存容量</th>
								<th>硬盘容量</th>
								<th>添加时间</th>
							</tr>
						</thead>
						<tbody id="tbody">
							<c:forEach items="${serverpage.result}" var="server" varStatus="x">
								<tr onclick="rowOnclick(this)" data="${server.id}">
									<td>${(x.index+1)+(page-1)*7}</td>
									<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${server.name}">${server.name}</div></td>
									<td>${server.code}</td>
									<td>${server.serverip}</td>
									<td>${server.model}</td>
									<td>${server.type}</td>
									<td>${server.cpuModel}</td>
									<td>${server.ramVolume}</td>
									<td>${server.diskVolume}</td>
									<td><fmt:formatDate value="${server.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								</tr>
							</c:forEach>
							<c:if test="${serverpage.result!=null}">
								<c:forEach begin="1" end="${7-fn:length(serverpage.result)}">
									<tr>
										<td colspan="15">&nbsp;</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
	            </div>
			</div>
			<!-- 数据库Tab页 -->
			<div class="tab-pane mar_5" id="tab4">
				<div class="tab-pane" style="overflow: auto;">
					<table class="table table-striped table-bordered table-condensed table-style" id="table">
						<thead>
							<tr>
								<th width="20">序号</th>
								<th>数据库名称</th>
								<th>数据库类型</th>
								<th>IP地址</th>
								<th>端口号</th>
								<th>用户名</th>
								<th>密码</th>
								<th>数据库实例</th>
								<th>运维服务商</th>
							</tr>
						</thead>
						<tbody id="tbody">
							<c:forEach items="${databasepage.result}" var="database" varStatus="x">
								<tr onclick="rowOnclick(this)" data="${database.id}">
									<td>${(x.index+1)+(page-1)*7}</td>
									<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${database.name}">${database.name}</div></td>
									<td>${database.dialect}</td>
									<td>${database.ip}</td>
									<td>${database.port}</td>
									<td>${database.username}</td>
									<td>${database.password}</td>
									<td>${database.sid}</td>
									<td>${database.factoryName}</td>
								</tr>
							</c:forEach>
							<c:if test="${databasepage.result!=null}">
								<c:forEach begin="1" end="${7-fn:length(databasepage.result)}">
									<tr>
										<td colspan="15">&nbsp;</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
	            </div>
			</div>
			<!-- FTP Tab页 -->
			<div class="tab-pane mar_5" id="tab5">
				<div class="tab-pane" style="overflow: auto;">
					<table class="table table-striped table-bordered table-condensed table-style" id="table">
						<thead>
							<tr>
								<th width="20">序号</th>
								<th>FTP名称</th>
								<th>FTP类型</th>
								<th>IP地址</th>
								<th>端口号</th>
								<th>用户名</th>
								<th>密码</th>
								<th>文件虚拟目录</th>
								<th>运维服务商</th>
							</tr>
						</thead>
						<tbody id="tbody">
							<c:forEach items="${ftppage.result}" var="ftp" varStatus="x">
								<tr onclick="rowOnclick(this)" data="${ftp.id}">
									<td>${(x.index+1)+(page-1)*7}</td>
									<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${ftp.name}">${ftp.name}</div></td>
									<td>${ftp.ftptype}</td>
									<td>${ftp.ip}</td>
									<td>${ftp.port}</td>
									<td>${ftp.username}</td>
									<td>${ftp.password}</td>
									<td>${ftp.dirname}</td>
									<td>${ftp.factoryName}</td>
								</tr>
							</c:forEach>
							<c:if test="${ftppage.result!=null}">
								<c:forEach begin="1" end="${7-fn:length(ftppage.result)}">
									<tr>
										<td colspan="15">&nbsp;</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
	            </div>
			</div>
			<!-- 平台Tab页 -->
			<div class="tab-pane mar_5" id="tab6">
				<div class="tab-pane" style="overflow: auto;">
					<table class="table table-striped table-bordered table-condensed table-style" id="table">
						<thead>
							<tr>
								<th width="20">序号</th>
								<th>平台名称</th>
								<th>IP地址</th>
								<th>端口号</th>
								<th>项目路径</th>
								<th>运维服务商</th>
							</tr>
						</thead>
						<tbody id="tbody">
							<c:forEach items="${projectpage.result}" var="project" varStatus="x">
								<tr onclick="rowOnclick(this)" data="${project.id}">
									<td>${(x.index+1)+(page-1)*7}</td>
									<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${project.name}">${project.name}</div></td>
									<td>${project.ip}</td>
									<td>${project.port}</td>
									<td>${project.url}</td>
									<td>${project.factoryName}</td>
								</tr>
							</c:forEach>
							<c:if test="${projectpage.result!=null}">
								<c:forEach begin="1" end="${7-fn:length(projectpage.result)}">
									<tr>
										<td colspan="15">&nbsp;</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
	            </div>
			</div>
		</div>
    </div>
</div>
<script type="text/javascript">
function showMore(type){
	var url = "property/asset/list/${menuid}/";
	if(type == "inner"){
		url = "property/asset/list/${menuid}/?type=inner";
	}
	parent.menuOnclick('${menuid}',"设备信息维护","资产管理",url);
	parent.$("#menu_"+data.ppid).parent("li").removeClass("sys_nav").addClass("active_nav").siblings(".mainnav_new li").removeClass("active_nav").addClass("sys_nav");
}

$(document).ready(function (){
	var openInfoStatus = $("#openInfoStatus").val();
	if(openInfoStatus == "openserver"){
		$("#tab3").addClass("active");
	}else if(openInfoStatus == "opendatabase"){
		$("#tab4").addClass("active");
	}else if(openInfoStatus == "openftp"){
		$("#tab5").addClass("active");
	}else if(openInfoStatus == "openproject"){
		$("#tab6").addClass("active");
	}
	
	var openInfoStatus1 = $("#openInfoStatus1").val();
	if(openInfoStatus1 == "opendevice"){
		$("#tab1").addClass("active");
	}else if(openInfoStatus1 == "opencabinet"){
		$("#tab7").addClass("active");
	}
});
</script>
<script src="${root}/compnents/bootstrap/js/jquery-ui-1.8.21.custom.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.cleditor.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.history.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>