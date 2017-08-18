package com.xiangxun.atms.module.land.service;

import java.util.List;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.land.vo.LandSamplingSchemePoint;
import com.xiangxun.atms.module.land.vo.LandSamplingSchemePointSearch;

public interface LandSamplingSchemePointService extends BaseService<LandSamplingSchemePoint, LandSamplingSchemePointSearch> {
	
	/**
	 * 发布采样点
	 * @param id 点位ID
	 * @param checkStatus 核查状态值
	 */
	void releaseById(String id, int checkStatus);
	/**
	 * 核查点位
	 * @param id
	 * @param checkStatus
	 */
	void checkById(String id, int checkStatus);
	
	/**
	 * 获取外业任务可选择的采样点
	 * @param sampleCode
	 * @return
	 */
	List<LandSamplingSchemePoint> queryMissionSelectPoints(String sampleCode);
	
	/**
	 * 获取外业任务的采样点
	 * @param missionId
	 * @return
	 */
	List<LandSamplingSchemePoint> queryMissionPoints(String missionId);
	
	/**
	 * 删除方案下的点位
	 * @param schemeId
	 */
	void deleteBySchemeId(String schemeId);
}