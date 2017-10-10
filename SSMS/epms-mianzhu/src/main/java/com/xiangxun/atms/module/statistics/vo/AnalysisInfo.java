package com.xiangxun.atms.module.statistics.vo;

public class AnalysisInfo implements java.io.Serializable {

	private static final long serialVersionUID = 8142283833688937499L;

	private String regionId;
	
	private Double minPh = 0.00;
	private Double maxPh = 0.00;
	private Double avgPh = 0.00;
	/**
	 * 标准差
	 */
	private Double stdevPh = 0.00;
	/**
	 * 变异系数（%）
	 */
	private Double byPh = 0.00;
	
	
	private Double minCd = 0.00;
	private Double maxCd = 0.00;
	private Double avgCd = 0.00;
	/**
	 * 标准差
	 */
	private Double stdevCd = 0.00;
	/**
	 * 变异系数（%）
	 */
	private Double byCd = 0.00;
	/**
	 * 超标率（%）
	 */
	private Double cbCd = 0.00;
	
	
	private Double minACd = 0.00;
	private Double maxACd = 0.00;
	private Double avgACd = 0.00;
	/**
	 * 标准差
	 */
	private Double stdevACd = 0.00;
	/**
	 * 变异系数（%）
	 */
	private Double byACd = 0.00;
	
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public Double getMinPh() {
		return minPh;
	}
	public void setMinPh(Double minPh) {
		this.minPh = minPh;
	}
	public Double getMaxPh() {
		return maxPh;
	}
	public void setMaxPh(Double maxPh) {
		this.maxPh = maxPh;
	}
	public Double getAvgPh() {
		return avgPh;
	}
	public void setAvgPh(Double avgPh) {
		this.avgPh = avgPh;
	}
	public Double getStdevPh() {
		return stdevPh;
	}
	public void setStdevPh(Double stdevPh) {
		this.stdevPh = stdevPh;
	}
	public Double getByPh() {
		return byPh;
	}
	public void setByPh(Double byPh) {
		this.byPh = byPh;
	}
	public Double getMinCd() {
		return minCd;
	}
	public void setMinCd(Double minCd) {
		this.minCd = minCd;
	}
	public Double getMaxCd() {
		return maxCd;
	}
	public void setMaxCd(Double maxCd) {
		this.maxCd = maxCd;
	}
	public Double getAvgCd() {
		return avgCd;
	}
	public void setAvgCd(Double avgCd) {
		this.avgCd = avgCd;
	}
	public Double getStdevCd() {
		return stdevCd;
	}
	public void setStdevCd(Double stdevCd) {
		this.stdevCd = stdevCd;
	}
	public Double getByCd() {
		return byCd;
	}
	public void setByCd(Double byCd) {
		this.byCd = byCd;
	}
	public Double getCbCd() {
		return cbCd;
	}
	public void setCbCd(Double cbCd) {
		this.cbCd = cbCd;
	}
	public Double getMinACd() {
		return minACd;
	}
	public void setMinACd(Double minACd) {
		this.minACd = minACd;
	}
	public Double getMaxACd() {
		return maxACd;
	}
	public void setMaxACd(Double maxACd) {
		this.maxACd = maxACd;
	}
	public Double getAvgACd() {
		return avgACd;
	}
	public void setAvgACd(Double avgACd) {
		this.avgACd = avgACd;
	}
	public Double getStdevACd() {
		return stdevACd;
	}
	public void setStdevACd(Double stdevACd) {
		this.stdevACd = stdevACd;
	}
	public Double getByACd() {
		return byACd;
	}
	public void setByACd(Double byACd) {
		this.byACd = byACd;
	}
	
}
