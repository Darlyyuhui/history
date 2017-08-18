package com.xiangxun.atms.module.land.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.module.land.dao.LandHomeMapper;
import com.xiangxun.atms.module.land.service.LandHomeService;
import com.xiangxun.atms.module.util.EchartPieData;
import com.xiangxun.atms.module.util.FtlJsonUtil;

@Service
public class LandHomeServiceImpl implements LandHomeService {

	@Resource
	LandHomeMapper landHomeMapper;

	@Override
	public Map<String, Object> getTopCount() {
		Map<String, Object> map = new HashMap<String, Object>();
		this.putTopMap(landHomeMapper.getTopLandBlockCount(), map);
		this.putTopMap(landHomeMapper.getTopSampleCount(), map);
		this.putTopMap(landHomeMapper.getTopRepairCount(), map);
		return map;
	}
	
	private void putTopMap(List<Map<String, Object>> list, Map<String, Object> map) {
		if (list == null) {
			return;
		}
		for (Map<String, Object> m : list) {
			if (m == null) continue;
			for (String k : m.keySet()) {
				map.put(k, m.get(k));
			}
		}
	}

	@Override
	public List<Map<String, Object>> getNewRegLandInfo(int pageSize) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageSize", pageSize);
		return landHomeMapper.getNewRegLandInfo(args);
	}

	@Override
	public List<Map<String, Object>> getSamplingPlanInfo(int pageSize) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageSize", pageSize);
		return landHomeMapper.getSamplingPlanInfo(args);
	}

	@Override
	public String getReggingCountCharts() {
		List<Map<String, Object>> list = landHomeMapper.getReggingCount();
		
		List<EchartPieData> dataList = new ArrayList<EchartPieData>();
		for (Map<String, Object> m : list) {
			dataList.add(new EchartPieData(m.get("NAME_").toString(), m.get("NUM_")));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("datas", dataList);
		FtlJsonUtil t = new FtlJsonUtil("land", "indexLine.ftl");
		return t.process(map);
	}

	@Override
	public String getLandTypeAreaCountCharts() {
		List<Map<String, Object>> list = landHomeMapper.getLandTypeAreaCount();
		
		List<EchartPieData> dataList = new ArrayList<EchartPieData>();
		for (Map<String, Object> m : list) {
			dataList.add(new EchartPieData(m.get("NAME").toString(), m.get("SUM_AREA")));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("datas", dataList);
		FtlJsonUtil t = new FtlJsonUtil("land", "indexPie.ftl");
		return t.process(map);
	}

	@Override
	public List<Map<String, Object>> getLandRepair(int pageSize) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageSize", pageSize);
		return landHomeMapper.getLandRepair(args);
	}
}
