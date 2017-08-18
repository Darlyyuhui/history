package com.xiangxun.atms.module.bs.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.bs.vo.Region;
import com.xiangxun.atms.module.bs.vo.RegionSearch;

public interface TRegionMapper extends BaseMapper<Region, RegionSearch> {
	
	List<Map<String,Object>> getFullNameById(Map<String, Object> args);
}