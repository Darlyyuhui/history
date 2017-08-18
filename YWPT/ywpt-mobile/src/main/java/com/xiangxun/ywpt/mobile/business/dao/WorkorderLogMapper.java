package com.xiangxun.ywpt.mobile.business.dao;

import org.apache.ibatis.annotations.Mapper;

import com.xiangxun.ywpt.mobile.business.domain.WorkorderLog;
@Mapper
public interface WorkorderLogMapper {
	public void save(WorkorderLog log);
		
	
}