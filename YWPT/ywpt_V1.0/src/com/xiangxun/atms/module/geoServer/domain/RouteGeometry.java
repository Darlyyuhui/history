package com.xiangxun.atms.module.geoServer.domain;

public class RouteGeometry {
	private String name;
	
	private String startPoint;
	
	private String endPoint;
	
	private String direction;
	
	private String status;
	
	private String statusTime;
	
	private int lever;
	
	private String geomText;
	
	private String foreignId;
	
	private void setName(String name) {
		this.name = name;
	}
	private String getName() {
		return name;
	}
	private void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}
	private String getStartPoint() {
		return startPoint;
	}
	private void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	private String getEndPoint() {
		return endPoint;
	}
	private void setDirection(String direction) {
		this.direction = direction;
	}
	private String getDirection() {
		return direction;
	}
	private void setStatus(String status) {
		this.status = status;
	}
	private String getStatus() {
		return status;
	}
	private void setStatusTime(String statusTime) {
		this.statusTime = statusTime;
	}
	private String getStatusTime() {
		return statusTime;
	}
	private void setLever(int lever) {
		this.lever = lever;
	}
	private int getLever() {
		return lever;
	}
	private void setGeomText(String geomText) {
		this.geomText = geomText;
	}
	private String getGeomText() {
		return geomText;
	}
	private void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}
	private String getForeignId() {
		return foreignId;
	}
	
	
}
