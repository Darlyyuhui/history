package com.xiangxun.atms.module.statistics.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.bs.cache.StandardMonitorCahe;
import com.xiangxun.atms.module.bs.vo.StandardMonitor;
import com.xiangxun.atms.module.statistics.dao.LandMapper;
import com.xiangxun.atms.module.statistics.service.LandService;
import com.xiangxun.atms.module.statistics.vo.LandACd;
import com.xiangxun.atms.module.statistics.vo.LandCd;
import com.xiangxun.atms.module.statistics.vo.LandLine;
import com.xiangxun.atms.module.statistics.vo.LandPh;
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

	@Override
	public List<String[]> getStandMByDicTypeCode(String dicTypeCode) {
		@SuppressWarnings("unchecked")
		Map<String, List<StandardMonitor>> cacheMap = (Map<String, List<StandardMonitor>>)cache.get(StandardMonitorCahe.TYPE_ITEMS);
		List<StandardMonitor> list = cacheMap.get(dicTypeCode);
		List<String[]> returnList = new ArrayList<String[]>();
		if (list != null && list.size() > 0) {
			for (StandardMonitor sm : list) {
				returnList.add(new String[]{this.makeFW(sm), sm.getLevel()});
			} 
		}
		return returnList;
	}
	
	/**
	 * 生成范围描述
	 * @param sm
	 * @return
	 */
	private String makeFW(StandardMonitor sm) {
		if (sm == null) {
			return "";
		}
		BigDecimal min = sm.getMinVal();
		BigDecimal max = sm.getMaxVal();
		if (min != null && max != null) {
			return min.doubleValue() + " ~ " + max.doubleValue();
		} else if (min == null && max != null) {
			return "< " + max.doubleValue();
		} else if (min != null && max == null) {
			return "> " + min.doubleValue();
		}
		return "";
	}

	@Override
	public LandPh getPhData(String regionId, String beginTime, String endTime) {
		//构建查询参数集合
		Map<String, Object> args = this.makeQueryParams("001", regionId, beginTime, endTime);
		LandPh ph = landMapper.getPhData(args);
		return ph == null ? new LandPh() : ph;
	}

	/**
	 * 构建查询参数
	 * @param dicTypeCode
	 * @param regionId
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	private Map<String, Object> makeQueryParams(String dicTypeCode, String regionId, String beginTime, String endTime) {
		List<String[]> list = this.getStandMByDicTypeCode(dicTypeCode);
		if (list == null || list.size() == 0) {
			return null;
		}
		String[] strs = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			strs[i] = list.get(i)[1];
		}
		
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("regionId", regionId);
		args.put("beginTime", beginTime);
		args.put("endTime", endTime);
		args.put("smLevels", strs);
		return args;
	}
	
	@Override
	public LandCd getCdData(String regionId, String beginTime, String endTime) {
		Map<String, Object> args = this.makeQueryParams("002", regionId, beginTime, endTime);
		LandCd cd = landMapper.getCdData(args);
		return cd == null ? new LandCd() : cd;
	}

	@Override
	public LandACd getACdData(String regionId, String beginTime, String endTime) {
		Map<String, Object> args = this.makeQueryParams("003", regionId, beginTime, endTime);
		LandACd acd = landMapper.getACdData(args);
		return acd == null ? new LandACd() : acd;
	}

}
