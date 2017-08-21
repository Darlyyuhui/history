<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	MapFactory.Require([
		"ItmsMap/Cross/Cross*",
		"ItmsMap/Util/ModuleMessage*",
		"ItmsMap/Util/ActiveMQWarpper*",
		"ItmsMap/Util/ModuleManager*"
	],function(cross, ModuleMessage, amq, moduleManager){
		var api = cross();
		api.statistic("stattable");
		
		// 显示所有报警信息
		api.getAlarmCodes();
		
		var moduleid = "${param.moduleid}";
		if(!moduleid)return;
		moduleManager.resetTool(moduleid);
		var ico = {initStatus: "mapAMQModuleClicked", classClicked: "mapAMQModuleClicked", classNormal: "mapAMQModuleNormal",label:"卡口报警实时刷新"};
		if(amq.isListenerExist("cross_current_alarm")) {
			api.autoFresh(true);
		}else {
			ico.initStatus = "mapAMQModuleNormal";
			api.autoFresh(false);
		}
		moduleManager.addTool(moduleid, ico, function(obj){
			if(obj.status == "normal") {
				api.autoFresh(false);
				ModuleMessage.showMessage("关闭卡口报警数据实时刷新！",ModuleMessage.SUCCESS);
			}
			else {
				api.getAlarmCodes();
				api.autoFresh(true);
				ModuleMessage.showMessage("开启卡口报警数据实时刷新！",ModuleMessage.SUCCESS);
			}
		});
		var icoDeviceStatus = {initStatus: "mapAMQModuleClicked", classClicked: "mapAMQModuleClicked", classNormal: "mapAMQModuleNormal",label:"卡口状态实时刷新"};
		if(amq.isListenerExist("device_status_log")) {
			api.autoFreshDeviceStatus(true);
		}else{
			icoDeviceStatus.initStatus = "mapAMQModuleNormal";
			api.autoFreshDeviceStatus(false);
		}
		moduleManager.addTool(moduleid, icoDeviceStatus, function(obj){
			if(obj.status == "normal") {
				api.autoFreshDeviceStatus(false);
				ModuleMessage.showMessage("关闭卡口状态实时刷新！",ModuleMessage.SUCCESS);
			}
			else {
				api.statistic("stattable");
				api.autoFreshDeviceStatus(true);
				ModuleMessage.showMessage("开启卡口状态实时刷新！",ModuleMessage.SUCCESS);
			}
		});
	});	
</script>
<div id="stattable"></div>