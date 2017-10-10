package com.xiangxun.epms.mobile.business.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.epms.mobile.business.domain.LandSamplingSchemePoint;

public interface LandSamplingSchemePointMapper {
	
	List<LandSamplingSchemePoint> getLandSamplingSchemePointByPlanId(Map<String,Object> args);
	void updateLandSamplingSchemePointById(LandSamplingSchemePoint landSamplingSchemePoint);
	void insertSelective(LandSamplingSchemePoint landSamplingSchemePoint);
	List<LandSamplingSchemePoint> getLandSamplingSchemePointById(String id);
	int contlist(String id);
	List<LandSamplingSchemePoint> findByPlanIdSamplin(String schemeId);
	LandSamplingSchemePoint findByCode(String code);
}
