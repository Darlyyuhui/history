package com.xiangxun.atms.module.property.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.property.domain.DeviceTypeInfo;
import com.xiangxun.atms.module.property.domain.DeviceTypeInfoSearch;
import com.xiangxun.atms.module.property.mapper.DeviceTypeInfoMapper;
import com.xiangxun.atms.module.property.service.DeviceTypeInfoService;
/**
 * 设备类型 服务接口
 * @author YanTao
 * @Apr 19, 2013 2:07:20 PM
 */
@Service("deviceTypeInfoService")
public class DeviceTypeInfoServiceImpl extends AbstractBaseService<DeviceTypeInfo, DeviceTypeInfoSearch> implements DeviceTypeInfoService {

	@Resource
	DeviceTypeInfoMapper deviceTypeInfoMapper;
	

	@Resource
	Cache cache;
	
	
	@Override
	protected BaseMapper<DeviceTypeInfo, DeviceTypeInfoSearch> getBaseMapper() {
		return deviceTypeInfoMapper;
	}

	
	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.deptment.service.DepartmentService#getChildren(java.lang.String)
	 */
	@Override
	public List<DeviceTypeInfo> getChildren(String id) {
		Assert.notNull("id must not be null");
		return deviceTypeInfoMapper.getLeafNodeById(id);
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.deptment.service.DepartmentService#hasChild(java.lang.String)
	 */
	@Override
	public boolean hasChild(String id) {
		Assert.notNull("id must not be null");
		return deviceTypeInfoMapper.hasChild(id)>0?true:false;
	}
	
	
	//===================== 基本操作方法 缓存刷新 ============================
	
	
	@Override
	public List<DeviceTypeInfo> findAll() {
		DeviceTypeInfoSearch search = new DeviceTypeInfoSearch();
		search.createCriteria().andIdIsNotNull();
		List<DeviceTypeInfo> deviceTypeList = deviceTypeInfoMapper.selectByExample(search);
		return deviceTypeList;
	}
	
	private void refreshCache() {
		List<DeviceTypeInfo> caches =  findAll();
		Table<String, String, String> table = HashBasedTable.create();
		for (DeviceTypeInfo deviceTypeInfo : caches) {
			//存储的对象为table
			table.put(deviceTypeInfo.getId(), DeviceTypeInfo.class.getSimpleName(), deviceTypeInfo.getName());
		}
		cache.put(DeviceTypeInfo.class.getSimpleName(), table);
		
	}

	
	@Override
	public int save(DeviceTypeInfo deviceTypeInfo) {
		String id = deviceTypeInfo.getPid();
		if(!id.equals("")){
			if(id.equals("00")){
				deviceTypeInfo.setLevels(1);
			}else{
				int level = deviceTypeInfoMapper.selectByPrimaryKey(id).getLevels();
				deviceTypeInfo.setLevels(level+1);
			}
		}
//		int level = deviceTypeInfoMapper.selectByPrimaryKey(id).getLevels();
//		deviceTypeInfo.setLevels(level+1);
		int result = super.save(deviceTypeInfo);
		if(result > 0){
			refreshCache();
		}
		return result;
	}
	
	@Transactional
	public int deleteByExample(DeviceTypeInfoSearch example) {
		int result = super.deleteByExample(example);
		if(result > 0){
		    refreshCache();
		}
	    return result;
	}
	
	@Override
	@Transactional
	public int deleteById(String id) {
		int result =  super.deleteById(id);
		if(result > 0){
		    refreshCache();
		}
	    return result;
	}

	@Override
	@Transactional
	public int updateByExample(DeviceTypeInfo record, DeviceTypeInfoSearch example) {
		int result =  super.updateByExample(record, example);
		//刷新缓存
	    refreshCache();
	    return result;
	}

	@Override
	@Transactional
	public int updateByExampleSelective(DeviceTypeInfo record, DeviceTypeInfoSearch example) {
		int result =  super.updateByExampleSelective(record, example);
		//刷新缓存
	    refreshCache();
	    return result;
	}

	@Override
	@Transactional
	public int updateById(DeviceTypeInfo record) {
		int result =  super.updateById(record);
		//刷新缓存
	    refreshCache();
	    return result;
	}
	
	@Override
	@Transactional
	public int updateByIdSelective(DeviceTypeInfo record) {
		int result =  super.updateByIdSelective(record);
		if(result > 0){
			refreshCache();
		}
	    return result;
	}
	
	
}
