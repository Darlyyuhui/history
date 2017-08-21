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
import com.xiangxun.atms.module.property.domain.DeviceCompanyInfo;
import com.xiangxun.atms.module.property.domain.DeviceCompanyInfoSearch;
import com.xiangxun.atms.module.property.domain.DeviceCompanyInfoSearch.Criteria;
import com.xiangxun.atms.module.property.mapper.DeviceCompanyInfoMapper;
import com.xiangxun.atms.module.property.service.DeviceCompanyInfoService;


/***
 * 设备提供公司 服务接口类
 * @author yantao
 */

@Service("deviceCompanyInfoService")
public class DeviceCompanyInfoServiceImpl extends AbstractBaseService<DeviceCompanyInfo, DeviceCompanyInfoSearch> implements DeviceCompanyInfoService {

	@Resource
	DeviceCompanyInfoMapper deviceCompanyInfoMapper;
	
	@Resource
	Cache cache;
	
	
	@Override
	protected BaseMapper<DeviceCompanyInfo, DeviceCompanyInfoSearch> getBaseMapper() {
		return deviceCompanyInfoMapper;
	}
	
	
	@Override
	public Page getCompanyInfoByCondition(Map<String, Object> map,int pageNo,int pageSize, String sortType) {
		DeviceCompanyInfoSearch search = new DeviceCompanyInfoSearch();
		Criteria criteria = search.createCriteria();
		if(map!=null){
			if(StringUtils.notEmpty(map.get("name")+"")){
				criteria.andNameLike("%"+map.get("name").toString()+"%");
			}
			if(StringUtils.notEmpty(map.get("contactperson")+"")){
				criteria.andContactpersonLike("%"+map.get("contactperson").toString()+"%");
			}
			if(StringUtils.notEmpty(map.get("contactphone")+"")){
				criteria.andContactphoneLike("%"+map.get("contactphone").toString()+"%");
			}
		}
		if(StringUtils.notEmpty(sortType)){
			search.setOrderByClause(sortType);
		}
		Page page = selectByExample(search, pageNo, pageSize);
		return page;
	}
	
	// =============== 刷新缓存 重载父类 ===========================
	

	@Override
	public List<DeviceCompanyInfo> findAll() {
		DeviceCompanyInfoSearch search = new DeviceCompanyInfoSearch();
		search.createCriteria().andIdIsNotNull();
		return deviceCompanyInfoMapper.selectByExample(search);
	}
	
	private void refreshCache() {
		List<DeviceCompanyInfo> caches =  findAll();
		Table<String, String, String> table = HashBasedTable.create();
		for (DeviceCompanyInfo companyInfo : caches) {
			//存储的对象为table
			table.put(companyInfo.getId(), DeviceCompanyInfo.class.getSimpleName(), companyInfo.getName());
		}
		cache.put(DeviceCompanyInfo.class.getSimpleName(), table);
	}
	
	
	@Override
	@Transactional
	public int save(DeviceCompanyInfo companyInfo) {
		int result = super.save(companyInfo);
		if(result > 0){
			refreshCache();
		}
		return result;
	}
	
	@Transactional
	public int deleteByExample(DeviceCompanyInfoSearch example) {
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
	public int deleteById(String id,DeviceCompanyInfo record) {
		int result =  super.deleteById(id,record);
		if(result > 0){
		    refreshCache();
		}
	    return result;
	}

	@Override
	@Transactional
	public int updateByExample(DeviceCompanyInfo record, DeviceCompanyInfoSearch example) {
		int result =  super.updateByExample(record, example);
		//刷新缓存
	    refreshCache();
	    return result;
	}

	@Override
	@Transactional
	public int updateByExampleSelective(DeviceCompanyInfo record, DeviceCompanyInfoSearch example) {
		int result =  super.updateByExampleSelective(record, example);
		//刷新缓存
	    refreshCache();
	    return result;
	}

	@Override
	@Transactional
	public int updateById(DeviceCompanyInfo record) {
		int result =  super.updateById(record);
		//刷新缓存
	    refreshCache();
	    return result;
	}
	
	@Override
	@Transactional
	public int updateByIdSelective(DeviceCompanyInfo record) {
		int result =  super.updateByIdSelective(record);
	    refreshCache();
	    return result;
	}
	
}
