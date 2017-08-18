package com.xiangxun.atms.module.geoServer.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangxun.atms.module.geoServer.domain.MultipleQueryInfo;
import com.xiangxun.atms.module.geoServer.mapper.MultipleQueryInfoMapper;

@Controller
@RequestMapping("openmap/multipleQuery")
public class MultipleQueryCtl {

	@Resource 
	 MultipleQueryInfoMapper multipleQueryInfoMapper;
	// 开源地图基于关键字综合信息查询
	@RequestMapping(value="search")
	@ResponseBody
	public List<MultipleQueryInfo> multipleQueryInfoList(String name ,String type) {	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name",name);
		map.put("type",type);
		List<MultipleQueryInfo> list=multipleQueryInfoMapper.searchMultipleQueryInfo(map);
		return list;
	}
	// 开源地图基于空间关系综合信息查询
	@RequestMapping(value="identify")
	@ResponseBody 
	public List<MultipleQueryInfo> multipleidentifyInfoList(String geometryText) {	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("geometryText",geometryText);
		List<MultipleQueryInfo> list=multipleQueryInfoMapper.identifyMultipleQueryInfo(map);
		return list;
	}
	
	@RequestMapping(value="queryroad")
	@ResponseBody
	public List<String> queryRoadByName(String roadName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("roadName", roadName);
		List<String> list = multipleQueryInfoMapper.queryRoadByName(map);
		return list;
	}
	
	@RequestMapping(value="getRoadGeometry")
	@ResponseBody
	public List<MultipleQueryInfo> getRoadGeometry(HttpServletRequest req) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("roadNames", req.getParameterValues("roadName"));
		return multipleQueryInfoMapper.getRoadGeometryByNames(map);
	}
	
}
