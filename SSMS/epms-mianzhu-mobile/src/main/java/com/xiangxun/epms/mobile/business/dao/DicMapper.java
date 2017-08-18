package com.xiangxun.epms.mobile.business.dao;

import java.util.List;
import java.util.Map;

public interface DicMapper {

	/**
	 * 缓存所有数据字典信息
	 * @return
	 */
	List<Map<String, Object>> getAllDic();
}
