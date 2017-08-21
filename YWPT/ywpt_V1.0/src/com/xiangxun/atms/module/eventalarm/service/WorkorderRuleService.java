package com.xiangxun.atms.module.eventalarm.service;

import java.util.List;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderRule;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderRuleSearch;

/**
 * 运维评估规则业务逻辑接口
 * @author kouyunhao
 *
 */
public interface WorkorderRuleService extends BaseService<WorkorderRule, WorkorderRuleSearch> {
	
	List<WorkorderRule> findAll();
	
	List<WorkorderRule> findByCode(String code);
	
	List<WorkorderRule> findByName(String name);
	
}
