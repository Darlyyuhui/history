package com.xiangxun.atms.module.property.mapper;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.VideoInfo;
import com.xiangxun.atms.module.property.domain.VideoInfoSearch;


public interface VideoInfoMapper extends BaseMapper<VideoInfo,VideoInfoSearch>{
	
	public Integer getMaxCodeNum();
	
	public Integer countByCode(String code);
	
	//根据道路ID 获得 视频设备列表
	List<VideoInfo> selectDevlistByRoadId(String roadId);
	
	//根据部门ID 获得 视频设备列表
	List<VideoInfo> selectDevlistByOrgId(String orgID);
	
	List<VideoInfo> getListByCondition(Map<String, Object> map);
	
	void insertHistoryRecord(String id);
 	
 	void updatePrimaryKey(String afterId, String beforeId);
    
 	//根据服务厂商ID获取监控设备信息
	List<VideoInfo> getListByFactoryid(Map<String, Object> map);
}