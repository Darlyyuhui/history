package com.xiangxun.atms.module.property.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.DeviceInfoSearch;

public interface DeviceInfoMapper extends BaseMapper<DeviceInfo,DeviceInfoSearch>{
	
	
	//获取上次自增编号
	Integer getMaxDeviceCodeNum();
	
	DeviceInfo getById(String id);
	
	List<DeviceInfo> selectOrgRoadDevCode();
	
	String selectRoadIdByDeviceCode(String deviceCode);
	
	List<String> selectCodesByDepartmentId(DeviceInfoSearch deviceInfoSearch);
	
	List<DeviceInfo> getListByCondition(Map<String, Object> map);
	//违法设备状态统计
	List<DeviceInfo> getListByConditionVio(Map<String, Object> map);
	//根据道路ID 获得 设备列表
	List<DeviceInfo> selectDevlistByRoadId(String roadId);
	
	//根据部门ID 获得 设备列表
	List<DeviceInfo> selectDevlistByOrgId(String orgID);
	
	//查询带状态信息的设备列表
    List<DeviceInfo> findDeviceByDatetime();
    
    //根据设备标号 查设备信息
    DeviceInfo selectDevlistByCode(String code);
    /**
     * 获取设备表中所有信息
     * @return List<DeviceInfo>
     */
    List<DeviceInfo> selectDeviceInfos();
    List<String> getDeviceCode(HashMap<String, Object> sql);
    
    //获取上次自增编号
	int countDeviceByCode(String code);
	
	//根据设备编号集合 获得设备列表
	List<DeviceInfo> selectDevlistByCodeList(Map<String,Object> params);
	
	//查询当前用户所属部门下的设备。add by kouyunhao 2014-02-24
	String findCurruserDeptDev(String userid);
	
	List<DeviceInfo> selectDevListByDeviceTypeCode(String deviceTypeCode);
	
	List<DeviceInfo> selectDevListByParentDevTypeCode(Map<String, Object> map);
	
	//获得所有设备功能含有 卡口检测 的设备
	List<DeviceInfo> selectCrossDevList();
	
 	void insertHistoryRecord(String id);
 	
 	void updatePrimaryKey(String afterId, String beforeId);
 	
 	void updateDeviceType(Map<String, Object> hashMap);
 	
 	//根据服务厂商ID获取卡口设备信息
 	List<DeviceInfo> getListByFactoryid(Map<String, Object> map);
}