package com.xiangxun.atms.module.question.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/***
 * 故障统计报表数据封装类
 * @author guikaiping
 */
public class BreakdownTypeReport implements Serializable{
	
	private static final long serialVersionUID = -3463723937557721045L;
	//故障类型名称
	private String breakdownTypeName;
	//厂商名称
	private String factoryName;
	
	//设备名称
	private String deviceName;
	//总和
	private BigDecimal totals;
	private BigDecimal[] values;
	private String[] typeNames;
	//总数
	private BigDecimal counts;
	
	public String getBreakdownTypeName() {
		return breakdownTypeName;
	}

	public void setBreakdownTypeName(String breakdownTypeName) {
		this.breakdownTypeName = breakdownTypeName;
	}

	public BigDecimal getTotals() {
		return totals;
	}

	public void setTotals(BigDecimal totals) {
		this.totals = totals;
	}

	public BigDecimal[] getValues() {
		return values;
	}

	public void setValues(BigDecimal[] values) {
		this.values = values;
	}

	public String[] getTypeNames() {
		return typeNames;
	}

	public void setTypeNames(String[] typeNames) {
		this.typeNames = typeNames;
	}

	public BigDecimal getCounts() {
		return counts;
	}

	public void setCounts(BigDecimal counts) {
		this.counts = counts;
	}
	
	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public BigDecimal getResultTotals(){
		BigDecimal result = new BigDecimal(0);
		for (int i = 0; i < values.length; i++) {
			result= result.add(values[i]==null?new BigDecimal(0):values[i]);
		}
		return result;
	}
	
}
