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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.bs.constant.AutoCode;
import com.xiangxun.atms.module.land.service.LandMissionService;
import com.xiangxun.atms.module.land.service.LandSamplingSchemeService;
import com.xiangxun.atms.module.land.vo.LandMission;
import com.xiangxun.atms.module.land.vo.LandMissionSearch;
import com.xiangxun.atms.module.land.vo.LandSamplingScheme;

@Controller
@RequestMapping(value = "land/mission")
public class LandMissionCtl extends BaseCtl<LandMission, LandMissionSearch> {

	@Resource
	LandMissionService landMissionService;
	@Resource
    LandSamplingSchemeService landSamplingSchemeService;
	@Resource
    Cache cache;

	@Override
	protected BaseService<LandMission, LandMissionSearch> getBaseService() {
		return landMissionService;
	}
	
	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable String menuid, Model model,
			@RequestParam(value = "sortType", defaultValue = "T.CREATE_TIME DESC") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber
			, HttpServletRequest request) {
		logger.info("【采样外业任务】数据列表查询......");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		Page page = landMissionService.getListByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE,
				sortType, menuid);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		return "land/mission/list";
	}

	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable String menuid, String page, Model model, HttpServletRequest request) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		this.getSchemeSelect(null, model);
		return "land/mission/add";
	}

	/**
	 * 获取任务选择项
	 * @param missionId
	 * @param model
	 */
	private void getSchemeSelect(String missionId, Model model) {
		//方案列表
		List<LandSamplingScheme> sList = landSamplingSchemeService.queryMissionSelectSchemes();
		//外业任务列表
		List<LandMission> mList = landMissionService.selectByExample(new LandMissionSearch());
		//key=schemeId, value=missionId
		Map<String, String> map = new HashMap<String, String>();
		for (LandMission m : mList) {
			map.put(m.getSchemeId(), m.getId());
		}
		//剔除已有任务的方案
		Iterator<LandSamplingScheme> itor = sList.iterator();
		LandSamplingScheme s = null;
		while (itor.hasNext()) {
			s = itor.next();
			//任务修改时的方案不剔除
			if (map.get(s.getId()) != null && map.get(s.getId()).equals(missionId)) {
				continue;
			}
			if (map.containsKey(s.getId())) {
				itor.remove();
			}
		}
		
		model.addAttribute("schemeList", sList);
	}
	
	@RequestMapping(value = "doAdd/{menuid}/", method = RequestMethod.POST)
	@LogAspect(desc = "保存【采样外业任务】")
	public String doAdd(@PathVariable String menuid, LandMission info
			, String page, RedirectAttributes redirectAttributes
			, HttpServletRequest request) {
		info.setId(UuidGenerateUtil.getUUIDLong());
		info.setCode(AutoCode.LAND_MISSION);
		info.setCreateId(getCurrentUserId());
		info.setCreateTime(new Date());
		landMissionService.save(info);
		redirectAttributes.addFlashAttribute("message", "添加成功");
		return "redirect:/land/mission/list/" + menuid + "/";

	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String updateForm(@PathVariable("id") String id, @PathVariable("menuid") String menuid
			, String page, Model model) {
		LandMission info = landMissionService.getById(id);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		model.addAttribute("info", info);
		this.getSchemeSelect(id, model);
		return "land/mission/update";
	}

	@RequestMapping(value = "doUpdate/{menuid}/", method = RequestMethod.POST)
	@LogAspect(desc = "修改【采样外业任务】")
	public String doUpdate(@PathVariable String menuid, @ModelAttribute("info")LandMission info,
			String page, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		info.setUpdateId(this.getCurrentUserId());
		info.setUpdateTime(new Date());
		landMissionService.updateByIdSelective(info);
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/land/mission/list/" + menuid + "/?isgetsession=1&page=" + page;
	}

	@ResponseBody
	@LogAspect(desc = "删除【采样外业任务】")
	@RequestMapping(value = "doDelete/", method = RequestMethod.POST)
	public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
		ResponseEntity entity = new ResponseEntity();
		try {
			logger.info("正在进行【采样外业任务】数据删除......");
			String[] id = ids.split(",");
			for (String string : id) {
				if (landMissionService.isDelete(string)) {
					landMissionService.deleteById(string);
				}
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			logger.error("删除采样外业任务出错。" + e.getMessage());
			entity.setResult("error");
			return entity;
		}
	}

	@RequestMapping(value = "showView/{menuid}/{id}/", method = RequestMethod.GET)
	public String showView(@PathVariable("id") String id, @PathVariable("menuid") String menuid, String page, Model model) {
		LandMission info = landMissionService.getById(id);
		LandSamplingScheme scheme = landSamplingSchemeService.getById(info.getSchemeId());
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		model.addAttribute("info", info);
		model.addAttribute("scheme", scheme);
		return "land/mission/view";
	}
	
	@ResponseBody
	@LogAspect(desc = "发布【采样外业任务】")
	@RequestMapping(value = "doSend/", method = RequestMethod.POST)
	public ResponseEntity send(@RequestParam(value = "ids", required = true) String ids) {
		ResponseEntity entity = new ResponseEntity();
		try {
			String[] id = ids.split(",");
			LandMission lm = null;
			for (String string : id) {
				lm = new LandMission();
				lm.setId(string);
				lm.setMissionStatus(1);
				landMissionService.updateByIdSelective(lm);
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			logger.error("发布采样外业任务出错。" + e.getMessage());
			entity.setResult("error");
			return entity;
		}
	}
	
	/**
	 * 采样登记时通过任务ID获取任务信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getMissionById/{id}/", method = RequestMethod.POST)
	public LandMission getMissionById(@PathVariable String id) {
		LandMission lm = landMissionService.getMissionById(id);
		lm.setRegionName(super.getRegionNameById(lm.getRegionId(), true, cache));
		return lm;
	}
	
}
