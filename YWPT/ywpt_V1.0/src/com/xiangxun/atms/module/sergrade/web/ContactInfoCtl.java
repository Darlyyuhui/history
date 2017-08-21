
package com.xiangxun.atms.module.sergrade.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
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
import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.deptment.vo.Department;
import com.xiangxun.atms.common.user.service.RoleService;
import com.xiangxun.atms.common.user.service.SkinService;
import com.xiangxun.atms.common.user.service.ThemeService;
import com.xiangxun.atms.common.user.service.UserRoleService;
import com.xiangxun.atms.common.user.service.UserService;
import com.xiangxun.atms.common.user.vo.RoleSearch;
import com.xiangxun.atms.common.user.vo.Skin;
import com.xiangxun.atms.common.user.vo.Theme;
import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.common.user.vo.UserRole;
import com.xiangxun.atms.common.user.vo.UserSearch;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.FileUtils;
import com.xiangxun.atms.framework.util.ListUtils;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.property.domain.CabInfo;
import com.xiangxun.atms.module.property.domain.DatabaseInfo;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.FtpInfo;
import com.xiangxun.atms.module.property.domain.ProjectInfo;
import com.xiangxun.atms.module.property.domain.ServerInfo;
import com.xiangxun.atms.module.property.domain.VideoInfo;
import com.xiangxun.atms.module.property.service.CabInfoService;
import com.xiangxun.atms.module.property.service.DatabaseInfoService;
import com.xiangxun.atms.module.property.service.DeviceInfoService;
import com.xiangxun.atms.module.property.service.FtpInfoService;
import com.xiangxun.atms.module.property.service.ProjectInfoService;
import com.xiangxun.atms.module.property.service.ServerInfoService;
import com.xiangxun.atms.module.property.service.VideoInfoService;
import com.xiangxun.atms.module.sergrade.domain.ContactInfo;
import com.xiangxun.atms.module.sergrade.domain.ContactInfoSearch;
import com.xiangxun.atms.module.sergrade.domain.FactoryContact;
import com.xiangxun.atms.module.sergrade.domain.FactoryContactSearch;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.service.ContactInfoService;
import com.xiangxun.atms.module.sergrade.service.FactoryContactService;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;

/**
 * 运维人员模块操作
 * @author guikaiping
 * @version 1.0
 */
@Controller
@RequestMapping(value="sergrade/contact/")
public class ContactInfoCtl extends BaseCtl {
	@Resource
	ContactInfoService contactInfoService;
	
	@Resource
	FactoryInfoService factoryInfoService;
	
	@Resource
	UserService userService;
	
	@Resource 
	SkinService skinService;
	
	@Resource
	ThemeService themeService;
	
	@Resource
	UserRoleService userRoleService;
	
	@Resource
	DeviceInfoService deviceInfoService;
	
	@Resource
	VideoInfoService videoInfoService;
	
	@Resource
	ServerInfoService serverInfoService;
	
	@Resource
	DatabaseInfoService databaseInfoService;
	
	@Resource
	FtpInfoService ftpInfoService;
	
	@Resource
	CabInfoService cabInfoService;
	
	@Resource
	ProjectInfoService projectInfoService;
	
	@Resource
	FactoryContactService factoryContactService;
	
	@Resource
	DepartmentService departmentService;
	
	@Resource
	RoleService roleService;
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		logger.info("正在进行运维人员列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//删除标志 0可用用户
		searchParams.put("hasdel", "0");
		Page page = contactInfoService.getListByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);
		
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		FactoryInfo factoryInfo = new FactoryInfo();
		try {
			//将查询的map转换成user对象
			BeanUtils.populate(factoryInfo, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		//存入
		model.addAttribute("factoryInfo", factoryInfo);
		
		JsonArray jsonArray = contactInfoService.getFactoryJsonArray(menuid);
		model.addAttribute("typeJsonArray", jsonArray.toString());
		
		
		return "sergrade/contact/list";
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
		return "sergrade/contact/sublist";
	}
	
	/***
	 * 同步树形结构服务
	 * 
	 * @param id
	 * @param resp
	 */
	@RequestMapping(value = "showTreeNode/{menuid}/", method = RequestMethod.GET)
	public void showTreeNode(@PathVariable String menuid, ServletResponse resp) {
		JsonArray jsonArray = contactInfoService.getFactoryJsonArray(menuid);
		logger.debug("response json:{0}", jsonArray.toString());
		try {
			resp.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 添加运维人员
	 * @param contactInfo
	 * @param menuid
	 * @param redirectAttributesr
	 * @return
	 */
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	@LogAspect(desc="添加新运维人员")
	public String doAdd(ContactInfo contactInfo,String menuid, RedirectAttributes redirectAttributes){
		logger.info("menuid:"+menuid);
		
		/**自动创建运维人员的登陆用户信息**/
		User user = new User();
		if(contactInfo.getDisabled() == null){
			user.setDisabled(false);
		}
		String userid = UuidGenerateUtil.getUUIDLong();
		user.setId(userid);
		//姓名
		user.setName(contactInfo.getUserName());
		//账号
		user.setAccount(contactInfo.getMobile());
		
		//对密码进行md5加密
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
	    String pwd = encoder.encodePassword("123456",contactInfo.getUserName()/*加入盐值**/);
		user.setPwd(pwd);
		//联系电话
		user.setMobile(contactInfo.getMobile());
		//获取运维部门名称
		String deptName = FileUtils.getKey("/com/xiangxun/atms/module/sergrade/config/sergrade-contact_zh_CN.properties","sergrade.contact_dept", this.getClass());
		Department dept = departmentService.getOneByName(deptName);
		//部门ID
		user.setDeptid(dept.getId());
		user.setMemo(contactInfo.getMemo());
		
		userService.save(user);
		redirectAttributes.addFlashAttribute("message","用户添加成功");
		//为新增用户添加默认皮肤。
		Skin skin = new Skin();
		skin.setId(UuidGenerateUtil.getUUIDLong());
		skin.setUserId(userid);
		skin.setSkin("1");
		skinService.save(skin);
		
		//为新增用户添加默认皮肤。
		Theme theme = new  Theme();
		theme.setId(UuidGenerateUtil.getUUIDLong());
		theme.setUserId(userid);
		theme.setTheme("2");
		List<Theme> themeList = themeService.findByUser(user.getId());
		if(themeList == null || themeList.size() == 0){
			themeService.save(theme);
		}
		/** 新增运维人员信息 **/
		contactInfo.setId(UuidGenerateUtil.getUUID());
		contactInfo.setUserid(userid);
		contactInfoService.save(contactInfo);
		
		/** 运维人员角色授权 **/
		//获取运维角色名称
		String roleName = FileUtils.getKey("/com/xiangxun/atms/module/sergrade/config/sergrade-contact_zh_CN.properties","sergrade.contact_role", this.getClass());
		RoleSearch roleSearch = new RoleSearch();
		roleSearch.createCriteria().andNameEqualTo(roleName);
		String roles = roleService.selectByExample(roleSearch).get(0).getId();
		UserRole[] userRoles = null;
		String[] roleids = roles.split(",");
		userRoles = new UserRole[roleids.length];
		for (int i = 0; i < roleids.length; i++) {
			userRoles[i] =  new UserRole(userid,roleids[i]);
		}
		userRoleService.save(userRoles);
		
		return "redirect:/sergrade/contact/list/"+menuid+"/";
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
		ContactInfo contactInfo = contactInfoService.getById(id);
		
		model.addAttribute("contactInfo", contactInfo);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "sergrade/contact/showview";
	}
	
	/***
	 * 获取待修改运维人员信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		ContactInfo contactInfo = contactInfoService.getById(id);
	
		model.addAttribute("contactInfo",contactInfo);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "sergrade/contact/update";
	}
	
	/***
	 * 修改运维人员
	 * @param user
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc="修改运维人员")
	public String doUpdate(@ModelAttribute("preloadContactInfo") ContactInfo contactInfo,String page,String menuid,RedirectAttributes redirectAttributes) {
		ContactInfoSearch contactInfoSearch = new ContactInfoSearch();
		contactInfoSearch.createCriteria().andIdEqualTo(contactInfo.getId());
		contactInfoService.updateByExampleSelective(contactInfo,contactInfoSearch);
		
		User user = new User();
		user.setId(contactInfo.getUserid());
		user.setName(contactInfo.getUserName());
		user.setAccount(contactInfo.getMobile());
		user.setMobile(contactInfo.getMobile());
		user.setMemo(contactInfo.getMemo());
		
		UserSearch userSearch = new UserSearch();
		userSearch.createCriteria().andIdEqualTo(contactInfo.getUserid());
		userService.updateByExampleSelective(user,userSearch);
		redirectAttributes.addFlashAttribute("message", "修改成功");
		
		//为新增用户添加默认皮肤。（为已添加的用户预留接口）
		Skin skin = new Skin();
		skin.setId(UuidGenerateUtil.getUUIDLong());
		skin.setUserId(user.getId());
		skin.setSkin("1");
		List<Skin> skinList = skinService.findByUser(contactInfo.getUserid());
		if(skinList == null || skinList.size() == 0){
			skinService.save(skin);
		}
		
		//为新增用户添加默认主题。（为已添加的用户预留接口）
		Theme theme = new  Theme();
		theme.setId(UuidGenerateUtil.getUUIDLong());
		theme.setUserId(user.getId());
		theme.setTheme("2");
		List<Theme> themeList = themeService.findByUser(contactInfo.getUserid());
		if(themeList == null || themeList.size() == 0){
			themeService.save(theme);
		}
		return "redirect:/sergrade/contact/list/"+menuid+"/?page="+page;
	}
	
	/***
	 * 删除运维人员
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除运维人员信息")
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在进行运维人员数据删除。。。。。。");
			String[] idss = ids.split(",");
			List<String> dels = Arrays.asList(idss);
			//删除相关用户信息
			for (int i = 0; i < idss.length; i++) {
				String userid = contactInfoService.getById(idss[i]).getUserid();
				int record =userService.deleteUser(userid);
				if(record>0){
					entity.setResult("ok");
					List<Skin> list = skinService.findByUser(userid);
					if(list!=null && list.size()>0){
						skinService.deleteById(list.get(0).getId());
					}
				}else{
					entity.setResult("error");
				}
			}
			//删除运维人员信息
			ContactInfoSearch contactInfoSearch = new ContactInfoSearch();
			contactInfoSearch.createCriteria().andIdIn(dels);
			ContactInfo contactInfo = new ContactInfo();
			contactInfo.setHasdel("1");
			contactInfoService.updateByExampleSelective(contactInfo, contactInfoSearch);
			
			//删除运维人员下分配的相关资产信息
			FactoryContactSearch factoryContactSearch = new FactoryContactSearch();
			factoryContactSearch.createCriteria().andContactidIn(dels);
			factoryContactService.deleteByExample(factoryContactSearch);
			
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	/***
	 * 获取待分配资产的运维信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "allot/{id}/{menuid}/", method = RequestMethod.GET)
	public String allotForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		ContactInfo contactInfo = contactInfoService.getById(id);
		//服务厂商ID
		String factoryId = contactInfo.getFactoryid();
		//运维人员ID
		String userId = contactInfo.getUserid();
		//获取服务商下所有卡口设备信息
		List<DeviceInfo> deviceList = deviceInfoService.getDevListByFactoryid(factoryId, menuid);
		for (DeviceInfo deviceInfo : deviceList) {
			Map<String, Object> map = new HashMap<String, Object>();
			//厂商ID
			map.put("factoryId", factoryId);
			//用户ID
			map.put("userId", userId);
			map.put("deviceid", deviceInfo.getId());
			int sum = factoryContactService.getReport(map).size();
			if (sum > 0) {
				deviceInfo.setHasAllot(true);
			}else {
				deviceInfo.setHasAllot(false);
			}
		}
		//获取服务商下所有监控设备信息
		List<VideoInfo> videoList = videoInfoService.getVideoListByFactoryid(factoryId, menuid);
		for (VideoInfo videoInfo : videoList) {
			Map<String, Object> map = new HashMap<String, Object>();
			//厂商ID
			map.put("factoryId", factoryId);
			//用户ID
			map.put("userId", userId);
			map.put("deviceid", videoInfo.getId());
			int sum = factoryContactService.getReport(map).size();
			if (sum > 0) {
				videoInfo.setHasAllot(true);
			}else {
				videoInfo.setHasAllot(false);
			}
		}
		//获取服务商下所有服务器设备信息
		List<ServerInfo> serverList = serverInfoService.getServerListByFactoryid(factoryId, menuid);
		for (ServerInfo serverInfo : serverList) {
			Map<String, Object> map = new HashMap<String, Object>();
			//厂商ID
			map.put("factoryId", factoryId);
			//用户ID
			map.put("userId", userId);
			map.put("deviceid", serverInfo.getId());
			int sum = factoryContactService.getReport(map).size();
			if (sum > 0) {
				serverInfo.setHasAllot(true);
			}else {
				serverInfo.setHasAllot(false);
			}
		}
		//获取服务商下所有数据库信息
		List<DatabaseInfo> databaseList = databaseInfoService.getDatabaseListByFactoryid(factoryId);
		for (DatabaseInfo databaseInfo : databaseList) {
			Map<String, Object> map = new HashMap<String, Object>();
			//厂商ID
			map.put("factoryId", factoryId);
			//用户ID
			map.put("userId", userId);
			map.put("deviceid", databaseInfo.getId());
			int sum = factoryContactService.getReport(map).size();
			if (sum > 0) {
				databaseInfo.setHasAllot(true);
			}else {
				databaseInfo.setHasAllot(false);
			}
		}
		//获取服务商下所有FTP信息
		List<FtpInfo> ftpList = ftpInfoService.getFtpListByFactoryid(factoryId);
		for (FtpInfo ftpInfo : ftpList) {
			Map<String, Object> map = new HashMap<String, Object>();
			//厂商ID
			map.put("factoryId", factoryId);
			//用户ID
			map.put("userId", userId);
			map.put("deviceid", ftpInfo.getId());
			int sum = factoryContactService.getReport(map).size();
			if (sum > 0) {
				ftpInfo.setHasAllot(true);
			}else {
				ftpInfo.setHasAllot(false);
			}
		}
		//获取服务商下所有平台软件信息
		List<ProjectInfo> projectList = projectInfoService.getProjectListByFactoryid(factoryId);
		for (ProjectInfo projectInfo : projectList) {
			Map<String, Object> map = new HashMap<String, Object>();
			//厂商ID
			map.put("factoryId", factoryId);
			//用户ID
			map.put("userId", userId);
			map.put("deviceid", projectInfo.getId());
			int sum = factoryContactService.getReport(map).size();
			if (sum > 0) {
				projectInfo.setHasAllot(true);
			}else {
				projectInfo.setHasAllot(false);
			}
		}
		//获取服务商下所有机柜设备信息
		List<CabInfo> cabList = cabInfoService.getCabListByFactoryid(factoryId);
		for (CabInfo cabInfo : cabList) {
			Map<String, Object> map = new HashMap<String, Object>();
			//厂商ID
			map.put("factoryId", factoryId);
			//用户ID
			map.put("userId", userId);
			map.put("deviceid", cabInfo.getId());
			int sum = factoryContactService.getReport(map).size();
			if (sum > 0) {
				cabInfo.setHasAllot(true);
			}else {
				cabInfo.setHasAllot(false);
			}
		}		
		
		model.addAttribute("contactInfo",contactInfo);
		model.addAttribute("deviceList",deviceList);
		model.addAttribute("videoList",videoList);
		model.addAttribute("serverList",serverList);
		model.addAttribute("databaseList",databaseList);
		model.addAttribute("ftpList",ftpList);
		model.addAttribute("projectList",projectList);
		model.addAttribute("cabList",cabList);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "sergrade/contact/allot";
	}
	
	/***
	 * 给运维人员分配责任资产信息
	 * @param factoryContact
	 * @param menuid
	 * @param redirectAttributesr
	 * @return
	 */
	@RequestMapping(value = "doAllot", method = RequestMethod.POST)
	@LogAspect(desc="分配责任资产")
	public String doAllot(FactoryContact factoryContact,String menuid, RedirectAttributes redirectAttributes){
		logger.info("menuid:"+menuid);
		//根据用户ID查询责任设备信息列表
		Map<String, Object> map = new HashMap<String, Object>();
		//用户ID
		String userId = factoryContact.getUserId();
		map.put("userId", userId);
		List<FactoryContact> list = factoryContactService.getReport(map);
		//ID集合
		String[] ids = new String[list.size()];
		//设备ID集合
		String[] deviceIds = new String[list.size()];
		//运维人员ID集合
		String[] contactIds = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ids[i] = list.get(i).getId();
			deviceIds[i] = list.get(i).getDeviceid();
			contactIds[i] = list.get(i).getContactid();
		}
		//设备ID集合 页面传值
		String[] allotDeviceIds = new String[0];
		if (!"".equals(factoryContact.getAllotProperty())) {
			allotDeviceIds = factoryContact.getAllotProperty().split(",");
		}
		//如果该用户已分配责任设备信息，则进行如下操作
		if (list.size() > 0) {
			//如果数据库中责任设备数和页面责任设备数相同，则为当前运维人员责任设备的修改操作
			if (list.size() == allotDeviceIds.length) {
				//数据库需要修改的设备ID集合
				List<String> setDeviceIdList = ListUtils.compare(allotDeviceIds, deviceIds);
				//页面传递的新的设备ID集合
				List<String> allotDeviceIdList = ListUtils.compare(deviceIds, allotDeviceIds);
				if (allotDeviceIdList.size() > 0) {
					for (int i = 0; i < setDeviceIdList.size(); i++) {
						//根据用户ID查询责任设备信息列表
						Map<String, Object> fmap = new HashMap<String, Object>();
						//用户ID
						fmap.put("userId", userId);
						//设备ID
						fmap.put("deviceid", setDeviceIdList.get(i));
						List<FactoryContact> conlist = factoryContactService.getReport(fmap);
						//修改操作
						for (FactoryContact vo : conlist) {
							FactoryContactSearch factoryContactSearch = new FactoryContactSearch();
							factoryContactSearch.createCriteria().andIdEqualTo(vo.getId());
							
							FactoryContact domain = new FactoryContact();
							domain.setDeviceid(allotDeviceIdList.get(i));
							factoryContactService.updateByExampleSelective(domain,factoryContactSearch);
							
						}
					}
				}
			//如果数据库中责任设备数小于页面责任设备数，则为新责任设备的添加操作
			} else if (list.size() < allotDeviceIds.length) {
				List<String> allotDeviceIdList = ListUtils.compare(deviceIds, allotDeviceIds);
				//数据库里需要的设备ID集合
				List<String> delDeviceIdList = ListUtils.compare(allotDeviceIds, deviceIds);
				//新增操作
				for (int i = 0; i < allotDeviceIdList.size(); i++) {
					//主键ID
					factoryContact.setId(UuidGenerateUtil.getUUIDLong());
					factoryContact.setDeviceid(allotDeviceIdList.get(i));
					factoryContactService.save(factoryContact);
				}
				//删除操作
				for (int j = 0; j < delDeviceIdList.size(); j++) {
					//根据用户ID查询责任设备信息列表
					Map<String, Object> fmap = new HashMap<String, Object>();
					//用户ID
					fmap.put("userId", userId);
					//设备ID
					fmap.put("deviceid", delDeviceIdList.get(j));
					List<FactoryContact> conlist = factoryContactService.getReport(fmap);
					for (FactoryContact vo : conlist) {
						FactoryContactSearch factoryContactSearch = new FactoryContactSearch();
						factoryContactSearch.createCriteria().andIdEqualTo(vo.getId());
						factoryContactService.deleteByExample(factoryContactSearch);
					}
				}
			//如果数据库中责任设备数大于页面责任设备数，则为当前所有责任设备的删除操作	
			} else if (list.size() > allotDeviceIds.length) {
				//页面删除设备ID集合
				List<String> allotDeviceIdList = ListUtils.compare(allotDeviceIds, deviceIds);
				//页面新增责任设备ID集合
				List<String> addDeviceIdList = ListUtils.compare(deviceIds, allotDeviceIds);
				//删除操作
				for (int i = 0; i < allotDeviceIdList.size(); i++) {
					//根据用户ID查询责任设备信息列表
					Map<String, Object> fmap = new HashMap<String, Object>();
					//用户ID
					fmap.put("userId", userId);
					//设备ID
					fmap.put("deviceid", allotDeviceIdList.get(i));
					List<FactoryContact> conlist = factoryContactService.getReport(fmap);
					for (FactoryContact vo : conlist) {
						FactoryContactSearch factoryContactSearch = new FactoryContactSearch();
						factoryContactSearch.createCriteria().andIdEqualTo(vo.getId());
						factoryContactService.deleteByExample(factoryContactSearch);
					}
				}
				//新增操作
				for (int i = 0; i < addDeviceIdList.size(); i++) {
					if (!"".equals(addDeviceIdList.get(i))) {
						//主键ID
						factoryContact.setId(UuidGenerateUtil.getUUIDLong());
						factoryContact.setDeviceid(addDeviceIdList.get(i));
						factoryContactService.save(factoryContact);
					}
					
				}
			}
		//如果该运维人员下无分配责任设备信息，则进行如下操作
		} else {
			for (int i = 0; i < allotDeviceIds.length; i++) {
				if (!"".equals(allotDeviceIds[i])) {
					//主键ID
					factoryContact.setId(UuidGenerateUtil.getUUIDLong());
					factoryContact.setDeviceid(allotDeviceIds[i]);
					factoryContactService.save(factoryContact);
				}
				
			}
			
		}
		
		redirectAttributes.addFlashAttribute("message","责任资产分配成功");
		
		return "redirect:/sergrade/contact/list/"+menuid+"/";
	}
	
	/**
	 * 编辑短信内容
	 * @param mobile
	 * @return
	 */
	public String sms(String mobile) {
		String sms = "尊敬的运维人员，您好！您已经成功注册智能交通运维管理平台用户，用户名为当前手机号码："+mobile+"，初始密码为：123456，请尽快登录系统修改密码。";
		
		return sms;
	}
}
