package com.xiangxun.atms.module.map.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.map.dao.MapDataMapper;
import com.xiangxun.atms.module.map.service.MapDataService;
import com.xiangxun.atms.module.map.vo.LandCount;
import com.xiangxun.atms.module.reg.vo.AirReg;
import com.xiangxun.atms.module.reg.vo.LandReg;
import com.xiangxun.atms.module.statistics.service.LandService;
import com.xiangxun.atms.module.statistics.vo.LandPie;

@Service
public class MapDataServiceImpl implements MapDataService {
    @Resource
    MapDataMapper mapDataMapper;

    @Resource
    LandService landService;
    
	@Override
	public Table<String, String, Float> getDataByMap(String regionId, String beginTime, String endTime) {
		List<LandPie> list1 = landService.getPieData(regionId, beginTime, endTime);
		
		List<LandCount> list2 = mapDataMapper.getLandCount(this.initQueryParam(regionId, beginTime, endTime));
		
		Table<String, String, Float> table = HashBasedTable.create();
		for (LandPie lp : list1) {
			table.put(lp.getcLv(), lp.getcLv(), lp.getPerc().floatValue());
		}
		for (LandCount lc : list2) {
			table.put("PH", lc.getName(), lc.getPh().floatValue());
			table.put("镉", lc.getName(), lc.getCd().floatValue());
			table.put("有效态镉", lc.getName(), lc.getAcd().floatValue());
		}
		return table;
	}
	
	/**
	 * 构建查询参数集
	 * @param regionId
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	private Map<String, Object> initQueryParam(String regionId, String beginTime, String endTime) {
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
		return args;
	}

	@Override
	public Map<String, Integer> getRegTypeDataByMap(String regionId, String beginTime, String endTime) {
		List<Map<String, Object>> list = mapDataMapper.getRegTypeCount(this.initQueryParam(regionId, beginTime, endTime));
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (Map<String, Object> m : list) {
			map.put(m.get("NAME").toString(), Integer.valueOf(m.get("NUM").toString()));
		}
		return map;
	}

	@Override
	public List<LandReg> getLandReg() {
		return mapDataMapper.getLandReg();
	}

	@Override
	public List<AirReg> getAirReg() {
		return mapDataMapper.getAirReg();
	}
}