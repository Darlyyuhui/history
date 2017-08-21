package com.xiangxun.atms.module.property.mapper;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.ServerInfo;
import com.xiangxun.atms.module.property.domain.ServerInfoSearch;

public interface ServerInfoMapper extends BaseMapper<ServerInfo, ServerInfoSearch> {
	
	void insertHistoryRecord(String id);
 	
 	void updatePrimaryKey(String afterId, String beforeId);
 	
 	//根据服务厂商ID获取服务器设备信息
	List<ServerInfo> getListByFactoryid(Map<String, Object> map);
}