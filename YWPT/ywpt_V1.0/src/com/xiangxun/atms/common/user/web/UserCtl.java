package com.xiangxun.atms.common.user.web;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.deptment.vo.Department;
import com.xiangxun.atms.common.system.service.ParamsService;
import com.xiangxun.atms.common.system.service.ResourceService;
import com.xiangxun.atms.common.system.vo.SystemResource;
import com.xiangxun.atms.common.user.service.RoleService;
import com.xiangxun.atms.common.user.service.SkinService;
import com.xiangxun.atms.common.user.service.ThemeService;
import com.xiangxun.atms.common.user.service.UserAuthorityService;
import com.xiangxun.atms.common.user.service.UserRoleService;
import com.xiangxun.atms.common.user.service.UserService;
import com.xiangxun.atms.common.user.vo.GrantUserVo;
import com.xiangxun.atms.common.user.vo.Modules;
import com.xiangxun.atms.common.user.vo.Role;
import com.xiangxun.atms.common.user.vo.Skin;
import com.xiangxun.atms.common.user.vo.Theme;
import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.common.user.vo.UserAuthority;
import com.xiangxun.atms.common.user.vo.UserRole;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.constant.Constant;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.resource.MessageResources;
import com.xiangxun.atms.framework.security.OperatorDetails;
import com.xiangxun.atms.framework.security.SpringSecurityUtils;
import com.xiangxun.atms.framework.util.ListUtils;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.StringDiyUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;


/***
 * 用户管理
 * @author zhouhaij
 * @Apr 16, 2013 5:39:59 PM
 */
@Controller
@RequestMapping(value="system/user")
public class UserCtl extends BaseCtl{

	@Resource
	UserService userService;
	
	@Resource
	RoleService roleService;
	
	@Resource
	UserRoleService userRoleService;
	
	@Resource
	ResourceService resourceService;
	
	@Resource 
	UserAuthorityService userAuthorityService;
	
	@Resource
	ParamsService paramsService;
	
	@Resource
	DepartmentService departmentService;
	
	@Resource 
	SkinService skinService;
	
	@Resource
	ThemeService themeService;

	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		logger.info("正在进行用户列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page page = userService.getUsersByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);
		
		//发布模式隐藏超管的用户
		boolean isRealse = isRealse(request.getSession().getServletContext());
		if (isRealse) {
			@SuppressWarnings("unchecked")
			List<User> list = page.getResult();
			// 获取资源管理器
			MessageResources resource = MessageResources.getMessageInstance(null, null);
			// 获取超级管理员的编码
			String superRole = resource.getMessage("super.user.code");
			for (Iterator<User> iterator = list.iterator(); iterator.hasNext();) {
				User user = iterator.next();
				if (user.getRoleIds().contains(superRole.toUpperCase())) {
					iterator.remove();
				}
			}
		}
		
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		User user = new User();
		try {
			//将查询的map转换成user对象
			BeanUtils.populate(user, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		OperatorDetails oper = SpringSecurityUtils.getCurrentUser();
		//存入当前登录用户
		model.addAttribute("oper", oper);
		model.addAttribute("user", user);
		
		
		return "system/user/list";
	}
	
	/***
	 * 跳转到iframe上一级页面 add by kouyunhao 2013-07-17
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="sublist/{menuid}/")
	public String iframe_list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		this.list(menuid,model,sortType,pageNumber,request);
		return "system/user/sublist";
	}
	
	/***
	 * 删除用户
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除用户信息")
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在进行用户数据删除。。。。。。");
			int record =userService.deleteUser(ids);
			if(record>0){
				entity.setResult("ok");
				//add by kouyunhao 2014-04-09 删除该用户皮肤记录。
				String[] id = ids.split(",");
				for (String string : id) {
					List<Skin> list = skinService.findByUser(string);
					if(list!=null && list.size()>0){
						skinService.deleteById(list.get(0).getId());
					}
				}
			}else{
				entity.setResult("error");
			}
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	
	/***
	 * 修改密码
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="changepasswd/{old}/{pwd}")
	@ResponseBody
	@LogAspect(desc="修改密码")
	public ResponseEntity changePasswd(@PathVariable String old,@PathVariable String pwd){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在进行用户密码修改。。。。。。");
			
			OperatorDetails operator = getCurrentUser();
			if(operator==null){
				entity.setResult("502");
		    	entity.setMessage("登陆已超时，请重新登陆");
		    	return entity;
			}
			//对密码进行md5加密
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		    String oldpwd = encoder.encodePassword(old,operator.getRealName()/*加入盐值**/);
		    User u = userService.findUserByLoginName(operator.getAccount());
		    if(!oldpwd.equals(u.getPwd())){
		    	entity.setResult("error");
		    	entity.setMessage("原密码不正确！");
		    	return entity;
		    }else{

			    User user = userService.getById(operator.getId());
			    String newPwd = encoder.encodePassword(pwd,user.getName()/*加入盐值**/);
				user.setPwd(newPwd);
				userService.updateByIdSelective(user);
				
				entity.setResult("ok");
				
				return entity;
		    }
			
		} catch (Exception e) {
			entity.setResult("error");
			entity.setMessage("密码修改失败");
			return entity;
		}
	}
	
	/***
	 * 重置密码
	 * @param id
	 * @return
	 */
	@RequestMapping(value="resetPassword/{id}")
	@ResponseBody
	@LogAspect(desc="用户重置密码")
	public ResponseEntity resetPassword(@PathVariable String id){
		ResponseEntity  entity = new ResponseEntity();
		try {
			User user = userService.getById(id);
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		    String newPwd = encoder.encodePassword(user.getAccount(),user.getName()/*加入盐值**/);
			user.setPwd(newPwd);
			userService.updateByIdSelective(user);
			entity.setResult("ok");
			return entity;
		}catch(Exception e){
			entity.setResult("error");
			entity.setMessage("密码修改失败");
			return entity;
		}
	}
	
	
	/***
	 * 判断编码是否重复
	 * @param req
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "accountExist")
	@ResponseBody
	public String accountExist(HttpServletRequest req, @RequestParam(value = "account") String account) {
		User user = userService.findUserByLoginName(account);
		String returnStr = Boolean.FALSE.toString();
		if (user == null) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		//不为空说明是修改
		if(StringDiyUtils.isNotEmpty(oper)){
			if(account.equals(oper)){
				return Boolean.TRUE.toString();
			}
		}
		return returnStr;
	}
	
	/***
	 * 添加用户
	 * @param user
	 * @param menuid
	 * @param redirectAttributesr
	 * @return
	 */
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	@LogAspect(desc="添加新用户")
	public String doAdd(User user,String menuid, RedirectAttributes redirectAttributes){
		logger.info("menuid:"+menuid);
		if(user.getDisabled()==null){
			user.setDisabled(false);
		}
		user.setId(UuidGenerateUtil.getUUIDLong());
		
		//对密码进行md5加密
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
	    String pwd = encoder.encodePassword(user.getPwd(),user.getName()/*加入盐值**/);
		user.setPwd(pwd);
		//对IP控制规则进行赋值
		if(StringDiyUtils.isNotEmpty(user.getIprule1()) && StringDiyUtils.isNotEmpty(user.getIprule2()) && StringDiyUtils.isNotEmpty(user.getIprule3()) && StringDiyUtils.isNotEmpty(user.getIprule4())){
			String iprule = user.getIprule1() +"."+ user.getIprule2() +"."+ user.getIprule3() +"."+ user.getIprule4();
			user.setIprule(iprule);
		}
		userService.save(user);
		redirectAttributes.addFlashAttribute("message","用户添加成功");
		//add by kouyunhao 2014-04-09 为新增用户添加默认皮肤。
		Skin skin = new Skin();
		skin.setId(UuidGenerateUtil.getUUIDLong());
		skin.setUserId(user.getId());
		skin.setSkin("1");
		skinService.save(skin);
		
		//add by kouyunhao 2014-05-13 为新增用户添加默认皮肤。
		Theme theme = new  Theme();
		theme.setId(UuidGenerateUtil.getUUIDLong());
		theme.setUserId(user.getId());
		theme.setTheme("1");
		List<Theme> themeList = themeService.findByUser(user.getId());
		if(themeList == null || themeList.size() == 0){
			themeService.save(theme);
		}
		return "redirect:/system/user/list/"+menuid+"/";
	}

	/***
	 * 获取待修改用户信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		//将IP规则分散到输入框中
		User user = userService.getById(id);
		String iprule = user.getIprule();
		if(iprule!=null){
			String[] ipruleArray = iprule.split("\\.");
			user.setIprule1(ipruleArray[0]);
			user.setIprule2(ipruleArray[1]);
			user.setIprule3(ipruleArray[2]);
			user.setIprule4(ipruleArray[3]);
		}
		model.addAttribute("user",user);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "system/user/update";
	}
	
	/***
	 * 显示详情
	 * @param id
	 * @param menuid
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "showview/{id}/{menuid}/", method = RequestMethod.GET)
	public String showview(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		List<String> roles = userService.getRoleNameListByUser(id);
		model.addAttribute("roles",ListUtils.toString(roles, ","));
		model.addAttribute("user",userService.getById(id));
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "system/user/showview";
	}

	/***
	 * 修改用户
	 * @param user
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc="修改用户")
	public String doUpdate(@ModelAttribute("preloadUser") User user,String page,String menuid,RedirectAttributes redirectAttributes) {
		//对IP控制规则进行赋值
		if(StringDiyUtils.isNotEmpty(user.getIprule1()) && StringDiyUtils.isNotEmpty(user.getIprule2()) && StringDiyUtils.isNotEmpty(user.getIprule3()) && StringDiyUtils.isNotEmpty(user.getIprule4())){
			String iprule = user.getIprule1() +"."+ user.getIprule2() +"."+ user.getIprule3() +"."+ user.getIprule4();
			user.setIprule(iprule);
		}else{
			user.setIprule("***.***.***.***");
		}
		userService.updateByIdSelective(user);
		redirectAttributes.addFlashAttribute("message", "修改成功");
		
		//add by kouyunhao 2014-04-09 为新增用户添加默认皮肤。（为已添加的用户预留接口）
		Skin skin = new Skin();
		skin.setId(UuidGenerateUtil.getUUIDLong());
		skin.setUserId(user.getId());
		skin.setSkin("1");
		List<Skin> skinList = skinService.findByUser(user.getId());
		if(skinList == null || skinList.size() == 0){
			skinService.save(skin);
		}
		
		//add by kouyunhao 2014-05-13 为新增用户添加默认主题。（为已添加的用户预留接口）
		Theme theme = new  Theme();
		theme.setId(UuidGenerateUtil.getUUIDLong());
		theme.setUserId(user.getId());
		theme.setTheme("1");
		List<Theme> themeList = themeService.findByUser(user.getId());
		if(themeList == null || themeList.size() == 0){
			themeService.save(theme);
		}
		return "redirect:/system/user/list/"+menuid+"/?page="+page;
	}
	
	@RequestMapping(value="grantrole/{menuid}/{userids}",method=RequestMethod.GET)
	public String grantRole(@PathVariable("userids") String id,@PathVariable("menuid") String menuid,String page,Model model,HttpServletRequest request){
		List<Role> roles = roleService.findAll();
		
		//判断是否为发布模式
		boolean isRealse = isRealse(request.getSession().getServletContext());
		if(isRealse){
			//获取资源管理器
			MessageResources resource = MessageResources.getMessageInstance(null,null);
			//获取超级管理员的编码
			String superRole = resource.getMessage("super.user.code");
			for (int i =0; i < roles.size();i++) {
				//隐藏超级管理员的角色
				if(superRole.toUpperCase().equals(roles.get(i).getCode().toUpperCase())){
					roles.remove(i);
				}
			}
		}
		
		String[] userids = id.split(",");
		StringBuilder sb = new StringBuilder();
		for (String string : userids) {
			User user = userService.getById(string);
			sb.append(user.getAccount()+"("+user.getName()+"),");
		}
		
		model.addAttribute("accounts",sb.toString());
		model.addAttribute("userids",id);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		model.addAttribute("roleList",roles);
		
		return "system/user/grant_role";
	}
	
	/***
	 * 数据授权
	 * @param id
	 * @param menuid
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="grantAuthority/{menuid}/{userids}",method=RequestMethod.GET)
	public String grantAuthority(@PathVariable("userids") String id,@PathVariable("menuid") String menuid,String page,Model model){
		String[] userids = id.split(",");
		List<User> users = new ArrayList<User>();
		for (String string : userids) {
			User user = userService.getById(string);
			users.add(user);
		}
		//获取资源管理器
		MessageResources resource = MessageResources.getMessageInstance(null,null);
		String[] menuCodes = resource.getMessage("sys.module.code").split(",");
		String[] menus = resource.getMessage("sys.module").split(",");
		
		String[] params = new String[menuCodes.length];
		for (int i = 0;i < Constant.SYSTEM_MODULE_CODE.length;i++) {
			params[i] = ParamsService.SYSTEM_PARAMS.get(Constant.SYSTEM_MODULE_CODE[i]);
		}
	
		Modules[] keys = new Modules[menus.length];
		for (int i = 0;i < menus.length;i++) {
			String sysexinclude = resource.getMessage("sys.module.exinclude");
			String exinclude = resource.getMessage("sys.module."+menuCodes[i]+".exinclude");
			if(StringUtils.isNotBlank(exinclude)){
				sysexinclude = sysexinclude+","+exinclude;
			}
			List<SystemResource> systemResource = resourceService.findAllMenuByKeyWord(menus[i],sysexinclude);
			JsonArray jsonArray = getJsonArray(systemResource,userids);
			if(userids.length==1){
				//获取该用户的所有权限记录
				List<UserAuthority> authoritys = userAuthorityService.getDeptIdByUserId(userids[0]);
				buildGrantDepart(model,"grant_"+menuCodes[i],userids[0], systemResource,authoritys);
			}
			model.addAttribute(menuCodes[i]+"_data", jsonArray.toString());
			keys[i] = new Modules(menuCodes[i],menus[i],params[i]);
		}
		model.addAttribute("modules",keys);
		model.addAttribute("users",users);
		model.addAttribute("userids",id);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "system/user/grant_data";
	}

	
	@RequestMapping(value="doGrantAuthority",method=RequestMethod.POST)
	@LogAspect(desc="用户数据授权")
	public String doGrantAuthority(@RequestParam("userids") String id,@RequestParam("menuid") String menuid,@RequestParam("page") String page,GrantUserVo grantVo,Model model,RedirectAttributes redirectAttributes){
		 logger.debug("正在进行用户数据授权处理......");
		 List<UserAuthority> authorityes = new ArrayList<UserAuthority>();
		 
		 String[] crossMenus = grantVo.getCrossMenus().split(",");
		 String[] grantCrosses = grantVo.getGrantCross().split(",");
		 
		 String[] llmenus = grantVo.getLlMenus().split(",");
		 String[] grantll = grantVo.getGrantLl().split(",");
		 
		 String[] viomenus = grantVo.getVioMenus().split(",");
		 String[] grantVio = grantVo.getGrantVio().split(",");
		 
		 String[] videoMenu = grantVo.getVideoMenus().split(",");
		 String[] grantVideo = grantVo.getGrantVideo().split(",");
		 
		 
		 String[] gpsMenu = null;
		 if(grantVo.getGpsMenus() != null){
			 gpsMenu = grantVo.getGpsMenus().split(",");
		 }
		 
		 String[] grantGps = null;
		 if(grantVo.getGrantGps() != null){
			 grantGps = grantVo.getGrantGps().split(",");
		 }
		 
		 String[] blackMenu = null;
		 if(StringDiyUtils.isNotEmpty(grantVo.getBlackMenus())){
			 blackMenu = grantVo.getBlackMenus().split(",");
		 }
		 String[] grantBlack = null;
		 if(StringDiyUtils.isNotEmpty(grantVo.getGrantBlack())){
			 grantBlack = grantVo.getGrantBlack().split(",");
		 }
		 
		String[] userids = id.split(",");
		
		for (String string : userids) {
			//卡口授权
			buildAuthorities(authorityes,crossMenus,grantCrosses,string);
			//违法授权
			buildAuthorities(authorityes,viomenus,grantVio,string);
			//监控授权
			buildAuthorities(authorityes,videoMenu,grantVideo,string);
			//流量授权
			buildAuthorities(authorityes,llmenus,grantll,string);
			//GPS授权
			buildAuthorities(authorityes,gpsMenu,grantGps,string);
			//布控
			buildAuthorities(authorityes,blackMenu,grantBlack,string);
			//保存数据
			userAuthorityService.save(string, authorityes);
		}
		
		redirectAttributes.addFlashAttribute("message", "授权成功");
		return "redirect:/system/user/list/"+menuid+"/?page="+page;
	}
	
	
	public void buildAuthorities(List<UserAuthority> authorityes,String[] menus,String[] grants,String userid){
		if(menus == null || menus.length==0) return;
		if(grants == null || grants.length==0) return;
		UserAuthority userAuthority = null;
		for (String menu : menus) {
			//无须加入是否是叶节点的判断，暂时屏蔽
			//if(!resourceService.hasChild(menu,MenuType.MODULE)){
				for (String grant : grants) {
					if(StringDiyUtils.isEmpty(grant) || StringDiyUtils.isEmpty(menu)){
						continue;
					}
					userAuthority = new UserAuthority(userid,menu,grant);
					authorityes.add(userAuthority);
				}
			//}
		}
		
	}
	
	
	/***
	 * 角色授权
	 * @param userids
	 * @param roles
	 * @param menuid
	 * @param page
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="doGrantRoles/{userids}/{roles}/")
	@LogAspect(desc="用户角色授权")
	public String doGrantRoles(@PathVariable("userids") String userids,@PathVariable("roles") String roles,String menuid,String page,RedirectAttributes redirectAttributes){
		String[] ids = userids.split(",");
		UserRole[] userRoles = null;
		for (String userid : ids) {
			String[] roleids = roles.split(",");
			userRoles = new UserRole[roleids.length];
			for (int i = 0; i < roleids.length; i++) {
				userRoles[i] =  new UserRole(userid,roleids[i]);
			}
			userRoleService.save(userRoles);
		}
		redirectAttributes.addFlashAttribute("message", "授权成功");
		return "redirect:/system/user/list/"+menuid+"/?page="+page;
	}
	
	/***
	 * 先根据form的id从数据库查出User对象，再把Form提交的内容绑定到该对象上
	 * @param id
	 * @return
	 */
	@ModelAttribute("preloadUser")
	public User getUser(@RequestParam(value = "id", required = false) String id) {
		if (id != null) {
			return userService.getById(id);
		}
		return null;
	}
	
	
	/**
	 * 组织页面需要的树结构json字符
	 * @param res
	 * @return
	 */
	private JsonArray getJsonArray(List<SystemResource> res,String[] userids) {
		JsonArray jsonArray = new JsonArray();
		if(res ==null) return jsonArray;
		List<String> menus = null;
		if(userids.length==1){
			menus = userAuthorityService.getMenuIdsByUserId(userids[0]);
		}
		for (SystemResource resource : res) {
			JsonObject json = new JsonObject();
			json.addProperty("id",resource.getId());
			json.addProperty("pId",resource.getParentid());
			json.addProperty("name",resource.getName());
			//如果是多个用户的话，全部勾选
			if(userids.length>1){
				json.addProperty("checked","true");
			}
			//否则进行对应的数据判断,当该用户有对应的菜单id勾选，否则不勾选
			else{
				if(menus!=null){
					if((menus.isEmpty()) ||menus.contains(resource.getId())){
						json.addProperty("checked","true");
					}
					else{
						json.addProperty("checked","false");
					}
				}
			}
			json.addProperty("code",resource.getModuleCode());
			jsonArray.add(json);
		}
		return jsonArray;
	}
	
	
	/**
	 * 获取用户拥有权限的部门
	 * @param res
	 * @return
	 */
	private JsonArray getDepartArray(Collection<Department> departs) {
		JsonArray jsonArray = new JsonArray();
		if(departs ==null) return jsonArray;
		for (Department department : departs) {
			if(department==null){
				continue;
			}
			JsonObject json = new JsonObject();
			json.addProperty("id",department.getId());
			json.addProperty("pId",department.getParentid());
			json.addProperty("name",department.getName());
			jsonArray.add(json);
		}
		return jsonArray;
	}
	
	
	/**
	 * @param model
	 * @param userids
	 * @param crossResources
	 */
	private void buildGrantDepart(Model model, String modelKey,String userid, List<SystemResource> crossResources,List<UserAuthority> authoritys) {
		Set<Department> departments = new HashSet<Department>();
		for (SystemResource menu : crossResources) {
			//从权限记录里找到该菜单
			for (UserAuthority userAuthority : authoritys) {
				if(userAuthority.getMenuid().equals(menu.getId())){
					String deptId = userAuthority.getDeptid();
					Department depart = departmentService.getById(deptId);
					if(depart!=null){
						departments.add(depart);
					}
				}
			}
		}
		model.addAttribute(modelKey,getDepartArray(departments).toString());
	}
}
