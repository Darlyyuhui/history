
package com.xiangxun.atms.module.question.web;

import java.util.List;
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
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.eventalarm.domain.EventtypeInfo;
import com.xiangxun.atms.module.eventalarm.service.EventtypeInfoService;
import com.xiangxun.atms.module.question.domain.BreakdownTypeReport;
import com.xiangxun.atms.module.question.service.BreakdownInfoService;

/**
 * 频发故障类型统计模块操作
 * @author guikaiping
 * @version 1.0
 */
@Controller
@RequestMapping(value="question/frequently/")
public class FrequentlyCtl extends BaseCtl {
	
	@Resource
	BreakdownInfoService breakdownInfoService;
	
	@Resource
	EventtypeInfoService eventtypeInfoService;
	
	/***
	 * 进入首页
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form/index/{menuid}/")
	public String index(@PathVariable String menuid, ModelMap model){
		model.addAttribute("menuid", menuid);

		return "question/frequently/form/index";
	}
	
	/***
	 * 初始化默认页面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form/blank{menuid}/")
	public String blank(@PathVariable String menuid, ModelMap model){
		//获取故障类型
		List<EventtypeInfo> typeList = eventtypeInfoService.findAll();
		String[] types = new String[typeList.size()];
		for (int i = 0; i < typeList.size(); i++) {
			types[i] = typeList.get(i).getName();
		}
		model.put("types", types);
		model.addAttribute("menuid", menuid);
		return "question/frequently/form/blank";
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
		if("day".equals(stateType)){
			return frequentlyCount(menuid, model, sortType, pageNumber, request, stateType);
		}
		//按周统计
		if("week".equals(stateType)){
			return frequentlyCount(menuid, model, sortType, pageNumber, request, stateType);
		}
		//按月统计
		if("month".equals(stateType)){
			return frequentlyCount(menuid, model, sortType, pageNumber, request, stateType);
		}
		//按年统计
		if("year".equals(stateType)){
			return frequentlyCount(menuid, model, sortType, pageNumber, request, stateType);
		}
		
		//按自定义时间段统计
		if("space".equals(stateType)){
			return frequentlyCount(menuid, model, sortType, pageNumber, request, stateType);
		}
		return null;
	}
	
	
	public String frequentlyCount(String menuid, ModelMap model,String sortType,int pageNumber, ServletRequest request, String stateType) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page page = breakdownInfoService.getFreReport(searchParams, pageNumber,Page.DEFAULT_PAGE_SIZE, sortType, menuid, stateType);
		String showTotal="0";
		if((pageNumber==page.getTotalPageCount()) ||(page.getTotalPageCount()==1)){
			BreakdownTypeReport result = breakdownInfoService.getFreReportTotal(searchParams, menuid, stateType);
			if(result != null){
				model.addAttribute("mTotals",result);
				if (Long.parseLong(result.getTotals().toString()) > 0) {
					//显示总的合计
					showTotal="1";
				}
			}
		}
		processResult(menuid, model, sortType, pageNumber, searchParams, page, showTotal);

		return "question/frequently/form/form";
	}

	//处理结果
	private void processResult(String menuid, ModelMap model, String sortType, int pageNumber, Map<String, Object> searchParams, Page page, String showTotal) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("totalPage", page.getTotalPageCount());
		model.addAttribute("curPageNo",pageNumber);
		model.addAttribute("showTotal",showTotal);
		model.addAttribute("types",searchParams.get("typeNames"));
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
	}
}
