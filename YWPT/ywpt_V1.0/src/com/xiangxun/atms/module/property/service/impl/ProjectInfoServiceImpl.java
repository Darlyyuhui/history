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
import com.xiangxun.atms.module.property.domain.ProjectInfo;
import com.xiangxun.atms.module.property.domain.ProjectInfoSearch;
import com.xiangxun.atms.module.property.mapper.ProjectInfoMapper;
import com.xiangxun.atms.module.property.service.ProjectInfoService;

/**
 * 平台软件信息业务逻辑接口实现类
 * @author kouyunhao
 *
 */
@Service("projectInfoService")
public class ProjectInfoServiceImpl extends AbstractBaseService<ProjectInfo, ProjectInfoSearch> implements ProjectInfoService {

	@Resource
	ProjectInfoMapper projectInfoMapper;
	
	@Override
	protected BaseMapper<ProjectInfo, ProjectInfoSearch> getBaseMapper() {
		return projectInfoMapper;
	}
	
	@Override
	public List<ProjectInfo> findAll() {
		ProjectInfoSearch example = new ProjectInfoSearch();
		example.createCriteria();
		return projectInfoMapper.selectByExample(example);
	}

	@Override
	public List<ProjectInfo> findByIp(String ip) {
		ProjectInfoSearch example = new ProjectInfoSearch();
		example.createCriteria().andIpEqualTo(ip);
		return projectInfoMapper.selectByExample(example);
	}

	@Override
	public List<ProjectInfo> findByName(String name) {
		ProjectInfoSearch example = new ProjectInfoSearch();
		example.createCriteria().andNameEqualTo(name);
		return projectInfoMapper.selectByExample(example);
	}
	
	@Override
	public List<ProjectInfo> getProjectListByFactoryid(String factoryId) {
		ProjectInfoSearch example = new ProjectInfoSearch();
		example.createCriteria().andFactoryIdEqualTo(factoryId);
		return projectInfoMapper.selectByExample(example);
	}
	
	/***
	 * 导出EXCEL专用
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectInfo> selectByExampleDiy(
			Map<String, Object> map, String sortType, String menuid) {
		if (map != null) {
			if (StringUtils.notEmpty(map.get("name") + "")) {
				map.put("name", "%" + map.get("name").toString() + "%");
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
