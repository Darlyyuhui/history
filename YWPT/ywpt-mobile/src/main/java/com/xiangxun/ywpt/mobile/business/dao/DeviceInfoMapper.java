package com.xiangxun.ywpt.mobile.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiangxun.ywpt.mobile.business.domain.DeviceInfo;
import com.xiangxun.ywpt.mobile.business.domain.PerambulateInfo;

@Mapper
public interface DeviceInfoMapper {
	
	List<DeviceInfo> queryList(DeviceInfo deviceInfo);
	
	int countList(DeviceInfo deviceInfo);
	
	DeviceInfo deviceDetails(DeviceInfo deviceInfo);
	
	DeviceInfo deviceDetailByContions(PerambulateInfo perambulateInfo);


}