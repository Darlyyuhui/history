package com.xiangxun.atms.framework.compnents.xls.imports.anotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.xiangxun.atms.framework.compnents.xls.imports.demo.TestModel;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.ValidateBean;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.ValidateBean.RowValidateType;


/**
 * <p>XlsMapping注解处理类</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public class XlsValidateResolver {
	/***
	 * 处理xls导入验证时的xlsMapping注解
	 * @param clazz
	 * @return xls映射属性包装类
	 */
	@SuppressWarnings("rawtypes")
	public List<ValidateBean> process(Class clazz) {
		Field[] fields = clazz.getDeclaredFields();
		List<ValidateBean> validateBeans = new ArrayList<ValidateBean>();
		for (Field field : fields) {
			XlsValidate val = field.getAnnotation(XlsValidate.class);
			XlsMapping mapping = field.getAnnotation(XlsMapping.class);
			if (val != null) {
				try {
					RowValidateType[] type = val.type();
					for (RowValidateType rowValidateType : type) {
						ValidateBean validateBean =	new  ValidateBean(rowValidateType,val.maxLength(),val.maxValue(),val.minLength(),val.minValue(),val.required(),val.datePattern());
						if (mapping != null) {
							validateBean.setCellNo(mapping.cellNo());
						}else{
							validateBean.setCellNo(-1);
						}
						validateBean.setFieldName(field.getName());
						validateBeans.add(validateBean);
					}
				} catch (IllegalArgumentException e) {
					
				} 
			}
		}
		return validateBeans;
	}
	
	public static void main(String[] args) {
		XlsValidateResolver r = new XlsValidateResolver();
		List<ValidateBean> vs = r.process(TestModel.class);
		for (ValidateBean validateBean : vs) {
			System.out.println(validateBean.getType().toString());
		} 
	}
	
}
