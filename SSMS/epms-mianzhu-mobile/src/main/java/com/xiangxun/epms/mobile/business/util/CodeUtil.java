package com.xiangxun.epms.mobile.business.util;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
/**
 * 生成code的值中的数字顺序号和年月字符串
 * @author admin
 *
 */
public class CodeUtil {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 生成digit位数字顺序号
	 * @param code  最新的code值
	 * @param digit 数字顺序号位数
	 * @return
	 */
	public static synchronized String setCode(String code,int digit){
		StringBuffer resulltCode = new StringBuffer();
		if(StringUtils.isEmpty(code)){
			for(int i=1;i<digit-1;i++){
				resulltCode.append("0");
			}
			resulltCode.append("1");
			
		}else{
			//取digit长度数字顺序号
			Integer num =Integer.valueOf(code.substring(code.length()-digit,code.length()));
			String str = String.valueOf(num+1);
			Integer len=str.length();
			for(int i=1;i<digit-len;i++){
				resulltCode.append("0");
			}
			resulltCode.append(str);
		}
		return resulltCode.toString();
	}
	/**
	 * 获取年月转换成字符串
	 * @return
	 */
	public static String getDateStr(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(calendar.getTime());
		StringBuffer u1 = new StringBuffer(formatNumber(calendar.get(Calendar.YEAR),4,'0'));
		u1.append(formatNumber(calendar.get(Calendar.MONTH) + 1,2,'0'));
		String str =u1.toString();
		return str;
	}
	public static synchronized String formatNumber(int number, int destLength, char paddedChar)
	{
		String oldString = String.valueOf(number);
		StringBuffer newString = new StringBuffer("");
		int oldLength = oldString.length();
		if (oldLength > destLength)
		{
			newString.append(oldString.substring(oldLength - destLength));
		}
		else if (oldLength == destLength)
		{
			newString.append(oldString);
		}
		else
		{
			for (int i = 0; i < destLength - oldLength; i++)
			{
				newString.append(paddedChar);
			}
			newString.append(oldString);
		}
		return newString.toString();
	}
}
