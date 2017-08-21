package com.xiangxun.atms.framework.compnents.mqservice;


import javax.jms.Connection;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.xiangxun.atms.framework.resource.MessageResources;
import com.xiangxun.atms.framework.util.StringUtils;

public class MQConnection {
	private static String activemqUrl = System
			.getProperty("atms.org.apache.activemq.brokerURL");
	private static Connection connection = null;

	private MQConnection() {
	}

	public static Connection getInstanceConnection() throws JMSException {
		connection=getInstanceNewConnection();
		if (connection == null) {
			if (StringUtils.isEmpty(activemqUrl)) {
				MessageResources ps = MessageResources.getMessageInstance("activemq.properties", null);
				activemqUrl=ps.getMessage("org.apache.activemq.brokerURL");
			}
			ActiveMQConnectionFactory activemqConnection = new ActiveMQConnectionFactory(
					ActiveMQConnection.DEFAULT_USER,
					ActiveMQConnection.DEFAULT_PASSWORD,
					"failover:("
							+ activemqUrl
							+ "?wireFormat.maxInactivityDuration=0)?initialReconnectDelay=100?maxReconnectAttempts=10");
				connection= activemqConnection.createConnection();
			return connection;
		}else {
			return connection;
		}
	}
	private static Connection getInstanceNewConnection(){
		if(connection==null){
			return connection;
		}
		ActiveMQConnection activeMQConnection=((ActiveMQConnection) connection);
		if(activeMQConnection.isStarted()){
			return connection;
		}else {
			connection=null;
			return connection;
		}
	}
}
