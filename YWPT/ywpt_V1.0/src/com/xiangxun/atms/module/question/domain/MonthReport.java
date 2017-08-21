package com.xiangxun.atms.module.question.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/***
 * 周报表数据封装类
 * @author zhouhaij
 */
public class MonthReport implements Serializable{
	
	private static final long serialVersionUID = -3463723937557721045L;

	/***
	 * 故障类型
	 */
	private String type;
	
	/***
	 * 故障类型 名称
	 */
	private String typeName;
	
	//服务商名称
	private String factoryid;
	private String factoryName;
	
	/***
	 * 一月的总和
	 */
	private BigDecimal totals;
	private BigDecimal ctotals;
	private BigDecimal ototals;
	
	private BigDecimal resultTotals1;
	
	//总数
	private int counts;
	
	//已解决数
	private int solveCounts;
	//未解决数
	private int nosolveCounts;
	
	/***以下为各天小时的数据***/
	private BigDecimal[] days;
	private BigDecimal[] days1;
	private BigDecimal[] days2;
	//统计二维数组。
	private String[][] dayCounts;
	
	private String viewId;//前台类型标识符ID
	private String dataXml;//封装前台要显示的数据集合
	

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	 * @return the ctotals
	 */
	public BigDecimal getCtotals() {
		return ctotals;
	}

	/**
	 * @param ctotals the ctotals to set
	 */
	public void setCtotals(BigDecimal ctotals) {
		this.ctotals = ctotals;
	}

	/**
	 * @return the ototals
	 */
	public BigDecimal getOtotals() {
		return ototals;
	}

	/**
	 * @param ototals the ototals to set
	 */
	public void setOtotals(BigDecimal ototals) {
		this.ototals = ototals;
	}

	/**
	 * @return the resultTotals1
	 */
	public BigDecimal getResultTotals1() {
		return resultTotals1;
	}

	/**
	 * @param resultTotals1 the resultTotals1 to set
	 */
	public void setResultTotals1(BigDecimal resultTotals1) {
		this.resultTotals1 = resultTotals1;
	}

	/**
	 * @return the counts
	 */
	public int getCounts() {
		return counts;
	}

	/**
	 * @param counts the counts to set
	 */
	public void setCounts(int counts) {
		this.counts = counts;
	}

	/**
	 * @return the solveCounts
	 */
	public int getSolveCounts() {
		return solveCounts;
	}

	/**
	 * @param solveCounts the solveCounts to set
	 */
	public void setSolveCounts(int solveCounts) {
		this.solveCounts = solveCounts;
	}

	/**
	 * @return the nosolveCounts
	 */
	public int getNosolveCounts() {
		return nosolveCounts;
	}

	/**
	 * @param nosolveCounts the nosolveCounts to set
	 */
	public void setNosolveCounts(int nosolveCounts) {
		this.nosolveCounts = nosolveCounts;
	}

	/**
	 * @return the days
	 */
	public BigDecimal[] getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(BigDecimal[] days) {
		this.days = days;
	}

	/**
	 * @return the days1
	 */
	public BigDecimal[] getDays1() {
		return days1;
	}

	/**
	 * @param days1 the days1 to set
	 */
	public void setDays1(BigDecimal[] days1) {
		this.days1 = days1;
	}

	/**
	 * @return the days3
	 */
	public BigDecimal[] getDays2() {
		return days2;
	}

	/**
	 * @param days3 the days3 to set
	 */
	public void setDays2(BigDecimal[] days2) {
		this.days2 = days2;
	}

	/**
	 * @return the dayCounts
	 */
	public String[][] getDayCounts() {
		return dayCounts;
	}

	/**
	 * @param dayCounts the dayCounts to set
	 */
	public void setDayCounts(String[][] dayCounts) {
		this.dayCounts = dayCounts;
	}

	/**
	 * @return the viewId
	 */
	public String getViewId() {
		return viewId;
	}

	/**
	 * @param viewId the viewId to set
	 */
	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	/**
	 * @return the dataXml
	 */
	public String getDataXml() {
		return dataXml;
	}

	/**
	 * @param dataXml the dataXml to set
	 */
	public void setDataXml(String dataXml) {
		this.dataXml = dataXml;
	}
	
	/**
	 * @return the factoryid
	 */
	public String getFactoryid() {
		return factoryid;
	}

	/**
	 * @param factoryid the factoryid to set
	 */
	public void setFactoryid(String factoryid) {
		this.factoryid = factoryid;
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

	public BigDecimal getResultTotals(){
		BigDecimal result = new BigDecimal(0);
		if (null != days) {
			for (int i = 0; i < days.length; i++) {
				result= result.add(days[i]==null?new BigDecimal(0):days[i]);
			}
		} else {
			result = null;
		}
		
		return result;
	}
	
	public BigDecimal getSResultTotals(){
		BigDecimal result = new BigDecimal(0);
		if (null != days1) {
			for (int i = 0; i < days1.length; i++) {
				result= result.add(days1[i]==null?new BigDecimal(0):days1[i]);
			}
		} else {
			result = null;
		}
		
		return result;
	}
	
	public BigDecimal getNSResultTotals(){
		BigDecimal result = new BigDecimal(0);
		if (null != days2) {
			for (int i = 0; i < days2.length; i++) {
				result= result.add(days2[i]==null?new BigDecimal(0):days2[i]);
			}
		} else {
			result = null;
		}
		
		return result;
	}
	
}
