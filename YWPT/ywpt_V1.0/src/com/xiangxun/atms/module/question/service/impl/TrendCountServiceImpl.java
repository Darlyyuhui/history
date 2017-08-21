package com.xiangxun.atms.module.question.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.user.service.AuthorityFilterContext;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.constant.FORMAT;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.module.eventalarm.domain.EventtypeInfo;
import com.xiangxun.atms.module.eventalarm.service.EventtypeInfoService;
import com.xiangxun.atms.module.question.domain.DayReport;
import com.xiangxun.atms.module.question.domain.MonthReport;
import com.xiangxun.atms.module.question.domain.WeekReport;
import com.xiangxun.atms.module.question.mapper.TrendCountMapper;
import com.xiangxun.atms.module.question.service.TrendCountService;
/***
 * 故障类型趋势统计报表
 * @author guikaiping
 */
@Service
public class TrendCountServiceImpl implements TrendCountService{

	@Resource 
	TrendCountMapper trendCountMapper;
	
	@Resource(name="authorityFilterContext")
	AuthorityFilterContext authorityFilterContext;
	
	@Resource
	EventtypeInfoService eventtypeInfoService;
	
	/***
	 * 故障类型趋势按天统计服务
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page getDayReport(Map<String, Object> map, int pageNo, int pageSize, String sortType, String menuid) {
		
		Map<String, Object> params = builderSearchCondition(map, sortType, menuid);
		//获取分页数据
		List<DayReport> dayReports = trendCountMapper.selectTrendDays(params,Page.getRowBounds(pageNo, pageSize));
		for (DayReport dayReport : dayReports) {
			if (null != dayReport) {
				dayReport.setTypeName(eventtypeInfoService.getByCode(dayReport.getType()).getName());
				//总数
				dayReport.setTotals(dayReport.getRecordTotal());
			}
		}
		//加入按类型排序
		Collections.sort(dayReports, new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				return ((DayReport)o1).getTypeName().hashCode()-((DayReport)o2).getTypeName().hashCode();
			}
		});
		
		//获取记录总数
		int totalCount = trendCountMapper.selectTotalTrendDays(params);
		//删除存入的参数
		map.remove("hours");
		map.remove("nextDay");
		return Page.getPage(totalCount, dayReports, pageNo, pageSize);
	}
	
	/**
	 * 故障类型趋势按天统计合计服务 曲线图
	 * @param map
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	public List<DayReport> getLineDayReport(Map<String, Object> map, String menuid, String day) {
		map.put("startTime", day);
		map.put("endTime",day);
		
		Map<String, Object> params = builderSearchCondition(map, "",menuid);
		//获取数据
		List<DayReport> dayReports = trendCountMapper.selectLineTrendDays(params);
		
		return dayReports;
	}


	
	/***
	 * 故障类型趋势按天统计合计服务
	 * @param map
	 * @param menuid
	 * @return
	 */
	@Override
	public Map<String, Object> getDayReportTotal(Map<String, Object> map, String menuid) {
		Map<String, Object> params = builderSearchCondition(map, "h01", menuid);
		Map<String, Object> resultMaps = trendCountMapper.selectTotalsDays(params);
		//删除存入的参数
		map.remove("hours");
		map.remove("nextDay");
		return resultMaps;
	}
	
	/***
	 * 故障类型趋势统计 按周查询
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	@Override
	public Page getWeekReport(Map<String, Object> map, int pageNo, int pageSize, String sortType, String menuid) {
		String day = map.get("beginDate")+"";
		//获取这一周的日期
		List<String>  days = Arrays.asList(DateUtil.getThisWeek(day).split(","));
		String[] starts = DateUtil.getThisWeekStartAndEndByDate(day);
		map.put("startTime", starts[0]);
		map.put("endTime",starts[1]);
		map.put("sortType",sortType);
		map.put("days", days);
		
		Map<String, Object> params = filter(map, menuid);
		
		List<WeekReport> reports = trendCountMapper.selectTrendWeek(params, Page.getRowBounds(pageNo, pageSize));
		for (WeekReport weekReport : reports) {
			if (null != weekReport) {
				weekReport.setTypeName(eventtypeInfoService.getByCode(weekReport.getType()).getName());
				weekReport.setTotals(weekReport.getTotals());
			}
		}
		
		//获取记录总数
		int totalCount = trendCountMapper.selectTotalTrendWeek(params);

		return Page.getPage(totalCount, reports, pageNo, pageSize);
	}
	
	/**
	 * 故障类型趋势 按周统计合计服务 曲线图
	 * @param map
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	public List<WeekReport> getLineWeekReport(Map<String, Object> map, String menuid, String weekstart, String weekend) {
		String day = map.get("beginDate")+"";
		//获取这一周的日期
		List<String>  days = Arrays.asList(DateUtil.getThisWeek(day).split(","));
		map.put("days", days);
		
		map.put("startTime", weekstart);
		map.put("endTime",weekend);

		Map<String, Object> params = filter(map, menuid);
		
		List<WeekReport> reports = trendCountMapper.selectLineTrendWeek(params);
		
		return reports;
	}

	/***
	 * 故障类型趋势 按周查询合计
	 * @param map
	 * @param menuid
	 * @return
	 */
	@Override
	public Map<String, Object> getWeekReportTotal(Map<String, Object> map, String menuid) {
		String day = map.get("beginDate")+"";
		//获取这一周的日期
		List<String>  days = Arrays.asList(DateUtil.getThisWeek(day).split(","));
		String[] starts = DateUtil.getThisWeekStartAndEndByDate(day);
		map.put("startTime", starts[0]);
		map.put("endTime",starts[1]);
		map.put("days", days);
		
		Map<String, Object> params = filter(map, menuid);
		
		//获取记录总数
		Map<String, Object> totalCount = trendCountMapper.selectTotalsWeek(params);
		return totalCount;
	}
	
	/***
	 * 故障类型趋势统计 按月查询
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	@Override
	public Page getMonthReport(Map<String, Object> map, int pageNo, int pageSize, String sortType, String menuid) {
		String day = map.get("beginDate")+"-01";
		//获取一月的开始日期和结束日期
		String[] times = DateUtil.getMonthStartEndDateByDate(day);
		//获取一月内的所有日期
		List<String> days = DateUtil.getAllDatesByDate(times[0],times[1]);
		map.put("startTime", times[0]);
		map.put("endTime",times[1]);
		map.put("sortType", sortType);
		map.put("days", days);
		return getDays(map, pageNo, pageSize, menuid, days);
	}
	
	/**
	 * 故障类型趋势按月统计合计服务 曲线图
	 * @param map
	 * @param dateTime
	 * @param menuid
	 * @return voList
	 */
	public List<MonthReport> getLineMonthReport(Map<String, Object> map, String menuid, String dateTime) {
		String day = dateTime+"-01";
		//获取一月的开始日期和结束日期
		String[] times = DateUtil.getMonthStartEndDateByDate(day);
		//获取一月内的所有日期
		List<String> days = DateUtil.getAllDatesByDate(times[0],times[1]);
		map.put("startTime", times[0]);
		map.put("endTime",times[1]);
		map.put("days", days);
		return getLineDays(map, menuid, days);
	}
	
	/**
	 * 故障类型趋势按时间段统计合计服务 曲线图
	 * @param map
	 * @param weekstart
	 * @param weekend
	 * @param menuid
	 * @return voList
	 */
	public List<MonthReport> getLineSpaceReport(Map<String, Object> map, String menuid, String weekstart, String weekend) {
		//获取时间段内的所有日期
		List<String> days = DateUtil.getAllDatesByDate(weekstart,weekend);
		map.put("startTime", weekstart);
		map.put("endTime",weekend);
		map.put("days", days);
		return getLineDays(map, menuid, days);
	}

	/***
	 * 故障类型趋势 查询按月合计
	 * @param map
	 * @param menuid
	 * @return
	 */
	@Override
	public MonthReport getMonthReportTotal(Map<String, Object> map, String menuid) {
		String day = map.get("beginDate")+"-01";
		//获取一月的开始日期和结束日期
		String[] times = DateUtil.getMonthStartEndDateByDate(day);
		//获取一月内的所有日期
		List<String> days = DateUtil.getAllDatesByDate(times[0],times[1]);
		map.put("startTime", times[0]);
		map.put("endTime",times[1]);
		MonthReport monthReport = getDaysTotal(map, menuid, days);
		return monthReport;
	}

	

	/***
	 * 故障类型趋势 自定义时间段查询统计
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	@Override
	public Page getSpaceTimeReport(Map<String, Object> map, int pageNo, int pageSize, String sortType, String menuid) {
		String startTime = map.get("beginDate")+"";		
		String endTime = map.get("endDate")+"";		
		
		//获取时间段内的所有日期
		List<String> days = DateUtil.getAllDatesByDate(startTime,endTime);
		map.put("startTime", startTime);
		map.put("endTime",endTime);
		map.put("sortType", sortType);
		map.put("days", days);
		return getDays(map, pageNo, pageSize, menuid, days);
	}


	/***
	 * 故障类型趋势 自定义时间段合计
	 * @param map
	 * @param menuid
	 * @return
	 */
	@Override
	public MonthReport getSpaceTimeReportTotal(Map<String, Object> map, String menuid) {
		String startTime = map.get("beginDate")+"";		
		String endTime = map.get("endDate")+"";		
		//获取时间段内的所有日期
		List<String> days = DateUtil.getAllDatesByDate(startTime,endTime);
		map.put("startTime", startTime);
		map.put("endTime",endTime);
		MonthReport monthReport = getDaysTotal(map, menuid, days);
		return monthReport;
	}

	private MonthReport getDaysTotal(Map<String, Object> map, String menuid, List<String> days) {
		map.put("days", days);
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid);
		//获取记录总数
		Map<String, Object> maps  = trendCountMapper.selectTotalsMonth(params);
		BigDecimal[] daysValue = new BigDecimal[days.size()];
		if(null != maps && !maps.isEmpty()){
			for (int i = 0; i < days.size(); i++) {
				daysValue[i] = (BigDecimal)maps.get("D"+(i+1));
			}
		}
		MonthReport monthReport = new MonthReport();
		monthReport.setDays(daysValue);
		monthReport.setTotals(monthReport.getResultTotals());
		
		return monthReport;
	}
	/***
	 * 故障类型趋势统计 获取时间段内的数据
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param menuid
	 * @param days
	 * @return
	 */
	private Page getDays(Map<String, Object> map, int pageNo, int pageSize, String menuid, List<String> days) {
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid);
		
		List<Map<String,Object>> lists = trendCountMapper.selectTrendMonth(params, Page.getRowBounds(pageNo,pageSize));
		List<MonthReport> results = new ArrayList<MonthReport>();
		MonthReport monthReport = null;
		for (Map<String, Object> maps : lists) {
			monthReport = new MonthReport();
			if(null != maps && !maps.isEmpty()){
				String type = maps.get("TYPE").toString();
				
				monthReport.setTypeName(eventtypeInfoService.getByCode(type).getName());
				
				BigDecimal[] daysValue = new BigDecimal[days.size()];
				for (int i = 0; i < days.size(); i++) {
					daysValue[i] = (BigDecimal)maps.get("D"+(i+1));
				}
				monthReport.setDays(daysValue);
				
				monthReport.setTotals(monthReport.getResultTotals());
				results.add(monthReport);
			}
		}
		//获取记录总数
		int totalCount = trendCountMapper.selectTotalTrendMonth(params);
		return Page.getPage(totalCount, results, pageNo, pageSize);
	}
	
	/***
	 * 获取故障类型趋势时间段内的数据 曲线图
	 * @param map
	 * @param menuid
	 * @param days
	 * @return
	 */
	private List<MonthReport> getLineDays(Map<String, Object> map, String menuid, List<String> days) {
		List<MonthReport> voList = new ArrayList<MonthReport>();
		if (voList.size() >= 0) {
			voList.clear();
		}
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid);
		//获取曲线图故障数据
		List<Map<String,Object>> lists = trendCountMapper.selectLineTrendMonth(params);
		
		for (Map<String, Object> maps : lists) {
			if(null != maps && !maps.isEmpty()){
				MonthReport monthReport = new MonthReport();
				String type = maps.get("TYPE").toString();
				EventtypeInfo byCode = eventtypeInfoService.getByCode(type);
				//修改如果为空就跳出，否则主页报错
				if(byCode == null) continue;
				monthReport.setTypeName(byCode.getName());
				
				BigDecimal[] daysValue = new BigDecimal[days.size()];
				for (int i = 0; i < days.size(); i++) {
					daysValue[i] = (BigDecimal)maps.get("D"+(i+1));
				}
				//故障数
				monthReport.setDays(daysValue);
				
				voList.add(monthReport);
			}
			
		}
		
		return voList;
	}
	
	/***
	 * 构造查询条件的map
	 * @param map
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	private Map<String, Object> builderSearchCondition(Map<String, Object> map, String sortType, String menuid) {
		List<String> hours = new ArrayList<String>();
		for (int i = 0; i < 24; i++) {
			String str =i+"";
			if(i<10){
				str = "0"+i;
			}
			hours.add(str);
		}
		String day = map.get("beginDate")+"";
		try {
			Date nextDay = DateUtil.getNextDay(DateUtil.parseDate(day, FORMAT.DATE.DEFAULT));
			map.put("day", day);
			map.put("nextDay",DateUtil.formatDate(FORMAT.DATE.DEFAULT,nextDay));
		} catch (ParseException e) {
			return null;
		}
		
		map.put("sortType", sortType);
		map.put("hours", hours);
		
		Map<String, Object> params = filter(map, menuid);
		return params;
	}
	
	
	

	private Map<String, Object> filter(Map<String, Object> map, String menuid) {
		//加入权限过滤
		Map<String, Object> params = authorityFilterContext.filter(map, menuid);
		return params;
	}
	
}
