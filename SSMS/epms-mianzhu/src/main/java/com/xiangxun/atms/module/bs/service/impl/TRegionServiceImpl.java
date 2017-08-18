package com.xiangxun.atms.module.bs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.bs.cache.TRegionCache;
import com.xiangxun.atms.module.bs.dao.TRegionMapper;
import com.xiangxun.atms.module.bs.service.TRegionService;
import com.xiangxun.atms.module.bs.vo.Region;
import com.xiangxun.atms.module.bs.vo.RegionSearch;

@Service
public class TRegionServiceImpl extends AbstractBaseService<Region, RegionSearch> implements TRegionService {
    
	@Resource
    TRegionMapper tregionMapper;

	@Resource
    Cache cache;
	
    @Override
    public BaseMapper<Region, RegionSearch> getBaseMapper() {
         return tregionMapper;
    }
    
    @Override
	public String makeTreeData() {
		@SuppressWarnings("unchecked")
		List<Region> list = (List<Region>)cache.get(TRegionCache.ALL_ITEM);
		
		JsonArray array = new JsonArray();
		
		JsonObject obj = new JsonObject();
		for (Region region : list) {
			obj = new JsonObject();
			obj.addProperty("id", region.getId());
			obj.addProperty("pId", region.getPid());
			obj.addProperty("name", region.getName());
			array.add(obj);
		}
		return array.toString();
	}

	@Override
	public int deleteById(String id) {
		int isOK = super.deleteById(id);
		this.refreshCache();
		return isOK;
	}

	@Override
	public int save(Region record) {
		int isOK = super.save(record);
		this.refreshCache();
		return isOK;
	}

	@Override
	public int updateByIdSelective(Region record) {
		int isOK = super.updateByIdSelective(record);
		this.refreshCache();
		return isOK;
	}

	@Override
	public void refreshCache() {
		RegionSearch search = new RegionSearch();
		search.setOrderByClause("PID ASC, SORT ASC");
		
		List<Region> list = this.selectByExample(search);
		
		Table<String, String, String> table = HashBasedTable.create();
        Table<String, String, String> table2 = HashBasedTable.create();
        Table<String, String, String> table3 = HashBasedTable.create();
        Table<String, String, Region> table4 = HashBasedTable.create();
        for (Region r : list) {
            table.put(r.getId(), TRegionCache.ID_NAME, r.getName());
            table2.put(r.getId(), TRegionCache.ID_FULLNAME, this.getFullNameById(r.getId()));
            table3.put(r.getId(), TRegionCache.ID_LOCATION, r.getLongitude()+","+r.getLatitude());
            table4.put(r.getId(), TRegionCache.ID_OBJ, r);
        }
        cache.put(TRegionCache.ID_NAME,table);
        cache.put(TRegionCache.ID_FULLNAME,table2);
        cache.put(TRegionCache.ID_LOCATION,table3);
        cache.put(TRegionCache.ID_OBJ,table4);
        cache.put(TRegionCache.ALL_ITEM, list);
	}

	@Override
	public String getFullNameById(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("id", id);
		List<Map<String,Object>> list = tregionMapper.getFullNameById(args);
		if (list == null || list.size() == 0) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		for (Map<String,Object> m : list) {
			str.append(m.get("NAME"));
		}
		return str.toString();
	}
    
}