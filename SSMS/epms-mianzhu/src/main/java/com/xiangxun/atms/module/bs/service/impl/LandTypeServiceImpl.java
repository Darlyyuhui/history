package com.xiangxun.atms.module.bs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.bs.cache.LandTypeCache;
import com.xiangxun.atms.module.bs.dao.LandTypeMapper;
import com.xiangxun.atms.module.bs.service.LandTypeService;
import com.xiangxun.atms.module.bs.vo.LandType;
import com.xiangxun.atms.module.bs.vo.LandTypeSearch;

@Service
public class LandTypeServiceImpl extends AbstractBaseService<LandType, LandTypeSearch> implements LandTypeService {
    @Resource
    LandTypeMapper landTypeMapper;

    @Resource
    Cache cache;
    
    @Override
    public BaseMapper<LandType, LandTypeSearch> getBaseMapper() {
         return landTypeMapper;
    }

	@Override
	public String makeTreeData() {
		@SuppressWarnings("unchecked")
		List<LandType> list = (List<LandType>)cache.get(LandTypeCache.ALL_ITEM);
		
		JsonArray array = new JsonArray();
		
		JsonObject obj = new JsonObject();
		obj = new JsonObject();
		obj.addProperty("id", "0");
		obj.addProperty("pId", "-1");
		obj.addProperty("name", "地块土壤类型");
		array.add(obj);
		for (LandType lt : list) {
			obj = new JsonObject();
			obj.addProperty("id", lt.getId());
			obj.addProperty("pId", lt.getPid());
			obj.addProperty("name", lt.getName());
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
	public int save(LandType record) {
		int isOK = super.save(record);
		this.refreshCache();
		return isOK;
	}

	@Override
	public int updateByIdSelective(LandType record) {
		int isOK = super.updateByIdSelective(record);
		this.refreshCache();
		return isOK;
	}

	@Override
	public void refreshCache() {
		LandTypeSearch search = new LandTypeSearch();
		search.setOrderByClause("PID ASC, SORT ASC");
		
		List<LandType> list = this.selectByExample(search);
		Table<String, String, String> table = HashBasedTable.create();
		Table<String, String, String> table1 = HashBasedTable.create();
        for (LandType lt : list) {
            table.put(lt.getId(), LandTypeCache.ID_NAME, lt.getName());
            table1.put(lt.getCode(), LandTypeCache.CODE_NAME, lt.getName());
        }
        cache.put(LandTypeCache.ID_NAME,table);
        cache.put(LandTypeCache.ALL_ITEM,list);
        cache.put( LandTypeCache.CODE_NAME, table1);
	}
}