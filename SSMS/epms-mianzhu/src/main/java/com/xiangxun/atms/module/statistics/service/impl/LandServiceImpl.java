package com.xiangxun.atms.module.statistics.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.statistics.dao.LandMapper;
import com.xiangxun.atms.module.statistics.service.LandService;
import com.xiangxun.atms.module.statistics.vo.LandLine;
import com.xiangxun.atms.module.statistics.vo.LandPie;

@Service
public class LandServiceImpl implements LandService {

	@Resource
	LandMapper landMapper;
	@Resource
    Cache cache;
	
	@Override
	public List<LandPie> getPieData(String regionId, String beginTime, String endTime) {
		//构建查询参数集合
		Map<String, Object> args = new HashMap<String, Object>();
		//设定时间默认值
		if (StringUtils.isEmpty(beginTime)) {
			beginTime = "1980-01-01 00:00:00";
		}
		args.put("beginTime", beginTime);
		if (StringUtils.isEmpty(endTime)) {
			endTime = "2100-12-31 23:59:59";
		}
		args.put("endTime", endTime);
		args.put("regionId", regionId);
		return landMapper.getPieData(args);
	}
	
	@Override
	public List<LandPie> getPieDataAll(String beginTime, String endTime) {
		//构建查询参数集合
		Map<String, Object> args = new HashMap<String, Object>();
		//设定时间默认值
		if (StringUtils.isEmpty(beginTime)) {
			beginTime = "1980-01-01 00:00:00";
		}
		args.put("beginTime", beginTime);
		if (StringUtils.isEmpty(endTime)) {
			endTime = "2100-12-31 23:59:59";
		}
		args.put("endTime", endTime);
		return landMapper.getPieDataAll(args);
	}

	@Override
	public List<LandLine> getLineData(String regionId, String beginTime, String endTime) {
		//构建查询参数集合
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("regionId", regionId);
		args.put("beginTime", beginTime);
		args.put("endTime", endTime);
		return landMapper.getLineData(args);
	}

}
