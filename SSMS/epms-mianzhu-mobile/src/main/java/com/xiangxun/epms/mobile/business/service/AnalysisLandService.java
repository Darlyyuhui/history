package com.xiangxun.epms.mobile.business.service;

import java.util.List;

import com.xiangxun.epms.mobile.business.domain.AnalysisLand;
import com.xiangxun.epms.mobile.business.util.Page;

public interface AnalysisLandService {
	/**
	 * 获取指标数据
	 */
	Page findByConttion(AnalysisLand analysisLand ,int pageSize, int pageNo);
	List< AnalysisLand> selectbyContion(String regionName,int pageSize, int pageNo);
	/**
	 * 根据行政区域id获取指标信息
	 */
	List< AnalysisLand> getListByCondition(String regionId);
	/**
	 * 根据其他条件查询指标数据
	 */
	Page selectByCondition(AnalysisLand analysisLand,String temple,int pageSize, int pageNo);
}
