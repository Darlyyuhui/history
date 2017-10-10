package com.xiangxun.atms.module.statistics.service;

import java.util.List;

import com.xiangxun.atms.module.statistics.vo.RepairInfo;

public interface RepairService {

	/**
	 * 获取修复进度字典配置（除最终阶段）
	 * @return
	 */
	List<String[]> getDicBySchedule();
	
	/**
	 * 查询数据
	 * @param regionId
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	List<RepairInfo> getList(String regionId, String beginTime, String endTime);
	
}
