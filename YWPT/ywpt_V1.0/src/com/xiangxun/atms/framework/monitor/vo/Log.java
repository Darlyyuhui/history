package com.xiangxun.atms.framework.monitor.vo;

public class Log {
	int type;
	String info;
	String sourceIP;
	String serverIP;
	int level;
	
	public Log(int type, String info, String sourceIP, String serverIP, int level){
		this.type = type;
		this.info = info;
		this.sourceIP = sourceIP;
		this.serverIP = serverIP;
		this.level = level;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getSourceIP() {
		return sourceIP;
	}
	public void setSourceIP(String sourceIP) {
		this.sourceIP = sourceIP;
	}
	public String getServerIP() {
		return serverIP;
	}
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
