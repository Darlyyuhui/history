package com.xiangxun.atms.module.statistics.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.module.statistics.service.RepairService;
import com.xiangxun.atms.module.statistics.vo.RepairInfo;

@Controller
@RequestMapping(value = "statistics/repair")
public class RepairCtl extends BaseCtl {

	@Resource
	RepairService repairService;
	
	@RequestMapping(value = "count/{menuid}/")
	public String count(@PathVariable String menuid, String regionId, String beginTime, String endTime, Model model, HttpServletRequest request) {
		List<RepairInfo> list = repairService.getList(regionId, beginTime, endTime);
		model.addAttribute("countList", list);
		model.addAttribute("menuid", menuid);
		model.addAttribute("regionId", regionId);
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("endTime", endTime);
		
		model.addAttribute("dicMsgs", repairService.getDicBySchedule());
		return "statistics/repair/count";
	}
	
}
