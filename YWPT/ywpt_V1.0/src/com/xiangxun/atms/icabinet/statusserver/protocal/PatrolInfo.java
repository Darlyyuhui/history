package com.xiangxun.atms.icabinet.statusserver.protocal;

import java.util.Date;

/**
 * 巡更信息对象
 * @author szf
 *
 */
public class PatrolInfo {
    private String ip;
    private Date date;
    private String deviceType;
    
    public PatrolInfo(String deviceType, String ip, Date date){
    	this.deviceType = deviceType;
    	this.ip = ip;
    	this.date = date;
    }

    public String getDeviceType(){
    	return deviceType;
    }
    
	public String getIp() {
		return ip;
	}

	public Date getDate() {
		return date;
	}
    
}
