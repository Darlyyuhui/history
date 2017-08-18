package com.xiangxun.atms.module.geoServer.mapper;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.module.geoServer.domain.SnapPoint;

public interface SnapPointMapper {
	/**
	 * 获取路网的所有几何体
	 * @return
	 */
	List<String> getRoadGeometrys();
	
	/**
	 * 捕捉某个范围的点
	 * @param map
	 * @return
	 */
	List<SnapPoint> searchSnapPoints(Map<String, Object> map);
	
	/**
	 * 取出相交的geometry
	 * @param geometryText
	 * @return
	 */
	List<SnapPoint> searchIntersectGeometryList(String geometryText);
	
	/**
	 * 取出点在线上的投影点
	 * @param map
	 * @return
	 */
	SnapPoint queryClosedPoint(Map<String, Object> map);
	
	/**
	 * 获取距离一点最近的道路
	 * @param geometryText
	 * @return
	 */
	SnapPoint getMinDistanceLine(String geometryText);
}
