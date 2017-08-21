package com.xiangxun.atms.module.repository.service;

import java.util.List;

import com.google.gson.JsonArray;
import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.repository.domain.CatalogInfo;
import com.xiangxun.atms.module.repository.domain.CatalogInfoSearch;

/**
 * 知识库目录管理业务逻辑接口
 * @author kouyunhao
 *
 */
public interface CatalogInfoService extends BaseService<CatalogInfo, CatalogInfoSearch> {
	
	/***
	 * 获取所有的机构数据
	 * @return
	 */
	public List<CatalogInfo> findAll();

	/***
	 * 获取文档目录的json字符
	 * @return
	 */
	public JsonArray getCatalogJsonArray();

	/***
	 * 判断是否有子节点
	 * @param id
	 * @return
	 */
	public boolean hasChild(String id);

	/***
	 * 根据id获取所有的子节点
	 * @param id
	 * @return
	 */
	public List<CatalogInfo> getChildren(String id);

	/***
	 * 根据name查询对象
	 * @param id
	 * @return
	 */
	public CatalogInfo getByName(String name);

}
