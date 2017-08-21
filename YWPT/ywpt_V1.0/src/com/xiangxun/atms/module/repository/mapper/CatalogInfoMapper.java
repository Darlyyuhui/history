package com.xiangxun.atms.module.repository.mapper;

import java.util.List;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.repository.domain.CatalogInfo;
import com.xiangxun.atms.module.repository.domain.CatalogInfoSearch;

public interface CatalogInfoMapper extends BaseMapper<CatalogInfo, CatalogInfoSearch> {
	
	/***
	 * 根据id获取所有的子节点
	 * @param id
	 * @return
	 */
	public List<CatalogInfo> getLeafNodeById(String id);
	
	/***
	 * 判断是否有子节点
	 * @param id
	 * @return
	 */
	public int hasChild(String id);
}