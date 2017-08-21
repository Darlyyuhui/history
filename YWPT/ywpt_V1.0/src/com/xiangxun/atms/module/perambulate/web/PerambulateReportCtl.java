package com.xiangxun.atms.module.perambulate.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.perambulate.service.PerambulateInfoService;
/**
 * 巡检报告
 * @author admin
 *
 */
@Controller
@RequestMapping(value="alarm/perambulateReport")
public class PerambulateReportCtl extends BaseCtl{
	@Resource
	PerambulateInfoService perambulateInfoService;
	
	
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "inserttime") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		String time=(String) searchParams.get("beginDate");
	
		model.addAttribute("time",time);
		List list=perambulateInfoService.getFileMes(time);
		model.addAttribute("Filelist",list);
		return "alarm/perambulate/export/list";
	}
}
