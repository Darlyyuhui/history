package com.xiangxun.atms.module.bs.web;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.bs.service.StandardIndustryService;
import com.xiangxun.atms.module.bs.vo.StandardIndustry;
import com.xiangxun.atms.module.bs.vo.StandardIndustrySearch;

@Controller
@RequestMapping(value = "bs/standardIndustry")
public class StandardIndustryCtl extends BaseCtl<StandardIndustry, StandardIndustrySearch> {
	@Resource
	StandardIndustryService standardIndustryService;

	@Override
	protected BaseService<StandardIndustry, StandardIndustrySearch> getBaseService() {
		return standardIndustryService;
	}

	@RequestMapping(value = "list/{menuid}/")
	@LogAspect(desc = "查询【行业标准】")
	public String list(@PathVariable String menuid, Model model,
			@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request) {
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		
		Page page = standardIndustryService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		StandardIndustry standardIndustry = new StandardIndustry();
	        try {
	            //将查询的map转换成对象
	            ConvertUtils.register(new DateConverter(),Date.class);
	            BeanUtils.populate(standardIndustry, searchParams);
	        } catch (IllegalAccessException e) {
	            e.printStackTrace();
	        } catch (InvocationTargetException e) {
	            e.printStackTrace();
	        }
	       
	    model.addAttribute("standardIndustry",standardIndustry);
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);

		return "bs/standardIndustry/list";
	}

	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable("menuid") String menuid, Model model, HttpServletRequest request,
			RedirectAttributes attr, String page) {
		model.addAttribute("page", page);
		model.addAttribute("menuid", menuid);
		return "bs/standardIndustry/add";
	}

	@RequestMapping(value = "doAdd/{menuid}/", method = RequestMethod.POST)
	@LogAspect(desc = "保存【行业标准】")
	public String doadd(@PathVariable String menuid, Model model, StandardIndustry standardIndustry, String isContinue,
			RedirectAttributes attr, MultipartHttpServletRequest fileRequest) {
		String id = UuidGenerateUtil.getUUIDLong();
		standardIndustry.setId(id);
		standardIndustry.setCreateTime(new Date());
		standardIndustry.setCreateId(getCurrentUserId());
		standardIndustryService.saveInfo(standardIndustry, fileRequest);
		attr.addFlashAttribute("message", "新增成功");
		if ("1".equals(isContinue)) {
			return "redirect:/bs/standardIndustry/add/" + menuid + "/";
		}
		return "redirect:/bs/standardIndustry/list/" + menuid + "/";
	}
	
	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, Model model) {
		StandardIndustry r = standardIndustryService.getById(id);
		model.addAttribute("info", r);
		model.addAttribute("menuid", menuid);
		return "bs/standardIndustry/update";
	}

	@RequestMapping(value = "doUpdate/{menuid}/{id}", method = RequestMethod.POST)
	@LogAspect(desc = "修改【行业标准】")
	public String doupdate(@PathVariable String menuid, @ModelAttribute("info") StandardIndustry info,
			RedirectAttributes attr, MultipartHttpServletRequest fileRequest) {
		
		info.setUpdateId(getCurrentUserId());
		info.setUpdateTime(new Date());
		standardIndustryService.updateInfo(info, fileRequest);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/bs/standardIndustry/list/" + menuid + "/";
	}

	@RequestMapping(value = "delete/{ids}/", method = RequestMethod.POST)
	@ResponseBody
	@LogAspect(desc = "删除【行业标准】信息")
	public ResponseEntity delete(@PathVariable("ids") String ids) {
		ResponseEntity entity = new ResponseEntity();
		try {
			logger.info("正在进行《行业标准》数据删除。。。。。。");
			String[] id = ids.split(",");
			for (String string : id) {
				standardIndustryService.deleteById(string);
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}

	@RequestMapping(value = "/showView/{menuid}/{id}/")
	public String view(@PathVariable String menuid, @PathVariable String id, String page, Model model) {
		StandardIndustry standardIndustry = standardIndustryService.getById(id);
		model.addAttribute("standardIndustry", standardIndustry);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "bs/standardIndustry/showView";
	}

}
