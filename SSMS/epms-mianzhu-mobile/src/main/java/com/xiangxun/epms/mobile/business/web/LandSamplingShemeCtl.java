package com.xiangxun.epms.mobile.business.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiangxun.epms.mobile.business.domain.LandSamplingSheme;
import com.xiangxun.epms.mobile.business.service.LandSamplingShemeService;
import com.xiangxun.epms.mobile.business.util.Page;
import com.xiangxun.epms.mobile.business.util.RegionUtil;

@Controller
@RequestMapping(value = "samply/server/land/sheme")
public class LandSamplingShemeCtl extends BaseCtl {
	@Resource
	LandSamplingShemeService landSamplingShemeService;

	@RequestMapping(value = "queryByFinish")
	public void queryAll(HttpServletRequest request, HttpServletResponse response) {
		List<LandSamplingSheme> list = null;
		try {
			list = landSamplingShemeService.findAllByFinsh(super.getQueryParams(request, "resTime"));
			if (list != null && list.size() > 0) {
				Map<String, Object> args = null;
				list = landSamplingShemeService.findAllByFinsh(args);
				super.TimeResult("1000", "查询成功", list, request, response);
			} else {
				String resTime = request.getParameter("resTime");
				if (resTime == null) {
					super.TimeResult("1000", "查询成功", list, request, response);
				}
				super.TimeResult("2000", "数据没有更新", list, request, response);
			}
			logger.info("table T_Land_Sampling_Sheme list query success");
		} catch (Exception e) {
			super.TimeResult("1001", "查询失败", list, request, response);
			logger.error("table T_Land_Sampling_Sheme list query failed:" + e.getMessage());
		}
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public void query(HttpServletRequest request, HttpServletResponse response) {
		try {
			super.pageParams(request);
			String regionId = request.getParameter("regionId");
			if (regionId == null || "".equals(regionId)) {
				String regionName = request.getParameter("regionName");
				if (StringUtils.isEmpty(regionName)) {
					regionId = null;
				} else {
					regionId = RegionUtil.getRegionId(regionName);
				}
			}
			LandSamplingSheme landSamplingSheme =new LandSamplingSheme();
			landSamplingSheme.setRegionId(regionId);
			String missionName =request.getParameter("missionName");
			if(missionName!=null&&!"".equals(missionName)){
				landSamplingSheme.setMissionName(missionName.trim());
			}
			Page page = landSamplingShemeService.queryList(landSamplingSheme, pageSize, pageNo);

			super.pageResult("1000", "查询成功", regionId==null?"":regionId, page, request, response);
		} catch (Exception e) {
			super.simpleResult("1001", "查询失败", request, response);
		}
	}
}
