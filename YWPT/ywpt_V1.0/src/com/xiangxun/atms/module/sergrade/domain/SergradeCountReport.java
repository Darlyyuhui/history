package com.xiangxun.atms.module.sergrade.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/***
 * 服务商下运维人员的责任资产数量统计报表数据封装类
 * @author guikaiping
 */
public class SergradeCountReport implements Serializable{
	
	private static final long serialVersionUID = -3463723937557721045L;
	//用户名称
	private String userName;
	//厂商名称
	private String factoryName;

	//总和
	private BigDecimal totals;
	private BigDecimal[] values;
	//总数
	private BigDecimal counts;
	
	//卡口设备总数
	private BigDecimal deviceCounts;
	//监控设备总数
	private BigDecimal videoCounts;
	//服务器设备总数
	private BigDecimal serverCounts;
	//数据库设备总数
	private BigDecimal databaseCounts;
	//FTP设备总数
	private BigDecimal ftpCounts;
	//平台设备总数
	private BigDecimal projectCounts;
	//机柜设备总数
		private BigDecimal cabinetCounts;
	
	public BigDecimal getCabinetCounts() {
			return cabinetCounts;
		}



		public void setCabinetCounts(BigDecimal cabinetCounts) {
			this.cabinetCounts = cabinetCounts;
		}



	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}



	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}



	/**
	 * @return the factoryName
	 */
	public String getFactoryName() {
		return factoryName;
	}



	/**
	 * @param factoryName the factoryName to set
	 */
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}



	/**
	 * @return the totals
	 */
	public BigDecimal getTotals() {
		return totals;
	}



	/**
	 * @param totals the totals to set
	 */
	public void setTotals(BigDecimal totals) {
		this.totals = totals;
	}



	/**
	 * @return the values
	 */
	public BigDecimal[] getValues() {
		return values;
	}



	/**
	 * @param values the values to set
	 */
	public void setValues(BigDecimal[] values) {
		this.values = values;
	}



	/**
	 * @return the counts
	 */
	public BigDecimal getCounts() {
		return counts;
	}



	/**
	 * @param counts the counts to set
	 */
	public void setCounts(BigDecimal counts) {
		this.counts = counts;
	}


	/**
	 * @return the deviceCounts
	 */
	public BigDecimal getDeviceCounts() {
		return deviceCounts;
	}



	/**
	 * @param deviceCounts the deviceCounts to set
	 */
	public void setDeviceCounts(BigDecimal deviceCounts) {
		this.deviceCounts = deviceCounts;
	}



	/**
	 * @return the videoCounts
	 */
	public BigDecimal getVideoCounts() {
		return videoCounts;
	}



	/**
	 * @param videoCounts the videoCounts to set
	 */
	public void setVideoCounts(BigDecimal videoCounts) {
		this.videoCounts = videoCounts;
	}



	/**
	 * @return the serverCounts
	 */
	public BigDecimal getServerCounts() {
		return serverCounts;
	}



	/**
	 * @param serverCounts the serverCounts to set
	 */
	public void setServerCounts(BigDecimal serverCounts) {
		this.serverCounts = serverCounts;
	}

	

	/**
	 * @return the databaseCounts
	 */
	public BigDecimal getDatabaseCounts() {
		return databaseCounts;
	}



	/**
	 * @param databaseCounts the databaseCounts to set
	 */
	public void setDatabaseCounts(BigDecimal databaseCounts) {
		this.databaseCounts = databaseCounts;
	}



	/**
	 * @return the ftpCounts
	 */
	public BigDecimal getFtpCounts() {
		return ftpCounts;
	}



	/**
	 * @param ftpCounts the ftpCounts to set
	 */
	public void setFtpCounts(BigDecimal ftpCounts) {
		this.ftpCounts = ftpCounts;
	}



	/**
	 * @return the projectCounts
	 */
	public BigDecimal getProjectCounts() {
		return projectCounts;
	}



	/**
	 * @param projectCounts the projectCounts to set
	 */
	public void setProjectCounts(BigDecimal projectCounts) {
		this.projectCounts = projectCounts;
	}



	public BigDecimal getResultTotals(){
		BigDecimal result = new BigDecimal(0);
		for (int i = 0; i < values.length; i++) {
			result= result.add(values[i]==null?new BigDecimal(0):values[i]);
		}
		return result;
	}
	
}
