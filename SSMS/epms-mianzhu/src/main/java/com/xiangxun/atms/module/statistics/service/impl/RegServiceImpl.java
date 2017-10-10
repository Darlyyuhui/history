package com.xiangxun.atms.module.statistics.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.common.dictionary.vo.DicSearch;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.bs.cache.TRegionCache;
import com.xiangxun.atms.module.bs.vo.Region;
import com.xiangxun.atms.module.statistics.dao.RegMapper;
import com.xiangxun.atms.module.statistics.service.RegService;

@Service
public class RegServiceImpl implements RegService {

	@Resource
	RegMapper regMapper;
	@Resource
	DicService dicService;
	@Resource
    Cache cache;

	@Override
	public List<Map<String, Object>> getList(String regionId, String beginTime, String endTime, Map<String, List<Dic>> dicmap) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("regionId", regionId);
		args.put("beginTime", beginTime);
		args.put("endTime", endTime);
		//土壤样品类型
		args.put("landDics", dicmap.get(DicType.SAMPLING_LAND_TYPE.getValue()));
		//水样品类型
		args.put("waterDics", dicmap.get(DicType.SAMPLING_WATER_TYPE2.getValue()));
		//农产品样品类型
		args.put("farmDics", dicmap.get(DicType.SAMPLING_FARM_TYPE.getValue()));
		
		return this.makeData(regMapper.getList(args));
	}
	
	@Override
	public Map<String, List<Dic>> getDicMap() {
		Map<String, List<Dic>> map = new HashMap<String, List<Dic>>();
		map.put(DicType.SAMPLING_LAND_TYPE.getValue(), this.getDicByType(DicType.SAMPLING_LAND_TYPE.getValue()));
		map.put(DicType.SAMPLING_WATER_TYPE2.getValue(), this.getDicByType(DicType.SAMPLING_WATER_TYPE2.getValue()));
		map.put(DicType.SAMPLING_FARM_TYPE.getValue(), this.getDicByType(DicType.SAMPLING_FARM_TYPE.getValue()));
		return map;
	}

	/**
	 * 获取字典类型
	 * @param dicType
	 * @return
	 */
	private List<Dic> getDicByType(String dicType) {
		DicSearch search = new DicSearch();
		search.createCriteria().andTypeEqualTo(dicType);
		search.setOrderByClause("CODE ASC");
		return dicService.selectByExample(search);
	}
	
	/**
	 * 加工数据
	 * @param list
	 * @return
	 */
	private List<Map<String, Object>> makeData(List<Map<String, Object>> list) {
		//将查询出的集合转成map，key=regionId, value=RegInfo
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
		for (Map<String, Object> m : list) {
			if (m == null) {
				continue;
			}
			map.put(m.get("REGION_ID").toString(), m);
		}
		//从缓存中取出所有区域
		@SuppressWarnings("unchecked")
		List<Region> regionList = (List<Region>)cache.get(TRegionCache.ALL_ITEM);
		
		//初始化需要返回的结果集
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		Map<String, Object> rinfo = null;
		//循环所有区域信息
		for (Region r : regionList) {
			//只处理各乡镇
			if ("0".equals(r.getPid())) {
				rinfo = map.get(r.getId());
				//如果查询结果集中没有，则默认
				if (rinfo == null) {
					rinfo = new HashMap<String, Object>();
					rinfo.put("REGION_ID", r.getId());
				}
				returnList.add(rinfo);
			}
		}
		return returnList;
	}
	
}
