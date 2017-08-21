package com.xiangxun.atms.framework.compnents.xls.imports.anotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>XlsMapping注解处理类</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public class XlsMappingResolver {
	/***
	 * 处理xls导入验证时的xlsMapping注解
	 * @param clazz
	 * @return xls映射属性包装类
	 */
	@SuppressWarnings("rawtypes")
	public List<XlsProperty> process(Class clazz) {
		Field[] fields = clazz.getDeclaredFields();
		List<XlsProperty> xlsPropertys = new ArrayList<XlsProperty>();
		for (Field field : fields) {
			 XlsMapping mapping = field.getAnnotation(XlsMapping.class);
			if (mapping != null) {
				field.setAccessible(true);
				try {
					String value = mapping.value();
					String propName = field.getName();
					XlsProperty xlsProperty =	new  XlsProperty(propName, mapping.cellNo());
					xlsProperty.setFixity(mapping.fixity());
					xlsProperty.setIstransfer(mapping.istransfer());
					xlsProperty.setDefaultValue(value);
					xlsProperty.setFixValue(mapping.fixValue());
					xlsPropertys.add(xlsProperty);
				} catch (IllegalArgumentException e) {
					
				} 
			}
		}
		return xlsPropertys;
	}
}
