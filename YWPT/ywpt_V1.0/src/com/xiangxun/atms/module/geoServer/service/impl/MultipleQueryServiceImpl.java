package com.xiangxun.atms.module.geoServer.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.module.geoServer.domain.MultipleQueryInfo;
import com.xiangxun.atms.module.geoServer.mapper.MultipleQueryInfoMapper;
import com.xiangxun.atms.module.geoServer.service.MultipleQueryService;
@Service
public class MultipleQueryServiceImpl implements MultipleQueryService{
 @Resource 
 MultipleQueryInfoMapper multipleQueryInfoMapper;	
	@Override
	public List<MultipleQueryInfo> searchMultipleQueryInfo(Map<String, Object> map) {
		return multipleQueryInfoMapper.searchMultipleQueryInfo(map);
	}
	@Override
	public List<MultipleQueryInfo> identifyMultipleQueryInfo(Map<String, Object> map) {	
		return multipleQueryInfoMapper.identifyMultipleQueryInfo(map);
	}
}
