package com.xiangxun.epms.mobile.business.dao;

import java.util.List;

import com.xiangxun.epms.mobile.business.domain.SamplingLandReg;
import com.xiangxun.epms.mobile.business.domain.SamplingReg;
public interface SamplingLandRegMapper {
	
	List<SamplingLandReg> getAllSamplingLandReg();
	void insertSelective(SamplingLandReg reg);
	void updateByPrimaryKeySelective(SamplingLandReg reg);
	List<SamplingReg> selectByPrimaryKey(String id);
	List<SamplingLandReg> selectByMissionId(String missionId);
	SamplingLandReg  findByCode(String code);
}