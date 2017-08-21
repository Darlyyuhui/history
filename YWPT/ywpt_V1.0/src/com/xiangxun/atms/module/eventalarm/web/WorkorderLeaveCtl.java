package com.xiangxun.atms.module.eventalarm.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xiangxun.atms.common.user.service.UserService;
import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.JsonUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.module.eventalarm.domain.ServerStatus;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderLog;
import com.xiangxun.atms.module.eventalarm.service.PublicMethodService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderLogService;
import com.xiangxun.atms.module.eventalarm.status.WorkorderStatus;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.service.AssetInfoService;
import com.xiangxun.atms.module.property.service.DeviceInfoService;
import com.xiangxun.atms.module.sergrade.service.FactoryContactService;
import com.xiangxun.atms.module.sms.service.SendTaskService;
import com.xiangxun.atms.module.sms.vo.SendTask;

/**
 * 工单遗留
 * @author tianbo
 *
 */
@Controller
@RequestMapping(value="alarm/workleave")
public class WorkorderLeaveCtl extends BaseCtl {
	
	@Resource
	WorkorderInfoService workorderInfoService;
	
	@Resource
	WorkorderLogService workorderLogService;
	
	@Resource
	DeviceInfoService deviceInfoService;
	
	@Resource
	PublicMethodService publicMethodService;
	
	@Resource
	AssetInfoService assetInfoService;
	
	@Resource
	UserService userService;
	
	@Resource
	SendTaskService sendTaskService;
	
	@Resource
	FactoryContactService factoryContactService;
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		logger.info("正在进行工单关闭信息列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
		SessionParameter sp = new SessionParameter();
		sp.updateSessionMap(request, menuid, searchParams);
		String[] statusarr = new String[]{WorkorderStatus.LEAVING.getValue()};
		StringBuilder sb = new StringBuilder();
		for(String st : statusarr){
			sb.append("'" + st + "',");
		}
		searchParams.put("sortType", "ASSIGNTIME desc");
		searchParams.put("statusin", StringUtils.removeEnd(sb.toString(), ","));
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
		
		return "alarm/workorder/leave/list";
	}
	
	/***
	 * 工单重新派发
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="/newDoAssign",method = RequestMethod.POST)
	@LogAspect(desc = "工单重新派发")
	public String newDoAssign(WorkorderInfo workorder, String menuid, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		User user = userService.getById(workorder.getContact());
		String ids = workorder.getId();
		String reason = workorder.getReason();
		String messagenote = reason;
		String[] id = ids.split(",");
		for (String string : id) {
			workorder = workorderInfoService.getById(string);
			//重新派发人
			workorder.setNote(reason);
			workorder.setAssignaccount(getCurrentUserId());
			
			//重新派发时间
			workorder.setAssigntime(new Date());
			workorder.setStatus(WorkorderStatus.ASSIGNED.getValue());
			workorder.setIsleave("0");
			
//			//重新设置维护人员信息
			workorder.setContact(user.getId());
			workorder.setTelephone(user.getMobile());
			String messages = workorder.getMessages();
			int indexOne = messages.indexOf("的");
			int indexTwo = messages.indexOf("，");
			String newMessages = messages.substring(0, indexOne + 1) + user.getName() + messages.substring(indexTwo,messages.length());
			workorder.setMessages(newMessages);
			
			//重新设置巡更
			workorder.setXungeng("0");
			workorder.setXungengtime(null);
			
			workorderInfoService.updateByIdSelective(workorder);
			
			
			// 发送短信服务
			SendTask sendTask = new SendTask();
			sendTask.setDestnumber(user.getMobile());
			sendTask.setContent(newMessages);
			sendTaskService.save(sendTask);
			
			
			//添加日志信息
			WorkorderLog log = new WorkorderLog();
			log.setId(UuidGenerateUtil.getUUID());
			log.setOperator(getCurrentUserId());
			log.setAccount(getCurrentUser().getAccount());
			log.setOpertime(new Date());
			log.setOpercontent("工单重新派发");
			log.setWorkstatus(WorkorderStatus.ASSIGNED.getValue());
			log.setWorkid(string);
			log.setNote(messagenote);
			workorderLogService.save(log);
		}
		redirectAttributes.addFlashAttribute("message", "工单重新派发成功");
		return "redirect:/alarm/workleave/list/" + menuid + "/";
	}
	
	/***
	 * 工单关闭
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="off",method = RequestMethod.POST)
	@LogAspect(desc = "工单关闭")
	public String off(WorkorderInfo workorder, String menuid, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String leaveoff = request.getParameter("leaveoff");
		String note = "";
		String reason = "";
		if(leaveoff != null){
			note = workorder.getNote();
		}else{
			reason = workorder.getReason();
		}
		String ids = workorder.getId();
		String[] id = ids.split(",");
		for (String string : id) {
			workorder = workorderInfoService.getById(string);
			workorder.setOffaccount(getCurrentUserId());
			workorder.setOfftime(new Date());
			workorder.setStatus(WorkorderStatus.CLOSED.getValue());
			workorder.setIsleave("0");
			workorder.setReason(reason);

			workorderInfoService.updateByIdSelective(workorder);
			
			//tianbo增加更新资产状态
			String assetid = workorder.getAssetid();
			
			AssetInfo assetInfo = assetInfoService.getById(assetid);
			assetInfo.setPayoutstatus("0");			
	//		assetInfo.setCabinetStatus(0);
			
			assetInfo.setCabinetStatus(assetInfo.getCabinetStatus());
			assetInfoService.updateByIdSelective(assetInfo);
			
//			DeviceInfo device = deviceInfoService.selectDevlistByCode(workorder.getDevicecode());
//			if(device != null){
//				device.setStatus("0");
//				deviceInfoService.updateByIdSelective(device);
//				//将资产状态修改为运行状态
//				List<AssetInfo> assetList = assetInfoService.findByDeviceId(device.getId());
//				if(assetList != null && assetList.size() != 0){
//					AssetInfo asset = assetList.get(0);
//					String status = asset.getAssetstatus();
//					if(!status.equals(WorkorderStatus.REFUSED.getValue())){
//						asset.setAssetstatus("001");
//						assetInfoService.updateByIdSelective(asset);
//					}
//				}
//			}
			//添加日志信息
			WorkorderLog log = new WorkorderLog();
			log.setId(UuidGenerateUtil.getUUID());
			log.setOperator(getCurrentUserId());
			log.setAccount(getCurrentUser().getAccount());
			log.setOpertime(new Date());
			log.setOpercontent("工单关闭");
			log.setWorkstatus(WorkorderStatus.CLOSED.getValue());
			log.setWorkid(string);
			log.setNote(reason.equals("") ? note : reason);
			workorderLogService.save(log);
		}
		redirectAttributes.addFlashAttribute("message", "工单关闭成功");
		return "redirect:/alarm/workleave/list/" + menuid + "/";
	}
	
	
	@RequestMapping(value = "user/{id}/")
	@ResponseBody
	public void getUser(HttpServletResponse resp, @PathVariable String id) {
		
		WorkorderInfo workorder = workorderInfoService.getById(id);
		
		User user = userService.getById(workorder.getContact());
		AssetInfo assetInfo = assetInfoService.getById(workorder.getAssetid());
		List<User> contactList = factoryContactService.findContactList(assetInfo.getDeviceid());
		setContactData(contactList);
		Gson gson = new Gson();
		JsonObject object = new JsonObject();
		object.addProperty("contactList", gson.toJson(contactList));
		object.addProperty("user", gson.toJson(user));
		try {
			resp.getWriter().write(object.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
}
