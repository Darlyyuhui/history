package com.xiangxun.atms.module.geoServer.domain;

import java.io.Serializable;

public class LayerBean implements Serializable {

	private static final long serialVersionUID = 7534897679911195272L;
	
	//地理库中的标识fid或objectId
	private long id;
	//对应业务库中的标识编号
	private String code;
	//对应业务库中的名称
	private String name;
	//对应业务库中的类型或级别
	private String type;
	//对应的几何体对象
	private String geometry;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public String getGeometry() {
		return geometry;
	}
	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}
	public void setGeometryByXY(double mapx, double mapy) {
		this.geometry = "{\"type\":\"point\",\"points\":\""+mapx+","+mapy+"\"}";
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
