package com.xiangxun.atms.module.statistics.vo;

import com.esotericsoftware.reflectasm.MethodAccess;

public class LandPh implements java.io.Serializable {

	private static final long serialVersionUID = 9148859316060078275L;

	private Long phTotal = 0L;
	private Long ph1 = 0L;
	private Long ph2 = 0L;
	private Long ph3 = 0L;
	private Long ph4 = 0L;
	private Long ph5 = 0L;
	private Long ph6 = 0L;
	private Long ph7 = 0L;
	private Long ph8 = 0L;
	private Long ph9 = 0L;
	private Long ph10 = 0L;
	private Long ph11 = 0L;
	private Long ph12 = 0L;
	private Long ph13 = 0L;
	private Long ph14 = 0L;
	
	public Long getPhTotal() {
		return phTotal;
	}
	public void setPhTotal(Long phTotal) {
		this.phTotal = phTotal;
	}
	public Long getPh1() {
		return ph1;
	}
	public void setPh1(Long ph1) {
		this.ph1 = ph1;
	}
	public Long getPh2() {
		return ph2;
	}
	public void setPh2(Long ph2) {
		this.ph2 = ph2;
	}
	public Long getPh3() {
		return ph3;
	}
	public void setPh3(Long ph3) {
		this.ph3 = ph3;
	}
	public Long getPh4() {
		return ph4;
	}
	public void setPh4(Long ph4) {
		this.ph4 = ph4;
	}
	public Long getPh5() {
		return ph5;
	}
	public void setPh5(Long ph5) {
		this.ph5 = ph5;
	}
	public Long getPh6() {
		return ph6;
	}
	public void setPh6(Long ph6) {
		this.ph6 = ph6;
	}
	public Long getPh7() {
		return ph7;
	}
	public void setPh7(Long ph7) {
		this.ph7 = ph7;
	}
	public Long getPh8() {
		return ph8;
	}
	public void setPh8(Long ph8) {
		this.ph8 = ph8;
	}
	public Long getPh9() {
		return ph9;
	}
	public void setPh9(Long ph9) {
		this.ph9 = ph9;
	}
	public Long getPh10() {
		return ph10;
	}
	public void setPh10(Long ph10) {
		this.ph10 = ph10;
	}
	public Long getPh11() {
		return ph11;
	}
	public void setPh11(Long ph11) {
		this.ph11 = ph11;
	}
	public Long getPh12() {
		return ph12;
	}
	public void setPh12(Long ph12) {
		this.ph12 = ph12;
	}
	public Long getPh13() {
		return ph13;
	}
	public void setPh13(Long ph13) {
		this.ph13 = ph13;
	}
	public Long getPh14() {
		return ph14;
	}
	public void setPh14(Long ph14) {
		this.ph14 = ph14;
	}
	
	public Long getValByNum(int i) {
		MethodAccess ma = MethodAccess.get(this.getClass());
		try {
			Object valObj = ma.invoke(this, "getPh"+i);
			if (valObj != null) {
				return (Long)valObj;
			}
		}catch(Exception e) {
		}
		return null;
	}
	
}
