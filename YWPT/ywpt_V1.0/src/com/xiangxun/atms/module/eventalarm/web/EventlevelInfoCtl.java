package com.xiangxun.atms.module.eventalarm.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

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
import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.eventalarm.domain.EventlevelInfo;
import com.xiangxun.atms.module.eventalarm.service.EventlevelInfoService;

/**
 * 事件级别
 * @author kouyunhao
 *
 */
@Controller
@RequestMapping(value = "alarm/eventlevel")
public class EventlevelInfoCtl extends BaseCtl {
	
	@Resource
	EventlevelInfoService eventlevelInfoService;
	
	@Resource
	DicService dicService;
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "name") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		logger.info("正在进行事件级别信息列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
		SessionParameter sp = new SessionParameter();
		sp.updateSessionMap(request, menuid, searchParams);
		Page page = eventlevelInfoService.getListByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);
		Object obj = page.getResult();
		if(obj!=null && obj instanceof List){
			@SuppressWarnings("unchecked")
			List<EventlevelInfo> list = (List<EventlevelInfo>)obj;
			if(list != null && list.size() != 0){
				for (EventlevelInfo resource : list) {
					String type = resource.getRelationType();
					if(type != null){
						String[] types = type.split(",");
						StringBuilder sb = new StringBuilder();
						for(String alarmtype : types){
							sb.append(dicService.getDicByCodeAndType(alarmtype, DicType.ALARMTYPE).getName());
							sb.append(",");
						}
						resource.setRelationType(StringUtils.removeEnd(sb.toString(), ","));
					}
				}
			}
		}
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		
		EventlevelInfo eventlevel = new EventlevelInfo();
		try {
			BeanUtils.populate(eventlevel, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		List<Dic> alarmtypeList = dicService.getDicByType(DicType.ALARMTYPE);
		JsonArray typeJsonArray = new JsonArray();
		JsonObject json = new JsonObject();
		json.addProperty("id","00");
		json.addProperty("pId","000");
		json.addProperty("name","全部");
		json.addProperty("code","00");
		json.addProperty("isParent",false);
		json.addProperty("checked","false");
		typeJsonArray.add(json);
		typeJsonArray = getTypeJsonArray(alarmtypeList, typeJsonArray);
        model.addAttribute("typeJsonArray", typeJsonArray.toString());
		request.getSession().setAttribute("alarmtypeList", alarmtypeList);
		model.addAttribute("eventlevel", eventlevel);
		
		List<Dic> eventLeverGradeType = dicService.getDicByType(DicType.EVENTLEVEL_GRADE_TYPE);
		request.getSession().setAttribute("eventLeverGradeType", eventLeverGradeType);
		
		return "alarm/eventlevel/list";
	}
	
	/**
	 * 组织页面需要的树结构json字符
	 * @param deptId
	 * @return
	 */
	private JsonArray getTypeJsonArray(List<Dic> typeList, JsonArray typeJsonArray) {
		for (Dic type : typeList) {
			JsonObject json = new JsonObject();
			json.addProperty("id",type.getCode());
			json.addProperty("pId","00");
			json.addProperty("name",type.getName());
			json.addProperty("code",type.getCode());
			json.addProperty("isParent",false);
			json.addProperty("checked","false");
			typeJsonArray.add(json);
		}
		return typeJsonArray;
	}
	
	
	@RequestMapping(value = "showTree/", method = RequestMethod.POST)
	public void showTree(@RequestParam(value = "id", defaultValue = "0")
	String id, ServletResponse resp) {
		logger.info("正在进行告警方式查询。。。。。。");
		JsonArray jsonArray = new JsonArray();
		JsonObject json = null;
		List<Dic> alarmtypeList = dicService.getDicByType(DicType.ALARMTYPE);
		if(alarmtypeList != null && alarmtypeList.size() != 0){
			for (Dic alarmtype : alarmtypeList) {
				json = new JsonObject();
				json.addProperty("id",alarmtype.getCode());
				json.addProperty("pid","0");
				json.addProperty("isParent",false);
				json.addProperty("name",alarmtype.getName());
				jsonArray.add(json);
			}
		}else{
			json = new JsonObject();
			json.addProperty("id","00");
			json.addProperty("pid","0");
			json.addProperty("name","文档管理");
			json.addProperty("isParent",false);
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
	 * 删除一个事件级别
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除了一个事件级别")
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在删除了一个事件级别。。。。。。");
			String[] id = ids.split(",");
			for (String string : id) {
				eventlevelInfoService.deleteById(string);
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	/***
	 * 添加一个事件级别
	 * @param eventlevel
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	@LogAspect(desc="添加一个事件级别")
	public String doAdd(EventlevelInfo eventlevel,String menuid,RedirectAttributes redirectAttributes){
		eventlevel.setId(UuidGenerateUtil.getUUID());
		eventlevelInfoService.save(eventlevel);
		redirectAttributes.addFlashAttribute("message","事件级别添加成功");
		return "redirect:/alarm/eventlevel/list/"+menuid+"/";
	}

	/***
	 * 获取待修改事件级别信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		EventlevelInfo eventlevel = eventlevelInfoService.getById(id);
		String type = eventlevel.getRelationType();
		if(type != null){
			String[] types = type.split(",");
			List<Dic> typeList = dicService.getDicByType(DicType.ALARMTYPE);
			String[] alarmtypes = new String[typeList.size()];
			for(int i = 0; i < typeList.size(); i++){
				for(int j = 0; j < types.length; j++){
					if(typeList.get(i).getCode().equals(types[j])){
						alarmtypes[i] = typeList.get(i).getCode();
					}
				}
			}
			model.addAttribute("types",alarmtypes);
		}
		model.addAttribute("eventlevel",eventlevel);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "alarm/eventlevel/update";
	}

	/***
	 * 修改事件级别信息
	 * @param eventlevel
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc="修改事件级别信息")
	public String doUpdate(@ModelAttribute("preload") EventlevelInfo eventlevel,String page,String menuid,RedirectAttributes redirectAttributes) {
		eventlevelInfoService.updateByIdSelective(eventlevel);
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/alarm/eventlevel/list/"+menuid+"/?page="+page;
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
		List<EventlevelInfo> eventlevelList = eventlevelInfoService.findByName(name);
		String returnStr = Boolean.FALSE.toString();
		if (eventlevelList.size() == 0) {
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
	 * 获取详情信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}/{menuid}/", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		EventlevelInfo eventlevel = eventlevelInfoService.getById(id);
		String type = eventlevel.getRelationType();
		if(type != null){
			String[] types = type.split(",");
			List<Dic> typeList = dicService.getDicByType(DicType.ALARMTYPE);
			String[] alarmtypes = new String[typeList.size()];
			for(int i = 0; i < typeList.size(); i++){
				for(int j = 0; j < types.length; j++){
					if(typeList.get(i).getCode().equals(types[j])){
						alarmtypes[i] = typeList.get(i).getCode();
					}
				}
			}
			model.addAttribute("types",alarmtypes);
		}
		model.addAttribute("eventlevel", eventlevel);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "alarm/eventlevel/view";
	}

}
