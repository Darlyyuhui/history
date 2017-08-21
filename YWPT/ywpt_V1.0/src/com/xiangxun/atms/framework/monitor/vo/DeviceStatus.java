package com.xiangxun.atms.framework.monitor.vo;

public class DeviceStatus {
	
	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPowerStatus() {
		return powerStatus;
	}

	public void setPowerStatus(Integer powerStatus) {
		this.powerStatus = powerStatus;
	}

	public Integer getNetStatus() {
		return netStatus;
	}

	public void setNetStatus(Integer netStatus) {
		this.netStatus = netStatus;
	}

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}	
	
	public String getDeviceType() {
		return deviceType;
	}
	
	public int getCabinetStatus() {
		return cabinetStatus;
	}

	public String assetId;
	public String deviceCode;
	public String assetName;
	public String ip;
	public Integer powerStatus = 0;//3异常，2警告，1正常，0未知
	public Integer netStatus = 0;//3异常，2警告，1正常，0未知
	public Integer dataStatus = 0;//3异常，2警告，1正常，0未知
	public String deviceType;
	public int cabinetStatus;
	
	public DeviceStatus(String assetId,String assetName, String ip,String deviceCode,String deviceType,int powerStatus,int netStatus,int dataStatus,int cabinetStatus){
		this.assetId = assetId;
		this.assetName = assetName;
		this.ip = ip;
		this.deviceCode = deviceCode;
		this.deviceType = deviceType;
		this.dataStatus = dataStatus;
		this.netStatus = netStatus;
		this.powerStatus = powerStatus;
		this.cabinetStatus = cabinetStatus;
	}
}
