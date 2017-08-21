
// 卡口
var mapCross = (function() {
	return function() {
		var api = {
			showAll : showAll,
			destroy : destroy,
			getCrossDeviceByCode : getCrossDeviceByCode
		};
		
		var amq;// 信息发送对象
		var precode;// 前一个卡口发送的设备code
		var handler;
		
		function _init() {
			handler = dojo.connect(map.infoWindow, "onHide", function() {
				if(amq && precode) {
					amq.disConnectionAmq(precode);
				}
			});
		}
		_init();
		
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
		
		// 查询所有图层上的卡口，并进行渲染
		function showAll() {
			// 全部展示，只是在单击的时候判断报警图层是否开启，在判断，当前点击的卡口是否为报警卡口
			// 或者在卡口报警图层的单击事件上添加一个timer阻止事件继续向上传播
			map.infoWindow.hide();
			var mslayer = itmap.arcgis.mapGraphicManager("cross").getLayer();
			dojo.connect(mslayer, "onClick", function(s){
				// 异步查询，并弹出infotemplate窗口显示信息
				getCrossDeviceByCode(s.graphic);
			});
			mslayer.clear();
			// 请求并查询
			queryCross(" 1=1 ", function(queryResult) {
				if(queryResult.features.length == 0) {
					return;
				}
				for(var i=0; i<queryResult.features.length; i++) {
					var graphic = queryResult.features[i];
					graphic.setSymbol(itmap.arcgis.symbol.crossMarkerSymbol());
					mslayer.add(graphic);
				}
			});
			return api;
		}
		function queryCross(wherestr, showQueryResult) {
			var queryTask = new esri.tasks.QueryTask(baseServiceURL.device.url);
			var query = new esri.tasks.Query();
			query.where = wherestr;
			query.outFields = ["*"];
			query.returnGeometry = true;
			
			queryTask.execute(query, showQueryResult);
		}
		// 根据卡口设备code获取卡口相关信息
		function getCrossDeviceByCode(g) {
			itmap.arcgis.util.showLoadingInfoWindow(g);
			var att = g.attributes;
			map.infoWindow.resize(530,300);
			map.infoWindow.setTitle("卡口实时信息");
			map.infoWindow.setContent("<div id='crossinfodiv'><img src='images/loading.gif'></div>");
			$("#crossinfodiv").load("device/deviceinfo/getDeviceInfoByCode", "code="+att.code, function() {
				map.infoWindow.show(g.geometry);
				// 取消前一个卡口的信息发送监听
				if(precode)amq.disConnectionAmq(precode);
				precode = att.code;
				amq.amqAddListener(att.code, mqcallback);
			});
			return api;
		}
		
		function destroy() {
			itmap.arcgis.mapGraphicManager("cross").getLayer().clear();
			if(amq && precode) {
				amq.disConnectionAmq(precode);
			}
			//释放监听
			dojo.disconnect(handler);
		}
		
		return api;
	}
})();