package com.xiangxun.atms.framework.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/***
 * List工具类
 * @author zhouhaij
 * @Apr 19, 2013 4:41:37 PM
 */
public abstract class ListUtils {

	/****
	 * 将List根据特定分割符转换为字符串
	 * @param list
	 * @param split
	 * @return
	 */
	public static String toString(java.util.List<String> list,String split){
		if(list==null)  return "";
		StringBuffer sb = new StringBuffer("");
		if(!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				String str = list.get(i);
				sb.append(str);
				if(i<list.size()-1){
					sb.append(split);
				}
			}
		}
		return sb.toString();
	}
	/**
	 * 判断集合为空
	 * @param o 集合类型
	 * @return
	 */
	public static boolean isNull(Collection<?> o){
		if (o.size()==0) {
			return true;
		}
		if (o.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * 判断集合不为空
	 * @param o 集合类型
	 * @return
	 */
	public static boolean isNotNull(Collection<?> o){
		if(o == null){
			return false;
		}else if (o.size()!=0 &&!o.isEmpty() ) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 取出两个数组中不同的数值
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static List<String> compare(String[] str1,String[] str2) {
		List<String> list1 = Arrays.asList(str1);
		List<String> list2 = new ArrayList<String>();
		for (String str : str2) {
			if (!list1.contains(str)) {
				list2.add(str);
			}
		}
		return list2;
	}
}
