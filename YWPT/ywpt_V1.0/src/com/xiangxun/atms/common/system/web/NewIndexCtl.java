/**
 * 
 */
package com.xiangxun.atms.common.system.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.google.gson.JsonObject;
import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.log.service.OperateLogService;
import com.xiangxun.atms.common.log.service.SystemLogService;
import com.xiangxun.atms.common.log.vo.OperationLog;
import com.xiangxun.atms.common.log.vo.SystemLog;
import com.xiangxun.atms.common.road.service.RoadInfoService;
import com.xiangxun.atms.common.system.service.ParamsService;
import com.xiangxun.atms.common.system.service.ResourceService;
import com.xiangxun.atms.common.system.service.SystemLoadService;
import com.xiangxun.atms.common.system.vo.SystemParams;
import com.xiangxun.atms.common.system.vo.SystemResource;
import com.xiangxun.atms.common.user.service.SkinService;
import com.xiangxun.atms.common.user.service.UserService;
import com.xiangxun.atms.common.user.type.MenuType;
import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.framework.base.ApplicationContextHolder;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.compnents.fusioncharts.MsLine2DChart;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.resource.MessageResources;
import com.xiangxun.atms.framework.security.OperatorDetails;
import com.xiangxun.atms.framework.security.SpringSecurityUtils;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.IDDStorage;
import com.xiangxun.atms.framework.util.JsonUtil;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.util.ZhongWenToPinYin;
import com.xiangxun.atms.module.eventalarm.domain.AlarmLog;
import com.xiangxun.atms.module.eventalarm.domain.ExceptionInfo;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderLog;
import com.xiangxun.atms.module.eventalarm.service.AlarmLogService;
import com.xiangxun.atms.module.eventalarm.service.EventtypeInfoService;
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
import com.xiangxun.atms.module.property.domain.ShowIcabinetStatus;
import com.xiangxun.atms.module.property.domain.VideoInfo;
import com.xiangxun.atms.module.property.service.AssetInfoService;
import com.xiangxun.atms.module.property.service.CabInfoService;
import com.xiangxun.atms.module.property.service.DatabaseInfoService;
import com.xiangxun.atms.module.property.service.DeviceCompanyInfoService;
import com.xiangxun.atms.module.property.service.DeviceInfoService;
import com.xiangxun.atms.module.property.service.FtpInfoService;
import com.xiangxun.atms.module.property.service.ProjectInfoService;
import com.xiangxun.atms.module.property.service.PropertyPercentService;
import com.xiangxun.atms.module.property.service.ServerInfoHisService;
import com.xiangxun.atms.module.property.service.ServerInfoService;
import com.xiangxun.atms.module.property.service.VideoInfoService;
import com.xiangxun.atms.module.question.domain.MonthReport;
import com.xiangxun.atms.module.question.domain.QuestionInfo;
import com.xiangxun.atms.module.question.domain.QuestionScheme;
import com.xiangxun.atms.module.question.service.QuestionInfoService;
import com.xiangxun.atms.module.question.service.QuestionSchemeService;
import com.xiangxun.atms.module.question.service.TrendCountService;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.service.FactoryContactService;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 新框架主页处理
 * 
 * @author kouyunhao
 * @version 1.0 May 12, 2014
 */
@Controller
@RequestMapping("home/newindex")
public class NewIndexCtl extends BaseCtl {

	@Resource
	ResourceService resourceService;
	@Resource
	FreeMarkerConfigurer freeMarkerConfigurer;
	@Resource
	SystemLogService systemLogService;
	@Resource
	UserService userService;
	@Resource
	OperateLogService operateLogService;
	@Resource
	SkinService skinService;
	@Resource
	SystemLoadService systemLoadService;
	@Resource
	DeviceInfoService deviceInfoService;
	@Resource
	DepartmentService departmentService;
	@Resource
	RoadInfoService roadInfoService;
	@Resource
	DicService dicService;
	@Resource
	DeviceCompanyInfoService deviceCompanyInfoService;
	@Resource
	VideoInfoService videoInfoService;
	@Resource
	ServerInfoService serverInfoService;

	@Resource
	FactoryInfoService factoryInfoService;

	@Resource
	QuestionInfoService questionInfoService;

	@Resource
	AlarmLogService alarmLogService;

	@Resource
	WorkorderInfoService workorderInfoService;

	@Resource
	QuestionSchemeService questionSchemeService;

	@Resource
	PublicMethodService publicMethodService;

	@Resource
	ExceptionInfoService exceptionInfoService;

	@Resource
	WorkorderLogService workorderLogService;

	@Resource
	AssetInfoService assetInfoService;

	@Resource
	ServerInfoHisService serverInfoHisService;

	@Resource
	DatabaseInfoService databaseInfoService;

	@Resource
	ProjectInfoService projectInfoService;

	@Resource
	FtpInfoService ftpInfoService;

	@Resource
	FactoryContactService factoryContactService;

	@Resource
	EventtypeInfoService eventtypeInfoService;

	@Resource
	TrendCountService trendCountService;

	@Resource
	PropertyPercentService propertyPercentService;

	@Resource
	ParamsService paramsService;

	@Resource
	CabInfoService cabInfoService;
	@Value("${cabinet.pattern}")
    String pattern; 
	// FLASH图标颜色数组
	private String color[] = { "FFCC33", "51DCFF", "5F96FC", "8BBA00", "FF8E46", "008E8E", "D64646", "8E468E", "588526", "B3AA00", "008ED6", "9D080D", "A186BE", "346DB2", "C13A36", "A02F2B", "7C9D35", "6A4A8D", "2B8CAC", "D2721E", "7792C1", "BF7B7A", "A6BC7C", "9183A7" };

	/***
	 * 处理首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "main/", method = RequestMethod.GET)
	public String desktop(ModelMap model, @RequestParam(value = "sortType", defaultValue = "OPERATION_TIME desc") String sortType, @RequestParam(value = "page", defaultValue = "0") int pageNumber) {
		// 用户信息
		model.addAttribute("user", getCurrentUser());
		model.addAttribute("opratorLog", systemLogService.getLastLogInfo(getCurrentUser()));
		List<String> userRole = userService.getRoleNameListByUser(getCurrentUserId());
		model.addAttribute("userRole", userRole.get(0));

		// 日志信息
		deviceLog(model, "ASSETNAME", pageNumber, 200);
		videoLog(model, "ASSETNAME", pageNumber, 200);
		serverLog(model, "ASSETNAME", pageNumber, 100);
		databaseLog(model, "ASSETNAME", pageNumber, 100);
		cabinetLog(model, "ASSETNAME", pageNumber, 100);
		ftpLog(model, "ASSETNAME", pageNumber, 100);
		projectLog(model, "ASSETNAME", pageNumber, 100);
		alarmLog(model, sortType, pageNumber, 7);
		questionLog(model, sortType, pageNumber, 7);
		workorderLog(model, sortType, pageNumber, 7);
		errorLog(model, sortType, pageNumber, 6);

		OperatorDetails userInfo = SpringSecurityUtils.getCurrentUser();
		if (userInfo == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		model.addAttribute("userName", userInfo.getRealName());

		String jumpUrl = "home/getsubmenu/";
		List<SystemResource> menus = resourceService.getMenusByUserId(userInfo.getId(), MenuType.MODULE);
		List<SystemResource> oneLevel = new ArrayList<SystemResource>();
		for (SystemResource menu : menus) {
			// 设置页面图片名称
			menu.setPicName(ZhongWenToPinYin.getPinYin(menu.getName()).trim().toLowerCase());
			// 寻找一级菜单
			if ("0".equals(menu.getParentid())) {
				menu.setContent(jumpUrl + menu.getId());
				oneLevel.add(menu);
			}
		}
		model.addAttribute("menus", oneLevel);

		// 获取资产监测menuid
		String menuid = "";
		List<SystemResource> list = resourceService.findAll(false);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCode().equals("ZICHANJIANCE")) {
				menuid = list.get(i).getId();
				break;
			}
		}
		model.addAttribute("menuid", menuid);

		// 所有资产数量柱状图
		List<AssetInfo> countByTypeList = assetInfoService.countByType();
		for (AssetInfo info : countByTypeList) {
			String assettype = info.getAssettype();
			if ("project".equals(assettype)) {
				info.setAssettype("平台");
			}
			if ("server".equals(assettype)) {
				info.setAssettype("服务器");
			}
			if ("cabinet".equals(assettype)) {
				info.setAssettype("智能机柜");
			}
			if ("device".equals(assettype)) {
				info.setAssettype("卡口");
			}
			if ("database".equals(assettype)) {
				info.setAssettype("数据库");
			}
			if ("ftp".equals(assettype)) {
				info.setAssettype("FTP服务");
			}
		}
		model.addAttribute("countByTypeList", countByTypeList);

		Date d = DateUtil.getPerDay(new java.util.Date(), 9);
		String s = DateUtil.dateFormatToString(d, "yyyy-MM-dd");
		String e = DateUtil.dateFormatToString(new java.util.Date(), "yyyy-MM-dd");

		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("startTime", s);
		searchParams.put("endTime", e);
		// 获取时间段内的所有日期
		List<String> mdays = DateUtil.getAllDatesByDate(s, e);
		List<MonthReport> clist = trendCountService.getLineSpaceReport(searchParams, menuid, s, e);
		boolean noDataFlag = this.getSpaceNodateStatus(clist);
		if (noDataFlag) {
			model.addAttribute("nodata", "nodata");
		} else {
			this.createLineDataXmlMonth(clist, model, mdays);
		}

		// tianbo增加获取系统参数勾选的参数
		List<SystemParams> openInfoList = paramsService.selectByMHName("open");
		// 去除掉value为0的系统参数
		String[] paramsType = new String[] { "opendevice", "openserver", "opendatabase", "openftp", "openproject", "opencabinet" };
		for (int i = openInfoList.size() - 1; i >= 0; i--) {
			SystemParams systemParams = openInfoList.get(i);
			if ("0".equals(systemParams.getValue())) {
				openInfoList.remove(i);
			} else {
				if (!isHave(paramsType, systemParams.getName())) {
					openInfoList.remove(i);
				}
			}
		}

		model.addAttribute("openInfoList", openInfoList);

		// 获得资产健康度
		double percent = propertyPercentService.getAllpercent(1);
		model.addAttribute("percent", percent);
		return "home/newIndex/main";
	}

	public static boolean isHave(String[] strs, String s) {
		/*
		 * 此方法有两个参数，第一个是要查找的字符串数组，第二个是要查找的字符或字符串
		 */
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].indexOf(s) != -1) {// 循环查找字符串数组中的每个字符串中是否包含所有查找的内容
				return true;// 查找到了就返回真，不在继续查询
			}
		}
		return false;// 没找到返回false
	}

	public void operatorLog(ModelMap model, String sortType, int pageNumber, int pageSize) {
		OperatorDetails oper = getCurrentUser();
		SystemLog systemLog = systemLogService.getLastLogInfo(oper);
		List<String> roles = userService.getRoleNameListByUser(getCurrentUserId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("operator", oper.getUsername());
		map.put("sortType", sortType);
		if (systemLog != null) {
			map.put("ip", systemLog.getIpAddress() == null ? "" : systemLog.getIpAddress());
		}
		Page page = operateLogService.getLogsByCondition(map, pageNumber, pageSize, sortType);
		model.addAttribute("operator", oper);
		model.addAttribute("operatorLogin", systemLog);
		model.addAttribute("operator_roles", roles);
		model.addAttribute("operation_list", page);
	}

	/**
	 * 事件告警信息
	 * 
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param pageSize
	 */
	@SuppressWarnings("unchecked")
	public void alarmLog(ModelMap model, String sortType, int pageNumber, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortType", "ALARM_TIME desc");
		Page page = alarmLogService.getListByCondition(map, pageNumber, 7, "ALARM_TIME desc");
		Object obj = page.getResult();
		if (obj != null && obj instanceof List) {
			List<AlarmLog> list = (List<AlarmLog>) obj;
			if (list != null && list.size() != 0) {
				for (AlarmLog log : list) {
					String[] attribute = alarmLogService.findEventAttributes(log.getEventType());
					log.setEventTypeName(attribute[0]);
					log.setEventLevel(attribute[1]);
					log.setAlarmType(attribute[2]);
					log.setAlarmColor(attribute[3]);
				}
			}
		}
		model.addAttribute("alarmLog_list", page);

	}

	/**
	 * 卡口设备检测信息
	 * 
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param pageSize
	 */
	public void deviceLog(ModelMap model, String sortType, int pageNumber, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("assettype", "device");
		Page page = assetInfoService.getListByCondition(map, pageNumber, pageSize, sortType, "");
		model.addAttribute("device_list", page);
	}

	/**
	 * 监控设备检测信息
	 * 
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param pageSize
	 */
	public void videoLog(ModelMap model, String sortType, int pageNumber, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("assettype", "video");
		Page page = assetInfoService.getListByCondition(map, pageNumber, 8, sortType, "");
		model.addAttribute("video_list", page);

	}

	/**
	 * 服务器设备检测信息
	 * 
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param pageSize
	 */
	public void serverLog(ModelMap model, String sortType, int pageNumber, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("assettype", "server");
		Page page = assetInfoService.getListByCondition(map, pageNumber, 8, sortType, "");
		model.addAttribute("server_list", page);

	}

	/**
	 * 数据库检测信息
	 * 
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param pageSize
	 */
	public void databaseLog(ModelMap model, String sortType, int pageNumber, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("assettype", "database");
		Page page = assetInfoService.getListByCondition(map, pageNumber, 8, sortType);
		model.addAttribute("database_list", page);

	}

	/**
	 * 机柜检测信息
	 * 
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param pageSize
	 */
	public void cabinetLog(ModelMap model, String sortType, int pageNumber, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("assettype", "cabinet");
		List<ShowIcabinetStatus> status = new ArrayList<>();
		String assetName = "", assetCode = "", orgID = "";

		int totalCount = getCabinetStatus(assetName, assetCode, orgID, pageNumber, 8, status);

		Page page = Page.getPage(totalCount, status, pageNumber, 8);
		model.addAttribute("cabinet_list", page);
		model.addAttribute("cabinet_pattern",pattern);
	}

	/**
	 * 调用存储过程查询机柜状态信息
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param statusList
	 * @return
	 */

	private int getCabinetStatus(String assetName, String assetCode, String orgid, int pageNo, int pageSize, List<ShowIcabinetStatus> statusList) {
		int rowCount = 0;
		String sql = "{call sp_icabinet_status_get(?,?,?,?,?,?,?)}";
		DataSource ds = (DataSource) ApplicationContextHolder.getBean("dataSource");
		try (Connection conn = ds.getConnection(); CallableStatement stat = conn.prepareCall(sql)) {
			stat.setString(1, assetName);
			stat.setString(2, assetCode);
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

	/**
	 * ftp检测信息
	 * 
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param pageSize
	 */
	public void ftpLog(ModelMap model, String sortType, int pageNumber, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("assettype", "ftp");
		Page page = assetInfoService.getListByCondition(map, pageNumber, 8, sortType);
		model.addAttribute("ftp_list", page);

	}

	/**
	 * 平台检测信息
	 * 
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param pageSize
	 */
	public void projectLog(ModelMap model, String sortType, int pageNumber, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("assettype", "project");
		Page page = assetInfoService.getListByCondition(map, pageNumber, 8, sortType);
		model.addAttribute("project_list", page);

	}

	/**
	 * 问题信息查询
	 * 
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param pageSize
	 */
	public void questionLog(ModelMap model, String sortType, int pageNumber, int pageSize) {
		logger.info("正在进行问题信息查询");

		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page page = questionInfoService.getReport(searchParams, pageNumber, pageSize, "INSERT_TIME DESC", "");

		model.addAttribute("questionlog_list", page);
	}

	/**
	 * 工单信息查询
	 * 
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param pageSize
	 */
	public void workorderLog(ModelMap model, String sortType, int pageNumber, int pageSize) {
		logger.info("正在进行工单信息查询");

		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("sortType", "ASSIGNTIME desc");
		Page page = workorderInfoService.getListByCondition(searchParams, pageNumber, pageSize, "ASSIGNTIME DESC", "");

		// miaoxu 查询颜色
		publicMethodService.setStatusColor(page);

		model.addAttribute("workorderlog_list", page);
	}

	public void loginLog(ModelMap model, String sortType, int pageNumber, int pageSize) {
		logger.info("正在进行登录日志查询");

		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("operatorId", getCurrentUser().getAccount());
		searchParams.put("sortType", sortType);
		Page page = systemLogService.getSystemLogsByCondition(searchParams, pageNumber, pageSize, sortType);

		SystemLog systemLog = new SystemLog();
		try {
			ConvertUtils.register(new DateConverter(), Date.class);
			// 将查询的map转换成对象
			BeanUtils.populate(systemLog, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("loginlog_list", page);
		model.addAttribute("log", systemLog);
	}

	public void errorLog(ModelMap model, String sortType, int pageNumber, int pageSize) {

		logger.info("正在进行异常操作日志查询");
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("operatorId", getCurrentUser().getAccount());
		searchParams.put("sortType", sortType);
		// 添加异常日志的标志
		searchParams.put("isSuccess", "0");
		processOperList(model, sortType, pageNumber, pageSize, searchParams);
	}

	public void workhandleLog(ModelMap model, String sortType, int pageNumber, int pageSize) {

		logger.info("正在进行异常操作日志查询");
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("operatorId", getCurrentUser().getAccount());
		searchParams.put("sortType", sortType);
		// 添加异常日志的标志
		searchParams.put("isSuccess", "0");
		processOperList(model, sortType, pageNumber, pageSize, searchParams);
	}

	/**
	 * @param model
	 * @param menuid
	 * @param sortType
	 * @param pageNumber
	 * @param searchParams
	 */
	private void processOperList(ModelMap model, String sortType, int pageNumber, int pageSize, Map<String, Object> searchParams) {
		Page page = operateLogService.getLogsByCondition(searchParams, pageNumber, pageSize, sortType);

		OperationLog operationLog = new OperationLog();
		try {
			ConvertUtils.register(new DateConverter(), Date.class);
			// 将查询的map转换成对象
			BeanUtils.populate(operationLog, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("errorlog_list", page);
		model.addAttribute("log", operationLog);
	}

	/***
	 * 处理右边主页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getsubmenu/{menuid}", method = RequestMethod.GET)
	@ResponseBody
	public void getsubmenu(@PathVariable String menuid, HttpServletResponse resp, HttpServletRequest request) {
		JsonObject object = new JsonObject();
		OperatorDetails userInfo = SpringSecurityUtils.getCurrentUser();
		if (userInfo == null) {
			object.addProperty("code", "502");
			try {
				resp.getWriter().write(object.toString());
				return;
			} catch (IOException e) {
				logger.error(e);
				return;
			}
		}

		// 检测是否为发布模式
		boolean isRealse = isRealse(request.getSession().getServletContext());
		String resCode = null;
		if (isRealse) {
			MessageResources resource = MessageResources.getMessageInstance(null, null);
			// 获取资源管理菜单编码
			resCode = resource.getMessage("res.code");
		}

		// 获得子系统名称
		SystemResource sys = resourceService.getById(menuid);

		// 获得左边第一级模块
		List<SystemResource> resList = resourceService.getChildMenusByUserId(userInfo.getId(), menuid, MenuType.MODULE);
		this.processMenus(resList);
		// 获得左边第二级功能
		for (SystemResource menu : resList) {
			List<SystemResource> sonResourceList = resourceService.getChildMenusByUserId(userInfo.getId(), menu.getId(), MenuType.MODULE);
			this.processMenus(sonResourceList);
			if (sonResourceList != null && sonResourceList.size() > 0) {
				if (resCode != null) {
					for (int i = 0; i < sonResourceList.size(); i++) {
						if (resCode.toUpperCase().equals(sonResourceList.get(i).getCode().toUpperCase())) {
							sonResourceList.remove(i);
							continue;
						}
					}
				}
				menu.setSonResourceList(sonResourceList);
			} else {
				List<SystemResource> templist = new ArrayList<SystemResource>();
				menu.setSonResourceList(templist);
			}
		}

		String menuStr = null;
		Configuration config = freeMarkerConfigurer.getConfiguration();
		Template template;
		Map<String, List<SystemResource>> menuModel = new HashMap<String, List<SystemResource>>();
		try {
			// 利用freemarker进行字符处理得到菜单
			menuModel.put("menus", resList);
			template = config.getTemplate("/ftl/newindex_menu.ftl");
			menuStr = FreeMarkerTemplateUtils.processTemplateIntoString(template, menuModel);
			// 根据子系统名，生成子系统首页链接
			String aStr = "<img src='../../images/newIndex/ico1.png'>";
			String bStr = "<a href='#' onclick=\"showSysIndex('" + sys.getContent() + "')\" >" + sys.getName() + "首页</a>";
			menuStr = menuStr.replaceAll("系统模块图标", aStr).replaceAll("系统模块首页", bStr);
			logger.debugLine();
			logger.debug(menuStr);
			logger.debugLine();
			object.addProperty("code", "200");
			object.addProperty("content", menuStr);
			if (!StringUtils.isBlank(sys.getContent())) {
				object.addProperty("url", sys.getContent());
			}
			resp.getWriter().write(object.toString());
		} catch (Exception e) {
			logger.error(e);
		}
	}
	// private void processMenus(List<SystemResource> resList) {
	// for (SystemResource menu : resList) {
	// if (!StringUtils.isEmpty(menu.getContent())) {
	// String url = StringUtils.removeEnd(menu.getContent(), "*");
	// logger.debug("菜单url:{0}", url);
	// if (url.indexOf("forward") == -1) {
	// // 如果以/结束，说明其中有其他参数，在url末尾加上menuid
	// if (url.endsWith("/")) {
	// url += menu.getId() + "/";
	// }
	// // 否则直接加上menuid
	// else {
	// url += "/" + menu.getId() + "/";
	// }
	// menu.setContent(url);
	// }
	// } else {
	// menu.setContent("");
	// }
	// menu.setIcon(menu.getIcon() == null ? "" : menu.getIcon());
	// }
	// }

	private void processMenus(List<SystemResource> resList) {
		for (SystemResource menu : resList) {
			if (!StringUtils.isEmpty(menu.getContent())) {
				String url = StringUtils.removeEnd(menu.getContent(), "*");
				logger.info("菜单url:{0}", url);
				if (url.indexOf("forward") == -1) {
					if (url.indexOf("http://") == -1) {
						// 如果以/结束，说明其中有其他参数，在url末尾加上menuid
						if (url.endsWith("/")) {
							url += menu.getId() + "/";
						}
						// 否则直接加上menuid
						else {
							url += "/" + menu.getId() + "/";
						}
					}
				}
				menu.setContent(url);
			} else {
				menu.setContent("");
			}
			menu.setIcon(menu.getIcon() == null ? "" : menu.getIcon());
		}
	}

	/**
	 * 获取右侧更多信息
	 * 
	 * @author kouyunhao
	 * @version 1.0
	 * @param type
	 * @param resp May 23, 2014
	 */
	@RequestMapping(value = "findmore/{type}", method = RequestMethod.GET)
	@ResponseBody
	public void findmore(@PathVariable String type, HttpServletResponse resp) {
		JsonObject json = new JsonObject();
		String url = "";
		List<SystemResource> res = null;
		Map<String, String> typeMap = new HashMap<String, String>();
		typeMap.put("alarm", "告警日志");
		typeMap.put("question", "问题录入");
		typeMap.put("workorder", "工单进度查询");
		typeMap.put("alarmParent", "事件告警管理");
		typeMap.put("workorderParent", "工单管理");
		typeMap.put("questionParent", "问题管理");
		res = resourceService.findAllMenuByKeyWord(typeMap.get(type), null);
		if (res != null && res.size() != 0) {
			url = res.get(0).getContent() + "/" + res.get(0).getId() + "/";
			json.addProperty("url", url);
			List<SystemResource> pres = resourceService.findAllMenuByKeyWord(typeMap.get(type + "Parent"), null);
			json.addProperty("ppid", pres.get(0).getId());
		}
		try {
			resp.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * 获取告警日志详情信息
	 * 
	 * @param id
	 * @param resp
	 */
	@RequestMapping(value = "alarm/log/view/{id}/", method = RequestMethod.GET)
	@ResponseBody
	@LogAspect(desc = "获取告警日志详情信息")
	public void alarmlog(@PathVariable String id, ServletResponse response) {
		try {
			AlarmLog log = alarmLogService.getById(id);
			String[] attribute = alarmLogService.findEventAttributes(log.getEventType());
			log.setEventTypeName(attribute[0]);
			log.setEventLevel(attribute[1]);
			log.setAlarmType(attribute[2]);

			StringBuffer buffer = new StringBuffer();
			buffer.append("<table class=\"table tingche-table table-border-rl table-border-bot\">");
			buffer.append("<tr><td class=\"device_td_bg2\" width=\"15%\">资产名称：</td><td width=\"30%\">" + (log.getDeviceName() == null ? "" : log.getDeviceName()) + "</td> <td class=\"device_td_bg2\" width=\"15%\">资产编号：</td><td width=\"30%\">" + (log.getDeviceCode() == null ? "" : log.getDeviceCode()) + "</td></tr>");
			buffer.append("<tr><td class=\"device_td_bg2\">资产 IP：</td><td>" + (log.getDeviceIp() == null ? "" : log.getDeviceIp()) + "</td><td class=\"device_td_bg2\">事件类别：</td><td>" + (log.getEventTypeName() == null ? "" : log.getEventTypeName()) + "</td></tr>");
			buffer.append("<tr><td class=\"device_td_bg2\">告警级别：</td><td>" + (log.getEventLevel() == null ? "" : log.getEventLevel()) + "</td><td class=\"device_td_bg2\">告警方式：</td><td>" + (log.getAlarmType() == null ? "" : log.getAlarmType()) + "</td></tr>");
			buffer.append("<tr><td class=\"device_td_bg2\">告警时间：</td><td colspan=\"3\">" + DateUtil.dateFormatToString(log.getAlarmTime(), "yyyy-MM-dd HH:mm:ss") + "</td></tr>");
			AssetInfo asset = new AssetInfo();
			if ("device".equals(log.getDeviceType()) || "server".equals(log.getDeviceType())) {
				asset = assetInfoService.getById(log.getDeviceCode());
			} else {
				List<AssetInfo> assetlist = assetInfoService.findByDeviceId(log.getDeviceCode());
				if (null != assetlist && assetlist.size() > 0) {
					asset = assetlist.get(0);
				} else {
					asset = null;
				}
			}
			if (null != asset) {
				if ("server".equals(asset.getAssettype())) {
					if (!"1".equals(asset.getCpuStatus()) || !"1".equals(asset.getMemoryStatus()) || !"1".equals(asset.getDiskStatus())) {
						buffer.append("<tr><td colspan=\"4\" style=\"text-align:center;\">");
						if ("0".equals(asset.getPayoutstatus())) {
							// buffer.append("<a href=\"javascript:showassign('"+asset.getId()+"', '"+asset.getAssettype()+"')\"> <i class=\"icon-share\"></i><span
							// style='text-decoration: underline;color:blue;'>派发工单</span></a>");
							buffer.append("<span style=\"color: green\">未派发</span>");
						} else {
							buffer.append("<span style=\"color: green\">已派发</span>");
						}
						buffer.append("</td></tr>");
					}
				}
				if ("database".equals(asset.getAssettype()) || "ftp".equals(asset.getAssettype()) || "project".equals(asset.getAssettype()) || "device".equals(asset.getAssettype())) {
					if (!"1".equals(asset.getNetStatus()) || !"1".equals(asset.getDataStatus())) {
						buffer.append("<tr><td colspan=\"4\" style=\"text-align:center;\">");
						if ("0".equals(asset.getPayoutstatus())) {
							// buffer.append("<a href=\"javascript:showassign('"+asset.getId()+"', '"+asset.getAssettype()+"')\"> <i class=\"icon-share\"></i><span
							// style='text-decoration: underline;color:blue;'>派发工单</span></a>");
							buffer.append("<span style=\"color: green\">未派发</span>");
						} else {
							buffer.append("<span style=\"color: green\">已派发</span>");
						}
						buffer.append("</td></tr>");
					}
				}
				if ("cabinet".equals(asset.getAssettype())) {
					if (!"0".equals(asset.getCabinetStatus())) {
						buffer.append("<tr><td colspan=\"4\" style=\"text-align:center;\">");
						if ("0".equals(asset.getPayoutstatus())) {
							// buffer.append("<a href=\"javascript:showassign('"+asset.getId()+"', '"+asset.getAssettype()+"')\"> <i class=\"icon-share\"></i><span
							// style='text-decoration: underline;color:blue;'>派发工单</span></a>");
							buffer.append("<span style=\"color: green\">未派发</span>");
						} else {
							buffer.append("<span style=\"color: green\">已派发</span>");
						}
						buffer.append("</td></tr>");
					}
				}
			} else {
				buffer.append("<tr><td colspan=\"4\" style=\"text-align:center;\">");
				buffer.append("<span style=\"color: green\">未转为资产</span>");
				buffer.append("</td></tr>");
			}

			buffer.append("</table>");
			response.getWriter().write(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * 获取待派发工单信息
	 * 
	 * @param id
	 * @param resp
	 */
	@RequestMapping(value = "showassign/{deviceid}/{devicetype}/", method = RequestMethod.GET)
	@ResponseBody
	@LogAspect(desc = "获取待派发工单信息")
	public void showassign(@PathVariable("deviceid") String deviceid, @PathVariable("devicetype") String devicetype, ServletResponse response) {
		try {
			AssetInfo asset = assetInfoService.getById(deviceid);
			deviceid = asset.getDeviceid();
			FactoryInfo factory = null;
			DeviceCompanyInfo company = null;
			List<User> contactList = factoryContactService.findContactList(deviceid);
			setContactData(contactList);

			StringBuffer buffer = new StringBuffer();
			if (contactList == null || contactList.size() == 0) {
				buffer.append("<div class='btn_line'>");
				buffer.append("该资产未设置维护人员！");
				buffer.append("</div>");
				response.getWriter().write(buffer.toString());
				return;
			}

			String deviceId = "";
			if (devicetype.equals("device")) {
				DeviceInfo device = deviceInfoService.getById(deviceid);
				if (device != null) {
					factory = factoryInfoService.getById(device.getFactoryId());
					company = deviceCompanyInfoService.getById(device.getCompanyId());
					deviceId = device.getId();
				}
			} else if (devicetype.equals("server")) {
				ServerInfo server = serverInfoService.getById(deviceid);
				if (server != null) {
					factory = factoryInfoService.getById(server.getFactoryId());
					deviceId = server.getId();
				}

			} else if (devicetype.equals("database")) {
				DatabaseInfo database = databaseInfoService.getById(deviceid);
				factory = factoryInfoService.getById(database.getFactoryId());
			} else if (devicetype.equals("ftp")) {
				FtpInfo ftp = ftpInfoService.getById(deviceid);
				factory = factoryInfoService.getById(ftp.getFactoryId());
			} else if (devicetype.equals("project")) {
				ProjectInfo project = projectInfoService.getById(deviceid);
				factory = factoryInfoService.getById(project.getFactoryId());
			} else if (devicetype.equals("cabinet")) {
				CabInfo cab = cabInfoService.getById(deviceid);
				factory = factoryInfoService.getById(cab.getFactoryId());
			}

			buffer.append("<form class='form-inline' id='input_form' name='input_form' style='margin:0'>");
			buffer.append("<input type='hidden' name='id' value='" + deviceId + "'/>");
			buffer.append("<input type='hidden' id='assetid' name='assetid' value='" + asset.getId() + "'/>");
			buffer.append("<h4 class='xtcs_h4' style='margin:0;'>资产信息详情</h4>");
			buffer.append("<div class='mar_5'>");
			buffer.append("<table class='table table-border-rl table-border-bot bukong-table' width='100%'>");
			buffer.append("<tr>");
			buffer.append("<td class='device_td_bg3' width='10%'>资产名称：</td>");
			buffer.append("<td width='40%'>");
			buffer.append("<input class='required' readonly autocomplete='off' style='width:60%;' name='devicename' value='" + asset.getAssetname() + "' type='text' />");
			buffer.append("</td>");
			buffer.append("<td class='device_td_bg3' width='10%'>资产编号：</td>");
			buffer.append("<td width='40%'>");
			buffer.append("<input class='required' readonly autocomplete='off' style='width:60%;' name='devicecode' value='" + asset.getAssetcode() + "' type='text' />");
			buffer.append("</td>");
			buffer.append("</tr>");
			buffer.append("<tr>");
			buffer.append("<td class='device_td_bg3'>资产型号：</td>");
			buffer.append("<td>");
			String assetmodel = "";
			if (null != asset.getAssetmodel()) {
				assetmodel = asset.getAssetmodel();
			}
			buffer.append("<input readonly autocomplete='off' style='width:60%;' name='assetmodel' value='" + assetmodel + "' type='text' />");
			buffer.append("</td>");
			buffer.append("<td class='device_td_bg3'>资产状态：</td>");
			buffer.append("<td>");
			String assetstatusName = "";
			if (null != asset.getAssetstatus()) {
				assetstatusName = dicService.getNameByKey("Dic", DicType.ASSETSTATUS.toString(), asset.getAssetstatus());
			}
			buffer.append("<input class='required' readonly autocomplete='off' style='width:60%;' name='assetstatus' value='" + assetstatusName + "' type='text' />");
			buffer.append("</td>");
			buffer.append("</tr>");
			buffer.append("<tr>");
			buffer.append("<td class='device_td_bg3'>所在位置：</td>");
			buffer.append("<td>");
			buffer.append("<input class='required' readonly autocomplete='off' style='width:60%;' name='position' value='" + asset.getInstallplace() + "' type='text' />");
			buffer.append("</td>");
			buffer.append("<td class='device_td_bg3'>设备IP：</td>");
			buffer.append("<td>");
			buffer.append("<input class='required' readonly autocomplete='off' style='width:60%;' name='deviceip' value='" + asset.getIp() + "' type='text' />");
			buffer.append("</td>");
			buffer.append("</tr>");
			buffer.append("</table>");
			buffer.append("</div>");

			if ("device".equals(devicetype)) {
				buffer.append("<h4 class='xtcs_h4' style='margin:0;'>建设厂家信息</h4>");
				buffer.append("<div class='mar_5'>");
				buffer.append("<table class='table table-border-rl table-border-bot bukong-table'>");
				buffer.append("<tr>");
				buffer.append("<td class='device_td_bg3'>厂商名称：</td>");
				buffer.append("<td width='40%'>");
				buffer.append("<input class='required' readonly autocomplete='off' style='width:60%;' value='" + company.getName() + "' type='text' />");
				buffer.append("</td>");
				buffer.append("<td class='device_td_bg3'>责 任 人：</td>");
				buffer.append("<td width='40%'>");
				buffer.append("<input class='required' readonly autocomplete='off' style='width:60%;' value='" + company.getContactperson() + "' type='text' />");
				buffer.append("</td>");
				buffer.append("</tr>");
				buffer.append("<tr>");
				buffer.append("<td class='device_td_bg3'>联系电话：</td>");
				buffer.append("<td>");
				buffer.append("<input class='required' readonly autocomplete='off' style='width:60%;' value='" + company.getContactphone() + "' type='text' />");
				buffer.append("</td>");
				buffer.append("<td></td><td></td>");
				buffer.append("</tr>");
				buffer.append("</table>");
				buffer.append("</div>");
			}

			buffer.append("<h4 class='xtcs_h4' style='margin:0;'>服务厂商信息</h4>");
			buffer.append("<div class='mar_5'>");
			buffer.append("<table class='table table-border-rl table-border-bot bukong-table'>");
			buffer.append("<tr>");
			buffer.append("<td class='device_td_bg3'>厂商名称：</td>");
			buffer.append("<td width='40%'>");
			buffer.append("<input class='required' readonly autocomplete='off' style='width:60%;' name='companyname' value='" + factory.getName() + "' type='text' />");
			buffer.append("<input type='hidden' name='companyid' value='" + factory.getId() + "' />");
			buffer.append("</td>");
			buffer.append("<td class='device_td_bg3'>联  系  人：</td>");
			buffer.append("<td width='40%'>");
			buffer.append("<select id='contact' name='contact' style='width:62%;' onchange='showTelephone(this)' class='required'>");
			buffer.append("<option value=''>请选择</option>");
			for (User contact : contactList) {
				buffer.append("<option value='" + contact.getId() + "' userdata='" + contact.getUserdata() + "'>" + contact.getName() + "</option>");
			}
			buffer.append("</select>");
			buffer.append("<font color='red'>*</font>");
			buffer.append("</td>");
			buffer.append("</tr>");
			buffer.append("<tr>");
			buffer.append("<td class='device_td_bg3'>联系电话：</td>");
			buffer.append("<td>");
			buffer.append("<input class='required' readonly autocomplete='off' style='width:60%;' id='telephone' name='telephone' value='' type='text' />");
			buffer.append("<font color='red'>*</font>");
			buffer.append("</td>");
			buffer.append("<td></td><td></td>");
			buffer.append("</tr>");
			buffer.append("<tr>");
			buffer.append("<td class='device_td_bg3'>短信通知：</td>");
			buffer.append("<td class='inputImportent' colspan='3'>");
			buffer.append("<textarea name='messages' id='messages' class='required' maxlength='200' style='WIDTH:85%;height:50px;'></textarea>");
			buffer.append("<font color='red'>*</font> ");
			buffer.append("</td>");
			buffer.append("</tr>");
			buffer.append("</table>");
			buffer.append("</div>");
			buffer.append("<div class='btn_line'>");
			buffer.append("<input id='submit_btn' class='btn btn-info mar_r10' type='button' value='保存' onclick=\"doAssign('" + devicetype + "');\"/>");
			buffer.append("</div>");
			buffer.append("</form>");

			response.getWriter().write(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 工单派发
	 * 
	 * @param workorder
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doAssign/{devicetype}/")
	@LogAspect(desc = "工单派发")
	public String doAdd(@PathVariable String devicetype, WorkorderInfo workorder, DeviceInfo device, VideoInfo video, ServerInfo server, String menuid, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		workorder.setId(UuidGenerateUtil.getUUID());
		String assetid = request.getParameter("assetid");
		if (devicetype.equals("device")) {
			workorder.setOrgid(device.getOrgId());
		} else if (devicetype.equals("video")) {
			workorder.setOrgid(video.getOrgId());
		} else if (devicetype.equals("server")) {
			workorder.setOrgid(server.getOrgId());
		} else {
			workorder.setIsouter("1");
		}
		if (devicetype.equals("database") || devicetype.equals("ftp") || devicetype.equals("project")) {
			workorder.setDevicecode(workorder.getId());
		}
		workorder.setAssetid(assetid);
		workorder.setAssignaccount(getCurrentUserId());
		workorder.setAssigntime(new Date());
		workorderInfoService.save(workorder);

		// 更新资产的派发状态
		AssetInfo assetInfo = assetInfoService.getById(assetid);
		assetInfo.setPayoutstatus("1");
		assetInfoService.updateById(assetInfo);
		device.setIssend("1");
		deviceInfoService.updateByIdSelective(device);
		// 添加日志信息
		WorkorderLog log = new WorkorderLog();
		log.setId(UuidGenerateUtil.getUUID());
		log.setOperator(getCurrentUserId());
		log.setAccount(getCurrentUser().getAccount());
		log.setOpertime(new Date());
		log.setOpercontent("工单派发");
		log.setWorkstatus(WorkorderStatus.ASSIGNED.getValue());
		log.setWorkid(workorder.getId());
		workorderLogService.save(log);
		redirectAttributes.addFlashAttribute("message", "工单派发成功");

		return "redirect:/home/newindex/main/";
	}

	/**
	 * 设置服务厂商联系人jsondata
	 * 
	 * @author kouyunhao
	 * @version 1.0
	 * @param builderList Mar 6, 2014
	 */
	public void setContactData(List<User> contactList) {
		if (contactList != null && contactList.size() != 0) {
			for (User contact : contactList) {
				try {
					contact.setUserdata(JsonUtil.toJson(contact));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/***
	 * 获取问题详情信息
	 * 
	 * @param id
	 * @param resp
	 */
	@RequestMapping(value = "question/log/view/{id}/", method = RequestMethod.GET)
	@ResponseBody
	@LogAspect(desc = "获取问题详情信息")
	public void questionlog(@PathVariable String id, ServletResponse response) {
		try {
			QuestionInfo questionInfo = questionInfoService.getById(id);
			// 方案列表
			Map<String, Object> searchParams = new HashMap<String, Object>();
			searchParams.put("infoId", id);
			List<QuestionScheme> schemeList = questionSchemeService.getReportByInfoId(searchParams, "");

			StringBuffer buffer = new StringBuffer();

			buffer.append("<table class='table tingche-table table-border-rl table-border-bot'>");
			buffer.append("<tr><td class='device_td_bg3'>录入人员：</td><td>" + userService.getById(questionInfo.getOperator()).getName() + "</td><td class='device_td_bg3'>录入时间：</td><td>" + DateUtil.dateFormatToString(questionInfo.getInsertTime(), "yyyy-MM-dd HH:mm:ss") + "</td></tr>");
			buffer.append("<tr><td class='device_td_bg3'>问题标题：</td><td colspan='3'>" + questionInfo.getTitle() + "</td></tr>");
			buffer.append("<tr><td class='device_td_bg3'>问题详情：</td><td colspan='3'><div style='height:26px;width:320px;overflow:hidden;float:left;text-align:left;' title='" + questionInfo.getContent() + "'>" + questionInfo.getContent() + "</div></td></tr>");
			buffer.append("<tr><td colspan='4' style='font-weight: bold;font-size: 14px;'>方案信息</td></tr>");

			buffer.append("<tr><td colspan='4'><table class='table table-striped table-bordered table-condensed table-style' id='table' style='width:99%;'>");
			buffer.append("<thead><tr><th>方案序号</th><th>方案内容</th><th>录入人员</th><th>录入时间</th></tr></thead>");
			buffer.append("<tbody id='tbody'>");
			for (int i = 0; i < schemeList.size(); i++) {
				QuestionScheme scheme = schemeList.get(i);
				buffer.append("<tr>");
				buffer.append("<td>" + scheme.getSchemeNo() + "</td>");
				buffer.append("<td width='20%'><div style='height:26px;width:220px;overflow:hidden;' title='" + scheme.getContent() + "'>" + scheme.getContent() + "</div></td>");
				buffer.append("<td>" + userService.getById(scheme.getOperator()).getName() + "</td>");
				buffer.append("<td>" + DateUtil.dateFormatToString(scheme.getInsertTime(), "yyyy-MM-dd HH:mm:ss") + "</td>");
				buffer.append("</tr>");
			}

			buffer.append("</tbody>");
			buffer.append("</table></td></tr>");

			buffer.append("</table>");
			response.getWriter().write(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * 获取工单详情信息
	 * 
	 * @param id
	 * @param resp
	 */
	@RequestMapping(value = "workorder/log/view/{id}/", method = RequestMethod.GET)
	@ResponseBody
	//@LogAspect(desc = "获取问题详情信息")
	public void workorderlog(@PathVariable String id, ServletResponse response) {
		try {
			WorkorderInfo workorder = workorderInfoService.getById(id);
			publicMethodService.setStatusColor(workorder);
			String exceptionid = workorder.getExceptionid();
			ExceptionInfo exception = null;
			if (exceptionid != null) {
				exception = exceptionInfoService.getById(exceptionid);
			}
			Map<String, Object> searchParams = new HashMap<String, Object>();
			searchParams.put("workid", id);
			searchParams.put("sortType", "OPERTIME desc");
			Page page = workorderLogService.getListByCondition(searchParams, 0, Page.DEFAULT_PAGE_SIZE - 5, "OPERTIME desc");
			Object obj = page.getResult();
			if (obj != null && obj instanceof List) {
				@SuppressWarnings("unchecked")
				List<WorkorderLog> list = (List<WorkorderLog>) obj;
				if (list != null && list.size() != 0) {
					for (WorkorderLog resource : list) {
						resource.setStatus(resource.getWorkstatus());
					}
				}
			}
			publicMethodService.setStatusColor(page);
			StringBuffer buffer = new StringBuffer();
			
			// 设备类型中文显示
			String deviceType = workorder.getDevicetype();
			if (("database").equals(deviceType)) {
				deviceType = "数据库";
			}
			if (("device").equals(deviceType)) {
				deviceType = "卡口";
			}
			if (("project").equals(deviceType)) {
				deviceType = "平台";
			}
			if (("server").equals(deviceType)) {
				deviceType = "服务器";
			}
			if (("ftp").equals(deviceType)) {
				deviceType = "FTP";
			}
			if (("cabinet").equals(deviceType)) {
				deviceType = "智能机柜";
			}

			buffer.append("<table class='table tingche-table table-border-rl table-border-bot'>");
			buffer.append("<tr>");
			buffer.append("<td class='device_td_bg2' style='width: 10%'>设备名称：</td><td style='width: 22%'>" + workorder.getDevicename() + "</td> ");
			buffer.append("<td class='device_td_bg2' style='width: 10%'>设备编号：</td><td style='width: 22%'>" + workorder.getDevicecode() + "</td>");
			buffer.append("<td class='device_td_bg2' style='width: 10%'>设备IP：</td><td style='width: 26%'>" + workorder.getDeviceip() + "</td> ");
			buffer.append("</tr>");
			buffer.append("<tr>");
			buffer.append("<td class='device_td_bg2'>设备类型：</td><td>" + deviceType + "</td>");
			buffer.append("<td class='device_td_bg2'>位置信息：</td><td>" + workorder.getPosition() + "</td>");
			String orgName = "";
			if (null != workorder.getOrgid()) {
				if (null != departmentService.getById(workorder.getOrgid())) {
					orgName = departmentService.getById(workorder.getOrgid()).getName();
				}
			}

			buffer.append("<td class='device_td_bg2'>所属部门：</td><td>" + orgName + "</td>");
			buffer.append("</tr>");
			buffer.append("<tr>");
			String userName = "";
			if (null != workorder.getAssignaccount()) {
				if (null != userService.getById(workorder.getAssignaccount())) {
					userName = userService.getById(workorder.getAssignaccount()).getName();
				}
			}

			buffer.append("<td class='device_td_bg2'>派发人：</td><td>" + userName + "</td>");
			buffer.append("<td class='device_td_bg2'>派发时间：</td><td>" + DateUtil.dateFormatToString(workorder.getAssigntime(), "yyyy-MM-dd HH:mm:ss") + "</td>");
			buffer.append("<td class='device_td_bg2'>工单状态：</td>");
			buffer.append("<td>" + workorder.getStatusHtml());
			if ("5".equals(workorder.getStatus())) {
				buffer.append("<font style='font-weight: bold;color: red;text-decoration: underline;'>遗留未关闭</font>");
			} else if ("7".equals(workorder.getStatus())) {
				buffer.append("<font style='font-size: large;font-weight: bolder;font-family: inherit;color: #3ECEF0;margin-left: 20px;'>评估得分：</font><font style='font-size: xx-large;font-weight: bolder;font-family: fantasy;color: ${color};'>" + workorder.getNote() + "</font>");
			}
			buffer.append("</td></tr>");
			buffer.append("<tr>");
			buffer.append("<td class='device_td_bg2'>是否场内：</td><td>");
			if ("0".equals(workorder.getIsouter())) {
				buffer.append("是");
			} else if ("1".equals(workorder.getIsouter())) {
				buffer.append("否");
			}
			buffer.append("</td>");
			buffer.append("<td class='device_td_bg2'>是否转派：</td><td>");
			if ("0".equals(workorder.getIsreassign())) {
				buffer.append("是");
			} else if ("1".equals(workorder.getIsreassign())) {
				buffer.append("否");
			}
			buffer.append("</td>");
			buffer.append("<td class='device_td_bg2'>是否遗留：</td><td>");
			if ("0".equals(workorder.getIsleave())) {
				buffer.append("是");
			} else if ("1".equals(workorder.getIsleave())) {
				buffer.append("否");
			}
			buffer.append("</td>");
			buffer.append("</tr>");
			
			String xungeng = "";
			if("0".equals(workorder.getXungeng())){
				xungeng = "未到场";
			}
			if("1".equals(workorder.getXungeng())){
				xungeng = "已到场";
			}
			buffer.append("<tr><td class='device_td_bg2'>巡更状态：</td><td>" + xungeng + "</td><td class='device_td_bg2'>巡更时间：</td><td>" + DateUtil.dateFormatToString(workorder.getXungengtime(), "yyyy-MM-dd HH:mm:ss") + "</td></tr>");
			buffer.append("<tr><td class='device_td_bg2'>短信内容：</td><td colspan='5'>" + workorder.getMessages() + "</td></tr>");
			buffer.append("</table>");

			buffer.append("<h4 class='xtcs_h4' style='margin:0;'>上报-详情</h4>");
			buffer.append("<table class='table tingche-table table-border-rl table-border-bot'>");
			buffer.append("<tr>");
			if (null != exception) {
				buffer.append("<td class='device_td_bg2' style='width: 10%'>上报人：</td><td style='width: 22%'>" + exception.getOperator() + "</td>");
				buffer.append("<td class='device_td_bg2' style='width: 10%'>上报时间：</td><td style='width: 22%'>" + DateUtil.dateFormatToString(exception.getReporttime(), "yyyy-MM-dd HH:mm:ss") + "</td>");
				buffer.append("<td class='device_td_bg2' style='width: 10%'>上报内容：</td><td style='width: 26%'>" + exception.getContent() + "</td>");
			} else {
				buffer.append("<td class='device_td_bg2' style='width: 10%'>上报人：</td><td style='width: 22%'>&nbsp;</td>");
				buffer.append("<td class='device_td_bg2' style='width: 10%'>上报时间：</td><td style='width: 22%'>&nbsp;</td>");
				buffer.append("<td class='device_td_bg2' style='width: 10%'>上报内容：</td><td style='width: 26%'>&nbsp;</td>");
			}

			buffer.append("</tr>");
			buffer.append("</table>");

			buffer.append("<h4 class='xtcs_h4' style='margin:0;'>工单关闭-详情</h4>");
			buffer.append("<table class='table tingche-table table-border-rl table-border-bot'>");
			buffer.append("<tr>");
			String offaccount = "";
			if (null != userService.getById(workorder.getOffaccount())) {
				offaccount = userService.getById(workorder.getOffaccount()).getName();
			}
			buffer.append("<td class='device_td_bg2' style='width: 10%'>关闭人：</td><td style='width: 22%'>" + offaccount + "</td>");
			buffer.append("<td class='device_td_bg2' style='width: 10%'>关闭时间：</td><td style='width: 22%'>" + DateUtil.dateFormatToString(workorder.getOfftime(), "yyyy-MM-dd HH:mm:ss") + "</td>");
			buffer.append("</tr>");
			buffer.append("</table>");

			buffer.append("<h4 class='xtcs_h4' style='margin:0;'>工单日志-详情</h4>");
			buffer.append("<table class='table table-striped table-bordered table-condensed table-style' id='table'>");
			buffer.append("<thead><tr><th>操作账号</th><th>操作姓名</th><th>操作时间</th><th>操作内容</th><th>工单状态</th><th>用时时长</th><th>备注信息</th></tr></thead>");
			buffer.append("<tbody id='tbody'>");

			Object new_obj = page.getResult();
			if (obj != null && obj instanceof List) {
				@SuppressWarnings("unchecked")
				List<WorkorderLog> list = (List<WorkorderLog>) new_obj;
				if (list != null && list.size() != 0) {
					for (int i = 0; i < list.size(); i++) {

						// 添加用时时长功能
						WorkorderLog resource = list.get(i);
						String toDate = DateUtil.dateFormatToString(resource.getOpertime(), "yyyy-MM-dd HH:mm:ss");
						SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

						// 将时长拼接为字符串
						String useTime = "";
						int useTimeSecs = 0;
						int day = 0;
						int minutes = 0;
						int hours = 0;

						if (i > 0) {
							// 计算上一步到此时这一步所用的时间长
							WorkorderLog resourceOne = list.get(i - 1);
							String fromDate = DateUtil.dateFormatToString(resourceOne.getOpertime(), "yyyy-MM-dd HH:mm:ss");

							try {

								long from = simpleFormat.parse(fromDate).getTime();
								long to = simpleFormat.parse(toDate).getTime();

								// 毫秒数
								useTimeSecs = (int) ((from - to) / (1000));
								// 转换为秒 分钟 小时 天
								if (useTimeSecs >= 60 && useTimeSecs < 60 * 60) {
									minutes = (int) ((from - to) / (1000 * 60));
									useTime = Integer.toString(minutes) + "分钟";
								}
								if (useTimeSecs >= 60 * 60 && useTimeSecs < 60 * 60 * 24) {
									hours = (int) ((from - to) / (1000 * 60 * 60));
									useTime = Integer.toString(hours) + "小时";
								}
								if (useTimeSecs >= 60 * 60 * 24) {
									day = (int) ((from - to) / (1000 * 60 * 60 * 24));
									useTime = Integer.toString(day) + "天";
								}
								if (useTimeSecs < 60) {
									useTime = Integer.toString(useTimeSecs) + "秒";
								}

							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						buffer.append("<tr>");
						buffer.append("<td>" + resource.getAccount() + "</td>");
						String operator = "";
						if (null != userService.getById(resource.getOperator())) {
							operator = userService.getById(resource.getOperator()).getName();
						}
						buffer.append("<td>" + operator + "</td>");
						buffer.append("<td>" + DateUtil.dateFormatToString(resource.getOpertime(), "yyyy-MM-dd HH:mm:ss") + "</td>");
						buffer.append("<td>" + resource.getOpercontent() + "</td>");
						buffer.append("<td>" + resource.getStatusHtml() + "</td>");

						buffer.append("<td>" + useTime + "</td>");

						String note = "";
						if (null != resource.getNote()) {
							note = resource.getNote();
						}
						//buffer.append("<td><div style='width:350px;overflow:hidden;float:left;' title='" + note + "'>" + note + "</div></td>");
						buffer.append("<td><span style='width:99%;word-break:normal;display:block;white-space:pre-wrap;overflow:hidden;'>" + note + "</span></td>");
						buffer.append("</tr>");
					}
				}
			}
			buffer.append("</tbody>");
			buffer.append("</table>");

			response.getWriter().write(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean getSpaceNodateStatus(List<MonthReport> list) {
		boolean flag = false;
		long num = 0;
		for (int n = 0; n < list.size(); n++) {
			MonthReport vo = list.get(n);
			BigDecimal[] days = vo.getDays();
			for (int j = 0; j < days.length; j++) {
				num += Double.valueOf(days[j].toString());
			}
		}

		if (num == 0) {
			flag = true;
		}
		return flag;
	}

	private void createLineDataXmlMonth(List<MonthReport> list, ModelMap model, List<String> mdays) {
		// 以下xml为生成局部的数据集合所用
		StringBuffer categoriexml = new StringBuffer("");
		categoriexml.append("<categories>");
		for (int i = 0; i < mdays.size(); i++) {
			categoriexml.append("<category name='" + mdays.get(i).substring(8, 10) + "日' />");
		}
		categoriexml.append("</categories>");
		if (list != null && list.size() > 0) {
			Set<MonthReport> set = new HashSet<MonthReport>();
			// 封装数据
			for (int n = 0; n < list.size(); n++) {
				MonthReport vo = list.get(n);
				StringBuffer datasetxml = new StringBuffer("");
				datasetxml.append("<dataset seriesname='" + (vo.getTypeName() != null ? vo.getTypeName().toString() : "") + "' color='" + color[n % 10] + "'  areaAlpha='50' showAreaBorder='1' areaBorderThickness='2' areaBorderColor='FF0000'>");
				BigDecimal[] days = vo.getDays();
				for (int j = 0; j < days.length; j++) {
					datasetxml.append("<set value='" + days[j] + "' />");
				}
				datasetxml.append("</dataset>");
				vo.setViewId(String.valueOf(n));
				vo.setDataXml(datasetxml.toString());
				set.add(vo);
			}
			model.addAttribute("datasetss", set);
		}
		model.addAttribute("categoriexml", categoriexml);
	}

	private MsLine2DChart getLineChart(String caption, String subCaption, String yname, String xname, String numberPrefix, String numberSuffix, String formatNumberScale, String decimalPrecision) {

		MsLine2DChart chart = new MsLine2DChart();
		chart.setHigh(IDDStorage.flahsChart_High);
		chart.setBgColor(IDDStorage.flahsChart_BgColor);
		chart.setCaption(caption);
		chart.setSubcaption(subCaption);
		chart.setYAxisName(yname);
		chart.setXAxisName(xname);
		chart.setNumberPrefix(numberPrefix);
		chart.setNumberSuffix(numberSuffix);
		chart.setFormatNumberScale(formatNumberScale);
		chart.setDecimalPrecision(decimalPrecision);
		// divlinecolor 网格颜色
		chart.setDivlinecolor("F47E00");
		// 面积边框色
		chart.setAreaBorderColor("DAE5F4");
		// numdivlines 刻度线标度数
		chart.setNumdivlines("4");
		chart.setNumVDivLines("29");
		chart.setShownames("1");
		chart.setVDivLineAlpha("30");
		return chart;
	}

	/***
	 * 实时获取状态值
	 * 
	 * @param req
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "getpercent/")
	@ResponseBody
	public void getpercent(HttpServletResponse resp) {
		// 获得资产健康度
		double percent = propertyPercentService.getAllpercent(1);
		try {
			JsonObject object = new JsonObject();
			object.addProperty("percent", percent);
			resp.getWriter().write(object.toString());
		} catch (IOException e) {
			logger.error(e);
		}
	}

}
