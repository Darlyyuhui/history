package com.xiangxun.atms.module.property.domain;

public class DeviceType {
    private String deviceId;

    private String devicetypeId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getDevicetypeId() {
        return devicetypeId;
    }

    public void setDevicetypeId(String devicetypeId) {
        this.devicetypeId = devicetypeId == null ? null : devicetypeId.trim();
    }
}