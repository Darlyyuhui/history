package com.xiangxun.atms.common.jms.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;

import com.xiangxun.atms.common.jms.service.impl.ActiveMQWarpper;
import com.xiangxun.atms.framework.base.ApplicationContextHolder;


/**
 * 监听amq的监听器
 */
public class ActiveMQInitListener implements ServletContextListener {
	
	private Thread mqThread = null;
	
    public void contextDestroyed(ServletContextEvent sce) {
    	ActiveMQWarpper amqWarpper = (ActiveMQWarpper)ApplicationContextHolder.getBean("amqWarpper");
    	if(null != amqWarpper) {
    		amqWarpper.destroy();
    	}
    	
    	if(mqThread != null){
    		mqThread.interrupt();
    	}
    	
    }

	public void contextInitialized(ServletContextEvent sce) {
    	String jmsURL = sce.getServletContext().getInitParameter("org.apache.activemq.brokerURL");
    	
    	if(StringUtils.isNotBlank(jmsURL)){
    		System.setProperty("atms.org.apache.activemq.brokerURL",jmsURL);
    	}
    	ActiveMQWarpper.URL = jmsURL;
    	
    	mqThread = new Thread(new Runnable() {
			@Override
			public void run() {
				ActiveMQWarpper amqWarpper = (ActiveMQWarpper)ApplicationContextHolder.getBean("amqWarpper");
				if(null != amqWarpper) {
					amqWarpper.init();
				}
			}
		});
    	mqThread.start();
    }
	
}
