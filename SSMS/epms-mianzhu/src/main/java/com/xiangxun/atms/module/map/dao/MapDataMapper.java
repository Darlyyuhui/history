package com.xiangxun.atms.module.map.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.module.map.vo.LandCount;
import com.xiangxun.atms.module.reg.vo.AirReg;
import com.xiangxun.atms.module.reg.vo.LandReg;

public interface MapDataMapper {
	
	/**
	 * 获取土壤统计数据
	 * @param args
	 * @return
	 */
	List<LandCount> getLandCount(Map<String, Object> args);
	
	/**
	 * 获取所有采样登记数
	 * @param args
	 * @return
	 */
	List<Map<String, Object>> getRegTypeCount(Map<String, Object> args);
	
	/**
	 * 获取所有农田土壤采样分析点位
	 * @return
	 */
	List<LandReg> getLandReg();
	/**
	 * 获取所有空气采样分析点位
	 * @return
	 */
	List<AirReg> getAirReg();
}