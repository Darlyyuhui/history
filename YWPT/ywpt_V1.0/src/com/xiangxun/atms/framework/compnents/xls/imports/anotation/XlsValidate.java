package com.xiangxun.atms.framework.compnents.xls.imports.anotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.xiangxun.atms.framework.compnents.xls.imports.validate.ValidateBean.RowValidateType;

/**
 * <p>xls导入验证注解</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface XlsValidate {

	 RowValidateType[] type();
	
	 int maxLength() default 0;
	
	 int maxValue()  default 0;
	
	 int minLength() default 0;
	
	 int minValue() default 0;
	
	 boolean required() default true;

	 //日期格式
	 String datePattern() default "yyyy-MM-dd HH:mm:ss";
}
