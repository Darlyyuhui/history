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
	var crossObj;
	MapFactory.Require([
		"ItmsMap/Cross/Cross*"
	],function(cross){
		crossObj = cross();
		crossObj.showAll();
		
		$("#crossAlarmSearch").click(function(){
			crossObj.alarmSearch($("#crossAlarmName").val(),$("#alarmstartTime").val(),$("#alarmendTime").val());
		});
		$("#alarmReSetQuery").click(function(){
			$("#crossAlarmName").val("");
			$("#alarmstartTime").val("");
			$("#alarmendTime").val("");
		});
	});

//-->
</script>
<div class="mar_5">
  <table class="map_table" style="margin-right:-20px\0;#margin-right:-20px;_margin-right:-20px;">
    <tr>
      <td width="64">报警车辆：</td>
      <td><input id="crossAlarmName" type="text" style="width:140px;height:28px;padding:4px; margin:0;overflow:hidden;"></td> 
    </tr>  
       <tr>
        <td>开始时间：</td>
        <td><input id="alarmstartTime" name="startTime" readonly="readonly" style="width:140px;height:28px;padding:4px;" type="text"
    value="${alarmstartTime}" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'alarmendTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
      </tr>
      <tr>
        <td>结束时间：</td>
        <td><input id="alarmendTime" value="${alarmendTime}" name="endTime"  readonly="readonly" style="width:140px;height:28px;padding:4px;" type="text"
    onfocus="WdatePicker({minDate:'#F{$dp.$D(\'alarmstartTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
      </tr>
     <tr>    
         <td colspan="2" style="text-align:center; padding-top:10px;">
         <input id="crossAlarmSearch" type="button" class="btn btn-info" value="查询" >
         <input id="alarmReSetQuery" type="button" class="btn btn-info mar_l10" value="重置" />
         </td>
     </tr>
     
  </table>
</div>
