package com.xiangxun.atms.module.geoServer.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.xiangxun.atms.module.geoServer.mapper.RouteGeometryMapper;
import com.xiangxun.atms.module.geoServer.service.RouteGeometryService;

public class RouteGeometryServiceImpl implements RouteGeometryService {
	@Resource
	RouteGeometryMapper routeGeometryMapper;
	
	 public void save(Map<String, String> map) {
		 routeGeometryMapper.save(map);
	 };
}
