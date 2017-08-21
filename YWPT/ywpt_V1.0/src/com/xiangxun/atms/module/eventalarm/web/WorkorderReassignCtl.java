package com.xiangxun.atms.module.eventalarm.web;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.common.road.service.RoadInfoService;
import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.JsonUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderLog;
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
import com.xiangxun.atms.module.property.service.impl.CabInfoServiceImpl;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.service.FactoryContactService;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;

/**
 * 工单转派管理
 * @author kouyunhao
 *
 */
@Controller
@RequestMapping(value="alarm/workreassign")
public class WorkorderReassignCtl extends BaseCtl {

	@Resource
	WorkorderInfoService workorderInfoService;
	
	@Resource
	WorkorderLogService workorderLogService;
	
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
	//CabInfoService cabInfoService;
	CabInfoServiceImpl cabInfoService;
	
	@Resource
	DeviceCompanyInfoService deviceCompanyInfoService;
	
	@Resource
	FactoryInfoService factoryInfoService;
	
	@Resource
	FactoryContactService factoryContactService;
	
	@Resource
	AssetInfoService assetInfoService;
	
	@Resource
	RoadInfoService roadInfoService;
	
	@Resource
	PublicMethodService publicMethodService;
	
	/***
	 * 工单转派
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String reassignlist(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		logger.info("正在进行工单转派信息列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
		SessionParameter sp = new SessionParameter();
		sp.updateSessionMap(request, menuid, searchParams);
		searchParams.put("sortType", "ASSIGNTIME desc");
		searchParams.put("status", WorkorderStatus.REFUSED.getValue());
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
		
		return "alarm/workorder/reassign/list";
	}
	
	/**
	 * 获取待转派工单信息
	 * @param workorder
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "showassign/{workorderid}/{menuid}/", method = RequestMethod.GET)
	public String showassign(@PathVariable("workorderid") String workorderid, 
			@PathVariable("menuid") String menuid, String page, Model model,RedirectAttributes redirectAttributes) {
		WorkorderInfo workorder = workorderInfoService.getById(workorderid);
		String devicetype = workorder.getDevicetype();
		String deviceid = "";
		DeviceCompanyInfo company = null;
		if(devicetype.equals("device")){
			DeviceInfo device = deviceInfoService.selectDevlistByCode(workorder.getDevicecode());
			deviceid = device.getId();
			company = deviceCompanyInfoService.getById(device.getCompanyId());
		}else if(devicetype.equals("video")){
			List<VideoInfo> deviceList = videoInfoService.findByCode(workorder.getDevicecode());
			if(deviceList != null && deviceList.size() != 0){
				VideoInfo device = deviceList.get(0);
				deviceid = device.getId();
				company = deviceCompanyInfoService.getById(device.getCompanyid());
			}
		}else if(devicetype.equals("server")){
			List<ServerInfo> deviceList = serverInfoService.findByCode(workorder.getDevicecode());
			if(deviceList != null && deviceList.size() != 0){
				ServerInfo device = deviceList.get(0);
				deviceid = device.getId();
			}
		}else if(devicetype.equals("cabinet")){
			List<CabInfo> deviceList=cabInfoService.findByName(workorder.getDevicename());
			if(deviceList != null && deviceList.size() != 0){
				CabInfo device = deviceList.get(0);
				deviceid = device.getId();
				company=deviceCompanyInfoService.getById(device.getCompanyId());
			}
		}else if(devicetype.equals("database")){
			List<DatabaseInfo> databaseList = databaseInfoService.findByName(workorder.getDevicename());
			if(databaseList != null && databaseList.size() != 0){
				DatabaseInfo device = databaseList.get(0);
				deviceid = device.getId();
			}
		}else if(devicetype.equals("ftp")){
			List<FtpInfo> ftpList = ftpInfoService.findByName(workorder.getDevicename());
			if(ftpList != null && ftpList.size() != 0){
				FtpInfo device = ftpList.get(0);
				deviceid = device.getId();
			}
		}else if(devicetype.equals("project")){
			List<ProjectInfo> projectList = projectInfoService.findByName(workorder.getDevicename());
			if(projectList != null && projectList.size() != 0){
				ProjectInfo device = projectList.get(0);
				deviceid = device.getId();
			}
		}
		FactoryInfo factory = factoryInfoService.getById(workorder.getCompanyid());
		model.addAttribute("factory",factory);
		List<AssetInfo> asset = assetInfoService.findByDeviceId(deviceid);
		List<User> contactList = factoryContactService.findContactList(deviceid);
		setContactData(contactList);
		model.addAttribute("asset",asset.size() == 0 ? null: asset.get(0));
		model.addAttribute("workorder",workorder);
		model.addAttribute("devicetype",devicetype);
		model.addAttribute("company",company);
		model.addAttribute("contactList",contactList);
		model.addAttribute("page", page);
		if(!devicetype.equals("device") && !devicetype.equals("video") &&!devicetype.equals("server")&&!devicetype.equals("cabinet")){
			return "alarm/workorder/reassign/reassignsoft";
		}
		return "alarm/workorder/reassign/assign";
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
	 * 工单转派
	 * @param workorder
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doAssign/", method = RequestMethod.POST)
	@LogAspect(desc="工单转派")
	public String doAdd(WorkorderInfo workorder, String menuid,RedirectAttributes redirectAttributes){
		
		workorder.setStatus(WorkorderStatus.REASSIGNED.getValue());
		workorder.setIsreassign("1");
		workorder.setXungeng("0");
		workorder.setXungengtime(null);
		workorderInfoService.updateByIdSelective(workorder);
		//添加日志信息
		WorkorderLog log = new WorkorderLog();
		log.setId(UuidGenerateUtil.getUUID());
		log.setOperator(getCurrentUserId());
		log.setAccount(getCurrentUser().getAccount());
		log.setOpertime(new Date());
		log.setOpercontent("工单转派");
		log.setWorkstatus(WorkorderStatus.REASSIGNED.getValue());
		log.setWorkid(workorder.getId());
		workorderLogService.save(log);
		redirectAttributes.addFlashAttribute("message","工单转派成功");
		return "redirect:/alarm/workreassign/list/"+menuid+"/";
	}
	
}
