package com.xiangxun.atms.module.property.web;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

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
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.DeviceTypeInfo;
import com.xiangxun.atms.module.property.service.DeviceTypeInfoService;
import com.xiangxun.atms.module.property.service.DeviceTypeService;

/***
 * 设备类型 维护
 * @author YanTao
 * @Apr 29, 2013 5:39:59 PM
 */
@Controller
@RequestMapping(value="property/devicetypeinfo")
public class DeviceTypeInfoCtl extends BaseCtl{

	@Resource
	DeviceTypeInfoService deviceTypeInfoService;
	
	@Resource
	DeviceTypeService deviceTypeService;
	
	@Resource
	private Validator validator;
	
	@RequestMapping(value="list/{menuid}/",method = RequestMethod.GET)
	public String list(@PathVariable String menuid,ModelMap modal){
		modal.addAttribute("menuid",menuid);
		return "property/devicetypeinfo/list";
	}
	
	/***************************************************************************
	 * 跳转到iframe上一级页面 add by kouyunhao 2013-07-18
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="sublist/{menuid}/",method = RequestMethod.GET)
	public String iframe_list(@PathVariable String menuid,ModelMap modal){
		modal.addAttribute("menuid",menuid);
		return "property/devicetypeinfo/sublist";
	}
	
	@RequestMapping(value="showTree/",method = RequestMethod.POST)
	public void showTree(@RequestParam(value="id",defaultValue="0") String id,ServletResponse resp){
		logger.info("正在进行设备类型信息查询。。。。。。");
		
		List<DeviceTypeInfo> deviceTypeInfoList =  deviceTypeInfoService.getChildren(id);
		List<DeviceTypeInfo> deviceTypeList =  deviceTypeInfoService.findAll();
		JsonArray jsonArray = new JsonArray();
		if(deviceTypeInfoList != null && deviceTypeInfoList.size() != 0){
			for (DeviceTypeInfo deviceTypeInfo : deviceTypeInfoList) {
				JsonObject json = new JsonObject();
				json.addProperty("id",deviceTypeInfo.getId());
				json.addProperty("isParent",deviceTypeInfoService.hasChild(deviceTypeInfo.getId()));
				json.addProperty("name",deviceTypeInfo.getName());
				jsonArray.add(json);
			}
		}else{
			JsonObject json = new JsonObject();
			json.addProperty("id","00");
			json.addProperty("name","卡口设备功能");
			if(deviceTypeList != null && deviceTypeList.size() != 0){
				json.addProperty("isParent",true);
			}else{
				json.addProperty("isParent",false);
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
	
	
	@RequestMapping(value="doAdd/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="添加新设备类型")
	public String doAdd(DeviceTypeInfo deviceTypeInfo,ModelMap modal,RedirectAttributes redirectAttributes,@PathVariable String menuid){
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DeviceTypeInfo>> failures = validator.validate(deviceTypeInfo);
		//=========================== edit by kouyunhao 2013-08-12 更改业务逻辑错误和页面提示信息错误。start================//
		if (failures.isEmpty()) {
			deviceTypeInfo.setId(UuidGenerateUtil.getUUID());
			deviceTypeInfoService.save(deviceTypeInfo);
			redirectAttributes.addFlashAttribute("message","设备类型添加成功");
		}
		//=========================== edit by kouyunhao 2013-08-12 更改业务逻辑错误和页面提示信息错误。end================//
		/**
		 if (!failures.isEmpty()) {
			redirectAttributes.addAttribute("message","设备类型添加成功");
		}
		deviceTypeInfo.setId(UuidGenerateUtil.getUUID());
		deviceTypeInfoService.save(deviceTypeInfo); 
		 */
		return "redirect:/property/devicetypeinfo/sublist/"+menuid+"/";
	}
	
	/***
	 * 获取待修改设备类型信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model,RedirectAttributes redirectAttributes) {
		if(id.equals("00")){
			redirectAttributes.addFlashAttribute("message", "设备功能根节点不允许修改");
			return "redirect:/property/devicetypeinfo/sublist/"+menuid+"/?page="+page;
		}
		model.addAttribute("deviceTypeInfo",deviceTypeInfoService.getById(id));
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "property/devicetypeinfo/update";
	}

	
	/***
	 * 修改设备类型
	 * @param role
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc="修改设备类型")
	public String doUpdate(@ModelAttribute("preloadDepartment") DeviceTypeInfo deviceTypeInfo,String page,String menuid,RedirectAttributes redirectAttributes) {
		deviceTypeInfoService.updateByIdSelective(deviceTypeInfo);
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/property/devicetypeinfo/sublist/"+menuid+"/?page="+page;
	}
	
	/***
	 * 删除设备类型
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除设备类型")
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在进行设备类型数据删除。。。。。。");
			String[] id = ids.split(",");
			StringBuffer returnBuffer = null;
			for (String string : id) {
				//=============================edit by kouyunhao 2013-0-10-21 更改业务逻辑，解决删除设备功能节点，而该节点下存在设备时页面报错bug===========//
				//List<DeviceTypeInfo> list = deviceTypeInfoService.getChildren(string);
				boolean hasChild = deviceTypeInfoService.hasChild(string);
				if(hasChild){
					entity.setResult("hasChild");
					return entity;
				}else{
					List<DeviceInfo> list = deviceTypeService.getDeviceByTypeId(string);
					if(list!=null && list.size()>0){
						returnBuffer = new StringBuffer();
						for(DeviceInfo device : list){
							returnBuffer.append("[");
							returnBuffer.append(device.getName());
							returnBuffer.append("]");
							returnBuffer.append(",");
						}
					}
				}
			}
			if(returnBuffer != null){
				entity.setResult("exist");
				entity.setMessage(org.apache.commons.lang.StringUtils.removeEnd(returnBuffer.toString(), ","));
				return entity;
			}else{
				for (String string : id) {
					deviceTypeInfoService.deleteById(string);
				}
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			entity.setMessage("发生异常！");
			return entity;
		}
	}
	
	/***
	 * 先根据form的id从数据库查出对象，再把Form提交的内容绑定到该对象上
	 * @param id
	 * @return
	 */
	@ModelAttribute("preloadDepartment")
	public DeviceTypeInfo getDepartment(@RequestParam(value = "id", required = false) String id) {
		if (id != null) {
			return deviceTypeInfoService.getById(id);
		}
		return null;
	}
	
	@RequestMapping(value="gettype/{deviceId}")
	@ResponseBody
	public List<DeviceTypeInfo> getTypeByDeviceId(@PathVariable String deviceId) {
		return deviceTypeService.getTypeByDeviceId(deviceId);
	}
	
}
