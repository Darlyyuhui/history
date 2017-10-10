package com.xiangxun.atms.module.statistics.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.bs.cache.TRegionCache;
import com.xiangxun.atms.module.bs.vo.Region;
import com.xiangxun.atms.module.statistics.dao.AnalysisMapper;
import com.xiangxun.atms.module.statistics.service.AnalysisService;
import com.xiangxun.atms.module.statistics.vo.AnalysisInfo;

@Service
public class AnalysisServiceImpl implements AnalysisService {

	@Resource
	AnalysisMapper analysisMapper;
	@Resource
    Cache cache;
	
	@Override
	public List<AnalysisInfo> getLandList(String regionId, String beginTime, String endTime) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("regionId", regionId);
		args.put("beginTime", beginTime);
		args.put("endTime", endTime);
		return this.makeData(analysisMapper.getLandList(args));
	}
	
	/**
	 * 加工数据
	 * @param list
	 * @return
	 */
	private List<AnalysisInfo> makeData(List<AnalysisInfo> list) {
		//将查询出的集合转成map，key=regionId, value=AnalysisInfo
		Map<String, AnalysisInfo> map = new HashMap<String, AnalysisInfo>();
		for (AnalysisInfo ri : list) {
			if (ri == null) {
				continue;
			}
			map.put(ri.getRegionId(), ri);
		}
		//从缓存中取出所有区域
		@SuppressWarnings("unchecked")
		List<Region> regionList = (List<Region>)cache.get(TRegionCache.ALL_ITEM);
		
		//初始化需要返回的结果集
		List<AnalysisInfo> returnList = new ArrayList<AnalysisInfo>();
		AnalysisInfo rinfo = null;
		//循环所有区域信息
		for (Region r : regionList) {
			//只处理各乡镇
			if ("0".equals(r.getPid())) {
				rinfo = map.get(r.getId());
				//如果查询结果集中没有，则默认
				if (rinfo == null) {
					rinfo = new AnalysisInfo();
					rinfo.setRegionId(r.getId());
				}
				returnList.add(rinfo);
			}
		}
		return returnList;
	}

}
