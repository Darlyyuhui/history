package com.xiangxun.atms.module.statistics.web;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.statistics.service.ApbService;
/**
 * 农产品基地产品
 * @author admin
 *
 */
@Controller
@RequestMapping(value = "statistics/apb")
public class ApbCtl extends BaseCtl {
    
	@Resource
    ApbService apbService;

    @RequestMapping(value="product/{menuid}/")
    public String list(@PathVariable String menuid
    		, @RequestParam(value = "sortType", defaultValue = "T.ID") String sortType
    		, @RequestParam(value = "page", defaultValue = "0") int pageNumber, Model model, HttpServletRequest request) {
    	
    	Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		Page page = apbService.queryPage(pageNumber, Page.DEFAULT_PAGE_SIZE, sortType, searchParams);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		
        return "statistics/apb/list";
    }

	
}