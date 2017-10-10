package com.xiangxun.atms.module.check.web;

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
import com.xiangxun.atms.module.check.service.DataCheckInfoService;

@Controller
@RequestMapping(value = "check/info")
public class DataCheckInfoCtl extends BaseCtl {

	@Resource
	DataCheckInfoService dataCheckInfoService;
	
	@RequestMapping(value="list/{menuid}/")
    public String list(@PathVariable String menuid, Model model, @RequestParam(value = "sortType", defaultValue = "ID DESC") String sortType
    		, @RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request) {
        logger.info("正在进行【数据校验集合】数据列表查询。。。。。。");
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		Page page = dataCheckInfoService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
        return "check/info/list";
    }
}
