package com.xiangxun.epms.mobile.business.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.epms.mobile.business.domain.Region;
public interface RegionMapper {
	List<Region> getAllRegion();
	List<Region> getAllRegionById(String id);
	List<Region> getAllRegionByName(String name);
	List<Map<String,Object>> getFullNameById(Map<String, Object> args);
}
