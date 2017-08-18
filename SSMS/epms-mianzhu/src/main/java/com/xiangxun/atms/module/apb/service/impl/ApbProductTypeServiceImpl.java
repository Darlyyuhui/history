package com.xiangxun.atms.module.apb.service.impl;

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
import com.xiangxun.atms.module.apb.cache.ApbProductTypeCache;
import com.xiangxun.atms.module.apb.dao.ApbProductTypeMapper;
import com.xiangxun.atms.module.apb.service.ApbProductTypeService;
import com.xiangxun.atms.module.apb.vo.ApbProductType;
import com.xiangxun.atms.module.apb.vo.ApbProductTypeSearch;

@Service
public class ApbProductTypeServiceImpl extends AbstractBaseService<ApbProductType, ApbProductTypeSearch> implements ApbProductTypeService {
    @Resource
    private ApbProductTypeMapper apbProductTypeMapper;
    @Resource
    Cache cache;

	@Override
	protected BaseMapper<ApbProductType, ApbProductTypeSearch> getBaseMapper() {
		return   apbProductTypeMapper;
	}

	@Override
	public void refreshCache() {
		ApbProductTypeSearch search = new ApbProductTypeSearch();
		List<ApbProductType> list = this.selectByExample(search);
		Table<String, String, String> table = HashBasedTable.create();
		Table<String, String, String> table1 = HashBasedTable.create();
		for(ApbProductType it:list){
			table.put(it.getId(), ApbProductTypeCache.ID_NAME, it.getName());
			table1.put(it.getCode(), ApbProductTypeCache.CODE_NAME, it.getName());
			
		}
		cache.put(ApbProductTypeCache.ID_NAME, table);
		cache.put(ApbProductTypeCache.CODE_NAME, table1);
		cache.put(ApbProductTypeCache.ALL_ITEM, list);
		
	}
	@Override
	public int updateByIdSelective(ApbProductType record){
		int isOK = super.updateByIdSelective(record);
		this.refreshCache();
		return isOK;
	}
	@Override
	public int save(ApbProductType record){
		int isOK = super.save(record);
		this.refreshCache();
		return isOK;
	}
	@Override
	public int deleteById(String id){
		int isOK = super.deleteById(id);
		this.refreshCache();
		return isOK;
		
	}

	@Override
	public String makeTreeData() {
		@SuppressWarnings("unchecked")
		List<ApbProductType> list = (List<ApbProductType>)cache.get(ApbProductTypeCache.ALL_ITEM);
		
		JsonArray array = new JsonArray();
		
		JsonObject obj = new JsonObject();
		obj = new JsonObject();
		obj.addProperty("id", "0");
		obj.addProperty("pId", "-1");
		obj.addProperty("name", "农产品种类");
		array.add(obj);
		for (ApbProductType lt : list) {
			obj = new JsonObject();
			obj.addProperty("id", lt.getId());
			obj.addProperty("pId", lt.getPid());
			obj.addProperty("name", lt.getName());
			array.add(obj);
		}
		return array.toString();
	}
}