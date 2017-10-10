package com.xiangxun.atms.module.map.vo;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 全市面积统计
 * @author HX
 *
 */
public class AreaCount implements java.io.Serializable {

	private static final long serialVersionUID = 2062439342474419660L;

	/**
	 * 污染面积
	 */
	private BigDecimal wrArea;
	/**
	 * 总面积
	 */
	private BigDecimal totalArea;
	/**
	 * 修复面积
	 */
	private BigDecimal repairArea;
	
	public BigDecimal getWrArea() {
		return wrArea;
	}
	public void setWrArea(BigDecimal wrArea) {
		this.wrArea = wrArea;
	}
	public BigDecimal getTotalArea() {
		return totalArea;
	}
	public void setTotalArea(BigDecimal totalArea) {
		this.totalArea = totalArea;
	}
	public BigDecimal getRepairArea() {
		return repairArea;
	}
	public void setRepairArea(BigDecimal repairArea) {
		this.repairArea = repairArea;
	}
	/**
	 * 污染率
	 * wrRates
	 */
	public double getWrRates() {
		DecimalFormat df = new DecimalFormat("#0.00");
		if (this.wrArea != null && this.totalArea != null && this.totalArea.doubleValue() > 0) {
			return Double.parseDouble(df.format(this.wrArea.doubleValue() / this.totalArea.doubleValue() * 100));
		}
		return 0.00;
	}
}
