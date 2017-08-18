package com.xiangxun.atms.module.land.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.land.vo.LandMission;
import com.xiangxun.atms.module.land.vo.LandMissionSearch;

public interface LandMissionMapper extends BaseMapper<LandMission, LandMissionSearch> {
	
	/**
	 * 获取采样登记时可选择的任务
	 * @return
	 */
	List<LandMission> queryRegSelect(Map<String, Object> args);
	
	/**
	 * 获取采样任务信息
	 * @param args
	 * @return
	 */
	LandMission getMissionById(Map<String, Object> args);
	
	/**
	 * 判断是否可删除
	 * @param id
	 * @return
	 */
	Integer isDelete(Map<String, Object> args);
	
}