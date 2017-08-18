package com.xiangxun.atms.module.reg.dao;

import java.util.List;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.reg.vo.AirReg;
import com.xiangxun.atms.module.reg.vo.AirRegSearch;

public interface AirRegMapper extends BaseMapper<AirReg, AirRegSearch> {
	
	/**
	 * 获取采样分析登记可用的项
	 * @return
	 */
	List<AirReg> getInfoByAnalysis();
}