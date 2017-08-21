package com.xiangxun.atms.module.sms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.sms.dao.EventLogMapper;
import com.xiangxun.atms.module.sms.service.EventLogService;
import com.xiangxun.atms.module.sms.vo.EventLog;
import com.xiangxun.atms.module.sms.vo.EventLogSearch;

/**
 * 短信日志实现类
 * @author tianbo
 *
 */
@Service("eventLogService")
public class EventLogServiceImpls extends AbstractBaseService<EventLog, EventLogSearch>
implements EventLogService{
	
	@Resource 
	EventLogMapper eventLogMapper;

	@Override
	protected BaseMapper<EventLog, EventLogSearch> getBaseMapper() {
		return eventLogMapper;
	}


}
