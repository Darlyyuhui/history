package com.xiangxun.atms.module.property.service;


import java.util.List;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.property.domain.DeviceDirectSpeed;
import com.xiangxun.atms.module.property.domain.DeviceDirectSpeedSearch;

/***
 * FTP相关的 服务接口类
 * 
 * @author yantao
 */
public interface DeviceDirectSpeedService extends BaseService<DeviceDirectSpeed, DeviceDirectSpeedSearch> {

	/***
	 * 获取分页列表数据
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getDeviceDirectSpeedByCondition(int pageNo, int pageSize, String sortType);
	
	/***
	 * 获取所有的
	 * @return
	 */
	public List<DeviceDirectSpeed> findAll();
	
	
	public List<DeviceDirectSpeed> getDeviceDirectSpeedByDeviceCode(String deviceCode);
	
	public void deleteByDeviceCode(String deviceCode);
	
	
}
