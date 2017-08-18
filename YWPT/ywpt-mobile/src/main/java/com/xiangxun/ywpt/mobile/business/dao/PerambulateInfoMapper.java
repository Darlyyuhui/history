package com.xiangxun.ywpt.mobile.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiangxun.ywpt.mobile.business.domain.PerambulateInfo;

@Mapper
public interface PerambulateInfoMapper {
	
	void perambulateUp(PerambulateInfo perambulateInfo);
	
	List<PerambulateInfo> queryList(PerambulateInfo perambulateInfo);
	
	int countList(PerambulateInfo perambulateInfo);

}
