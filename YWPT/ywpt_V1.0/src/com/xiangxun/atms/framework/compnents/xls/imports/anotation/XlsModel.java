package com.xiangxun.atms.framework.compnents.xls.imports.anotation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>xls待转换的model</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public class XlsModel implements Serializable{
	private static final long serialVersionUID = 4262615683584636066L;
	
	private List<XlsProperty> xlsPropertys;
	
	/***
	 * 在导入EXCEL时,有些值在存入系统时,使用的值需要转换
	 * 如:一个保管期限的下拉列表中有{永久(Y),长期(C),短期(D)}
	 * 系统中存入的只能是 Y,C,D之类的值,导入EXCEL的值却是永久,长期,短期这类的值,需要转换.
	 * 转换方式:取到EXCEL中的具体值,从传入的MAP中取值.如 取值长期  C = MAP.GET("长期");
	 * 此map的存入方式为map.put("长期","C")  key表示需要转换的值,value表示转换后的值
	 */
    private Map<String,String> transferMap;

	/**
	 * @return the xlsPropertys
	 */
	public List<XlsProperty> getXlsPropertys() {
		return this.xlsPropertys;
	}

	/**
	 * @param xlsPropertys the xlsPropertys to set
	 */
	public void setXlsPropertys(List<XlsProperty> xlsPropertys) {
		this.xlsPropertys = xlsPropertys;
	}

	/**
	 * @return the transferMap
	 */
	public Map<String, String> getTransferMap() {
		return this.transferMap;
	}

	/**
	 * @param transferMap the transferMap to set
	 */
	public void setTransferMap(Map<String, String> transferMap) {
		this.transferMap = transferMap;
	}
    
    
}
