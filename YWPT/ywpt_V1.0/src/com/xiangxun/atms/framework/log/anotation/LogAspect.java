package com.xiangxun.atms.framework.log.anotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author admin
 *
 */
//注解运行的位置
@Target({ElementType.METHOD})  
//运行时执行的注解
@Retention(RetentionPolicy.RUNTIME)  
@Documented 
public @interface LogAspect {
	String desc() default "无描述信息";
}
