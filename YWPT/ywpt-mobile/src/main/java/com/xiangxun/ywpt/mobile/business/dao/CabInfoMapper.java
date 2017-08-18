package com.xiangxun.ywpt.mobile.business.dao;

import org.apache.ibatis.annotations.Mapper;

import com.xiangxun.ywpt.mobile.business.domain.CabInfo;
import com.xiangxun.ywpt.mobile.business.domain.PerambulateInfo;


@Mapper
public interface CabInfoMapper {

	CabInfo cabDetails(CabInfo cabInfo);
	
	CabInfo cabDetailByContions(PerambulateInfo perambulateInfo);


}