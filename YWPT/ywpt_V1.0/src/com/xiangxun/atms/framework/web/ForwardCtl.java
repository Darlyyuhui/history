  /* 
   * Copyright 2012  diablo stutio TECHNOLOGY CO., TLD.
   *
   */
package com.xiangxun.atms.framework.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xiangxun.atms.framework.security.OperatorDetails;
import com.xiangxun.atms.framework.security.SpringSecurityUtils;

/**
 * <h2>基础控制器</h2>
 * @author zhouhaijian
 * @version 1.0
 * @since 1.0
 */
@Controller
public class ForwardCtl {
	/***
	 * 页面跳转
	 * @return
	 */
	@RequestMapping(value="/forward/{url}/{module}/{page}/",method = RequestMethod.GET)
	public ModelAndView forward(@PathVariable String url,@PathVariable String module,@PathVariable String page,HttpServletRequest req){
		//获取菜单id
		String menuId = req.getParameter("menuid");
		OperatorDetails userInfo = SpringSecurityUtils.getCurrentUser();
		if(userInfo==null){
			throw new UsernameNotFoundException("用户不存在");
		}
		ModelAndView mv =  new ModelAndView(url+"/"+module+"/"+page);
		if(menuId!=null){
			mv.addObject("menuid",menuId);
			mv.addObject("page",req.getParameter("page"));
		}
		return mv;
	}
	
	@RequestMapping(value="/forward/{url}/{page}/",method = RequestMethod.GET)
	public ModelAndView forward(@PathVariable String url,@PathVariable String page,HttpServletRequest req){
		//获取菜单id
		String menuId = req.getParameter("menuid");
		OperatorDetails userInfo = SpringSecurityUtils.getCurrentUser();
		
		if(userInfo==null){
			throw new UsernameNotFoundException("用户不存在");
		}
		ModelAndView mv =  new ModelAndView(url+"/"+page);
		if(menuId!=null){
			mv.addObject("menuid",menuId);
		}
		return mv;
	}
	
	@RequestMapping(value="/forward/{url}/{module}/{page1}/{page2}/",method = RequestMethod.GET)
	public ModelAndView forward(@PathVariable String url,@PathVariable String module,@PathVariable String page1,@PathVariable String page2,HttpServletRequest req){
		//获取菜单id
		String menuId = req.getParameter("menuid");
		OperatorDetails userInfo = SpringSecurityUtils.getCurrentUser();
		if(userInfo==null){
			throw new UsernameNotFoundException("用户不存在");
		}
		ModelAndView mv =  new ModelAndView(url+"/"+module+"/"+page1+"/"+page2);
		if(menuId!=null){
			mv.addObject("menuid",menuId);
		}
		return mv;
	}
	
	/***
	 * 跳转到登陆页面
	 * @param url
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/forwardLogin/",method = RequestMethod.GET)
	public String forwardLogin(@PathVariable String url,@PathVariable String page,HttpServletRequest req){
		return "home/relogin";
	}
}
