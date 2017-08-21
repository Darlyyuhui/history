package com.xiangxun.atms.module.property.service;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.property.domain.ServerInfoHis;
import com.xiangxun.atms.module.property.domain.ServerInfoHisSearch;

public interface ServerInfoHisService extends BaseService<ServerInfoHis, ServerInfoHisSearch> {
	
	public void updateAfterRecordId(String id, String afterId);
	
	boolean hasRecordModified(String id);

}
