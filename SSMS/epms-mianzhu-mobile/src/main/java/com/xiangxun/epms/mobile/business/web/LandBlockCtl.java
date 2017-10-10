package com.xiangxun.epms.mobile.business.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiangxun.epms.mobile.business.domain.LandBlock;
import com.xiangxun.epms.mobile.business.service.LandBlockService;

@Controller
@RequestMapping(value="samply/server/block")
public class LandBlockCtl extends BaseCtl {
	@Resource
	LandBlockService landBlockService;
	@RequestMapping("query")
	public void query(HttpServletRequest request, HttpServletResponse response){
		try{
			List<LandBlock> list=landBlockService.findAll();
			super.dataResult( "1000", "查询成功",list, request, response);
			logger.info("landBlock query success");
		}catch(Exception e){
			super.simpleResult("1001", "查询失败", request, response);
			logger.info("landBlock query failed");
		}
	}

}
