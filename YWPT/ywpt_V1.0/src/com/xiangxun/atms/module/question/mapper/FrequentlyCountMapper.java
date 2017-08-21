package com.xiangxun.atms.module.question.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

/***
 * 频发故障类型统计
 * @author guikaiping
 */
public interface FrequentlyCountMapper {
	/***
	 * 频发故障类型统计
	 * @param params map里必须放入startTime,endTime,sortType,days
	 * @return
	 */
	List<Map<String,Object>> selectFrequently(Map<String,Object> params,RowBounds rowBounds);
	
	/***
	 * 频发故障类型统计 柱状图
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> selectChart(Map<String,Object> params);
	
	/***
	 * 频发故障类型统计 饼图
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> selectPie(Map<String,Object> params);
	
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
	int selectTotalFrequently(Map<String,Object> params);
	
}
