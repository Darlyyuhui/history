package com.xiangxun.ywpt.mobile.business.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.ywpt.mobile.business.dao.CabInfoMapper;
import com.xiangxun.ywpt.mobile.business.domain.CabInfo;
import com.xiangxun.ywpt.mobile.business.domain.PerambulateInfo;
import com.xiangxun.ywpt.mobile.business.service.CabInfoService;

@Service("CabInfoService")
public class CabInfoServiceImpl implements CabInfoService{

	@Resource
	CabInfoMapper cabInfoMapper;
	
	@Override
	public CabInfo cabDetails(CabInfo cabInfo) {
		CabInfo cab= cabInfoMapper.cabDetails(cabInfo);
		return cab;
	}

	@Override
	public CabInfo cabDetailByContions(PerambulateInfo perambulateInfo) {
		CabInfo cabInfo = cabInfoMapper.cabDetailByContions(perambulateInfo);
		return cabInfo;
	}

}
