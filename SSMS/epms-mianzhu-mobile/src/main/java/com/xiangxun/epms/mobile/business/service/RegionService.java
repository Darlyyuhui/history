package com.xiangxun.epms.mobile.business.service;

import java.util.List;

import com.xiangxun.epms.mobile.business.domain.Region;

public interface RegionService {
	/**
	 * 获取所有行政区域
	 */
	List<Region> getAllRegion();
	/**
	 * 获取所有行政区域
	 */
	List<Region> getAllRegionById(String id);
	/**
	 * 	根据地区名称模糊查询信息
	 */
	List<Region> getAllRegionByName(String name);
	
	/**
	 * 缓存新行政区域信息
	 */
	void initRegion();
	String getFullNameById(String id);
		
}
