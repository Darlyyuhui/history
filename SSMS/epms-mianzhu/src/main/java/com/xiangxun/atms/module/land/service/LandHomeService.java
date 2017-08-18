package com.xiangxun.atms.module.land.service;

import java.util.List;
import java.util.Map;

public interface LandHomeService {

	/**
	 * 获取顶部地块统计信息
	 * @return
	 */
	Map<String, Object> getTopCount();
	
	/**
	 * 获取新登记的地块信息
	 * @param pageSize 记录数
	 * @return
	 */
	List<Map<String, Object>> getNewRegLandInfo(int pageSize);
	/**
	 * 获取正在执行的采样计划
	 * @param args
	 * @return
	 */
	List<Map<String, Object>> getSamplingPlanInfo(int pageSize);
	
	
	/**
	 * 获取当前采样情况统计图表options
	 * @return
	 */
	String getReggingCountCharts();
	/**
	 * 土壤类型面积统计图表options
	 * @return
	 */
	String getLandTypeAreaCountCharts();
	
	/**
	 * 土壤修复进度跟踪
	 * @param args
	 * @return
	 */
	List<Map<String, Object>> getLandRepair(int pageSize);
}
