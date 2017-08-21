
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
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.sergrade.domain.SergradeCountReport;
import com.xiangxun.atms.module.sergrade.service.SergradeCountService;

/**
 * 服务商下的责任资产数量统计模块操作
 * @author guikaiping
 * @version 1.0
 */
@Controller
@RequestMapping(value="sergrade/count/")
public class SergradeCountCtl extends BaseCtl {
	
	@Resource
	SergradeCountService sergradeCountService;
	
	/***
	 * 进入首页
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form/index/{menuid}/")
	public String index(@PathVariable String menuid, ModelMap model){
		model.addAttribute("menuid", menuid);

		return "sergrade/count/form/index";
	}
	
	/***
	 * 初始化默认页面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form/blank{menuid}/")
	public String blank(@PathVariable String menuid, ModelMap model){
		model.addAttribute("menuid", menuid);
		return "sergrade/count/form/blank";
	}
	
	/***
	 * 查询方法
	 * @param menuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "form/search/{menuid}/")
	public String search(@PathVariable String menuid, ModelMap model, @RequestParam(value = "page", defaultValue = "0")	int pageNumber,ServletRequest request){
		return sergradeCount(menuid, model, pageNumber, request);
	}
	/**
	 * 服务商下的责任资产数量统计
	 * @param menuid
	 * @param model
	 * @param pageNumber
	 * @param request
	 * @return
	 */
	public String sergradeCount(String menuid, ModelMap model,int pageNumber, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page page = sergradeCountService.getSerReport(searchParams, pageNumber,Page.DEFAULT_PAGE_SIZE, menuid);
		String showTotal="0";
		if((pageNumber==page.getTotalPageCount()) ||(page.getTotalPageCount()==1)){
			SergradeCountReport result = sergradeCountService.getSerReportTotal(searchParams, menuid);
			if(result != null){
				model.addAttribute("mTotals",result);
				if (Long.parseLong(result.getTotals().toString()) > 0) {
					//显示总的合计
					showTotal="1";
				}
			}
		}
		processResult(menuid, model, pageNumber, searchParams, page, showTotal);

		return "sergrade/count/form/form";
	}

	//处理结果
	private void processResult(String menuid, ModelMap model, int pageNumber, Map<String, Object> searchParams, Page page, String showTotal) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("pageList", page);
		model.addAttribute("totalPage", page.getTotalPageCount());
		model.addAttribute("curPageNo",pageNumber);
		model.addAttribute("showTotal",showTotal);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
	}
}
