package com.xiangxun.atms.module.property.service;


import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.property.domain.VideoInfo;
import com.xiangxun.atms.module.property.domain.VideoInfoSearch;



/***
 * 监控设备相关的 服务接口类
 * @author yantao
 */
public interface VideoInfoService extends BaseService<VideoInfo,VideoInfoSearch>{
	
	public Page getVideoInfoByCondition(Map<String, Object> map,int pageNo,int pageSize,String sortType,String menuid);
	
	//监控设备导出专用
	public List<VideoInfo> selectByExampleDiy(Map<String, Object> searchParams,String sortType);
	
	public Integer getMaxCodeNum();
	
	public Integer countByCode(String code);
	
	public List<VideoInfo> findByCode(String code);
	
	public List<VideoInfo> findByName(String name);
	
	public List<VideoInfo> findByIp(String ip);
	
	public List<VideoInfo> findByRoadId(String id);
	
	/***
	 * 根据部门ID 获得 设备列表
	 * @param orgId 部门id
	 * @return List<VideoInfo>
	 */
	public List<VideoInfo> findDevlistByOrgId(String orgId);
	
	/**
	 * 根据道路ID 获得 视频设备列表
	 * @param roadId 道路id
	 * @return List<VideoInfo>
	 */
	List<VideoInfo> selectDevlistByRoadId(String roadId);
	
	/***
	 * 根据当前登陆用户 和 其他条件，综合查符合权限的所有设备
	 * @return
	 */
	public List<VideoInfo> getAllVideoByAuthority(Map<String,Object> map, String menuid);
	
	public List<VideoInfo> findAll();
	
	/**
	 * 保存历史记录
	 * @param id
	 */
	public void saveHistoryRecord(String id);
	
	/**
	 * 修改主键
	 * @param afterId
	 * @param beforeId
	 */
	public void updatePrimaryKey(String afterId, String beforeId);
	
	/**
	 * 根据厂商ID获取监控设备信息
	 * @param factoryId
	 * @param menuid
	 * @return
	 */
	public List<VideoInfo> getVideoListByFactoryid(String factoryId, String menuid);
	
}
