package com.xiangxun.atms.module.analysis.service;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.analysis.vo.LandAnalysis;
import com.xiangxun.atms.module.analysis.vo.LandAnalysisSearch;

public interface LandAnalysisService extends BaseService<LandAnalysis, LandAnalysisSearch> {
	
	/**
	 * 根据采样登记信息删除分析数据
	 * @param regId
	 */
	void deleteByRegId(String regId);
}