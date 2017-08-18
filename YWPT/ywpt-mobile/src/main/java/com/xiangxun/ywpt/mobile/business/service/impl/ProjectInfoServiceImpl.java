package com.xiangxun.ywpt.mobile.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.ywpt.mobile.business.dao.ProjectInfoMapper;
import com.xiangxun.ywpt.mobile.business.domain.ProjectInfo;
import com.xiangxun.ywpt.mobile.business.service.ProjectInfoService;

@Service("ProjectInfoService")
public class ProjectInfoServiceImpl implements ProjectInfoService{

	
	@Resource
	ProjectInfoMapper projectInfoMapper;
	
	@Override
	public List<ProjectInfo> projectDetails(ProjectInfo projectInfo) {
		List<ProjectInfo> list = projectInfoMapper.projectDetails(projectInfo);
		return list;
	}

}
