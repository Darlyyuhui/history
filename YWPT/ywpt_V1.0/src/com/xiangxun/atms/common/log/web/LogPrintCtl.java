package com.xiangxun.atms.common.log.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.xiangxun.atms.common.log.service.OperateLogService;
import com.xiangxun.atms.common.log.vo.OperationLog;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.Servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
/***
 * 操作日志打印处理
 * @author guikaiping
 */
@Controller
@RequestMapping("system/log/print")
public class LogPrintCtl extends BaseCtl{

	@Resource
	OperateLogService operateLogService;
	
	@Resource 
	FreeMarkerConfigurer freeMarkerConfigurer;
	
	
	//打印
	@RequestMapping(value = "doPrint/{menuid}/",method=RequestMethod.GET)
	@ResponseBody
	public void doPrint(@PathVariable String menuid, ModelMap model,@RequestParam(value = "sortType", defaultValue = "device_code")	String sortType, @RequestParam(value = "page", defaultValue = "0")	int pageNumber, ServletRequest request,HttpServletResponse resp){
		//按操作人员统计违法确认操作日志数的打印
		logPrint(menuid, model, pageNumber, request, resp);
	}
	
	/***
	 * 按操作人员统计违法确认操作日志数的打印
	 * @param menuid
	 * @param model
	 * @param pageNumber
	 * @param request
	 * @param resp
	 */
	@SuppressWarnings("unchecked")
	private void logPrint(@PathVariable String menuid, ModelMap model, @RequestParam(value = "page", defaultValue = "0")	int pageNumber, ServletRequest request,HttpServletResponse resp){
		String pageSize = request.getParameter("pageSize");
		int pages = 20;
		try{
			pages =Integer.parseInt(pageSize);
		}catch (NumberFormatException e) {
		}
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page page = operateLogService.getLogsStatis(searchParams, pageNumber,pages);
		
		List<OperationLog> operationLogs = page.getResult();
		
		//导出日期
		String secondTitle = "";
		if (null != searchParams && searchParams.size() != 0) {
			//开始日期
			String startTime = searchParams.get("startTime") == null?"":searchParams.get("startTime").toString();
			//结束日期
			String endTime = searchParams.get("endtime") == null?"":searchParams.get("endtime").toString();

			//修改统计日期的值
			if (startTime.equals("") && endTime.equals("")) {
				secondTitle = "统计单位：条";
			} else if (startTime.equals("") && !"".equals(endTime)) {
				secondTitle = "统计日期：开始 至 "+endTime+"     统计单位：条";
			} else if (!"".equals(startTime) && endTime.equals("")) {
				secondTitle = "统计日期："+startTime+" 至 当前     统计单位：条";
			} else if (!"".equals(startTime) && !"".equals(endTime)) {
				secondTitle = "统计日期："+startTime+" 至 "+endTime+"     统计单位：条";
			}
		} else {
			secondTitle = "统计单位：条";
		}
		
		model.addAttribute("curPageNo",pageNumber+1);
		model.addAttribute("pageList", operationLogs);
		model.addAttribute("secondTitle", secondTitle);
		model.addAttribute("totalPage", page.getTotalPageCount());
		Configuration config = freeMarkerConfigurer.getConfiguration();
		Template template;
		try {
			template = config.getTemplate("/jsp/system/log/print/log_print.ftl");
			String contents = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			resp.getWriter().write(contents);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	
	}

}
