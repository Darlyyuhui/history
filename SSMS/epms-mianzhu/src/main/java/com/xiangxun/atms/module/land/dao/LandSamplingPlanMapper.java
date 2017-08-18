package com.xiangxun.atms.module.land.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.land.vo.LandSamplingPlan;
import com.xiangxun.atms.module.land.vo.LandSamplingPlanSearch;

public interface LandSamplingPlanMapper extends BaseMapper<LandSamplingPlan, LandSamplingPlanSearch> {
	
	List<LandSamplingPlan> queryBySelectItem();
	
	/**
	 * 计划完成
	 * @param args
	 */
	void doFinish(Map<String, Object> args);
	
	Integer isDelete(String id);
}