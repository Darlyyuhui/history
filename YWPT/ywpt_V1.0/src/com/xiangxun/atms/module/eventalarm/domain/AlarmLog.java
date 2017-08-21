package com.xiangxun.atms.module.eventalarm.domain;

import java.util.Date;
//@Document(collection="ALARM_DEVICE_LOG")
public class AlarmLog {
//    @Indexed
    private String id;

//    @Indexed
    private String deviceName;

//    @Indexed
    private String deviceCode;

//    @Indexed
    private String deviceIp;

//    @Indexed
    private String deviceType;

//    @Indexed
    private String isOuter;

//    @Indexed
    private Date alarmTime;

//    @Indexed
    private String eventType;
    
  //附加属性
    private String alarmTimeStr;
    
    private String eventTypeName;
    
    private String alarmColor;
    
    private String eventLevel;
    
    private String alarmType;
    
    private String startTime;
    
    private String endTime;
  //附加属性
    private String count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp == null ? null : deviceIp.trim();
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getIsOuter() {
        return isOuter;
    }

    public void setIsOuter(String isOuter) {
        this.isOuter = isOuter == null ? null : isOuter.trim();
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

	public String getAlarmTimeStr() {
		return alarmTimeStr;
	}

	public void setAlarmTimeStr(String alarmTimeStr) {
		this.alarmTimeStr = alarmTimeStr;
	}

	public String getEventTypeName() {
		return eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	public String getEventLevel() {
		return eventLevel;
	}

	public void setEventLevel(String eventLevel) {
		this.eventLevel = eventLevel;
	}

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAlarmColor() {
		return alarmColor;
	}

	public void setAlarmColor(String alarmColor) {
		this.alarmColor = alarmColor;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	
	
}