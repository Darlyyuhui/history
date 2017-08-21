package com.xiangxun.atms.framework.util;

import java.util.Comparator;

import com.xiangxun.atms.common.dictionary.vo.Dic;

/***
 * 数据字典自动排序 工具类
 * @author yantao
 */
@SuppressWarnings("unchecked")
public class DicComparator implements Comparator {

	@Override
	public int compare(Object arg0, Object arg1) {
		Dic dic0 = (Dic)arg0;
		Dic dic1 = (Dic)arg1;
		int flag = dic0.getCode().compareTo(dic1.getCode());
		return flag;
	}

}
