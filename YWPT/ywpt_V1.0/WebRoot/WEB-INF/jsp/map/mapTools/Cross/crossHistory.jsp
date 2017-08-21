<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
	.gpsBtn{
		width:30px;
		height:30px;
		overflow:hidden;
		cursor:pointer;
		float:left;
		margin-left:3px;
	}
</style>
<div style="padding-bottom:5px;">
	<table class="map_table mar_5">
		<tr>
			<td>号牌号码：</td>
			<td><input type="text" id="crossHisCarnum" name="crossHisCarnum" style="width:150px;height:28px;padding:4px;overflow:hidden;"></td>
		</tr>
		<tr>
			<td>开始时间：</td>
			<td><input id="crossStartTime" name="crossStartTime" type="text"
					readonly="readonly" style="width:150px;height:28px;padding:4px;" value="${crossStartTime}"
					onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'crossEndTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</td>
		</tr>
		<tr>
			<td>结束时间：</td>
			<td><input id="crossEndTime" name="crossEndTime" type="text"
					readonly="readonly" style="width:150px;height:28px;padding:4px;" value="${crossEndTime}"
					onfocus="WdatePicker({minDate:'#F{$dp.$D(\'crossStartTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</td>
		</tr>
	</table>
	<div class="btn_line" style="padding:5px;">
		<table style="width: 100%;">
	  	  <tr>
	  	    <td>
	  	      <ul>
	  	        <li><input id="crossRouteCheck" type="checkbox" style="margin-top: -2px;margin-top: -1pxurl(0);_margin-top: -1px;margin-right: 3px;">位置纠偏</li>
	  	      </ul>
	  	    </td>
	  	    <td>
	  	      <input id="crossAlarmQuery" type="button" value="查询" class="btn btn-info">
	  	      <input id="crossAlarmHistoryClear" type="button" value="清除" class="btn btn-info mar_l10">
	  	    </td>
	  	  </tr>
	  	</table>
	</div>
	<div style="padding:5px 0px 0 50px">
		<div id="cross_playandpuase" class="gpsBtn" title="播放/暂停"></div>
		<div id="cross_stop" class="gpsBtn" title="停止"></div>
		<div id="cross_speeddown" class="gpsBtn" title="减速"></div>
		<div id="cross_speedup" class="gpsBtn" title="加速"></div>
		<div class="clear"></div>
	</div>
</div>

<script type="text/javascript">
	MapFactory.Require([
		"ItmsMap/Cross/Cross*",
		"ItmsMap/Cross/CrossHistory*",
		"ItmsMap/Util/ModuleMessage*"
	],function(crossFun, cross, ModuleMessage){
		var api = cross();
		crossFun().showAll();// 显示所有的卡口设备
		var isRouteFlag = false;
		$("#crossAlarmQuery").click(function(){
			resetBtnAndPlayer();
			var carNum = $("#crossHisCarnum").val().replace(/^\s*/, "").replace(/\s*$/, "");
			if(!carNum) {
				ModuleMessage.showMessage("<span style='color: red;'>号牌号码不能为空！</span>",ModuleMessage.LOG);
				return;
			}
			if(carNum.length < 7 || carNum.length > 8) {
				ModuleMessage.showMessage("<span style='color: red;'>请输入完整的号牌号码！</span>",ModuleMessage.LOG);
				return;
			}
			if(!$("#crossStartTime").val()) {
				ModuleMessage.showMessage("<span style='color: red;'>开始时间不能为空！</span>",ModuleMessage.LOG);
				return;
			}
			if(!$("#crossEndTime").val()) {
				ModuleMessage.showMessage("<span style='color: red;'>结束时间不能为空！</span>",ModuleMessage.LOG);
				return;
			}
			api.queryHistoryData(carNum, $("#crossStartTime").val(), $("#crossEndTime").val(), isRouteFlag);
		});
		
		$("#crossRouteCheck").click(function(){
			if($(this).attr("checked")) {
				// 选中
				isRouteFlag = true;
			}
			else {
				isRouteFlag = false;
			}
		});
		$("#cross_stop").click(function(){
			api.stop();
			btnStatus["cross_playandpuase"] = false;
			$("#cross_playandpuase").css("backgroundPosition",normalStatus["cross_playandpuase"]);
		});
		$("#cross_speeddown").click(function(){
			api.setspeed('D');
		});
		$("#cross_speedup").click(function(){
			api.setspeed('U');
		});
		$("#crossAlarmHistoryClear").click(function(){
			$("#crossHisCarnum").val("");
			$("#crossStartTime").val("");
			$("#crossEndTime").val("");
			resetBtnAndPlayer();
		});
		function resetBtnAndPlayer() {
			api.destroy();
			btnStatus["cross_playandpuase"] = false;
			$("#cross_playandpuase").css("backgroundPosition", normalStatus["cross_playandpuase"]);
		}
		var btnid="cross_playandpuase,cross_stop,cross_speeddown,cross_speedup";
		var normalStatus = {
			cross_playandpuase : "0 0",
			cross_stop : "-60px 0",
			cross_speeddown : "-90px 0",
			cross_speedup : "-120px 0"
		};
		var hoverStatus = {
			cross_playandpuase : "0px -30px",
			cross_stop : "-60px -30px",
			cross_speeddown : "-90px -30px",
			cross_speedup : "-120px -30px"
		};
		var clickStatus = {
			cross_playandpuase : "-30px -30px",
			cross_stop : "-60px 0",
			cross_speeddown : "-90px 0",
			cross_speedup : "-120px 0"
		};
		var btnStatus = {
			cross_playandpuase : false,
			cross_stop : false,
			cross_speeddown : false,
			cross_speedup : false
		};
		
		$(btnid.split(",")).each(function(index,item){
			$("#"+item).css({
				"backgroundImage" : "url(images/map/play/gpshistory-btn.png)",
				"backgroundPosition" : normalStatus[item]
			}).mouseenter(function(){
				if(item=="cross_playandpuase" && btnStatus[item]){
					
				}else{
					$(this).css("backgroundPosition",hoverStatus[item]);
				}
			}).mouseleave(function(){
				if(item=="cross_playandpuase" && btnStatus[item]){
					
				}else{
					$(this).css("backgroundPosition",normalStatus[item]);
				}
			}).mousedown(function(){
				if(btnStatus[item]){
					$(this).css("backgroundPosition",normalStatus[item]);
				}else{
					$(this).css("backgroundPosition",clickStatus[item]);
				}
				btnStatus[item] = btnStatus[item] ? false : true;
			}).mouseup(function(){
				if(item=="cross_playandpuase" && btnStatus[item]){
					api.play();
				}else if(item=="cross_playandpuase" && !btnStatus[item]){
					api.pause();
				}else{
					$(this).css("backgroundPosition",hoverStatus[item]);
				}
			});
		});
	
	});
</script>