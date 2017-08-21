package com.xiangxun.atms.framework.util;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import com.xiangxun.atms.framework.constant.FORMAT;

/**
 * Description: 格式化工具类。
 * 
 * @version 1.2
 */

public class FormatUtils {

	public static final String NULL = "<null>";

	/**
	 * 格式化传入的Boolean对象。
	 * 
	 * @param obj
	 *            Boolean - 要格式化的对象
	 * @return String - 格式化后的字符串
	 */
	public static String formatBoolean(Boolean obj) {
		if (ObjectUtils.isNull(obj))
			return NULL;

		return String.valueOf(obj);
	}

	/**
	 * 格式化传入的Character对象。
	 * 
	 * @param obj
	 *            Character - 要格式化的对象
	 * @return String - 格式化后的字符串
	 */
	public static String formatCharacter(Character obj) {
		if (ObjectUtils.isNull(obj))
			return NULL;

		return String.valueOf(obj);
	}

	/**
	 * 格式化传入的Byte对象。
	 * 
	 * @param obj
	 *            Byte - 要格式化的对象
	 * @return String - 格式化后的字符串
	 */
	public static String formatByte(Byte obj) {
		if (ObjectUtils.isNull(obj))
			return NULL;

		return String.valueOf(obj);
	}

	/**
	 * 格式化传入的Short对象。
	 * 
	 * @param obj
	 *            Short - 要格式化的对象
	 * @return String - 格式化后的字符串
	 */
	public static String formatShort(Short obj) {
		if (ObjectUtils.isNull(obj))
			return NULL;

		return String.valueOf(obj);
	}

	/**
	 * 格式化传入的Integer对象。
	 * 
	 * @param obj
	 *            Integer - 要格式化的对象
	 * @return String - 格式化后的字符串
	 */
	public static String formatInteger(Integer obj) {
		if (ObjectUtils.isNull(obj))
			return NULL;

		return String.valueOf(obj);
	}

	/**
	 * 格式化传入的Long对象。
	 * 
	 * @param obj
	 *            Long - 要格式化的对象
	 * @return String - 格式化后的字符串
	 */
	public static String formatLong(Long obj) {
		if (ObjectUtils.isNull(obj))
			return NULL;

		return String.valueOf(obj);
	}

	/**
	 * 格式化传入的Float对象。
	 * 
	 * @param obj
	 *            Float - 要格式化的对象
	 * @return String - 格式化后的字符串
	 */
	public static String formatFloat(Float obj) {
		if (ObjectUtils.isNull(obj))
			return NULL;

		DecimalFormat df = new DecimalFormat("0.00########");

		return df.format(obj);
	}

	/**
	 * 格式化传入的Double对象。
	 * 
	 * @param obj
	 *            Double - 要格式化的对象
	 * @return String - 格式化后的字符串
	 */
	public static String formatDouble(Double obj) {
		if (ObjectUtils.isNull(obj))
			return NULL;

		DecimalFormat df = new DecimalFormat("0.00########");

		return df.format(obj);
	}

	// //////////////////////////////// 格式复合类型
	// //////////////////////////////////

	/**
	 * 格式化数组。
	 * 
	 * @param array
	 *            Object - 要格式化的数组
	 * @param wrap
	 *            boolean - 是否换行
	 * @return String - 完成格式化后的返回字符串
	 */
	public static String formatArray(Object[] array, boolean wrap) {
		if (ObjectUtils.isNull(array))
			return NULL;

		StringBuffer msg = new StringBuffer();

		Object obj = null;

		if (wrap)
			msg.append("\n    ");

		msg.append("[");

		if (wrap)
			msg.append("\n");

		for (int i = 0; i < array.length; i++) {
			obj = array;

			if (wrap)
				msg.append("        ");

			msg.append(formatObject(obj));

			if (wrap)
				msg.append("\n");
			else if (i < array.length - 1)
				msg.append(", ");
		}

		if (wrap)
			msg.append("    ");

		msg.append("]");

		if (wrap)
			msg.append("\n");

		return msg.toString();
	}

	// //////////////////////////////////////////////////////////////////////////////

	/**
	 * 格式化Map。
	 * 
	 * @param map
	 *            Map - 要格式化的Map
	 * @param needClassName
	 *            boolean - 是否需要对象的类名
	 * @param wrap
	 *            boolean - 是否换行
	 * @return String - 完成格式化后的返回字符串
	 */
	public static String formatMap(Map map, boolean needClassName, boolean wrap) {
		if (ObjectUtils.isNull(map))
			return NULL;

		if (map.size() == 0)
			return "{}";

		Iterator it = map.keySet().iterator();

		Object key = null;
		Object obj = null;

		int max_key_length = 0;

		if (wrap) {
			while (it.hasNext()) {
				key = it.next();
				max_key_length = Math.max(max_key_length, key.toString().length());
			}
			it = map.keySet().iterator();
		}

		StringBuffer msg = new StringBuffer();

		if (wrap)
			msg.append("\n    ");

		msg.append("{");

		if (wrap)
			msg.append("\n");

		int i = 0;

		while (it.hasNext()) {
			key = it.next();

			if (wrap)
				msg.append("        ");

			if (needClassName)
				msg.append("(").append(key.getClass().getName()).append(")").append(" ");

			msg.append("'").append(key).append("'");

			if (wrap)
				msg.append(StringUtils.stringOfChar(' ', max_key_length - key.toString().length()));

			msg.append(" --> ");

			obj = map.get(key);

			if (ObjectUtils.isNull(obj)) {
				msg.append(NULL);
			} else {
				if (needClassName)
					msg.append("(").append(obj.getClass().getName()).append(")").append(" ");

				msg.append(formatObject(obj));
			}

			if (wrap)
				msg.append("\n");
			else if (it.hasNext())
				msg.append(", ");
		}

		if (wrap)
			msg.append("    ");

		msg.append("}");

		return msg.toString();
	}

	// //////////////////////////////////////////////////////////////////////////////

	/**
	 * 格式化Enumeration。
	 * 
	 * @param enu
	 *            Enumeration - 要格式化的Enumeration
	 * @param needClassName
	 *            boolean - 是否需要对象的类名
	 * @param wrap
	 *            boolean - 是否换行
	 * @return String - 完成格式化后的返回字符串
	 */
	public static String formatEnumeration(Enumeration enu, boolean needClassName, boolean wrap) {
		if (ObjectUtils.isNull(enu))
			return NULL;

		Object obj = null;

		StringBuffer msg = new StringBuffer();

		if (wrap)
			msg.append("\n    ");

		msg.append("{");

		if (wrap)
			msg.append("\n");

		int i = 0;

		while (enu.hasMoreElements()) {
			obj = enu.nextElement();

			if (ObjectUtils.isNull(obj)) {
				msg.append(NULL);
			} else {
				if (needClassName)
					msg.append("(").append(obj.getClass().getName()).append(")").append(" ");

				msg.append(formatObject(obj));
			}

			if (wrap)
				msg.append("\n");
			else if (enu.hasMoreElements())
				msg.append(", ");
		}

		if (wrap)
			msg.append("    ");

		msg.append("}");

		if (wrap)
			msg.append("\n");

		return msg.toString();
	}

	// //////////////////////////////////////////////////////////////////////////////

	/**
	 * 将传入的对象格式化为字符串。
	 * 
	 * @param obj
	 *            Object - 要格式化的对象
	 * @return String - 格式化后的字符串
	 */
	public static String formatObject(Object obj) {
		try {
			if (ObjectUtils.isNull(obj))
				return NULL;

			if (obj instanceof String) {
				return "\"" + obj.toString() + "\"";
			}
			if (obj instanceof Boolean) {
				return formatBoolean((Boolean) obj);
			}
			if (obj instanceof Character) {
				return "'" + formatCharacter((Character) obj) + "'";
			}
			if (obj instanceof Byte) {
				return formatByte((Byte) obj);
			}
			if (obj instanceof Short) {
				return formatShort((Short) obj);
			}
			if (obj instanceof Integer) {
				return formatInteger((Integer) obj);
			}
			if (obj instanceof Float) {
				return formatFloat((Float) obj);
			}
			if (obj instanceof Double) {
				return formatDouble((Double) obj);
			}
			if (obj instanceof Date) {
				return DateUtil.formatDate(FORMAT.DATETIME.DEFAULT, (Date) obj);
			}
			if (obj instanceof Object[]) {
				return formatArray((Object[]) obj, false);
			}
			if (obj instanceof Map) {
				return formatMap((Map) obj, true, false);
			}
			if (obj instanceof Enumeration) {
				return formatEnumeration((Enumeration) obj, true, false);
			} else {
				return obj.toString();
			}
		} catch (Exception ex) {
			return ex.toString();
		}
	}

	/**
	 * 将传入的字符串按指定的宽度格式化，即如果传入的字符串长度大于指定的宽度， 则根据是否截断返回对应字符串，否则则根据输入参数在前后补齐。
	 * 
	 * @param s
	 *            String - 要格式化的字符串
	 * @param width
	 *            int - 指定要格式化的宽度
	 * @param align
	 *            int - 对齐模式：小于0表示左对齐； 等于0表示在居中对齐； 大于0表示在右对齐
	 * @param fillWith
	 *            char - 补齐字符
	 * @param omitted
	 *            boolean - 当传入字符串长度大于指定长度时是否省略/截断
	 * @param ellipsis
	 *            String - 省略号，如果该参数为空，则仅仅按宽度截断
	 * @return String - 返回格式化后的字符串
	 */
	public static String formatWidth(String s, int width, int align, char fillWith, boolean omitted, String ellipsis) {
		if (s.length() == width) {
			return s;
		} else if (s.length() > width) {
			if (omitted)
				return StringUtils.omit(s, width, ellipsis);
			else
				return s;
		} else // if (s.length() > width)
		{
			int diff = width - s.length();

			if (align < 0) // 左对齐，后面补字符
			{
				return s + StringUtils.stringOfChar(fillWith, diff);
			} else if (align == 0) // 居中对齐，两边都补字符
			{
				return StringUtils.stringOfChar(fillWith, diff / 2) + s + StringUtils.stringOfChar(fillWith, diff - (diff / 2));
			} else // if (align > 0) // 右对齐，前面补字符
			{
				return StringUtils.stringOfChar(fillWith, diff) + s;
			}
		}
	}

	/**
	 * 将传入的字符串按指定的宽度格式化，即如果传入的字符串长度大于指定的宽度， 则原样返回传入字符串，否则则根据输入参数在前后补齐。
	 * 
	 * @param s
	 *            String - 要格式化的字符串
	 * @param width
	 *            int - 指定要格式化的宽度
	 * @param align
	 *            int - 对齐模式：小于0表示左对齐； 等于0表示在居中对齐； 大于0表示在右对齐
	 * @param fillWith
	 *            char - 补齐字符
	 * @return String - 返回格式化后的字符串
	 */
	public static String formatWidth(String s, int width, int align, char fillWith) {
		return formatWidth(s, width, align, fillWith, false, "");
	}

	/**
	 * 将传入的字符串按指定的宽度格式化，即如果传入的字符串长度大于指定的宽度， 则原样返回传入字符串，否则则根据输入参数在前后补齐空格。
	 * 
	 * @param s
	 *            String - 要格式化的字符串
	 * @param width
	 *            int - 指定要格式化的宽度
	 * @param align
	 *            int - 对齐模式：小于0表示左对齐； 等于0表示在居中对齐； 大于0表示在右对齐
	 * @return String - 返回格式化后的字符串
	 */
	public static String formatWidth(String s, int width, int align) {
		return formatWidth(s, width, align, ' ', false, "");
	}

	/**
	 * 将传入的字符串按指定的宽度格式化，即如果传入的字符串长度大于指定的宽度， 则原样返回传入字符串，否则则根据输入参数在后面补齐空格。
	 * 
	 * @param s
	 *            String - 要格式化的字符串
	 * @param width
	 *            int - 指定要格式化的宽度
	 * @return String - 返回格式化后的字符串
	 */
	public static String formatWidth(String s, int width) {
		return formatWidth(s, width, -1, ' ');
	}

	// //////////////////////////////////////////////////////////////////////////////

	/**
	 * 格式化传入的键值对。
	 * 
	 * @param name
	 *            String - 键名
	 * @param value
	 *            Object - 键值
	 * @param width
	 *            int - 键名宽度
	 * @return String - 返回格式化后的字符串
	 */
	public static String formatValue(String name, Object value, int width) {
		return formatWidth(name, width) + " = " + formatObject(value);
	}

	// //////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {

		Object[] arrays = { 1, 23, 45, 32, 67, 98, 100 };
		String result = FormatUtils.formatArray(arrays, true);
		System.out.println(result);

	}
}
