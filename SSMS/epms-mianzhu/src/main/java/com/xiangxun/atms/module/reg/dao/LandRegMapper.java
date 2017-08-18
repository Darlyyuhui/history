package com.xiangxun.atms.module.reg.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.reg.vo.LandReg;
import com.xiangxun.atms.module.reg.vo.LandRegSearch;

public interface LandRegMapper extends BaseMapper<LandReg, LandRegSearch> {
	
	List<Map<String, Object>> getLandBlocksByRegId(Map<String, Object> args);
	
	void deleteLandBlocksByRegId(Map<String, Object> args);
	
	void saveLandBlocksLink(Map<String, Object> args);
	
	/**
	 * 获取采样分析登记可用的项
	 * @return
	 */
	List<LandReg> getInfoByAnalysis();
	
}