package com.xiangxun.epms.mobile.business.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.epms.mobile.business.domain.LandSamplingSheme;
import com.xiangxun.epms.mobile.business.util.Page;

public interface LandSamplingShemeService {
	List<LandSamplingSheme> findAll();
	//public Page queryList(LandSamplingSheme landSamplingSheme, int pageSize, int pageNo );
	List<LandSamplingSheme> findAllByFinsh(Map<String, Object> args);
	LandSamplingSheme findById(String id);
	/**
	 * 查询未完成的方案，下面的点位，以及完成的采样，大气特殊一点
	 * @return
	 */
	List<Map<String,Object>>  findAllByFinshAndRegType();
	//历史采样
	Page historysheme(LandSamplingSheme landSamplingSheme,int pageSize, int pageNo);
	
	String getIdbyMissionId(String missionId);
}
