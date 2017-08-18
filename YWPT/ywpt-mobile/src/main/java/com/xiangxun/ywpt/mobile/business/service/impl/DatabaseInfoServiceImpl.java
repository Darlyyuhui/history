package com.xiangxun.ywpt.mobile.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.ywpt.mobile.business.dao.DatabaseInfoMapper;
import com.xiangxun.ywpt.mobile.business.domain.DatabaseInfo;
import com.xiangxun.ywpt.mobile.business.service.DatabaseInfoService;

@Service("DatabaseInfoService")
public class DatabaseInfoServiceImpl implements DatabaseInfoService{

	
	@Resource
	DatabaseInfoMapper databaseInfoMapper;
	
	@Override
	public List<DatabaseInfo> databaseDetails(DatabaseInfo databaseInfo) {
		
		List<DatabaseInfo> databaseList = databaseInfoMapper.databaseDetails(databaseInfo);
		// TODO Auto-generated method stub
		return databaseList;
	}


}
