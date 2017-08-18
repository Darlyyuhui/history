package com.xiangxun.atms.module.map.vo;

import java.math.BigDecimal;

/**
 * 土壤采样统计,最小值,最大值,平均值,标准差,变异系数,镉超标率
 * @author HaoXiang
 * 2017年7月24日
 */
public class LandCount implements java.io.Serializable {

	private static final long serialVersionUID = 7938728748316690197L;
	private String name;
	private BigDecimal ph;
	private BigDecimal cd;
	private BigDecimal acd;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
