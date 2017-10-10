package com.xiangxun.atms.module.statistics.vo;

public class RegInfo implements java.io.Serializable {

	private static final long serialVersionUID = 6589454415393940655L;

	private String regionId;
	/**
	 * 农田土壤
	 */
	private Long landNum = 0L;
	/**
	 * 地表水
	 */
	private Long dbsNum = 0L;
	/**
	 * 地下水
	 */
	private Long dxsNum = 0L;
	/**
	 * 底泥
	 */
	private Long dnNum = 0L;
	/**
	 * 大气
	 */
	private Long dqNum = 0L;
	/**
	 * 背景土壤
	 */
	private Long bjNum = 0L;
	/**
	 * 农产品
	 */
	private Long ncpNum = 0L;
	/**
	 * 肥料
	 */
	private Long flNum = 0L;
	
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public Long getLandNum() {
		return landNum;
	}
	public void setLandNum(Long landNum) {
		this.landNum = landNum;
	}
	public Long getDbsNum() {
		return dbsNum;
	}
	public void setDbsNum(Long dbsNum) {
		this.dbsNum = dbsNum;
	}
	public Long getDxsNum() {
		return dxsNum;
	}
	public void setDxsNum(Long dxsNum) {
		this.dxsNum = dxsNum;
	}
	public Long getDnNum() {
		return dnNum;
	}
	public void setDnNum(Long dnNum) {
		this.dnNum = dnNum;
	}
	public Long getDqNum() {
		return dqNum;
	}
	public void setDqNum(Long dqNum) {
		this.dqNum = dqNum;
	}
	public Long getBjNum() {
		return bjNum;
	}
	public void setBjNum(Long bjNum) {
		this.bjNum = bjNum;
	}
	public Long getNcpNum() {
		return ncpNum;
	}
	public void setNcpNum(Long ncpNum) {
		this.ncpNum = ncpNum;
	}
	public Long getFlNum() {
		return flNum;
	}
	public void setFlNum(Long flNum) {
		this.flNum = flNum;
	}
	
}
