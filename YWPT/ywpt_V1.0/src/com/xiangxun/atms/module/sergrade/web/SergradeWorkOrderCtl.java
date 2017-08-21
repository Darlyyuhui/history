package com.xiangxun.atms.module.sergrade.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.question.domain.MonthReport;
import com.xiangxun.atms.module.sergrade.service.SergradeWorkOrderService;
/***
 * 卡口号牌颜色流量统计分析处理
 * @author guikaiping
 */
@Controller
@RequestMapping("sergrade/workorder/")
public class SergradeWorkOrderCtl extends BaseCtl{

	@Resource
	SergradeWorkOrderService sergradeWorkOrderService;
	
	/***
	 * 进入首页
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form/index/{menuid}/")
	public String index(@PathVariable String menuid, ModelMap model){
		model.addAttribute("menuid", menuid);
		
		return "sergrade/workorder/form/index";
	}
	
	/***
	 * 初始化默认页面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form/blank/{menuid}/")
	public String blank(@PathVariable String menuid, ModelMap model){
		//获取当前年
		String currYear = DateUtil.currentYear();
		//获取当前月份
		String currMonth = DateUtil.currentMonth();
		String date = currYear + "-" + currMonth;
		
		model.addAttribute("date", date);
		model.addAttribute("menuid", menuid);
		return "sergrade/workorder/form/blank";
	}
	
	/***
	 * 查询方法
	 * @param menuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "search/{menuid}/")
	public String search(@PathVariable String menuid, ModelMap model,@RequestParam(value = "sortType", defaultValue = "factoryid") String sortType, @RequestParam(value = "page", defaultValue = "0")	int pageNumber,ServletRequest request){
		return workorderCount(menuid, model, sortType, pageNumber, request);
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
	public String workorderCount(String menuid, ModelMap model,String sortType,int pageNumber, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page page = sergradeWorkOrderService.getWorkOrderReport(searchParams, pageNumber,Page.DEFAULT_PAGE_SIZE, sortType, menuid);
		String showTotal="0";
		if((pageNumber==page.getTotalPageCount()) ||(page.getTotalPageCount()==1)){
			MonthReport result = sergradeWorkOrderService.getWorkOrderReportTotal(searchParams, menuid);
			if(result != null){
				model.addAttribute("mTotals",result);
				if (Long.parseLong(result.getTotals().toString()) > 0) {
					//显示总的合计
					showTotal="1";
				}
			}
		}
		processResult(menuid, model, sortType, pageNumber, searchParams, page, showTotal);
		return "sergrade/workorder/form/form";
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
