package com.xiangxun.ywpt.mobile.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiangxun.ywpt.mobile.business.domain.ProjectInfo;

@Mapper
public interface ProjectInfoMapper {

	List<ProjectInfo> projectDetails(ProjectInfo projectInfo);

}