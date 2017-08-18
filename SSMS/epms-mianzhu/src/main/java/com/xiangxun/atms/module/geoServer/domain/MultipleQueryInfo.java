package com.xiangxun.atms.module.geoServer.domain;

public class MultipleQueryInfo {
	private String name;
	private  String geometryText;
	private String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGeometryText() {
		return geometryText;
	}
	public void setGeometryText(String geometryText) {
		this.geometryText = geometryText;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
