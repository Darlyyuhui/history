package com.xiangxun.atms.module.statistics.vo;

import com.esotericsoftware.reflectasm.MethodAccess;

public class LandCd implements java.io.Serializable {

	private static final long serialVersionUID = 3497473055402638522L;

	private Long cdTotal = 0L;
	private Long cd1 = 0L;
	private Long cd2 = 0L;
	private Long cd3 = 0L;
	private Long cd4 = 0L;
	private Long cd5 = 0L;
	private Long cd6 = 0L;
	private Long cd7 = 0L;
	private Long cd8 = 0L;
	private Long cd9 = 0L;
	private Long cd10 = 0L;
	public Long getCdTotal() {
		return cdTotal;
	}
	public void setCdTotal(Long cdTotal) {
		this.cdTotal = cdTotal;
	}
	public Long getCd1() {
		return cd1;
	}
	public void setCd1(Long cd1) {
		this.cd1 = cd1;
	}
	public Long getCd2() {
		return cd2;
	}
	public void setCd2(Long cd2) {
		this.cd2 = cd2;
	}
	public Long getCd3() {
		return cd3;
	}
	public void setCd3(Long cd3) {
		this.cd3 = cd3;
	}
	public Long getCd4() {
		return cd4;
	}
	public void setCd4(Long cd4) {
		this.cd4 = cd4;
	}
	public Long getCd5() {
		return cd5;
	}
	public void setCd5(Long cd5) {
		this.cd5 = cd5;
	}
	public Long getCd6() {
		return cd6;
	}
	public void setCd6(Long cd6) {
		this.cd6 = cd6;
	}
	public Long getCd7() {
		return cd7;
	}
	public void setCd7(Long cd7) {
		this.cd7 = cd7;
	}
	public Long getCd8() {
		return cd8;
	}
	public void setCd8(Long cd8) {
		this.cd8 = cd8;
	}
	public Long getCd9() {
		return cd9;
	}
	public void setCd9(Long cd9) {
		this.cd9 = cd9;
	}
	public Long getCd10() {
		return cd10;
	}
	public void setCd10(Long cd10) {
		this.cd10 = cd10;
	}
	
	public Long getValByNum(int i) {
		MethodAccess ma = MethodAccess.get(this.getClass());
		try {
			Object valObj = ma.invoke(this, "getCd"+i);
			if (valObj != null) {
				return (Long)valObj;
			}
		}catch(Exception e) {
		}
		return null;
	}
}
