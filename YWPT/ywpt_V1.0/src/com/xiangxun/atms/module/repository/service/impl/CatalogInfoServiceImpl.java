package com.xiangxun.atms.module.repository.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.common.collect.Table;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.repository.domain.CatalogInfo;
import com.xiangxun.atms.module.repository.domain.CatalogInfoSearch;
import com.xiangxun.atms.module.repository.mapper.CatalogInfoMapper;
import com.xiangxun.atms.module.repository.service.CatalogInfoService;

/**
 * 知识库目录管理业务逻辑接口实现类
 * @author kouyunhao
 *
 */
@Service("catalogInfoService")
public class CatalogInfoServiceImpl extends AbstractBaseService<CatalogInfo, CatalogInfoSearch> implements CatalogInfoService {
	
	@Resource
	CatalogInfoMapper catalogInfoMapper;

	@Resource
	Cache cache;

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.framework.base.AbstractBaseService#getBaseMapper()
	 */
	@Override
	protected BaseMapper<CatalogInfo, CatalogInfoSearch> getBaseMapper() {
		return catalogInfoMapper;
	}

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.module.emergency.service.CatalogInfoService#getCatalogJsonArray(java.lang.String)
	 */
	@Override
	public JsonArray getCatalogJsonArray() {
		JsonArray catalogJsonArray = new JsonArray();
		List<CatalogInfo> catalogList = findAll();
		getCatalogJsonArray(catalogList,catalogJsonArray);
		return catalogJsonArray;
	}
	
	/**
	 * 组织页面需要的树结构json字符
	 * @param deptId
	 * @return
	 */
	private void getCatalogJsonArray(List<CatalogInfo> catalogList,JsonArray catalogJsonArray) {
		if(catalogList ==null) return;
		for (CatalogInfo department : catalogList) {
			JsonObject json = new JsonObject();
			json.addProperty("id",department.getId());
			json.addProperty("pId",department.getPid());
			json.addProperty("isParent",hasChild(department.getId()));
			json.addProperty("name",department.getName());
			if("00".equals(department.getId())){
				json.addProperty("nocheck","true");
			}else{
				json.addProperty("checked","false");
			}
			catalogJsonArray.add(json);
		}
	}

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.module.emergency.service.CatalogInfoService#findAll()
	 */
	@Override
	public List<CatalogInfo> findAll() {
		CatalogInfoSearch search = new CatalogInfoSearch();
		search.createCriteria().andIdIsNotNull();
		search.setOrderByClause("name");
		return catalogInfoMapper.selectByExample(search);
	}

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.module.emergency.service.CatalogInfoService#hasChild(java.lang.String)
	 */
	@Override
	public boolean hasChild(String id) {
		Assert.notNull(id,"id must not be null");
		return catalogInfoMapper.hasChild(id)>0;
	}

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.module.emergency.service.CatalogInfoService#getChildren(java.lang.String)
	 */
	@Override
	public List<CatalogInfo> getChildren(String id) {
		Assert.notNull(id,"id must not be null");
		return catalogInfoMapper.getLeafNodeById(id);
	}

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.module.emergency.service.CatalogInfoService#getByName(java.lang.String)
	 */
	@Override
	public CatalogInfo getByName(String name) {
		CatalogInfoSearch search = new CatalogInfoSearch();
		search.createCriteria().andNameEqualTo(name);
		List<CatalogInfo> resultList = catalogInfoMapper.selectByExample(search);
		CatalogInfo catalog = null;
		if(resultList != null && resultList.size() != 0){
			catalog = resultList.get(0);
		}
		return catalog;
	}

}
