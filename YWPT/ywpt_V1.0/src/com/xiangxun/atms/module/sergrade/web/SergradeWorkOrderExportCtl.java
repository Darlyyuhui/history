package com.xiangxun.atms.module.sergrade.web;

import java.io.File;
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

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.compnents.xls.export.template.ExcelEntity;
import com.xiangxun.atms.framework.compnents.xls.export.template.ExcelMerge;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimplePostExportProssor;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimpleXlsExportor;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.question.domain.MonthReport;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;
import com.xiangxun.atms.module.sergrade.service.SergradeWorkOrderService;
/***
 * 卡口号牌颜色流量统计分析导出处理
 * @author guikaiping
 */
@Controller
@RequestMapping("sergrade/workorder/export")
public class SergradeWorkOrderExportCtl extends BaseCtl{

	@Resource
	SergradeWorkOrderService sergradeWorkOrderService;
	
	@Resource
	FactoryInfoService factoryInfoService;
	
	/***
	 * 导出
	 * @param menuid
	 * @param model
	 * @param request
	 * @param resp
	 */
	@LogAspect(desc="导出服务商运维概况统计信息")
	@RequestMapping(value = "doExport/{menuid}/",method=RequestMethod.POST)
	public void doExport(@PathVariable String menuid, ModelMap model,ServletRequest request,HttpServletResponse resp){
		workorderExport(menuid,model,request,resp);
	}
	
	/***
	 * 按时间段导出
	 * @param menuid
	 * @param model
	 * @param request
	 * @param resp
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void workorderExport(String menuid, ModelMap model,ServletRequest request,HttpServletResponse resp){
		// 4 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(4);
				
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "post_");

		String startTime = searchParams.get("beginDate")+"";		
		String endTime = searchParams.get("endDate")+"";		
		
		//获取时间段内的所有月份
		List<String> daysList = DateUtil.getAllMonthsByMonth(startTime,endTime,"yyyy-MM");
		searchParams.put("startTime", startTime);
		searchParams.put("endTime",endTime);
		searchParams.put("days", daysList);
		
		Page page = sergradeWorkOrderService.getWorkOrderReport(searchParams, 0,999999, "factoryid", menuid);
		List<MonthReport> reports = page.getResult();
		// 设置内容
		List result = new ArrayList();
		
		for (int i = 0; i < reports.size(); i++) {
			MonthReport report = reports.get(i);
			int days = report.getDayCounts().length;
			Object[] values = new Object[7+days*3];
			values[0]= factoryInfoService.getById(report.getFactoryid()).getName();
			int a = 1;
			int b = 2;
			int c = 3;
			for (int j = 0; j < days; j++) {
				values[a] = report.getDayCounts()[j][0];
				values[b] = report.getDayCounts()[j][1];
				values[c] = report.getDayCounts()[j][2];
				a += 3;
				b += 3;
				c += 3;
			}
			
			values[1+days*3]=	report.getResultTotals();
			values[2+days*3]=	report.getSResultTotals();
			values[3+days*3]=	report.getNSResultTotals();
			result.add(values);
		}
		
		MonthReport resultTotle = sergradeWorkOrderService.getWorkOrderReportTotal(searchParams, menuid);
		//统计日期
		String day = DateUtil.currentDate();
		
		//构造要修改的值集合
		List<ExcelEntity> excels = new ArrayList<ExcelEntity>();
		//修改日期
		List<String> days = (List)searchParams.get("days");
		//合并合计单元格
		ExcelMerge  mergeEntity = null;
		
		ExcelMerge  titleNumMerge = null;
		//合并标题
		ExcelMerge  titleMerge = null;	
		ExcelMerge  secondTitle = null;	
		
		//构造要修改的值集合
		List<ExcelMerge> merges = new ArrayList<ExcelMerge>();
		int k = 0;
	
		mergeEntity = new ExcelMerge(4+reports.size(),4+reports.size(),0,0);
		for (int j=0;j<days.size();j++) {
			String d = days.get(j);
			excels.add(new ExcelEntity(0,2+k,""));
			excels.add(new ExcelEntity(0,3+k,""));
			excels.add(new ExcelEntity(0,4+k,""));
			excels.add(new ExcelEntity(1,2+k,""));
			excels.add(new ExcelEntity(1,3+k,""));
			excels.add(new ExcelEntity(1,4+k,""));
			
			excels.add(new ExcelEntity(2,1+k,""));
			excels.add(new ExcelEntity(2,2+k,""));
			excels.add(new ExcelEntity(2,3+k,""));
			excels.add(new ExcelEntity(3,1+k,"总工单数"));
			excels.add(new ExcelEntity(3,2+k,"已解决"));
			excels.add(new ExcelEntity(3,3+k,"未解决"));
			excels.add(new ExcelEntity(5+reports.size(),2+k,""));
			excels.add(new ExcelEntity(5+reports.size(),3+k,""));
			excels.add(new ExcelEntity(5+reports.size(),4+k,""));
			
			ExcelMerge  dayMerge = new ExcelMerge(2,2,1+k,3+k);
			merges.add(dayMerge);
			
			excels.add(new ExcelEntity(2,1+k,d));
			
			k += 3;
		}
		excels.add(new ExcelEntity(0,1+days.size()*3,""));
		excels.add(new ExcelEntity(0,2+days.size()*3,""));
		excels.add(new ExcelEntity(0,3+days.size()*3,""));
		excels.add(new ExcelEntity(1,1+days.size()*3,""));
		excels.add(new ExcelEntity(1,2+days.size()*3,""));
		excels.add(new ExcelEntity(1,3+days.size()*3,""));
		
		excels.add(new ExcelEntity(2,1+days.size()*3,""));
		excels.add(new ExcelEntity(2,2+days.size()*3,""));
		excels.add(new ExcelEntity(2,3+days.size()*3,""));
		
		excels.add(new ExcelEntity(3,1+days.size()*3,"总工单数"));
		excels.add(new ExcelEntity(3,2+days.size()*3,"已解决"));
		excels.add(new ExcelEntity(3,3+days.size()*3,"未解决"));
		excels.add(new ExcelEntity(5+reports.size(),1+days.size()*3,""));
		excels.add(new ExcelEntity(5+reports.size(),2+days.size()*3,""));
		excels.add(new ExcelEntity(5+reports.size(),3+days.size()*3,""));
		
		ExcelMerge  dayMerge = new ExcelMerge(2,2,1+days.size()*3,3+days.size()*3);
		merges.add(dayMerge);
		
		//合并标题
		titleMerge = new ExcelMerge(0,0,0,3+days.size()*3);	
		secondTitle = new ExcelMerge(1,1,0,3+days.size()*3);	
		titleNumMerge = new ExcelMerge(2,3,1+days.size()*3,3+days.size()*3);
		excels.add(new ExcelEntity(2,1+days.size()*3,"总数"));
		
		excels.add(new ExcelEntity(0,0,"服务商运维概况统计分析报表"));
		//修改统计日期的值
		excels.add(new ExcelEntity(1,0,"统计日期："+searchParams.get("startTime")+"至"+searchParams.get("endTime")+"     统计单位：个"));
		excels.add(new ExcelEntity(4+reports.size(),0,"合计"));
				
		if(resultTotle!=null){
			int col = 1;
			int pcol = 2;
			int ocol = 3;
			for (int m=0;m<days.size();m++) {
				if (null != resultTotle.getDayCounts()) {
					String values = resultTotle.getDayCounts()[m][0];
					String pvalues = resultTotle.getDayCounts()[m][1];
					String ovalues = resultTotle.getDayCounts()[m][2];
					
					if (null != values && null != pvalues && null != ovalues) {
						excels.add(new ExcelEntity(4+reports.size(),col,values));
						excels.add(new ExcelEntity(4+reports.size(),pcol,pvalues));
						excels.add(new ExcelEntity(4+reports.size(),ocol,ovalues));
					} else {
						excels.add(new ExcelEntity(4+reports.size(),col,""));
						excels.add(new ExcelEntity(4+reports.size(),pcol,""));
						excels.add(new ExcelEntity(4+reports.size(),ocol,""));
					}
					
				}
				
				col += 3;
				pcol += 3;
				ocol += 3;
			}
			if (null != resultTotle.getResultTotals()) {
				if (Long.parseLong(resultTotle.getResultTotals().toString()) > 0) {
					excels.add(new ExcelEntity(4+reports.size(),col,resultTotle.getResultTotals()));
					excels.add(new ExcelEntity(4+reports.size(),pcol,resultTotle.getSResultTotals()));
					excels.add(new ExcelEntity(4+reports.size(),ocol,resultTotle.getNSResultTotals()));
				} else {
					excels.add(new ExcelEntity(4+reports.size(),col,""));
					excels.add(new ExcelEntity(4+reports.size(),pcol,""));
					excels.add(new ExcelEntity(4+reports.size(),ocol,""));
				}
				
			}
		}
		merges.add(mergeEntity);
		merges.add(titleMerge);
		merges.add(secondTitle);
		merges.add(titleNumMerge);
		
		ExcelEntity[] t = new ExcelEntity[]{};
		ExcelMerge[] h = new ExcelMerge[]{};

		export.setPostProcessor(new SimplePostExportProssor(excels.toArray(t),merges.toArray(h)));
		
		export.setFileName("服务商运维概况统计分析报表("+day+").xls");
		export.exportExcelFileStream("xls" + File.separator + "sergrade_workorder.xls", null, result, resp);
		
	}
}
