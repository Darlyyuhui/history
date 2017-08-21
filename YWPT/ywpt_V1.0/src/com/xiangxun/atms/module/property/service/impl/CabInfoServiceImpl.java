package com.xiangxun.atms.module.property.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.xiangxun.atms.module.property.domain.CabInfoSearch.Criteria;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.property.domain.CabInfo;
import com.xiangxun.atms.module.property.domain.CabInfoSearch;
import com.xiangxun.atms.module.property.domain.CabinetAbnormalType;
import com.xiangxun.atms.module.property.domain.DatabaseInfo;
import com.xiangxun.atms.module.property.domain.DatabaseInfoSearch;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.ServerInfo;
import com.xiangxun.atms.module.property.domain.ServerInfoSearch;
import com.xiangxun.atms.module.property.mapper.CabInfoMapper;

import com.xiangxun.atms.module.property.service.CabInfoService;

@Service("cabInfoService")
public class CabInfoServiceImpl extends AbstractBaseService<CabInfo, CabInfoSearch> implements CabInfoService {
	@Resource
	CabInfoMapper cabInfoMapper;
	@Resource 
	JdbcTemplate jdbc;

	@Override
	protected BaseMapper<CabInfo, CabInfoSearch> getBaseMapper() {
		return cabInfoMapper;
	}

	@Override
	public List<CabInfo> findByName(String name) {
		CabInfoSearch example = new CabInfoSearch();
		example.createCriteria().andNameEqualTo(name);
		return cabInfoMapper.selectByExample(example);
	}

	@Override
	public List<CabInfo> findByIp(String ip) {
		CabInfoSearch search = new CabInfoSearch();
		search.createCriteria().andCodeIsNotNull().andIpEqualTo(ip);
		return cabInfoMapper.selectByExample(search);
	}

	@Override
	public Page getListByCondition(Map<String, Object> map, int pageNo, int pageSize, String sortType, String menuid) {
		return super.getListByCondition(map, pageNo, pageSize, sortType, menuid);
	}

	public List<CabInfo> selectAll() {
		return cabInfoMapper.getAll();

	}

	@Override
	public List<CabInfo> getCabListByFactoryid(String factoryId) {
		CabInfoSearch example = new CabInfoSearch();
		example.createCriteria().andFactoryIdEqualTo(factoryId);
		return cabInfoMapper.selectByExample(example);
	}	
	
	@Override
	public List<CabInfo> findByCode(String code) {
		CabInfoSearch example = new CabInfoSearch();
		example.createCriteria().andCodeEqualTo(code);
		return cabInfoMapper.selectByExample(example);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<CabInfo> selectByExampleDiy(Map<String, Object> map, String sortType, String menuid) {
		if (map != null) {
			if (StringUtils.notEmpty(map.get("name") + "")) {
				map.put("name", "%" + map.get("name").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("ip") + "")) {
				map.put("ip", "%" + map.get("ip").toString() + "%");
			}
			
			if (StringUtils.notEmpty(map.get("roadId") + "")) {
				if ("00".equals(map.get("roadId"))) {
					map.put("roadId", null);
				} else {
					map.put("roadId", "%" + map.get("roadId").toString() + "%");
				}
			}
			
		}
		if (StringUtils.notEmpty(sortType)) {
			map.put("sortColumn", sortType);
		}
		Page page = getListByCondition(map, 1, 2000, sortType, menuid);
		return page.getResult();
	}

}
