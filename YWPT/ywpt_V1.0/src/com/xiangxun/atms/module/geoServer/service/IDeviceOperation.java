package com.xiangxun.atms.module.geoServer.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.module.geoServer.domain.DevicePoint;

public interface IDeviceOperation {
	
	/**
	 * 根据设备code数组查询对应的设备信息
	 * @param codes
	 * @return
	 * @throws Exception
	 */
	public List<DevicePoint> getDeviceByCodes(String[] codes)throws Exception;
	
	/**
	 * 通过道路名称模糊查询GIS库中的道路信息列表
	 * @param roadName 道路名称
	 * @return List<Map<String, Object>> 道路信息集合
	 * @throws Exception
	 */
	public List<Map<String, Object>> getMapRoadInfo(String roadName)throws Exception;
	
	/**
	 * 通过道路名称模糊查询GIS库中的道路名称列表
	 * @param roadName 道路名称
	 * @return List<String> 道路名集合
	 * @throws Exception
	 */
	public List<String> getMapRoad(String roadName)throws Exception;

	/**
	 * 在map中添加道路上的设备点
	 * @param point
	 * @return int
	 * @throws Exception 
	 */
	public int addDevice(DevicePoint point) throws Exception;
	
	/**
	 * 
	 * @param point 需要删除的信息对象
	 * @return int
	 * @throws Exception
	 */
	public int deleteDevice(DevicePoint point) throws Exception;
	/**
	 * 
	 * @param point 需要修改的设备信息
	 * @return int
	 * @throws Exception
	 */
	public int updateDevice(DevicePoint point) throws Exception;
}


