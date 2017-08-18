package com.xiangxun.atms.module.land.dao;

import java.util.List;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.land.vo.LandSamplingScheme;
import com.xiangxun.atms.module.land.vo.LandSamplingSchemeSearch;

public interface LandSamplingSchemeMapper extends BaseMapper<LandSamplingScheme, LandSamplingSchemeSearch> {
	
	/**
	 * 查询可选择的方案列表
	 * @return
	 */
	List<LandSamplingScheme> queryMissionSelectSchemes();
	
	Integer isDelete(String id);
	
}