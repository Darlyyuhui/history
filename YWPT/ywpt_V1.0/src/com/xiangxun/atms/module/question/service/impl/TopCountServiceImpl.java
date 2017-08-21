
package com.xiangxun.atms.module.question.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.user.service.AuthorityFilterContext;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.module.eventalarm.domain.EventtypeInfo;
import com.xiangxun.atms.module.eventalarm.domain.EventtypeInfoSearch;
import com.xiangxun.atms.module.eventalarm.service.EventtypeInfoService;
import com.xiangxun.atms.module.property.service.AssetInfoService;
import com.xiangxun.atms.module.question.domain.BreakdownTypeReport;
import com.xiangxun.atms.module.question.mapper.TopCountMapper;
import com.xiangxun.atms.module.question.service.TopCountService;

/**
 * 频发故障设备TOP统计服务
 * @author guikaiping
 * @version 1.0
 */
@Service
public class TopCountServiceImpl implements TopCountService {
	@Resource
	TopCountMapper topCountMapper;
	
	@Resource(name="authorityFilterContext")
	AuthorityFilterContext authorityFilterContext;
	
	@Resource
	EventtypeInfoService eventtypeInfoService;
	
	@Resource
	AssetInfoService assetInfoService;
	
	
	/**
	 * 频发故障设备TOP统计数量
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @param stateType
	 * @return
	 */
	@Override
	public Page getReport(Map<String, Object> map, int pageNo, int pageSize, String sortType, String menuid, String stateType) {
		//获取故障类型
		String flag = "";
		if (null != map.get("flag")) {
			flag = map.get("flag").toString();
		}else {
			flag = "device";
		}
		EventtypeInfoSearch search = new EventtypeInfoSearch();
		search.createCriteria().andTypeEqualTo(flag);
		List<EventtypeInfo> typeList = eventtypeInfoService.selectByExample(search);
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
		
		map.put("sortType", sortType);
		map.put("types", types);
		return getTypes(map, pageNo, pageSize, menuid, types, stateType);
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
	public BreakdownTypeReport getReportTotal(Map<String, Object> map, String menuid, String stateType) {
		//获取故障类型
		String flag = "";
		if (null != map.get("flag")) {
			flag = map.get("flag").toString();
		}else {
			flag = "device";
		}
		EventtypeInfoSearch search = new EventtypeInfoSearch();
		search.createCriteria().andTypeEqualTo(flag);
		List<EventtypeInfo> typeList = eventtypeInfoService.selectByExample(search);
		
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
		BreakdownTypeReport breakdownTypeReport = getTypesTotal(map, menuid, types, stateType);
		return breakdownTypeReport;
	}

	
	/***
	 * 获取频发故障类型合计数据
	 * @param map
	 * @param menuid
	 * @param stateType
	 * @return BreakdownTypeReport
	 */
	private BreakdownTypeReport getTypesTotal(Map<String, Object> map, String menuid, String[] types, String stateType) {
		map.put("types", types);
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid, stateType);
		Map<String, Object> maps  = topCountMapper.selectTotals(params);

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
	private Page getTypes(Map<String, Object> map, int pageNo, int pageSize, String menuid, String[] types, String stateType) {
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid, stateType);
		
		List<Map<String,Object>> lists = topCountMapper.selectTop(params, Page.getRowBounds(pageNo,pageSize));
		List<BreakdownTypeReport> results = new ArrayList<BreakdownTypeReport>();
		BreakdownTypeReport breakdownTypeReport = null;
		for (Map<String, Object> maps : lists) {
			breakdownTypeReport = new BreakdownTypeReport();
			if(null != maps && !maps.isEmpty()){
				String deviceName = "";
				if (null != maps.get("DEVICE_CODE")) {
					String isOuter = maps.get("IS_OUTER").toString();
					if ("0".equals(isOuter)) {
						if (null != assetInfoService.getById(maps.get("DEVICE_CODE").toString())) {
							deviceName = assetInfoService.getById(maps.get("DEVICE_CODE").toString()).getAssetname();
							breakdownTypeReport.setDeviceName(deviceName);
						}
					} else {
						if (null != assetInfoService.findByDeviceId(maps.get("DEVICE_CODE").toString())) {
							deviceName = assetInfoService.findByDeviceId(maps.get("DEVICE_CODE").toString()).get(0).getAssetname();
							breakdownTypeReport.setDeviceName(deviceName);
						}
					}
					
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
		int totalCount = topCountMapper.selectTotalTop(params);
		
		return Page.getPage(totalCount, results, pageNo, pageSize);
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
