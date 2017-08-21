package com.xiangxun.atms.module.property.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.property.domain.DatabaseInfo;
import com.xiangxun.atms.module.property.domain.DatabaseInfoSearch;
import com.xiangxun.atms.module.property.domain.ServerInfo;
import com.xiangxun.atms.module.property.mapper.DatabaseInfoMapper;
import com.xiangxun.atms.module.property.service.DatabaseInfoService;

/**
 * 数据库信息业务逻辑接口实现类
 * @author kouyunhao
 *
 */
@Service("databaseInfoService")
public class DatabaseInfoServiceImpl extends AbstractBaseService<DatabaseInfo, DatabaseInfoSearch> implements DatabaseInfoService {

	@Resource
	DatabaseInfoMapper databaseInfoMapper;
	
	@Override
	protected BaseMapper<DatabaseInfo, DatabaseInfoSearch> getBaseMapper() {
		return databaseInfoMapper;
	}

	@Override
	public List<DatabaseInfo> findAll() {
		DatabaseInfoSearch example = new DatabaseInfoSearch();
		example.createCriteria();
		return databaseInfoMapper.selectByExample(example);
	}

	@Override
	public List<DatabaseInfo> findByIp(String ip) {
		DatabaseInfoSearch example = new DatabaseInfoSearch();
		example.createCriteria().andIpEqualTo(ip);
		return databaseInfoMapper.selectByExample(example);
	}

	@Override
	public List<DatabaseInfo> findByName(String name) {
		DatabaseInfoSearch example = new DatabaseInfoSearch();
		example.createCriteria().andNameEqualTo(name);
		return databaseInfoMapper.selectByExample(example);
	}
	
	@Override
	public List<DatabaseInfo> getDatabaseListByFactoryid(String factoryId) {
		DatabaseInfoSearch example = new DatabaseInfoSearch();
		example.createCriteria().andFactoryIdEqualTo(factoryId);
		return databaseInfoMapper.selectByExample(example);
	}
	
	/***
	 * 导出EXCEL专用
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DatabaseInfo> selectByExampleDiy(
			Map<String, Object> map, String sortType, String menuid) {
		if (map != null) {
			if (StringUtils.notEmpty(map.get("name") + "")) {
				map.put("name", "%" + map.get("name").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("dialect") + "")) {
				map.put("dialect", "%" + map.get("dialect").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("ip") + "")) {
				map.put("ip", "%" + map.get("ip").toString() + "%");
			}
		}
		if (StringUtils.notEmpty(sortType)) {
			map.put("sortColumn", sortType);
		}
		
		//Page page = databaseInfoService.getListByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);

		
		//Page page = getListByCondition(map, 1, 2000, sortType, menuid);
		Page page = getListByCondition(map, 1, 2000, sortType);

		return page.getResult();
	}

}
