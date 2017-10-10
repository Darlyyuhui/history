package com.xiangxun.atms.module.statistics.vo;

import com.esotericsoftware.reflectasm.MethodAccess;

public class LandACd implements java.io.Serializable {

	private static final long serialVersionUID = 5507185465184848872L;

	private Long acdTotal = 0L;
	private Long acd1 = 0L;
	private Long acd2 = 0L;
	private Long acd3 = 0L;
	private Long acd4 = 0L;
	private Long acd5 = 0L;
	private Long acd6 = 0L;
	private Long acd7 = 0L;
	private Long acd8 = 0L;
	private Long acd9 = 0L;
	private Long acd10 = 0L;
	public Long getAcdTotal() {
		return acdTotal;
	}
	public void setAcdTotal(Long acdTotal) {
		this.acdTotal = acdTotal;
	}
	public Long getAcd1() {
		return acd1;
	}
	public void setAcd1(Long acd1) {
		this.acd1 = acd1;
	}
	public Long getAcd2() {
		return acd2;
	}
	public void setAcd2(Long acd2) {
		this.acd2 = acd2;
	}
	public Long getAcd3() {
		return acd3;
	}
	public void setAcd3(Long acd3) {
		this.acd3 = acd3;
	}
	public Long getAcd4() {
		return acd4;
	}
	public void setAcd4(Long acd4) {
		this.acd4 = acd4;
	}
	public Long getAcd5() {
		return acd5;
	}
	public void setAcd5(Long acd5) {
		this.acd5 = acd5;
	}
	public Long getAcd6() {
		return acd6;
	}
	public void setAcd6(Long acd6) {
		this.acd6 = acd6;
	}
	public Long getAcd7() {
		return acd7;
	}
	public void setAcd7(Long acd7) {
		this.acd7 = acd7;
	}
	public Long getAcd8() {
		return acd8;
	}
	public void setAcd8(Long acd8) {
		this.acd8 = acd8;
	}
	public Long getAcd9() {
		return acd9;
	}
	public void setAcd9(Long acd9) {
		this.acd9 = acd9;
	}
	public Long getAcd10() {
		return acd10;
	}
	public void setAcd10(Long acd10) {
		this.acd10 = acd10;
	}
	public Long getValByNum(int i) {
		MethodAccess ma = MethodAccess.get(this.getClass());
		try {
			Object valObj = ma.invoke(this, "getAcd"+i);
			if (valObj != null) {
				return (Long)valObj;
			}
		}catch(Exception e) {
		}
		return null;
	}
}
