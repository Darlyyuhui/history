package com.xiangxun.atms.module.statistics.vo;

import java.math.BigDecimal;

public class LandPie implements java.io.Serializable {
	
	private static final long serialVersionUID = 2622800739291382519L;
	/**
	 * 区域ID
	 */
	private String regionId;
	/**
	 * 区域名称
	 */
	private String regionName;
	/**
	 * 评价等级
	 */
	private String cLv;
	/**
	 * 点位数
	 */
	private Integer num;
	/**
	 * 点位占比
	 */
	private BigDecimal perc;
	/**
	 * 影响面积
	 */
	private BigDecimal wrArea;
	
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getcLv() {
		return cLv;
	}
	public void setcLv(String cLv) {
		this.cLv = cLv;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public BigDecimal getPerc() {
		return perc;
	}
	public void setPerc(BigDecimal perc) {
		this.perc = perc;
	}
	public BigDecimal getWrArea() {
		return wrArea;
	}
	public void setWrArea(BigDecimal wrArea) {
		this.wrArea = wrArea;
	}
	
	
}
