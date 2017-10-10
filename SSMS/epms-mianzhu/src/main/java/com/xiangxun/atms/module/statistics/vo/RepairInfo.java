package com.xiangxun.atms.module.statistics.vo;

public class RepairInfo implements java.io.Serializable {

	private static final long serialVersionUID = 6567461198083537117L;

	private String regionId;
	/**
	 * 区域总面积
	 */
	private double total = 0.00;
	/**
	 * 修改完成的面积
	 */
	private double end = 0.00;
	/**
	 * 阶段1的面积
	 */
	private double stage1 = 0.00;
	/**
	 * 阶段2的面积
	 */
	private double stage2 = 0.00;
	private double stage3 = 0.00;
	private double stage4 = 0.00;
	private double stage5 = 0.00;
	private double stage6 = 0.00;
	private double stage7 = 0.00;
	private double stage8 = 0.00;
	private double stage9 = 0.00;
	private double stage10 = 0.00;
	private double stage11 = 0.00;
	private double stage12 = 0.00;
	private double stage13 = 0.00;
	private double stage14 = 0.00;
	private double stage15 = 0.00;
	private double stage16 = 0.00;
	private double stage17 = 0.00;
	private double stage18 = 0.00;
	private double stage19 = 0.00;
	private double stage20 = 0.00;
	
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getEnd() {
		return end;
	}
	public void setEnd(double end) {
		this.end = end;
	}
	public double getStage1() {
		return stage1;
	}
	public void setStage1(double stage1) {
		this.stage1 = stage1;
	}
	public double getStage2() {
		return stage2;
	}
	public void setStage2(double stage2) {
		this.stage2 = stage2;
	}
	public double getStage3() {
		return stage3;
	}
	public void setStage3(double stage3) {
		this.stage3 = stage3;
	}
	public double getStage4() {
		return stage4;
	}
	public void setStage4(double stage4) {
		this.stage4 = stage4;
	}
	public double getStage5() {
		return stage5;
	}
	public void setStage5(double stage5) {
		this.stage5 = stage5;
	}
	public double getStage6() {
		return stage6;
	}
	public void setStage6(double stage6) {
		this.stage6 = stage6;
	}
	public double getStage7() {
		return stage7;
	}
	public void setStage7(double stage7) {
		this.stage7 = stage7;
	}
	public double getStage8() {
		return stage8;
	}
	public void setStage8(double stage8) {
		this.stage8 = stage8;
	}
	public double getStage9() {
		return stage9;
	}
	public void setStage9(double stage9) {
		this.stage9 = stage9;
	}
	public double getStage10() {
		return stage10;
	}
	public void setStage10(double stage10) {
		this.stage10 = stage10;
	}
	public double getStage11() {
		return stage11;
	}
	public void setStage11(double stage11) {
		this.stage11 = stage11;
	}
	public double getStage12() {
		return stage12;
	}
	public void setStage12(double stage12) {
		this.stage12 = stage12;
	}
	public double getStage13() {
		return stage13;
	}
	public void setStage13(double stage13) {
		this.stage13 = stage13;
	}
	public double getStage14() {
		return stage14;
	}
	public void setStage14(double stage14) {
		this.stage14 = stage14;
	}
	public double getStage15() {
		return stage15;
	}
	public void setStage15(double stage15) {
		this.stage15 = stage15;
	}
	public double getStage16() {
		return stage16;
	}
	public void setStage16(double stage16) {
		this.stage16 = stage16;
	}
	public double getStage17() {
		return stage17;
	}
	public void setStage17(double stage17) {
		this.stage17 = stage17;
	}
	public double getStage18() {
		return stage18;
	}
	public void setStage18(double stage18) {
		this.stage18 = stage18;
	}
	public double getStage19() {
		return stage19;
	}
	public void setStage19(double stage19) {
		this.stage19 = stage19;
	}
	public double getStage20() {
		return stage20;
	}
	public void setStage20(double stage20) {
		this.stage20 = stage20;
	}
	//已完成占比
	public double getEndProp() {
		return this.end / this.total * 100;
	}
}
