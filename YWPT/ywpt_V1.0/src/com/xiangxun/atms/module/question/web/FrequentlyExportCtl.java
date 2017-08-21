package com.xiangxun.atms.module.question.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.xiangxun.atms.module.eventalarm.domain.EventtypeInfo;
import com.xiangxun.atms.module.eventalarm.service.EventtypeInfoService;
import com.xiangxun.atms.module.question.domain.BreakdownTypeReport;
import com.xiangxun.atms.module.question.service.BreakdownInfoService;
/***
 * 频发故障类型统计分析导出处理
 * @author guikaiping
 */
@Controller
@RequestMapping("question/frequently/export")
public class FrequentlyExportCtl extends BaseCtl{

	@Resource
	BreakdownInfoService breakdownInfoService;
	
	@Resource
	EventtypeInfoService eventtypeInfoService;
	
	/***
	 * 频发故障类型统计导出
	 * @param menuid
	 * @param model
	 * @param request
	 * @param resp
	 * @param tag
	 */
	@LogAspect(desc="导出频发故障类型统计信息")
	@RequestMapping(value = "doExport/{menuid}/",method=RequestMethod.POST)
	public void doExport(@PathVariable String menuid, ModelMap model,@RequestParam(value="tag") String tag,ServletRequest request,HttpServletResponse resp){
		//按天统计的导出
		if("day".equals(tag)){
			frequentlyCountExport(menuid,model,request,resp,tag);
		}
		//按周统计的导出
		if("week".equals(tag)){
			frequentlyCountExport(menuid,model,request,resp,tag);
		}
		
		//按月统计的导出
		if("month".equals(tag)){
			frequentlyCountExport(menuid,model,request,resp,tag);
		}
		//按年统计的导出
		if("year".equals(tag)){
			frequentlyCountExport(menuid,model,request,resp,tag);
		}
		//按自定义时间段统计的导出
		if("space".equals(tag)){
			frequentlyCountExport(menuid,model,request,resp,tag);
		}
	}

	
	/***
	 * 按天、周、月、年、时间段频发故障类型统计的导出
	 * @param menuid
	 * @param model
	 * @param request
	 * @param resp
	 * @param tag
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void frequentlyCountExport(String menuid, ModelMap model,ServletRequest request,HttpServletResponse resp, String tag){

		// 3 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(3);
				
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "post_");
		String stateType = searchParams.get("stateType").toString();
		
		//获取故障类型
		List<EventtypeInfo> typeList = eventtypeInfoService.findAll();
		String[] types = new String[typeList.size()];
		for (int i = 0; i < typeList.size(); i++) {
			types[i] = typeList.get(i).getCode();
		}
		
		//按天统计
		if("day".equals(stateType)){
			//获取当前查询时间
			String day = (String) searchParams.get("beginDate");
			searchParams.put("startTime", day);
			searchParams.put("endTime",day);
		}
		//按周统计
		if("week".equals(stateType)){
			String day = searchParams.get("beginDate")+"";
			//获取这一周的日期
			String[] starts = DateUtil.getThisWeekStartAndEndByDate(day);
			searchParams.put("startTime", starts[0]);
			searchParams.put("endTime",starts[1]);
		}
		//按年统计
		if("year".equals(stateType)){
			//按年统计的第一天
			String startDay = searchParams.get("beginDate")+"-01-01";
			//按年统计的最后一天
			String endDay = searchParams.get("beginDate")+"-12-31";
			searchParams.put("startTime", startDay);
			searchParams.put("endTime",endDay);
		}
		
		//按月统计
		if("month".equals(stateType)){
			String day = searchParams.get("beginDate")+"-01";
			//获取一月的开始日期和结束日期
			String[] times = DateUtil.getMonthStartEndDateByDate(day);
			//获取一月内的所有日期
			searchParams.put("startTime", times[0]);
			searchParams.put("endTime",times[1]);
		}
		//按自定义时间段统计
		if("space".equals(stateType)){
			String startTime = searchParams.get("beginDate")+"";		
			String endTime = searchParams.get("endDate")+"";		
			
			//获取时间段内的所有日期
			searchParams.put("startTime", startTime);
			searchParams.put("endTime",endTime);
		}
		
		searchParams.put("types", types);
		//获取数据
		Page page = breakdownInfoService.getFreReport(searchParams, 0,999999, "", menuid, tag);
		List<BreakdownTypeReport> reports = page.getResult();
		// 设置内容
		List result = new ArrayList();
		for (int i = 0; i < reports.size(); i++) {
			BreakdownTypeReport report = reports.get(i);
			int typeSize = report.getValues().length;
			Object[] values = new Object[2+typeSize];
			values[0]= report.getFactoryName();
			for (int j = 0; j < typeSize; j++) {
				values[1+j] = report.getValues()[j];
			}
			values[1+typeSize]=	report.getResultTotals();
			result.add(values);
		}
		BreakdownTypeReport resultTotle = breakdownInfoService.getFreReportTotal(searchParams, menuid, tag);
		
		//统计日期
		String day = searchParams.get("beginDate")+"";
		//开始日期
		String startDate = searchParams.get("beginDate")+"";
		//结束日期
		String endDate = searchParams.get("endDate")+"";
		//构造要修改的值集合
		List<ExcelEntity> excels = new ArrayList<ExcelEntity>();
		
		//合并合计单元格
		ExcelMerge  mergeEntity = new ExcelMerge(3+reports.size(),3+reports.size(),0,0);
		
		ExcelMerge  titleNumMerge = null;	
		//构造要修改的值集合
		List<ExcelMerge> merges = new ArrayList<ExcelMerge>();
	
		for (int j=0;j<types.length;j++) {
			String typeName = "";
			typeName = eventtypeInfoService.getByCode(types[j]).getName();
			excels.add(new ExcelEntity(0,2+j,""));
			excels.add(new ExcelEntity(1,2+j,""));
			
			excels.add(new ExcelEntity(2,1+j,typeName));
			
		}
		//合并标题
		ExcelMerge  titleMerge = new ExcelMerge(0,0,0,1+types.length);
		//合并副标题
		ExcelMerge  secondTitle = new ExcelMerge(1,1,0,1+types.length);	
		
		//修改统计日期的值
		if("day".equals(tag)){
			excels.add(new ExcelEntity(1,0,"统计日期："+(searchParams.get("startTime")+"").substring(0,10)+"  统计单位：次	 "));
		} else {
			excels.add(new ExcelEntity(1,0,"统计日期："+searchParams.get("startTime")+"至"+searchParams.get("endTime")+"　统计单位：次	"));
		}
		titleNumMerge = new ExcelMerge(2,2,1+types.length,1+types.length);	
		if("day".equals(tag)){
			excels.add(new ExcelEntity(0,0,"按天统计故障分析报表"));
			export.setFileName("按天统计频发故障分析报表("+day+").xls");
		} else if("week".equals(tag)){
			excels.add(new ExcelEntity(0,0,"按周统计故障分析报表"));
			export.setFileName("按周统计频发故障分析报表("+startDate+"-"+endDate+").xls");
		} else if("month".equals(tag)){
			excels.add(new ExcelEntity(0,0,"按月统计故障分析报表"));
			export.setFileName("按月统计频发故障分析报表("+day+"月份).xls");
		} else if("year".equals(tag)){
			excels.add(new ExcelEntity(0,0,"按年统计故障分析报表"));
			export.setFileName("按年统计频发故障分析报表("+day+"年度).xls");
		} else if("space".equals(tag)){
			excels.add(new ExcelEntity(0,0,"按时间段统计故障分析报表"));
			export.setFileName("按时间段统计频发故障分析报表("+startDate+"-"+endDate+").xls");
		}
		excels.add(new ExcelEntity(2,1+types.length,"总数"));
		excels.add(new ExcelEntity(3+reports.size(),0,"合计"));
		
				
		if(resultTotle!=null){
			int col = 1;
			for (int m=0;m<types.length;m++) {
				BigDecimal values = resultTotle.getValues()[m];
				if (null != values) {
					excels.add(new ExcelEntity(3+reports.size(),col,values));
				} else {
					excels.add(new ExcelEntity(3+reports.size(),col,""));
				}
				col += 1;
			}
			if (Long.parseLong(resultTotle.getResultTotals().toString()) > 0) {
				excels.add(new ExcelEntity(3+reports.size(),col,resultTotle.getResultTotals()));
			} else {
				excels.add(new ExcelEntity(3+reports.size(),col,""));
			}
			
	
		}
		merges.add(mergeEntity);
		merges.add(titleMerge);
		merges.add(secondTitle);
		merges.add(titleNumMerge);
		
		ExcelEntity[] t = new ExcelEntity[]{};
		ExcelMerge[] h = new ExcelMerge[]{};
		
		//将需要动态替换的数组传入PostExportProssor，导出时会进行自动处理
		export.setPostProcessor(new SimplePostExportProssor(excels.toArray(t),merges.toArray(h)));
		
		export.exportExcelFileStream("xls" + File.separator + "question_frequently_count.xls", null, result, resp);
	
	}
	
	
}
