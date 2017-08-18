package com.xiangxun.atms.module.apb.service;


import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.apb.vo.ApbProductType;
import com.xiangxun.atms.module.apb.vo.ApbProductTypeSearch;

public interface ApbProductTypeService extends BaseService<ApbProductType, ApbProductTypeSearch> {
	/**
	 * 刷新缓存
	 */
	void refreshCache();
	/**
	 * 生成树形数据
	 * @return
	 */
	String makeTreeData();
	
}