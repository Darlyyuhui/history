package com.xiangxun.atms.common.system.web;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiangxun.atms.common.system.service.ParamsService;
import com.xiangxun.atms.framework.base.BaseCtl;
/***
 * 登陆页面
 * @author yantao
 * @2013-04-04
 */
@Controller
public class LoginCtl extends BaseCtl{

	
	@Resource
	ParamsService paramsService;
	
	/***
	 * 登陆页面显示 以及错误提示
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login",method = RequestMethod.GET)
	public String left(String error,ModelMap model,HttpServletRequest request){
		
		Map<String,String> maps = paramsService.getAllParams("1");
		model.addAllAttributes(maps);
		
		if("true".equals(error)){
			logger.info("用户登陆失败！");
		}
		//产品首页
        //return "home/login_five";
		//运维平台首页
		return "login";
	}
	
	
	
}
