package com.xiangxun.ywpt.mobile.business.dao;

import org.apache.ibatis.annotations.Mapper;

import com.xiangxun.ywpt.mobile.business.domain.ServerInfo;

@Mapper
public interface ServerInfoMapper {


	ServerInfo serverDetails(ServerInfo serverInfo);

}