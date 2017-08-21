/**
 * @(#)NumberFormatTools.java 1.3 08/09/12
 * MODIFY MEMO:
 * 
 * 张成/2008_09_12/主要修改程序注释，因为程序要按照统一的JAVA编码规范
 * 
 */
package com.xiangxun.atms.framework.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * <p>Description: 基本类型数据转换
 * 
 * @version 	1.3
 * @author 		liup
 * @since 		jdk1.4 以上
 */
public class NumberFormatTools {
	private static NumberFormatTools instance;

	public NumberFormatTools() {
	}

	public static NumberFormatTools getInstance() {
		if (instance != null)
			return instance;
		else
			return instance = new NumberFormatTools();
	}

	/**
	 * 得到 Integer类型
	 * 
	 * @param obj 要转换的对象
	 * 
	 * @return	Integer 返回转换后的值
	 */
	public Integer toInteger(Object obj) {
		try {
			if (obj == null || obj.equals("")) {
				return 0;
			} else {
				return new Integer(this.toNumber(obj).intValue());
			}
		} catch (Exception error) {
			return 0;
		}
	}

	/**
	 * 得到 Float类型
	 * 
	 * @param obj 要转换的对象
	 * 
	 * @return	Float 返回转换后的值
	 */

	public Float toFloat(Object obj) {
		if (obj == null || obj.equals("")) {
			return new Float(0);
		} else {
			return new Float(this.toNumber(obj).floatValue());
		}

	}

	/**
	 * 得到 Short类型
	 * 
	 * @param obj 要转换的对象
	 * 
	 * @return	Short 返回转换后的值
	 */

	public Short toShort(Object obj) {
		if (obj == null || obj.equals("")) {
			return null;
		} else {
			return new Short(this.toNumber(obj).shortValue());
		}

	}

	/**
	 * 对Double空对象进行格式化
	 * 
	 * @param obj 要进行格式化的对象
	 * 
	 * @return	Double 返回格式化的对象后的值
	 */
	public Double formatNull(Double obj) {
		if (obj == null || "".equals(obj))
			return 0.0;
		else
			return obj;
	}

	/**
	 * 对Integer空对象进行格式化
	 * 
	 * @param obj 要进行格式化的对象
	 * 
	 * @return	Integer 返回格式化的对象后的值
	 */
	public Integer formatNull(Integer obj) {
		if (obj == null || "".equals(obj))
			return 0;
		else
			return obj;
	}

	/**
	 * 对Long空对象进行格式化
	 * 
	 * @param obj 要进行格式化的对象
	 * 
	 * @return	Long 返回格式化的对象后的值
	 */
	public Long formatNull(Long obj) {
		if (obj == null || "".equals(obj))
			return Long.parseLong("0");
		else
			return obj;
	}

	/**
	 * 得到 Long类型
	 * 
	 * @param obj 要转换的对象
	 * 
	 * @return	Long 返回转换后的值
	 */

	public Long toLong(Object obj) {
		if (obj == null || obj.equals("")) {
			return new Long(0);
		} else {
			return new Long(this.toNumber(obj).longValue());
		}

	}

	/**
	 * 得到 Double类型
	 * 
	 * @param obj 要转换的对象
	 * 
	 * @return	Double 返回转换后的值
	 */
	public Double toDouble(Object obj) {
		try {
			if (obj == null || obj.toString() == null
					|| "".equals(obj.toString()) || "0".equals(obj.toString())) {
				return 0.0;
			} else {
				obj = (String.valueOf(obj)).trim();
				return new Double(this.toNumber(obj).doubleValue());
			}

		} catch (Exception err) {
			return 0.0;
		}
	}

	/**
	 * 构造一个  Number对象
	 * 
	 * @param obj Number 要转换的对象
	 * 
	 * @return	Number 返回转换后的值
	 */
	public Number toNumber(Object obj) {
		if (obj == null) {
			return null;
		}
		if (obj instanceof Number) {
			return (Number) obj;
		}
		try {
			NumberFormat parser = getNumberFormat(Locale.getDefault());
			return parser.parse(String.valueOf(obj));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 构造一个  Locale对象
	 * 
	 * @param locale Locale对象
	 * 
	 * @return	NumberFormat
	 */
	private NumberFormat getNumberFormat(Locale locale) {
		return NumberFormat.getNumberInstance(locale);
	}
}
