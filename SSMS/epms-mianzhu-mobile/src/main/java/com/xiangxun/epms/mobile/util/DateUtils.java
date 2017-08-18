package com.xiangxun.epms.mobile.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *时间处理
 * author:
 **/
public final class DateUtils {
	static final String DORMART_TYPE = "yyyy-MM-dd HH:mm:ss";

	// 返回指定时间格式
	public static String dateToString(Date oldDate, String formartPatten) {
		try {
			SimpleDateFormat sdDateFormat = new SimpleDateFormat(formartPatten);
			return sdDateFormat.format(oldDate);
		} catch (Exception err) {
			System.out.println("时间转换失败" + oldDate.toString() + " to " + formartPatten);
			return null;
		}
	}

	// 返回默认时间格式
	public static String dateToString(Date oldDate) {
		try {
			SimpleDateFormat sdDateFormat = new SimpleDateFormat(DORMART_TYPE);
			return sdDateFormat.format(oldDate);
		} catch (Exception err) {
			return null;
		}
	}
	// 将字符串转换为日期,当出现错误时抛出异常
	public static Date stringToDateThrowException(String str, String formartPatten) throws Exception {
		SimpleDateFormat sdDateFormat = new SimpleDateFormat(formartPatten);
		if (str != null && !"".equals(str)) {
			Date date = null;
			date = sdDateFormat.parse(str);
			return date;
		}
		return null;
	}
	public static Timestamp StringToTimestamp(String srtTime){
		Timestamp ts = Timestamp.valueOf(srtTime);
		return ts;
	}
	  
}
