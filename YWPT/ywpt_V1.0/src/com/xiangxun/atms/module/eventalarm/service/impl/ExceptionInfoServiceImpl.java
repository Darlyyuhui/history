package com.xiangxun.atms.module.eventalarm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.eventalarm.domain.ExceptionInfo;
import com.xiangxun.atms.module.eventalarm.domain.ExceptionInfoSearch;
import com.xiangxun.atms.module.eventalarm.mapper.ExceptionInfoMapper;
import com.xiangxun.atms.module.eventalarm.service.ExceptionInfoService;

/**
 * 工单异常上报业务逻辑接口实现类
 * @author kouyunhao
 *
 */
@Service("exceptionInfoService")
public class ExceptionInfoServiceImpl extends AbstractBaseService<ExceptionInfo, ExceptionInfoSearch> implements ExceptionInfoService {

	@Resource
	ExceptionInfoMapper exceptionInfoMapper;
	
	@Override
	protected BaseMapper<ExceptionInfo, ExceptionInfoSearch> getBaseMapper() {
		return exceptionInfoMapper;
	}

}
