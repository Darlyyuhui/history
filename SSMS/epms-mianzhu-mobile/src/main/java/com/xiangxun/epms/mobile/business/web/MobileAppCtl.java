package com.xiangxun.epms.mobile.business.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiangxun.epms.mobile.business.domain.MobileApp;
import com.xiangxun.epms.mobile.business.service.MobileAppService;

@Controller
@RequestMapping(value = "samply/server/mobile/app")
public class MobileAppCtl extends BaseCtl {
	@Resource
	MobileAppService mobileAppService;

	@RequestMapping(value = "getNewVersion")
	public void getNewVersion(String version, HttpServletRequest request, HttpServletResponse response) {
		try {
			MobileApp info = mobileAppService.getNewVersion();
			if (info != null && info.getVersion() != null) {
				if (version == null) {
					super.dataResult("1000", "有最新版本", info, request, response);
				} else {
					if (info.getVersion().compareTo(version) <= 0) {
						super.dataResult("1001", "没有更新", info, request, response);
					} else {
						super.dataResult("1000", "有最新版本", info, request, response);
					}

				}
			} else {
				super.dataResult("1001", "没有更新", info, request, response);
			}
		} catch (Exception e) {
			super.simpleResult("1001", "没有更新", request, response);
		}
	}

}
