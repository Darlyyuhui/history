package com.xiangxun.atms.module.land.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.land.dao.LandSamplingPlanMapper;
import com.xiangxun.atms.module.land.service.LandSamplingPlanService;
import com.xiangxun.atms.module.land.vo.LandSamplingPlan;
import com.xiangxun.atms.module.land.vo.LandSamplingPlanSearch;

@Service
public class LandSamplingPlanServiceImpl extends AbstractBaseService<LandSamplingPlan, LandSamplingPlanSearch> implements LandSamplingPlanService {
    @Resource
    private LandSamplingPlanMapper landSamplingPlanMapper;

    @Override
    public BaseMapper<LandSamplingPlan, LandSamplingPlanSearch> getBaseMapper() {
         return landSamplingPlanMapper;
    }

	@Override
	public List<LandSamplingPlan> queryBySelectItem() {
		return landSamplingPlanMapper.queryBySelectItem();
	}

	@Override
	public void doFinish(String planId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("planId", planId);
		landSamplingPlanMapper.doFinish(args);
	}

	@Override
	public boolean isDelete(String id) {
		Integer num = landSamplingPlanMapper.isDelete(id);
		return num==null||num==0?true:false;
	}
}