package com.xiangxun.atms.module.eventalarm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderRule;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderRuleSearch;
import com.xiangxun.atms.module.eventalarm.mapper.WorkorderRuleMapper;
import com.xiangxun.atms.module.eventalarm.service.WorkorderRuleService;

/**
 * 运维评估规则业务逻辑接口实现类
 * @author kouyunhao
 *
 */
@Service("workorderRuleService")
public class WorkorderRuleServiceImpl extends AbstractBaseService<WorkorderRule, WorkorderRuleSearch> implements WorkorderRuleService {

	@Resource
	WorkorderRuleMapper workorderRuleMapper;
	
	@Override
	protected BaseMapper<WorkorderRule, WorkorderRuleSearch> getBaseMapper() {
		return workorderRuleMapper;
	}

	@Override
	public List<WorkorderRule> findAll() {
		WorkorderRuleSearch example = new WorkorderRuleSearch();
		example.createCriteria();
		return workorderRuleMapper.selectByExample(example);
	}

	@Override
	public List<WorkorderRule> findByCode(String code) {
		WorkorderRuleSearch example = new WorkorderRuleSearch();
		example.createCriteria().andCodeEqualTo(code);
		return workorderRuleMapper.selectByExample(example);
	}

	@Override
	public List<WorkorderRule> findByName(String name) {
		WorkorderRuleSearch example = new WorkorderRuleSearch();
		example.createCriteria().andNameEqualTo(name);
		return workorderRuleMapper.selectByExample(example);
	}

}
