package com.xiangxun.atms.module.sergrade.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.user.service.AuthorityFilterContext;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.module.question.domain.MonthReport;
import com.xiangxun.atms.module.sergrade.mapper.SergradeWorkOrderMapper;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;
import com.xiangxun.atms.module.sergrade.service.SergradeWorkOrderService;
/***
 * 运维概况统计报表
 * @author guikaiping
 */
@Service
public class SergradeWorkOrderServiceImpl implements SergradeWorkOrderService{

	@Resource
	SergradeWorkOrderMapper sergradeWorkOrderMapper;
	
	@Resource
	FactoryInfoService factoryInfoService;
	
	@Resource(name="authorityFilterContext")
	AuthorityFilterContext authorityFilterContext;
	
	/***
	 * 按自定义时间段统计运维概况
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	@Override
	public Page getWorkOrderReport(Map<String, Object> map, int pageNo, int pageSize, String sortType, String menuid) {
		String startTime = map.get("beginDate")+"";		
		String endTime = map.get("endDate")+"";		
		
		//获取时间段内的所有月份
		List<String> days = DateUtil.getAllMonthsByMonth(startTime,endTime,"yyyy-MM");
		map.put("startTime", startTime);
		map.put("endTime",endTime);
		map.put("sortType", sortType);
		map.put("days", days);
		return getDays(map, pageNo, pageSize, menuid, days);
	}


	/***
	 * 自定义时间段合计
	 * @param map
	 * @param menuid
	 * @return
	 */
	@Override
	public MonthReport getWorkOrderReportTotal(Map<String, Object> map, String menuid) {
		String startTime = map.get("beginDate")+"";		
		String endTime = map.get("endDate")+"";		
		//获取时间段内的所有日期
		List<String> days = DateUtil.getAllMonthsByMonth(startTime,endTime,"yyyy-MM");
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
		Map<String, Object> maps  = sergradeWorkOrderMapper.selectTotals(params);
		BigDecimal[] daysValue = new BigDecimal[days.size()];
		BigDecimal[] days1Value = new BigDecimal[days.size()];
		BigDecimal[] days2Value = new BigDecimal[days.size()];
		//添加流量统计二维数组。
		String[][] dayCounts = new String[days.size()][3];
		if(null != maps && !maps.isEmpty()){
			for (int i = 0; i < days.size(); i++) {
				daysValue[i] = ((BigDecimal)maps.get("D"+(i+1)));
				days1Value[i] = ((BigDecimal)maps.get("CD"+(i+1)));
				days2Value[i] = ((BigDecimal)maps.get("OD"+(i+1)));
				
				dayCounts[i][0] = String.valueOf(maps.get("D"+(i+1)));
				dayCounts[i][1] = String.valueOf(maps.get("CD"+(i+1)));
				dayCounts[i][2] = String.valueOf(maps.get("OD"+(i+1)));
			}
		}
		MonthReport monthReport = new MonthReport();
		//每月 工单总数
		monthReport.setDays(daysValue);
		//每月 已解决工单总数
		monthReport.setDays1(days1Value);
		//每月 未解决工单总数
		monthReport.setDays2(days2Value);
		
		monthReport.setDayCounts(dayCounts);
		//总数 工单总数
		monthReport.setTotals(monthReport.getResultTotals());
		//总数 已解决工单总数
		monthReport.setCtotals(monthReport.getSResultTotals());
		//总数 未解决工单总数
		monthReport.setOtotals(monthReport.getNSResultTotals());
		
		//删除存入的参数
		map.remove("orgs");
		
		return monthReport;
	}
	
	/**
	 * 运维概况统计服务 柱状图
	 * @param map
	 * @param menuid
	 * @return voList
	 */
	public List<MonthReport> getWorkOrderChartReport(Map<String, Object> map, String menuid) {
		String startTime = map.get("beginDate")+"";
		String endTime = map.get("endDate")+"";
		//获取时间段内所有月份
		List<String> days = DateUtil.getAllMonthsByMonth(startTime,endTime,"yyyy-MM");
		map.put("days", days);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return getChartDays(map, menuid);
	}
	
	/***
	 * 获取时间段内的数据
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
		
		List<Map<String,Object>> lists = sergradeWorkOrderMapper.selectWorkOrder(params, Page.getRowBounds(pageNo,pageSize));
		List<MonthReport> results = new ArrayList<MonthReport>();
		MonthReport monthReport = null;
		for (Map<String, Object> maps : lists) {
			monthReport = new MonthReport();
			if(null != maps && !maps.isEmpty()){
				String factoryid = "";
				if (null != maps.get("FACTORYID")) {
					factoryid = maps.get("FACTORYID").toString();
				}
				monthReport.setFactoryid(factoryid);
				BigDecimal[] daysValue = new BigDecimal[days.size()];
				BigDecimal[] days1Value = new BigDecimal[days.size()];
				BigDecimal[] days2Value = new BigDecimal[days.size()];
				//统计二维数组。
				String[][] dayCounts = new String[days.size()][3];
				
				for (int i = 0; i < days.size(); i++) {
					daysValue[i] = ((BigDecimal)maps.get("D"+(i+1)));
					days1Value[i] = ((BigDecimal)maps.get("CD"+(i+1)));
					days2Value[i] = ((BigDecimal)maps.get("OD"+(i+1)));
					
					dayCounts[i][0] = String.valueOf(maps.get("D"+(i+1)));
					dayCounts[i][1] = String.valueOf(maps.get("CD"+(i+1)));
					dayCounts[i][2] = String.valueOf(maps.get("OD"+(i+1)));
				}
		
				//每月 工单总数
				monthReport.setDays(daysValue);
				//每月 已解决工单总数
				monthReport.setDays1(days1Value);
				//每月 未解决工单总数
				monthReport.setDays2(days2Value);
				
				monthReport.setDayCounts(dayCounts);
				//总数 工单总数
				monthReport.setTotals(monthReport.getResultTotals());
				//总数 已解决工单总数
				monthReport.setCtotals(monthReport.getSResultTotals());
				//总数 未解决工单总数
				monthReport.setOtotals(monthReport.getNSResultTotals());
				
				results.add(monthReport);
			}
		}
		//获取记录总数
		int totalCount = sergradeWorkOrderMapper.selectTotalWorkOrder(params);
		//删除存入的参数
		map.remove("orgs");
		return Page.getPage(totalCount, results, pageNo, pageSize);
	}
	
	/***
	 * 获取时间段内的数据 柱状图
	 * @param map
	 * @param menuid
	 * @return
	 */
	private List<MonthReport> getChartDays(Map<String, Object> map, String menuid) {
		List<MonthReport> voList = new ArrayList<MonthReport>();
		if (voList.size() >= 0) {
			voList.clear();
		}
		
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid);
		
		List<Map<String,Object>> lists = sergradeWorkOrderMapper.selectChartWorkOrder(params);
		MonthReport monthReport = null;
		for (int i = 0; i < lists.size(); i++) {
			Map<String, Object> maps = lists.get(i);
			if(null != maps && !maps.isEmpty()){
				monthReport = new MonthReport();
				//服务商名称
				String factoryid = "";
				String factoryName = "";
				if (null != maps.get("FACTORYID")) {
					factoryid = maps.get("FACTORYID").toString();
					factoryName = factoryInfoService.getById(factoryid).getName();
				}
				monthReport.setFactoryName(factoryName);
				//工单总数量
				monthReport.setCounts(Integer.valueOf(maps.get("COUNTS").toString()));
				//已解决工单数量
				monthReport.setSolveCounts(Integer.valueOf(maps.get("SOLVE_COUNTS").toString()));
				//未解决工单数量
				monthReport.setNosolveCounts(Integer.valueOf(maps.get("NOSOLVE_COUNTS").toString()));
			}
			if (null != monthReport) {
				voList.add(monthReport);
			}
		}
		
		//删除存入的参数
		map.remove("orgs");
		return voList;
	}
	

	private Map<String, Object> filter(Map<String, Object> map, String menuid) {
		//加入权限过滤
		Map<String, Object> params = authorityFilterContext.filter(map, menuid);

		return params;
	}

}
