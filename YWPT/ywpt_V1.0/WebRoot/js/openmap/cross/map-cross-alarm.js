
// 卡口报警
var mapCrossAlarm = (function() {
	return function() {
		var api = {
			getAlarmPageByParam : getAlarmPageByParam,
			setAlarmSearchResult : setAlarmSearchResult,
			updateAlarmLayer : updateAlarmLayer,
			getAlarmCodes : getAlarmCodes,
			alarmSearch : alarmSearch,
			setCacheName : setCacheName,
			getCrossAlarmByDevice : getCrossAlarmByDevice
		};
		
		// 根据卡口设备code和布控车辆 分页查找布控报警集合
		function getAlarmPageByParam(code,blackId,pageNumber) {
			$.ajax({
			  type: "POST",
			  url: "cross/alarm/byparam/"+code+"/"+blackId,
			  cache: false,
			  dataType: "json",
			  data:"pageNumber="+pageNumber,
			  success: function(result){
				// 查询结果为一个page对象
				if(!result)return;
				var list = result.list.result;// 布控报警vo对象的list集合
				var black = result.black;// 布控信息
				
				if(!list || list.length < 1 || !black) {
					$("#blackcolor").text("");
					$("#blacktype").text("");
					$("#cartype").text("");
					$("#alarmtype").text("");
					$("#alarmCarnum").text("");
					$("#alarmTime").text("");
					$("#mapcrossimg").attr("src","images/defaults.jpg");
					mapCross.imgList = [];
					mapCross.imgIndex = 0;
				}
				else {
					$("#blackcolor").text(black.carPlateColorCode);
					$("#blacktype").text(list[0].alarmType);
					$("#cartype").text(black.carPlateTypeCode);
					$("#alarmtype").text(list[0].alarmType);
					$("#alarmCarnum").text(list[0].plateNum);
					$("#alarmTime").text(list[0].trrafficTime);
					$("#mapcrossimg").attr("src",list[0].imgPath);
					mapCross.imgList = list;
					mapCross.imgIndex = 0;
					
					setCacheName("blackcolor", "platecolor", black.carPlateColorCode);
					setCacheName("cartype", "platetype", black.carPlateTypeCode);
				}
			  },
			  error: function(e){
			  }
			});
			return api;
		}
		
		// 根据传递的feature弹出信息框
		function getCrossAlarmByDevice(g) {
			if(popup){
				map.removePopup(popup);
			}
			popup = new OpenLayers.Popup.FramedCloud("", g.geometry.getBounds().getCenterLonLat(), null,
				"<div id='crossinfodiv' style='width: 530px; height: 300px;line-height:25px;' ><img src='images/loading.gif'></div>",null, true);
			map.addPopup(popup, true);
			$("#crossinfodiv").load("cross/alarm/olgetalarminfobycode/"+g.attributes.code);
			return api;
		}
		
		function setAlarmSearchResult(result, isSwitch) {
			var _relation = {
				plateNum : "报警车辆",
				trrafficTime : "报警时间"
			};
			var _resArr = [];
			for(var i = 0;i < result.length;i++){
				_resArr.push(new resItemO(result[i].plateNum,function(obj){
					if(popup){
						map.removePopup(popup);
					}
					// 根据code在地图上查询对应设备并显示信息框
					var content = "<div style='width:300px;margin:0;padding:0;border:0px;'>"+
						"<img src='"+obj.imgPath+"' onclick='window.parent.tipsdownImgFd(\"图片特写\", this.src);' style='width: 300px;height: 260px;'></div>";
					
					for(var m=0; m<crossDevices.length; m++) {
						if(obj.deviceCode == crossDevices[m].code) {
							var devicept= new OpenLayers.Geometry.Point(crossDevices[m].mapx, crossDevices[m].mapy);
							popup = new OpenLayers.Popup.FramedCloud("", devicept.getBounds().getCenterLonLat(), null,content,null, true);
							map.addPopup(popup, true);
							break;
						}
					}
					
				},result[i],result[i]));
			}
			$("#mapOther").css("display","none");
			itmap.util.mapResultboxNew().init("mapResultC").clearBox();
			itmap.util.mapResultboxNew().init("mapResultC").addContentAsTable({content:_resArr,switchtab:isSwitch,relation:_relation});
			return api;
		}
		
		function alarmSearch() {
			$.ajax({
			  type: "POST",
			  dataType: "json",
			  url: "cross/alarm/alllist",
			  cache: false,
			  success: function(list){
			  	debugger;
				if(!list)return;
				var resulthtml="<table class='commonResultTable'>";
				resulthtml += "<tr class=\"commonResultTableTitleRow\"><td>报警车辆</td><td>报警卡口信息</td></tr>";
				
				setAlarmSearchResult(list, false);
				showAlarm(list);
			  },
			  error: function(e){
			  	debugger;
			  }
			}); 
		}
		
		function getAlarmCodes() {
			$.ajax({
			  type: "POST",
			  dataType: "json",
			  url: "cross/alarm/allcodes",
			  cache: false,
			  success: function(list){
				showAlarm(list);
			  },
			  error: function(e){
			  }
			}); 
		}
		
		function updateAlarmLayer(list) {
			var alarmlayer = map.getLayer("crossAlarm");
			if(!alarmlayer)return;
			// 清除图层要素
			alarmlayer.removeAllFeatures();
			for(var k=0,klen=list.length; k<klen; k++) {
				for(var m=0; m<crossDevices.length; m++) {
					if(list[k] == crossDevices[m].code) {
						var devicept= new OpenLayers.Geometry.Point(crossDevices[m].mapx, crossDevices[m].mapy);
						var deviceAttr = {"buildtime":itmap.util.DateFromat.dateFormartFull(crossDevices[m].buildtime),
								"devicetypecode":crossDevices[m].devicetypecode,"ip":crossDevices[m].ip,"name":crossDevices[m].name,"code":crossDevices[m].code};
						var deviceGraphic=  new OpenLayers.Feature.Vector(devicept, deviceAttr);
						alarmlayer.addFeatures(deviceGraphic);
						break;
					}
				}
			}
		}
		
		var popup;// 卡口信息弹出框
		var style = new OpenLayers.Style({graphicWidth: 24,graphicHeight: 24, externalGraphic: "images/map/cross-alarm.png"});
		var layerListeners = {featureclick: function(feature) {
				getCrossAlarmByDevice(feature.feature);
			}
		};
		
		// 更新地图上的报警卡口
		function showAlarm(list) {
			hideInfoWindow();
			
			var alarmlayer = map.getLayer("crossAlarm");
			if(!alarmlayer) {
				alarmlayer = new OpenLayers.Layer.Vector("crossAlarm", {styleMap: new OpenLayers.StyleMap(style),eventListeners: layerListeners });  	
				alarmlayer.name="crossAlarm";
				alarmlayer.id="crossAlarm";
				map.addLayer(alarmlayer);
			}
			// 清除图层要素
			alarmlayer.removeAllFeatures();
			for(var k=0,klen=list.length; k<klen; k++) {
				for(var m=0; m<crossDevices.length; m++){
					if(list[k].deviceCode == crossDevices[m].code) {
						var devicept= new OpenLayers.Geometry.Point(crossDevices[m].mapx, crossDevices[m].mapy);
						var deviceAttr = {"buildtime":itmap.util.DateFromat.dateFormartFull(crossDevices[m].buildtime),
								"devicetypecode":crossDevices[m].devicetypecode,"ip":crossDevices[m].ip,"name":crossDevices[m].name,"code":crossDevices[m].code};
						var deviceGraphic=  new OpenLayers.Feature.Vector(devicept, deviceAttr);
						alarmlayer.addFeatures(deviceGraphic);
						break;
					}
				}
			}
		}
		
		function setCacheName(divid, type, code) {
			$.ajax({
			  type: "POST",
			  url: "system/diccache/"+type+"/"+code,
			  dataType: "json",
			  cache: false,
			  success: function(obj){
			  	$("#"+divid).html(obj.name);
			  },
			  error: function(err) {
			  }
			});
		}
		
		// 清除地图信息框
		function hideInfoWindow(popup) {
			if(popup) {
				map.removePopup(popup);
			}
			else {
				var pops = map.popups;
				for(var i=0,len=pops.length; i<len; i++) {
					map.removePopup(pops[i]);
				}
			}
			
		}
		
		return api;
	}
})();