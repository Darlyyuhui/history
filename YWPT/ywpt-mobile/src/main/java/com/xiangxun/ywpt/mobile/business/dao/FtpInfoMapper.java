package com.xiangxun.ywpt.mobile.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiangxun.ywpt.mobile.business.domain.FtpInfo;

@Mapper
public interface FtpInfoMapper {
	
	List<FtpInfo> ftpDetails(FtpInfo ftpInfo);
}