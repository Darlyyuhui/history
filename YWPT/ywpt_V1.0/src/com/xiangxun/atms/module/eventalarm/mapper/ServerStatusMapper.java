package com.xiangxun.atms.module.eventalarm.mapper;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.eventalarm.domain.ServerStatus;
import com.xiangxun.atms.module.eventalarm.domain.ServerStatusSearch;

public interface ServerStatusMapper extends BaseMapper<ServerStatus,ServerStatusSearch>{
	
	ServerStatus selectServerStatusByIp(String ip);
	
}