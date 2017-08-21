
package com.xiangxun.atms.module.sergrade.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.common.user.service.AuthorityFilterContext;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfoSearch;
import com.xiangxun.atms.module.sergrade.mapper.FactoryInfoMapper;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;

/**
 * 运维服务商信息服务
 * @author guikaiping
 * @version 1.0
 */
@Service("factoryInfoService")
public class FactoryInfoServiceImpl extends AbstractBaseService<FactoryInfo, FactoryInfoSearch> implements FactoryInfoService {

	@Resource
	FactoryInfoMapper factoryInfoMapper;
	
	@Resource
	Cache cache;
	
	@Resource(name="authorityFilterContext")
	AuthorityFilterContext authorityFilterContext;
	
	@Override
	protected BaseMapper<FactoryInfo, FactoryInfoSearch> getBaseMapper() {
		return factoryInfoMapper;
	}
	
	/***
	 * 查询运维服务商列表信息服务
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	@Override
	public Page getReport(Map<String, Object> map, int pageNo, int pageSize, String sortType, String menuid) {
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid, null);
		//获取分页数据
		List<FactoryInfo> reports = factoryInfoMapper.selectList(params,Page.getRowBounds(pageNo, pageSize));
		//获取记录总数
		int totalCount = factoryInfoMapper.selectTotal(params);
		
		return Page.getPage(totalCount, reports, pageNo, pageSize);
	}
	
	/***
     * 根据名称获取对象
     * @param model
     * @return FactoryInfo
     */
	@Override
	public List<FactoryInfo> getByName(String name) {
		FactoryInfoSearch search = new FactoryInfoSearch();
		search.createCriteria().andNameEqualTo(name);
		return factoryInfoMapper.selectByExample(search);
	}
	
	/**
	 * 处理查询参数
	 * @param map
	 * @param menuid
	 * @param stateType
	 * @return
	 */
	private Map<String, Object> filter(Map<String, Object> map, String menuid, String stateType) {
		//加入权限过滤
		Map<String, Object> params = authorityFilterContext.filter(map, menuid);
		return params;
	}

	@Override
	public List<FactoryInfo> findAll() {
		FactoryInfoSearch search = new FactoryInfoSearch();
		search.createCriteria();
		return factoryInfoMapper.selectByExample(search);
	}
	
	private void refreshCache() {
		List<FactoryInfo> caches =  this.findAll();
		Table<String, String, String> table = HashBasedTable.create();
		for (FactoryInfo factoryInfo : caches) {
			//存储的对象为table
			table.put(factoryInfo.getId(), FactoryInfo.class.getSimpleName(), factoryInfo.getName());
		}
		cache.put(FactoryInfo.class.getSimpleName(), table);
	}
	
	@Override
	@Transactional
	public int save(FactoryInfo factoryInfo) {
		int result = super.save(factoryInfo);
		if(result > 0){
			refreshCache();
		}
		return result;
	}
	
	@Transactional
	public int deleteByExample(FactoryInfoSearch example) {
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
	public int deleteById(String id,FactoryInfo record) {
		int result =  super.deleteById(id,record);
		if(result > 0){
		    refreshCache();
		}
	    return result;
	}

	@Override
	@Transactional
	public int updateByExample(FactoryInfo record, FactoryInfoSearch example) {
		int result =  super.updateByExample(record, example);
	    refreshCache();
	    return result;
	}

	@Override
	@Transactional
	public int updateByExampleSelective(FactoryInfo record, FactoryInfoSearch example) {
		int result =  super.updateByExampleSelective(record, example);
	    refreshCache();
	    return result;
	}

	@Override
	@Transactional
	public int updateById(FactoryInfo record) {
		int result =  super.updateById(record);
	    refreshCache();
	    return result;
	}
	
	@Override
	@Transactional
	public int updateByIdSelective(FactoryInfo record) {
		int result =  super.updateByIdSelective(record);
	    refreshCache();
	    return result;
	}
	
}
