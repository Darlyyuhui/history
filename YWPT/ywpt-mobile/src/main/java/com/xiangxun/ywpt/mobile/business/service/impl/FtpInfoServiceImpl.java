package com.xiangxun.ywpt.mobile.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.ywpt.mobile.business.dao.FtpInfoMapper;
import com.xiangxun.ywpt.mobile.business.domain.FtpInfo;
import com.xiangxun.ywpt.mobile.business.service.FtpInfoService;

@Service("FtpInfoService")
public class FtpInfoServiceImpl implements FtpInfoService{

	@Resource
	FtpInfoMapper ftpInfoMapper;
	
	@Override
	public List<FtpInfo> ftpDetails(FtpInfo ftpInfo) {
		
		List<FtpInfo> ftpList = ftpInfoMapper.ftpDetails(ftpInfo);
		return ftpList;

	}

}
