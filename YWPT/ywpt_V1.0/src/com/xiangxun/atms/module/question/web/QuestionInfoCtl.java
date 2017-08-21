
package com.xiangxun.atms.module.question.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.ListUtils;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.question.domain.QuestionInfo;
import com.xiangxun.atms.module.question.domain.QuestionInfoSearch;
import com.xiangxun.atms.module.question.domain.QuestionScheme;
import com.xiangxun.atms.module.question.domain.QuestionSchemeSearch;
import com.xiangxun.atms.module.question.service.QuestionInfoService;
import com.xiangxun.atms.module.question.service.QuestionSchemeService;

/**
 * 问题信息模块操作
 * @author guikaiping
 * @version 1.0
 */
@Controller
@RequestMapping(value="question/info/")
public class QuestionInfoCtl extends BaseCtl {
	
	@Resource
	QuestionInfoService questionInfoService;
	
	@Resource
	QuestionSchemeService questionSchemeService;
	
	
	/**
	 * 获取问题信息列表信息
	 * @param menuid
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param request
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "INSERT_TIME DESC") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");		
		super.updateSession(request, menuid, searchParams);
		
		Page page = questionInfoService.getReport(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE, sortType, menuid);
	
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("totalPage", page.getTotalPageCount());
		model.addAttribute("curPageNo",pageNumber);
		
		model.addAttribute("operator", searchParams.get("operator"));
		model.addAttribute("title", searchParams.get("title"));
		model.addAttribute("beginDate", searchParams.get("beginDate"));
		model.addAttribute("endDate", searchParams.get("endDate"));
		
		//将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
				
		return "question/info/list";
	}
	
	/***
	 * 删除问题信息
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除问题信息")
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在进行问题信息数据删除。。。。。。");
			String[] idss = ids.split(",");
			List<String> dels = Arrays.asList(idss);
			QuestionInfoSearch questionInfoSearch = new QuestionInfoSearch();
			questionInfoSearch.createCriteria().andIdIn(dels);
			questionInfoService.deleteByExample(questionInfoSearch);
			
			//关联删除问题下的方案信息
			QuestionSchemeSearch questionSchemeSearch = new QuestionSchemeSearch();
			questionSchemeSearch.createCriteria().andInfoIdIn(dels);
			questionSchemeService.deleteByExample(questionSchemeSearch);
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	/**
	 * 问题信息添加页面
	 * @param menuid
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "showadd/{menuid}/", method = RequestMethod.GET)
	public String showAdd(@PathVariable("menuid") String menuid, String page, Model model,RedirectAttributes redirectAttributes) {
		//获取当前系统时间											
		String nowTime = DateUtil.getCurrentDateStr("yyyy-MM-dd HH:mm:ss");
		
		model.addAttribute("nowTime",nowTime);
		model.addAttribute("operator",getCurrentUserId());
		model.addAttribute("operatorName",getCurrentUser().getRealName());
		
		return "question/info/add";
	}
	
	/***
	 * 添加问题信息
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	@LogAspect(desc="添加问题信息")
	public String doAdd(QuestionInfo questionInfo,String menuid,String page,RedirectAttributes redirectAttributes,HttpServletRequest request){
		logger.info("menuid:"+menuid);
		questionInfo.setId(UuidGenerateUtil.getUUID());
		questionInfo.setInsertTime(DateUtil.stringFormatToDate(questionInfo.getInsertTimeStr(), "yyyy-MM-dd HH:mm:ss"));
		questionInfoService.save(questionInfo);
		redirectAttributes.addFlashAttribute("message","添加成功");
		return "redirect:/question/info/list/"+menuid + "/";
	}
	
	/***
	 * 获取待修改问题信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		QuestionInfo questionInfo  = questionInfoService.getById(id);
		
		model.addAttribute("questionInfo", questionInfo);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		
		return "question/info/update";
	}

	/***
	 * 修改问题信息
	 * @param role
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc="修改问题信息")
	public String doUpdate(@ModelAttribute("preloadQuestionInfo") QuestionInfo questionInfo,String page,String menuid,RedirectAttributes redirectAttributes,HttpServletRequest request) {
		QuestionInfoSearch questionInfoSearch = new QuestionInfoSearch();
		questionInfoSearch.createCriteria().andIdEqualTo(questionInfo.getId());
		
		questionInfo.setInsertTime(DateUtil.stringFormatToDate(questionInfo.getInsertTimeStr(), "yyyy-MM-dd HH:mm:ss"));
		questionInfoService.updateByExampleSelective(questionInfo,questionInfoSearch);
		
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/question/info/list/"+menuid+"/";
	}
	
	/**
	 * 获取详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}/{menuid}/", method = RequestMethod.GET)
	public String showView(@PathVariable("id") String id, @PathVariable("menuid")
			String menuid, String page, @RequestParam(value = "page", defaultValue = "0") int pageNumber, Model model) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);

		QuestionInfo questionInfo = questionInfoService.getById(id);
		
		//方案列表
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("infoId", id);
		Page schemePage = questionSchemeService.getReport(searchParams, pageNumber, 10, "t.SCHEME_NO asc", menuid);
		
		model.addAttribute("questionInfo", questionInfo);
		model.addAttribute("schemePageList", schemePage);
		
		return "question/info/view";
	}
	
	/***
	 * 获取待录入方案的问题信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "scheme/{id}/{menuid}/", method = RequestMethod.GET)
	public String schemeForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,String page,Model model) {
		QuestionInfo questionInfo  = questionInfoService.getById(id);
		
		//方案列表
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("infoId", id);
		Page schemePage = questionSchemeService.getReport(searchParams, pageNumber, 100, "to_number(substr(t.SCHEME_NO,3)) asc", menuid);
		
		model.addAttribute("questionInfo", questionInfo);
		model.addAttribute("schemePageList", schemePage);
		
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		model.addAttribute("num", schemePage.getTotalSize());
		
		//获取当前系统时间											
		String nowTime = DateUtil.getCurrentDateStr("yyyy-MM-dd HH:mm:ss");
		model.addAttribute("nowTime",nowTime);
		model.addAttribute("operator",getCurrentUserId());
		
		return "question/info/scheme";
	}

	/***
	 * 录入方案
	 * @param role
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doScheme", method = RequestMethod.POST)
	@LogAspect(desc="方案录入")
	public String doScheme(String page,String menuid,RedirectAttributes redirectAttributes,HttpServletRequest request) {
		logger.info("menuid:"+menuid);
		//问题信息ID
		String infoId = request.getParameter("infoId");
		//当前操作人员ID
		String operator = getCurrentUserId();
		//获取当前系统时间											
		String nowTime = DateUtil.getCurrentDateStr("yyyy-MM-dd HH:mm:ss");
		//方案列表
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("infoId", infoId);
		List<QuestionScheme> list = questionSchemeService.getReportByInfoId(map, menuid);
		//方案信息ID集合
		String[] ids = new String[list.size()];
		//方案序号集合
		String[] schemeNos = new String[list.size()];
		//方案内容集合
		String[] contents = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ids[i] = list.get(i).getId();
			schemeNos[i] = list.get(i).getSchemeNo();
			contents[i] = list.get(i).getSchemeNo()+"&"+list.get(i).getContent();
		}
		//方案ID集合
		String[] schemeIds = request.getParameterValues("schemeId");
		//方案序号集合
		String[] workNos = request.getParameterValues("workNo");
		//方案内容集合
		String[] workContents = request.getParameterValues("workContent");
		for (int i = 0; i < workContents.length; i++) {
			workContents[i] = workNos[i]+"&"+workContents[i];
		}
		//如果该问题存在方案信息，则进行如下操作
		if (list.size() > 0) {
			//如果数据库中方案数和页面方案数相同，则为当前所有方案的修改操作
			if (list.size() == schemeIds.length) {
				for (int i = 0; i < workNos.length; i++) {
					QuestionSchemeSearch questionSchemeSearch = new QuestionSchemeSearch();
					questionSchemeSearch.createCriteria().andIdEqualTo(schemeIds[i]);
					
					QuestionScheme questionScheme = new QuestionScheme();
					questionScheme.setSchemeNo(workNos[i]);
					questionScheme.setContent(workContents[i].split("&")[1]);
					questionSchemeService.updateByExampleSelective(questionScheme,questionSchemeSearch);
				}
			//如果数据库中方案数小于页面方案数，则为当前所有方案的添加操作
			} else if (list.size() < schemeIds.length) {
				List<String> schemeNoList = ListUtils.compare(schemeNos, workNos);
				List<String> contentList = ListUtils.compare(contents, workContents);
				for (int i = 0; i < schemeNoList.size(); i++) {
					QuestionScheme questionScheme = new QuestionScheme();
					questionScheme.setId(UuidGenerateUtil.getUUID());
					questionScheme.setInfoId(infoId);
					questionScheme.setOperator(operator);
					questionScheme.setInsertTime(DateUtil.stringFormatToDate(nowTime, "yyyy-MM-dd HH:mm:ss"));
					questionScheme.setSchemeNo(schemeNoList.get(i));
					questionScheme.setContent(contentList.get(i).split("&")[1]);
					questionSchemeService.save(questionScheme);
				}
			//如果数据库中方案数大于页面方案数，则为当前所有方案的删除操作	
			} else if (list.size() > schemeIds.length) {
				List<String> idList = ListUtils.compare(schemeIds, ids);
				for (int i = 0; i < idList.size(); i++) {
					QuestionSchemeSearch questionSchemeSearch = new QuestionSchemeSearch();
					questionSchemeSearch.createCriteria().andIdIn(idList);
					questionSchemeService.deleteByExample(questionSchemeSearch);
				}
			}
		//如果该问题存在方案信息，则进行如下操作
		} else {
			for (int i = 0; i < workNos.length; i++) {
				QuestionScheme questionScheme = new QuestionScheme();
				questionScheme.setId(UuidGenerateUtil.getUUID());
				questionScheme.setInfoId(infoId);
				questionScheme.setOperator(operator);
				questionScheme.setInsertTime(DateUtil.stringFormatToDate(nowTime, "yyyy-MM-dd HH:mm:ss"));
				questionScheme.setSchemeNo(workNos[i]);
				questionScheme.setContent(workContents[i].split("&")[1]);
				questionSchemeService.save(questionScheme);
			}
		}
		
		redirectAttributes.addFlashAttribute("message","方案录入成功");
		return "redirect:/question/info/list/"+menuid+"/";
	}
	
}
