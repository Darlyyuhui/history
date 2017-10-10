package com.xiangxun.atms.module.bs.service.impl;

import java.util.ArrayList;
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
import com.xiangxun.atms.module.bs.cache.RepairStageCache;
import com.xiangxun.atms.module.bs.dao.RepairStageMapper;
import com.xiangxun.atms.module.bs.service.RepairStageService;
import com.xiangxun.atms.module.bs.vo.RepairStage;
import com.xiangxun.atms.module.bs.vo.RepairStageSearch;

@Service
public class RepairStageServiceImpl extends AbstractBaseService<RepairStage, RepairStageSearch> implements RepairStageService {
    @Resource
    RepairStageMapper repairStageMapper;
    
    @Resource
    Cache cache;

    @Override
    public BaseMapper<RepairStage, RepairStageSearch> getBaseMapper() {
         return repairStageMapper;
    }

	@Override
	public String makeTreeData() {
		@SuppressWarnings("unchecked")
		List<RepairStage> list = (List<RepairStage>)cache.get(RepairStageCache.ALL_ITEM);
		
		JsonArray array = new JsonArray();
		
		JsonObject obj = new JsonObject();
		obj = new JsonObject();
		obj.addProperty("id", "0");
		obj.addProperty("pId", "-1");
		obj.addProperty("name", "土壤修复阶段");
		array.add(obj);
		for (RepairStage rs : list) {
			obj = new JsonObject();
			obj.addProperty("id", rs.getId());
			obj.addProperty("pId", rs.getPid());
			obj.addProperty("name", rs.getName());
			array.add(obj);
		}
		return array.toString();
	}
	

	@Override
	public void refreshCache() {
		RepairStageSearch search = new RepairStageSearch();
		search.setOrderByClause("SORT ASC");
		List<RepairStage> list = this.selectByExample(search);
		
		Table<String, String, String> table = HashBasedTable.create();
		//1级阶段集合
		List<RepairStage> topList = new ArrayList<RepairStage>();
		//将除过1级阶段的放入map，key=pid
		Map<String, List<RepairStage>> map = new HashMap<String, List<RepairStage>>();
		List<RepairStage> temp = null;
		String key = null;
		for (RepairStage rs : list) {
			table.put(rs.getId(), RepairStageCache.ID_NAME, rs.getName());
			if ("0".equals(rs.getPid())) {
				topList.add(rs);
			} else {
				key = rs.getPid();
				temp = map.get(key);
				if (temp == null) {
					temp = new ArrayList<RepairStage>();
				}
				temp.add(rs);
				map.put(key, temp);
			}
		}
		cache.put(RepairStageCache.ALL_ITEM, list);
		cache.put(RepairStageCache.ID_NAME, table);
		cache.put(RepairStageCache.TOP_LIST, topList);
		cache.put(RepairStageCache.STAGE_MAP, map);
	}

	@Override
	public int deleteById(String id) {
		int isOK = super.deleteById(id);
		this.refreshCache();
		return isOK;
	}

	@Override
	public int save(RepairStage record) {
		int isOK = super.save(record);
		this.refreshCache();
		return isOK;
	}

	@Override
	public int updateByIdSelective(RepairStage record) {
		int isOK = super.updateByIdSelective(record);
		this.refreshCache();
		return isOK;
	}
}