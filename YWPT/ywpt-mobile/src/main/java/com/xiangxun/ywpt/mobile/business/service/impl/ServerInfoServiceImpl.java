package com.xiangxun.ywpt.mobile.business.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.ywpt.mobile.business.dao.ServerInfoMapper;
import com.xiangxun.ywpt.mobile.business.domain.ServerInfo;
import com.xiangxun.ywpt.mobile.business.service.ServerInfoService;


@Service("ServerInfoService")
public class ServerInfoServiceImpl implements ServerInfoService{

	@Resource
	ServerInfoMapper serverInfoMapper;
	
	/**
	 * 查询某一个服务器设备的详情
	 * @author miaoxu
	 *
	 */
	@Override
	public ServerInfo serverDetails(ServerInfo serverInfo) {
		ServerInfo server = serverInfoMapper.serverDetails(serverInfo);
		return server;
	}
	
}
