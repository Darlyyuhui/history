package com.xiangxun.atms.module.check.service;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.check.vo.DataCheckResult;
import com.xiangxun.atms.module.check.vo.DataCheckResultSearch;

public interface DataCheckResultService extends BaseService<DataCheckResult, DataCheckResultSearch> {
	
	void doCheck(String infoId);
}