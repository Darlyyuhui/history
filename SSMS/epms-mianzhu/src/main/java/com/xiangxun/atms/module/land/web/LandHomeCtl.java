package com.xiangxun.atms.module.land.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.module.land.service.LandHomeService;

@Controller
@RequestMapping(value = "land/home")
public class LandHomeCtl extends BaseCtl {

	@Resource
	LandHomeService landHomeService;
	
	@RequestMapping(value = "index/")
	public String index(HttpServletRequest request, Model model) {
		model.addAttribute("topMap", landHomeService.getTopCount());
		
		model.addAttribute("newLand", landHomeService.getNewRegLandInfo(3));
		model.addAttribute("sampling", landHomeService.getSamplingPlanInfo(3));
		
		model.addAttribute("lineChart", landHomeService.getReggingCountCharts());
		model.addAttribute("pieChart", landHomeService.getLandTypeAreaCountCharts());
		
		model.addAttribute("repairList", landHomeService.getLandRepair(3));
		return "home/land_main";
	}
}
