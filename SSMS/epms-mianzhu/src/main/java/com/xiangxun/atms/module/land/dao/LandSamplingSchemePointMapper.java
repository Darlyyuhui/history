package com.xiangxun.atms.module.land.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.land.vo.LandSamplingSchemePoint;
import com.xiangxun.atms.module.land.vo.LandSamplingSchemePointSearch;

public interface LandSamplingSchemePointMapper extends BaseMapper<LandSamplingSchemePoint, LandSamplingSchemePointSearch> {
	
	void releaseById(Map<String, Object> args);
	
	void checkById(Map<String, Object> args);
	
	/**
	 * 获取外业任务可选择的采样点
	 * @param sampleCode
	 * @return
	 */
	List<LandSamplingSchemePoint> queryMissionSelectPoints(Map<String, Object> args);
	
	/**
	 * 获取外业任务的采样点
	 * @param missionId
	 * @return
	 */
	List<LandSamplingSchemePoint> queryPointsByMissionId(Map<String, Object> args);
}