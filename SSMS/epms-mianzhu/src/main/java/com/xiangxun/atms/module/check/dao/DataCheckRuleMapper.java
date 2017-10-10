package com.xiangxun.atms.module.check.dao;

import java.util.Map;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.check.vo.DataCheckRule;
import com.xiangxun.atms.module.check.vo.DataCheckRuleSearch;

public interface DataCheckRuleMapper extends BaseMapper<DataCheckRule, DataCheckRuleSearch> {
	
	Integer checkOnly(Map<String, Object> args);
}