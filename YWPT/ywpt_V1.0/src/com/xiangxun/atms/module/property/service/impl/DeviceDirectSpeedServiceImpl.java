package com.xiangxun.atms.module.property.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.property.domain.DeviceDirectSpeed;
import com.xiangxun.atms.module.property.domain.DeviceDirectSpeedSearch;
import com.xiangxun.atms.module.property.mapper.DeviceDirectSpeedMapper;
import com.xiangxun.atms.module.property.service.DeviceDirectSpeedService;


/***
 * 设备方向车道 服务接口类
 * @author yantao
 */

@Service("deviceDirectSpeedService")
public class DeviceDirectSpeedServiceImpl extends AbstractBaseService<DeviceDirectSpeed, DeviceDirectSpeedSearch> implements DeviceDirectSpeedService {

	@Resource
	DeviceDirectSpeedMapper deviceDirectSpeedMapper;
	
	@Resource
	Cache cache;
	
	@Override
	public List<DeviceDirectSpeed> findAll() {
		DeviceDirectSpeedSearch search = new DeviceDirectSpeedSearch();
		search.createCriteria().andIdIsNotNull();
		return deviceDirectSpeedMapper.selectByExample(search);
	}
	
	@Override
	protected BaseMapper<DeviceDirectSpeed, DeviceDirectSpeedSearch> getBaseMapper() {
		return deviceDirectSpeedMapper;
	}
	
	
	@Override
	public Page getDeviceDirectSpeedByCondition(int pageNo,int pageSize, String sortType) {
		DeviceDirectSpeedSearch search = new DeviceDirectSpeedSearch();
		if(StringUtils.notEmpty(sortType)){
			search.setOrderByClause(sortType);
		}
		Page page = selectByExample(search, pageNo, pageSize);
		return page;
	}

	@Override
	public List<DeviceDirectSpeed> getDeviceDirectSpeedByDeviceCode(String deviceCode) {
		return deviceDirectSpeedMapper.getDeviceDirectSpeedByDeviceCode(deviceCode);
	}

	@Override
	public void deleteByDeviceCode(String deviceCode) {
	    deviceDirectSpeedMapper.deleteByDeviceCode(deviceCode);
	}
	
	
}
