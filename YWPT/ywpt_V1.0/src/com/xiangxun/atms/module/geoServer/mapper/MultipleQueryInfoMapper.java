package com.xiangxun.atms.module.geoServer.mapper;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.module.geoServer.domain.MultipleQueryInfo;

public interface MultipleQueryInfoMapper {
	   List<MultipleQueryInfo>	searchMultipleQueryInfo(Map<String, Object> map);
	   List<MultipleQueryInfo>	identifyMultipleQueryInfo(Map<String, Object> map);
	   // 根据几何体查询设备信息，返回设备道路信息
	   List<Map<String, Object>>	queryDevice(String geometryText);
	   // 查询所有设备信息，返回设备道路信息
	   List<Map<String, Object>>	queryAllDevice();
	   // 根据道路名称查询模糊查询道路完整名
	   List<String> queryRoadByName(Map<String, String> map);
	   List<MultipleQueryInfo> getRoadGeometryByNames(Map<String, String[]> map);
}
