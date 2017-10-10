package com.xiangxun.atms.module.check.service;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.check.vo.DataCheckInfo;
import com.xiangxun.atms.module.check.vo.DataCheckInfoSearch;

public interface DataCheckInfoService extends BaseService<DataCheckInfo, DataCheckInfoSearch> {
	
	void saveInfoBySchemeId(String schemeId);
	
	void saveInfoByPlanId(String planId);
	
}