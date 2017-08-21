package com.xiangxun.atms.module.property.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.ICabinetLog;
import com.xiangxun.atms.module.property.domain.ICabinetLogSearch;
import com.xiangxun.atms.module.property.mapper.ICabinetLogMapper;
import com.xiangxun.atms.module.property.service.ICabinetLogService;
@Service("icabinetLogService")
public class ICabinetLogServiceImpl extends AbstractBaseService<ICabinetLog, ICabinetLogSearch> implements ICabinetLogService {
	@Resource
	ICabinetLogMapper icabinetLogMapper;
	
	@Override
	protected BaseMapper<ICabinetLog, ICabinetLogSearch> getBaseMapper() {
		return icabinetLogMapper;
	}
	
	@Override
	public List<ICabinetLog> getMessage(String execno){
		return icabinetLogMapper.selectByIp(execno);
	}
}
