package com.xiangxun.atms.module.map.vo;

import java.math.BigDecimal;

public class RepairProject implements java.io.Serializable {

	private static final long serialVersionUID = 8630193301241399962L;

	private String id;
    private String code;
    private String name;
    private BigDecimal area;
    private String regionId;
    
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
	public BigDecimal getArea() {
		return area;
	}
	public void setArea(BigDecimal area) {
		this.area = area;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	
}
