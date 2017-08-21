package com.xiangxun.atms.common.log.web;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangxun.atms.common.log.service.OperateLogService;
import com.xiangxun.atms.common.log.service.SystemLogService;
import com.xiangxun.atms.common.log.vo.OperationLog;
import com.xiangxun.atms.common.log.vo.SystemLog;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.validator.ResponseEntity;

/**
 * <p>日志控制器</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
@Controller
@RequestMapping("system/log")
public class LogCtl extends BaseCtl{
	
	@Resource
	SystemLogService systemLogService;
	
	@Resource
	OperateLogService operateLogService;

	@RequestMapping(value="loginlist/{menuid}")
	public String loginlist(ModelMap model,@PathVariable String menuid, @RequestParam(value = "sortType", defaultValue = "id") String sortType, @RequestParam(value = "page", defaultValue = "0") int pageNumber,ServletRequest request){
		logger.info("正在进行登录日志查询");
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

		Page page = systemLogService.getSystemLogsByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE, sortType);

		SystemLog systemLog = new SystemLog();
		try {
			ConvertUtils.register(new DateConverter(),Date.class);
			// 将查询的map转换成对象
			BeanUtils.populate(systemLog, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("log", systemLog);
		return "system/log/login_list";
	}
	
	@RequestMapping(value="showloginview/{id}/{menuid}")
	public String showloginview(@PathVariable String id,@PathVariable String menuid,ModelMap model){
		SystemLog syslog = systemLogService.getById(id);
		model.addAttribute("menuid", menuid);
		model.addAttribute("syslog", syslog);
		return "system/log/login_view";
	}
	
	
	@RequestMapping(value="operlist/{menuid}")
	public String operlist(ModelMap model,@PathVariable String menuid, @RequestParam(value = "sortType", defaultValue = "id") String sortType, @RequestParam(value = "page", defaultValue = "0") int pageNumber,ServletRequest request){
		logger.info("正在进行操作日志查询");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		processOperList(model, menuid, sortType, pageNumber, searchParams);
		return "system/log/oper_list";
	}
	
	/**
	 * 违法确认统计
	 * @param id
	 * @param menuid
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="operstatis/{menuid}")
	public String operstatis(ModelMap model,@PathVariable String menuid, @RequestParam(value = "page", defaultValue = "0") int pageNumber,ServletRequest request){
		logger.info("正在进行违法确认操作日志统计查询");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		processOperStatis(model, menuid, pageNumber, searchParams);
		return "system/log/oper_statis";
	}
	
	@RequestMapping(value = "showOperview/{id}/{menuid}/", method = RequestMethod.GET)
	public String showOperview(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model){
		model.addAttribute("log",operateLogService.selectLogById(id));
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "system/log/showview_oper";
	}

	@RequestMapping("errorlist/{menuid}")
	public String errorList(ModelMap model,@PathVariable String menuid, @RequestParam(value = "sortType", defaultValue = "id") String sortType, @RequestParam(value = "page", defaultValue = "0") int pageNumber,ServletRequest request){
		logger.info("正在进行异常操作日志查询");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//添加异常日志的标志
		searchParams.put("isSuccess", "0");
		processOperList(model, menuid, sortType, pageNumber, searchParams);
		return "system/log/error_list";
	}
	
	@RequestMapping(value = "showview/{id}/{menuid}/", method = RequestMethod.GET)
	public String showview(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model){
		model.addAttribute("log",operateLogService.selectLogById(id));
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "system/log/showerror";
	}
	
	/***
	 * 删除日志
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在进行日志数据删除。。。。。。");
			String[] id = ids.split(",");
			for (String string : id) {
				operateLogService.deleteLogById(string);
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	/**
	 * @param model
	 * @param menuid
	 * @param sortType
	 * @param pageNumber
	 * @param searchParams
	 */
	private void processOperList(ModelMap model, String menuid, String sortType, int pageNumber, Map<String, Object> searchParams) {
		Page page = operateLogService.getLogsByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE, sortType);

		OperationLog operationLog = new OperationLog();
		try {
			ConvertUtils.register(new DateConverter(),Date.class);
			// 将查询的map转换成对象
			BeanUtils.populate(operationLog, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("log", operationLog);
	}
	
	/**
	 * 违法确认操作日志统计
	 * @param model
	 * @param menuid
	 * @param sortType
	 * @param pageNumber
	 * @param searchParams
	 */
	private void processOperStatis(ModelMap model, String menuid, int pageNumber, Map<String, Object> searchParams) {
		//获取按操作人员统计违法确认操作日志
		Page page = operateLogService.getLogsStatis(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE);

		OperationLog operationLog = new OperationLog();
		try {
			ConvertUtils.register(new DateConverter(),Date.class);
			// 将查询的map转换成对象
			BeanUtils.populate(operationLog, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("pageList", page);
		model.addAttribute("log", operationLog);
	}
}
