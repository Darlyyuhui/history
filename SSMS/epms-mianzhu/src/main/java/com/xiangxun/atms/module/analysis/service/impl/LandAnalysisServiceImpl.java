package com.xiangxun.atms.module.analysis.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.analysis.dao.LandAnalysisMapper;
import com.xiangxun.atms.module.analysis.service.LandAnalysisService;
import com.xiangxun.atms.module.analysis.vo.LandAnalysis;
import com.xiangxun.atms.module.analysis.vo.LandAnalysisSearch;
import com.xiangxun.atms.module.reg.service.LandRegService;
import com.xiangxun.atms.module.reg.vo.LandReg;

@Service
public class LandAnalysisServiceImpl extends AbstractBaseService<LandAnalysis, LandAnalysisSearch> implements LandAnalysisService {
    @Resource
    LandAnalysisMapper landAnalysisMapper;
    @Resource
    LandRegService landRegService;
    
    @Override
    public BaseMapper<LandAnalysis, LandAnalysisSearch> getBaseMapper() {
         return landAnalysisMapper;
    }
    
    @Transactional
	@Override
	public void deleteByRegId(String regId) {
    	LandAnalysisSearch search = new LandAnalysisSearch();
		search.createCriteria().andRegIdEqualTo(regId);
		this.deleteByExample(search);
	}

	@Override
	public int deleteById(String id) {
		LandAnalysis la = this.getById(id);
    	if (la != null) {
    		String regId = la.getRegId();
    		if (StringUtils.isNotEmpty(regId)) {
    			LandReg lr = landRegService.getById(regId);
        		//删除采样数据时，只删除同时导入采样登记数据
        		if (lr != null && "3".equals(lr.getSamplingSource())) {
        			landRegService.deleteById(regId);
        		}
    		}
    	}
		return super.deleteById(id);
	}
}