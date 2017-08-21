package com.xiangxun.atms.module.question.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/***
 * 按天统计的报表
 * @author zhouhaij
 */
public class DayReport implements Serializable{
	
	private static final long serialVersionUID = -8410433203223618940L;
	
	/***
	 * 故障类型
	 */
	private String type;
	
	/***
	 * 故障类型 名称
	 */
	private String typeName;
	
	private Long[] days;
	
	//故障总数
	private int counts;

	/***以下为各个小时的数据***/
	private BigDecimal h01;
	private BigDecimal h02;
	private BigDecimal h03;
	private BigDecimal h04;
	private BigDecimal h05;
	private BigDecimal h06;
	private BigDecimal h07;
	private BigDecimal h08;
	private BigDecimal h09;
	private BigDecimal h10;
	private BigDecimal h11;
	private BigDecimal h12;
	private BigDecimal h13;
	private BigDecimal h14;
	private BigDecimal h15;
	private BigDecimal h16;
	private BigDecimal h17;
	private BigDecimal h18;
	private BigDecimal h19;
	private BigDecimal h20;
	private BigDecimal h21;
	private BigDecimal h22;
	private BigDecimal h23;
	private BigDecimal h24;
	
	/***
	 * 24小时的总和
	 */
	private BigDecimal totals;
	
	private BigDecimal resultTotals;
	
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

	public Long[] getDays() {
		return days;
	}

	public void setDays(Long[] days) {
		this.days = days;
	}

	public int getCounts() {
		return counts;
	}



	public void setCounts(int counts) {
		this.counts = counts;
	}



	public BigDecimal getH01() {
		return h01;
	}



	public void setH01(BigDecimal h01) {
		this.h01 = h01;
	}



	public BigDecimal getH02() {
		return h02;
	}



	public void setH02(BigDecimal h02) {
		this.h02 = h02;
	}



	public BigDecimal getH03() {
		return h03;
	}



	public void setH03(BigDecimal h03) {
		this.h03 = h03;
	}



	public BigDecimal getH04() {
		return h04;
	}



	public void setH04(BigDecimal h04) {
		this.h04 = h04;
	}



	public BigDecimal getH05() {
		return h05;
	}



	public void setH05(BigDecimal h05) {
		this.h05 = h05;
	}



	public BigDecimal getH06() {
		return h06;
	}



	public void setH06(BigDecimal h06) {
		this.h06 = h06;
	}



	public BigDecimal getH07() {
		return h07;
	}



	public void setH07(BigDecimal h07) {
		this.h07 = h07;
	}



	public BigDecimal getH08() {
		return h08;
	}



	public void setH08(BigDecimal h08) {
		this.h08 = h08;
	}



	public BigDecimal getH09() {
		return h09;
	}



	public void setH09(BigDecimal h09) {
		this.h09 = h09;
	}



	public BigDecimal getH10() {
		return h10;
	}



	public void setH10(BigDecimal h10) {
		this.h10 = h10;
	}



	public BigDecimal getH11() {
		return h11;
	}



	public void setH11(BigDecimal h11) {
		this.h11 = h11;
	}



	public BigDecimal getH12() {
		return h12;
	}



	public void setH12(BigDecimal h12) {
		this.h12 = h12;
	}



	public BigDecimal getH13() {
		return h13;
	}



	public void setH13(BigDecimal h13) {
		this.h13 = h13;
	}



	public BigDecimal getH14() {
		return h14;
	}



	public void setH14(BigDecimal h14) {
		this.h14 = h14;
	}



	public BigDecimal getH15() {
		return h15;
	}



	public void setH15(BigDecimal h15) {
		this.h15 = h15;
	}



	public BigDecimal getH16() {
		return h16;
	}



	public void setH16(BigDecimal h16) {
		this.h16 = h16;
	}



	public BigDecimal getH17() {
		return h17;
	}



	public void setH17(BigDecimal h17) {
		this.h17 = h17;
	}



	public BigDecimal getH18() {
		return h18;
	}



	public void setH18(BigDecimal h18) {
		this.h18 = h18;
	}



	public BigDecimal getH19() {
		return h19;
	}



	public void setH19(BigDecimal h19) {
		this.h19 = h19;
	}



	public BigDecimal getH20() {
		return h20;
	}



	public void setH20(BigDecimal h20) {
		this.h20 = h20;
	}



	public BigDecimal getH21() {
		return h21;
	}



	public void setH21(BigDecimal h21) {
		this.h21 = h21;
	}



	public BigDecimal getH22() {
		return h22;
	}



	public void setH22(BigDecimal h22) {
		this.h22 = h22;
	}



	public BigDecimal getH23() {
		return h23;
	}



	public void setH23(BigDecimal h23) {
		this.h23 = h23;
	}



	public BigDecimal getH24() {
		return h24;
	}



	public void setH24(BigDecimal h24) {
		this.h24 = h24;
	}



	public BigDecimal getTotals() {
		return totals;
	}



	public void setTotals(BigDecimal totals) {
		this.totals = totals;
	}



	public BigDecimal getResultTotals() {
		return resultTotals;
	}



	public void setResultTotals(BigDecimal resultTotals) {
		this.resultTotals = resultTotals;
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

	public BigDecimal getRecordTotal(){
		return h01.add(h02).add(h03).add(h04).add(h05).add(h06
				).add(h07).add(h08).add(h09).add(h10).add(h11).add(h12
				).add(h13).add(h14).add(h15).add(h16).add(h17).add(h18
				).add(h19).add(h20).add(h21).add(h22).add(h23).add(h24);
	}
	
}
