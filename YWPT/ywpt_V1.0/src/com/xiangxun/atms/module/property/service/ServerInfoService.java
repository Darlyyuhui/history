package com.xiangxun.atms.module.property.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.ServerInfo;
import com.xiangxun.atms.module.property.domain.ServerInfoSearch;

/**
 * 场内设备管理-服务器信息业务逻辑接口
 * @author kouyunhao
 *
 */
public interface ServerInfoService extends BaseService<ServerInfo, ServerInfoSearch> {

	List<ServerInfo> findAll();
	
	List<ServerInfo> findByIp(String ip);
	
	List<ServerInfo> findByName(String name);
	
	List<ServerInfo> findByCode(String code);
	
	
	/***
	 * 导出EXCEL专用
	 * @return
	 */
	public List<ServerInfo> selectByExampleDiy(Map<String, Object> searchParams,String sortType,String menuid);
	
	
	
	
	/**
	 * 保存历史记录
	 * @param id
	 */
	public void saveHistoryRecord(String id);
	
	/**
	 * 修改主键
	 * @param afterId
	 * @param beforeId
	 */
	public void updatePrimaryKey(String afterId, String beforeId);
	
	/**
	 * 根据厂商ID获取服务器设备信息
	 * @param factoryId
	 * @param menuid
	 * @return
	 */
	public List<ServerInfo> getServerListByFactoryid(String factoryId, String menuid);
}
