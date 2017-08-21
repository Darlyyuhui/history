package com.xiangxun.atms.module.property.service.impl;



import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.property.cache.DeviceCodeFtpCache;
import com.xiangxun.atms.module.property.domain.DeviceFtpInfo;
import com.xiangxun.atms.module.property.domain.DeviceFtpInfoSearch;
import com.xiangxun.atms.module.property.domain.DeviceFtpInfoSearch.Criteria;
import com.xiangxun.atms.module.property.mapper.DeviceFtpInfoMapper;
import com.xiangxun.atms.module.property.service.DeviceFtpInfoService;


/***
 * FTP相关的 服务接口类
 * @author yantao
 */

@Service("ftpService")
public class DeviceFtpInfoServiceImpl extends AbstractBaseService<DeviceFtpInfo, DeviceFtpInfoSearch> implements DeviceFtpInfoService {

	@Resource
	DeviceFtpInfoMapper deviceFtpInfoMapper;
	
	@Resource
	Cache cache;
	
	@Resource(name="deviceCodeFtpCache")
	DeviceCodeFtpCache deviceCodeFtpCache;
	
	@Override
	protected BaseMapper<DeviceFtpInfo, DeviceFtpInfoSearch> getBaseMapper() {
		return deviceFtpInfoMapper;
	}
	
	
	@Override
	public Page getFtpByCondition(Map<String, Object> map,int pageNo,int pageSize, String sortType) {
		DeviceFtpInfoSearch search = new DeviceFtpInfoSearch();
		Criteria criteria = search.createCriteria();
		if(map!=null){
			if(StringUtils.notEmpty(map.get("name")+"")){
				criteria.andNameLike("%"+map.get("name").toString()+"%");
			}
			if(StringUtils.notEmpty(map.get("ftpip")+"")){
				criteria.andFtpipLike("%"+map.get("ftpip").toString()+"%");
			}
			if(StringUtils.notEmpty(map.get("ftpport")+"")){
				criteria.andFtpportEqualTo(map.get("ftpport").toString());
			}
			if(StringUtils.notEmpty(map.get("ftpuser")+"")){
				criteria.andFtpuserEqualTo(map.get("ftpuser").toString());
			}
		}
		if(StringUtils.notEmpty(sortType)){
			search.setOrderByClause(sortType);
		}
		Page page = selectByExample(search, pageNo, pageSize);
		return page;
	}


	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.device.service.FtpService#getFtpByDeviceCode(java.lang.String)
	 */
	@Override
	public DeviceFtpInfo getFtpByDeviceCode(String deviceCode) {
		return deviceFtpInfoMapper.getFtpByDeviceCode(deviceCode);
	}
	
//===================== 基本操作方法 缓存刷新 ============================
	
	
	@Override
	public List<DeviceFtpInfo> findAll() {
		DeviceFtpInfoSearch search = new DeviceFtpInfoSearch();
		search.createCriteria().andIdIsNotNull();
		return deviceFtpInfoMapper.selectByExample(search);
	}
	
	private void refreshCache() {
		List<DeviceFtpInfo> caches =  findAll();
		Table<String, String, String> table = HashBasedTable.create();
		for (DeviceFtpInfo info : caches) {
			//存储的对象为table
			table.put(info.getId(), DeviceFtpInfo.class.getSimpleName(), info.getName());
		}
		cache.put(DeviceFtpInfo.class.getSimpleName(), table);
		
		try {
			deviceCodeFtpCache.init();
			logger.info("设备编号 ftp信息缓存刷新完成.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("[路口信息] 缓存刷新完成");
	}

	
	@Override
	public int save(DeviceFtpInfo info) {
		int result = super.save(info);
		if(result > 0){
			refreshCache();
		}
		return result;
	}
	
	@Transactional
	public int deleteByExample(DeviceFtpInfoSearch example) {
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
	public int updateByExample(DeviceFtpInfo record, DeviceFtpInfoSearch example) {
		int result =  super.updateByExample(record, example);
		//刷新缓存
	    refreshCache();
	    return result;
	}

	@Override
	@Transactional
	public int updateByExampleSelective(DeviceFtpInfo record, DeviceFtpInfoSearch example) {
		int result =  super.updateByExampleSelective(record, example);
		//刷新缓存
	    refreshCache();
	    return result;
	}

	@Override
	@Transactional
	public int updateById(DeviceFtpInfo record) {
		int result =  super.updateById(record);
		//刷新缓存
	    refreshCache();
	    return result;
	}
	
	@Override
	@Transactional
	public int updateByIdSelective(DeviceFtpInfo record) {
		int result =  super.updateByIdSelective(record);
		if(result > 0){
			refreshCache();
		}
	    return result;
	}


	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.common.device.service.FtpService#getByIp(java.lang.String)
	 */
	@Override
	public List<DeviceFtpInfo> getByIp(String ip) {
		DeviceFtpInfoSearch search = new DeviceFtpInfoSearch();
		search.createCriteria().andIdIsNotNull().andFtpipEqualTo(ip);
		return deviceFtpInfoMapper.selectByExample(search);
	}
	
}
