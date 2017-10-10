package com.xiangxun.atms.module.analysis.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.analysis.dao.AirAnalysisMapper;
import com.xiangxun.atms.module.analysis.service.AirAnalysisService;
import com.xiangxun.atms.module.analysis.vo.AirAnalysis;
import com.xiangxun.atms.module.analysis.vo.AirAnalysisSearch;
import com.xiangxun.atms.module.reg.service.AirRegService;
import com.xiangxun.atms.module.reg.vo.AirReg;

@Service
public class AirAnalysisServiceImpl extends AbstractBaseService<AirAnalysis, AirAnalysisSearch> implements AirAnalysisService {
    @Resource
	AirAnalysisMapper airAnalysisMapper;
    @Resource
    AirRegService airRegService;

    @Override
    public BaseMapper<AirAnalysis, AirAnalysisSearch> getBaseMapper() {
         return airAnalysisMapper;
    }

    @Transactional
	@Override
	public void deleteByRegId(String regId) {
		AirAnalysisSearch search = new AirAnalysisSearch();
		search.createCriteria().andRegIdEqualTo(regId);
		this.deleteByExample(search);
	}

    @Transactional
	@Override
	public int deleteById(String id) {
    	AirAnalysis air = this.getById(id);
    	if (air != null) {
    		String regId = air.getRegId();
    		if (StringUtils.isNotEmpty(regId)) {
    			AirReg ar = airRegService.getById(regId);
        		//删除采样数据时，只删除同时导入采样登记数据
        		if (ar != null && "3".equals(ar.getSamplingSource())) {
        			airRegService.deleteById(regId);
        		}
    		}
    	}
		return super.deleteById(id);
	}
    
}