package com.xiangxun.atms.framework.monitor.vo;

public class AlarmLog {
	
	
//	ID	N	VARCHAR2(32)	N	sys_guid()		主键
//	DEVICE_NAME	N	VARCHAR2(100)	Y			设备名称
//	DEVICE_CODE	N	VARCHAR2(30)	Y			设备编号
//	DEVICE_IP	N	VARCHAR2(30)	Y			设备IP                               LOG_SOURCE_IP
//	DEVICE_TYPE	N	VARCHAR2(10)	Y			设备类别                              LOG_TYPE
//	IS_OUTER	N	VARCHAR2(2)	Y	0		是否场外（0-场外；1-场内。）         1
//	ALARM_TIME	N	DATE	Y			        告警时间                             LOG_DATETIME
//	EVENT_TYPE	N	VARCHAR2(30)	Y			事件类型
	
	private String DEVICE_NAME;
	private String DEVICE_CODE;
	private String DEVICE_IP;
	private String DEVICE_TYPE;
	private String IS_OUTER;
	private String EVENT_TYPE;
	
	public String getDEVICE_NAME() {
		return DEVICE_NAME;
	}
	public void setDEVICE_NAME(String device_name) {
		DEVICE_NAME = device_name;
	}
	public String getDEVICE_CODE() {
		return DEVICE_CODE;
	}
	public void setDEVICE_CODE(String device_code) {
		DEVICE_CODE = device_code;
	}
	public String getDEVICE_IP() {
		return DEVICE_IP;
	}
	public void setDEVICE_IP(String device_ip) {
		DEVICE_IP = device_ip;
	}
	public String getDEVICE_TYPE() {
		return DEVICE_TYPE;
	}
	public void setDEVICE_TYPE(String device_type) {
		DEVICE_TYPE = device_type;
	}
	public String getIS_OUTER() {
		return IS_OUTER;
	}
	public void setIS_OUTER(String is_outer) {
		IS_OUTER = is_outer;
	}
	public String getEVENT_TYPE() {
		return EVENT_TYPE;
	}
	public void setEVENT_TYPE(String event_type) {
		EVENT_TYPE = event_type;
	}
	
	

}
