package com.xiangxun.atms.module.land.service;

import java.util.List;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.land.vo.LandMission;
import com.xiangxun.atms.module.land.vo.LandMissionSearch;

public interface LandMissionService extends BaseService<LandMission, LandMissionSearch> {
	
	/**
	 * 获取采样登记时可选择的任务
	 * @param sampleCode
	 * @return
	 */
	List<LandMission> queryRegSelect(String sampleCode);
	
	/**
	 * 获取采样任务信息
	 * @param args
	 * @return
	 */
	LandMission getMissionById(String id);
	
	/**
	 * 刷新缓存
	 */
	void refreshCache();
	
	/**
	 * 判断是否可删除
	 * @param id
	 * @return
	 */
	boolean isDelete(String id);
}