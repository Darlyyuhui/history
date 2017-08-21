package com.xiangxun.atms.module.sergrade.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

/***
 * 服务商运维概况按时间段分析统计
 * @author guikaiping
 */
public interface SergradeWorkOrderMapper {
	/***
	 * 获取按时间段的服务概况统计
	 * @param params map里必须放入startTime,endTime,sortType,days
	 * @return
	 */
	List<Map<String,Object>> selectWorkOrder(Map<String,Object> params,RowBounds rowBounds);
	
	/***
	 * 获取按时间段的服务概况统计 柱状图
	 * @param params map里必须放入startTime,endTime,sortType
	 * @return
	 */
	List<Map<String,Object>> selectChartWorkOrder(Map<String,Object> params);
	
	/***
	 * 用于合计服务
	 * @param params
	 */
	Map<String,Object> selectTotals(Map<String,Object> params);
	
	/***
	 * 获取分页总数
	 * @param params
	 * @return
	 */
	int selectTotalWorkOrder(Map<String,Object> params);
	
}