package com.xiangxun.atms.common.jms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * 消息发送封装类
 * @author xiongjie
 */
@Service("amqWarpper")
public class ActiveMQWarpper {
	protected Log logger = LogFactory.getLog(ActiveMQWarpper.class);
	
	/**
	 * 用于监听创建produce和销毁produce的topic
	 */
	public static String ITMS_TOPIC = "itms_topic";
	public static String URL = null;
	public static Long FIVE_MIN = 5*60*1000L;
	
	/**
	 * 存放所有producer，在卡口数据回传到
	 */
	private Map<String, MessageProducer> produceMap = new HashMap<String, MessageProducer>();
	private Map<String, Long> produceTimeMap = new HashMap<String, Long>();
	
	private ActiveMQSession session = null;
	private MessageConsumer consumer = null;
	private myMessageListener consumerListener = null;
	private ActiveMQConnection conn = null;
	
	private boolean isInit = false;
	
	private ActiveMQWarpper() {
		if(null != URL && !"".equals(URL)) {
			init();
		}
	}
	
	/**
	 * 发送消息给指定的卡口code
	 * @param deviceCode
	 * @param message
	 */
	public boolean sendMessage(String topicTxt, String message) {
		if(!isInit) {
			return false;
		}
		// 判断topic是否存在
		MessageProducer producer = getProduce(topicTxt);
		if(null == producer) {
			return false;
		}
		try {
			producer.send(session.createTextMessage(message));
			return true;
		} catch (Exception e) {
			logger.debug("MQ发送消息异常！");
			System.err.println("MQ发送消息异常！");
			return false;
		}
	}
	
	private MessageProducer getProduce(String topic) {
		return produceMap.get(topic);
	}
	
	/**
	 * 根据topic名称获取topic是否为活动状态
	 * @param topic
	 * @return false:topic不可用  true:topic状态可用
	 */
	public boolean getProduceStatus(String topic) {
		return (null != produceMap.get(topic));
	}
	
	/**
	 * 创建produce
	 */
	public void createProduce(String topic) {
		if(!isInit) {
			produceMap.put(topic, null);
			return;
		}
		MessageProducer producer = null;
		try {
			Destination dest = session.createTopic(topic);
			producer =  session.createProducer(dest);
			produceMap.put(topic, producer);
			produceTimeMap.put(topic, new Date().getTime());
		} catch (JMSException e) {
			logger.debug("创建MessageProducer失败！");
			System.err.println("创建MessageProducer失败！");
		}
	}
	/**
	 * 当session断开的时候，重新创建produce
	 */
	private void createProduce() {
		Set<String> keyset = produceMap.keySet();
		try {
			for (String key : keyset) {
				Destination dest = session.createTopic(key);
				MessageProducer producer =  session.createProducer(dest);
				produceMap.put(key, producer);
				produceTimeMap.put(key, new Date().getTime());
			}
		} catch (JMSException e) {
			logger.debug("重新创建MessageProducer map失败！");
			System.err.println("重新创建MessageProducer map失败！");
		}
	}
	public void init() {
		try {
			// 配置为自动重连
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("failover:("+URL+")");
			conn = (ActiveMQConnection)factory.createConnection();
			session = (ActiveMQSession)conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
			
			Destination dest = session.createTopic(ITMS_TOPIC);
			consumer =  session.createConsumer(dest);
			consumerListener = new myMessageListener();
			consumer.setMessageListener(consumerListener);
			
			conn.start();
			System.out.println("ActiveMQConnectionFactory创建成功！");
			
			isInit = true;
			createProduce();
			
			// 这里启动一个线程，用于销毁不在连接的produce(默认10分钟没有发送消息给服务器就当做是断开连接)
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (!Thread.currentThread().isInterrupted()) {
						try {
							Long now = new Date().getTime();
							// 找出所有不在使用的produce连接对象
							List<String> delkeys = new ArrayList<String>();
							for (String key : produceMap.keySet()) {
								Long time = produceTimeMap.get(key);
								if(!(null == time || now < (time+FIVE_MIN))) {
									delkeys.add(key);
								}
							}
							// 删除所有不在连接的对象
							for (String str : delkeys) {
								MessageProducer p = produceMap.remove(str);
								produceTimeMap.remove(str);// 移除对应的topic和时间
								if(null != p) {
									p.close();
									p = null;
								}
							}
							
							// 6分钟执行一次
							Thread.sleep(FIVE_MIN+60000);
						} catch (Exception e) {
							logger.debug("销毁MessageProducer出错，不在执行！");
							System.err.println("销毁MessageProducer出错，不在执行！");
						}
					}
				}
			}).start();
		} catch (Exception e) {
			if(e instanceof NullPointerException) {
				logger.debug("创建ActiveMQConnectionFactory出错：atms.org.apache.activemq.brokerURL为空！");
			}
			else if(e instanceof JMSException) {
				logger.debug("创建ActiveMQConnectionFactory出错：不能连接到URL:"+
						System.getProperty("atms.org.apache.activemq.brokerURL"));
			}
			else {
				logger.debug("创建ActiveMQConnectionFactory出错！");
			}
			System.err.println("ActiveMQConnectionFactory创建失败！");
		}
	}
	
	public void destroy() {
		if(!isInit) {
			return;
		}
		for (String key : produceMap.keySet()) {
			MessageProducer producer = produceMap.get(key);
			try {
				if(null != producer)producer.close();
			} catch (Exception e) {
				System.out.println("--销毁MessageProducer的时候出错了！--");
			}
			producer = null;
		}
		try {
			if(null != consumer)consumer.close();
			consumer = null;
		} catch (JMSException e) {
			System.out.println("--销毁MessageConsumer的时候出错了！--");
		}
		try {
			if(null != session)session.close();
			session = null;
		} catch (JMSException e) {
			System.out.println("--销毁ActiveMQSession的时候出错了！--");
		}
		try {
			if(null != conn)conn.close();
			conn = null;
		} catch (JMSException e) {
			System.out.println("--销毁ActiveMQConnection的时候出错了！--");
		}
	}
	
	class myMessageListener implements MessageListener{
		@Override
		public void onMessage(Message message) {
			try {
				String topicTxt = ((TextMessage)message).getText();
				
				String[] msgs = topicTxt.split(":");
				if(null != msgs && msgs.length == 2) {
					if("initialized".equals(msgs[0])) {
						// 创建topic会话
						Destination dest = session.createTopic(msgs[1]);
						MessageProducer producer =  session.createProducer(dest);
						produceMap.put(msgs[1], producer);
						produceTimeMap.put(msgs[1], new Date().getTime());
					}
					else if("destroyed".equals(msgs[0])) {
						produceTimeMap.remove(msgs[1]);
						// 销毁topic会话
						MessageProducer p = produceMap.remove(msgs[1]);
						if(null != p) {
							p.close();
							p = null;
						}
					}
				}
				else {
					// 更新topic会话时间，保持连接
					if(null != produceTimeMap.get(topicTxt)) {
						produceTimeMap.put(topicTxt, new Date().getTime());
					}
				}
			} catch (JMSException e) {
				e.printStackTrace();
				System.err.println("mq接收的消息转换错误！");
			}
		}
	}

}
