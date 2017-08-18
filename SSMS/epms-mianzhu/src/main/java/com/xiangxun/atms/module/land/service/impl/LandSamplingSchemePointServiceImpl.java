package com.xiangxun.atms.module.land.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.land.dao.LandSamplingSchemePointMapper;
import com.xiangxun.atms.module.land.service.LandSamplingSchemePointService;
import com.xiangxun.atms.module.land.vo.LandSamplingSchemePoint;
import com.xiangxun.atms.module.land.vo.LandSamplingSchemePointSearch;

@Service
public class LandSamplingSchemePointServiceImpl extends AbstractBaseService<LandSamplingSchemePoint, LandSamplingSchemePointSearch> implements LandSamplingSchemePointService {
    @Resource
    private LandSamplingSchemePointMapper landSamplingSchemePointMapper;

    @Override
    public BaseMapper<LandSamplingSchemePoint, LandSamplingSchemePointSearch> getBaseMapper() {
         return landSamplingSchemePointMapper;
    }

	@Override
	public void releaseById(String id, int checkStatus) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("id", id);
		args.put("checkStatus", checkStatus);
		landSamplingSchemePointMapper.releaseById(args);
	}

	@Override
	public void checkById(String id, int checkStatus) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("id", id);
		args.put("checkStatus", checkStatus);
		landSamplingSchemePointMapper.checkById(args);
	}

	@Override
	public List<LandSamplingSchemePoint> queryMissionSelectPoints(String sampleCode) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("sampleCode", sampleCode);
		return landSamplingSchemePointMapper.queryMissionSelectPoints(args);
	}

	@Override
	public List<LandSamplingSchemePoint> queryMissionPoints(String missionId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("missionId", missionId);
		return landSamplingSchemePointMapper.queryPointsByMissionId(args);
	}

	@Override
	public void deleteBySchemeId(String schemeId) {
		LandSamplingSchemePointSearch search = new LandSamplingSchemePointSearch();
		search.createCriteria().andSchemeIdEqualTo(schemeId);
		this.deleteByExample(search);
	}
    
}