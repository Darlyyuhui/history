package com.xiangxun.atms.module.eventalarm.web;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.module.eventalarm.domain.ExceptionInfo;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderLog;
import com.xiangxun.atms.module.eventalarm.service.ExceptionInfoService;
import com.xiangxun.atms.module.eventalarm.service.PublicMethodService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderLogService;
import com.xiangxun.atms.module.eventalarm.status.WorkorderStatus;
import com.xiangxun.atms.module.repository.domain.KnowledgeInfo;
import com.xiangxun.atms.module.repository.service.CatalogInfoService;
import com.xiangxun.atms.module.repository.service.KnowledgeInfoService;

/**
 * 运维情况上报
 * @author kouyunhao
 *
 */
@Controller
@RequestMapping(value="alarm/workreport")
public class WorkorderReportCtl extends BaseCtl {
	
	@Resource
	WorkorderInfoService workorderInfoService;
	
	@Resource
	ExceptionInfoService exceptionInfoService;
	
	@Resource
	WorkorderLogService workorderLogService;
	
	@Resource
	KnowledgeInfoService knowledgeInfoService;
	
	@Resource
	PublicMethodService publicMethodService;
	
	@Resource
	CatalogInfoService catalogInfoService;
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		logger.info("正在进行运维情况上报信息列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
		SessionParameter sp = new SessionParameter();
		sp.updateSessionMap(request, menuid, searchParams);
		
		/*
		String[] statusarr = new String[]{WorkorderStatus.RECEIVED.getValue(),WorkorderStatus.COMPLETE.getValue()};
		StringBuilder sb = new StringBuilder();
		for(String st : statusarr){
			sb.append("'" + st + "',");
		}
		searchParams.put("sortType", "ASSIGNTIME desc");
		searchParams.put("statusin", StringUtils.removeEnd(sb.toString(), ","));*/
		
		
		
		searchParams.put("status", WorkorderStatus.RECEIVED.getValue());
		searchParams.put("sortType", "ASSIGNTIME desc");
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
		
		return "alarm/workorder/report/list";
	}
	
	/**
	 * 运维情况异常上报
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doRecord", method = RequestMethod.POST)
	@LogAspect(desc = "运维情况上报")
	public String doModify(WorkorderInfo workorder,ExceptionInfo exception, String menuid, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String ids = workorder.getId();
		String[] id = ids.split(",");
		String exceptionid = UuidGenerateUtil.getUUID();
		exception.setId(exceptionid);
		exception.setReporttime(DateUtil.stringFormatToDate(exception.getReporttimeStr(), "yyyy-MM-dd HH:mm:ss"));
		exceptionInfoService.save(exception);
		for (String string : id) {
			workorder = workorderInfoService.getById(string);
			workorder.setExceptionid(exceptionid);
			workorder.setStatus(WorkorderStatus.REPORTED.getValue());
			workorderInfoService.updateByIdSelective(workorder);
			//添加日志信息
			WorkorderLog log = new WorkorderLog();
			log.setId(UuidGenerateUtil.getUUID());
			log.setOperator(getCurrentUserId());
			log.setAccount(getCurrentUser().getAccount());
			log.setOpertime(new Date());
			log.setOpercontent("异常上报");
			log.setWorkstatus(WorkorderStatus.REPORTED.getValue());
			log.setWorkid(string);
			log.setReason(exception.getContent());
			workorderLogService.save(log);
		}
		
		redirectAttributes.addFlashAttribute("message", "异常上报成功");
		return "redirect:/alarm/workreport/list/" + menuid + "/";
	}
	
	
	/**
	 * 运维情况正常上报
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "normalRecord", method = RequestMethod.POST)
	@LogAspect(desc = "运维情况上报")
	public String doNormalModify(WorkorderInfo workorder,ExceptionInfo exception, String menuid, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String ids = workorder.getId();
		String[] id = ids.split(",");
		String exceptionid = UuidGenerateUtil.getUUID();
		exception.setId(exceptionid);
		exception.setReporttime(DateUtil.stringFormatToDate(exception.getReporttimeStr(), "yyyy-MM-dd HH:mm:ss"));
		exceptionInfoService.save(exception);
		for (String string : id) {
			workorder = workorderInfoService.getById(string);
			workorder.setExceptionid(exceptionid);
			workorder.setStatus(WorkorderStatus.COMPLETE.getValue());
			workorderInfoService.updateByIdSelective(workorder);
			//添加日志信息
			WorkorderLog log = new WorkorderLog();
			log.setId(UuidGenerateUtil.getUUID());
			log.setOperator(getCurrentUserId());
			log.setAccount(getCurrentUser().getAccount());
			log.setOpertime(new Date());
			log.setOpercontent("正常上报");
			log.setWorkstatus(WorkorderStatus.COMPLETE.getValue());
			log.setWorkid(string);
			log.setReason(exception.getContent());
			workorderLogService.save(log);
		}
		
		redirectAttributes.addFlashAttribute("message", "正常上报成功");
		return "redirect:/alarm/workreport/list/" + menuid + "/";
	}
	
	
	@RequestMapping(value = "showexperience/{id}/{menuid}/", method = RequestMethod.GET)
	public String shoexperience(@PathVariable("id") String id, @PathVariable("menuid") String menuid, 
		String page, Model model,RedirectAttributes redirectAttributes) {
		WorkorderInfo workorder = workorderInfoService.getById(id);
		publicMethodService.setStatusColor(workorder);
		model.addAttribute("workorder", workorder);
		model.addAttribute("catalogList", catalogInfoService.findAll());
		return "alarm/workorder/report/experience";
	}
	
	/**
	 * 生成经验
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doExperience", method = RequestMethod.POST)
	@LogAspect(desc = "生成经验")
	public String doExperience(WorkorderInfo workorder,KnowledgeInfo knowledge, String menuid, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String exceptionid = UuidGenerateUtil.getUUID();
		knowledge.setId(exceptionid);
//		knowledge.setKnowledgeType("003");
		knowledge.setOperator(getCurrentUserId());
		knowledge.setCreatetime(new Date());
		knowledge.setApplyflag("1");
		knowledge.setApplytime(new Date());
		knowledge.setNote(workorder.getId());
		knowledgeInfoService.save(knowledge);
		redirectAttributes.addFlashAttribute("message", "生成经验成功");
		return "redirect:/alarm/workschedule/list/" + menuid + "/";
	}

}
