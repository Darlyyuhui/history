package com.xiangxun.atms.module.bs.service;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.bs.vo.Region;
import com.xiangxun.atms.module.bs.vo.RegionSearch;

public interface TRegionService extends BaseService<Region, RegionSearch> {
	
	/**
	 * 生成树形数据
	 * @return
	 */
	String makeTreeData();
	
	/**
	 * 刷新缓存
	 */
	void refreshCache();
	
	/**
	 * 获取区域全名
	 * @param id
	 * @return
	 */
	String getFullNameById(String id);
}