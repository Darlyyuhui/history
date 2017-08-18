package com.xiangxun.atms.module.geoServer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiangxun.atms.module.geoServer.vo.GeoserverSearch;
import com.xiangxun.atms.module.geoServer.domain.LayerBean;

public interface LayerBeanMapper {
	
	/**
	 * 选取记录
	 * @param tableName 要查询的表名
	 * @param search 查询操作
	 * */
	List<LayerBean> selectByExample(@Param("tableName") String tableName, @Param("search") GeoserverSearch search);	
	
	/**
	 * 根据表明和where条件字符串查询
	 * @param tableName
	 * @param whereStr
	 * @return
	 */
	List<LayerBean> selectByWhere(@Param("tableName") String tableName, @Param("whereStr") String whereStr);
	
	/**
	 * 更新记录
	 * @param record 更新的记录
	 * @param tableName 操作的数据表名
	 * @param search 搜索条件 根据此条件确定更新哪些记录
	 * */
	int updateByExample(@Param("record") LayerBean record, @Param("tableName") String tableName, @Param("search") GeoserverSearch search);
	
	/**
	 * 更新记录
	 * @param record 更新对象
	 * @return
	 */
	int updateById(@Param("record") LayerBean record);
	
	/**
	 * 删除记录
	 * @param tableName 操作的数据表名
	 * @param search　搜索条件　根据此条件确定删除哪些记录
	 * */
	int delete(@Param("tableName") String tableName, @Param("search") GeoserverSearch search);
	
	/**
	 * 删除记录
	 * @param tableName 表名
	 * @param whereStr 条件
	 * @return
	 */
	int deleteByWhere(@Param("tableName") String tableName, @Param("whereStr") String whereStr);
	
	/**
	 * 添加记录
	 * @param 添加的记录
	 * @param 操作的数据表名 
	 * */
	int add(@Param("records") List<LayerBean> records, @Param("tableName") String tableName);
	
	/**
	 * 获取距离一点最近的道路上的投影点
	 * @param geometryText
	 * @return
	 */
	LayerBean getClosedPointOnRoadLine(String geometryText);
}
