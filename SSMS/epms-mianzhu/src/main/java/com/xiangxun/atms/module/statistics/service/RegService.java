package com.xiangxun.atms.module.statistics.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.common.dictionary.vo.Dic;

public interface RegService {

	List<Map<String, Object>> getList(String regionId, String beginTime, String endTime, Map<String, List<Dic>> dicmap);
	
	/**
	 * 获取样品类型数据字典 
	 * @return
	 */
	Map<String, List<Dic>> getDicMap();
	
}
