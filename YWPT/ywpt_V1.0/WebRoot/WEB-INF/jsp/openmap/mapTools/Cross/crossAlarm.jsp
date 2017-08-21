<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
.input {
	border-left:1px solid #1f6377;
	border-top:1px solid #1f6377;
	border-right:1px solid #b3bfca;
	border-bottom:1px solid #b3bfca;
	height:18px;
	padding:3px;
}
</style>
<script type="text/javascript">
<!--
	if(!mapCrossObj)mapCrossObj = mapCross();
	mapCrossObj.showAll();// 显示所有卡口设备
	
	var mapCrossAlarmObj = mapCrossAlarm();
	
	var deviceCodes = [];
	function searchAlarmCar() {
		$.ajax({
		  type: "POST",
		  dataType: "json",
		  url: "cross/alarm/getlist",
		  cache: false,
		  data: "plateNum="+$("#searchname").val(),
		  success: function(list){
			  mapCrossAlarmObj.setAlarmSearchResult(list,true);
		  }
		});
	}
	
	//mapCrossAlarmObj.alarmSearch();
	mapCrossAlarmObj.getAlarmCodes();
	function alarmCodesHandler(message) {
		//debugger;
		var str;
		if (message.text == undefined) {
			str = message.textContent;
		} else {
			str = message.text;
		}
		mapCrossAlarmObj.updateAlarmLayer(str.split(","));
	}

//-->
</script>
<table class="table-sel-input mar_b5 mar_t5 mar_l5 mar_r5">
  <tr>
    <td width="56">报警车辆</td>
    <td><input id="searchname" type="text" style="width:150px;"/></td>
  </tr>
</table>
<div class="btn_line" style="padding:5px;">
  <input type="button" class="btn btn-info" style="padding:3px 10px;" value="查询" onclick="searchAlarmCar()"/>
</div>
