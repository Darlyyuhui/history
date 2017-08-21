package com.xiangxun.atms.module.property.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.ServerInfoHis;
import com.xiangxun.atms.module.property.domain.ServerInfoHisSearch;
import com.xiangxun.atms.module.property.mapper.ServerInfoHisMapper;
import com.xiangxun.atms.module.property.service.ServerInfoHisService;

@Service("serverInfoHisService")
public class ServerInfoHisServiceImpl extends AbstractBaseService<ServerInfoHis, ServerInfoHisSearch> implements ServerInfoHisService {

	@Resource
	ServerInfoHisMapper serverInfoHisMapper;
	
	@Override
	public void updateAfterRecordId(String id, String afterId) {
		ServerInfoHis serviceHis = super.getById(id);
		serviceHis.setAfterRecordId(afterId);
		serverInfoHisMapper.updateByPrimaryKeySelective(serviceHis);
	}

	@Override
	public boolean hasRecordModified(String id) {
		int count = serverInfoHisMapper.countRecordInHistory(id);
		if(count == 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	protected BaseMapper<ServerInfoHis, ServerInfoHisSearch> getBaseMapper() {
		return serverInfoHisMapper;
	}

}
