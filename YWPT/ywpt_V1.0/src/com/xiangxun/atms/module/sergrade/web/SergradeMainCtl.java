package com.xiangxun.atms.module.sergrade.web;

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
import com.xiangxun.atms.module.question.domain.MonthReport;
import com.xiangxun.atms.module.sergrade.domain.SergradeCountReport;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;
import com.xiangxun.atms.module.sergrade.service.SergradeCountService;
import com.xiangxun.atms.module.sergrade.service.SergradeWorkOrderService;

/**
 * 服务级别管理首页
 * @author guikaiping
 *
 */
@Controller
@RequestMapping(value = "sergrade/main")
public class SergradeMainCtl extends BaseCtl {
	
	@Resource
	FactoryInfoService factoryInfoService;
	
	@Resource
	SergradeWorkOrderService sergradeWorkOrderService;
	
	@Resource
	SergradeCountService sergradeCountService;
	
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
	public String show(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "NAME") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("sortType",sortType);
		
		Page page = factoryInfoService.getReport(searchParams, pageNumber, 8, sortType, menuid);
		processResult(menuid, model, pageNumber, searchParams, page);
		return "sergrade/main/show";
	}
	
	//处理结果
	private void processResult(String menuid, ModelMap model, int pageNumber, Map<String, Object> searchParams, Page page) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("pageList", page);
	}
	
	/**
	 * 统计当月服务商运维概况服务 首页柱状图
	 * @param HttpServletResponse
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "workorder/column/{menuid}/", method = RequestMethod.POST)
	public @ResponseBody String workorderColumn(@PathVariable String menuid, HttpServletResponse response) {
		Map<String, Object> searchParams = new HashMap<String, Object>();
	 	String startTime = DateUtil.getCurrentDateStr("yyyy-MM");
		String endTime = DateUtil.getCurrentDateStr("yyyy-MM");
	
		searchParams.put("beginDate", startTime);
		searchParams.put("endDate", endTime); 
		//获取数据
		List<MonthReport> list = sergradeWorkOrderService.getWorkOrderChartReport(searchParams, menuid);
		DataJson  data = new DataJson();	    
		try {
			StringBuffer kkdayflashStr = new StringBuffer("");
			kkdayflashStr.append("<graph xAxisName='服务商名称' yAxisName='工单数(个)' caption='' subCaption='' decimalPrecision='0' rotateNames='1' numDivLines='3' numberPrefix='' showValues='0' formatNumberScale='0'>");
			StringBuffer dxml = new StringBuffer("");
			if(null != list && list.size()>0){
				dxml.append("<categories>");
				for (int i = 0; i < list.size(); i++) {
					MonthReport vo = list.get(i);
					dxml.append("<category name='"+vo.getFactoryName()+" '/>");
				}
				//事故页面柱状图 默认显示15根柱子的宽度
				if(list.size()<10){
					int tempnum = 10 - list.size();
					for(int n=0;n<tempnum;n++){
						dxml.append("<category name='    ' />");
					}
				}
				
				dxml.append("</categories>");
				
				dxml.append("<dataset seriesName='工单总数量' color='"+ color[0 % 15] + "' showValues='0'>");
				for (int i = 0; i < list.size(); i++) {
					MonthReport vo = list.get(i);
					dxml.append("<set value='"+vo.getCounts()+"'/>");
				}
				dxml.append("</dataset>");
				
				dxml.append("<dataset seriesName='已解决工单数量' color='"+ color[1 % 15] + "' showValues='0'>");
				for (int i = 0; i < list.size(); i++) {
					MonthReport vo = list.get(i);
					dxml.append("<set value='"+vo.getSolveCounts()+"'/>");
				}
				dxml.append("</dataset>");
				
				dxml.append("<dataset seriesName='未解决工单数量' color='"+ color[2 % 15] + "' showValues='0'>");
				for (int i = 0; i < list.size(); i++) {
					MonthReport vo = list.get(i);
					dxml.append("<set value='"+vo.getNosolveCounts()+"'/>");
				}
				dxml.append("</dataset>");
				
			}
			
			kkdayflashStr.append(dxml.toString());
			kkdayflashStr.append("</graph>");
			
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
	 *服务商下责任资产数量统计信息  首页柱状图
	 * @param HttpServletResponse
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "count/column/{menuid}/", method = RequestMethod.POST)
	public @ResponseBody String countColumn(@PathVariable String menuid, HttpServletResponse response) {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		//获取数据
		List<SergradeCountReport> list = sergradeCountService.getSerChartReport(searchParams, menuid);
		DataJson  data = new DataJson();	    
		try {
			StringBuffer kkdayflashStr = new StringBuffer("");
			kkdayflashStr.append("<graph xAxisName='服务商名称' yAxisName='运维设备数(台)' caption='' subCaption='' decimalPrecision='0' rotateNames='1' numDivLines='3' numberPrefix='' showValues='0' formatNumberScale='0'>");
			StringBuffer dxml = new StringBuffer("");
			if(null != list && list.size()>0){
				dxml.append("<categories>");
				for (int i = 0; i < list.size(); i++) {
					SergradeCountReport vo = list.get(i);
					dxml.append("<category name='"+vo.getFactoryName()+" '/>");
				}
				//事故页面柱状图 默认显示15根柱子的宽度
				if(list.size()<10){
					int tempnum = 10 - list.size();
					for(int n=0;n<tempnum;n++){
						dxml.append("<category name='    ' />");
					}
				}
				
				dxml.append("</categories>");
				
				dxml.append("<dataset seriesName='卡口' color='"+ color[0 % 15] + "' showValues='0'>");
				for (int i = 0; i < list.size(); i++) {
					SergradeCountReport vo = list.get(i);
					dxml.append("<set value='"+vo.getDeviceCounts()+"'/>");
				}
				dxml.append("</dataset>");
				
				/*dxml.append("<dataset seriesName='监控' color='"+ color[1 % 15] + "' showValues='0'>");
				for (int i = 0; i < list.size(); i++) {
					SergradeCountReport vo = list.get(i);
					dxml.append("<set value='"+vo.getVideoCounts()+"'/>");
				}
				dxml.append("</dataset>");*/
				
				dxml.append("<dataset seriesName='服务器' color='"+ color[3 % 15] + "' showValues='0'>");
				for (int i = 0; i < list.size(); i++) {
					SergradeCountReport vo = list.get(i);
					dxml.append("<set value='"+vo.getServerCounts()+"'/>");
				}
				dxml.append("</dataset>");
				
				dxml.append("<dataset seriesName='数据库' color='"+ color[4 % 15] + "' showValues='0'>");
				for (int i = 0; i < list.size(); i++) {
					SergradeCountReport vo = list.get(i);
					dxml.append("<set value='"+vo.getDatabaseCounts()+"'/>");
				}
				dxml.append("</dataset>");
				
				dxml.append("<dataset seriesName='FTP' color='"+ color[5 % 15] + "' showValues='0'>");
				for (int i = 0; i < list.size(); i++) {
					SergradeCountReport vo = list.get(i);
					dxml.append("<set value='"+vo.getFtpCounts()+"'/>");
				}
				dxml.append("</dataset>");
				
				dxml.append("<dataset seriesName='平台' color='"+ color[2 % 15] + "' showValues='0'>");
				for (int i = 0; i < list.size(); i++) {
					SergradeCountReport vo = list.get(i);
					dxml.append("<set value='"+vo.getProjectCounts()+"'/>");
				}
				dxml.append("</dataset>");
				dxml.append("<dataset seriesName='机柜' color='"+ color[6 % 15] + "' showValues='0'>");
				for (int i = 0; i < list.size(); i++) {
					SergradeCountReport vo = list.get(i);
					dxml.append("<set value='"+vo.getCabinetCounts()+"'/>");
				}
				dxml.append("</dataset>");
			}
			
			kkdayflashStr.append(dxml.toString());
			kkdayflashStr.append("</graph>");
			
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
