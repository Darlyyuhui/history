package com.xiangxun.atms.module.land.service;

import java.util.List;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.land.vo.LandBlock;
import com.xiangxun.atms.module.land.vo.LandBlockSearch;

public interface LandBlockService extends BaseService<LandBlock, LandBlockSearch> {
	
	List<LandBlock> queryBySelectItem();
	
	/**
	 * 刷新缓存
	 */
	void refreshCache();
}