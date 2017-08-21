package com.xiangxun.atms.module.geoServer.mapper;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.module.geoServer.domain.DevicePoint;

public interface GeoServerDeviceInfoMapper {
	// 在根据设备code数组查询设备信息
	List<DevicePoint> getDeviceByCodes(Map<String, Object> map);
	// 在GeoServer端添加设备点
	int	addDeviceInfo(DevicePoint point);
	// 在GeoServer端删除设备点
	int	deleteDevice(DevicePoint point);
	// 在GeoServer端修改设备点
	int	updateDeviceInfo(DevicePoint point);
	// 根据道路名称查询模糊查询道路完整名
	List<String> queryRoadByName(Map<String, String> map);
	// 根据道路名称查询模糊查询道路完整名
	List<Map<String, Object>> getRoadInfoByName(Map<String, String> map);
}
