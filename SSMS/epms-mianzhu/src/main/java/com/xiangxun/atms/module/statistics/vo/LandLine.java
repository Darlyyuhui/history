package com.xiangxun.atms.module.statistics.vo;

import java.math.BigDecimal;

public class LandLine implements java.io.Serializable {

	private static final long serialVersionUID = 2949064442409780648L;
	private String regionName;
	private BigDecimal ph;
	private BigDecimal cd;
	private BigDecimal acd;
	
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public BigDecimal getPh() {
		return ph;
	}
	public void setPh(BigDecimal ph) {
		this.ph = ph;
	}
	public BigDecimal getCd() {
		return cd;
	}
	public void setCd(BigDecimal cd) {
		this.cd = cd;
	}
	public BigDecimal getAcd() {
		return acd;
	}
	public void setAcd(BigDecimal acd) {
		this.acd = acd;
	}
	
}
