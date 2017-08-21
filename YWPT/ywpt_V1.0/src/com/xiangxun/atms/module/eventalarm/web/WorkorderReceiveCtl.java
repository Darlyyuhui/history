package com.xiangxun.atms.module.eventalarm.web;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderLog;
import com.xiangxun.atms.module.eventalarm.service.PublicMethodService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderLogService;
import com.xiangxun.atms.module.eventalarm.status.WorkorderStatus;

/**
 * 工单接收
 * @author kouyunhao
 *
 */
@Controller
@RequestMapping(value="alarm/workreceive")
public class WorkorderReceiveCtl extends BaseCtl {
	
	@Resource
	WorkorderInfoService workorderInfoService;
	
	@Resource
	WorkorderLogService workorderLogService;
	
	@Resource
	PublicMethodService publicMethodService;
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		logger.info("正在进行工单接收信息列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
		SessionParameter sp = new SessionParameter();
		sp.updateSessionMap(request, menuid, searchParams);
		String[] statusarr = new String[]{WorkorderStatus.ASSIGNED.getValue(),WorkorderStatus.REASSIGNED.getValue()};
		StringBuilder sb = new StringBuilder();
		for(String st : statusarr){
			sb.append("'" + st + "',");
		}
		searchParams.put("sortType", "ASSIGNTIME desc");
		searchParams.put("statusin", StringUtils.removeEnd(sb.toString(), ","));
		
		//权限过滤（按照部门和角色）
		String userDept = getCurrentUser().getDeptId();
		Collection<GrantedAuthority> authoritys = getCurrentUser().getAuthorities();
		boolean hasAuthority = false;
		Iterator<GrantedAuthority> it = authoritys.iterator();
		while(it.hasNext()){
			GrantedAuthority authority = it.next();
			if(authority.equals("ADMINISTRATOR")){
				hasAuthority = true;
			}
		}
		if(userDept.equals("00") || hasAuthority){
		}else{
			searchParams.put("contact", getCurrentUserId());
		}
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
		
		return "alarm/workorder/receive/list";
	}
	
	/***
	 * 工单接收
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value = "receive", method = RequestMethod.POST)
	@LogAspect(desc = "工单接收")
	public String receive(WorkorderInfo workorder,String menuid, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String reason = workorder.getReason();
		String ids = workorder.getId();
		String[] id = ids.split(",");
		for (String string : id) {
			workorder = workorderInfoService.getById(string);
			workorder.setStatus(WorkorderStatus.RECEIVED.getValue());
			workorderInfoService.updateByIdSelective(workorder);
			//添加日志信息
			WorkorderLog log = new WorkorderLog();
			log.setId(UuidGenerateUtil.getUUID());
			log.setOperator(getCurrentUserId());
			log.setAccount(getCurrentUser().getAccount());
			log.setOpertime(new Date());
			log.setOpercontent("工单接收");
			log.setWorkstatus(WorkorderStatus.RECEIVED.getValue());
			log.setWorkid(string);
			log.setNote(reason);
			workorderLogService.save(log);
		}
		redirectAttributes.addFlashAttribute("message", "工单接收成功");
		return "redirect:/alarm/workreceive/list/" + menuid + "/";
	}
	
	/***
	 * 工单退回
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value = "refuse", method = RequestMethod.POST)
	@LogAspect(desc = "工单退回")
	public String refuse(WorkorderInfo workorder,String menuid, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String reason = workorder.getReason();
		String ids = workorder.getId();
		String[] id = ids.split(",");
		for (String string : id) {
			workorder = workorderInfoService.getById(string);
			workorder.setIsreassign("0");
			workorder.setStatus(WorkorderStatus.REFUSED.getValue());
			workorderInfoService.updateByIdSelective(workorder);
			//添加日志信息
			WorkorderLog log = new WorkorderLog();
			log.setId(UuidGenerateUtil.getUUID());
			log.setOperator(getCurrentUserId());
			log.setAccount(getCurrentUser().getAccount());
			log.setOpertime(new Date());
			log.setOpercontent("工单退回");
			log.setWorkstatus(WorkorderStatus.REFUSED.getValue());
			log.setWorkid(string);
			log.setNote(reason);
			workorderLogService.save(log);
		}
		redirectAttributes.addFlashAttribute("message", "工单退回成功");
		return "redirect:/alarm/workreceive/list/" + menuid + "/";
	}

}
