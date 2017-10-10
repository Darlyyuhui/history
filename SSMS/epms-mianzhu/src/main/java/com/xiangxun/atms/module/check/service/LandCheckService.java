package com.xiangxun.atms.module.check.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.module.check.vo.DataCheckInfo;
import com.xiangxun.atms.module.check.vo.DataCheckResult;
import com.xiangxun.atms.module.check.vo.DataCheckRule;

public interface LandCheckService {

	/**
	 * 土壤分析数据校验
	 * @param cacheMap
	 * @param info
	 * @return
	 */
	List<DataCheckResult> doLandCheck(Map<String, List<DataCheckRule>> cacheMap, DataCheckInfo info);
}
