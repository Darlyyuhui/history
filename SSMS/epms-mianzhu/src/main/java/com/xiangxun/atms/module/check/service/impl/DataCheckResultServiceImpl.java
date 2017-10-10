package com.xiangxun.atms.module.check.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Table;
import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.check.cache.DataCheckRuleCache;
import com.xiangxun.atms.module.check.dao.DataCheckResultMapper;
import com.xiangxun.atms.module.check.service.DataCheckInfoService;
import com.xiangxun.atms.module.check.service.DataCheckResultService;
import com.xiangxun.atms.module.check.service.LandCheckService;
import com.xiangxun.atms.module.check.vo.DataCheckInfo;
import com.xiangxun.atms.module.check.vo.DataCheckResult;
import com.xiangxun.atms.module.check.vo.DataCheckResultSearch;
import com.xiangxun.atms.module.check.vo.DataCheckRule;

@Service
public class DataCheckResultServiceImpl extends AbstractBaseService<DataCheckResult, DataCheckResultSearch> implements DataCheckResultService {
    @Resource
    DataCheckResultMapper dataCheckResultMapper;
    @Resource
    DataCheckInfoService dataCheckInfoService;
    @Resource
    Cache cache;
    
    @Resource
    LandCheckService landCheckService;

    @Override
    public BaseMapper<DataCheckResult, DataCheckResultSearch> getBaseMapper() {
         return dataCheckResultMapper;
    }

	@Override
	public void doCheck(String infoId) {
		@SuppressWarnings("unchecked")
		Table<String, String, List<DataCheckRule>> table = (Table<String, String, List<DataCheckRule>>)cache.get(DataCheckRuleCache.TYPE_ITEMS);
		//获取分析校验规则集合
		Map<String, List<DataCheckRule>> cacheMap = table.column("01");
		
		//获取校验信息
		DataCheckInfo info = dataCheckInfoService.getById(infoId);
		if (info == null || info.getStatus() > 0) {
			return;
		}
		
		List<DataCheckResult> list = new ArrayList<DataCheckResult>();
		if ("NTTR".equals(info.getSampleType())) {
			list.addAll(landCheckService.doLandCheck(cacheMap, info));
		}
		
		for (DataCheckResult dcr : list) {
			this.save(dcr);
		}
		DataCheckInfo updateInfo = new DataCheckInfo();
		updateInfo.setId(infoId);
		updateInfo.setStatus(list.size() == 0 ? 1 : 2);
		updateInfo.setCheckTime(new Date());
		dataCheckInfoService.updateByIdSelective(updateInfo);
	}
	
}