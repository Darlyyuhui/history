/*
ActiveMQWarpper.removeListener(preCode);
ActiveMQWarpper.addListener(preCode, function(message){
	var str;
	if (message.text == undefined) {
		str = message.textContent;
	} else {
		str = message.text;
	}
	
	//str:messageImgPath
	
});
*/
var ActiveMQWarpper = (function(){
	var api = {
		destroy: destroy,// 销毁所有的mq监听
		addListener: addListener,
		addPersistentListener: addPersistentListener,
		sendMessage: sendMessage,
		removeListener: removeListener
	};
	
	var baseTopic = "itms_topic";
	var amq = null;
	var timer = null;
	var topics = [];// 存放以建立的连接topic和回调函数{topic:topicName, callback:cakkbackFunction}
	var isConnected = false;
	var isInit = false;
	// 初始化mq
	function _initAMQ() {
		amq = org.activemq.Amq;
		amq.init({
			uri : root+'amq',
			logging : true,
			timeout : 35,
			connectStatusHandler : function(connected) {
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
						// 重新监听断开之前的监听
						for(var i=0,len=topics.length; i<len; i++) {
							if(topics[i])addListener(topics[i].topic, topics[i].callback, true);
						}
						if (typeof console != 'undefined' && console.log)console.log("已重新建立AMQ连接！");
					}
				}
			},
			clientId : (new Date()).getTime().toString()
		});
		isInit = true;
	}
	if(!isInit)_initAMQ();
	
	// 添加一个定时器，5分钟发送一次数据，保持连接状态
	if(timer)clearInterval(timer);
	timer = setInterval(function() {
		for(var i=0,len=topics.length; i<len; i++) {
			if(topics[i])amq.sendMessage("topic://"+baseTopic, topics[i].topic);
		}
	}, 300000);
	
	function addListener(topic, mqresult, isReConnect) {
		if(!isReConnect)topics.push({topic: topic, callback: mqresult});
		
		amq.addListener(topic, "topic://"+topic, mqresult);
		// 创建topic会话
		amq.sendMessage("topic://"+baseTopic, "initialized:"+topic);
		
	}
	function addPersistentListener() {
		
	}
	function sendMessage(msg) {
		amq.sendMessage("topic://"+baseTopic, msg);
	}
	// flag参数，表示是否删除保持连接中的对象
	function removeListener(topic, flag) {
		if(null != amq) {
			amq.removeListener(topic, "topic://"+topic);
			if(typeof flag != "undefined" && !flag)return;
			// 移除监听的时候，删除topic集合中对应的topic信息
			for(var i=0,len=topics.length; i<len; i++) {
				if(topics[i] && topic == topics[i].topic) {
					topics[i] = null;
					break;
				}
			}
		}
	}
	function destroy() {
		for(var i=0,len=topics.length; i<len; i++) {
			if(topics[i])amq.removeListener(topics[i].topic, "topic://"+topics[i].topic);
		}
		topics = [];
	}
	return api;
})();