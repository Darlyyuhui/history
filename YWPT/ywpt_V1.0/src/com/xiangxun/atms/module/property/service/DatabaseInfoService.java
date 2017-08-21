package com.xiangxun.atms.module.property.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.property.domain.DatabaseInfo;
import com.xiangxun.atms.module.property.domain.DatabaseInfoSearch;
import com.xiangxun.atms.module.property.domain.ServerInfo;

/**
 * 数据库信息业务逻辑接口
 * @author kouyunhao
 *
 */
public interface DatabaseInfoService extends BaseService<DatabaseInfo, DatabaseInfoSearch> {
	
	List<DatabaseInfo> findAll();
	
	List<DatabaseInfo> findByIp(String ip);
	
	List<DatabaseInfo> findByName(String name);
	
	/**
	 * 根据厂商ID获取数据库信息
	 * @param factoryId
	 * @param menuid
	 * @return
	 */
	public List<DatabaseInfo> getDatabaseListByFactoryid(String factoryId);
	
	/***
	 * 导出EXCEL专用
	 * @return
	 */
	public List<DatabaseInfo> selectByExampleDiy(Map<String, Object> searchParams,String sortType,String menuid);
	

}
