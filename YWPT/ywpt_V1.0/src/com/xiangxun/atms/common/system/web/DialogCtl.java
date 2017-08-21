/**
 * 
 */
package com.xiangxun.atms.common.system.web;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.road.service.RoadInfoService;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.module.property.service.DeviceInfoService;

/**
 * 模块对话框
 * @author YanTao
 * @version 2.0
 */
@Controller
@RequestMapping(value="system/dialog")
public class DialogCtl extends BaseCtl {
	
	@Resource
	DeviceInfoService deviceInfoService;
	
	@Resource
	RoadInfoService roadInfoService;

	@Resource
	DepartmentService departmentService;
	
	@RequestMapping(value="index/{menuid}/")
	public String index(@PathVariable String menuid,ModelMap model){
		return "system/dialog/index";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
