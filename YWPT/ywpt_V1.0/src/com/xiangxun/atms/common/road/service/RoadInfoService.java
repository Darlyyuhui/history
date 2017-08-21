package com.xiangxun.atms.common.road.service;

import java.util.List;

import com.xiangxun.atms.common.road.vo.RoadInfo;
import com.xiangxun.atms.common.road.vo.RoadInfoSearch;
import com.xiangxun.atms.framework.base.BaseService;

/**
 * 道路信息 服务接口
 * @author YanTao
 * @Apr 19, 2013 2:07:20 PM
 */
public interface RoadInfoService extends BaseService<RoadInfo,RoadInfoSearch>{
	
	/***
	 * 获取所有的道路信息
	 * @return
	 */
	public List<RoadInfo> findAll();
	
	/***
	 * 根据id获取所有的子节点
	 * @param id
	 * @return
	 */
	public List<RoadInfo> getChildren(String id);
	
	/***
	 * 判断是否有子节点
	 * @param id
	 * @return
	 */
	public boolean hasChild(String id);
	
	public List<RoadInfo> findByRoadname(String name);
	
	List<RoadInfo> findByUploadcode(String uploadcode);
	
	List<RoadInfo> findByLevels(String levels);
	

}
