package com.xiangxun.atms.module.property.web;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.xiangxun.atms.common.deptment.vo.Department;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.common.road.service.RoadInfoService;
import com.xiangxun.atms.common.road.vo.RoadInfo;
import com.xiangxun.atms.common.system.service.ParamsService;
import com.xiangxun.atms.common.system.vo.SystemParams;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimpleXlsExportor;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.monitor.nio.EchoWorker;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.CabInfo;
import com.xiangxun.atms.module.property.domain.DatabaseInfo;
import com.xiangxun.atms.module.property.domain.DeviceCompanyInfo;
import com.xiangxun.atms.module.property.domain.DeviceFtpInfo;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.DeviceTypeInfo;
import com.xiangxun.atms.module.property.domain.ICabinetLog;
import com.xiangxun.atms.module.property.domain.ModifyRecord;
import com.xiangxun.atms.module.property.domain.VideoAddressInfo;
import com.xiangxun.atms.module.property.mapper.CabInfoMapper;
import com.xiangxun.atms.module.property.service.AssetInfoService;
import com.xiangxun.atms.module.property.service.CabInfoService;
import com.xiangxun.atms.module.property.service.DeviceCompanyInfoService;
import com.xiangxun.atms.module.property.service.DeviceInfoHisService;
import com.xiangxun.atms.module.property.service.DeviceInfoService;
import com.xiangxun.atms.module.property.service.ICabinetLogService;
import com.xiangxun.atms.module.property.service.ModifyRecordService;
import com.xiangxun.atms.module.sergrade.domain.FactoryContact;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.service.FactoryContactService;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;
import com.xiangxun.atms.icabinet.tools.ICabinetOption;
import com.xiangxun.atms.icabinet.tools.ICabinetHelper;

@Controller
@RequestMapping(value = "property/cabinfo/")
public class CabInfoCtl extends BaseCtl {
	@Resource
	FactoryInfoService factoryInfoService;

	@Resource
	CabInfoService cabInfoService;
	@Resource
	FactoryContactService factoryContactService;
	@Resource
	WorkorderInfoService workorderInfoService;

	@Resource
	AssetInfoService assetInfoService;
	@Resource
	ModifyRecordService modifyRecordService;
	@Resource
	RoadInfoService roadInfoService;

	@Autowired
	ICabinetHelper helper;
	@Resource
	CabInfoMapper cabInfoMapper;
	@Resource
	ParamsService paramsService;
	@Resource
	DeviceInfoService deviceInfoService;
	@Resource
	DeviceInfoHisService deviceInfoHisService;
	@Resource
	ICabinetLogService icabinetlogservice;
	
	
	@Resource
	DeviceCompanyInfoService deviceCompanyInfoService;
	
	boolean num = true;

	/***
	 * 跳转到主界面
	 * 
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable String menuid, ModelMap model,
			@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request) {

		logger.info("正在进行信息列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Map<String, Object> mapTemp = Servlets.getParametersStartingWith(request, "search_");

		String sroadId = "";
		Object sessionParams = request.getSession().getAttribute("searchParams_cabinet");
		if (sessionParams != null) {
			Map<String, Object> paramsMap = (Map<String, Object>) sessionParams;
			if (request.getParameter("searchFlag") != null) {
				if (request.getParameter("searchFlag").equals("1")) {
					if (paramsMap.get("roadId") != null) {
						sroadId = (String) paramsMap.get("roadId");
					}
					mapTemp.put("roadId", sroadId);
					searchParams.put("roadId", sroadId);

				} else if (request.getParameter("searchFlag").equals("2")) {
					if (paramsMap.get("roadId") != null) {
						sroadId = (String) paramsMap.get("roadId");
					}
					if (!sroadId.equals("")) {
						paramsMap.remove("roadId");
						searchParams.putAll(paramsMap);
						mapTemp.putAll(paramsMap);
					}
				}
				request.getSession().removeAttribute("searchParams_cabinet");
			} else {
				searchParams.putAll(paramsMap);
				mapTemp.putAll(paramsMap);
			}
		}
		Object obj = searchParams.get("roadId");
		if (obj != null && !obj.equals("")) {
			String roadid = (String) obj;
			if (roadid.equals("00")) {
				searchParams.remove("roadId");
			} else {
				String parentid = roadInfoService.getById(roadid).getPid();
				if (parentid.equals("00")) {
					searchParams.put("roadPid", roadid);
					searchParams.remove("roadId");
				}
			}
		} else {
			if (!sroadId.equals("")) {
				if (sroadId.equals("00")) {
					searchParams.remove("roadId");
				} else {
					String parentid = roadInfoService.getById(sroadId).getPid();
					if (parentid.equals("00")) {
						searchParams.put("roadPid", sroadId);
						searchParams.remove("roadId");
					}
				}
			}
		}
		
		//将查询条件放到session中
				request.getSession().setAttribute("searchParams_cabinet", mapTemp);
				request.getSession().setAttribute("menuinfoFlag", "cabinet");
				//设备名称
				String name = request.getParameter("na me");
				//IP地址
				String ip = request.getParameter("ip");
				if (!"".equals(name) && null != name) {
					searchParams.put("name", name);
					request.getSession().setAttribute("name", name);
				}
				if (!"".equals(ip) && null != ip) {
					searchParams.put("ip", ip);
					request.getSession().setAttribute("ip", ip);
				}
				/**
				 * add by kouyunhao 2014-09-29 添加按照创建时间（INSERTTIME）倒叙排列。
				 */
		
		Page page = cabInfoService.getListByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE, sortType,
				menuid);


		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		CabInfo cab = new CabInfo();
		try {
			BeanUtils.populate(cab, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("cab", cab);
		
		if(!num){
			request.getSession().removeAttribute("searchParams_cabinet");
			num = true;
		}

		return "property/cabinfo/list";
	}

	/*
	 * 
	 * 跳转到iframe上一级页面 add by kouyunhao 2013-07-18
	 * 
	 * @param menuid
	 * 
	 * @param model
	 * 
	 * @return
	 */
	@RequestMapping(value = "sublist/{menuid}/")
	public String iframe_list(@PathVariable String menuid, ModelMap model,
			@RequestParam(value = "sortType", defaultValue = "NAME") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request) {
		
		num = false;

		this.list(menuid, model, sortType, pageNumber, request);

		return "property/cabinfo/sublist";
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
	@RequestMapping(value = "showAdd/{menuid}/{roadid}/", method = RequestMethod.GET)
	public String showDeviceAdd(@PathVariable("menuid") String menuid, @PathVariable("roadid") String roadid,
			String page, Model model, RedirectAttributes redirectAttributes) {
		// 服务厂商
		List<FactoryInfo> factoryList = factoryInfoService.findAll();
		model.addAttribute("factoryList", factoryList);
		// 设备厂家
		List<DeviceCompanyInfo> companyList = deviceCompanyInfoService.findAll();
	    model.addAttribute("companyList", companyList);
				
		// 添加验证，阻止一级道路下再添加设备信息start===============//
		RoadInfo roadInfo = roadInfoService.getById(roadid);
		String parentid = roadInfo.getPid();
		if (parentid.equals("00")) {
			redirectAttributes.addFlashAttribute("message", "一级道路下不允许添加设备信息");
			return "redirect:/property/cabinfo/sublist/" + menuid + "/";
		}
		// 添加验证，阻止一级道路下再添加设备信息end===============//
		model.addAttribute("roadId", roadid);
		model.addAttribute("roadInfo", roadInfo);
		return "property/cabinfo/add";
	}

	/***
	 * 添加一个机柜
	 * 
	 * @param cab
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	@LogAspect(desc = "添加一个机柜")
	public String doAdd(CabInfo cab, AssetInfo asset, String menuid, RedirectAttributes redirectAttributes)
			throws ParseException {
		String UUID = UuidGenerateUtil.getUUID();
		cab.setId(UUID);
		cab.setOrgId("00");
		RoadInfo roadinfo = roadInfoService.getById(cab.getRoadId());
		if (roadinfo != null) {
			cab.setRoadId(roadinfo.getId());

		}
		cabInfoService.save(cab);
		String toasset = asset.getToasset();

		if (toasset.equals("1")) {
			asset.setId(UuidGenerateUtil.getUUID());
			asset.setAssettype("cabinet");
			asset.setAssetname(cab.getName());
			asset.setDeviceid(UUID);
			asset.setCabinetStatus(-1);
			asset.setGuaranteetime(DateUtil.parseDate(asset.getGuaranteetimestr(), "yyyy-MM-dd"));
			asset.setPurchasetime(DateUtil.parseDate(asset.getPurchasetimestr(), "yyyy-MM-dd"));
			asset.setInstalltime(DateUtil.parseDate(asset.getInstalltimestr(), "yyyy-MM-dd HH:mm:ss"));
			asset.setAssetmodel("DS-TP3200-EC");
			// 服务商ID
			
			asset.setServiceid((cab.getFactoryId()));
			assetInfoService.save(asset);
		}
		redirectAttributes.addFlashAttribute("message", "添加成功");
		EchoWorker.listDeviceStatus.clear();

		return "redirect:/property/cabinfo/sublist/" + menuid + "/";
	}

	/***
	 * 获取详情信息
	 *   
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}/{menuid}/", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, @PathVariable("menuid") String menuid, String page, Model model) {
		CabInfo cab = cabInfoService.getById(id);
		// FactoryInfo factory = factoryInfoService.getById(cab.getFactoryId());
		// if(factory != null){
		// cab.setFactoryName(factory.getName());
		// }
		model.addAttribute("cab", cab);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "property/cabinfo/view";
	}

	/***
	 * 获取待修改机柜信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/{roadId}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, @PathVariable("menuid") String menuid,
			@PathVariable("roadId") String roadId, String page, Model model, HttpServletRequest request) {
		CabInfo cab = cabInfoService.getById(id);
		
		// 设备厂家
	    List<DeviceCompanyInfo> companyList = deviceCompanyInfoService.findAll();
		model.addAttribute("companyList", companyList);
		// 服务厂商
		List<FactoryInfo> factoryList = factoryInfoService.findAll();
		model.addAttribute("factoryList", factoryList);
		// 判断是否已经转为资产
		String isasset = "0";
		List<AssetInfo> assetList = assetInfoService.findByDeviceId(id);
		if (assetList != null && assetList.size() != 0) {
			isasset = "1";
			model.addAttribute("asset", assetList.get(0));
		}

		model.addAttribute("isasset", isasset);

		model.addAttribute("cab", cab);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "property/cabinfo/update";
	}

	/***
	 * 修改机柜信息
	 * 
	 * @param cab
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc = "修改机柜信息")
	public String doUpdate(@ModelAttribute("preload") CabInfo cab, AssetInfo asset, String id, String page,
			String menuid, RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {		cabInfoService.updateByIdSelective(cab);
		// 资产配置修改
		String isasset = request.getParameter("isasset");
		asset.setGuaranteetime(DateUtil.parseDate(asset.getGuaranteetimestr(), "yyyy-MM-dd"));
		asset.setPurchasetime(DateUtil.parseDate(asset.getPurchasetimestr(), "yyyy-MM-dd"));
		asset.setInstalltime(DateUtil.parseDate(asset.getInstalltimestr(), "yyyy-MM-dd HH:mm:ss"));
		// 服务商ID
		asset.setFactoryId(cab.getFactoryId());
		if (isasset.equals("1")) {
			asset.setId(request.getParameter("assetid"));
			//修改之前修改了机柜名称而没有修改资产名称的漏洞
			asset.setAssetname(cab.getName());
			//将机柜表中的factoryID转为资产表中的serviceID
			asset.setServiceid(cab.getFactoryId());
			//将之前的机柜状态再次写入
			
			AssetInfo cab1 = assetInfoService.getById(asset.getId());
			asset.setCabinetStatus(cab1.getCabinetStatus());
			
			assetInfoService.updateByIdSelective(asset);
		} else {
			String toasset = asset.getToasset();
			if (toasset.equals("1")) {
				asset.setAssetname(cab.getName());
				asset.setId(UuidGenerateUtil.getUUID());
				asset.setAssettype("cabinet");
				asset.setCabinetStatus(-1);
				asset.setAssetname(cab.getName());
			    asset.setServiceid(cab.getFactoryId());
				asset.setDeviceid(cab.getId());
				asset.setAssetmodel("DS-TP3200-EC");
				assetInfoService.save(asset);
			}
		}

		redirectAttributes.addFlashAttribute("message", "修改成功");
		EchoWorker.listDeviceStatus.clear();

		return "redirect:/property/cabinfo/sublist/" + menuid + "/?page=" + page;
	}

	/***
	 * 删除一个机柜
	 * 
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value = "delete/{ids}/", method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc = "删除了一个机柜")
	public ResponseEntity delete(@PathVariable String ids) {
		ResponseEntity entity = new ResponseEntity();
		System.out.println("delete mothod");
		try {
			logger.info("正在删除一个机柜。。。。。。");
			String[] id = ids.split(",");
			
			for (int i = 0; i < id.length; i++) {
				

				// 同步删除资产信息
				// List<AssetInfo> assetList =
				// assetInfoService.findByDeviceId(string);
				// if(assetList != null && assetList.size() != 0){
				// AssetInfo asset = assetList.get(0);
				// assetInfoService.deleteById(asset.getId());
				// }
				CabInfo cab = cabInfoService.getById(id[i]);
				String assetcode = "";
				List<AssetInfo> assetList = assetInfoService.findByDeviceId(id[i]);
				if (assetList != null && assetList.size() != 0) {
					assetcode = assetList.get(0).getAssetcode();
				}
				// 同步删除资产信息
				assetInfoService.deleteAssetByDeviceId(id[i]);
				// 同步删除服务商分配信息
				// assetInfoService.deleteFactoryContactByDeviceId(string);
				// 同步删除设备日志信息
				//assetInfoService.deleteDeviceLogByCode(cab.getId());
				// 同步删除工单信息
				List<WorkorderInfo> workorderList = workorderInfoService.findByDeviceCode(assetcode);
				if (workorderList != null && workorderList.size() != 0) {
					for (WorkorderInfo workorder : workorderList) {
						String exceptionid = workorder.getExceptionid();
						if (exceptionid != null) {
							assetInfoService.deleteWorkorderReportById(exceptionid);
						}
						assetInfoService.deleteWorkorderLogByWorkId(workorder.getId());
						assetInfoService.deleteWorkorderAppriseByWorkId(workorder.getId());
					}
				}
				assetInfoService.deleteWorkorderByCode(assetcode);

				cabInfoService.deleteById(id[i]);
			
				
				
			}
			
			/*for (String string : id) {
				// 同步删除资产信息
				// List<AssetInfo> assetList =
				// assetInfoService.findByDeviceId(string);
				// if(assetList != null && assetList.size() != 0){
				// AssetInfo asset = assetList.get(0);
				// assetInfoService.deleteById(asset.getId());
				// }
				CabInfo cab = cabInfoService.getById(string);
				String assetcode = "";
				List<AssetInfo> assetList = assetInfoService.findByDeviceId(string);
				if (assetList != null && assetList.size() != 0) {
					assetcode = assetList.get(0).getAssetcode();
				}
				// 同步删除资产信息
				assetInfoService.deleteAssetByDeviceId(string);
				// 同步删除服务商分配信息
				// assetInfoService.deleteFactoryContactByDeviceId(string);
				// 同步删除设备日志信息
				//assetInfoService.deleteDeviceLogByCode(cab.getId());
				// 同步删除工单信息
				List<WorkorderInfo> workorderList = workorderInfoService.findByDeviceCode(assetcode);
				if (workorderList != null && workorderList.size() != 0) {
					for (WorkorderInfo workorder : workorderList) {
						String exceptionid = workorder.getExceptionid();
						if (exceptionid != null) {
							assetInfoService.deleteWorkorderReportById(exceptionid);
						}
						assetInfoService.deleteWorkorderLogByWorkId(workorder.getId());
						assetInfoService.deleteWorkorderAppriseByWorkId(workorder.getId());
					}
				}
				assetInfoService.deleteWorkorderByCode(assetcode);

				cabInfoService.deleteById(string);
			}*/
			

			
			entity.setResult("ok");
			EchoWorker.listDeviceStatus.clear();

			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			EchoWorker.listDeviceStatus.clear();

			return entity;
		}

	}

	/***
	 * 判断名称是否重复
	 * 
	 * @param req
	 * @param nameExist
	 * @return
	 */
	@RequestMapping(value = "nameExist")
	@ResponseBody
	public String nameExist(HttpServletRequest req, @RequestParam(value = "name") String name) {
		List<CabInfo> cabList = cabInfoService.findByName(name);
		String returnStr = Boolean.FALSE.toString();
		if (cabList.size() == 0) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		// 不为空说明是修改
		if (StringUtils.isNotBlank(oper)) {
			if (name.equals(oper)) {
				return Boolean.TRUE.toString();
			}
		}
		return returnStr;
	}

	/***
	 * 判断ip是否重复
	 * 
	 * @param req
	 * @param ip
	 * @return
	 */
	@RequestMapping(value = "ipExist")
	@ResponseBody
	public String ipExist(HttpServletRequest req, @RequestParam(value = "ip") String ip) {
		List<CabInfo> devList =  cabInfoService.findByIp(ip);
		String returnStr = Boolean.FALSE.toString();
		if (devList.size() == 0) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		// 不为空说明是修改
		if (org.apache.commons.lang.StringUtils.isNotBlank(oper)) {
			if (ip.equals(oper)) {
				return Boolean.TRUE.toString();
			}
		}
		return returnStr;
	}
	
	/***
	 * 判断设备编号是否重复 
	 * 
	 * @param req
	 * @param ip
	 * @return
	 */
	@RequestMapping(value = "codeExist")
	@ResponseBody
	public String codeExist(HttpServletRequest req, @RequestParam(value = "code") String code) {
		List<CabInfo> devList = cabInfoService.findByCode(code);
		String returnStr = Boolean.FALSE.toString();
		if (devList.size() == 0) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		// 不为空说明是修改
		if (org.apache.commons.lang.StringUtils.isNotBlank(oper)) {
			if (code.equals(oper)) {
				return Boolean.TRUE.toString();
			}
		}
		return returnStr;
	}
	/**
	 * 下发前保存
	 * @param menuid
	 * @param req
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "savebeforesend/{menuid}/")
	public String savebeforesend(String menuid,HttpServletRequest req,RedirectAttributes redirectAttributes){
		//温度最小值
	    String temrangemin = req.getParameter("temrangemin");
		processSystemParams("icabinet_temperature_range_min",temrangemin,"1");
		//温度最大值
	    String temrangemax = req.getParameter("temrangemax");
		processSystemParams("icabinet_temperature_range_max",temrangemax,"1");
		//湿度最小值
	    String humrangmin = req.getParameter("humrangmin");
		processSystemParams("icabinet_humidity_range_min",humrangmin,"1");
		
		//湿度最大值
	    String humrangmax = req.getParameter("humrangmax");
		processSystemParams("icabinet_humidity_range_max",humrangmax,"1");
		
		//振动起始值
	    String shorangmin = req.getParameter("shorangmin");
		processSystemParams("icabinet_shock_range_min",shorangmin,"1");
		
		//振动结束值
	    String shorangmax = req.getParameter("shorangmax");
		processSystemParams("icabinet_shock_range_max",shorangmax,"1");
		
		//AC插座电压起始值
	    String volrangmin = req.getParameter("volrangmin");
		processSystemParams("icabinet_ac_voltage_range_min",volrangmin,"1");
		
		//AC插座电压结束值
	    String volrangmax = req.getParameter("volrangmax");
		processSystemParams("icabinet_ac_voltage_range_max",volrangmax,"1");
		
		//AC插座电流起始值
	    String currangmin = req.getParameter("currangmin");
		processSystemParams("icabinet_ac_current_range_min",currangmin,"1");
		
		//AC插座电流结束值
	    String currangmax = req.getParameter("currangmax");
		processSystemParams("icabinet_ac_current_range_max",currangmax,"1");
		
		//采样间隔
	    String samplingTime = req.getParameter("samplingtime");
		processSystemParams("icabinet_samplingtime",samplingTime,"1");
		
		return "redirect:/property/cabinfo/send/" + menuid + "/";
	}
	
	
	private void processSystemParams(String key,String value,String type) {
		SystemParams systemParams = paramsService.getParamsByKeyAndType(key,type);
		if(!StringUtils.isEmpty(value)){
			if(systemParams ==null){
				systemParams = new SystemParams();
			}
			systemParams.setName(key);
			//1表示系统参数
			systemParams.setTypes(type);
			systemParams.setValue(value);
			if(StringUtils.isEmpty(systemParams.getId())){
				systemParams.setId(UuidGenerateUtil.getUUIDLong());
				paramsService.save(systemParams);
			}else{
				paramsService.updateByIdSelective(systemParams);
			}
		}
	}
	
	
	/**
	 * 下发机柜配置
	 * @param menuid
	 * @param model
	 * @param sortType
	 * @param session
	 * @param pageNumber
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "send/{menuid}/")
	public String send(@PathVariable String menuid, ModelMap model,
			@RequestParam(value = "sortType", defaultValue = "id") String sortType, HttpSession session,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request) {
		List<ICabinetOption> options = new ArrayList<ICabinetOption>();
		List<CabInfo> cabList = cabInfoService.selectAll();
		Map<String,String> paramsList=paramsService.getAllParams("1");
		System.out.println(paramsList.get("icabinet_ac_current_range_min"));
		for (CabInfo ci : cabList) {
			ICabinetOption option = new ICabinetOption(ci.getIp(), ci.getCode(), ci.getName(), ci.getAlarmhostip());
			option.setSamplingTime("10");
			option.setAcSocketCurrentMin(paramsList.get("icabinet_ac_current_range_min"));
			option.setAcSocketCurrentMax(paramsList.get("icabinet_ac_current_range_max"));
			option.setAcSocketVoltageMin(paramsList.get("icabinet_ac_voltage_range_min"));
			option.setAcSocketVoltageMax(paramsList.get("icabinet_ac_voltage_range_max"));

//			option.setTemperatureMin(paramsList.get("icabinet_temperature_range_min"));
//			option.setTemperatureMax(paramsList.get("icabinet_temperature_range_max"));
			option.setTemperatureRegionMin(paramsList.get("icabinet_temperature_range_min"));
			option.setTemperatureRegionMax(paramsList.get("icabinet_temperature_range_max"));
//			option.setHumidityMin(paramsList.get("icabinet_temperature_range_min"));
//			option.setHumidityMax("100");
			option.setHumidityRegionMin(paramsList.get("icabinet_humidity_range_min"));
			option.setHumidityRegionMax(paramsList.get("icabinet_humidity_range_max"));
			option.setSamplingTime(paramsList.get("icabinet_samplingtime"));
			options.add(option);
		}
		String execNo = helper.start(options.toArray(new ICabinetOption[0]));
	   request.getSession().setAttribute("execno", execNo);
		logger.info("execno:" + execNo);
		
		return "redirect:/property/cabinfo/ShowStatus/" + menuid + "/";
	}

	/**
	 * 机柜下发状态显示界面
	 * @param menuid
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "ShowStatus/{menuid}/")
	public String ShowStatus(@PathVariable String menuid, ModelMap model,
			@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request){
	
		String execno = (String) request.getSession().getAttribute("execno");
		List<ICabinetLog> icaList=icabinetlogservice.getMessage(execno);
//		
//		int totalCount =0;
//		
//		if(icaList != null){
//			totalCount = icaList.size();
//		}
//		
//		Page pageList = Page.getPage(totalCount, icaList, pageNumber, Page.DEFAULT_PAGE_SIZE);
//		

		
		
		
		model.addAttribute("icaList", icaList);
		model.addAttribute("menuid", menuid);
		return "property/cabinfo/status"; 
	}
	

	
	/***
	 * 机柜信息 导出
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="exportXls/{menuid}/",method=RequestMethod.GET)
	public void exportXls(@PathVariable String menuid,HttpServletResponse response,@RequestParam(value = "sortType", defaultValue = "NAME") String sortType,ServletRequest request){
		// 3 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(3);
		//接收前台参数
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		List<CabInfo> list = cabInfoService.selectByExampleDiy(searchParams,sortType,menuid);
		
		// 设置内容
		List result = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			CabInfo cabInfo = list.get(i);
			
			//建设厂商信息
			DeviceCompanyInfo deviceCompanyInfo = deviceCompanyInfoService.getById(cabInfo.getCompanyId());
			String deviceCompanyName = "";
			if(deviceCompanyInfo!=null){
				deviceCompanyName = deviceCompanyInfo.getName();
			}
			
			//所在道路
			String roadId = cabInfo.getRoadId();
			RoadInfo roadinfo = roadInfoService.getById(roadId);
			String roadName = "";
			if(roadinfo!=null){
				roadName = roadinfo.getName();
			}
			
			//封装数组
			Object[] values = new Object[] { 
				i+1, 
				cabInfo.getCode(),
				cabInfo.getName(),
				cabInfo.getIp(),
				roadName,
				deviceCompanyName,
				
			};
			result.add(values);
		}
		
		String nowtimeStr = DateUtil.dateFormatToString(new java.util.Date(), "yyyy年MM月dd日HH时mm分ss秒");
		export.setFileName("机柜信息一览表["+nowtimeStr+"].xls");
		export.exportExcelFileStream("xls" + File.separator + "cabinfo_exp.xls", null, result, response);
	}
	
}
