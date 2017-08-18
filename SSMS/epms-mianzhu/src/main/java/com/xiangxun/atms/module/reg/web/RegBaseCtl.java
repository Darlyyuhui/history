package com.xiangxun.atms.module.reg.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.land.service.LandMissionService;
import com.xiangxun.atms.module.land.vo.LandMission;

public abstract class RegBaseCtl<T, S> extends BaseCtl<T, S> {

	/**
	 * 加载可选择的采样任务
	 * @param sampleCode
	 * @param service
	 * @param model
	 */
	protected void initModel(String sampleCode, LandMissionService service, HttpServletRequest requet) {
		HttpSession session = requet.getSession();
		session.setAttribute("missions", service.queryRegSelect(sampleCode));
	}
	
	/**
	 * 采样登记查看时，查询采样任务信息
	 * @param missionId
	 * @param service
	 * @param cache
	 * @param model
	 */
	protected void viewModel(String missionId, LandMissionService service, Cache cache, Model model) {
		if (StringUtils.isNotEmpty(missionId)) {
    		LandMission mission = service.getMissionById(missionId);
    		if (mission != null) {
    			mission.setRegionName(super.getRegionNameById(mission.getRegionId(), true, cache));
    		}
    		model.addAttribute("mission", mission);
    	}
	}
	
}
