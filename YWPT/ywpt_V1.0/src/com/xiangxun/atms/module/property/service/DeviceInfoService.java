package com.xiangxun.atms.module.property.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.DeviceInfoSearch;





/***
 * 设备管理服务接口类
 * @author yantao
 */
public interface DeviceInfoService extends BaseService<DeviceInfo,DeviceInfoSearch>{
	
	/***
	 * 获取用户的分页列表数据
	 * @param orgid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getDeviceInfoByCondition(Map<String, Object> map,int pageNo,int pageSize,String sortType,String menuid);
	
	/***
	 * 获取所有的
	 * @return
	 */
	public List<DeviceInfo> findAll();
	
	/***
	 * 获取设备编号后四位的最大值
	 * @return
	 */
	public Integer getMaxDeviceCodeNum();
	
	/***
	 * 根据设备编号获取部门id
	 * @param deviceCode
	 * @return
	 */
	public String getDepartmentIdByDeviceCode(String deviceCode);
	
	/***
	 * 根据设备编号获取道路id
	 * @param deviceCode
	 * @return
	 */
	public String getRoadIdByDeviceCode(String deviceCode);
	
	/***
	 * 获取部门全部信息
	 * @param deviceCode
	 * @return
	 */
	public List<DeviceInfo> getDepartmentIdByDeviceCode() ;
	
	/***
	 * 根据部门id获取其部门下的所有设备
	 * @param departmentId
	 * @return
	 */
	public List<String> getCodesByDepartmentId(List<String> departmentIds);
	
	/***
	 * 根据当前登陆用户，查符合权限的所有设备
	 * @return
	 */
	public List<DeviceInfo> getAllDeviceByAuthority();
	
	public List<DeviceInfo> getAllDeviceByAuthority(String menuid);
	
	/***
	 * 根据当前登陆用户，查符合权限的所有违法设备
	 * @return
	 */
	public List<DeviceInfo> getAllDeviceByAuthorityVio();
	
	/***
	 * 根据当前登陆用户 和 其他条件，综合查符合权限的所有设备
	 * @return
	 */
	public List<DeviceInfo> getAllDeviceByAuthority(Map<String,Object> map);
	
	/***
	 * 根据当前登陆用户 和 其他条件，综合查符合权限的所有设备
	 * @return
	 */
	public List<DeviceInfo> getAllDeviceByAuthority(Map<String,Object> map,String m);
	/***
	 * 根据道路ID 获得 设备列表
	 * @return
	 */
	public List<DeviceInfo> findDevlistByRoadId(String roadId);
	
	/***
	 * 根据部门ID 获得 设备列表
	 * @return
	 */
	public List<DeviceInfo> findDevlistByOrgId(String roadId);
	
	/***
	 * 根据IP 获得 设备列表 add by kouyunhao 2013-11-15
	 * @return
	 */
	public List<DeviceInfo> findByIp(String ip);
	
	/***
	 * 根据name 获得 设备列表
	 * @return
	 */
	public List<DeviceInfo> findByName(String name);
	
	/**
	 * 根据org_id查询devicecode
	 * @param sql
	 * @return
	 */
	List<String> getDeviceCode(String sql);
	/***
	 * 根据设备标号 查设备信息
	 * @return
	 */
	public DeviceInfo selectDevlistByCode(String code);
	
	/***
	 * 根据设备标号 查设备个数 用于后台2次验证设备编号重复问题
	 * @return
	 */
	public Integer countDeviceByCode(String code);
	
	
	/***
	 * 导出EXCEL专用
	 * @return
	 */
	public List<DeviceInfo> selectByExampleDiy(Map<String, Object> searchParams,String sortType,String menuid);
	
	//根据设备编号集合 获得设备列表
	public List<DeviceInfo> selectDevlistByCodeList(String devCodes);
	
	//查询当前用户所属部门下的设备。add by kouyunhao 2014-02-24
	public String findCurruserDeptDev(String userid);
	
	/***
	 * 根据设备功能获得设备列表
	 * @param deviceTypeId  设备功能编号
	 * @return
	 */
	public List<DeviceInfo>  getDevListByDeviceTypeCode(String deviceTypeCode);
	
	/**
	 * 获取所有的违法设备<br>
	 * xiongjie添加
	 * @return
	 */
	public List<DeviceInfo>  getAllVioDevList(String menuid);
	
	//获得所有设备功能含有 卡口检测 的设备
	public List<DeviceInfo> selectCrossDevList();
	
	/**
	 * 保存历史记录
	 * @param id
	 */
	public void saveHistoryRecord(String id);
	
	/**
	 * 修改主键
	 * @param afterId
	 * @param beforeId
	 */
	public void updatePrimaryKey(String afterId, String beforeId);
	
	/**
	 * 修改设备功能
	 * @param afterId
	 * @param beforeId
	 */
	public void updateDeviceType(String sql1, String sql2, String sql3, String sql4);
	
	/**
	 * 根据厂商ID获取卡口设备信息
	 * @param factoryId
	 * @param menuid
	 * @return
	 */
	public List<DeviceInfo> getDevListByFactoryid(String factoryId, String menuid);
}
