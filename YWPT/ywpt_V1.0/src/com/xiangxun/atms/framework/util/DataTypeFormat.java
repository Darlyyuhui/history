package com.xiangxun.atms.framework.util;

import org.apache.commons.lang.StringUtils;

/**
 * 类型转换工具类
 * @author lhh
 *
 */
public class DataTypeFormat {
	
	/**
	 * string转换成int
	 * @param str
	 * @return
	 */
	public static int getStringToInt(String str){
		int i=0;
		if (StringUtils.isNotEmpty(str)) {
			i=Integer.valueOf(str);
		}else{
			i=0;
		}
		return i;
	}
	
	/**
	 * string转换成long
	 * @param str
	 * @return
	 */
	public static long getStringToLong(String str){
		long i=0;
		if (StringUtils.isNotEmpty(str)) {
			i=Long.valueOf(str);
		}else{
			i=0;
		}
		return i;
	}
	
	/**
	 * string转换成double
	 * @param str
	 * @return
	 */
	public static double getStringToDouble(String str){
		double i=0;
		if (StringUtils.isNotEmpty(str)) {
			i=Double.valueOf(str);
		}else{
			i=0;
		}
		return i;
	}
	
	
}
