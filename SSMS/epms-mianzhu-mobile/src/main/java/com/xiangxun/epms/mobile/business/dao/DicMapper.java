package com.xiangxun.epms.mobile.business.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.epms.mobile.business.domain.Dic;

public interface DicMapper {

	/**
	 * 缓存所有数据字典信息
	 * @return
	 */
	List<Map<String, Object>> getAllDic();
	/**
	 * 根据样品名称获取水采样code
	 */
	String getWater(String sampleName);
	/**
	 * 根据采样登记类型获取code和样品类型
	 */
	List<Dic> getDicList(String type);
	
	List<String> typeList();
}
