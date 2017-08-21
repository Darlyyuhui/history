package com.xiangxun.atms.module.property.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.property.domain.DatabaseInfo;
import com.xiangxun.atms.module.property.domain.ProjectInfo;
import com.xiangxun.atms.module.property.domain.ProjectInfoSearch;

/**
 * 平台软件信息业务逻辑接口
 * @author kouyunhao
 *
 */
public interface ProjectInfoService extends BaseService<ProjectInfo, ProjectInfoSearch> {
	
	List<ProjectInfo> findAll();
	
	List<ProjectInfo> findByIp(String ip);
	
	List<ProjectInfo> findByName(String name);
	
	/**
	 * 根据厂商ID获取平台软件信息
	 * @param factoryId
	 * @param menuid
	 * @return
	 */
	public List<ProjectInfo> getProjectListByFactoryid(String factoryId);
	
	/***
	 * 导出EXCEL专用
	 * @return
	 */
	public List<ProjectInfo> selectByExampleDiy(Map<String, Object> searchParams,String sortType,String menuid);
	

}
