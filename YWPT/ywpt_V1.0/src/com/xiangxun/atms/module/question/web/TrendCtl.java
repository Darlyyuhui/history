package com.xiangxun.atms.module.question.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangxun.atms.common.system.service.ParamsService;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.question.domain.MonthReport;
import com.xiangxun.atms.module.question.service.TrendCountService;
/***
 * 故障类型趋势统计分析处理
 * @author guikaiping
 */
@Controller
@RequestMapping("question/trend/")
public class TrendCtl extends BaseCtl{

	@Resource
	TrendCountService trendCountService;
	
	/***
	 * 进入首页
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form/index/{menuid}/")
	public String index(@PathVariable String menuid, ModelMap model){
		model.addAttribute("menuid", menuid);

		//页面查询条件 地点对话框显示模式
		String dialogmodel = ParamsService.SYSTEM_PARAMS.get("dialogmodel");
		model.addAttribute("dialogmodel", dialogmodel);
		
		return "question/trend/form/index";
	}
	
	/***
	 * 查询方法
	 * @param menuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "form/search/{menuid}/")
	public String search(@PathVariable String menuid, ModelMap model,@RequestParam(value = "sortType", defaultValue = "")	String sortType, @RequestParam(value = "page", defaultValue = "0")	int pageNumber,ServletRequest request){
		String stateType = request.getParameter("search_stateType");
		//按天统计
		if("1".equals(stateType)){
			return day(menuid, model, sortType, pageNumber, request);
		}
		//按周统计
		if("2".equals(stateType)){
			return week(menuid, model, sortType, pageNumber, request);
		}
		//按月统计
		if("3".equals(stateType)){
			return month(menuid, model, sortType, pageNumber, request);
		}
		//按自定义时间段统计
		if("4".equals(stateType)){
			return spaceTime(menuid, model, sortType, pageNumber, request);
		}
		return null;
	}
	
	
	/***
	 * 按天统计
	 * @param menuid
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String day(String menuid, ModelMap model,String sortType,int pageNumber, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page page = trendCountService.getDayReport(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE, sortType, menuid);
		String showTotal="0";
		if((pageNumber==page.getTotalPageCount()) ||(page.getTotalPageCount()==1)){
			Map result = trendCountService.getDayReportTotal(searchParams, menuid);
			if(result != null){
				model.putAll(result);
				//显示总的合计
				showTotal="1";
			}
		}
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		
		model.addAttribute("totalPage", page.getTotalPageCount());
		model.addAttribute("curPageNo",pageNumber);
		model.addAttribute("showTotal",showTotal);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		return "question/trend/form/day";
	}
	
	

	/***
	 * 按周统计
	 * @param menuid
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String week(String menuid, ModelMap model,String sortType,int pageNumber, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page page = trendCountService.getWeekReport(searchParams, pageNumber,Page.DEFAULT_PAGE_SIZE, sortType, menuid);
		String showTotal="0";
		if((pageNumber==page.getTotalPageCount()) ||(page.getTotalPageCount()==1)){
			Map result = trendCountService.getWeekReportTotal(searchParams, menuid);
			if(result != null){
				model.putAll(result);
				//显示总的合计
				showTotal="1";
			}
		}
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("totalPage", page.getTotalPageCount());
		model.addAttribute("curPageNo",pageNumber);
		model.addAttribute("showTotal",showTotal);
		model.put("startTime",searchParams.get("startTime"));
		model.put("endTime",searchParams.get("endTime"));
		model.put("days", searchParams.get("days"));
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		return "question/trend/form/week";
	}
	
	
	/***
	 * 按月统计
	 * @param menuid
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param request
	 * @return
	 */
	public String month(String menuid, ModelMap model,String sortType,int pageNumber, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page page = trendCountService.getMonthReport(searchParams, pageNumber,Page.DEFAULT_PAGE_SIZE, sortType, menuid);
		String showTotal="0";
		if((pageNumber==page.getTotalPageCount()) ||(page.getTotalPageCount()==1)){
			MonthReport result = trendCountService.getMonthReportTotal(searchParams, menuid);
			if(result != null){
				model.addAttribute("mTotals",result);
				if (Long.parseLong(result.getTotals().toString()) > 0) {
					//显示总的合计
					showTotal="1";
				}
			}
		}
		processResult(menuid, model, sortType, pageNumber, searchParams, page, showTotal);
		return "question/trend/form/month";
	}

	
	/***
	 * 按自定义时间段统计
	 * @param menuid
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param request
	 * @return
	 */
	public String spaceTime(String menuid, ModelMap model,String sortType,int pageNumber, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page page = trendCountService.getSpaceTimeReport(searchParams, pageNumber,Page.DEFAULT_PAGE_SIZE, sortType, menuid);
		String showTotal="0";
		if((pageNumber==page.getTotalPageCount()) ||(page.getTotalPageCount()==1)){
			MonthReport result = trendCountService.getSpaceTimeReportTotal(searchParams, menuid);
			if(result != null){
				model.addAttribute("mTotals",result);
				if (Long.parseLong(result.getTotals().toString()) > 0) {
					//显示总的合计
					showTotal="1";
				}
			}
		}
		processResult(menuid, model, sortType, pageNumber, searchParams, page, showTotal);
		return "question/trend/form/space_time";
	}
	
	//处理结果
	private void processResult(String menuid, ModelMap model, String sortType, int pageNumber, Map<String, Object> searchParams, Page page, String showTotal) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);

		model.addAttribute("totalPage", page.getTotalPageCount());
		model.addAttribute("curPageNo",pageNumber);
		model.addAttribute("showTotal",showTotal);
		model.addAttribute("days",searchParams.get("days"));
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
	}
}
