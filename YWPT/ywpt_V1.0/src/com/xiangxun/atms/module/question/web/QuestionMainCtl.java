package com.xiangxun.atms.module.question.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.type.DataJson;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.JsonUtil;
import com.xiangxun.atms.module.eventalarm.domain.EventtypeInfo;
import com.xiangxun.atms.module.eventalarm.service.EventtypeInfoService;
import com.xiangxun.atms.module.question.domain.BreakdownTypeReport;
import com.xiangxun.atms.module.question.service.BreakdownInfoService;
import com.xiangxun.atms.module.question.service.QuestionInfoService;

/**
 * 问题管理首页
 * @author guikaiping
 *
 */
@Controller
@RequestMapping(value = "question/main")
public class QuestionMainCtl extends BaseCtl {
	
	@Resource
	QuestionInfoService questionInfoService;
	
	@Resource
	BreakdownInfoService breakdownInfoService;
	
	@Resource
	EventtypeInfoService eventtypeInfoService;
	
	//FLASH图标颜色数组
	private String color[] = { "AFD8F8", "51DCFF","5F96FC", "8BBA00", "FF8E46",
			"008E8E", "D64646", "8E468E", "588526", "B3AA00", "008ED6",
			"9D080D", "A186BE", "346DB2", "C13A36", "A02F2B", "7C9D35",
			"6A4A8D", "2B8CAC", "D2721E", "7792C1", "BF7B7A", "A6BC7C",
			"9183A7" };
	/***
	 * 进入主页面 
	 * @param menuid
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(value="show/{menuid}/")
	public String show(@PathVariable String menuid,ModelMap model,@RequestParam(value = "page", defaultValue = "0") int pageNumber,@RequestParam(value = "sortType", defaultValue = "INSERT_TIME DESC") String sortType, HttpServletRequest request){
		Map<String, Object> searchParams = new HashMap<String, Object>();
		//获取当前查询时间
		String day = DateUtil.currentDate();
		//获取这一周的日期
		String[] starts = DateUtil.getThisWeekStartAndEndByDate(day);
		searchParams.put("beginDate", starts[0] + "00:00:00");
		searchParams.put("endDate",starts[1] + "23:59:59");
		searchParams.put("sortType",sortType);
		
		Page page = questionInfoService.getReport(searchParams, pageNumber, 8, sortType, menuid);
		processResult(menuid, model, pageNumber, searchParams, page);
		
		return "question/main/show";
	}
	
	//处理结果
	private void processResult(String menuid, ModelMap model, int pageNumber, Map<String, Object> searchParams, Page page) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("pageList", page);
	}
	
	
	
	/**
	 * 按天统计各部门事故数量服务 首页柱状图
	 * @param HttpServletResponse
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "column/{menuid}/", method = RequestMethod.POST)
	public @ResponseBody String countByAreaColumn(@PathVariable String menuid, HttpServletResponse response) {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		//获取当前查询时间
		String day = DateUtil.currentDate();
		//获取这一周的日期
		String[] starts = DateUtil.getThisWeekStartAndEndByDate(day);
		//获取当天各部门下事故统计信息
		List<BreakdownTypeReport> list = breakdownInfoService.getFreChartReport(searchParams, menuid, starts[0], starts[1], "week");// 查询 当前起始日期的 数据
		
		DataJson  data = new DataJson();	    
		try {
			StringBuffer kkdayflashStr = new StringBuffer("");
			kkdayflashStr.append("<chart  caption='' hoverCapSepChar=''  aboutMenuItemLabel='西安翔迅科技有限公司'  aboutMenuItemLink=''  canvasbgColor='#EFEFEF'  xAxisName='厂商名称'  yAxisName='故障数量(次)'  bgColor='#FBFBFB'  formatNumber='1'   rotateYAxisName='1'  baseFont='微软雅黑'  baseFontSize='12'  outCnvBaseFont='微软雅黑'  outCnvBaseFontSize='12'  decimalPrecision='0'  chartLeftMargin='5'  showValues='0'  formatNumberScale='0'  labelDisplay='wrap'  showBorder='0'  borderColor='#cccccc'  >");
			StringBuffer dxml = new StringBuffer("");
			if(null != list && list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					BreakdownTypeReport vo = list.get(i);
					dxml.append("<set name='"+vo.getFactoryName()+"' value='"+vo.getCounts()+"' color='"+ color[i % 15] + "' /> ");
				}
				//事故页面柱状图 默认显示15根柱子的宽度
				if(list.size()<15){
					int tempnum = 15 - list.size();
					for(int n=0;n<tempnum;n++){
						dxml.append("<set name='    ' />");
					}
				}
			}
			
			kkdayflashStr.append(dxml.toString());
			kkdayflashStr.append("</chart>");
			//用于判断是否存在数据
			if (list.size() == 0) {
				data.setMessage("nodata");
			} else {
				data.setMessage(kkdayflashStr.toString());
			}
			
			data.setSuccess(true);
  	    } catch (Exception e) {
	  		e.printStackTrace();
	  		data.setSuccess(false);
	  	}
	  	String jsonStr;
		try {
			jsonStr = JsonUtil.toJson(data);
			response.setCharacterEncoding("utf-8");
		  	response.getWriter().print(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 按天统计各事故类型数量 卡口首页饼图
	 * @param HttpServletResponse
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "pie/{menuid}/", method = RequestMethod.POST)
	public @ResponseBody String countByAreaPie(@PathVariable String menuid, HttpServletResponse response) {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		//获取故障类型
		List<EventtypeInfo> typeList = eventtypeInfoService.findAll();
		String[] types = new String[typeList.size()];
		for (int i = 0; i < typeList.size(); i++) {
			types[i] = typeList.get(i).getCode();
		}
		
		//获取当前查询时间
		String day = DateUtil.currentDate();
		//获取这一周的日期
		String[] starts = DateUtil.getThisWeekStartAndEndByDate(day);
		searchParams.put("startTime", starts[0]);
		searchParams.put("endTime",starts[1]);
		searchParams.put("types", types);
		//查询数据
		List<BreakdownTypeReport> list = breakdownInfoService.getFrePieReport(searchParams, menuid, starts[0], starts[1], "week");
		DataJson data = new DataJson();	    
		try {
			StringBuffer kkdayflashStr = new StringBuffer("");
			kkdayflashStr.append("<chart caption='' aboutMenuItemLabel='西安翔迅科技有限公司'  aboutMenuItemLink=''  bgColor='#FBFBFB'  formatNumber='1'  numberSuffix='起'  rotateYAxisName='0'  baseFont='微软雅黑'  baseFontSize='12'  outCnvBaseFont='微软雅黑'  outCnvBaseFontSize='12'  decimalPrecision='0'  chartLeftMargin='5'  shownames='1'  showValues='1'  animation='1'  formatNumberScale='0'  showPercentageValues='0'  startingAngle='45'  slicingDistance='1'  enableSmartLabels='1'  enableRotation='1'  >");
			StringBuffer dxml = new StringBuffer("");
			if(list!=null && list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					BreakdownTypeReport vo = list.get(i);
					for (int j = 0; j < types.length; j++) {
						dxml.append("<set name='"+vo.getTypeNames()[j].replace("%", "%25")+"' value='"+vo.getValues()[j]+"' color='"+ color[j % 15] + "' /> ");
					}
				} 
			} 
			
			kkdayflashStr.append(dxml.toString());
			kkdayflashStr.append("</chart>");
			//用于判断是否存在数据
			if (list.size() == 0) {
				data.setMessage("nodata");
			} else {
				data.setMessage(kkdayflashStr.toString());
			}
			data.setSuccess(true);
  	    } catch (Exception e) {
	  		e.printStackTrace();
	  		data.setSuccess(false);
	  	}
	  	String jsonStr;
		try {
			jsonStr = JsonUtil.toJson(data);
			response.setCharacterEncoding("utf-8");
		  	response.getWriter().print(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
