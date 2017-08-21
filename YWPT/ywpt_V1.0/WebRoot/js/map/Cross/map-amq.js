
// amq消息推送服务类
var mapAMQ = (function() {
	return function(topic, connHandler) {
		var api = {
			amqAddPureListener : amqAddPureListener,
			amqAddListener : amqAddListener,
			disConnectionAmq : disConnectionAmq,
			sendMessage : sendMessage
		};
		
		var amq = null;
		var timer = null;
		
		// 初始化mq
		function _initAMQ() {
			amq = org.activemq.Amq;
			amq.init({
				uri : 'amq',
				logging : true,
				timeout : 35,
				connectStatusHandler : connHandler,
				clientId : (new Date()).getTime().toString()
			});
		}
		_initAMQ();
		
		function amqAddPureListener(msg, mqresult) {
			amq.addListener(msg, "topic://"+msg, mqresult);
		}
		// 添加监听
		function amqAddListener(deviceCode, mqresult) {
			// 测试
			//deviceCode = "610000458004014605";
			amq.addListener(deviceCode, "topic://"+topic+"_"+deviceCode, mqresult);
			// 创建topic会话
			amq.sendMessage("topic://"+topic, "initialized_"+deviceCode);
			
			// 添加一个定时器，5分钟发送一次数据，保持连接状态
			if(timer)clearInterval(timer);
			timer = setInterval(function() {
				amq.sendMessage("topic://"+topic, deviceCode);
			}, 300000);
			return api;
		}
		
		// 移除监听
		function disConnectionAmq(deviceCode) {
			// 测试
			//deviceCode = "610000458004014605";
			if(null != amq) {
				amq.removeListener(deviceCode, "topic://"+topic+"_"+deviceCode);
				//amq.sendMessage("topic://"+topic, "destroyed_"+deviceCode);
			}
			return api;
		}
		
		// 发送消息
		function sendMessage(msg) {
			amq.sendMessage("topic://"+topic, msg);
			return api;
		}
		
		return api;
	}
})();