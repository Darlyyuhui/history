package com.xiangxun.atms.module.apb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.apb.cache.ApbInfoCache;
import com.xiangxun.atms.module.apb.cache.ApbProductTypeCache;
import com.xiangxun.atms.module.apb.dao.ApbInfoMapper;
import com.xiangxun.atms.module.apb.service.ApbInfoService;
import com.xiangxun.atms.module.apb.service.ApbProductTypeService;
import com.xiangxun.atms.module.apb.vo.ApbInfo;
import com.xiangxun.atms.module.apb.vo.ApbInfoSearch;

@Service
public class ApbInfoServiceImpl extends AbstractBaseService<ApbInfo, ApbInfoSearch> implements ApbInfoService {
    @Resource
    private ApbInfoMapper apbInfoMapper;
    @Resource
    Cache cache;
    @Resource
    ApbProductTypeService apbProductTypeService;
    @Override
    public BaseMapper<ApbInfo, ApbInfoSearch> getBaseMapper() {
         return  apbInfoMapper;
    }
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void refreshCache() {
		
		ApbInfoSearch search = new ApbInfoSearch();
		List<ApbInfo> list = this.selectByExample(search);
		Table<String, String, String> table = HashBasedTable.create();
		Table<String, String, List<String>> table1 = HashBasedTable.create();
		com.google.common.collect.Table table6= (com.google.common.collect.Table)cache.get(ApbProductTypeCache.CODE_NAME);
		for(ApbInfo apbInfo:list){
			List<String> mainProductList = new ArrayList<String>();
			table.put(apbInfo.getId(), ApbInfoCache.ID_NAME, apbInfo.getName());
			String mainProduct=apbInfo.getMainProduct();
			if(mainProduct != null && !"".equals(mainProduct)){
				String[] mainProducts=mainProduct.split(",");
				for(String s:mainProducts){
					String ss=(String) table6.column(ApbProductTypeCache.CODE_NAME).get(s);
					if(ss!=null&&!"".equals(ss)){
						mainProductList.add(ss);
					}
				}
			}
			table1.put(apbInfo.getId(), ApbInfoCache.ID_MAINPRODUCT, mainProductList);
		}
		cache.put(ApbInfoCache.ID_NAME, table);
		cache.put(ApbInfoCache.ALL_ITEM, list);
		cache.put(ApbInfoCache.ID_MAINPRODUCT, table1);
	}
	@Override
	public int updateByIdSelective(ApbInfo apbInfo){
		int isOK = super.updateByIdSelective(apbInfo);
		this.refreshCache();
		return isOK;
	}
	@Override
	public int save(ApbInfo record){
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
}