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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.bs.service.StandardMonitorService;
import com.xiangxun.atms.module.bs.vo.StandardMonitor;
import com.xiangxun.atms.module.bs.vo.StandardMonitorSearch;

@Controller
@RequestMapping(value = "bs/standardMonitor")
public class StandardMonitorCtl extends BaseCtl<StandardMonitor, StandardMonitorSearch> {
	@Resource
	StandardMonitorService standardMonitorService;

	@Override
	protected BaseService<StandardMonitor, StandardMonitorSearch> getBaseService() {
		return standardMonitorService;
	}

	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable String menuid, Model model,
			@RequestParam(value = "sortType", defaultValue = "CODE ASC") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		Page page = standardMonitorService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		StandardMonitor standardMonitor = new StandardMonitor();
	    try {
	            //将查询的map转换成对象
	            ConvertUtils.register(new DateConverter(),Date.class);
	            BeanUtils.populate(standardMonitor, searchParams);
	    } catch (IllegalAccessException e) {
	            e.printStackTrace();
	    } catch (InvocationTargetException e) {
	        	e.printStackTrace();
	    }
	    model.addAttribute("standardMonitor",standardMonitor);     
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
	
		return "bs/standardMonitor/list";
	}
	
	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable("menuid") String menuid, String page, String typeCode
			, Model model, HttpServletRequest request) {
		model.addAttribute("page", page);
		model.addAttribute("menuid", menuid);
		model.addAttribute("typeCode", typeCode);
		return "bs/standardMonitor/add";
	}

	@RequestMapping(value = "doAdd/{menuid}/", method = RequestMethod.POST)
	@LogAspect(desc = "保存【监测指标】")
	public String doAdd(@PathVariable String menuid, Model model, StandardMonitor standardMonitor, String page,
			HttpServletRequest request, String isContinue, RedirectAttributes attr) {
		standardMonitor.setId(UuidGenerateUtil.getUUIDLong());
		standardMonitor.setCreateTime(new Date());
		standardMonitor.setUpdateId(getCurrentUserId());
		standardMonitorService.save(standardMonitor);
		attr.addFlashAttribute("message", "新增成功");
		if ("1".equals(isContinue)) {
			return "redirect:/bs/standardMonitor/add/" + menuid + "/?page="+page+"&typeCode="+standardMonitor.getTypeCode();
		}
		return "redirect:/bs/standardMonitor/list/" + menuid + "/";
	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
		StandardMonitor r = standardMonitorService.getById(id);
		model.addAttribute("info", r);
		model.addAttribute("page", page);
		model.addAttribute("menuid", menuid);
		return "bs/standardMonitor/update";
	}

	@RequestMapping(value = "doUpdate/{menuid}/", method = RequestMethod.POST)
	@LogAspect(desc = "修改【监测指标】")
	public String doUpdate(@PathVariable String menuid, @ModelAttribute("info") StandardMonitor info, String page
			, HttpServletRequest request, RedirectAttributes attr) {
		info.setUpdateId(getCurrentUserId());
		info.setUpdateTime(new Date());
		standardMonitorService.updateByIdSelective(info);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/bs/standardMonitor/list/" + menuid + "/?isgetsession=1&page="+page;

	}

	@RequestMapping(value = "delete/{ids}/", method = RequestMethod.POST)
	@ResponseBody
	@LogAspect(desc = "删除【监测指标】信息。。。。。")
	public ResponseEntity delete(@PathVariable("ids") String ids) {
		ResponseEntity entity = new ResponseEntity();
		try {
			logger.info("正在进行【监测指标】数据删除。。。。。。");
			String[] id = ids.split(",");
			for (String string : id) {
				standardMonitorService.deleteById(string);
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}

	@RequestMapping(value = "showView/{menuid}/{id}/")
	public String view(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
		StandardMonitor standardMonitor = standardMonitorService.getById(id);
		model.addAttribute("info", standardMonitor);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "bs/standardMonitor/showView1123";
	}
}
