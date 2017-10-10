package com.xiangxun.epms.mobile.business.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.xiangxun.epms.mobile.business.domain.SamplingReg;

public interface SamplingRegService {
	
	List<SamplingReg> insertSelective(HttpServletRequest request);
	List<SamplingReg> selectFindByMissionId(String missionId,String samplingCode,String code);
	/**
	 * 根据id,表名查询采样数据详情
	 * @param missionId
	 * @return
	 */
	SamplingReg selectFindById(String tableName,String id,String missionId );

}
