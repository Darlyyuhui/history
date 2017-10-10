package com.xiangxun.epms.mobile.business.dao;



import java.util.List;

import com.xiangxun.epms.mobile.business.domain.SamplingReg;


public interface SamplingAirRegMapper {
	
	List<SamplingReg> findById(String id);
	//根据任务id获取任务下所有现场采样已经选择的大气点位的id
	List<String>findPointIdByMissionId(String missionId);
}