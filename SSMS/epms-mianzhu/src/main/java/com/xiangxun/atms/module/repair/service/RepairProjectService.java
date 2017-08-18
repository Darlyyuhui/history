package com.xiangxun.atms.module.repair.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.repair.vo.RepairProject;
import com.xiangxun.atms.module.repair.vo.RepairProjectSearch;

public interface RepairProjectService extends BaseService<RepairProject, RepairProjectSearch> {
	
	/**
	 * 保存项目与地块关系
	 */
	void saveProBlockLink(String proId, String[] blockId);
	
	/**
	 * 删除项目与地块关系
	 */
	void deleteProBlockLink(String proId);
	
	/**
	 * 获取土壤登记的地块信息
	 * @param regId
	 * @return
	 */
	List<String> getLandBlocksById(String proId);
	
	void saveInfo(RepairProject info, MultipartHttpServletRequest fileRequest);
	
	void updateInfo(RepairProject info, MultipartHttpServletRequest fileRequest);
}