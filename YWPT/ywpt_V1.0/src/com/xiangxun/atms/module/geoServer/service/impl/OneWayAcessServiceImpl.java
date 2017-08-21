package com.xiangxun.atms.module.geoServer.service.impl;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;


import com.xiangxun.atms.module.geoServer.domain.OneWayAcessInfo;
import com.xiangxun.atms.module.geoServer.mapper.OneWayAcessInfoMapper;
import com.xiangxun.atms.module.geoServer.service.OneWayAcessService;
@Service
public class OneWayAcessServiceImpl implements OneWayAcessService {

	@Resource
	OneWayAcessInfoMapper oneWayAcessInfoMapper;


	@Override
	public List<OneWayAcessInfo> searchOneWayAcessInfo(Map<String, Object> map) {
		return oneWayAcessInfoMapper.searchOneWayAcessInfo(map);
	}
	@Override
	public void addOneWayAcessInfo(Map<String, Object> map) {
		oneWayAcessInfoMapper.addOneWayAcessInfo(map);
	}
	
	@Override
	public void deleteOneWayAcess(Map<String, Object> map) {
		oneWayAcessInfoMapper.deleteOneWayAcess(map);
	}
	@Override
	public void updateOneWayAcessInfo(Map<String, Object> map) {
		oneWayAcessInfoMapper.updateOneWayAcessInfo(map);
	}
}
