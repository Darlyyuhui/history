<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div style="width:510px;margin:0;padding:0;border:0px;">
	<div style="width:260px;float:left;">
		<span style="font-weight:bold;">布控报警信息：</span><br />
		&nbsp;&nbsp;&nbsp;&nbsp;号牌号码：<select style="width:120px" onchange="selChange()" id="sel" /><br />
		&nbsp;&nbsp;&nbsp;&nbsp;颜&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：<span id='blackcolor'></span><br />
		&nbsp;&nbsp;&nbsp;&nbsp;布控类型：<span id='blacktype'></span><br />
		&nbsp;&nbsp;&nbsp;&nbsp;车辆类型：<span id='cartype'></span><br />
		&nbsp;&nbsp;&nbsp;&nbsp;报警方式：<span id='alarmtype'></span><br />
		<span style="font-weight:bold;">报警信息：</span><br />
		&nbsp;&nbsp;&nbsp;&nbsp;报警号牌：<span id='alarmCarnum'></span><br />
		&nbsp;&nbsp;&nbsp;&nbsp;报警时间：<span id='alarmTime'></span><br />
	</div>
	<div style="float:right;width:250px;height:250px;overflow:hidden;">
		<img id='mapcrossimg' src='images/defaults.jpg' onclick='window.parent.tipsdownImgFd("图片特写", this.src);' style='width: 250px;height: 211px;'/>
		<input type='button' value='上一张' onclick="changeImg('pre')">
		<input type='button' value='下一张' onclick="changeImg('next')">
	</div>
</div>
<script type="text/javascript">
	var mapCrossAlarmObj = mapCrossAlarm();
	var imgList = null;
	var imgIndex = 0;
	var code = "";
	
	$(function() {
		var map = ${mapobj};
		code = map.code;
		var black = map.black;
		var list = map.list.result;
		if(!list || list.length < 1 || !black) {
			$("#crossinfodiv").html("没有布控信息或者没有报警信息！");
			return;
		}
		
		imgList = list;
		imgIndex = 0;
		var cars = map.cars;
		
		for(var i=0; i<cars.length; i++) {
			// change的时候根据布控id进行查询布控信息
			// 只显示有报警的布控信息--剔除操作
			for(var j in list) {
				if(list[j].blackId == cars[i].blackId) {
					$("#sel").append("<option value=\""+cars[i].blackId+"\">"+cars[i].plateNum+"</option>");
					break;
				}
			}
		}
		
		$("#blackcolor").text(black.carPlateColorCode);
		$("#blacktype").text(list[0].alarmType);
		$("#cartype").text(black.carPlateTypeCode);
		$("#alarmtype").text(list[0].alarmType);
		$("#alarmCarnum").text(list[0].plateNum);
		$("#alarmTime").text(list[0].trrafficTime);
		$("#mapcrossimg").attr("src", list[0].imgPath);
		
		mapCrossAlarmObj.setCacheName("blackcolor", "platecolor", black.carPlateColorCode);
		mapCrossAlarmObj.setCacheName("cartype", "platetype", black.carPlateTypeCode);
	});
	
	function selChange() {
		mapCrossAlarmObj.getAlarmPageByParam(code, $("#sel").val(), 0);
	}
	
	function changeImg(type) {
		if(type == "pre") {
			if(imgIndex == 0)return;
			imgIndex -= 1;
			$("#mapcrossimg").attr("src",imgList[imgIndex].imgPath);
			$("#alarmTime").text(imgList[imgIndex].trrafficTime);
			$("#alarmCarnum").text(imgList[imgIndex].plateNum);
		}
		else if(type = "next") {
			if(imgIndex == imgList.length-1)return;
			imgIndex += 1;
			$("#mapcrossimg").attr("src",imgList[imgIndex].imgPath);
			$("#alarmTime").text(imgList[imgIndex].trrafficTime);
			$("#alarmCarnum").text(imgList[imgIndex].plateNum);
		}
	}
	
</script>
