package com.xiangxun.ywpt.mobile.business.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.xiangxun.ywpt.mobile.business.dao.OperationsAppMapper;
import com.xiangxun.ywpt.mobile.business.domain.OperationsApp;
import com.xiangxun.ywpt.mobile.business.service.OperationsAppService;
import com.xiangxun.ywpt.mobile.config.parameter.CustomParameters;

@Service
public class OperationsAppServiceImpl implements OperationsAppService {

	@Resource
	OperationsAppMapper operationsAppMapper;
	@Resource
	CustomParameters custom;
	
	@Override
	public OperationsApp getNewVersion() {
		OperationsApp info = operationsAppMapper.getNewVersion();
		/*if (info == null) {
			return new OperationsApp();
		}
		info.setSaveUrl("http://193.169.100.153:8090/2017-06-28pm1454-dev-3.0.apk");*/
		return info;
	}

}
