package com.xiangxun.atms.module.bs.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.bs.service.OwnerService;
import com.xiangxun.atms.module.bs.vo.Owner;
import com.xiangxun.atms.module.bs.vo.OwnerSearch;

@Controller
@RequestMapping(value = "bs/owner/")
public class OwnerCtl extends BaseCtl<Owner, OwnerSearch> {
	@Resource
	OwnerService ownerService;

	@Override
	protected BaseService<Owner, OwnerSearch> getBaseService() {
		return ownerService;
	}

	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable String menuid, Model model,
			@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request) {
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		
		Page page = ownerService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);

		return "bs/owner/list";
	}

	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable("menuid") String menuid, Model model
			, HttpServletRequest request, String page) {
		model.addAttribute("page", page);
		model.addAttribute("menuid", menuid);
		return "bs/owner/add";
	}

	@RequestMapping(value = "doAdd/{menuid}/", method = RequestMethod.POST)
	@LogAspect(desc = "保存【村民责任人】")
	public String doAdd(@PathVariable String menuid, Model model, Owner owner, HttpServletRequest request,
			String isContinue, RedirectAttributes attr) {
		owner.setId(UuidGenerateUtil.getUUIDLong());
		ownerService.save(owner);
		attr.addFlashAttribute("message", "新增成功");
		if ("1".equals(isContinue)) {
			return "redirect:/bs/owner/add/" + menuid + "/";
		}
		return "redirect:/bs/owner/list/" + menuid + "/";
	}
	
	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
		Owner r = ownerService.getById(id);
		model.addAttribute("info", r);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "bs/owner/update";
	}

	@RequestMapping(value = "doUpdate/{menuid}/", method = RequestMethod.POST)
	@LogAspect(desc = "修改【村民责任人】")
	public String doUpdate(@PathVariable String menuid, @ModelAttribute("info") Owner info, String page, RedirectAttributes attr) {
		ownerService.updateByIdSelective(info);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/bs/owner/list/" + menuid + "/?isgetsession=1&page="+page;
	}

	@RequestMapping(value = "delete/{ids}/", method = RequestMethod.POST)
	@ResponseBody
	@LogAspect(desc = "删除【村民责任人】信息")
	public ResponseEntity delete(@PathVariable("ids") String ids) {
		ResponseEntity entity = new ResponseEntity();
		try {
			logger.info("正在进行【村民责任人】数据删除");
			String[] id = ids.split(",");
			for (String string : id) {
				ownerService.deleteById(string);
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}

	@RequestMapping(value = "showView/{menuid}/{id}/")
	public String view(@PathVariable String menuid, @PathVariable("id") String id, String page, Model model) {
		Owner owner = ownerService.getById(id);
		model.addAttribute("info", owner);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "bs/owner/showView";
	}

}
