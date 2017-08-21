package com.xiangxun.atms.module.eventalarm.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.aspectj.weaver.tools.cache.CacheKeyResolver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.framework.base.ApplicationContextHolder;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimpleXlsExportor;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.eventalarm.domain.ServerStatus;
import com.xiangxun.atms.module.eventalarm.domain.ServerStatusProcess;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.ShowIcabinetStatus;
import com.xiangxun.atms.module.property.service.AssetInfoService;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;
import com.xiangxun.xml.root.status.serverStatus.XmlDiskInfo;

/***
 * 设备状态监测
 * 
 * @author YanTao
 */
@Controller
@RequestMapping(value = "alarm/status")
public class StatusMonitorCtl extends BaseCtl {

	@Resource
	AssetInfoService assetInfoService;
	
	@Resource
	DicService dicService;

	@Resource
	DepartmentService departmentService;
	@Resource
	FactoryInfoService factoryInfoService;
	
	@Value("${cabinet.pattern}")
    String pattern; 
	
	/**
	 * 跳转到主界面
	 * 
	 * @param menuid
	 * @param modal
	 * @return
	 */
	@RequestMapping(value = "list/{menuid}/", method = RequestMethod.GET)
	public String list(@PathVariable String menuid, ModelMap modal) {
		modal.addAttribute("menuid", menuid);
		return "alarm/status/list";
	}

	/**
	 * 地图页面展示卡口资产状态分布
	 * 
	 * @param menuid
	 * @param modal
	 * @return
	 */
	@RequestMapping(value = "map/{menuid}/", method = RequestMethod.GET)
	public String map(@PathVariable String menuid, ModelMap modal) {
		modal.addAttribute("menuid", menuid);
		return "alarm/status/maplist";
	}

	/**
	 * 关联device表查询资产状态
	 * 
	 * @param menuid
	 * @param modal
	 * @return
	 */
	@RequestMapping(value = "maplist/{menuid}/")
	@ResponseBody
	public List<AssetInfo> mapList(@PathVariable String menuid, ModelMap modal) {
		return assetInfoService.getAllMapList();
	}

	/**
	 * 获取infowindow信息
	 * 
	 * @param id
	 * @param modal
	 * @return
	 */
	@RequestMapping(value = "infowindow/{id}/")
	public String infowindow(@PathVariable String id, ModelMap modal) {
		modal.addAttribute("info", assetInfoService.getById(id));
		return "alarm/status/mapInfowindow";
	}

	// 资产监测列表
	@SuppressWarnings("unused")
	@RequestMapping(value = "search/{type}/{menuid}/")
	public String list(@PathVariable String type, @PathVariable String menuid, ModelMap model, @RequestParam(value = "sortType", defaultValue = "ASSETNAME") String sortType, @RequestParam(value = "page", defaultValue = "0") int pageNumber, ServletRequest request) {
		logger.info("正在进行设备状态监测列表查询。。。。。。");

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("assettype", type);

		Page page = null;
		if (type.equals("ftp") || type.equals("database") || type.equals("project")) {

			// searchParams.put("sortType", "POWER_STATUS desc");

			page = assetInfoService.getListByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE, sortType);

		} else if (type.equals("cabinet")) {


			List<ShowIcabinetStatus> status = new ArrayList<>();
			String assetName = "", assetstatus = "", orgID = "";
			if (searchParams.containsKey("assetname"))
				assetName = searchParams.get("assetname").toString();
			if (searchParams.containsKey("assetstatus"))
				assetstatus = searchParams.get("assetstatus").toString();
			if (searchParams.containsKey("orgId"))
				orgID = searchParams.get("orgId").toString();
			int totalCount = getCabinetStatus(assetName, assetstatus, orgID, pageNumber, Page.DEFAULT_PAGE_SIZE, status);

			page = Page.getPage(totalCount, status, pageNumber, Page.DEFAULT_PAGE_SIZE);
		
			/*
			//根据部门进行查询智能机柜信息
			List<ShowIcabinetStatus> status = new ArrayList<>();
			String assetName = "", assetstatus = "", orgID = "";
			int totalCounts = 0;
			if (searchParams.containsKey("assetname"))
				assetName = searchParams.get("assetname").toString();
			if (searchParams.containsKey("assetstatus"))
				assetstatus = searchParams.get("assetstatus").toString();
			if (searchParams.containsKey("orgIds"))
				orgID = searchParams.get("orgIds").toString();
			
	        String[] orgIDs = orgID.split(",");
	        for(int i = 0;i < orgIDs.length; i++){
	        	orgID = orgIDs[i];
				List<ShowIcabinetStatus> icabinetStatus = new ArrayList<>();
				int totalCount = getCabinetStatus(assetName, assetstatus, orgID, pageNumber, Page.DEFAULT_PAGE_SIZE, icabinetStatus);
				totalCounts = totalCounts + totalCount;
				status.addAll(icabinetStatus);
				icabinetStatus.clear();
				totalCount = 0;

	        }
			//int totalCount = getCabinetStatus(assetName, assetstatus, orgID, pageNumber, Page.DEFAULT_PAGE_SIZE, status);

			page = Page.getPage(totalCounts, status, pageNumber, Page.DEFAULT_PAGE_SIZE);
		*/} else {
			String deptid = getCurrentUser().getDeptId();
			if (deptid.equals("00")) {

				// 查出数据库中所有的数据
				if (searchParams.containsKey("assetname") && !searchParams.containsKey("orgIds")) {
					// String assetname="";
					String assetname = searchParams.get("assetname").toString();
					page = assetInfoService.getListByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE, sortType);

				} else {

					String assetstatus="",assettype="",orgIds="",assetname="";
					int totalCounts = 0;
					if (searchParams.containsKey("assetstatus"))
						assetstatus = searchParams.get("assetstatus").toString();
					if (searchParams.containsKey("assettype"))
						assettype=searchParams.get("assettype").toString();
					if (searchParams.containsKey("orgIds")){
						orgIds=searchParams.get("orgIds").toString();
					}
					if (searchParams.containsKey("assetname")){
						assetname=searchParams.get("assetname").toString();
					} 
					
					AssetInfo assetInfo = new AssetInfo();
					List<AssetInfo> assetInfoLists = new ArrayList<AssetInfo>();

					String[] orgIDs = orgIds.split(",");
					for(int i = 0;i<orgIDs.length ;i++){
						assetInfo.setAssettype(assettype);
						assetInfo.setAssetstatus(assetstatus);
						assetInfo.setOrgId(orgIDs[i]);
						assetInfo.setAssetname(assetname);
						
						List<AssetInfo> assetInfoList = assetInfoService.findAllAsset(assetInfo);
						
						int totalCount = assetInfoList.size();
						totalCounts = totalCounts + totalCount;
						totalCount = 0;
						
						assetInfoLists.addAll(assetInfoList);
						assetInfoList.clear();
						
					}
					
						
					/*assetInfo.setAssettype(assettype);
					assetInfo.setAssetstatus(assetstatus);
					assetInfo.setOrgId(orgId);*/
						
					/*List<AssetInfo> assetInfoList = assetInfoService.findAllAsset(assetInfo);
						
					int totalCount = assetInfoList.size();*/

					// 创建list集合分别放入5种不同情况的数据
					List<AssetInfo> newListOne = new ArrayList<AssetInfo>();
					List<AssetInfo> newListTwo = new ArrayList<AssetInfo>();
					List<AssetInfo> newListThree = new ArrayList<AssetInfo>();
					List<AssetInfo> newListFour = new ArrayList<AssetInfo>();
					List<AssetInfo> newListFive = new ArrayList<AssetInfo>();
					// 新加
					List<AssetInfo> newListSix = new ArrayList<AssetInfo>();

					// AssetInfo assetInfo = new AssetInfo();
					for (int i = 0; i < assetInfoLists.size(); i++) {
						assetInfo = assetInfoLists.get(i);
						String netStatus = assetInfo.getNetStatus();
						String dataStatus = assetInfo.getDataStatus();

						if (("3").equals(netStatus) && ("3").equals(dataStatus)) {
							newListOne.add(assetInfo);
						} else if (("1").equals(netStatus) && ("3").equals(dataStatus) || ("3").equals(netStatus) && ("1").equals(dataStatus)) {
							newListTwo.add(assetInfo);
						} else if (("0").equals(netStatus) && ("3").equals(dataStatus) || ("3").equals(netStatus) && ("0").equals(dataStatus)) {
							newListThree.add(assetInfo);
						} else if (("0").equals(netStatus) && ("0").equals(dataStatus)) {
							newListFour.add(assetInfo);
						} else if (("1").equals(netStatus) && ("0").equals(dataStatus) || ("0").equals(netStatus) && ("1").equals(dataStatus)) {
							newListFive.add(assetInfo);
						} else if (("1").equals(netStatus) && ("1").equals(dataStatus)) {
							newListSix.add(assetInfo);
						}
						assetInfo = null;

					}
					// 清空原有pageList里面的数据
					assetInfoLists.clear();

					// 将新排序的assetInfo对象放入pageList里面
					assetInfoLists.addAll(newListOne);
					assetInfoLists.addAll(newListTwo);
					assetInfoLists.addAll(newListThree);
					assetInfoLists.addAll(newListFour);
					assetInfoLists.addAll(newListFive);
					assetInfoLists.addAll(newListSix);

					// 进行分页
					int pageNum = 0;

					if (pageNumber == 0) {
						pageNumber = 1;
						pageNum = 15;
					}
					if (pageNumber * 15 > assetInfoLists.size()) {
						pageNum = assetInfoLists.size();
					} else {
						pageNum = pageNumber * 15;
					}

					page = Page.getPage(totalCounts, assetInfoLists.subList((pageNumber) * 15 - 15, pageNum), pageNumber, Page.DEFAULT_PAGE_SIZE);

				}

				/*
				 * sortType = "NET_STATUS desc"; page = assetInfoService.getListByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE, sortType);
				 */

			} else {
				// searchParams.put("sortType", "POWER_STATUS desc");

				page = assetInfoService.getListByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE, sortType, menuid);

			}
		}

		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("cabinet_pattern", pattern);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		AssetInfo asset = new AssetInfo();
		
		// 部门
		JsonArray departmentjsonArray = departmentService
				.getDeptJsonArray(menuid);
		model.addAttribute("departmentjsonArray", departmentjsonArray
				.toString());
		
		try {
			BeanUtils.populate(asset, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("asset", asset);
		// 资产类型
		model.addAttribute("assetstatusList", dicService.getDicByType(DicType.ASSETSTATUS));
		return "alarm/status/search";
	}

	@RequestMapping(value = "showTree/", method = RequestMethod.POST)
	public void showTree(@RequestParam(value = "id", defaultValue = "0") String id, ServletResponse resp) {
		logger.info("正在构造资产信息树。。。。。。");
		JsonArray jsonArray = new JsonArray();
		JsonObject json = null;
		if (id.equals("0")) {
			json = new JsonObject();
			json.addProperty("id", "00");
			json.addProperty("pid", "0");
			json.addProperty("name", "资产信息");
			json.addProperty("isParent", id.equals("0") ? true : false);
			jsonArray.add(json);
		} else if (id.equals("00")) {
			json = new JsonObject();
			json.addProperty("id", "01");
			json.addProperty("pid", "00");
			json.addProperty("name", "场外资产");
			json.addProperty("isParent", id.equals("00") ? true : false);
			jsonArray.add(json);
			json = new JsonObject();
			json.addProperty("id", "02");
			json.addProperty("pid", "00");
			json.addProperty("name", "场内资产");
			json.addProperty("isParent", id.equals("00") ? true : false);
			jsonArray.add(json);
		} else if (id.equals("01")) {
			json = new JsonObject();
			json.addProperty("id", "001");
			json.addProperty("pid", "01");
			json.addProperty("name", "卡口设备");
			json.addProperty("isParent", false);
			jsonArray.add(json);
			json = new JsonObject();
			json.addProperty("id", "002");
			json.addProperty("pid", "01");
			json.addProperty("name", "监控设备");
			json.addProperty("isParent", false);
			jsonArray.add(json);
		} else {
			json = new JsonObject();
			json.addProperty("id", "005");
			json.addProperty("pid", "02");
			json.addProperty("name", "服务器");
			json.addProperty("isParent", false);
			jsonArray.add(json);
		}
		logger.debug("response json:{0}", jsonArray.toString());
		try {
			resp.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

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
		AssetInfo asset = assetInfoService.getById(id);
		model.addAttribute("asset", asset);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);

		if (asset != null && asset.getIp() != null && !"".equals(asset.getIp())) {
			ServerStatus status = assetInfoService.selectServerStatusByIp(asset.getIp());
			model.addAttribute("status", status);
			if (status == null) {
				return "alarm/status/view";
			}
			List<XmlDiskInfo> dlist = new ArrayList<XmlDiskInfo>();
			String diskInfo = status.getDiskinfo();
			String[] darry = diskInfo.split(",");
			for (String info : darry) {
				String[] infoArray = info.split(":");
				if (infoArray.length == 5) {
					XmlDiskInfo vo = new XmlDiskInfo();
					vo.setDiskName(infoArray[0]);
					vo.setUsePercent(infoArray[2]);
					vo.setUsed(infoArray[3]);
					vo.setSize(infoArray[4]);
					dlist.add(vo);
				}
			}
			model.addAttribute("statusdlist", dlist);

			List<ServerStatusProcess> processList = assetInfoService.findProcessByIp(asset.getIp());
			int processNum = processList.size();
			model.addAttribute("processList", processList);
			model.addAttribute("processNum", processNum);
		}
		return "alarm/status/view";
	}

	@RequestMapping(value = "view1/{id}/{menuid}/", method = RequestMethod.GET)
	public String view1(@PathVariable("id") String id, @PathVariable("menuid") String menuid, String page, Model model) {

		return "alarm/status/view1";
	}

	/***
	 * 实时获取状态值
	 * 
	 * @param req
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "getStatusInfo/{ip}/")
	@ResponseBody
	public void getStatusInfo(HttpServletResponse resp, @PathVariable String ip) {
		ServerStatus status = assetInfoService.selectServerStatusByIp(ip);
		if (status != null) {
			String cpu = status.getCpuinfo();
			String mem = status.getMemoryinfo();
			try {
				JsonObject object = new JsonObject();
				object.addProperty("cpu", cpu);
				object.addProperty("mem", mem);
				resp.getWriter().write(object.toString());
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}

	/**
	 * 调用存储过程查询机柜状态信息
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param statusList
	 * @return
	 */

	public int getCabinetStatus(String assetName, String assetstatus, String orgid, int pageNo, int pageSize, List<ShowIcabinetStatus> statusList) {
		int rowCount = 0;
		String sql = "{call sp_icabinet_status_get(?,?,?,?,?,?,?)}";
		DataSource ds = (DataSource) ApplicationContextHolder.getBean("dataSource");
		try (Connection conn = ds.getConnection(); CallableStatement stat = conn.prepareCall(sql)) {
			stat.setString(1, assetName);
			stat.setString(2, assetstatus);
			stat.setInt(3, pageNo);
			stat.setInt(4, pageSize);
			stat.setString(5, orgid);
			stat.registerOutParameter(6, Types.INTEGER);
			stat.registerOutParameter(7, Types.CLOB);
			stat.execute();
			rowCount = stat.getInt(6);
			BufferedReader br = new BufferedReader(stat.getClob(7).getCharacterStream());
			StrBuilder xml = new StrBuilder();
			String row = br.readLine();
			while (StringUtils.isNotEmpty(row)) {
				xml.appendln(row);
				row = br.readLine();
			}
			SAXReader sr = new SAXReader();
			try (InputStream in = new ByteArrayInputStream(xml.toString().getBytes("UTF-8"))) {
				Document document = sr.read(in);
				Element root = document.getRootElement();
				List<Element> elementList = root.elements();
				for (Element e : elementList) {
					ShowIcabinetStatus status = new ShowIcabinetStatus();
					status.setIp(e.elementText("ip"));
					status.setAssetname(e.elementText("name"));
					status.setACcurrent(e.elementText("current"));
					status.setACvoltage(e.elementText("voltage"));
					status.setAssetcode(e.elementText("code"));
					status.setAssetstatus(e.elementText("status"));
					status.setHumidity(e.elementText("humidity"));
					status.setTemperature(e.elementText("temperature"));
					status.setSmoke(e.elementText("smoke"));
					status.setShock(e.elementText("shock"));
					status.setId(e.elementText("code"));
					status.setAssettype(e.elementText("type"));
					status.setACterminal(e.elementText("ac"));
					status.setACcurrentvalue(e.elementText("v_socket_current"));
					status.setACvoltagevalue(e.elementText("v_socket_voltage"));
					status.setHumidityvalue(e.elementText("v_humidity"));
					status.setTemperaturevalue(e.elementText("v_temperature"));
					status.setShockvalue(e.elementText("v_shock"));
					status.setServiceid(e.elementText("serviceid"));
					status.setPayoutstatus(e.elementText("payoutstatus"));
					status.setOrgName(e.elementText("orgname"));
					status.setPowerStatus(e.elementText("power_status"));
					status.setNetStatus(e.elementText("net_status"));
					statusList.add(status);
				}
			} catch (IOException | DocumentException e1) {

				e1.printStackTrace();
			}
		} catch (SQLException | IOException e2) {
			e2.printStackTrace();
		}
		return rowCount;
	}

	/***
	 * 导出
	 * 
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "exportXls/{menuid}/{type}/", method = RequestMethod.GET)
	public void exportXls(@PathVariable String menuid, @PathVariable String type,String orgId,HttpServletResponse response, @RequestParam(value = "sortType", defaultValue = "ASSETNAME") String sortType, HttpServletRequest request) {
		// 3 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(3);
		// 接收前台参数
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		orgId = request.getParameter("orgId");
		searchParams.put("assettype", type);
		searchParams.put("orgId", orgId);

		List<AssetInfo> list = new ArrayList<AssetInfo>();
		List<ShowIcabinetStatus> listCabinet = null;
		List<AssetInfo> lists =  new ArrayList<AssetInfo>();

		
		Page page = null;
		if (type.equals("cabinet")) {
			List<ShowIcabinetStatus> status = new ArrayList<>();
			String assetName = "", assetstatus = "";
			if (searchParams.containsKey("assetname"))
				assetName = searchParams.get("assetname").toString();
			if (searchParams.containsKey("assetstatus"))
				assetstatus = searchParams.get("assetstatus").toString();
			if (searchParams.containsKey("orgId"))
				orgId = searchParams.get("orgId").toString();
				orgId="";
			int totalCount = getCabinetStatus(assetName, assetstatus, orgId, 0, 5000, status);
			page = Page.getPage(totalCount, status, 0, 5000);
			listCabinet = page.getResult();
		} else {

			AssetInfo assetInfo = new AssetInfo();
			String[] orgIDs = orgId.split(",");
			for(int i = 0;i<orgIDs.length ;i++){
				assetInfo.setAssettype(type);
				assetInfo.setOrgId(orgIDs[i]);
				assetInfo.setAssetstatus("");
				assetInfo.setAssetname("");

				list = assetInfoService.findAllAsset(assetInfo);
				lists.addAll(list);
				list.clear();
			}
			
			//AssetInfo assetInfo = new AssetInfo();
			//assetInfo.setAssettype(type);
			//assetInfo.setAssetstatus(status);
			//assetInfo.setOrgId(orgId);
			//assetInfo.setAssetstatus("");
			//list = assetInfoService.findAllAsset(assetInfo);
			
			/*page = assetInfoService.getListByCondition(searchParams, 0, 5000, sortType);
			list = page.getResult();*/
		}

		List result = new ArrayList();
		String xlsName = "";
		String xlsMode = "";

		// 设置内容
		switch (type) {
		case "device":
			xlsName = "卡口设备状态";
			xlsMode = "devicestatus_exp.xls";
			for (int i = 0; i < lists.size(); i++) {
				AssetInfo assetInfo = lists.get(i);
				// 封装数组
				FactoryInfo factoryInfo = factoryInfoService.getById(assetInfo.getServiceid());
				String factoryname = "";
				if(factoryInfo != null){
					factoryname = factoryInfo.getName();
				}else{
					factoryname = "";
				}
				
				Object[] values = new Object[] { 
					i + 1, 
					assetInfo.getAssetcode(),
					assetInfo.getAssetname(), 
					assetInfo.getIp(), 
					assetInfo.getOrgName(), 
					assetInfo.getPowerStatus() != null && assetInfo.getPowerStatus().equals("1") ? "正常" : "异常", 
					assetInfo.getNetStatus() != null && assetInfo.getNetStatus().equals("1") ? "正常" : "异常", 
					assetInfo.getDataStatus() != null && assetInfo.getDataStatus().equals("1") ? "正常" : "异常", 
					//assetInfo.getEngineering(),
					
					//导出增加服务厂商
					factoryname 				
				};
				result.add(values);
			}
			break;
		case "cabinet":
			xlsName = "智能机柜设备状态";
			if(pattern.equals("1")){
				xlsMode = "haikang_cabinetstatus_exp.xls";
			}else{
				xlsMode = "cabinetstatus_exp.xls";
			}
			
			for (int i = 0; i < listCabinet.size(); i++) {
				ShowIcabinetStatus showIcabinetStatus = listCabinet.get(i);
				// 170531135954965ba567
				FactoryInfo factoryInfo = factoryInfoService.getById(showIcabinetStatus.getServiceid());
				// 封装数组
				if(pattern.equals("1")){
					Object[] values = new Object[] {
							i + 1,
							showIcabinetStatus.getAssetcode(),
							showIcabinetStatus.getAssetname(), 
							showIcabinetStatus.getIp(), 
							//showIcabinetStatus.getOrgName(),
							showIcabinetStatus.getTemperature() != null && showIcabinetStatus.getTemperature().equals("1") ? "正常" : "异常", 
							showIcabinetStatus.getHumidity() != null && showIcabinetStatus.getHumidity().equals("1") ? "正常" : "异常",
							showIcabinetStatus.getNetStatus() != null && showIcabinetStatus.getNetStatus().equals("1") ? "正常" : "异常", 
    					    showIcabinetStatus.getPowerStatus() != null && showIcabinetStatus.getPowerStatus().equals("1") ? "正常" : "异常", 
							showIcabinetStatus.getSmoke() != null && showIcabinetStatus.getSmoke().equals("1") ? "正常" : "异常", 
							showIcabinetStatus.getShock() != null && showIcabinetStatus.getShock().equals("1") ? "正常" : "异常",
							showIcabinetStatus.getACterminal() != null && showIcabinetStatus.getACterminal().equals("1") ? "正常" : "异常",
							(showIcabinetStatus.getACcurrent()!= null && showIcabinetStatus.getACcurrent().equals("1"))&&(showIcabinetStatus.getACvoltagevalue()!= null && showIcabinetStatus.getACvoltagevalue().equals("1")) ? "正常" : "异常",
							factoryInfo.getName() 
					};
					result.add(values);
				}else if(pattern.equals("2")){
					Object[] values = new Object[] {
							i + 1,
							showIcabinetStatus.getAssetcode(),
							showIcabinetStatus.getAssetname(), 
							showIcabinetStatus.getIp(), 
							//showIcabinetStatus.getOrgName(),
							showIcabinetStatus.getTemperature() != null && showIcabinetStatus.getTemperature().equals("1") ? "正常" : "异常", 
							showIcabinetStatus.getHumidity() != null && showIcabinetStatus.getHumidity().equals("1") ? "正常" : "异常",
							showIcabinetStatus.getNetStatus() != null && showIcabinetStatus.getNetStatus().equals("1") ? "正常" : "异常", 
						    showIcabinetStatus.getPowerStatus() != null && showIcabinetStatus.getPowerStatus().equals("1") ? "正常" : "异常", 
//							showIcabinetStatus.getSmoke() != null && showIcabinetStatus.getSmoke().equals("1") ? "正常" : "异常", 
//							showIcabinetStatus.getShock() != null && showIcabinetStatus.getShock().equals("1") ? "正常" : "异常",
//							showIcabinetStatus.getACterminal() != null && showIcabinetStatus.getACterminal().equals("1") ? "正常" : "异常",
//							showIcabinetStatus.getACvoltage() != null && showIcabinetStatus.getACvoltage().equals("1") ? "正常" : "异常", 
							factoryInfo.getName() 
					};
					result.add(values);
				}
				
			}
			break;
		case "server":

			xlsName = "服务器设备状态";
			xlsMode = "serverstatus_exp.xls";

			for (int i = 0; i < lists.size(); i++) {
				AssetInfo assetInfo = lists.get(i);
				FactoryInfo factoryInfo = factoryInfoService.getById(assetInfo.getFactoryId());

				// 封装数组
				Object[] values = new Object[] { 
						i + 1, 
						assetInfo.getAssetcode(),
						assetInfo.getAssetname(), 
						assetInfo.getIp(), 
						assetInfo.getOrgName(),
						assetInfo.getCpuStatus() != null && assetInfo.getCpuStatus().equals("1") ? "正常" : "异常", 
						assetInfo.getMemoryStatus() != null && assetInfo.getMemoryStatus().equals("1") ? "正常" : "异常", 
						assetInfo.getDiskStatus() != null && assetInfo.getDiskStatus().equals("1") ? "正常" : "异常", 
						//assetInfo.getEngineering()
						factoryInfo.getName() 

				};
				result.add(values);
			}
			break;
		case "database":
		case "ftp":
		case "project":
			for (int i = 0; i < lists.size(); i++) {
				AssetInfo assetInfo = lists.get(i);
				FactoryInfo factoryInfo = factoryInfoService.getById(assetInfo.getServiceid());

				// 封装数组
				Object[] values = new Object[] { 
						i + 1, 
						assetInfo.getAssetcode(),
						assetInfo.getAssetname(), 
						assetInfo.getIp(), 
						assetInfo.getNetStatus() != null && assetInfo.getNetStatus().equals("1") ? "正常" : "异常", 
						assetInfo.getDataStatus() != null && assetInfo.getDataStatus().equals("1") ? "正常" : "异常", 
						//assetInfo.getEngineering() 
						factoryInfo.getName() 

				};
				result.add(values);
			}
			break;

		default:
			break;
		}

		if (type.equals("database")) {
			xlsName = "数据库设备状态";
			xlsMode = "databasestatus_exp.xls";
		} else if (type.equals("ftp")) {
			xlsName = "FTP设备状态";
			xlsMode = "ftpstatus_exp.xls";
		} else if (type.equals("project")) {
			xlsName = "平台设备状态";
			xlsMode = "projectstatus_exp.xls";
		}

		String nowtimeStr = DateUtil.dateFormatToString(new java.util.Date(), "yyyy年MM月dd日HH时mm分ss秒");
		export.setFileName(xlsName + "一览表[" + nowtimeStr + "].xls");
		export.exportExcelFileStream("xls" + File.separator + xlsMode, null, result, response);
	}

}
