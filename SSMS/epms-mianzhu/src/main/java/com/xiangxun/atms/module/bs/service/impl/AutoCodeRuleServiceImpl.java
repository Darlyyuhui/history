package com.xiangxun.atms.module.bs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.bs.dao.AutoCodeRuleMapper;
import com.xiangxun.atms.module.bs.service.AutoCodeRuleService;
import com.xiangxun.atms.module.bs.vo.AutoCodeRule;
import com.xiangxun.atms.module.bs.vo.AutoCodeRuleSearch;

@Service
public class AutoCodeRuleServiceImpl extends AbstractBaseService<AutoCodeRule, AutoCodeRuleSearch> implements AutoCodeRuleService {
    @Resource
    AutoCodeRuleMapper autoCodeRuleMapper;

    @Override
    public BaseMapper<AutoCodeRule, AutoCodeRuleSearch> getBaseMapper() {
         return autoCodeRuleMapper;
    }
}