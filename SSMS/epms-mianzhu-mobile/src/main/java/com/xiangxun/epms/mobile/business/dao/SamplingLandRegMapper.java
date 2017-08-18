package com.xiangxun.epms.mobile.business.dao;

import java.util.List;

import com.xiangxun.epms.mobile.business.domain.SamplingLandReg;
public interface SamplingLandRegMapper {
	
	List<SamplingLandReg> getAllSamplingLandReg();
	void insertSelective(SamplingLandReg reg);
	void updateByPrimaryKeySelective(SamplingLandReg reg);
	List<SamplingLandReg> selectByPrimaryKey(String id);
	List<SamplingLandReg> selectByMissionId(String missionId);
}