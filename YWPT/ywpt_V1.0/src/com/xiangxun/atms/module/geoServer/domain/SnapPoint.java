package com.xiangxun.atms.module.geoServer.domain;

public class SnapPoint {

	private  String geometryText;
	private String name;
	private String lineText;
	
	public String getLineText() {
		return lineText;
	}

	public void setLineText(String lineText) {
		this.lineText = lineText;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setGeometryText(String geometryText) {
		this.geometryText = geometryText;
	}

	public String getGeometryText() {
		return geometryText;
	}
	
}
