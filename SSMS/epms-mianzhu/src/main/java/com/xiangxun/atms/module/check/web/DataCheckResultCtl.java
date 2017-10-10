package com.xiangxun.atms.module.check.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.check.service.DataCheckInfoService;
import com.xiangxun.atms.module.check.service.DataCheckResultService;

@Controller
@RequestMapping(value = "check/result")
public class DataCheckResultCtl extends BaseCtl {

	@Resource
	DataCheckResultService dataCheckResultService;
	@Resource
	DataCheckInfoService dataCheckInfoService;
	
	public final String SESSON_INFO_KEY = "dataCheckInfo";
	
	@RequestMapping(value="list/{menuid}/{infoId}/")
    public String list(@PathVariable("infoId") String infoId, @PathVariable("menuid") String menuid, Model model
    		, @RequestParam(value = "sortType", defaultValue = "ID DESC") String sortType
    		, @RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request) {
        logger.info("正在进行【数据校验集合】数据列表查询。。。。。。");
        this.initSession(infoId, request);
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("infoId", infoId);
		Page page = dataCheckResultService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		model.addAttribute("menuid", menuid);
		model.addAttribute("infoId", infoId);
        return "check/result/list";
    }
	
	private void initSession(String infoId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute(SESSON_INFO_KEY, dataCheckInfoService.getById(infoId));
	}
	
	@RequestMapping(value = "loadResult/{menuid}/{infoId}/")
	public String loadResult(@PathVariable("infoId") String infoId, @PathVariable("menuid") String menuid, Model model, HttpServletRequest request) {
		this.initSession(infoId, request);
		model.addAttribute("menuid", menuid);
		model.addAttribute("infoId", infoId);
		return "check/result/load_result";
	}
	
	@ResponseBody
	@RequestMapping(value = "doCheck/{infoId}/", method = RequestMethod.POST)
	public void doCheck(@PathVariable String infoId) {
		try {
			dataCheckResultService.doCheck(infoId);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("分析数据校验出错：" + e.getMessage());
		}
	}
	
}
