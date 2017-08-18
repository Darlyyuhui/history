package com.xiangxun.atms.module.bs.service;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.bs.vo.LandType;
import com.xiangxun.atms.module.bs.vo.LandTypeSearch;

public interface LandTypeService extends BaseService<LandType, LandTypeSearch> {
	
	/**
	 * 生成树形数据
	 * @return
	 */
	String makeTreeData();
	
	/**
	 * 刷新缓存
	 */
	void refreshCache();
}