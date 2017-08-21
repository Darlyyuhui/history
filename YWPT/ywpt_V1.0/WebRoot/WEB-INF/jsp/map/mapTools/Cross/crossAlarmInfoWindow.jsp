<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div style="width:500px;margin:0;padding:0;border:0px;">
	<div style="width:250px;float:left;">
		<table class="commonResultTable">
			<tr class="commonResultTableTitleRow">
				<td><span style="font-weight:bold;">设备信息：</span></td>
			</tr>
			<tr class="commonResultTableEvenRow">
				<td>&nbsp;&nbsp;&nbsp;&nbsp;设备名称：<span id='devicename'></span></td>
			</tr>
			<tr class="commonResultTableOddRow">
				<td>&nbsp;&nbsp;&nbsp;&nbsp;设备编码：<span id='devicecode'></span></td>
			</tr>
			<tr class="commonResultTableEvenRow">
				<td>&nbsp;&nbsp;&nbsp;&nbsp;设备地点：<span id='deviceaddress'></span></td>
			</tr>
			<tr class="commonResultTableOddRow">
				<td>&nbsp;&nbsp;&nbsp;&nbsp;管理部门：<span id='managerdept'></span></td>
			</tr>
		</table>
		<table class="commonResultTable" style="margin-top:5px;">
			<tr class="commonResultTableTitleRow">
				<td><span style="font-weight:bold;">布控信息：</span></td>
			</tr>
			<tr class="commonResultTableEvenRow">
				<td>&nbsp;&nbsp;&nbsp;&nbsp;号牌号码：<span id='blackcarnum'></span></td>
			</tr>
			<tr class="commonResultTableOddRow">
				<td>&nbsp;&nbsp;&nbsp;&nbsp;布控类型：<span id='blacktype'></span></td>
			</tr>
			<tr class="commonResultTableEvenRow">
				<td>&nbsp;&nbsp;&nbsp;&nbsp;车辆颜色：<span id='blackcolor'></span></td>
			</tr>
			<tr class="commonResultTableOddRow">
				<td>&nbsp;&nbsp;&nbsp;&nbsp;车辆类型：<span id='biCarType'></span></td>
			</tr>
			<tr class="commonResultTableEvenRow">
				<td>&nbsp;&nbsp;&nbsp;&nbsp;车辆状态：<span id='blackcarstate'></span></td>
			</tr>
			<tr class="commonResultTableOddRow">
				<td>&nbsp;&nbsp;&nbsp;&nbsp;号牌种类：<span id='blackcarnumtype'></span></td>
			</tr>
			<tr class="commonResultTableEvenRow">
				<td>&nbsp;&nbsp;&nbsp;&nbsp;号牌颜色：<span id='blackcarnumcolor'></span></td>
			</tr>
			<tr class="commonResultTableOddRow">
			</tr>
		</table>
	</div>
	<div style="float:left;width:245px;overflow:hidden;margin-left:5px;">
		<table class="commonResultTable">
			<tr class="commonResultTableTitleRow">
				<td><span style="font-weight:bold;">报警信息：</span></td>
			</tr>
			<tr class="commonResultTableEvenRow">
				<td>&nbsp;&nbsp;&nbsp;&nbsp;报警号牌：<span id='alarmCarnum'></span></td>
			</tr>
			<tr class="commonResultTableOddRow">
				<td>&nbsp;&nbsp;&nbsp;&nbsp;报警时间：<span id='alarmTime'></span></td>
			</tr>
		</table>
		<img id='mapcrossimg' src='images/defaults.jpg' onclick='window.parent.tipsdownImgFd("图片特写", this.src);' style='width: 245px;height: 211px;margin:5px 0;'/>
		<ul class="resultPager" style="width:100%;">
			<li id="cainfopreimg" style="float:left;padding-right:10px;cursor:pointer;">上一条</li>
			<li id="cainfonextimg" style="float:left;cursor:pointer;">下一条</li>
			<li style="float:right;border:0;padding:0">
			<a id='cainfocanceltitle' title="关闭报警提示"><img id='cainfocancel' src='images/map/cross/alarmon.png' style="height:24px; cursor:pointer;"/></a>
			</li>
		</ul>
	</div>
	<div class="clear"></div>
</div>
<script type="text/javascript">
var crossAPI;
MapFactory.Require([
	"ItmsMap/Cross/Cross*"
],function(cross){
	crossAPI = cross();
});
$(function(){
	var alarmIndex = 0;
	var obj = ${mapobj};
	var page = obj.page;
	var pageNo = page.currentPageNo, pageCount = page.totalPageCount, pageSize = page.pageSize;
	var alarmList = page.result;
	var alarmLen = alarmList.length;
	var alarm = null;
	var blackList = obj.blackList;
	var black = null;
	var deviceInfo = obj.deviceInfo;
	
	function _init() {
		$("#devicename").text(deviceInfo.name);
		$("#devicecode").text(deviceInfo.code);
		$("#deviceaddress").text(deviceInfo.roadName);
		$("#managerdept").text(deviceInfo.orgNames);
		
		var showalarmbtn = '${param.showalarmbtn}';
		if(showalarmbtn == 'true') {
			$("#cainfocanceltitle").css("visibility","visible");
		}
		else {
			$("#cainfocanceltitle").css("visibility","hidden");
		}
		
		$("#cainfocancel").toggle(function(){
			$("#cainfocanceltitle")[0].title = "恢复报警提示";
			$(this).attr("src","images/map/cross/alarmoff.png");
			crossAPI.setCrossAlarmCancelCode(deviceInfo.code, true);
		},function(){
			$("#cainfocanceltitle")[0].title = "关闭报警提示";
			$(this).attr("src","images/map/cross/alarmon.png");
			crossAPI.setCrossAlarmCancelCode(deviceInfo.code, false);
		});
		
		$("#cainfopreimg").click(function(){
			if(alarmIndex == 0)return;
			alarmIndex -= 1;
			updateInfoWidow();
			$("#cainfonextimg").removeClass("disableselect");
			if(alarmIndex == 0)$("#cainfopreimg").addClass("disableselect");
		});
		
		$("#cainfonextimg").click(function(){
			if(alarmIndex == alarmLen-1)return;
			alarmIndex += 1;
			updateInfoWidow();
			$("#cainfopreimg").removeClass("disableselect");
			if(alarmIndex == alarmLen-1)$("#cainfonextimg").addClass("disableselect");
		});
		_bindHoverEvt("cainfopreimg");
		_bindHoverEvt("cainfonextimg");
		$("#cainfopreimg").addClass("disableselect");
		if(alarmIndex == alarmLen-1)$("#cainfonextimg").addClass("disableselect");
	}
	
	function _bindHoverEvt(elemId){
		$("#"+elemId).mouseover(function(){
			if($("#"+elemId)[0].className == 'disableselect')return;
			$("#"+elemId).addClass("hover");
		});
		$("#"+elemId).mouseout(function(){
			$("#"+elemId).removeClass("hover");
		});
	}
	
	function getBlack(blackId) {
		for(var i=0; i<alarmLen; i++) {
			if(blackId == blackList[i].id) {
				return blackList[i];
			}
		}
		return null;
	}
	
	function updateInfoWidow() {
		alarm = alarmList[alarmIndex];
		if(!alarm)return;
		black = getBlack(alarm.blackId);
		if(black) {
			// 布控车辆类型
			$("#blackcarnum").text(black.carPlateNum);
			// 布控车辆类型
			$("#biCarType").text(black.carTypeCode);
			// 布控类型
			$("#blacktype").text(black.blackTypeCode);
			// 布控车辆状态
			$("#blackcarstate").text(black.carStateCode);
			// 布控号牌种类
			$("#blackcarnumtype").text(black.carPlateTypeCode);
			// 布控号牌颜色
			$("#blackcarnumcolor").text(black.carPlateColorCode);
			// 布控车辆颜色
			$("#blackcolor").text(black.carColorCode);
		}
		
		$("#alarmCarnum").text(alarm.plateNum);
		$("#alarmTime").text(alarm.startTimeStr);
		$("#mapcrossimg").attr("src", alarm.imgPath);
	}
	
	// 延迟500毫秒后，设置信息框的值，因为直接设置有可能页面还没有加载完成
	setTimeout(function(){
		_init();
		updateInfoWidow();
	}, 500);
});
	
</script>
