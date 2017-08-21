<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/funsionchartstx/jquery-1.9.1.js" type="text/javascript"></script>
<script src="${root}/compnents/funsionchartstx/fusioncharts.js" type="text/javascript"></script>
<script src="${root}/compnents/funsionchartstx/fusioncharts.charts.js" type="text/javascript"></script>
<script src="${root}/compnents/funsionchartstx/fusioncharts.maps.js" type="text/javascript"></script>
<script src="${root}/compnents/funsionchartstx/fusioncharts.widgets.js" type="text/javascript"></script>
<script src="${root}/compnents/funsionchartstx/fusioncharts.gantt.js" type="text/javascript"></script>
<script src="${root}/compnents/funsionchartstx/fusioncharts.powercharts.js" type="text/javascript"></script>

<script type="text/javascript">

var cpuinfo = ${status.cpuinfo};
var meminfo = ${status.memoryinfo};

function startlist(){
   window.setInterval("getStatusInfo()",5000);
}


function getStatusInfo(){
	$.getJSON("${root}/alarm/status/getStatusInfo/${status.ip}/?num="+Math.random(),
		function(data) {
			cpuinfo = parseInt(data.cpu, 10);
			meminfo = parseInt(data.mem, 10);
	});
}

  FusionCharts.ready(function(){
    var fusioncharts = new FusionCharts({
    type: 'angulargauge',
    renderAt: 'chart-container',
    width: '100%',
    height: '185',
    dataFormat: 'json',
    dataSource: {
        "chart": {
            "caption": "CPU 使用率",
            "subcaption": "${status.ip}",
            "lowerLimit": "0",
            "upperLimit": "100",
            "editMode": "1",
            "showValue": "1",
            "valueBelowPivot": "1",
            "tickValueDistance": "25",
            "gaugeFillMix": "{dark-30},{light-60},{dark-10}",
            "gaugeFillRatio": "15",
            "theme": "fint",
            "valueFontSize": "14"
        },
        "colorRange": {
            "color": [{
                "minValue": "0",
                "maxValue": "60",
                "code": "#6baa01"
            }, {
                "minValue": "60",
                "maxValue": "90",
                "code": "#f8bd19"
            }, {
                "minValue": "90",
                "maxValue": "100",
                "code": "#e44a00"
            }]
        },
        "dials": {
            "dial": [{
                "id": "crntYr",
                "value": "18",
                "showValue": "1",
                "tooltext": "当前CPU占用率为 : $value",
                "rearExtension": "5"
            }]
        }
    },
    events: {
        "rendered": function(evtObj, argObj) {
            evtObj.sender.intervalVar = setInterval(function() {
                var chartIns = evtObj.sender,
                    prcnt = cpuinfo + parseInt(Math.floor(Math.random() * 5), 10);
                    if(prcnt>100){
                    prcnt = 100;
                    }
                chartIns.feedData("value=" + prcnt);
            }, 1000);
        },
        "realtimeUpdateComplete": function(evtObj, argObj) {
            var updtObj = argObj && argObj.updateObject,
                values = updtObj && updtObj.values,
                updtValStr = values && values[0],
                updtVal = updtValStr && parseFloat(updtValStr).toFixed(0),
                divToUpdate = document.getElementById("score-detail");
            
        },
        "disposed": function(evtObj, argObj) {
            clearInterval(evtObj.sender.intervalVar);
        }
    }
}
);


var fusionchartsmem = new FusionCharts({
    type: 'angulargauge',
    renderAt: 'chart-memory',
    width: '100%',
    height: '185',
    dataFormat: 'json',
    dataSource: {
        "chart": {
            "caption": "内存使用率",
            "subcaption": "${status.ip}",
            "lowerLimit": "0",
            "upperLimit": "100",
            "editMode": "1",
            "showValue": "1",
            "valueBelowPivot": "1",
            "tickValueDistance": "25",
            "gaugeFillMix": "{dark-30},{light-60},{dark-10}",
            "gaugeFillRatio": "15",
            "theme": "fint",
            "valueFontSize": "14"
        },
        "colorRange": {
            "color": [{
                "minValue": "0",
                "maxValue": "60",
                "code": "#6baa01"
            }, {
                "minValue": "60",
                "maxValue": "90",
                "code": "#f8bd19"
            }, {
                "minValue": "90",
                "maxValue": "100",
                "code": "#e44a00"
            }]
        },
        "dials": {
            "dial": [{
                "id": "crntYr",
                "value": "18",
                "showValue": "1",
                "tooltext": "当前内存占用率为 : $value",
                "rearExtension": "5"
            }]
        }
    },
    events: {
        "rendered": function(evtObj, argObj) {
            evtObj.sender.intervalVar = setInterval(function() {
                var chartIns = evtObj.sender,
                    prcnt = meminfo + parseInt(Math.floor(Math.random() * 5), 10);
                    if(prcnt>100){
                    prcnt = 100;
                    }
                chartIns.feedData("value=" + prcnt);
            }, 1000);
        },
        "realtimeUpdateComplete": function(evtObj, argObj) {
            var updtObj = argObj && argObj.updateObject,
                values = updtObj && updtObj.values,
                updtValStr = values && values[0],
                updtVal = updtValStr &&
                parseFloat(updtValStr).toFixed(0),
                divToUpdate = document.getElementById("score-detail");
        },
        "disposed": function(evtObj, argObj) {
            clearInterval(evtObj.sender.intervalVar);
        }
    }
}
);

    fusioncharts.render();
    fusionchartsmem.render();
    startlist();
});
</script>

<div style="height:660px;overflow:hidden;margin-left:180px;">
      
       <div class="pie-chart pull-left" style="width: 40%;">
         <h4 class="title_intro"><span><img src="${root}/images/picone/rzxt.png"></span>${status.ip}详细监控信息</h4>
         <div id="diagram" style="min-height:310px;margin-bottom:-10px;">
		  	<table class="table table-striped table-bordered table-condensed table-style" id="table">
					<tbody id="tbody1">
							<tr>
								<td>
								主机名
								</td>
								<td>
								${status.computername}
								</td>
							</tr>
							<tr>
								<td>
								操作系统
								</td>
								<td>
								${status.osname}
								</td>
							</tr>
							<tr>
								<td>
								系统版本
								</td>
								<td>
								${status.osversion}
								</td>
							</tr>
							<tr>
								<td>
								系统类型
								</td>
								<td>
								${status.ostype}
								</td>
							</tr>
							<tr>
								<td>
								处理器
								</td>
								<td>
								${status.cpunumber}
								</td>
							</tr>
							<tr>
								<td>
								BIOS版本
								</td>
								<td>
								${status.biosversion}
								</td>
							</tr>
							<tr>
								<td>
								系统区域设置
								</td>
								<td>
								${status.sysareaset}
								</td>
							</tr>
							<tr>
								<td>
								输入法区域设置
								</td>
								<td>
								${status.inputareaset}
								</td>
							</tr>
							<tr>
								<td>
								物理内存总量
								</td>
								<td>
								${status.hymemoryall}
								</td>
							</tr>
							<tr>
								<td>
								可用的物理内存
								</td>
								<td>
								${status.hymemoryfree}
								</td>
							</tr>
							<tr>
								<td>
								产品 ID
								</td>
								<td>
								${status.osid}
								</td>
							</tr>
							<tr>
								<td>
								注册的所有人
								</td>
								<td>
								${status.osreguser}
								</td>
							</tr>
					</tbody>
			</table>
         </div>
       </div>
       
        <div class="diagram pull-right" style="width: 59.9%;">
         <h4 class="title_intro"><span><img src="${root}/images/picone/zhuxing.png"></span>实时状态</h4>
        <table class="table table-striped table-bordered table-condensed table-style" id="table">
         <tr>
								<td  style="width: 49%;">
								<div id="chart-container"></div>
								</td>
								<td  style="width: 49%;">
								<div id="chart-memory"></div>
								</td>
		 </tr>
		 <tr>
		  <td colspan="2">
		  <table class="table table-striped table-bordered table-condensed table-style" id="table">
					<thead>
						<tr>
							<th>盘符</th>
							<th>容量</th>
							<th>已用</th>
							<th>使用率</th>
						</tr>
					</thead>
					<tbody id="tbody1">
					    <c:forEach items="${statusdlist}" var="diskinfo">
							<tr>
								<td>${diskinfo.diskName}</td>
								<td>${diskinfo.size}</td>
								<td>${diskinfo.used}</td>
								<td>${diskinfo.usePercent}</td>
							</tr>
						</c:forEach>
					</tbody>	
		  </table>
	
		  </td>
		 </tr>
		 </table>
       </div>
       
       <div class="clear"></div>
       
       <div class="diagram pull-left" style="width:100%;">
         <h4 class="title_intro"><span><img src="${root}/images/picone/zhuxing.png"></span>进程数 ${processNum} 个</h4>
         <div  style="display:block;height:210px; overflow-y:scroll;">
        <table class="table table-striped table-bordered table-condensed table-style" id="table"  >
                    <thead>
						<tr>
							<th>映像名称</th>
							<th>内存使用</th>
							<th>用户名</th>
							<th>会话名</th>
						</tr>
					</thead>
					
					<tbody>
					    <c:forEach items="${processList}" var="process">
							<tr>
								<td>${process.name}</td>
								<td>${process.memory}</td>
								<td>${process.userstr}</td>
								<td>${process.talkname}</td>
							</tr>
						</c:forEach>
					</tbody>
        </table>
        </div>
       </div>
       
    </div>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>