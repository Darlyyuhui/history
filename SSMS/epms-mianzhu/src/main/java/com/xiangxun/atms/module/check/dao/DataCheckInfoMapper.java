package com.xiangxun.atms.module.check.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.check.vo.DataCheckInfo;
import com.xiangxun.atms.module.check.vo.DataCheckInfoSearch;

public interface DataCheckInfoMapper extends BaseMapper<DataCheckInfo, DataCheckInfoSearch> {
	
	/**
	 * 获取方案下分析数据信息
	 * @param args
	 * @return
	 */
	List<Map<String, Object>> getMessage(Map<String, Object> args);
	
	/**
	 * 获取采样计划下所有方案
	 * @param args
	 * @return
	 */
	List<Map<String, Object>> getSchemeIdByPlanId(Map<String, Object> args);
}