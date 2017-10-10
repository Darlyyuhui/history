package com.xiangxun.atms.module.check.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.module.check.vo.LandAnalysis;

public interface LandCheckMapper {

	/**
	 * 获取土壤标准差
	 * @param args
	 * @return
	 */
	LandAnalysis getLandStdevAndAvg(Map<String, Object> args);
	
	/**
	 * 获取方案的土壤数据
	 * @param args
	 * @return
	 */
	List<LandAnalysis> getLandDataBySchemeId(Map<String, Object> args);
}
