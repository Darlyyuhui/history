package com.xiangxun.atms.module.geoServer.domain;

import java.util.Date;

public class OneWayAcessInfo {
	private String name;
	private String type;
	private String geometryId;
	private String status;
	private Date startTime;
	private Date endtTme;
	private  String geometryText;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGeometryId() {
		return geometryId;
	}
	public void setGeometryId(String geometryId) {
		this.geometryId = geometryId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndtTme() {
		return endtTme;
	}
	public void setEndtTme(Date endtTme) {
		this.endtTme = endtTme;
	}
	public void setGeometryText(String geometryText) {
		this.geometryText = geometryText;
	}
	public String getGeometryText() {
		return geometryText;
	}
	
}
