package com.xiangxun.atms.common.dictionary.type;

import org.apache.commons.beanutils.Converter;


/***
 * DicType类型转换器
 * @author zhouhaij
 * @Apr 28, 2013 4:46:40 PM
 */
public class DicTypeConverter implements Converter{

	@SuppressWarnings("unchecked")
	@Override
	public Object convert( Class arg0, Object arg1) {
		if(arg1 == null || arg1.toString().length()==0) return null;
		return DicType.valuesOf(arg1.toString());
	}

}
