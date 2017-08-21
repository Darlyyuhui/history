package com.xiangxun.atms.framework.compnents.xls.imports.anotation;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <p>标题：对象属性参数</p>
 * @author zhouhaij 
 * @version 1.0 
 */
public class XlsProperty {
	/*
	 * excel 列中对应到javabean中的 javabean属性名称
	 */
	private String propertyName = null;
	/*
	 * excel中的标题中的列数 (  取值的时候可以用javabean属性和对应的列数取值),默认值为-1表示,此属性的值将不从excel中获取
	 */
	private int xlsCellColumn = -1;
	/*
	 * 在excel中没有的数据,在保存转换成对象时需要系统对javabean属性中的某一个值设置一个动态传入的值(指所有JavaBean的这个属性值，都是统一传入的固定值).设置此属性,必须设置默认值和fixValue
	 * 
	 */
	private Boolean fixity = false;
	/*
	 * 在导入EXCEL时,有些值在存入系统时,使用的值需要转换
	 * 如:一个保管期限的下拉列表中有{永久(Y),长期(C),短期(D)}
	 * 系统中存入的只能是 Y,C,D之类的值,导入EXCEL的值却是永久,长期,短期这类的值,需要转换.
	 * 转换方式:取到EXCEL中的具体值,从传入的MAP中取值.如 取值长期  C = MAP.GET("长期"); 
	 */
	private Boolean istransfer = false;
	
	/***
	 * 与fixity对应，如果fixity为真，那么返回的值将设置成此属性对应的值
	 */
	private String fixValue = "";
	
	/*
	 * 如果值为空,设置的默认值
	 */
	private String defaultValue = null;
	
	/***
	 * excel对应的cell的值
	 */
	private String xlsCellValue = null;
	
	/**
	 * @param propertyName
	 * @param xlsCellColumn
	 */
	public XlsProperty(String propertyName, int xlsCellColumn) {
		this.propertyName = propertyName;
		this.xlsCellColumn = xlsCellColumn;
	}
	
	
	
	/**
	 * @param propertyName
	 * @param fixity
	 * @param fixValue
	 * @param defaultValue
	 */
	public XlsProperty(String propertyName, Boolean fixity, String fixValue,String defaultValue) {
		this.propertyName = propertyName;
		this.fixity = fixity;
		this.fixValue = fixValue;
		this.defaultValue = defaultValue;
	}


	/**
	 * @param propertyName
	 * @param istransfer
	 */
	public XlsProperty(String propertyName,int xlsCellColumn,Boolean istransfer) {
		this.xlsCellColumn = xlsCellColumn;
		this.propertyName = propertyName;
		this.istransfer = istransfer;
	}



	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return this.propertyName;
	}
	/**
	 * @param propertyName the propertyName to set
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	/**
	 * @return the xlsCellColumn
	 */
	public int getXlsCellColumn() {
		return this.xlsCellColumn;
	}
	/**
	 * @param xlsCellColumn the xlsCellColumn to set
	 */
	public void setXlsCellColumn(int xlsCellColumn) {
		this.xlsCellColumn = xlsCellColumn;
	}
	/**
	 * @return the fixity
	 */
	public Boolean getFixity() {
		return this.fixity;
	}
	/**
	 * @param fixity the fixity to set
	 */
	public void setFixity(Boolean fixity) {
		this.fixity = fixity;
	}
	/**
	 * @return the istransfer
	 */
	public Boolean getIstransfer() {
		return this.istransfer;
	}
	/**
	 * @param istransfer the istransfer to set
	 */
	public void setIstransfer(Boolean istransfer) {
		this.istransfer = istransfer;
	}
	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return this.defaultValue;
	}
	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	/**
	 * @return the xlsCellValue
	 */
	public String getXlsCellValue() {
		return this.xlsCellValue;
	}
	/**
	 * @param xlsCellValue the xlsCellValue to set
	 */
	public void setXlsCellValue(String xlsCellValue) {
		this.xlsCellValue = xlsCellValue;
	}
	/**
	 * @return the fixValue
	 */
	public String getFixValue() {
		return this.fixValue;
	}
	/**
	 * @param fixValue the fixValue to set
	 */
	public void setFixValue(String fixValue) {
		this.fixValue = fixValue;
	}
	/**
     * {@link object.toString()}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
	
	
}
