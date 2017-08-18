package com.xiangxun.atms.module.apb.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.apb.cache.ApbInfoProductLinkCache;
import com.xiangxun.atms.module.apb.dao.ApbInfoMapper;
import com.xiangxun.atms.module.apb.dao.ApbInfoProductLinkMapper;
import com.xiangxun.atms.module.apb.service.ApbInfoProductLinkService;
import com.xiangxun.atms.module.apb.service.ApbInfoService;
import com.xiangxun.atms.module.apb.vo.ApbInfo;
import com.xiangxun.atms.module.apb.vo.ApbInfoProductLink;
import com.xiangxun.atms.module.apb.vo.ApbInfoProductLinkSearch;
import com.xiangxun.atms.module.apb.vo.ApbInfoSearch;
import com.xiangxun.atms.module.apb.vo.ApbProductType;


@Service
public class ApbInfoProductLinkServiceImpl extends AbstractBaseService<ApbInfoProductLink, ApbInfoProductLinkSearch> implements ApbInfoProductLinkService {
    @Resource
    private ApbInfoProductLinkMapper apbInfoProductLinkMapper;
    @Resource
    Cache cache;
    @Resource
    private ApbInfoService apbInfoService;
	@Resource
	private ApbInfoMapper apbInfoMapper;
	@SuppressWarnings({ "unused" })
	@Override
	public void refreshCache() {
		
		ApbInfoProductLinkSearch search = new ApbInfoProductLinkSearch();
		List<ApbInfoProductLink> list =this.selectByExample(search);
		ApbInfoSearch apbInfosearch = new ApbInfoSearch();
		List<ApbInfo> list1 = apbInfoService.selectByExample(apbInfosearch);
		Table<String,String,List<ApbInfo>> table =HashBasedTable.create();
		Table<String,String,List<String>> table1 =HashBasedTable.create();
		Table<String,String,List<ApbProductType>> table2 =HashBasedTable.create();
		Table<String,String,List<String>> table3 =HashBasedTable.create();
		Map<String,String> map = new HashMap<String,String>();
		Set<String> set = new HashSet<String>();
		Set<String> set1 = new HashSet<String>();
		for(ApbInfoProductLink apb:list){
			if(apb.getProductId()!=null||"".equals(apb.getProductId())){
				set.add(apb.getProductId());
			
			}
			if(apb.getInfoId()!=null||"".equals(apb.getInfoId())){
				set1.add(apb.getInfoId());
			}
		}
		
		for(String id: set){
			List<ApbInfo> namelist =new ArrayList<ApbInfo>();
			List<String> list2=new ArrayList<String>();
			 namelist=this.selectApbinfoNameByApbProductId(id);
			table.put(id, ApbInfoProductLinkCache.ID_APBINFO, namelist);
			for(ApbInfo info:namelist){
				list2.add(info.getName());
				
			}
			table1.put(id, ApbInfoProductLinkCache.ID_INFONAME, list2);
		}
		for(String id:set1){
			List<ApbProductType> ProductNamelist =new ArrayList<ApbProductType>();
			List<String> list3=new ArrayList<String>();
			ProductNamelist =this.selectProductTypeNameByApbInfoId(id);
			table2.put(id, ApbInfoProductLinkCache.ID_APBPRODUCTTYPE, ProductNamelist);
			for(ApbProductType type:ProductNamelist){
				list3.add(type.getName());
			}
			table3.put(id, ApbInfoProductLinkCache.ID_APBPRODUCTTYPENAME, list3);
		}
		cache.put(ApbInfoProductLinkCache.ID_APBINFO, table);
		cache.put(ApbInfoProductLinkCache.ID_INFONAME, table1);
		cache.put(ApbInfoProductLinkCache.ID_APBPRODUCTTYPE, table2);
		cache.put(ApbInfoProductLinkCache.ID_APBPRODUCTTYPENAME, table3);
	}
	
	@Override
	protected BaseMapper<ApbInfoProductLink, ApbInfoProductLinkSearch> getBaseMapper() {
		
		return    apbInfoProductLinkMapper;
	}

	@Override
	public void updateByProductId(ApbInfoProductLink apbInfoProductLink) {
		
		apbInfoProductLinkMapper.updateByProductId(apbInfoProductLink);
		this.refreshCache();
	}

	@Override
	public void insertSelective(ApbInfoProductLink apbInfoProductLink) {
		
		apbInfoProductLinkMapper.insertSelective(apbInfoProductLink);
		this.refreshCache();
	}
	@Override
	public int deleteById(String id){
		int isOK = super.deleteById(id);
		this.refreshCache();
		return isOK;
		
	}

	@Override
	public List<ApbInfo> selectApbinfoNameByApbProductId(String productId) {
		
		return apbInfoProductLinkMapper.selectApbinfoNameByApbProductId(productId);
		
	}
	@Override
	public int updateByIdSelective(ApbInfoProductLink record){
		int isOK = super.updateByIdSelective(record);
		this.refreshCache();
		return isOK;
	}
	@Override
	public int save(ApbInfoProductLink record){
		int isOK = super.save(record);
		this.refreshCache();
		return isOK;
	}

	@Override
	public void deleteByApbInfoProductLink(ApbInfoProductLink apbInfoProductLink){
		apbInfoProductLinkMapper.deleteByApbInfoProductLink(apbInfoProductLink);
		this.refreshCache();
	}

	@Override
	public List<ApbProductType> selectProductTypeNameByApbInfoId(String infoId) {
		return apbInfoProductLinkMapper.selectProductTypeNameByApbInfoId(infoId);
	}
}