package com.xiangxun.atms.module.gplot.web;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.xiangxun.atms.module.gplot.domain.StatusApp;
import com.xiangxun.atms.module.gplot.service.GplotSearchService;

@Controller
@RequestMapping("gplot/main/")
public class GplotCtl extends BaseCtl{
	
	@Resource
	GplotSearchService gplotSearchService;
	
	@RequestMapping(value="show")
	public String show(){
		
		System.out.println("可以成功连接");
		return "gplot/main/show";
	}
	
	@RequestMapping(value="tuopu/{menuid}/")
	public String tuopu(@PathVariable String menuid){
		System.out.println("可以成功连接");
		return "gplot/main/tuopu";
	}
	
	
	@RequestMapping(value="twoShow/{menuid}/")
	public String twoShow(@PathVariable String menuid){	
		
		System.out.println("2次可以成功连接");
		return "gplot/main/show";
	}
	
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid){	
		
		System.out.println("拓扑图3可以成功连接");
		return "gplot/main/list";
	}
	
	/***
     * 根据条件获取记录
     * @param type
     * @return statusAppList
     */
	@RequestMapping(value="view/{menuid}/")
	public String view(@PathVariable String menuid,ModelMap model,@RequestParam String type,HttpServletRequest request){
		
		logger.info("正在根据分类查询设备。。。");		
		//将request带回来的参数放入map集合中
		Map<String, String> searchParams = new HashMap<String, String>();
		String paramNames =request.getParameter("type");	
		//System.out.println(paramNames);
		searchParams.put("type", paramNames);
		List<StatusApp> statusAppList = gplotSearchService.getTypeByCondition(searchParams);
		
		List<StatusApp> aloneList = new ArrayList<StatusApp>();
		for(int i = 0;i < statusAppList.size();i++){
			
			//aloneList.add(i, element)
			
			//System.out.println("第"+ i+1 + "个list集合" + statusAppList.get(i));
		}
		
		model.addAttribute("statusAppList", statusAppList);
		return "gplot/main/view";
	}


}
