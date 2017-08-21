package com.xiangxun.atms.module.geoServer.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangxun.atms.common.road.service.RoadInfoService;
import com.xiangxun.atms.common.road.vo.RoadInfo;
import com.xiangxun.atms.module.geoServer.domain.MultipleQueryInfo;
import com.xiangxun.atms.module.geoServer.mapper.MultipleQueryInfoMapper;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.service.DeviceInfoService;

@Controller
@RequestMapping("openmap/multipleQuery")
public class MultipleQueryCtl {
	@Resource
	DeviceInfoService deviceInfoService;
	@Resource
	RoadInfoService roadInfoService;
	
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
	
	@RequestMapping(value="queryalldevice")
	@ResponseBody
	public List<Map<String, Object>> queryAllDevice() {
		return multipleQueryInfoMapper.queryAllDevice();
	}
	
	@RequestMapping(value="querydevice")
	@ResponseBody
	public List<Map<String, Object>> queryDevice(String geometryText) {
		if("".equals(geometryText)) return null;
		List<Map<String, Object>> list=multipleQueryInfoMapper.queryDevice(geometryText);
		for(int i=0,len=list.size(); i<len; i++) {
			Map<String, Object> item = list.get(i);
			String code = item.get("code")+"";
			String roadid = item.get("roadid")+"";
			DeviceInfo deviceInfo = deviceInfoService.selectDevlistByCode(code);
			RoadInfo roadInfo = roadInfoService.getById(roadid);
			String deviceName = "未知设备";
			if(null != deviceInfo)deviceName = deviceInfo.getName();
			String roadName = "未知道路";
			if(null != roadInfo)deviceName = roadInfo.getName();
			item.put("devicename", deviceName);
			item.put("roadname", roadName);
		}
		return list;
	}
	
}
