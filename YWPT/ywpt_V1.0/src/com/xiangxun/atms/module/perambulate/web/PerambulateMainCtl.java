package com.xiangxun.atms.module.perambulate.web;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.module.perambulate.domain.PerambulateInfo;
import com.xiangxun.atms.module.perambulate.service.PerambulateInfoService;


/**
 * 巡检模块首页
 * @author yangzhenyu
 *
 */
@Controller
@RequestMapping(value="alarm/perambulateMain")
public class PerambulateMainCtl extends BaseCtl{
	@Resource
	PerambulateInfoService perambulateInfoService;
	
	
	
	
	
	
	/***
	 * 跳转到首页
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="show/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "inserttime") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		logger.info("正在进行巡检信息列表查询。。。。。。");
		Map<String, Object>map=new HashMap<>();
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//获取当前查询时间
				String day = DateUtil.currentDate();
				//获取这一周的日期
				String[] starts = DateUtil.getThisWeekStartAndEndByDate(day);
				searchParams.put("beginDate", starts[0] + "00:00:00");
				searchParams.put("endDate",starts[1] + "23:59:59");
				searchParams.put("sortType",sortType);
				map.put("startday", starts[0]);
				map.put("endday", starts[1]);
		List getcountbydevicetype=perambulateInfoService.getCountByDeviceType(map);
		
		for (int i = getcountbydevicetype.size() - 1; i >= 0; i--) {
			Map valueMap = (Map) getcountbydevicetype.get(i);
			String type = valueMap.get("DEVICETYPE").toString();
			if(type.equals("server")){
				valueMap.put("TYPES", "服务器");
			}
			if(type.equals("device")){
				valueMap.put("TYPES", "卡口");
			}
			if(type.equals("cabinet")){
				valueMap.put("TYPES", "智能机柜");
			}
			if(type.equals("database")){
				valueMap.put("TYPES", "数据库");
			}
			if(type.equals("ftp")){
				valueMap.put("TYPES", "FTP");
			}
			if(type.equals("project")){
				valueMap.put("TYPES", "平台");
			}
		}
		List getcountbyuser=perambulateInfoService.getCountByUser(map);
		
		Page page = perambulateInfoService.getListByCondition(searchParams, pageNumber,8, sortType);
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("getcountbydevicetype",getcountbydevicetype);
		model.addAttribute("getcountbyuser",getcountbyuser);
		return "alarm/perambulate/main/show";
	}
}
