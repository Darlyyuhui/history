package com.xiangxun.atms.module.eventalarm.web;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.common.user.service.UserService;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.module.eventalarm.domain.ExceptionInfo;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderAppraise;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderLog;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderRule;
import com.xiangxun.atms.module.eventalarm.service.ExceptionInfoService;
import com.xiangxun.atms.module.eventalarm.service.PublicMethodService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderAppraiseService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderLogService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderRuleService;
import com.xiangxun.atms.module.eventalarm.status.WorkorderStatus;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;

/**
 * 工单评估
 * @author kouyunhao
 *
 */
@Controller
@RequestMapping(value = "alarm/workappraise")
public class WorkorderAppraiseCtl extends BaseCtl {
	
	@Resource
	WorkorderInfoService workorderInfoService;
	
	@Resource
	WorkorderRuleService workorderRuleService;
	
	@Resource
	WorkorderAppraiseService workorderAppraiseService;
	
	@Resource
	PublicMethodService publicMethodService;
	
	@Resource
	ExceptionInfoService exceptionInfoService;
	
	@Resource
	UserService userService;
	
	@Resource
	WorkorderLogService workorderLogService;
	
	@Resource
	FactoryInfoService factoryInfoService;
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		logger.info("正在进行工单评估信息列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
		SessionParameter sp = new SessionParameter();
		sp.updateSessionMap(request, menuid, searchParams);
		searchParams.put("sortType", "ASSIGNTIME desc");
		searchParams.put("status", WorkorderStatus.CLOSED.getValue());
		Page page = workorderInfoService.getListByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);
		publicMethodService.setStatusColor(page);
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		
		WorkorderInfo workorder = new WorkorderInfo();
		try {
			BeanUtils.populate(workorder, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("workorder", workorder);
		
		return "alarm/workorder/appraise/list";
	}

	/***
	 * 获取待评估工单信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "appraise/{id}/{menuid}/", method = RequestMethod.GET)
	public String appraise(@PathVariable("id") String id, @PathVariable("menuid") String menuid, 
			@RequestParam(value = "sortType", defaultValue = "OPERTIME desc") String sortType,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,Model model, HttpServletRequest request) throws ParseException {
		WorkorderInfo workorder = workorderInfoService.getById(id);
		model.addAttribute("workorder", workorder);
		publicMethodService.setStatusColor(workorder);
		String exceptionid = workorder.getExceptionid();
		if(exceptionid != null){
			ExceptionInfo exception = exceptionInfoService.getById(exceptionid);
			model.addAttribute("exception", exception);
		}
		model.addAttribute("pageList",workorderRuleService.findAll());
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",pageNumber);
		return "alarm/workorder/appraise/appraise";
	}

	/***
	 * 工单评估
	 * @param rule
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doAppraise", method = RequestMethod.POST)
	@LogAspect(desc="工单评估信息")
	public String doUpdate(@ModelAttribute("preload") WorkorderAppraise appraise,
			String page,String menuid,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String ruleids = appraise.getRuleids();
		String workorderid = appraise.getWorkorderid();
		WorkorderInfo workorder = workorderInfoService.getById(workorderid);
		String finalscore = String.valueOf(appraise.getFinalscore());
		if(!ruleids.equals("")){
			String[] id = ruleids.split(",");
			for (String string : id) {
				WorkorderRule rule = workorderRuleService.getById(string);
				appraise = new WorkorderAppraise();
				appraise.setId(UuidGenerateUtil.getUUID());
				appraise.setWorkorderid(workorderid);
				appraise.setFinalscore(new BigDecimal(finalscore));
				appraise.setContactname(userService.getById(workorder.getContact()).getName());
				appraise.setTelephone(workorder.getTelephone());
				appraise.setViolaterule(rule.getName());
				appraise.setDeductscore(rule.getScore());
				appraise.setAppraiser(getCurrentUserId());
				appraise.setAppraisetime(new Date());
				workorderAppraiseService.save(appraise);
			}
		}else{
			appraise.setId(UuidGenerateUtil.getUUID());
			appraise.setContactname(userService.getById(workorder.getContact()).getName());
			appraise.setTelephone(workorder.getTelephone());
			appraise.setAppraiser(getCurrentUserId());
			appraise.setAppraisetime(new Date());
			workorderAppraiseService.save(appraise);
		}
		
		// add update fuwushangpingfen by tianbo 
		int countresult = workorderAppraiseService.findAvgFinalscore(workorder.getCompanyid());
		FactoryInfo factoryInfo = new FactoryInfo();
		factoryInfo.setId(workorder.getCompanyid());
		factoryInfo.setPoint(BigDecimal.valueOf(countresult));
		factoryInfoService.updateByIdSelective(factoryInfo);
		
		//添加日志信息
		WorkorderLog log = new WorkorderLog();
		log.setId(UuidGenerateUtil.getUUID());
		log.setOperator(getCurrentUserId());
		log.setAccount(getCurrentUser().getAccount());
		log.setOpertime(new Date());
		log.setOpercontent("工单评估");
		log.setWorkstatus(WorkorderStatus.APPRAISED.getValue());
		log.setWorkid(workorderid);
		log.setNote(String.valueOf(appraise.getFinalscore()));
		workorderLogService.save(log);
		workorder.setNote(String.valueOf(appraise.getFinalscore()));
		workorder.setStatus(WorkorderStatus.APPRAISED.getValue());
		workorderInfoService.updateByIdSelective(workorder);
		redirectAttributes.addFlashAttribute("message", "工单评估成功");
		return "redirect:/alarm/workappraise/list/"+menuid+"/?page="+page;
	}
	
	/**
	 * ajax获取评估分数
	 * @author kouyunhao
	 * @param ids
	 * @param response
	 */
	@RequestMapping(value="getScoreByAjax/{ids}/",method = RequestMethod.GET)
	public void getScoreByAjax(@PathVariable String ids, HttpServletResponse response){
		String[] id = ids.split(",");
		int deduct = 0;
		for (String string : id) {
			WorkorderRule rule = workorderRuleService.getById(string);
			if(rule != null){
				deduct += Integer.parseInt(rule.getScore()+"");
			}
		}
		try {
			response.getWriter().write(String.valueOf(100-deduct));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 获取详情信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}/{menuid}/", method = RequestMethod.GET)
	public String showView(@PathVariable("id") String id, @PathVariable("menuid") String menuid, 
			@RequestParam(value = "sortType", defaultValue = "OPERTIME desc") String sortType,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,Model model, HttpServletRequest request) throws ParseException {
		WorkorderInfo workorder = workorderInfoService.getById(id);
		publicMethodService.setStatusColor(workorder);
		String exceptionid = workorder.getExceptionid();
		if(exceptionid != null){
			ExceptionInfo exception = exceptionInfoService.getById(exceptionid);
			model.addAttribute("exception", exception);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("workorderid", id);
		Page page = workorderAppraiseService.getListByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE-5, sortType);
		String note = workorder.getNote();
		if(note != null){
			String color = "green";
			String level = "优";
			int score = Integer.parseInt(note);
			if(score >= 80 && score < 90){
				color = "orange";
				level = "中";
			}else if(score < 80){
				color = "red";
				level = "差";
			}
			model.addAttribute("color", color);
			model.addAttribute("level", level);
		}
		model.addAttribute("pageList", page);
		model.addAttribute("workorder", workorder);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",pageNumber);
		return "alarm/workorder/appraise/view";
	}

}
