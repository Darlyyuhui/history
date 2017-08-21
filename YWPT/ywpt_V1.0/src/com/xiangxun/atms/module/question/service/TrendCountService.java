package com.xiangxun.atms.module.question.service;
import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.question.domain.DayReport;
import com.xiangxun.atms.module.question.domain.MonthReport;
import com.xiangxun.atms.module.question.domain.WeekReport;

/***
 * 故障类型趋势统计接口
 * @author guikaiping
 */
public interface TrendCountService {

	/***
	 * 故障类型趋势按天统计服务
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	public Page getDayReport(Map<String, Object> map,int pageNo,int pageSize,String sortType,String menuid);
	
	/**
	 * 故障类型趋势按天统计合计服务 曲线图
	 * @param map
	 * @param menuid
	 * @return
	 */
	public List<DayReport> getLineDayReport(Map<String, Object> map, String menuid, String day);
	
	/***
	 * 故障类型趋势按天统计合计服务
	 * @param map
	 * @param menuid
	 * @return
	 */
	public Map<String,Object> getDayReportTotal(Map<String, Object> map,String menuid);
	
	/***
	 * 故障类型趋势 按周查询
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	public Page getWeekReport(Map<String, Object> map,int pageNo,int pageSize,String sortType,String menuid);
	
	/***
	 * 故障类型趋势 按周查询曲线图
	 * @param map
	 * @param weekstart
	 * @param weekend
	 * @param menuid
	 * @return
	 */
	public List<WeekReport> getLineWeekReport(Map<String, Object> map,String menuid,String weekstart, String weekend);
	
	
	/***
	 * 故障类型趋势查询合计
	 * @param map
	 * @param menuid
	 * @return
	 */
	public Map<String,Object> getWeekReportTotal(Map<String, Object> map,String menuid);
	
	
	/***
	 * 故障类型趋势按月查询
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	public Page getMonthReport(Map<String, Object> map,int pageNo,int pageSize,String sortType,String menuid);
	
	/***
	 * 故障类型趋势按时间段查询 曲线图
	 * @param map
	 * @param weekstart
	 * @param weekend
	 * @param menuid
	 * @return
	 */
	public List<MonthReport> getLineSpaceReport(Map<String, Object> map,String menuid,String weekstart, String weekend);
	
	/***
	 * 故障类型趋势按月查询 曲线图
	 * @param map
	 * @param dateTime
	 * @param menuid
	 * @return
	 */
	public List<MonthReport> getLineMonthReport(Map<String, Object> map,String menuid,String dateTime);
	
	/***
	 * 故障类型趋势查询按月合计
	 * @param map
	 * @param menuid
	 * @return
	 */
	public MonthReport getMonthReportTotal(Map<String, Object> map,String menuid);
	
	
	/***
	 * 故障类型趋势自定义时间段
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	public Page getSpaceTimeReport(Map<String, Object> map,int pageNo,int pageSize,String sortType,String menuid);
	
	/***
	 * 故障类型趋势 自定义时间段合计
	 * @param map
	 * @param menuid
	 * @return
	 */
	public MonthReport getSpaceTimeReportTotal(Map<String, Object> map, String menuid);
}
