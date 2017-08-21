package com.xiangxun.atms.framework.compnents.xls.imports.validate;
/***
 * 数据唯一性校验器
 * @author zhouhaij
 */
public interface UniqueValidator {
	
	/***
	 * 判断数据是否存在
	 * @param fieldName 要判断的属性名
	 * @param value 存在即返回true
	 * @return
	 */
	public boolean validate(String fieldName,String value);
}
