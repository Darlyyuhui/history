package com.xiangxun.atms.module.geoServer.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.module.geoServer.domain.DevicePoint;
import com.xiangxun.atms.module.geoServer.mapper.GeoServerDeviceInfoMapper;
import com.xiangxun.atms.module.geoServer.service.IDeviceOperation;

@Service("geoServerDeviceOperation")
public class GeoServerDeviceOperation implements IDeviceOperation {

	@Resource
	GeoServerDeviceInfoMapper geoServerDeviceInfoMapper;
	
	@Override
	public List<DevicePoint> getDeviceByCodes(String[] codes)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codes", codes);
		return geoServerDeviceInfoMapper.getDeviceByCodes(map);
	}
	
	@Override
	public int addDevice(DevicePoint point) throws Exception {
		return geoServerDeviceInfoMapper.addDeviceInfo(point);
	}

	@Override
	public int deleteDevice(DevicePoint point) throws Exception {
		return geoServerDeviceInfoMapper.deleteDevice(point);
	}

	@Override
	public List<String> getMapRoad(String roadName) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("roadName", roadName);
		List<String> list = geoServerDeviceInfoMapper.queryRoadByName(map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getMapRoadInfo(String roadName) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("roadName", roadName);
		return geoServerDeviceInfoMapper.getRoadInfoByName(map);
	}

	@Override
	public int updateDevice(DevicePoint point) throws Exception {
		return geoServerDeviceInfoMapper.updateDeviceInfo(point);
	}
}


