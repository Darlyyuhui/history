package com.xiangxun.atms.module.analysis.vo.xls;

import java.math.BigDecimal;

import com.xiangxun.atms.framework.compnents.xls.imports.anotation.XlsMapping;

/**
 * 土壤数据导入实体类
 * @author HaoXiang
 * 2017年7月13日
 */
public class LandImport implements java.io.Serializable {

	private static final long serialVersionUID = 4578466114782476825L;

    /**
     * 采样编号
     */
    @XlsMapping(cellNo = 0)
    private String regNo;
    
    /**
     * 分析单位
     */
    @XlsMapping(cellNo = 1)
    private String dept;
    
    /**
     * ph值
     */
    @XlsMapping(cellNo = 2)
    private BigDecimal ph;

    /**
     * 镉
     */
    @XlsMapping(cellNo = 3)
    private BigDecimal cadmium;

    /**
     * 有效态镉
     */
    @XlsMapping(cellNo = 4)
    private BigDecimal availableCadmium;
    
    private int rowNum;

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
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

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

}
