package com.xiangxun.atms.module.land.service;

import java.util.List;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.land.vo.LandSamplingPlan;
import com.xiangxun.atms.module.land.vo.LandSamplingPlanSearch;

public interface LandSamplingPlanService extends BaseService<LandSamplingPlan, LandSamplingPlanSearch> {
	
	List<LandSamplingPlan> queryBySelectItem();
	
	/**
	 * 完成采样计划
	 * @param planId
	 */
	void doFinish(String planId);
	
	/**
	 * 判断是否可删除
	 * @param id
	 * @return
	 */
	boolean isDelete(String id);
}