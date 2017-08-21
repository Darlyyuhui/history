package com.xiangxun.atms.module.eventalarm.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;
import com.xiangxun.atms.common.road.service.RoadInfoService;
import com.xiangxun.atms.common.road.vo.RoadInfo;
import com.xiangxun.atms.common.user.service.UserService;
import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.JsonUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.module.eventalarm.domain.ExceptionInfo;
import com.xiangxun.atms.module.eventalarm.domain.ServerStatus;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderLog;
import com.xiangxun.atms.module.eventalarm.service.ExceptionInfoService;
import com.xiangxun.atms.module.eventalarm.service.PublicMethodService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderLogService;
import com.xiangxun.atms.module.eventalarm.status.WorkorderStatus;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.CabInfo;
import com.xiangxun.atms.module.property.domain.DatabaseInfo;
import com.xiangxun.atms.module.property.domain.DeviceCompanyInfo;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.FtpInfo;
import com.xiangxun.atms.module.property.domain.ProjectInfo;
import com.xiangxun.atms.module.property.domain.ServerInfo;
import com.xiangxun.atms.module.property.domain.VideoInfo;
import com.xiangxun.atms.module.property.service.AssetInfoService;
import com.xiangxun.atms.module.property.service.CabInfoService;
import com.xiangxun.atms.module.property.service.DatabaseInfoService;
import com.xiangxun.atms.module.property.service.DeviceCompanyInfoService;
import com.xiangxun.atms.module.property.service.DeviceInfoService;
import com.xiangxun.atms.module.property.service.FtpInfoService;
import com.xiangxun.atms.module.property.service.ProjectInfoService;
import com.xiangxun.atms.module.property.service.ServerInfoService;
import com.xiangxun.atms.module.property.service.VideoInfoService;
import com.xiangxun.atms.module.sergrade.domain.FactoryContact;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.service.ContactInfoService;
import com.xiangxun.atms.module.sergrade.service.FactoryContactService;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;
import com.xiangxun.atms.module.sms.service.SendTaskService;
import com.xiangxun.atms.module.sms.vo.SendTask;

/**
 * 工单派发管理
 * @author kouyunhao
 *
 */
@Controller
@RequestMapping(value="alarm/workassign")
public class WorkorderAssignCtl extends BaseCtl {

	@Resource
	WorkorderInfoService workorderInfoService;
	
	@Resource
	WorkorderLogService workorderLogService;
	
	@Resource
	AssetInfoService assetInfoService;
	
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
	ProjectInfoService projectInfoService;
	
	@Resource
	CabInfoService cabInfoService;
	
	@Resource
	DeviceCompanyInfoService deviceCompanyInfoService;
	
	@Resource
	ContactInfoService contactInfoService;
	
	@Resource
	FactoryInfoService factoryInfoService;
	
	@Resource
	FactoryContactService factoryContactService;
	
	@Resource
	RoadInfoService roadInfoService;
	
	@Resource
	PublicMethodService publicMethodService;
	
	@Resource
	ExceptionInfoService exceptionInfoService;
	
	@Resource
	SendTaskService sendTaskService;
	
	@Resource
	UserService userService;
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		logger.info("正在进行工单派发信息列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
		SessionParameter sp = new SessionParameter();
		sp.updateSessionMap(request, menuid, searchParams);
		searchParams.put("sortType", "ASSIGNTIME desc");
		Page page = workorderInfoService.getListByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);
		publicMethodService.setStatusColor(page);
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		
		WorkorderInfo workorder = new WorkorderInfo();
		try {
			BeanUtils.populate(workorder, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("workorder", workorder);
		
		return "alarm/workorder/assign/list";
	}
	
	/**
	 * 获取待派发工单信息
	 * @param workorder
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "showassign/{deviceid}/{devicetype}/{menuid}/", method = RequestMethod.GET)
	public String showassign(@PathVariable("deviceid") String deviceid, 
			@PathVariable("devicetype") String devicetype, 
			@PathVariable("menuid") String menuid, String page, Model model,RedirectAttributes redirectAttributes) {
		AssetInfo asset = assetInfoService.getById(deviceid);
		deviceid = asset.getDeviceid();
		FactoryInfo factory = null;
		List<User> contactList = factoryContactService.findContactList(deviceid);
		setContactData(contactList);
		boolean dataerror = false;
		if(devicetype.equals("device")){
			DeviceInfo device = deviceInfoService.getById(deviceid);
			if(device != null){
				factory = factoryInfoService.getById(device.getFactoryId());
				DeviceCompanyInfo company = deviceCompanyInfoService.getById(device.getCompanyId());
				RoadInfo road = roadInfoService.getById(device.getRoadId());
				model.addAttribute("company",company);
				model.addAttribute("position",road.getName());
				model.addAttribute("device", device);
			}else{
				dataerror = true;
			}
		}else if(devicetype.equals("cabinet")){
			CabInfo cab=cabInfoService.getById(deviceid);
			if(cab!=null){
				factory=factoryInfoService.getById(cab.getFactoryId());
				DeviceCompanyInfo company = deviceCompanyInfoService.getById(cab.getCompanyId());
				RoadInfo road = roadInfoService.getById(cab.getRoadId());
				model.addAttribute("company",company);
				model.addAttribute("position",road.getName());
				model.addAttribute("cab", cab);
			}else{
				dataerror=true;
			}
			
		}else if(devicetype.equals("video")){
			VideoInfo video = videoInfoService.getById(deviceid);
			if(video != null){
				factory = factoryInfoService.getById(video.getFactoryId());
				DeviceCompanyInfo company = deviceCompanyInfoService.getById(video.getCompanyid());
				RoadInfo road = roadInfoService.getById(video.getRoadinfoId());
				model.addAttribute("company",company);
				model.addAttribute("position",road.getName());
				model.addAttribute("device", video);
			}else{
				dataerror = true;
			}
			
		}else if(devicetype.equals("server")){
			ServerInfo server = serverInfoService.getById(deviceid);
			if(server != null){
				factory = factoryInfoService.getById(server.getFactoryId());
				//model.addAttribute("position","室内机房");
				model.addAttribute("position",asset.getInstallplace());
				model.addAttribute("device", server);
			}else{
				dataerror = true;
			}
			
		}else if(devicetype.equals("database")){
			DatabaseInfo database = databaseInfoService.getById(deviceid);
			factory = factoryInfoService.getById(database.getFactoryId());
		}else if(devicetype.equals("ftp")){
			FtpInfo ftp = ftpInfoService.getById(deviceid);
			factory = factoryInfoService.getById(ftp.getFactoryId());
		}else if(devicetype.equals("project")){
			ProjectInfo project = projectInfoService.getById(deviceid);
			factory = factoryInfoService.getById(project.getFactoryId());
		}
		if(dataerror){
			redirectAttributes.addFlashAttribute("message","数据异常");
			return "redirect:/alarm/status/search/"+devicetype+"/"+menuid+"/";
		}
		model.addAttribute("page", page);
		model.addAttribute("asset",asset);
		model.addAttribute("factory",factory);
		model.addAttribute("contactList",contactList);
		model.addAttribute("devicetype", devicetype);
		if(!devicetype.equals("device") && !devicetype.equals("video") &&!devicetype.equals("server")){
			return "alarm/workorder/assign/assignsoft";
		}
		return "alarm/workorder/assign/assign";
	}
	
	/**
	 * 设置服务厂商联系人jsondata
	 * @author kouyunhao
	 * @version 1.0
	 * @param builderList
	 * Mar 6, 2014
	 */
	public void setContactData(List<User> contactList){
		if(contactList != null && contactList.size() != 0){
			for(User contact : contactList){
				try {
					contact.setUserdata(JsonUtil.toJson(contact));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 工单派发
	 * @param workorder
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doAssign/{devicetype}/", method = RequestMethod.POST)
	@LogAspect(desc="工单派发")
	public String doAdd(@PathVariable String devicetype,WorkorderInfo workorder, 
			DeviceInfo device,CabInfo cab, VideoInfo video, ServerInfo server, String menuid,RedirectAttributes redirectAttributes,HttpServletRequest request){
		String assetid = request.getParameter("assetid");
		workorder.setId(UuidGenerateUtil.getUUID());
		if(devicetype.equals("device")){
			workorder.setOrgid(device.getOrgId());
		}else if(devicetype.equals("video")){
			workorder.setOrgid(video.getOrgId());
		}else if(devicetype.equals("cabinet")){
			workorder.setOrgid(cab.getOrgId());
		}else if(devicetype.equals("server")){
			workorder.setOrgid(server.getOrgId());
		}else{
			workorder.setIsouter("1");
		}
		if(devicetype.equals("database") || devicetype.equals("ftp") || devicetype.equals("project")){
			workorder.setDevicecode(workorder.getId());
		}
		workorder.setAssignaccount(getCurrentUserId());
		workorder.setAssigntime(new Date());
		workorder.setAssetid(assetid);
		workorderInfoService.save(workorder);
		device.setIssend("1");
		deviceInfoService.updateByIdSelective(device);
		
		//tianbo增加更新这条资产的发布状态
		AssetInfo assetInfo = assetInfoService.getById(assetid);
		assetInfo.setPayoutstatus("1");
		assetInfoService.updateByIdSelective(assetInfo);
		
		//tianbo发送短信服务
		SendTask sendTask = new SendTask();
		sendTask.setDestnumber(workorder.getTelephone());
		sendTask.setContent(workorder.getMessages());
		sendTaskService.save(sendTask);
		
		//添加日志信息
		WorkorderLog log = new WorkorderLog();
		log.setId(UuidGenerateUtil.getUUID());
		log.setOperator(getCurrentUserId());
		log.setAccount(getCurrentUser().getAccount());
		log.setOpertime(new Date());
		log.setOpercontent("工单派发");
		log.setWorkstatus(WorkorderStatus.ASSIGNED.getValue());
		log.setWorkid(workorder.getId());
		workorderLogService.save(log);
		redirectAttributes.addFlashAttribute("message","工单派发成功");
		return "redirect:/alarm/status/search/"+devicetype+"/"+menuid+"/";
	}
	
	/***
	 * 获取详情信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}/{menuid}/", method = RequestMethod.GET)
	public String showView(@PathVariable("id") String id, @PathVariable("menuid") String menuid, 
			@RequestParam(value = "sortType", defaultValue = "OPERTIME desc") String sortType,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,Model model, HttpServletRequest request) throws ParseException {
		WorkorderInfo workorder = workorderInfoService.getById(id);
		publicMethodService.setStatusColor(workorder);
		String exceptionid = workorder.getExceptionid();
		if(exceptionid != null){
			ExceptionInfo exception = exceptionInfoService.getById(exceptionid);
			model.addAttribute("exception", exception);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("workid", id);
		searchParams.put("sortType", "OPERTIME desc");
		Page page = workorderLogService.getListByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE-5, sortType);
		Object obj = page.getResult();
		if(obj!=null && obj instanceof List){
			@SuppressWarnings("unchecked")
			List<WorkorderLog> list = (List<WorkorderLog>)obj;
			if(list != null && list.size() != 0){
				for (WorkorderLog resource : list) {
					resource.setStatus(resource.getWorkstatus());
				}
			}
		}
		publicMethodService.setStatusColor(page);
		model.addAttribute("pageList", page);
		model.addAttribute("workorder", workorder);
		model.addAttribute("menuid",menuid);
		return "alarm/workorder/view";
	}
	
	/***
	 * 获取详情信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "logView/{id}/{menuid}/", method = RequestMethod.GET)
	public String logView(@PathVariable("id") String id, @PathVariable("menuid") String menuid, 
			@RequestParam(value = "sortType", defaultValue = "OPERTIME desc") String sortType,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,Model model, HttpServletRequest request) throws ParseException {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("workid", id);
		searchParams.put("sortType", "OPERTIME desc");
		Page page = workorderLogService.getListByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE-5, sortType);
		Object obj = page.getResult();
		if(obj!=null && obj instanceof List){
			@SuppressWarnings("unchecked")
			List<WorkorderLog> list = (List<WorkorderLog>)obj;
			if(list != null && list.size() != 0){
				for (WorkorderLog resource : list) {
					resource.setStatus(resource.getWorkstatus());
				}
			}
		}
		publicMethodService.setStatusColor(page);
		model.addAttribute("pageList", page);
		model.addAttribute("page",pageNumber);
		model.addAttribute("menuid",menuid);
		return "alarm/workorder/detail";
	}
	
	/***
	 * 获取选择的责任人工单数
	 * @param req
	 * @param ip
	 * @return
	 */
	@RequestMapping(value = "countworkordernew/{contact}/")
	@ResponseBody
	public void getStatusInfo(HttpServletResponse resp,@PathVariable String contact) {
		int count = workorderInfoService.countByContact(contact);
				JsonObject object = new JsonObject();
				object.addProperty("count", String.valueOf(count));
				try {
					resp.getWriter().write(object.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	
}
