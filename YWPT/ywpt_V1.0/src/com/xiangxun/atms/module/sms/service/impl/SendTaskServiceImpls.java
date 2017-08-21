package com.xiangxun.atms.module.sms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.sms.dao.SendTaskMapper;
import com.xiangxun.atms.module.sms.service.SendTaskService;
import com.xiangxun.atms.module.sms.vo.SendTask;
import com.xiangxun.atms.module.sms.vo.SendTaskSearch;

/**
 * 待发送短信实现类
 * @author tianbo
 *
 */
@Service("sendTaskService")
public class SendTaskServiceImpls extends AbstractBaseService<SendTask,SendTaskSearch> implements SendTaskService {
	
	@Resource
	SendTaskMapper sendTaskMapper;
	
	@Override
	protected BaseMapper<SendTask, SendTaskSearch> getBaseMapper() {
		// TODO Auto-generated method stub
		return sendTaskMapper;
	}

}
