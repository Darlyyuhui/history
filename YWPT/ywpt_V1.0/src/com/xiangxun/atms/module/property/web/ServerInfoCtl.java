package com.xiangxun.atms.module.property.web;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
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
import com.xiangxun.atms.common.road.vo.RoadInfo;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimpleXlsExportor;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.CabInfo;
import com.xiangxun.atms.module.property.domain.DeviceCompanyInfo;
import com.xiangxun.atms.module.property.domain.ModifyRecord;
import com.xiangxun.atms.module.property.domain.ServerInfo;
import com.xiangxun.atms.module.property.service.AssetInfoService;
import com.xiangxun.atms.module.property.service.ModifyRecordService;
import com.xiangxun.atms.module.property.service.ServerInfoHisService;
import com.xiangxun.atms.module.property.service.ServerInfoService;
import com.xiangxun.atms.module.sergrade.domain.FactoryContact;
import com.xiangxun.atms.module.sergrade.service.FactoryContactService;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;

/***
 * 服务器信息维护
 * @author kouyunhao
 * 
 */
@Controller
@RequestMapping(value="property/serverinfo")
public class ServerInfoCtl extends BaseCtl {
	
	@Resource
	ModifyRecordService modifyRecordService;
	
	@Resource
	WorkorderInfoService workorderInfoService;
	
	@Resource
	DepartmentService departmentService;
	
	@Resource
	FactoryInfoService factoryInfoService;
	
	@Resource
	FactoryContactService factoryContactService;
	
	@Resource
	ServerInfoService serverInfoService;
	
	@Resource
	ServerInfoHisService serverInfoHisService;
	
	@Resource
	AssetInfoService assetInfoService;
	
	private JsonArray deptJsonArray;
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		logger.info("正在进行服务器信息列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
		SessionParameter sp = new SessionParameter();
		sp.updateSessionMap(request, menuid, searchParams);
		searchParams.put("sortType", "ADD_TIME desc");
		Page page = null;
		String deptid = getCurrentUser().getDeptId();
		if(deptid.equals("00")){
			page = serverInfoService.getListByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);
		}else{
			page = serverInfoService.getListByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType,menuid);
		}
		for(int i = 0; i < page.getSize(); i++){
			ServerInfo serverInfo = (ServerInfo) page.getResult().get(i);
			if(serverInfo != null){
				//添加是否变更判断
				boolean hasModified = serverInfoHisService.hasRecordModified(serverInfo.getId());
				serverInfo.setHasModified(hasModified);
			}
		}
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		// 部门
		JsonArray departmentjsonArray = departmentService
				.getDeptJsonArray(menuid);
		request.getSession().setAttribute("departmentjsonArray", departmentjsonArray.toString());
		//服务厂商
		request.getSession().setAttribute("factoryList", factoryInfoService.findAll());
		ServerInfo server = new ServerInfo();
		try {
			BeanUtils.populate(server, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("server", server);
		
		return "property/serverinfo/list";
	}
	
	/**
	 * 组织页面需要的树结构json字符
	 * 
	 * @param deptId
	 * @return
	 */
	@SuppressWarnings("unused")
	private JsonArray getDeptJsonArray(List<Department> departList) {
		if (departList == null)
			return deptJsonArray;
		for (Department department : departList) {
			JsonObject json = new JsonObject();
			json.addProperty("id", department.getId());
			json.addProperty("pId", department.getParentid());
			json.addProperty("isParent", departmentService.hasChild(department
					.getId()));
			json.addProperty("name", department.getName());
			if ("00".equals(department.getId())) {
				json.addProperty("nocheck", "true");
			} else {
				json.addProperty("checked", "false");
			}
			if (departmentService.hasChild(department.getId())) {
				List<Department> sonDepartList = departmentService
						.getChildren(department.getId());
				this.getDeptJsonArray(sonDepartList);
			}
			deptJsonArray.add(json);
		}
		return deptJsonArray;
	}
	
	/***
	 * 删除一个服务器
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除了一个服务器")
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在删除了一个服务器。。。。。。");
			String[] id = ids.split(",");
			boolean deleteFlag = true;
//			for (String string : id) {
//				boolean hasModified = serverInfoHisService.hasRecordModified(string);
//				if(hasModified){
//					deleteFlag = false;
//				}
//			}
//			if(!deleteFlag){
//				entity.setResult("can't");
//				return entity;
//			}
			for (String string : id) {
//				//同步删除资产信息
//				List<AssetInfo> assetList = assetInfoService.findByDeviceId(string);
//				if(assetList != null && assetList.size() != 0){
//					AssetInfo asset = assetList.get(0);
//					assetInfoService.deleteById(asset.getId());
//				}
				ServerInfo server = serverInfoService.getById(string);
				//同步删除资产信息
				assetInfoService.deleteAssetByDeviceId(string);
				//同步删除服务商分配信息
				assetInfoService.deleteFactoryContactByDeviceId(string);
				//同步删除卡口历史信息
				assetInfoService.deleteServerHisByCode(server.getCode());
				//同步删除更改记录
				assetInfoService.deleteModifyByCode(server.getCode());
				//同步删除设备日志信息
				assetInfoService.deleteDeviceLogByCode(server.getCode());
				//同步删除工单信息
				List<WorkorderInfo> workorderList = workorderInfoService.findByDeviceCode(server.getCode());
				if(workorderList != null && workorderList.size() != 0){
					for(WorkorderInfo workorder : workorderList){
						String exceptionid = workorder.getExceptionid();
						if(exceptionid != null){
							assetInfoService.deleteWorkorderReportById(exceptionid);
						}
						assetInfoService.deleteWorkorderLogByWorkId(workorder.getId());
						assetInfoService.deleteWorkorderAppriseByWorkId(workorder.getId());
					}
				}
				assetInfoService.deleteWorkorderByCode(server.getCode());
				
				serverInfoService.deleteById(string);
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	/***
	 * 添加一个服务器
	 * @param server
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	@LogAspect(desc="添加一个服务器")
	public String doAdd(ServerInfo server,AssetInfo asset,String menuid,RedirectAttributes redirectAttributes) throws ParseException{
		server.setId(UuidGenerateUtil.getUUID());
		server.setCode("6100"+ new SimpleDateFormat("yyMMddHHmmss").format(new Date()));
		server.setRamVolume(server.getRamVolume()+server.getRamUnit());
		server.setDiskVolume(server.getDiskVolume()+server.getDiskUnit());
		server.setOperator(getCurrentUserId());
		server.setAddTime(new Date());
		serverInfoService.save(server);
				
		String toasset = asset.getToasset();
		if(toasset.equals("1")){
			asset.setId(UuidGenerateUtil.getUUID());
			asset.setAssettype("server");
			asset.setAssetname(server.getName());
			asset.setDeviceid(server.getId());
			asset.setIp(server.getServerip());
			asset.setGuaranteetime(DateUtil.parseDate(asset.getGuaranteetimestr(), "yyyy-MM-dd"));
			asset.setPurchasetime(DateUtil.parseDate(asset.getPurchasetimestr(), "yyyy-MM-dd"));
			asset.setInstalltime(DateUtil.parseDate(asset.getInstalltimestr(), "yyyy-MM-dd HH:mm:ss"));
			//服务商ID
			asset.setFactoryId(server.getFactoryId());
			assetInfoService.save(asset);
		}
		redirectAttributes.addFlashAttribute("message","服务器信息添加成功");
		return "redirect:/property/serverinfo/list/"+menuid+"/";
	}

	/***
	 * 获取待修改服务器信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		ServerInfo server = serverInfoService.getById(id);
		server.setRamUnit(server.getRamVolume().substring(server.getRamVolume().length()-2, server.getRamVolume().length()));
		server.setRamVolume(server.getRamVolume().substring(0, server.getRamVolume().length()-2));
		server.setDiskUnit(server.getDiskVolume().substring(server.getDiskVolume().length()-2, server.getDiskVolume().length()));
		server.setDiskVolume(server.getDiskVolume().substring(0, server.getDiskVolume().length()-2));
		server.setOrgNames(server.getOrgId() == null ? "" : departmentService.getById(server.getOrgId()).getName());
		
		//判断是否已经转为资产
		String isasset = "0";
		List<AssetInfo> assetList = assetInfoService.findByDeviceId(id);
		if(assetList != null && assetList.size() != 0){
			isasset = "1";
			model.addAttribute("asset", assetList.get(0));
		}
		model.addAttribute("isasset", isasset);
		
		model.addAttribute("server",server);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "property/serverinfo/update";
	}

	/***
	 * 修改服务器信息
	 * @param server
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc="修改服务器信息")
	public String doUpdate(@ModelAttribute("preload") ServerInfo server,AssetInfo asset,String page,String menuid,
			RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {
		String ismodify = request.getParameter("ismodify");
		if(ismodify.equals("1")){
			String modifyId = server.getId();
			//保存变更记录信息
			ModifyRecord modifyRecord = new ModifyRecord();
			modifyRecord.setId(UuidGenerateUtil.getUUID());
			modifyRecord.setModifyId(modifyId);
			modifyRecord.setModuleName("server");
			modifyRecord.setOperator(getCurrentUserId());
			Date datetime = new Date();
			modifyRecord.setOperatorTime(datetime);
			modifyRecord.setVersion(DateUtil.dateFormatToString(datetime, "yyyyMMddHHmmss"));
			modifyRecord.setModifyType("变更");
			modifyRecordService.save(modifyRecord);
			//将变更的记录COPY到历史表
			serverInfoService.saveHistoryRecord(modifyId);
			AssetInfo assetinfo = null;
			List<AssetInfo> assetList = assetInfoService.findByDeviceId(modifyId);
			if(assetList != null && assetList.size() != 0){
				assetinfo = assetList.get(0);
			}
			//修改变更的记录ID
			String afterRecordId = UuidGenerateUtil.getUUID();
			serverInfoService.updatePrimaryKey(afterRecordId, modifyId);
			//同步修改资产信息
			if(assetinfo != null){
				assetinfo.setDeviceid(afterRecordId);
				assetInfoService.updateByIdSelective(assetinfo);
			}
			//将修改后的记录ID同步更新到历史表字段
			serverInfoHisService.updateAfterRecordId(modifyId, afterRecordId);
			//同步修改运维服务商责任资产设备ID
			List<FactoryContact> list = factoryContactService.findByDeviceId(modifyId);
			for(FactoryContact factoryContact : list){
				factoryContact.setDeviceid(afterRecordId);
				factoryContactService.updateByIdSelective(factoryContact);
			}
			//设置待修改的设备ID为变更后的ID
			server.setId(afterRecordId);
		}
		server.setRamVolume(server.getRamVolume()+server.getRamUnit());
		server.setDiskVolume(server.getDiskVolume()+server.getDiskUnit());
		serverInfoService.updateByIdSelective(server);
		
		String assetid = request.getParameter("assetid");
		//资产配置修改
		String isasset = request.getParameter("isasset");
		//判断是否转入监控
		String toasset = asset.getToasset();
		
		asset.setAssetname(server.getName());
		asset.setGuaranteetime(DateUtil.parseDate(asset.getGuaranteetimestr(), "yyyy-MM-dd"));
		asset.setPurchasetime(DateUtil.parseDate(asset.getPurchasetimestr(), "yyyy-MM-dd"));
		asset.setInstalltime(DateUtil.parseDate(asset.getInstalltimestr(), "yyyy-MM-dd HH:mm:ss"));
		asset.setFactoryId(server.getFactoryId());
		
		if(isasset.equals("1")){
			//如果原有资产信息  并且这次还要监控  则修改操作
			if(toasset.equals("1")){
				//修改
				asset.setId(assetid);
				assetInfoService.updateByIdSelective(asset);
			}else{
				assetInfoService.deleteById(assetid);
			}
		}else{
			//新增
			if(toasset.equals("1")){
				asset.setId(UuidGenerateUtil.getUUID());
				asset.setAssettype("server");
				asset.setAssetname(server.getName());
				asset.setDeviceid(server.getId());
				asset.setIp(server.getServerip());
				asset.setGuaranteetime(DateUtil.parseDate(asset.getGuaranteetimestr(), "yyyy-MM-dd"));
				asset.setPurchasetime(DateUtil.parseDate(asset.getPurchasetimestr(), "yyyy-MM-dd"));
				asset.setInstalltime(DateUtil.parseDate(asset.getInstalltimestr(), "yyyy-MM-dd HH:mm:ss"));
				asset.setFactoryId(server.getFactoryId());
				asset.setAssetstatus("001");
				asset.setInstallplace("内场");
				assetInfoService.save(asset);
			}
		}
		
		String message = "修改成功";
		if(ismodify.equals("1")){
			message = "服务器变更操作成功";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/property/serverinfo/list/"+menuid+"/?page="+page;
	}
	
	/***
	 * 判断名称是否重复 add by kouyunhao 2013-09-02 
	 * @param req
	 * @param nameExist
	 * @return
	 */
	@RequestMapping(value = "nameExist")
	@ResponseBody
	public String nmaeExist(HttpServletRequest req, @RequestParam(value = "name") String name) {
		List<ServerInfo> serverList = serverInfoService.findByName(name);
		String returnStr = Boolean.FALSE.toString();
		if (serverList.size() == 0) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		//不为空说明是修改
		if(StringUtils.isNotBlank(oper)){
			if(name.equals(oper)){
				return Boolean.TRUE.toString();
			}
		}
		return returnStr;
	}

	/***
	 * 判断IP是否重复 add by kouyunhao 2013-09-02 
	 * @param req
	 * @param nameExist
	 * @return
	 */
	@RequestMapping(value = "ipExist")
	@ResponseBody
	public String ipExist(HttpServletRequest req, @RequestParam(value = "serverip") String serverip) {
		List<ServerInfo> serverList = serverInfoService.findByIp(serverip);
		String returnStr = Boolean.FALSE.toString();
		if (serverList.size() == 0) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		//不为空说明是修改
		if(StringUtils.isNotBlank(oper)){
			if(serverip.equals(oper)){
				return Boolean.TRUE.toString();
			}
		}
		return returnStr;
	}
	
	/***
	 * 判断ip是否重复 add by kouyunhao 2013-11-15
	 * @param req
	 * @param ip
	 * @return
	 */
	@RequestMapping(value = "isIpExist/{ip}/",method = RequestMethod.GET)
	public void isIpExist(@PathVariable String ip, ServletResponse response){
		List<ServerInfo> devList = serverInfoService.findByIp(ip);
		try {
			response.getWriter().write(devList.size()==0?"no":"yes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 获取详情信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}/{menuid}/", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		ServerInfo server = serverInfoService.getById(id);
		server.setFactoryName(factoryInfoService.getById(server.getFactoryId()).getName());
		server.setOrgNames(server.getOrgId() == null ? "" : departmentService.getById(server.getOrgId()).getName());
		model.addAttribute("server", server);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "property/serverinfo/view";
	}
	
	/**
	 * 监控设备变更处理
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doModify", method = RequestMethod.POST)
	@LogAspect(desc = "监控设备变更处理")
	public String doModify(ModifyRecord modifyRecord,String menuid, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String modifyTypeName = "维修";
		String ids = modifyRecord.getModifyId();
		String[] id = ids.split(",");
		for (String string : id) {
			//保存变更记录信息
			modifyRecord.setId(UuidGenerateUtil.getUUID());
			modifyRecord.setModuleName("server");
			modifyRecord.setModifyDatetime(DateUtil.stringFormatToDate(modifyRecord.getModifyDatetimeStr(), "yyyy-MM-dd"));
			modifyRecord.setOperator(getCurrentUserId());
			Date datetime = new Date();
			modifyRecord.setOperatorTime(new Date());
			modifyRecord.setVersion(DateUtil.dateFormatToString(datetime, "yyyyMMddHHmmss"));
			String modifyType = modifyRecord.getModifyType();
			if(modifyType.equals("2")){
				modifyTypeName = "移除";
			}else if(modifyType.equals("3")){
				modifyTypeName = "报废";
			}else if(modifyType.equals("4")){
				modifyTypeName = "变更";
			}
			modifyRecord.setModifyType(modifyTypeName);
			modifyRecord.setModifyId(string);
			modifyRecordService.save(modifyRecord);
			//将变更的记录COPY到历史表
			serverInfoService.saveHistoryRecord(string);
			
			AssetInfo asset = null;
			List<AssetInfo> assetList = assetInfoService.findByDeviceId(string);
			if(assetList != null && assetList.size() != 0){
				asset = assetList.get(0);
			}
			//修改变更的记录ID
			String afterRecordId = UuidGenerateUtil.getUUID();
			serverInfoService.updatePrimaryKey(afterRecordId, string);
			//同步修改资产信息
			if(asset != null){
				asset.setDeviceid(afterRecordId);
				assetInfoService.updateByIdSelective(asset);
			}
			//将修改后的记录ID同步更新到历史表字段
			serverInfoHisService.updateAfterRecordId(string, afterRecordId);
			//同步修改运维服务商责任资产设备ID
			List<FactoryContact> list = factoryContactService.findByDeviceId(string);
			for(FactoryContact factoryContact : list){
				factoryContact.setDeviceid(afterRecordId);
				factoryContactService.updateByIdSelective(factoryContact);
			}
		}
		redirectAttributes.addFlashAttribute("message", "服务器" + modifyTypeName + "操作成功");
		return "redirect:/property/serverinfo/list/" + menuid + "/";
	}	
	
	/***
	 * 服务器信息 导出
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="exportXls/{menuid}/",method=RequestMethod.GET)
	public void exportXls(@PathVariable String menuid,HttpServletResponse response,@RequestParam(value = "sortType", defaultValue = "NAME") String sortType,
			ServletRequest request){
		// 3 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(3);
		//接收前台参数
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		List<ServerInfo> list = serverInfoService.selectByExampleDiy(searchParams,sortType,menuid);
		
		// 设置内容
		List result = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			ServerInfo serverInfo = list.get(i);
			
			//封装数组
			Object[] values = new Object[] { 
				i+1, 	
				serverInfo.getName(),
				serverInfo.getCode(),
				serverInfo.getServerip(),
				serverInfo.getModel(),
				serverInfo.getType(),
				serverInfo.getCpuModel(),
				serverInfo.getRamVolume(),
				serverInfo.getDiskVolume(),
				serverInfo.getAddTime(),
			};
			result.add(values);
		}
		
		String nowtimeStr = DateUtil.dateFormatToString(new java.util.Date(), "yyyy年MM月dd日HH时mm分ss秒");
		export.setFileName("服务器信息一览表["+nowtimeStr+"].xls");
		export.exportExcelFileStream("xls" + File.separator + "serverInfo_exp.xls", null, result, response);
	}
}
