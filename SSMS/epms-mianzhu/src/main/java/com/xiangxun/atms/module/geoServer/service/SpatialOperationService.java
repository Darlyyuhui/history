package com.xiangxun.atms.module.geoServer.service;

import java.util.List;

import com.xiangxun.atms.module.geoServer.domain.LayerBean;
import com.xiangxun.atms.module.geoServer.domain.LayerEnum;

public interface SpatialOperationService {
		
	/**
	 * 以几何图形查询
	 * @param geo 集合图形的字符串表示
	 * */
	List<LayerBean> identify(String geo, List<LayerEnum> layerEnums);
	
	/**
	 * 线图层上捕捉点
	 * */
	String snap(String geo, LayerEnum layer, double tolerance);
	
	
	/**
	 * 路径分析操作
	 * @param analaysisUrl 分析请求的服务路径
	 * @param stops 
	 * @param method 调用的方法
	 * @param barriers 障碍点
	 * @param layerName 线状图层 在此图层中进行路径分析
	 * */
	String routeTask(String analysisUrl, String stops, String method, String barriers, String layerName);
	
	
	/**
	 * 缓冲区分析操作
	 * @param analysisUrl 分析操作的服务路径
	 * @param geoms 几何体
	 * @method 调用的方法
	 * @distance 距离
	 * */	
	String bufferTask(String analysisUrl, String geoms, String method, String distance);
	
	/**
	 * 在几何要素上获取投影点(最近点)
	 * @param point
	 * @param geos
	 * @return
	 */
	String getClosestPointFromGeos(String point, String[] geos);
	
	/**
	 * 求点在指定线数据源上的投影点(最近点)
	 * @param geo 要求type是线类型
	 * @param tablesName 要求指定数据源是线类型数据
	 * @return 最近点的wkt描述
	 * */
	String getClosestPointFromLayer(String geo, String[] tableNames);
	
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
	String union(String[] geos);
	
}
