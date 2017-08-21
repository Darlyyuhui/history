<%@ page language="java" pageEncoding="UTF-8"%>
<link href="${root}/compnents/jquery-jbox/2.3/Skins/Bootstrap/jbox.css" rel="stylesheet" />
<script src="${root}/compnents/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${root}/compnents/jquery-jbox/2.3/i18n/jquery.jBox-zh-CN.min.js" type="text/javascript"></script>
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
<style type="text/css">
.ul_lable {
	wihte-space:nowrap;
	width:70px;
}
</style>
<div class="row-fluid">
	<div class="conten_box">
		<h4 class="xtcs_h4" style="margin-top: 0;">系统参数</h4>
		<form id="inputForm" class="form-horizontal" action="${root}/system/params/doUpdateSysParams"  method="post" style="margin: 0; padding: 0">
			<input type="hidden" name="menuid" value="${menuid}" />
			<div class="row-fluid">
				<ul class="nav nav-tabs" style="padding-left:10px;margin:0;">
					<li class="active"><a href="#tab1" data-toggle="tab">系统参数</a></li>
					<li><a href="#tab5" data-toggle="tab">地图配置</a></li>
				</ul>
				<div class="tab-content" style="margin: 0; padding: 0; height: 600px;">
					<!-- 系统参数页签 start -->
					<div class="tab-pane mar_5 active" id="tab1">
						<table class="table table-border-bot bukong-table table-border-rl table-padd10-lr">
							<tr>
								<td class="device_td_bg3">平台名称：</td>
								<td colspan="3"><input type="text" class="text required" maxlength="30" minlength="1" id="itmsname" name="itmsname" value="${itmsname}" style="width: 30%;" /></td>
							</tr>
							<tr style="display: none;">
								<td class="device_td_bg3">区域编码：</td>
								<td colspan="3"><input type="text" class="text required" maxlength="6" minlength="6" id="area-code" name="areacode" digits=true value="${areacode}" style="width: 30%;" /></td>
							</tr>
							<tr style="display: none;">
								<td class="device_td_bg3">权限设置：</td>
								<td class="inputImportent" colspan="3">
									<ul class="ul_checkbox">
									  <li><input type="radio" id="chk-isCross" name="isStartDepartControl" value="1" ${isStartDepartControl== '1'?'checked':''} onclick="isStartDepart(this)">启用</li>
									  <li><input type="radio" name="isStartDepartControl" value="0" ${isStartDepartControl== '0'?'checked':''} onclick="isStart(this)">不启用【不启用时登录人能看到所有部门的信息】</li>
									  <div class="clear"></div>
									</ul>
								</td>
							</tr>
							
							<tr>
								<td class="device_td_bg3" style="width:186px;">频发故障设备TOP数量设置：</td>
								
								<td colspan="3">
									只显示前 <input type="text" id="topnum" style="width:50px;" name="topnum" value="${topnum}" class="required digits" maxlength="3" minlength="1" min="1" max="100"/> 名的设备
								</td>
							</tr>
							
							<tr>
								<td class="device_td_bg3" style="width:186px;">网络状态监测刷新时间设置：</td>
								
								<td colspan="3">
									每隔 <input type="text" id="netrefnum"  style="width:50px;" name="netrefnum" value="${netrefnum}" class="required digits" maxlength="3" minlength="1" min="1" max="100"/> 分钟监测一次
								</td>
							</tr>
							

							
							
							<!--  
							<tr>
								<td class="device_td_bg3" style="width:186px;">机柜采样间隔时间设置：</td>
								
								<td colspan="3">
									每隔 <input type="text" id="netrefnum"  style="width:50px;" name="netrefnum" value="" class="required digits" maxlength="3" minlength="1" min="1" max="100"/> 秒监测一次
								</td>
							</tr>
							-->

							<tr>
								<td class="device_td_bg3" style="width:186px;">启用监控项设置：</td>
								
								<td colspan="3">
									<ul class="ul_checkbox">
									  <li><input type="checkbox" id="chk-isCross" name="sysMondule" value="1" ${opendevice=='1'?'checked':''}>卡口</li>
									  <li><input type="checkbox" id="chk-isVio" name="sysMondule" value="2" ${openserver=='1'?'checked':''}>服务器</li>
									  <li><input type="checkbox" id="chk-isVideo" name="sysMondule" value="3" ${opendatabase=='1'?'checked':''}>数据库</li>
									  <li><input type="checkbox" id="chk-isCount" name="sysMondule" value="4" ${openftp=='1'?'checked':''}>FTP服务</li>
									  <li><input type="checkbox" id="chk-isGps" name="sysMondule" value="5" ${openproject=='1'?'checked':''}>平台</li>
									  <li><input type="checkbox" id="chk-isBlack" name="sysMondule" value="6" ${opencabinet=='1'?'checked':''}>智能机柜</li>
									  <div class="clear"></div>
									</ul>
								</td>
							</tr>
							
							<tr>
								<td class="device_td_bg3" style="width:186px;">工单派发类型：</td>
								<td colspan="3">
									<input type="checkbox" id="openworkorder" name="openworkorder" value="1" ${openworkorder=='1'?'checked':'0'}>自动派发
								</td>
							</tr>
							
							<tr>
								<td class="device_td_bg3" style="width:186px;">运维人员最大工单数量：</td>
								<td colspan="1">
									最大可获得工单<input type="text" id="maxworder" style="width:50px;" maxlength="6" class="digits" name="maxworder" value="${maxworder}"> 份 /人     <font size="1" color="red">* 此项只有配置工单自动派发后才有效</font> 
								</td>
							</tr>
							
							<tr>
								<td class="device_td_bg3" style="width:186px;">工单派发监控类型：</td>
								
								<td colspan="3">
								    <ul class="ul_checkbox">
									  <li style="width: 20%;min-width: 265px;">服务器检测状态：</li>
									  <li><input type="checkbox" id="chk-isCross" name="orderType" value="1" ${torderCpuStatus=='1'?'checked':''}>CPU状态</li>
									  <li><input type="checkbox" id="chk-isVio" name="orderType" value="2" ${torderMemoryStatus=='1'?'checked':''}>内存状态</li>
									  <li><input type="checkbox" id="chk-isVideo" name="orderType" value="3" ${torderDiskStatus=='1'?'checked':''}>硬盘状态</li>
									  <div class="clear"></div>
									</ul>
									<ul class="ul_checkbox">
									  <li style="width: 20%;min-width: 265px;">卡口检测状态：</li>
									  <li><input type="checkbox" id="chk-isCount" name="orderType" value="4" ${torderPowerStatus=='1'?'checked':''}>供电状态</li>
									  <div class="clear"></div>
									</ul>
									 <ul class="ul_checkbox">
									  <li style="width: 20%;min-width: 265px;">卡口、数据库、ftp、平台通用检测状态：</li>
									  <li><input type="checkbox" id="chk-isGps" name="orderType" value="5" ${torderNetStatus=='1'?'checked':''}>网络状态</li>
									  <li><input type="checkbox" id="isBlack" name="orderType" value="6" ${torderDataStatus=='1'?'checked':''}>运行状态</li>
									  <div class="clear"></div>
									</ul>
									<ul class="ul_checkbox">
									  <li style="width: 20%;min-width: 265px;">智能机柜检测状态：</li>
									  <li><input type="checkbox" id="istemperature" name="orderType" value="7" ${tordertemperature=='1'?'checked':''}>温度状态</li>
									  <li><input type="checkbox" id="ishumidity" name="orderType" value="8" ${torderhumidityStatus=='1'?'checked':''}>湿度状态</li>
									  <li><input type="checkbox" id="isnetstatus" name="orderType" value="9" ${torderCabinetNetStatus=='1'?'checked':''}>网络状态</li>
									  <li><input type="checkbox" id="ispowerstatus" name="orderType" value="10" ${torderCabinetPowerStatus=='1'?'checked':''}>供电状态</li>
									 <c:if test="${cabinet_pattern=='1'}">
									  <li><input type="checkbox" id="issmoke" name="orderType" value="11" ${tordersmokeStatus=='1'?'checked':''}>烟感状态</li>
									  <li><input type="checkbox" id="isshock" name="orderType" value="12" ${tordershockStatus=='1'?'checked':''}>振动状态</li>
									  <li><input type="checkbox" id="isACterminal" name="orderType" value="13" ${torderACterminalStatus=='1'?'checked':''}>AC端子状态</li>
									  <li><input type="checkbox" id="isACchazuo" name="orderType" value="14" ${torderACchazuoStatus=='1'?'checked':''}>AC插座状态</li>
									   </c:if>
									  <div class="clear"></div>
									</ul>
								</td>
							</tr>
							
						</table>
						<div class="tab-content" style="margin: 0; padding: 0; height: 400px;">
					<h4 class="xtcs_h4" style="margin:0;">机柜配置</h4>
					<table class="table table-border-bot bukong-table table-border-rl table-padd10-lr">
					<tr>
								<td class="device_td_bg3" style="width:186px;">机柜温度正常范围：</td>
								
								<td colspan="3">
									<input type="text" id="temrangemin"  style="width:50px;" name="icabinet_temperature_range_min" value="${icabinet_temperature_range_min}" class="required digits" maxlength="6" minlength="1" min="-20000" max="20000"/>℃ —
									<input type="text" id="temrangemax"  style="width:50px;" name="icabinet_temperature_range_max" value="${icabinet_temperature_range_max}" class="required digits" maxlength="6" minlength="1" min="-20000" max="20000"/>℃
								</td>
								
							</tr>
							<tr>
								<td class="device_td_bg3" style="width:186px;">机柜湿度正常范围：</td>
								
								<td colspan="3">
									<input type="text" id="humrangmin"  style="width:50px;" name="icabinet_humidity_range_min" value="${icabinet_humidity_range_min}" class="required digits" maxlength="3" minlength="1" min="1" max="100"/>% —
									<input type="text" id="humrangmax"  style="width:50px;" name="icabinet_humidity_range_max" value="${icabinet_humidity_range_max}" class="required digits" maxlength="3" minlength="1" min="1" max="100"/>%
								</td>
								
							</tr>
							<c:if test="${cabinet_pattern=='1'}">
							<tr>
								<td class="device_td_bg3" style="width:186px;">机柜振动正常范围：</td>
								
								<td colspan="3">
									<input type="text" id="shorangmin"  style="width:50px;" name="icabinet_shock_range_min" value="${icabinet_shock_range_min}" class="required digits" maxlength="3" minlength="1" min="1" />μm —
									<input type="text" id="shorangmax"  style="width:50px;" name="icabinet_shock_range_max" value="${icabinet_shock_range_max }" class="required digits" maxlength="3" minlength="1" min="1" />μm
								</td>
								
							</tr>
							<tr>
								<td class="device_td_bg3" style="width:186px;">AC插座电压正常范围：</td>
								
								<td colspan="3">
									<input type="text" id="volrangmin"  style="width:50px;" name="icabinet_ac_voltage_range_min" value="${icabinet_ac_voltage_range_min}" class="required digits" maxlength="3" minlength="1" min="0" max="220"/>V —
									<input type="text" id="volrangmax"  style="width:50px;" name="icabinet_ac_voltage_range_max" value="${icabinet_ac_voltage_range_max}" class="required digits" maxlength="3" minlength="1" min="220" max="380"/>V
								</td>
								
							</tr>
							<tr>
								<td class="device_td_bg3" style="width:186px;">AC插座电流正常范围：</td>
								
								<td colspan="3">
									<input type="text" id="currangmin"  style="width:50px;" name="icabinet_ac_current_range_min" value="${icabinet_ac_current_range_min}" class="required digits" maxlength="4" minlength="1" min="0" max="1000" />mA —
									<input type="text" id="currangmax"  style="width:50px;" name="icabinet_ac_current_range_max" value="${icabinet_ac_current_range_max}" class="required digits" maxlength="4" minlength="1" min="0" max="1000"/>mA
								</td>
								
							</tr>
						   <tr>
								<td class="device_td_bg3" style="width: 186px;">传感器采样间隔：</td>

								<td colspan="3"><input type="text" id="icabinet_samplingtime" name="icabinet_samplingtime" style="width: 50px;"	value="${icabinet_samplingtime}" class="required digits" maxlength="3" minlength="1" min="1" max="300" />分钟</td>
								
							</tr>
							 </c:if>
					</table>	
              <c:if test="${cabinet_pattern=='1'}">
	<input id="cancel_btn1" class="btn btn-info" type="button" value="下发" onclick="showList()" />  
              </c:if>
					   	
			
		                    </div>	
					</div>
					<!-- 系统参数页签end -->
					
					
					<!-- 地图配置页签 start -->
					<div class="tab-pane mar_10" id="tab5">
						<ul class="ul_checkbox">
						  <li>地图引擎：</li>
						  <li><input type="radio" name="mapEngineType" value="0" ${mapEngineType=='0'?'checked':''} onchange="changeMapEngine('0')"> ARCGIS</li>
						  <li><input type="radio" name="mapEngineType" value="1" ${mapEngineType=='1'?'checked':''} onchange="changeMapEngine('1')"> PGIS</li>
						  <li><input type="radio" name="mapEngineType" value="2" ${mapEngineType=='2'?'checked':''} onchange="changeMapEngine('2')"> GEOSERVER</li>
						  <li><input type="radio" name="mapEngineType" value="3" ${mapEngineType=='3'?'checked':''} onchange="changeMapEngine('3')"> SUPERMAP</li>
						  <div class="clear"></div>
						</ul>
						<div style="margin-top:10px;">
							<table id="arcgis" class="table table-border-bot table-padd10-lr table-border-rl tingche-table">
								<tr>
									<td width="150" class="device_td_bg4">服务地址：</td>
									<td><input type="text" class="text required" id="serverurl" name="serverurl" value="" style="width:70%;" onchange="setMapParams()"/></td>
								</tr>
								<tr>
									<td class="device_td_bg4">比例尺：</td>
									<td><input type="text" class="text required" id="resolutions" name="resolutions" value="" style="width:70%;" onchange="setMapParams()"/></td>
								</tr>
								<tr>
									<td class="device_td_bg4">中心点以及地图级别：</td>
									<td><input type="text" class="text required" id="centerandzoom" name="centerandzoom" value="${atms.map_center_zoom}" style="width:70%;" onchange="setMapParams()"/></td>
								</tr>
								<tr>
									<td class="device_td_bg4">最大范围：</td>
									<td><input type="text" class="text required" id="maxextent" name="maxextent" value="${atms.map_max_extent}" style="width:70%;" onchange="setMapParams()"/></td>
								</tr>
								<tr>
									<td class="device_td_bg4">卡口报警时间间隔：</td>
									<td><input type="text" class="required digits" id="mapAlarmTimeSpace" name="mapAlarmTimeSpace" value="${atms.mapAlarmTimeSpace}" style="width:70%;" maxlength="5" onchange="setMapParams()"/>秒</td>
								</tr>
								<tr>
									<td class="device_td_bg4">兴趣点设置：</td>
									<td>
										<ul class="ul_checkbox">
											<li><input type="checkbox" name="map_poi" value="1" ${map_education_org=="1" ? "checked" : ""}/>教育机构</li>
											<li><input type="checkbox" name="map_poi" value="2" ${map_enterprise=="1" ? "checked" : ""}/>企事业单位</li>
											<li><input type="checkbox" name="map_poi" value="3" ${map_government=="1" ? "checked" : ""}/>行政单位</li>
											<li><input type="checkbox" name="map_poi" value="4" ${map_medical_place=="1" ? "checked" : ""}/>医疗卫生</li>
											<li><input type="checkbox" name="map_poi" value="5" ${map_oil_station=="1" ? "checked" : ""}/>汽车服务</li>
											<li><input type="checkbox" name="map_poi" value="6" ${map_org=="1" ? "checked" : ""}/>行政区面</li>
											<li><input type="checkbox" name="map_poi" value="7" ${map_scenic_spot=="1" ? "checked" : ""}/>旅游景点</li>
											<li><input type="checkbox" name="map_poi" value="8" ${map_supermarket=="1" ? "checked" : ""}/>超市广场</li>
											<li><input type="checkbox" name="map_poi" value="9" ${map_township_gov=="1" ? "checked" : ""}/>乡镇点</li>
											<li><input type="checkbox" name="map_poi" value="10" ${map_pulbic_places=="1" ? "checked" : ""}/>其他</li>
											<li><input type="checkbox" name="map_poi" value="11" ${map_road=="1" ? "checked" : ""}/>道路</li>
										</ul>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<!-- 地图配置页签end -->
				</div>
			</div>
			<div class="btn_line">
				<!--  <button id="subbutg" class="btn btn-info"  type="submit" >确 定</button>-->
			<input id="submit_btn" onclick="checkForm();" class="btn btn-info mar_r10" type="button" value="保存" />
			</div>
		</form>
		<div class="clear"></div>
	</div>
</div>

 <script type="text/javascript">
 
 function checkForm(){
	 
	var flag=true;
	var temrangemin=$("#temrangemin").val();
	var temrangemax=$("#temrangemax").val();
	
	var humrangmin=$("#humrangmin").val();
	var humrangmax=$("#humrangmax").val();
	
	var shorangmin=$("#shorangmin").val();
	var shorangmax=$("#shorangmax").val();
	var volrangmin=$("#volrangmin").val();
	var volrangmax=$("#volrangmax").val();
	var currangmin=$("#currangmin").val();
	var currangmax=$("#currangmax").val();
	 if(parseInt(temrangemin)>parseInt(temrangemax)){
		 flag=false;
		 alert("温度最大值小于最小值！");
	 }else if(parseInt(humrangmin)>parseInt(humrangmax)){
		 flag=false;
		 alert("湿度最大值小于最小值！");
	 }else if(parseInt(shorangmin)>parseInt(shorangmax)){
		 flag=false;
		 alert("振动最大值小于最小值！");
	 }else if(parseInt(volrangmin)>parseInt(volrangmax)){
		 flag=false;
		 alert("AC电压最大值小于最小值！");
	 }else if(parseInt(currangmin)>parseInt(currangmax)){
		 flag=false;
		 alert("AC电流最大值小于最小值！");
	 }
	 
	 if(flag){
		 $("#inputForm").submit();
	 }

 }
 
</script>

<script>

function showList(){
	var flag=true;
	var temrangemin=$("#temrangemin").val();
	var temrangemax=$("#temrangemax").val();
	var humrangmin=$("#humrangmin").val();
	var humrangmax=$("#humrangmax").val();
	var shorangmin=$("#shorangmin").val();
	var shorangmax=$("#shorangmax").val();
	var volrangmin=$("#volrangmin").val();
	var volrangmax=$("#volrangmax").val();
	var currangmin=$("#currangmin").val();
	var currangmax=$("#currangmax").val();
	var samplingtime = $("#icabinet_samplingtime").val();
	if(parseInt(temrangemin)>parseInt(temrangemax)){
		 flag=false;
		 alert("温度最大值小于最小值,下发失败！");
	 }else if(parseInt(humrangmin)>parseInt(humrangmax)){
		 flag=false;
		 alert("湿度最大值小于最小值,下发失败！");
	 }else if(parseInt(shorangmin)>parseInt(shorangmax)){
		 flag=false;
		 alert("振动最大值小于最小值,下发失败！");
	 }else if(parseInt(volrangmin)>parseInt(volrangmax)){
		 flag=false;
		 alert("AC电压最大值小于最小值,下发失败！");
	 }else if(parseInt(currangmin)>parseInt(currangmax)){
		 flag=false;
		 alert("AC电流最大值小于最小值,下发失败！");
	 }
	 
	 if(flag){
		 
	 
	var params = [
		           "temrangemin=" + temrangemin,
			       "temrangemax=" + temrangemax,
			       "humrangmin=" + humrangmin,
			       "humrangmax=" + humrangmax,
			       "shorangmin=" + shorangmin,
			       "shorangmax=" + shorangmax, 
			       "volrangmin=" + volrangmin,
			       "volrangmax=" + volrangmax,
			       "currangmin=" + currangmin,
			       "currangmax=" + currangmax, 
			       "samplingtime=" + samplingtime 
			      ].join("&");
	window.location.href = "${root}/property/cabinfo/savebeforesend/${menuid}/?" + params;	
	}
	}</script>
	
<script>
	function isStartDepart(a) {
		if ($(a).attr("checked") == 'checked') {
			$("#user-depart-options").show();
		}
	}
	
	function isStart(a) {
		if ($(a).attr("checked") == 'checked') {
			$("#user-depart-options").hide();
		}
	}

	$(document).ready(function() {
		//聚焦第一个输入框
		$("#user-name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate();
	});
	
	var arcgisRestUrl = '${atms.arcgis_rest_url}';
	var arcgisRes = '${atms.arcgis_resolutions}';
	var arcgiscenterZoom = '${atms.map_center_zoom}';
	var arcgismaxExtent = '${atms.map_max_extent}';
	var geoserverUrl = '${atms.geoserver_base_url}';
	var geoserverRes = '${atms.geoserver_resolutions}';
	var geoservercenterZoom = '${atms.map_center_zoom}';
	var geoservermaxExtent = '${atms.map_max_extent}';
	var supermapUrl = '${atms.supermap_base_url}';
	var supermapcenterZoom = '${atms.map_center_zoom}';
	var supermapmaxExtent = '${atms.map_max_extent}';
	var maptype = '${mapEngineType}';
	
	function changeMapEngine(type) {
		maptype = type;
		$("#resolutions").removeAttr("disabled");
		if(type == '0') {
			$("#serverurl").val(arcgisRestUrl);
			$("#resolutions").val(arcgisRes);
			$("#centerandzoom").val(arcgiscenterZoom);
			$("#maxextent").val(arcgismaxExtent);
		}
		else if(type == '1') {
			$("#serverurl").val("");
			$("#resolutions").val("");
			$("#centerandzoom").val("");
			$("#maxextent").val("");
		}
		else if(type == '2') {
			$("#serverurl").val(geoserverUrl);
			$("#resolutions").val(geoserverRes);
			$("#centerandzoom").val(geoservercenterZoom);
			$("#maxextent").val(geoservermaxExtent);
		}
		else if(type == '3') {
			$("#resolutions").val("");
			$("#resolutions").attr("disabled", "disabled");
			$("#serverurl").val(supermapUrl);
			$("#centerandzoom").val(supermapcenterZoom);
			$("#maxextent").val(supermapmaxExtent);
		}
	}
	function setMapParams() {
		if(maptype == '0') {
			arcgisRestUrl = $("#serverurl").val();
			arcgisRes = $("#resolutions").val();
			arcgiscenterZoom = $("#centerandzoom").val();
			arcgismaxExtent = $("#maxextent").val();
		}
		else if(maptype == '2') {
			geoserverUrl = $("#serverurl").val();
			geoserverRes = $("#resolutions").val();
			geoservercenterZoom = $("#centerandzoom").val();
			geoservermaxExtent = $("#maxextent").val();
		}
		else if(maptype == '3') {
			supermapUrl = $("#serverurl").val();
			supermapcenterZoom = $("#centerandzoom").val();
			supermapmaxExtent = $("#maxextent").val();
		}
	}
	(function(){
		if(maptype == '0') {
			$("#serverurl").val(arcgisRestUrl);
			$("#resolutions").val(arcgisRes);
		}
		else if(maptype == '2') {
			$("#serverurl").val(geoserverUrl);
			$("#resolutions").val(geoserverRes);
		}
		else if(maptype == '3') {
			$("#serverurl").val(supermapUrl);
			$("#resolutions").attr("disabled", "disabled");
		}
		var isautodel = '${isautodel}';
		var savedate = '${savedate}';
		var div = document.getElementById("deldiv");
		var savedate1 = document.getElementById("savedate");
		if(isautodel == '0'){
			div.style.display = "none";
			$("#isautodel").attr("checked",false);
		}else{
			div.style.display = "";
			$("#isautodel").attr("checked",true);
			savedate1.value(savedate);
		}
	})();
</script>
