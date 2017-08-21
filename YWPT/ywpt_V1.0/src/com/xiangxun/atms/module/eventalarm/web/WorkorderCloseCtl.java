package com.xiangxun.atms.module.eventalarm.web;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
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
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.service.AssetInfoService;
import com.xiangxun.atms.module.property.service.DeviceInfoService;

/**
 * 工单关闭
 * @author kouyunhao
 *
 */
@Controller
@RequestMapping(value="alarm/workclose")
public class WorkorderCloseCtl extends BaseCtl {
	
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
		String[] statusarr = new String[]{WorkorderStatus.RECEIVED.getValue(),WorkorderStatus.REFUSED.getValue(),WorkorderStatus.REPORTED.getValue(),WorkorderStatus.COMPLETE.getValue()};
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
		
		return "alarm/workorder/close/list";
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
//			assetInfo.setCabinetStatus(0);
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
		return "redirect:/alarm/workclose/list/" + menuid + "/";
	}
	
	/***
	 * 工单遗留
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="leave",method = RequestMethod.POST)
	@LogAspect(desc = "工单遗留")
	public String leave(WorkorderInfo workorder, String menuid, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String ids = workorder.getId();
		String[] id = ids.split(",");
		String reason = workorder.getReason();
		for (String string : id) {
			workorder = workorderInfoService.getById(string);
			workorder.setStatus(WorkorderStatus.LEAVING.getValue());
			workorder.setIsleave("1");
			workorderInfoService.updateByIdSelective(workorder);
			
			String assetid = workorder.getAssetid();
			AssetInfo assetInfo = assetInfoService.getById(assetid);
			assetInfo.setPayoutstatus("2");
			assetInfoService.updateByIdSelective(assetInfo);
			
			//添加日志信息
			WorkorderLog log = new WorkorderLog();
			log.setId(UuidGenerateUtil.getUUID());
			log.setOperator(getCurrentUserId());
			log.setAccount(getCurrentUser().getAccount());
			log.setOpertime(new Date());
			log.setOpercontent("工单遗留");
			log.setWorkstatus(WorkorderStatus.LEAVING.getValue());
			log.setWorkid(string);
			log.setNote(reason);
			workorderLogService.save(log);
		}
		redirectAttributes.addFlashAttribute("message", "工单遗留成功");
		return "redirect:/alarm/workclose/list/" + menuid + "/";
	}

}
