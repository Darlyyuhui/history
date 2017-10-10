package com.xiangxun.atms.module.check.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.module.check.dao.DataCheckInfoMapper;
import com.xiangxun.atms.module.check.service.DataCheckInfoService;
import com.xiangxun.atms.module.check.vo.DataCheckInfo;
import com.xiangxun.atms.module.check.vo.DataCheckInfoSearch;
import com.xiangxun.atms.module.land.service.LandSamplingSchemeService;
import com.xiangxun.atms.module.land.vo.LandSamplingScheme;

@Service
public class DataCheckInfoServiceImpl extends AbstractBaseService<DataCheckInfo, DataCheckInfoSearch> implements DataCheckInfoService {
    @Resource
    DataCheckInfoMapper dataCheckInfoMapper;
    @Resource
    LandSamplingSchemeService schemeService;

    @Override
    public BaseMapper<DataCheckInfo, DataCheckInfoSearch> getBaseMapper() {
         return dataCheckInfoMapper;
    }

	@Override
	public void saveInfoByPlanId(String planId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("planId", planId);
		List<Map<String, Object>> list = dataCheckInfoMapper.getSchemeIdByPlanId(args);
		Object id = null;
		for (Map<String, Object> m : list) {
			id = m.get("ID");
			if (id == null || "".equals(id)) {
				continue;
			}
			this.saveInfoBySchemeId(id.toString());
		}
	}

	@Override
	public void saveInfoBySchemeId(String schemeId) {
		LandSamplingScheme scheme = schemeService.getById(schemeId);
		if (scheme == null) {
			scheme = new LandSamplingScheme();
		}
		String tableName = "LAND";
		if ("NTTR".equals(scheme.getSampleCode())) {
			tableName = "LAND";
		}
//		else if ("DQ".equals(scheme.getSampleCode())) {
//			tableName = "AIR";
//		}
//		else if ("BJTR".equals(scheme.getSampleCode())) {
//			tableName = "LAND";
//		}
//		else if ("FL".equals(scheme.getSampleCode())) {
//			tableName = "LAND";
//		}
//		else if ("SD".equals(scheme.getSampleCode())) {
//			tableName = "LAND";
//		}
//		else if ("DBS".equals(scheme.getSampleCode()) || "DXS".equals(scheme.getSampleCode()) || "DN".equals(scheme.getSampleCode())) {
//			tableName = "WATER";
//		}
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("schemeId", schemeId);
		args.put("tableName", tableName);
		List<Map<String, Object>> msgList = dataCheckInfoMapper.getMessage(args);
		
		DataCheckInfo info = new DataCheckInfo();
		info.setId(UuidGenerateUtil.getUUIDLong());
		info.setSchemeId(schemeId);
		info.setSchemeName(scheme.getName());
		info.setRegionId(scheme.getRegionId());
		info.setSampleType(scheme.getSampleCode());
		info.setSamplingDept(scheme.getDept());
		info.setAnalysisDept(msgList.get(0).get("RESULT").toString());
		info.setAnalysisCount(msgList.get(1).get("RESULT").toString());
		info.setTestItems(scheme.getTestItems());
		//未校验
		info.setStatus(0);
		
		try {
			this.save(info);
		} catch(Exception e) {
			logger.error("保存校验准备数据失败：" + e.getMessage());
		}
		
	}
}