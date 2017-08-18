package com.xiangxun.atms.module.land.dao;

import java.util.List;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.land.vo.LandBlock;
import com.xiangxun.atms.module.land.vo.LandBlockSearch;

public interface LandBlockMapper extends BaseMapper<LandBlock, LandBlockSearch> {
	
	List<LandBlock> queryBySelectItem();
}