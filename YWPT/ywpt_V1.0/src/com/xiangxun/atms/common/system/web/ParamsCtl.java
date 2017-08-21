package com.xiangxun.atms.common.system.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.common.system.service.ParamsService;
import com.xiangxun.atms.common.system.vo.SystemParams;
import com.xiangxun.atms.common.user.service.ThemeService;
import com.xiangxun.atms.common.user.vo.Theme;
import com.xiangxun.atms.framework.base.ApplicationContextHolder;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.module.gis.map.IMapOperation;
import com.xiangxun.atms.module.gis.service.impl.MapServiceImpl;

/***
 * 系统设置处理
 * 
 * @author zhouhaij
 * @May 6, 2013 3:33:09 PM
 */

@Controller
@RequestMapping("system/params")
public class ParamsCtl extends BaseCtl {

	@Resource
	ParamsService paramsService;

	@Resource
	ThemeService themeService;
	
	@Value("${cabinet.pattern}")
    String pattern; 
	/***
	 * 系统参数
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("syslist/{menuid}/")
	@RolesAllowed("ROLE_ADMINISTRATOR,ROLE_SYSTEM")
	public String syslist(@PathVariable String menuid, ModelMap model) {
		model.addAttribute("menuid", menuid);
		Map<String, String> maps = paramsService.getAllParams("1");
		String savedate = maps.get("savedate");
		if (savedate.equals("0")) {
			maps.remove("savedate");
			maps.put("savedate", "");
		}
		model.addAllAttributes(maps);
		model.addAttribute("cabinet_pattern",pattern);
		return "system/settings/sys_list";
	}

	@RequestMapping(value = "doUpdateSysParams", method = RequestMethod.POST)
	@LogAspect(desc = "修改系统参数")
	public String doUpdateSysParams(String menuid, HttpServletRequest req, RedirectAttributes redirectAttributes) {
		/***
		 * 1 表示卡口 2 表示监控
		 */

		// 田勃增加系统参数是否自动分派工单
		String openworkorder = req.getParameter("openworkorder");
		if (openworkorder == null) {
			openworkorder = "0";
		}
		processSystemParams("openworkorder", openworkorder, "1");

		String maxworder = req.getParameter("maxworder");
		if (maxworder == null) {
			openworkorder = "10";
		}
		processSystemParams("maxworder", maxworder, "1");

		// 田勃增加系统参数自动派发工单类型设置

		String[] orderType = req.getParameterValues("orderType");
				 
		
		String[] paramsOrderType =null;
		Integer[] valsOrderType=null;
		//判断机柜类型
		if(pattern.equals("1")){
			 paramsOrderType = new String[] {"torderCpuStatus", "torderMemoryStatus", "torderDiskStatus", "torderPowerStatus", "torderNetStatus", "torderDataStatus","tordertemperature","torderhumidityStatus","torderCabinetNetStatus","torderCabinetPowerStatus","tordersmokeStatus","tordershockStatus","torderACterminalStatus","torderACchazuoStatus"};
			valsOrderType = new Integer[] { 0, 0, 0, 0, 0, 0,0,0,0,0,0,0,0,0 };
			
		}else{
			paramsOrderType = new String[] {"torderCpuStatus", "torderMemoryStatus", "torderDiskStatus", "torderPowerStatus", "torderNetStatus", "torderDataStatus","tordertemperature","torderhumidityStatus","torderCabinetNetStatus","torderCabinetPowerStatus"};
			 valsOrderType = new Integer[] { 0, 0, 0, 0, 0, 0,0,0,0,0 };
		}

		if (orderType != null) {

			for (int i = 0; i < orderType.length; i++) {
				Integer val = Integer.parseInt(orderType[i]);
				valsOrderType[val - 1] = 1;
			}

			for (int j = 0; j < valsOrderType.length; j++) {
				processSystemParams(paramsOrderType[j], valsOrderType[j] + "", "1");
			}

		} else {

			// 当所有模块都没勾选时，设定为0
			for (int j = 0; j < paramsOrderType.length; j++) {
				processSystemParams(paramsOrderType[j], "0", "1");
			}
		}

		String[] values = req.getParameterValues("sysMondule");

		String[] params = new String[] { "opendevice", "openserver", "opendatabase", "openftp", "openproject", "openznjg" };
		Integer[] vals = new Integer[] { 0, 0, 0, 0, 0, 0 };

		if (values != null) {

			for (int i = 0; i < values.length; i++) {
				Integer val = Integer.parseInt(values[i]);
				vals[val - 1] = 1;
			}

			for (int j = 0; j < vals.length; j++) {
				processSystemParams(params[j], vals[j] + "", "1");
			}

		} else {

			// 当所有模块都没勾选时，设定为0
			for (int j = 0; j < params.length; j++) {
				processSystemParams(params[j], "0", "1");
			}
		}

		// 平台名称
		String itmsname = req.getParameter("itmsname");
		processSystemParams("itmsname", itmsname, "1");

		// 区域编码
		String areacode = req.getParameter("areacode");
		processSystemParams("areacode", areacode, "1");

		// 频发故障设备TOP数量
		String topnum = req.getParameter("topnum");
		processSystemParams("topnum", topnum, "1");

		// 频发故障设备TOP数量
		String netrefnum = req.getParameter("netrefnum");
		processSystemParams("netrefnum", netrefnum, "1");
		// 温度最小值
		String temrangemin = req.getParameter("icabinet_temperature_range_min");
		processSystemParams("icabinet_temperature_range_min", temrangemin, "1");

		// 温度最大值
		String temrangemax = req.getParameter("icabinet_temperature_range_max");
		processSystemParams("icabinet_temperature_range_max", temrangemax, "1");

		// 湿度最小值
		String humrangmin = req.getParameter("icabinet_humidity_range_min");
		processSystemParams("icabinet_humidity_range_min", humrangmin, "1");

		// 湿度最大值
		String humrangmax = req.getParameter("icabinet_humidity_range_max");
		processSystemParams("icabinet_humidity_range_max", humrangmax, "1");

		// 振动起始值
		String shorangmin = req.getParameter("icabinet_shock_range_min");
		processSystemParams("icabinet_shock_range_min", shorangmin, "1");

		// 振动结束值
		String shorangmax = req.getParameter("icabinet_shock_range_max");
		processSystemParams("icabinet_shock_range_max", shorangmax, "1");

		// AC插座电压起始值
		String volrangmin = req.getParameter("icabinet_ac_voltage_range_min");
		processSystemParams("icabinet_ac_voltage_range_min", volrangmin, "1");

		// AC插座电压结束值
		String volrangmax = req.getParameter("icabinet_ac_voltage_range_max");
		processSystemParams("icabinet_ac_voltage_range_max", volrangmax, "1");

		// AC插座电流起始值
		String currangmin = req.getParameter("icabinet_ac_current_range_min");
		processSystemParams("icabinet_ac_current_range_min", currangmin, "1");

		// AC插座电流结束值
		String currangmax = req.getParameter("icabinet_ac_current_range_max");
		processSystemParams("icabinet_ac_current_range_max", currangmax, "1");

		// 采样间隔
		String samplingTime = req.getParameter("icabinet_samplingtime");
		if (StringUtils.isEmpty(samplingTime))
			samplingTime = "10";
		processSystemParams("icabinet_samplingtime", samplingTime, "1");

		// 地图引擎设置add by kouyunhao 2014-02-13
		String mapEngineType = req.getParameter("mapEngineType");
		String serverurl = req.getParameter("serverurl");
		String resolutions = req.getParameter("resolutions");
		String centerandzoom = req.getParameter("centerandzoom");
		String maxextent = req.getParameter("maxextent");

		// 控制地图兴趣点查询类型选项 add by ZLT 2014-11-24
		String[] mapPoiParams = { "map_education_org", "map_enterprise", "map_government", "map_medical_place", "map_oil_station", "map_org", "map_scenic_spot", "map_supermarket", "map_township_gov", "map_pulbic_places", "map_road" };
		String[] pois = req.getParameterValues("map_poi");
		if (pois == null) {
			for (int i = 0, len = mapPoiParams.length; i < len; i++) {
				processSystemParams(mapPoiParams[i], "0", "1");
			}
		} else {
			Integer[] poisVal = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			for (int i = 0, len = pois.length; i < len; i++) {
				int poi = Integer.parseInt(pois[i]);
				poisVal[poi - 1] = 1;
			}
			for (int i = 0, len = mapPoiParams.length; i < len; i++) {
				processSystemParams(mapPoiParams[i], poisVal[i] + "", "1");
			}
		}
		////////

		processSystemParams("mapEngineType", mapEngineType, "1");
		// 将地图引擎配置信息存入application变量中，以供切换地图时用。add by kouyunhao 2014-02-13
		// 地图引擎设置。 0：ARCGIS 1：PIGIS 2：开源地图 3：超图地图。
		req.getSession().getServletContext().setAttribute("mapEngineType", mapEngineType);

		processSystemParams("map_center_zoom", centerandzoom, "1");
		processSystemParams("map_max_extent", maxextent, "1");
		// 注入地图接口实现类 xiongjie
		IMapOperation mapOperation = null;
		if ("0".equals(mapEngineType)) {
			processSystemParams("arcgis_rest_url", serverurl, "1");
			processSystemParams("arcgis_resolutions", resolutions, "1");
			try {
				mapOperation = (IMapOperation) ApplicationContextHolder.getBean("mapArcgisImpl");
			} catch (RuntimeException e) {
				// 当不使用地图的时候，获取对应的bean会报错，找不到对应的bean
			}
		} else if ("2".equals(mapEngineType)) {
			processSystemParams("geoserver_base_url", serverurl, "1");
			processSystemParams("geoserver_resolutions", resolutions, "1");
			try {
				mapOperation = (IMapOperation) ApplicationContextHolder.getBean("geoLayerOperationImpl");
			} catch (RuntimeException e) {
				// 当不使用地图的时候，获取对应的bean会报错，找不到对应的bean
			}
		} else if ("3".equals(mapEngineType)) {
			processSystemParams("supermap_base_url", serverurl, "1");
			// processSystemParams("supermap_resolutions",resolutions,"1");
			try {
				mapOperation = (IMapOperation) ApplicationContextHolder.getBean("superMapOperationImpl");
			} catch (RuntimeException e) {
				// 当不使用地图的时候，获取对应的bean会报错，找不到对应的bean
			}
		}
		MapServiceImpl mapService = (MapServiceImpl) ApplicationContextHolder.getBean("mapService");
		mapService.setMapOperation(mapOperation);
		// 注入地图接口实现类 end

		// 添加地图卡口报警查询时间间隔(时间间隔的单位为秒) xiongjie
		String mapAlarmTimeSpace = req.getParameter("mapAlarmTimeSpace");
		processSystemParams("mapAlarmTimeSpace", mapAlarmTimeSpace, "1");

		// 刷新系统参数全局变量
		Map<String, String> maps = paramsService.getAllParams("1");
		ParamsService.SYSTEM_PARAMS.putAll(maps);

		// 存入用户参数
		Map<String, String> userMaps = paramsService.getAllParams("0");
		ParamsService.SYSTEM_PARAMS.putAll(userMaps);

		maps.putAll(userMaps);

		req.getSession().getServletContext().setAttribute("atms", maps);

		redirectAttributes.addFlashAttribute("message", "设置成功");

		return "redirect:/system/params/syslist/" + menuid + "/";

	}

	/***
	 * 用户参数
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("userlist/{menuid}/")
	public String userlist(@PathVariable String menuid, ModelMap model) {
		Map<String, String> maps = paramsService.getAllParams("0");
		model.addAllAttributes(maps);
		model.addAttribute("menuid", menuid);
		// add by kouyunhao 2014-05-13 获取用户主题设置，用于前台页面展示。
		List<Theme> themeType = themeService.findByUser(getCurrentUserId());
		model.addAttribute("themeType", themeType != null && themeType.size() != 0 ? themeType.get(0).getTheme() : "");
		return "system/settings/user_list";
	}

	@RequestMapping(value = "doUpdateUserParams", method = RequestMethod.POST)
	@LogAspect(desc = "修改用户参数")
	public String doUpdateUserParams(String menuid, HttpServletRequest req, RedirectAttributes redirectAttributes) {
		// 图片抽点
		String[] autoStart = req.getParameterValues("pressImage");
		String pressImage = "0";
		if (autoStart != null) {
			pressImage = autoStart[0];
		}

		// 单点叠加文字
		String[] isSingleWaters = req.getParameterValues("isSingleWater");
		String isSingleWater = "0";
		if (isSingleWaters != null) {
			isSingleWater = isSingleWaters[0];
		}

		String boxWidth = req.getParameter("boxWidth");
		String position = req.getParameter("watermark_position");
		String size = req.getParameter("watermark_size");
		String color = req.getParameter("watermark_color");
		String backgroundColor = req.getParameter("watermark_backgroundColor");
		String canvasLeft = req.getParameter("watermark_canvasLeft");
		String canvasTop = req.getParameter("watermark_canvasTop");
		String canvasWidth = req.getParameter("watermark_canvasWidth");
		/*
		 * add by haoxiang 2014.08.22 增加水印叠加方式参数
		 */
		String canvasPosition = req.getParameter("watermark_canvasPosition");

		String zoneName = req.getParameter("zoneName") == null ? "0" : "1";
		String zoneLength = req.getParameter("zoneLength") == null ? "0" : "1";
		String zonePlate = req.getParameter("zonePlate") == null ? "0" : "1";
		String zoneRoadLimit = req.getParameter("zoneRoadLimit") == null ? "0" : "1";
		String zoneCarSpeed = req.getParameter("zoneCarSpeed") == null ? "0" : "1";
		String zoneRatio = req.getParameter("zoneRatio") == null ? "0" : "1";
		String zoneCarTime = req.getParameter("zoneCarTime") == null ? "0" : "1";

		processSystemParams("zoneName", zoneName, "0");
		processSystemParams("zoneLength", zoneLength, "0");
		processSystemParams("zonePlate", zonePlate, "0");
		processSystemParams("zoneRoadLimit", zoneRoadLimit, "0");
		processSystemParams("zoneCarSpeed", zoneCarSpeed, "0");
		processSystemParams("zoneRatio", zoneRatio, "0");
		processSystemParams("zoneCarTime", zoneCarTime, "0");

		processSystemParams("zonePressImage", req.getParameter("zonePressImage"), "0");
		processSystemParams("pressImage", pressImage, "0");
		processSystemParams("isSingleWater", isSingleWater, "0");
		processSystemParams("comfirmCombin", req.getParameter("comfirmCombin") == null ? "0" : "1", "0");
		processSystemParams("uncomfirmCombin", req.getParameter("uncomfirmCombin") == null ? "0" : "1", "0");
		processSystemParams("boxWidth", boxWidth, "0");
		processSystemParams("watermark_position", position, "0");
		processSystemParams("watermark_size", size, "0");
		processSystemParams("watermark_color", color, "0");
		processSystemParams("watermark_backgroundColor", backgroundColor, "0");
		processSystemParams("watermark_canvasLeft", canvasLeft, "0");
		processSystemParams("watermark_canvasTop", canvasTop, "0");
		processSystemParams("watermark_canvasWidth", canvasWidth, "0");

		/*
		 * add by haoxiang 2014.08.22 增加水印叠加方式参数
		 */
		processSystemParams("watermark_canvasPosition", canvasPosition, "0");

		// 刷新系统参数全局变量
		Map<String, String> maps = paramsService.getAllParams("0");
		ParamsService.SYSTEM_PARAMS.putAll(maps);

		// 刷新系统参数全局变量
		Map<String, String> sysmaps = paramsService.getAllParams("1");
		ParamsService.SYSTEM_PARAMS.putAll(sysmaps);

		maps.putAll(sysmaps);
		redirectAttributes.addFlashAttribute("message", "设置成功");
		req.getSession().getServletContext().setAttribute("atms", maps);

		return "redirect:/system/params/userlist/" + menuid + "/";
	}

	/**
	 * @param key
	 * @param value
	 * @param type
	 */
	private void processSystemParams(String key, String value, String type) {
		SystemParams systemParams = paramsService.getParamsByKeyAndType(key, type);
		if (!StringUtils.isEmpty(value)) {
			if (systemParams == null) {
				systemParams = new SystemParams();
			}
			systemParams.setName(key);
			// 1表示系统参数
			systemParams.setTypes(type);
			systemParams.setValue(value);
			if (StringUtils.isEmpty(systemParams.getId())) {
				systemParams.setId(UuidGenerateUtil.getUUIDLong());
				paramsService.save(systemParams);
			} else {
				paramsService.updateByIdSelective(systemParams);
			}
		}
	}

}
