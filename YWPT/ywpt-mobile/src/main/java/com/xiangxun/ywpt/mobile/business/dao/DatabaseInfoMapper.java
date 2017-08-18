package com.xiangxun.ywpt.mobile.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiangxun.ywpt.mobile.business.domain.DatabaseInfo;

@Mapper
public interface DatabaseInfoMapper {
	List<DatabaseInfo> databaseDetails(DatabaseInfo databaseInfo);

}