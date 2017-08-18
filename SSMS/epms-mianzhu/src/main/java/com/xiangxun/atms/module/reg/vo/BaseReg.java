package com.xiangxun.atms.module.reg.vo;

import java.math.BigDecimal;
import java.util.List;

import com.xiangxun.atms.module.files.vo.Files;

/**
 * 采样登记的继承类，添加附件属性
 * @author HaoXiang
 * 2017年7月28日
 */
public class BaseReg implements java.io.Serializable {
	
	private static final long serialVersionUID = 6418272050706431932L;
	
	private List<Files> files;
	
	private BigDecimal ph;
	//镉
    private BigDecimal cadmium;
    //有效态镉
    private BigDecimal availableCadmium;
    //分析单位
    private String analysisDept;
    //区域ID
    private String regionId;
	
	public List<Files> getFiles() {
		return files;
	}
	public void setFiles(List<Files> files) {
		this.files = files;
	}
	public BigDecimal getPh() {
		return ph;
	}
	public void setPh(BigDecimal ph) {
		this.ph = ph;
	}
	public BigDecimal getCadmium() {
		return cadmium;
	}
	public void setCadmium(BigDecimal cadmium) {
		this.cadmium = cadmium;
	}
	public BigDecimal getAvailableCadmium() {
		return availableCadmium;
	}
	public void setAvailableCadmium(BigDecimal availableCadmium) {
		this.availableCadmium = availableCadmium;
	}
	public String getAnalysisDept() {
		return analysisDept;
	}
	public void setAnalysisDept(String analysisDept) {
		this.analysisDept = analysisDept;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	
}
