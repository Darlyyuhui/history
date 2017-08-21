package com.xiangxun.atms.framework.compnents.xls.imports.validate;
/**
 * @author zhouhaijian
 */
public class ValidateBean {
	/***
	 * 单元格的值
	 */
	private String value;
	/***
	 * 验证类型
	 */
	private RowValidateType type;
	
	/***
	 * 行号
	 */
	private Integer row;
	
	/***
	 * 列名
	 */
	private String  colName;
	
	private int maxLength;
	
	private Integer maxValue;
	
	private int minLength;
	
	private Integer minValue;
	
	private Boolean required = false;

	//日期格式
	private String datePattern ="yyyy-MM-dd HH:mm:ss";
	
	private int cellNo;
	
	private String fieldName;
	
	public String getValue() {
		return value;
	}

	@SuppressWarnings("unused")
	private ValidateBean() {
		
	}

	public ValidateBean(String value, RowValidateType type, Integer row,String colName,int maxLength) {
		this.value = value;
		this.type = type;
		this.row = row;
		this.colName = colName;
		this.maxLength = maxLength;
	}
	
	

	/**
	 * @param type
	 * @param maxLength
	 * @param maxValue
	 * @param minLength
	 * @param minValue
	 * @param required
	 * @param datePattern
	 */
	public ValidateBean(RowValidateType type, int maxLength, Integer maxValue,int minLength, Integer minValue, Boolean required,
			String datePattern) {
		this.type = type;
		this.maxLength = maxLength;
		this.maxValue = maxValue;
		this.minLength = minLength;
		this.minValue = minValue;
		this.required = required;
		this.datePattern = datePattern;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the type
	 */
	public RowValidateType getType() {
		return this.type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(RowValidateType type) {
		this.type = type;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}
	
	/**
	 * @return the maxLength
	 */
	public int getMaxLength() {
		return this.maxLength;
	}

	/**
	 * @param maxLength the maxLength to set
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * @return the maxValue
	 */
	public Integer getMaxValue() {
		return this.maxValue;
	}

	/**
	 * @param maxValue the maxValue to set
	 */
	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return the minLength
	 */
	public int getMinLength() {
		return this.minLength;
	}

	/**
	 * @param minLength the minLength to set
	 */
	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	/**
	 * @return the minValue
	 */
	public Integer getMinValue() {
		return this.minValue;
	}

	/**
	 * @param minValue the minValue to set
	 */
	public void setMinValue(Integer minValue) {
		this.minValue = minValue;
	}

	/**
	 * @return the required
	 */
	public Boolean getRequired() {
		return this.required;
	}

	/**
	 * @param required the required to set
	 */
	public void setRequired(Boolean required) {
		this.required = required;
	}

	/**
	 * @return the datePattern
	 */
	public String getDatePattern() {
		return this.datePattern;
	}

	/**
	 * @param datePattern the datePattern to set
	 */
	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	
	
	/**
	 * @return the cellNo
	 */
	public int getCellNo() {
		return this.cellNo;
	}

	/**
	 * @param cellNo the cellNo to set
	 */
	public void setCellNo(int cellNo) {
		this.cellNo = cellNo;
	}



	public enum RowValidateType{
		// 手机号码验证,11位，不知道详细的手机号码段，只是验证开头必须是1和位数  
		PHONE(1),
		// Date
		DATE(2),
		// 性别
		GENDER(3),
		//验证电话号码（不一定是手机）
		TELEPHONE(4),
		//检验金额
		SCORE(5),
		//检验整数,适用于正整数、负整数、0，负整数不能以-0开头
		INTEGER(6),
		//email
		EMAIL(7),
		
		BOOLEAN(8),
		//Checks if the value's adjusted length is less than or equal to the max.
		MAXLENGTH(9),
		//最小长度
		MINLENGTH(11),
		UNIQUE(10),
		//add by kouyunhao 2014-04-03 匹配校验。
		MATCH(11);
		
		private final int value;

		private RowValidateType(int value) {
			this.value = value;
		}

		public int value() {
			return this.value;
		}

		public String toString() {
			return Integer.toString(this.value);
		}

		public static RowValidateType valueOf(int levelValue) {
			for (RowValidateType logLevel : values()) {
				if (logLevel.value == levelValue) {
					return logLevel;
				}
			}
			throw new IllegalArgumentException("No matching constant for ["
					+ levelValue + "]");
		}
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
