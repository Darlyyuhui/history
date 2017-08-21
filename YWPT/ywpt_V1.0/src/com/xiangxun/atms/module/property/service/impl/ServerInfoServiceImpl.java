package com.xiangxun.atms.module.property.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.common.user.service.DepartmentAuthorityFilter;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.property.domain.ServerInfo;
import com.xiangxun.atms.module.property.domain.ServerInfoSearch;
import com.xiangxun.atms.module.property.mapper.ServerInfoMapper;
import com.xiangxun.atms.module.property.service.ServerInfoService;

/**
 * 场内设备管理-服务器信息业务逻辑接口
 * @author kouyunhao
 *
 */
@Service("serverInfoService")
public class ServerInfoServiceImpl extends AbstractBaseService<ServerInfo, ServerInfoSearch> implements ServerInfoService {

	@Resource
	Cache cache;
	
	@Resource
	ServerInfoMapper serverInfoMapper;
	
	@Resource
	DepartmentAuthorityFilter departmentAuthorityFilter;
	
	@Override
	public List<ServerInfo> findAll() {
		ServerInfoSearch example = new ServerInfoSearch();
		example.createCriteria();
		return serverInfoMapper.selectByExample(example);
	}

	@Override
	protected BaseMapper<ServerInfo, ServerInfoSearch> getBaseMapper() {
		return serverInfoMapper;
	}

	@Override
	public List<ServerInfo> findByIp(String ip) {
		ServerInfoSearch example = new ServerInfoSearch();
		example.createCriteria().andServeripEqualTo(ip);
		return serverInfoMapper.selectByExample(example);
	}

	@Override
	public List<ServerInfo> findByName(String name) {
		ServerInfoSearch example = new ServerInfoSearch();
		example.createCriteria().andNameEqualTo(name);
		return serverInfoMapper.selectByExample(example);
	}

	@Override
	public List<ServerInfo> findByCode(String code) {
		ServerInfoSearch example = new ServerInfoSearch();
		example.createCriteria().andCodeEqualTo(code);
		return serverInfoMapper.selectByExample(example);
	}

	@Override
	public void saveHistoryRecord(String id) {
		serverInfoMapper.insertHistoryRecord(id);
		refreshCache();
	}

	@Override
	public void updatePrimaryKey(String afterId, String beforeId) {
		serverInfoMapper.updatePrimaryKey(afterId, beforeId);
		refreshCache();
	}
	
	private void refreshCache() {
		List<ServerInfo> caches =  this.findAll();
		Table<String, String, String> table = HashBasedTable.create();
		for (ServerInfo server : caches) {
			//存储的对象为table
			table.put(server.getId(), ServerInfo.class.getSimpleName(), server.getName()+" [ "+server.getServerip()+" ] ");
		}
		cache.put(ServerInfo.class.getSimpleName(), table);
	}
	
	/**
	 * 根据厂商ID获取服务器设备信息
	 * @param factoryId
	 * @param menuid
	 * @return
	 */
	@Override
	public List<ServerInfo> getServerListByFactoryid(String factoryId, String menuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		//厂商ID
		map.put("factoryId", factoryId);
		//添加数据权限
		map = departmentAuthorityFilter.filter(map, menuid);
		List<ServerInfo> list = serverInfoMapper.getListByFactoryid(map);
		return list;
	}

	/***
	 * 导出EXCEL专用
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ServerInfo> selectByExampleDiy(
			Map<String, Object> map, String sortType, String menuid) {
		if (map != null) {
			if (StringUtils.notEmpty(map.get("name") + "")) {
				map.put("name", "%" + map.get("name").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("code") + "")) {
				map.put("code", "%" + map.get("code").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("serverip") + "")) {
				map.put("serverip", "%" + map.get("serverip").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("orgId") + "")) {
				map.put("orgId", "%" + map.get("orgId").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("orgNames") + "")) {
				map.put("orgNames", "%" + map.get("orgNames").toString() + "%");
			}
		}
		if (StringUtils.notEmpty(sortType)) {
			map.put("sortColumn", sortType);
		}
		Page page = getListByCondition(map, 1, 2000, sortType, menuid);
		return page.getResult();
	}
	
	
	
}
