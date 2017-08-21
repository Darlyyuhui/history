package com.xiangxun.atms.module.geoServer.domain;

public class DevicePoint {

	private Integer gId=0;
	private Integer roadId=0;
	private String roadGroupId="";
	//当前设备所在道路的名称
	private String roadName="";
	//几何体字符串
	private String geometryText="";
	//设备类型
	private String deviceType="";
	//设备code值
	private String deviceCode="";
	
	public Integer getGId() {
		return gId;
	}
	public void setGId(Integer id) {
		gId = id;
	}
	public Integer getRoadId() {
		return roadId;
	}
	public void setRoadId(Integer roadId) {
		this.roadId = roadId;
	}
	public String getRoadGroupId() {
		return roadGroupId;
	}
	public void setRoadGroupId(String roadGroupId) {
		this.roadGroupId = roadGroupId;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getGeometryText() {
		return geometryText;
	}
	public void setGeometryText(String geometryText) {
		this.geometryText = geometryText;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	
}
