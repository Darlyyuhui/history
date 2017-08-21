
package com.xiangxun.atms.module.question.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.user.service.AuthorityFilterContext;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.module.eventalarm.domain.EventtypeInfo;
import com.xiangxun.atms.module.eventalarm.service.EventtypeInfoService;
import com.xiangxun.atms.module.question.domain.BreakdownInfo;
import com.xiangxun.atms.module.question.domain.BreakdownInfoSearch;
import com.xiangxun.atms.module.question.domain.BreakdownTypeReport;
import com.xiangxun.atms.module.question.mapper.BreakdownInfoMapper;
import com.xiangxun.atms.module.question.mapper.FrequentlyCountMapper;
import com.xiangxun.atms.module.question.service.BreakdownInfoService;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;

/**
 * 故障信息服务
 * @author guikaiping
 * @version 1.0
 */
@Service("breakdownInfoService")
public class BreakdownInfoServiceImpl extends AbstractBaseService<BreakdownInfo, BreakdownInfoSearch> implements BreakdownInfoService {
	@Resource
	BreakdownInfoMapper breakdownInfoMapper;
	
	@Resource
	FrequentlyCountMapper frequentlyCountMapper;
	
	@Resource(name="authorityFilterContext")
	AuthorityFilterContext authorityFilterContext;
	
	@Resource
	FactoryInfoService factoryInfoService;
	
	@Resource
	EventtypeInfoService eventtypeInfoService;
	
	@Override
	protected BaseMapper<BreakdownInfo, BreakdownInfoSearch> getBaseMapper() {
		return breakdownInfoMapper;
	}
	
	/**
	 * 统计频发故障类型数量
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @param stateType
	 * @return
	 */
	@Override
	public Page getFreReport(Map<String, Object> map, int pageNo, int pageSize, String sortType, String menuid, String stateType) {
		//获取故障类型
		List<EventtypeInfo> typeList = eventtypeInfoService.findAll();
		String[] types = new String[typeList.size()];
		String[] typeNames = new String[typeList.size()];
		for (int i = 0; i < typeList.size(); i++) {
			types[i] = typeList.get(i).getCode();
			typeNames[i] = typeList.get(i).getName();
		}
		//按天统计
		if("day".equals(stateType)){
			//获取当前查询时间
			String day = (String) map.get("beginDate");
			map.put("startTime", day);
			map.put("endTime",day);
		}
		//按周统计
		if("week".equals(stateType)){
			String day = map.get("beginDate")+"";
			//获取这一周的日期
			String[] starts = DateUtil.getThisWeekStartAndEndByDate(day);
			map.put("startTime", starts[0]);
			map.put("endTime",starts[1]);
		}
		//按月统计
		if("month".equals(stateType)){
			String day = map.get("beginDate")+"-01";
			//获取一月的开始日期和结束日期
			String[] times = DateUtil.getMonthStartEndDateByDate(day);
			//获取一月内的所有日期
			map.put("startTime", times[0]);
			map.put("endTime",times[1]);
		}
		//按年统计
		if("year".equals(stateType)){
			//按年统计的第一天
			String startDay = map.get("beginDate")+"-01-01";
			//按年统计的最后一天
			String endDay = map.get("beginDate")+"-12-31";
			map.put("startTime", startDay);
			map.put("endTime",endDay);
		}
		//按自定义时间段统计
		if("space".equals(stateType)){
			String startTime = map.get("beginDate")+"";		
			String endTime = map.get("endDate")+"";		
			
			//获取时间段内的所有日期
			map.put("startTime", startTime);
			map.put("endTime",endTime);
		}
		
		map.put("sortType", sortType);
		map.put("types", types);
		map.put("typeNames", typeNames);
		return getFreTypes(map, pageNo, pageSize, menuid, types, stateType);
	}
	
	/***
	 * 按日、周、月、时间段统计频发故障类型数量
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	@Override
	public BreakdownTypeReport getFreReportTotal(Map<String, Object> map, String menuid, String stateType) {
		//获取故障类型
		List<EventtypeInfo> typeList = eventtypeInfoService.findAll();
		String[] types = new String[typeList.size()];
		for (int i = 0; i < typeList.size(); i++) {
			types[i] = typeList.get(i).getCode();
		}
		//按天统计
		if("day".equals(stateType)){
			//获取当前查询时间
			String day = (String) map.get("beginDate");
			map.put("startTime", day);
			map.put("endTime",day);
		}
		//按周统计
		if("week".equals(stateType)){
			String day = map.get("beginDate")+"";
			//获取这一周的日期
			String[] starts = DateUtil.getThisWeekStartAndEndByDate(day);
			map.put("startTime", starts[0]);
			map.put("endTime",starts[1]);
		}
		//按月统计
		if("month".equals(stateType)){
			String day = map.get("beginDate")+"-01";
			//获取一月的开始日期和结束日期
			String[] times = DateUtil.getMonthStartEndDateByDate(day);
			//获取一月内的所有日期
			map.put("startTime", times[0]);
			map.put("endTime",times[1]);
		}
		//按年统计
		if("year".equals(stateType)){
			//按年统计的第一天
			String startDay = map.get("beginDate")+"-01-01";
			//按年统计的最后一天
			String endDay = map.get("beginDate")+"-12-31";
			map.put("startTime", startDay);
			map.put("endTime",endDay);
		}
		//按自定义时间段统计
		if("space".equals(stateType)){
			String startTime = map.get("beginDate")+"";		
			String endTime = map.get("endDate")+"";		
			
			//获取时间段内的所有日期
			map.put("startTime", startTime);
			map.put("endTime",endTime);
		}
		
		map.put("types", types);
		BreakdownTypeReport breakdownTypeReport = getFreTypesTotal(map, menuid, types, stateType);
		return breakdownTypeReport;
	}

	/**
	 * 统计频发故障类型合计服务 柱状图
	 * @param map
	 * @param startDay
	 * @param endDay
	 * @param menuid
	 * @return voList
	 */
	public List<BreakdownTypeReport> getFreChartReport(Map<String, Object> map, String menuid, String startDay, String endDay, String stateType) {
		//获取故障类型
		List<EventtypeInfo> typeList = eventtypeInfoService.findAll();
		String[] types = new String[typeList.size()];
		for (int i = 0; i < typeList.size(); i++) {
			types[i] = typeList.get(i).getCode();
		}
		map.put("startTime", startDay);
		map.put("endTime",endDay);
		map.put("types", types);
		
		return getFreChartTypes(map, menuid, types, stateType);
	}
	
	/**
	 * 统计频发故障类型合计服务 饼图
	 * @param map
	 * @param startDay
	 * @param endDay
	 * @param menuid
	 * @return voList
	 */
	public List<BreakdownTypeReport> getFrePieReport(Map<String, Object> map, String menuid, String startDay, String endDay, String stateType) {
		//获取故障类型
		List<EventtypeInfo> typeList = eventtypeInfoService.findAll();
		String[] types = new String[typeList.size()];
		for (int i = 0; i < typeList.size(); i++) {
			types[i] = typeList.get(i).getCode();
		}
		map.put("startTime", startDay);
		map.put("endTime",endDay);
		map.put("types", types);
		
		return getFrePieTypes(map, menuid, types, stateType);
	}

	
	/***
	 * 获取频发故障类型合计数据
	 * @param map
	 * @param menuid
	 * @param stateType
	 * @return BreakdownTypeReport
	 */
	private BreakdownTypeReport getFreTypesTotal(Map<String, Object> map, String menuid, String[] types, String stateType) {
		map.put("types", types);
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid, stateType);
		Map<String, Object> maps  = frequentlyCountMapper.selectTotals(params);

		BigDecimal[] values = new BigDecimal[types.length];

		if(null != maps && !maps.isEmpty()){
			for (int i = 0; i < types.length; i++) {
				values[i] = (BigDecimal) maps.get("A"+(i+1));	
			}
		}
		BreakdownTypeReport breakdownTypeReport = new BreakdownTypeReport();
		breakdownTypeReport.setValues(values);
		breakdownTypeReport.setTotals(breakdownTypeReport.getResultTotals());
		
		return breakdownTypeReport;
	}

	/***
	 * 获取数据 频发故障类型统计
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param menuid
	 * @param days
	 * @return
	 */
	private Page getFreTypes(Map<String, Object> map, int pageNo, int pageSize, String menuid, String[] types, String stateType) {
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid, stateType);
		
		List<Map<String,Object>> lists = frequentlyCountMapper.selectFrequently(params, Page.getRowBounds(pageNo,pageSize));
		List<BreakdownTypeReport> results = new ArrayList<BreakdownTypeReport>();
		BreakdownTypeReport breakdownTypeReport = null;
		for (Map<String, Object> maps : lists) {
			breakdownTypeReport = new BreakdownTypeReport();
			if(null != maps && !maps.isEmpty()){
				String factoryName = "";
				if (null != maps.get("FACTORY_CODE")) {
					factoryName = factoryInfoService.getById(maps.get("FACTORY_CODE").toString()).getName();
					breakdownTypeReport.setFactoryName(factoryName);
				}
				
				BigDecimal[] values = new BigDecimal[types.length];
				
				for (int i = 0; i < types.length; i++) {
					values[i] = (BigDecimal) maps.get("A"+(i+1));
				}
				breakdownTypeReport.setValues(values);

				breakdownTypeReport.setTotals(breakdownTypeReport.getResultTotals());
				results.add(breakdownTypeReport);
			}
		}
		//获取记录总数
		int totalCount = frequentlyCountMapper.selectTotalFrequently(params);
		
		return Page.getPage(totalCount, results, pageNo, pageSize);
	}
	/***
	 * 获取数据 柱状图 频发故障类型统计
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param menuid
	 * @param days
	 * @return
	 */
	private List<BreakdownTypeReport> getFreChartTypes(Map<String, Object> map, String menuid, String[] types, String stateType) {
		List<BreakdownTypeReport> voList = new ArrayList<BreakdownTypeReport>();
		if (voList.size() >= 0) {
			voList.clear();
		}
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid, stateType);
		List<Map<String,Object>> lists = frequentlyCountMapper.selectChart(params);
		BreakdownTypeReport breakdownTypeReport = null;
		for (int i = 0; i < lists.size(); i++) {
			Map<String, Object> maps = lists.get(i);
			if(null != maps && !maps.isEmpty()){
				breakdownTypeReport = new BreakdownTypeReport();
				String factoryName = "";
				if (null != maps.get("FACTORY_CODE")) {
					factoryName = factoryInfoService.getById(maps.get("FACTORY_CODE").toString()).getName();
				}
				//厂商名称
				breakdownTypeReport.setFactoryName(factoryName);
				//事故数量
				breakdownTypeReport.setCounts((BigDecimal) maps.get("COUNTS"));
			}
			if (null != breakdownTypeReport) {
				voList.add(breakdownTypeReport);
			}
		}
		
		return voList;
	}
	
	/***
	 * 获取数据 饼图 频发故障类型统计
	 * @param map
	 * @param menuid
	 * @return
	 */
	private List<BreakdownTypeReport> getFrePieTypes(Map<String, Object> map, String menuid, String[] types, String stateType) {
		List<BreakdownTypeReport> voList = new ArrayList<BreakdownTypeReport>();
		if (voList.size() >= 0) {
			voList.clear();
		}
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid, stateType);
		
		List<Map<String,Object>> lists = frequentlyCountMapper.selectPie(params);
		BreakdownTypeReport breakdownTypeReport = null;
		for (Map<String, Object> maps : lists) {
			if(null != maps && !maps.isEmpty()){
				breakdownTypeReport = new BreakdownTypeReport();
				BigDecimal[] values = new BigDecimal[types.length];
				String[] typeNames = new String[types.length];
				for (int i = 0; i < types.length; i++) {
					typeNames[i] = eventtypeInfoService.getByCode(maps.get("TYPE"+(i+1)).toString()).getName();
					values[i] = (BigDecimal) maps.get("A"+(i+1));
				}
				breakdownTypeReport.setTypeNames(typeNames);
				breakdownTypeReport.setValues(values);
			}
			
			if (null != breakdownTypeReport) {
				voList.add(breakdownTypeReport);
			}
		}	
		
		
		return voList;
	}
	/**
	 * 处理查询参数
	 * @param map
	 * @param menuid
	 * @param stateType
	 * @return
	 */
	private Map<String, Object> filter(Map<String, Object> map, String menuid, String stateType) {
		//加入权限过滤
		Map<String, Object> params = authorityFilterContext.filter(map, menuid);
				
		return params;
	}
	
}
