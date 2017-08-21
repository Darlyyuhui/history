package com.xiangxun.atms.framework.compnents.xls.export.template;

import java.io.Serializable;

/**
 * <h2>xls数据封装类</h2>
 * @author zhouhaijian
 * @version 1.0
 * @since 1.0
 * 
 */
public class ExcelEntity implements Serializable{
	private static final long serialVersionUID = 7963547099195132250L;

	//行号
	private int rowNo = 0;

	//单元格
	private int cell = 0;

	//值
	private Object value = null;

	/**
	 * 
	 */
	public ExcelEntity() {
		super();
	}

	/**
	 * @param rowNo
	 * @param cell
	 * @param value
	 */
	public ExcelEntity(int rowNo, int cell, Object value) {
		super();
		this.rowNo = rowNo;
		this.cell = cell;
		this.value = value;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public int getCell() {
		return cell;
	}

	public void setCell(int cell) {
		this.cell = cell;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}


}