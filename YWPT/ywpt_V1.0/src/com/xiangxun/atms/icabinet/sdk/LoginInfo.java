package com.xiangxun.atms.icabinet.sdk;

import java.util.Date;

public class LoginInfo {
	private static final int captureFrequence = 0;// 单位秒
	private String ip;
	private String id;
	private String sn;
	private int port = 8000; // 配置的SDK访问端口(服务端口)，海康默认
	private String userName = "admin";// 海康默认
	private String password = "12345";// 海康默认
	private Date captureImageTime;

	public LoginInfo(String ip, String id, String sn) {
		this.ip = ip;
		this.id= id;
		this.sn = sn;
		this.captureImageTime = new Date();
		captureImageTime.setTime(captureImageTime.getTime() - 24 * 60 * 60 * 1000);// 往前推一天
	}

	public String getIp() {
		return ip;
	}
	
	public String getId(){
		return id;
	}
	
	public String getSn(){
		return sn;
	}
	
	public void setSn(String sn){
		this.sn = sn;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	

	/**
	 * 是否可以抓拍图片（处理抓拍时间间隔）
	 * 
	 * @return
	 */
	public boolean shouldCaptureImage() {
		Date now = new Date();
		if ((now.getTime() - captureImageTime.getTime()) > captureFrequence * 1000) {
			captureImageTime = now;
			return true;
		}
		return false;
	}

}
