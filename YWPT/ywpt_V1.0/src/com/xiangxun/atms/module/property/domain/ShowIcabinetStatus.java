package com.xiangxun.atms.module.property.domain;

public class ShowIcabinetStatus {
	private String id;
	private String assettype;

	private String assetcode;
	private String assetname;
	private String ip;
	private String orgName;

	private String temperature;
	private String temperaturevalue;
	private String humidity;
	private String humidityvalue;
	private String shock;
	private String shockvalue;
	private String ACvoltage;
	private String serviceid;
	private String payoutstatus;
	private String powerStatus;
	private String netStatus;
	
	public String getServiceid() {
		return serviceid;
	}
	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}
	public String getTemperaturevalue() {
		return temperaturevalue;
	}
	public void setTemperaturevalue(String temperaturevalue) {
		this.temperaturevalue = temperaturevalue;
	}
	public String getHumidityvalue() {
		return humidityvalue;
	}
	public void setHumidityvalue(String humidityvalue) {
		this.humidityvalue = humidityvalue;
	}
	public String getShockvalue() {
		return shockvalue;
	}
	public void setShockvalue(String shockvalue) {
		this.shockvalue = shockvalue;
	}
	public String getACvoltagevalue() {
		return ACvoltagevalue;
	}
	public void setACvoltagevalue(String aCvoltagevalue) {
		ACvoltagevalue = aCvoltagevalue;
	}
	public String getACcurrentvalue() {
		return ACcurrentvalue;
	}
	public void setACcurrentvalue(String aCcurrentvalue) {
		ACcurrentvalue = aCcurrentvalue;
	}
	private String ACvoltagevalue;
	private String ACcurrent;
	private String ACcurrentvalue;
	private String ACterminal;
	private String smoke;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getAssetcode() {
		return assetcode;
	}
	public void setAssetcode(String assetcode) {
		this.assetcode = assetcode;
	}
	public String getAssetname() {
		return assetname;
	}
	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAssetstatus() {
		return assetstatus;
	}
	public void setAssetstatus(String assetstatus) {
		this.assetstatus = assetstatus;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getShock() {
		return shock;
	}
	public void setShock(String shock) {
		this.shock = shock;
	}
	public String getACvoltage() {
		return ACvoltage;
	}
	public void setACvoltage(String aCvoltage) {
		ACvoltage = aCvoltage;
	}
	public String getACcurrent() {
		return ACcurrent;
	}
	public void setACcurrent(String aCcurrent) {
		ACcurrent = aCcurrent;
	}
	public String getSmoke() {
		return smoke;
	}
	public void setSmoke(String smoke) {
		this.smoke = smoke;
	}
	public String getACterminal() {
		return ACterminal;
	}
	public void setACterminal(String aCterminal) {
		ACterminal = aCterminal;
	}
	private String assetstatus; 
	public String getAssettype() {
		return assettype;
	}
	public void setAssettype(String assettype) {
		this.assettype = assettype;
	}
	public String getPayoutstatus() {
		return payoutstatus;
	}
	public void setPayoutstatus(String payoutstatus) {
		this.payoutstatus = payoutstatus;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getPowerStatus() {
		return powerStatus;
	}
	public void setPowerStatus(String powerStatus) {
		this.powerStatus = powerStatus;
	}
	public String getNetStatus() {
		return netStatus;
	}
	public void setNetStatus(String netStatus) {
		this.netStatus = netStatus;
	}
     	
	
}
