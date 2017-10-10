package com.xiangxun.atms.module.analysis.service;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.analysis.vo.AirAnalysis;
import com.xiangxun.atms.module.analysis.vo.AirAnalysisSearch;

public interface AirAnalysisService extends BaseService<AirAnalysis, AirAnalysisSearch> {
	
	/**
	 * 根据采样登记信息删除分析数据
	 * @param regId
	 */
	void deleteByRegId(String regId);
}