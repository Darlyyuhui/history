package com.xiangxun.atms.module.land.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.land.service.LandBlockService;
import com.xiangxun.atms.module.land.service.LandSamplingPlanService;
import com.xiangxun.atms.module.land.service.LandSamplingSchemeService;
import com.xiangxun.atms.module.land.vo.LandSamplingPlan;
import com.xiangxun.atms.module.land.vo.LandSamplingScheme;
import com.xiangxun.atms.module.land.vo.LandSamplingSchemeSearch;

@Controller
@RequestMapping(value = "land/sampling/scheme")
@SessionAttributes(value = {"plans", "blocks"})
public class LandSamplingSchemeCtl extends BaseCtl<LandSamplingScheme, LandSamplingSchemeSearch> {

	@Resource
	LandSamplingSchemeService landSamplingSchemeService;
	@Resource
	LandSamplingPlanService landSamplingPlanService;
	@Resource
	LandBlockService landBlockService;
	@Resource
	DicService dicService;

	@Override
	protected BaseService<LandSamplingScheme, LandSamplingSchemeSearch> getBaseService() {
		return landSamplingSchemeService;
	}
	
	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable String menuid, Model model,
			@RequestParam(value = "sortType", defaultValue = "T.CREATE_TIME DESC") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber
			, HttpServletRequest request) {
		logger.info("【采样方案】数据列表查询......");
		this.initModel(model);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		Page page = landSamplingSchemeService.getListByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE,
				sortType, menuid);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		return "land/scheme/info/list";
	}
	
	private void initModel(Model model) {
		model.addAttribute("plans", landSamplingPlanService.queryBySelectItem());
		model.addAttribute("blocks", landBlockService.queryBySelectItem());
	}

	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable String menuid, String page, Model model, HttpServletRequest request) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "land/scheme/info/add";
	}
	
	/**
	 * 获取可选择的采样品种
	 * @param planId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getSampleCodes/{planId}/")
	public String getSampleCodes(@PathVariable String planId, String code, Model model) {
		Map<String, String> map = new HashMap<String, String>();
		//获取计划信息
		LandSamplingPlan plan = landSamplingPlanService.getById(planId);
		String[] planCodes = plan.getSampleCodes().split(",");
		//将计划中选择的采样样品拆解后放入map
		for (String str : planCodes) {
			map.put(str, str);
		}
		
		List<Dic> dicList = dicService.getDicByType(DicType.SAMPLING_TYPES);
		Iterator<Dic> itor = dicList.iterator();
		while(itor.hasNext()) {
			Dic d = itor.next();
			if (!map.containsKey(d.getCode())) {
				itor.remove();
			}
		}
		model.addAttribute("samplingTypes", dicList);
		model.addAttribute("code", code);
		return "land/scheme/info/sel_samplingCode";
	}

	@RequestMapping(value = "doAdd/{menuid}/", method = RequestMethod.POST)
	@LogAspect(desc = "保存【采样方案】")
	public String doAdd(@PathVariable String menuid, LandSamplingScheme info
			, String page, RedirectAttributes redirectAttributes
			, MultipartHttpServletRequest fileRequest) {
		info.setId(UuidGenerateUtil.getUUIDLong());
		info.setCreateId(getCurrentUserId());
		info.setCreateTime(new Date());
		landSamplingSchemeService.saveInfo(info, fileRequest);
		redirectAttributes.addFlashAttribute("message", "添加成功");
		return "redirect:/land/sampling/scheme/list/" + menuid + "/";

	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String updateForm(@PathVariable("id") String id, @PathVariable("menuid") String menuid
			, String page, Model model) {
		LandSamplingScheme info = landSamplingSchemeService.getById(id);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		model.addAttribute("info", info);
		return "land/scheme/info/update";
	}

	@RequestMapping(value = "doUpdate/{menuid}/", method = RequestMethod.POST)
	@LogAspect(desc = "修改【采样方案】")
	public String doUpdate(@PathVariable String menuid, @ModelAttribute("info")LandSamplingScheme info,
			String page, MultipartHttpServletRequest fileRequest, RedirectAttributes redirectAttributes) {
		info.setUpdateId(this.getCurrentUserId());
		info.setUpdateTime(new Date());
		landSamplingSchemeService.updateInfo(info, fileRequest);
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/land/sampling/scheme/list/" + menuid + "/?isgetsession=1&page=" + page;
	}

	@ResponseBody
	@LogAspect(desc = "删除【采样方案】")
	@RequestMapping(value = "doDelete/", method = RequestMethod.POST)
	public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
		ResponseEntity entity = new ResponseEntity();
		try {
			logger.info("正在进行【采样方案】数据删除......");
			String[] id = ids.split(",");
			for (String string : id) {
				if (landSamplingSchemeService.isDelete(string)) {
					landSamplingSchemeService.deleteById(string);
				}
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			logger.error("删除采样方案出错。" + e.getMessage());
			entity.setResult("error");
			return entity;
		}
	}

	@RequestMapping(value = "showView/{menuid}/{id}/", method = RequestMethod.GET)
	public String showView(@PathVariable("id") String id, @PathVariable("menuid") String menuid, String page, Model model) {
		
		LandSamplingScheme info = landSamplingSchemeService.getById(id);
		
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		model.addAttribute("info", info);
		return "land/scheme/info/view";
	}
	
}
