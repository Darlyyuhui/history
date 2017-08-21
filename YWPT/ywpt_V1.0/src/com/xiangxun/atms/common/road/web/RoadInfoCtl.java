package com.xiangxun.atms.common.road.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.common.road.service.RoadInfoService;
import com.xiangxun.atms.common.road.vo.RoadInfo;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.DicComparator;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.util.ZhongWenToPinYin;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.gis.domain.LayerBean;
import com.xiangxun.atms.module.gis.domain.LayerEnum;
import com.xiangxun.atms.module.gis.service.IMapService;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.VideoInfo;
import com.xiangxun.atms.module.property.service.DeviceInfoService;
import com.xiangxun.atms.module.property.service.VideoInfoService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/*******************************************************************************
 * 道路信息维护
 * 
 * @author YanTao
 * @Apr 29, 2013 5:39:59 PM
 */
@Controller
@RequestMapping(value = "system/road")
public class RoadInfoCtl extends BaseCtl {

	@Resource
	DeviceInfoService deviceInfoService;
	
	@Resource
	VideoInfoService videoService;
	
	@Resource
	RoadInfoService roadInfoService;

	@Resource
	private Validator validator;

	@Resource
	DicService dicService;

	@Resource
	IMapService mapService;

	@Resource
	FreeMarkerConfigurer freeMarkerConfigurer;

	@RequestMapping(value = "getbyid/{id}")
	@ResponseBody
	public RoadInfo getRoadById(@PathVariable
	String id) {
		return roadInfoService.getById(id);
	}

	@RequestMapping(value = "list/{menuid}/", method = RequestMethod.GET)
	public String list(@PathVariable
	String menuid, ModelMap modal) {
		modal.addAttribute("menuid", menuid);
		return "system/road/list";
	}

	/***************************************************************************
	 * 跳转到iframe上一级页面 add by kouyunhao 2013-08-02
	 * 
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "sublist/{menuid}/", method = RequestMethod.GET)
	public String iframe_list(@PathVariable
	String menuid, ModelMap modal) {
		modal.addAttribute("menuid", menuid);
		return "system/road/sublist";
	}

	@RequestMapping(value = "showTree/", method = RequestMethod.POST)
	public void showTree(@RequestParam(value = "id", defaultValue = "0")
	String id, ServletResponse resp) {
		logger.info("正在进行道路信息查询。。。。。。");

		List<RoadInfo> roadInfoList = roadInfoService.getChildren(id);
		List<RoadInfo> roadListAll = roadInfoService.findAll();
		/**
		 * add by kouyunhao 2014-09-10 添加排序，解决道路树无序混乱状态（宜昌需求）
		 */
//		roadInfoList = sort(roadInfoList);
//		roadListAll = sort(roadListAll);
		
		JsonArray jsonArray = new JsonArray();
		if(roadInfoList != null && roadInfoList.size() != 0){
			for (RoadInfo roadInfo : roadInfoList) {
				JsonObject json = new JsonObject();
				json.addProperty("id", roadInfo.getId());
				json.addProperty("pid", roadInfo.getPid());
				json.addProperty("isParent", roadInfoService.hasChild(roadInfo
						.getId()));
				json.addProperty("name", roadInfo.getName());
				if (!roadInfoService.hasChild(roadInfo.getId())) {
					json.addProperty("icon", "../../../../images/road.png");
				}else{
					json.addProperty("icon", "../../../../images/icon1.gif");
					json.addProperty("iconClose", "../../../../images/road_down.png");
					json.addProperty("iconOpen", "../../../../images/road_up.png");
				}
				jsonArray.add(json);
			}
		}else{
			JsonObject json = new JsonObject();
			json.addProperty("id", "00");
			json.addProperty("pid", "0");
			json.addProperty("name", "道路信息");
			if(roadListAll != null && roadListAll.size() != 0){
				json.addProperty("isParent",true);
			}else{
				json.addProperty("isParent",false);
			}
			if (!roadInfoService.hasChild("00")) {
				json.addProperty("icon", "../../../../images/road.png");
			}else{
				json.addProperty("icon", "../../../../images/icon1.gif");
				json.addProperty("iconClose", "../../../../images/road_down.png");
				json.addProperty("iconOpen", "../../../../images/road_up.png");
			}
			jsonArray.add(json);
		}
		logger.debug("response json:{0}", jsonArray.toString());
		try {
			resp.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * add by kouyunhao 2014-09-10 道路信息排序
	 * @param list
	 * @return
	 */
	public List<RoadInfo> sort(List<RoadInfo> list){
		List<RoadInfo> returnList = new ArrayList<RoadInfo>();
		Map<String,RoadInfo> roadMap = new HashMap<String, RoadInfo>();
		String[] pinyinArr = new String[list.size()];
		for(int i = 0; i < list.size(); i++){
			String pinyin = ZhongWenToPinYin.getPinyinCapitalize(list.get(i).getName());
			pinyinArr[i] = pinyin;
			roadMap.put(pinyin, list.get(i));
		}
		List<String> sorted = ZhongWenToPinYin.sort(pinyinArr);
		for(String s : sorted){
			Iterator<String> it = roadMap.keySet().iterator();
			while(it.hasNext()){
				String next = it.next();
				if(s.equals(next)){
					returnList.add(roadMap.get(next));
				}
			}
		}
		return returnList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "showadd/{menuid}/{roadid}/", method = RequestMethod.GET)
	public String showRoadInfoAdd(@PathVariable("menuid")
	String menuid, @PathVariable("roadid")
	String roadid, String page, Model model,
			RedirectAttributes redirectAttributes) {
		if (!roadid.equals("00")) {
			// ========================== add by kouyunhao 2013-08-12
			// 添加验证，阻止二级道路下再添加道路信息start===============//
			RoadInfo fatherRoad = roadInfoService.getById(roadInfoService.getById(
					roadid).getPid());
			if (fatherRoad != null) {
				String gfatherid = roadInfoService.getById(
						roadInfoService.getById(roadid).getPid()).getPid();
				if (gfatherid.equals("00")) {
					redirectAttributes.addFlashAttribute("message",
					"二级道路下不允许再添加道路信息");
					return "redirect:/system/road/sublist/" + menuid + "/";
				}
			}
		} 
		// ========================== add by kouyunhao 2013-08-12
		// 添加验证，阻止二级道路下再添加道路信息end===============//
		// 从字典获得 方向信息 网络运营商 接入方式
		List<Dic> roadTypeList = dicService.getDicByType(DicType.ROAD_TYPE);
		// 对道路类型进行按照编号排序
		if (roadTypeList != null && roadTypeList.size() > 0) {
			DicComparator dicComparator = new DicComparator();
			Collections.sort(roadTypeList, dicComparator);
		}
		model.addAttribute("roadTypeList", roadTypeList);
		model.addAttribute("menuid", menuid);
		model.addAttribute("pid", roadid);
		if (roadid.equals("00")) {
			return "system/road/addgroup";
		}else{
			return "system/road/add";
		}
	}

	@RequestMapping(value = "doAdd/{menuid}/", method = RequestMethod.POST)
	@LogAspect(desc = "添加新道路")
	public String doAdd(RoadInfo roadInfo, ModelMap modal,
			RedirectAttributes redirectAttributes, @PathVariable
			String menuid) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<RoadInfo>> failures = validator
				.validate(roadInfo);
		if (!failures.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "道路添加成功");
		}
		roadInfo.setId(UuidGenerateUtil.getUUID());
		roadInfo.setStatus("0");
		String roadType = roadInfo.getCoderoadtype();
		//====================== eidt by kouyunhao 2013-12-05 解决道路信息修改时upload为空bug。================================//
		//String roadType = roadInfo.getCodeRoadType();
		String roadDh = roadInfo.getCodeRoadDh();
		String roadZh = roadInfo.getCodeRoadZh();
		String roadMi = roadInfo.getCodeRoadMi();

		if (!StringUtils.isEmpty(roadType) && !StringUtils.isEmpty(roadDh)
				&& !StringUtils.isEmpty(roadZh) && !StringUtils.isEmpty(roadMi)) {
			roadInfo.setUploadcode(roadType + roadDh + roadZh + roadMi);
		}

		roadInfoService.save(roadInfo);
		redirectAttributes.addFlashAttribute("message", "道路添加成功");
		return "redirect:/system/road/sublist/" + menuid + "/";
	}

	/***************************************************************************
	 * 获取待修改道路信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id")
	String id, @PathVariable("menuid")
	String menuid, String page, Model model) {
		RoadInfo roadInfo = roadInfoService.getById(id);

		// 需要解析UPLOADCODE字段
		String uploadCode = roadInfo.getUploadcode();
		if (uploadCode != null && !"".equals(uploadCode)) {
			roadInfo.setCoderoadtype(uploadCode.substring(0, 1));
			//====================== eidt by kouyunhao 2013-12-05 解决道路信息修改时upload为空bug。================================//
			//roadInfo.setCodeRoadType(uploadCode.substring(0, 1));
			roadInfo.setCodeRoadDh(uploadCode.substring(1, 5));
			roadInfo.setCodeRoadZh(uploadCode.substring(5, 9));
			roadInfo.setCodeRoadMi(uploadCode.substring(9, 12));
		}

		// 从字典获得 方向信息 网络运营商 接入方式
		List<Dic> roadTypeList = dicService.getDicByType(DicType.ROAD_TYPE);
		model.addAttribute("roadTypeList", roadTypeList);

		model.addAttribute("roadInfo", roadInfo);
		model.addAttribute("menuid", menuid);
		if (roadInfo.getPid().equals("00")) {
			return "system/road/updategroup";
		} else {
			return "system/road/update";
		}
	}

	/***************************************************************************
	 * 修改道路
	 * 
	 * @param role
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc = "修改道路")
	public String doUpdate(@ModelAttribute("preloadDepartment")
	RoadInfo roadInfo, String page, String menuid,
			RedirectAttributes redirectAttributes) {
		
		String roadType = roadInfo.getCoderoadtype();
		String roadDh = roadInfo.getCodeRoadDh();
		String roadZh = roadInfo.getCodeRoadZh();
		String roadMi = roadInfo.getCodeRoadMi();

		if (!StringUtils.isEmpty(roadType) && !StringUtils.isEmpty(roadDh)
				&& !StringUtils.isEmpty(roadZh) && !StringUtils.isEmpty(roadMi)) {
			roadInfo.setUploadcode(roadType + roadDh + roadZh + roadMi);
			// ==================== add by kouyunhao 2013-08-07
			// 同步修改coderodetype。
			roadInfo.setCoderoadtype(roadType);
		}
		roadInfoService.updateByIdSelective(roadInfo);
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/system/road/sublist/" + menuid + "/?page=" + page;
	}

	/***************************************************************************
	 * 删除道路
	 * 
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value = "delete/{id}/", method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc = "删除道路")
	public ResponseEntity delete(@PathVariable String id,HttpServletRequest request) {
		ResponseEntity entity = new ResponseEntity();
		try {
			logger.info("正在进行道路数据删除。。。。。。");
			//删除前判断是否道路下存在设备
			List<DeviceInfo> list = deviceInfoService.findDevlistByRoadId(id);
			if(list!=null && list.size()>0){
				entity.setResult("error");
				entity.setMessage("要删除的道路上已添加卡口设备，不能直接删除！");
				return entity;
			}
			List<VideoInfo> vlist = videoService.findByRoadId(id);
			if(vlist!=null && vlist.size()>0){
				entity.setResult("error");
				entity.setMessage("要删除的道路上已添加监控设备，不能直接删除！");
				return entity;
			}
			List<RoadInfo> roadinfos = roadInfoService.getChildren(id);
			if (roadinfos.size() > 0) {
				entity.setResult("error");
				entity.setMessage("要删除的节点存在子节点，不能直接删除！");
				return entity;
			} else {
				RoadInfo rinfo = roadInfoService.getById(id);
				roadInfoService.deleteById(id, rinfo);
				//检查SESSION中有没有缓存ROADID
				Object sessionParams = request.getSession().getAttribute("searchParams");
				if(sessionParams != null){
					Map<String, Object> paramsMap = (Map<String, Object>) sessionParams;
					if(paramsMap.get("roadId") != null){
						String roadId = paramsMap.get("roadId").toString();
						if(id.equals(roadId)){
							paramsMap.put("roadId", null);
						}
					}
				}
				entity.setResult("ok");
				return entity;
			}
		} catch (Exception e) {
			entity.setResult("error");
			entity.setMessage("发生异常！");
			return entity;
		}
	}

	/***************************************************************************
	 * 先根据form的id从数据库查出对象，再把Form提交的内容绑定到该对象上
	 * 
	 * @param id
	 * @return
	 */
	@ModelAttribute("preloadDepartment")
	public RoadInfo getDepartment(@RequestParam(value = "id", required = false)
	String id) {
		if (id != null) {
			return roadInfoService.getById(id);
		}
		return null;
	}
	
	/**
	 * 获取详情
	 * @param id
	 * @param model
	 * @return
	 */
	/**
	 * 获取详情
	 * @author kouyunhao
	 * @version 1.0
	 * @param id
	 * @param menuid
	 * @param model
	 * @return
	 * Nov 21, 2013
	 */
	@RequestMapping(value = "view/{id}/{menuid}/", method = RequestMethod.GET)
	public String showView(@PathVariable("id") String id, @PathVariable("menuid") String menuid, String page, Model model) {
		RoadInfo roadInfo = roadInfoService.getById(id);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		model.addAttribute("roadInfo", roadInfo);
		return "system/road/view";
	}

	/**
	 * 道路名称重复校验
	 * @author kouyunhao
	 * @version 1.0
	 * @param req
	 * @param name
	 * @return
	 * Nov 21, 2013
	 */
	@RequestMapping(value = "groupNameExist")
	@ResponseBody
	public String codeExist(HttpServletRequest req,
			@RequestParam(value = "name")
			String name) {
		List<RoadInfo> roadInfoList = roadInfoService.findByRoadname(name);
		String returnStr = Boolean.FALSE.toString();
		if (roadInfoList.size() == 0) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		// 不为空说明是修改
		if (org.apache.commons.lang.StringUtils.isNotBlank(oper)) {
			if (name.equals(oper)) {
				return Boolean.TRUE.toString();
			}
		}
		return returnStr;
	}
	
	/**
	 * 上传编号重复校验
	 * @author kouyunhao
	 * @version 1.0
	 * @param response
	 * @param uploadcode
	 * @return
	 * Nov 21, 2013
	 */
	@RequestMapping(value = "uploadcodeExist/{uploadcode}/",method = RequestMethod.GET)
	public void uploadcodeExist( @PathVariable String uploadcode, ServletResponse response){
		JsonObject json = new JsonObject();
		String message = "";
		List<RoadInfo> roadInfoList = roadInfoService.findByUploadcode(uploadcode);
		if(roadInfoList != null && roadInfoList.size() != 0){
			message = "上传编号已存在，请重新输入！";
		}
		json.addProperty("message", message);
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***************************************************************************
	 * 判断道路名称是否正确
	 * 
	 * @param req
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "roadNameExist/{roadname}/")
	@ResponseBody
	public void roadNameExist(HttpServletResponse resp, @PathVariable
	String roadname) {
		// 调用GIS接口
		List<LayerBean> mapRoadList = null;
		try {
			//====================== eidt by kouyunhao 2013-12-05 解决客户端传入道路名称乱码bug。================================//
			roadname = StringUtils.getUTF8(roadname);
			mapRoadList = mapService.getByName(LayerEnum.ROAD_LINE, roadname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 如果有值，则封装成LIST
		List<String> list = null;
		if(null != mapRoadList) {
			list = new ArrayList<String>();
			boolean flag = false;
			for(int i=0,len=mapRoadList.size(); i<len; i++) {
				flag = false;
				LayerBean mapRoad = mapRoadList.get(i);
				// 剔除重复的道路名称
				for(int j=0,jl=list.size(); j<jl; j++) {
					if(list.get(j).equals(mapRoad.getName())) {
						flag = true;
						break;
					}
				}
				if(!flag)list.add(mapRoad.getName());
			}
		}

		if (list != null && list.size() > 0) {
			Configuration config = freeMarkerConfigurer.getConfiguration();
			Map<String, List<String>> menuModel = new HashMap<String, List<String>>();

			try {
				// 利用freemarker进行字符处理得到菜单
				menuModel.put("roadnames", list);
				Template template = config.getTemplate("/ftl/roadnamemenu.ftl");
				String htmlstr = FreeMarkerTemplateUtils
						.processTemplateIntoString(template, menuModel);
				JsonObject object = new JsonObject();
				object.addProperty("content", htmlstr);
				resp.getWriter().write(object.toString());
			} catch (IOException e) {
				logger.error(e);
			} catch (TemplateException e) {
				logger.error(e);
			}
		} else {
			try {
				JsonObject object = new JsonObject();
				object.addProperty("content",
						"<font color=red>没有匹配到GIS库中的道路,请重新输入!</font>");
				resp.getWriter().write(object.toString());
			} catch (IOException e) {
				logger.error(e);
			}
		}

	}
}
