package com.xiangxun.atms.framework.compnents.xls.imports.anotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>xls属性映射的注解</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface XlsMapping {
	
	int cellNo() default -1;
	
	/*
	 * 在excel中没有的数据,在保存转换成对象时需要系统对javabean属性中的某一个值设置一个动态传入的值(指所有JavaBean的这个属性值，都是统一传入的固定值).设置此属性,必须设置默认值和fixValue
	 * 
	 */
	boolean fixity() default false;
	/*
	 * 在导入EXCEL时,有些值在存入系统时,使用的值需要转换
	 * 如:一个保管期限的下拉列表中有{永久(Y),长期(C),短期(D)}
	 * 系统中存入的只能是 Y,C,D之类的值,导入EXCEL的值却是永久,长期,短期这类的值,需要转换.
	 * 转换方式:取到EXCEL中的具体值,从传入的MAP中取值.如 取值长期  C = MAP.GET("长期"); 
	 */
	boolean istransfer() default false;
	
	/***
	 * 与fixity对应，如果fixity为真，那么返回的值将设置成此属性对应的值
	 */
	 String fixValue() default "";
	
	/*
	 * 如果值为空,设置的默认值
	 */
	 String value() default "";

}
