package com.xiangxun.atms.module.analysis.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.analysis.dao.LandAnalysisMapper;
import com.xiangxun.atms.module.analysis.service.LandAnalysisService;
import com.xiangxun.atms.module.analysis.vo.LandAnalysis;
import com.xiangxun.atms.module.analysis.vo.LandAnalysisSearch;
import com.xiangxun.atms.module.statistics.service.LandService;

@Service
public class LandAnalysisServiceImpl extends AbstractBaseService<LandAnalysis, LandAnalysisSearch> implements LandAnalysisService {
    @Resource
    LandAnalysisMapper landAnalysisMapper;

    @Resource
    LandService landService;
    
    @Override
    public BaseMapper<LandAnalysis, LandAnalysisSearch> getBaseMapper() {
         return landAnalysisMapper;
    }
}