package com.xiangxun.epms.mobile.business.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiangxun.epms.mobile.business.domain.LandType;
import com.xiangxun.epms.mobile.business.service.LandTypeService;

@Controller
@RequestMapping(value="samply/server/land/type")
public class LandTypeCtl extends BaseCtl {
	@Resource
	LandTypeService landTypeService;
	@RequestMapping(value="queryAll")
	public void query(HttpServletRequest request,HttpServletResponse response){
		try{
			List<LandType> list = landTypeService.findAll();
			logger.info("LandType query success ");
			super.dataResult("1000", "查询成功", list, request, response);
		}catch(Exception e){
			logger.error("LandType query failed :" +e.getMessage());
			super.simpleResult("1001", "查询失败", request, response);
		}
	}

}
