package com.xiangxun.atms.module.eventalarm.mapper;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderAppraise;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderAppraiseSearch;

public interface WorkorderAppraiseMapper extends BaseMapper<WorkorderAppraise, WorkorderAppraiseSearch> {
	
	int findAvgFinalscore(String companyid);
}