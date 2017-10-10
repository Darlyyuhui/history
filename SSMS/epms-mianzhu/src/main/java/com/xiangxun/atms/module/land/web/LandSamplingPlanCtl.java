package com.xiangxun.atms.module.land.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Table;
import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.bs.constant.AutoCode;
import com.xiangxun.atms.module.check.service.DataCheckInfoService;
import com.xiangxun.atms.module.land.service.LandSamplingPlanService;
import com.xiangxun.atms.module.land.vo.LandSamplingPlan;
import com.xiangxun.atms.module.land.vo.LandSamplingPlanSearch;

@Controller
@RequestMapping(value = "land/sampling/plan")
@SessionAttributes(value = {"samplingTypes"})
public class LandSamplingPlanCtl extends BaseCtl<LandSamplingPlan, LandSamplingPlanSearch> {

	@Resource
	LandSamplingPlanService landSamplingPlanService;
	@Resource
	DataCheckInfoService dataCheckInfoService;
	@Resource
	DicService dicService;
	@Resource
	Cache cache;

	@Override
	protected BaseService<LandSamplingPlan, LandSamplingPlanSearch> getBaseService() {
		return landSamplingPlanService;
	}
	
	@Override
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable String menuid, Model model,
			@RequestParam(value = "sortType", defaultValue = "CREATE_TIME DESC") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber
			, HttpServletRequest request) {
		logger.info("【采样计划】数据列表查询......");
		this.initModel(model);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		Page page = landSamplingPlanService.getListByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE,
				sortType, menuid);
		
		//采样类型字典缓存
		Table<String, String, String> table = (Table<String, String, String>)cache.get("Dic");
		Map<String, String> map = table.column("SAMPLING_TYPES");
		List<LandSamplingPlan> list = page.getResult();
		String[] samplingCodeArray = null;
		for (LandSamplingPlan lsp : list) {
			if (StringUtils.isEmpty(lsp.getSampleCodes())) {
				continue;
			}
			//拆解采样类型串
			samplingCodeArray = lsp.getSampleCodes().split(",");
			String typeCode = "";
			//将拆解的字典类型拼装成字符串
			for (String str : samplingCodeArray) {
				typeCode += map.get(str) + ",";
			}
			lsp.setSampleCodes(typeCode.substring(0, typeCode.length()-1));
		}
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		return "land/plan/list";
	}
	
	//初始化加载
	private void initModel(Model model) {
		model.addAttribute("samplingTypes", dicService.getDicByType(DicType.SAMPLING_TYPES));
	}
	
	
	/**
	 * 初始化可选的采样品种
	 * @param id 修改时的对象ID
	 * @param model
	 */
	private void initTypesDic(String id, Model model) {
		//获取采样类型字典
		List<Dic> list = dicService.getDicByType(DicType.SAMPLING_TYPES);
		//存放采样类型是否可被选择的map， key=字典code，value=是否disable
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		for (Dic d : list) {
			map.put(d.getCode(), false);
		}
		
		//查询未完成项目
		LandSamplingPlanSearch search = new LandSamplingPlanSearch();
		if (StringUtils.isEmpty(id)) {
			search.createCriteria().andIsFinishEqualTo(0);
		} else {
			search.createCriteria().andIsFinishEqualTo(0).andIdNotEqualTo(id);
		}
		List<LandSamplingPlan> planList = landSamplingPlanService.selectByExample(search);
		if (planList != null && planList.size() != 0) {
			String[] samplingCodeArray = null;
			for (LandSamplingPlan lsp : planList) {
				if (StringUtils.isEmpty(lsp.getSampleCodes())) {
					continue;
				}
				//拆解采样类型串
				samplingCodeArray = lsp.getSampleCodes().split(",");
				for (String str : samplingCodeArray) {
					map.put(str, true);
				}
			}
		}
		model.addAttribute("disableMap", map);
	}

	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable String menuid, String page, Model model, HttpServletRequest request) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		this.initTypesDic(null, model);
		return "land/plan/add";
	}

	@RequestMapping(value = "doAdd/{menuid}/", method = RequestMethod.POST)
	@LogAspect(desc = "保存【采样计划】")
	public String doAdd(@PathVariable String menuid, LandSamplingPlan info
			, String page, RedirectAttributes redirectAttributes
			, HttpServletRequest request) {
		info.setId(UuidGenerateUtil.getUUIDLong());
		info.setCode(AutoCode.LAND_SAMPLING_PLAN);
		info.setCreateId(getCurrentUserId());
		info.setCreateTime(new Date());
		landSamplingPlanService.save(info);
		redirectAttributes.addFlashAttribute("message", "添加成功");
		return "redirect:/land/sampling/plan/list/" + menuid + "/";

	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String updateForm(@PathVariable("id") String id, @PathVariable("menuid") String menuid
			, String page, Model model) {
		LandSamplingPlan info = landSamplingPlanService.getById(id);
		Map<String, Boolean> checkedMap = new HashMap<String, Boolean>();
		String[] samplingTypes = info.getSampleCodes().split(",");
		for (String typeCpde : samplingTypes) {
			checkedMap.put(typeCpde, true);
		}
		model.addAttribute("checkedMap", checkedMap);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		model.addAttribute("info", info);
		this.initTypesDic(id, model);
		return "land/plan/update";
	}

	@RequestMapping(value = "doUpdate/{menuid}/", method = RequestMethod.POST)
	@LogAspect(desc = "修改【采样计划】")
	public String doUpdate(@PathVariable String menuid, @ModelAttribute("info")LandSamplingPlan info,
			String page, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		info.setUpdateId(this.getCurrentUserId());
		info.setUpdateTime(new Date());
		landSamplingPlanService.updateByIdSelective(info);
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/land/sampling/plan/list/" + menuid + "/?isgetsession=1&page=" + page;
	}

	@ResponseBody
	@LogAspect(desc = "删除【采样计划】")
	@RequestMapping(value = "doDelete/", method = RequestMethod.POST)
	public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
		ResponseEntity entity = new ResponseEntity();
		try {
			logger.info("正在进行【采样计划】数据删除......");
			String[] id = ids.split(",");
			for (String string : id) {
				if (landSamplingPlanService.isDelete(string)) {
					landSamplingPlanService.deleteById(string);
				}
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			logger.error("删除采样计划出错。" + e.getMessage());
			entity.setResult("error");
			return entity;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "showView/{menuid}/{id}/", method = RequestMethod.GET)
	public String showView(@PathVariable("id") String id, @PathVariable("menuid") String menuid, String page, Model model) {
		LandSamplingPlan info = landSamplingPlanService.getById(id);
		//采样类型字典缓存
		Table<String, String, String> table = (Table<String, String, String>)cache.get("Dic");
		Map<String, String> map = table.column("SAMPLING_TYPES");
		if (StringUtils.isNotEmpty(info.getSampleCodes())) {
			//拆解采样类型串
			String[] samplingCodeArray = info.getSampleCodes().split(",");
			String typeCode = "";
			//将拆解的字典类型拼装成字符串
			for (String str : samplingCodeArray) {
				typeCode += map.get(str) + ",";
			}
			info.setSampleCodes(typeCode.substring(0, typeCode.length()-1));
		}
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		model.addAttribute("info", info);
		return "land/plan/view";
	}
	
	@LogAspect(desc = "完成【采样计划】")
	@ResponseBody
	@RequestMapping(value = "doFinish/", method = RequestMethod.POST)
	public ResponseEntity doFinish(@RequestParam(value = "ids", required = true) String ids) {
		ResponseEntity re = new ResponseEntity();
		try {
			String[] idArray = ids.split(",");
			for (String planId : idArray) {
				landSamplingPlanService.doFinish(planId);
				dataCheckInfoService.saveInfoByPlanId(planId);
			}
			re.setResult("ok");
		}catch (Exception e) {
			logger.error("调用存储过程出错。" + e.getMessage());
			re.setResult("error");
		}
		return re;
	}
	
}
