package com.xiangxun.atms.framework.util;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.GenericValidator;

/**
 * <p>
 * 类说明：
 * </p>
 */
public class ValidateUtil extends GenericValidator {

	private static final long serialVersionUID = -5256713446214845228L;

	@SuppressWarnings("unchecked")
	public static <T> T[] arrayCopyOf(T[] original, int newLength) {
		return (T[]) copyOf(original, newLength, original.getClass());
	}

	@SuppressWarnings("unchecked")
	public static <T, U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
		T[] copy = ((Object) newType == (Object) Object[].class) ? (T[]) new Object[newLength] : (T[]) Array.newInstance(newType.getComponentType(), newLength);
		System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
		return copy;
	}

	/**
	 * 
	 * <p>
	 * 方法描述：List 转换为 Set
	 * </p>
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Set listToSet(List list) {
		Set set = new HashSet();
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			set.add(obj);
		}
		return set;
	}

	/**
	 * 对金额进行格式化
	 * 
	 * @param money
	 * 
	 * @return String 返回格式化后的金额
	 */
	public String formatMnyStr(double money) {
		DecimalFormat format = new DecimalFormat("###,##0.00");
		return format.format(money);
	}

	/************************************************************************************************************
	 * 使用正则表达式进行常用验证 －－－－begin
	 ***********************************************************************************************************/

	private static ValidateUtil instance;

	public static ValidateUtil getInstance() {
		if (instance != null)
			return instance;
		else
			return instance = new ValidateUtil();
	}

	public boolean startCheck(String reg, String string) {
		boolean tem = false;
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(string);
		tem = matcher.matches();
		return tem;
	}

	/**
	 * 检验整数,适用于正整数、负整数、0，负整数不能以-0开头
	 * 
	 * 
	 * @param nr
	 * 
	 * @return boolean
	 */
	public boolean checkNr(String nr) {
		String reg = "^(-?)[1-9]+\\d*|0";
		return startCheck(reg, nr);
	}

	/**
	 * 检验金额,正负小数
	 * 
	 * @param nr
	 * 
	 * @return boolean
	 */
	public boolean checkMny(String nr) {
		if (nr == null || "".equals(nr))
			return false;
		boolean retflg = false;
		String reg = "^(-?)[1-9|,|0.00,|E]+\\d*|0";
		char[] strc = nr.toCharArray();
		if (strc != null && strc.length > 1) {
			if ("0".equals(String.valueOf(strc[0]))) {
				if (!".".equals(String.valueOf(strc[1])))
					return false;
			}
		}
		retflg = startCheck(reg, nr);
		if (!retflg) {
			return retflg;
		} else {
			double mny = 0.0;
			try {
				mny = Double.valueOf(nr);
				if (mny > 100000000) {
					retflg = false;
				}
			} catch (Exception err) {
				return false;
			}
			return retflg;
		}
	}

	/**
	 * 检验百分比
	 * 
	 * @param nr
	 * 
	 * @return boolean
	 */
	public boolean checkPercent(String nr) {
		String reg = "^(-?)[1-9|,|0.00,|E,|%,|-]+\\d*|0";
		boolean retflg = false;
		char[] strc = nr.toCharArray();
		if (strc != null && strc.length > 1) {
			if ("0".equals(String.valueOf(strc[0]))) {
				if (!".".equals(String.valueOf(strc[1])))
					return false;
			}
		}
		retflg = startCheck(reg, nr);
		if (!retflg) {
			return retflg;
		} else {
			double mny = 0.0;
			try {
				mny = Double.valueOf(nr.replace("%", ""));
				if (mny > 100000000) {
					retflg = false;
				}
			} catch (Exception err) {
				return false;
			}
			return retflg;
		}
	}

	/**
	 * 手机号码验证,11位，不知道详细的手机号码段，只是验证开头必须是1和位数
	 * 
	 * @param cellPhoneNr
	 * 
	 * @return boolean
	 * */
	public boolean checkCellPhone(String cellPhoneNr) {
		String reg = "^[1][\\d]{10}";
		return startCheck(reg, cellPhoneNr);
	}

	/**
	 * 电话号码验证（不一定是手机）
	 * 
	 * @param cellTelephone
	 * @return
	 */
	public boolean checkCellTelephone(String cellTelephone) {
		String reg = "[\\d]{0,15}";
		return startCheck(reg, cellTelephone);
	}

	/**
	 * 检验空白符
	 * 
	 * @param line
	 * 
	 * @return boolean
	 * */
	public boolean checkWhiteLine(String line) {
		String regex = "(\\s|\\t|\\r)+";

		return startCheck(regex, line);
	}

	/**
	 * 检查EMAIL地址 用户名和网站名称必须>=1位字符
	 * 地址结尾必须是以com|cn|com|cn|net|org|gov|gov.cn|edu|edu.cn结尾
	 * 
	 * @param email
	 * 
	 * @return boolean
	 * */
	public boolean checkEmailWithSuffix(String email) {
		String regex = "\\w+\\@\\w+\\.(com|cn|com.cn|net|org|gov|gov.cn|edu|edu.cn)";

		return startCheck(regex, email);
	}

	/**
	 * 检查EMAIL地址 用户名和网站名称必须>=1位字符 地址结尾必须是2位以上，如：cn,test,com,info
	 * 
	 * @param email
	 * 
	 * @return boolean
	 * */
	public boolean checkEmail(String email) {
		String regex = "\\w+\\@\\w+\\.\\w{2,}";

		return startCheck(regex, email);
	}

	/**
	 * 检查邮政编码(中国),6位，第一位必须是非0开头，其他5位数字为0-9
	 * 
	 * @param postCode
	 * 
	 * @return boolean
	 */
	public boolean checkPostcode(String postCode) {
		String regex = "^[1-9]\\d{5}";
		return startCheck(regex, postCode);
	}

	/**
	 * 检验用户名 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾 用户名有最小长度和最大长度限制，比如用户名必须是4-20位
	 * 
	 * @param username
	 * @param min
	 * @param max
	 * 
	 * @return boolean
	 */
	public boolean checkUsername(String username, int min, int max) {
		String regex = "[\\w\u4e00-\u9fa5]{" + min + "," + max + "}(?<!_)";
		return startCheck(regex, username);
	}

	/**
	 * 检验用户名 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾 有最小位数限制的用户名，比如：用户名最少为4位字符
	 * 
	 * @param username
	 * @param min
	 * 
	 * @return boolean
	 */
	public boolean checkUsername(String username, int min) {
		// [\\w\u4e00-\u9fa5]{2,}?
		String regex = "[\\w\u4e00-\u9fa5]{" + min + ",}(?<!_)";

		return startCheck(regex, username);
	}

	/**
	 * 检验用户名 取值范围为a-z,A-Z,0-9,"_",汉字 最少一位字符，最大字符位数无限制，不能以"_"结尾
	 * 
	 * @param username
	 * 
	 * @return boolean
	 */
	public boolean checkUsername(String username) {
		String regex = "[\\w\u4e00-\u9fa5]+(?<!_)";
		return startCheck(regex, username);
	}

	/**
	 * 查看IP地址是否合法
	 * 
	 * @param ipAddress
	 * 
	 * @return boolean
	 */
	public boolean checkIP(String ipAddress) {
		String regex = "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\." + "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\." + "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\." + "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])";

		return startCheck(regex, ipAddress);
	}

	/**
	 * 验证国内电话号码 格式：010-67676767，区号长度3-4位，必须以"0"开头，号码是7-8位
	 * 
	 * @param phoneNr
	 * 
	 * @return boolean
	 */
	public boolean checkPhoneNr(String phoneNr) {
		String regex = "^[0]\\d{2,3}\\-\\d{7,8}";

		return startCheck(regex, phoneNr);
	}

	/**
	 * 验证国内电话号码 格式：6767676, 号码位数必须是7-8位,头一位不能是"0"
	 * 
	 * @param phoneNr
	 * 
	 * @return boolean
	 */
	public boolean checkPhoneNrWithoutCode(String phoneNr) {
		String reg = "^[1-9]\\d{6,7}";

		return startCheck(reg, phoneNr);
	}

	/**
	 * 验证国内电话号码 格式：0106767676，共11位或者12位，必须是0开头
	 * 
	 * @param phoneNr
	 * 
	 * @return boolean
	 */
	public boolean checkPhoneNrWithoutLine(String phoneNr) {
		String reg = "^[0]\\d{10,11}";

		return startCheck(reg, phoneNr);
	}

	/**
	 * 验证国内身份证号码：15或18位，由数字组成，不能以0开头
	 * 
	 * @param idNr
	 * 
	 * @return boolean
	 */
	public boolean checkIdCard(String idNr) {
		String reg = "^[1-9](\\d{14}|\\d{17})";

		return startCheck(reg, idNr);
	}

	/**
	 * 网址验证<br>
	 * 符合类型：<br>
	 * http://www.test.com<br>
	 * http://163.com
	 * 
	 * @param url
	 * 
	 * @return boolean
	 */
	public boolean checkWebSite(String url) {
		// http://www.163.com
		String reg = "^(http)\\://(\\w+\\.\\w+\\.\\w+|\\w+\\.\\w+)";

		return startCheck(reg, url);
	}

	public String double2String(double d, int fNumber) {
		if (fNumber < 0)
			fNumber = 0;
		String pattern = null;
		switch (fNumber) {
		case 0:
			pattern = "#0"; //$NON-NLS-1$
			break;
		default:
			pattern = "#0."; //$NON-NLS-1$
			StringBuffer b = new StringBuffer(pattern);
			for (int i = 0; i < fNumber; i++) {
				b.append('#');
			}
			pattern = b.toString();
			break;
		}
		DecimalFormat formatter = new DecimalFormat();
		formatter.applyPattern(pattern);
		String value = formatter.format(d);
		return value;
	}
	/************************************************************************************************************
	 * 使用正则表达式进行常用验证 －－－－end
	 ***********************************************************************************************************/

}
