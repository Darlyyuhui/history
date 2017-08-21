package com.xiangxun.atms.module.geoServer.mapper;

import java.util.List;
import java.util.Map;

public interface RouteGeometryMapper {
	 void save(Map<String, String> map);
	 void updateByForeignid(Map<String, String> map);	 
}
