package com.xiangxun.atms.module.map.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.bs.cache.LandTypeCache;
import com.xiangxun.atms.module.geoServer.domain.LayerBean;
import com.xiangxun.atms.module.geoServer.domain.LayerEnum;
import com.xiangxun.atms.module.geoServer.service.IMapOperation;
import com.xiangxun.atms.module.land.cache.LandBlockCache;
import com.xiangxun.atms.module.land.vo.LandBlock;
import com.xiangxun.atms.module.map.dao.MapDataMapper;
import com.xiangxun.atms.module.map.service.MapDataService;
import com.xiangxun.atms.module.map.vo.AreaCount;
import com.xiangxun.atms.module.map.vo.LandAnalysisCount;
import com.xiangxun.atms.module.map.vo.LandCount;
import com.xiangxun.atms.module.map.vo.RepairCount;
import com.xiangxun.atms.module.map.vo.RepairLandBlock;
import com.xiangxun.atms.module.map.vo.RepairProject;
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
    @Resource
	IMapOperation iMapOperation;
    @Resource
    Cache cache;
    
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
	public List<LandReg> getLandReg(String beginTime, String endTime) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("beginTime", beginTime);
		args.put("endTime", endTime);
		return mapDataMapper.getLandReg(args);
	}

	@Override
	public List<AirReg> getAirReg(String beginTime, String endTime) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("beginTime", beginTime);
		args.put("endTime", endTime);
		return mapDataMapper.getAirReg(args);
	}

	@Override
	public AreaCount getMapIndexLandAreaCount() {
		Map<String, Object> args = new HashMap<String, Object>();
		return mapDataMapper.getMapIndexLandAreaCount(args);
	}

	@Override
	public LandAnalysisCount getLandListByIndexMap(String beginTime, String endTime) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("beginTime", beginTime);
		args.put("endTime", endTime);
		return mapDataMapper.getLandListByIndexMap(args);
	}

	@Override
	public RepairCount getRepairCount(String proId, String scheduleCode, String beginTime, String endTime) {
		if (StringUtils.isEmpty(proId) || StringUtils.isEmpty(scheduleCode)) {
			return null;
		}
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("proId", proId);
		args.put("scheduleCode", scheduleCode);
		args.put("beginTime", beginTime);
		args.put("endTime", endTime);
		return mapDataMapper.getRepairCount(args);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RepairLandBlock> getRepairLandBlock(String proId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("proId", proId);
		List<Map<String, Object>> list = mapDataMapper.getRepairLandBlock(args);
		
		
		Table<String, String, LandBlock> table1 = (Table<String, String, LandBlock>)cache.get(LandBlockCache.LB_ID_OBJ);
		Map<String, LandBlock> cacheMap1 = table1.column(LandBlockCache.LB_ID_OBJ);
		
		Table<String, String, String> table2 = (Table<String, String, String>)cache.get(LandTypeCache.ID_NAME);
		Map<String, String> cacheMap2 = table2.column(LandTypeCache.ID_NAME);
		
		List<RepairLandBlock> rList = new ArrayList<RepairLandBlock>();
		String lbId = null;
		for (Map<String, Object> map : list) {
			if (map == null) {
				continue;
			}
			if (map.get("BLOCK_ID") == null) {
				continue;
			}
			lbId = map.get("BLOCK_ID").toString();
			rList.add(this.getLbParamByCache(lbId, cacheMap1.get(lbId), cacheMap2));
		}
		return rList;
	}
	
	private RepairLandBlock getLbParamByCache(String lbId, LandBlock cacheLb, Map<String, String> landTypeMap) {
		RepairLandBlock t = new RepairLandBlock();
		t.setId(lbId);
		if (cacheLb != null) {
			t.setCode(cacheLb.getCode());
			t.setName(cacheLb.getName());
			t.setLatitude(cacheLb.getLatitude());
			t.setLongitude(cacheLb.getLongitude());
			t.setArea(cacheLb.getArea());
			t.setTypeCode(this.getDicByCache(DicType.LAND_BLOCK_TYPE, cacheLb.getTypeCode()));
			t.setSoilType(landTypeMap.get(cacheLb.getSoilType()));
			t.setPolluteType(this.getDicByCache(DicType.LAND_POLLUTE_TYPE, cacheLb.getPolluteType()));
		}
		t.setGeoJson(this.getMapGeoByLandBlockId(lbId));
		return t;
	}
	
	/**
	 * 转换数据字典
	 * @param typeCode
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String getDicByCache(DicType typeCode, String id) {
		Table<String, String, String> table = (Table<String, String, String>)cache.get("Dic");
		if (table == null) {
			return null;
		}
		Map<String, String> map = table.column(typeCode.getValue());
		if (map == null) {
			return null;
		}
		return map.get(id);
	}
	
	/**
	 * 通过地块ID获取地图geo数据
	 * @param lbId
	 * @return
	 */
	private String getMapGeoByLandBlockId(String lbId) {
		String mapGeo = null;
		List<LayerBean> layerList = iMapOperation.getByCode(LayerEnum.LAND, lbId);
		if (layerList != null) {
			for (LayerBean layerBean : layerList) {
				mapGeo = layerBean.getGeometry();
			}
		}
		return mapGeo;
	}

	@Override
	public List<RepairProject> getRepairProjects(int size) {
		Map<String, Object> args = new HashMap<String, Object>();
		return mapDataMapper.getRepairProjects(args, Page.getRowBounds(1, size));
	}
	
	
}