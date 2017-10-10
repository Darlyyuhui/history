package com.xiangxun.atms.module.map.vo;

import java.math.BigDecimal;

public class RepairLandBlock implements java.io.Serializable {

	private static final long serialVersionUID = 4483459578542848300L;

	private String id;
	private String code;
	private String name;
	private String typeCode;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private BigDecimal area;
    private String soilType;
    private String polluteType;
	private String geoJson;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public BigDecimal getArea() {
		return area;
	}
	public void setArea(BigDecimal area) {
		this.area = area;
	}
	public String getSoilType() {
		return soilType;
	}
	public void setSoilType(String soilType) {
		this.soilType = soilType;
	}
	public String getPolluteType() {
		return polluteType;
	}
	public void setPolluteType(String polluteType) {
		this.polluteType = polluteType;
	}
	public String getGeoJson() {
		return geoJson;
	}
	public void setGeoJson(String geoJson) {
		this.geoJson = geoJson;
	}
}
