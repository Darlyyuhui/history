package com.xiangxun.atms.module.question.web;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.compnents.fusioncharts.SingleColumn3DChart;
import com.xiangxun.atms.framework.compnents.fusioncharts.SinglePieColumn3DChart;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.IDDStorage;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.eventalarm.domain.EventtypeInfo;
import com.xiangxun.atms.module.eventalarm.service.EventtypeInfoService;
import com.xiangxun.atms.module.question.domain.BreakdownTypeReport;
import com.xiangxun.atms.module.question.service.BreakdownInfoService;
/***
 * 频发故障类型统计分析FLASH图表处理
 * @author guikaiping
 */
@Controller
@RequestMapping("question/frequently/chart")
public class FrequentlyChartCtl extends BaseCtl{

	@Resource
	BreakdownInfoService breakdownInfoService;
	
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
	@LogAspect(desc="FLASH图表 频发故障类型统计信息")
	@RequestMapping(value = "chart/{menuid}/", method = RequestMethod.POST)
	public String chart(@PathVariable String menuid, ModelMap model,@RequestParam(value="tag") String tag, @RequestParam(value="screenWidth") String screenWidth, HttpServletRequest request){
		String stateType = request.getParameter("search_stateType");

		if ("chart".equals(tag)) {
			return columnChart(menuid, model, screenWidth, request, stateType);
		} else if ("pie".equals(tag)) {
			return pieChart(menuid, model, screenWidth, request, stateType);
		}
		
		return null;
	}
	
	//===================生成 时间段 违法车次 环比FLASH 动态图标 ==========================================
	public String columnChart(String menuid, ModelMap model, String screenWidth, HttpServletRequest request, String stateType) {
		//获得页面传参
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		//统计日期
		String beginDate = "";
		String endDate = "";
		//按天统计
		if("day".equals(stateType)){
			//获取当前查询时间
			beginDate = searchParams.get("beginDate")+"";
			endDate = searchParams.get("beginDate")+"";
		}
		//按周统计
		if("week".equals(stateType)){
			String day = searchParams.get("beginDate")+"";
			//获取这一周的日期
			String[] starts = DateUtil.getThisWeekStartAndEndByDate(day);
			beginDate = starts[0];
			endDate = starts[1];
		}
		//按月统计
		if("month".equals(stateType)){
			String day = searchParams.get("beginDate")+"-01";
			//获取一月的开始日期和结束日期
			String[] times = DateUtil.getMonthStartEndDateByDate(day);
			//获取一月内的所有日期
			beginDate = times[0];
			endDate = times[1];
		}
		//按年统计
		if("year".equals(stateType)){
			//按年统计的第一天
			String startDay = searchParams.get("beginDate")+"-01-01";
			//按年统计的最后一天
			String endDay = searchParams.get("beginDate")+"-12-31";
			beginDate = startDay;
			endDate = endDay;
		}
		//按自定义时间段统计
		if("space".equals(stateType)){
			String startTime = searchParams.get("beginDate")+"";		
			String endTime = searchParams.get("endDate")+"";		
			
			//获取时间段内的所有日期
			beginDate = startTime;
			endDate = endTime;
		}
		
		List<BreakdownTypeReport> list = breakdownInfoService.getFreChartReport(searchParams, menuid, beginDate, endDate, stateType);// 查询 当前起始日期的 数据
		
		//初始化数据
    	String barChartStr="<table align='center' height='400' width='100%' style='background-color: white;border:solid 1px #CCCCCC;'><tr><td align='center' valign='middle'>" +
		"<div style='line-height:30px;width:130px;margin:0 auto;margin-top:60px;text-align:center;background:#ced5e6;'>没有数据...</div></td></tr></table>";
    	
    	if(null != list && list.size()>0){
			//标题
			String title = "";
			//统计时间
			String date = "";
			//按天统计
    		if("day".equals(stateType)){
    			title = "按天统计频发故障柱状图";
    			date = "统计时间：[ " + beginDate + " ]";
    		}
    		//按周统计
    		if("week".equals(stateType)){
    			title = "按周统计频发故障柱状图";
    			date = "统计时间：[ " + beginDate + " 至 " + endDate + " ]";
    		}
    		//按月统计
    		if("month".equals(stateType)){
    			title = "按月统计频发故障柱状图";
    			date = "统计时间：[ " + beginDate + " 至 " + endDate + " ]";
    		}
    		//按年统计
    		if("year".equals(stateType)){
    			title = "按年统计频发故障柱状图";
    			date = "统计时间：[ " + beginDate + " 至 " + endDate + " ]";
    		}
    		//按自定义时间段统计
    		if("space".equals(stateType)){
    			title = "按时间段统计频发故障柱状图";
    			date = "统计时间：[ " + beginDate + " 至 " + endDate + " ]";
    		}
    		SingleColumn3DChart chart = new SingleColumn3DChart();
    		int width = (int)Double.parseDouble(screenWidth);
    		if(width > 600){
    			chart.setWide(width-200);
    		}else{
    			chart.setWide(IDDStorage.flashChart_Wide);
    		}
    		chart.setHigh(IDDStorage.flahsChart_High);
        	chart.setBgColor(IDDStorage.flahsChart_BgColor);
           	chart.setCaption(title);
           	chart.setSubcaption(date);
           	chart.setXAxisName("厂商名称");
           	chart.setYAxisName("故障数量(次)");
           	chart.setBaseFontSize("12");
           	chart.setRotateYAxisName("0");
           	chart.setNumberSuffix("");
           	chart.setFormatNumber("1");
           	chart.setFormatNumberScale("0");
           	chart.setDecimalPrecision("0");
           	chart.setShowValues("1");
           	chart.setLabelDisplay("wrap");
           	chart.setShowBorder("1");
           	chart.setBorderColor("#BAB9B9");
           	chart.setBodyxml(this.createDataXml(list));
           	barChartStr = chart.drawChart(request);
		}
    	request.setAttribute("barChartStr", barChartStr);
    	
		return "question/frequently/form/chart/ringFlash";
	}
	
	
	//=============================曲线面积图=======开始=================================================
	
	//===================生成 时间段 违法车次 饼图 =====
	public String pieChart(String menuid, ModelMap model, String screenWidth, ServletRequest request, String stateType) {
		//获得页面传参
    	Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
    	//统计日期
		String beginDate = "";
		String endDate = "";
		//按天统计
		if("day".equals(stateType)){
			//获取当前查询时间
			beginDate = searchParams.get("beginDate")+"";
			endDate = searchParams.get("beginDate")+"";
		}
		//按周统计
		if("week".equals(stateType)){
			String day = searchParams.get("beginDate")+"";
			//获取这一周的日期
			String[] starts = DateUtil.getThisWeekStartAndEndByDate(day);
			beginDate = starts[0];
			endDate = starts[1];
		}
		//按月统计
		if("month".equals(stateType)){
			String day = searchParams.get("beginDate")+"-01";
			//获取一月的开始日期和结束日期
			String[] times = DateUtil.getMonthStartEndDateByDate(day);
			//获取一月内的所有日期
			beginDate = times[0];
			endDate = times[1];
		}
		//按年统计
		if("year".equals(stateType)){
			//按年统计的第一天
			String startDay = searchParams.get("beginDate")+"-01-01";
			//按年统计的最后一天
			String endDay = searchParams.get("beginDate")+"-12-31";
			beginDate = startDay;
			endDate = endDay;
		}
		//按自定义时间段统计
		if("space".equals(stateType)){
			String startTime = searchParams.get("beginDate")+"";		
			String endTime = searchParams.get("endDate")+"";		
			
			//获取时间段内的所有日期
			beginDate = startTime;
			endDate = endTime;
		}
		double widthF = (int)Float.parseFloat(screenWidth)*0.7;
		//查询数据
		List<BreakdownTypeReport> list = breakdownInfoService.getFrePieReport(searchParams, menuid,beginDate, endDate, stateType);
		
		String pieChartStr= "<table align='center' height='400' width='100%' style='background-color: white;border:solid 1px #CCCCCC;'><tr><td align='center' valign='middle'>" +
		"<div style='line-height:30px;width:130px;margin:0 auto;margin-top:60px;text-align:center;background:#ced5e6;'>没有数据...</div></td></tr></table>";
		//判断 当前数据 和 对比历史数据  都没有数据的时候，显示 无数据 提示
		boolean noDataFlag = true;
		for (int i = 0; i < list.size(); i++) {
			BreakdownTypeReport form = list.get(i);
			if(null != form.getValues()){
				BigDecimal result = new BigDecimal(0);
				for (int j = 0; j < form.getValues().length; j++) {
					result= result.add(form.getValues()[j]==null?new BigDecimal(0):form.getValues()[j]);
				}
				if (Long.valueOf(result.toString()) > 0) {
					noDataFlag = false;
					break;
				}
			}
		}
		if(list!=null && list.size()>0){
			//标题
			String title = "";
			//统计时间
			String date = "";
			
    		SinglePieColumn3DChart chart=new SinglePieColumn3DChart();
    		if(screenWidth!=null && screenWidth.length()>0){
            	chart.setWide((int)widthF);
        	}else{
        		chart.setWide(570);
        	}
        	chart.setHigh(IDDStorage.flahsChart_High);
        	chart.setBgColor(IDDStorage.flahsChart_BgColor);
        	
        	//按天统计
    		if("day".equals(stateType)){
    			title = "按天统计频发故障饼状图";
    			date = "统计时间：[ "+beginDate+" ]";
    		}
    		//按周统计
    		if("week".equals(stateType)){
    			title = "按周统计频发故障饼状图";
    			date = "统计时间：[ "+beginDate+" 至 "+endDate+" ]";
    		}
    		//按月统计
    		if("month".equals(stateType)){
    			title = "按月统计频发故障饼状图";
    			date = "统计时间：[ "+beginDate+" 至 "+endDate+" ]";
    		}
    		//按年统计
    		if("year".equals(stateType)){
    			title = "按年统计频发故障饼状图";
    			date = "统计时间：[ "+beginDate+" 至 "+endDate+" ]";
    		}
    		//按自定义时间段统计
    		if("space".equals(stateType)){
    			title = "按时间段统计频发故障饼状图";
    			date = "统计时间：[ "+beginDate+" 至 "+endDate+" ]";
    		}
    		chart.setCaption(title);
    		chart.setSubcaption(date);
        	chart.setShownames("1");
        	chart.setShowValues("1");
        	chart.setNumberSuffix("次");
        	chart.setEnableRotation("1");
        	chart.setEnableSmartLabels("0");
        	chart.setAnimation("1");
        	chart.setFormatNumber("1");
        	chart.setFormatNumberScale("0");
        	chart.setStartingAngle("45");
        	chart.setDecimalPrecision("0");
        	chart.setEnableSmartLabels("1");
        	chart.setSlicingDistance("1");
        	if (noDataFlag) {
        		pieChartStr= "<table align='center' height='400' width='100%' style='background-color: white;border:solid 1px #CCCCCC;'><tr><td align='center' valign='middle'>" +
        		"<div style='line-height:30px;width:130px;margin:0 auto;margin-top:60px;text-align:center;background:#ced5e6;'>没有数据...</div></td></tr></table>";
    		} else {
    			chart.setBodyxml(this.createPieDataXml(list));
    			pieChartStr=chart.drawChart((HttpServletRequest) request);
			}
    	}
		request.setAttribute("pieChartStr", pieChartStr);
		
		return "question/frequently/form/chart/pieFlash";
		
	}
	
	//===================生成FLASH 动态图标的数据集转换为XML ==========================================
	private String createDataXml(List<BreakdownTypeReport> list) {
		StringBuffer dxml = new StringBuffer(" ");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				BreakdownTypeReport vo = list.get(i);
				dxml.append("<set name='" + vo.getFactoryName() +"' value='" + vo.getCounts() + "' color='"+ color[i % 10] + "'  />");
			}
			//事故页面柱状图 默认显示15根柱子的宽度
			if(list.size()<15){
				int tempnum = 15 - list.size();
				for(int n=0;n<tempnum;n++){
					dxml.append("<set name='    ' />");
				}
			}
		} else {
			return "";
		}
		return dxml.toString();
	}
		
	
	/**
	 * 频发故障类型统计 ===饼状图=封装XML===
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception 
	 */
	private String createPieDataXml(List<BreakdownTypeReport> list) {
		//获取故障类型
		List<EventtypeInfo> typeList = eventtypeInfoService.findAll();
		String[] types = new String[typeList.size()];
		for (int i = 0; i < typeList.size(); i++) {
			types[i] = typeList.get(i).getCode();
		}
		
    	StringBuffer dxml = new StringBuffer("");
		for (int i = 0; i < list.size(); i++) {
			BreakdownTypeReport vo = list.get(i);
			for (int j = 0; j < types.length; j++) {
				dxml.append("<set name='"+vo.getTypeNames()[j].replace("%", "%25")+"' value='"+vo.getValues()[j]+"' color='"+ color[j % 15] + "' /> ");
			}
		} 
		return dxml.toString();
	}
	
}
