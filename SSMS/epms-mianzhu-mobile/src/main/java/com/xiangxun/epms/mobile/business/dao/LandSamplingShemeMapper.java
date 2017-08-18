package com.xiangxun.epms.mobile.business.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.epms.mobile.business.domain.LandSamplingSheme;

public interface LandSamplingShemeMapper {
	List<LandSamplingSheme> findAll();
	List<LandSamplingSheme> findAllByFinsh(Map<String,Object> arg);
	int countlist(Map<String,Object> args);
	LandSamplingSheme findById(String id);
	List<LandSamplingSheme> findAllBySelective(LandSamplingSheme landSamplingSheme) ;
	
}