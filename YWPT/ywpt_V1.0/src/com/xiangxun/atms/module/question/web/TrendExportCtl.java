package com.xiangxun.atms.module.question.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.compnents.xls.export.template.ExcelEntity;
import com.xiangxun.atms.framework.compnents.xls.export.template.ExcelMerge;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimplePostExportProssor;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimpleXlsExportor;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.eventalarm.service.EventtypeInfoService;
import com.xiangxun.atms.module.question.domain.DayReport;
import com.xiangxun.atms.module.question.domain.MonthReport;
import com.xiangxun.atms.module.question.domain.WeekReport;
import com.xiangxun.atms.module.question.service.TrendCountService;
/***
 * 故障类型趋势统计分析导出处理
 * @author guikaiping
 */
@Controller
@RequestMapping("question/trend/export")
public class TrendExportCtl extends BaseCtl{

	@Resource
	TrendCountService trendCountService;
	
	@Resource
	EventtypeInfoService eventtypeInfoService;
	
	
	/***
	 * 导出
	 * @param menuid
	 * @param model
	 * @param request
	 * @param resp
	 */
	@LogAspect(desc="导出故障类型趋势统计信息")
	@RequestMapping(value = "doExport/{menuid}/",method=RequestMethod.POST)
	public void doExport(@PathVariable String menuid, ModelMap model,@RequestParam(value="tag") String tag,ServletRequest request,HttpServletResponse resp){
		//按天统计的导出
		if("day".equals(tag)){
			dayExport(menuid,model,request,resp);
		}
		//按周统计的导出
		if("week".equals(tag)){
			weekExport(menuid,model,request,resp);
		}
		
		//按月统计的导出
		if("month".equals(tag)){
			monthExport(menuid,model,request,resp);
		}
		
		//按自定义时间段统计的导出
		if("space".equals(tag)){
			spaceExport(menuid,model,request,resp);
		}
	}
	
	/***
	 * 按天统计的导出
	 * @param menuid
	 * @param model
	 * @param request
	 * @param resp
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void dayExport(String menuid, ModelMap model,ServletRequest request,HttpServletResponse resp){
		// 3 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(3);

		// 获取数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "post_");
		
		Page page = trendCountService.getDayReport(searchParams, 0,999999, "", menuid);
		List<DayReport> dayReports = page.getResult();
		// 设置内容
		List result = new ArrayList();
	
		for (int i = 0; i < dayReports.size(); i++) {
			DayReport dayReport = dayReports.get(i);
			Object[] values = new Object[] { 
				eventtypeInfoService.getByCode(dayReport.getType()).getName(),
				dayReport.getH01(),
				dayReport.getH02(),
				dayReport.getH03(),
				dayReport.getH04(),
				dayReport.getH05(),
				dayReport.getH06(),
				dayReport.getH07(),
				dayReport.getH08(),
				dayReport.getH09(),
				dayReport.getH10(),
				dayReport.getH11(),
				dayReport.getH12(),
				dayReport.getH13(),
				dayReport.getH14(),
				dayReport.getH15(),
				dayReport.getH16(),
				dayReport.getH17(),
				dayReport.getH18(),
				dayReport.getH19(),
				dayReport.getH20(),
				dayReport.getH21(),
				dayReport.getH22(),
				dayReport.getH23(),
				dayReport.getH24(),
				dayReport.getTotals()
			};
			result.add(values);
		}
		
		Map<String,Object> totals = trendCountService.getDayReportTotal(searchParams, menuid);
		//统计日期
		String day = searchParams.get("beginDate")+"";
		//合并合计单元格
		ExcelMerge  mergeEntity = new ExcelMerge(3+dayReports.size(),3+dayReports.size(),0,0);	
		
		//构造要修改的值集合
		List<ExcelEntity> excels = new ArrayList<ExcelEntity>();
		//修改统计日期的值
		excels.add(new ExcelEntity(1,0,"统计日期："+day+"     统计单位：次"));
		excels.add(new ExcelEntity(3+dayReports.size(),0,"合计"));
		
		if(totals!=null && totals.size()>0){
			int col = 1;
			int sumValues = 0;
			for (int j=1;j<=24;j++) {
				String key ="H"+j+"";
				if(j<10){
					key = "H0"+j;
				}
				Object values = totals.get(key);
				sumValues +=Long.parseLong(values.toString());
				excels.add(new ExcelEntity(3+dayReports.size(),col,values));
				col++;
			}
			excels.add(new ExcelEntity(3+dayReports.size(),col,sumValues));
		}
		ExcelEntity[] t = new ExcelEntity[]{};
		//将需要动态替换的数组传入PostExportProssor，导出时会进行自动处理
		export.setPostProcessor(new SimplePostExportProssor(excels.toArray(t),new ExcelMerge[]{mergeEntity}));
		
		export.setFileName("故障类型趋势按天统计分析报表("+day+").xls");
		export.exportExcelFileStream("xls" + File.separator + "question_trend_day.xls", null, result, resp);
		
	}
	

	/***
	 * 按周统计的导出
	 * @param menuid
	 * @param model
	 * @param request
	 * @param resp
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void weekExport(String menuid, ModelMap model,ServletRequest request,HttpServletResponse resp){
		// 3 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(4);
		// 获取数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "post_");
		String beginDate = searchParams.get("beginDate")+"";
		//获取这一周的日期
		List<String>  daysList = Arrays.asList(DateUtil.getThisWeek(beginDate).split(","));
		String[] starts = DateUtil.getThisWeekStartAndEndByDate(beginDate);
		searchParams.put("startTime", starts[0]);
		searchParams.put("endTime",starts[1]);
		searchParams.put("days", daysList);
		
		Page page = trendCountService.getWeekReport(searchParams, 0,999999, "", menuid);
		List<WeekReport> reports = page.getResult();
		// 设置内容
		List result = new ArrayList();
		for (int i = 0; i < reports.size(); i++) {
			WeekReport report = reports.get(i);
			Object[] values = new Object[] { 
				eventtypeInfoService.getByCode(report.getType()).getName(),
				report.getD01(),
				report.getD02(),
				report.getD03(),
				report.getD04(),
				report.getD05(),
				report.getD06(),
				report.getD07(),
				report.getTotals()
			};
			result.add(values);
		}
		
		Map<String,Object> totals = trendCountService.getWeekReportTotal(searchParams, menuid);
		//统计日期
		String day = searchParams.get("beginDate")+"";
		//合并合计单元格
		ExcelMerge  mergeEntity = new ExcelMerge(4+reports.size(),4+reports.size(),0,0);
		//构造要修改的值集合
		List<ExcelEntity> excels = new ArrayList<ExcelEntity>();
		//修改统计日期的值
		excels.add(new ExcelEntity(1,0,"统计日期："+searchParams.get("startTime")+"至"+searchParams.get("endTime")+"     统计单位：次"));
		//修改星期下的日期
		List<String> days = (List)searchParams.get("days");
		for (int j=0;j<days.size();j++) {
			String d = days.get(j);
			excels.add(new ExcelEntity(3,1+j,d));
			
		}
		
		excels.add(new ExcelEntity(4+reports.size(),0,"合计"));
		
		if(totals!=null && totals.size()>0){
			int col = 1;
			int sumValues = 0;
			for (int j=1;j<=7;j++) {
				String key ="D0"+j+"";
				Object values = totals.get(key);
				sumValues +=Long.parseLong(values.toString());
				excels.add(new ExcelEntity(4+reports.size(),col,values));
				col++;
			}
			excels.add(new ExcelEntity(4+reports.size(),col,sumValues));
		}
		ExcelEntity[] t = new ExcelEntity[]{};
		//将需要动态替换的数组传入PostExportProssor，导出时会进行自动处理
		export.setPostProcessor(new SimplePostExportProssor(excels.toArray(t),new ExcelMerge[]{mergeEntity}));
		
		export.setFileName("故障类型趋势按周统计分析报表("+day+").xls");
	
		export.exportExcelFileStream("xls" + File.separator + "question_trend_week.xls", null, result, resp);
		
	}
	
	/***
	 * 按月导出
	 * @param menuid
	 * @param model
	 * @param request
	 * @param resp
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void monthExport(String menuid, ModelMap model,ServletRequest request,HttpServletResponse resp){
		// 3 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(3);
				
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "post_");
		String beginDate = searchParams.get("beginDate")+"-01";
		//获取一月的开始日期和结束日期
		String[] times = DateUtil.getMonthStartEndDateByDate(beginDate);
		//获取一月内的所有日期
		List<String> daysList = DateUtil.getAllDatesByDate(times[0],times[1]);
		searchParams.put("startTime", times[0]);
		searchParams.put("endTime",times[1]);
		searchParams.put("days", daysList);
		
		Page page = trendCountService.getMonthReport(searchParams, 0,999999, "", menuid);

		List<MonthReport> reports = page.getResult();
		// 设置内容
		List result = new ArrayList();
	
		for (int i = 0; i < reports.size(); i++) {
			MonthReport report = reports.get(i);
			int days = report.getDays().length;
			Object[] values = new Object[2+days];
			values[0] = report.getTypeName();
			for (int j = 0; j < days; j++) {
				values[1+j] = report.getDays()[j];
			}
			values[1+days]=	report.getResultTotals();
			result.add(values);
		}
		MonthReport resultTotle = trendCountService.getMonthReportTotal(searchParams, menuid);
		//统计日期
		String day = searchParams.get("beginDate")+"";
		
		//构造要修改的值集合
		List<ExcelEntity> excels = new ArrayList<ExcelEntity>();
		
		//修改日期
		List<String> days = (List)searchParams.get("days");
		//合并合计单元格
		ExcelMerge  mergeEntity = null;
		
		//合并标题
		ExcelMerge  titleMerge = null;	
		ExcelMerge  secondTitle = null;	
	
		mergeEntity = new ExcelMerge(3+reports.size(),3+reports.size(),0,0);
		
		for (int j=0;j<days.size();j++) {
			String d = days.get(j);
			excels.add(new ExcelEntity(0,2+j,""));
			excels.add(new ExcelEntity(1,2+j,""));
			
			excels.add(new ExcelEntity(2,1+j,d.substring(8, 10)+"日"));
			excels.add(new ExcelEntity(4+reports.size(),1+j,""));
		}
		titleMerge = new ExcelMerge(0,0,0,1+days.size());	
		secondTitle = new ExcelMerge(1,1,0,1+days.size());	
		
		excels.add(new ExcelEntity(4+reports.size(),1+days.size(),""));
		excels.add(new ExcelEntity(2,1+days.size(),"总数"));
		
		//修改统计日期的值
		excels.add(new ExcelEntity(1,0,"统计日期："+searchParams.get("startTime")+"至"+searchParams.get("endTime")+"     统计单位：次"));
		excels.add(new ExcelEntity(3+reports.size(),0,"合计"));
		
		if(resultTotle!=null){
			int col = 1;
			for (int j=0;j<resultTotle.getDays().length;j++) {
				BigDecimal values = resultTotle.getDays()[j];
				if (null != values) {
					excels.add(new ExcelEntity(3+reports.size(),col,values));
				} else {
					excels.add(new ExcelEntity(3+reports.size(),col,""));
				}
				col++;
			}
			if (Long.parseLong(resultTotle.getResultTotals().toString()) > 0) {
				excels.add(new ExcelEntity(3+reports.size(),col,resultTotle.getResultTotals()));
			} else {
				excels.add(new ExcelEntity(3+reports.size(),col,""));
			}
			
		}
		ExcelEntity[] t = new ExcelEntity[]{};
		//将需要动态替换的数组传入PostExportProssor，导出时会进行自动处理
		export.setPostProcessor(new SimplePostExportProssor(excels.toArray(t),new ExcelMerge[]{mergeEntity,titleMerge,secondTitle}));
		
		export.setFileName("故障类型趋势按月统计分析报表("+day+").xls");
		export.exportExcelFileStream("xls" + File.separator + "question_trend_month.xls", null, result, resp);
	}
	
	/***
	 * 按时间段导出
	 * @param menuid
	 * @param model
	 * @param request
	 * @param resp
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void spaceExport(String menuid, ModelMap model,ServletRequest request,HttpServletResponse resp){
		
		// 3 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(3);
				
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "post_");
		
		String startTime = searchParams.get("beginDate")+"";		
		String endTime = searchParams.get("endDate")+"";		
		
		//获取时间段内的所有日期
		List<String> daysList = DateUtil.getAllDatesByDate(startTime,endTime);
		searchParams.put("startTime", startTime);
		searchParams.put("endTime",endTime);
		searchParams.put("days", daysList);
		
		Page page = trendCountService.getSpaceTimeReport(searchParams, 0,999999, "", menuid);

		List<MonthReport> reports = page.getResult();
		// 设置内容
		List result = new ArrayList();
	
		for (int i = 0; i < reports.size(); i++) {
			MonthReport report = reports.get(i);
			int days = report.getDays().length;
			Object[] values = new Object[2+days];
			values[0]= 	report.getTypeName();
			
			for (int j = 0; j < days; j++) {
				values[1+j] = report.getDays()[j];
			}
			values[1+days]=	report.getResultTotals();
			result.add(values);
		}
		
		MonthReport resultTotle = trendCountService.getSpaceTimeReportTotal(searchParams, menuid);
		//统计日期
		String day = searchParams.get("beginDate")+"";
		
		//构造要修改的值集合
		List<ExcelEntity> excels = new ArrayList<ExcelEntity>();
		
		//修改日期
		List<String> days = (List)searchParams.get("days");
		//合并合计单元格
		ExcelMerge  mergeEntity = null;
		//合并标题
		ExcelMerge  titleMerge = null;	
		ExcelMerge  secondTitle = null;	
	
		mergeEntity = new ExcelMerge(3+reports.size(),3+reports.size(),0,0);
		
		for (int j=0;j<days.size();j++) {
			String d = days.get(j);
			excels.add(new ExcelEntity(0,2+j,""));
			excels.add(new ExcelEntity(1,2+j,""));
			
			excels.add(new ExcelEntity(2,1+j,d.substring(8, 10)+"日"));
			excels.add(new ExcelEntity(4+reports.size(),1+j,""));
		}
		titleMerge = new ExcelMerge(0,0,0,1+days.size());	
		secondTitle = new ExcelMerge(1,1,0,1+days.size());	
		
		excels.add(new ExcelEntity(4+reports.size(),1+days.size(),""));
		excels.add(new ExcelEntity(2,1+days.size(),"总数"));
		
		//修改统计日期的值
		excels.add(new ExcelEntity(1,0,"统计日期："+searchParams.get("startTime")+"至"+searchParams.get("endTime")+"     统计单位：次"));
		
		excels.add(new ExcelEntity(0,0,"故障类型趋势按时间段统计分析报表"));
		excels.add(new ExcelEntity(3+reports.size(),0,"合计"));
				
		if(resultTotle!=null){
			int col = 1;
			
			for (int j=0;j<resultTotle.getDays().length;j++) {
				
				BigDecimal values = resultTotle.getDays()[j];
				if (null != values) {
					excels.add(new ExcelEntity(3+reports.size(),col,values));
				}else {
					excels.add(new ExcelEntity(3+reports.size(),col,""));
				}
				col++;
			}
			if (Long.parseLong(resultTotle.getResultTotals().toString()) > 0) {
				excels.add(new ExcelEntity(3+reports.size(),col,resultTotle.getResultTotals()));
			} else {
				excels.add(new ExcelEntity(3+reports.size(),col,""));
			}
			
		}
		ExcelEntity[] t = new ExcelEntity[]{};
		//将需要动态替换的数组传入PostExportProssor，导出时会进行自动处理
		export.setPostProcessor(new SimplePostExportProssor(excels.toArray(t),new ExcelMerge[]{mergeEntity,titleMerge,secondTitle}));
		
		export.setFileName("故障类型趋势按时间段统计分析报表("+day+").xls");
		export.exportExcelFileStream("xls" + File.separator + "question_trend_space.xls", null, result, resp);
	}
}
