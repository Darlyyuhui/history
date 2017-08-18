package com.xiangxun.epms.mobile.business.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiangxun.epms.mobile.business.domain.ApbInfo;
import com.xiangxun.epms.mobile.business.service.ApbInfoService;

@Controller
@RequestMapping("server/apb/info")
public class ApbInfoCtl extends BaseCtl {
	@Resource
	ApbInfoService apbInfoService;
	@RequestMapping(value="queryAll")
	public void queryAll(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		try{
			List<ApbInfo> list=apbInfoService.findAll();
			logger.info("ApbInfo query success ");
			super.dataResult("1000", "查询成功", list, request, response);
		}catch(Exception e){
			super.simpleResult("1001", "查询失败", request, response);
			logger.error("ApbInfo query failed" +e.getMessage());
		}
	}
}
