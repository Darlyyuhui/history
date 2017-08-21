package com.xiangxun.atms.common.system.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.google.gson.JsonObject;
import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.deptment.vo.Department;
import com.xiangxun.atms.common.log.service.OperateLogService;
import com.xiangxun.atms.common.log.service.SystemLogService;
import com.xiangxun.atms.common.log.vo.OperationLog;
import com.xiangxun.atms.common.log.vo.SystemLog;
import com.xiangxun.atms.common.system.service.ResourceService;
import com.xiangxun.atms.common.system.vo.SystemResource;
import com.xiangxun.atms.common.user.service.SkinService;
import com.xiangxun.atms.common.user.service.UserService;
import com.xiangxun.atms.common.user.type.MenuType;
import com.xiangxun.atms.common.user.vo.Skin;
import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.resource.MessageResources;
import com.xiangxun.atms.framework.security.OperatorDetails;
import com.xiangxun.atms.framework.security.SpringSecurityUtils;
import com.xiangxun.atms.framework.util.IpAddressUtil;
import com.xiangxun.atms.framework.util.StringDiyUtils;
import com.xiangxun.atms.framework.util.ZhongWenToPinYin;

import freemarker.template.Configuration;
import freemarker.template.Template;

/***
 * 框架主页处理
 * 
 * @author zhouhaij
 * @Apr 9, 2013 2:45:38 PM
 */
@Controller
public class HomeCtl extends BaseCtl {

	@Resource
	ResourceService resourceService;
	@Resource
	FreeMarkerConfigurer freeMarkerConfigurer;
	@Resource
	SystemLogService systemLogService;
	@Resource
	UserService userService;
	@Resource
	OperateLogService operateLogService;
	@Resource
	SkinService skinService;
	@Resource
	DepartmentService departmentService;
	/***
	 * 处理top
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "home/admin/", method = RequestMethod.GET)
	public String showMenu(HttpServletRequest request,ModelMap model) {
		OperatorDetails userInfo = SpringSecurityUtils.getCurrentUser();
		if (userInfo == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		model.addAttribute("userName", userInfo.getRealName());
		
		model.addAttribute("user", userInfo);
		SystemLog log = systemLogService.getLastLogInfo(userInfo);
		if(log!=null){
			String logintimeStr = DateUtil.formatDate(log.getOperationTime(), "yyyy-MM-dd HH:mm:ss");
			model.addAttribute("opratorLog", log);
			model.addAttribute("logintimeStr", logintimeStr);
		}
		List<String> userRole = userService.getRoleNameListByUser(getCurrentUserId());
		model.addAttribute("userRole", userRole.get(0));
		Department d = departmentService.getById(userInfo.getDeptId());
		model.addAttribute("departmentname", d.getName());
		
		//判断用户的IP是否符合规则
	    String ipstr = userInfo.getIpAddress();
	    String account = userInfo.getAccount();
	    //根据账号 获取对应的IP限制规则
	    //1.具体的IP地址控制 2.IP段的模糊控制,忽略的可以用*号代替 ***.***.***.***
	    User user = userService.findUserByLoginName(account);
	    String iprule = user.getIprule();
	    
	    if(StringDiyUtils.isNotEmpty(iprule)){
	    	System.out.println("---- 登陆人IP ----"+ipstr);
	    	String ip = IpAddressUtil.getIpAddress(request);
	    	String[] ruleArray = iprule.split("\\.");
	 	    String[] ipArray = ipstr.split("\\.");
	 	    for(int i=0;i<ruleArray.length;i++){
	 	    	String rulei = ruleArray[i];
	 	    	//不足三位补齐
	 	    	int lena = rulei.length();
	 	    	if(lena!=3){
	 	    		for(int j=0;j<(3-lena);j++){
	 	    			rulei = "0" + rulei;
	 	    		}
	 	    	}
	 	    	
	 	    	String ipi = ipArray[i];
	 	    	//不足三位补齐
	 	    	int lenb = ipi.length();
	 	    	if(lenb!=3){
	 	    		for(int j=0;j<(3-lenb);j++){
	 	    			ipi = "0" + ipi;
	 	    		}
	 	    	}
	 	    	if(rulei.equals(ipi)){
	 	    		continue;
	 	    	}else{
	 	    		String a = rulei.substring(0, 1);
	 	    		String b = rulei.substring(1, 2);
	 	    		String c = rulei.substring(2, 3);
	 	    		if(!"*".equals(a) && !a.equals(ipi.substring(0, 1))){
	 	    			return "redirect:/login?error=true";
	 	    		}
	 	    		if(!"*".equals(b) && !b.equals(ipi.substring(1, 2))){
	 	    			return "redirect:/login?error=true";
	 	    		}
	 	    		if(!"*".equals(c) && !c.equals(ipi.substring(2, 3))){
	 	    			return "redirect:/login?error=true";
	 	    		}
	 	    	}
	 	    }
	    }
	    
		String jumpUrl = "home/newindex/getsubmenu/";
		List<SystemResource> menus = resourceService.getMenusByUserId(userInfo.getId(), MenuType.MODULE);
		List<SystemResource> oneLevel = new ArrayList<SystemResource>();
		for (SystemResource menu : menus) {
			// 设置页面图片名称
			menu.setPicName(ZhongWenToPinYin.getPinYin(menu.getName()).trim().toLowerCase());
			// 寻找一级菜单
			if ("0".equals(menu.getParentid())) {
				menu.setContent(jumpUrl + menu.getId());
				String menuName = menu.getName().substring(0, 2);
				if(Pattern.matches("^[A-Z]+$", menu.getName().substring(0, 3))){
					menuName = menu.getName().substring(0,3);
				}
				if(menu.getName().contains("挖占")){
					menuName = "挖占";
				}
				menu.setName(menuName);
				//综合态势首页 特殊显示，不加入普通的一级菜单
				if("综合态势首页".equals(menu.getName())){
					model.addAttribute("isShowComplex", 1);
					continue;
				}
				oneLevel.add(menu);
			}
		}
		model.addAttribute("menus", oneLevel);
		//add by kouyunhao 2014-04-17 为当前用户设置默认皮肤。
		List<Skin> skinList = skinService.findByUser(userInfo.getId());
		if(skinList != null && skinList.size() != 0){
			model.addAttribute("skincssType", skinList.get(0).getSkin());
		}
		
		model.addAttribute("first", oneLevel.get(0));
		
		
		return "home/newIndex/index";
	}
	
	/***
	 * 基础模块 首页显示
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "home/welcome/", method = RequestMethod.GET)
	@SuppressWarnings("unchecked")
	public String desktop(ModelMap model) {
		OperatorDetails oper = getCurrentUser();
		SystemLog systemLog = systemLogService.getLastLogInfo(oper);
		List<String> roles = userService.getRoleNameListByUser(getCurrentUserId());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("operator", oper.getUsername());
		if(systemLog!=null){
			map.put("ip", systemLog.getIpAddress()==null?"":systemLog.getIpAddress());
		}
		Page page = operateLogService.getLogsByCondition(map, 0, 10, "id");
		List<OperationLog> resultList = page.getResult();
		model.addAttribute("operator",oper);
		model.addAttribute("operatorLogin", systemLog);
		model.addAttribute("operator_roles", roles);
		model.addAttribute("operation_list", resultList);
		
		return "home/welcome";
	}

	/***
	 * 处理右边主页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "home/getsubmenu/{menuid}", method = RequestMethod.GET)
	@ResponseBody
	public void getsubmenu(@PathVariable String menuid, ModelMap model, HttpServletResponse resp, HttpServletRequest request) {
		JsonObject object = new JsonObject();
		OperatorDetails userInfo = SpringSecurityUtils.getCurrentUser();
		if (userInfo == null) {
			object.addProperty("code","502");
			try {
				resp.getWriter().write(object.toString());
				return;
			} catch (IOException e) {
				logger.error(e);
				return;
			}
		}

		// 检测是否为发布模式
		boolean isRealse = isRealse(request.getSession().getServletContext());
		String resCode = null;
		if (isRealse) {
			MessageResources resource = MessageResources.getMessageInstance(null, null);
			// 获取资源管理菜单编码
			resCode = resource.getMessage("res.code");
		}

		// 获得子系统名称
		SystemResource sys = resourceService.getById(menuid);

		// 获得左边第一级模块
		List<SystemResource> resList = resourceService.getChildMenusByUserId(userInfo.getId(), menuid, MenuType.MODULE);
		this.processMenus(resList);
		// 获得左边第二级功能
		for (SystemResource menu : resList) {
			List<SystemResource> sonResourceList = resourceService.getChildMenusByUserId(userInfo.getId(), menu.getId(), MenuType.MODULE);
			this.processMenus(sonResourceList);
			if (sonResourceList != null && sonResourceList.size() > 0) {
				if (resCode != null) {
					for (int i = 0; i < sonResourceList.size(); i++) {
						if (resCode.toUpperCase().equals(sonResourceList.get(i).getCode().toUpperCase())) {
							sonResourceList.remove(i);
							continue;
						}
					}
				}
				menu.setSonResourceList(sonResourceList);
			} else {
				List<SystemResource> templist = new ArrayList<SystemResource>();
				menu.setSonResourceList(templist);
			}
		}

		String menuStr = null;
		Configuration config = freeMarkerConfigurer.getConfiguration();
		Template template;
		Map<String, List<SystemResource>> menuModel = new HashMap<String, List<SystemResource>>();
		try {
			// 利用freemarker进行字符处理得到菜单
			menuModel.put("menus", resList);
			template = config.getTemplate("/ftl/menu.ftl");
			menuStr = FreeMarkerTemplateUtils.processTemplateIntoString(template, menuModel);
			// 根据子系统名，生成子系统首页链接
			String aStr = "<a href='#' onclick=\"showSysIndex('" + sys.getContent() + "')\" >" + sys.getName() + "首页</a>";
			menuStr = menuStr.replaceAll("系统功能菜单", aStr);
			logger.debugLine();
			logger.debug(menuStr);
			logger.debugLine();
			object.addProperty("code","200");
			object.addProperty("content", menuStr);
			if (!StringUtils.isBlank(sys.getContent())) {
				object.addProperty("url", sys.getContent());
			}
			resp.getWriter().write(object.toString());
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private void processMenus(List<SystemResource> resList) {
		for (SystemResource menu : resList) {
			if (!StringUtils.isEmpty(menu.getContent())) {
				String url = StringUtils.removeEnd(menu.getContent(), "*");
				logger.debug("菜单url:{0}", url);
				if (url.indexOf("forward") == -1) {
					if(url.indexOf("http://") == -1){
						// 如果以/结束，说明其中有其他参数，在url末尾加上menuid
						if (url.endsWith("/")) {
							url += menu.getId() + "/";
						}
						// 否则直接加上menuid
						else {
							url += "/" + menu.getId() + "/";
						}
					}
				}
				menu.setContent(url);
			} else {
				menu.setContent("");
			}
			menu.setIcon(menu.getIcon() == null ? "" : menu.getIcon());
		}
	}

}
