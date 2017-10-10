package com.xiangxun.atms.module.check.vo;

public class LandAnalysis {

	private String rId;
	private String rCode;
	
	private double ph;
	private double cd;
	private double acd;
	
	private double stdevPh;
	private double stdevCd;
	private double stdevAcd;
	
	private double avgPh;
	private double avgCd;
	private double avgAcd;
	
	public String getrId() {
		return rId;
	}
	public void setrId(String rId) {
		this.rId = rId;
	}
	public String getrCode() {
		return rCode;
	}
	public void setrCode(String rCode) {
		this.rCode = rCode;
	}
	public double getPh() {
		return ph;
	}
	public void setPh(double ph) {
		this.ph = ph;
	}
	public double getCd() {
		return cd;
	}
	public void setCd(double cd) {
		this.cd = cd;
	}
	public double getAcd() {
		return acd;
	}
	public void setAcd(double acd) {
		this.acd = acd;
	}
	public double getStdevPh() {
		return stdevPh;
	}
	public void setStdevPh(double stdevPh) {
		this.stdevPh = stdevPh;
	}
	public double getStdevCd() {
		return stdevCd;
	}
	public void setStdevCd(double stdevCd) {
		this.stdevCd = stdevCd;
	}
	public double getStdevAcd() {
		return stdevAcd;
	}
	public void setStdevAcd(double stdevAcd) {
		this.stdevAcd = stdevAcd;
	}
	public double getAvgPh() {
		return avgPh;
	}
	public void setAvgPh(double avgPh) {
		this.avgPh = avgPh;
	}
	public double getAvgCd() {
		return avgCd;
	}
	public void setAvgCd(double avgCd) {
		this.avgCd = avgCd;
	}
	public double getAvgAcd() {
		return avgAcd;
	}
	public void setAvgAcd(double avgAcd) {
		this.avgAcd = avgAcd;
	}
	
}
