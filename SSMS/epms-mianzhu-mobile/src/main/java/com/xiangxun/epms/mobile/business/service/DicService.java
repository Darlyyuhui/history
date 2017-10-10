package com.xiangxun.epms.mobile.business.service;

import java.util.List;

import com.xiangxun.epms.mobile.business.domain.Dic;

public interface DicService {

	/**
	 * 缓存所有数据字典项
	 */
	void initDic();
	/**
	 * 根据样品名称获取水采样code
	 */
	String getWater(String sampleName);
	/**
	 * 根据采样登记类型获取code和样品类型
	 */
	List<Dic> getDicList(String type);
	List<String> typeList();
	void simplyType();
}
