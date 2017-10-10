package com.xiangxun.atms.module.map.vo;

public class RepairCount implements java.io.Serializable {

	private static final long serialVersionUID = 5751456199264089663L;

	/**
	 * 采样个数
	 */
	private Long regTotal = 0L;
	/**
	 * 镉平均
	 */
	private Double avgCd = 0.00;
	/**
	 * PH平均
	 */
	private Double avgPh = 0.00;
	/**
	 * 有效态镉平均
	 */
	private Double avgAcd = 0.00;
	/**
	 * ph级别
	 */
	private String phLv = "";
	/**
	 * 超标率
	 */
	private Double cbCd = 0.00;
	
	/*
	 * 地块内容
	 */
	private String geoJson;
	
	
	public String getGeoJson() {
		return geoJson;
	}
	public void setGeoJson(String geoJson) {
		this.geoJson = geoJson;
	}
	public Long getRegTotal() {
		return regTotal;
	}
	public void setRegTotal(Long regTotal) {
		this.regTotal = regTotal;
	}
	public Double getAvgCd() {
		return avgCd;
	}
	public void setAvgCd(Double avgCd) {
		this.avgCd = avgCd;
	}
	public Double getAvgPh() {
		return avgPh;
	}
	public void setAvgPh(Double avgPh) {
		this.avgPh = avgPh;
	}
	public Double getAvgAcd() {
		return avgAcd;
	}
	public void setAvgAcd(Double avgAcd) {
		this.avgAcd = avgAcd;
	}
	public String getPhLv() {
		return phLv;
	}
	public void setPhLv(String phLv) {
		this.phLv = phLv;
	}
	public Double getCbCd() {
		return cbCd;
	}
	public void setCbCd(Double cbCd) {
		this.cbCd = cbCd;
	}
}
