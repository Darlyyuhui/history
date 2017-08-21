package com.xiangxun.atms.module.sergrade.web;

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

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.compnents.xls.export.template.ExcelEntity;
import com.xiangxun.atms.framework.compnents.xls.export.template.ExcelMerge;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimplePostExportProssor;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimpleXlsExportor;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.sergrade.domain.SergradeCountReport;
import com.xiangxun.atms.module.sergrade.service.SergradeCountService;
/***
 * 服务商下责任资产数量统计分析导出处理
 * @author guikaiping
 */
@Controller
@RequestMapping("sergrade/count/export")
public class SergradeCountExportCtl extends BaseCtl{

	@Resource
	SergradeCountService sergradeCountService;
	
	/***
	 * 服务商下责任资产数量统计导出
	 * @param menuid
	 * @param model
	 * @param request
	 * @param resp
	 */
	@LogAspect(desc="导出服务商下责任资产数量统计信息")
	@RequestMapping(value = "doExport/{menuid}/",method=RequestMethod.POST)
	public void doExport(@PathVariable String menuid, ModelMap model, ServletRequest request,HttpServletResponse resp){
		serCountExport(menuid,model,request,resp);
	}

	
	/***
	 * 服务商下责任资产数量统计的导出
	 * @param menuid
	 * @param model
	 * @param request
	 * @param resp
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void serCountExport(String menuid, ModelMap model,ServletRequest request,HttpServletResponse resp){

		// 3 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(3);
				
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "post_");

		//获取数据
		Page page = sergradeCountService.getSerReport(searchParams, 0,999999, menuid);
		List<SergradeCountReport> reports = page.getResult();
		// 设置内容
		List result = new ArrayList();
		for (int i = 0; i < reports.size(); i++) {
			SergradeCountReport report = reports.get(i);
			int typeSize = report.getValues().length;
			Object[] values = new Object[2+typeSize];
			values[0]= report.getFactoryName();
			for (int j = 0; j < typeSize; j++) {
				values[1+j] = report.getValues()[j];
			}
			values[1+typeSize]=	report.getResultTotals();
			result.add(values);
		}
		SergradeCountReport resultTotle = sergradeCountService.getSerReportTotal(searchParams, menuid);
		
		//导出日期
		String day = DateUtil.getCurrentDateStr("yyyy-MM-dd");
		//构造要修改的值集合
		List<ExcelEntity> excels = new ArrayList<ExcelEntity>();
		//合并合计单元格
		ExcelMerge mergeEntity = new ExcelMerge(3+reports.size(),3+reports.size(),0,0);
		
		ExcelMerge titleNumMerge = null;	
		//构造要修改的值集合
		List<ExcelMerge> merges = new ArrayList<ExcelMerge>();
		//合并副标题
		ExcelMerge  secondTitle = new ExcelMerge(1,1,0,3);	
	
		excels.add(new ExcelEntity(1,0,"导出日期："+(day+"  统计单位：台	 ")));
		
		titleNumMerge = new ExcelMerge(2,2,4,4);	

		export.setFileName("服务商下责任资产数量统计分析报表("+day+").xls");
		
		excels.add(new ExcelEntity(3+reports.size(),0,"合计"));
		
				
		if(resultTotle!=null){
			int col = 1;
			for (int m=0;m<6;m++) {
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
		merges.add(secondTitle);
		merges.add(titleNumMerge);
		
		ExcelEntity[] t = new ExcelEntity[]{};
		ExcelMerge[] h = new ExcelMerge[]{};
		
		//将需要动态替换的数组传入PostExportProssor，导出时会进行自动处理
		export.setPostProcessor(new SimplePostExportProssor(excels.toArray(t),merges.toArray(h)));
		
		export.exportExcelFileStream("xls" + File.separator + "sergrade_count.xls", null, result, resp);
	
	}
	
	
}
