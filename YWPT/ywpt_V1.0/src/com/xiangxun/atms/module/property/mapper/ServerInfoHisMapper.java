package com.xiangxun.atms.module.property.mapper;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.ServerInfoHis;
import com.xiangxun.atms.module.property.domain.ServerInfoHisSearch;

public interface ServerInfoHisMapper extends BaseMapper<ServerInfoHis, ServerInfoHisSearch> {
	
	int countRecordInHistory(String id);
}