package com.xiangxun.atms.module.question.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/***
 * 周报表数据封装类
 * @author zhouhaij
 */
public class WeekReport implements Serializable{
	
	private static final long serialVersionUID = -3463723937557721045L;
	
	/***
	 * 故障类型
	 */
	private String type;
	
	/***
	 * 故障类型 名称
	 */
	private String typeName;
	/***
	 * 一周的总和
	 */
	private BigDecimal totals;
	//故障总数
	private int counts;
	/***以下为各天小时的数据***/
	private BigDecimal d01;
	private BigDecimal d02;
	private BigDecimal d03;
	private BigDecimal d04;
	private BigDecimal d05;
	private BigDecimal d06;
	private BigDecimal d07;
	/***各天数据结束***/
	
	/***
	 * 一周的星期几
	 */
	private String[] days;
	
	private String viewId;//前台类型标识符ID
	
	private String dataXml;//封装前台要显示的数据集合
	
	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getCounts() {
		return counts;
	}


	public void setCounts(int counts) {
		this.counts = counts;
	}


	public BigDecimal getD01() {
		return d01;
	}


	public void setD01(BigDecimal d01) {
		this.d01 = d01;
	}


	public BigDecimal getD02() {
		return d02;
	}


	public void setD02(BigDecimal d02) {
		this.d02 = d02;
	}


	public BigDecimal getD03() {
		return d03;
	}


	public void setD03(BigDecimal d03) {
		this.d03 = d03;
	}


	public BigDecimal getD04() {
		return d04;
	}


	public void setD04(BigDecimal d04) {
		this.d04 = d04;
	}


	public BigDecimal getD05() {
		return d05;
	}


	public void setD05(BigDecimal d05) {
		this.d05 = d05;
	}


	public BigDecimal getD06() {
		return d06;
	}


	public void setD06(BigDecimal d06) {
		this.d06 = d06;
	}


	public BigDecimal getD07() {
		return d07;
	}


	public void setD07(BigDecimal d07) {
		this.d07 = d07;
	}


	public String[] getDays() {
		return days;
	}


	public void setDays(String[] days) {
		this.days = days;
	}


	public void setTotals(BigDecimal totals) {
		this.totals = totals;
	}
	

	public String getViewId() {
		return viewId;
	}


	public void setViewId(String viewId) {
		this.viewId = viewId;
	}


	public String getDataXml() {
		return dataXml;
	}


	public void setDataXml(String dataXml) {
		this.dataXml = dataXml;
	}


	/**
	 * @return the totals
	 */
	public BigDecimal getTotals() {
		
		BigDecimal[] datas = new BigDecimal[]{d01,d02,d03,d04,d05,d06,d07};
		BigDecimal result = new BigDecimal(0);
		for (int i = 0; i < datas.length; i++) {
			result= result.add(datas[i]);
		}
		this.totals = result;
		return totals;
	}
	
}
