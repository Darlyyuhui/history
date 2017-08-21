package com.xiangxun.atms.icabinet.sdk;

public interface IMessageHelper {
	/**
	 * 根据ip判断是否为智能机柜
	 * 
	 * @param ip IP
	 * @return true.智能机柜
	 */
	boolean isICabinet(String ip);

	/**
	 * 获取下一个ID
	 * 
	 * @return ID
	 */
	String getNextID();

	/**
	 * 是否可以抓拍图片（主要用于延时：两次抓拍间隔时间）
	 * 
	 * @param ip 判断的IP地址
	 */
	boolean shouldCaptureImage(String ip);
	
	/**
	 * 取IP的登录信息
	 * 
	 * @param ip
	 * @return
	 */
	LoginInfo getLoginInfoByIP(String ip);	
}
