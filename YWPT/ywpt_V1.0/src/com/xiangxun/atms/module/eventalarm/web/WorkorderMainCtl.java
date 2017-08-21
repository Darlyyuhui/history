package com.xiangxun.atms.module.eventalarm.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.eventalarm.domain.AlarmLog;
import com.xiangxun.atms.module.eventalarm.domain.EventtypeInfo;
import com.xiangxun.atms.module.eventalarm.service.AlarmLogService;
import com.xiangxun.atms.module.eventalarm.service.EventtypeInfoService;
import com.xiangxun.atms.module.eventalarm.service.PublicMethodService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;

/**
 * 事件告警首页
 * @author kouyunhao
 *
 */
@Controller
@RequestMapping(value = "workorder/main")
public class WorkorderMainCtl extends BaseCtl {
	
	@Resource
	EventtypeInfoService eventtypeInfoService; 
	
	@Resource
	WorkorderInfoService workorderInfoService;
	
	@Resource
	PublicMethodService publicMethodService;
	
	@RequestMapping(value="show/{menuid}/")
	public String show(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "ALARM_TIME desc") String sortType,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,HttpServletRequest request){
		
		//查询出统计数据
		List workorderCountByType = workorderInfoService.getWorkorderCountByType();
		
		for (int i = workorderCountByType.size() - 1; i >= 0; i--) {
			Map valueMap = (Map) workorderCountByType.get(i);
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
		
		List workorderCountByStatus = workorderInfoService.getWorkorderCountByStatus();
		
		Map<String, Object> workParams = new HashMap<String, Object>();
		
		
		for (int i = workorderCountByStatus.size() - 1; i >= 0; i--) {
			Map valueMap = (Map) workorderCountByStatus.get(i);
			
			String status = valueMap.get("STATUS").toString();
			//private String statusname[] = {"已派发","已接收","已退回","已转派","已上报","遗留中","已关闭","已评估","已完成"};
			if(status.equals("0")){
				valueMap.put("TYPES", "已派发");
			}
			if(status.equals("1")){
				valueMap.put("TYPES", "已接收");
			}
			if(status.equals("2")){
				valueMap.put("TYPES", "已退回");
			}
			if(status.equals("3")){
				valueMap.put("TYPES", "已转派");
			}
			if(status.equals("4")){
				valueMap.put("TYPES", "已上报");
			}
			if(status.equals("5")){
				valueMap.put("TYPES", "遗留中");
			}
			if(status.equals("6")){
				valueMap.put("TYPES", "已关闭");
			}
			if(status.equals("7")){
				valueMap.put("TYPES", "已评估");
			}
			if(status.equals("8")){
				valueMap.put("TYPES", "已完成");
			}
		}
		
//		workParams.put("contact", getCurrentUserId().equals("130521090208040c87f4ab27fd194da7")?null:getCurrentUserId());
		//权限过滤（按照部门和角色）
				String userDept = getCurrentUser().getDeptId();
				Collection<GrantedAuthority> authoritys = getCurrentUser().getAuthorities();
				boolean hasAuthority = false;
				Iterator<GrantedAuthority> it = authoritys.iterator();
				while(it.hasNext()){
					GrantedAuthority authority = it.next();
					if(authority.equals("ADMINISTRATOR")){
						hasAuthority = true;
					}
				}
		if(userDept.equals("00") || hasAuthority){
			
		}else{
			workParams.put("contact", getCurrentUserId());
		}
		workParams.put("sortType", "ASSIGNTIME desc");
		Page workPage = workorderInfoService.getListByCondition(workParams,pageNumber,9,sortType);
		publicMethodService.setStatusColor(workPage);
		model.addAttribute("workList", workPage);
		model.addAttribute("sortType", sortType);
		model.addAttribute("page", pageNumber);
		model.addAttribute("menuid",menuid);
		model.addAttribute("workorderCountByType",workorderCountByType);
		model.addAttribute("workorderCountByStatus",workorderCountByStatus);
		return "alarm/workorder/show";
	}

}
