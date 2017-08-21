package com.xiangxun.atms.module.question.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.compnents.fusioncharts.MsLine2DChart;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.IDDStorage;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.eventalarm.service.EventtypeInfoService;
import com.xiangxun.atms.module.question.domain.DayReport;
import com.xiangxun.atms.module.question.domain.MonthReport;
import com.xiangxun.atms.module.question.domain.WeekReport;
import com.xiangxun.atms.module.question.service.TrendCountService;
/***
 * 故障类型趋势统计分析FLASH图表处理
 * @author guikaiping
 */
@Controller
@RequestMapping("question/trend/chart")
public class TrendChartCtl extends BaseCtl{

	@Resource
	TrendCountService trendCountService;
	
	@Resource
	EventtypeInfoService eventtypeInfoService;
	//FLASH图标颜色数组
	private String color[] = { "FFCC33", "51DCFF","5F96FC", "8BBA00", "FF8E46",
			"008E8E", "D64646", "8E468E", "588526", "B3AA00", "008ED6",
			"9D080D", "A186BE", "346DB2", "C13A36", "A02F2B", "7C9D35",
			"6A4A8D", "2B8CAC", "D2721E", "7792C1", "BF7B7A", "A6BC7C",
			"9183A7" };
	/***
	 * FLASH图表显示
	 * @param menuid
	 * @param model
	 * @param request
	 * @param resp
	 */
	@LogAspect(desc="FLASH图表 故障类型趋势统计信息")
	@RequestMapping(value = "chart/{menuid}/", method = RequestMethod.POST)
	public String chart(@PathVariable String menuid, ModelMap model,@RequestParam(value="screenWidth") String screenWidth, ServletRequest request){
		String stateType = request.getParameter("search_stateType");
		//按天统计
		if("1".equals(stateType)){
			return dayLine(menuid, model, screenWidth, request);
		} 
		//按周统计
		if("2".equals(stateType)){
			return weekLine(menuid, model, screenWidth, request);
		}
		//按月统计
		if("3".equals(stateType)){
			return monthLine(menuid, model, screenWidth, request);
		}
		//按时间段统计
		if("4".equals(stateType)){
			return spaceLine(menuid, model, screenWidth, request);
		}
		return null;
	}
	
	//=============================曲线图=======开始=================================================
	//===================生成 日 故障类型趋势 曲线图 ==========================================
	public String dayLine(String menuid, ModelMap model, String screenWidth, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//统计日期
		String countDate = searchParams.get("beginDate")+"";
		//转换FLASH上显示的日期格式
		Date count= DateUtil.stringFormatToDate(countDate, "yyyy-MM-dd");
		String countDateStr = DateUtil.dateFormatToString(count, "yyyy年MM月dd日");
		//查询数据
		List<DayReport> list = trendCountService.getLineDayReport(searchParams, menuid, countDate);// 查询 当前起始日期的 数据

		boolean noDataFlag = this.getDayNodateStatus(list);
		if (noDataFlag) {
			request.setAttribute("nodata", "nodata");
		}else{
			MsLine2DChart chart = this.getLineChart("故障类型趋势按天统计曲线图", "统计日期：[ "+countDateStr+" ]", "故障数(次)", "24小时", "", "", "0", "0",(int)Double.parseDouble(screenWidth));
			this.createLineDataXmlDay(list, request);
			String chartHeadStr = chart.createRootChart();
	    	request.setAttribute("chartHeadStr", chartHeadStr);
	    	request.setAttribute("chart", chart);
		}
    	
		return "question/trend/form/chart/lineFlash";
	}
	
	//判断日  故障类型趋势曲线图是否全部为空
	private boolean getDayNodateStatus(List<DayReport> list){
		boolean flag = false;
		int num = 0;
		for(int n=0;n<list.size();n++){
			DayReport vo = list.get(n);
			BigDecimal all = vo.getRecordTotal();
			num +=Long.parseLong(all.toString());
	    }
		
		if(num==0){
			flag = true;
		}
		return flag;
	}
	
	
	//===================生成周故障类型趋势曲线图=====
    public String weekLine(String menuid, ModelMap model, String screenWidth, ServletRequest request) {
    	//获得页面传参
    	Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
    	//统计日期
		String countDate = searchParams.get("beginDate")+"";
		
		//得到选择的统计日期所在的周开始和结束
		String[] currentWeeks = DateUtil.getThisWeekStartAndEndByDate(countDate);
		String cweekstart = currentWeeks[0];
		String cweekend = currentWeeks[1];
		
		//查询数据
		List<WeekReport> list = trendCountService.getLineWeekReport(searchParams, menuid, cweekstart, cweekend);// 查询 当前起始日期所在一周的数据
		
		boolean noDataFlag = this.getWeekNodateStatus(list);
		if (noDataFlag) {
			request.setAttribute("nodata", "nodata");
		}else{
			MsLine2DChart chart = this.getLineChart("故障类型趋势按周统计曲线图", "统计日期：[ "+cweekstart+" 至 "+cweekend+" ]", "故障数(次)", "该周每一天", "", "", "0", "0",(int)Double.parseDouble(screenWidth));
			this.createLineDataXmlWeek(list, request);
			String chartHeadStr = chart.createRootChart();
	    	request.setAttribute("chartHeadStr", chartHeadStr);
	    	request.setAttribute("chart", chart);
		}
		
		return "question/trend/form/chart/lineFlash";
	}
    
  //判断周曲线图是否全部为空
	private boolean getWeekNodateStatus(List<WeekReport> list){
		boolean flag = false;
		int num = 0;
		for(int n=0;n<list.size();n++){
			WeekReport vo = list.get(n);
			num += Long.parseLong(vo.getTotals().toString());
	    }
		
		if(num==0){
			flag = true;
		}
		return flag;
	}
	
	//===================生成月故障类型趋势曲线图=====
	public String monthLine(String menuid, ModelMap model, String screenWidth, ServletRequest request) {
		//获得页面传参
    	Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
    	//统计日期
		String countDate = searchParams.get("beginDate")+"";
		
		String day = countDate+"-01";
		//获取一月的开始日期和结束日期
		String[] times = DateUtil.getMonthStartEndDateByDate(day);
		//获取一月内的所有日期
		List<String> mdays = DateUtil.getAllDatesByDate(times[0],times[1]);
		
		//图表显示日期格式转型
		Date cDate = DateUtil.stringFormatToDate(countDate, "yyyy-MM");
		String cDateStr = DateUtil.dateFormatToString(cDate, "yyyy年MM月");

		//查询数据
		List<MonthReport> list = trendCountService.getLineMonthReport(searchParams, menuid,countDate);// 查询 当前起始日期的 数据
		
		boolean noDataFlag = this.getMonthNodateStatus(list);
		if (noDataFlag) {
			request.setAttribute("nodata", "nodata");
		}else{
			MsLine2DChart chart = this.getLineChart("故障类型趋势按月统计曲线图", "统计日期：[ "+cDateStr+" ]", "故障数(次)", "该月每一天", "", "", "0", "0",(int)Double.parseDouble(screenWidth));
			this.createLineDataXmlMonth(list, request, mdays);
			String chartHeadStr = chart.createRootChart();
	    	request.setAttribute("chartHeadStr", chartHeadStr);
	    	request.setAttribute("chart", chart);
		}
		
		return "question/trend/form/chart/lineFlash";
	}
	
	//判断月曲线图是否全部为空
	private boolean getMonthNodateStatus(List<MonthReport> list){
		boolean flag = false;
		long num = 0;
		for(int n=0;n<list.size();n++){
			MonthReport vo = list.get(n);
			BigDecimal[] days =vo.getDays();
			for(int j=0;j<days.length;j++){
				num += Double.valueOf(days[j].toString());
			}
	    }
		
		if(num==0){
			flag = true;
		}
		return flag;
	}
	
	//===================生成月故障类型趋势曲线图 =====
	public String spaceLine(String menuid, ModelMap model, String screenWidth, ServletRequest request) {
		//获得页面传参
    	Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
    	//统计日期
		String beginDate = searchParams.get("beginDate")+"";
		String endDate =   searchParams.get("endDate")+"";
		
		//获取时间段内的所有日期
		List<String> mdays = DateUtil.getAllDatesByDate(beginDate,endDate);
		//查询数据
		List<MonthReport> list = trendCountService.getLineSpaceReport(searchParams, menuid,beginDate, endDate);// 查询 当前起始日期的 数据
		
		boolean noDataFlag = this.getSpaceNodateStatus(list);
		if (noDataFlag) {
			request.setAttribute("nodata", "nodata");
		}else{
			MsLine2DChart chart = this.getLineChart("故障类型趋势按时间段统计曲线图", "统计日期：[ "+beginDate+" 至 "+endDate+" ]", "故障数(次)", "该时间段每一天", "", "", "0", "0",(int)Double.parseDouble(screenWidth));
			this.createLineDataXmlMonth(list, request, mdays);
			String chartHeadStr = chart.createRootChart();
	    	request.setAttribute("chartHeadStr", chartHeadStr);
	    	request.setAttribute("chart", chart);
		}
		
		return "question/trend/form/chart/lineFlash";
	}
	//判断月  曲线图是否全部为空
	private boolean getSpaceNodateStatus(List<MonthReport> list){
		boolean flag = false;
		long num = 0;
		for(int n=0;n<list.size();n++){
			MonthReport vo = list.get(n);
			BigDecimal[] days = vo.getDays();
			for(int j=0;j<days.length;j++){
				num += Double.valueOf(days[j].toString());
			}
	    }
		
		if(num==0){
			flag = true;
		}
		return flag;
	}
	
	
	//===================生成FLASH 动态图标的数据集转换为XML ==========================================
	private void createLineDataXmlDay(List<DayReport> list,ServletRequest request) {
		//以下xml为生成局部的数据集合所用
		StringBuffer categoriexml = new StringBuffer("");
		categoriexml.append("<categories>");
		for(int hours=0;hours<24;hours++){
			categoriexml.append("<category name='"+hours+"时' />");
		}
		categoriexml.append("</categories>");
		if(list!=null && list.size()>0){
			Set<DayReport> set = new HashSet<DayReport>();
			//封装数据
			for(int n=0;n<list.size();n++){
				StringBuffer datasetxml = new StringBuffer("");
				DayReport vo = list.get(n);
				datasetxml.append("<dataset seriesname='"+eventtypeInfoService.getByCode(vo.getType()).getName()+"' color='"+color[n % 10]+"' showValues='0' areaAlpha='50' showAreaBorder='1' areaBorderThickness='2' areaBorderColor='FF0000'>");
				datasetxml.append("<set value='"+vo.getH01()+"' />");
				datasetxml.append("<set value='"+vo.getH02()+"' />");
				datasetxml.append("<set value='"+vo.getH03()+"' />");
				datasetxml.append("<set value='"+vo.getH04()+"' />");
				datasetxml.append("<set value='"+vo.getH05()+"' />");
				datasetxml.append("<set value='"+vo.getH06()+"' />");
				datasetxml.append("<set value='"+vo.getH07()+"' />");
				datasetxml.append("<set value='"+vo.getH08()+"' />");
				datasetxml.append("<set value='"+vo.getH09()+"' />");
				datasetxml.append("<set value='"+vo.getH10()+"' />");
				datasetxml.append("<set value='"+vo.getH11()+"' />");
				datasetxml.append("<set value='"+vo.getH12()+"' />");
				datasetxml.append("<set value='"+vo.getH13()+"' />");
				datasetxml.append("<set value='"+vo.getH14()+"' />");
				datasetxml.append("<set value='"+vo.getH15()+"' />");
				datasetxml.append("<set value='"+vo.getH16()+"' />");
				datasetxml.append("<set value='"+vo.getH17()+"' />");
				datasetxml.append("<set value='"+vo.getH18()+"' />");
				datasetxml.append("<set value='"+vo.getH19()+"' />");
				datasetxml.append("<set value='"+vo.getH20()+"' />");
				datasetxml.append("<set value='"+vo.getH21()+"' />");
				datasetxml.append("<set value='"+vo.getH22()+"' />");
				datasetxml.append("<set value='"+vo.getH23()+"' />");
				datasetxml.append("<set value='"+vo.getH24()+"' />");
				datasetxml.append("</dataset>");
				vo.setViewId(String.valueOf(n));
				vo.setDataXml(datasetxml.toString());
				set.add(vo);
		    }
			request.setAttribute("datasetss", set);
		}
		request.setAttribute("categoriexml", categoriexml);
	}
	
	//生成周封装数据
	private void createLineDataXmlWeek(List<WeekReport> list,ServletRequest request) {
		//以下xml为生成局部的数据集合所用
		StringBuffer categoriexml = new StringBuffer("");
		categoriexml.append("<categories>");
		categoriexml.append("<category name='星期一'/><category name='星期二'/><category name='星期三'/><category name='星期四'/><category name='星期五'/><category name='星期六'/><category name='星期日'/>");
		categoriexml.append("</categories>");
		if(list!=null && list.size()>0){
			Set<WeekReport> set = new HashSet<WeekReport>();
			//封装数据
			for(int n=0;n<list.size();n++){
				StringBuffer datasetxml = new StringBuffer("");
				WeekReport vo = list.get(n);
				datasetxml.append("<dataset seriesname='"+eventtypeInfoService.getByCode(vo.getType()).getName()+"' color='"+color[n % 10]+"' showValues='0' areaAlpha='50' showAreaBorder='1' areaBorderThickness='2' areaBorderColor='FF0000'>");
				datasetxml.append("<set value='"+vo.getD01()+"' />");
				datasetxml.append("<set value='"+vo.getD02()+"' />");
				datasetxml.append("<set value='"+vo.getD03()+"' />");
				datasetxml.append("<set value='"+vo.getD04()+"' />");
				datasetxml.append("<set value='"+vo.getD05()+"' />");
				datasetxml.append("<set value='"+vo.getD06()+"' />");
				datasetxml.append("<set value='"+vo.getD07()+"' />");
				datasetxml.append("</dataset>");
				vo.setViewId(String.valueOf(n));
				vo.setDataXml(datasetxml.toString());
				set.add(vo);
		    }
			request.setAttribute("datasetss", set);
		}
		request.setAttribute("categoriexml", categoriexml);
	}

	//================故障类型趋势曲线图===数据集转换为XML ==========================================
    
	private void createLineDataXmlMonth(List<MonthReport> list,ServletRequest request,List<String> mdays) {
		//以下xml为生成局部的数据集合所用
		StringBuffer categoriexml = new StringBuffer("");
		categoriexml.append("<categories>");
		for (int i = 0; i < mdays.size(); i++) {
			categoriexml.append("<category name='"+mdays.get(i).substring(8,10)+"日' />");
		}
		categoriexml.append("</categories>");
		if(list!=null && list.size()>0){
			Set<MonthReport> set = new HashSet<MonthReport>();
			//封装数据
			for(int n=0;n<list.size();n++){
				MonthReport vo =  list.get(n);
				StringBuffer datasetxml = new StringBuffer("");
				datasetxml.append("<dataset seriesname='"+(vo.getTypeName()!=null ? vo.getTypeName().toString():"")+"' color='"+color[n % 10]+"'  areaAlpha='50' showAreaBorder='1' areaBorderThickness='2' areaBorderColor='FF0000'>");
				BigDecimal[] days = vo.getDays();
				for(int j=0;j<days.length;j++){
					datasetxml.append("<set value='"+days[j]+"' />");
				}
				datasetxml.append("</dataset>");
				vo.setViewId(String.valueOf(n));
				vo.setDataXml(datasetxml.toString());
				set.add(vo);
			}
			request.setAttribute("datasetss", set);
		}
		request.setAttribute("categoriexml", categoriexml);
	}

	//===================曲线图公 属性赋值方法======================
	private MsLine2DChart getLineChart(String caption,String subCaption,String yname,String xname,
			String numberPrefix,String numberSuffix,String formatNumberScale,String decimalPrecision,Integer width){
		
		MsLine2DChart chart = new MsLine2DChart();
		if(width!=null && width > 600){
			chart.setWide(width-300);
		}else{
			chart.setWide(IDDStorage.flashChart_Wide);
		}
    	chart.setHigh(IDDStorage.flahsChart_High);
    	chart.setBgColor(IDDStorage.flahsChart_BgColor);
    	chart.setCaption(caption);
    	chart.setSubcaption(subCaption);
    	chart.setYAxisName(yname);
    	chart.setXAxisName(xname);
    	chart.setNumberPrefix(numberPrefix);
    	chart.setNumberSuffix(numberSuffix);
    	chart.setFormatNumberScale(formatNumberScale);
    	chart.setDecimalPrecision(decimalPrecision);
    	//divlinecolor 网格颜色
		chart.setDivlinecolor("F47E00");
		//面积边框色
		chart.setAreaBorderColor("DAE5F4");
		//numdivlines 刻度线标度数
		chart.setNumdivlines("4");
		chart.setNumVDivLines("29");
		chart.setShownames("1");
		chart.setVDivLineAlpha("30");
		return chart;
	}
	
}
