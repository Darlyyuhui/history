package com.xiangxun.ywpt.mobile.business.dao;

import org.apache.ibatis.annotations.Mapper;

import com.xiangxun.ywpt.mobile.business.domain.OperationsApp;


@Mapper
public interface OperationsAppMapper {

	OperationsApp getNewVersion();
	
}
