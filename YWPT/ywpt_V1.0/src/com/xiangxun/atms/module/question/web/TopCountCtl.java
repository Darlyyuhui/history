
package com.xiangxun.atms.module.question.web;

import java.util.HashMap;
import java.util.List;
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
import com.xiangxun.atms.module.eventalarm.domain.EventtypeInfo;
import com.xiangxun.atms.module.eventalarm.service.EventtypeInfoService;
import com.xiangxun.atms.module.question.domain.BreakdownTypeReport;
import com.xiangxun.atms.module.question.service.TopCountService;

/**
 * 频发故障设备TOP统计模块操作
 * @author guikaiping
 * @version 1.0
 */
@Controller
@RequestMapping(value="question/top/")
public class TopCountCtl extends BaseCtl {
	
	@Resource
	TopCountService topCountService;
	
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

		return "question/top/form/index";
	}
	
	/***
	 * 初始化默认页面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form/blank/{menuid}/")
	public String blank(@PathVariable String menuid, ModelMap model){
		Map<String, Object> emap = new HashMap<String, Object>();
		//获取故障类型
		emap.put("type", "device");
		List<EventtypeInfo> typeList = eventtypeInfoService.getReport(emap, menuid);
		String[] types = new String[typeList.size()];
		String[] typeNames = new String[typeList.size()];
		for (int i = 0; i < typeList.size(); i++) {
			types[i] = typeList.get(i).getCode();
			typeNames[i] = typeList.get(i).getName();
		}
		model.put("types", types);
		model.put("typeNames", typeNames);
		model.addAttribute("menuid", menuid);
		return "question/top/form/blank";
	}
	
	/***
	 * 查询方法
	 * @param menuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "form/search/{menuid}/{flag}/")
	public String search(@PathVariable String menuid, @PathVariable String flag, ModelMap model,@RequestParam(value = "sortType", defaultValue = "")	String sortType, @RequestParam(value = "page", defaultValue = "0")	int pageNumber,ServletRequest request){
		String stateType = request.getParameter("search_stateType");
		//按天统计
		if("day".equals(stateType)){
			return topCount(menuid, flag, model, sortType, pageNumber, request, stateType);
		}
		//按周统计
		if("week".equals(stateType)){
			return topCount(menuid, flag, model, sortType, pageNumber, request, stateType);
		}
		//按月统计
		if("month".equals(stateType)){
			return topCount(menuid, flag, model, sortType, pageNumber, request, stateType);
		}
		//按年统计
		if("year".equals(stateType)){
			return topCount(menuid, flag, model, sortType, pageNumber, request, stateType);
		}
		
		//按自定义时间段统计
		if("space".equals(stateType)){
			return topCount(menuid, flag, model, sortType, pageNumber, request, stateType);
		}
		return null;
	}
	
	
	public String topCount(String menuid, String flag, ModelMap model,String sortType,int pageNumber, ServletRequest request, String stateType) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("flag", flag);
		
		//获取频发故障设备TOP数量
		String topnum = ParamsService.SYSTEM_PARAMS.get("topnum");
		String topsql = " where rownum <= "+topnum;
		searchParams.put("topsql", topsql);
		
		Page page = topCountService.getReport(searchParams, pageNumber,Page.DEFAULT_PAGE_SIZE, sortType, menuid, stateType);
		String showTotal="0";
		if((pageNumber==page.getTotalPageCount()) ||(page.getTotalPageCount()==1)){
			BreakdownTypeReport result = topCountService.getReportTotal(searchParams, menuid, stateType);
			if(result != null){
				model.addAttribute("mTotals",result);
				if (Long.parseLong(result.getTotals().toString()) > 0) {
					//显示总的合计
					showTotal="1";
				}
			}
		}
		processResult(menuid, model, sortType, pageNumber, searchParams, page, showTotal);

		return "question/top/form/form";
	}

	//处理结果
	private void processResult(String menuid, ModelMap model, String sortType, int pageNumber, Map<String, Object> searchParams, Page page, String showTotal) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("totalPage", page.getTotalPageCount());
		model.addAttribute("curPageNo",pageNumber);
		model.addAttribute("showTotal",showTotal);
		model.addAttribute("types",searchParams.get("types"));
		String[] types = (String[]) searchParams.get("types");
		String[] typeNames = new String[types.length];
		for (int i = 0; i < types.length; i++) {
			if (null != types[i]) {
				typeNames[i] = eventtypeInfoService.getByCode(types[i]).getName();
			}
		}
		
		model.addAttribute("typeNames",typeNames);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
	}
}
