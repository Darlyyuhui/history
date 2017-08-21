package com.xiangxun.atms.module.geoServer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xiangxun.atms.module.gis.domain.LayerBean;

public interface SpatialOperationMapper {
	
	/**
	 * 求两个几何图形做相交操作后的图形
	 * @param first
	 * @param second
	 * */
	String intersect(String first, String second);
		
	/**
	 * 求某个图形与数据库中的指定数据相交后的图形
	 * @param geo 传入的几何对象
	 * @param tableNames　表名
	 * */	
	//List<String> intersectWithLayer(@Param("geo") String geo, @Param("tableNames") List<String> tableNames);
	
	/**
	 * 返回指定数据表中与该图形相交的所有几何体
	 * @param geo 传入的几何对象
	 * @param tableNames　表名，多个表名组成的list
	 * */
	List<LayerBean> getIntersections(@Param("geo") String geo, @Param("tableNames") List<String> tableNames);
	
	/**
	 *返回包含在指定图形中的几何体
	 *@param geo 表示某个范围的多边形
	 *@param tableNames 表名
	 * */
	List<LayerBean> getInnerGeometries(@Param("geo") String geo, @Param("tableNames") List<String> tableNames);
		
	/**
	 * 确定图形是否包含 first是否包含second
	 * @param first
	 * @param second
	 * */
	boolean contains(String first, String second);
	
	/**
	 * 确定传入的几何体是否包含在指定数据源的几何体中
	 * @param geo
	 * @param tableNames 表名
	 * */
	//boolean isWithinLayer(@Param("geo") String geo, @Param("tableNames") List<String> tableNames);
		
	/**
	 * 求点在线上的投影点（最近点）
	 * @param first 要求type是线类型
	 * @param second 要求type是点类型
	 * */
	String getClosestPoint(String first, String second);
	
	
	/**
	 * 求点在指定线数据源上的投影点(最近点)
	 * @param geo 要求type是线类型
	 * @param tablesName 要求指定数据源是线类型数据
	 * @return 最近点的wkt描述
	 * @Param("geo") String geo, @Param("tableNames") String[] tableNames
	 * */
	String getClosestPointFromLayer(Map<String, Object> map);
	
	/**
	 * 在几何要素上获取投影点(最近点)
	 * @param map  String point, String[] geos
	 * @return
	 */
	String getClosestPointFromGeos(Map<String, Object> map);
	
	/**
	 * 线图层上捕捉点
	 * 对应的mapper为原样输入模式，传入的geo需要在前后添加'单引号
	 * */
	String snap(@Param("geo") String geo, @Param("tableName") String tableName, @Param("tolerance") double tolerance);
	
	/**
	 * @param geotarget第一个参数为需要截断的几何体，返回结果为该线的截断子集
	 * @param geosplit第二个为截断的障碍几何体
	 * @return 返回结果为线的集合的wkt字符串
	 * */
	String split(String geotarget, String geosplit);
	
	/**
	 * 合并集合体
	 * @param geo1
	 * @param geo2
	 * @return
	 */
	String union(String geo1, String geo2);
}
