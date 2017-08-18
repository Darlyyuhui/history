package com.xiangxun.atms.module.land.dao;

import java.util.List;
import java.util.Map;

public interface LandHomeMapper {

	/**
	 * 获取顶部地块统计
	 * @return
	 */
	List<Map<String, Object>> getTopLandBlockCount();
	/**
	 * 获取顶部采样统计
	 * @return
	 */
	List<Map<String, Object>> getTopSampleCount();
	/**
	 * 获取土壤修复项目统计
	 * @return
	 */
	List<Map<String, Object>> getTopRepairCount();
	
	
	/**
	 * 获取新登记的地块信息
	 * @param args
	 * @return
	 */
	List<Map<String, Object>> getNewRegLandInfo(Map<String, Object> args);
	/**
	 * 获取正在执行的采样计划
	 * @param args
	 * @return
	 */
	List<Map<String, Object>> getSamplingPlanInfo(Map<String, Object> args);
	
	
	
	/**
	 * 获取当前采样情况统计
	 * @return
	 */
	List<Map<String, Object>> getReggingCount();
	
	/**
	 * 土壤类型面积统计
	 * @return
	 */
	List<Map<String, Object>> getLandTypeAreaCount();
	
	
	
	/**
	 * 土壤修复进度跟踪
	 * @param args
	 * @return
	 */
	List<Map<String, Object>> getLandRepair(Map<String, Object> args);
	
}
