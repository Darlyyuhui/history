package com.xiangxun.epms.mobile.business.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.epms.mobile.business.domain.LandSamplingSheme;

public interface LandSamplingShemeMapper {
	List<LandSamplingSheme> findAll();
	List<LandSamplingSheme> findAllByFinsh(Map<String,Object> arg);
	
	LandSamplingSheme findById(String id);
	
	List<LandSamplingSheme> findAllByFinshAndRegType();
	//历史采样
	List<LandSamplingSheme> historysheme(LandSamplingSheme landSamplingSheme);
	//通过missionId获取方案id
	String getIdbyMissionId(String missionId);
	
}