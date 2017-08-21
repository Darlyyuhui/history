
package com.xiangxun.atms.module.sergrade.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfoSearch;

/**
 * 运维服务商信息服务
 * @author guikaiping
 * @version 1.0
 */
public interface FactoryInfoService extends BaseService<FactoryInfo,FactoryInfoSearch> {
	/***
	 * 查询运维服务商列表信息服务
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	public Page getReport(Map<String, Object> map, int pageNo, int pageSize, String sortType, String menuid);
	
	/***
     * 根据名称获取对象
     * @param model
     * @return FactoryInfo
     */
	public List<FactoryInfo> getByName(String name);
	
	public List<FactoryInfo> findAll();
}
