
// 卡口
var crossDevices=[];
var mapCrossObj;
var mapCross = (function() {
	return function() {
		var api = {
			showAll : showAll,
			destroy : destroy,
			getCrossDeviceByCode : getCrossDeviceByCode
		};
		
		var amq;// 信息发送对象
		var precode;// 前一个卡口发送的设备code
		
		var isConnected = false;
		function _initAMQ() {
			amq = mapAMQ("cross_current_img", function(connected){
				if(!connected) {
					if (typeof console != 'undefined' && console.log)console.log("AMQ连接断开了！");
					_initAMQ();
					if(isConnected) {
						isConnected = false;
					}
				}
				else {
					if(!isConnected) {
						isConnected = true;
						if(typeof alarmCodesHandler != 'undefined')amq.amqAddPureListener("cross_current_alarm", alarmCodesHandler);
						if (typeof console != 'undefined' && console.log)console.log("已重新建立AMQ连接！");
						if(precode) {
							amq.amqAddListener(precode, mqcallback);
						}
					}
				}
			});
		}
		_initAMQ();// 初始化amq
		
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
		
		var popup;// 卡口信息弹出框
		var style = new OpenLayers.Style({graphicWidth: 24,graphicHeight: 24, externalGraphic: "images/map/cross-normal.png"});
		var layerListeners = {featureclick: function(feature) {
				getCrossDeviceByCode(feature.feature);
			}
		};
		
		// 查询所有图层上的卡口，并进行渲染
		function showAll() {
			// 全部展示，只是在单击的时候判断报警图层是否开启，在判断，当前点击的卡口是否为报警卡口
			// 或者在卡口报警图层的单击事件上添加一个timer阻止事件继续向上传播
			hideInfoWindow();
			
			var mslayer = map.getLayer("cross");
			if(!mslayer) {
				mslayer = new OpenLayers.Layer.Vector("cross", {styleMap: new OpenLayers.StyleMap(style),eventListeners: layerListeners });  	
				mslayer.name="cross";
				mslayer.id="cross";
				map.addLayer(mslayer);
			}
			
			//给设备图层添加graphic
			$.ajax({
				type : "POST",
				url:"cross/search/getDevices", 
				cache: false,
				data:"deviceName=&orgId=&type=",
				dataType : "json",
				success : function(devicelist) {
					crossDevices = devicelist;
					// 清除图层要素
					mslayer.removeAllFeatures();
					
					for(var m=0; m<devicelist.length; m++){
						var devicept= new OpenLayers.Geometry.Point(devicelist[m].mapx, devicelist[m].mapy);
						var deviceAttr = {"buildtime":itmap.util.DateFromat.dateFormartFull(devicelist[m].buildtime),
								"devicetypecode":devicelist[m].devicetypecode,"ip":devicelist[m].ip,"name":devicelist[m].name,"code":devicelist[m].code};
						var deviceGraphic=  new OpenLayers.Feature.Vector(devicept, deviceAttr);
						mslayer.addFeatures(deviceGraphic);
					}
				}
			});
			
			return api;
		}
		
		// 根据卡口设备code获取卡口相关信息
		function getCrossDeviceByCode(g) {
			var att = g.attributes;
			if(popup){
				map.removePopup(popup);
			}
			popup = new OpenLayers.Popup.FramedCloud("", g.geometry.getBounds().getCenterLonLat(), null,
				"<div id='crossinfodiv' style='width: 530px; height: 300px;line-height:25px;' ><img src='images/loading.gif'></div>",null, true);
			map.addPopup(popup, true);
			popup.hide = function(){
				map.removePopup(popup);
				if(amq && precode) {
					amq.disConnectionAmq(precode);
				}
			}
			$("#crossinfodiv").load("device/deviceinfo/getDeviceInfoByCode", "code="+att.code, function() {
				// 取消前一个卡口的信息发送监听
				if(precode)amq.disConnectionAmq(precode);
				precode = att.code;
				amq.amqAddListener(att.code, mqcallback);
			});
			
			return api;
		}
		
		function destroy() {
			hideInfoWindow();
			var mslayer = map.getLayer("cross");
			if(mslayer) {
				mslayer.removeAllFeatures();
			}
			if(amq && precode) {
				amq.disConnectionAmq(precode);
			}
		}
		
		return api;
	}
})();