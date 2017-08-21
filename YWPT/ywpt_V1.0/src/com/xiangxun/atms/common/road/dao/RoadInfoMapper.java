package com.xiangxun.atms.common.road.dao;

import java.util.List;

import com.xiangxun.atms.common.road.vo.RoadInfo;
import com.xiangxun.atms.common.road.vo.RoadInfoSearch;
import com.xiangxun.atms.framework.base.BaseMapper;

public interface RoadInfoMapper extends BaseMapper<RoadInfo, RoadInfoSearch> {
	
	/***
	 * 根据id获取所有的子节点
	 * @param id
	 * @return
	 */
	public List<RoadInfo> getLeafNodeById(String id);
	
	/***
	 * 判断是否有子节点
	 * @param id
	 * @return
	 */
	public int hasChild(String id);

}