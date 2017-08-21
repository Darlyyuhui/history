package com.xiangxun.atms.module.question.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.xiangxun.atms.module.question.domain.DayReport;
import com.xiangxun.atms.module.question.domain.WeekReport;

/***
 * 故障类型趋势统计
 * @author guikaiping
 */
public interface TrendCountMapper {
	/***
	 * 故障类型趋势按天统计
	 * @param params map里必须放入day,nextDay,sortType,hours
	 * @return
	 */
	List<DayReport> selectTrendDays(Map<String,Object> params,RowBounds rowBounds);
	
	/***
	 * 故障类型趋势按天统计 曲线图
	 * @param params map里必须放入day,lastDay,sortType
	 * @return
	 */
	List<DayReport> selectLineTrendDays(Map<String,Object> params);
	
	/***
	 * 获取分页总数 按天
	 * @param params
	 * @return
	 */
	int selectTotalTrendDays(Map<String,Object> params);
	
	/***
	 * 获取所有的合计数据 按天
	 * @param params
	 * @return
	 */
	Map<String,Object> selectTotalsDays(Map<String,Object> params);
	
	
	/***
	 * 故障类型趋势按周统计
	 * @param params map里必须放入startTime,endTime,sortType,days
	 * @return
	 */
	List<WeekReport> selectTrendWeek(Map<String,Object> params,RowBounds rowBounds);
	
	/***
	 * 故障类型按周统计 曲线图
	 * @param params map里必须放入startTime,endTime,sortType,days
	 * @return
	 */
	List<WeekReport> selectLineTrendWeek(Map<String,Object> params);
	
	/***
	 * 获取分页总数 按周
	 * @param params
	 * @return
	 */
	int selectTotalTrendWeek(Map<String,Object> params);
	
	/***
	 * 获取所有的合计数据 按周
	 * @param params
	 * @return
	 */
	Map<String,Object> selectTotalsWeek(Map<String,Object> params);
	
	/***
	 * 故障类型趋势按月统计
	 * @param params map里必须放入startTime,endTime,sortType,days
	 * @return
	 */
	List<Map<String,Object>> selectTrendMonth(Map<String,Object> params,RowBounds rowBounds);
	
	/***
	 * 故障类型趋势按月统计 曲线图
	 * @param params map里必须放入startTime,endTime,sortType,days
	 * @return
	 */
	List<Map<String,Object>> selectLineTrendMonth(Map<String,Object> params);
	
	
	/***
	 * 用于合计服务 按月和自定义时间段
	 * @param params
	 */
	Map<String,Object> selectTotalsMonth(Map<String,Object> params);
	
	/***
	 * 获取分页总数 按月和自定义时间段
	 * @param params
	 * @return
	 */
	int selectTotalTrendMonth(Map<String,Object> params);
	
}
