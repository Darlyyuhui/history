package com.xiangxun.atms.module.bs.service;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.bs.vo.RepairStage;
import com.xiangxun.atms.module.bs.vo.RepairStageSearch;

public interface RepairStageService extends BaseService<RepairStage, RepairStageSearch> {
	
	/**
	 * 生成树形数据
	 * @return
	 */
	String makeTreeData();
	
	/**
	 * 刷新缓存
	 */
	void refreshCache();
}