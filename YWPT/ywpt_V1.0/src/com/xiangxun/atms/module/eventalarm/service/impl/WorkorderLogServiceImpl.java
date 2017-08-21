package com.xiangxun.atms.module.eventalarm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderLog;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderLogSearch;
import com.xiangxun.atms.module.eventalarm.mapper.WorkorderLogMapper;
import com.xiangxun.atms.module.eventalarm.service.WorkorderLogService;

/**
 * 工单操作日志业务逻辑接口
 * @author kouyunhao
 *
 */
@Service("workorderLogService")
public class WorkorderLogServiceImpl extends AbstractBaseService<WorkorderLog, WorkorderLogSearch> implements WorkorderLogService {

	@Resource
	WorkorderLogMapper workorderLogMapper;
	
	@Override
	protected BaseMapper<WorkorderLog, WorkorderLogSearch> getBaseMapper() {
		return workorderLogMapper;
	}

}
