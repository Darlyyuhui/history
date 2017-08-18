package com.xiangxun.atms.module.map.service;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Table;
import com.xiangxun.atms.module.reg.vo.AirReg;
import com.xiangxun.atms.module.reg.vo.LandReg;

public interface MapDataService {
	
	/**
	 * 获取土壤统计数据
	 * @param regionId
	 * @param beginTime
	 * @param endTime
	 * @return c=类型名称  row=ph、镉、有效态镉
	 */
	Table<String, String, Float> getDataByMap(String regionId, String beginTime, String endTime);
	
	/**
	 * 获取所有采样登记数
	 * @param regionId
	 * @param beginTime
	 * @param endTime
	 * @return key=登记类型 value=值
	 */
	Map<String, Integer> getRegTypeDataByMap(String regionId, String beginTime, String endTime);
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