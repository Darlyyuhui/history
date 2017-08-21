package com.xiangxun.atms.icabinet.sdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageSender {
	private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);
	
	private IMessageDeliver deliver;
	private IMessageHelper helper;

	public MessageSender(IMessageDeliver deliver, IMessageHelper helper) {
		this.deliver = deliver;
		this.helper = helper;		
	}

	/**
	 * 回调信息（由DLL库调用）
	 * 
	 * @param msg
	 */
	public void send(AlarmInfo msg) {
		deliver.messageDeliver(msg);
	}

	/**
	 * 创建一个返回的信息（由DLL库调用）
	 * 
	 * @return
	 */
	public AlarmInfo createAlarmInfo() {
		AlarmInfo info = new AlarmInfo(helper.getNextID());
		return info;
	}

	/**
	 * 在DLL中记日志
	 * 
	 * @param msg
	 */
	public void println(String msg) {
		logger.info(msg);
	}

	/**
	 * 在DLL中记错误
	 * 
	 * @param msg
	 */
	public void error(String msg) {
		logger.error(msg);
	}

	/**
	 * 取IP的登录信息
	 * 
	 * @param ip
	 * @return
	 */
	public LoginInfo getLoginInfoByIP(final String ip) {
		return helper.getLoginInfoByIP(ip);
	}

	/**
	 * 判断是否智能机柜
	 * 
	 * @param sn 收到的序列号
	 * @return
	 */
	public boolean isICabinet(String ip) {		
		return helper.isICabinet(ip);
	}

	/**
	 * 是否可以抓拍图片（主要用于延时：两次抓拍间隔时间）
	 * 
	 * @param ip 判断的IP地址
	 * @return
	 */
	public boolean shouldCaptureImage(String ip) {
		return helper.shouldCaptureImage(ip);
	}
}
