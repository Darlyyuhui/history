package com.xiangxun.atms.module.analysis.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.analysis.dao.AirAnalysisMapper;
import com.xiangxun.atms.module.analysis.service.AirAnalysisService;
import com.xiangxun.atms.module.analysis.vo.AirAnalysis;
import com.xiangxun.atms.module.analysis.vo.AirAnalysisSearch;

@Service
public class AirAnalysisServiceImpl extends AbstractBaseService<AirAnalysis, AirAnalysisSearch> implements AirAnalysisService {
    @Resource
	AirAnalysisMapper airAnalysisMapper;

    @Override
    public BaseMapper<AirAnalysis, AirAnalysisSearch> getBaseMapper() {
         return airAnalysisMapper;
    }
}