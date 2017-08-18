package com.xiangxun.epms.mobile.business.dao;

import java.util.List;

import com.xiangxun.epms.mobile.business.domain.AnalysisLand;

public interface AnalysisLandMapper {
	
	List<AnalysisLand> findByConttion(AnalysisLand analysisLand);
	int countList(AnalysisLand analysisLand);
	List<AnalysisLand> getListByCondition(String regionId);
	List<AnalysisLand> selectByCondition(AnalysisLand arg);
}