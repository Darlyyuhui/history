package com.xiangxun.ywpt.mobile.business.dao;

import org.apache.ibatis.annotations.Mapper;

import com.xiangxun.ywpt.mobile.business.domain.ExceptionInfo;

@Mapper
public interface ExceptionInfoMapper {
   public void save(ExceptionInfo exc);
}
