package com.xiangxun.atms.module.check.service;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.check.vo.DataCheckRule;
import com.xiangxun.atms.module.check.vo.DataCheckRuleSearch;

public interface DataCheckRuleService extends BaseService<DataCheckRule, DataCheckRuleSearch> {
	
	/**
	 * 判断校验规则唯一
	 * @param type
	 * @param objCode
	 * @param dimension
	 * @return
	 */
	boolean checkOnly(String type, String objCode, String dimension);
	
	/**
	 * 刷新缓存
	 */
	void refreshCache();
	
}