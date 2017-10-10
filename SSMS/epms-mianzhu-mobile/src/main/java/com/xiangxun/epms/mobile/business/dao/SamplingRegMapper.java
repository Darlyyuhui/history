package com.xiangxun.epms.mobile.business.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.epms.mobile.business.domain.SamplingReg;

public interface SamplingRegMapper {
	/**
	 * 添加数据
	 * @param ags
	 */
	void insertSelective(Map<String,Object> ags);
	/**
	 * 根据任务id查询采样数据
	 * @param missionId
	 * @return
	 */
	List<SamplingReg> selectFindByMissionId(SamplingReg samplingReg);
	/**
	 * 根据id,表名查询采样数据详情
	 * @param missionId
	 * @return
	 */
	List<SamplingReg> selectFindById(Map<String,String> ags);

}
