package com.xiangxun.atms.module.geoServer.domain;

public class CrossInfo {
	private String code;
	private  String geometryText;
	private String type;

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
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
}
