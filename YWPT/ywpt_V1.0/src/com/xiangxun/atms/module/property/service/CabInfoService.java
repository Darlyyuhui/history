package com.xiangxun.atms.module.property.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.property.domain.CabInfo;
import com.xiangxun.atms.module.property.domain.CabInfoSearch;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.ServerInfo;

public interface CabInfoService extends BaseService<CabInfo, CabInfoSearch> {

	List<CabInfo> findByName(String name);

	List<CabInfo> selectAll();

	/***
	 * 根据IP 获得 设备列表
	 * 
	 * @return
	 */
	public List<CabInfo> findByIp(String ip);

	public List<CabInfo> getCabListByFactoryid(String factoryId);
	 
	List<CabInfo> findByCode(String code);
	
	/***
	 * 导出EXCEL专用
	 * @return
	 */
	public List<CabInfo> selectByExampleDiy(Map<String, Object> searchParams,String sortType,String menuid);
	

}
