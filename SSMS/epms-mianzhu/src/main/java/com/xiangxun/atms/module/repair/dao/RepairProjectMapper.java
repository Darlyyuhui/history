package com.xiangxun.atms.module.repair.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.repair.vo.RepairProject;
import com.xiangxun.atms.module.repair.vo.RepairProjectSearch;

public interface RepairProjectMapper extends BaseMapper<RepairProject, RepairProjectSearch> {
	
	/**
	 * 保存项目与地块关系
	 */
	void saveProBlockLink(Map<String, Object> args);
	
	/**
	 * 删除项目与地块关系
	 */
	void deleteProBlockLink(Map<String, Object> args);
	
	List<Map<String, Object>> getLandBlocksById(Map<String, Object> args);
}