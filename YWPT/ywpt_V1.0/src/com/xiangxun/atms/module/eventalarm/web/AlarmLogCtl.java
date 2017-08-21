package com.xiangxun.atms.module.eventalarm.web;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.HttpRequest;
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
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.type.DataJson;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.JsonUtils;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.eventalarm.domain.AlarmLog;
import com.xiangxun.atms.module.eventalarm.service.AlarmLogService;
import com.xiangxun.atms.module.eventalarm.service.EventtypeInfoService;

/***
 * 告警日志信息
 * 
 * @author kouyunhao
 */
@Controller
@RequestMapping(value = "alarm/log")
public class AlarmLogCtl extends BaseCtl {

	@Resource
	AlarmLogService alarmLogService;

	@Resource
	EventtypeInfoService eventtypeInfoService;

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
		return "alarm/log/list";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "search/{type}/{menuid}/")
	public String list(@PathVariable String menuid, @PathVariable String type, ModelMap model, @RequestParam(value = "sortType", defaultValue = "ALARM_TIME desc") String sortType, @RequestParam(value = "page", defaultValue = "0") int pageNumber, ServletRequest request) {

		logger.info("正在进行告警日志信息列表查询。。。。。。");

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("sortType", sortType);
		searchParams.put("deviceType", type);
		Page page = null;
		if (type.equals("ftp") || type.equals("database") || type.equals("project")) {
			page = alarmLogService.getListByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE, sortType);
		} else {
			String deptid = getCurrentUser().getDeptId();
			if (deptid.equals("00")) {
				page = alarmLogService.getListByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE, sortType);
			} else {
				page = alarmLogService.getListByCondition(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE, sortType, menuid);
			}
		}
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
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		AlarmLog log = new AlarmLog();
		try {
			BeanUtils.populate(log, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("log", log);
		model.addAttribute("eventtypeList", eventtypeInfoService.findAll());
		return "alarm/log/search";
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
		AlarmLog log = alarmLogService.getById(id);
		String[] attribute = alarmLogService.findEventAttributes(log.getEventType());
		log.setEventTypeName(attribute[0]);
		log.setEventLevel(attribute[1]);
		log.setAlarmType(attribute[2]);
		log.setAlarmColor(attribute[3]);
		model.addAttribute("log", log);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "alarm/log/view";
	}

	/***
	 * 获得顶部报警数据
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "newAlarm", method = RequestMethod.GET)
	public @ResponseBody String newAlarm(HttpServletRequest request, HttpServletResponse response) {
		// String sql = "select * from ALARM_DEVICE_LOG where ALARM_TIME = (select max(ALARM_TIME) from ALARM_DEVICE_LOG) and rownum=1";
		// tianbo修改报警只显示配置了顶置显示的
		String sql = "select al.id,al.device_name,al.device_code,al.device_ip,al.device_type,al.is_outer,al.alarm_time,al.event_type from ALARM_DEVICE_LOG al,alarm_eventtype_info ai,alarm_eventlevel_info ait where ait.relation_type like '%003%' and ai.relation_level = ait.id and al.event_type = ai.code and ALARM_TIME = (select max(ALARM_TIME) from ALARM_DEVICE_LOG) and rownum=1 ";
		List<AlarmLog> log = alarmLogService.getNewAlarm(sql);
		// 赋值关联属性值
		AlarmLog vo = log.get(0);
		Date time = vo.getAlarmTime();
		vo.setAlarmTimeStr(DateUtil.dateFormatToString(time, "yyyy-MM-dd HH:mm:SS"));
		String[] attribute = alarmLogService.findEventAttributes(vo.getEventType());
		vo.setEventTypeName(attribute[0]);
		vo.setEventLevel(attribute[1]);
		vo.setAlarmType(attribute[2]);
		// 页面回显
		DataJson data = new DataJson();
		data.setData(vo);

		String jsonStr;
		try {
			data.setSuccess(true);
			jsonStr = JsonUtils.getJSONString(data);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonStr);
		} catch (Exception e) {
		}
		return null;
	}

	/***
	 * 获得悬浮框报警数据
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "newAlarmAll", method = RequestMethod.POST)
	public @ResponseBody String newAlarmAll(HttpServletRequest request, HttpServletResponse response) {
		// String sql = "select * from ALARM_DEVICE_LOG where ALARM_TIME = (select max(ALARM_TIME) from ALARM_DEVICE_LOG) and rownum=1";
		// tianbo修改报警只显示配置了顶置显示的
		String sql = "select tt.id,tt.device_name,tt.device_name,tt.device_code,tt.device_ip,tt.device_type,tt.is_outer,tt.alarm_time,tt.event_type from (select al.id,al.device_name,al.device_code,al.device_ip,al.device_type,al.is_outer,al.alarm_time,al.event_type,ROWNUM from ALARM_DEVICE_LOG al, alarm_eventtype_info  ai, alarm_eventlevel_info ait where ait.relation_type like '%003%' and ai.relation_level = ait.id and al.event_type = ai.code order by alarm_time desc)tt where ROWNUM <=30";
		List<AlarmLog> log = alarmLogService.getNewAlarm(sql);
		// 赋值关联属性值
		for (AlarmLog alarmLog : log) {
			Date time = alarmLog.getAlarmTime();
			alarmLog.setAlarmTimeStr(DateUtil.dateFormatToString(time, "yyyy-MM-dd HH:mm:SS"));
			String[] attribute = alarmLogService.findEventAttributes(alarmLog.getEventType());
			alarmLog.setEventTypeName(attribute[0]);
			alarmLog.setEventLevel(attribute[1]);
			alarmLog.setAlarmType(attribute[2]);
			alarmLog.setAlarmColor(attribute[3]);
		}
		// 页面回显
		DataJson data = new DataJson();
		data.setData(log);

		String jsonStr;
		try {
			data.setSuccess(true);
			jsonStr = JsonUtils.getJSONString(data);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonStr);
		} catch (Exception e) {
		}
		return null;
	}	

}
