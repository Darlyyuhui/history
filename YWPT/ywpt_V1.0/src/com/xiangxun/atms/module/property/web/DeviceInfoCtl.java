package com.xiangxun.atms.module.property.web;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Table;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.deptment.vo.Department;
import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.common.dictionary.vo.KeyValue;
import com.xiangxun.atms.common.road.service.RoadInfoService;
import com.xiangxun.atms.common.road.vo.RoadInfo;
import com.xiangxun.atms.framework.base.ApplicationContextHolder;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimpleXlsExportor;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.ExcelImportValidator;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.monitor.conf.Dictionary;
import com.xiangxun.atms.framework.monitor.nio.EchoWorker;
import com.xiangxun.atms.framework.monitor.vo.DeviceStatus;
import com.xiangxun.atms.framework.security.OperatorDetails;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.FileUtils;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UploadFileUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;
import com.xiangxun.atms.module.gis.domain.LayerBean;
import com.xiangxun.atms.module.gis.service.IMapService;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.DeviceCompanyInfo;
import com.xiangxun.atms.module.property.domain.DeviceCompanyInfoSearch;
import com.xiangxun.atms.module.property.domain.DeviceDirectSpeed;
import com.xiangxun.atms.module.property.domain.DeviceFtpInfo;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.DeviceOuery;
import com.xiangxun.atms.module.property.domain.DeviceType;
import com.xiangxun.atms.module.property.domain.DeviceTypeInfo;
import com.xiangxun.atms.module.property.domain.DeviceTypeInfoSearch;
import com.xiangxun.atms.module.property.domain.DeviceTypeSearch;
import com.xiangxun.atms.module.property.domain.DeviceTypeSearch.Criteria;
import com.xiangxun.atms.module.property.domain.ModifyRecord;
import com.xiangxun.atms.module.property.domain.VideoAddressInfo;
import com.xiangxun.atms.module.property.domain.VideoInfo;
import com.xiangxun.atms.module.property.domain.VideoInfoSearch;
import com.xiangxun.atms.module.property.service.AssetInfoService;
import com.xiangxun.atms.module.property.service.DeviceCompanyInfoService;
import com.xiangxun.atms.module.property.service.DeviceDirectSpeedService;
import com.xiangxun.atms.module.property.service.DeviceFtpInfoService;
import com.xiangxun.atms.module.property.service.DeviceInfoHisService;
import com.xiangxun.atms.module.property.service.DeviceInfoService;
import com.xiangxun.atms.module.property.service.DeviceTypeInfoService;
import com.xiangxun.atms.module.property.service.DeviceTypeService;
import com.xiangxun.atms.module.property.service.ModifyRecordService;
import com.xiangxun.atms.module.property.service.VideoAddressService;
import com.xiangxun.atms.module.property.service.VideoInfoService;
import com.xiangxun.atms.module.property.service.impl.DevicesXlsImportService;
import com.xiangxun.atms.module.sergrade.domain.FactoryContact;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.service.FactoryContactService;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;
import com.xiangxun.atms.module.sergrade.web.FactoryInfoCtl;


/*******************************************************************************
 * 设备管理
 * 
 * @author yantao
 * @Apr 17, 2013 5:39:59 PM
 */

@Controller
@RequestMapping(value = "property/deviceinfo")
public class DeviceInfoCtl extends BaseCtl {
	private static final String IMGPATH = "upload_res/deviceImg";

	@Resource
	DeviceInfoService deviceInfoService;

	@Resource
	DeviceTypeInfoService deviceTypeInfoService;

	@Resource
	DepartmentService departmentService;

	@Resource
	DeviceCompanyInfoService deviceCompanyInfoService;
	
	@Resource 
	FactoryInfoService factoryInfoService;
	
	@Resource
	FactoryContactService factoryContactService;

	@Resource
	DeviceFtpInfoService deviceFtpInfoService;

	@Resource
	DicService dicService;

	@Resource
	DeviceDirectSpeedService deviceDirectSpeedService;

	@Resource
	DeviceTypeService deviceTypeService;

	@Resource
	RoadInfoService roadInfoService;

	@Resource
	VideoAddressService videoAddressService;
	
	@Resource
	VideoInfoService videoService;
	
	@Resource
	ModifyRecordService modifyRecordService;
	
	@Resource
	DeviceInfoHisService deviceInfoHisService;
	
	@Resource
	AssetInfoService assetInfoService;
	
	@Resource
	WorkorderInfoService workorderInfoService;
	
	@Resource
	IMapService mapService;
	
	@Resource
	Cache cache;
	
	@Resource
	DevicesXlsImportService devicesXlsImportService;

	private JsonArray deptJsonArray;

	private JsonArray devTypeJsonArray;
	
	boolean num = true;
	
	/***************************************************************************
	 * 设备信息列表页面
	 * 
	 * @param menuid
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable
	String menuid, ModelMap model,
			@RequestParam(value = "sortType", defaultValue = "NAME")
			String sortType, @RequestParam(value = "page", defaultValue = "0")
			int pageNumber, HttpServletRequest request) {

		logger.info("正在进行设备列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Map<String, Object> mapTemp = Servlets.getParametersStartingWith(request, "search_");
		
		//如果session中存在[视频信息]，则将session清空，防止session中有同名param，影响操作。
		Object menuinfoFlag = request.getSession().getAttribute("menuinfoFlag");
		if(menuinfoFlag != null){
			if(menuinfoFlag.equals("video")){
				request.getSession().removeAttribute("searchParams");
			}
		}
		//1 如果点击查询按钮，session里面存放的是选中的树节点（roadId）和新的查询条件，此时将旧的查询条件从session中remove；
		//2 如果没有点击查询按钮，session中存放的是以前的查询条件（选中的树节点（roadId）和旧的查询条件），不做删除。
		String sroadId = "";
		Object sessionParams = request.getSession().getAttribute("searchParams");
		if(sessionParams != null){
			Map<String, Object> paramsMap = (Map<String, Object>) sessionParams;
			if(request.getParameter("searchFlag") != null){
				if(request.getParameter("searchFlag").equals("1")){
					if(paramsMap.get("roadId") != null){
						sroadId = (String) paramsMap.get("roadId");
					}
					mapTemp.put("roadId", sroadId);
					searchParams.put("roadId", sroadId);
					
				}else if(request.getParameter("searchFlag").equals("2")){
					if(paramsMap.get("roadId") != null){
						sroadId = (String) paramsMap.get("roadId");
					}
					if(!sroadId.equals("")){
						paramsMap.remove("roadId");
						searchParams.putAll(paramsMap);
						mapTemp.putAll(paramsMap);
					}
				}
				request.getSession().removeAttribute("searchParams");
			}else{
				searchParams.putAll(paramsMap);
				mapTemp.putAll(paramsMap);
			}
		}

		//========================== add by kouyunhao 2013-12-10 点击道路分组，显示道路下的设备信息start===============//
		
		Object obj = searchParams.get("roadId");
		if(obj != null && !obj.equals("")){
			String roadid = (String)obj;
			if(roadid.equals("00")){
				searchParams.remove("roadId");
			}else{
				String parentid = roadInfoService.getById(roadid).getPid();
				if(parentid.equals("00")){
					searchParams.put("roadPid", roadid);
					searchParams.remove("roadId");
				}
			}
		}
		else{
			if(!sroadId.equals("")){
				if(sroadId.equals("00")){
					searchParams.remove("roadId");
				}else{
					String parentid = roadInfoService.getById(sroadId).getPid();
					if(parentid.equals("00")){
						searchParams.put("roadPid", sroadId);
						searchParams.remove("roadId");
					}
				}
			}
		}
		
		//========================== add by kouyunhao 2013-12-10 点击道路分组，显示道路下的设备信息end===============//
		
		//将查询条件放到session中
		request.getSession().setAttribute("searchParams", mapTemp);
		request.getSession().setAttribute("menuinfoFlag", "device");
		//设备名称
		String name = request.getParameter("name");
		//设备编号
		String code = request.getParameter("code");
		//建设厂家
		String companyId = request.getParameter("companyId");
		//设备类型
		String devicetypecode = request.getParameter("devicetypecode");
		//设备功能
		String deviceTypeIds = request.getParameter("deviceTypeIds");
		//所属部门
		String orgId = request.getParameter("orgId");
		//IP地址
		String ip = request.getParameter("ip");
		if (!"".equals(name) && null != name) {
			searchParams.put("name", name);
			request.getSession().setAttribute("name", name);
		}
		if (!"".equals(code) && null != code) {
			searchParams.put("code", code);
			request.getSession().setAttribute("code", code);
		}
		if (!"".equals(companyId) && null != companyId) {
			searchParams.put("companyId", companyId);	
			request.getSession().setAttribute("companyId", companyId);
		}
		if (!"".equals(devicetypecode) && null != devicetypecode) {
			searchParams.put("devicetypecode", devicetypecode);
			request.getSession().setAttribute("devicetypecode", devicetypecode);
		}
		if (!"".equals(deviceTypeIds) && null != deviceTypeIds) {
			searchParams.put("deviceTypeIds", deviceTypeIds);
			request.getSession().setAttribute("deviceTypeIds", deviceTypeIds);
		}
		if (!"".equals(orgId) && null != orgId) {
			searchParams.put("orgId", orgId);
			request.getSession().setAttribute("orgId", orgId);
		}
		if (!"".equals(ip) && null != ip) {
			searchParams.put("ip", ip);
			request.getSession().setAttribute("ip", ip);
		}
		/**
		 * add by kouyunhao 2014-09-29 添加按照创建时间（INSERTTIME）倒叙排列。
		 */
		searchParams.put("sortType", "INSERTTIME desc");
		Page page = deviceInfoService.getDeviceInfoByCondition(searchParams,pageNumber, Page.DEFAULT_PAGE_SIZE, sortType, menuid);

		if (!"".equals(name) && null != name) {
			request.getSession().setAttribute("name", name);
		}
		if (!"".equals(code) && null != code) {
			request.getSession().setAttribute("code", code);
		}
		if (!"".equals(companyId) && null != companyId) {
			request.getSession().setAttribute("companyId", companyId);
		}
		if (!"".equals(devicetypecode) && null != devicetypecode) {
			request.getSession().setAttribute("devicetypecode", devicetypecode);
		}
		if (!"".equals(deviceTypeIds) && null != deviceTypeIds) {
			request.getSession().setAttribute("deviceTypeIds", deviceTypeIds);
		}
		if (!"".equals(orgId) && null != orgId) {
			request.getSession().setAttribute("orgId", orgId);
		}
		if (!"".equals(ip) && null != ip) {
			request.getSession().setAttribute("ip", ip);
		}
		
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets
				.encodeParameterStringWithPrefix(mapTemp, "search_"));

		// 设备厂家 从缓存中获取
		Cache cache = (Cache) ApplicationContextHolder.getBean("ehcacheImpl");
		Table dciTable = (Table) cache.get("DeviceCompanyInfo");
		if (dciTable != null) {
			Map companyInfoMap = dciTable.column("DeviceCompanyInfo");
			model.addAttribute("companyInfoMap", companyInfoMap);
		}
		
		//资产类型
		request.getSession().setAttribute("assetstatusList", dicService.getDicByType(DicType.ASSETSTATUS));

		// 设备功能
		List<DeviceTypeInfo> deviceTypeInfoList = deviceTypeInfoService
				.getChildren("0");
		devTypeJsonArray = new JsonArray();
		JsonArray deviceTypejsonArray = getDeviceTypeJsonArray(deviceTypeInfoList);
		model.addAttribute("deviceTypejsonArray", deviceTypejsonArray);
//		getDeviceTypeNames(page, model);
		// 部门
		JsonArray departmentjsonArray = departmentService
				.getDeptJsonArray(menuid);
		model.addAttribute("departmentjsonArray", departmentjsonArray
				.toString());

		DeviceInfo deviceInfo = new DeviceInfo();
		try {
			BeanUtils.populate(deviceInfo, mapTemp);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("device", deviceInfo);
		
		if(!num){
			request.getSession().removeAttribute("searchParams");
			num = true;
		}
		
		return "property/deviceinfo/list";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "jsonlist/{menuid}/")
	@ResponseBody
	public List jsonlist(@PathVariable String menuid, HttpServletRequest request) {
		//设备名称
		String name = request.getParameter("name");
		//设备编号
		String code = request.getParameter("code");
		//建设厂家
		String companyId = request.getParameter("companyId");
		//设备类型
		String devicetypecode = request.getParameter("devicetypecode");
		//设备功能
		String deviceTypeIds = request.getParameter("deviceTypeIds");
		//所属部门
		String orgId = request.getParameter("orgId");
		//IP地址
		String ip = request.getParameter("ip");
		Map<String, Object> searchParams = new HashMap<String, Object>();
		if (!"".equals(name) && null != name) {
			searchParams.put("name", name);
			request.getSession().setAttribute("name", name);
		}
		if (!"".equals(code) && null != code) {
			searchParams.put("code", code);
			request.getSession().setAttribute("code", code);
		}
		if (!"".equals(companyId) && null != companyId) {
			searchParams.put("companyId", companyId);	
			request.getSession().setAttribute("companyId", companyId);
		}
		if (!"".equals(devicetypecode) && null != devicetypecode) {
			searchParams.put("devicetypecode", devicetypecode);
			request.getSession().setAttribute("devicetypecode", devicetypecode);
		}
		if (!"".equals(deviceTypeIds) && null != deviceTypeIds) {
			searchParams.put("deviceTypeIds", deviceTypeIds);
			request.getSession().setAttribute("deviceTypeIds", deviceTypeIds);
		}
		if (!"".equals(orgId) && null != orgId) {
			searchParams.put("orgId", orgId);
			request.getSession().setAttribute("orgId", orgId);
		}
		if (!"".equals(ip) && null != ip) {
			searchParams.put("ip", ip);
			request.getSession().setAttribute("ip", ip);
		}
		Page page = deviceInfoService.getDeviceInfoByCondition(searchParams, 0, 3000, "INSERTTIME desc", menuid);
		return page.getResult();
	}

	
		
	/*
	 * 
	 * 跳转到iframe上一级页面 add by kouyunhao 2013-07-18
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "sublist/{menuid}/")
	public String iframe_list(@PathVariable
	String menuid, ModelMap model,
			@RequestParam(value = "sortType", defaultValue = "NAME")
			String sortType, @RequestParam(value = "page", defaultValue = "0")
			int pageNumber, HttpServletRequest request) {
		num = false;
		this.list(menuid, model, sortType, pageNumber, request);
		
		return "property/deviceinfo/sublist";
	}

	/***************************************************************************
	 * 设备信息添加页面
	 * 
	 * @param id
	 * @param menuid
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "showadd/{menuid}/{roadid}/", method = RequestMethod.GET)
	public String showDeviceAdd(@PathVariable("menuid")
	String menuid, @PathVariable("roadid")
	String roadid, String page, Model model,RedirectAttributes redirectAttributes) {
		//========================== add by kouyunhao 2013-08-12 添加验证，阻止一级道路下再添加设备信息start===============//
		RoadInfo roadInfo = roadInfoService.getById(roadid);
		String parentid = roadInfo.getPid();
		if(parentid.equals("00")){
			redirectAttributes.addFlashAttribute("message","一级道路下不允许添加设备信息");
			return "redirect:/property/deviceinfo/sublist/"+menuid+"/";
		}
		//========================== add by kouyunhao 2013-08-12 添加验证，阻止一级道路下再添加设备信息end===============//
		// 设备厂家
		List<DeviceCompanyInfo> companyList = deviceCompanyInfoService
				.findAll();
		model.addAttribute("companyList", companyList);
		
		// 服务厂商
		List<FactoryInfo> factoryList = factoryInfoService
				.findAll();
		model.addAttribute("factoryList", factoryList);

		// 设备类型
		List<DeviceTypeInfo> deviceTypeInfoList = deviceTypeInfoService
				.getChildren("0");
		devTypeJsonArray = new JsonArray();
		JsonArray deviceTypejsonArray = getDeviceTypeJsonArray(deviceTypeInfoList);
		model.addAttribute("deviceTypejsonArray", deviceTypejsonArray);

		// 设备类型<数据字典>
		List<Dic> deviceTypes = dicService.getDicByType(DicType.DEVICE_TYPE);
		model.addAttribute("deviceTypeDic", deviceTypes);

		// 组织机构
		JsonArray departmentjsonArray = departmentService
				.getDeptJsonArray(menuid);
		model.addAttribute("departmentjsonArray", departmentjsonArray
				.toString());
		//所属部门下拉列表数据构造 add by kouyunhao 2014-02-12
		List<Department> departmentList = departmentService.findAllBesidesRoot();
		model.addAttribute("departmentList", departmentList);

		// 默认建设时间
		String nowdate = DateUtil.dateFormatToString(new java.util.Date(),
				"yyyy-MM-dd");
		model.addAttribute("nowdate", nowdate);

		// 从字典获得 方向信息
		List<Dic> directList = dicService.getDicByType(DicType.DIRECT);
		model.addAttribute("directList", directList);

		// 从字典获得 网络运营商
		List<Dic> netcompanyList = dicService.getDicByType(DicType.NET_COMPANY);
		model.addAttribute("netcompanyList", netcompanyList);

		// 从字典获得 接入方式
		List<Dic> nettypeList = dicService.getDicByType(DicType.NET_IN_TYPE);
		model.addAttribute("nettypeList", nettypeList);


		// 生成设备注册序列号
		DeviceInfo deviceInfo = new DeviceInfo();
		Random r = new Random();
		int nums = r.nextInt(899999999) + 100000000;
		String licenseStr = String.valueOf(nums);
		deviceInfo
				.setLicense(licenseStr.substring(0, 3) + "-"
						+ licenseStr.substring(3, 6) + "-"
						+ licenseStr.substring(6, 9));
		
		
		model.addAttribute("deviceInfo", deviceInfo);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		model.addAttribute("roadId", roadid);
		model.addAttribute("roadInfo", roadInfo);
		return "property/deviceinfo/add";
	}

	
	/***************************************************************************
	 * 获取详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}/{menuid}/{roadId}", method = RequestMethod.GET)
	public String getDeviceInfo(@PathVariable("id")
	String id, @PathVariable("menuid")
	String menuid, @PathVariable("roadId")
	String roadId, String page, Model model) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		model.addAttribute("roadId", roadId);

		DeviceInfo deviceInfo = deviceInfoService.getById(id);
		Date date = deviceInfo.getBuildtime();
		if (date != null) {
			deviceInfo.setBuildtimeStr(DateUtil.dateFormatToString(date,
					"yyyy-MM-dd"));
		}

		// 获得方向速度配置
		List<DeviceDirectSpeed> ddsList = deviceDirectSpeedService
				.getDeviceDirectSpeedByDeviceCode(deviceInfo.getCode());
		if (ddsList != null && ddsList.size() > 0) {
			for (DeviceDirectSpeed dds : ddsList) {
				String directionCode = dds.getDirectionCode();
				if ("01".equals(directionCode)) {
					deviceInfo.setDirectionCode_01(directionCode);
					deviceInfo.setLandtotal_01(dds.getLandtotal());
					deviceInfo.setCarlimitspeed_01(dds.getCarlimitspeed());
					deviceInfo.setCarlimitspeedcatch_01(dds
							.getCarlimitspeedcatch());
					deviceInfo
							.setBigcarlimitspeed_01(dds.getBigcarlimitspeed());
					deviceInfo.setBigcarlimitspeedcatch_01(dds
							.getBigcarlimitspeedcatch());
				}
				if ("02".equals(directionCode)) {
					deviceInfo.setDirectionCode_02(directionCode);
					deviceInfo.setLandtotal_02(dds.getLandtotal());
					deviceInfo.setCarlimitspeed_02(dds.getCarlimitspeed());
					deviceInfo.setCarlimitspeedcatch_02(dds
							.getCarlimitspeedcatch());
					deviceInfo
							.setBigcarlimitspeed_02(dds.getBigcarlimitspeed());
					deviceInfo.setBigcarlimitspeedcatch_02(dds
							.getBigcarlimitspeedcatch());
				}
				if ("03".equals(directionCode)) {
					deviceInfo.setDirectionCode_03(directionCode);
					deviceInfo.setLandtotal_03(dds.getLandtotal());
					deviceInfo.setCarlimitspeed_03(dds.getCarlimitspeed());
					deviceInfo.setCarlimitspeedcatch_03(dds
							.getCarlimitspeedcatch());
					deviceInfo
							.setBigcarlimitspeed_03(dds.getBigcarlimitspeed());
					deviceInfo.setBigcarlimitspeedcatch_03(dds
							.getBigcarlimitspeedcatch());
				}
				if ("04".equals(directionCode)) {
					deviceInfo.setDirectionCode_04(directionCode);
					deviceInfo.setLandtotal_04(dds.getLandtotal());
					deviceInfo.setCarlimitspeed_04(dds.getCarlimitspeed());
					deviceInfo.setCarlimitspeedcatch_04(dds
							.getCarlimitspeedcatch());
					deviceInfo
							.setBigcarlimitspeed_04(dds.getBigcarlimitspeed());
					deviceInfo.setBigcarlimitspeedcatch_04(dds
							.getBigcarlimitspeedcatch());
				}
			}
		}
		model.addAttribute("deviceInfo", deviceInfo);
		//所在道路信息
		RoadInfo roadInfo = roadInfoService.getById(roadId);
		model.addAttribute("roadInfo", roadInfo);

		// 获取设备关联的 设备类型 所属部门
		StringBuffer dtNameStrs = new StringBuffer("");
		StringBuffer dtTypeStrs = new StringBuffer("");
		List<DeviceTypeInfo> dtList = deviceTypeService.getTypeByDeviceId(id);

		if (dtList != null && dtList.size() > 0) {
			for (int i = 0; i < dtList.size(); i++) {
				DeviceTypeInfo typeInfo = dtList.get(i);
				dtNameStrs.append(typeInfo.getName());
				dtTypeStrs.append(typeInfo.getId());
				if (i < dtList.size() - 1) {
					dtNameStrs.append(",");
					dtTypeStrs.append(",");
				}

			}
		}

		//add by kouyunhao 2013-12-17 获取设备关联的视频监控信息
		String videoCode = deviceInfo.getVideoCode();
		if(videoCode != null){
			List<VideoInfo> videoInfoList = videoService.findByCode(videoCode);
			if(videoInfoList != null && videoInfoList.size() != 0){
				VideoInfo videoInfo = videoInfoList.get(0);
				//播放配置模板信息
				if(videoInfo.getUsetemplate().equals("1")){
					VideoAddressInfo realvideoaddress = videoAddressService.getById(videoInfo.getRealvideoaddressid());
					if(realvideoaddress != null){
						videoInfo.setRealvideoaddressName(realvideoaddress.getName() + "[" +realvideoaddress.getIp()+ "]");
					}
					VideoAddressInfo hisvideoaddress = videoAddressService.getById(videoInfo.getHisvideoaddressid());
					if(hisvideoaddress != null){
						videoInfo.setHisvideoaddressName(hisvideoaddress.getName() + "[" +hisvideoaddress.getIp()+ "]");
					}
				}
				model.addAttribute("videoInfo", videoInfo);
			}
		}
		model.addAttribute("dtNameStrs", dtNameStrs);
		model.addAttribute("dtTypeStrs", dtTypeStrs);

		return "property/deviceinfo/view";
	}

	/***************************************************************************
	 * 获取待修改
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/{roadId}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, @PathVariable("menuid") String menuid, @PathVariable("roadId") String roadId, 
			String page, Model model, HttpServletRequest request) {

		// 设备厂家
		List<DeviceCompanyInfo> companyList = deviceCompanyInfoService.findAll();
		model.addAttribute("companyList", companyList);
		// 服务厂商
		List<FactoryInfo> factoryList = factoryInfoService.findAll();
		model.addAttribute("factoryList", factoryList);

		// 设备类型
		List<DeviceTypeInfo> deviceTypeInfoList = deviceTypeInfoService.getChildren("0");
		devTypeJsonArray = new JsonArray();
		JsonArray deviceTypejsonArray = getDeviceTypeJsonArray(deviceTypeInfoList);
		model.addAttribute("deviceTypejsonArray", deviceTypejsonArray);

		// 设备类型<数据字典>
		List<Dic> deviceTypes = dicService.getDicByType(DicType.DEVICE_TYPE);
		model.addAttribute("deviceTypeDic", deviceTypes);

		// 部门
		JsonArray departmentjsonArray = departmentService
				.getDeptJsonArray(menuid);
		model.addAttribute("departmentjsonArray", departmentjsonArray
				.toString());


		// 默认建设时间
		String nowdate = DateUtil.dateFormatToString(new java.util.Date(),
				"yyyy-MM-dd");
		model.addAttribute("nowdate", nowdate);

		// 从字典获得 方向信息 网络运营商 接入方式
		List<Dic> dicList = dicService.getDicByType(DicType.DIRECT);
		model.addAttribute("dicList", dicList);

		List<Dic> netcompanyList = dicService.getDicByType(DicType.NET_COMPANY);
		model.addAttribute("netcompanyList", netcompanyList);

		List<Dic> nettypeList = dicService.getDicByType(DicType.NET_IN_TYPE);
		model.addAttribute("nettypeList", nettypeList);

		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		model.addAttribute("roadId", roadId);
		model.addAttribute("roadInfo",roadInfoService.getById(roadId));
		
		//所属部门下拉列表数据构造 add by kouyunhao 2014-02-12
		List<Department> departmentList = departmentService.findAllBesidesRoot();
		model.addAttribute("departmentList", departmentList);

		DeviceInfo deviceInfo = deviceInfoService.getById(id);
		String orgId = deviceInfo.getOrgId();
		Department department = departmentService.getById(orgId);

		model.addAttribute("deviceInfo", deviceInfo);
		model.addAttribute("department", department);

		// 获取设备关联的 设备类型 所属部门
		StringBuffer dtNameStrs = new StringBuffer("");
		StringBuffer dtTypeStrs = new StringBuffer("");
		List<DeviceTypeInfo> dtList = deviceTypeService.getTypeByDeviceId(id);

		if (dtList != null && dtList.size() > 0) {
			for (int i = 0; i < dtList.size(); i++) {
				DeviceTypeInfo typeInfo = dtList.get(i);
				dtNameStrs.append(typeInfo.getName());
				dtTypeStrs.append(typeInfo.getId());
				if (i < dtList.size() - 1) {
					dtNameStrs.append(",");
					dtTypeStrs.append(",");
				}

			}
		}
		//判断是否已经转为资产
		String isasset = "0";
		List<AssetInfo> assetList = assetInfoService.findByDeviceId(id);
		if(assetList != null && assetList.size() != 0){
			isasset = "1";
			model.addAttribute("asset", assetList.get(0));
		}
		model.addAttribute("isasset", isasset);
		
		model.addAttribute("dtNameStrs", dtNameStrs);
		model.addAttribute("dtTypeStrs", dtTypeStrs);
		return "property/deviceinfo/update";
	}
	
	
	
	/***************************************************************************
	 * 添加一个设备信息，并上传设备图片
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "doAddImg", method = RequestMethod.POST)
	// @LogAspect(desc = "添加一个设备信息")
	public String doAddImg(DeviceInfo deviceInfo, AssetInfo asset, String menuid,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			@RequestParam("file1")
			CommonsMultipartFile file1, @RequestParam("file2")
			CommonsMultipartFile file2, @RequestParam("file3")
			CommonsMultipartFile file3) throws ParseException {
		
		// 设备主键
		deviceInfo.setId(UuidGenerateUtil.getUUID());
		// 建设时间
		String buildTimeStr = deviceInfo.getBuildtimeStr();
		deviceInfo.setBuildtime(DateUtil.stringFormatToDate(buildTimeStr,"yyyy-MM-dd"));
		// 设备功能（多个，以,分割）
		String deviceTypeids = deviceInfo.getDeviceTypeIds();
		String[] dtarray = deviceTypeids.split(",");

		//验证设备编号是否重复
		String deviceCode = deviceInfo.getCode();
		int n = deviceInfoService.countDeviceByCode(deviceCode);
		if(n>0){
			redirectAttributes.addFlashAttribute("message", "设备编号重复，分析到异常数据，请联系管理员！");
			return "redirect:/property/deviceinfo/sublist/" + menuid + "/";
		}

		if (dtarray != null && dtarray.length > 0) {
			// 保存关联关系
			for (String typeid : dtarray) {
				DeviceType deviceType = new DeviceType();
				deviceType.setDeviceId(deviceInfo.getId());
				deviceType.setDevicetypeId(typeid);
				deviceTypeService.save(deviceType);
			}
		}

		// 获取上传设备照片路径
		String mkdir = request.getSession().getServletContext().getRealPath("/") + File.separator + IMGPATH;
		File file = null;
		// 上传设备图片1
		if (file1.getSize() != 0) {
			com.xiangxun.atms.framework.util.FileUtils.mkDirectory(mkdir);
			String file1Name = file1.getOriginalFilename();
			String fileType = file1Name.substring(file1Name.lastIndexOf("."));
			String uploadFileName = getImgName("01", fileType, deviceInfo
					.getCode());
			file = FileUtils.getCreateFile(mkdir, uploadFileName);
			if (org.apache.commons.lang.StringUtils.isNotEmpty(file
					.getAbsolutePath())) {
				UploadFileUtils.up(file1, file.getAbsolutePath());
			}
		}
		deviceInfo.setPhoto1(file1.getSize() == 0 ? "" : IMGPATH
				+ File.separator + file.getName());
		File filePoho2 = null;
		// 上传设备图片2
		if (file2.getSize() != 0) {
			String file2Name = file2.getOriginalFilename();
			String fileType = file2Name.substring(file2Name.lastIndexOf("."));
			String uploadFileName = getImgName("02", fileType, deviceInfo
					.getCode());
			filePoho2 = FileUtils.getCreateFile(mkdir, uploadFileName);
			if (org.apache.commons.lang.StringUtils.isNotEmpty(filePoho2
					.getAbsolutePath())) {
				UploadFileUtils.up(file2, filePoho2.getAbsolutePath());
			}
		}
		deviceInfo.setPhoto2(file2.getSize() == 0 ? "" : IMGPATH
				+ File.separator + filePoho2.getName());
		File filePoho3 = null;
		// 上传设备图片3
		if (file3.getSize() != 0) {
			String file3Name = file3.getOriginalFilename();
			String fileType = file3Name.substring(file3Name.lastIndexOf("."));
			String uploadFileName = getImgName("03", fileType, deviceInfo
					.getCode());
			filePoho3 = FileUtils.getCreateFile(mkdir, uploadFileName);
			if (org.apache.commons.lang.StringUtils.isNotEmpty(filePoho3
					.getAbsolutePath())) {
				UploadFileUtils.up(file3, filePoho3.getAbsolutePath());
			}
		}
		deviceInfo.setPhoto3(file3.getSize() == 0 ? "" : IMGPATH
				+ File.separator + filePoho3.getName());
		deviceInfo.setInserttime(new Date());
		deviceInfoService.save(deviceInfo);

		// 获得道路信息
		RoadInfo roadinfo = roadInfoService.getById(deviceInfo.getRoadId());
		// 获得设备类型
		String code = deviceInfo.getCode();
		
		LayerBean t = new LayerBean();
		t.setType("卡口设备");
		t.setName(deviceInfo.getName());
		t.setCode(code);
		if(roadinfo != null){
			t.setRoadId(roadinfo.getId());
			t.setRoadName(roadinfo.getName());
		}
		// 获得经纬度
		String mapy = request.getParameter("mapy");
		String mapx = request.getParameter("mapx");
		
		//添加非空判断  yantao add
		if(mapy!=null && !"".equals(mapy) && mapx!=null && !"".equals(mapx)){
			t.setGeometry("{\"type\":\"point\",\"points\":\""+mapx+","+mapy+"\"}");
			//mapService.add(LayerEnum.CROSS, t);
		}
		
		//判断是否加入监控
		String toasset = asset.getToasset();
		if(toasset.equals("1")){
			asset.setId(UuidGenerateUtil.getUUID());
			asset.setAssettype("device");
			asset.setDeviceid(deviceInfo.getId());
			asset.setAssetname(deviceInfo.getName());
			asset.setGuaranteetime(DateUtil.parseDate(asset.getGuaranteetimestr(), "yyyy-MM-dd"));
			asset.setPurchasetime(DateUtil.parseDate(asset.getPurchasetimestr(), "yyyy-MM-dd"));
			asset.setInstalltime(DateUtil.parseDate(asset.getInstalltimestr(), "yyyy-MM-dd HH:mm:ss"));
			//服务商ID
			asset.setFactoryId(deviceInfo.getFactoryId());
			assetInfoService.save(asset);
		}
		redirectAttributes.addFlashAttribute("message", "设备信息添加成功");
		
		EchoWorker.listDeviceStatus.clear();

		return "redirect:/property/deviceinfo/sublist/" + menuid + "/";

	}
	
	
	/***************************************************************************
	 * 卡口设备信息修改 方法
	 * @param deviceInfo
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "doUpdateImg", method = RequestMethod.POST)
	public String doUpdateImg(@ModelAttribute("preloadRole")
	DeviceInfo deviceInfo, AssetInfo asset, String page, String menuid,
			RedirectAttributes redirectAttributes, @RequestParam("file1")
			CommonsMultipartFile file1, @RequestParam("file2")
			CommonsMultipartFile file2, @RequestParam("file3")
			CommonsMultipartFile file3, String img1path, String img2path,
			String img3path, HttpServletRequest request) throws ParseException {

		String ismodify = request.getParameter("ismodify");
		if(ismodify.equals("1")){
			String modifyId = deviceInfo.getId();
			//保存变更记录信息
			ModifyRecord modifyRecord = new ModifyRecord();
			modifyRecord.setId(UuidGenerateUtil.getUUID());
			modifyRecord.setModifyId(modifyId);
			modifyRecord.setModuleName("device");
			modifyRecord.setOperator(getCurrentUserId());
			Date datetime = new Date();
			modifyRecord.setOperatorTime(datetime);
			modifyRecord.setVersion(DateUtil.dateFormatToString(datetime, "yyyyMMddHHmmss"));
			modifyRecord.setModifyType("变更");
			modifyRecordService.save(modifyRecord);
			//将变更的记录COPY到历史表
			deviceInfoService.saveHistoryRecord(modifyId);
			
			AssetInfo assetinfo = null;
			List<AssetInfo> assetList = assetInfoService.findByDeviceId(modifyId);
			if(assetList != null && assetList.size() != 0){
				assetinfo = assetList.get(0);
			}
			//修改变更的记录ID
			String afterRecordId = UuidGenerateUtil.getUUID();
			deviceInfoService.updatePrimaryKey(afterRecordId, modifyId);
			
			//同步修改资产信息
			if(assetinfo != null){
				assetinfo.setDeviceid(afterRecordId);
				assetInfoService.updateByIdSelective(assetinfo);
			}
			//将修改后的记录ID同步更新到历史表字段
			deviceInfoHisService.updateAfterRecordId(modifyId, afterRecordId);
			//同步修改设备功能
			String sql1 = 
					"insert into property_device_type_temp (device_id, devicetype_id) "+
					"select device_id, devicetype_id from property_device_type where device_id=" + "'"+ modifyId + "'";
			String sql2 =
					"update property_device_type_temp t set device_id=" + "'"+ afterRecordId + "'" + "where device_id=" + "'"+ modifyId + "'";
			String sql3 =
					"insert into property_device_type (device_id, devicetype_id) "+
					"select device_id, devicetype_id from property_device_type_temp where device_id=" + "'"+ afterRecordId + "'";
			String sql4 = "delete property_device_type_temp";
			deviceInfoService.updateDeviceType(sql1, sql2, sql3, sql4);
			//同步修改运维服务商责任资产设备ID
			List<FactoryContact> list = factoryContactService.findByDeviceId(modifyId);
			for(FactoryContact factoryContact : list){
				factoryContact.setDeviceid(afterRecordId);
				factoryContactService.updateByIdSelective(factoryContact);
			}
			//设置待修改的设备ID为变更后的ID
			deviceInfo.setId(afterRecordId);
		}
		
		// 先清除关联
		DeviceTypeSearch dts = new DeviceTypeSearch();
		Criteria criteria = dts.createCriteria();
		criteria.andDeviceIdEqualTo(deviceInfo.getId());
		deviceTypeService.deleteByExample(dts);
		// 修改关联关系
		String dtStrs = deviceInfo.getDeviceTypeIds();
		String[] dtarray = dtStrs.split(",");
		if (dtarray != null && dtarray.length > 0) {
			// 保存关联关系
			for (String typeid : dtarray) {
				DeviceType deviceType = new DeviceType();
				deviceType.setDeviceId(deviceInfo.getId());
				deviceType.setDevicetypeId(typeid);
				deviceTypeService.save(deviceType);
			}
		}

		
		// 处理图片，把用户删除的图片删掉，新上传的增加
		String mkdir = request.getSession().getServletContext().getRealPath("/") + IMGPATH;
		String img1 = getPhotoPath(mkdir, deviceInfo.getCode(), deviceInfo.getPhoto1(), file1, img1path, "01");
		String img2 = getPhotoPath(mkdir, deviceInfo.getCode(), deviceInfo.getPhoto2(), file2, img2path, "02");
		String img3 = getPhotoPath(mkdir, deviceInfo.getCode(), deviceInfo.getPhoto3(), file3, img3path, "03");
		deviceInfo.setPhoto1(img1);
		deviceInfo.setPhoto2(img2);
		deviceInfo.setPhoto3(img3);
		
		String assetid = request.getParameter("assetid");
		//要修改的内容本身是否已经转入资产
		String isasset = request.getParameter("isasset");
		//新的操作：是否转入资产
		String toasset = asset.getToasset();
		//服务商ID
		asset.setAssetname(deviceInfo.getName());
		asset.setGuaranteetime(DateUtil.parseDate(asset.getGuaranteetimestr(), "yyyy-MM-dd"));
		asset.setPurchasetime(DateUtil.parseDate(asset.getPurchasetimestr(), "yyyy-MM-dd"));
		//asset.setAssetstatus("001");	
		asset.setInstalltime(DateUtil.parseDate(asset.getInstalltimestr(), "yyyy-MM-dd HH:mm:ss"));
		asset.setFactoryId(deviceInfo.getFactoryId());
		asset.setInstallplace(deviceInfo.getRoadName());

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
				asset.setAssettype("device");
				asset.setAssetstatus("001");	
				asset.setDeviceid(deviceInfo.getId());
				asset.setAssetname(deviceInfo.getName());
				asset.setGuaranteetime(DateUtil.parseDate(asset.getGuaranteetimestr(), "yyyy-MM-dd"));
				asset.setPurchasetime(DateUtil.parseDate(asset.getPurchasetimestr(), "yyyy-MM-dd"));
				asset.setInstalltime(DateUtil.parseDate(asset.getInstalltimestr(), "yyyy-MM-dd HH:mm:ss"));
				asset.setFactoryId(deviceInfo.getFactoryId());
				RoadInfo roadinfo = roadInfoService.getById(deviceInfo.getRoadId());
				asset.setInstallplace(roadinfo.getName());
				assetInfoService.save(asset);
			}
		}
		
		String message = "修改成功";
		deviceInfoService.updateByIdSelective(deviceInfo);
		if(ismodify.equals("1")){
			message = "卡口设备变更操作成功";
		}
		redirectAttributes.addFlashAttribute("message", message);
		
		// 获得道路信息
		RoadInfo roadinfo = roadInfoService.getById(deviceInfo.getRoadId());
		// 获得设备类型
		LayerBean t = new LayerBean();
		t.setType("卡口设备");
		t.setCode(deviceInfo.getCode());
		t.setName(deviceInfo.getName());
		if(roadinfo != null){
			t.setRoadId(roadinfo.getId());
			t.setRoadName(roadinfo.getName());
		}
		// 获得经纬度
		String mapy = request.getParameter("mapy");
		String mapx = request.getParameter("mapx");
		
		//添加非空判断  yantao add
		if(mapy!=null && !"".equals(mapy) && mapx!=null && !"".equals(mapx)){
			t.setGeometry("{\"type\":\"point\",\"points\":\""+mapx+","+mapy+"\"}");
			//mapService.save(LayerEnum.CROSS, t);
		}
		EchoWorker.listDeviceStatus.clear();

		return "redirect:/property/deviceinfo/sublist/" + menuid + "/?&page=" + page;
	}

	/***************************************************************************
	 * 删除一个
	 * 
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value = "delete/{ids}/", method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc = "删除了一个设备")
	public ResponseEntity delete(@PathVariable
	String ids, HttpServletRequest request) {
		ResponseEntity entity = new ResponseEntity();
		entity.setResult("ok");
		try {
			logger.info("正在删除了一个设备。。。。。。");
//			boolean deleteFlag = true;
			String[] id = ids.split(",");
//			for (String string : id) {
//				boolean hasModified = deviceInfoHisService.hasRecordModified(string);
//				if(hasModified){
//					deleteFlag = false;
//				}
//			}
//			if(!deleteFlag){
//				entity.setResult("can't");
//				return entity;
//			}
			
			for (int i = 0; i < id.length; i++) {

				DeviceInfo d = deviceInfoService.getById(id[i]);
//				int results  = vioAreaService.getVioAreasByDeviceCode(d.getCode());
//				if(results>0){
//					entity.setResult("error");
//					entity.setMessage("该设备已被区间测速功能所引用，无法删除。");
//					break;
//				}
//				
//				//同步删除资产信息
//				List<AssetInfo> assetList = assetInfoService.findByDeviceId(string);
//				if(assetList != null && assetList.size() != 0){
//					AssetInfo asset = assetList.get(0);
//					assetInfoService.deleteById(asset.getId());
//				}
				
				//同步删除资产信息
				assetInfoService.deleteAssetByDeviceId(id[i]);
				//同步删除服务商分配信息
				assetInfoService.deleteFactoryContactByDeviceId(id[i]);
				//同步删除更改记录
				assetInfoService.deleteModifyByCode(d.getCode());
				//同步删除卡口历史信息
				assetInfoService.deleteDeviceHisByCode(d.getCode());
				//同步删除设备日志信息
				assetInfoService.deleteDeviceLogByCode(d.getCode());
				//同步删除工单信息
				List<WorkorderInfo> workorderList = workorderInfoService.findByDeviceCode(d.getCode());
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
				assetInfoService.deleteWorkorderByCode(d.getCode());
				
				//=======1======== 同步删除设备关联的视频监控信息  add by kouyunhao 2013-12-17  ==============
				String videoCode = d.getVideoCode();
				if(videoCode != null){
					//========================= edit by kouyunhao 2014-03-28 修改视频删除方法，解决删除设备信息无法同步删除视频设备信息问题。========start//
					//videoService.deleteById(videoid);
					VideoInfoSearch example = new VideoInfoSearch();
					example.createCriteria().andCodeEqualTo(videoCode);
					videoService.deleteByExample(example);
				}
				deviceInfoService.deleteById(id[i],d);
				
				// 删除地图中的卡口设备
				//mapService.deleteByCode(LayerEnum.CROSS, d.getCode());
				
				//=====3======同步删除设备对应的文件========================
				String mkdir = request.getSession().getServletContext().getRealPath("/")+ IMGPATH;
				if (d != null) {
					if (StringUtils.isNotEmpty(d.getPhoto1())) {
						String file1Path = d.getPhoto1().replace(IMGPATH, "");
						FileUtils.delFile(mkdir + file1Path);
					}
					if (StringUtils.isNotEmpty(d.getPhoto2())) {
						String file2Path = d.getPhoto2().replace(IMGPATH, "");
						FileUtils.delFile(mkdir + file2Path);
					}
					if (StringUtils.isNotEmpty(d.getPhoto3())) {
						String file3Path = d.getPhoto3().replace(IMGPATH, "");
						FileUtils.delFile(mkdir + file3Path);
					}

				}
				
				//=====4======同步删除设备对应的已派发工单========================
//				if (d != null) {
//					DeviceMaintainInfoSearch infoSearch = new DeviceMaintainInfoSearch();
//					infoSearch.createCriteria().andDevicecodeEqualTo(d.getCode());
//					deviceMaintainService.deleteByExample(infoSearch);
//				}
				
				//=====5=====同步删除设备功能关联表===================
				DeviceTypeSearch dts = new DeviceTypeSearch();
				dts.createCriteria().andDeviceIdEqualTo(id[i]);
				deviceTypeService.deleteByExample(dts);
				
				//====6=====同步删除设备方向和限速信息关联表============
				deviceDirectSpeedService.deleteByDeviceCode(d.getCode());
				
			
			}
			
			/*for (String string : id) {
				DeviceInfo d = deviceInfoService.getById(string);
//				int results  = vioAreaService.getVioAreasByDeviceCode(d.getCode());
//				if(results>0){
//					entity.setResult("error");
//					entity.setMessage("该设备已被区间测速功能所引用，无法删除。");
//					break;
//				}
//				
//				//同步删除资产信息
//				List<AssetInfo> assetList = assetInfoService.findByDeviceId(string);
//				if(assetList != null && assetList.size() != 0){
//					AssetInfo asset = assetList.get(0);
//					assetInfoService.deleteById(asset.getId());
//				}
				
				//同步删除资产信息
				assetInfoService.deleteAssetByDeviceId(string);
				//同步删除服务商分配信息
				assetInfoService.deleteFactoryContactByDeviceId(string);
				//同步删除更改记录
				assetInfoService.deleteModifyByCode(d.getCode());
				//同步删除卡口历史信息
				assetInfoService.deleteDeviceHisByCode(d.getCode());
				//同步删除设备日志信息
				assetInfoService.deleteDeviceLogByCode(d.getCode());
				//同步删除工单信息
				List<WorkorderInfo> workorderList = workorderInfoService.findByDeviceCode(d.getCode());
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
				assetInfoService.deleteWorkorderByCode(d.getCode());
				
				//=======1======== 同步删除设备关联的视频监控信息  add by kouyunhao 2013-12-17  ==============
				String videoCode = d.getVideoCode();
				if(videoCode != null){
					//========================= edit by kouyunhao 2014-03-28 修改视频删除方法，解决删除设备信息无法同步删除视频设备信息问题。========start//
					//videoService.deleteById(videoid);
					VideoInfoSearch example = new VideoInfoSearch();
					example.createCriteria().andCodeEqualTo(videoCode);
					videoService.deleteByExample(example);
				}
				deviceInfoService.deleteById(string,d);
				
				// 删除地图中的卡口设备
				//mapService.deleteByCode(LayerEnum.CROSS, d.getCode());
				
				//=====3======同步删除设备对应的文件========================
				String mkdir = request.getSession().getServletContext().getRealPath("/")+ IMGPATH;
				if (d != null) {
					if (StringUtils.isNotEmpty(d.getPhoto1())) {
						String file1Path = d.getPhoto1().replace(IMGPATH, "");
						FileUtils.delFile(mkdir + file1Path);
					}
					if (StringUtils.isNotEmpty(d.getPhoto2())) {
						String file2Path = d.getPhoto2().replace(IMGPATH, "");
						FileUtils.delFile(mkdir + file2Path);
					}
					if (StringUtils.isNotEmpty(d.getPhoto3())) {
						String file3Path = d.getPhoto3().replace(IMGPATH, "");
						FileUtils.delFile(mkdir + file3Path);
					}

				}
				
				//=====4======同步删除设备对应的已派发工单========================
//				if (d != null) {
//					DeviceMaintainInfoSearch infoSearch = new DeviceMaintainInfoSearch();
//					infoSearch.createCriteria().andDevicecodeEqualTo(d.getCode());
//					deviceMaintainService.deleteByExample(infoSearch);
//				}
				
				//=====5=====同步删除设备功能关联表===================
				DeviceTypeSearch dts = new DeviceTypeSearch();
				dts.createCriteria().andDeviceIdEqualTo(string);
				deviceTypeService.deleteByExample(dts);
				
				//====6=====同步删除设备方向和限速信息关联表============
				deviceDirectSpeedService.deleteByDeviceCode(d.getCode());
				
			}*/
			//解决:将资产中的设备删除后,告警模块仍然有此设备的告警提示  此问题
			EchoWorker.listDeviceStatus.clear();
			
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			entity.setMessage("删除失败。");
			e.printStackTrace();
			return entity;
		}
	}
	
	
	
	// =========== 带视频监控的设备 相关操作方法 =============
	
	
	/**
	 * 视频监控信息添加
	 * @author kouyunhao
	 * @version 1.0
	 * @param deviceInfo
	 * @return
	 * Dec 17, 2013
	 */
	public String saveVideoInfo(DeviceInfo deviceInfo){
		VideoInfo videoInfo = new VideoInfo();
		videoInfo.setId(UuidGenerateUtil.getUUID());
		videoInfo.setDirectionCode(deviceInfo.getDirectionCode());
		videoInfo.setVideotypeCode(deviceInfo.getVideotypeCode());
		videoInfo.setRoadinfoId(deviceInfo.getRoadId());
		videoInfo.setName(deviceInfo.getName());
		videoInfo.setOrgId(deviceInfo.getOrgId());
		videoInfo.setDeviceIp(deviceInfo.getIp());
		if("1".equals(deviceInfo.getUsetemplate())){
			videoInfo.setUsetemplate("1");
			videoInfo.setRealvideoaddressid(deviceInfo.getRealvideoaddressid());
			videoInfo.setHisvideoaddressid(deviceInfo.getHisvideoaddressid());
		}else{
			videoInfo.setUsetemplate("0");
			videoInfo.setRealvideoaddressid(null);
			videoInfo.setHisvideoaddressid(null);
			videoInfo.setIp(deviceInfo.getIps());
			videoInfo.setPort(deviceInfo.getPort());
			videoInfo.setUsername(deviceInfo.getUsername());
			videoInfo.setPassword(deviceInfo.getPassword());
		}
		String deviceCode = deviceInfo.getCode();
		// 设备编号四位
		Integer maxNum = videoService.getMaxCodeNum();
		if(maxNum==null || maxNum==0){
			maxNum = 1;
		}else{
			maxNum = maxNum + 1;
		}
		String code = deviceCode.substring(0, 12).concat("05").concat(getMaxNum(maxNum));
		videoInfo.setCode(code);
		videoInfo.setCompanyid(deviceInfo.getCompanyId());
		videoInfo.setMapx(deviceInfo.getMapx());
		videoInfo.setMapy(deviceInfo.getMapy());
		try {
			videoService.save(videoInfo);
			return code;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getMaxNum(int maxNum){
		String result = "";
		char[] numArr = String.valueOf(maxNum).toCharArray();
		if(numArr.length == 1){
			result = "000" + maxNum;
		}else
		if(numArr.length == 2){
			result = "00" + maxNum;
		}else
		if(numArr.length == 3){
			result = "0" + maxNum;
		}else{
			result = "" + maxNum;
		}
		return result;
	}
	
	/**
	 * 视频监控信息修改
	 * @author kouyunhao
	 * @version 1.0
	 * @param deviceInfo
	 * Dec 17, 2013
	 */
	public String updateVideoInfo(DeviceInfo deviceInfo){
		String videoCode = deviceInfo.getVideoCode();
		if(videoCode == null || "".equals(videoCode)){
			String videoCodenew = saveVideoInfo(deviceInfo);
			return videoCodenew;
		}else{
			List<VideoInfo> videoInfoList = videoService.findByCode(videoCode);
			if(videoInfoList != null && videoInfoList.size() != 0){
				VideoInfo videoInfo = new VideoInfo();
				videoInfo.setId(videoInfoList.get(0).getId());
				videoInfo.setDirectionCode(deviceInfo.getDirectionCode());
				videoInfo.setVideotypeCode(deviceInfo.getVideotypeCode());
				videoInfo.setRoadinfoId(deviceInfo.getRoadId());
				videoInfo.setName(deviceInfo.getName());
				videoInfo.setOrgId(deviceInfo.getOrgId());
				videoInfo.setDeviceIp(deviceInfo.getIp());
				if("1".equals(deviceInfo.getUsetemplate())){
					videoInfo.setUsetemplate("1");
					videoInfo.setRealvideoaddressid(deviceInfo.getRealvideoaddressid());
				}else{
					videoInfo.setUsetemplate("0");
					videoInfo.setRealvideoaddressid(null);
					videoInfo.setHisvideoaddressid(null);
					videoInfo.setIp(deviceInfo.getIps());
					videoInfo.setPort(deviceInfo.getPort());
					videoInfo.setUsername(deviceInfo.getUsername());
					videoInfo.setPassword(deviceInfo.getPassword());
				}
				videoInfo.setCompanyid(deviceInfo.getCompanyId());
				videoInfo.setMapx(deviceInfo.getMapx());
				videoInfo.setMapy(deviceInfo.getMapy());
				videoInfo.setCode(videoCode);
				try {
					videoService.updateById(videoInfo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return videoCode;
			}else{
				String videoCodenew = saveVideoInfo(deviceInfo);
				return videoCodenew;
			}
		}
	}

	// ============================== 类的公共方法 ==================================

	/**
	 * 设备类型需要的树结构json字符
	 * 
	 * @return
	 */
	private JsonArray getDeviceTypeJsonArray(List<DeviceTypeInfo> devTypeList) {
//		if (devTypeList == null)
//			return devTypeJsonArray;
		if(devTypeList != null && devTypeList.size() != 0){
			for (DeviceTypeInfo dt : devTypeList) {
				JsonObject json = new JsonObject();
				json.addProperty("id", dt.getId());
				json.addProperty("pId", dt.getPid());
				json
						.addProperty("isParent", departmentService.hasChild(dt
								.getId()));
				json.addProperty("name", dt.getName());
				if ("00".equals(dt.getId())) {
					json.addProperty("nocheck", "true");
				} else {
					json.addProperty("checked", "false");
				}
				if (deviceTypeInfoService.hasChild(dt.getId())) {
					List<DeviceTypeInfo> sonDepartList = deviceTypeInfoService
							.getChildren(dt.getId());
					this.getDeviceTypeJsonArray(sonDepartList);
				}
				devTypeJsonArray.add(json);
			}
		}else{
			JsonObject json0 = new JsonObject();
			json0.addProperty("id", "00");
			json0.addProperty("pId", "0");
			json0.addProperty("isParent", true);
			json0.addProperty("name", "设备功能");
			json0.addProperty("nocheck", "true");
			List<DeviceTypeInfo> sonDepartList0 = deviceTypeInfoService
					.getChildren("00");
			this.getDeviceTypeJsonArray(sonDepartList0);
			devTypeJsonArray.add(json0);
		}
		
		return devTypeJsonArray;
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

	

	/**
	 * 产生随机数名称
	 * 
	 * @return
	 */
	private String getImgName(String index, String suffix, String d) {
		String date = DateUtil.currentDateTime().replaceAll("-", "")
				.replaceAll(":", "").replace(" ", "");
		return date + "_" + d + "_" + index + suffix;

	}

	/**
	 * 根据设备类型信息，上传文件，和原上传文件路径，返回新的图片路径
	 * 
	 * @param mkdir
	 *            删除文件路径
	 * @param code
	 *            设备编号
	 * @param photoPath
	 *            修改后路径
	 * @param uploadFile
	 * @param imgPath
	 *            修改前路径
	 * @param fx
	 *            方向
	 * @return
	 */
	private String getPhotoPath(String mkdir, String code, String photoPath,
			CommonsMultipartFile uploadFile, String imgPath, String fx) {
		if (uploadFile.getSize() != 0) {
			File file = null;
			if (StringUtils.isNotEmpty(imgPath)) {
				String file1Path = imgPath.replace(IMGPATH, "");
				if (FileUtils.delFile(mkdir + file1Path)) {

					// 上传设备图片1
					com.xiangxun.atms.framework.util.FileUtils
							.mkDirectory(mkdir);
					String file1Name = uploadFile.getOriginalFilename();
					String fileType = file1Name.substring(file1Name
							.lastIndexOf("."));
					String uploadFileName = getImgName(fx, fileType, code);
					file = FileUtils.getCreateFile(mkdir, uploadFileName);
					if (StringUtils.isNotEmpty(file.getAbsolutePath())) {
						UploadFileUtils.up(uploadFile, file.getAbsolutePath());
					}

				}

			} else {
				// 上传设备图片1
				com.xiangxun.atms.framework.util.FileUtils.mkDirectory(mkdir);
				String file1Name = uploadFile.getOriginalFilename();
				String fileType = file1Name.substring(file1Name
						.lastIndexOf("."));
				String uploadFileName = getImgName(fx, fileType, code);
				file = FileUtils.getCreateFile(mkdir, uploadFileName);
				if (StringUtils.isNotEmpty(file.getAbsolutePath())) {
					UploadFileUtils.up(uploadFile, file.getAbsolutePath());
				}

			}
			return IMGPATH + File.separator + file.getName();

		} else {
			if (StringUtils.isNotEmpty(photoPath)) {
				if (StringUtils.isNotEmpty(imgPath)) {
					return imgPath;
				} else {
					return "";
				}

			} else {
				if (StringUtils.isNotEmpty(imgPath)) {
					String file1Path = imgPath.replace(IMGPATH, "");
					if (FileUtils.delFile(mkdir + file1Path)) {
						logger.debug("删除图片成功");
						return "";
					} else {
						return "";
					}
				} else {
					return "";
				}
			}

		}
	}
	
	/***
	 * 根据部门ID 查对应的部门CODE 
	 * @param req
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "getOrgCodeByOrgId/{orgid}/")
	@ResponseBody
	public void getOrgCodeByOrgId(HttpServletResponse resp, @PathVariable String orgid) {
		
		Department department = departmentService.getById(orgid);
		try {
			if(department!=null && department.getCode()!=null){
				JsonObject object = new JsonObject();
				object.addProperty("content",department.getCode());
				resp.getWriter().write(object.toString());
			}
		} catch (IOException e) {
			logger.error(e);
		}
		
	}
	
	/***
	 * 设备信息 导出
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="exportXls/{menuid}/",method=RequestMethod.GET)
	public void exportXls(@PathVariable String menuid,HttpServletResponse response,@RequestParam(value = "sortType", defaultValue = "NAME") String sortType,ServletRequest request){
		// 3 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(3);
		//接收前台参数
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		List<DeviceInfo> list = deviceInfoService.selectByExampleDiy(searchParams,sortType,menuid);
		// 设置内容
		List result = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			DeviceInfo deviceInfo = list.get(i);
			//建设时间格式转换
//			Date buildtime = deviceInfo.getBuildtime();
//			String buildtimeStr = DateUtil.dateFormatToString(buildtime, "yyyy-MM-dd HH:mm:SS");
			//所属部门信息
			Department department = departmentService.getById(deviceInfo.getOrgId());
			String departmentName = "";
			if(department!=null){
				departmentName  = department.getName();
			}
			//建设厂商信息
//			DeviceCompanyInfo deviceCompanyInfo = deviceCompanyInfoService.getById(deviceInfo.getCompanyId());
//			String deviceCompanyName = "";
//			if(deviceCompanyInfo!=null){
//				deviceCompanyName = deviceCompanyInfo.getName();
//			}
			//是否后台分析
//			String isblackanalyse = deviceInfo.getIsblackanalyse();
//			String blackanalyse = "";
//			if("1".equals(isblackanalyse)){
//				blackanalyse = "后台分析";
//			}else{
//				blackanalyse = "前端分析";
//			}
			//序列号
//			String license = deviceInfo.getLicense();
			//所在道路
			String roadId = deviceInfo.getRoadId();
			RoadInfo roadinfo = roadInfoService.getById(roadId);
			String roadName = "";
			if(roadinfo!=null){
				roadName = roadinfo.getName();
			}
			
			//封装数组
			Object[] values = new Object[] { 
				i+1, 
				deviceInfo.getCode(),
				deviceInfo.getName(),
				deviceInfo.getIp(),
				roadName,
				departmentName,
	//			deviceCompanyName,
	//			buildtimeStr,
	//			blackanalyse,
	//			license,
			};
			result.add(values);
		}
		
		String nowtimeStr = DateUtil.dateFormatToString(new java.util.Date(), "yyyy年MM月dd日HH时mm分ss秒");
		export.setFileName("设备信息一览表["+nowtimeStr+"].xls");
		export.exportExcelFileStream("xls" + File.separator + "deviceinfo_exp.xls", null, result, response);
	}
	
	/***
	 * 判断设备编号是否重复
	 * @param req
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "codeExist/{deviceCode}/")
	@ResponseBody
	public void deviceCodeExist(HttpServletResponse resp, @PathVariable String deviceCode) {
		
		//验证设备编号是否重复
		int n = deviceInfoService.countDeviceByCode(deviceCode);
		if(n>0){
			try {
				JsonObject object = new JsonObject();
				object.addProperty("content","<font color=red>设备编号已经存在!</font>");
				resp.getWriter().write(object.toString());
			} catch (IOException e) {
				logger.error(e);
			}
		}else{
			try {
				JsonObject object = new JsonObject();
				object.addProperty("content","<font color=green>设备编号唯一性效验正常!</font>");
				resp.getWriter().write(object.toString());
			} catch (IOException e) {
				logger.error(e);
			}
		}
		
	}
	
	/***
	 * 判断name是否重复 add by kouyunhao 2013-11-15
	 * @param req
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "nameExist")
	@ResponseBody
	public String nameExist(HttpServletRequest req, @RequestParam(value = "name") String name) {
		List<DeviceInfo> devList = deviceInfoService.findByName(name);
		String returnStr = Boolean.FALSE.toString();
		if (devList.size() == 0) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		//不为空说明是修改
		if(org.apache.commons.lang.StringUtils.isNotBlank(oper)){
			if(name.equals(oper)){
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
	@RequestMapping(value = "ipExist")
	@ResponseBody
	public String codeExist(HttpServletRequest req, @RequestParam(value = "ip") String ip) {
		List<DeviceInfo> devList = deviceInfoService.findByIp(ip);
		String returnStr = Boolean.FALSE.toString();
		if (devList.size() == 0) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		//不为空说明是修改
		if(org.apache.commons.lang.StringUtils.isNotBlank(oper)){
			if(ip.equals(oper)){
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
	public void setProjectCodeByAjax(@PathVariable String ip, ServletResponse response){
		List<DeviceInfo> devList = deviceInfoService.findByIp(ip);
		try {
			response.getWriter().write(devList.size()==0?"no":"yes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将页面中的查询条件从session中删除
	 * @author kouyunhao
	 * @version 1.0
	 * @param request
	 * @return
	 * Jun 10, 2014
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="del_earchparams",method = RequestMethod.GET)
	@ResponseBody
	@LogAspect(desc="删除查询条件")
	public ResponseEntity del_earchparams(HttpServletRequest request){
		ResponseEntity result = new ResponseEntity();
		try {
			Object sessionParams = request.getSession().getAttribute("searchParams");
			if(sessionParams != null){
				String sroadId = "";
				Map<String, Object> paramsMap = (Map<String, Object>) sessionParams;
				if(paramsMap.get("roadId") != null){
					sroadId = (String) paramsMap.get("roadId");
				}
				Map<String, Object> newParamsMap = new HashMap<String, Object>();
				newParamsMap.put("roadId", sroadId);
				request.getSession().setAttribute("searchParams", newParamsMap);
			}
			result.setResult("ok");
			return result;
		} catch (RuntimeException e) {
			logger.error(e);
			result.setResult("error");
			return result;
		}
	} 
	
	/**
	 * xiongjie添加<br />
	 * 数据库卡口数据同步
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/syncronize/", method = RequestMethod.GET)
	public void syncronize(HttpServletRequest request,HttpServletResponse resp) throws IOException{
		resp.setCharacterEncoding("utf-8");
		
		OperatorDetails operator = getCurrentUser();
		Set<GrantedAuthority> authorities = (Set<GrantedAuthority>)operator.getAuthorities();
		boolean flag = false;
		Iterator<GrantedAuthority> iter = authorities.iterator();
		while(iter.hasNext()) {
			GrantedAuthority a = iter.next();
			if("ADMINISTRATOR".equals(a.getAuthority())) {
				flag = true;
				break;
			}
		}
		if(!flag) {
			resp.getWriter().print("请使用管理员帐号登录！");
			return;
		}
		
		List<DeviceInfo> list = deviceInfoService.findAll();
		List<LayerBean> beans = new ArrayList<LayerBean>();
		Table<String, String, String> dicTable = (Table<String, String, String>) cache.get(Dic.class.getSimpleName());
		Table<String, String, String> roadTable = (Table<String, String, String>) cache.get(RoadInfo.class.getSimpleName());
		// 删除全部的卡口地图数据
		//mapService.deleteByWhere(LayerEnum.CROSS, "1=1");
		
		for(int i=0,len=list.size(); i<len; i++) {
			DeviceInfo dev = list.get(i);
			if(null == dev || null == dev.getMapx() || null == dev.getMapy())continue;
			
			LayerBean t = new LayerBean();
			t.setType(dicTable.get(dev.getDevicetypecode(), DicType.DEVICE_TYPE));
			t.setName(dev.getName());
			t.setCode(dev.getCode());
			t.setRoadId(dev.getRoadId());
			t.setRoadName(roadTable.get(dev.getRoadId(), RoadInfo.class.getSimpleName()));
			t.setGeometry("{\"type\":\"point\",\"points\":\""+dev.getMapx()+","+dev.getMapy()+"\"}");
			beans.add(t);
		}
		//mapService.addList(LayerEnum.CROSS, beans);
		resp.getWriter().print("同步数据成功！设备总数为："+list.size()+"条，同步数据为："+beans.size()+"条。");
	}
	
	/**
	 * 卡口设备变更处理
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doModify", method = RequestMethod.POST)
	@LogAspect(desc = "卡口设备变更处理")
	public String doModify(ModifyRecord modifyRecord,String menuid, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String modifyTypeName = "维修";
		String ids = modifyRecord.getModifyId();
		String[] id = ids.split(",");
		for (String string : id) {
			//保存变更记录信息
			modifyRecord.setId(UuidGenerateUtil.getUUID());
			modifyRecord.setModuleName("device");
			modifyRecord.setModifyDatetime(DateUtil.stringFormatToDate(modifyRecord.getModifyDatetimeStr(), "yyyy-MM-dd"));
			modifyRecord.setOperator(getCurrentUserId());
			Date datetime = new Date();
			modifyRecord.setOperatorTime(datetime);
			modifyRecord.setVersion(DateUtil.dateFormatToString(datetime, "yyyyMMddHHmmss"));
			String modifyType = modifyRecord.getModifyType();
			if(modifyType.equals("2")){
				modifyTypeName = "移除";
			}else if(modifyType.equals("3")){
				modifyTypeName = "报废";
			}
			modifyRecord.setModifyType(modifyTypeName);
			modifyRecord.setModifyId(string);
			modifyRecordService.save(modifyRecord);
			//将变更的记录COPY到历史表
			deviceInfoService.saveHistoryRecord(string);
			AssetInfo asset = null;
			List<AssetInfo> assetList = assetInfoService.findByDeviceId(string);
			if(assetList != null && assetList.size() != 0){
				asset = assetList.get(0);
			}
			//修改变更的记录ID
			String afterRecordId = UuidGenerateUtil.getUUID();
			deviceInfoService.updatePrimaryKey(afterRecordId, string);
			//同步修改资产信息
			if(asset != null){
				asset.setDeviceid(afterRecordId);
				assetInfoService.updateByIdSelective(asset);
			}
			//将修改后的记录ID同步更新到历史表字段
			deviceInfoHisService.updateAfterRecordId(string, afterRecordId);
			//同步修改设备功能
			String sql1 = 
					"insert into property_device_type_temp (device_id, devicetype_id) "+
					"select device_id, devicetype_id from property_device_type where device_id=" + "'"+ string + "'";
			String sql2 =
					"update property_device_type_temp t set device_id=" + "'"+ afterRecordId + "'" + "where device_id=" + "'"+ string + "'";
			String sql3 =
					"insert into property_device_type (device_id, devicetype_id) "+
					"select device_id, devicetype_id from property_device_type_temp where device_id=" + "'"+ afterRecordId + "'";
			String sql4 =
					"delete property_device_type_temp";
			deviceInfoService.updateDeviceType(sql1, sql2, sql3, sql4);
			//同步修改运维服务商责任资产设备ID
			List<FactoryContact> list = factoryContactService.findByDeviceId(string);
			for(FactoryContact factoryContact : list){
				factoryContact.setDeviceid(afterRecordId);
				factoryContactService.updateByIdSelective(factoryContact);
			}
		}
		redirectAttributes.addFlashAttribute("message", "卡口设备" + modifyTypeName + "操作成功");
		return "redirect:/property/deviceinfo/list/" + menuid + "/";
	}
	

	/****
	 * 下载模板
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="downloadXls/",method = RequestMethod.GET)
	public void downLoadXls(HttpServletRequest request,HttpServletResponse response){
		try {
			String filePath = ResourceUtils.getFile("classpath:xls/device_import.xls").getPath();
			this.download(filePath, "device_import.xls", request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/***
	 * 上传导入文件
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	@RequestMapping(value = "upload/{menuid}/",method = RequestMethod.POST)
	public String upload(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,@RequestParam("file") MultipartFile file,HttpServletRequest request,HttpServletResponse response){
	    File uploadFile = null;
	    List<KeyValue> errorMessages = new ArrayList<KeyValue>();
	    List<String> errors = ExcelImportValidator.errorMessages;
	    
	    //清楚历史记录
	    List<Object> objectList = devicesXlsImportService.getObjectList();
	    objectList.clear();
	    errors.clear();
	    
	    String errorView ="property/deviceinfo/errors";
	    
	    model.addAttribute("menuid", menuid);
	    
	    if (!file.isEmpty()) {
		    try{	
		    	uploadFile = upload(file, request, response, errorMessages);
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e);
				errorMessages.add(new KeyValue("success","false"));
				errorMessages.add(new KeyValue("msg", "系统异常"));
				model.addAttribute("errors", errorMessages);
				return errorView;
			}
	    }
		try{
			List<String> files = new ArrayList<String>();
			files.add(uploadFile.getAbsolutePath());
			//获取错误消息
			List<String> s = devicesXlsImportService.importValite(files);
			
			for (int i = 0; i < s.size(); i++) {
				errorMessages.add(new KeyValue("msg",s.get(i)));
			}
			//如果有错误
 			if(!s.isEmpty()){
				model.addAttribute("errors", errorMessages);
				return errorView;
				
			}else{
				//获取封装的对象
				List deviceList = devicesXlsImportService.getObjectList();
				HttpSession session = request.getSession();
				session.removeAttribute("import_device_list");
				session.setAttribute("import_device_list", deviceList);
				Page page=new Page();
				int strat=0;
				int end=0;
				if (pageNumber==0) {
					strat=0;
					end=15;
				}else {
					strat=(pageNumber-1)*15;
					end=pageNumber*15;
				}
				page=page.getPage(deviceList.size(), getList(deviceList,strat,end), pageNumber, 15); 
				model.addAttribute("sortType", sortType);
				model.addAttribute("pageList", page);
				return "property/deviceinfo/preview";
			} 
	    }catch (Exception e) {
	    	errorMessages.add(new KeyValue("msg","数据处理异常"));
	    	model.addAttribute("errors", errorMessages);
	    	return errorView;
	    }
	}
	
	/***
	 * 上传导入数据分页
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "uploadpage/data/{menuid}/", method = RequestMethod.GET)
	public String uploadData(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){	   
	    model.addAttribute("menuid", menuid);
				//获取封装的对象
				HttpSession session = request.getSession();
				List device = (List) session.getAttribute("import_device_list");
				
				Page page=new Page();
				int strat=0;
				int end=0;
				if (pageNumber==0) {
					strat=0;
					end=15;
				}else {
					strat=(pageNumber-1)*15;
					end=pageNumber*15;
				}
				if (end>device.size()) {
					end=device.size();
				}
				page=page.getPage(device.size(), getList(device,strat,end), pageNumber, 15);
				model.addAttribute("sortType", sortType);
				model.addAttribute("pageList", page);
				return "property/deviceinfo/preview";
	   
	}
	
	/***
	 * 导入到数据库
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("doImport/{menuid}/")
	@ResponseBody
	public String doImport(@PathVariable("menuid") String menuid,HttpServletRequest request,RedirectAttributes redirectAttributes){
		HttpSession session = request.getSession();
		Object object = session.getAttribute("import_device_list");
		if(object==null){
			throw new UsernameNotFoundException("用户不存在");
		}
		try{
			List<DeviceOuery> list = (List<DeviceOuery>)object;
			List<DeviceInfo> deviceList = convettruckBean(list);
			/*for (DeviceInfo deviceBean : deviceList) {
				deviceInfoService.save(deviceBean);
			}*/
			for (int i = 0; i < deviceList.size(); i++) {
				deviceInfoService.save(deviceList.get(i));
			}
			return returnJson();
		}catch (Exception e) {
			JsonObject json = new JsonObject();
			json.addProperty("result", "error");
			json.addProperty("resultmessage", "第  "+i+" 行出错请检查表格!");
			return json.toString();
		}
	}
	//此处判断第i行给保存出错，给用户提示
int i = 1;	
	private List<DeviceInfo> convettruckBean(List<DeviceOuery> list) throws ParseException {
		List<DeviceInfo> deviceBeanList = new ArrayList<DeviceInfo>(list.size()*2);
		i = 1;
		for (DeviceOuery deviceQuery : list) {
			i++;
			DeviceInfo deviceBean = new DeviceInfo();
			//设置id
			deviceBean.setId(UuidGenerateUtil.getUUID());
			//设置设备编号和谁被名称和设备ip
			deviceBean.setCode(deviceQuery.getDevice_code());
			deviceBean.setName(deviceQuery.getDevice_name());
			deviceBean.setIp(deviceQuery.getDevice_ip());
			
			//通过道路名称查找道路信息
			List<RoadInfo> findByRoadname = roadInfoService.findByRoadname(deviceQuery.getDevice_road());
			if(findByRoadname != null && findByRoadname.size() > 0){
				//设置道路信息
				deviceBean.setRoadId(findByRoadname.get(0).getId());
				deviceBean.setRoadName(findByRoadname.get(0).getName());
			}
			//通过部门名称查找部门信息，设置管理部门信息
			Department oneByName = departmentService.getOneByName(deviceQuery.getDevice_depatment());
			if(oneByName != null){
				deviceBean.setOrgId(oneByName.getId());
				deviceBean.setOrgNames(oneByName.getName());
			}
			
			//根据厂商名称获取厂商信息，设置厂商id
			DeviceCompanyInfoSearch companyInfoSearch = new DeviceCompanyInfoSearch();
			companyInfoSearch.createCriteria().andNameEqualTo(deviceQuery.getDevice_business());
			List<DeviceCompanyInfo> selectByExample2 = deviceCompanyInfoService.selectByExample(companyInfoSearch);
			if(selectByExample2 != null && selectByExample2.size() > 0){
				deviceBean.setCompanyId(selectByExample2.get(0).getId());
			}
			
			//设置服务厂商
			String device_fuwucs = deviceQuery.getDevice_fuwucs();
			if(device_fuwucs !=null && !device_fuwucs.equals("")){
				List<FactoryInfo> factoryList = factoryInfoService.getByName(device_fuwucs);
				if(factoryList != null && factoryList.size() > 0){
					FactoryInfo factoryInfo = factoryList.get(0);
					deviceBean.setFactoryId(factoryInfo.getId());
				}
			}
			//设置设备功能
			String device_gongneng = deviceQuery.getDevice_gongneng();
			if(device_gongneng !=null && !device_gongneng.equals("")){
				String[] deviceTypeName = device_gongneng.split(",");
				for (String typeName : deviceTypeName) {
					DeviceTypeInfoSearch deviceTypeInfoSearch = new DeviceTypeInfoSearch();
					deviceTypeInfoSearch.createCriteria().andNameEqualTo(typeName);
					List<DeviceTypeInfo> deviceTypeInfoList = deviceTypeInfoService.selectByExample(deviceTypeInfoSearch);
					if(deviceTypeInfoList!= null && deviceTypeInfoList.size()>0){
						DeviceTypeInfo deviceTypeInfo = deviceTypeInfoList.get(0);
						
						DeviceType deviceType = new DeviceType();
						deviceType.setDeviceId(deviceBean.getId());
						deviceType.setDevicetypeId(deviceTypeInfo.getId());
						deviceTypeService.save(deviceType);
						
					}
				}
			}
			//设置添加时间
			deviceBean.setBuildtime(new Date());
			
			//设置添加时间
			deviceBean.setInserttime(new Date());
			//设置超时时间
			deviceBean.setTimeout(BigDecimal.valueOf(60));
//----------------   add device end ---------------------------
//----------------   add asset start ------------------------
			String asset_status = deviceQuery.getAsset_status();
			String asset_baoxiutime = deviceQuery.getAsset_baoxiutime();
			String asset_caigoutime = deviceQuery.getAsset_caigoutime();
			String asset_anzhuangtime = deviceQuery.getAsset_anzhuangtime();
			// not null 
			AssetInfo assetInfo = new AssetInfo();
			assetInfo.setId(UuidGenerateUtil.getUUID());
			assetInfo.setAssettype("device");
			assetInfo.setDeviceid(deviceBean.getId());
			assetInfo.setAssetname(deviceBean.getName());
			List<Dic> dicByType = dicService.getDicByType(DicType.ASSETSTATUS);
			for (Dic dic : dicByType) {
				if(dic.getName().equals(asset_status)) assetInfo.setAssetstatus(dic.getCode());
			}
			//生成资产编号
			//获取数据库中的
			String assetInfoCode = assetInfoService.getAssetInfoCode();
			if(assetInfoCode ==null || "".equals(assetInfoCode)){
				assetInfoCode = "170000000000000";
			}else{
				assetInfoCode = (Long.parseLong(assetInfoCode) + 1) + "";
			}
			assetInfo.setAssetcode(assetInfoCode.trim());
			
			assetInfo.setGuaranteetime(DateUtil.parseDate(asset_baoxiutime, "yyyy-MM-dd"));
			assetInfo.setPurchasetime(DateUtil.parseDate(asset_caigoutime, "yyyy-MM-dd"));
			assetInfo.setInstalltime(DateUtil.parseDate(asset_anzhuangtime, "yyyy-MM-dd HH:mm:ss"));
			assetInfo.setIp(deviceBean.getIp());
			//服务商ID
			assetInfo.setFactoryId(deviceBean.getFactoryId());
			assetInfo.setServiceid(deviceBean.getFactoryId());
			assetInfo.setInstallplace(deviceBean.getName());
			assetInfoService.save(assetInfo);
			deviceBeanList.add(deviceBean);
		}
		
		EchoWorker.listDeviceStatus.clear();
		return deviceBeanList;
	}
	
	@SuppressWarnings({ "unchecked"})
	public List getList(List deviceList,int start,int end){
		List fenyeList = new ArrayList();
		for (int i = start; i < end; i++) {
			try {
				fenyeList.add(deviceList.get(i));
			} catch (RuntimeException e) {
				return fenyeList;
			}
		}
		return fenyeList;
	}
	
}
