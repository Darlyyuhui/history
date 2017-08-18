package com.xiangxun.ywpt.mobile.business.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiangxun.ywpt.mobile.business.domain.OperationsApp;
import com.xiangxun.ywpt.mobile.business.service.OperationsAppService;

@Controller
@RequestMapping(value = "server/operations/app")
public class OperationsAppCtl extends BaseCtl {

	@Resource
	OperationsAppService operationsAppService;
	
	@RequestMapping(value = "getNewVersion")
	public void getNewVersion(HttpServletRequest request, HttpServletResponse response) {

		OperationsApp info = new OperationsApp();
		info.setSaveUrl( "http://"+ request.getLocalAddr()+":"+request.getServerPort()+"/ywt20170705am0856-dev-1.0.apk");

		//OperationsApp info = operationsAppService.getNewVersion();
		super.dataResult("1", "返回成功", info, request, response);
	}
	
}
