package com.xiangxun.atms.framework.util;


import java.util.Arrays;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


/***
 * 工具类
 * @author yantao
 * @Mar 21, 2013 4:41:37 PM
 */
public class ZhongWenToPinYin {
	/**
	 * 获取拼音
	 * 
	 * @param zhongwen
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getPinYin(String zhongwen) {

		String zhongWenPinYin = "";
		char[] chars = zhongwen.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			String[] pinYin;
			try {
				pinYin = PinyinHelper.toHanyuPinyinStringArray(chars[i],
						getDefaultOutputFormat());
				// 当转换不是中文字符时,返回null
				if (pinYin != null) {
					zhongWenPinYin += capitalize(pinYin[0]);
				} else {
					zhongWenPinYin += chars[i];
				}
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				e.printStackTrace();
				System.out.println("中文转换拼音错误!");
			}
		}
		return zhongWenPinYin;
	}
	
	
	public static String getPinyinCapitalize(String zhongwen){
		String zhongWenPinYin = "";
		char[] chars = zhongwen.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			String[] pinYin;
			try {
				pinYin = PinyinHelper.toHanyuPinyinStringArray(chars[i],getDefaultOutputFormat());
				/**
				 * add by kouyunhao 2014-09-10 添加排序，仅解决道路树排序时【长安区】被处理成zaq问题，
				 * 排序之前pinYin[]={zhang,chang}，默认取第一个。
				 */
				String[] chang_py = new String[]{"zhang","chang"};
				if(pinYin != null){
					if(pinYin.length == 2){
						if(pinYin[0].equals(chang_py[0]) && pinYin[1].equals(chang_py[1])){
							pinYin = sort1(pinYin);
						}
					}
				}
				// 当转换不是中文字符时,返回null
				if (pinYin != null) {
					zhongWenPinYin += new String(pinYin[0].toCharArray()[0]+"");
				} else {
					zhongWenPinYin += chars[i];
				}
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				e.printStackTrace();
				System.out.println("中文转换拼音错误!");
			}
		}
		return zhongWenPinYin;
	}
	
	
	public static String getFirstPartPinYin(String zhongwen) {

		String zhongWenPinYin = "";
		char[] chars = zhongwen.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			String[] pinYin;
			try {
				pinYin = PinyinHelper.toHanyuPinyinStringArray(chars[i],getDefaultOutputFormat());
				// 当转换不是中文字符时,返回null
				if (pinYin != null) {
					zhongWenPinYin +=  new String(pinYin[0].toCharArray()[0]+"");
				} else {
					zhongWenPinYin += chars[i];
				}
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				e.printStackTrace();
				System.out.println("中文转换拼音错误!");
			}
		}
		return zhongWenPinYin;
	}
	
	/**
	 * 获取拼音 首字母不大写
	 * 
	 * @param zhongwen
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getPinYinNormal(String zhongwen) {

		String zhongWenPinYin = "";
		char[] chars = zhongwen.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			String[] pinYin;
			try {
				pinYin = PinyinHelper.toHanyuPinyinStringArray(chars[i],
						getDefaultOutputFormat());
				/**
				 * add by kouyunhao 2014-09-10 添加排序，仅解决道路树排序时【长安区】被处理成zaq问题，
				 * 排序之前pinYin[]={zhang,chang}，默认取第一个。
				 */
				String[] chang_py = new String[]{"zhang","chang"};
				if(pinYin != null){
					if(pinYin.length == 2){
						if(pinYin[0].equals(chang_py[0]) && pinYin[1].equals(chang_py[1])){
							pinYin = sort1(pinYin);
						}
					}
				}
				// 当转换不是中文字符时,返回null
				if (pinYin != null) {
					zhongWenPinYin += pinYin[0];
				} else {
					zhongWenPinYin += chars[i];
				}
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				e.printStackTrace();
				System.out.println("中文转换拼音错误!");
			}
		}
		return zhongWenPinYin;
	}

	/**
	 * Default Format 默认输出格式
	 * 
	 * @return
	 */
	public static HanyuPinyinOutputFormat getDefaultOutputFormat() {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 没有音调数字
		format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);// u显示

		return format;
	}

	/**
	 * Capitalize 首字母大写
	 * 
	 * @param s
	 * @return
	 */
	public static String capitalize(String s) {
		char ch[];
		ch = s.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		String newString = new String(ch);
		return newString;
	}
	
	/**
	 * add by kouyunhao 2014-09-10 冒泡排序算法 
	 * 该算法取list[i]（每个汉字首字母组合），进行排序。
	 * 在用到getPinyinCapitalize(String zhongwen)方法时由于涉及中文多音字，
	 * 可能对排序结果产生影响，即使在该方法中添加了排序。建议慎重使用（除非
	 * 参数list已经是已经过处理的英文字符串，
	 * 即未调用getPinyinCapitalize(String zhongwen)方法）。
	 * @param list
	 * @return
	 */
	public static List<String> sort(String[] list){
		for(int i = 0; i < list.length-1; i++){
			list = sort1(list);
		}
		return Arrays.asList(list);
	}
	
	public static String[] sort1(String[] list){
		for(int i = 0; i < list.length-1; i++){
			int flag = compare(list[i], list[i+1]);
			String temp = null;
			if(flag > 0){
				temp = list[i];
				list[i] = list[i+1];
				list[i+1] = temp;
			}
		}
		return list;
	}
	
	public static int compare(Object s1, Object s2) {
		int result = -2;
		char ch1[];
		char ch2[];
		ch1 = s1.toString().toCharArray();
		ch2 = s2.toString().toCharArray();
		int length = Math.min(ch1.length, ch2.length);
		if(ch1.length == ch2.length){
			result = getResult(ch1, ch2, length, result);
			if(result != 1 && result != -1){
				result = 0;
			}
		}else{
			result = getResult(ch1, ch2, length, result);
			if(result != 1 && result != -1){
				if(ch1.length > length){
					result = 1;
				}else{
					result = -1;
				}
			}
		}
		return result;
	}
	
	public static int getResult(char ch1[], char ch2[], int length, int result){
		for(int i = 0; i < length; i++){
			if(ch1[i] > ch2[i]){
				result = 1;
				break;
			}else if(ch1[i] < ch2[i]){
				result = -1;
				break;
			}else{
				continue;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(getPinYin("槐泗岗234"));
		System.out.println(getPinYin("槐泗岗admin234"));
		System.out.println(getPinyinCapitalize("长安区"));
		
		String[] list = new String[]{"zgrs","zgrs","zgr","zgrmjfj","zgrkxz","bjay","ypfz","ctds"};
		sort(list);
	}
}
