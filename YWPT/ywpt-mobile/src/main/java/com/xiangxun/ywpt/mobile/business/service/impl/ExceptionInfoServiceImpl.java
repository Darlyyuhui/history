package com.xiangxun.ywpt.mobile.business.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.ywpt.mobile.business.dao.ExceptionInfoMapper;
import com.xiangxun.ywpt.mobile.business.domain.ExceptionInfo;
import com.xiangxun.ywpt.mobile.business.service.ExceptionInfoService;
@Service("exceptionInfoService")
public class ExceptionInfoServiceImpl implements ExceptionInfoService {
@Resource ExceptionInfoMapper exceptionInfoMapper;
	@Override
	public void save(ExceptionInfo exc) {
		exceptionInfoMapper.save(exc);

	}

}
