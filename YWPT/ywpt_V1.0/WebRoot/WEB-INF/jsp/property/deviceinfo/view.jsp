<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" 	type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">
<!--
	$(document).ready(function(){
		var videocode = '${deviceInfo.videoCode}';
		if(videocode != ''){
			$("#videoinfo_div").css("display","");
		}
	});
//-->
</script>
<div class="conten_box">
  <div style="height:530px;overflow-y:scroll;">
	<h4 class="xtcs_h4" style="margin:0;">基本信息</h4>
	<input type="hidden" name="menuid" value="${menuid}"/>
	<input type="hidden" name="page" value="${page}"/>
	<input type="hidden" name="id" value="${deviceInfo.id}"/>
	<table class="table table-border-bot tingche-table">
		<tr>
			<td class="device_td_bg">设备编码：</td>
			<td width="30%">${deviceInfo.code}</td>
			<td class="device_td_bg">设备名称：</td>
			<td><div style="width:160px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;white-space:nowrap;" title="${deviceInfo.name}">${deviceInfo.name}</div></td>
		</tr>
		<tr>
			<td class="device_td_bg">所在道路：</td>
			<td><tags:xiangxuncache keyName="RoadInfo" id="${deviceInfo.roadId}"></tags:xiangxuncache></td>
			<td class="device_td_bg">设备功能：</td>
			<td>${dtNameStrs}</td>
		</tr>
		<tr>
			<td class="device_td_bg">所属部门：</td>
			<td><tags:xiangxuncache keyName="Department" id="${deviceInfo.orgId}"></tags:xiangxuncache></td>
			<td class="device_td_bg">IP 地 址：</td>
			<td>${deviceInfo.ip}</td>
		</tr>
		<tr>
			<td class="device_td_bg">添加时间：</td>
			<td><fmt:formatDate value="${deviceInfo.inserttime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td class="device_td_bg">超时设置：</td>
			<td>${deviceInfo.timeout}分钟</td>
		</tr>
	</table>
	<h4 class="xtcs_h4" style="margin:0;">建设信息</h4>
	<input type="hidden" name="menuid" value="${menuid}"/>
	<input type="hidden" name="page" value="${page}"/>
	<input type="hidden" name="id" value="${deviceInfo.id}"/>
	<table class="table table-border-bot tingche-table">
		<tr>
			<td class="device_td_bg">建设厂家：</td>
			<td width="30%"><tags:xiangxuncache keyName="DeviceCompanyInfo" id="${deviceInfo.companyId}"></tags:xiangxuncache></td>
			<td class="device_td_bg">建设时间：</td>
			<td>${deviceInfo.buildtimeStr}</td>
		</tr>
		<tr>
			<td class="device_td_bg">服务厂商：</td>
			<td><tags:xiangxuncache keyName="FactoryInfo" id="${deviceInfo.factoryId}"></tags:xiangxuncache></td>
			<td class="device_td_bg">网络运营商：</td>
			<td><tags:xiangxuncache keyName="Dic" typeCode="netcompany" id="${deviceInfo.buildispId}" ></tags:xiangxuncache></td>
		</tr>
		<tr>
			<td class="device_td_bg">接入方式：</td>
			<td><tags:xiangxuncache keyName="Dic" typeCode="netintype" id="${deviceInfo.buildnetworkId}" ></tags:xiangxuncache></td>
			<td class="device_td_bg">接电位置：</td>
			<td>${deviceInfo.powersource}</td>
		</tr>
		<tr>
			<td class="device_td_bg">现场照片：</td>
			<td colspan="3">
			  <c:choose>
			    <c:when test="${empty deviceInfo.photo1}">
			     <img style="height:30px;width:35px;margin-right:20px;" src="${root}/images/crossErrorPic.png" onclick="showLarge(this)"/>
			    </c:when>
			    <c:otherwise>
			      <img style="height:30px;width:35px;margin-right:20px;" src="${root}/${deviceInfo.photo1}" onclick="showLarge(this)"/>
			    </c:otherwise>
			  </c:choose>
			  
			  <c:choose>
				<c:when test="${empty deviceInfo.photo2}">
				 <img style="height:30px;width:35px;margin-right:20px;" src="${root}/images/crossErrorPic.png" onclick="showLarge(this)"/>
				</c:when>
				<c:otherwise>
				  <img style="height:30px;width:35px;margin-right:20px;" src="${root}/${deviceInfo.photo2}" onclick="showLarge(this)"/>
				</c:otherwise>
			  </c:choose>
			  
			  <c:choose>
				<c:when test="${empty deviceInfo.photo3}">
				  <img style="height:30px;width:35px;margin-right:20px;" src="${root}/images/crossErrorPic.png" onclick="showLarge(this)"/>
				</c:when>
				<c:otherwise>
			  	  <img style="height:30px;width:35px;margin-right:20px;" src="${root}/${deviceInfo.photo3}" onclick="showLarge(this)"/>
				</c:otherwise>
			  </c:choose>
			</td>
		</tr>
	</table>
	<div id="videoinfo_div" style="display:none;">
	    <h4 class="xtcs_h4" style="margin:0;">视频监控配置</h4>
		<table class="table tingche-table table-border-bot mar_b5">
			<tr>
			    <td class="device_td_bg">监控名称：</td>
			    <td width="30%">${videoInfo.name}</td>
			    <td class="device_td_bg" >监控类型：</td>
			    <td><tags:xiangxuncache keyName="Dic" typeCode="monittype" id="${videoInfo.videotypeCode}" ></tags:xiangxuncache></td>
			  </tr>
			  <tr>
			    <td class="device_td_bg">监控方向：</td>
			    <td><tags:xiangxuncache keyName="Dic" typeCode="direction" id="${videoInfo.directionCode}" ></tags:xiangxuncache></td>
			    <td class="device_td_bg">播放配置：</td>
			    <td>${1==videoInfo.usetemplate?'使用模板':'单独配置'}</td>
			  </tr>
			  <tr>
			    <td class="device_td_bg">所属部门：</td>
			    <td><tags:xiangxuncache keyName="Department" id="${videoInfo.orgId}"></tags:xiangxuncache></td>
			    <td class="device_td_bg">建设厂家：</td>
			    <td><tags:xiangxuncache keyName="DeviceCompanyInfo" id="${videoInfo.companyid}"></tags:xiangxuncache></td>
			  </tr>
			  <c:if test="${videoInfo.usetemplate == '0'}">
			  <tr>
			   	<td class="device_td_bg">流媒体服务器：</td>
			   	<td colspan="3" style="position:relative;">
				  <div style="position:absolute;left:10px; top:5px;">
			        IP地址：${videoInfo.ip}&nbsp;&nbsp;&nbsp;&nbsp;
				     端口号：${videoInfo.port}&nbsp;&nbsp;&nbsp;&nbsp;
				     用户名：${videoInfo.username}&nbsp;&nbsp;&nbsp;&nbsp;
				     密  码：${videoInfo.password}
				  </div>
			   	</td>
			  </tr>
			  </c:if>
			  <c:if test="${videoInfo.usetemplate == '1'}">
			  <tr>
				<td class="device_td_bg">流媒体服务器：</td>
				<td colspan="3" style="position:relative;">
				  <div style="position:absolute;left:10px; top:5px;">
					${videoInfo.realvideoaddressName}
				  </div>
				</td>
			  </tr>
			  </c:if>
		</table>
	</div>
  </div>
  <div class="btn_line">
	<input type="hidden" name="roadId" value="${roadId}" />
	<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
  </div>
</div>
<div id="menuContent" class="menuContent" style="display:none; position: absolute;" >
	<ul id="dtTreeSpace" class="ztree" style="margin-top: 0px;border: 1px solid #617775;background: #f0f6e4;width:210px;height:260px;overflow-y:scroll;overflow-x:auto;"></ul>
</div>

<div id="orgTreeContent" class="menuContent" style="display:none; position: absolute;" >
	<ul id="orgTreeSpace" class="ztree" style="margin-top: 0px;border: 1px solid #617775;background: #f0f6e4;width:210px;height:260px;overflow-y:scroll;overflow-x:auto;"></ul>
</div>

<script type="text/javascript">

	function showLarge(a) {
		top.tipsdownImgFd('图片特写', $(a).attr('src'));
	}

	function goList(){
		var name = window.parent.document.getElementById("s_name").value;
		var code = window.parent.document.getElementById("s_code").value;
		var companyId = window.parent.document.getElementById("dev-select").value;
		var devicetypecode = window.parent.document.getElementById("selXSF").value;
		var deviceTypeIds = window.parent.document.getElementById("deviceTypeIds").value;
		var orgId = window.parent.document.getElementById("search_orgId").value;
		var ip = window.parent.document.getElementById("s_ip").value;
		window.location.href ="${root}/property/deviceinfo/sublist/${menuid}/?page=${page}&name="+name+"&code="+code+"&companyId="+companyId+"&devicetypecode="+devicetypecode+"&deviceTypeIds="+deviceTypeIds+"&orgId="+orgId+"&ip="+ip+"&searchFlag=1";
	}
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>