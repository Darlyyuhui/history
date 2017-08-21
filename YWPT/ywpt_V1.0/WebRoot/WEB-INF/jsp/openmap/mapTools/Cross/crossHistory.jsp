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
<div id="omTabContent" class='zTabToolContent'>
	<div>
		<table width="100%" border="0" style="margin-top: 10px;">
			<tr>
				<td width="64">
					<label for="alarmCarnum">
						号牌号码
					</label>
				</td>
				<td>
					<input class="input" type="text" id="alarmCarnum"
						name="alarmCarnum" style="width: 130px; margin-top: 0px; margin-bottom:0px;">
				</td>
			</tr>
			<tr style="height:40px;">
				<td width="64">
					<label for="crossStartTime">
						开始时间
					</label>
				</td>
				<td>
					<input class="input" id="crossStartTime" name="crossStartTime"
						readonly="readonly" style="width: 140px; margin: 0; padding:3px 0px;" value="${crossStartTime}"
						onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'crossStartTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				</td>
			</tr>
			<tr>
				<td width="64">
					<label for="crossEndTime">
						结束时间
					</label>
				</td>
				<td>
					<input class="input" id="crossEndTime" name="crossEndTime" 
						readonly="readonly" style="width: 140px; margin: 0; padding:3px 0px;" value="${crossEndTime}"
						onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'crossEndTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				</td>
			</tr>
			<tr>
				<td colspan=2 style="text-align: center; padding:10px 0px;">
					<input id="olcrossAlarmQuery" type="button" class="btn btn-info"
						style="margin: 0; padding: 0 10px; height: 24px; line-height: 12px;"
						value="查询" />
				</td>
			</tr>
		</table>
		<div id="chr_msg"></div>
		<div style="padding: 5px 10px 5px 26px">
			<div id="cross_playandpuase" class="gpsBtn"  alt="播放/暂停"></div>
			<div id="cross_stop" class="gpsBtn" onclick="stop();" alt="停止"></div>
			<div id="cross_speeddown" class="gpsBtn" onclick="setspeed('D');" alt="减速"></div>
			<div id="cross_speedup" class="gpsBtn" onclick="setspeed('U');" alt="加速"></div>
		</div>
		<div style="clear:both;"></div>
		<div id="ch_msg" style="color: red; font-weight: bold;"></div>
	</div>
</div>

<script type="text/javascript">
	if(!mapCrossObj)mapCrossObj = mapCross();
	mapCrossObj.showAll();// 显示所有卡口设备
	
	$("#olcrossAlarmQuery").click(function(){
		$("#ch_msg").html("");
		if(!$("#alarmCarnum").val()) {
			$("#ch_msg").html("号牌号码不能为空！");
			return;
		}
		if(!$("#crossStartTime").val()) {
			$("#ch_msg").html("开始时间不能为空！");
			return;
		}
		if(!$("#crossEndTime").val()) {
			$("#ch_msg").html("结束时间不能为空！");
			return;
		}
		$.ajax({
    		type : "POST",
    		url:"cross/search/getcrosshistory",
    		dataType : "json",
    		cache: false,
    		data:"carNum="+$("#alarmCarnum").val()+"&startTime="+$("#crossStartTime").val()+"&endTime="+$("#crossEndTime").val(),
    		success : function(list) {
    		//添加该车辆经过的卡口图层
        	addDeviceHistoryLayer(list);
        	//添加结果到左侧列表
        	addResoultOnLeftMenu(list);
    		},
    		error : function() {
        	//	debugger;
    		}
    	});
		
	});
	var popup;
	var olplay;
	var reader = new OpenLayers.Format.WKT();
    var writer=new OpenLayers.Format.WKT();	
    var render = itmap.openlayer.renderer();
    var routeLayer;// 卡口历史轨迹图层crossRouteLayer
    var gpsmoveLayer; // 卡口历史轨迹小车移动图层gpsmoveLayer
    
    //查询轨迹回放路径
	function recordRouteSearch(movelist){
	    var features=[];
		for(var i in movelist ){
			var point=new OpenLayers.Geometry.Point(parseFloat(movelist[i][0]),parseFloat(movelist[i][1]));
			var feature=new OpenLayers.Feature.Vector(point);
			features.push(writer.write(feature));			
		}
		var stops=features.join("|");
		$.ajax({
			  type: "POST",
			  url: "openmap/naServer",
			  cache: false,
			  data: {reqUrl:baseServiceURL.geoserverNSService.url,stopPoint:stops,methodname:"getClosedRoute",barriers:""},		
			  success: function(strs){
			  		routeLayer = map.getLayer("crossRouteLayer")
				    if(!routeLayer){
						routeLayer = new OpenLayers.Layer.Vector("crossRouteLayer",{styleMap:new OpenLayers.StyleMap({"default":new OpenLayers.Style({strokeColor: "#ff0000",strokeWidth: 3})})});
						routeLayer.id="crossRouteLayer";
						map.addLayers([routeLayer]);
				      }
					var features = reader.read(strs);
				        routeLayer.removeAllFeatures();
					    routeLayer.addFeatures(features);					
					var geometry=features.geometry.components;				
					if(!map.getExtent().containsBounds(features.geometry.getBounds())) {
						map.zoomToExtent(features.geometry.getBounds());
					}				
					var remotePointList=[];				
					var linePoints = itmap.openlayer.util.orderRouteResult(features.geometry.components);
					for(var k=0,klen=linePoints.length; k<klen; k++) {
						remotePointList.push(new Array(linePoints[k].x,linePoints[k].y));
					}
					var p;
					if(remotePointList.length == 0) {
						p = new OpenLayers.Geometry.Point(parseFloat(pointList[0][0]),parseFloat(pointList[0][1]));
					}
					if(remotePointList.length  == 1) {
						p = new OpenLayers.Geometry.Point(parseFloat(remotePointList[0][0]),parseFloat(remotePointList[0][1]));
					}
					if(p) {
						var symbol=new OpenLayers.Symbolizer.Point({
							externalGraphic:"images/map/car.png",
							graphicWidth:30,
							graphicHeight:30					
						});
						routeLayer.addFeatures([new OpenLayers.Feature.Vector(p,null,symbol)]);
						map.setCenter(new OpenLayers.LonLat(p.x,p.y));
						return;			
					}
					// 添加箭头
					render.addRouteResultArrow(remotePointList, routeLayer);				
					gpshistorymove(remotePointList);
								  
			  },
			  error: function(e) {
				  alert("错误:"+e);
				  console.log(e);
			  }
			}); 		
	}

	

	// 轨迹播放开始
	function gpshistorymove(list) {
		var gpslist = new Array();
		if(list)gpslist = list;	
		if(gpslist.length < 1) {
			alert("没有轨迹！");;
			return;
		}
		
		gpsmoveLayer = map.getLayer("gpsmoveLayer");
	    if(!gpsmoveLayer){
			gpsmoveLayer = new OpenLayers.Layer.Vector("gpsmoveLayer");
			gpsmoveLayer.id = "gpsmoveLayer";
			map.addLayers([gpsmoveLayer]);
		}
		
		var symbol=new OpenLayers.Symbolizer.Point({
			externalGraphic:"images/map/car.png",
			graphicWidth:30,
			graphicHeight:30					
		});
		var point = new OpenLayers.Geometry.Point(parseFloat(gpslist[0][0]),parseFloat(gpslist[0][1]));
		var car = new OpenLayers.Feature.Vector(point,null, symbol);
		gpsmoveLayer.addFeatures([car]);	
		if(gpslist.length > 2) {			
			olplay = new itmap.openlayer.play(gpslist,car,gpsmoveLayer);
		}
	
	}
	//设置播放速度------
	function setspeed(value) {
		olplay.setspeed(value);
	}
	//播放开始-----
	function play() {
		olplay.play();
	}
	//播放终止-------
	function pause() {
		olplay.pause();
	}
	//播放停止-----
	function stop() {
		olplay.stop();
	}
	function routeSearch(list) {
		//轨迹点左标串
		var movelist = [];
		for(var m=0;m<crossDevices.length;m++){
			for(var i=0;i<list.length;i++){
				if(list[i].deviceCode==crossDevices[m].code){
					movelist.push([crossDevices[m].mapx,crossDevices[m].mapy]);
				}
			}
		}
		if(movelist.length < 2) {
			movelist.push([108.93965017200003 , 34.271449938000046]);
			movelist.push([108.90611773800003 , 34.28802301500008]);
			movelist.push([108.97535523800008 , 34.190154703000076]);
			movelist.push([108.99772831100006 , 34.20241292800006]);
		}
		recordRouteSearch(movelist);
	}
//把车辆通过的卡口显示在地图上
 function addDeviceHistoryLayer(list){
          if(crossDevices.length == 0) {
          	$.ajax({
				type : "POST",
				url:"cross/search/getDevices", 
				cache: false,
				data:"deviceName=&orgId=&type=",
				dataType : "json",
				success : function(devicelist) {
					crossDevices = devicelist;
					
					// 执行分析
		          	routeSearch(list);
				}
			});
          }
          else {
			// 执行分析
          	routeSearch(list);
          }
         }
  //左侧工具条显示查询结果
    function addResoultOnLeftMenu(list){
    	var _relation = {
        		deviceNames : "报警卡口",
        		endTime : "通行时间",
        		directionCode:"行驶方向",
        		carPlateNum: "车牌号码"
        	}
        	var _resArr = [];
        	var _codes = [];
            for(var i in list) {
            	_codes.push("'"+list[i].deviceCode+"'");
    			_resArr.push(new resItemO(list[i].deviceNames, function(obj){
    				// 根据code在地图上查询对应设备并显示信息框 ,为了保障卡口点击时卡口信息实时更新，这里没有获取deviceHistoryLayer中的内容，而是每次重新POST请求
    	 	     	$.ajax({
    			    		type : "POST",
    			    		url:"cross/search/getDevices", 
    			    		cache: false,
    			    		data:"deviceName="+obj.deviceNames,
    			    		dataType : "json",
    			    		success : function(device) {
    			    		for(var i=0;i<device.length;i++){
                            if(device[i].code==obj.deviceCode){                                     
        	 			     	var devicept= new  OpenLayers.Geometry.Point(device[i].mapx,device[i].mapy);	
        	 			   	var content = "<div style='width:300px;margin:0;padding:0;border:0px;'>"+
        					              "<img src='"+obj.img1Path+"' onclick='window.parent.tipsdownImgFd(\"图片特写\", this.src);' style='width: 300px;height: 260px;'></div>";
        	 			 				   if(popup){ map.removePopup(popup);}   		    	  
        	 				    	        popup = new OpenLayers.Popup.FramedCloud("", devicept.getBounds().getCenterLonLat(),null, content,null, true);          
        	 				 	            map.addPopup(popup);    	 				 	            
                                    }
     			    		}
    			     	      }
    		 	     	   }); 
    			},list[i],list[i]));
            }
            itmap.util.mapResultboxNew().init("mapResultC").addContent({content:_resArr,switchtab:false,relation:_relation}); 
        }

    $(function(){
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
					play();
				}else if(item=="cross_playandpuase" && !btnStatus[item]){
					pause();
				}else{
					$(this).css("backgroundPosition",hoverStatus[item]);
				}
			});
		});
	})
</script>